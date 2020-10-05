package com.beetlestance.spoonacular_kotlin

internal object SpoonacularUserCredentials {

    var spoonacularUserName: String? = null

    val userName: String
        get() = requireNotNull(spoonacularUserName, { REASON_NO_USER_CREDENTIAL })

    var spoonacularUserHash: String? = null

    val userHash: String
        get() = requireNotNull(spoonacularUserHash, { REASON_NO_USER_CREDENTIAL })

    private const val REASON_NO_USER_CREDENTIAL = "No user credentials provided, pass it " +
            "manually or call Spoonacluar.setUserCredentials() before using MealPlannerService Api's"
}
