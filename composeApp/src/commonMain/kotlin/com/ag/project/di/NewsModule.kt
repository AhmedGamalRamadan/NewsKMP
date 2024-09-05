package com.ag.project.di

import com.ag.project.data.repository.NewsRepositoryImpl
import com.ag.project.domain.repository.NewsRepository
import com.ag.project.presentation.screen.NewsViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module


val newsModule = module {

    single {
        HttpClient{
            install(ContentNegotiation){
                json()
            }
        }
    }

    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }

    viewModel {
        NewsViewModel(get())
    }

}