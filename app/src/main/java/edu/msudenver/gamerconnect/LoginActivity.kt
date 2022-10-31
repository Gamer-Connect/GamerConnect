package edu.msudenver.gamerconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.msudenver.gamerconnect.MainDashboard.DashboardScreen


class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"
    private lateinit var auth: FirebaseAuth
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var mAuthListener: AuthStateListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn: Button = findViewById(R.id.loginBtn)
        val emailEdtTxt: EditText = findViewById(R.id.userEmail)
        val passwordEdtTxt: EditText = findViewById(R.id.userPassword)

        checkLog()


        loginBtn.setOnClickListener {
            email = emailEdtTxt.text.toString()
            password = passwordEdtTxt.text.toString()
            validateLogin(email, password)
        }

        /** Create Account */
        val buttonClick = findViewById<Button>(R.id.createButton)
        buttonClick.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }

    private fun checkLog() {
        val user = Firebase.auth.currentUser
        if(user != null){
            updateUI(user)
        }
        return
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
        Log.d(TAG, "email:$email password:$password being sent to auth")
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Account sign-in successfully")
                    val user = auth.currentUser
                    updateUI(user)
                    finish()
                }
                if(!task.isSuccessful) {
                    try {
                        throw task.exception!!
                    } catch (e: FirebaseAuthInvalidUserException) {
                        //statusTextView.setError("Invalid Emaild Id")
                        //statusTextView.requestFocus()
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Log.d(TAG, "email :$email")
                        //statusTextView.setError("Invalid Password")
                        //statusTextView.requestFocus()
                    } catch (e: FirebaseNetworkException) {
                        Toast.makeText(applicationContext, "Failed sign-in no network", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Log.e(TAG, e.message!!)
                    }
                    Log.w(TAG, "signInWithEmail:failed", task.exception)
                    Toast.makeText(applicationContext, "Sign-in failed", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this, DashboardScreen::class.java)
        startActivity(intent)
    }
}