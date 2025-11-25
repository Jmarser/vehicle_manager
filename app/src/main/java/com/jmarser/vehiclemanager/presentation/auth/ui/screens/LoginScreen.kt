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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceLarge
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceNormal
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceSmall
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.presentation.auth.ui.components.MyClickableText
import com.jmarser.vehiclemanager.presentation.components.AppImages
import com.jmarser.vehiclemanager.presentation.components.AppImages.logo
import com.jmarser.vehiclemanager.presentation.components.ButtonWithPb
import com.jmarser.vehiclemanager.presentation.components.HeaderAuth
import com.jmarser.vehiclemanager.presentation.components.PasswordInputField
import com.jmarser.vehiclemanager.presentation.components.TextInputField
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {


    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(appDimens.paddingMedium)
            .systemBarsPadding()
            .imePadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ){
        HeaderAuth(
            modifier = Modifier,
            title = R.string.login_sesion,
            logo = logo()
        )

        VerticalSpaceLarge()


        TextInputField(
            modifier = Modifier
                .padding(horizontal = appDimens.paddingMedium),
            value = "",
            onValueChange = {},
            placeholder = R.string.email_placeholder,
            label = R.string.email,
            semanticText = R.string.semantic_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            isError = false,
            textError = R.string.error_email_invalid,
            leadingIcon = AppImages.ic_email
        )

        VerticalSpaceNormal()

        PasswordInputField(
            modifier = Modifier
                .padding(horizontal = appDimens.paddingMedium),
            value = "",
            onValueChange = {},
            label = R.string.password,
            semanticText = R.string.semantic_password,
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next,
            isError = false,
            textError = R.string.error_password_invalid,
            leadingIcon = AppImages.ic_password,
            iconShow = AppImages.ic_eye_open,
            iconHide = AppImages.ic_eye_hide,
            iconInfo = AppImages.ic_info,
            iconInfoDescription = R.string.info_password_description,
        )

        VerticalSpaceSmall()

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = appDimens.paddingMedium),
            horizontalArrangement = Arrangement.End
        ){
            TextButton(
                modifier = Modifier,
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.forgotten_yout_password),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }

        VerticalSpaceLarge()

        ButtonWithPb(
            modifier = Modifier,
            label = R.string.login,
            value = Unit,
            onClick = {},
            isEnabled = false,
            displayProgressbar = false,
            semanticDescription = R.string.semantic_button_login
        )

        VerticalSpaceNormal()

        MyClickableText(
            modifier = Modifier,
            textNormal = R.string.dont_have_account,
            textClickable = R.string.register_now,
            textDescription = R.string.clickable_text_description_login,
            onClick = {}
        )
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun LoginScreenPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        LoginScreen(modifier = Modifier)
    }
}
