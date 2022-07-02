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

import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestAnalyzeRecipeInstructions
import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestClassifyCuisine
import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestCreateRecipeCard
import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestParseIngredients
import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestVisualizeEquipment
import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestVisualizeIngredients
import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestVisualizePriceBreakdown
import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestVisualizeRecipeNutrition
import com.beetlestance.aphid.spoonacular.kotlin.models.request.recipe.RequestVisualizeRecipeTaste
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.AnalyzeARecipeSearchQuery
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.AnalyzeRecipeInstructions
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.AnalyzedRecipeInstructions
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.AutoCompleteRecipeSearch
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.ClassifyCuisine
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.ConvertAmount
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.CreateRecipeCard
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.GuessNutritionByDishName
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.ParseIngredients
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.QuickAnswer
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.RandomRecipes
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.RecipeEquipment
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.RecipeInformation
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.RecipeIngredients
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.RecipeNutrients
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.RecipePriceBreakdown
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.RecipeSearch
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.RecipeTaste
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.SearchRecipeComplex
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.SearchRecipesByIngredients
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.SearchRecipesByNutrients
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.SimilarRecipes
import com.beetlestance.aphid.spoonacular.kotlin.models.response.recipe.SummarizeRecipe
import com.beetlestance.aphid.spoonacular.kotlin.services.endpoints.Recipes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesService {

    /**
     * Get Analyzed Recipe Instructions
     * Get an analyzed breakdown of a recipe's instructions. Each step is enriched with the
     * ingredients and equipment required.
     * @param id The recipe id.
     * @param stepBreakdown Whether to break down the recipe steps even more. (optional)
     * @return List<AnalyzedRecipeInstructions>
     */
    @GET(Recipes.ById.GET_ANALYZED_RECIPE_INSTRUCTIONS)
    fun getAnalyzedRecipeInstructions(
        @Path("id") id: Long,
        @Query("stepBreakdown") stepBreakdown: Boolean? = null
    ): Call<List<AnalyzedRecipeInstructions>>

    /**
     * Get Recipe Equipment by ID
     * Get a recipe's equipment list.
     * @param id The recipe id.
     * @return RecipeEquipment
     */
    @GET(Recipes.ById.GET_RECIPE_EQUIPMENT)
    fun getRecipeEquipmentByID(@Path("id") id: Long): Call<RecipeEquipment>

    /**
     * Get Recipe Information
     * Use a recipe id to get full information about a recipe, such as ingredients, nutrition, diet
     * and allergen information, etc.
     * @param id The id of the recipe.
     * @param includeNutrition Include nutrition data in the recipe information. Nutrition data is
     * per serving. If you want the nutrition data for the entire recipe, just multiply by the
     * number of servings. (optional)
     * @return RecipeInformation
     */
    @GET(Recipes.ById.GET_RECIPE_INFORMATION)
    fun getRecipeInformation(
        @Path("id") id: Long,
        @Query("includeNutrition") includeNutrition: Boolean? = null
    ): Call<RecipeInformation>

    /**
     * Get Recipe Ingredients by ID
     * Get a recipe's ingredient list.
     * @param id The recipe id.
     * @return RecipeIngredients
     */
    @GET(Recipes.ById.GET_RECIPE_INGREDIENTS)
    fun getRecipeIngredientsByID(@Path("id") id: Long): Call<RecipeIngredients>

    /**
     * Get Recipe Nutrition Widget by ID
     * Get a recipe's nutrition widget data.
     * @param id The recipe id.
     * @return RecipeNutrients
     */
    @GET(Recipes.ById.GET_RECIPE_NUTRITION)
    fun getRecipeNutritionWidgetByID(@Path("id") id: Long): Call<RecipeNutrients>

    /**
     * Get a recipe's taste.
     * The tastes supported are sweet, salty, sour, bitter, savory, and fatty.
     * @param id The recipe id.
     * @return RecipeTaste
     */
    @GET(Recipes.ById.GET_RECIPE_TASTE)
    fun getRecipeTasteWidgetByID(@Path("id") id: Long): Call<RecipeTaste>

    /**
     * Get Recipe Price Breakdown by ID
     * Get a recipe's price breakdown data.
     * @param id The recipe id.
     * @return RecipePriceBreakdown
     */
    @GET(Recipes.ById.GET_RECIPE_PRICE_BREAKDOWN)
    fun getRecipePriceBreakdownByID(@Path("id") id: Long): Call<RecipePriceBreakdown>

    /**
     * Get Similar Recipes
     * Find recipes which are similar to the given one.
     * @param id The id of the source recipe for which similar recipes should be found.
     * @param number The number of random recipes to be returned (between 1 and 100). (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with
     * proper attribution. (optional)
     * @return List<SimilarRecipes>
     */
    @GET(Recipes.ById.GET_SIMILAR_RECIPES)
    fun getSimilarRecipes(
        @Path("id") id: Long,
        @Query("number") number: Int? = null,
        @Query("limitLicense") limitLicense: Boolean? = null
    ): Call<List<SimilarRecipes>>

    /**
     * Summarize Recipe
     * Automatically generate a short description that summarizes key information about the recipe.
     * @param id The recipe id.
     * @return SummarizeRecipe
     */
    @GET(Recipes.ById.SUMMARIZE_RECIPE)
    fun summarizeRecipe(@Path("id") id: Long): Call<SummarizeRecipe>

    /**
     * Visualize Recipe Equipment by ID
     * Visualize a recipe's equipment list.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_EQUIPMENT)
    fun visualizeRecipeEquipmentByID(
        @Path("id") id: Long,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): Call<String>

    /**
     * Visualize Recipe Ingredients by ID
     * Visualize a recipe's ingredient list.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_INGREDIENTS)
    fun visualizeRecipeIngredientsByID(
        @Path("id") id: Long,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): Call<String>

    /**
     * Visualize Recipe Nutrition by ID
     * Visualize a recipe's nutritional information as HTML including CSS.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_NUTRITION)
    fun visualizeRecipeNutritionByID(
        @Path("id") id: Long,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): Call<String>

    /**
     * Visualize Recipe Price Breakdown by ID
     * Visualize a recipe's price breakdown.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_PRICE_BREAKDOWN)
    fun visualizeRecipePriceBreakdownByID(
        @Path("id") id: Long,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): Call<String>

    /**
     * Visualize Recipe Taste by ID
     * Visualize a recipe's price breakdown.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_TASTE)
    fun visualizeRecipeTasteByID(
        @Path("id") id: Long,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): Call<String>

    /**
     * Analyze a Recipe Search Query
     * Parse a recipe search query to find out its intention.
     * @param q The recipe search query.
     * @return AnalyzeARecipeSearchQuery
     */
    @GET(Recipes.ANALYZE_A_RECIPE_SEARCH_QUERY)
    fun analyzeARecipeSearchQuery(@Query("q") q: String): Call<AnalyzeARecipeSearchQuery>

    /**
     * Analyze Recipe Instructions
     * Extract ingredients and equipment from the recipe's instructions.
     * @see RequestAnalyzeRecipeInstructions
     * @return AnalyzeRecipeInstructions
     */
    @POST(Recipes.ANALYZE_RECIPE_INSTRUCTIONS)
    fun analyzeRecipeInstructions(
        @Body requestAnalyzeRecipeInstructions: RequestAnalyzeRecipeInstructions
    ): Call<AnalyzeRecipeInstructions>

    /**
     * Autocomplete Recipe Search
     * Autocomplete a partial input to suggest possible recipe names.
     * @param query The query to be autocompleted.
     * @param number The number of results to return (between 1 and 25). (optional)
     * @return List<AutoCompleteRecipeSearch>
     */
    @GET(Recipes.AUTOCOMPLETE_RECIPE_SEARCH)
    fun autocompleteRecipeSearch(
        @Query("query") query: String,
        @Query("number") number: Long? = null
    ): Call<List<AutoCompleteRecipeSearch>>

    /**
     * Classify Cuisine
     * Classify the recipe's cuisine.
     * @see RequestClassifyCuisine
     * @return ClassifyCuisine
     */
    @POST(Recipes.CLASSIFY_CUISINE)
    fun classifyCuisine(
        @Body requestClassifyCuisine: RequestClassifyCuisine
    ): Call<ClassifyCuisine>

    /**
     * Convert Amounts
     * Convert amounts like "2 cups of flour to grams".
     * @param ingredientName The ingredient which you want to convert.
     * @param sourceAmount The amount from which you want to convert, e.g. the 2.5 in "2.5
     * cups of flour to grams".
     * @param sourceUnit The unit from which you want to convert, e.g. the grams in "2.5 cups
     * of flour to grams". You can also use "piece", e.g. "3.4 oz tomatoes
     * to piece"
     * @param targetUnit The unit to which you want to convert, e.g. the grams in "2.5 cups of
     * flour to grams". You can also use "piece", e.g. "3.4 oz tomatoes to
     * piece"
     * @return ConvertAmount
     */
    @POST(Recipes.CONVERT_AMOUNTS)
    fun convertAmounts(
        @Query("ingredientName") ingredientName: String,
        @Query("sourceAmount") sourceAmount: Double,
        @Query("sourceUnit") sourceUnit: String,
        @Query("targetUnit") targetUnit: String
    ): Call<ConvertAmount>

    /**
     * Create Recipe Card
     * Generate a recipe card for a recipe.
     * @see RequestCreateRecipeCard
     * @return CreateRecipeCard
     */
    @POST(Recipes.CREATE_RECIPE_CARD)
    fun createRecipeCard(
        @Body requestCreateRecipeCard: RequestCreateRecipeCard
    ): Call<CreateRecipeCard>

    /**
     * Extract Recipe from Website
     * This endpoint lets you extract recipe data such as title, ingredients, and instructions from
     * any properly formatted Website.
     * @param url The URL of the recipe page.
     * @param forceExtraction If true, the extraction will be triggered whether we already know the
     * recipe or not. Use this only if information is missing as this operation is slower.(optional)
     * @param analyze If true, the recipe will be analyzed and classified resolving in more data
     * such as cuisines, dish types, and more. (optional)
     * @return RecipeInformation
     */
    @GET(Recipes.EXTRACT_RECIPE_FROM_WEBSITE)
    fun extractRecipeFromWebsite(
        @Query("url") url: String,
        @Query("forceExtraction") forceExtraction: Boolean? = null,
        @Query("analyze") analyze: Boolean? = null
    ): Call<RecipeInformation>

    /**
     * Get Random Recipes
     * Find random (popular) recipes. If you need to filter recipes by diet, nutrition etc.
     * you might want to consider using the complex recipe search endpoint and set the sort request
     * parameter to random.
     * @param limitLicense Whether the recipes should have an open license that allows display with
     * proper attribution. (optional)
     * @param tags The tags (can be diets, meal types, cuisines, or intolerances) that the recipe
     * must have. (optional)
     * @param number The number of random recipes to be returned (between 1 and 100). (optional)
     * @return List<RecipeInformation>
     */
    @GET(Recipes.GET_RANDOM_RECIPES)
    fun getRandomRecipes(
        @Query("limitLicense") limitLicense: Boolean? = null,
        @Query("tags") tags: String? = null,
        @Query("number") number: Int? = null
    ): Call<RandomRecipes>

    /**
     * Get Recipe Information Bulk
     * Get information about multiple recipes at once. This is equivalent to calling the Get Recipe
     * Information endpoint multiple times, but faster.
     * @param ids A comma-separated list of recipe ids.
     * @param includeNutrition Include nutrition data to the recipe information. Nutrition data is
     * per serving. If you want the nutrition data for the entire recipe, just multiply by the
     * number of servings. (optional)
     * @return List<RecipeInformation>
     */
    @GET(Recipes.GET_RECIPE_INFORMATION_BULK)
    fun getRecipeInformationBulk(
        @Query("ids") ids: String,
        @Query("includeNutrition") includeNutrition: Boolean? = null
    ): Call<List<RecipeInformation>>

    /**
     * Guess Nutrition by Dish Name
     * Estimate the macronutrients of a dish based on its title.
     * @param title The title of the dish.
     * @return GuessNutritionByDishName
     */
    @GET(Recipes.GUESS_NUTRITION_BY_DISH_NAME)
    fun guessNutritionByDishName(
        @Query("title") title: String
    ): Call<GuessNutritionByDishName>

    /**
     * Parse Ingredients
     * Extract an ingredient from plain text.
     * @see RequestParseIngredients
     * @return List<ParseIngredients>
     */
    @POST(Recipes.PARSE_INGREDIENTS)
    fun parseIngredients(
        @Body requestParseIngredients: RequestParseIngredients
    ): Call<List<ParseIngredients>>

    /**
     * Quick Answer
     * Answer a nutrition related natural language question.
     * @param q The nutrition related question.
     * @return QuickAnswer
     */
    @GET(Recipes.QUICK_ANSWER)
    fun quickAnswer(
        @Query("q") q: String
    ): Call<QuickAnswer>

    /**
     * Search Recipes
     * Our recipe API includes over 360,000 recipes as well as an open source recipe database.
     * Consider using the "Search Recipes Complex" endpoint for much more flexibility.
     * @param query The (natural language) recipe search query.
     * @param cuisine The cuisine(s) of the recipes. One or more comma separated. See a full list
     * of supported cuisines. (optional)
     * @param diet The diet for which the recipes must be suitable. See a full list of supported
     * diets. (optional)
     * @param excludeIngredients A comma-separated list of ingredients or ingredient types that the
     * recipes must not contain. (optional)
     * @param intolerances A comma-separated list of intolerances. All recipes returned must not
     * contain ingredients that are not suitable for people with the intolerances entered.
     * See a full list of supported intolerances. Please note: due to the automatic nature of the
     * recipe analysis, the API cannot be 100% accurate in all cases. Please advise your users to
     * seek professional help with medical issues. (optional)
     * @param offset The number of results to skip (between 0 and 900). (optional)
     * @param number The number of results to return (between 1 and 100). (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with
     * proper attribution. (optional)
     * @param instructionsRequired Whether the recipes must have instructions. (optional)
     * @return RecipeSearch
     */
    @GET(Recipes.SEARCH_RECIPES)
    fun searchRecipes(
        @Query("query") query: String,
        @Query("cuisine") cuisine: String? = null,
        @Query("diet") diet: String? = null,
        @Query("excludeIngredients") excludeIngredients: String? = null,
        @Query("intolerances") intolerances: String? = null,
        @Query("offset") offset: Int? = null,
        @Query("number") number: Int? = null,
        @Query("limitLicense") limitLicense: Boolean? = null,
        @Query("instructionsRequired") instructionsRequired: Boolean? = null
    ): Call<RecipeSearch>

    /**
     * Search Recipes by Ingredients
     * Ever wondered what recipes you can cook with the ingredients you have in your fridge or
     * pantry?  = null This endpoint lets you find recipes that either maximize the usage of
     * ingredients you have at hand (pre shopping) or minimize the ingredients that you don't
     * currently have (post shopping).
     * @param ingredients A comma-separated list of ingredients that the recipes should contain.
     * @param number The maximum number of recipes to return (between 1 and 100).
     * Defaults to 10. (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with
     * proper attribution. (optional)
     * @param ranking Whether to maximize used ingredients (1) or minimize missing ingredients (2)
     * first. (optional)
     * @param ignorePantry Whether to ignore typical pantry items, such as water, salt, flour, etc.
     * (optional)
     * @return List<SearchRecipesByIngredients>
     */
    @GET(Recipes.SEARCH_RECIPES_BY_INGREDIENTS)
    fun searchRecipesByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int? = null,
        @Query("limitLicense") limitLicense: Boolean? = null,
        @Query("ranking") ranking: Int? = null,
        @Query("ignorePantry") ignorePantry: Boolean? = null
    ): Call<List<SearchRecipesByIngredients>>

    /**
     * Search Recipes by Nutrients
     * Find a set of recipes that adhere to the given nutritional limits. You may set limits for
     * macronutrients (calories, protein, fat, and carbohydrate) and/or many micronutrients.
     * @param minCarbs The minimum amount of carbohydrates in grams the recipe must have. (optional)
     * @param maxCarbs The maximum amount of carbohydrates in grams the recipe can have. (optional)
     * @param minProtein The minimum amount of protein in grams the recipe must have. (optional)
     * @param maxProtein The maximum amount of protein in grams the recipe can have. (optional)
     * @param minCalories The minimum amount of calories the recipe must have. (optional)
     * @param maxCalories The maximum amount of calories the recipe can have. (optional)
     * @param minFat The minimum amount of fat in grams the recipe must have. (optional)
     * @param maxFat The maximum amount of fat in grams the recipe can have. (optional)
     * @param minAlcohol The minimum amount of alcohol in grams the recipe must have. (optional)
     * @param maxAlcohol The maximum amount of alcohol in grams the recipe can have. (optional)
     * @param minCaffeine The minimum amount of caffeine in milligrams the recipe must have. (optional)
     * @param maxCaffeine The maximum amount of caffeine in milligrams the recipe can have. (optional)
     * @param minCopper The minimum amount of copper in milligrams the recipe must have. (optional)
     * @param maxCopper The maximum amount of copper in milligrams the recipe can have. (optional)
     * @param minCalcium The minimum amount of calcium in milligrams the recipe must have. (optional)
     * @param maxCalcium The maximum amount of calcium in milligrams the recipe can have. (optional)
     * @param minCholine The minimum amount of choline in milligrams the recipe must have. (optional)
     * @param maxCholine The maximum amount of choline in milligrams the recipe can have. (optional)
     * @param minCholesterol The minimum amount of cholesterol in milligrams the recipe must have. (optional)
     * @param maxCholesterol The maximum amount of cholesterol in milligrams the recipe can have. (optional)
     * @param minFluoride The minimum amount of fluoride in milligrams the recipe must have. (optional)
     * @param maxFluoride The maximum amount of fluoride in milligrams the recipe can have. (optional)
     * @param minSaturatedFat The minimum amount of saturated fat in grams the recipe must have. (optional)
     * @param maxSaturatedFat The maximum amount of saturated fat in grams the recipe can have. (optional)
     * @param minVitaminA The minimum amount of Vitamin A in IU the recipe must have. (optional)
     * @param maxVitaminA The maximum amount of Vitamin A in IU the recipe can have. (optional)
     * @param minVitaminC The minimum amount of Vitamin C in milligrams the recipe must have. (optional)
     * @param maxVitaminC The maximum amount of Vitamin C in milligrams the recipe can have. (optional)
     * @param minVitaminD The minimum amount of Vitamin D in micrograms the recipe must have. (optional)
     * @param maxVitaminD The maximum amount of Vitamin D in micrograms the recipe can have. (optional)
     * @param minVitaminE The minimum amount of Vitamin E in milligrams the recipe must have. (optional)
     * @param maxVitaminE The maximum amount of Vitamin E in milligrams the recipe can have. (optional)
     * @param minVitaminK The minimum amount of Vitamin K in micrograms the recipe must have. (optional)
     * @param maxVitaminK The maximum amount of Vitamin K in micrograms the recipe can have. (optional)
     * @param minVitaminB1 The minimum amount of Vitamin B1 in milligrams the recipe must have. (optional)
     * @param maxVitaminB1 The maximum amount of Vitamin B1 in milligrams the recipe can have. (optional)
     * @param minVitaminB2 The minimum amount of Vitamin B2 in milligrams the recipe must have. (optional)
     * @param maxVitaminB2 The maximum amount of Vitamin B2 in milligrams the recipe can have. (optional)
     * @param minVitaminB5 The minimum amount of Vitamin B5 in milligrams the recipe must have. (optional)
     * @param maxVitaminB5 The maximum amount of Vitamin B5 in milligrams the recipe can have. (optional)
     * @param minVitaminB3 The minimum amount of Vitamin B3 in milligrams the recipe must have. (optional)
     * @param maxVitaminB3 The maximum amount of Vitamin B3 in milligrams the recipe can have. (optional)
     * @param minVitaminB6 The minimum amount of Vitamin B6 in milligrams the recipe must have. (optional)
     * @param maxVitaminB6 The maximum amount of Vitamin B6 in milligrams the recipe can have. (optional)
     * @param minVitaminB12 The minimum amount of Vitamin B12 in micrograms the recipe must have. (optional)
     * @param maxVitaminB12 The maximum amount of Vitamin B12 in micrograms the recipe can have. (optional)
     * @param minFiber The minimum amount of fiber in grams the recipe must have. (optional)
     * @param maxFiber The maximum amount of fiber in grams the recipe can have. (optional)
     * @param minFolate The minimum amount of folate in grams the recipe must have. (optional)
     * @param maxFolate The maximum amount of folate in grams the recipe can have. (optional)
     * @param minFolicAcid The minimum amount of folic acid in grams the recipe must have. (optional)
     * @param maxFolicAcid The maximum amount of folic acid in grams the recipe can have. (optional)
     * @param minIodine The minimum amount of iodine in grams the recipe must have. (optional)
     * @param maxIodine The maximum amount of iodine in grams the recipe can have. (optional)
     * @param minIron The minimum amount of iron in milligrams the recipe must have. (optional)
     * @param maxIron The maximum amount of iron in milligrams the recipe can have. (optional)
     * @param minMagnesium The minimum amount of magnesium in milligrams the recipe must have. (optional)
     * @param maxMagnesium The maximum amount of magnesium in milligrams the recipe can have. (optional)
     * @param minManganese The minimum amount of manganese in milligrams the recipe must have. (optional)
     * @param maxManganese The maximum amount of manganese in milligrams the recipe can have. (optional)
     * @param minPhosphorus The minimum amount of phosphorus in milligrams the recipe must have. (optional)
     * @param maxPhosphorus The maximum amount of phosphorus in milligrams the recipe can have. (optional)
     * @param minPotassium The minimum amount of potassium in milligrams the recipe must have. (optional)
     * @param maxPotassium The maximum amount of potassium in milligrams the recipe can have. (optional)
     * @param minSelenium The minimum amount of selenium in grams the recipe must have. (optional)
     * @param maxSelenium The maximum amount of selenium in grams the recipe can have. (optional)
     * @param minSodium The minimum amount of sodium in milligrams the recipe must have. (optional)
     * @param maxSodium The maximum amount of sodium in milligrams the recipe can have. (optional)
     * @param minSugar The minimum amount of sugar in grams the recipe must have. (optional)
     * @param maxSugar The maximum amount of sugar in grams the recipe can have. (optional)
     * @param minZinc The minimum amount of zinc in milligrams the recipe must have. (optional)
     * @param maxZinc The maximum amount of zinc in milligrams the recipe can have. (optional)
     * @param offset The number of results to skip (between 0 and 900). (optional)
     * @param number The number of expected results (between 1 and 100). (optional)
     * @param random If true, every request will give you a random set of recipes within the
     * requested limits. (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with
     * proper attribution. (optional)
     * @return List<SearchRecipesByNutrients>
     */
    @GET(Recipes.SEARCH_RECIPES_BY_NUTRIENTS)
    fun searchRecipesByNutrients(
        @Query("minCarbs") minCarbs: Int? = null,
        @Query("maxCarbs") maxCarbs: Int? = null,
        @Query("minProtein") minProtein: Int? = null,
        @Query("maxProtein") maxProtein: Int? = null,
        @Query("minCalories") minCalories: Int? = null,
        @Query("maxCalories") maxCalories: Int? = null,
        @Query("minFat") minFat: Int? = null,
        @Query("maxFat") maxFat: Int? = null,
        @Query("minAlcohol") minAlcohol: Int? = null,
        @Query("maxAlcohol") maxAlcohol: Int? = null,
        @Query("minCaffeine") minCaffeine: Int? = null,
        @Query("maxCaffeine") maxCaffeine: Int? = null,
        @Query("minCopper") minCopper: Int? = null,
        @Query("maxCopper") maxCopper: Int? = null,
        @Query("minCalcium") minCalcium: Int? = null,
        @Query("maxCalcium") maxCalcium: Int? = null,
        @Query("minCholine") minCholine: Int? = null,
        @Query("maxCholine") maxCholine: Int? = null,
        @Query("minCholesterol") minCholesterol: Int? = null,
        @Query("maxCholesterol") maxCholesterol: Int? = null,
        @Query("minFluoride") minFluoride: Int? = null,
        @Query("maxFluoride") maxFluoride: Int? = null,
        @Query("minSaturatedFat") minSaturatedFat: Int? = null,
        @Query("maxSaturatedFat") maxSaturatedFat: Int? = null,
        @Query("minVitaminA") minVitaminA: Int? = null,
        @Query("maxVitaminA") maxVitaminA: Int? = null,
        @Query("minVitaminC") minVitaminC: Int? = null,
        @Query("maxVitaminC") maxVitaminC: Int? = null,
        @Query("minVitaminD") minVitaminD: Int? = null,
        @Query("maxVitaminD") maxVitaminD: Int? = null,
        @Query("minVitaminE") minVitaminE: Int? = null,
        @Query("maxVitaminE") maxVitaminE: Int? = null,
        @Query("minVitaminK") minVitaminK: Int? = null,
        @Query("maxVitaminK") maxVitaminK: Int? = null,
        @Query("minVitaminB1") minVitaminB1: Int? = null,
        @Query("maxVitaminB1") maxVitaminB1: Int? = null,
        @Query("minVitaminB2") minVitaminB2: Int? = null,
        @Query("maxVitaminB2") maxVitaminB2: Int? = null,
        @Query("minVitaminB5") minVitaminB5: Int? = null,
        @Query("maxVitaminB5") maxVitaminB5: Int? = null,
        @Query("minVitaminB3") minVitaminB3: Int? = null,
        @Query("maxVitaminB3") maxVitaminB3: Int? = null,
        @Query("minVitaminB6") minVitaminB6: Int? = null,
        @Query("maxVitaminB6") maxVitaminB6: Int? = null,
        @Query("minVitaminB12") minVitaminB12: Int? = null,
        @Query("maxVitaminB12") maxVitaminB12: Int? = null,
        @Query("minFiber") minFiber: Int? = null,
        @Query("maxFiber") maxFiber: Int? = null,
        @Query("minFolate") minFolate: Int? = null,
        @Query("maxFolate") maxFolate: Int? = null,
        @Query("minFolicAcid") minFolicAcid: Int? = null,
        @Query("maxFolicAcid") maxFolicAcid: Int? = null,
        @Query("minIodine") minIodine: Int? = null,
        @Query("maxIodine") maxIodine: Int? = null,
        @Query("minIron") minIron: Int? = null,
        @Query("maxIron") maxIron: Int? = null,
        @Query("minMagnesium") minMagnesium: Int? = null,
        @Query("maxMagnesium") maxMagnesium: Int? = null,
        @Query("minManganese") minManganese: Int? = null,
        @Query("maxManganese") maxManganese: Int? = null,
        @Query("minPhosphorus") minPhosphorus: Int? = null,
        @Query("maxPhosphorus") maxPhosphorus: Int? = null,
        @Query("minPotassium") minPotassium: Int? = null,
        @Query("maxPotassium") maxPotassium: Int? = null,
        @Query("minSelenium") minSelenium: Int? = null,
        @Query("maxSelenium") maxSelenium: Int? = null,
        @Query("minSodium") minSodium: Int? = null,
        @Query("maxSodium") maxSodium: Int? = null,
        @Query("minSugar") minSugar: Int? = null,
        @Query("maxSugar") maxSugar: Int? = null,
        @Query("minZinc") minZinc: Int? = null,
        @Query("maxZinc") maxZinc: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("number") number: Int? = null,
        @Query("random") random: Boolean? = null,
        @Query("limitLicense") limitLicense: Boolean? = null
    ): Call<List<SearchRecipesByNutrients>>

    /**
     * Search Recipes Complex
     * Search through hundreds of thousands of recipes using advanced filtering and ranking. NOTE:
     * This method combines searching by query, by ingredients, and by nutrients into one endpoint.
     * @param query The (natural language) recipe search query.
     * @param cuisine The cuisine(s) of the recipes. One or more, comma separated (will be
     * interpreted as 'OR'). See a full list of supported cuisines. (optional)
     * @param excludeCuisine The cuisine(s) the recipes must not match. One or more, comma separated
     * (will be interpreted as 'AND'). See a full list of supported cuisines. (optional)
     * @param diet The diet for which the recipes must be suitable. See a full list of supported
     * diets. (optional)
     * @param intolerances A comma-separated list of intolerances. All recipes returned must not
     * contain ingredients that are not suitable for people with the intolerances entered.
     * See a full list of supported intolerances. (optional)
     * @param equipment The equipment required. Multiple values will be interpreted as 'or'.
     * For example, value could be "blender, frying pan, bowl". (optional)
     * @param includeIngredients A comma-separated list of ingredients that should/must be used in
     * the recipes. (optional)
     * @param excludeIngredients A comma-separated list of ingredients or ingredient types that the
     * recipes must not contain. (optional)
     * @param type The type of recipe. See a full list of supported meal types. (optional)
     * @param instructionsRequired Whether the recipes must have instructions. (optional)
     * @param fillIngredients Add information about the ingredients and whether they are used or
     * missing in relation to the query. (optional)
     * @param addRecipeInformation If set to true, you get more information about the recipes
     * returned. (optional)
     * @param addRecipeNutrition If set to true, you get nutritional information about each recipes
     * returned. (optional)
     * @param author The username of the recipe author. (optional)
     * @param tags User defined tags that have to match. The author param has to be set. (optional)
     * @param recipeBoxId The id of the recipe box to which the search should be limited to. (optional)
     * @param titleMatch Enter text that must be found in the title of the recipes. (optional)
     * @param maxReadyTime The maximum time in minutes it should take to prepare and cook the
     * recipe. (optional)
     * @param ignorePantry Whether to ignore typical pantry items, such as water, salt, flour, etc.
     * (optional)
     * @param sort The strategy to sort recipes by. See a full list of supported sorting options.
     * (optional)
     * @param sortDirection The direction in which to sort. Must be either 'asc' (ascending) or
     * 'desc' (descending). (optional)
     * @param minCarbs The minimum amount of carbohydrates in grams the recipe must have. (optional)
     * @param maxCarbs The maximum amount of carbohydrates in grams the recipe can have. (optional)
     * @param minProtein The minimum amount of protein in grams the recipe must have. (optional)
     * @param maxProtein The maximum amount of protein in grams the recipe can have. (optional)
     * @param minCalories The minimum amount of calories the recipe must have. (optional)
     * @param maxCalories The maximum amount of calories the recipe can have. (optional)
     * @param minFat The minimum amount of fat in grams the recipe must have. (optional)
     * @param maxFat The maximum amount of fat in grams the recipe can have. (optional)
     * @param minAlcohol The minimum amount of alcohol in grams the recipe must have. (optional)
     * @param maxAlcohol The maximum amount of alcohol in grams the recipe can have. (optional)
     * @param minCaffeine The minimum amount of caffeine in milligrams the recipe must have. (optional)
     * @param maxCaffeine The maximum amount of caffeine in milligrams the recipe can have. (optional)
     * @param minCopper The minimum amount of copper in milligrams the recipe must have. (optional)
     * @param maxCopper The maximum amount of copper in milligrams the recipe can have. (optional)
     * @param minCalcium The minimum amount of calcium in milligrams the recipe must have. (optional)
     * @param maxCalcium The maximum amount of calcium in milligrams the recipe can have. (optional)
     * @param minCholine The minimum amount of choline in milligrams the recipe must have. (optional)
     * @param maxCholine The maximum amount of choline in milligrams the recipe can have. (optional)
     * @param minCholesterol The minimum amount of cholesterol in milligrams the recipe must have. (optional)
     * @param maxCholesterol The maximum amount of cholesterol in milligrams the recipe can have. (optional)
     * @param minFluoride The minimum amount of fluoride in milligrams the recipe must have. (optional)
     * @param maxFluoride The maximum amount of fluoride in milligrams the recipe can have. (optional)
     * @param minSaturatedFat The minimum amount of saturated fat in grams the recipe must have. (optional)
     * @param maxSaturatedFat The maximum amount of saturated fat in grams the recipe can have. (optional)
     * @param minVitaminA The minimum amount of Vitamin A in IU the recipe must have. (optional)
     * @param maxVitaminA The maximum amount of Vitamin A in IU the recipe can have. (optional)
     * @param minVitaminC The minimum amount of Vitamin C milligrams the recipe must have. (optional)
     * @param maxVitaminC The maximum amount of Vitamin C in milligrams the recipe can have. (optional)
     * @param minVitaminD The minimum amount of Vitamin D in micrograms the recipe must have. (optional)
     * @param maxVitaminD The maximum amount of Vitamin D in micrograms the recipe can have. (optional)
     * @param minVitaminE The minimum amount of Vitamin E in milligrams the recipe must have. (optional)
     * @param maxVitaminE The maximum amount of Vitamin E in milligrams the recipe can have. (optional)
     * @param minVitaminK The minimum amount of Vitamin K in micrograms the recipe must have. (optional)
     * @param maxVitaminK The maximum amount of Vitamin K in micrograms the recipe can have. (optional)
     * @param minVitaminB1 The minimum amount of Vitamin B1 in milligrams the recipe must have. (optional)
     * @param maxVitaminB1 The maximum amount of Vitamin B1 in milligrams the recipe can have. (optional)
     * @param minVitaminB2 The minimum amount of Vitamin B2 in milligrams the recipe must have. (optional)
     * @param maxVitaminB2 The maximum amount of Vitamin B2 in milligrams the recipe can have. (optional)
     * @param minVitaminB5 The minimum amount of Vitamin B5 in milligrams the recipe must have. (optional)
     * @param maxVitaminB5 The maximum amount of Vitamin B5 in milligrams the recipe can have. (optional)
     * @param minVitaminB3 The minimum amount of Vitamin B3 in milligrams the recipe must have. (optional)
     * @param maxVitaminB3 The maximum amount of Vitamin B3 in milligrams the recipe can have. (optional)
     * @param minVitaminB6 The minimum amount of Vitamin B6 in milligrams the recipe must have. (optional)
     * @param maxVitaminB6 The maximum amount of Vitamin B6 in milligrams the recipe can have. (optional)
     * @param minVitaminB12 The minimum amount of Vitamin B12 in micrograms the recipe must have. (optional)
     * @param maxVitaminB12 The maximum amount of Vitamin B12 in micrograms the recipe can have. (optional)
     * @param minFiber The minimum amount of fiber in grams the recipe must have. (optional)
     * @param maxFiber The maximum amount of fiber in grams the recipe can have. (optional)
     * @param minFolate The minimum amount of folate in grams the recipe must have. (optional)
     * @param maxFolate The maximum amount of folate in grams the recipe can have. (optional)
     * @param minFolicAcid The minimum amount of folic acid in grams the recipe must have. (optional)
     * @param maxFolicAcid The maximum amount of folic acid in grams the recipe can have. (optional)
     * @param minIodine The minimum amount of iodine in grams the recipe must have. (optional)
     * @param maxIodine The maximum amount of iodine in grams the recipe can have. (optional)
     * @param minIron The minimum amount of iron in milligrams the recipe must have. (optional)
     * @param maxIron The maximum amount of iron in milligrams the recipe can have. (optional)
     * @param minMagnesium The minimum amount of magnesium in milligrams the recipe must have. (optional)
     * @param maxMagnesium The maximum amount of magnesium in milligrams the recipe can have. (optional)
     * @param minManganese The minimum amount of manganese in milligrams the recipe must have. (optional)
     * @param maxManganese The maximum amount of manganese in milligrams the recipe can have. (optional)
     * @param minPhosphorus The minimum amount of phosphorus in milligrams the recipe must have. (optional)
     * @param maxPhosphorus The maximum amount of phosphorus in milligrams the recipe can have. (optional)
     * @param minPotassium The minimum amount of potassium in milligrams the recipe must have. (optional)
     * @param maxPotassium The maximum amount of potassium in milligrams the recipe can have. (optional)
     * @param minSelenium The minimum amount of selenium in grams the recipe must have. (optional)
     * @param maxSelenium The maximum amount of selenium in grams the recipe can have. (optional)
     * @param minSodium The minimum amount of sodium in milligrams the recipe must have. (optional)
     * @param maxSodium The maximum amount of sodium in milligrams the recipe can have. (optional)
     * @param minSugar The minimum amount of sugar in grams the recipe must have. (optional)
     * @param maxSugar The maximum amount of sugar in grams the recipe can have. (optional)
     * @param minZinc The minimum amount of zinc in milligrams the recipe must have. (optional)
     * @param maxZinc The maximum amount of zinc in milligrams the recipe can have. (optional)
     * @param offset The number of results to skip (between 0 and 900). (optional)
     * @param number The number of expected results (between 1 and 100). (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with
     * proper attribution. (optional)
     * @return SearchRecipeComplex
     */
    @GET(Recipes.SEARCH_RECIPES_COMPLEX)
    fun searchRecipesComplex(
        @Query("query") query: String? = null,
        @Query("cuisine") cuisine: String? = null,
        @Query("excludeCuisine") excludeCuisine: String? = null,
        @Query("diet") diet: String? = null,
        @Query("intolerances") intolerances: String? = null,
        @Query("equipment") equipment: String? = null,
        @Query("includeIngredients") includeIngredients: String? = null,
        @Query("excludeIngredients") excludeIngredients: String? = null,
        @Query("type") type: String? = null,
        @Query("instructionsRequired") instructionsRequired: Boolean? = null,
        @Query("fillIngredients") fillIngredients: Boolean? = null,
        @Query("addRecipeInformation") addRecipeInformation: Boolean? = null,
        @Query("addRecipeNutrition") addRecipeNutrition: Boolean? = null,
        @Query("author") author: String? = null,
        @Query("tags") tags: String? = null,
        @Query("recipeBoxId") recipeBoxId: Long? = null,
        @Query("titleMatch") titleMatch: String? = null,
        @Query("maxReadyTime") maxReadyTime: Long? = null,
        @Query("ignorePantry") ignorePantry: Boolean? = null,
        @Query("sort") sort: String? = null,
        @Query("sortDirection") sortDirection: String? = null,
        @Query("minCarbs") minCarbs: Int? = null,
        @Query("maxCarbs") maxCarbs: Int? = null,
        @Query("minProtein") minProtein: Int? = null,
        @Query("maxProtein") maxProtein: Int? = null,
        @Query("minCalories") minCalories: Int? = null,
        @Query("maxCalories") maxCalories: Int? = null,
        @Query("minFat") minFat: Int? = null,
        @Query("maxFat") maxFat: Int? = null,
        @Query("minAlcohol") minAlcohol: Int? = null,
        @Query("maxAlcohol") maxAlcohol: Int? = null,
        @Query("minCaffeine") minCaffeine: Int? = null,
        @Query("maxCaffeine") maxCaffeine: Int? = null,
        @Query("minCopper") minCopper: Int? = null,
        @Query("maxCopper") maxCopper: Int? = null,
        @Query("minCalcium") minCalcium: Int? = null,
        @Query("maxCalcium") maxCalcium: Int? = null,
        @Query("minCholine") minCholine: Int? = null,
        @Query("maxCholine") maxCholine: Int? = null,
        @Query("minCholesterol") minCholesterol: Int? = null,
        @Query("maxCholesterol") maxCholesterol: Int? = null,
        @Query("minFluoride") minFluoride: Int? = null,
        @Query("maxFluoride") maxFluoride: Int? = null,
        @Query("minSaturatedFat") minSaturatedFat: Int? = null,
        @Query("maxSaturatedFat") maxSaturatedFat: Int? = null,
        @Query("minVitaminA") minVitaminA: Int? = null,
        @Query("maxVitaminA") maxVitaminA: Int? = null,
        @Query("minVitaminC") minVitaminC: Int? = null,
        @Query("maxVitaminC") maxVitaminC: Int? = null,
        @Query("minVitaminD") minVitaminD: Int? = null,
        @Query("maxVitaminD") maxVitaminD: Int? = null,
        @Query("minVitaminE") minVitaminE: Int? = null,
        @Query("maxVitaminE") maxVitaminE: Int? = null,
        @Query("minVitaminK") minVitaminK: Int? = null,
        @Query("maxVitaminK") maxVitaminK: Int? = null,
        @Query("minVitaminB1") minVitaminB1: Int? = null,
        @Query("maxVitaminB1") maxVitaminB1: Int? = null,
        @Query("minVitaminB2") minVitaminB2: Int? = null,
        @Query("maxVitaminB2") maxVitaminB2: Int? = null,
        @Query("minVitaminB5") minVitaminB5: Int? = null,
        @Query("maxVitaminB5") maxVitaminB5: Int? = null,
        @Query("minVitaminB3") minVitaminB3: Int? = null,
        @Query("maxVitaminB3") maxVitaminB3: Int? = null,
        @Query("minVitaminB6") minVitaminB6: Int? = null,
        @Query("maxVitaminB6") maxVitaminB6: Int? = null,
        @Query("minVitaminB12") minVitaminB12: Int? = null,
        @Query("maxVitaminB12") maxVitaminB12: Int? = null,
        @Query("minFiber") minFiber: Int? = null,
        @Query("maxFiber") maxFiber: Int? = null,
        @Query("minFolate") minFolate: Int? = null,
        @Query("maxFolate") maxFolate: Int? = null,
        @Query("minFolicAcid") minFolicAcid: Int? = null,
        @Query("maxFolicAcid") maxFolicAcid: Int? = null,
        @Query("minIodine") minIodine: Int? = null,
        @Query("maxIodine") maxIodine: Int? = null,
        @Query("minIron") minIron: Int? = null,
        @Query("maxIron") maxIron: Int? = null,
        @Query("minMagnesium") minMagnesium: Int? = null,
        @Query("maxMagnesium") maxMagnesium: Int? = null,
        @Query("minManganese") minManganese: Int? = null,
        @Query("maxManganese") maxManganese: Int? = null,
        @Query("minPhosphorus") minPhosphorus: Int? = null,
        @Query("maxPhosphorus") maxPhosphorus: Int? = null,
        @Query("minPotassium") minPotassium: Int? = null,
        @Query("maxPotassium") maxPotassium: Int? = null,
        @Query("minSelenium") minSelenium: Int? = null,
        @Query("maxSelenium") maxSelenium: Int? = null,
        @Query("minSodium") minSodium: Int? = null,
        @Query("maxSodium") maxSodium: Int? = null,
        @Query("minSugar") minSugar: Int? = null,
        @Query("maxSugar") maxSugar: Int? = null,
        @Query("minZinc") minZinc: Int? = null,
        @Query("maxZinc") maxZinc: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("number") number: Int? = null,
        @Query("random") random: Boolean? = null,
        @Query("limitLicense") limitLicense: Boolean? = null
    ): Call<SearchRecipeComplex>

    /**
     * Visualize Equipment
     * Visualize the equipment used to make a recipe.
     * @see RequestVisualizeEquipment
     * @return String
     */
    @POST(Recipes.VISUALIZE_EQUIPMENT)
    fun visualizeEquipment(
        @Body requestVisualizeEquipment: RequestVisualizeEquipment
    ): Call<String>

    /**
     * Visualize Ingredients
     * Visualize ingredients of a recipe.
     * @see RequestVisualizeIngredients
     * @return String
     */
    @POST(Recipes.VISUALIZE_INGREDIENTS)
    fun visualizeIngredients(
        @Body requestVisualizeIngredients: RequestVisualizeIngredients
    ): Call<String>

    /**
     * Visualize Price Breakdown
     * Visualize the price breakdown of a recipe.
     * @see RequestVisualizePriceBreakdown
     * @return String
     */
    @POST(Recipes.VISUALIZE_PRICE_BREAKDOWN)
    fun visualizePriceBreakdown(
        @Body requestVisualizePriceBreakdown: RequestVisualizePriceBreakdown
    ): Call<String>

    /**
     * Visualize Recipe Nutrition
     * Visualize a recipe's nutritional information as HTML including CSS
     * @return String
     */
    @POST(Recipes.VISUALIZE_RECIPE_NUTRITION)
    fun visualizeRecipeNutrition(
        @Body requestVisualizeRecipeNutrition: RequestVisualizeRecipeNutrition
    ): Call<String>

    /**
     * Visualize Recipe Taste
     * Visualize a recipe's taste information as HTML including CSS.
     * @return String
     */
    @POST(Recipes.VISUALIZE_RECIPE_TASTE)
    fun visualizeRecipeTaste(
        @Body requestVisualizeRecipeTaste: RequestVisualizeRecipeTaste
    ): Call<String>
}
