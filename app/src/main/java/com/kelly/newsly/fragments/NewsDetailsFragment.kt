package com.kelly.newsly.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kelly.newsly.R
import com.kelly.newsly.databinding.FragmentNewsDetailsBinding

class NewsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentNewsDetailsBinding
    private val args by navArgs<NewsDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)

        binding.apply {
            Glide.with(requireActivity())
                .load(args.newsImage)
                .centerCrop()
                .into(newsImageDetails)
            newsTitleDetails.text = args.newsTitle
            newsDescriptionDetails.text = args.newsDescription
        }

        return binding.root
    }
}