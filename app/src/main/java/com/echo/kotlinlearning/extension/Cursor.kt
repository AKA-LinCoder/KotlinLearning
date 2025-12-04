package com.echo.kotlinlearning.extension

import android.database.Cursor

fun Cursor.getStringSafe(columnName: String, defaultValue: String = ""): String {
    val index = getColumnIndex(columnName)
    return if (index >= 0) getString(index) else defaultValue
}

fun Cursor.getIntSafe(columnName: String, defaultValue: Int = 0): Int {
    val index = getColumnIndex(columnName)
    return if (index >= 0) getInt(index) else defaultValue
}

fun Cursor.getFloatSafe(columnName: String, defaultValue: Float = 0f): Float {
    val index = getColumnIndex(columnName)
    return if (index >= 0) getFloat(index) else defaultValue
}

fun Cursor.getDoubleSafe(columnName: String, defaultValue: Double = 0.0): Double {
    val index = getColumnIndex(columnName)
    return if (index >= 0) getDouble(index) else defaultValue
}

fun Cursor.getLongSafe(columnName: String, defaultValue: Long = 0L): Long {
    val index = getColumnIndex(columnName)
    return if (index >= 0) getLong(index) else defaultValue
}
