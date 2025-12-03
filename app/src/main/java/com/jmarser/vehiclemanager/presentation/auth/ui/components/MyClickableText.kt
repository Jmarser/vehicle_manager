package com.jmarser.vehiclemanager.presentation.auth.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.core.utils.TestTags
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun MyClickableText(
    modifier: Modifier = Modifier,
    @StringRes textNormal: Int,
    @StringRes textClickable: Int,
    @StringRes textDescription: Int,
    onClick: () -> Unit,
) {
    val normalText = stringResource(textNormal)
    val clickableText = stringResource(textClickable)
    val descriptionText = stringResource(textDescription)

    Row(
        modifier =
            modifier
                .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = normalText,
            style = MaterialTheme.typography.labelMedium,
        )

        TextButton(
            modifier =
                Modifier
                    .semantics {
                        testTag = TestTags.CLICKABLE_TEXT_COMPONENT
                        role = Role.Button
                        contentDescription = descriptionText
                    },
            onClick = onClick,
        ) {
            Text(
                text = clickableText,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
            )
        }
    }
}

@Preview(
    showSystemUi = false,
    showBackground = true,
)
@Composable
fun MyClickableTextPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone(),
    ) {
        MyClickableText(
            modifier = Modifier,
            textNormal = R.string.dont_have_account,
            textClickable = R.string.register_now,
            textDescription = R.string.clickable_text_description_login,
            onClick = {},
        )
    }
}
