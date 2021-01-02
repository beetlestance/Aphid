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
package com.beetlestance.aphid.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.beetlestance.aphid.data.AphidTables
import com.beetlestance.aphid.data.entities.Chat
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ChatDao : EntityDao<Chat>() {

    @Transaction
    @Query(value = ALL_CONVERSATION)
    abstract fun conversationObservable(): Flow<List<Chat>>

    companion object {
        private const val ALL_CONVERSATION = "SELECT * FROM ${AphidTables.CHAT_TABLE}"
    }
}
