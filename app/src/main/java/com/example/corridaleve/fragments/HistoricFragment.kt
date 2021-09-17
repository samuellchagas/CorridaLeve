package com.example.corridaleve.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.corridaleve.adapter.HistoricAdapter
import com.example.corridaleve.databinding.HistoricFragmentBinding
import com.example.corridaleve.model.Historic
import com.example.corridaleve.viewmodel.HistoricViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HistoricFragment : Fragment() {

    private var _binding: HistoricFragmentBinding? = null
    private val binding: HistoricFragmentBinding get() = _binding!!
    private val viewModel: HistoricViewModel by viewModel()

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

        viewModel.requestListHistoric()

        viewModel.listHistoric.observe(viewLifecycleOwner,{
            binding.recyclerView.adapter = HistoricAdapter(it)
        })

    }
}