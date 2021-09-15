package com.example.corridaleve.fragments

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.corridaleve.R
import com.example.corridaleve.databinding.LoginFragmentBinding
import com.example.corridaleve.viewmodel.LoginViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: LoginFragmentBinding? = null
    private val binding: LoginFragmentBinding get() = _binding!!
    private val viewModel: LoginViewModel by viewModel()
    private var auth = Firebase.auth

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

        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                (requireContext()),
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),101)
        }

        viewModel.getEmail()

        viewModel.emailLiveData.observe(viewLifecycleOwner,{
            binding.loginEmail.setText(it)
        })

        viewModel.getPassword()

        viewModel.passwordLiveData.observe(viewLifecycleOwner,{
            binding.loginPassword.setText(it)
        })

        viewModel.switchDefault()

        binding.btnConfirm.setOnClickListener {
            val email = binding.loginEmail.text.toString()
            val password = binding.loginPassword.text.toString()
            if(email.isEmpty()||password.isEmpty()){
                Toast.makeText(requireContext(), "Email ou senha inválida", Toast.LENGTH_SHORT).show()
            }else{
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()){task ->
                    if(task.isSuccessful){
                        findNavController().navigate(R.id.action_loginFragment_to_homeActivity)
                        requireActivity().finish()

                    }else{
                        Toast.makeText(requireContext(),"Usuario e/ou senha inválida!",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

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

        binding.textView3.setOnClickListener{findNavController().navigate(R.id.action_loginFragment_to_registerFragment)}

    }


}