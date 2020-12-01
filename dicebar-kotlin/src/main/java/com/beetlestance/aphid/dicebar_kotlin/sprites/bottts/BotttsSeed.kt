package com.beetlestance.aphid.dicebar_kotlin.sprites.bottts

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSeed


/**
 * The value of [DiceBarSeed] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/bottts">Bottts Seed Options</a>
 * */

data class BotttsSeed(

    @BotttsColor.PossibleValues
    val colors: List<String>? = null,

    @BotttsPrimaryColorLevel.PossibleValues
    val primaryColorLevel: Int = BotttsPrimaryColorLevel.SIX_HUNDRED,

    @BotttsSecondaryColorLevel.PossibleValues
    val secondaryColorLevel: Int = BotttsSecondaryColorLevel.FOUR_HUNDRED,

    // Assigns sides and top a random secondary color
    val colorful: Boolean = false,

    //Probability in percent avatar will have a mouth
    val mouthChance: Int = 100,

    // Probability in percent avatar will have side elements
    val sidesChance: Int = 100,

    // Probability in percent avatar will have texture
    val textureChance: Int = 50,

    // Probability in percent avatar will have a top element
    val topChance: Int = 100

) : DiceBarSeed()