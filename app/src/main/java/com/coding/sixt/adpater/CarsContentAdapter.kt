/**
 * HitsContentsAdapter
 * display the Hits items by this Adapter
 * Data bound to HitItemBinding by HitViewHolder
 * in section onBindViewHolder when user click on item an object send to another activity by intent
 * 2022-06-18 21:35
 */


package com.coding.sixt.adpater

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coding.sixt.R
import com.coding.sixt.databinding.CarItemBinding
import com.coding.sixt.model.CarPreview
import com.coding.sixt.utilitiy.HelperSIXT
import com.coding.sixt.utilitiy.Mapping
import javax.inject.Inject


class CarsContentAdapter  @Inject constructor (private val CarsContentList: List<CarPreview>)

    : ListAdapter<CarPreview, CarsContentAdapter.ViewHolder>(CarDiff()) {
    var context: Context? = null
    private lateinit var binding: CarItemBinding
    private lateinit var navController : NavController
    @Inject
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = CarItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return CarsContentList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)= holder.bind(CarsContentList[position])

    inner class ViewHolder(private val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
        fun bind(item: CarPreview) {
            Glide.with(binding.root).load(item.carImageUrl).error(context?.getDrawable(R.drawable.fallbackimage))
                .into(binding.CarItemImageView)

            binding.make.text = item.make + " | "+ item.series
            binding.modelName.text = "or similar | "+item.modelName
            binding.name.text = item.name
            binding.transmissionView.text = (context?.getString(R.string.transmission)) + " : " + Mapping().transmissionMapping(item.transmission)
            binding.fuelTypeView.text     = (context?.getString(R.string.fuelType)) + " : " + Mapping().fuelTypeMapping(item.fuelType)

            binding.root.setOnClickListener {
                navController = findNavController(binding.root)
                val bundle = Bundle()
                bundle.putParcelable(HelperSIXT.CAR_OBJECT_BUNDLE, item)
                navController.navigate(R.id.mapFragment,bundle)
            }
        }
    }
        private class CarDiff : DiffUtil.ItemCallback<CarPreview>() {
            override fun areItemsTheSame(
                oldItem: CarPreview,
                newItem: CarPreview
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CarPreview,
                newItem: CarPreview
            ): Boolean {
                return oldItem == newItem
            }
        }

}
