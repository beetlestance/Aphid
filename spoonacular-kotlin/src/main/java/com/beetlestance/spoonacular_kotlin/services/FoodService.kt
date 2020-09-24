package com.beetlestance.spoonacular_kotlin.services

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
     * Use an ingredient id to get all available information about an ingredient, such as its image and supermarket aisle.
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
     * @param intolerances A comma-separated list of intolerances. All recipes returned must not contain ingredients that are not suitable for people with the intolerances entered. See a full list of supported intolerances. (optional)
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
     * @param body
     * @return Any
     */
    @POST(Food.Ingredients.MAP_INGREDIENTS_TO_GROCERY_PRODUCTS)
    fun mapIngredientsToGroceryProducts(
        @Body requestMapIngredientsToGroceryProduct: RequestMapIngredientsToGroceryProduct
    ): Any
}
