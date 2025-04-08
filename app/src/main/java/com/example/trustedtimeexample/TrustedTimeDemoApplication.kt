package com.example.trustedtimeexample

import android.app.Application
import com.example.trustedtimeexample.di.TrustedTimeClientAccessor
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject
import com.google.android.gms.time.TrustedTimeClient

@HiltAndroidApp
class TrustedTimeDemoApplication : Application() {

    @Inject
    lateinit var trustedTimeClientAccessor: TrustedTimeClientAccessor

    // Global reference for use throughout your app.
    var trustedTimeClient: TrustedTimeClient? = null
        private set

    override fun onCreate() {
        super.onCreate()
        trustedTimeClientAccessor.createClient().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                trustedTimeClient = task.result
            } else {
                // Log or handle the error appropriately
                task.exception?.printStackTrace()
            }
        }
    }
}
