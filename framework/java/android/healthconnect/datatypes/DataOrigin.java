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

package android.healthconnect.datatypes;

import android.annotation.NonNull;
import android.annotation.Nullable;

/** Specifies the contributing source/application of any {@link Record} */
public final class DataOrigin {
    /**
     * @see DataOrigin
     */
    public static final class Builder {
        private String mPackageName;

        /**
         * Sets the package name of the contributing package. Auto-populated by the platform at
         * record insertion time.
         */
        @NonNull
        public Builder setPackageName(@Nullable String packageName) {
            mPackageName = packageName;
            return this;
        }

        /**
         * @return {@link DataOrigin}'s object
         */
        @NonNull
        public DataOrigin build() {
            return new DataOrigin(mPackageName);
        }
    }

    private final String mPackageName;

    private DataOrigin(String packageName) {
        mPackageName = packageName;
    }

    /**
     * @return null if the package name has not been set or the caller doesn't have the correct
     *     permissions to read the corresponding package name.
     */
    @Nullable
    public String getPackageName() {
        return mPackageName;
    }
}
