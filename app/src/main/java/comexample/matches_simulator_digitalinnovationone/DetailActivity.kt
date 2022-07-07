package comexample.matches_simulator_digitalinnovationone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import comexample.matches_simulator_digitalinnovationone.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val detailActivityBinding: ActivityDetailBinding by lazy{
        ActivityDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(detailActivityBinding.root)

        setSupportActionBar(detailActivityBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}