package com.example.eventmanager.objects

import android.animation.ObjectAnimator
import android.view.View

object Animator {
    val ANIMATE_UP_FROM_BOTTOM = -500f
    val ANIMATE_UP_FROM_CENTER = -100f
    val ANIMATE_DOWN = 0f

    fun animate(animateObjects: List<View>, shiftTranslate: Float) {
        animateObjects.map {
            ObjectAnimator.ofFloat(it, "translationY", shiftTranslate).apply {
                duration = 200
                start()
            }
        }

    }
}