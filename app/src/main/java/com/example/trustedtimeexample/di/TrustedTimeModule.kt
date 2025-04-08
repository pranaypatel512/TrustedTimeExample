package com.example.trustedtimeexample.di

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.android.gms.time.TrustedTime
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import com.google.android.gms.time.TrustedTimeClient

@Module
@InstallIn(SingletonComponent::class)
object TrustedTimeModule {
    @Provides
    fun provideTrustedTimeClientAccessor(
        @ApplicationContext context: Context
    ): TrustedTimeClientAccessor {
        return object : TrustedTimeClientAccessor {
            override fun createClient(): Task<TrustedTimeClient> {
                return TrustedTime.createClient(context)
            }
        }
    }
}
