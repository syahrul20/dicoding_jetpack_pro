package com.sf.jetpack.mymov.utils

import java.text.SimpleDateFormat
import java.util.*


fun convertDate(date: String, format: String, to: String): String {
    var dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
    val newDate: Date? = dateFormat.parse(date)
    dateFormat = SimpleDateFormat(to, Locale.ENGLISH)
    return dateFormat.format(newDate ?: "")

}