package com.example.angel.messengerapp.ui.main

import com.example.angel.messengerapp.data.vo.ConversationListVO
import com.example.angel.messengerapp.data.vo.UserListVO

interface MainInteractor {

    interface OnConversationsLoadFinishedListener {
        fun onConversationsLoadSuccess(conversationsListVO: ConversationListVO)
        fun onConversationsLoadError()
    }

    interface OnContactsLoadFinishedListener {
        fun onContactsLoadSuccess(userListVO: UserListVO)
        fun  onContactsLoadError()
    }

    interface OnLogoutFinishedListener {
        fun onLogoutSuccess()
    }

    fun loadContacts(listener: MainInteractor.OnContactsLoadFinishedListener)

    fun loadConversations(listener: MainInteractor.OnConversationsLoadFinishedListener)

    fun logout(listener: MainInteractor.OnLogoutFinishedListener)
}