package com.jmarser.vehiclemanager.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun TextInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholder: Int? = null,
    @StringRes label: Int? = null,
    @StringRes textError: Int? = null,
    @StringRes semanticText: Int,
    leadingIcon: ImageVector? = null,
    trealingIcon: ImageVector? = null,
    @StringRes trealingIconDescription: Int? = null,
    trealingIconClick: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    shape: RoundedCornerShape = RoundedCornerShape(appDimens.roundedShapePercent25),
    isError: Boolean = false,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val semanticsMessage = stringResource(semanticText)

    Column(
        modifier = modifier,
    ) {
        OutlinedTextField(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = textError?.let { 0.dp } ?: appDimens.paddingMedium)
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused) {
                            keyboardController?.hide()
                        }
                    }.semantics {
                        contentDescription = semanticsMessage
                    },
            value = value,
            onValueChange = onValueChange,
            placeholder =
                placeholder?.let {
                    { Text(text = stringResource(it)) }
                },
            label =
                label?.let {
                    {
                        Text(text = stringResource(it))
                    }
                },
            shape = shape,
            leadingIcon =
                leadingIcon?.let {
                    {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                        )
                    }
                },
            trailingIcon =
                trealingIcon?.let {
                    {
                        IconButton(
                            onClick = trealingIconClick,
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = trealingIconDescription?.let { stringResource(it) },
                            )
                        }
                    }
                },
            keyboardOptions =
                KeyboardOptions.Default.copy(
                    keyboardType = keyboardType,
                    imeAction = imeAction,
                ),
            keyboardActions =
                KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                    onGo = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                    onNext = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                    onSend = {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    },
                ),
            isError = isError,
        )

        textError?.let {
            Text(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(end = appDimens.paddingMedium),
                text = stringResource(it),
                textAlign = TextAlign.End,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error,
            )
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
)
@Composable
fun TextInputFieldPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone(),
    ) {
        TextInputField(
            modifier = Modifier,
            value = "",
            onValueChange = {},
            placeholder = R.string.email_placeholder,
            label = R.string.email,
            semanticText = R.string.semantic_email,
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next,
            isError = false,
            textError = R.string.error_email_invalid,
            leadingIcon = AppImages.ic_email,
        )
    }
}
