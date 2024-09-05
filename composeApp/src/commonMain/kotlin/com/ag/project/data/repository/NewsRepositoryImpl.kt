package com.ag.project.data.repository

import com.ag.project.domain.model.NewsResponse
import com.ag.project.domain.repository.NewsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse


class NewsRepositoryImpl(
    private val client: HttpClient
) : NewsRepository {

    override suspend fun getNewsByCategory(category: String): NewsResponse {
        return try {
            val newsResponse: HttpResponse = client.get("https://newsapi.org/v2/top-headlines") {
                url {
                    parameters.append("country", "us")
                    parameters.append("category", category)
                    parameters.append("apiKey", "ece0393f10644b12bac90080fddfb6b7")
                }
            }
            newsResponse.body()
        } catch (e: Exception) {
            println("Error fetching news for category: $category. Exception: ${e.message}")

            NewsResponse(emptyList(), e.message.toString(), 0)
        }
    }

}