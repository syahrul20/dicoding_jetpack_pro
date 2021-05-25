package com.sf.jetpack.mymov.utils

import java.text.SimpleDateFormat
import java.util.*


fun convertDate(date: String, format: String, to: String): String {
    var dateFormat = SimpleDateFormat(format)
    val newDate: Date = dateFormat.parse(date)
    dateFormat = SimpleDateFormat(to)
    return dateFormat.format(newDate)
}