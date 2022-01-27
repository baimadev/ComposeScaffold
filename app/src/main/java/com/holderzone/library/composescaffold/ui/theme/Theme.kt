package com.holderzone.library.composescaffold.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.holderzone.library.composescaffold.util.ext.ScreenConfigInfo

@Composable
fun CustomTheme(
    content: @Composable() () -> Unit
) {
    //TODO 使用哪种主题
    val colors = lightColorSet

    //初始化屏幕宽高
    ScreenConfigInfo.initScreenConfigInfo()
    ProvideTypes(typeSet = typeSet) {
        ProvideColors(colorSet = colors) {
            MaterialTheme(
                typography = Typography,
                shapes = Shapes,
                content = content
            )
        }
    }

}

object AppTheme {
    val colors: CustomColors
        @Composable
        get() = LocalAppColors.current

    val types: CustomTypes
        @Composable
        get() = LocalAppTypes.current

}