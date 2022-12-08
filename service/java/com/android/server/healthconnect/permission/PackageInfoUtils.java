/*
 * Copyright (C) 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.server.healthconnect.permission;

import static android.content.pm.PackageManager.GET_PERMISSIONS;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.healthconnect.HealthConnectManager;
import android.os.UserHandle;
import android.os.UserManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/** Utility class with PackageInfo-related methods for {@link FirstGrantTimeManager} */
class PackageInfoUtils {
    private static final String TAG = "HealthConnectPackageInfoUtils";

    /**
     * Store PackageManager for each user. Keys are users, values are PackageManagers which get from
     * each user.
     */
    private final Map<UserHandle, PackageManager> mUsersPackageManager = new ArrayMap<>();

    private final Context mContext;

    PackageInfoUtils(Context context) {
        mContext = context;
    }

    @NonNull
    Map<String, Set<Integer>> collectSharedUserNameToUidsMapping(
            @NonNull Map<UserHandle, List<PackageInfo>> userHandleToPackageInfo) {
        Map<String, Set<Integer>> sharedUserNameToUids = new ArrayMap<>();
        for (List<PackageInfo> healthPackagesInfos : userHandleToPackageInfo.values()) {
            for (PackageInfo info : healthPackagesInfos) {
                if (info.sharedUserId != null) {
                    if (sharedUserNameToUids.get(info.sharedUserId) == null) {
                        sharedUserNameToUids.put(info.sharedUserId, new ArraySet<>());
                    }
                    sharedUserNameToUids.get(info.sharedUserId).add(info.applicationInfo.uid);
                }
            }
        }
        return sharedUserNameToUids;
    }

    @NonNull
    Map<UserHandle, List<PackageInfo>> getPackagesHoldingHealthPermissions() {
        // TODO(b/260707328): replace with getPackagesHoldingPermissions
        Map<UserHandle, List<PackageInfo>> userToHealthAppsInfo = new ArrayMap<>();
        for (UserHandle user : getAllUserHandles()) {
            List<PackageInfo> allInfos =
                    getPackageManagerAsUser(user)
                            .getInstalledPackages(
                                    PackageManager.PackageInfoFlags.of(GET_PERMISSIONS));
            List<PackageInfo> healthAppsInfos = new ArrayList<>();

            for (PackageInfo info : allInfos) {
                if (anyRequestedHealthPermissionGranted(info)) {
                    healthAppsInfos.add(info);
                }
            }
            userToHealthAppsInfo.put(user, healthAppsInfos);
        }

        return userToHealthAppsInfo;
    }

    boolean hasGrantedHealthPermissions(@NonNull String[] packageNames, @NonNull UserHandle user) {
        for (String packageName : packageNames) {
            PackageInfo info = getPackageInfoWithPermissionsAsUser(packageName, user);
            if (anyRequestedHealthPermissionGranted(info)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    String[] getPackagesForUid(@NonNull int packageUid, @NonNull UserHandle user) {
        return getPackageManagerAsUser(user).getPackagesForUid(packageUid);
    }

    private boolean anyRequestedHealthPermissionGranted(@NonNull PackageInfo packageInfo) {
        if (packageInfo == null || packageInfo.requestedPermissions == null) {
            Log.w(TAG, "Can't extract requested permissions from the package info.");
            return false;
        }

        for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
            String currPerm = packageInfo.requestedPermissions[i];
            if (HealthConnectManager.isHealthPermission(mContext, currPerm)
                    && ((packageInfo.requestedPermissionsFlags[i]
                                    & PackageInfo.REQUESTED_PERMISSION_GRANTED)
                            != 0)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    PackageInfo getPackageInfoWithPermissionsAsUser(
            @NonNull String packageName, @NonNull UserHandle user) {
        try {
            return getPackageManagerAsUser(user)
                    .getPackageInfo(
                            packageName, PackageManager.PackageInfoFlags.of(GET_PERMISSIONS));
        } catch (PackageManager.NameNotFoundException e) {
            // App not found.
            Log.e(TAG, "NameNotFoundException for " + packageName);
            return null;
        }
    }

    @Nullable
    String getSharedUserNameFromUid(int uid) {
        String[] packages =
                mUsersPackageManager
                        .get(UserHandle.getUserHandleForUid(uid))
                        .getPackagesForUid(uid);
        if (packages == null || packages.length == 0) {
            Log.e(TAG, "Can't get package names for UID: " + uid);
            return null;
        }
        try {
            PackageInfo info =
                    getPackageManagerAsUser(UserHandle.getUserHandleForUid(uid))
                            .getPackageInfo(packages[0], PackageManager.PackageInfoFlags.of(0));
            return info.sharedUserId;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package " + packages[0] + " not found.");
            return null;
        }
    }

    @Nullable
    String getPackageNameFromUid(int uid) {
        String[] packages =
                mUsersPackageManager
                        .get(UserHandle.getUserHandleForUid(uid))
                        .getPackagesForUid(uid);
        if (packages == null || packages.length != 1) {
            Log.w(TAG, "Can't get one package name for UID: " + uid);
            return null;
        }
        try {
            PackageInfo info =
                    getPackageManagerAsUser(UserHandle.getUserHandleForUid(uid))
                            .getPackageInfo(packages[0], PackageManager.PackageInfoFlags.of(0));
            return info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Package " + packages[0] + " not found.");
            return null;
        }
    }

    @Nullable
    Integer getPackageUid(@NonNull String packageName, @NonNull UserHandle user) {
        Integer uid = null;
        try {
            uid =
                    getPackageManagerAsUser(user)
                            .getPackageUid(
                                    packageName,
                                    PackageManager.PackageInfoFlags.of(/* flags= */ 0));
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "NameNotFound exception for " + packageName);
        }
        return uid;
    }

    @NonNull
    private PackageManager getPackageManagerAsUser(@NonNull UserHandle user) {
        PackageManager packageManager = mUsersPackageManager.get(user);
        if (packageManager == null) {
            packageManager = mContext.createContextAsUser(user, /* flag= */ 0).getPackageManager();
            mUsersPackageManager.put(user, packageManager);
        }
        return packageManager;
    }

    @NonNull
    private List<UserHandle> getAllUserHandles() {
        return Objects.requireNonNull(
                        mContext.getSystemService(UserManager.class),
                        "UserManager service cannot be null")
                .getUserHandles(/* excludeDying= */ true);
    }
}