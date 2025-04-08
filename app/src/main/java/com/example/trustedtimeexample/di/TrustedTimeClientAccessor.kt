package com.example.trustedtimeexample.di

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.android.gms.time.TrustedTimeClient

interface TrustedTimeClientAccessor {
    fun createClient(): Task<TrustedTimeClient>
}
