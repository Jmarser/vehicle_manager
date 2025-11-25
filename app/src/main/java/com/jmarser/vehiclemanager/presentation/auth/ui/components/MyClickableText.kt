package com.jmarser.vehiclemanager.presentation.auth.ui.components


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme

@Composable
fun MyClickableText(
    modifier: Modifier = Modifier,
    @StringRes textNormal: Int,
    @StringRes textClickable: Int,
    @StringRes textDescription: Int,
    onClick: () -> Unit
) {

    val normalText = stringResource(textNormal)
    val clickableText = stringResource(textClickable)
    val descriptionText = stringResource(textDescription)

    val annotatedText = buildAnnotatedString {
        append(normalText)
        append(" ")

        withLink(
            LinkAnnotation.Clickable(
                tag = "Clickable_text",
                styles = TextLinkStyles(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    )
                ),
                linkInteractionListener = {
                    onClick()
                }
            )
        ){
            append(clickableText)
        }
    }

    BasicText(
        modifier = Modifier
            .padding(bottom = appDimens.paddingXL)
            .semantics{
                role = Role.Button
                contentDescription = descriptionText
            },
        text = annotatedText
    )
}

@Preview(
    showSystemUi = false,
    showBackground = true
)
@Composable
fun MyClickableTextPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        MyClickableText(
            modifier = Modifier,
            textNormal = R.string.dont_have_account,
            textClickable = R.string.register_now,
            textDescription = R.string.clickable_text_description_login,
            onClick = {}
        )
    }
}
