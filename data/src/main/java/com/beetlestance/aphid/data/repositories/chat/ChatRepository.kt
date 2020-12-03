package com.beetlestance.aphid.data.repositories.chat

import com.beetlestance.aphid.base.result.dataOrThrowException
import com.beetlestance.aphid.data.entities.Chat
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val chatDataSource: ChatDataSource,
    private val chatStore: ChatStore
) {
    fun observeChat(): Flow<List<Chat>> = chatStore.observeChat()

    suspend fun sendMessage(message: String) {
        chatStore.insertMessage(message)
        chatDataSource.getChatReply(message).dataOrThrowException {
            chatStore.insertChat(this)
        }
    }
}