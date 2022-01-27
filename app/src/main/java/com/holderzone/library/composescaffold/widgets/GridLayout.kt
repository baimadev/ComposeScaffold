package com.holderzone.library.composescaffold.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.holderzone.library.composescaffold.util.ext.hdp
import com.holderzone.library.composescaffold.util.ext.wdp
import com.holderzone.library.composescaffold.util.ext.spi


/**
 * @param columns 一行多少列
 */
@Composable
fun <T> GridLayout(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    list: List<T>,
    columns:Int,
    content: @Composable ((data: T) -> Unit)?=null,
    withIndex: @Composable ((data: T, index:Int) -> Unit)?=null,
) {

    val yu = list.size % columns
    var rows = list.size / columns
    if (yu > 0){
        rows += 1
    }

    var index = 0
    Column(modifier = modifier) {

        for (i in 0 until  rows) {
            Spacer(modifier = Modifier.width(contentPadding.calculateLeftPadding(LayoutDirection.Ltr)))
            Row {
                for (j in 0 until columns) {
                    if (index >= list.size) break
                    content?.invoke(list[index])
                    withIndex?.invoke(list[index],index)
                    Spacer(
                        modifier = Modifier.width(
                            contentPadding.calculateLeftPadding(
                                LayoutDirection.Ltr
                            )
                        )
                    )
                    index++
                }
            }
            Spacer(modifier = Modifier.height(contentPadding.calculateTopPadding()))
        }
    }

}

@Composable
@Preview(widthDp = 400, heightDp = 500)
fun GridLayoutPreview() {

    GridLayout(
        contentPadding = PaddingValues(horizontal = 20.wdp, vertical = 30.hdp),
        columns = 3,
        list = arrayListOf(1, 2, 3, 4, 5,6,7,8),
        content = {
            Surface(modifier = Modifier
                .size(100.wdp)
                .background(color = Color.Yellow)

            ) {
                Text(text = it.toString(),color = Color.Black,fontSize = 25.spi)
            }
        }
    )
}
