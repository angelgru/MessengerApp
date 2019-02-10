package com.example.angel.messengerapp.ui.main

import android.util.Log
import com.example.angel.messengerapp.data.vo.ConversationListVO
import com.example.angel.messengerapp.data.vo.UserListVO

class MainPresenterImpl(val view: MainView): MainPresenter,
    MainInteractor.OnConversationsLoadFinishedListener,
    MainInteractor.OnContactsLoadFinishedListener,
    MainInteractor.OnLogoutFinishedListener {

    private val interactor: MainInteractor = MainInteractorImpl(view.getContext())

    override fun loadConversations() {
        interactor.loadConversations(this)
    }

    override fun loadContacts() {
        interactor.loadContacts(this)
    }

    override fun executeLogout() {
        interactor.logout(this)
    }

    override fun onConversationsLoadSuccess(conversationsListVO: ConversationListVO) {
        if(!conversationsListVO.conversations.isEmpty()) {
            val conversationsFragment = view.getConversationsFragment()
            val conversations = conversationsFragment.conversations
            val adapter = conversationsFragment.conversationsAdapter

//            Clearing the conversations list and notifying the adapter about the change
            conversations.clear()
            adapter.notifyDataSetChanged()

//            Adding the conversations to the list and notifying the adapter to update its view
            conversationsListVO.conversations.forEach { contact ->
                conversations.add(contact)
                adapter.notifyItemInserted(conversations.size - 1)
            }

        } else{
            view.showNoConversations()
        }
    }

    override fun onConversationsLoadError() {
        view.showConversationsLoadError()
    }

    override fun onContactsLoadSuccess(userListVO: UserListVO) {
        if(!userListVO.users.isEmpty()) {
            Log.e("ANGEL", "Contacts not empty")
            val contactsFragment = view.getContactsFragment()
            val contacts = contactsFragment.contacts
            val adapter = contactsFragment.contactsAdapter

            contacts.clear()
            adapter.notifyDataSetChanged()
            userListVO.users.forEach { contact ->
                contacts.add(contact)
                adapter.notifyItemInserted(contacts.size - 1)
            }
        }
    }

    override fun onContactsLoadError() {
        view.showContactsLoadError()
    }

    override fun onLogoutSuccess() {
        view.navigateToLogin()
    }
}