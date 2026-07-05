package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import com.github.mslocombe.pionandroidtechtest.network.ProductClient
import com.github.mslocombe.pionandroidtechtest.network.ProductParser
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException

class GetPhotoListUseCase @Inject constructor(
    val client: ProductClient,
    val productParser: ProductParser,
    val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {

    suspend operator fun invoke(): PhotoListResult = withContext(defaultDispatcher) {
        val response = client.requestProducts() ?: return@withContext PhotoListResult.Error

        val parsing = try {
            productParser.parse(response)
        } catch(_: JSONException) {
            return@withContext PhotoListResult.Error
        }

        val cards = parsing.map {
            PhotoCard(it.thumbnail)
        }

        PhotoListResult.Success(cards)
    }
}