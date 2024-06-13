package com.example.exerciseapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.UUID

@Entity(tableName = "exerciseEntry")
data class ExerciseEntry (
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @Relation(
        parentColumn = "id",
        entityColumn = "exerciseTypeId"
    )
    @ColumnInfo(name = "exercise_sets") val exerciseSetDefault: Int,
    @ColumnInfo(name = "exercise_reps") val exerciseRepDefault: Int,
)