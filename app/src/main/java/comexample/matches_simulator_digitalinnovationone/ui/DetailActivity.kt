package comexample.matches_simulator_digitalinnovationone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import comexample.matches_simulator_digitalinnovationone.databinding.ActivityDetailBinding
import comexample.matches_simulator_digitalinnovationone.domain.Match

class DetailActivity : AppCompatActivity() {

    object Extras{
        const val MATCH = "EXTRA_MATCH"
    }

    private val detailActivityBinding: ActivityDetailBinding by lazy{
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(detailActivityBinding.root)

        setSupportActionBar(detailActivityBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {
        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let{
            Glide.with(this).load(it.place.image).into(detailActivityBinding.ivPlace)
            supportActionBar?.title = it.place.name

            detailActivityBinding.tvDescription.text = it.description

            Glide.with(this).load(it.homeTeam.image).into(detailActivityBinding.ivHomeTeam)
            detailActivityBinding.tvHomeTeamName.text = it.homeTeam.name
            detailActivityBinding.rbHomeTeamStars.rating = it.homeTeam.stars.toFloat()
            if(it.homeTeam.score != null){
                detailActivityBinding.tvHomeTeamScore.text = it.homeTeam.score.toString()
            }

            Glide.with(this).load(it.awayTeam.image).into(detailActivityBinding.ivAwayTeam)
            detailActivityBinding.tvAwayTeamName.text = it.awayTeam.name
            detailActivityBinding.rbAwayTeamStars.rating = it.awayTeam.stars.toFloat()
            if(it.awayTeam.score != null){
                detailActivityBinding.tvAwayTeamScore.text = it.awayTeam.score.toString()
            }
        }
    }
}