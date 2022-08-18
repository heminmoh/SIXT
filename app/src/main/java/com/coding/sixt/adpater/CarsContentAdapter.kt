/**
 * HitsContentsAdapter
 * display the Hits items by this Adapter
 * Data bound to HitItemBinding by HitViewHolder
 * in section onBindViewHolder when user click on item an object send to another activity by intent
 * 2022-06-18 21:35
 */


package com.coding.sixt.adpater

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coding.sixt.R
import com.coding.sixt.binding.CarViewHolder
import com.coding.sixt.databinding.CarItemBinding
import com.coding.sixt.model.CarPreview
import javax.inject.Inject


class CarsContentAdapter  @Inject constructor (private val CarsContentList: List<CarPreview>)

                                               :RecyclerView.Adapter<CarViewHolder>() {
    var context: Context? = null
    private lateinit var binding: CarItemBinding
    private lateinit var navController : NavController
    @Inject
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        binding = CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        navController = Navigation.findNavController(parent)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return CarsContentList.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int)
    {
        Glide.with(binding.root).load(CarsContentList[position].carImageUrl).into(binding.CarItemImageView)
        binding.name.text = CarsContentList[position].name
        return holder.bind(CarsContentList[position])
    }





}