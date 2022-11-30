package edu.msudenver.gamerconnect

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileActivity : AppCompatActivity() {

    lateinit var toggle : ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val homeIntent = Intent(this, HomePageActivity::class.java )
        val profileIntent = Intent(this, ProfileActivity::class.java )

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> startActivity(homeIntent)
                R.id.contact -> Toast.makeText(applicationContext, "Clicked Contact", Toast.LENGTH_SHORT).show()
                R.id.game_options -> Toast.makeText(applicationContext, "Clicked Game_Options", Toast.LENGTH_SHORT).show()
                R.id.about -> startActivity(profileIntent)
                R.id.login -> Toast.makeText(applicationContext, "Clicked Log In", Toast.LENGTH_SHORT).show()
                R.id.log_out -> signOut()
                R.id.share -> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.rate_us -> Toast.makeText(applicationContext, "Clicked Rate Us", Toast.LENGTH_SHORT).show()

            }

            true
        }


    }

    private fun signOut() {
        Firebase.auth.signOut()
        val logOutIntent = Intent(this, LoginActivity::class.java )
        startActivity(logOutIntent)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}