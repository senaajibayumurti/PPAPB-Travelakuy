package com.example.ppapb_travelakuy.listener

interface AuthListener {

    fun setAccount(username: String, password: String)
    fun RegisterAccount(username: String, email: String, password: String)
    fun LoginAccount(username: String, password: String)
    fun getID(): String

    fun logout()

}