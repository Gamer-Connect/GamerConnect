package edu.msudenver.gamerconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import edu.msudenver.gamerconnect.manageaccount.AccountAuth
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private val TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var loginBtn: Button = findViewById(R.id.loginBtn)
        var emailEdtTxt: EditText = findViewById(R.id.userEmail)
        var passwordEdtTxt: EditText = findViewById(R.id.userPassword)
        lateinit var email:String
        lateinit var password:String

        loginBtn.setOnClickListener{
            email = emailEdtTxt.text.toString()
            password = passwordEdtTxt.text.toString()

            if (!email.contains("@")) {
                Toast.makeText(applicationContext,
                    "Please enter a valid email",
                    Toast.LENGTH_SHORT)
                    .show()
            }

        }

        val buttonClick = findViewById<Button>(R.id.createButton)
        buttonClick.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}