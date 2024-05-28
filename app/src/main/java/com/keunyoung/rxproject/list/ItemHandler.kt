package com.keunyoung.rxproject.list

import com.keunyoung.rxproject.model.ListItem

interface ItemHandler {
	fun onClickFavorite(item: ListItem)
}