package com.example.angel.messengerapp.ui.chat

import com.example.angel.messengerapp.ui.base.BaseView
import com.example.angel.messengerapp.utils.Message
import com.stfalcon.chatkit.messages.MessagesListAdapter

interface ChatView: BaseView {

    interface ChatAdapter {
        fun navigateToChat(recipientName: String, recipientId: Long,
                           conversationId: Long? = null)
    }
    fun showConversationLoadError()
    fun showMessageSendError()
    fun getMessageListAdapter(): MessagesListAdapter<Message>
}