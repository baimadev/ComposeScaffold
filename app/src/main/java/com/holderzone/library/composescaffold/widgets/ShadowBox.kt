package com.holderzone.library.composescaffold.widgets

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import com.holderzone.library.composescaffold.util.ext.dp2px
import android.R
import androidx.core.content.ContextCompat
import com.google.accompanist.glide.rememberGlidePainter


@Composable
fun ShadowBox(modifier: Modifier, color:Color){
    val content = LocalContext.current

    val bitmap = Bitmap.createBitmap(50.dp2px(),50.dp2px(),Bitmap.Config.ARGB_8888)
    val color = ContextCompat.getColor(content,R.color.white)
    bitmap.eraseColor(color) // 填充颜色

    val painter = rememberGlidePainter(request = bitmap,fadeIn = true)


    Image(modifier = modifier.size(50.dp), painter = painter, contentDescription = null)
}


