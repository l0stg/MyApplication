package com.example.myapplicationapi.imageViewFargment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.myapplicationapi.DataModel.Items
import com.example.myapplicationapi.R
import com.example.myapplicationapi.databinding.FragmentDetailBinding
import com.example.myapplicationapi.databinding.FragmentImageBinding

class ImageFragment : Fragment() {

    private var binding: FragmentImageBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imagePut = arguments?.getSerializable("Item") as Items //вынести во вью модел

        with(binding!!) {
            Glide.with(imageDetail.context)
                .load(imagePut.imageAvatar)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageDetail)
        }
    }

}