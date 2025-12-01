package com.echo.kotlinlearning.fragments

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.echo.kotlinlearning.R

class FragmentActivity : AppCompatActivity(), LeftFragment.OnButtonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_fragment)
        replaceFragment(RightFragment())



    }

    private fun replaceFragment(fragment: Fragment) {
//        val fragmentManager = supportFragmentManager
//        val leftFragment = fragmentManager.findFragmentById(R.id.left_fragment) as LeftFragment
////        leftFragment.view.findViewById<>()
//        val transaction = fragmentManager.beginTransaction()
//        transaction.replace(R.id.rightLayout,fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
    }

    override fun onButtonClick() {
        replaceFragment(AnotherFragment())
    }

}