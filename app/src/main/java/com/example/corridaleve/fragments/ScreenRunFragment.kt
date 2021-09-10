package com.example.corridaleve.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.corridaleve.R
import com.example.corridaleve.databinding.ScreenRunFragmentBinding
import java.util.zip.Inflater

class ScreenRunFragment:Fragment() {

    private var _binding: ScreenRunFragmentBinding? = null
    private val binding:ScreenRunFragmentBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ScreenRunFragmentBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageViewStop.setOnClickListener { requireActivity().finish() }
    }

}