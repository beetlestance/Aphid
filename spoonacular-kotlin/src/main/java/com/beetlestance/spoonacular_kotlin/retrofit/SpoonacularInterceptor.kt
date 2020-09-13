package com.beetlestance.spoonacular_kotlin.retrofit

import com.beetlestance.spoonacular_kotlin.Spoonacular
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response


internal class SpoonacularInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()

        if (request.url.host != Spoonacular.SPOONACULAR_API_HOST) {
            // do not intercept requests for other hosts
            // this allows the interceptor to be used on a shared okhttp client
            return chain.proceed(request)
        }

        // add (or replace) the API key query parameter
        val urlBuilder: HttpUrl.Builder = request.url.newBuilder()
        urlBuilder.setQueryParameter(PARAM_API_KEY, apiKey)

        val builder: Request.Builder = request.newBuilder()
        builder.url(urlBuilder.build())

        return chain.proceed(builder.build())
    }

    companion object {
        private val PARAM_API_KEY = "apiKey"
    }

}
