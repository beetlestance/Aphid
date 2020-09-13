package com.beetlestance.spoonacular_kotlin.services

import com.beetlestance.spoonacular_kotlin.models.SpoonacularApiResponse
import com.beetlestance.spoonacular_kotlin.models.request.RequestAnalyzeRecipeInstructions
import com.beetlestance.spoonacular_kotlin.models.request.RequestClassifyCuisine
import com.beetlestance.spoonacular_kotlin.models.request.RequestCreateRecipeCard
import com.beetlestance.spoonacular_kotlin.models.request.RequestParseIngredients
import com.beetlestance.spoonacular_kotlin.models.request.RequestVisualizeEquipment
import com.beetlestance.spoonacular_kotlin.models.request.RequestVisualizeIngredients
import com.beetlestance.spoonacular_kotlin.models.request.RequestVisualizePrceBreakdown
import com.beetlestance.spoonacular_kotlin.models.request.RequestVisualizeRecipeNutrition
import com.beetlestance.spoonacular_kotlin.services.endpoints.Recipes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigDecimal

interface RecipesService {

    /**
     * Get Analyzed Recipe Instructions
     * Get an analyzed breakdown of a recipe's instructions. Each step is enriched with the ingredients and equipment required.
     * @param id The recipe id.
     * @param stepBreakdown Whether to break down the recipe steps even more. (optional)
     * @return Any
     */
    @GET(Recipes.ById.GET_ANALYZED_RECIPE_INSTRUCTIONS)
    fun getAnalyzedRecipeInstructions(
        @Path("id") id: BigDecimal,
        @Query("stepBreakdown") stepBreakdown: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Get Recipe Equipment by ID
     * Get a recipe's equipment list.
     * @param id The recipe id.
     * @return Any
     */
    @GET(Recipes.ById.GET_RECIPE_EQUIPMENT)
    fun getRecipeEquipmentByID(@Path("id") id: BigDecimal): Call<SpoonacularApiResponse<Any>>

    /**
     * Get Recipe Information
     * Use a recipe id to get full information about a recipe, such as ingredients, nutrition, diet and allergen information, etc.
     * @param id The id of the recipe.
     * @param includeNutrition Include nutrition data in the recipe information. Nutrition data is per serving. If you want the nutrition data for the entire recipe, just multiply by the number of servings. (optional)
     * @return Any
     */
    @GET(Recipes.ById.GET_RECIPE_INFORMATION)
    fun getRecipeInformation(
        @Path("id") id: BigDecimal,
        @Query("includeNutrition") includeNutrition: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Get Recipe Ingredients by ID
     * Get a recipe's ingredient list.
     * @param id The recipe id.
     * @return Any
     */
    @GET(Recipes.ById.GET_RECIPE_INGREDIENTS)
    fun getRecipeIngredientsByID(@Path("id") id: BigDecimal): Call<SpoonacularApiResponse<Any>>

    /**
     * Get Recipe Nutrition Widget by ID
     * Get a recipe's nutrition widget data.
     * @param id The recipe id.
     * @return Any
     */
    @GET(Recipes.ById.GET_RECIPE_NUTRITION)
    fun getRecipeNutritionWidgetByID(@Path("id") id: BigDecimal): Call<SpoonacularApiResponse<Any>>

    /**
     * Get Recipe Price Breakdown by ID
     * Get a recipe's price breakdown data.
     * @param id The recipe id.
     * @return Any
     */
    @GET(Recipes.ById.GET_RECIPE_PRICE_BREAKDOWN)
    fun getRecipePriceBreakdownByID(@Path("id") id: BigDecimal): Call<SpoonacularApiResponse<Any>>

    /**
     * Get Similar Recipes
     * Find recipes which are similar to the given one.
     * @param id The id of the source recipe for which similar recipes should be found.
     * @param number The number of random recipes to be returned (between 1 and 100). (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with proper attribution. (optional)
     * @return Any
     */
    @GET(Recipes.ById.GET_SIMILAR_RECIPES)
    fun getSimilarRecipes(
        @Path("id") id: BigDecimal,
        @Query("number") number: BigDecimal?,
        @Query("limitLicense") limitLicense: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Summarize Recipe
     * Automatically generate a short description that summarizes key information about the recipe.
     * @param id The recipe id.
     * @return Any
     */
    @GET(Recipes.ById.SUMMARIZE_RECIPE)
    fun summarizeRecipe(@Path("id") id: BigDecimal): Call<SpoonacularApiResponse<Any>>

    /**
     * Visualize Recipe Equipment by ID
     * Visualize a recipe's equipment list.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_EQUIPMENT)
    fun visualizeRecipeEquipmentByID(
        @Path("id") id: BigDecimal,
        @Query("defaultCss") defaultCss: Boolean?
    ): Call<SpoonacularApiResponse<String>>

    /**
     * Visualize Recipe Ingredients by ID
     * Visualize a recipe's ingredient list.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_INGREDIENTS)
    fun visualizeRecipeIngredientsByID(
        @Path("id") id: BigDecimal,
        @Query("defaultCss") defaultCss: Boolean?
    ): Call<SpoonacularApiResponse<String>>


    /**
     * Visualize Recipe Nutrition by ID
     * Visualize a recipe's nutritional information as HTML including CSS.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_NUTRITION)
    fun visualizeRecipeNutritionByID(
        @Path("id") id: BigDecimal,
        @Query("defaultCss") defaultCss: Boolean?
    ): Call<SpoonacularApiResponse<String>>

    /**
     * Visualize Recipe Price Breakdown by ID
     * Visualize a recipe's price breakdown.
     * @param id The recipe id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Recipes.ById.VISUALIZE_RECIPE_PRICE_BREAKDOWN)
    fun visualizeRecipePriceBreakdownByID(
        @Path("id") id: BigDecimal,
        @Query("defaultCss") defaultCss: Boolean?
    ): Call<SpoonacularApiResponse<String>>

    /**
     * Analyze a Recipe Search Query
     * Parse a recipe search query to find out its intention.
     * @param q The recipe search query.
     * @return Any
     */
    @GET(Recipes.ANALYZE_A_RECIPE_SEARCH_QUERY)
    fun analyzeARecipeSearchQuery(@Query("q") q: String): Call<SpoonacularApiResponse<Any>>

    /**
     * Analyze Recipe Instructions
     * Extract ingredients and equipment from the recipe's instructions.
     * @see RequestAnalyzeRecipeInstructions
     * @return Any
     */
    @POST(Recipes.ANALYZE_RECIPE_INSTRUCTIONS)
    fun analyzeRecipeInstructions(
        @Body requestAnalyzeRecipeInstructions: RequestAnalyzeRecipeInstructions
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Autocomplete Recipe Search
     * Autocomplete a partial input to suggest possible recipe names.
     * @param query The query to be autocompleted.
     * @param number The number of results to return (between 1 and 25). (optional)
     * @return Any
     */
    @GET(Recipes.AUTOCOMPLETE_RECIPE_SEARCH)
    fun autocompleteRecipeSearch(
        @Query("query") query: String,
        @Query("number") number: BigDecimal?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Classify Cuisine
     * Classify the recipe's cuisine.
     * @see RequestClassifyCuisine
     * @return Any
     */
    @POST(Recipes.CLASSIFY_CUISINE)
    fun classifyCuisine(
        @Body requestClassifyCuisine: RequestClassifyCuisine
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Convert Amounts
     * Convert amounts like \&quot;2 cups of flour to grams\&quot;.
     * @param ingredientName The ingredient which you want to convert.
     * @param sourceAmount The amount from which you want to convert, e.g. the 2.5 in \&quot;2.5 cups of flour to grams\&quot;.
     * @param sourceUnit The unit from which you want to convert, e.g. the grams in \&quot;2.5 cups of flour to grams\&quot;. You can also use \&quot;piece\&quot;, e.g. \&quot;3.4 oz tomatoes to piece\&quot;
     * @param targetUnit The unit to which you want to convert, e.g. the grams in \&quot;2.5 cups of flour to grams\&quot;. You can also use \&quot;piece\&quot;, e.g. \&quot;3.4 oz tomatoes to piece\&quot;
     * @return Any
     */
    @POST(Recipes.CONVERT_AMOUNTS)
    fun convertAmounts(
        @Query("ingredientName") ingredientName: String,
        @Query("sourceAmount") sourceAmount: BigDecimal,
        @Query("sourceUnit") sourceUnit: String,
        @Query("targetUnit") targetUnit: String
    ): Call<SpoonacularApiResponse<Any>>


    /**
     * Create Recipe Card
     * Generate a recipe card for a recipe.
     * @see RequestCreateRecipeCard
     * @return Any
     */
    @POST(Recipes.CREATE_RECIPE_CARD)
    fun createRecipeCard(
        @Body requestCreateRecipeCard: RequestCreateRecipeCard
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Extract Recipe from Website
     * This endpoint lets you extract recipe data such as title, ingredients, and instructions from any properly formatted Website.
     * @param url The URL of the recipe page.
     * @param forceExtraction If true, the extraction will be triggered whether we already know the recipe or not. Use this only if information is missing as this operation is slower. (optional)
     * @param analyze If true, the recipe will be analyzed and classified resolving in more data such as cuisines, dish types, and more. (optional)
     * @return Any
     */
    @GET(Recipes.EXTRACT_RECIPE_FROM_WEBSITE)
    fun extractRecipeFromWebsite(
        @Query("url") url: String,
        @Query("forceExtraction") forceExtraction: Boolean?,
        @Query("analyze") analyze: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Get Random Recipes
     * Find random (popular) recipes. If you need to filter recipes by diet, nutrition etc. you might want to consider using the complex recipe search endpoint and set the sort request parameter to random.
     * @param limitLicense Whether the recipes should have an open license that allows display with proper attribution. (optional)
     * @param tags The tags (can be diets, meal types, cuisines, or intolerances) that the recipe must have. (optional)
     * @param number The number of random recipes to be returned (between 1 and 100). (optional)
     * @return Any
     */
    @GET(Recipes.GET_RANDOM_RECIPES)
    fun getRandomRecipes(
        @Query("limitLicense") limitLicense: Boolean?,
        @Query("tags") tags: String?,
        @Query("number") number: BigDecimal?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Get Recipe Information Bulk
     * Get information about multiple recipes at once. This is equivalent to calling the Get Recipe Information endpoint multiple times, but faster.
     * @param ids A comma-separated list of recipe ids.
     * @param includeNutrition Include nutrition data to the recipe information. Nutrition data is per serving. If you want the nutrition data for the entire recipe, just multiply by the number of servings. (optional)
     * @return Any
     */
    @GET(Recipes.GET_RECIPE_INFORMATION_BULK)
    fun getRecipeInformationBulk(
        @Query("ids") ids: String,
        @Query("includeNutrition") includeNutrition: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Guess Nutrition by Dish Name
     * Estimate the macronutrients of a dish based on its title.
     * @param title The title of the dish.
     * @return Any
     */
    @GET(Recipes.GUESS_NUTRITION_BY_DISH_NAME)
    fun guessNutritionByDishName(
        @Query("title") title: String
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Parse Ingredients
     * Extract an ingredient from plain text.
     * @see RequestParseIngredients
     * @return Any
     */
    @POST(Recipes.PARSE_INGREDIENTS)
    fun parseIngredients(
        @Body requestParseIngredients: RequestParseIngredients
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Quick Answer
     * Answer a nutrition related natural language question.
     * @param q The nutrition related question.
     * @return Any
     */
    @GET(Recipes.QUICK_ANSWER)
    fun quickAnswer(
        @Query("q") q: String
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Search Recipes
     * Our recipe API includes over 360,000 recipes as well as an open source recipe database. Consider using the \&quot;Search Recipes Complex\&quot; endpoint for much more flexibility.
     * @param query The (natural language) recipe search query.
     * @param cuisine The cuisine(s) of the recipes. One or more comma separated. See a full list of supported cuisines. (optional)
     * @param diet The diet for which the recipes must be suitable. See a full list of supported diets. (optional)
     * @param excludeIngredients A comma-separated list of ingredients or ingredient types that the recipes must not contain. (optional)
     * @param intolerances A comma-separated list of intolerances. All recipes returned must not contain ingredients that are not suitable for people with the intolerances entered. See a full list of supported intolerances. Please note: due to the automatic nature of the recipe analysis, the API cannot be 100% accurate in all cases. Please advise your users to seek professional help with medical issues. (optional)
     * @param offset The number of results to skip (between 0 and 900). (optional)
     * @param number The number of results to return (between 1 and 100). (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with proper attribution. (optional)
     * @param instructionsRequired Whether the recipes must have instructions. (optional)
     * @return Any
     */
    @GET(Recipes.SEARCH_RECIPES)
    fun searchRecipes(
        @Query("query") query: String,
        @Query("cuisine") cuisine: String?,
        @Query("diet") diet: String?,
        @Query("excludeIngredients") excludeIngredients: String?,
        @Query("intolerances") intolerances: String?,
        @Query("offset") offset: BigDecimal?,
        @Query("number") number: BigDecimal?,
        @Query("limitLicense") limitLicense: Boolean?,
        @Query("instructionsRequired") instructionsRequired: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Search Recipes by Ingredients
     *              Ever wondered what recipes you can cook with the ingredients you have in your fridge or pantry? This endpoint lets you find recipes that either maximize the usage of ingredients you have at hand (pre shopping) or minimize the ingredients that you don't currently have (post shopping).
     * @param ingredients A comma-separated list of ingredients that the recipes should contain.
     * @param number The maximum number of recipes to return (between 1 and 100). Defaults to 10. (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with proper attribution. (optional)
     * @param ranking Whether to maximize used ingredients (1) or minimize missing ingredients (2) first. (optional)
     * @param ignorePantry Whether to ignore typical pantry items, such as water, salt, flour, etc. (optional)
     * @return Any
     */
    @GET(Recipes.SEARCH_RECIPES_BY_INGREDIENTS)
    fun searchRecipesByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("number") number: BigDecimal?,
        @Query("limitLicense") limitLicense: Boolean?,
        @Query("ranking") ranking: BigDecimal?,
        @Query("ignorePantry") ignorePantry: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Search Recipes by Nutrients
     * Find a set of recipes that adhere to the given nutritional limits. You may set limits for macronutrients (calories, protein, fat, and carbohydrate) and/or many micronutrients.
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
     * @param random If true, every request will give you a random set of recipes within the requested limits. (optional)
     * @param limitLicense Whether the recipes should have an open license that allows display with proper attribution. (optional)
     * @return Any
     */
    @GET(Recipes.SEARCH_RECIPES_BY_NUTRIENTS)
    fun searchRecipesByNutrients(
        @Query("minCarbs") minCarbs: BigDecimal?,
        @Query("maxCarbs") maxCarbs: BigDecimal?,
        @Query("minProtein") minProtein: BigDecimal?,
        @Query("maxProtein") maxProtein: BigDecimal?,
        @Query("minCalories") minCalories: BigDecimal?,
        @Query("maxCalories") maxCalories: BigDecimal?,
        @Query("minFat") minFat: BigDecimal?,
        @Query("maxFat") maxFat: BigDecimal?,
        @Query("minAlcohol") minAlcohol: BigDecimal?,
        @Query("maxAlcohol") maxAlcohol: BigDecimal?,
        @Query("minCaffeine") minCaffeine: BigDecimal?,
        @Query("maxCaffeine") maxCaffeine: BigDecimal?,
        @Query("minCopper") minCopper: BigDecimal?,
        @Query("maxCopper") maxCopper: BigDecimal?,
        @Query("minCalcium") minCalcium: BigDecimal?,
        @Query("maxCalcium") maxCalcium: BigDecimal?,
        @Query("minCholine") minCholine: BigDecimal?,
        @Query("maxCholine") maxCholine: BigDecimal?,
        @Query("minCholesterol") minCholesterol: BigDecimal?,
        @Query("maxCholesterol") maxCholesterol: BigDecimal?,
        @Query("minFluoride") minFluoride: BigDecimal?,
        @Query("maxFluoride") maxFluoride: BigDecimal?,
        @Query("minSaturatedFat") minSaturatedFat: BigDecimal?,
        @Query("maxSaturatedFat") maxSaturatedFat: BigDecimal?,
        @Query("minVitaminA") minVitaminA: BigDecimal?,
        @Query("maxVitaminA") maxVitaminA: BigDecimal?,
        @Query("minVitaminC") minVitaminC: BigDecimal?,
        @Query("maxVitaminC") maxVitaminC: BigDecimal?,
        @Query("minVitaminD") minVitaminD: BigDecimal?,
        @Query("maxVitaminD") maxVitaminD: BigDecimal?,
        @Query("minVitaminE") minVitaminE: BigDecimal?,
        @Query("maxVitaminE") maxVitaminE: BigDecimal?,
        @Query("minVitaminK") minVitaminK: BigDecimal?,
        @Query("maxVitaminK") maxVitaminK: BigDecimal?,
        @Query("minVitaminB1") minVitaminB1: BigDecimal?,
        @Query("maxVitaminB1") maxVitaminB1: BigDecimal?,
        @Query("minVitaminB2") minVitaminB2: BigDecimal?,
        @Query("maxVitaminB2") maxVitaminB2: BigDecimal?,
        @Query("minVitaminB5") minVitaminB5: BigDecimal?,
        @Query("maxVitaminB5") maxVitaminB5: BigDecimal?,
        @Query("minVitaminB3") minVitaminB3: BigDecimal?,
        @Query("maxVitaminB3") maxVitaminB3: BigDecimal?,
        @Query("minVitaminB6") minVitaminB6: BigDecimal?,
        @Query("maxVitaminB6") maxVitaminB6: BigDecimal?,
        @Query("minVitaminB12") minVitaminB12: BigDecimal?,
        @Query("maxVitaminB12") maxVitaminB12: BigDecimal?,
        @Query("minFiber") minFiber: BigDecimal?,
        @Query("maxFiber") maxFiber: BigDecimal?,
        @Query("minFolate") minFolate: BigDecimal?,
        @Query("maxFolate") maxFolate: BigDecimal?,
        @Query("minFolicAcid") minFolicAcid: BigDecimal?,
        @Query("maxFolicAcid") maxFolicAcid: BigDecimal?,
        @Query("minIodine") minIodine: BigDecimal?,
        @Query("maxIodine") maxIodine: BigDecimal?,
        @Query("minIron") minIron: BigDecimal?,
        @Query("maxIron") maxIron: BigDecimal?,
        @Query("minMagnesium") minMagnesium: BigDecimal?,
        @Query("maxMagnesium") maxMagnesium: BigDecimal?,
        @Query("minManganese") minManganese: BigDecimal?,
        @Query("maxManganese") maxManganese: BigDecimal?,
        @Query("minPhosphorus") minPhosphorus: BigDecimal?,
        @Query("maxPhosphorus") maxPhosphorus: BigDecimal?,
        @Query("minPotassium") minPotassium: BigDecimal?,
        @Query("maxPotassium") maxPotassium: BigDecimal?,
        @Query("minSelenium") minSelenium: BigDecimal?,
        @Query("maxSelenium") maxSelenium: BigDecimal?,
        @Query("minSodium") minSodium: BigDecimal?,
        @Query("maxSodium") maxSodium: BigDecimal?,
        @Query("minSugar") minSugar: BigDecimal?,
        @Query("maxSugar") maxSugar: BigDecimal?,
        @Query("minZinc") minZinc: BigDecimal?,
        @Query("maxZinc") maxZinc: BigDecimal?,
        @Query("offset") offset: BigDecimal?,
        @Query("number") number: BigDecimal?,
        @Query("random") random: Boolean?,
        @Query("limitLicense") limitLicense: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Search Recipes Complex
     * Search through hundreds of thousands of recipes using advanced filtering and ranking. NOTE: This method combines searching by query, by ingredients, and by nutrients into one endpoint.
     * @param query The (natural language) recipe search query.
     * @param cuisine The cuisine(s) of the recipes. One or more, comma separated (will be interpreted as 'OR'). See a full list of supported cuisines. (optional)
     * @param excludeCuisine The cuisine(s) the recipes must not match. One or more, comma separated (will be interpreted as 'AND'). See a full list of supported cuisines. (optional)
     * @param diet The diet for which the recipes must be suitable. See a full list of supported diets. (optional)
     * @param intolerances A comma-separated list of intolerances. All recipes returned must not contain ingredients that are not suitable for people with the intolerances entered. See a full list of supported intolerances. (optional)
     * @param equipment The equipment required. Multiple values will be interpreted as 'or'. For example, value could be \&quot;blender, frying pan, bowl\&quot;. (optional)
     * @param includeIngredients A comma-separated list of ingredients that should/must be used in the recipes. (optional)
     * @param excludeIngredients A comma-separated list of ingredients or ingredient types that the recipes must not contain. (optional)
     * @param type The type of recipe. See a full list of supported meal types. (optional)
     * @param instructionsRequired Whether the recipes must have instructions. (optional)
     * @param fillIngredients Add information about the ingredients and whether they are used or missing in relation to the query. (optional)
     * @param addRecipeInformation If set to true, you get more information about the recipes returned. (optional)
     * @param addRecipeNutrition If set to true, you get nutritional information about each recipes returned. (optional)
     * @param author The username of the recipe author. (optional)
     * @param tags User defined tags that have to match. The author param has to be set. (optional)
     * @param recipeBoxId The id of the recipe box to which the search should be limited to. (optional)
     * @param titleMatch Enter text that must be found in the title of the recipes. (optional)
     * @param maxReadyTime The maximum time in minutes it should take to prepare and cook the recipe. (optional)
     * @param ignorePantry Whether to ignore typical pantry items, such as water, salt, flour, etc. (optional)
     * @param sort The strategy to sort recipes by. See a full list of supported sorting options. (optional)
     * @param sortDirection The direction in which to sort. Must be either 'asc' (ascending) or 'desc' (descending). (optional)
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
     * @param limitLicense Whether the recipes should have an open license that allows display with proper attribution. (optional)
     * @return Any
     */
    @GET(Recipes.SEARCH_RECIPES_COMPLEX)
    fun searchRecipesComplex(
        @Query("query") query: String,
        @Query("cuisine") cuisine: String?,
        @Query("excludeCuisine") excludeCuisine: String?,
        @Query("diet") diet: String?,
        @Query("intolerances") intolerances: String?,
        @Query("equipment") equipment: String?,
        @Query("includeIngredients") includeIngredients: String?,
        @Query("excludeIngredients") excludeIngredients: String?,
        @Query("type") type: String?,
        @Query("instructionsRequired") instructionsRequired: Boolean?,
        @Query("fillIngredients") fillIngredients: Boolean?,
        @Query("addRecipeInformation") addRecipeInformation: Boolean?,
        @Query("addRecipeNutrition") addRecipeNutrition: Boolean?,
        @Query("author") author: String?,
        @Query("tags") tags: String?,
        @Query("recipeBoxId") recipeBoxId: BigDecimal?,
        @Query("titleMatch") titleMatch: String?,
        @Query("maxReadyTime") maxReadyTime: BigDecimal?,
        @Query("ignorePantry") ignorePantry: Boolean?,
        @Query("sort") sort: String?,
        @Query("sortDirection") sortDirection: String?,
        @Query("minCarbs") minCarbs: BigDecimal?,
        @Query("maxCarbs") maxCarbs: BigDecimal?,
        @Query("minProtein") minProtein: BigDecimal?,
        @Query("maxProtein") maxProtein: BigDecimal?,
        @Query("minCalories") minCalories: BigDecimal?,
        @Query("maxCalories") maxCalories: BigDecimal?,
        @Query("minFat") minFat: BigDecimal?,
        @Query("maxFat") maxFat: BigDecimal?,
        @Query("minAlcohol") minAlcohol: BigDecimal?,
        @Query("maxAlcohol") maxAlcohol: BigDecimal?,
        @Query("minCaffeine") minCaffeine: BigDecimal?,
        @Query("maxCaffeine") maxCaffeine: BigDecimal?,
        @Query("minCopper") minCopper: BigDecimal?,
        @Query("maxCopper") maxCopper: BigDecimal?,
        @Query("minCalcium") minCalcium: BigDecimal?,
        @Query("maxCalcium") maxCalcium: BigDecimal?,
        @Query("minCholine") minCholine: BigDecimal?,
        @Query("maxCholine") maxCholine: BigDecimal?,
        @Query("minCholesterol") minCholesterol: BigDecimal?,
        @Query("maxCholesterol") maxCholesterol: BigDecimal?,
        @Query("minFluoride") minFluoride: BigDecimal?,
        @Query("maxFluoride") maxFluoride: BigDecimal?,
        @Query("minSaturatedFat") minSaturatedFat: BigDecimal?,
        @Query("maxSaturatedFat") maxSaturatedFat: BigDecimal?,
        @Query("minVitaminA") minVitaminA: BigDecimal?,
        @Query("maxVitaminA") maxVitaminA: BigDecimal?,
        @Query("minVitaminC") minVitaminC: BigDecimal?,
        @Query("maxVitaminC") maxVitaminC: BigDecimal?,
        @Query("minVitaminD") minVitaminD: BigDecimal?,
        @Query("maxVitaminD") maxVitaminD: BigDecimal?,
        @Query("minVitaminE") minVitaminE: BigDecimal?,
        @Query("maxVitaminE") maxVitaminE: BigDecimal?,
        @Query("minVitaminK") minVitaminK: BigDecimal?,
        @Query("maxVitaminK") maxVitaminK: BigDecimal?,
        @Query("minVitaminB1") minVitaminB1: BigDecimal?,
        @Query("maxVitaminB1") maxVitaminB1: BigDecimal?,
        @Query("minVitaminB2") minVitaminB2: BigDecimal?,
        @Query("maxVitaminB2") maxVitaminB2: BigDecimal?,
        @Query("minVitaminB5") minVitaminB5: BigDecimal?,
        @Query("maxVitaminB5") maxVitaminB5: BigDecimal?,
        @Query("minVitaminB3") minVitaminB3: BigDecimal?,
        @Query("maxVitaminB3") maxVitaminB3: BigDecimal?,
        @Query("minVitaminB6") minVitaminB6: BigDecimal?,
        @Query("maxVitaminB6") maxVitaminB6: BigDecimal?,
        @Query("minVitaminB12") minVitaminB12: BigDecimal?,
        @Query("maxVitaminB12") maxVitaminB12: BigDecimal?,
        @Query("minFiber") minFiber: BigDecimal?,
        @Query("maxFiber") maxFiber: BigDecimal?,
        @Query("minFolate") minFolate: BigDecimal?,
        @Query("maxFolate") maxFolate: BigDecimal?,
        @Query("minFolicAcid") minFolicAcid: BigDecimal?,
        @Query("maxFolicAcid") maxFolicAcid: BigDecimal?,
        @Query("minIodine") minIodine: BigDecimal?,
        @Query("maxIodine") maxIodine: BigDecimal?,
        @Query("minIron") minIron: BigDecimal?,
        @Query("maxIron") maxIron: BigDecimal?,
        @Query("minMagnesium") minMagnesium: BigDecimal?,
        @Query("maxMagnesium") maxMagnesium: BigDecimal?,
        @Query("minManganese") minManganese: BigDecimal?,
        @Query("maxManganese") maxManganese: BigDecimal?,
        @Query("minPhosphorus") minPhosphorus: BigDecimal?,
        @Query("maxPhosphorus") maxPhosphorus: BigDecimal?,
        @Query("minPotassium") minPotassium: BigDecimal?,
        @Query("maxPotassium") maxPotassium: BigDecimal?,
        @Query("minSelenium") minSelenium: BigDecimal?,
        @Query("maxSelenium") maxSelenium: BigDecimal?,
        @Query("minSodium") minSodium: BigDecimal?,
        @Query("maxSodium") maxSodium: BigDecimal?,
        @Query("minSugar") minSugar: BigDecimal?,
        @Query("maxSugar") maxSugar: BigDecimal?,
        @Query("minZinc") minZinc: BigDecimal?,
        @Query("maxZinc") maxZinc: BigDecimal?,
        @Query("offset") offset: BigDecimal?,
        @Query("number") number: BigDecimal?,
        @Query("random") random: Boolean?,
        @Query("limitLicense") limitLicense: Boolean?
    ): Call<SpoonacularApiResponse<Any>>

    /**
     * Visualize Equipment
     * Visualize the equipment used to make a recipe.
     * @see RequestVisualizeEquipment
     * @return String
     */
    @POST(Recipes.VISUALIZE_EQUIPMENT)
    fun visualizeEquipment(
        @Body requestVisualizeEquipment: RequestVisualizeEquipment
    ): Call<SpoonacularApiResponse<String>>

    /**
     * Visualize Ingredients
     * Visualize ingredients of a recipe.
     * @see RequestVisualizeIngredients
     * @return String
     */
    @POST(Recipes.VISUALIZE_INGREDIENTS)
    fun visualizeIngredients(
        @Body requestVisualizeIngredients: RequestVisualizeIngredients
    ): Call<SpoonacularApiResponse<String>>

    /**
     * Visualize Price Breakdown
     * Visualize the price breakdown of a recipe.
     * @see RequestVisualizePrceBreakdown
     * @return String
     */
    @POST(Recipes.VISUALIZE_PRICE_BREAKDOWN)
    fun visualizePriceBreakdown(
        @Body requestVisualizePrceBreakdown: RequestVisualizePrceBreakdown
    ): Call<SpoonacularApiResponse<String>>

    /**
     * Visualize Recipe Nutrition
     * Visualize a recipe's nutritional information as HTML including CSS
     * @return String
     */
    @POST(Recipes.VISUALIZE_RECIPE_NUTRITION)
    fun visualizeRecipeNutrition(
        @Body requestVisualizeRecipeNutrition: RequestVisualizeRecipeNutrition
    ): Call<SpoonacularApiResponse<String>>
}
