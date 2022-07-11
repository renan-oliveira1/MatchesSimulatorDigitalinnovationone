package comexample.matches_simulator_digitalinnovationone.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import comexample.matches_simulator_digitalinnovationone.R
import comexample.matches_simulator_digitalinnovationone.data.MatchesApi
import comexample.matches_simulator_digitalinnovationone.databinding.ActivityMainBinding
import comexample.matches_simulator_digitalinnovationone.domain.Match
import comexample.matches_simulator_digitalinnovationone.ui.adapter.MatchesAdapter
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val mainActivityBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var matchesApi: MatchesApi
    private lateinit var matchesAdapter: MatchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)

        setHttpClient()
        setupMatchesList()
        setupMatchesRefresh()
        setupFloatingActionButton()
    }

    private fun setupMatchesList(){
        mainActivityBinding.rvMatches.hasFixedSize()
        mainActivityBinding.rvMatches.layoutManager = LinearLayoutManager(this)


        findMatchesFromApi()
    }

    private fun findMatchesFromApi() {
        mainActivityBinding.srlMatches.isRefreshing = true
        matchesApi.getMatches().enqueue(object : Callback<List<Match>> {
            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful) {
                    var matches = response.body()
                    if (matches != null) {
                        matchesAdapter = MatchesAdapter(matches)
                        mainActivityBinding.rvMatches.adapter = matchesAdapter
                    }
                } else {
                    showErrorMessage()
                }
                mainActivityBinding.srlMatches.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
            }

        })
    }

    private fun setupMatchesRefresh(){
        mainActivityBinding.srlMatches.setOnRefreshListener(this::findMatchesFromApi)
    }

    private fun setupFloatingActionButton(){
        mainActivityBinding.fabSimulate.setOnClickListener { view ->
            view.animate().rotation(360F).setDuration(500).setListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) {
                    for (i in 0..matchesAdapter.itemCount -1 ){
                        val match: Match = matchesAdapter.matches[i]
                        match.homeTeam.score = Random.nextInt(match.homeTeam.stars + 1)
                        match.awayTeam.score = Random.nextInt(match.awayTeam.stars + 1)
                        matchesAdapter.notifyItemChanged(i)
                    }
                }
            })
        }
    }

    private fun setHttpClient(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://renan-oliveira1.github.io/matches-simulator-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        matchesApi = retrofit.create(MatchesApi::class.java)
    }

    private fun showErrorMessage() {
        Snackbar.make(mainActivityBinding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show()
    }
}