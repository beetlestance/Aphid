/*
 * Copyright 2021 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beetlestance.aphid.data_android.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.beetlestance.aphid.data.AphidDatabase
import com.beetlestance.aphid.data.daos.ChatDao
import com.beetlestance.aphid.data.daos.RecipeDao
import com.beetlestance.aphid.data_android.AphidRoomDatabase
import com.beetlestance.aphid.data_android.AphidRoomDatabase_Migrations
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RoomDatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AphidRoomDatabase {
        val builder = Room.databaseBuilder(context, AphidRoomDatabase::class.java, "aphid.db")
            .addMigrations(*AphidRoomDatabase_Migrations.build())
            .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object DatabaseDaoModule {

    @Provides
    fun provideAphidRecipes(db: AphidDatabase): RecipeDao = db.recipesDao()

    @Provides
    fun provideAphidChats(db: AphidDatabase): ChatDao = db.chatDao()
}

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModuleBinds {

    @Binds
    abstract fun bindAphidDatabase(db: AphidRoomDatabase): AphidDatabase
}
