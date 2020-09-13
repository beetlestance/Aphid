package com.beetlestance.spoonacular_kotlin

import com.beetlestance.spoonacular_kotlin.retrofit.SpoonacularInterceptor
import com.beetlestance.spoonacular_kotlin.services.RecipesService
import com.beetlestance.spoonacular_kotlin.utils.MoshiSerializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


open class Spoonacular(private val apiKey: String) {

    /**
     * Returns the default OkHttp client instance. It is strongly recommended to override this and use your app
     * instance.
     */
    protected open fun okHttpClient(): OkHttpClient {
        return okHttpClientBuilder().build()
    }

    /**
     * Returns the default OkHttp client builder. It is strongly recommended to override this and use your app
     * instance.
     *
     * @see setOkHttpClientInterceptors
     */
    protected open fun okHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
        builder.setOkHttpClientInterceptors()
        return builder
    }

    /**
     * Return the current [Retrofit] instance.
     *
     * When building, sets the base url and a custom client with an [SpoonacularInterceptor] which supplies authentication
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
    protected open fun retrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(SPOONACULAR_API_URL)
            .addConverterFactory(MoshiConverterFactory.create(MoshiSerializer.moshi))
            .client(okHttpClient())
    }

    /**
     * Adds an interceptor to add the api key query parameter and to log requests.
     */
    protected open fun OkHttpClient.Builder.setOkHttpClientInterceptors() {
        addInterceptor(SpoonacularInterceptor(apiKey))
    }

    /**
     * Provides all Api's related to recipe's
     * */
    fun createRecipeService(): RecipesService {
        return retrofit().create(RecipesService::class.java)
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
