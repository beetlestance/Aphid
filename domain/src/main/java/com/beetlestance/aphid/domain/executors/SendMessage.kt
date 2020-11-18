package com.beetlestance.aphid.domain.executors

import com.beetlestance.aphid.data.repositories.chat.ChatRepository
import com.beetlestance.aphid.domain.UseCase
import javax.inject.Inject

class SendMessage @Inject constructor(
    private val chatRepository: ChatRepository
) : UseCase<String>() {
    override suspend fun doWork(params: String) {
        chatRepository.sendMessage(params)
    }
}