package com.example.exerciseapplication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
import java.util.UUID
import com.example.exerciseapplication.data.serializer.UUIDSerializer


@Serializable
@Entity(tableName = "exercise")
data class Exercise (
    @Serializable(with = UUIDSerializer::class)
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "exercise_name") val exerciseName: String,
    @ColumnInfo(name = "default_weight_amount") val defaultWeightAmount: Float,
    @ColumnInfo(name = "exercise_default_sets") val exerciseSetDefault: Int,
    @ColumnInfo(name = "exercise_default_reps") val exerciseRepDefault: Int,
    @ColumnInfo(name = "in_current_rotation") val inCurrentRotation: Boolean,
)

