package com.beetlestance.aphid.feature_chat

sealed class ChatActions {
    data class SendMessage(
        val message: String
    ) : ChatActions()
}