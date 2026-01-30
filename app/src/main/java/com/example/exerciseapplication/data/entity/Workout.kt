package com.example.exerciseapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.exerciseapplication.data.serializer.LocalDateSerializer
import com.example.exerciseapplication.data.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.util.UUID

@Serializable
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
    indices = [
        Index("exerciseId"),
        Index(
            value = ["exerciseId", "performedDate"],
            unique = true
    )]
)
data class Workout(
    @Serializable(with = UUIDSerializer::class)
    @PrimaryKey val id: UUID,
    @Serializable(with = UUIDSerializer::class)
    val exerciseId: UUID,
    @Serializable(with = LocalDateSerializer::class)
    val performedDate: LocalDate,
    val reps: Int,
    val sets: Int,
    val weightAmount: Float,
    @ColumnInfo(defaultValue = "")
    val notes: String,
)

