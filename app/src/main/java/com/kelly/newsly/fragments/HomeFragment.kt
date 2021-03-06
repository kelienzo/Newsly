package com.kelly.newsly.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import com.kelly.newsly.classes.LoadingDialog
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

        newsViewModel = ViewModelProvider(requireActivity())[NewsViewModel::class.java]

        newsAdapter = NewsAdapter(listOf()) { news ->
            val action = HomeFragmentDirections.actionHomeFragmentToNewsDetailsFragment(
                news.image,
                news.title,
                news.description,
                news.url
            )
            findNavController().navigate(action)

//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
//            startActivity(intent)
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
        }

        return binding.root
    }
}