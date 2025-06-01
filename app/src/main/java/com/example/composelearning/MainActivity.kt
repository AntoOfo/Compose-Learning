package com.example.composelearning

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.Button
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.example.composelearning.ui.theme.ComposeLearningTheme
import android.content.res.Configuration.UI_MODE_NIGHT_YES

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

// top level composable that controls which screen to show
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // rememberSaveable ensures that onBoarding screen isnt shown again if app is rotated or whatever
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    Surface(modifier) {
        // show onboarding screen or greeting screen based on state
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            Greetings()
        }
    }
}

// Onboarding screen composable
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit, // function to be called when button is clicked
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")

        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

// greets a list of names by making multiple greeting cards
@Composable
private fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    // lazy column is a scrollable column, accomodates the big list
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        // loop through names and display a greeting composable for each
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // remember the expanded state
    var expanded by rememberSaveable { mutableStateOf(false) }

    // (ANIMATED) adjust padding based on expanded state
    val extraPadding by animateDpAsState(
        if (expanded) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    // surface for card styling
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        // horizontal layout
        Row(modifier = Modifier.padding(24.dp)) {
            // left side column with text
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding.coerceAtLeast(0.dp)) // padding based on state, never go below 0.dp
            ) {
                Text(text = "Hello")
                Text(text = "$name!", style = MaterialTheme.typography.headlineMedium)
            }

            // right side button
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

// dark mode preview
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)

// preview for Greetings screen
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingsPreview() {
    ComposeLearningTheme {
        Greetings()
    }
}

// preview for Onboarding screen
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    ComposeLearningTheme {
        // Empty lambda since preview doesn't need actual behavior
        OnboardingScreen(onContinueClicked = {})
    }
}

// preview for MyApp - shows either onboarding or greetings
@Preview(showBackground = true, widthDp = 320)
@Composable
fun MyAppPreview() {
    ComposeLearningTheme {
        MyApp(Modifier.fillMaxSize())
    }
}