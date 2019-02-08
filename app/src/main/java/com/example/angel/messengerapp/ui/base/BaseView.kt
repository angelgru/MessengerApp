package com.example.angel.messengerapp.ui.base

import android.content.Context

interface BaseView {

    fun bindViews()
    fun getContext(): Context
}