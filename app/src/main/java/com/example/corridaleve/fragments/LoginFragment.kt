package com.example.corridaleve.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.corridaleve.R
import com.example.corridaleve.databinding.LoginFragmentBinding
import com.example.corridaleve.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding: LoginFragmentBinding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEmail()

        viewModel.emailLiveData.observe(viewLifecycleOwner,{
            binding.loginEmail.setText(it)
        })

        viewModel.getPassword()

        viewModel.passwordLiveData.observe(viewLifecycleOwner,{
            binding.loginPassword.setText(it)
        })

        viewModel.switchDefault()

        viewModel.switchDefaultLiveData.observe(viewLifecycleOwner,{
            if(it){
                binding.switchSaveLogin.toggle()
            }
        })

        binding.switchSaveLogin.setOnCheckedChangeListener { _, isCheck ->

            if (isCheck) {
                viewModel.saveLogin(
                    binding.loginEmail.text.toString(),
                    binding.loginPassword.text.toString()
                )
            }else{
                viewModel.deleteLogin()
            }
        }

        binding.btnConfirm.setOnClickListener { findNavController().navigate(R.id.action_loginFragment_to_homeFragment) }

        binding.textView3.setOnClickListener{findNavController().navigate(R.id.action_loginFragment_to_registerFragment)}

    }


}