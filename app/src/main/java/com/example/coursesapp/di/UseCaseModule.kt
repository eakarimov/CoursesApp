package com.example.coursesapp.di

import com.example.coursesapp.domain.repository.CoursesRepository
import com.example.coursesapp.domain.usecase.GetCoursesUseCase
import com.example.coursesapp.domain.usecase.GetFavoriteCoursesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCoursesUseCase(
        repository: CoursesRepository
    ): GetCoursesUseCase {
        return GetCoursesUseCase(coursesRepository = repository)
    }

    @Provides
    fun provideGetFavoriteCoursesUseCase(
        repository: CoursesRepository
    ): GetFavoriteCoursesUseCase {
        return GetFavoriteCoursesUseCase(coursesRepository = repository)
    }
}