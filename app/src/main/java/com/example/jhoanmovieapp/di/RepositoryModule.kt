package com.example.jhoanmovieapp.di

import com.example.jhoanmovieapp.network.repository.MovieRepository
import com.example.jhoanmovieapp.network.repository.MovieRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepositoryImp) : MovieRepository


}