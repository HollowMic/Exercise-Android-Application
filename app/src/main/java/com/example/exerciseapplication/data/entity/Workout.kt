package com.example.exerciseapplication.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.UUID

@Entity(
    tableName = "workout",
    foreignKeys = [
        ForeignKey(
            entity = Exercise::class,
            parentColumns = ["id"],
            childColumns = ["exerciseId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("exerciseId")]
)
data class Workout(
    @PrimaryKey val id: UUID,
    val exerciseId: UUID,
    val performedDate: LocalDate,
    val reps: Int,
    val sets: Int,
    val weightAmount: Float,
)

