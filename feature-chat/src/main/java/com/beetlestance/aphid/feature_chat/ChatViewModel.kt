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
package com.beetlestance.aphid.feature_chat

import androidx.lifecycle.viewModelScope
import com.beetlestance.aphid.base_android.ReduxStateViewModel
import com.beetlestance.aphid.domain.executors.SendMessage
import com.beetlestance.aphid.domain.invoke
import com.beetlestance.aphid.domain.observers.ObserveChat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    observeChat: ObserveChat,
    private val sendChatMessage: SendMessage
) : ReduxStateViewModel<ChatViewState>(ChatViewState()) {

    init {
        viewModelScope.launch {
            observeChat.observe().collectAndSetState {
                copy(messages = it)
            }
        }

        observeChat()
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            sendChatMessage(message)
        }
    }
}
