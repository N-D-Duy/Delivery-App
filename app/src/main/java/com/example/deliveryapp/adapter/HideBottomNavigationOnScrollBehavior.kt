package com.example.deliveryapp.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class HideBottomNavigationOnScrollBehavior(context: Context, attrs: AttributeSet) :
    AppBarLayout.ScrollingViewBehavior(context, attrs) {

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: View,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed
        )

        // Ẩn hoặc hiện BottomNavigationView khi cuộn màn hình
        val bottomNavigationView = child as BottomNavigationView
        if (dyConsumed > 0 && bottomNavigationView.translationY == 0f) {
            // Cuộn xuống, ẩn BottomNavigationView
            bottomNavigationView.animate().translationY(bottomNavigationView.height.toFloat())
                .duration = 200
        } else if (dyConsumed < 0 && bottomNavigationView.translationY > 0f) {
            // Cuộn lên, hiện BottomNavigationView
            bottomNavigationView.animate().translationY(0f).duration = 200
        }
    }
}
