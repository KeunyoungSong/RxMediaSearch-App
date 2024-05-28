package com.keunyoung.rxproject.list.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.keunyoung.rxproject.databinding.ItemVideoBinding
import com.keunyoung.rxproject.list.ItemHandler
import com.keunyoung.rxproject.model.ListItem
import com.keunyoung.rxproject.model.VideoItem

class VideoViewHolder(
	private val binding: ItemVideoBinding, private val itemHandler: ItemHandler? = null
) : RecyclerView.ViewHolder(binding.root) {
	
	fun bind(item: ListItem) {
		item as VideoItem
		binding.item = item
		binding.handler = itemHandler
	}
}