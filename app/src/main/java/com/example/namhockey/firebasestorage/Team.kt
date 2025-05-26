package com.example.namhockey.firebasestorage

data class Team(
    val teamName: String = "",
    val managerName: String = "",
    val email: String = "",
    val phone: String = "",
    val description: String = "",
    val logoUrl: String? = null
)