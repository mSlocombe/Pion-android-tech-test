package com.github.mslocombe.pionandroidtechtest.network

import kotlinx.coroutines.test.runTest
import org.junit.Test

class ProductClientTest {

    @Test
    fun clientReceivesResponse() = runTest {
        val client = ProductClient()
        val products = client.requestProducts()

        println("response: $products")
        assert(products != null) // we got something
    }
}