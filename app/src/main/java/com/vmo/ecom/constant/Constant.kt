package com.vmo.ecom.constant

object Constant {
    const val BASE_URL = "http://setel.axzae.com/"
    const val THUMB_URL = "https://source.unsplash.com/150x150/?restaurant,"

    const val WORKING_HOUR_FORMAT = "(\\\\d{1,2}:\\\\d{1,2} (am|pm)) (-) (\\\\d{1,2}:\\\\d{1,2} (am|pm))"
    private const val MONDAY = "Mon"
    private const val TUESDAY = "Tue"
    private const val WEDNESDAY = "Wed"
    private const val THURSDAY = "Thu"
    private const val FRIDAY = "Fri"
    private const val SATURDAY = "Sat"
    private const val SUNDAY = "Sun"
    val DOUBLE_WEEK_DAYS = arrayListOf(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY)
    val WEEK_DAYS = arrayListOf(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY)

    const val DATE_FORMAT_12 = "YYYY/mm/DD HH:mm a"
    const val HOUR_FORMAT_24 = "HH:mm"
    const val HOUR_FORMAT_12 = "hh:mm a"
    const val DAY_OF_WEEK_FORMAT = "EEE"
    const val DAY_IN_MILLISECONDS = 86400000 // 24 * 60 * 60 * 1000
    const val MINUTE_IN_MILLISECONDS = 60000 // 60 * 1000
    const val DAY_IN_MINUTES = 1440 // 24 * 60
}