package edu.msudenver.gamerconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)




        fun getCurrentUser() {
            val user = Firebase.auth.currentUser
            user?.let {
                val name = user.displayName
                val email = user.email
                val photoUrl = user.photoUrl

                val emailVerified = user.isEmailVerified

                // Unique Firebase user ID
                // Use FirebaseUser.getToken() to confirm with Firebase
                val userId = user.uid
            }

        }


        val logoutButton = findViewById<Button>(R.id.logoutBtn)
        logoutButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}