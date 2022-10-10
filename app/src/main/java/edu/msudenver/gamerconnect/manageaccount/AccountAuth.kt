package edu.msudenver.gamerconnect.manageaccount

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth

/**
 * This class uses Firebase Authentication to
 *                  -Check state of login
 *                  -Create new user account
 *                  -Sign-in to existing user account
 *                  -Sends verification email
 */
class AccountAuth: Activity() {

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
            reload();
        }
    }

    private fun reload() {
        TODO("Not yet implemented")
    }

    // Sign-up new users
    private fun createAccount(email:String, password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "userCreateEmail:success")
                }
            }

    }

    companion object {
        private const val TAG = "EmailPassword"
    }


}