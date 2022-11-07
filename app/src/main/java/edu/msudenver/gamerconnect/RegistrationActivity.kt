package edu.msudenver.gamerconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.auth.api.identity.SignInPassword
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.msudenver.gamerconnect.MainDashboard.DashboardScreen
import edu.msudenver.gamerconnect.manageaccount.AccountAuth

class RegistrationActivity : AppCompatActivity() {
    private val TAG = "RegistrationActivity"
    private lateinit var auth: FirebaseAuth
    private lateinit var fullName:String
    private lateinit var userName:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var checkPassword:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        val fullNameTxt: EditText = findViewById(R.id.createName)
        val userNameTxt: EditText = findViewById(R.id.createUserName)
        val emailTxt: EditText = findViewById(R.id.createEmail)
        val passwordTxt: EditText = findViewById(R.id.createPassword)
        val checkPasswordTxt: EditText = findViewById(R.id.createPasswordCheck)

        val createBtn: Button = findViewById(R.id.createAccount)

        createBtn.setOnClickListener {
            fullName = fullNameTxt.text.toString()
            userName = userNameTxt.text.toString()
            email = emailTxt.text.toString()
            password = passwordTxt.text.toString()
            checkPassword = checkPasswordTxt.text.toString()


            //validateInfo(fullName,userName)
            //validEmail(email)
            //validPassword(password, checkPassword)

            createUser(email, password)



        }

    }



    private fun validateInfo(name:String, user:String) {
        if (name.isEmpty()) {
            //set err text
        }
        if (user.isEmpty()) {
            //set err txt
        }

    }
    private fun validEmail(email: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //err msg
        }
    }
    private fun validPassword(password: String, checkPassword: String) {
        TODO("Not yet implemented")
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
                    //updateUI(user)
                }
            }


    }

    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


}