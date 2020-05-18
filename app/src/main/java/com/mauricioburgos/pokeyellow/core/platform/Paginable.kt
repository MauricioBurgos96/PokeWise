package com.mauricioburgos.pokeyellow.core.platform

interface Paginable {
    var currentPage: Int
    var lastPage: Int
    var isLoading: Boolean
    var isLastPage: Boolean
    var hasNextPage: Boolean

    fun resetPager(){
        currentPage = 0
        lastPage = 1
        isLoading = false
        isLastPage = false
    }
}