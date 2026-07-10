package com.github.mslocombe.pionandroidtechtest.app2.inject

import com.github.mslocombe.pionandroidtechtest.app2.ui.login.LoginValidator
import com.github.mslocombe.pionandroidtechtest.app2.ui.login.LoginValidatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
class LoginValidatorModule {

    @Provides
    fun provideLoginValidator(): LoginValidator {
        return LoginValidatorImpl()
    }
}