package co.geisyanne.digitalbank.presenter.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import co.geisyanne.digitalbank.R
import co.geisyanne.digitalbank.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

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


                Toast.makeText(requireContext(), "Login...", Toast.LENGTH_SHORT).show()


            } else {
                Toast.makeText(requireContext(), R.string.enter_password, Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(requireContext(), R.string.enter_email, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}