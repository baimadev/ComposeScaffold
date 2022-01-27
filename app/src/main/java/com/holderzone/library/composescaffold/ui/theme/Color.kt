package com.holderzone.library.composescaffold.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


val purpleA093EC = Color(0xFFA093EC)

val blue6D7C8F = Color(0xFF6D7C8F)
val blue495668 = Color(0xFF495668)
val blue56657B = Color(0xFF56657B)
val blue4B5D75 = Color(0xFF4B5D75)
val blue2D3C51  = Color(0xFF2D3C51)
val blue37485F  = Color(0xFF37485F)
val blue42D2DE = Color(0xFF42D2DE)
val blue4E6585 = Color(0xFF4E6585)
val blue3A94FF = Color(0xFF3A94FF)
val blue0561FF = Color(0xFF0561FF)
val blue057EFF = Color(0xFF057EFF)
val blue0569FF = Color(0xFF0569FF)

val green6DDACB = Color(0xFF6DDACB)
val green32BFCE = Color(0xFF32BFCE)
val green68F3E3 = Color(0xFF68F3E3)
val green2CCE9F  = Color(0xFF2CCE9F)

val black = Color(0xFF000000)
val black111111 = Color(0xFF111111)
val black272727 = Color(0xFF272727)
val black333333  = Color(0xFF333333)


val grayF2F2F2 = Color(0xFFF2F2F2)
val gray707070 = Color(0xFF707070)
val grayBECCCE = Color(0xFFBECCCE)
val grayD9D9D9  = Color(0xFFD9D9D9)
val gray9A9A9A = Color(0xFF9A9A9A)
val grayE8EBEE = Color(0xFFE8EBEE)
val grayE3E3E3 = Color(0xFFE3E3E3)
val grayA1B1B3 = Color(0xFFA1B1B3)
val grayF6F6F6 = Color(0xFFF6F6F6)

val yellowEB9E10  = Color(0xFFEB9E10)
val yellowFFDA05  = Color(0xFFFFDA05)
val yellowEBE405 = Color(0xFFEBE405)
val yellowFFAC59 = Color(0xFFFFAC59)
val yellowFF973A = Color(0xFFFF973A)

val white = Color(0xFFFFFFFF)
val white00FFFFFF = Color(0x00FFFFFF)
val whiteFFFFFFFF = Color(0xFFFFFFFF)

val grayFFF2F4F6= Color(0xFFF2F4F6)
val grayFFE8F0F1= Color(0xFFE8F0F1)
val grayFFA3ABB5 = Color(0xFFA3ABB5)
val purpleBE2EFF = Color(0xFFBE2EFF)

val pinkFF1CE0 = Color(0xFFFF1CE0)

val green05FFA8 = Color(0xFF05FFA8)
val green6FFF7E = Color(0xFF6FFF7E)

val redFF3A3A = Color(0xFFFF3A3A)
val redFF0505 = Color(0xFFFF0505)

val blackFF141517 = Color(0xFF141517)

val darkColorSet = CustomColors(
    primary = green6DDACB,
    background = Color.Black,
    primaryVariant = Color.Yellow,
    secondary = Color.Blue
)

val lightColorSet = CustomColors(
    primary = green6DDACB,
    background = Color.Cyan,
    primaryVariant = Color.Gray,
    secondary = Color.Blue
)


class CustomColors(
    val primary: Color,
    val background: Color,
    val primaryVariant: Color,
    val secondary: Color,
)


@Composable
fun ProvideColors(
    colorSet: CustomColors,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(LocalAppColors provides remember { colorSet }, content = content)
}

val LocalAppColors = staticCompositionLocalOf {
    darkColorSet
}

