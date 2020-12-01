package com.beetlestance.aphid.dicebar_kotlin

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl

object DiceBarAvatarHelper {
    /**
     * Hypertext Transfer Protocol Secure (HTTPS) scheme
     * */
    private const val DICE_BAR_URL_SCHEME: String = "https"

    /**
     * Api Host for  Api's
     * */
    private const val DICE_BAR_API_HOST: String = "avatars.dicebear.com"

    /**
     * Api Version for  Api's
     * */
    private const val DICE_BAR_API_VERSION: String = "4.1"

    /**
     * Api Version for  Api's
     * */
    private const val DICE_BAR_API_FRAGMENT: String = "api"

    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    fun createAvatarUrl(sprite: DiceBarSprite, seed: String, config: DiceBarConfig): HttpUrl {
        val configJson = moshi.adapter(config.javaClass).toJson(config)
        val queryParamsType = Types.newParameterizedType(
            Map::class.java, String::class.java, Any::class.java
        )
        val queryParams = moshi.adapter<Map<String, Any>>(queryParamsType).fromJson(configJson)

        val baseUrl = HttpUrl.Builder()
            .scheme(DICE_BAR_URL_SCHEME)
            .host(DICE_BAR_API_HOST)
            .addPathSegment(DICE_BAR_API_VERSION)
            .addPathSegment(DICE_BAR_API_FRAGMENT)
            .addPathSegment(sprite.type)
            .addPathSegment("$seed.svg")

        queryParams?.forEach { (name, value) ->
            when (value) {
                is List<*> -> value.forEach { option ->
                    baseUrl.addQueryParameter("$name[]", option.toString())
                }
                else -> baseUrl.addQueryParameter(name, value.toString())
            }
        }

        return baseUrl.build()
    }
}