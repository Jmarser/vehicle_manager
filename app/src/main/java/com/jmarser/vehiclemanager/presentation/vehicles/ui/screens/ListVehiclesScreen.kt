package com.jmarser.vehiclemanager.presentation.vehicles.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun ListVehiclesScreen(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Listado de vehículos",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ListVehiclesScreenPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        ListVehiclesScreen(modifier = Modifier)
    }
}
