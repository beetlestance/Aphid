package com.beetlestance.aphid.data.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.beetlestance.aphid.data.AphidTables
import com.beetlestance.aphid.data.entities.Chat
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ChatDao : EntityDao<Chat>() {

    @Transaction
    @Query(value = ALL_CONVERSATION)
    abstract fun conversationObservable(): Flow<List<Chat>>

    companion object {
        const val ALL_CONVERSATION = "SELECT * FROM ${AphidTables.CHAT_TABLE}"
    }
}