package com.github.mslocombe.pionandroidtechtest.network

import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

class ProductParser @Inject constructor() {
    companion object {
        private const val TAG = "ProductParser"
    }

    @Throws(JSONException::class)
    fun parse(input: String): List<Product> {
        val rootObj = JSONObject(input)
        val productsArray = rootObj.getJSONArray("products")

        val products = mutableListOf<Product>()
        for (index in 0..<productsArray.length()) {
            val thisProduct = productsArray.getJSONObject(index)
            val id = thisProduct.getInt("id")
            val title = thisProduct.getString("title")
            val thumbnail = thisProduct.getString("thumbnail")
            products.add(
                Product(id, title, thumbnail)
            )
        }

        return products
    }
}