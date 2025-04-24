package co.geisyanne.digitalbank.presenter.auth.recover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import co.geisyanne.digitalbank.R
import co.geisyanne.digitalbank.databinding.FragmentRecoverBinding
import co.geisyanne.digitalbank.util.FirebaseHelper
import co.geisyanne.digitalbank.util.StateView
import co.geisyanne.digitalbank.util.initToolbar
import co.geisyanne.digitalbank.util.showBottomSheet
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecoverFragment : Fragment() {

    private var _binding: FragmentRecoverBinding? = null
    private val binding get() = _binding!!

    private val recoverViewModel: RecoverViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoverBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar(binding.toolbarRecover)
        initListeners()
    }

    private fun initListeners() {
        binding.btnSendRecover.setOnClickListener { validateData()}
    }

    private fun validateData() {
        val email = binding.editEmailRecover.text.toString().trim()

        if (email.isNotEmpty()) {
            recoverAccount(email)
        } else {

            showBottomSheet(message = getString(R.string.enter_your_email))
        }
    }

    private fun recoverAccount(email: String) {
        recoverViewModel.recover(email).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.progressRecover.isVisible = true
                }

                is StateView.Success -> {
                    binding.progressRecover.isVisible = false

                    showBottomSheet(message = getString(R.string.send_email_success))
                }

                is StateView.Error -> {
                    binding.progressRecover.isVisible = false
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