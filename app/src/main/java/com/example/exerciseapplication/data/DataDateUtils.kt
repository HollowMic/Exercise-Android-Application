package com.example.exerciseapplication.data

import androidx.room.TypeConverter
import java.time.LocalDate


class DateConverters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate): String = date.toString()

    @TypeConverter
    fun toLocalDate(value: String): LocalDate = LocalDate.parse(value)
}
