package com.keunyoung.rxproject

import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.keunyoung.rxproject.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityMainBinding
	
	private val searchFragment = SearchFragment()
	private val fragmentList = listOf(searchFragment, FavoritesFragment())
	private val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragmentList)
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initView()
	}
	
	private fun initView() {
		binding = ActivityMainBinding.inflate(layoutInflater).apply {
			setContentView(root)
			view = this@MainActivity
			viewPager.adapter = adapter
			
			TabLayoutMediator(tabLayout, viewPager) { tab, position ->
				tab.text = if (fragmentList[position] is SearchFragment) {
					"검색 결과"
				} else {
					"즐겨찾기"
				}
			}.attach()
		}
	}
	
	// 검색 메뉴 활성화
	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.options_menu, menu)
		val searchItem = menu?.findItem(R.id.search)
		val searchView = searchItem?.actionView as? SearchView
		searchView?.let {
			setupSearchView(it)
		}
		return true
	}
	
	private fun setupSearchView(searchView: SearchView) {
		searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				return false
			}
			
			override fun onQueryTextChange(newText: String?): Boolean {
				return false
			}
		})
	}
}