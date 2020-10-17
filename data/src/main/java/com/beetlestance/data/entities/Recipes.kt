package com.beetlestance.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.beetlestance.data.AphidTables

@Entity(
    tableName = AphidTables.RECIPE_TABLE,
    indices = [
        Index(value = ["recipe_id"], unique = true)
    ]
)
data class Recipes(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long = 0,

    @ColumnInfo(name = "recipe_id") val recipeId: Int? = 0,

    @ColumnInfo(name = "name") val name: String? = null,

    @ColumnInfo(name = "rating") val rating: Int? = null,

    @ColumnInfo(name = "serving") val serving: Int? = null,

    @ColumnInfo(name = "time") val time: Long? = null,

    @ColumnInfo(name = "calories") val calories: Long? = null,

    @ColumnInfo(name = "image") val image: String? = null

) : AphidEntity
