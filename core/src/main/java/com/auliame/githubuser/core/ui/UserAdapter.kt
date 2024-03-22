package com.auliame.githubuser.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.auliame.githubuser.core.databinding.ItemUserBinding
import com.auliame.githubuser.core.domain.model.UserModel
import com.bumptech.glide.Glide

class UserAdapter(
    private val users: List<UserModel>,
    val onItemClick: (UserModel) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(ItemUserBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = users[position]
        holder.bind(data)
    }

    inner class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(data: UserModel){
            with(binding){
                Glide.with(itemView).load(data.avatarUrl).into(ivUser)
                tvUser.text = data.username
                root.setOnClickListener {
                    onItemClick(data)
                }
            }
        }
    }
}