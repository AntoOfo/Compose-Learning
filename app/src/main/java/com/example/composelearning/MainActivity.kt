package com.example.composelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelearning.ui.theme.ComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLearningTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

// clean up onCreate by using a separate composable to hold others that can be reused
@Composable
fun MyApp(modifier: Modifier = Modifier, names: List<String> = listOf("World", "Compose")) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background
    ) {
        // this padding is for all the whole column in the apps background
        Column(modifier = modifier.padding(vertical = 4.dp)) {
            for (name in names) {
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primary,
        // vertical and horizontal padding for space around each individual greeting card
        // basically for each individual text and its background card
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)) {
        Column(modifier = modifier
            .padding(24.dp)
            .fillMaxWidth()) {      // stretches row to fit width
            Text(text = "Hello")
            Text(text = "$name!")
        }
    }
}

@Preview(showBackground = true, widthDp = 320)  // widthDp to replicate a small phones width
@Composable
fun GreetingPreview() {
    ComposeLearningTheme {
        MyApp()
    }
}