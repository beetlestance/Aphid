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
package com.beetlestance.aphid.spoonacular.kotlin.retrofit

import com.beetlestance.aphid.spoonacular.kotlin.Spoonacular
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
        private const val PARAM_API_KEY = "apiKey"
    }
}
