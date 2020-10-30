package ru.dmdev.cocktails.di.modules

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.dmdev.cocktails.BuildConfig
import ru.dmdev.cocktails.api.CocktailsApi
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideCocktailsApi(): CocktailsApi {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("x-rapidapi-host", "the-cocktail-db.p.rapidapi.com")
                    .header(
                        "x-rapidapi-key", BuildConfig.RAPID_API_KEY
                    )
                    .build()

                chain.proceed(request)
            }
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://the-cocktail-db.p.rapidapi.com/")
            .client(client)
            .build()

        return retrofit.create(CocktailsApi::class.java)
    }
}