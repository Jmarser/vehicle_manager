package com.jmarser.vehiclemanager.presentation.auth.ui.screens


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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceLarge
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceNormal
import com.jmarser.vehiclemanager.core.presentation.ui.DeviceOrientation
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForTablet
import com.jmarser.vehiclemanager.core.presentation.ui.rememberDeviceOrientation
import com.jmarser.vehiclemanager.presentation.auth.ui.components.MyClickableText
import com.jmarser.vehiclemanager.presentation.components.AppImages
import com.jmarser.vehiclemanager.presentation.components.ButtonWithPb
import com.jmarser.vehiclemanager.presentation.components.HeaderAuth
import com.jmarser.vehiclemanager.presentation.components.PasswordInputField
import com.jmarser.vehiclemanager.presentation.components.TextInputField
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {

    val orientation = rememberDeviceOrientation()

    when(orientation){
        DeviceOrientation.Portrait -> RegisterScreenPhone(modifier = modifier)
        DeviceOrientation.Landscape,
                DeviceOrientation.Undefined -> RegisterScreenTablet(modifier = modifier)
    }

}

@Composable
fun RegisterScreenPhone(
    modifier: Modifier = Modifier
){

    Column (
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .imePadding()
            .padding(horizontal = appDimens.paddingMedium),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            IconButton(
                onClick = { },
            ) {
                Icon(
                    imageVector = AppImages.ic_back,
                    contentDescription = stringResource(R.string.back_to_login)
                )
            }
        }
        HeaderAuth(
            title = R.string.register_user,
            logo = AppImages.logo()
        )

        VerticalSpaceLarge()

        TextInputField(
            modifier = Modifier
                .padding(horizontal = appDimens.paddingMedium)
                .testTag(""),
            value = "",
            onValueChange = {},
            label = R.string.user_name,
            semanticText = R.string.semantic_user_name,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next,
            isError = false,
            leadingIcon = AppImages.ic_user
        )

        TextInputField(
            modifier = Modifier
                .padding(horizontal = appDimens.paddingMedium)
                .testTag(""),
            value = "",
            onValueChange = {},
            label = R.string.email,
            semanticText = R.string.semantic_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            isError = false,
            leadingIcon = AppImages.ic_email
        )

        PasswordInputField(
            modifier = Modifier
                .padding(horizontal = appDimens.paddingMedium)
                .testTag(""),
            value = "",
            onValueChange = {},
            label = R.string.password,
            semanticText = R.string.semantic_password,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            isError = false,
            leadingIcon = AppImages.ic_password,
            iconShow = AppImages.ic_eye_open,
            iconHide = AppImages.ic_eye_hide,
            iconInfo = AppImages.ic_info,
            iconInfoDescription = R.string.info_password_description
        )

        PasswordInputField(
            modifier = Modifier
                .padding(horizontal = appDimens.paddingMedium)
                .testTag(""),
            value = "",
            onValueChange = {},
            label = R.string.repit_password,
            semanticText = R.string.semantic_password,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            isError = false,
            leadingIcon = AppImages.ic_password,
            iconShow = AppImages.ic_eye_open,
            iconHide = AppImages.ic_eye_hide,
        )

        VerticalSpaceLarge()

        ButtonWithPb(
            modifier = Modifier,
            isEnabled = false,
            label = R.string.register,
            displayProgressbar = false,
            onClick = {},
            value = Unit,
            semanticDescription = R.string.semantic_button_register
        )

        MyClickableText(
            modifier = Modifier,
            textNormal = R.string.have_account,
            textClickable = R.string.signIn,
            textDescription = R.string.clickable_text_description_register,
            onClick = {}
        )
    }
}

@Composable
fun RegisterScreenTablet(
    modifier: Modifier = Modifier
){
    Column (
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            IconButton(
                onClick = { },
            ) {
                Icon(
                    imageVector = AppImages.ic_back,
                    contentDescription = stringResource(R.string.back_to_login)
                )
            }
        }

        Row(
            modifier = modifier
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
                        .testTag(""),
                    value = "",
                    onValueChange = {},
                    label = R.string.user_name,
                    semanticText = R.string.semantic_user_name,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    isError = false,
                    leadingIcon = AppImages.ic_user
                )

                TextInputField(
                    modifier = Modifier
                        .padding(horizontal = appDimens.paddingMedium)
                        .testTag(""),
                    value = "",
                    onValueChange = {},
                    label = R.string.email,
                    semanticText = R.string.semantic_email,
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                    isError = false,
                    leadingIcon = AppImages.ic_email
                )

                PasswordInputField(
                    modifier = Modifier
                        .padding(horizontal = appDimens.paddingMedium)
                        .testTag(""),
                    value = "",
                    onValueChange = {},
                    label = R.string.password,
                    semanticText = R.string.semantic_password,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                    isError = false,
                    leadingIcon = AppImages.ic_password,
                    iconShow = AppImages.ic_eye_open,
                    iconHide = AppImages.ic_eye_hide,
                    iconInfo = AppImages.ic_info,
                    iconInfoDescription = R.string.info_password_description
                )

                PasswordInputField(
                    modifier = Modifier
                        .padding(horizontal = appDimens.paddingMedium)
                        .testTag(""),
                    value = "",
                    onValueChange = {},
                    label = R.string.repit_password,
                    semanticText = R.string.semantic_password,
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                    isError = false,
                    leadingIcon = AppImages.ic_password,
                    iconShow = AppImages.ic_eye_open,
                    iconHide = AppImages.ic_eye_hide,
                )

                VerticalSpaceLarge()

                ButtonWithPb(
                    modifier = Modifier,
                    isEnabled = false,
                    label = R.string.register,
                    displayProgressbar = false,
                    onClick = {},
                    value = Unit,
                    semanticDescription = R.string.semantic_button_register
                )

                MyClickableText(
                    modifier = Modifier,
                    textNormal = R.string.have_account,
                    textClickable = R.string.signIn,
                    textDescription = R.string.clickable_text_description_register,
                    onClick = {}
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
fun RegisterScreenPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        RegisterScreenPhone(modifier = Modifier)
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.FOLDABLE
)
@Composable
fun RegisterScreenPreview2() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        RegisterScreenPhone(modifier = Modifier)
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    device = Devices.TABLET
)
@Composable
fun RegisterScreenPreview3() {
    MyAppTheme(
        windowSizeClass = getSizeForTablet()
    ) {
        RegisterScreenTablet(modifier = Modifier)
    }
}
