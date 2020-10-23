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
data class Recipe(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long = 0,

    @ColumnInfo(name = "recipe_id") val recipeId: Int? = 0,

    @ColumnInfo(name = "title") val title: String? = null,

    @ColumnInfo(name = "rating") val rating: Double? = null,

    @ColumnInfo(name = "serving") val serving: Int? = null,

    @ColumnInfo(name = "ready_in_minutes") val readyInMinutes: Int? = null,

    @ColumnInfo(name = "calories") val calories: Long? = null,

    @ColumnInfo(name = "image_url") val imageUrl: String? = null,

    @ColumnInfo(name = "image_type") val imageType: String? = null,

    @ColumnInfo(name = "is_favourite") val isFavourite: Boolean? = false

) : AphidEntity
