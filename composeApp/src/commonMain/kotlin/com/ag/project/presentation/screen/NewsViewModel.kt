package com.ag.project.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ag.project.domain.model.Article
import com.ag.project.domain.repository.NewsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    init {
        getNewsByCategory("General")
    }

    private val _newsState = MutableStateFlow<List<Article>>(emptyList())
    val newsState = _newsState.asStateFlow()


    fun getNewsByCategory(category: String) {
        viewModelScope.launch {

            try {
                val response = newsRepository.getNewsByCategory(category)
                _newsState.emit(response.articles)

            } catch (e: Exception) {
                println("Error fetching news: ${e.message}")
                _newsState.emit(emptyList())
            }
        }
    }

    fun getNewsBySearch(q: String) {
        viewModelScope.launch {

            try {
                val response = newsRepository.getNewsBySearch(q = q)
                _newsState.emit(response.articles)
            } catch (e: Exception) {
                println("Error fetching news by search: ${e.message}")
                _newsState.emit(emptyList())
            }
        }
    }
}