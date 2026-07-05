package com.github.mslocombe.pionandroidtechtest.network

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import jakarta.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.withContext

class ProductClient @Inject constructor() {
    companion object {
        private const val TAG = "ProductClient"
    }

    private val client = HttpClient()

    suspend fun requestProducts(): String? = withContext(Dispatchers.IO) {
        try {
            val response = client.get("https://dummyjson.com/products?select=id,title,thumbnail")
            if (response.status == HttpStatusCode.OK) {
                response.bodyAsText()
            } else {
                Log.e(TAG, "requestProducts failed: $response")
                null
            }
        } catch(e: Throwable) {
            Log.e(TAG, "requestProducts: exception $e", e)
            if(e is CancellationException) ensureActive()

            null
        }
    }
}