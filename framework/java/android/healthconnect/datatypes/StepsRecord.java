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

import static android.healthconnect.datatypes.AggregationType.AggregationTypeIdentifier.STEPS_RECORD_COUNT_TOTAL;
import static android.healthconnect.datatypes.RecordTypeIdentifier.RECORD_TYPE_STEPS;

import android.annotation.NonNull;
import android.healthconnect.HealthConnectManager;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * Captures the number of steps taken since the last reading. Each step is only reported once so
 * records shouldn't have overlapping time. The start time of each record should represent the start
 * of the interval in which steps were taken.
 */
@Identifier(recordIdentifier = RecordTypeIdentifier.RECORD_TYPE_STEPS)
public final class StepsRecord extends IntervalRecord {
    /** Builder class for {@link StepsRecord} */
    public static final class Builder {
        private final Metadata mMetadata;
        private final Instant mStartTime;
        private final Instant mEndTime;
        private final long mCount;
        private ZoneOffset mStartZoneOffset =
                ZoneOffset.systemDefault().getRules().getOffset(Instant.now());
        private ZoneOffset mEndZoneOffset =
                ZoneOffset.systemDefault().getRules().getOffset(Instant.now());

        /**
         * @param metadata Metadata to be associated with the record. See {@link Metadata}.
         * @param startTime Start time of this activity
         * @param endTime End time of this activity
         * @param count Number of steps recorded for this activity
         */
        public Builder(
                @NonNull Metadata metadata,
                @NonNull Instant startTime,
                @NonNull Instant endTime,
                long count) {
            Objects.requireNonNull(metadata);
            Objects.requireNonNull(startTime);
            Objects.requireNonNull(endTime);
            mMetadata = metadata;
            mStartTime = startTime;
            mEndTime = endTime;
            mCount = count;
        }

        /** Sets the zone offset of the user when the activity started */
        @NonNull
        public Builder setStartZoneOffset(@NonNull ZoneOffset startZoneOffset) {
            Objects.requireNonNull(startZoneOffset);

            mStartZoneOffset = startZoneOffset;
            return this;
        }

        /** Sets the zone offset of the user when the activity ended */
        @NonNull
        public Builder setEndZoneOffset(@NonNull ZoneOffset endZoneOffset) {
            Objects.requireNonNull(endZoneOffset);

            mEndZoneOffset = endZoneOffset;
            return this;
        }

        /**
         * @return Object of {@link StepsRecord}
         */
        @NonNull
        public StepsRecord build() {
            return new StepsRecord(
                    mMetadata, mStartTime, mStartZoneOffset, mEndTime, mEndZoneOffset, mCount);
        }
    }

    /**
     * Metric identifier to get total steps count using aggregate APIs in {@link
     * HealthConnectManager}
     */
    @NonNull
    public static final AggregationType<Long> COUNT_TOTAL =
            new AggregationType<>(
                    STEPS_RECORD_COUNT_TOTAL, AggregationType.SUM, RECORD_TYPE_STEPS, Long.class);

    private final long mCount;

    private StepsRecord(
            @NonNull Metadata metadata,
            @NonNull Instant startTime,
            @NonNull ZoneOffset startZoneOffset,
            @NonNull Instant endTime,
            @NonNull ZoneOffset endZoneOffset,
            long count) {
        super(metadata, startTime, startZoneOffset, endTime, endZoneOffset);
        mCount = count;
    }

    /**
     * @return Number of steps taken
     */
    public long getCount() {
        return mCount;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare.
     * @return {@code true} if this object is the same as the obj
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        StepsRecord record = (StepsRecord) o;
        return getCount() == record.getCount();
    }

    /**
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCount());
    }
}
