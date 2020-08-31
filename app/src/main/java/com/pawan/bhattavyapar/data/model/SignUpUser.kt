package com.pawan.bhattavyapar.data.model

/**
 * Data class that captures user information for logged in users retrieved from SignUpRepository
 */
data class SignUpUser(
    val userId: String,
    val displayName: String
)