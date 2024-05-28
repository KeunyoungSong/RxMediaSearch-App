package com.keunyoung.rxproject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.keunyoung.rxproject.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {
	private var binding: FragmentFavoritesBinding? = null
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
	): View {
		return FragmentFavoritesBinding.inflate(inflater, container, false).apply {
			binding = this
		}.root
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		binding = null
	}
}