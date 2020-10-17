package com.beetlestance.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.beetlestance.data.AphidTables
import com.beetlestance.data.entities.Recipes
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RecipesDao : EntityDao<Recipes>() {

    @Transaction
    @Query("SELECT * FROM ${AphidTables.RECIPE_TABLE}")
    abstract fun recipes(): Flow<List<Recipes>>

}
