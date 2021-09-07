package com.example.corridaleve.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.corridaleve.databinding.HomeFragmentBinding
import com.example.corridaleve.databinding.RegisterFragmentBinding
import com.example.corridaleve.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment:Fragment() {
    private var _binding: RegisterFragmentBinding? = null
    private val binding: RegisterFragmentBinding get() = _binding!!

    private val viewModel:RegisterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= RegisterFragmentBinding.inflate(inflater,container,false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener{
            findNavController().navigateUp()
        }

        binding.btnConfirm.setOnClickListener {
            viewModel.validLogin(
                binding.registerEmail.text.toString(),
                binding.registerPassword.text.toString(),
                binding.confirmPassword.text.toString())
        }

        viewModel.validUserLiveData.observe(viewLifecycleOwner,{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(it.email,it.password).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(requireContext(),"Cadastro efetuado com Sucesso!",Toast.LENGTH_LONG).show()
                }
            }

        })

        viewModel.errorLiveData.observe(viewLifecycleOwner,{
            Toast.makeText(requireContext(),it,Toast.LENGTH_LONG).show()
        })
    }
}
