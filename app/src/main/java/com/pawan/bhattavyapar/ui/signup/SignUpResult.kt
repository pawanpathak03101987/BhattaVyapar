package com.pawan.bhattavyapar.ui.signup

/**
 * Authentication result : success (user details) or error message.
 */
data class SignUpResult(
    val success: SignUpUserView? = null,
    val error: Int? = null
)