package com.example.anmp_project1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        
        val sessionManager = com.example.anmp_project1.SessionManager(requireContext())
        val savedUserId = sessionManager.getUserId()
        if (savedUserId != -1) {
            val action = LoginFragmentDirections.actionDashboardFragment(savedUserId)
            view.findNavController().navigate(action)
            return
        }

        binding.txtError.visibility = View.GONE
        binding.btnLogin.setOnClickListener{
            val name = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.login(name, password)
        }
        viewModel.userLD.observe(viewLifecycleOwner) { user ->
            if(user != null){
                sessionManager.saveUserId(user.id)
                val action = LoginFragmentDirections.actionDashboardFragment(user.id)
                findNavController().navigate(action)
            } else {
                binding.txtError.visibility = View.VISIBLE
            }
        }
    }
}