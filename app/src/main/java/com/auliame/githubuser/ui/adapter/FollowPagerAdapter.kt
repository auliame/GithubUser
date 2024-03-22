package com.auliame.githubuser.ui.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.auliame.githubuser.ui.followers.FollowersFragment
import com.auliame.githubuser.ui.following.FollowingFragment

class FollowPagerAdapter(activity: AppCompatActivity,private val username: String) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment(username)
            1 -> fragment = FollowingFragment(username)
        }
        return fragment as Fragment
    }
}