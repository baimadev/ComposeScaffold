package com.holderzone.library.composescaffold.util

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import java.util.*
import java.util.concurrent.TimeUnit

object NoIndication : Indication {
    private object NoIndicationInstance : IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }

    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }
}

fun Modifier.noPressColorClick(onClick: () -> Unit): Modifier = composed(
    factory = {
        Modifier
            .clickable(indication = NoIndication,
                interactionSource = remember { MutableInteractionSource() }) {
                onClick()
            }
    }
)

fun Modifier.throttleFirst(time : Int,onClick: () -> Unit):Modifier=composed(
    factory = {
        var lastClickTime :Long = 0
        Modifier.clickable {
            val currentTime :Long = Calendar.getInstance().timeInMillis
            if(currentTime - lastClickTime > time){
                lastClickTime = currentTime
                onClick.invoke()
            }
        }
    }
)