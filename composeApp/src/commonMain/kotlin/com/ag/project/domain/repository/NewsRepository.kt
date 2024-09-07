package com.ag.project.domain.repository

import com.ag.project.domain.model.NewsResponse

interface NewsRepository {

    suspend fun getNewsByCategory(category: String): NewsResponse

    suspend fun getNewsBySearch(q: String): NewsResponse

}