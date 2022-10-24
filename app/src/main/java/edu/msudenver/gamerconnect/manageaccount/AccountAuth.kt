package edu.msudenver.gamerconnect.manageaccount

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import edu.msudenver.gamerconnect.ProfileActivity
import edu.msudenver.gamerconnect.RegistrationActivity

/**
 * This class uses Firebase Authentication to
 *                  -Check state of login
 *                  -Create new user account
 *                  -Sign-in to existing user account
 *                  -Sends verification email
 */
open class AccountAuth: Activity() {

    private lateinit var auth: FirebaseAuth

    // Check Auth state
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null) {
            reload()
        }
    }

    private fun reload() {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    /** Sign-up new users */
    fun createAccount(email:String, password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Account created successfully")
                    val user = auth.currentUser
                    updateUI(user)
                }
                else {
                    Log.w(TAG, "userCreateAccount:failure", task.exception)
                    Toast.makeText(baseContext, "Account already exists or couldn't be created.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(user)
                }
            }

    }

    /** Sign-in existing users */
    fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "Account sign-in successfully")
                    val user = auth.currentUser
                    updateUI(user)
                }
                else {
                    Log.w(TAG, "Failure to sign-in", task.exception)
                    Toast.makeText(baseContext, "Password is incorrect or email is wrong.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    /** Get and verify account information */

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

    private fun updateUI(user: FirebaseUser?) {
        reload()

    }

    companion object {
        const val TAG = "AccountAuth"
    }


}