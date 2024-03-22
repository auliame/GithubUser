package com.auliame.githubuser.ui.followers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.auliame.githubuser.core.data.Resource
import com.auliame.githubuser.databinding.FragmentFollowersBinding
import com.auliame.githubuser.core.ui.UserAdapter
import com.auliame.githubuser.utils.gone
import org.koin.androidx.viewmodel.ext.android.viewModel


class FollowersFragment(private val username: String) : Fragment() {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowersViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserFollowers(username).observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> {
                    binding.progressBar.gone()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {

                }
                is Resource.Success -> {
                    with(binding){
                        progressBar.gone()
                        rvFollowers.adapter = UserAdapter(it.data!!){

                        }
                        rvFollowers.layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }
        }
    }

}