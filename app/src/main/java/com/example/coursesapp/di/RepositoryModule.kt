package com.example.coursesapp.di

import com.example.coursesapp.data.repository.CoursesRepositoryImpl
import com.example.coursesapp.domain.repository.CoursesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCoursesRepository(impl: CoursesRepositoryImpl): CoursesRepository
}