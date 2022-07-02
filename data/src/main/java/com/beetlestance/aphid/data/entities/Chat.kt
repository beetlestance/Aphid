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
import androidx.room.PrimaryKey
import com.beetlestance.aphid.data.AphidTables

@Entity(
    tableName = AphidTables.CHAT_TABLE
)
data class Chat(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id")
    override val id: Long = 0,

    @ColumnInfo(name = "type") val type: String? = null,

    @ColumnInfo(name = "image") val image: String? = null,

    @ColumnInfo(name = "message") val message: String? = null,

    @ColumnInfo(name = "time_stamp") val timeStamp: Long? = null
) : AphidEntity
