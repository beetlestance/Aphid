package com.beetlestance.aphid.dicebar_kotlin.avataar

import com.beetlestance.aphid.dicebar_kotlin.DiceBarSeed

/**
 * The value of [DiceBarSeed] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/avataaars">Avataaars Seed Options</a>
 * */
data class AvataaarsSeed(

    @AvataaarStyle.PossibleValues
    val style: String = AvataaarStyle.TRANSPARENT,

    @AvataaarMode.PossibleValues
    val mode: String = AvataaarMode.INCLUDE,

    @AvataaarTop.PossibleValues
    val top: List<String>? = null,

    // Probability in percent
    val topChance: Int = 100,

    @AvataaarHat.PossibleValues
    val hatColor: List<String>? = null,

    @AvataaarHairColor.PossibleValues
    val hairColor: List<String>? = null,

    @AvataaarAccessories.PossibleValues
    val accessories: List<String>? = null,

    // Probability in percent
    val accessoriesChance: Int = 10,

    @AvataaarAccessoriesColor.PossibleValues
    val accessoriesColor: List<String>? = null,

    @AvataaarFacialHair.PossibleValues
    val facialHair: List<String>? = null,

    // Probability in percent
    val facialHairChance: Int = 10,

    @AvataaarFacialHairColor.PossibleValues
    val facialHairColor: List<String>? = null,

    @AvataaarClothes.PossibleValues
    val clothes: List<String>? = null,

    @AvataaarClothesColor.PossibleValues
    val clothesColor: List<String>? = null,

    @AvataaarEyes.PossibleValues
    val eyes: List<String>? = null,

    @AvataaarEyeBrow.PossibleValues
    val eyebrow: List<String>? = null,

    @AvataaarMouth.PossibleValues
    val mouth: List<String>? = null,

    @AvataaarSkin.PossibleValues
    val skin: List<String>? = null

) : DiceBarSeed()