package com.example.trustedtimeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trustedtimeexample.di.TrustedTimeClientAccessor
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.google.android.gms.time.TrustedTimeClient

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var trustedTimeClientAccessor: TrustedTimeClientAccessor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrustedTimeDemoApp(trustedTimeClientAccessor)
        }
    }
}
