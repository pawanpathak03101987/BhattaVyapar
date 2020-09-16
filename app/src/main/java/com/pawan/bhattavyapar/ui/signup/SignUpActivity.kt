package com.pawan.bhattavyapar.ui.signup


import android.app.Activity
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.pawan.bhattavyapar.MyApplication

import com.pawan.bhattavyapar.R
import com.pawan.bhattavyapar.ui.login.LoginActivity
import com.pawan.bhattavyapar.ui.signup.SignUpUserView
import com.pawan.bhattavyapar.ui.signup.SignUpViewModel
import com.pawan.bhattavyapar.ui.login.LoginViewModelFactory
import com.pawan.bhattavyapar.ui.mainpage.MainActivity
import com.pawan.bhattavyapar.utils.Others
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.*

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_sign_up)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.btnLogin)

        val btnLogin = findViewById(R.id.btnLogin) as Button
        val btnSignup = findViewById(R.id.btnSignup) as Button

        btnLogin.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        })
        button3.setOnClickListener(View.OnClickListener {
           checkforNewUser()
        })




        //val loading = findViewById<ProgressBar>(R.id.loading)

        /*signUpViewModel = ViewModelProviders.of(this, SignUpViewModelFactory())
            .get(SignUpViewModel::class.java)

        signUpViewModel.signUpFormState.observe(this@SignUpActivity, Observer {
            val signUpFormState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = signUpFormState.isDataValid

            if (signUpFormState.usernameError != null) {
                username.error = getString(signUpFormState.usernameError)
            }
            if (signUpFormState.passwordError != null) {
                password.error = getString(signUpFormState.passwordError)
            }
        })

        signUpViewModel.signUpResult.observe(this@SignUpActivity, Observer {
            val signUpResult = it ?: return@Observer

            //loading.visibility = View.GONE
            if (signUpResult.error != null) {
                showLoginFailed(signUpResult.error)
            }
            if (signUpResult.success != null) {
                updateUiWithUser(signUpResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })*/

        /*username.afterTextChanged {
            signUpViewModel.signUpDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }
*/
        /*password.apply {
            afterTextChanged {
                signUpViewModel.signUpDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        signUpViewModel.signUp(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                //loading.visibility = View.VISIBLE
                signUpViewModel.signUp(username.text.toString(), password.text.toString())
            }
        }*/
    }

    private fun checkforNewUser() {

        MyApplication.getFireStoreObj().collection("MstUsers").whereEqualTo("PrimaryMobileNo", editMobileNumber.text.toString()).get().addOnSuccessListener {  result ->

            if (editMobileNumber.text.toString().length==0)
            {
                Toast.makeText(this,"Enter mobile number",Toast.LENGTH_LONG).show()
            }else if (editPassword.text.toString().length==0)
            {
                Toast.makeText(this,"Enter password",Toast.LENGTH_LONG).show()
            }else if (editConfirmPassword.text.toString().length==0)
            {
                Toast.makeText(this,"Enter confirm password",Toast.LENGTH_LONG).show()
            }else if(!editPassword.text.toString().equals(editConfirmPassword.text.toString()))
            {
                Toast.makeText(this,"Password is not matched",Toast.LENGTH_LONG).show()
            } else  if (result.size()>0)
            {
                Toast.makeText(this,"This mobile is already exits",Toast.LENGTH_LONG).show()
            }
            else{
              val docUid=UUID.randomUUID().toString()
                val user = hashMapOf(
                    "CountryID" to 3,
                    "CreatedOn" to Others.getCurrentDate(),
                    "FirstName" to "",
                    "FullName" to "",
                    "IsActive" to true,
                    "IsDeleted" to false,
                    "LastName" to "",
                    "Password" to editPassword.text.toString(),
                    "PrimaryMobileNo" to editMobileNumber.text.toString(),
                    "UserRoleID" to 1,
                    "UserUID" to docUid
                )
                Log.e("User", "user is new")
// Add a new document with a generated ID
                  MyApplication.getFireStoreObj().collection("MstUsers").document(docUid)
                       .set(user) .addOnSuccessListener {
                          val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                          startActivity(intent)
                      }

                      .addOnFailureListener { e -> Log.w("Sign up", "Error writing document", e) }

            }




        }
            .addOnFailureListener { exception ->
                Log.e("User", "Error getting documents.", exception)
                //  Log.e("User", "${document.id} => ${document.data}")
            }













    }

    private fun updateUiWithUser(model: SignUpUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })


}