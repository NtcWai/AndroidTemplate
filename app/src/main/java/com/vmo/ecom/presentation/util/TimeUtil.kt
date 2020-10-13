package com.vmo.ecom.presentation.util

import com.vmo.ecom.constant.Constant
import com.vmo.ecom.domain.model.WorkingWeekDay
import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {

    /**
     * @param input format:
     * @see com.vmo.ecom.constant.Constant.WORKING_HOUR_FORMAT
     * 1: "Mon-Sat 11:30 am - 10:00 pm  / Sun 5:30 pm - 10:00 pm"
     * 2: "Mon-Sun 11:00 am - 10:00 pm"
     * 3: "Mon-Thu, Sun 11:30 am - 10:00 pm  / Fri-Sat 11:30 am - 11:00 pm"
     * It's better if checking input format
     * @return
     */
    fun parseToWorkingDays(input: String): MutableList<WorkingWeekDay> {
        val workingWeekDays = mutableSetOf<WorkingWeekDay>()
        val workingTimeTexts = input.split("/").map { it.trim() }
        workingTimeTexts.forEach { workingTime ->
            val indexPreviousNumber = workingTime.indexOfFirst { it.isDigit() }
            val workingDayText = workingTime.substring(0, indexPreviousNumber).trim() // example Mon-Thu, Sun
            val workingHourText = workingTime.substring(indexPreviousNumber).trim() // example: 11:30 am - 10:00 pm
            val openCloseHourTexts = workingHourText.split("-").map { it.trim() }
            val openHourText = openCloseHourTexts[0]
            val closeHourText = openCloseHourTexts[1]
            val workingDayRanges = workingDayText.split(",").map { it.trim() }
            workingDayRanges.forEach { workingDayRange ->
                if (workingDayRange.contains("-")) {
                    val workingDays = workingDayRange.split("-").map { it.trim() }
                    val startIndex = Constant.DOUBLE_WEEK_DAYS.indexOfFirst { it == workingDays[0] }
                    var endIndex = Constant.DOUBLE_WEEK_DAYS.indexOfFirst { it == workingDays[1] }
                    if (endIndex < startIndex) {
                        endIndex = Constant.DOUBLE_WEEK_DAYS.indexOfLast { it == workingDays[1] }
                    }
                    for (index in startIndex..endIndex) {
                        val weekDay = Constant.DOUBLE_WEEK_DAYS[index]
                        val indexOfWeek = Constant.WEEK_DAYS.indexOf(weekDay)
                        workingWeekDays.add(WorkingWeekDay(weekDay, indexOfWeek, openHourText, closeHourText))
                    }
                } else {
                    val indexOfWeek = Constant.WEEK_DAYS.indexOf(workingDayRange)
                    workingWeekDays.add(WorkingWeekDay(workingDayRange, indexOfWeek, openHourText, closeHourText))
                }
            }
        }
        val sortedWorkingWeekDays = mutableListOf<WorkingWeekDay>()
        workingWeekDays
            .sortedBy {
                it.indexOfWeek
            }
            .forEachIndexed { index, workingWeekDay ->
                sortedWorkingWeekDays.add(index, workingWeekDay)
            }
        return sortedWorkingWeekDays
    }

    fun getWorkingStatus(localTimeStamp: Long, timeZoneOffset: Int, workingWeekDays: MutableList<WorkingWeekDay>): Boolean {
        val todayText = getDayOfWeek(localTimeStamp, timeZoneOffset)
        val todayWorkingText = workingWeekDays.find { it.weekDay == todayText }
        return if (todayWorkingText == null) {
            false
        } else {
            val openMinuteValue = getTotalMinutes(todayWorkingText.openHour)
            var closeMinuteValue = getTotalMinutes(todayWorkingText.closeHour)
            val timeStamp = localTimeStamp + timeZoneOffset
            val currentMinuteValue = ((timeStamp % Constant.DAY_IN_MILLISECONDS) / Constant.MINUTE_IN_MILLISECONDS).toInt()
            if (openMinuteValue == null || closeMinuteValue == null) {
                false
            } else {
                if (currentMinuteValue >= Constant.DAY_IN_MINUTES) {
                    false
                } else {
                    if (closeMinuteValue < openMinuteValue) { // in case close time over 24:00
                       closeMinuteValue += Constant.MINUTE_IN_MILLISECONDS
                    }
                    currentMinuteValue in openMinuteValue..closeMinuteValue
                }
            }
        }
    }

    /**
     * @param hourText12 format: 12:30 am or 01:00 pm
     * @return total time in minute
     */
    private fun getTotalMinutes(hourText12: String): Int? {
        val format24 = SimpleDateFormat(Constant.HOUR_FORMAT_24, Locale.US)
        val format12 = SimpleDateFormat(Constant.HOUR_FORMAT_12, Locale.US)
        val date = format12.parse(hourText12)
        return date?.let {
            val hourMinute = format24.format(date).split(":").map { it.trim() }
            hourMinute[0].toInt() * 60 + hourMinute[1].toInt()
        }
    }

    fun getTimeZoneOffset(): Int {
        val tz = TimeZone.getDefault()
        val now = Date()
        return tz.getOffset(now.time)
    }

    /**
     * @return Mon, Tue ....
     */
    fun getDayOfWeek(localTimeStamp: Long, timeZoneOffset: Int): String? {
        val dateFormat = SimpleDateFormat(Constant.DAY_OF_WEEK_FORMAT, Locale.US)
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return dateFormat.format(localTimeStamp + timeZoneOffset)
    }

}