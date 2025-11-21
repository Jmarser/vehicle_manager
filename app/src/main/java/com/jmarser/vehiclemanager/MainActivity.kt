package com.jmarser.vehiclemanager

import android.bluetooth.BluetoothClass
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.stylusHoverIcon
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.jmarser.vehiclemanager.ui.theme.VehicleManagerTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val windowSizeClass = calculateWindowSizeClass(this)

            VehicleManagerTheme(
                windowSizeClass = windowSizeClass
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){     Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.displayLarge
    )
        Text(
            text = "Nuevo Texto",
            style = MaterialTheme.typography.bodyLarge
        ) }

}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun GreetingPreview() {
    VehicleManagerTheme (
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(360.dp, 800.dp))
    ){
        Greeting("Android")
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true,
    showSystemUi = true,
    device = Devices.TABLET)
@Composable
fun GreetingPreview2() {
    VehicleManagerTheme (
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(800.dp, 1280.dp))
    ){
        Greeting("Android")
    }
}