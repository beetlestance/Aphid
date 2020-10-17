package com.beetlestance.data_android

import androidx.room.Database
import androidx.room.RoomDatabase
import com.beetlestance.data.AphidDatabase
import com.beetlestance.data.entities.Recipes
import dev.matrix.roomigrant.GenerateRoomMigrations

@Database(
    entities = [
        Recipes::class
    ],
    version = 26
)
@GenerateRoomMigrations
abstract class AphidRoomDatabase : RoomDatabase(), AphidDatabase
