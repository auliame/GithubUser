package com.auliame.githubuser.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.auliame.githubuser.R
import com.auliame.githubuser.core.data.Resource
import com.auliame.githubuser.core.domain.model.UserModel
import com.auliame.githubuser.databinding.ActivityMainBinding
import com.auliame.githubuser.core.ui.UserAdapter
import com.auliame.githubuser.core.utils.EXTRA_USER
import com.auliame.githubuser.ui.detail.DetailActivity
import com.auliame.githubuser.utils.gone
import com.auliame.githubuser.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getUsers()

        with(binding){
            btnFavorite.setOnClickListener {
                startActivity(Intent(this@MainActivity, Class.forName("com.auliame.githubuser.favorite.ui.ListFavoriteActivity")))
            }
        }
    }

    private fun getUsers() {
        viewModel.getUsers().observe(this){
            when(it){
                is Resource.Error -> {
                    binding.progressBar.gone()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    binding.progressBar.gone()
                    setList(it.data!!)
                }
            }
        }
    }

    private fun setList(data: List<UserModel>){
        with(binding){
            rvUser.visible()
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvUser.adapter =
                UserAdapter(users = data) {
                    startActivity(
                        Intent(this@MainActivity, DetailActivity::class.java)
                            .putExtra(EXTRA_USER, it)
                    )
                }
        }
    }
}