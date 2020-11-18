package com.beetlestance.aphid.data.mapper

import com.beetlestance.aphid.base.CHAT_MESSAGE_ANSWER
import com.beetlestance.aphid.data.entities.Chat
import com.beetlestance.spoonacular_kotlin.models.response.recipe.QuickAnswer
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuickAnswerToChat @Inject constructor() : Mapper<QuickAnswer, Chat> {
    override suspend fun map(from: QuickAnswer) = Chat(
        id = 0,
        image = from.image,
        type = CHAT_MESSAGE_ANSWER,
        message = from.answer,
        timeStamp = System.currentTimeMillis()
    )
}