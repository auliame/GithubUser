package com.auliame.githubuser.favorite.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.auliame.githubuser.core.ui.UserAdapter
import com.auliame.githubuser.core.utils.EXTRA_USER
import com.auliame.githubuser.favorite.R
import com.auliame.githubuser.favorite.databinding.ActivityListFavoriteBinding
import com.auliame.githubuser.favorite.di.favoriteModule
import com.auliame.githubuser.ui.detail.DetailActivity
import com.auliame.githubuser.utils.gone
import com.auliame.githubuser.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class ListFavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListFavoriteBinding
    private val viewModel: ListFavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        loadKoinModules(favoriteModule)
        binding = ActivityListFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rvFavorite)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteUser().observe(this){
            if (it.isNotEmpty()){
                with(binding){
                    rvFavorite.visible()
                    rvFavorite.adapter = UserAdapter(it){ user ->
                        startActivity(
                            Intent(this@ListFavoriteActivity, DetailActivity::class.java)
                                .putExtra(EXTRA_USER, user)
                        )
                    }
                    rvFavorite.layoutManager = LinearLayoutManager(this@ListFavoriteActivity)
                }
            }else{
                binding.rvFavorite.gone()
            }
        }
    }
}