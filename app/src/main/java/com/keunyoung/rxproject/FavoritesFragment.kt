package com.keunyoung.rxproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.keunyoung.rxproject.databinding.FragmentFavoritesBinding
import com.keunyoung.rxproject.list.ListAdapter

class FavoritesFragment : Fragment() {
	private var _binding: FragmentFavoritesBinding? = null
	private val binding get() = _binding!!
	private val adapter by lazy { ListAdapter() }
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		return FragmentFavoritesBinding.inflate(inflater, container, false).apply {
			_binding = this
		}.root
	}
	
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.apply {
			recyclerView.adapter = adapter
		}
	}
	
	override fun onResume() {
		super.onResume()
		with(binding) {
			if (Common.favoritesList.isEmpty()) {
				emptyTextView.isVisible = true
				recyclerView.isVisible = false
			} else {
				emptyTextView.isVisible = false
				recyclerView.isVisible = true
			}
		}
		adapter.submitList(Common.favoritesList.sortedBy { it.dateTime })
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}