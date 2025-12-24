package com.jmarser.vehiclemanager.presentation.auth.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceLarge
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceNormal
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceSmall
import com.jmarser.vehiclemanager.core.presentation.ui.DeviceOrientation
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForTablet
import com.jmarser.vehiclemanager.core.presentation.ui.rememberDeviceOrientation
import com.jmarser.vehiclemanager.core.utils.TestTags
import com.jmarser.vehiclemanager.presentation.auth.ui.components.MyClickableText
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.login.LoginEffect
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.login.LoginEvent
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.login.LoginFormState
import com.jmarser.vehiclemanager.presentation.auth.viewmodel.login.LoginViewModel
import com.jmarser.vehiclemanager.presentation.components.AppImages
import com.jmarser.vehiclemanager.presentation.components.AppImages.logo
import com.jmarser.vehiclemanager.presentation.components.ButtonWithPb
import com.jmarser.vehiclemanager.presentation.components.HeaderAuth
import com.jmarser.vehiclemanager.presentation.components.PasswordInputField
import com.jmarser.vehiclemanager.presentation.components.TextInputField
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToRegister: () -> Unit,
    navigateToHome: () -> Unit,
    navigateToForgotPassword: () -> Unit,
) {
    val context = LocalContext.current
    val formState by viewModel.formState.collectAsStateWithLifecycle()
    val orientation = rememberDeviceOrientation()

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                LoginEffect.NavigateToHome -> navigateToHome()
                LoginEffect.NavigateToForgotPassword -> navigateToForgotPassword()
                LoginEffect.NavigateToRegister -> navigateToRegister()
                is LoginEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    when (orientation) {
        DeviceOrientation.Portrait ->
            LoginScreenPhone(
                modifier = modifier,
                viewModel = viewModel,
                formState = formState,
            )
        DeviceOrientation.Landscape,
        DeviceOrientation.Undefined,
        ->
            LoginScreenTablet(
                modifier = modifier,
                viewModel = viewModel,
                formState = formState,
            )
    }
}

@Composable
fun LoginScreenPhone(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    formState: LoginFormState,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(appDimens.paddingMedium)
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        HeaderAuth(
            modifier =
                Modifier
                    .testTag(TestTags.HEADER_LOGIN),
            title = R.string.login_sesion,
            logo = logo(),
        )

        VerticalSpaceLarge()

        TextInputField(
            modifier =
                Modifier
                    .padding(horizontal = appDimens.paddingMedium)
                    .testTag(TestTags.EMAIL_INPUT_LOGIN),
            value = formState.email,
            onValueChange = {
                viewModel.onEvent(LoginEvent.SetEmail(it))
            },
            placeholder = R.string.email_placeholder,
            label = R.string.email,
            semanticText = R.string.semantic_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            isError = (formState.isEmailValid == false),
            textError = formState.emailErrorMessage,
            leadingIcon = AppImages.ic_email,
        )

        VerticalSpaceNormal()

        PasswordInputField(
            modifier =
                Modifier
                    .padding(horizontal = appDimens.paddingMedium)
                    .testTag(TestTags.PASSWORD_INPUT_LOGIN),
            value = formState.password,
            onValueChange = {
                viewModel.onEvent(LoginEvent.SetPassword(it))
            },
            label = R.string.password,
            semanticText = R.string.semantic_password,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            isError = (formState.isPasswordValid == false),
            textError = formState.passwordErrorMessage,
            leadingIcon = AppImages.ic_password,
            iconShow = AppImages.ic_eye_open,
            iconHide = AppImages.ic_eye_hide,
            iconInfo = AppImages.ic_info,
            iconInfoDescription = R.string.info_password_description,
        )

        VerticalSpaceSmall()

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = appDimens.paddingMedium),
            horizontalArrangement = Arrangement.End,
        ) {
            TextButton(
                modifier =
                    Modifier
                        .testTag(TestTags.FORGOT_PASSWORD_BUTTON),
                onClick = {
                    viewModel.onEvent(LoginEvent.OnForgotPasswordClick)
                },
            ) {
                Text(
                    text = stringResource(R.string.fogotten_your_password),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }

        VerticalSpaceLarge()

        ButtonWithPb(
            modifier =
                Modifier
                    .testTag(TestTags.LOGIN_BUTTON),
            label = R.string.login,
            value = Unit,
            onClick = {
                viewModel.onEvent(LoginEvent.OnLoginClick)
            },
            isEnabled = formState.isButtonEnabled,
            displayProgressbar = formState.isLoading,
            semanticDescription = R.string.semantic_button_login,
        )

        VerticalSpaceNormal()

        MyClickableText(
            modifier =
                Modifier
                    .testTag(TestTags.REGISTER_LINK),
            textNormal = R.string.dont_have_account,
            textClickable = R.string.register_now,
            textDescription = R.string.clickable_text_description_login,
            onClick = {
                viewModel.onEvent(LoginEvent.OnRegisterClick)
            },
        )
    }
}

@Composable
fun LoginScreenTablet(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel,
    formState: LoginFormState,
) {
    Row(
        modifier =
            modifier
                .fillMaxSize()
                .systemBarsPadding()
                .padding(horizontal = appDimens.paddingLarge),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        Column(
            modifier =
                Modifier
                    .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            HeaderAuth(
                modifier =
                    Modifier
                        .testTag(TestTags.HEADER_LOGIN),
                title = R.string.login_sesion,
                logo = logo(),
            )
        }
        Column(
            modifier =
                Modifier
                    .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            TextInputField(
                modifier =
                    Modifier
                        .padding(horizontal = appDimens.paddingMedium)
                        .testTag(TestTags.EMAIL_INPUT_LOGIN),
                value = formState.email,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.SetEmail(it))
                },
                placeholder = R.string.email_placeholder,
                label = R.string.email,
                semanticText = R.string.semantic_email,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next,
                isError = (formState.isEmailValid == false),
                textError = formState.emailErrorMessage,
                leadingIcon = AppImages.ic_email,
            )

            VerticalSpaceNormal()

            PasswordInputField(
                modifier =
                    Modifier
                        .padding(horizontal = appDimens.paddingMedium)
                        .testTag(TestTags.PASSWORD_INPUT_LOGIN),
                value = formState.password,
                onValueChange = {
                    viewModel.onEvent(LoginEvent.SetPassword(it))
                },
                label = R.string.password,
                semanticText = R.string.semantic_password,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next,
                isError = (formState.isPasswordValid == false),
                textError = formState.passwordErrorMessage,
                leadingIcon = AppImages.ic_password,
                iconShow = AppImages.ic_eye_open,
                iconHide = AppImages.ic_eye_hide,
                iconInfo = AppImages.ic_info,
                iconInfoDescription = R.string.info_password_description,
            )

            VerticalSpaceSmall()

            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = appDimens.paddingMedium),
                horizontalArrangement = Arrangement.End,
            ) {
                TextButton(
                    modifier =
                        Modifier
                            .testTag(TestTags.FORGOT_PASSWORD_BUTTON),
                    onClick = {
                        viewModel.onEvent(LoginEvent.OnForgotPasswordClick)
                    },
                ) {
                    Text(
                        text = stringResource(R.string.fogotten_your_password),
                        textAlign = TextAlign.End,
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }

            VerticalSpaceLarge()

            ButtonWithPb(
                modifier =
                    Modifier
                        .testTag(TestTags.LOGIN_BUTTON),
                label = R.string.login,
                value = Unit,
                onClick = {
                    viewModel.onEvent(LoginEvent.OnLoginClick)
                },
                isEnabled = false,
                displayProgressbar = false,
                semanticDescription = R.string.semantic_button_login,
            )

            VerticalSpaceNormal()

            MyClickableText(
                modifier =
                    Modifier
                        .testTag(TestTags.REGISTER_LINK),
                textNormal = R.string.dont_have_account,
                textClickable = R.string.register_now,
                textDescription = R.string.clickable_text_description_login,
                onClick = {
                    viewModel.onEvent(LoginEvent.OnRegisterClick)
                },
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.PHONE,
)
@Composable
fun LoginScreenPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone(),
    ) {
        LoginScreen(
            modifier = Modifier,
            navigateToHome = {},
            navigateToRegister = {},
            navigateToForgotPassword = {},
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.FOLDABLE,
)
@Composable
fun LoginScreenPreview3() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone(),
    ) {
        LoginScreen(
            modifier = Modifier,
            navigateToHome = {},
            navigateToRegister = {},
            navigateToForgotPassword = {},
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.TABLET,
)
@Composable
fun LoginScreenPreview2() {
    MyAppTheme(
        windowSizeClass = getSizeForTablet(),
    ) {
        LoginScreen(
            modifier = Modifier,
            navigateToHome = {},
            navigateToRegister = {},
            navigateToForgotPassword = {},
        )
    }
}
