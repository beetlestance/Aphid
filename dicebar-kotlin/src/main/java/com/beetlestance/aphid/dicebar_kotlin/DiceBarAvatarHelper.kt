package com.beetlestance.aphid.dicebar_kotlin

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite
import com.beetlestance.aphid.dicebar_kotlin.utils.toQueryParams
import okhttp3.HttpUrl

object DiceBarAvatarHelper {
    /**
     * Hypertext Transfer Protocol Secure (HTTPS) scheme
     * */
    private const val DICE_BAR_URL_SCHEME = "https"

    /**
     * Api Host for  Api's
     * */
    private const val DICE_BAR_API_HOST = "avatars.dicebear.com"

    /**
     * Api Version for  Api's
     * */
    private const val DICE_BAR_API_VERSION = "4.1"

    /**
     * Api Version for  Api's
     * */
    private const val DICE_BAR_API_FRAGMENT = "api"

    fun createAvatarUrl(sprite: DiceBarSprite, seed: String, config: DiceBarConfig): HttpUrl {
        val queryParams = config.toQueryParams()

        val baseUrl = HttpUrl.Builder()
            .scheme(DICE_BAR_URL_SCHEME)
            .host(DICE_BAR_API_HOST)
            .addPathSegment(DICE_BAR_API_VERSION)
            .addPathSegment(DICE_BAR_API_FRAGMENT)
            .addPathSegment(sprite.type)
            .addPathSegment("$seed.svg")

        queryParams?.forEach { (name, value) ->
            baseUrl.addQueryParameter(name, value)
        }

        return baseUrl.build()
    }
}