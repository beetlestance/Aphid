package com.beetlestance.aphid.data.repositories.chat

import com.beetlestance.aphid.base.CHAT_MESSAGE_QUESTION
import com.beetlestance.aphid.data.daos.ChatDao
import com.beetlestance.aphid.data.entities.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatStore @Inject constructor(
    private val chatDao: ChatDao
) {
    fun observeChat(): Flow<List<Chat>> = chatDao.conversationObservable()

    suspend fun insertMessage(message: String) {
        val chat = Chat(
            type = CHAT_MESSAGE_QUESTION,
            message = message
        )
        chatDao.insert(chat)
    }

    suspend fun insertChat(chat: Chat) {
        chatDao.insert(chat)
    }
}