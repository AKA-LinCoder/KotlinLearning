package jetpack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityCounterBinding
import androidx.core.content.edit

class CounterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCounterBinding

    private lateinit var viewModel: MainViewModel

    private lateinit var sp: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count", 0)
        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)
        binding.plusOneBtn.setOnClickListener {
            viewModel.counter++
            refreshCounter()

        }
        binding.clearBtn.setOnClickListener {
            viewModel.counter = 0
            refreshCounter()
        }
        refreshCounter()

    }
    private fun refreshCounter(){
        binding.infoText.text = viewModel.counter.toString()
    }


    override fun onPause() {
        super.onPause()
        sp.edit { putInt("count", viewModel.counter) }
    }
}