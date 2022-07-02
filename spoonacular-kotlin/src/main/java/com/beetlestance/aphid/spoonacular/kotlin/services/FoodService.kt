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

import com.beetlestance.aphid.spoonacular.kotlin.models.request.food.RequestClassifyGroceryProduct
import com.beetlestance.aphid.spoonacular.kotlin.models.request.food.RequestDetectTextInFood
import com.beetlestance.aphid.spoonacular.kotlin.models.request.food.RequestMapIngredientsToGroceryProduct
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.ARandomFoodJoke
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.CustomFood
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.DetectFoodInText
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.FoodSiteContent
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.FoodVideos
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.RandomFoodTrivia
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.converse.ConversationSuggests
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.converse.TalkToChatbot
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.images.ImageAnalysisByUrl
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.images.ImageClassificationByUrl
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.ingredients.AutoCompleteIngredientSearch
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.ingredients.IngredientInformation
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.ingredients.IngredientSubstitutes
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.ingredients.MapIngredientsToGroceryProducts
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.menuitem.AutoCompleteMenuItem
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.menuitem.MenuItem
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.menuitem.MenuItemInformation
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.product.AutoCompleteProductSearch
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.product.ClassifyGroceryProduct
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.product.ComparableProduct
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.product.GroceryProduct
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.product.GroceryProductByUpc
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.product.ProductInformation
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.wine.DishPairingForWine
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.wine.WineDescription
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.wine.WinePairing
import com.beetlestance.aphid.spoonacular.kotlin.models.response.food.wine.WineRecommendation
import com.beetlestance.aphid.spoonacular.kotlin.services.endpoints.Food
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodService {

    /**
     * Get Ingredient Information
     * Use an ingredient id to get all available information about an ingredient, such as its
     * image and supermarket aisle.
     * @param id The ingredient id.
     * @param amount The amount of this ingredient. (optional)
     * @param unit The unit for the given amount. (optional)
     * @return GetIngredientInformation
     */
    @GET(Food.Ingredients.ById.GET_INGREDIENT_INFORMATION)
    fun getIngredientInformation(
        @Path("id") id: Long,
        @Query("amount") amount: Double? = null,
        @Query("unit") unit: String? = null
    ): Call<IngredientInformation>

    /**
     * Get Ingredient Substitutes by ID
     * Search for substitutes for a given ingredient.
     * @param id The id of the ingredient you want substitutes for.
     * @return IngredientSubstitutes
     */
    @GET(Food.Ingredients.ById.GET_INGREDIENT_SUBSTITUTES)
    fun getIngredientSubstitutesByID(@Path("id") id: Long): Call<IngredientSubstitutes>

    /**
     * Autocomplete Ingredient Search
     * Autocomplete the entry of an ingredient.
     * @param query The partial or full ingredient name.
     * @param number The number of results to return (between 1 and 100). (optional)
     * @param metaInformation Whether to return more meta information about the ingredients. (optional)
     * @param intolerances A comma-separated list of intolerances. All recipes returned must
     * not contain ingredients that are not suitable for people with the intolerances entered.
     * See a full list of supported intolerances. (optional)
     * @return List<AutoCompleteIngredientSearch>
     */
    @GET(Food.Ingredients.AUTOCOMPLETE_INGREDIENT_SEARCH)
    fun autocompleteIngredientSearch(
        @Query("query") query: String,
        @Query("number") number: Int? = null,
        @Query("metaInformation") metaInformation: Boolean? = null,
        @Query("intolerances") intolerances: Boolean? = null
    ): Call<List<AutoCompleteIngredientSearch>>

    /**
     * Get Ingredient Substitutes
     * Search for substitutes for a given ingredient.
     * @param ingredientName The name of the ingredient you want to replace.
     * @return IngredientSubstitutes
     */
    @GET(Food.Ingredients.GET_INGREDIENT_SUBSTITUTES)
    fun getIngredientSubstitutes(
        @Query("ingredientName") ingredientName: String
    ): Call<IngredientSubstitutes>

    /**
     * Map Ingredients to Grocery Products
     * Map a set of ingredients to products you can buy in the grocery store.
     * @param requestMapIngredientsToGroceryProduct
     * @return List<MapIngredientsToGroceryProducts>
     */
    @POST(Food.Ingredients.MAP_INGREDIENTS_TO_GROCERY_PRODUCTS)
    fun mapIngredientsToGroceryProducts(
        @Body requestMapIngredientsToGroceryProduct: RequestMapIngredientsToGroceryProduct
    ): Call<List<MapIngredientsToGroceryProducts>>

    /**
     * Get Product Information
     * Use a product id to get full information about a product, such as ingredients, nutrition,
     * etc. The nutritional information is per serving.
     * @param id The id of the packaged food.
     * @return ProductInformation
     */
    @GET(Food.Products.ById.GET_PRODUCT_INFORMATION)
    fun getProductInformation(@Query("id") id: Long): Call<ProductInformation>

    /**
     * Visualize Product Nutrition by ID
     * Visualize a product's nutritional information as HTML including CSS.
     * @param id The id of the product.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Food.Products.ById.VISUALIZE_PRODUCT_NUTRITION)
    fun visualizeProductNutritionByID(
        @Query("id") id: Long,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): Call<String>

    /**
     * Get Comparable Products
     * Find comparable products to the given one.
     * @param upc The UPC of the product for which you want to find comparable products.
     * @return ComparableProduct
     */
    @GET(Food.Products.ByUPC.GET_COMPARABLE_PRODUCTS)
    fun getComparableProducts(@Path("upc") upc: Long): Call<ComparableProduct>

    /**
     * Search Grocery Products by UPC
     * Get information about a packaged food using its UPC.
     * @param upc The product's UPC.
     * @return GroceryProductByUpc
     */
    @GET(Food.Products.ByUPC.SEARCH_GROCERY_PRODUCTS)
    fun searchGroceryProductsByUPC(@Path("upc") upc: Long): Call<GroceryProductByUpc>

    /**
     * Autocomplete Product Search
     * Generate suggestions for grocery products based on a (partial) query. The matches will be
     * found by looking in the title only.
     * @param query The (partial) search query.
     * @param number The number of results to return (between 1 and 25). (optional)
     * @return AutoCompleteProductSearch
     */
    @POST(Food.Products.AUTOCOMPLETE_PRODUCT_SEARCH)
    fun autocompleteProductSearch(
        @Query("query") query: String,
        @Query("number") number: Int? = null
    ): Call<AutoCompleteProductSearch>

    /**
     * Classify Grocery Product
     * This endpoint allows you to match a packaged food to a basic category, e.g. a specific brand
     * of milk to the category milk.
     * @param requestClassifyGroceryProduct A json object containing the product title.
     * @param locale The display name of the returned category, supported is en_US
     * (for American English) and en_GB (for British English). (optional)
     * @return ClassifyGroceryProduct
     */
    @POST(Food.Products.CLASSIFY_GROCERY_PRODUCT)
    fun classifyGroceryProduct(
        @Body requestClassifyGroceryProduct: RequestClassifyGroceryProduct,
        @Query("locale") locale: String? = null
    ): Call<ClassifyGroceryProduct>

    /**
     * Classify Grocery Product Bulk
     * Provide a set of product jsons, get back classified products.
     * @param requestClassifyGroceryProduct
     * @param locale The display name of the returned category, supported is en_US
     * (for American English) and en_GB (for British English). (optional)
     * @return List<ClassifyGroceryProduct>
     */
    @POST(Food.Products.CLASSIFY_GROCERY_PRODUCT_BULK)
    fun classifyGroceryProductBulk(
        @Body requestClassifyGroceryProduct: RequestClassifyGroceryProduct,
        @Query("locale") locale: String? = null
    ): Call<List<ClassifyGroceryProduct>>

    /**
     * Search Grocery Products
     * Search packaged food products, such as frozen pizza or Greek yogurt.
     * @param query The search query.
     * @param minCalories The minimum amount of calories the product must have. (optional)
     * @param maxCalories The maximum amount of calories the product can have. (optional)
     * @param minCarbs The minimum amount of carbohydrates in grams the product must have. (optional)
     * @param maxCarbs The maximum amount of carbohydrates in grams the product can have. (optional)
     * @param minProtein The minimum amount of protein in grams the product must have. (optional)
     * @param maxProtein The maximum amount of protein in grams the product can have. (optional)
     * @param minFat The minimum amount of fat in grams the product must have. (optional)
     * @param maxFat The maximum amount of fat in grams the product can have. (optional)
     * @param offset The number of results to skip (between 0 and 990). (optional)
     * @param number The number of expected results (between 1 and 100). (optional)
     * @return GroceryProduct
     */
    @GET(Food.Products.SEARCH_GROCERY_PRODUCTS)
    fun searchGroceryProducts(
        @Query("query") query: String,
        @Query("minCalories") minCalories: Int? = null,
        @Query("maxCalories") maxCalories: Int? = null,
        @Query("minCarbs") minCarbs: Int? = null,
        @Query("maxCarbs") maxCarbs: Int? = null,
        @Query("minProtein") minProtein: Int? = null,
        @Query("maxProtein") maxProtein: Int? = null,
        @Query("minFat") minFat: Int? = null,
        @Query("maxFat") maxFat: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("number") number: Int? = null
    ): Call<GroceryProduct>

    /**
     * Get Menu Item Information
     * Use a menu item id to get all available information about a menu item, such as nutrition.
     * @param id The menu item id.
     * @return MenuItemInformation
     */
    @GET(Food.MenuItems.ById.GET_MENU_ITEM_INFORMATION)
    fun getMenuItemInformation(@Query("id") id: Long): Call<MenuItemInformation>

    /**
     * Visualize Menu Item Nutrition by ID
     * Visualize a menu item's nutritional information as HTML including CSS.
     * @param id The menu item id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Food.MenuItems.ById.VISUALIZE_MENU_ITEM_NUTRITION)
    fun visualizeMenuItemNutritionByID(
        @Query("id") id: Long,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): Call<String>

    /**
     * Autocomplete Menu Item Search
     * Generate suggestions for menu items based on a (partial) query. The matches will be found by
     * looking in the title only.
     * @param query The (partial) search query.
     * @param number The number of results to return (between 1 and 25). (optional)
     * @return AutoCompleteMenuItem
     */
    @GET(Food.MenuItems.AUTOCOMPLETE_MENU_ITEM_SEARCH)
    fun autocompleteMenuItemSearch(
        @Query("query") query: String,
        @Query("number") number: Int? = null
    ): Call<AutoCompleteMenuItem>

    /**
     * Search Menu Items
     * Search over 115,000 menu items from over 800 fast food and chain restaurants. For example,
     * McDonald's Big Mac or Starbucks Mocha.
     * @param query The search query.
     * @param minCalories The minimum amount of calories the menu item must have. (optional)
     * @param maxCalories The maximum amount of calories the menu item can have. (optional)
     * @param minCarbs The minimum amount of carbohydrates in grams the menu item must have. (optional)
     * @param maxCarbs The maximum amount of carbohydrates in grams the menu item can have. (optional)
     * @param minProtein The minimum amount of protein in grams the menu item must have. (optional)
     * @param maxProtein The maximum amount of protein in grams the menu item can have. (optional)
     * @param minFat The minimum amount of fat in grams the menu item must have. (optional)
     * @param maxFat The maximum amount of fat in grams the menu item can have. (optional)
     * @param offset The offset number for paging (between 0 and 990). (optional)
     * @param number The number of expected results (between 1 and 10). (optional)
     * @return MenuItem
     */
    @GET(Food.MenuItems.SEARCH_MENU_ITEMS)
    fun searchMenuItems(
        @Query("query") query: String,
        @Query("minCalories") minCalories: Int? = null,
        @Query("maxCalories") maxCalories: Int? = null,
        @Query("minCarbs") minCarbs: Int? = null,
        @Query("maxCarbs") maxCarbs: Int? = null,
        @Query("minProtein") minProtein: Int? = null,
        @Query("maxProtein") maxProtein: Int? = null,
        @Query("minFat") minFat: Int? = null,
        @Query("maxFat") maxFat: Int? = null,
        @Query("offset") offset: Int? = null,
        @Query("number") number: Int? = null
    ): Call<MenuItem>

    /**
     * Get Dish Pairing for Wine
     * Find a dish that goes well with a given wine.
     * @param wine The type of wine that should be paired, e.g. "merlot", "riesling", or "malbec".
     * @return DishPairingForWine
     */
    @GET(Food.Wine.GET_DISH_PAIRING_FOR_WINE)
    fun getDishPairingForWine(@Query("wine") wine: String): Call<DishPairingForWine>

    /**
     * Get Wine Description
     * Get a simple description of a certain wine, e.g. "malbec", "riesling", or "merlot".
     * @param wine The name of the wine that should be paired, e.g. "merlot", "riesling", or "malbec".
     * @return WineDescription
     */
    @GET(Food.Wine.GET_WINE_DESCRIPTION)
    fun getWineDescription(@Query("wine") wine: String): Call<WineDescription>

    /**
     * Get Wine Pairing
     * Find a wine that goes well with a food. Food can be a dish name ("steak"), an ingredient
     * name ("salmon"), or a cuisine ("italian").
     * @param food The food to get a pairing for. This can be a dish ("steak"), an ingredient
     * ("salmon"), or a cuisine ("italian").
     * @param maxPrice The maximum price for the specific wine recommendation in USD. (optional)
     * @return WinePairing
     */
    @GET(Food.Wine.GET_WINE_PAIRING)
    fun getWinePairing(
        @Query("food") food: String,
        @Query("maxPrice") maxPrice: Int? = null
    ): Call<WinePairing>

    /**
     * Get Wine Recommendation
     * Get a specific wine recommendation (concrete product) for a given wine type, e.g. "merlot".
     * @param wine The type of wine to get a specific product recommendation for.
     * @param maxPrice The maximum price for the specific wine recommendation in USD. (optional)
     * @param minRating The minimum rating of the recommended wine between 0 and 1. For example,
     * 0.8 equals 4 out of 5 stars. (optional)
     * @param number The number of wine recommendations expected (between 1 and 100). (optional)
     * @return WineRecommendation
     */
    @GET(Food.Wine.GET_WINE_RECOMMENDATION)
    fun getWineRecommendation(
        @Query("wine") wine: String,
        @Query("maxPrice") maxPrice: Int? = null,
        @Query("minRating") minRating: Double? = null,
        @Query("number") number: Int? = null
    ): Call<WineRecommendation>

    /**
     * Image Analysis by URL
     * Analyze a food image. The API tries to classify the image, guess the nutrition, and find a
     * matching recipes. You can play around with that endpoint!
     * @param imageUrl The URL of the image to be analyzed.
     * @return Any
     */
    @GET(Food.Images.IMAGE_ANALYSIS_BY_URL)
    fun imageAnalysisByURL(@Query("imageUrl") imageUrl: String): Call<ImageAnalysisByUrl>

    /**
     * Image Classification by URL
     * Classify a food image. You can play around with that endpoint!
     * @param imageUrl The URL of the image to be classified.
     * @return ImageClassificationByUrl
     */
    @GET(Food.Images.IMAGE_CLASSIFICATION_BY_URL)
    fun imageClassificationByURL(@Query("imageUrl") imageUrl: String): Call<ImageClassificationByUrl>

    /**
     * Get Conversation Suggests
     * This endpoint returns suggestions for things the user can say or ask the chatbot.
     * @param query A (partial) query from the user. The endpoint will return if it matches topics
     * it can talk about.
     * @param number The number of suggestions to return (between 1 and 25). (optional)
     * @return ConversationSuggests
     */
    @GET(Food.Converse.GET_CONVERSATION_SUGGESTS)
    fun getConversationSuggests(
        @Query("query") query: String,
        @Query("number") number: Int? = null
    ): Call<ConversationSuggests>

    /**
     * Talk to Chatbot
     * This endpoint can be used to have a conversation about food with the spoonacular chatbot.
     * Use the "Get Conversation Suggests" endpoint to show your user what he or she can say.
     * @param text The request / question / answer from the user to the chatbot.
     * @param contextId An arbitrary globally unique id for your conversation. The conversation can
     * contain states so you should pass your context id if you want the bot to be able to remember
     * the conversation. (optional)
     * @return TalkToChatbot
     */
    @GET(Food.Converse.TALK_TO_CHAT_BOT)
    fun talkToChatbot(
        @Query("text") text: String,
        @Query("contextId") contextId: String? = null
    ): Call<TalkToChatbot>

    /**
     * Get a Random Food Joke
     * Get a random joke that is related to food. Caution: this is an endpoint for adults!
     * @return ARandomFoodJoke
     */
    @GET(Food.GET_A_RANDOM_FOOD_JOKE)
    fun getARandomFoodJoke(): Call<ARandomFoodJoke>

    /**
     * Get Random Food Trivia
     * Returns random food trivia.
     * @return RandomFoodTrivia
     */
    @GET(Food.GET_RANDOM_FOOD_TRIVIA)
    fun getRandomFoodTrivia(): Call<RandomFoodTrivia>

    /**
     * Detect Food in Text
     * Take any text and find all mentions of food contained within it. This task is also called
     * Named Entity Recognition (NER). In this case, the entities are foods. Either dishes, such as
     * pizza or cheeseburger, or ingredients, such as cucumber or almonds.
     * @param requestDetectTextInFood The text in which food items, such as dish names and ingredients,
     * should be detected in.
     * @return DetectFoodInText
     */
    @POST(Food.DETECT_FOOD_IN_TEXT)
    fun detectFoodInText(
        @Body requestDetectTextInFood: RequestDetectTextInFood
    ): Call<DetectFoodInText>

    /**
     * Search Custom Foods
     * Search custom foods in a user's account.
     * @param query The search query.
     * @param username The username.
     * @param hash The private hash for the username.
     * @param offset The number of results to skip (between 0 and 990). (optional)
     * @param number The number of expected results (between 1 and 100). (optional)
     * @return CustomFood
     */
    @GET(Food.SEARCH_CUSTOM_FOODS)
    fun searchCustomFoods(
        @Query("query") query: String,
        @Query("username") username: String,
        @Query("hash") hash: String,
        @Query("offset") offset: Int? = null,
        @Query("number") number: Int? = null
    ): Call<CustomFood>

    /**
     * Search Food Videos
     * Find recipe and other food related videos.
     * @param query The search query.
     * @param type The type of the recipes. See a full list of supported meal types. (optional)
     * @param cuisine The cuisine(s) of the recipes. One or more, comma separated. See a full list
     * of supported cuisines. (optional)
     * @param diet The diet for which the recipes must be suitable. See a full list of supported
     * diets. (optional)
     * @param includeIngredients A comma-separated list of ingredients that the recipes should
     * contain. (optional)
     * @param excludeIngredients A comma-separated list of ingredients or ingredient types that
     * the recipes must not contain. (optional)
     * @param minLength Minimum video length in seconds. (optional)
     * @param maxLength Maximum video length in seconds. (optional)
     * @param offset The number of results to skip (between 0 and 900). (optional)
     * @param number The number of results to return (between 1 and 100). (optional)
     * @return FoodVideos
     */
    @GET(Food.SEARCH_FOOD_VIDEOS)
    fun searchFoodVideos(
        @Query("query") query: String,
        @Query("type") type: String?,
        @Query("cuisine") cuisine: String?,
        @Query("diet") diet: String?,
        @Query("includeIngredients") includeIngredients: String?,
        @Query("excludeIngredients") excludeIngredients: String?,
        @Query("minLength") minLength: Int?,
        @Query("maxLength") maxLength: Int?,
        @Query("offset") offset: Int?,
        @Query("number") number: Int?
    ): Call<FoodVideos>

    /**
     * Search Site Content
     * Search spoonacular's site content. You'll be able to find everything that you could also find
     * using the search suggestions on spoonacular.com. This is a suggest API so you can send
     * partial strings as queries.
     * @param query The query to search for. You can also use partial queries such as "spagh" to
     * already find spaghetti recipes, articles, grocery products, and other content.
     * @return FoodSiteContent
     */
    @GET(Food.SEARCH_SITE_CONTENT)
    fun searchSiteContent(@Query("query") query: String): Call<FoodSiteContent>
}
