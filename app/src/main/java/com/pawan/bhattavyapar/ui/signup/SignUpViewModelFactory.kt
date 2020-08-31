package com.pawan.bhattavyapar.ui.signup

import com.pawan.bhattavyapar.ui.signup.SignUpViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pawan.bhattavyapar.data.SignUpDataSource
import com.pawan.bhattavyapar.data.SignUpRepository

/**
 * ViewModel provider factory to instantiate SignUpViewModel.
 * Required given SignUpViewModel has a non-empty constructor
 */
class SignUpViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(
                signUpRepository  = SignUpRepository(
                    dataSource = SignUpDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}