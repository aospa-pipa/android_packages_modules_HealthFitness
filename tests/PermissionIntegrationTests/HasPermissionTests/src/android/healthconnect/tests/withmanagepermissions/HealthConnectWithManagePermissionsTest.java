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

package android.healthconnect.tests.withmanagepermissions;

import static org.junit.Assert.fail;
import static org.junit.Assume.assumeTrue;

import static com.google.common.truth.Truth.assertThat;

import static com.android.compatibility.common.util.SystemUtil.runWithShellPermissionIdentity;

import android.content.Context;
import android.Manifest;
import android.os.Build;

import androidx.test.InstrumentationRegistry;

import android.healthconnect.Constants;
import android.healthconnect.HealthConnectManager;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

/**
 * Integration tests for {@link android.healthconnect.HealthConnectManager} Permission-related APIs.
 *
 * <p><b>Note:</b> These tests operate while holding {@link
 * Constants.MANAGE_HEALTH_PERMISSIONS_NAME}. For tests asserting that non-holders of the permission
 * cannot call the APIs, please see {@link
 * android.healthconnect.tests.withoutmanagepermissions.HealthConnectWithoutManagePermissionsTest}.
 *
 * <p><b>Note:</b> Since we need to hold the aforementioned permission, this test needs to be signed
 * with the same certificate as the HealthConnect module. Therefore, <b>we skip this test when it
 * cannot hold {@link Constants.MANAGE_HEALTH_PERMISSIONS_NAME}. The primary use of these tests is
 * therefore during development, when we are building from source rather than using prebuilts</b>.
 * Additionally, this test can run as a presubmit on the main (master) branch where modules are
 * always built from source.
 *
 * <p><b>Build/Install/Run:</b> {@code atest HealthConnectWithManagePermissionsIntegrationTests}.
 */
@RunWith(AndroidJUnit4.class)
public class HealthConnectWithManagePermissionsTest {
    private static final String DEFAULT_APP_PACKAGE = "android.healthconnect.test.app";
    private static final String INEXISTENT_APP_PACKAGE = "my.invalid.package.name";
    // TODO(b/251787336): Modify these to actual health permissions once they have been defined.
    private static final String DEFAULT_PERM = "android.permission.health.TEST_PERM";
    private static final String DEFAULT_PERM_2 = "android.permission.health.TEST_PERM_2";
    private static final String UNDECLARED_PERM = "android.permission.health.TEST_PERM_3";
    private static final String INVALID_PERM = "android.permission.health.MY_INVALID_PERM";
    private static final String NON_HEALTH_PERM = Manifest.permission.ACCESS_COARSE_LOCATION;

    private Context mContext;
    private HealthConnectManager mHealthConnectManager;

    @Before
    public void setUp() throws Exception {
        mContext = InstrumentationRegistry.getTargetContext();
        assumeTrue(
                "Skipping test - test cannot hold "
                        + Constants.MANAGE_HEALTH_PERMISSIONS_NAME
                        + " for the build-under-test. This is likely because the build uses "
                        + "mainline prebuilts and therefore does not have a compatible signature "
                        + "with this test app. See the test class Javadoc for more info.",
                canHoldManageHealthPermsPermission());
        mHealthConnectManager = mContext.getSystemService(HealthConnectManager.class);

        revokePermissionViaPackageManager(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
        revokePermissionViaPackageManager(DEFAULT_APP_PACKAGE, DEFAULT_PERM_2);
        assertPermNotGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
        assertPermNotGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM_2);
    }

    @Test
    public void testGrantHealthPermission_appHasPermissionDeclared_success() throws Exception {
        mHealthConnectManager.grantHealthPermission(DEFAULT_APP_PACKAGE, DEFAULT_PERM);

        assertPermGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
    }

    @Test
    public void testGrantHealthPermission_permissionAlreadyGranted_success() throws Exception {
        mHealthConnectManager.grantHealthPermission(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
        assertPermGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
        // Let's regrant it
        mHealthConnectManager.grantHealthPermission(DEFAULT_APP_PACKAGE, DEFAULT_PERM);

        assertPermGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
    }

    @Test(expected = SecurityException.class)
    public void testGrantHealthPermission_appHasPermissionNotDeclared_throwsSecurityException()
            throws Exception {
        mHealthConnectManager.grantHealthPermission(DEFAULT_APP_PACKAGE, UNDECLARED_PERM);
        fail("Expected SecurityException due permission not being declared by target app.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGrantHealthPermission_invalidPermission_throwsIllegalArgumentException()
            throws Exception {
        mHealthConnectManager.grantHealthPermission(DEFAULT_APP_PACKAGE, INVALID_PERM);
        fail("Expected IllegalArgumentException due to invalid permission.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGrantHealthPermission_nonHealthPermission_throwsIllegalArgumentException()
            throws Exception {
        mHealthConnectManager.grantHealthPermission(DEFAULT_APP_PACKAGE, NON_HEALTH_PERM);
        fail("Expected IllegalArgumentException due to non-health permission.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGrantHealthPermission_invalidPackageName_throwsIllegalArgumentException()
            throws Exception {
        mHealthConnectManager.grantHealthPermission(INEXISTENT_APP_PACKAGE, DEFAULT_PERM);
        fail("Expected IllegalArgumentException due to invalid package.");
    }

    @Test(expected = NullPointerException.class)
    public void testGrantHealthPermission_nullPermission_throwsNPE() throws Exception {
        mHealthConnectManager.grantHealthPermission(
                DEFAULT_APP_PACKAGE, /* permissionName= */ null);
        fail("Expected NullPointerException due to null permission.");
    }

    @Test(expected = NullPointerException.class)
    public void testGrantHealthPermission_nullPackageName_throwsNPE() throws Exception {
        mHealthConnectManager.grantHealthPermission(/* packageName= */ null, DEFAULT_PERM);
        fail("Expected NullPointerException due to null package.");
    }

    @Test
    public void testRevokeHealthPermission_appHasPermissionGranted_success() throws Exception {
        grantPermissionViaPackageManager(DEFAULT_APP_PACKAGE, DEFAULT_PERM);

        mHealthConnectManager.revokeHealthPermission(
                DEFAULT_APP_PACKAGE, DEFAULT_PERM, /* reason= */ null);

        assertPermNotGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
    }

    @Test
    public void testRevokeHealthPermission_appHasPermissionNotGranted_success() throws Exception {
        mHealthConnectManager.revokeHealthPermission(
                DEFAULT_APP_PACKAGE, DEFAULT_PERM, /* reason= */ null);

        assertPermNotGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
    }

    @Test(expected = SecurityException.class)
    public void testRevokeHealthPermission_appHasPermissionNotDeclared_throwsSecurityException()
            throws Exception {
        mHealthConnectManager.revokeHealthPermission(
                DEFAULT_APP_PACKAGE, UNDECLARED_PERM, /* reason= */ null);
        fail("Expected SecurityException due permission not being declared by target app.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRevokeHealthPermission_invalidPermission_throwsIllegalArgumentException()
            throws Exception {
        mHealthConnectManager.revokeHealthPermission(
                DEFAULT_APP_PACKAGE, INVALID_PERM, /* reason= */ null);
        fail("Expected IllegalArgumentException due to invalid permission.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRevokeHealthPermission_nonHealthPermission_throwsIllegalArgumentException()
            throws Exception {
        mHealthConnectManager.revokeHealthPermission(
                DEFAULT_APP_PACKAGE, NON_HEALTH_PERM, /* reason= */ null);
        fail("Expected IllegalArgumentException due to non-health permission.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRevokeHealthPermission_invalidPackageName_throwsIllegalArgumentException()
            throws Exception {
        mHealthConnectManager.revokeHealthPermission(
                INEXISTENT_APP_PACKAGE, DEFAULT_PERM, /* reason= */ null);
        fail("Expected IllegalArgumentException due to invalid package.");
    }

    @Test(expected = NullPointerException.class)
    public void testRevokeHealthPermission_nullPermission_throwsNPE() throws Exception {
        mHealthConnectManager.revokeHealthPermission(
                DEFAULT_APP_PACKAGE, /* permissionName= */ null, /* reason= */ null);
        fail("Expected NullPointerException due to null permission.");
    }

    @Test(expected = NullPointerException.class)
    public void testRevokeHealthPermission_nullPackageName_throwsNPE() throws Exception {
        mHealthConnectManager.revokeHealthPermission(
                /* packageName= */ null, DEFAULT_PERM, /* reason= */ null);
        fail("Expected NullPointerException due to null package.");
    }

    @Test
    public void testGetGrantedHealthPermissions_appHasNoPermissionGranted_emptyList()
            throws Exception {
        List<String> grantedPerms =
                mHealthConnectManager.getGrantedHealthPermissions(DEFAULT_APP_PACKAGE);

        assertThat(grantedPerms.size()).isEqualTo(0);
    }

    @Test
    public void testGetGrantedHealthPermissions_appHasPermissionsGranted_success()
            throws Exception {
        grantPermissionViaPackageManager(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
        grantPermissionViaPackageManager(DEFAULT_APP_PACKAGE, DEFAULT_PERM_2);

        List<String> grantedPerms =
                mHealthConnectManager.getGrantedHealthPermissions(DEFAULT_APP_PACKAGE);

        assertThat(grantedPerms)
                .containsExactlyElementsIn(
                        Arrays.asList(new String[] {DEFAULT_PERM, DEFAULT_PERM_2}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetGrantedHealthPermissions_invalidPackageName_throwsIllegalArgumentException()
            throws Exception {
        mHealthConnectManager.getGrantedHealthPermissions(INEXISTENT_APP_PACKAGE);
        fail("Expected IllegalArgumentException due to invalid package.");
    }

    @Test(expected = NullPointerException.class)
    public void testGetGrantedHealthPermissions_nullPackageName_throwsNPE() throws Exception {
        mHealthConnectManager.getGrantedHealthPermissions(/* packageName= */ null);
        fail("Expected NullPointerException due to null package.");
    }

    @Test
    public void testRevokeAllHealthPermissions_appHasNoPermissionsGranted_success()
            throws Exception {
        mHealthConnectManager.revokeAllHealthPermissions(DEFAULT_APP_PACKAGE, /* reason= */ null);

        assertThat(mHealthConnectManager.getGrantedHealthPermissions(DEFAULT_APP_PACKAGE).size())
                .isEqualTo(0);
        assertPermNotGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
        assertPermNotGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM_2);
    }

    @Test
    public void testRevokeAllHealthPermissions_appHasPermissionsGranted_success() throws Exception {
        grantPermissionViaPackageManager(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
        grantPermissionViaPackageManager(DEFAULT_APP_PACKAGE, DEFAULT_PERM_2);

        mHealthConnectManager.revokeAllHealthPermissions(DEFAULT_APP_PACKAGE, /* reason= */ null);

        assertThat(mHealthConnectManager.getGrantedHealthPermissions(DEFAULT_APP_PACKAGE).size())
                .isEqualTo(0);
        assertPermNotGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM);
        assertPermNotGrantedForApp(DEFAULT_APP_PACKAGE, DEFAULT_PERM_2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRevokeAllHealthPermissions_invalidPackageName_throwsIllegalArgumentException()
            throws Exception {
        mHealthConnectManager.revokeAllHealthPermissions(
                INEXISTENT_APP_PACKAGE, /* reason= */ null);
        fail("Expected IllegalArgumentException due to invalid package.");
    }

    @Test(expected = NullPointerException.class)
    public void testRevokeAllHealthPermissions_nullPackageName_throwsNPE() throws Exception {
        mHealthConnectManager.revokeAllHealthPermissions(
                /* packageName= */ null, /* reason= */ null);
        fail("Expected NullPointerException due to null package.");
    }

    private void grantPermissionViaPackageManager(String packageName, String permName) {
        runWithShellPermissionIdentity(
                () ->
                        mContext.getPackageManager()
                                .grantRuntimePermission(packageName, permName, mContext.getUser()),
                Manifest.permission.GRANT_RUNTIME_PERMISSIONS);
    }

    private void revokePermissionViaPackageManager(String packageName, String permName) {
        runWithShellPermissionIdentity(
                () ->
                        mContext.getPackageManager()
                                .revokeRuntimePermission(
                                        packageName,
                                        permName,
                                        mContext.getUser(),
                                        /* reason= */ null),
                Manifest.permission.REVOKE_RUNTIME_PERMISSIONS);
    }

    private void assertPermGrantedForApp(String packageName, String permName) throws Exception {
        assertThat(mContext.getPackageManager().checkPermission(permName, packageName))
                .isEqualTo(PackageManager.PERMISSION_GRANTED);
    }

    private void assertPermNotGrantedForApp(String packageName, String permName) throws Exception {
        assertThat(mContext.getPackageManager().checkPermission(permName, packageName))
                .isEqualTo(PackageManager.PERMISSION_DENIED);
    }

    /**
     * Returns {@code true} if the test can hold {@link Constants.MANAGE_HEALTH_PERMISSIONS_NAME}.
     *
     * <p>The permission is protected at signature level, and should be granted automatically if
     * it's possible to hold. So effectively this method can simply check if this test's package
     * holds the permission.
     */
    private static boolean canHoldManageHealthPermsPermission() {
        return InstrumentationRegistry.getContext()
                        .checkSelfPermission(Constants.MANAGE_HEALTH_PERMISSIONS_NAME)
                == PackageManager.PERMISSION_GRANTED;
    }
}