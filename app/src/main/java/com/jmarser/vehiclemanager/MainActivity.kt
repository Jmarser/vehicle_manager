package com.jmarser.vehiclemanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceNormal
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForTablet
import com.jmarser.vehiclemanager.presentation.navigation.AppNavGraph
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)

            MyAppTheme(
                windowSizeClass = windowSizeClass,
            ) {
                val navController = rememberNavController()
                AppNavGraph(
                    modifier = Modifier,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Hello $name!",
            modifier = modifier,
            style = MaterialTheme.typography.displayLarge,
        )
        VerticalSpaceNormal()
        Text(
            text = "Nuevo Texto",
            style = MaterialTheme.typography.bodyLarge,
        )
        VerticalSpaceNormal()
        Button(
            modifier = Modifier.padding(top = 24.dp),
            onClick = {},
        ) {
            Text(
                text = "Prueba Button",
            )
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    showBackground = true,
    showSystemUi = true,
)
@Composable
fun GreetingPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone(),
    ) {
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.TABLET,
)
@Composable
fun GreetingPreview2() {
    MyAppTheme(
        windowSizeClass = getSizeForTablet(),
    ) {
        Greeting("Android")
    }
}
