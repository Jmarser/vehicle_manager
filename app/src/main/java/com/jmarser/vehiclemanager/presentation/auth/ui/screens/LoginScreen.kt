package com.jmarser.vehiclemanager.presentation.auth.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceLarge
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceNormal
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceSmall
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.presentation.components.AppImages
import com.jmarser.vehiclemanager.presentation.components.AppImages.logo
import com.jmarser.vehiclemanager.presentation.components.HeaderAuth
import com.jmarser.vehiclemanager.presentation.components.TextInputField
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier
) {

    var passwordVisible by remember { mutableStateOf(false) }

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

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = appDimens.paddingMedium),
            value = "",
            onValueChange = {},
            label = { Text(text = "Password") },
            singleLine = true,
            shape = RoundedCornerShape(appDimens.roundedShapePercent25),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            )
        )

        VerticalSpaceSmall()

        Text(
            text = "¿Has olvidado tu contraseña?",
            style = MaterialTheme.typography.labelMedium
        )

        VerticalSpaceLarge()

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(appDimens.buttonHeightNormal)
                .padding(horizontal = appDimens.paddingXL),
            onClick = {},
            shape = RoundedCornerShape(appDimens.roundedShapePercent50)
        ) {
            Text(
                text = "Iniciar sesión",
            )
        }

        VerticalSpaceNormal()
        
        Text(
            text = "¿No tienes una cuenta? Regístrate",
            style = MaterialTheme.typography.labelMedium
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
