package com.example.angel.messengerapp.data.vo

data class ConversationVO(
    val conversationId: Long,
    val secondPartyUsername: String,
    val messages: ArrayList<MessageVO>
)