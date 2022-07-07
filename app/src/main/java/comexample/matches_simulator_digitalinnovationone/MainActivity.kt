package comexample.matches_simulator_digitalinnovationone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import comexample.matches_simulator_digitalinnovationone.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val mainActivityBinding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mainActivityBinding.root)
    }

}