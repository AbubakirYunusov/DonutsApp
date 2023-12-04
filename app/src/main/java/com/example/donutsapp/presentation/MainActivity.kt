package com.example.donutsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animeapp.R
import com.example.animeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by  lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}