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

import com.beetlestance.spoonacular_kotlin.retrofit.SpoonacularInterceptor
import com.beetlestance.spoonacular_kotlin.services.FoodService
import com.beetlestance.spoonacular_kotlin.services.MealPlannerService
import com.beetlestance.spoonacular_kotlin.services.RecipesService
import com.beetlestance.spoonacular_kotlin.services.UserService
import com.beetlestance.spoonacular_kotlin.utils.MoshiSerializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@Suppress("unused", "MemberVisibilityCanBePrivate")
open class Spoonacular(private val apiKey: String) {

    /**
     * Return the current [Retrofit] instance.
     *
     * When building, sets the base url and a custom client with an [SpoonacularInterceptor] which
     * supplies authentication
     * data.
     */
    protected open fun retrofit(): Retrofit {
        return retrofitBuilder().build()
    }

    /**
     * Creates a [Retrofit.Builder] that sets the base URL, adds a Moshi converter and sets [okHttpClient]
     * as its client.
     *
     * @see okHttpClient
     */
    fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(SPOONACULAR_API_URL)
            .addConverterFactory(MoshiConverterFactory.create(MoshiSerializer.moshi))
            .client(okHttpClient())
    }

    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and
     * use your app instance.
     */
    protected open fun okHttpClient(): OkHttpClient {
        return okHttpClientBuilder().build()
    }

    /**
     * Returns the default OkHttp client builder. It is strongly recommended to override this and
     * use your app instance.
     *
     * @see setOkHttpClientInterceptors
     */
    fun okHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.setOkHttpClientInterceptors()
        return builder
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    fun OkHttpClient.Builder.setOkHttpClientInterceptors() {
        addInterceptor(SpoonacularInterceptor(apiKey))
    }

    /**
     * Provides all Api's related to recipe's
     * */
    fun createRecipeService(): RecipesService {
        return retrofit().create(RecipesService::class.java)
    }

    /**
     * Provides all Api's related to foods
     * */
    fun createFoodService(): FoodService {
        return retrofit().create(FoodService::class.java)
    }

    /**
     * Provides all Api's related to meal planner
     * */
    fun createMealPlannerService(): MealPlannerService {
        return retrofit().create(MealPlannerService::class.java)
    }

    /**
     * Provides all Api's related to spoonacular users.
     * */
    fun createUserService(): UserService {
        return retrofit().create(UserService::class.java)
    }

    /**
     * Sets user credentials to avoid passing username and hash on every MealPlannerService Api
     * */
    fun setUserCredentials(userName: String, hash: String) {
        SpoonacularUserCredentials.spoonacularUserName = userName
        SpoonacularUserCredentials.spoonacularUserHash = hash
    }

    companion object {

        /**
         * Api Host for Spoonacular Api's
         * */
        internal const val SPOONACULAR_API_HOST = "api.spoonacular.com"

        /**
         * Base Url for Spoonacular Api's
         * */
        private const val SPOONACULAR_API_URL = "https://$SPOONACULAR_API_HOST"
    }
}
