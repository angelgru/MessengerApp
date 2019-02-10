package com.example.angel.messengerapp.ui.chat

import com.example.angel.messengerapp.data.vo.ConversationVO

interface ChatInteractor {

    interface OnMessageSendFinishedListener {
        fun onSendSuccess()

        fun onSendError()
    }

    interface onMessageLoadFinishedListener {
        fun onLoadSuccess(conversationVO: ConversationVO)
        fun onLoadError()
    }

    fun sendMessage(recipientId: Long, message: String, listener: OnMessageSendFinishedListener)

    fun loadMessages(conversationId: Long, listener: onMessageLoadFinishedListener)
}