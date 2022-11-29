package edu.msudenver.gamerconnect


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import com.api.igdb.apicalypse.APICalypse
import com.api.igdb.apicalypse.Sort
import com.api.igdb.exceptions.RequestException
import com.api.igdb.request.IGDBWrapper
import com.api.igdb.request.games
import com.google.android.material.navigation.NavigationView
import proto.Game


class HomePageActivity : AppCompatActivity(){
    lateinit var recyclerView: RecyclerView

    private inner class ItemHolder(view: View): RecyclerView.ViewHolder(view) {

    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        val logout = findViewById<Button>(R.id.logout)
//        logout.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//
//        val profileButton = findViewById<Button>(R.id.profile)
//        profileButton.setOnClickListener {
//            val intent = Intent(this, ProfileActivity::class.java)
//            startActivity(intent)
//        }
//
//        return true
//    }

    lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_home)

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

        IGDBWrapper.setCredentials(Companion.CLIENT_ID, Companion.BEARER_TOKEN)

        val IGDB_API = APICalypse().fields("*").sort("release_dates.date", Sort.DESCENDING)
        try{
            val games: List<Game> = IGDBWrapper.games(IGDB_API)
        } catch(e: RequestException) {
            // Do something or error
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val BEARER_TOKEN:String = "232aut9v0nwswgsjs3t773m2tgraap" // or access token
        private const val CLIENT_ID: String = "b7gj741p59m8q3fuunsy1w7pclf79i"
    }


}