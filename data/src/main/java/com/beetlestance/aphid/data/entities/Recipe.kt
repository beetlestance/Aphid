/*
 * Copyright 2021 BeetleStance
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
package com.beetlestance.aphid.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.beetlestance.aphid.data.AphidTables

@Entity(
    tableName = AphidTables.RECIPE_TABLE,
    indices = [
        Index(value = ["recipe_id"], unique = true)
    ]
)
data class Recipe(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    override val id: Long = 0,

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
