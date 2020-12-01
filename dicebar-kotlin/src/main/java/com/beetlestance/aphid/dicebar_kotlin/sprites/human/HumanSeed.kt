package com.beetlestance.aphid.dicebar_kotlin.sprites.human

import com.beetlestance.aphid.dicebar_kotlin.sprites.DiceBarSeed


/**
 * The value of [DiceBarSeed] can be anything you like - but don't use any sensitive or personal data here!
 *
 * @see <a href="https://avatars.dicebear.com/styles/human">Human Seed Options</a>
 * */

data class HumanSeed(

    @HumanMood.PossibleValues
    val mood: List<String>? = null,

    ) : DiceBarSeed()