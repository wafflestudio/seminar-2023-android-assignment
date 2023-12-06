package com.wafflestudio.assignment5

sealed class Screen(
    val screenType: ScreenType
){
    enum class ScreenType{Onboarding, Tutorial, MovieSearch, Clock}
}