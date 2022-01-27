package com.holderzone.library.composescaffold.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.holderzone.library.composescaffold.util.ext.spi

// Set of Material typography styles to start with
val Typography = Typography(

    caption = TextStyle(
        fontSize = 15.spi,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
    ),

    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.spi
    ),

    subtitle1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 19.spi
    ),

    h4 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 30.spi
    ),

    h6 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 21.spi
    ),
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 25.spi
    ),


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)


val typeSet: CustomTypes = CustomTypes(
    title1 = TextStyle(
        fontSize = 15.spi,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
    ),
    title2 = TextStyle(
        fontSize = 25.spi,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
    )

)

class CustomTypes(
    val title1:TextStyle,
    val title2:TextStyle,
)


@Composable
fun ProvideTypes(
    typeSet: CustomTypes,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalAppTypes provides remember { typeSet }, content = content)
}

val LocalAppTypes = staticCompositionLocalOf {
    typeSet
}