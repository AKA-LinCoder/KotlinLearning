package jetpack

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.echo.kotlinlearning.R
import com.echo.kotlinlearning.databinding.ActivityCounterBinding
import androidx.core.content.edit
import kotlin.concurrent.thread

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
            viewModel.plusOne()
        }
        binding.clearBtn.setOnClickListener {
            viewModel.clear()
        }
        viewModel.counter.observe(this) {count ->
            binding.infoText.text = count.toString()
        }
        refreshCounter()

        val userDao = AppDatabase.getDatabase(this).userDao()
        val user1 = User("TOM", "John", 12)
        val user2 = User("JERRY", "Tom", 22)
        binding.addDataBtn.setOnClickListener {
            thread {
                user1.id = userDao.insertUser(user1)
                user2.id = userDao.insertUser(user2)
            }
        }
        binding.updateDataBtn.setOnClickListener {
            thread {
                user1.age = 20
                userDao.updateUser(user1)
            }
        }
        binding.deleteDataBtn.setOnClickListener {
            thread {
                userDao.deleteUserByLastName("Tom")
            }
        }
        binding.queryDataBtn.setOnClickListener {
            thread {
               for (user in userDao.loadAllUser()){
                   Log.d("TAG", "queryDataBtn: $user")
               }
            }
        }
//        binding.getUserBtn.setOnClickListener {
//            thread {
//               for (user in userDao.loadUsersOlderThan(20)){
//               }
//            }
//        }




    }
    private fun refreshCounter(){
        binding.infoText.text = viewModel.counter.toString()
    }
    override fun onPause() {
        super.onPause()
        sp.edit { putInt("count", viewModel.counter.value?:0) }
    }
}