package com.beetlestance.aphid.dicebar_kotlin

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.avataar.AvataaarsConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.avataar.AvataaarsSprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.bottts.BotttsConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.bottts.BotttsSprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.female.FemaleConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.female.FemaleSprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.gridy.GridyConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.gridy.GridySprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.human.HumanConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.human.HumanSprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.identicon.IdenticonConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.identicon.IdenticonSprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.initials.InitialsConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.initials.InitialsSprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.jdenticon.JdenticonConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.jdenticon.JdenticonSprite
import com.beetlestance.aphid.dicebar_kotlin.sprites.male.MaleConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.male.MaleSprite
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.HttpUrl

object DiceBarAvatarHelper {

    /**
     * Hypertext Transfer Protocol Secure (HTTPS) scheme for DiceBar Api
     * */
    private const val DICE_BAR_URL_SCHEME: String = "https"

    /**
     * Api Host for for DiceBar Api
     * */
    private const val DICE_BAR_API_HOST: String = "avatars.dicebear.com"

    /**
     * Api Version for DiceBar Api
     * */
    private const val DICE_BAR_API_VERSION: String = "4.1"

    /**
     * Api Version for DiceBar Api
     * */
    private const val DICE_BAR_API_FRAGMENT: String = "api"

    /**
     * Moshi to serialize config collection and convert to queryParams
     * */
    private val moshi: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    /**
     * Svg options for DiceBar Api
     * */
    private fun DiceBarConfig.toQueryParams(): Map<String, Any>? {
        val configJson = moshi.adapter(this.javaClass).toJson(this)
        val queryParamsType = Types.newParameterizedType(
            Map::class.java, String::class.java, Any::class.java
        )
        return moshi.adapter<Map<String, Any>>(queryParamsType).fromJson(configJson)
    }

    /**
     * Create Svg Url with styling options.
     *
     * @param sprite replace with [AvataaarsSprite], [BotttsSprite], [FemaleSprite], [GridySprite],
     * [HumanSprite], [IdenticonSprite], [InitialsSprite], [JdenticonSprite], [MaleSprite]
     * @param seed can be anything you like - but don't use any sensitive or personal data here!
     * @param config The used avatar style may offer additional options. Replace with
     * [AvataaarsConfig], [BotttsConfig], [FemaleConfig], [GridyConfig] ,[HumanConfig],
     * [IdenticonConfig], [InitialsConfig], [JdenticonConfig], [MaleConfig]
     * @return [HttpUrl] for svg with styles and options
     *
     * @see <a href="https://avatars.dicebear.com/docs/http-api">DiceBar HTTP-API</a>
     * */
    fun createAvatarUrl(sprite: DiceBarSprite, seed: String, config: DiceBarConfig): HttpUrl {
        // Extract user defined styling params
        val queryParams = config.toQueryParams()

        // Create Api base url
        val baseUrl = HttpUrl.Builder()
            .scheme(DICE_BAR_URL_SCHEME)
            .host(DICE_BAR_API_HOST)
            .addPathSegment(DICE_BAR_API_VERSION)
            .addPathSegment(DICE_BAR_API_FRAGMENT)
            .addPathSegment(sprite.type)
            .addPathSegment("$seed.svg")

        // Add query params to url builder
        queryParams?.forEach { (name, value) ->
            when (value) {
                is List<*> -> value.forEach { option ->
                    // Need to specify array params explicitly
                    baseUrl.addQueryParameter("$name[]", option.toString())
                }
                else -> baseUrl.addQueryParameter(name, value.toString())
            }
        }

        return baseUrl.build()
    }
}