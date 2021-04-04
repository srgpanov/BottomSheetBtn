package com.example.bottomsheetbtn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bottomsheetbtn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
            BottomSheetDialog().show(supportFragmentManager,null)
        }
    }
}