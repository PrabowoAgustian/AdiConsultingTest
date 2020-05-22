package com.dirumahajanews.helper

import android.annotation.SuppressLint
import org.apache.commons.lang3.StringUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.*
import java.util.concurrent.TimeUnit

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class TimeHelper {
    companion object {
        var DAY = "day"
        var HOUR = "hour"
        var MINUTE = "minute"
        var SECOND = "second"

        fun getDateFormated(format: String, time: Long): String {
            val id = Locale("en")
            return SimpleDateFormat(format, id).format(Date(time))
        }

        fun getDateFormatedNow(format: String): String {
            val id = Locale("en", "EN")
            return SimpleDateFormat(format, id).format(Date())
        }

        fun getDateFormated(format: String, time: Long, locale: Locale): String {
            return SimpleDateFormat(format, locale).format(Date(time))
        }

        fun getTime(): String {
            val milliseconds: Long = 1000000
            val seconds = milliseconds / 1000 % 60
            return seconds.toString()
        }

        fun getTime(StartTime: Long?, EndTime: Long?): String {
            val milliseconds = EndTime!! - StartTime!!
            val seconds = milliseconds / 1000
            return seconds.toString()
        }


        fun getCalendarDaysOfWeek(dayOfWeek: String): Int {
            return if (dayOfWeek.equals("Monday", ignoreCase = true)) {
                1
            } else if (dayOfWeek.equals("Tuesday", ignoreCase = true)) {
                2
            } else if (dayOfWeek.equals("Wednesday", ignoreCase = true)) {
                3
            } else if (dayOfWeek.equals("Thursday", ignoreCase = true)) {
                4
            } else if (dayOfWeek.equals("Friday", ignoreCase = true)) {
                5
            } else if (dayOfWeek.equals("Saturday", ignoreCase = true)) {
                6
            } else if (dayOfWeek.equals("Sunday", ignoreCase = true)) {
                7
            } else {
                1
            }
        }

        fun convertDate(date: String, from: String, to: String): String {
            var newDate: String? = null
            @SuppressLint("SimpleDateFormat") val inputFormat = SimpleDateFormat(from)
            @SuppressLint("SimpleDateFormat") val outputFormat = SimpleDateFormat(to)
            try {
                newDate = outputFormat.format(inputFormat.parse(date))
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            return newDate ?: ""
        }

        fun getTimeFormated(time: String?): String {
            if (StringUtils.isBlank(time))
                return ""
            var reformattedDate = ""
            @SuppressLint("SimpleDateFormat") val inputFormat = SimpleDateFormat("dd-MM-yyyy HH:mm")
            @SuppressLint("SimpleDateFormat") val outputFormat = SimpleDateFormat("HH:mm")
            try {
                reformattedDate = outputFormat.format(inputFormat.parse(time))
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return reformattedDate
        }

        fun getPreferredDateFormated(date: String): String? {
            var reformattedDate: String? = null
            @SuppressLint("SimpleDateFormat") val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy")
            @SuppressLint("SimpleDateFormat") val outputFormat = SimpleDateFormat("yyyy-MM-dd")
            try {
                reformattedDate = outputFormat.format(inputFormat.parse(date))
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return reformattedDate
        }

        fun getTimeFormatedNew(time: String): String? {
            var reformattedDate: String? = null
            @SuppressLint("SimpleDateFormat") val inputFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
            @SuppressLint("SimpleDateFormat") val outputFormat = SimpleDateFormat("HH:mm")
            @SuppressLint("SimpleDateFormat") val otherInput =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            otherInput.timeZone = TimeZone.getTimeZone("UTC")
            try {
                reformattedDate = outputFormat.format(inputFormat.parse(time))
            } catch (e: ParseException) {
                try {
                    reformattedDate = outputFormat.format(otherInput.parse(time.replace("Z", "")))
                } catch (e1: ParseException) {
                    e1.printStackTrace()
                }

            }

            return reformattedDate
        }

        fun getTimeFormatedUpdate(time: String): String? {
            var reformattedDate: String? = null
            @SuppressLint("SimpleDateFormat") val inputFormat =
                SimpleDateFormat("dd-MM-yyyy HH:mm")
            @SuppressLint("SimpleDateFormat") val outputFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
            @SuppressLint("SimpleDateFormat") val otherInput =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            otherInput.timeZone = TimeZone.getTimeZone("UTC")
            try {
                reformattedDate = outputFormat.format(inputFormat.parse(time))
            } catch (e: ParseException) {
                try {
                    reformattedDate = outputFormat.format(otherInput.parse(time.replace("", "Z")))
                } catch (e1: ParseException) {
                    e1.printStackTrace()
                }

            }

            return reformattedDate
        }

        fun getDateFormated(time: String): String? {
            var reformattedDate: String? = null
            @SuppressLint("SimpleDateFormat") val inputFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
            @SuppressLint("SimpleDateFormat") val outputFormat = SimpleDateFormat("dd MMM")
            @SuppressLint("SimpleDateFormat") val otherInput =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            otherInput.timeZone = TimeZone.getTimeZone("UTC")
            try {
                reformattedDate = outputFormat.format(inputFormat.parse(time))
            } catch (e: ParseException) {
                try {
                    reformattedDate = outputFormat.format(otherInput.parse(time.replace("Z", "")))
                } catch (e1: ParseException) {
                    e1.printStackTrace()
                }

            }

            return reformattedDate
        }

        fun getDateFormatedNew(time: String): String? {
            var reformattedDate: String? = null
            @SuppressLint("SimpleDateFormat") val inputFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.getDefault())
            @SuppressLint("SimpleDateFormat") val outputFormat =
                SimpleDateFormat("EEE, dd MMM YYYY HH:mm")
            @SuppressLint("SimpleDateFormat") val otherInput =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            otherInput.timeZone = TimeZone.getTimeZone("UTC")
            try {
                reformattedDate = outputFormat.format(inputFormat.parse(time))
            } catch (e: ParseException) {
                try {
                    reformattedDate = outputFormat.format(otherInput.parse(time.replace("Z", "")))
                } catch (e1: ParseException) {
                    e1.printStackTrace()
                }

            }

            return reformattedDate
        }

        fun getDateFormatedUpdate(time: String): String? {
            var reformattedDate: String? = null
            @SuppressLint("SimpleDateFormat") val inputFormat =
                SimpleDateFormat("dd MMMM yyy HH:mm")
            val outputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ", Locale.ENGLISH)
            try {
                reformattedDate = outputFormat.format(inputFormat.parse(time))
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return reformattedDate
        }

        fun getMonthAndYearFormated(time: Long, offset: Int): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time
            calendar.add(Calendar.MONTH, offset)
            return getDateFormated("MMM yyyy", calendar.timeInMillis)
        }

        fun getEndTimeFormated(time: Long): String {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = time
            calendar.add(Calendar.HOUR_OF_DAY, 1)
            return getDateFormated("HH:mm", calendar.timeInMillis)
        }

        fun isSameDay(selectedDate: Long, compareDate: Long): Boolean {
            return getDateFormated("ddMMMyyyy", selectedDate) == getDateFormated(
                "ddMMMyyyy",
                compareDate
            )
        }

        fun isToday(selectedDate: Long): Boolean {
            return getDateFormated("ddMMMyyyy", selectedDate) == getDateFormated(
                "ddMMMyyyy",
                getCurrentDate().timeInMillis
            )
        }

        fun getMonthDifference(selectedCalendarTime: Long): Int {
            val selectedCalendar = Calendar.getInstance(Locale("en", "EN"))
            selectedCalendar.timeInMillis = selectedCalendarTime
            val now = Calendar.getInstance(Locale("en", "EN"))
            val m1 = selectedCalendar.get(Calendar.YEAR) * 12 + selectedCalendar.get(Calendar.MONTH)
            val m2 = now.get(Calendar.YEAR) * 12 + now.get(Calendar.MONTH)
            return m1 - m2
        }

        fun getCurrentDate(): Calendar {
            return Calendar.getInstance(Locale("en", "EN"))
        }

        fun getCurrentTime(): Calendar {
            return Calendar.getInstance(Locale("en", "EN"))
        }

        fun getCurrentDate(selectedDate: Long?): Calendar {
            val calendar = Calendar.getInstance(Locale("en", "EN"))
            calendar.timeInMillis = selectedDate!!
            return calendar
        }

        fun getCurrentDateNoTime(selectedDate: Long?): Calendar {
            val calendar = Calendar.getInstance(Locale("en", "EN"))
            calendar.timeInMillis = selectedDate!!
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)
            return calendar
        }

        fun getDayUpperCase(selectedDate: Long?): String {
            return getDateFormated("EEE", selectedDate!!, Locale("en")).toUpperCase()
        }

        fun getDayCapitalize(selectedDate: Long?): String {
            return getDateFormated("EEEE", selectedDate!!, Locale("en"))
        }

        fun getActualDateFormated(date: Long?): String {
            return getDateFormated("yyyy-MM-dd'T'HH:mm:ssZZZZZ", date!!)
        }

        fun getCurretHour(): Int {
            return Integer.valueOf(getDateFormated("HH:mm", getCurrentDate().timeInMillis))
        }

        fun getFullDateFormated(date: Long?): String {
            return getDateFormated("EEE, dd MMM yyyy", date!!)
        }

        fun getTimeFormated(time: Long?): String {
            return getDateFormated("HH:mm", time!!)
        }

        fun getDateFormated(date: Long?): String {
            return getDateFormated("dd MMMM yyyy", date!!)
        }

        fun greaterThanLimit(time: String): Boolean {
            val currentTime = Integer.valueOf(getDateFormatedNow("HH"))
            return Integer.valueOf(
                time.replace(
                    ".",
                    ":"
                ).split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            ) > currentTime + 6
        }

        fun getHourFromTime(time: String): Int {
            return Integer.valueOf(
                time.replace(".", ":")
                    .split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            )
        }

        fun getMinutesFromTime(time: String): Int {
            return Integer.valueOf(
                time.replace(".", ":")
                    .split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
            )
        }

        fun getMapFormattedTimeWithoutDays(seconds: Long): Map<String, String> {
            val time = HashMap<String, String>()
            val hours = TimeUnit.SECONDS.toHours(seconds)
            val minute =
                TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60
            val second =
                TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60
            time[HOUR] = String.format(Locale.getDefault(), "%02d", hours)
            time[MINUTE] = String.format(Locale.getDefault(), "%02d", minute)
            time[SECOND] = String.format(Locale.getDefault(), "%02d", second)
            return time
        }

        fun getDateInEpoch(date: String): Long {
            val newDate = date.replace(
                ".",
                "_"
            ).split("_".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0]
            @SuppressLint("SimpleDateFormat")
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
            try {
                val calendar = getCurrentDate(simpleDateFormat.parse(newDate).time)
                return calendar.timeInMillis
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return getCurrentDate().timeInMillis
        }

        fun getFirstDayOfWeek(): DayOfWeek? {
            when (getDayUpperCase(getCurrentDate().timeInMillis)) {
                "SUN", "MON" -> {
                }
                "TUE" -> {
                }
                "WED" -> {
                }
                "THU" -> {
                }
                "FRI" -> {
                }
                "SAT" -> {
                }
            }
            return null
        }

        fun getDayUpperCaseFromCalendarDay(day: String): String {
            @SuppressLint("SimpleDateFormat")
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            try {
                val calendar = getCurrentDate(simpleDateFormat.parse(day).time)
                return getDayUpperCase(calendar.timeInMillis)
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return getDayUpperCase(getCurrentDate().timeInMillis)
        }

        fun belowToday(startDateEpoch: Long): Boolean {
            return getCurrentDate().timeInMillis >= startDateEpoch
        }

        fun getTimeByHour(hour: Int): String {
            return StringHelper.getStringBuilderToString(
                StringHelper.convertIntToValue(hour),
                ":00"
            )
        }

        fun getActualDate(date: Long, time: Int, minut: Int): String {
            val calendar = getCurrentDate(date)
            calendar.set(Calendar.AM_PM, Calendar.AM_PM)
            calendar.set(Calendar.HOUR_OF_DAY, time)
            calendar.set(Calendar.MINUTE, minut)
            calendar.set(Calendar.SECOND, 0)
            return getActualDateFormated(calendar.timeInMillis)
        }

        fun getActualDate(date: Long): String {
            val calendar = getCurrentDate(date)
            return getActualDate(
                date,
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE)
            )
        }
    }
}