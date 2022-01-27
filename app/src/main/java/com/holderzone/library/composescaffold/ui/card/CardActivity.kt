package com.holderzone.library.composescaffold.ui.card

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.`annotation`.Route
import com.holderzone.library.composescaffold.base.HolderVMActivity
import com.holderzone.library.composescaffold.config.router.RoutePath
import com.holderzone.library.composescaffold.ui.card.screen.CardScreen
import com.holderzone.library.composescaffold.ui.theme.CustomTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlin.Unit

@AndroidEntryPoint
@Route(path = RoutePath.TestCard)
public class CardActivity : HolderVMActivity<CardViewModel>() {
    @ExperimentalAnimationApi
    public override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                CardScreen()
            }
        }
    }

    protected override fun initData(savedInstanceState: Bundle?): Unit {
    }

    public override fun injectViewModel(): CardViewModel =
        ViewModelProvider(this)[CardViewModel::class.java]

}
