package com.example.trustedtimeexample

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trustedtimeexample.di.TrustedTimeClientAccessor
import com.google.android.gms.time.TrustedTimeClient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrustedTimeDemoApp(trustedTimeClientAccessor: TrustedTimeClientAccessor) {
    var trustedTimeMillis by remember { mutableStateOf<Long?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Trigger the task to create TrustedTimeClient and compute time.
    LaunchedEffect(trustedTimeClientAccessor) {
        trustedTimeClientAccessor.createClient().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val trustedTimeClient: TrustedTimeClient = task.result
                // Compute the current trusted time.
                trustedTimeMillis = trustedTimeClient.computeCurrentUnixEpochMillis()
            } else {
                errorMessage = "Error obtaining TrustedTimeClient."
            }
        }
    }

    // Get the current system time.
    val systemTimeMillis = System.currentTimeMillis()
    val timeDiff = trustedTimeMillis?.let { it - systemTimeMillis } ?: 0L

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("TrustedTime Demo") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                errorMessage != null -> {
                    Text(text = errorMessage!!)
                }
                trustedTimeMillis != null -> {
                    Text(text = "Trusted Time: $trustedTimeMillis")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "System Time: $systemTimeMillis")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Difference: $timeDiff ms")
                }
                else -> {
                    Text(text = "Fetching Trusted Time...")
                }
            }
        }
    }
}
