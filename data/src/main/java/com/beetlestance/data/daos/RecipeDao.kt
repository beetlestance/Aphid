package com.beetlestance.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.beetlestance.data.AphidTables
import com.beetlestance.data.entities.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
abstract class RecipeDao : EntityDao<Recipe>() {

    @Transaction
    @Query(value = ALL_RECIPE_QUERY)
    abstract fun recipes(): Flow<List<Recipe>>

    @Query(value = ALL_RECIPE_QUERY)
    abstract suspend fun allRecipes(): List<Recipe>

    companion object {
        const val ALL_RECIPE_QUERY = "SELECT * FROM ${AphidTables.RECIPE_TABLE}"
    }
}
