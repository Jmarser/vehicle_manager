package com.jmarser.vehiclemanager.presentation.components


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme
import kotlinx.coroutines.launch
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInputField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholder: Int? = null,
    @StringRes label: Int? = null,
    @StringRes textError: Int? = null,
    @StringRes semanticText: Int,
    leadingIcon: ImageVector? = null,
    iconShow: ImageVector,
    iconHide: ImageVector,
    iconInfo: ImageVector? = null,
    @StringRes iconInfoDescription: Int? = null,
    iconInfoClick: () -> Unit = {},
    keyboardType: KeyboardType = KeyboardType.Password,
    imeAction: ImeAction = ImeAction.Next,
    shape: RoundedCornerShape = RoundedCornerShape(appDimens.roundedShapePercent25),
    isError: Boolean = false,
    onSendAction: () -> Unit = {}
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current
    var passVisible by remember { mutableStateOf(false) }

    val semanticMessage = stringResource(semanticText)
    val errorMessage = textError?.let { stringResource(it) }

    Column (
        modifier = modifier
            .padding(bottom = textError?.let { 0.dp } ?: appDimens.paddingMedium)
    ){
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged{focusState ->
                    if(!focusState.isFocused) keyboardController?.hide()
                }
                .semantics{
                    contentDescription = semanticMessage
                },
            value = value,
            onValueChange = onValueChange,
            placeholder = placeholder?.let {
                {
                    Text(text = stringResource(it))
                }
            },
            label = label?.let {
                {
                    Text(text = stringResource(it))
                }
            },
            singleLine = true,
            maxLines = 1,
            shape = shape,
            leadingIcon = leadingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = null

                    )
                }
            },
            trailingIcon = {
                Row (
                    modifier = Modifier
                        .wrapContentWidth(Alignment.End),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(appDimens.spacerSmall)
                ){
                    IconToggleButton(
                        checked = passVisible,
                        onCheckedChange = { passVisible = it}
                    ) {
                        Icon(
                            modifier = Modifier,
                            imageVector = if (passVisible) iconHide else iconShow,
                            contentDescription = stringResource(if (passVisible) R.string.password_hide else R.string.password_show)
                        )
                    }

                    iconInfo?.let { icon ->

                        val tooltipState = rememberTooltipState(isPersistent = true)
                        val scope = rememberCoroutineScope()

                        TooltipBox(
                            positionProvider = TooltipDefaults.rememberTooltipPositionProvider(),
                            state = tooltipState,
                            tooltip = {
                                PlainTooltip (
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(appDimens.paddingNormal)
                                        .verticalScroll(rememberScrollState()),
                                ){
                                    Column (
                                        modifier = Modifier
                                            .padding(appDimens.paddingNormal)
                                    ){
                                        Text(
                                            text = stringResource(R.string.title_message_info_password),
                                            style = MaterialTheme.typography.labelLarge,
                                        )
                                        Text(
                                            text = stringResource(R.string.info_upper_case),
                                            style = MaterialTheme.typography.labelMedium,
                                        )
                                        Text(
                                            text = stringResource(R.string.info_lower_case),
                                            style = MaterialTheme.typography.labelMedium,
                                        )
                                        Text(
                                            text = stringResource(R.string.info_one_number),
                                            style = MaterialTheme.typography.labelMedium,
                                        )
                                        Text(
                                            text = stringResource(R.string.info_caracter_special),
                                            style = MaterialTheme.typography.labelMedium,
                                        )
                                        Text(
                                            text = stringResource(R.string.info_min_character),
                                            style = MaterialTheme.typography.labelMedium,
                                        )
                                    }
                                }
                            },
                            onDismissRequest = {tooltipState.dismiss()},
                            focusable = true,
                            hasAction = false
                        ) {
                            IconButton(
                                onClick = {
                                    scope.launch {
                                        if (tooltipState.isVisible) tooltipState.dismiss() else tooltipState.show()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = iconInfoDescription?.let { stringResource(it) }
                                )
                            }
                        }
                    }

                }
            },
            visualTransformation = if (passVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(
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
                    onSendAction()
                }
            ),
            isError = isError,
        )

        errorMessage?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = appDimens.paddingMedium),
                text = it,
                textAlign = TextAlign.End,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun PasswordInputFieldPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        PasswordInputField(
            modifier = Modifier,
            value = "",
            onValueChange = {},
            label = R.string.password,
            semanticText = R.string.semantic_email,
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
    }
}
