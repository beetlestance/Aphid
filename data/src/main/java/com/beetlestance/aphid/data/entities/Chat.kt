package com.beetlestance.aphid.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.beetlestance.aphid.data.AphidTables

@Entity(
    tableName = AphidTables.CHAT_TABLE
)
data class Chat(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") override val id: Long = 0,

    @ColumnInfo(name = "type") val type: String? = null,

    @ColumnInfo(name = "image") val image: String? = null,

    @ColumnInfo(name = "message") val message: String? = null,

    @ColumnInfo(name = "time_stamp") val timeStamp: Long? = null
) : AphidEntity