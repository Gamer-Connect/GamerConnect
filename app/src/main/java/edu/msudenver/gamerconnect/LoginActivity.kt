package edu.msudenver.gamerconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.msudenver.gamerconnect.manageaccount.AccountAuth

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"
    private lateinit var auth: FirebaseAuth
    private lateinit var email:String
    private lateinit var password:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn: Button = findViewById(R.id.loginBtn)
        var emailEdtTxt: EditText = findViewById(R.id.userEmail)
        var passwordEdtTxt: EditText = findViewById(R.id.userPassword)


        loginBtn.setOnClickListener {
            email = emailEdtTxt.text.toString()
            password = passwordEdtTxt.text.toString()
            validateLogin(email, password)
        }

        val buttonClick = findViewById<Button>(R.id.createButton)
        buttonClick.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }

    private fun validateLogin(email: String, password: String) {
        if (!email.contains("@") || email.isNullOrBlank()) {
            Toast.makeText(applicationContext,
                "Please enter a valid email",
                Toast.LENGTH_SHORT)
                .show()
            return
        }
        if (password.isNullOrBlank()) {
            return
        }

        authLogin(email, password)
    }

    private fun authLogin(email: String, password:String) {
        Log.d(TAG, "email:$email password: $password being sent to auth")
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Account sign-in successfully")
                    val user = auth.currentUser
                    updateUI(user)
                    finish()
                }
                else {
                    Log.w(TAG, "Failure to sign-in", task.exception)
                    Toast.makeText(baseContext, "Password is incorrect or email is wrong.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }

    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
}