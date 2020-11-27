package com.movies.app.data.api.interceptor

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor constructor(
  private val apiKey: String
) : Interceptor {

  override fun intercept(chain: Chain): Response {
    val original: Request = chain.request()
    val originalHttpUrl: HttpUrl = original.url()

    // Add api key to query
    val url = originalHttpUrl.newBuilder()
        .addQueryParameter("api_key", apiKey)
        .build()

    val requestBuilder: Request.Builder = original.newBuilder()
        .url(url)

    val request: Request = requestBuilder.build()

    return chain.proceed(request)
  }
}