package com.example.corridaleve.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corridaleve.adapter.HistoricAdapter
import com.example.corridaleve.databinding.HistoricFragmentBinding
import com.example.corridaleve.model.Historic

class HistoricFragment : Fragment() {

    private var _binding: HistoricFragmentBinding? = null
    private val binding: HistoricFragmentBinding get() = _binding!!
    private val listExemplo: List<Historic> = listOf(
        Historic("13,2", "1:00:13", "4,13"),
        Historic("13,2", "1:00:13", "4,13"),
        Historic("13,2", "1:00:13", "4,13")
    );

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HistoricFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.adapter = HistoricAdapter(listExemplo)

    }
}