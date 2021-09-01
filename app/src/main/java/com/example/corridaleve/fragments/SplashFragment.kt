package com.example.corridaleve.fragments

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.corridaleve.R
import com.example.corridaleve.databinding.SplashFragmentBinding

class SplashFragment: Fragment() {
    private var _binding: SplashFragmentBinding? = null
    private val binding: SplashFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SplashFragmentBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.animation = AnimationUtils.loadAnimation(requireContext(), R.anim.top_animation)

        Handler(Looper.getMainLooper()).postDelayed({findNavController().navigate(R.id.action_splashFragment_to_loginFragment)},3000);


    }
}