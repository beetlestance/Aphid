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