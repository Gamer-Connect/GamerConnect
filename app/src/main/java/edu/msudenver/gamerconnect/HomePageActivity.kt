package edu.msudenver.gamerconnect


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.api.igdb.apicalypse.APICalypse
import com.api.igdb.apicalypse.Sort
import com.api.igdb.exceptions.RequestException
import com.api.igdb.request.IGDBWrapper
import com.api.igdb.request.games
import proto.Game


class HomePageActivity : AppCompatActivity(){
    lateinit var recyclerView: RecyclerView

    private inner class ItemHolder(view: View): RecyclerView.ViewHolder(view) {

    }








    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page_home)

        IGDBWrapper.setCredentials(Companion.CLIENT_ID, Companion.BEARER_TOKEN)

        val IGDB_API = APICalypse().fields("*").sort("release_dates.date", Sort.DESCENDING)
        try{
            val games: List<Game> = IGDBWrapper.games(IGDB_API)
        } catch(e: RequestException) {
            // Do something or error
        }

    }

    companion object {
        private const val BEARER_TOKEN:String = "232aut9v0nwswgsjs3t773m2tgraap" // or access token
        private const val CLIENT_ID: String = "b7gj741p59m8q3fuunsy1w7pclf79i"
    }


}