package cz.hlinkapp.flidea.di.modules

import cz.hlinkapp.flidea.contracts.ServerContract.Companion.BASE_URL
import cz.hlinkapp.flidea.data.data_sources.server.SkypickerService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * A Dagger2 module which provides Retrofit-related classes.
 */
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun provideSkypickerService(retrofit: Retrofit) : SkypickerService {
        return retrofit.create(SkypickerService::class.java)
    }

}