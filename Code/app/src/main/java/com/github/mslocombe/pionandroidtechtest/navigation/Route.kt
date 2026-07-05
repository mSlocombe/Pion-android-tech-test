package com.github.mslocombe.pionandroidtechtest.navigation

import kotlinx.serialization.Serializable

interface Route

@Serializable
data object Login: Route
@Serializable
data object Photos: Route