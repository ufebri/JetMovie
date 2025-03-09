package com.bedboy.jetmovie.ui.profile

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.R
import com.bedboy.jetmovie.databinding.ContentProfileBinding
import com.bedboy.jetmovie.utils.DialogHelper
import com.bedboy.jetmovie.utils.PermissionManager
import com.bedboy.jetmovie.utils.ViewModelFactory

class ProfileFragment : Fragment() {

    private var contentProfileBinding: ContentProfileBinding? = null
    private val binding get() = contentProfileBinding
    private lateinit var viewModel: ProfileViewModel
    private lateinit var permissionManager: PermissionManager

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            handlePermissionResult(isGranted)
        }

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

            permissionManager = PermissionManager(requireActivity())

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
                        requestOrCheckPermission()
                    } else {
                        viewModel.setReminder(false)
                    }
                }
            }
        }
    }

    private fun requestOrCheckPermission() {
        if (!permissionManager.isPermissionNotificationGranted()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                // Jika Android < 13, tidak perlu meminta izin notifikasi
                binding?.switchReminder?.isChecked = true
            }
        } else {
            viewModel.setReminder(true)
        }
    }

    private fun handlePermissionResult(isGranted: Boolean) {
        if (isGranted) {
            viewModel.setReminder(true)
        } else {
            DialogHelper.showDialog(
                context = requireActivity(),
                title = getString(R.string.title_dialog_permission),
                message = getString(R.string.message_permission_notification),
                positiveText = getString(R.string.text_open_setting),
                negativeText = getString(R.string.text_cancel),
                onConfirm = {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        requireActivity().startActivity(
                            Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                                putExtra(Settings.EXTRA_APP_PACKAGE, requireActivity().packageName)
                            })
                    }
                }
            )
            binding?.switchReminder?.isChecked = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        contentProfileBinding = null
    }
}