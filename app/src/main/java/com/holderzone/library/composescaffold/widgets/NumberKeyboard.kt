package com.holderzone.library.composescaffold.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.holderzone.library.composescaffold.R
import com.holderzone.library.composescaffold.ui.theme.blue56657B
import com.holderzone.library.composescaffold.ui.theme.blue6D7C8F
import com.holderzone.library.composescaffold.ui.theme.purpleA093EC
import com.holderzone.library.composescaffold.ui.theme.white
import com.holderzone.library.composescaffold.util.ext.hdp
import com.holderzone.library.composescaffold.util.ext.wdp


/**
 * @param onValueChange 数字输入回调
 * @param onClearClick 清空回调
 * @param onBackSpaceClick 退格回调
 */
@Composable
fun NumberKeyboard(
    modifier: Modifier = Modifier,
    onValueChange: (Int) -> Unit,
    onClearClick: () -> Unit,
    onBackSpaceClick: () -> Unit,
    isUserIcon: Boolean = false
) {

    val numberList = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

    GridLayout(
        modifier = modifier
            .width(350.wdp)
            .height(290.hdp),
        contentPadding = PaddingValues(7.wdp),
        list = numberList,
        columns = 3,
        withIndex = { data, index ->
            NumberItem(number = data, isUserIcon = isUserIcon) {
                when (data) {
                    1, 2, 3, 4, 5, 6, 7, 8, 9 -> {
                        onValueChange(data)
                    }
                    10 -> {
                        onClearClick()
                    }
                    11 -> {
                        onValueChange(0)
                    }
                    12 -> {
                        onBackSpaceClick()
                    }
                }
            }
        })

}

@Composable
fun NumberItem(isUserIcon: Boolean = false, number: Int, onItemClick: () -> Unit) {

    when (number) {
        1, 2, 3, 4, 5, 6, 7, 8, 9 -> {
            GrayButton(onItemClick = onItemClick) {
                Text(
                    text = number.toString(),
                    style = MaterialTheme.typography.button,
                    color = white
                )
            }

        }
        10 -> {
            GrayButton(onItemClick = onItemClick, backGroundColor = purpleA093EC) {
                Text(
                    text = "清空",
                    style = MaterialTheme.typography.subtitle1,
                    color = white
                )
            }
        }
        11 -> {
            GrayButton(onItemClick = onItemClick) {
                Text(
                    text = 0.toString(),
                    style = MaterialTheme.typography.button,
                    color = white
                )
            }
        }
        12 -> {
            GrayButton(onItemClick = onItemClick) {
                if (isUserIcon) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_del),
                        contentDescription = ""
                    )
                } else {
                    Text(
                        text = "退格",
                        style = MaterialTheme.typography.subtitle1,
                        color = white
                    )
                }

            }
        }
    }

}

@Composable
fun GrayButton(
    onItemClick: () -> Unit,
    backGroundColor: Color = blue6D7C8F,
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .width(111.wdp)
            .height(67.hdp)
            .clip(RoundedCornerShape(7.wdp))
            .setPressColorAndBackground(blue56657B, backGroundColor)
            .clickable { onItemClick() },
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
