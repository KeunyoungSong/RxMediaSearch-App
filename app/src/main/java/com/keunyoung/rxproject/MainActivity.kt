package com.keunyoung.rxproject

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.keunyoung.rxproject.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.ObservableEmitter
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.operators.observable.ObservableCreate
import io.reactivex.rxjava3.internal.operators.observable.ObservableEmpty
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
	
	private lateinit var binding: ActivityMainBinding
	
	private val searchFragment = SearchFragment()
	private val fragmentList = listOf(searchFragment, FavoritesFragment())
	private val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle, fragmentList)
	
	private var observableTextQuery: Disposable? = null
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		initView()
	}
	
	override fun onDestroy() {
		super.onDestroy()
		observableTextQuery?.dispose()
		observableTextQuery = null
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
		observableTextQuery = ObservableCreate { emitter: ObservableEmitter<String>? ->
			searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
				override fun onQueryTextSubmit(query: String?): Boolean {
					query?.let { emitter?.onNext(it) }
					return false
				}
				
				override fun onQueryTextChange(newText: String?): Boolean {
					binding.viewPager.setCurrentItem(0, true)
					newText?.let { emitter?.onNext(it) }
					return false
				}
			})
		}.debounce(1000, TimeUnit.MILLISECONDS)
			.subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe { query ->
				searchFragment.searchKeyword(query)
			}
	}
}