package co.geisyanne.digitalbank.presenter.auth.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.geisyanne.digitalbank.R
import co.geisyanne.digitalbank.databinding.FragmentLoginBinding
import co.geisyanne.digitalbank.util.FirebaseHelper
import co.geisyanne.digitalbank.util.StateView
import co.geisyanne.digitalbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.txtRegisterLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.txtRecoverLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverFragment)
        }

        binding.btnLogin.setOnClickListener { validateData()}
    }

    private fun validateData() {
        val email = binding.editEmailLogin.text.toString().trim()
        val password = binding.editPasswordLogin.text.toString().trim()

        if (email.isNotEmpty()) {
            if (password.isNotEmpty()) {

                loginUser(email, password)

            } else {
                showBottomSheet(message = getString(R.string.enter_your_password))
            }
        } else {
            showBottomSheet(message = getString(R.string.enter_your_email))

        }
    }

    private fun loginUser(email: String, password: String) {
        loginViewModel.login(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressLogin.isVisible = true
                }

                is StateView.Success -> {
                    binding.progressLogin.isVisible = false

                    findNavController().navigate(R.id.action_global_homeFragment)
                }

                is StateView.Error -> {
                    binding.progressLogin.isVisible = false

                    Log.i("INFOTESTE", "loginUser: ${stateView.message}")

                    showBottomSheet(
                        message = getString(
                            FirebaseHelper.validError(
                                stateView.message ?: ""
                            )
                        )
                    )
                }
            }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}