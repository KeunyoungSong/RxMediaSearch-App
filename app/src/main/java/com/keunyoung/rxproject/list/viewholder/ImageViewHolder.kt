package com.keunyoung.rxproject.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.keunyoung.rxproject.databinding.ItemImageBinding
import com.keunyoung.rxproject.model.ImageItem
import com.keunyoung.rxproject.model.ListItem

class ImageViewHolder(
	private val binding: ItemImageBinding
): RecyclerView.ViewHolder(binding.root) {
	
	fun bind(item: ListItem){
		item as ImageItem
		binding.item = item
	}
}