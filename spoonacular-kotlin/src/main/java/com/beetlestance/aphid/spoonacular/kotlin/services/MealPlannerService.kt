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
package com.beetlestance.aphid.spoonacular.kotlin.services

import com.beetlestance.aphid.spoonacular.kotlin.SpoonacularUserCredentials.userHash
import com.beetlestance.aphid.spoonacular.kotlin.SpoonacularUserCredentials.userName
import com.beetlestance.aphid.spoonacular.kotlin.constants.Diets
import com.beetlestance.aphid.spoonacular.kotlin.models.request.mealplanner.RequestAddToShoppingList
import com.beetlestance.aphid.spoonacular.kotlin.models.request.mealplanner.addtomealplan.RequestAddIngredientsToMealPlan
import com.beetlestance.aphid.spoonacular.kotlin.models.request.mealplanner.addtomealplan.RequestAddMealPlanTemplateToMealPlan
import com.beetlestance.aphid.spoonacular.kotlin.models.request.mealplanner.addtomealplan.RequestAddToMealPlan
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.AddToMealPlan
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.AddToShoppingList
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.ClearMealPlanDay
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.DeleteFromMealPlan
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.DeleteFromShoppingList
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.GenerateMealPlan
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.GenerateShoppingList
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.MealPlanDay
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.MealPlanTemplate
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.MealPlanTemplates
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.MealPlanWeek
import com.beetlestance.aphid.spoonacular.kotlin.models.response.mealplanner.ShoppingList
import com.beetlestance.aphid.spoonacular.kotlin.services.endpoints.MealPlanner
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MealPlannerService {

    /**
     * Add to Meal Plan
     * Add an item to the user's meal plan.
     * @param username The username.
     * @param hash The private hash for the username.
     * @param requestAddToMealPlan
     * @see RequestAddMealPlanTemplateToMealPlan
     * @see RequestAddIngredientsToMealPlan
     * @return AddToMealPlan
     */
    @POST(MealPlanner.UserName.Items.ADD_TO_MEAL_PLAN)
    fun addToMealPlan(
        @Path("username") username: String = userName,
        @Query("hash") hash: String = userHash,
        @Body requestAddToMealPlan: RequestAddToMealPlan
    ): Call<AddToMealPlan>

    /**
     * Delete from Meal Plan
     * Delete an item from the user's meal plan.
     * @param username The username.
     * @param id The shopping list item id.
     * @param hash The private hash for the username.
     * @return DeleteFromMealPlan
     */
    @DELETE(MealPlanner.UserName.Items.DELETE_FROM_MEAL_PLAN)
    fun deleteFromMealPlan(
        @Path("username") username: String = userName,
        @Path("id") id: Long,
        @Query("hash") hash: String = userHash
    ): Call<DeleteFromMealPlan>

    /**
     * Get Meal Plan Template
     * Get information about a meal plan template.
     * @param username The username.
     * @param id The shopping list item id.
     * @param hash The private hash for the username.
     * @return Any
     */
    @GET(MealPlanner.UserName.Templates.GET_MEAL_PLAN_TEMPLATE)
    fun getMealPlanTemplate(
        @Path("username") username: String = userName,
        @Path("id") id: Long,
        @Query("hash") hash: String = userHash
    ): Call<MealPlanTemplate>

    /**
     * Get Meal Plan Templates
     * Get meal plan templates from user or public ones.
     * @param username The username.
     * @param hash The private hash for the username.
     * @return MealPlanTemplates
     */
    @GET(MealPlanner.UserName.Templates.GET_MEAL_PLAN_TEMPLATES)
    fun getMealPlanTemplates(
        @Path("username") username: String = userName,
        @Query("hash") hash: String = userHash
    ): Call<MealPlanTemplates>

    /**
     * Add to Shopping List
     * Add an item to the current shopping list of a user.
     * @param username The username.
     * @param hash The private hash for the username.
     * @param requestAddToShoppingList contains aisle and name of item
     * @return AddToShoppingList
     */
    @POST(MealPlanner.UserName.ShoppingLists.ADD_TO_SHOPPING_LIST)
    fun addToShoppingList(
        @Path("username") username: String = userName,
        @Query("hash") hash: String = userHash,
        @Body requestAddToShoppingList: RequestAddToShoppingList
    ): Call<AddToShoppingList>

    /**
     * Delete from Shopping List
     * Delete an item from the current shopping list of the user.
     * @param username The username.
     * @param id The shopping list item id.
     * @param hash The private hash for the username.
     * @return DeleteFromShoppingList
     */
    @DELETE(MealPlanner.UserName.ShoppingLists.DELETE_FROM_SHOPPING_LIST)
    fun deleteFromShoppingList(
        @Path("username") username: String = userName,
        @Path("id") id: Long,
        @Query("hash") hash: String = userHash
    ): Call<DeleteFromShoppingList>

    /**
     * Generate Shopping List
     * Generate the shopping list for a user from the meal planner in a given time frame.
     * @param username The username.
     * @param startDate The start date in the format yyyy-mm-dd.
     * @param endDate The end date in the format yyyy-mm-dd.
     * @param hash The private hash for the username.
     * @return GenerateShoppingList
     */
    @POST(MealPlanner.UserName.ShoppingLists.GENERATE_SHOPPING_LIST)
    fun generateShoppingList(
        @Path("username") username: String = userName,
        @Path("start-date") startDate: String,
        @Path("end-date") endDate: String,
        @Query("hash") hash: String = userHash
    ): Call<GenerateShoppingList>

    /**
     * Get Shopping List
     * Get the current shopping list for the given user.
     * @param username The username.
     * @param hash The private hash for the username.
     * @return ShoppingList
     */
    @GET(MealPlanner.UserName.ShoppingLists.GET_SHOPPING_LIST)
    fun getShoppingList(
        @Path("username") username: String = userName,
        @Query("hash") hash: String = userHash
    ): Call<ShoppingList>

    /**
     * Get Meal Plan Day
     * Retrieve a meal planned day for the given user. The username must be a spoonacular user and
     * the hash must the the user's hash that can be found in his/her account.
     * @param username The username.
     * @param date The start date of the meal planned week in the format yyyy-mm-dd.
     * @param hash The private hash for the username.
     * @return MealPlanDay
     */
    @GET(MealPlanner.UserName.GET_MEAL_PLAN_DAY)
    fun getMealPlanDay(
        @Path("username") username: String = userName,
        @Path("date") date: String,
        @Query("hash") hash: String = userHash
    ): Call<MealPlanDay>

    /**
     * Clear Meal Plan Day
     * Delete all planned items from the user's meal plan for a specific day.
     * @param username The username.
     * @param date The start date of the meal planned week in the format yyyy-mm-dd.
     * @param hash The private hash for the username.
     * @return ClearMealPlanDay
     */
    @DELETE(MealPlanner.UserName.CLEAR_MEAL_PLAN_DAY)
    fun clearMealPlanDay(
        @Path("username") username: String = userName,
        @Path("date") date: String,
        @Query("hash") hash: String = userHash
    ): Call<ClearMealPlanDay>

    /**
     * Get Meal Plan Week
     * Retrieve a meal planned week for the given user. The username must be a spoonacular user and
     * the hash must the the user's hash that can be found in his/her account.
     * @param username The username.
     * @param startDate The start date of the meal planned week in the format yyyy-mm-dd.
     * @param hash The private hash for the username.
     * @return MealPlanWeek
     */
    @GET(MealPlanner.UserName.GET_MEAL_PLAN_WEEK)
    fun getMealPlanWeek(
        @Path("username") username: String = userName,
        @Path("start-date") startDate: String,
        @Query("hash") hash: String = userHash
    ): Call<MealPlanWeek>

    /**
     * Generate Meal Plan
     * Generate a meal plan with three meals per day (breakfast, lunch, and dinner).
     * @param timeFrame Either for one "day" or an entire "week". (optional)
     * @param targetCalories What is the caloric target for one day? The meal plan generator will
     * try to get as close as possible to that goal. (optional)
     * @param diet Enter a diet that the meal plan has to adhere to. See a full list of supported
     * diets. (optional)
     * @see Diets
     * @param exclude A comma-separated list of allergens or ingredients that must be excluded. (optional)
     * @return Any
     */
    @GET(MealPlanner.GENERATE_MEAL_PLAN)
    fun generateMealPlan(
        @Query("timeFrame") timeFrame: String?,
        @Query("targetCalories") targetCalories: Int?,
        @Query("diet") diet: String?,
        @Query("exclude") exclude: String?
    ): Call<GenerateMealPlan>

    /**
     * Get public templates.
     * */
    @GET(MealPlanner.GET_PUBLIC_MEAL_PLAN_TEMPLATES)
    fun getPublicMealTemplate(): Call<MealPlanTemplates>
}
