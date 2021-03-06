package com.oleksandrkarpiuk.recipemaster.di.modules.data.remote

import com.oleksandrkarpiuk.recipemaster.api.SpoonacularApi
import com.oleksandrkarpiuk.recipemaster.api.recipes.randomrecipes.RandomRecipesApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object RemoteModule {

    @Singleton
    @Provides
    fun provideSpoonacularRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(SpoonacularApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRandomRecipesApi(retrofit: Retrofit): RandomRecipesApi {
        return retrofit.create(RandomRecipesApi::class.java)
    }

}