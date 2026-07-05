package com.github.mslocombe.pionandroidtechtest.ui.screen.photos

import android.util.Log
import androidx.compose.ui.graphics.Color
import com.github.mslocombe.pionandroidtechtest.network.ProductClient
import com.github.mslocombe.pionandroidtechtest.network.ProductParser
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import kotlin.random.Random

class GetPhotoListUseCase @Inject constructor(
    val client: ProductClient,
    val productParser: ProductParser,
    val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) {
    companion object {
        private const val TAG = "GetPhotoListUseCase"
    }

    suspend operator fun invoke(): PhotoListResult = withContext(defaultDispatcher) {
        val response = client.requestProducts() ?: return@withContext PhotoListResult.Error

        val parsing = try {
            productParser.parse(response)
        } catch(e: JSONException) {
            Log.e(TAG, "invoke parsing failed", e)
            return@withContext PhotoListResult.Error
        }

        val cards = parsing.map {
            PhotoCardState(
                it.id,
                it.title,
                it.thumbnail,
                Color(Random.nextInt())
            )
        }

        PhotoListResult.Success(cards)
    }
}