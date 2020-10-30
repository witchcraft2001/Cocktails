package ru.dmdev.cocktails.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.dmdev.cocktails.BuildConfig
import ru.dmdev.cocktails.api.responses.CategoryListResponse
import ru.dmdev.cocktails.api.responses.CocktailListResponse

interface CocktailsApi {
    @GET("list.php?c=list")
    suspend fun getCategoriesAsync(): CategoryListResponse

    @GET("filter.php")
    suspend fun getFilteredCocktailsAsync(@Query("c") category: String): CocktailListResponse

    companion object Factory {
        fun create(): CocktailsApi {
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
//                .addInterceptor(UserAgentInterceptor())
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl("https://the-cocktail-db.p.rapidapi.com/")
                .client(client)
                .build()

            return retrofit.create(CocktailsApi::class.java)
        }

//        class UserAgentInterceptor : Interceptor {
//            override fun intercept(chain: Interceptor.Chain): Response {
//                val original = chain.request()
//                val request = original.newBuilder()
//                    .header("x-rapidapi-host", "the-cocktail-db.p.rapidapi.com")
//                    .header("x-rapidapi-key", "")
//                    .build()
//
//                return chain.proceed(request)
//            }
//        }
    }
}