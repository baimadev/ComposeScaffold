package com.holderzone.library.composescaffold.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import android.graphics.BlurMaskFilter
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas

/**
 * @param pressColor 按下的颜色
 * @param backgroundColor 背景颜色
 * @param onItemClick 点击事件
 */
@Composable
fun Modifier.setPressColorAndBackground(
    pressColor: Color,
    backgroundColor: Color,
    onItemClick: (() -> Unit)? = null
): Modifier {
    val state = remember { mutableStateOf(false) }
    return this
        .background(color = if (state.value) pressColor else backgroundColor)
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
}


/**
 * 应用阴影效果，支持颜色、偏移和模糊半径。
 * 注意 Modifier 的应用顺序，应在 background 之前应用。
 * 由于父控件会限制子控件的绘制区域，请确保父控件的大小超出子控件的阴影区域。
 */
fun Modifier.shadow(
    shadow: Shadow,
    shape: Shape = RectangleShape
) = this.then(
    ShadowModifier(shadow, shape)
)

private class ShadowModifier(
    val shadow: Shadow,
    val shape: Shape
) : DrawModifier {

    override fun ContentDrawScope.draw() {
        drawIntoCanvas { canvas ->
            val paint = Paint().apply {
                color = shadow.color
                asFrameworkPaint().apply {
                    maskFilter = BlurMaskFilter(
                        convertRadiusToSigma(shadow.blurRadius),
                        BlurMaskFilter.Blur.NORMAL
                    )
                }
            }
            shape.createOutline(
                size, layoutDirection, this
            ).let { outline ->
                canvas.drawWithOffset(shadow.offset) {
                    when (outline) {
                        is Outline.Rectangle -> {
                            drawRect(outline.rect, paint)
                        }
                        is Outline.Rounded -> {
                            drawPath(
                                Path().apply { addRoundRect(outline.roundRect) },
                                paint
                            )
                        }
                        is Outline.Generic -> {
                            drawPath(outline.path, paint)
                        }
                    }
                }
            }
        }
        drawContent()
    }

    private fun convertRadiusToSigma(
        radius: Float,
        enable: Boolean = true
    ): Float = if (enable) {
        (radius * 0.57735 + 0.5).toFloat()
    } else {
        radius
    }

    private fun Canvas.drawWithOffset(
        offset: Offset,
        block: Canvas.() -> Unit
    ) {
        save()
        translate(offset.x, offset.y)
        block()
        restore()
    }
}
