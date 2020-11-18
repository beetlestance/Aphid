package com.beetlestance.aphid.data.repositories.chat

import com.beetlestance.aphid.base.result.dataOrThrowException
import javax.inject.Inject

class ChatRepository @Inject constructor(
    private val chatDataSource: ChatDataSource,
    private val chatStore: ChatStore
) {
    fun observeChat() = chatStore.observeChat()

    suspend fun sendMessage(message: String) {
        chatStore.insertMessage(message)
        chatDataSource.getChatReply(message).dataOrThrowException {
            chatStore.insertChat(this)
        }
    }
}