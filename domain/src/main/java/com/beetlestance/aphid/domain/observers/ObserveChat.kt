package com.beetlestance.aphid.domain.observers

import com.beetlestance.aphid.data.entities.Chat
import com.beetlestance.aphid.data.repositories.chat.ChatRepository
import com.beetlestance.aphid.domain.ObserveUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveChat @Inject constructor(
    private val chatRepository: ChatRepository
) : ObserveUseCase<Unit, List<Chat>>() {
    override fun createObservable(params: Unit): Flow<List<Chat>> {
        return chatRepository.observeChat()
    }
}