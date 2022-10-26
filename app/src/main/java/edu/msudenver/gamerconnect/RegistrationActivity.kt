package edu.msudenver.gamerconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.msudenver.gamerconnect.manageaccount.AccountAuth

class RegistrationActivity : AppCompatActivity() {
    private val TAG = "RegistrationActivity"
    private lateinit var auth: FirebaseAuth
    private lateinit var fullName:String
    private lateinit var userName:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var checkPassword:String

    private val fullNameTxt: EditText = findViewById(R.id.createName)
    private val userNameTxt: EditText = findViewById(R.id.createUserName)
    private val emailTxt: EditText = findViewById(R.id.createEmail)
    private val passwordTxt: EditText = findViewById(R.id.createPassword)
    private val checkPasswordTxt: EditText = findViewById(R.id.createPasswordCheck)
    private lateinit var err: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)


        val createBtn: Button = findViewById(R.id.createAccount)

        createBtn.setOnClickListener {
            fullName = fullNameTxt.text.toString()
            userName = userNameTxt.text.toString()
            email = emailTxt.text.toString()
            password = passwordTxt.text.toString()
            checkPassword = checkPasswordTxt.text.toString()


            if (validName(fullName) && validUser(userName) && validEmail(email) && validPassword(password, checkPassword))
                createUser(email, password)

        }

    }


    private fun validName(fullName: String): Boolean {
        err = findViewById(R.id.errName)

        if (fullName.isEmpty()) {
            err.visibility = View.VISIBLE
            err.text = "Please enter name"
            err.error
            return false
        }

        return true
    }

    /** TODO create validation to check for duplication of user name within database */
    private fun validUser(userName: String): Boolean {
        err = findViewById(R.id.errUser)

        if (userName.isEmpty()) {
            err.visibility = View.VISIBLE
            err.text = "Please enter user name"
            err.error
            return false
        }
        return true
    }


    private fun validEmail(email: String): Boolean {
        err = findViewById(R.id.errEmail)

        if (email.isEmpty()) {
            err.visibility = View.VISIBLE
            err.text = "Please enter email"
            err.error
            return false
        }

        /** Checks format for <Local-part>, @, <domain name>, <reserved domain> */
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            err.visibility = View.VISIBLE
            err.text = "Please enter a valid email"
            err.error
            return false
        }
        return true
    }


    private fun validPassword(password: String, checkPassword: String): Boolean {
            err = findViewById(R.id.errPassword)

        if (checkPassword.length < 6) {
            err.visibility = View.VISIBLE
            err.text = "Passwords must be at least 6 characters"
            err.error
            passwordTxt.text.clear()
            checkPasswordTxt.text.clear()
            return false
        }

            if (!password.equals(checkPassword)) {
                err.visibility = View.VISIBLE
                err.text = "Passwords do not match"
                err.error
                passwordTxt.text.clear()
                checkPasswordTxt.text.clear()

            }
        return true
    }

    private fun createUser(email: String, password: String) {
        Log.d(TAG, "email:$email password:$password being sent to create")
            auth = Firebase.auth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Log.d(AccountAuth.TAG, "Account created successfully")
                        val user = auth.currentUser
                        updateUI(user)
                    }
                    else {
                        Log.w(AccountAuth.TAG, "userCreateAccount:failure", task.exception)
                        Toast.makeText(baseContext, "Account already exists or couldn't be created.",
                            Toast.LENGTH_SHORT).show()
                    }
                }


    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


}





















