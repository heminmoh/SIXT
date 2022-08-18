/**
 * HitViewHolder
 * binding data with hitItem.xml
 * HitViewHolder take an object as HitItemBinding(hitItem) and by bind function, data bound.
 * 2022-06-19 02:55
 */

package com.coding.sixt.binding

import androidx.recyclerview.widget.RecyclerView
import com.coding.sixt.databinding.CarItemBinding
import com.coding.sixt.model.CarPreview
import javax.inject.Inject

class CarViewHolder  @Inject constructor (private val binding: CarItemBinding) : RecyclerView.ViewHolder(binding.root)
{
    fun bind(HitsData: CarPreview) {
        binding.metadata = HitsData
    }
}