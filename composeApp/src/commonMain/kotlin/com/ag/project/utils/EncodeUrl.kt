package com.ag.project.utils


fun encodeUrl(url: String): String {
    return url.replace(" ", "%20")
        .replace("?", "%3F")
        .replace("=", "%3D")
        .replace("&", "%26")
        .replace("/", "%2F")
        .replace(":", "%3A")
}