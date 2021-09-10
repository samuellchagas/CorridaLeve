package com.example.corridaleve.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.corridaleve.R
import com.example.corridaleve.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView2.setupWithNavController(
            (supportFragmentManager
                .findFragmentById(R.id.fragmentContainerView2) as NavHostFragment).navController
        )
    }

    override fun onBackPressed() {

    AlertDialog.Builder(this).setTitle("Sair do aplicativo?")
        .setMessage("").setPositiveButton("SIM"){_,_->
        finish()
    }.setNegativeButton("NÃO"){_,_->}.show()

    }

}