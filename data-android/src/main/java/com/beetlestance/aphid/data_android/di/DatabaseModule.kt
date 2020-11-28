package com.beetlestance.aphid.data_android.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.beetlestance.aphid.data.AphidDatabase
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
    fun provideAphidRecipes(db: AphidDatabase) = db.recipesDao()

    @Provides
    fun provideAphidChats(db: AphidDatabase) = db.chatDao()
}

@InstallIn(SingletonComponent::class)
@Module
abstract class DatabaseModuleBinds {

    @Binds
    abstract fun bindAphidDatabase(db: AphidRoomDatabase): AphidDatabase

}
