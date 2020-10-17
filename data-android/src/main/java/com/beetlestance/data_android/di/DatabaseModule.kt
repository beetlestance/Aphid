package com.beetlestance.data_android.di

import android.content.Context
import android.os.Debug
import androidx.room.Room
import com.beetlestance.data.AphidDatabase
import com.beetlestance.data_android.AphidRoomDatabase
import com.beetlestance.data_android.AphidRoomDatabase_Migrations
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
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

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseDaoModule {

    @Provides
    fun provideAphidRecipes(db: AphidDatabase) = db.recipesDao()
}

@InstallIn(ApplicationComponent::class)
@Module
abstract class DatabaseModuleBinds {

    @Binds
    abstract fun bindAphidDatabase(db: AphidRoomDatabase): AphidDatabase

}
