package com.example.corridaleve.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.corridaleve.databinding.ActivityMapsBinding
import com.example.corridaleve.databinding.MainActivityBinding

class MainActivity:AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}