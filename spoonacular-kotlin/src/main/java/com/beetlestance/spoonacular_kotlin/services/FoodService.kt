package com.beetlestance.spoonacular_kotlin.services

import com.beetlestance.spoonacular_kotlin.models.request.RequestClassifyGroceryProduct
import com.beetlestance.spoonacular_kotlin.models.request.RequestMapIngredientsToGroceryProduct
import com.beetlestance.spoonacular_kotlin.services.endpoints.Food
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.math.BigDecimal

interface FoodService {

    /**
     * Get Ingredient Information
     * Use an ingredient id to get all available information about an ingredient, such as its
     * image and supermarket aisle.
     * @param id The ingredient id.
     * @param amount The amount of this ingredient. (optional)
     * @param unit The unit for the given amount. (optional)
     * @return Any
     */
    @GET(Food.Ingredients.ById.GET_INGREDIENT_INFORMATION)
    fun getIngredientInformation(
        @Path("id") id: BigDecimal,
        @Query("amount") amount: BigDecimal? = null,
        @Query("unit") unit: String? = null
    ): Any

    /**
     * Get Ingredient Substitutes by ID
     * Search for substitutes for a given ingredient.
     * @param id The id of the ingredient you want substitutes for.
     * @return Any
     */
    @GET(Food.Ingredients.ById.GET_INGREDIENT_SUBSTITUTES)
    fun getIngredientSubstitutesByID(@Path("id") id: BigDecimal): Any

    /**
     * Autocomplete Ingredient Search
     * Autocomplete the entry of an ingredient.
     * @param query The partial or full ingredient name.
     * @param number The number of results to return (between 1 and 100). (optional)
     * @param metaInformation Whether to return more meta information about the ingredients. (optional)
     * @param intolerances A comma-separated list of intolerances. All recipes returned must
     * not contain ingredients that are not suitable for people with the intolerances entered.
     * See a full list of supported intolerances. (optional)
     * @return Any
     */
    @GET(Food.Ingredients.AUTOCOMPLETE_INGREDIENT_SEARCH)
    fun autocompleteIngredientSearch(
        @Query("query") query: String,
        @Query("number") number: BigDecimal? = null,
        @Query("metaInformation") metaInformation: Boolean? = null,
        @Query("intolerances") intolerances: Boolean? = null
    ): Any

    /**
     * Get Ingredient Substitutes
     * Search for substitutes for a given ingredient.
     * @param ingredientName The name of the ingredient you want to replace.
     * @return Any
     */
    @GET(Food.Ingredients.GET_INGREDIENT_SUBSTITUTES)
    fun getIngredientSubstitutes(@Query("ingredientName") ingredientName: String): Any

    /**
     * Map Ingredients to Grocery Products
     * Map a set of ingredients to products you can buy in the grocery store.
     * @param requestMapIngredientsToGroceryProduct
     * @return Any
     */
    @POST(Food.Ingredients.MAP_INGREDIENTS_TO_GROCERY_PRODUCTS)
    fun mapIngredientsToGroceryProducts(
        @Body requestMapIngredientsToGroceryProduct: RequestMapIngredientsToGroceryProduct
    ): Any

    /**
     * Get Product Information
     * Use a product id to get full information about a product, such as ingredients, nutrition,
     * etc. The nutritional information is per serving.
     * @param id The id of the packaged food.
     * @return Any
     */
    @GET(Food.Products.ById.GET_PRODUCT_INFORMATION)
    fun getProductInformation(@Query("id") id: BigDecimal): Any

    /**
     * Visualize Product Nutrition by ID
     * Visualize a product's nutritional information as HTML including CSS.
     * @param id The id of the product.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Food.Products.ById.VISUALIZE_PRODUCT_NUTRITION)
    fun visualizeProductNutritionByID(
        @Query("id") id: BigDecimal,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): String

    /**
     * Get Comparable Products
     * Find comparable products to the given one.
     * @param upc The UPC of the product for which you want to find comparable products.
     * @return Any
     */
    @GET(Food.Products.ByUPC.GET_COMPARABLE_PRODUCTS)
    fun getComparableProducts(@Path("upc") upc: BigDecimal): Any

    /**
     * Search Grocery Products by UPC
     * Get information about a packaged food using its UPC.
     * @param upc The product's UPC.
     * @return Any
     */
    @GET(Food.Products.ByUPC.SEARCH_GROCERY_PRODUCTS)
    fun searchGroceryProductsByUPC(@Path("upc") upc: BigDecimal): Any

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
     * @return Any
     */
    @GET(Food.Products.SEARCH_GROCERY_PRODUCTS)
    fun searchGroceryProducts(
        @Query("query") query: String,
        @Query("minCalories") minCalories: BigDecimal? = null,
        @Query("maxCalories") maxCalories: BigDecimal? = null,
        @Query("minCarbs") minCarbs: BigDecimal? = null,
        @Query("maxCarbs") maxCarbs: BigDecimal? = null,
        @Query("minProtein") minProtein: BigDecimal? = null,
        @Query("maxProtein") maxProtein: BigDecimal? = null,
        @Query("minFat") minFat: BigDecimal? = null,
        @Query("maxFat") maxFat: BigDecimal? = null,
        @Query("offset") offset: BigDecimal? = null,
        @Query("number") number: BigDecimal? = null
    ): Any

    /**
     * Classify Grocery Product
     * This endpoint allows you to match a packaged food to a basic category, e.g. a specific brand
     * of milk to the category milk.
     * @param requestClassifyGroceryProduct A json object containing the product title.
     * @param locale The display name of the returned category, supported is en_US
     * (for American English) and en_GB (for British English). (optional)
     * @return Any
     */
    @POST(Food.Products.CLASSIFY_GROCERY_PRODUCT)
    fun classifyGroceryProduct(
        @Body requestClassifyGroceryProduct: RequestClassifyGroceryProduct,
        @Query("locale") locale: String? = null
    ): Any

    /**
     * Classify Grocery Product Bulk
     * Provide a set of product jsons, get back classified products.
     * @param requestClassifyGroceryProduct
     * @param locale The display name of the returned category, supported is en_US
     * (for American English) and en_GB (for British English). (optional)
     * @return Any
     */
    @POST(Food.Products.CLASSIFY_GROCERY_PRODUCT_BULK)
    fun classifyGroceryProductBulk(
        @Body requestClassifyGroceryProduct: RequestClassifyGroceryProduct,
        @Query("locale") locale: String? = null
    ): Any

    /**
     * Get Menu Item Information
     * Use a menu item id to get all available information about a menu item, such as nutrition.
     * @param id The menu item id.
     * @return Any
     */
    @GET(Food.MenuItems.ById.GET_MENU_ITEM_INFORMATION)
    fun getMenuItemInformation(@Query("id") id: BigDecimal): Any

    /**
     * Visualize Menu Item Nutrition by ID
     * Visualize a menu item's nutritional information as HTML including CSS.
     * @param id The menu item id.
     * @param defaultCss Whether the default CSS should be added to the response. (optional)
     * @return String
     */
    @GET(Food.MenuItems.ById.VISUALIZE_MENU_ITEM_NUTRITION)
    fun visualizeMenuItemNutritionByID(
        @Query("id") id: BigDecimal,
        @Query("defaultCss") defaultCss: Boolean? = null
    ): String

    /**
     * Autocomplete Menu Item Search
     * Generate suggestions for menu items based on a (partial) query. The matches will be found by
     * looking in the title only.
     * @param query The (partial) search query.
     * @param number The number of results to return (between 1 and 25). (optional)
     * @return Any
     */
    @GET(Food.MenuItems.AUTOCOMPLETE_MENU_ITEM_SEARCH)
    fun autocompleteMenuItemSearch(
        @Query("query") query: String,
        @Query("number") number: BigDecimal? = null
    ): Any

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
     * @return Any
     */
    @GET(Food.MenuItems.SEARCH_MENU_ITEMS)
    fun searchMenuItems(
        @Query("query") query: String,
        @Query("minCalories") minCalories: BigDecimal? = null,
        @Query("maxCalories") maxCalories: BigDecimal? = null,
        @Query("minCarbs") minCarbs: BigDecimal? = null,
        @Query("maxCarbs") maxCarbs: BigDecimal? = null,
        @Query("minProtein") minProtein: BigDecimal? = null,
        @Query("maxProtein") maxProtein: BigDecimal? = null,
        @Query("minFat") minFat: BigDecimal? = null,
        @Query("maxFat") maxFat: BigDecimal? = null,
        @Query("offset") offset: BigDecimal? = null,
        @Query("number") number: BigDecimal? = null
    ): Any
}
