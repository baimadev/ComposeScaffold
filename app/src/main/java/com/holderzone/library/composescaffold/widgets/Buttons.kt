package com.holderzone.library.composescaffold.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.holderzone.library.composescaffold.ui.theme.AppTheme
import com.holderzone.library.composescaffold.ui.theme.white
import com.holderzone.library.composescaffold.util.ext.hdp
import com.holderzone.library.composescaffold.util.ext.wdp


/**
 * 遮罩按钮：点击时显示遮罩
 */
@OptIn(ExperimentalMaterialApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Composable
fun MaskButton(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(25.wdp),
    backgroundColor: Color,
    maskColor: Color,
    onItemClick: (() -> Unit)?,
    content: @Composable (Modifier) -> Unit
) {

    Surface(
        shape = shape,
        modifier = modifier,
        color = backgroundColor
    ) {

        val state = remember { mutableStateOf(false) }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxHeight()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            state.value = true
                            if (tryAwaitRelease()) {
                                state.value = false
                                onItemClick?.invoke()
                            } else {
                                state.value = false
                            }
                        }
                    )
                }
        ) {

            if (state.value) {
                Spacer(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = maskColor)
                )
            }
            content(Modifier.fillMaxSize())
        }
    }
}

/**
 * 圆角按钮
 */
@Composable
fun CornerButton(modifier: Modifier, color: Color = AppTheme.colors.primary, @StringRes str:Int) {

    Box(
        contentAlignment = Alignment.Center, modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color = color)

    ) {
        Text(
            text = stringResource(id = str),
            color = white,
            style = MaterialTheme.typography.subtitle1
        )
    }

}

/**
 * 带返回按钮的button
 */
@Composable
fun ReturnButton(modifier: Modifier = Modifier, text: String) {

    Row(
        modifier = modifier.padding(vertical = 20.hdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Default.ArrowBackIos,
            null,
            tint = MaterialTheme.colors.secondaryVariant,
            modifier = Modifier.size(22.5f.wdp)
        )

        Spacer(modifier = Modifier.width(19.wdp))

        Text(
            text,
            color = MaterialTheme.colors.secondaryVariant,
            style = MaterialTheme.typography.h2
        )
    }
}

