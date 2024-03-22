package com.auliame.githubuser.ui.following

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.auliame.githubuser.core.data.Resource
import com.auliame.githubuser.databinding.FragmentFollowingBinding
import com.auliame.githubuser.core.ui.UserAdapter
import com.auliame.githubuser.utils.gone
import org.koin.androidx.viewmodel.ext.android.viewModel


class FollowingFragment(private val username: String) : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FollowingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserFollowing(username).observe(viewLifecycleOwner){
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
                        rvFollowing.adapter = UserAdapter(it.data!!){

                        }
                        rvFollowing.layoutManager = LinearLayoutManager(requireContext())
                    }
                }
            }
        }
    }

}