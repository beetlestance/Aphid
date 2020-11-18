package com.beetlestance.aphid.feature_chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.beetlestance.aphid.domain.invoke
import com.beetlestance.aphid.base_android.AphidViewModel
import com.beetlestance.aphid.domain.executors.SendMessage
import com.beetlestance.aphid.domain.observers.ObserveChat
import com.beetlestance.aphid.domain.watchStatus
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class ChatViewModel @ViewModelInject constructor(
    observeChat: ObserveChat,
    private val fetchMessage: SendMessage
) : AphidViewModel<ChatViewState>(ChatViewState()) {


    private val pendingActions = Channel<ChatActions>(Channel.BUFFERED)

    init {
        viewModelScope.launch {
            observeChat.observe().collectAndSetState {
                copy(messages = it)
            }
        }

        observeChat()

        viewModelScope.launch {
            pendingActions.consumeAsFlow().collect { action ->
                when (action) {
                    is ChatActions.SendMessage -> sendMessage(action)
                }
            }
        }
    }

    private fun sendMessage(action: ChatActions.SendMessage) {
        viewModelScope.launch {
            fetchMessage(action.message).watchStatus {
            }
        }
    }

    fun submitAction(action: ChatActions) {
        viewModelScope.launch {
            if (!pendingActions.isClosedForSend) {
                pendingActions.send(action)
            }
        }
    }
}