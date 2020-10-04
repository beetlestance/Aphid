package com.beetlestance.spoonacular_kotlin.services

import com.beetlestance.spoonacular_kotlin.models.request.mealplanner.RequestAddToShoppingList
import com.beetlestance.spoonacular_kotlin.models.request.mealplanner.addtomealplan.RequestAddIngredientsToMealPlan
import com.beetlestance.spoonacular_kotlin.models.request.mealplanner.addtomealplan.RequestAddMealPlanTemplateToMealPlan
import com.beetlestance.spoonacular_kotlin.models.request.mealplanner.addtomealplan.RequestAddToMealPlan
import com.beetlestance.spoonacular_kotlin.services.endpoints.MealPlanner
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigDecimal

interface MealPlannerService {

    /**
     * Add to Meal Plan
     * Add an item to the user's meal plan.
     * @param username The username.
     * @param hash The private hash for the username.
     * @param requestAddToMealPlan
     * @see RequestAddMealPlanTemplateToMealPlan
     * @see RequestAddIngredientsToMealPlan
     * @return Any
     */
    @POST(MealPlanner.UserName.Items.ADD_TO_MEAL_PLAN)
    fun addToMealPlan(
        @Path("username") username: String,
        @Query("hash") hash: String,
        @Body requestAddToMealPlan: RequestAddToMealPlan
    ): Call<Any>

    /**
     * Delete from Meal Plan
     * Delete an item from the user's meal plan.
     * @param username The username.
     * @param id The shopping list item id.
     * @param hash The private hash for the username.
     * @return Any
     */
    @DELETE(MealPlanner.UserName.Items.DELETE_FROM_MEAL_PLAN)
    fun deleteFromMealPlan(
        @Path("username") username: String,
        @Path("id") id: BigDecimal,
        @Query("hash") hash: String,
    ): Call<Any>

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
        @Path("username") username: String,
        @Path("id") id: BigDecimal,
        @Query("hash") hash: String
    ): Call<Any>

    /**
     * Get Meal Plan Templates
     * Get meal plan templates from user or public ones.
     * @param username The username.
     * @param hash The private hash for the username.
     * @return Any
     */
    @GET(MealPlanner.UserName.Templates.GET_MEAL_PLAN_TEMPLATES)
    fun getMealPlanTemplates(
        @Path("username") username: String,
        @Query("hash") hash: String
    ): Call<Any>

    /**
     * Add to Shopping List
     * Add an item to the current shopping list of a user.
     * @param username The username.
     * @param hash The private hash for the username.
     * @param requestAddToShoppingList contains aisle and name of item
     * @return Any
     */
    @POST(MealPlanner.UserName.ShoppingLists.ADD_TO_SHOPPING_LIST)
    fun addToShoppingList(
        @Path("username") username: String,
        @Query("hash") hash: String,
        @Body requestAddToShoppingList: RequestAddToShoppingList
    ): Call<Any>

    /**
     * Delete from Shopping List
     * Delete an item from the current shopping list of the user.
     * @param username The username.
     * @param id The shopping list item id.
     * @param hash The private hash for the username.
     * @return Any
     */
    @DELETE(MealPlanner.UserName.ShoppingLists.DELETE_FROM_SHOPPING_LIST)
    fun deleteFromShoppingList(
        @Path("username") username: String,
        @Path("id") id: BigDecimal,
        @Query("hash") hash: String
    ): Call<Any>

    /**
     * Generate Shopping List
     * Generate the shopping list for a user from the meal planner in a given time frame.
     * @param username The username.
     * @param startDate The start date in the format yyyy-mm-dd.
     * @param endDate The end date in the format yyyy-mm-dd.
     * @param hash The private hash for the username.
     * @return Any
     */
    @POST(MealPlanner.UserName.ShoppingLists.GENERATE_SHOPPING_LIST)
    fun generateShoppingList(
        @Path("username") username: String,
        @Path("start-date") startDate: String,
        @Path("end-date") endDate: String,
        @Query("hash") hash: String
    ): Call<Any>

    /**
     * Get Shopping List
     * Get the current shopping list for the given user.
     * @param username The username.
     * @param hash The private hash for the username.
     * @return Any
     */
    @GET(MealPlanner.UserName.ShoppingLists.GET_SHOPPING_LIST)
    fun getShoppingList(
        @Path("username") username: String,
        @Query("hash") hash: String
    ): Call<Any>

    /**
     * Get Meal Plan Week
     * Retrieve a meal planned week for the given user. The username must be a spoonacular user and
     * the hash must the the user's hash that can be found in his/her account.
     * @param username The username.
     * @param startDate The start date of the meal planned week in the format yyyy-mm-dd.
     * @param hash The private hash for the username.
     * @return Any
     */
    @GET(MealPlanner.UserName.GET_MEAL_PLAN_WEEK)
    fun getMealPlanWeek(
        @Path("username") username: String,
        @Path("start-date") startDate: String,
        @Query("hash") hash: String
    ): Call<Any>

    /**
     * Generate Meal Plan
     * Generate a meal plan with three meals per day (breakfast, lunch, and dinner).
     * @param timeFrame Either for one "day" or an entire "week". (optional)
     * @param targetCalories What is the caloric target for one day? The meal plan generator will
     * try to get as close as possible to that goal. (optional)
     * @param diet Enter a diet that the meal plan has to adhere to. See a full list of supported
     * diets. (optional)
     * @param exclude A comma-separated list of allergens or ingredients that must be excluded. (optional)
     * @return Any
     */
    @GET(MealPlanner.GENERATE_MEAL_PLAN)
    fun generateMealPlan(
        @Query("timeFrame") timeFrame: String?,
        @Query("targetCalories") targetCalories: BigDecimal?,
        @Query("diet") diet: String?,
        @Query("exclude") exclude: String?
    ): Call<Any>
}
