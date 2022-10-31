package edu.msudenver.gamerconnect

import android.app.GameManager
import android.content.Context.GAME_SERVICE
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.core.Context

class HomePageActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_home)

//        // Only call this for Android 12 and higher devices
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            // Get GameManager from SystemService
//            val gameManager: GameManager? = context.getSystemService(Context.GAME_SERVICE) as GameManager?
//
//            // Returns the selected GameMode
//            val gameMode = gameManager?.gameMode
//        }

    }
}