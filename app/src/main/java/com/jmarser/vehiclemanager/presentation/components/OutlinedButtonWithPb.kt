package com.jmarser.vehiclemanager.presentation.components

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun <T> OutlinedButtonWithPb(
    modifier: Modifier = Modifier,
    @StringRes textbtn: Int? = null,
    iconStart: ImageVector? = null,
    iconEnd: ImageVector? = null,
    displayProgressbar: Boolean = false,
    isEnabled: Boolean = true,
    semanticMessage: String? = null,
    shape: RoundedCornerShape = RoundedCornerShape(appDimens.roundedShapePercent50),
    value: T,
    onClick: (T) -> Unit,
) {
    val buttonSemantics =
        Modifier.semantics {
            role = Role.Button
            contentDescription = semanticMessage ?: "Button"
        }

    AnimatedContent(
        targetState = displayProgressbar,
        transitionSpec = {
            fadeIn(tween(250)) + scaleIn(tween(250)) togetherWith fadeOut(tween(250)) + scaleOut(tween(250))
        },
        label = "AnimatedButton",
    ) { isLoading ->
        if (isLoading) {
            CircularProgressIndicator(
                modifier =
                    modifier
                        .size(appDimens.buttonHeightNormal)
                        .padding(appDimens.paddingMedium),
                strokeWidth = appDimens.borderNormal,
            )
        } else {
            OutlinedButton(
                modifier =
                    modifier
                        .height(appDimens.buttonHeightNormal)
                        .padding(horizontal = appDimens.paddingMedium)
                        .then(buttonSemantics),
                shape = shape,
                onClick = { onClick(value) },
                enabled = isEnabled,
                border = ButtonDefaults.outlinedButtonBorder().copy(width = appDimens.borderNormal),
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    iconStart?.let { icon ->
                        Icon(
                            modifier =
                                Modifier
                                    .padding(end = appDimens.paddingSmall),
                            imageVector = icon,
                            contentDescription = null,
                        )
                    }

                    textbtn?.let { text ->
                        Text(
                            text = stringResource(id = text),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }

                    iconEnd?.let { icon ->
                        Icon(
                            modifier =
                                Modifier
                                    .padding(start = appDimens.paddingSmall),
                            imageVector = icon,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
)
@Composable
fun OutlinedButtonWithPbPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone(),
    ) {
        OutlinedButtonWithPb(
            modifier = Modifier,
            iconStart = AppImages.ic_email,
            iconEnd = AppImages.ic_info,
            textbtn = R.string.login,
            value = Unit,
            displayProgressbar = false,
            onClick = {},
        )
    }
}
