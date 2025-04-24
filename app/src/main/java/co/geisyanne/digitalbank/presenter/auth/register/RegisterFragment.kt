package co.geisyanne.digitalbank.presenter.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.geisyanne.digitalbank.R
import co.geisyanne.digitalbank.data.model.User
import co.geisyanne.digitalbank.databinding.FragmentRegisterBinding
import co.geisyanne.digitalbank.util.FirebaseHelper
import co.geisyanne.digitalbank.util.StateView
import co.geisyanne.digitalbank.util.initToolbar
import co.geisyanne.digitalbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbarRegister)
        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener { validateData() }
    }

    private fun validateData() {

        val name = binding.editNameRegister.text.toString().trim()
        val email = binding.editEmailRegister.text.toString().trim()
        val phone = binding.editPhoneRegister.text.toString().trim()
        val password = binding.editPasswordRegister.text.toString().trim()
        val passwordConfirm = binding.editConfirmPasswordRegister.text.toString().trim()

        if (name.isNotEmpty()) {
            if (email.isNotEmpty()) {
                if (phone.isNotEmpty()) {
                    if (password.isNotEmpty()) {
                        if (passwordConfirm.isNotEmpty()) {

                            if (password == passwordConfirm) {
                                val user = User(name, email, phone, password)
                                registerUser(user)
                            } else {
                                showBottomSheet(message = getString(R.string.passwords_do_not_match))
                            }

                        }
                    } else {
                        showBottomSheet(message = getString(R.string.enter_your_password))
                    }
                } else {
                    showBottomSheet(message = getString(R.string.enter_your_phone))
                }
            } else {
                showBottomSheet(message = getString(R.string.enter_your_email))
            }
        } else {
            showBottomSheet(message = getString(R.string.enter_your_name))
        }
    }

    private fun registerUser(user: User) {
        registerViewModel.register(user).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressRegister.isVisible = true
                }

                is StateView.Success -> {
                    binding.progressRegister.isVisible = false

                    findNavController().navigate(R.id.action_global_homeFragment)
                }

                is StateView.Error -> {
                    binding.progressRegister.isVisible = false
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