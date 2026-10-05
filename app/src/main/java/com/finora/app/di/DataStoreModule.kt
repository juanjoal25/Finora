package com.finora.app.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.finora.app.core.constants.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.sessionDataStore: DataStore<Preferences> by preferencesDataStore(
    name = AppConstants.SESSION_PREFERENCES_NAME,
)
private val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(
    name = AppConstants.THEME_PREFERENCES_NAME,
)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    @SessionDataStore
    fun provideSessionDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.sessionDataStore

    @Provides
    @Singleton
    @ThemeDataStore
    fun provideThemeDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.themeDataStore
}
