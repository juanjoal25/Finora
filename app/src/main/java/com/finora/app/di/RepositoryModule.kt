package com.finora.app.di

import com.finora.app.data.repository.AuthRepositoryImpl
import com.finora.app.data.repository.CategoryRepositoryImpl
import com.finora.app.data.repository.SettingsRepositoryImpl
import com.finora.app.data.repository.TransactionRepositoryImpl
import com.finora.app.data.repository.UserRepositoryImpl
import com.finora.app.domain.repository.AuthRepository
import com.finora.app.domain.repository.CategoryRepository
import com.finora.app.domain.repository.SettingsRepository
import com.finora.app.domain.repository.TransactionRepository
import com.finora.app.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The single file that needs to change to swap the v1 local/simulated implementations for
 * a real backend later (e.g. Firebase/Supabase AuthRepository) — everything above this
 * boundary depends only on the domain interfaces.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    abstract fun bindTransactionRepository(impl: TransactionRepositoryImpl): TransactionRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}
