package edu.msudenver.gamerconnect


import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.api.igdb.apicalypse.APICalypse
import com.api.igdb.exceptions.RequestException
import com.api.igdb.request.IGDBWrapper
import com.api.igdb.request.games
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import proto.Game
import androidx.appcompat.widget.SearchView
import com.api.igdb.request.covers
import com.api.igdb.utils.ImageSize
import com.api.igdb.utils.ImageType
import com.api.igdb.utils.imageBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import proto.Cover


class HomePageActivity : AppCompatActivity() { // End Class

    // Coroutines
    private val thread = CoroutineScope(Dispatchers.IO)
    private val thread2 = CoroutineScope(Dispatchers.IO)


    fun getGameData(userInput: String): MutableList<Game> {
        thread.launch {
            IGDBWrapper.setCredentials(CLIENT_ID, ACCESS_TOKEN)
            val apiCalypse = APICalypse().fields("name").limit(10).search(userInput)
            try{
                gameList += IGDBWrapper.games(apiCalypse)

            } catch(e: RequestException) {
                println(e.statusCode)
                println(e.request)
                println(e.result)

            }


        }
        Thread.sleep(1000)
        return gameList
    }

    fun getGameImage(gameId: MutableList<String>): MutableList<Cover> {
        thread.launch {
            IGDBWrapper.setCredentials(CLIENT_ID, ACCESS_TOKEN)

            for (id in gameId) {

                val apiCalypse = APICalypse().fields("*").where("game = $id")

                try {
                    gameImagesId += IGDBWrapper.covers(apiCalypse)

                } catch (e: RequestException) {
                    println(e.statusCode)
                    println(e.request)
                    println(e.result)

                }
            }



        }
        Thread.sleep(2000)
        return gameImagesId
    }


    //Recycler
    lateinit var recyclerView: RecyclerView


    private inner class GameHolder(view: View): RecyclerView.ViewHolder(view) {

        val gameName: TextView = view.findViewById(R.id.gameName)
        var gameImage: ImageView = view.findViewById(R.id.gameImage)

    }


    private inner class GameAdapter(var gamelist: MutableList<Game>, var gameImageList: MutableList<Cover>): RecyclerView.Adapter<GameHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.game_list,parent,false)
            return GameHolder(view)
        }

        override fun onBindViewHolder(holder: GameHolder, position: Int) {
            val game = gamelist[position]
            if (position < gameImageList.size){
                if (game.id == (gameImageList[position].game.id)){
                    holder.gameName.text = game.name.toString()
                    val image_id = gameImagesId[position].imageId
                    val imageURL = imageBuilder(image_id,ImageSize.COVER_BIG, ImageType.PNG)

                    Picasso.get().load(imageURL).into(holder.gameImage)

                }

            }

            holder.gameName.text = game.name.toString()






        }

        override fun getItemCount(): Int {
            return gamelist.size

        }
    }




    lateinit var toggle : ActionBarDrawerToggle
    var gameList: MutableList<Game> = mutableListOf()
    var gameImagesId: MutableList<Cover> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_home)
        val drawerLayout : DrawerLayout = findViewById(R.id.drawerLayout)
        val navView : NavigationView = findViewById(R.id.nav_view)
        val searchView: SearchView = findViewById(R.id.search_bar)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.invalidate()

        /**SearchBar */
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                /* TODO create a drop down search suggestion */
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                gameImagesId.clear()
                gameList.clear()
                recyclerView.invalidate()
                getGameData(query)
                getGameImage(loadImageValue())
                searchView.clearFocus()
                recyclerView.adapter = GameAdapter(gameList, gameImagesId)
                return false
            }

        })


    /** NavBar  */
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
                R.id.log_out -> Firebase.auth.signOut()
                R.id.log_out -> signOut()
                R.id.share -> Toast.makeText(applicationContext, "Clicked Share", Toast.LENGTH_SHORT).show()
                R.id.rate_us -> Toast.makeText(applicationContext, "Clicked Rate Us", Toast.LENGTH_SHORT).show()

            }

            true
        }

    }

    private fun loadImageValue():MutableList<String> {
        var idList: MutableList<String> = mutableListOf()
        val gameId = gameList
        var count = 0
        for (i in gameId) {
                idList += gameId[count].id.toString()
            count += 1

        }

        return idList

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


    override fun onResume() {
        super.onResume()

}


    companion object {
        private const val ACCESS_TOKEN:String = "xlyjc16gkdzntzsjvlg4hwne2nm075" // or bearer token
        private const val CLIENT_ID: String = "b7gj741p59m8q3fuunsy1w7pclf79i"
    }


}



