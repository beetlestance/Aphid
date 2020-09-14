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
package com.beetlestance.spoonacular_kotlin.constants

@Suppress("unused")
object Diets {

    /**
     * Eliminating gluten means avoiding wheat, barley, rye, and other gluten-containing grains and
     * foods made from them (or that may have been cross contaminated).
     * */
    const val GLUTEN_FREE: String = "Gluten Free"

    /**
     * The keto diet is based more on the ratio of fat, protein, and carbs in the diet rather than
     * specific ingredients. Generally speaking, high fat, protein-rich foods are acceptable and
     * high carbohydrate foods are not.
     */
    const val KETOGENIC: String = "Ketogenic"

    /**
     * No ingredients may contain meat or meat by-products, such as bones or gelatin.
     */
    const val VEGETARIAN: String = "Vegetarian"

    /**
     *  All ingredients must be vegetarian and none of the ingredients can be or contain egg.
     */
    const val LACTO_VEGETARIAN: String = "Lacto-Vegetarian"

    /**
     * All ingredients must be vegetarian and none of the ingredients can be or contain dairy.
     * */
    const val OVO_VEGETARIAN: String = "Ovo-Vegetarian"

    /**
     * No ingredients may contain meat or meat by-products, such as bones or gelatin,
     * nor may they contain eggs, dairy, or honey.
     */
    const val VEGAN: String = "Vegan"

    /** Everything is allowed except meat and meat by-products - some pescetarians eat eggs and dairy, some do not.*/
    const val PESCETARIAN: String = "Pescetarian"

    /**
     * Allowed ingredients include meat (especially grass fed), fish, eggs, vegetables,
     * some oils (e.g. coconut and olive oil), and in smaller quantities, fruit, nuts,
     * and sweet potatoes. We also allow honey and maple syrup (popular in Paleo desserts,
     * but strict Paleo followers may disagree). Ingredients not allowed include legumes
     * (e.g. beans and lentils), grains, dairy, refined sugar, and processed foods.
     */
    const val PALEO: String = "Paleo"

    /**
     * Very similar to Paleo, except dairy is allowed - think raw and full fat milk,
     * butter, ghee, etc.
     */
    const val PRIMAL: String = "Primal"

    /** Allowed ingredients include meat, fish/seafood, eggs, vegetables, fresh fruit, coconut oil,
     *  olive oil, small amounts of dried fruit and nuts/seeds. Ingredients not allowed include
     *  added sweeteners (natural and artificial, except small amounts of fruit juice),
     *  dairy (except clarified butter or ghee), alcohol, grains, legumes (except green beans,
     *  sugar snap peas, and snow peas), and food additives, such as carrageenan, MSG, and sulfites.
     */
    const val WHOLE_30: String = "Whole30"
}
