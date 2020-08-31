package com.pawan.bhattavyapar.data

import com.pawan.bhattavyapar.data.model.SignUpUser
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class SignUpDataSource {

    fun login(username: String, password: String): Result<SignUpUser> {
        try {
            // TODO: handle loggedInUser authentication
            val fakeUser = SignUpUser(java.util.UUID.randomUUID().toString(), "Jane Doe")
            return Result.Success(fakeUser)
        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}