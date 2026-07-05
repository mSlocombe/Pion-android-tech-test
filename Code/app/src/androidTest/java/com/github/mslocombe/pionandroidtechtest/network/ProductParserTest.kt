package com.github.mslocombe.pionandroidtechtest.network

import org.json.JSONException
import org.junit.Test

class ProductParserTest {

    @Test
    fun responseDoesNotContainAnyProducts() {
        val testJson = """
            {
              "products": [],
              "total": 0,
              "skip": 0,
              "limit": 30
            }
        """.trimIndent()

        val parser = ProductParser()
        val result = parser.parse(testJson)

        assert(result == emptyList<Product>())
    }

    @Test
    fun responseContainsOneProduct() {
        val testJson = """
        {
            "products": [
                {
                    "id": 1,
                    "title": "Essence Mascara Lash Princess",
                    "thumbnail": "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"
                }
            ],
            "total": 1,
            "skip": 0,
            "limit": 30
        }
        """.trimIndent()

        val parser = ProductParser()
        val result = parser.parse(testJson)

        assert(result == listOf(
            Product("Essence Mascara Lash Princess","https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp")
        ))
    }

    @Test
    fun responseContainsManyProducts() {
        val testJson = """
        {
            "products": [
                {
                    "id": 1,
                    "title": "Essence Mascara Lash Princess",
                    "thumbnail": "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"
                },
                {   
                    "id": 2,
                    "title": "Eyeshadow Palette with Mirror",
                    "thumbnail": "https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/thumbnail.webp"
                },
                {
                    "id": 3,
                    "title": "Powder Canister",
                    "thumbnail": "https://cdn.dummyjson.com/product-images/beauty/powder-canister/thumbnail.webp"
                }
            ],
            "total": 3,
            "skip": 0,
            "limit": 30
        }
        """.trimIndent()

        val parser = ProductParser()
        val result = parser.parse(testJson)

        assert(result == listOf(
            Product("Essence Mascara Lash Princess", "https://cdn.dummyjson.com/product-images/beauty/essence-mascara-lash-princess/thumbnail.webp"),
            Product("Eyeshadow Palette with Mirror", "https://cdn.dummyjson.com/product-images/beauty/eyeshadow-palette-with-mirror/thumbnail.webp"),
            Product("Powder Canister", "https://cdn.dummyjson.com/product-images/beauty/powder-canister/thumbnail.webp")
        ))
    }

    @Test
    fun responseContainsInvalidJSON() {
        val testJson = """
            Not JSON at all
        """.trimIndent()

        val parser = ProductParser()
        try {
            parser.parse(testJson)
            assert(false) {
                println("Expected JSONException")
            }
        } catch(ex: JSONException) {
            assert(ex is JSONException)
        }
    }
}