package com.beetlestance.aphid.data

import com.beetlestance.aphid.data.daos.RecipeDao

interface AphidDatabase {

    fun recipesDao(): RecipeDao

}

internal object AphidTables {

    const val RECIPE_TABLE: String = "recipe_table"

}
