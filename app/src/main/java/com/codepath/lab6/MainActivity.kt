package com.codepath.lab6

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.codepath.lab6.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
        val isDarkModeEnabled = sharedPreferences.getBoolean("DARK_MODE", false)
        if (isDarkModeEnabled) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val parksFragment = ParksFragment()
        val campgroundFragment = CampgroundFragment()
        val settingsFragment = SettingsFragment()

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            val fragmentToLoad: Fragment = when (item.itemId) {
                R.id.nav_parks -> parksFragment
                R.id.nav_campgrounds -> campgroundFragment
                R.id.nav_settings -> settingsFragment
                else -> parksFragment
            }
            replaceFragment(fragmentToLoad)
            true
        }

        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.nav_parks
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame_layout, fragment)
            .commit()
    }
}