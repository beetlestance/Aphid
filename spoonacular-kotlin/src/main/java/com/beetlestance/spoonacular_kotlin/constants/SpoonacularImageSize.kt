package com.beetlestance.spoonacular_kotlin.constants

@Suppress("unused")
object SpoonacularImageSize {

    object Equipment {
        const val LOW_QUALITY: String = "100x100"
        const val MEDIUM_QUALITY: String = "250x250"
        const val HIGH_QUALITY: String = "500x500"
    }

    object Grocery {
        const val LOW_QUALITY: String = "90x90"
        const val MEDIUM_QUALITY: String = "312x231"
        const val HIGH_QUALITY: String = "636x393"
    }

    object Ingredient {
        const val LOW_QUALITY: String = "100x100"
        const val MEDIUM_QUALITY: String = "250x250"
        const val HIGH_QUALITY: String = "500x500"
    }

    object MenuItem {
        const val LOW_QUALITY: String = "90x90"
        const val MEDIUM_QUALITY: String = "312x231"
        const val HIGH_QUALITY: String = "636x393"
    }

    object Recipe {
        const val ULTRA_LOW_QUALITY: String = "90x90"
        const val VERY_LOW_QUALITY: String = "240x150"
        const val LOW_QUALITY: String = "312x150"
        const val MEDIUM_QUALITY: String = "312x231"
        const val HIGH_QUALITY: String = "480x360"
        const val VERY_HIGH_QUALITY: String = "556x370"
        const val ULTRA_HIGH_QUALITY: String = "636x393"
    }

}
