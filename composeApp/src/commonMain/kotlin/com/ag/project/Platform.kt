package com.ag.project

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform