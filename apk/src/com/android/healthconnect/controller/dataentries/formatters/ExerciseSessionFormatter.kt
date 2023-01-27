/*
 * Copyright (C) 2023 The Android Open Source Project
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
 *
 *
 */

package com.android.healthconnect.controller.dataentries.formatters

import android.content.Context
import android.healthconnect.datatypes.ExerciseLap
import android.healthconnect.datatypes.ExerciseSegment
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_BACK_EXTENSION
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_BARBELL_SHOULDER_PRESS
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_BENCH_PRESS
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_BENCH_SIT_UP
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_BURPEE
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_CRUNCH
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_DEADLIFT
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_DUMBBELL_CURL_LEFT_ARM
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_DUMBBELL_CURL_RIGHT_ARM
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_DUMBBELL_FRONT_RAISE
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_DUMBBELL_LATERAL_RAISE
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_LEFT_ARM
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_RIGHT_ARM
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_TWO_ARM
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_FORWARD_TWIST
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_JUMPING_JACK
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_JUMP_ROPE
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_LAT_PULL_DOWN
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_LUNGE
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_PLANK
import android.healthconnect.datatypes.ExerciseSegmentType.EXERCISE_SEGMENT_TYPE_SQUAT
import android.healthconnect.datatypes.ExerciseSessionRecord
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_BADMINTON
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_BASEBALL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_BASKETBALL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_BIKING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_BIKING_STATIONARY
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_BOOT_CAMP
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_BOXING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_CALISTHENICS
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_CRICKET
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_DANCING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_ELLIPTICAL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_EXERCISE_CLASS
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_FENCING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_FOOTBALL_AMERICAN
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_FOOTBALL_AUSTRALIAN
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_FRISBEE_DISC
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_GOLF
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_GUIDED_BREATHING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_GYMNASTICS
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_HANDBALL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_HIGH_INTENSITY_INTERVAL_TRAINING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_HIKING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_ICE_HOCKEY
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_ICE_SKATING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_MARTIAL_ARTS
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_OTHER_WORKOUT
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_PADDLING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_PILATES
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_RACQUETBALL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_ROCK_CLIMBING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_ROLLER_HOCKEY
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_ROWING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_ROWING_MACHINE
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_RUGBY
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_RUNNING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_RUNNING_TREADMILL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SAILING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SCUBA_DIVING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SKATING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SKIING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SNOWBOARDING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SNOWSHOEING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SOCCER
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SOFTBALL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SQUASH
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_STAIR_CLIMBING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_STAIR_CLIMBING_MACHINE
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_STRENGTH_TRAINING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_STRETCHING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SURFING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SWIMMING_OPEN_WATER
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_SWIMMING_POOL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_TABLE_TENNIS
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_TENNIS
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_VOLLEYBALL
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_WALKING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_WATER_POLO
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_WEIGHTLIFTING
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_WHEELCHAIR
import android.healthconnect.datatypes.ExerciseSessionType.EXERCISE_SESSION_TYPE_YOGA
import android.icu.text.MessageFormat.format
import com.android.healthconnect.controller.R
import com.android.healthconnect.controller.dataentries.FormattedEntry
import com.android.healthconnect.controller.dataentries.FormattedEntry.FormattedSessionDetail
import com.android.healthconnect.controller.dataentries.FormattedEntry.SessionHeader
import com.android.healthconnect.controller.dataentries.formatters.DurationFormatter.formatDurationLong
import com.android.healthconnect.controller.dataentries.formatters.DurationFormatter.formatDurationShort
import com.android.healthconnect.controller.dataentries.formatters.shared.LengthFormatter
import com.android.healthconnect.controller.dataentries.formatters.shared.SessionDetailsFormatter
import com.android.healthconnect.controller.dataentries.formatters.shared.SessionFormatter
import com.android.healthconnect.controller.dataentries.units.UnitPreferences
import com.android.healthconnect.controller.utils.LocalDateTimeFormatter
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.Duration
import javax.inject.Inject

/** Formatter for printing ExerciseSessionRecord data. */
class ExerciseSessionFormatter
@Inject
constructor(@ApplicationContext private val context: Context) :
    SessionFormatter<ExerciseSessionRecord>(context),
    SessionDetailsFormatter<ExerciseSessionRecord> {

    private val timeFormatter = LocalDateTimeFormatter(context)

    override suspend fun formatValue(
        record: ExerciseSessionRecord,
        unitPreferences: UnitPreferences
    ): String {
        return formatSession(record) { duration -> formatDurationShort(context, duration) }
    }

    override suspend fun formatA11yValue(
        record: ExerciseSessionRecord,
        unitPreferences: UnitPreferences
    ): String {
        return formatSession(record) { duration -> formatDurationLong(context, duration) }
    }

    override fun getNotes(record: ExerciseSessionRecord): String? {
        return record.notes?.toString()
    }

    override suspend fun formatRecordDetails(record: ExerciseSessionRecord): List<FormattedEntry> {
        val sortedSegments =
            record.segments.sortedBy { it.startTime }.map { formatSegment(record.metadata.id, it) }
        val sortedLaps =
            record.laps.sortedBy { it.startTime }.map { formatLaps(record.metadata.id, it) }
        return buildList {
            if (sortedSegments.isNotEmpty()) {
                add(SessionHeader(context.getString(R.string.exercise_segments_header)))
                addAll(sortedSegments)
            }
            if (sortedLaps.isNotEmpty()) {
                add(SessionHeader(context.getString(R.string.exercise_laps_header)))
                addAll(sortedLaps)
            }
        }
    }

    private fun formatSession(
        record: ExerciseSessionRecord,
        formatDuration: (duration: Duration) -> String
    ): String {
        val type = getExerciseType(record.exerciseType)
        return if (!record.title.isNullOrBlank()) {
            context.getString(R.string.session_title, record.title, type)
        } else {
            val duration = Duration.between(record.startTime, record.endTime)
            context.getString(R.string.session_title, formatDuration(duration), type)
        }
    }

    private fun formatSegment(id: String, segment: ExerciseSegment): FormattedSessionDetail {
        return FormattedSessionDetail(
            uuid = id,
            header = timeFormatter.formatTimeRange(segment.startTime, segment.endTime),
            headerA11y = timeFormatter.formatTimeRangeA11y(segment.startTime, segment.endTime),
            title = formatSegmentTitle(segment.repetitionsCount, segment.segmentType),
            titleA11y = formatSegmentTitleA11y(segment.repetitionsCount, segment.segmentType),
        )
    }

    private fun formatLaps(id: String, lap: ExerciseLap): FormattedSessionDetail {
        return FormattedSessionDetail(
            uuid = id,
            header = timeFormatter.formatTimeRange(lap.startTime, lap.endTime),
            headerA11y = timeFormatter.formatTimeRangeA11y(lap.startTime, lap.endTime),
            title = LengthFormatter.formatValue(context, lap.length, unitPreferences),
            titleA11y = LengthFormatter.formatA11yValue(context, lap.length, unitPreferences),
        )
    }

    private fun formatSegmentTitle(repetitionsCount: Int, type: Int): String {
        val segmentType = getSegmentType(type)
        val repetitions =
            format(context.getString(R.string.repetitions), mapOf("count" to repetitionsCount))
        return context.getString(R.string.repetitions_format, segmentType, repetitions)
    }

    private fun formatSegmentTitleA11y(repetitionsCount: Int, type: Int): String {
        val segmentType = getSegmentType(type)
        val repetitions =
            format(context.getString(R.string.repetitions_long), mapOf("count" to repetitionsCount))
        return context.getString(R.string.repetitions_format, segmentType, repetitions)
    }

    private fun getSegmentType(segmentType: Int): String {
        return when (segmentType) {
            EXERCISE_SEGMENT_TYPE_BACK_EXTENSION -> context.getString(R.string.back_extension)
            EXERCISE_SEGMENT_TYPE_BARBELL_SHOULDER_PRESS ->
                context.getString(R.string.barbell_shoulder_press)
            EXERCISE_SEGMENT_TYPE_BENCH_PRESS -> context.getString(R.string.bench_press)
            EXERCISE_SEGMENT_TYPE_BENCH_SIT_UP -> context.getString(R.string.bench_sit_up)
            EXERCISE_SEGMENT_TYPE_BURPEE -> context.getString(R.string.burpee)
            EXERCISE_SEGMENT_TYPE_CRUNCH -> context.getString(R.string.crunch)
            EXERCISE_SEGMENT_TYPE_DEADLIFT -> context.getString(R.string.deadlift)
            EXERCISE_SEGMENT_TYPE_DUMBBELL_CURL_LEFT_ARM ->
                context.getString(R.string.dumbbell_curl_left_arm)
            EXERCISE_SEGMENT_TYPE_DUMBBELL_CURL_RIGHT_ARM ->
                context.getString(R.string.dumbbell_curl_right_arm)
            EXERCISE_SEGMENT_TYPE_DUMBBELL_FRONT_RAISE ->
                context.getString(R.string.dumbbell_front_raise)
            EXERCISE_SEGMENT_TYPE_DUMBBELL_LATERAL_RAISE ->
                context.getString(R.string.dumbbell_lateral_raise)
            EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_LEFT_ARM ->
                context.getString(R.string.dumbbell_triceps_extension_left_arm)
            EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_RIGHT_ARM ->
                context.getString(R.string.dumbbell_triceps_extension_right_arm)
            EXERCISE_SEGMENT_TYPE_DUMBBELL_TRICEPS_EXTENSION_TWO_ARM ->
                context.getString(R.string.dumbbell_triceps_extension_two_arm)
            EXERCISE_SEGMENT_TYPE_FORWARD_TWIST -> context.getString(R.string.forward_twist)
            EXERCISE_SEGMENT_TYPE_JUMPING_JACK -> context.getString(R.string.jumping_jack)
            EXERCISE_SEGMENT_TYPE_JUMP_ROPE -> context.getString(R.string.jump_rope)
            EXERCISE_SEGMENT_TYPE_LAT_PULL_DOWN -> context.getString(R.string.lat_pull_down)
            EXERCISE_SEGMENT_TYPE_LUNGE -> context.getString(R.string.lunge)
            EXERCISE_SEGMENT_TYPE_PLANK -> context.getString(R.string.plank)
            EXERCISE_SEGMENT_TYPE_SQUAT -> context.getString(R.string.squat)
            else -> throw IllegalArgumentException("Unknown exercise segment type $segmentType")
        }
    }

    private fun getExerciseType(type: Int): String {
        return when (type) {
            EXERCISE_SESSION_TYPE_BADMINTON -> context.getString(R.string.badminton)
            EXERCISE_SESSION_TYPE_BASEBALL -> context.getString(R.string.baseball)
            EXERCISE_SESSION_TYPE_BASKETBALL -> context.getString(R.string.basketball)
            EXERCISE_SESSION_TYPE_BIKING -> context.getString(R.string.biking)
            EXERCISE_SESSION_TYPE_BIKING_STATIONARY -> context.getString(R.string.biking_stationary)
            EXERCISE_SESSION_TYPE_BOOT_CAMP -> context.getString(R.string.boot_camp)
            EXERCISE_SESSION_TYPE_BOXING -> context.getString(R.string.boxing)
            EXERCISE_SESSION_TYPE_CALISTHENICS -> context.getString(R.string.calisthenics)
            EXERCISE_SESSION_TYPE_CRICKET -> context.getString(R.string.cricket)
            EXERCISE_SESSION_TYPE_DANCING -> context.getString(R.string.dancing)
            EXERCISE_SESSION_TYPE_ELLIPTICAL -> context.getString(R.string.elliptical)
            EXERCISE_SESSION_TYPE_EXERCISE_CLASS -> context.getString(R.string.exercise_class)
            EXERCISE_SESSION_TYPE_FENCING -> context.getString(R.string.fencing)
            EXERCISE_SESSION_TYPE_FOOTBALL_AMERICAN -> context.getString(R.string.football_american)
            EXERCISE_SESSION_TYPE_FOOTBALL_AUSTRALIAN ->
                context.getString(R.string.activity_type_australian_football)
            EXERCISE_SESSION_TYPE_FRISBEE_DISC -> context.getString(R.string.frisbee_disc)
            EXERCISE_SESSION_TYPE_GOLF -> context.getString(R.string.golf)
            EXERCISE_SESSION_TYPE_GUIDED_BREATHING -> context.getString(R.string.guided_breathing)
            EXERCISE_SESSION_TYPE_GYMNASTICS -> context.getString(R.string.gymnastics)
            EXERCISE_SESSION_TYPE_HANDBALL -> context.getString(R.string.handball)
            EXERCISE_SESSION_TYPE_HIGH_INTENSITY_INTERVAL_TRAINING ->
                context.getString(R.string.high_intensity_interval_training)
            EXERCISE_SESSION_TYPE_HIKING -> context.getString(R.string.hiking)
            EXERCISE_SESSION_TYPE_ICE_HOCKEY -> context.getString(R.string.ice_hockey)
            EXERCISE_SESSION_TYPE_ICE_SKATING -> context.getString(R.string.ice_skating)
            EXERCISE_SESSION_TYPE_MARTIAL_ARTS -> context.getString(R.string.martial_arts)
            EXERCISE_SESSION_TYPE_OTHER_WORKOUT -> context.getString(R.string.workout)
            EXERCISE_SESSION_TYPE_PADDLING -> context.getString(R.string.paddling)
            EXERCISE_SESSION_TYPE_PILATES -> context.getString(R.string.pilates)
            EXERCISE_SESSION_TYPE_RACQUETBALL -> context.getString(R.string.racquetball)
            EXERCISE_SESSION_TYPE_ROCK_CLIMBING -> context.getString(R.string.rock_climbing)
            EXERCISE_SESSION_TYPE_ROLLER_HOCKEY -> context.getString(R.string.roller_hockey)
            EXERCISE_SESSION_TYPE_ROWING -> context.getString(R.string.rowing)
            EXERCISE_SESSION_TYPE_ROWING_MACHINE -> context.getString(R.string.rowing_machine)
            EXERCISE_SESSION_TYPE_RUGBY -> context.getString(R.string.rugby)
            EXERCISE_SESSION_TYPE_RUNNING -> context.getString(R.string.running)
            EXERCISE_SESSION_TYPE_RUNNING_TREADMILL -> context.getString(R.string.running_treadmill)
            EXERCISE_SESSION_TYPE_SAILING -> context.getString(R.string.sailing)
            EXERCISE_SESSION_TYPE_SCUBA_DIVING -> context.getString(R.string.scuba_diving)
            EXERCISE_SESSION_TYPE_SKATING -> context.getString(R.string.skating)
            EXERCISE_SESSION_TYPE_SKIING -> context.getString(R.string.skiing)
            EXERCISE_SESSION_TYPE_SNOWBOARDING -> context.getString(R.string.snowboarding)
            EXERCISE_SESSION_TYPE_SNOWSHOEING -> context.getString(R.string.snowshoeing)
            EXERCISE_SESSION_TYPE_SOCCER -> context.getString(R.string.soccer)
            EXERCISE_SESSION_TYPE_SOFTBALL -> context.getString(R.string.softball)
            EXERCISE_SESSION_TYPE_SQUASH -> context.getString(R.string.squash)
            EXERCISE_SESSION_TYPE_STAIR_CLIMBING -> context.getString(R.string.stair_climbing)
            EXERCISE_SESSION_TYPE_STAIR_CLIMBING_MACHINE ->
                context.getString(R.string.stair_climbing_machine)
            EXERCISE_SESSION_TYPE_STRENGTH_TRAINING -> context.getString(R.string.strength_training)
            EXERCISE_SESSION_TYPE_STRETCHING -> context.getString(R.string.stretching)
            EXERCISE_SESSION_TYPE_SURFING -> context.getString(R.string.surfing)
            EXERCISE_SESSION_TYPE_SWIMMING_OPEN_WATER ->
                context.getString(R.string.swimming_open_water)
            EXERCISE_SESSION_TYPE_SWIMMING_POOL -> context.getString(R.string.swimming_pool)
            EXERCISE_SESSION_TYPE_TABLE_TENNIS -> context.getString(R.string.table_tennis)
            EXERCISE_SESSION_TYPE_TENNIS -> context.getString(R.string.tennis)
            EXERCISE_SESSION_TYPE_VOLLEYBALL -> context.getString(R.string.volleyball)
            EXERCISE_SESSION_TYPE_WALKING -> context.getString(R.string.walking)
            EXERCISE_SESSION_TYPE_WATER_POLO -> context.getString(R.string.water_polo)
            EXERCISE_SESSION_TYPE_WEIGHTLIFTING -> context.getString(R.string.weightlifting)
            EXERCISE_SESSION_TYPE_WHEELCHAIR -> context.getString(R.string.wheelchair)
            EXERCISE_SESSION_TYPE_YOGA -> context.getString(R.string.yoga)
            else -> throw IllegalArgumentException("Unknown exercise session type $type")
        }
    }
}