package com.beetlestance.aphid.data_android.repositories

interface RecipesRepository {

    suspend fun fetchRecipes()
}
