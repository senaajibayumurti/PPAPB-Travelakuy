package com.example.ppapb_travelakuy.db.model

data class Akun(
    var id: String = "",
    val username: String,
    val email: String,
    val password: String,
    val isAdmin: Boolean = false,

)