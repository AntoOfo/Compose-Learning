package com.example.composelearning

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// fields to expect in our row data
data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)

// defines all the images and string resources we'll use in our lazy row
val alignYourBodyData = listOf(
    DrawableStringPair(R.drawable.ab1_inversion, R.string.Inversions),
    DrawableStringPair(R.drawable.quickyoga, R.string.QuickYoga),
    DrawableStringPair(R.drawable.stretch, R.string.Stretching),
    DrawableStringPair(R.drawable.tabata, R.string.Tabata),
    DrawableStringPair(R.drawable.hiit, R.string.Hiit),
    DrawableStringPair(R.drawable.natal, R.string.Natal)
)