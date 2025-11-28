package com.jmarser.vehiclemanager.presentation.auth.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceLarge
import com.jmarser.vehiclemanager.core.presentation.ui.DeviceOrientation
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForTablet
import com.jmarser.vehiclemanager.core.presentation.ui.rememberDeviceOrientation
import com.jmarser.vehiclemanager.core.utils.TestTags
import com.jmarser.vehiclemanager.presentation.components.AppImages
import com.jmarser.vehiclemanager.presentation.components.ButtonWithPb
import com.jmarser.vehiclemanager.presentation.components.HeaderAuth
import com.jmarser.vehiclemanager.presentation.components.TextInputField
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun ForgotPasswordScreen(
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit
) {

    val semantic = stringResource(R.string.semantic_button_back)
    val orientation = rememberDeviceOrientation()

    when (orientation) {
        DeviceOrientation.Landscape -> ForgotPasswordScreenTablet(
            modifier = modifier,
            semanticBackButton = semantic,
            navigateToLogin = navigateToLogin
        )

        DeviceOrientation.Portrait,
        DeviceOrientation.Undefined -> ForgotPasswordScreenPhone(
            modifier = modifier,
            semanticBackButton = semantic,
            navigateToLogin = navigateToLogin
        )
    }
}

@Composable
fun ForgotPasswordScreenPhone(
    modifier: Modifier = Modifier,
    semanticBackButton: String,
    navigateToLogin: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(
                modifier = Modifier
                    .semantics {
                        contentDescription = semanticBackButton
                    }
                    .testTag(TestTags.ON_BACK_BUTTON_FORGOT),
                onClick = {
                    navigateToLogin()
                }
            ) {
                Icon(
                    imageVector = AppImages.ic_back,
                    contentDescription = null
                )
            }
        }

        HeaderAuth(
            modifier = Modifier
                .testTag(TestTags.HEADER_FORGOT),
            title = R.string.forgot_password,
            logo = AppImages.logo()
        )

        VerticalSpaceLarge()

        TextInputField(
            modifier = Modifier
                .padding(horizontal = appDimens.paddingMedium)
                .testTag(TestTags.EMAIL_INPUT_FORGOT),
            value = "",
            onValueChange = { },
            label = R.string.email,
            placeholder = R.string.email_placeholder,
            semanticText = R.string.semantic_email,
            leadingIcon = AppImages.ic_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Send
        )

        Text(
            modifier = Modifier
                .padding(horizontal = appDimens.paddingXL)
                .testTag(TestTags.MESSAGE_INFO_FORGOT),
            text = stringResource(R.string.message_screen_forgot),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Start
            )
        )

        VerticalSpaceLarge()

        ButtonWithPb(
            modifier = Modifier
                .testTag(TestTags.FORGOT_BUTTON),
            label = R.string.send,
            value = Unit,
            onClick = { },
            isEnabled = false,
            displayProgressbar = false,
            semanticDescription = R.string.semantic_button_forgot
        )
    }
}

@Composable
fun ForgotPasswordScreenTablet(
    modifier: Modifier = Modifier,
    semanticBackButton: String,
    navigateToLogin: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            IconButton(
                modifier = Modifier
                    .semantics {
                        contentDescription = semanticBackButton
                    }
                    .testTag(TestTags.ON_BACK_BUTTON_FORGOT),
                onClick = {
                    navigateToLogin()
                }
            ) {
                Icon(
                    imageVector = AppImages.ic_back,
                    contentDescription = null
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = appDimens.paddingLarge),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                HeaderAuth(
                    modifier = Modifier
                        .testTag(TestTags.HEADER_REGISTER),
                    title = R.string.register_user,
                    logo = AppImages.logo()
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TextInputField(
                    modifier = Modifier
                        .padding(horizontal = appDimens.paddingMedium)
                        .testTag(TestTags.EMAIL_INPUT_FORGOT),
                    value = "",
                    onValueChange = { },
                    label = R.string.email,
                    placeholder = R.string.email_placeholder,
                    semanticText = R.string.semantic_email,
                    leadingIcon = AppImages.ic_email,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Send
                )

                Text(
                    modifier = Modifier
                        .padding(horizontal = appDimens.paddingXL)
                        .testTag(TestTags.MESSAGE_INFO_FORGOT),
                    text = stringResource(R.string.message_screen_forgot),
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Start
                    )
                )

                VerticalSpaceLarge()

                ButtonWithPb(
                    modifier = Modifier
                        .testTag(TestTags.FORGOT_BUTTON),
                    label = R.string.send,
                    value = Unit,
                    onClick = { },
                    isEnabled = false,
                    displayProgressbar = false,
                    semanticDescription = R.string.semantic_button_forgot
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PHONE
)
@Composable
fun ForgotPasswordScreenPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        ForgotPasswordScreen(modifier = Modifier, navigateToLogin = {})
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.FOLDABLE
)
@Composable
fun ForgotPasswordScreenPreview2() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        ForgotPasswordScreen(modifier = Modifier, navigateToLogin = {})
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun ForgotPasswordScreenPreview3() {
    MyAppTheme(
        windowSizeClass = getSizeForTablet()
    ) {
        ForgotPasswordScreen(modifier = Modifier, navigateToLogin = {})
    }
}
