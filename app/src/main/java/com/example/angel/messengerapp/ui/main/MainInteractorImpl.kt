package com.example.angel.messengerapp.ui.main

import android.annotation.SuppressLint
import android.content.Context
import com.example.angel.messengerapp.data.local.AppPreferences
import com.example.angel.messengerapp.data.remote.repository.ConversationRepository
import com.example.angel.messengerapp.data.remote.repository.ConversationRepositoryImpl
import com.example.angel.messengerapp.data.remote.repository.UserRepository
import com.example.angel.messengerapp.data.remote.repository.UserRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainInteractorImpl(val context: Context): MainInteractor {

    private val userRepository: UserRepository = UserRepositoryImpl(context)
    private val conversationRepository: ConversationRepository = ConversationRepositoryImpl(context)

    @SuppressLint("CheckResult")
    override fun loadContacts(listener: MainInteractor.OnContactsLoadFinishedListener) {
        userRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res ->
                listener.onContactsLoadSuccess(res)
            }, { error ->
                listener.onContactsLoadError()
                error.printStackTrace()
            })
    }

    @SuppressLint("CheckResult")
    override fun loadConversations(listener: MainInteractor.OnConversationsLoadFinishedListener) {
        conversationRepository.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({res ->
                listener.onConversationsLoadSuccess(res)
            }, {error ->
                listener.onConversationsLoadError()
                error.printStackTrace()
            })
    }

    override fun logout(listener: MainInteractor.OnLogoutFinishedListener) {
        val preferences: AppPreferences = AppPreferences.create(context)
        preferences.clear()
        listener.onLogoutSuccess()
    }
}