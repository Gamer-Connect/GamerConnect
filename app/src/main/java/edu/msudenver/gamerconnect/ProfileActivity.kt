package edu.msudenver.gamerconnect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

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

        navView.setNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> Toast.makeText(applicationContext, "Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.contact -> Toast.makeText(applicationContext, "Clicked Contact", Toast.LENGTH_SHORT).show()
                R.id.game_options -> Toast.makeText(applicationContext, "Clicked Game_Options", Toast.LENGTH_SHORT).show()
                R.id.about -> Toast.makeText(applicationContext, "Clicked About", Toast.LENGTH_SHORT).show()
                R.id.login -> Toast.makeText(applicationContext, "Clicked Log In", Toast.LENGTH_SHORT).show()
                R.id.log_out -> Toast.makeText(applicationContext, "Clicked Log Out", Toast.LENGTH_SHORT).show()
                R.id.share -> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.rate_us -> Toast.makeText(applicationContext, "Clicked Rate Us", Toast.LENGTH_SHORT).show()

            }

            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}