package com.example.exerciseapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "exercise")
data class Exercise (
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "exercise_name") val exerciseName: String,
    @ColumnInfo(name = "exercise_default_sets") val exerciseSetDefault: Int,
    @ColumnInfo(name = "exercise_default_reps") val exerciseRepDefault: Int,
)

