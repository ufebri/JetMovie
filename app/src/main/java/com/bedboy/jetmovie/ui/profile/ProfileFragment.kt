package com.bedboy.jetmovie.ui.profile

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.databinding.ContentProfileBinding
import com.bedboy.jetmovie.utils.PermissionHelper
import com.bedboy.jetmovie.utils.ViewModelFactory

class ProfileFragment : Fragment() {

    private var contentProfileBinding: ContentProfileBinding? = null
    private val binding get() = contentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentProfileBinding = ContentProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

            //Dark Mode Config
            binding?.apply {
                //Check First
                viewModel.isDarkThemeActive.observe(viewLifecycleOwner) { isDarkModeActive: Boolean ->
                    if (isDarkModeActive) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        switchTheme.isChecked = true
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        switchTheme.isChecked = false
                    }
                }

                //Change Listener of Switch Theme
                switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
                    viewModel.setDarkTheme(isChecked)
                }

                viewModel.isReminderActive.observe(viewLifecycleOwner) { isReminderActive: Boolean ->
                    switchReminder.isChecked = isReminderActive
                }

                switchReminder.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        if (!PermissionHelper.isPermissionGranted(requireActivity(), Manifest.permission.POST_NOTIFICATIONS)) {
                            PermissionHelper.requestPermission(requireActivity(), Manifest.permission.POST_NOTIFICATIONS)

                            switchReminder.isChecked = false
                        } else {
                            viewModel.setReminder(isChecked)
                        }
                    } else {
                        viewModel.setReminder(isChecked)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        contentProfileBinding = null
    }
}