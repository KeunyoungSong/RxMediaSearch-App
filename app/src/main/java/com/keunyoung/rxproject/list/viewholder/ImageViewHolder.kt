package com.keunyoung.rxproject.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.keunyoung.rxproject.databinding.ItemImageBinding
import com.keunyoung.rxproject.list.ItemHandler
import com.keunyoung.rxproject.model.ImageItem
import com.keunyoung.rxproject.model.ListItem

class ImageViewHolder(
	private val binding: ItemImageBinding, private val itemHandler: ItemHandler? = null
) : RecyclerView.ViewHolder(binding.root) {
	
	fun bind(item: ListItem) {
		item as ImageItem
		binding.item = item
		binding.handler = itemHandler
	}
}