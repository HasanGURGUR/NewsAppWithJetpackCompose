package com.hasangurgur.newsappjetpackcompose.presentation.onboarding

import androidx.annotation.DrawableRes
import com.hasangurgur.newsappjetpackcompose.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int,
)

val pages = listOf(
    Page(
        title = "Welcome to NewsApp",
        description = "Stay updated with the latest news and articles.",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Personalized Experience",
        description = "Get news tailored to your interests.",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Easy Navigation",
        description = "Navigate through categories effortlessly.",
        image = R.drawable.onboarding3
    )
)