package com.beetlestance.data

import com.beetlestance.data.daos.RecipeDao

interface AphidDatabase {

    fun recipesDao(): RecipeDao

}

internal object AphidTables {

    const val RECIPE_TABLE: String = "recipe_table"

}
