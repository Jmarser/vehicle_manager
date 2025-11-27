package com.jmarser.vehiclemanager.presentation.auth.ui.screens


import android.app.Activity
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceLarge
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceMedium
import com.jmarser.vehiclemanager.core.presentation.ui.DeviceOrientation
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForTablet
import com.jmarser.vehiclemanager.core.presentation.ui.rememberDeviceOrientation
import com.jmarser.vehiclemanager.core.utils.TestTags
import com.jmarser.vehiclemanager.presentation.components.AppImages
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val activity = context as Activity

    DisposableEffect(Unit) {
        val windowInsetsController = WindowCompat.getInsetsController(
            activity.window, activity.window.decorView
        )

        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())

        onDispose {
            windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        }
    }

    var startAnimation by remember { mutableStateOf(false) }

    val alphaAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(1500)
    )
    val scaleAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1f else .8f,
        animationSpec = tween(1500)
    )

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    val orientation = rememberDeviceOrientation()

    when(orientation){
        DeviceOrientation.Portrait -> SplashScreenPhone(
            modifier = modifier,
            alphaAnim = alphaAnim,
            scaleAnim = scaleAnim
        )
        DeviceOrientation.Landscape,
        DeviceOrientation.Undefined -> SplashScreenTablet(
            modifier = modifier,
            alphaAnim = alphaAnim,
            scaleAnim = scaleAnim
        )
    }


}

@Composable
fun SplashScreenPhone(
    modifier: Modifier = Modifier,
    alphaAnim: Float,
    scaleAnim: Float
){
    Column (
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier
                .padding(top = appDimens.paddingXL)
                .alpha(alphaAnim)
                .scale(scaleAnim)
                .testTag(TestTags.NAME_APP_SPLASH),
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(Modifier.weight(.8f))
        Image(
            modifier = Modifier
                .size(appDimens.logoSize)
                .alpha(alphaAnim)
                .scale(scaleAnim),
            painter = AppImages.logo(),
            contentDescription = null
        )
        VerticalSpaceMedium()
        Text(
            modifier = Modifier
                .testTag(TestTags.SLOGAN_APP_SPLASH)
                .alpha(alphaAnim)
                .scale(scaleAnim),
            text = stringResource(R.string.slogan_app),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(Modifier.weight(1.2f))
        Text(
            modifier = Modifier
                .padding(bottom = appDimens.paddingNormal)
                .alpha(alphaAnim)
                .scale(scaleAnim)
                .testTag(TestTags.DEVELOPER_NAME_SPLASH),
            text = stringResource(R.string.developer_name),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun SplashScreenTablet(
    modifier: Modifier = Modifier,
    alphaAnim: Float,
    scaleAnim: Float
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = appDimens.paddingLarge),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ){
            Column (
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    modifier = Modifier
                        .alpha(alphaAnim)
                        .scale(scaleAnim)
                        .testTag(TestTags.NAME_APP_SPLASH),
                    text = stringResource(R.string.app_name),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineLarge
                )
                VerticalSpaceLarge()
                Text(
                    modifier = Modifier
                        .alpha(alphaAnim)
                        .scale(scaleAnim)
                        .testTag(TestTags.SLOGAN_APP_SPLASH),
                    text = stringResource(R.string.slogan_app),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Column (
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    modifier = Modifier
                        .size(appDimens.logoSize)
                        .alpha(alphaAnim)
                        .scale(scaleAnim),
                    painter = AppImages.logo(),
                    contentDescription = null
                )
            }
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = appDimens.paddingMedium),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ){
            Text(
                modifier = Modifier
                    .padding(bottom = appDimens.paddingNormal)
                    .alpha(alphaAnim)
                    .scale(scaleAnim)
                    .testTag(TestTags.DEVELOPER_NAME_SPLASH),
                text = stringResource(R.string.developer_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun SplashScreenPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForTablet()
    ) {
        SplashScreen(modifier = Modifier)
    }
}
