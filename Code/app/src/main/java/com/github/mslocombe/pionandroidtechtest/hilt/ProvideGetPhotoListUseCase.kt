package com.github.mslocombe.pionandroidtechtest.hilt

import com.github.mslocombe.pionandroidtechtest.network.ProductClient
import com.github.mslocombe.pionandroidtechtest.network.ProductParser
import com.github.mslocombe.pionandroidtechtest.ui.screen.photos.GetPhotoListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(ViewModelComponent::class)
class ProvideGetPhotoListUseCase {

    @Provides
    fun provide(): GetPhotoListUseCase {
        return GetPhotoListUseCase(
            ProductClient(),
            ProductParser(),
            Dispatchers.Default
        )
    }
}