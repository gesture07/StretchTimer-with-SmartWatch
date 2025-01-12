/* While this template provides a good starting point for using Wear Compose, you can always
 * take a look at https://github.com/android/wear-os-samples/tree/main/ComposeStarter and
 * https://github.com/android/wear-os-samples/tree/main/ComposeAdvanced to find the most up to date
 * changes to the libraries and their usages.
 */

package com.example.stretchtimer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.LocalTextStyle
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import com.example.stretchtimer.R
import com.example.stretchtimer.presentation.theme.StretchTimerTheme

import androidx.wear.compose.material.*
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Wear OS 앱의 초기 화면 설정
            StretchTimerTheme {
                TimerInputScreen()
//                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // 화면 중앙에 배치
    ) {
        Text(
            text = "Hello World", // 표시할 텍스트
//            style = MaterialTheme.typography.h4 // Material Design에서 제공하는 스타일을 사용
        )
    }
}

@Composable
fun TimerInputScreen() {
    var timerInput by remember { mutableStateOf("00:00") }
    var isValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input Field
        BasicTextField(
            value = timerInput,
            onValueChange = { newValue ->
                if (validateTimerInput(newValue)) {
                    timerInput = newValue
                    isValid = true
                } else {
                    isValid = false
                }
            },
            modifier = Modifier
                .width(100.dp)
                .padding(8.dp),
//                .background(MaterialTheme.colorScheme.surface),
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Center
//                fontSize = MaterialTheme.typography.bodyLarge.fontSize
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Validation Message
        if (!isValid) {
            Text(
                text = "Invalid format! Use MM:SS"
//                color = MaterialTheme.colorScheme.error,
//                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Selected Time
        Button(onClick = {
            // Handle timer start
            println("Timer set to: $timerInput")
        }) {
            Text(text = "Start Timer")
        }
    }
}

// Helper Function to Validate Timer Input
fun validateTimerInput(input: String): Boolean {
    val regex = Regex("^\\d{2}:\\d{2}$")
    return regex.matches(input)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StretchTimerTheme {
        MainScreen() // 미리보기 화면에 동일한 UI를 보여줌
    }
}