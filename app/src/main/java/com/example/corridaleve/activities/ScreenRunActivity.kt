package com.example.corridaleve.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.corridaleve.R
import com.example.corridaleve.databinding.ActivityHomeBinding
import com.example.corridaleve.databinding.ActivityScreenRunBinding
import com.example.corridaleve.databinding.ScreenRunFragmentBinding

class ScreenRunActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScreenRunBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScreenRunBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onBackPressed() {
        Toast.makeText(this, "Corrida em andamento, para sair aperte o STOP!", Toast.LENGTH_LONG).show()
    }
}