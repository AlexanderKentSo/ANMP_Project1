package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.anmp_project1.databinding.FragmentLoginBinding
import com.example.anmp_project1.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.txtError.visibility = View.GONE
        binding.btnLogin.setOnClickListener{
            var user = viewModel.login(binding.txtUsername.text.toString(), binding.txtPassword.text.toString())
            if(user != null){
                val action = LoginFragmentDirections.actionDashboardFragent(user.id)
                it.findNavController().navigate(action)
            } else {
                binding.txtError.visibility = View.VISIBLE
            }
        }
    }
}