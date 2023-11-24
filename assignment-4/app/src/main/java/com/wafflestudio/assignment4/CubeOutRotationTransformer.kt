package com.wafflestudio.assignment4

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

class CubeOutRotationTransformer: ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
        page.cameraDistance = 20000f

        when {
            position < -1 -> { // 페이지가 왼쪽 화면 밖에 있는 경우
                page.alpha = 0f
            }
            position <= 1 -> {
                page.alpha = 1f

                // 페이지의 회전 중심 설정
                page.pivotX = if (position < 0) page.width.toFloat() else 0f
                page.pivotY = page.height * 0.5f

                // 페이지가 화면 왼쪽에 있을 때
                if (position < 0) {
                    page.rotationY = 90 * abs(position)
                } else { // 페이지가 화면 오른쪽에 있을 때
                    page.rotationY = -90 * abs(position)
                }

                // 페이지가 조금씩 살짝 넘어가면 투명도 조정
                page.translationX = page.width * -position
            }
            else -> { // 페이지가 오른쪽 화면 밖에 있는 경우
                page.alpha = 0f
            }
        }
    }
}
