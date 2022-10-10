package edu.msudenver.gamerconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlin.math.log

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        lateinit var loginBtn: Button
        lateinit var email: EditText
        lateinit var password: EditText

        loginBtn = findViewById(R.id.loginBtn)
        email = findViewById(R.id.userEmail)
        password = findViewById(R.id.userPassword)

        loginBtn.setOnClickListener{

        }

        val buttonClick = findViewById<Button>(R.id.createButton)
        buttonClick.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

    }
}