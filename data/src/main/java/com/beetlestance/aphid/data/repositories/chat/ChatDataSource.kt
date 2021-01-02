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
package com.beetlestance.aphid.data.repositories.chat

import com.beetlestance.aphid.base.extensions.executeWithRetry
import com.beetlestance.aphid.base.extensions.toResult
import com.beetlestance.aphid.base.result.Result
import com.beetlestance.aphid.data.entities.Chat
import com.beetlestance.aphid.data.mapper.QuickAnswerToChat
import com.beetlestance.spoonacular_kotlin.services.RecipesService
import javax.inject.Inject

class ChatDataSource @Inject constructor(
    private val recipesService: RecipesService,
    private val quickAnswerToChat: QuickAnswerToChat
) {
    suspend fun getChatReply(question: String): Result<Chat> {
        return recipesService.quickAnswer(question)
            .executeWithRetry()
            .toResult(quickAnswerToChat::map)
    }
}
