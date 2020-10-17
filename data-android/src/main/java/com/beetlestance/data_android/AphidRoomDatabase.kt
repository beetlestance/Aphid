package com.beetlestance.data_android

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beetlestance.data.AphidDatabase
import com.beetlestance.data.entities.Recipe
import dev.matrix.roomigrant.GenerateRoomMigrations

@Database(
    entities = [
        Recipe::class
    ],
    version = 1
)
@GenerateRoomMigrations
abstract class AphidRoomDatabase : RoomDatabase(), AphidDatabase
