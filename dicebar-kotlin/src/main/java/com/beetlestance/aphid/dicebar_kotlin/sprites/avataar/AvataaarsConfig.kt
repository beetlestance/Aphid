package com.beetlestance.aphid.dicebar_kotlin.sprites.avataar

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarConfig
import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSpriteOptions
import com.squareup.moshi.Json

/**
 * The value of [DiceBarConfig] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/avataaars">Avataaars Config Options</a>
 * */
data class AvataaarsConfig(

    @AvataaarStyle.PossibleValues
    @Json(name = AvataaarOptions.STYLE)
    val style: String = AvataaarStyle.TRANSPARENT,

    @AvataaarMode.PossibleValues
    @Json(name = AvataaarOptions.MODE)
    val mode: String = AvataaarMode.INCLUDE,

    @AvataaarTop.PossibleValues
    @Json(name = AvataaarOptions.TOP)
    val top: List<String>? = null,

    // Probability in percent
    @Json(name = AvataaarOptions.TOP_CHANCE)
    val topChance: Float = 100f,

    @AvataaarHat.PossibleValues
    @Json(name = AvataaarOptions.HAT_COLOR)
    val hatColor: List<String>? = null,

    @AvataaarHairColor.PossibleValues
    @Json(name = AvataaarOptions.HAIR_COLOR)
    val hairColor: List<String>? = null,

    @AvataaarAccessories.PossibleValues
    @Json(name = AvataaarOptions.ACCESSORIES)
    val accessories: List<String>? = null,

    // Probability in percent
    @Json(name = AvataaarOptions.ACCESSORIES_CHANCE)
    val accessoriesChance: Float = 10f,

    @AvataaarAccessoriesColor.PossibleValues
    @Json(name = AvataaarOptions.ACCESSORIES_COLOR)
    val accessoriesColor: List<String>? = null,

    @AvataaarFacialHair.PossibleValues
    @Json(name = AvataaarOptions.FACIAL_HAIR)
    val facialHair: List<String>? = null,

    // Probability in percent
    @Json(name = AvataaarOptions.FACIAL_HAIR_CHANCE)
    val facialHairChance: Float = 10f,

    @AvataaarFacialHairColor.PossibleValues
    @Json(name = AvataaarOptions.FACIAL_HAIR_COLOR)
    val facialHairColor: List<String>? = null,

    @AvataaarClothes.PossibleValues
    @Json(name = AvataaarOptions.CLOTHES)
    val clothes: List<String>? = null,

    @AvataaarClothesColor.PossibleValues
    @Json(name = AvataaarOptions.CLOTHES_COLOR)
    val clothesColor: List<String>? = null,

    @AvataaarEyes.PossibleValues
    @Json(name = AvataaarOptions.EYES)
    val eyes: List<String>? = null,

    @AvataaarEyeBrow.PossibleValues
    @Json(name = AvataaarOptions.EYEBROW)
    val eyebrow: List<String>? = null,

    @AvataaarMouth.PossibleValues
    @Json(name = AvataaarOptions.MOUTH)
    val mouth: List<String>? = null,

    @AvataaarSkin.PossibleValues
    @Json(name = AvataaarOptions.SKIN)
    val skin: List<String>? = null,

    @Json(name = DiceBarSpriteOptions.RADIUS)
    override val radius: Int = 0,

    // Fixed width
    @Json(name = DiceBarSpriteOptions.WIDTH)
    override val width: Int? = null,

    // Fixed height
    @Json(name = DiceBarSpriteOptions.HEIGHT)
    override val height: Int? = null,

    // Avatar margin in percent. HTTP-API limitation Max value 25
    @Json(name = DiceBarSpriteOptions.MARGIN)
    override val margin: Int = 0,

    // Any valid color identifier. HTTP-API limitation Only hex (3-digit, 6-digit and 8-digit)
    // values are allowed. Use url encoded hash: %23.
    @Json(name = DiceBarSpriteOptions.BACKGROUND)
    override val background: String? = null

) : DiceBarConfig