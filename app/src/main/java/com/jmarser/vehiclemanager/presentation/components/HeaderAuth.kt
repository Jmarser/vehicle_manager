package com.jmarser.vehiclemanager.presentation.components


import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.jmarser.vehiclemanager.R
import com.jmarser.vehiclemanager.core.presentation.component.VerticalSpaceNormal
import com.jmarser.vehiclemanager.core.presentation.ui.appDimens
import com.jmarser.vehiclemanager.core.presentation.ui.getSizeForPhone
import com.jmarser.vehiclemanager.ui.theme.MyAppTheme


@Composable
fun HeaderAuth(
    modifier: Modifier = Modifier,
    @StringRes title: Int? = null,
    logo: Painter
) {

    Column (
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            modifier = Modifier
                .size(appDimens.logoSize),
            painter = logo,
            contentDescription = stringResource(R.string.img_header_auth_description)
        )

        VerticalSpaceNormal()

        title?.let {
            Text(
                text = stringResource(id = it),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun HeaderAuthPreview() {
    MyAppTheme(
        windowSizeClass = getSizeForPhone()
    ) {
        HeaderAuth(
            modifier = Modifier
                .fillMaxWidth(),
            logo = painterResource(id = R.drawable.img_header),
            title = R.string.login
        )
    }
}
