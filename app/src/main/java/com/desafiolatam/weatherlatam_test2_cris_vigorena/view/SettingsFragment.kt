package com.desafiolatam.weatherlatam_test2_cris_vigorena.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.desafiolatam.weatherlatam_test2_cris_vigorena.R
import com.desafiolatam.weatherlatam_test2_cris_vigorena.data.CELSIUS
import com.desafiolatam.weatherlatam_test2_cris_vigorena.data.FAHRENHEIT
import com.desafiolatam.weatherlatam_test2_cris_vigorena.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        sharedPref.getString(getString(R.string.settings_temperature_unit), CELSIUS)?.let { unit ->
            if (unit == CELSIUS) {
                binding.rbMetric.isChecked = true
            } else {
                binding.rbImperial.isChecked = true
            }
        }

        savePreferences()
    }

    private fun savePreferences() {
        val sharePref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        binding.rbMetric.setOnCheckedChangeListener { _, checked ->
            with(sharePref.edit()) {
                if (checked) {
                    putString(getString(R.string.settings_temperature_unit), CELSIUS)
                } else {
                    putString(getString(R.string.settings_temperature_unit), FAHRENHEIT)
                }
                apply()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
