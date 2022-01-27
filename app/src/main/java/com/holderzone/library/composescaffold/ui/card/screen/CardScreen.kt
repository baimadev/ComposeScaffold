package com.holderzone.library.composescaffold.ui.card.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.*
import com.holderzone.library.composescaffold.R
import com.holderzone.library.composescaffold.ui.theme.black272727
import com.holderzone.library.composescaffold.ui.theme.blackFF141517
import com.holderzone.library.composescaffold.util.ext.hdp

@ExperimentalAnimationApi
@Composable
fun CardScreen() {
    //val viewModel: CardViewModel = viewModel()
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(blackFF141517.copy(0.5f))
    ) {

        LoadingScreen()
    }
}


@Composable
fun LoadingScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (hint,loading) = createRefs()

        Text(text = "资源加载中 . . .",
            color = black272727,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.constrainAs(hint){
                centerHorizontallyTo(parent)
                bottom.linkTo(parent.bottom,190.hdp)
            })

        LottieAnimation(modifier = Modifier.constrainAs(loading){
            centerHorizontallyTo(parent)
            centerVerticallyTo(parent)
        },composition = composition, progress = progress)
    }


}

@ExperimentalAnimationApi
@Preview(showBackground = true, widthDp = 1280, heightDp = 800)
@Composable
fun SplashScreenPreview() {
    CardScreen()
}