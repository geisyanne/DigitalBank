package co.geisyanne.digitalbank.presenter.auth.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.geisyanne.digitalbank.R
import co.geisyanne.digitalbank.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

                            Toast.makeText(requireContext(), "Registrando...", Toast.LENGTH_SHORT)
                                .show()

                        } else {
                            Toast.makeText(
                                requireContext(),
                                R.string.enter_confirm_password,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            requireContext(), R.string.enter_password, Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(requireContext(), R.string.enter_phone, Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(requireContext(), R.string.enter_email, Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), R.string.enter_name, Toast.LENGTH_SHORT).show()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}