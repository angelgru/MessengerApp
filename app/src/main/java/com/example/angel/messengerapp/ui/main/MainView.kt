package com.example.angel.messengerapp.ui.main

import com.example.angel.messengerapp.ui.base.BaseView

interface MainView: BaseView {

    fun showConversationsLoadError()
    fun showContactsLoadError()
    fun showConversationsScreen()
    fun showContactsScreen()
    fun getContactsFragment(): ContactsFragment
    fun getConversationsFragment(): ConversationsFragment
    fun showNoConversations()
    fun navigateToLogin()
    fun navigateToSettings()
}