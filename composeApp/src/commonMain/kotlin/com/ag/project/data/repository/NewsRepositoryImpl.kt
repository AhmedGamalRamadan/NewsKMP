package com.ag.project.data.repository

import com.ag.project.domain.model.NewsResponse
import com.ag.project.domain.repository.NewsRepository
import com.ag.project.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse


class NewsRepositoryImpl(
    private val client: HttpClient
) : NewsRepository {

    override suspend fun getNewsByCategory(category: String): NewsResponse {
        return try {
            val newsResponse: HttpResponse = client.get(
                "${Constants.BASE_URL}/${Constants.TOP_HEADLINE_ENDPOINT}"
            ) {

                url {
                    parameters.append("country", "us")
                    parameters.append("category", category)
                    parameters.append("apiKey", Constants.API_KEY)
                }
            }
            newsResponse.body()
        } catch (e: Exception) {
            println("Error fetching news for category: $category. Exception: ${e.message}")
            NewsResponse(emptyList(), e.message.toString(), 0)
        }
    }

    override suspend fun getNewsBySearch(q: String): NewsResponse {

        return try {
            val newsResponse: HttpResponse = client.get(
                "${Constants.BASE_URL}/${Constants.EVERY_THING_ENDPOINT}"
            ) {
                url {
                    parameters.append("q", q)
                    parameters.append("apiKey", Constants.API_KEY)
                }
            }
            newsResponse.body()
        } catch (e: Exception) {
            println("Error fetching news : $q. Exception: ${e.message}")
            NewsResponse(emptyList(), e.message.toString(), 0)

        }
    }
}