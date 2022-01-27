package com.holderzone.library.composescaffold.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.google.accompanist.glide.rememberGlidePainter

/**
 * @param modifier 外部传入Image的大小
 * @param placeholder 占位图，默认是 R.drawable.loadding
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun UrlImage(
    modifier: Modifier = Modifier,
    url: Any? = null,
    @DrawableRes resId:Int? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {


    val painter = if (resId != null)
        painterResource(id = resId)
    else {
        rememberGlidePainter(request = url,fadeIn = true)
    }

    Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
    )
}
