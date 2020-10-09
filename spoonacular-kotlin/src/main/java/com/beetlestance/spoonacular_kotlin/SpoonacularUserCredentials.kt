/*
 * Copyright 2020 BeetleStance
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
