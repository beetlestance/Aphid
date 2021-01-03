package com.beetlestance.spoonacular_kotlin.services

import com.beetlestance.spoonacular_kotlin.SpoonacularTest
import com.google.common.truth.Truth
import com.google.common.truth.TruthFailureSubject
import com.google.common.truth.TruthJUnit
import junit.framework.Assert.fail
import org.junit.Test

class FoodTest : SpoonacularTest() {

    val foodService = spoonacular.createFoodService()

}