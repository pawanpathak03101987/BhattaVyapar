package com.pawan.bhattavyapar.ui.signup

/**
 * Data validation state of the Sign Up form.
 */
data class SignUpFormState(val usernameError: Int? = null,
                          val passwordError: Int? = null,
                          val isDataValid: Boolean = false)