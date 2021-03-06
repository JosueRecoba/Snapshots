package com.example.snapshots

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.snapshots.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    private lateinit var mActiveFragment: Fragment
    private lateinit var mFragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setupBottomNav()
    }

    private fun setupBottomNav(){
        mFragmentManager = supportFragmentManager

        val HomeFragment = HomeFragment()
        val AddFragment = AddFragment()
        val profileFragment = ProfileFragment()

        mActiveFragment = HomeFragment

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, profileFragment, ProfileFragment::class.java.name)
            .hide(profileFragment).commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, AddFragment, AddFragment::class.java.name)
            .hide(AddFragment).commit()

        mFragmentManager.beginTransaction()
            .add(R.id.hostFragment, HomeFragment, HomeFragment::class.java.name).commit()

        mBinding.bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_home -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(HomeFragment).commit()
                    mActiveFragment = HomeFragment
                    true
                }

                R.id.action_add -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(AddFragment).commit()
                    mActiveFragment = AddFragment
                    true
                }

                R.id.action_profile -> {
                    mFragmentManager.beginTransaction().hide(mActiveFragment).show(profileFragment).commit()
                    mActiveFragment = profileFragment
                    true
                }
                else -> false
            }
        }
    }
}   