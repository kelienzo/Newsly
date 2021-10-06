package com.kelly.newsly.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelly.newsly.R
import com.kelly.newsly.adapter.NewsAdapter
import com.kelly.newsly.databinding.FragmentHomeBinding
import com.kelly.newsly.viewmodel.NewsViewModel

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var newsViewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.progressBar.visibility = View.VISIBLE

        newsViewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        newsAdapter = NewsAdapter(listOf()) { news ->
            val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(
                news.image,
                news.title,
                news.description
            )
            findNavController().navigate(action)
        }


        newsViewModel.run {
            getAllNews()
            newsLiveData.observe(viewLifecycleOwner, { news ->
                newsAdapter.news = news
                newsAdapter.notifyDataSetChanged()
            })
        }

        binding.newsRv.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        binding.apply {
            viewFade.animate().apply {
                alpha(0f)
                duration = 3000
            }.start()

            progressBar.visibility = View.GONE
        }

        return binding.root
    }
}