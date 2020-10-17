package com.beetlestance.data

import com.beetlestance.data.daos.RecipesDao

interface AphidDatabase {

    fun recipesDao(): RecipesDao

}

internal object AphidTables {

    const val RECIPE_TABLE: String = "recipe_table"

}
