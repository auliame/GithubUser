package com.auliame.githubuser.ui.detail

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.auliame.githubuser.R
import com.auliame.githubuser.core.data.Resource
import com.auliame.githubuser.core.domain.model.UserDetailModel
import com.auliame.githubuser.core.domain.model.UserModel
import com.auliame.githubuser.core.utils.EXTRA_USER
import com.auliame.githubuser.core.utils.TAB_TITLES
import com.auliame.githubuser.databinding.ActivityDetailBinding
import com.auliame.githubuser.ui.adapter.FollowPagerAdapter
import com.auliame.githubuser.utils.gone
import com.auliame.githubuser.utils.visible
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private lateinit var dataUser: UserModel
    private var isFavorite = false

    @SuppressLint("UseCompatLoadingForDrawables", "NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        dataUser = intent.extras!!.getParcelable(EXTRA_USER, UserModel::class.java) as UserModel

        with(binding){
            isFavorite = dataUser.isFavorite
            fabFavorite.setImageResource(
                if (isFavorite) R.drawable.red_favorite_24 else R.drawable.baseline_favorite_border_24
            )
            fabFavorite.setOnClickListener {
                setFavorite()
            }
        }

        getUser(dataUser.username)
    }

    private fun setFavorite(){
        isFavorite = !isFavorite
        viewModel.updateFavoriteUser(dataUser, isFavorite)

        if (!isFavorite){
            binding.fabFavorite.setImageResource(R.drawable.baseline_favorite_border_24)
        }else{
            binding.fabFavorite.setImageResource(R.drawable.red_favorite_24)
        }
    }

    private fun getUser(username: String) {
        viewModel.getUserDetail(username).observe(this){
            when(it){
                is Resource.Error -> {
                    binding.progressBar.gone()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    binding.progressBar.gone()
                    setData(it.data!!)
                }
            }
        }
    }

    private fun setData(data: UserDetailModel){
        with(binding){
            fabFavorite.visible()
            val txtFollow = "${data.followers} Followers ${data.following} Following"
            val txtUsername = "@${data.username}"
            Glide.with(this@DetailActivity).load(data.avatarUrl).into(ivUser)
            tvName.text = data.name
            tvUsername.text = txtUsername
            tvFollow.text = txtFollow

            val followPagerAdapter = FollowPagerAdapter(this@DetailActivity, data.username)
            viewPager.adapter = followPagerAdapter
            TabLayoutMediator(tabs, viewPager) { tab, position ->
                tab.text = TAB_TITLES[position]
            }.attach()
        }
    }
}