package com.example.exerciseapplication.data

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            """
            ALTER TABLE workout
            ADD COLUMN notes TEXT NOT NULL DEFAULT ''
            """.trimIndent()
        )
    }
}
