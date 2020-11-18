package com.beetlestance.aphid.data

import com.beetlestance.aphid.data.daos.ChatDao
import com.beetlestance.aphid.data.daos.RecipeDao

interface AphidDatabase {
    fun recipesDao(): RecipeDao
    fun chatDao(): ChatDao
}

internal object AphidTables {

    const val RECIPE_TABLE: String = "recipe_table"
    const val CHAT_TABLE: String = "chat_table"

}
