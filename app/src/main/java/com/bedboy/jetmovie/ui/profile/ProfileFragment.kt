package com.bedboy.jetmovie.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bedboy.jetmovie.databinding.ContentProfileBinding
import com.bedboy.jetmovie.utils.ViewModelFactory

class ProfileFragment: Fragment() {

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
                viewModel.themeSetting.observe(viewLifecycleOwner) { isDarkModeActive: Boolean ->
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
                    viewModel.saveThemeSetting(isChecked)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        contentProfileBinding = null
    }
}