package com.beetlestance.aphid.feature_chat

import com.beetlestance.aphid.data.entities.Chat

data class ChatViewState(
    var messages: List<Chat> = emptyList()
)