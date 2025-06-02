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

// defines images and string resources we'll use in our Lazy Horizontal Grid
val favoriteCollectionsData = listOf(
    DrawableStringPair(R.drawable.leaves, R.string.NatureMeditations),
    DrawableStringPair(R.drawable.mantra, R.string.Mantras),
    DrawableStringPair(R.drawable.stress, R.string.Stress),
    DrawableStringPair(R.drawable.massage, R.string.Massage),
    DrawableStringPair(R.drawable.overwhelmed, R.string.Overwhelmed),
    DrawableStringPair(R.drawable.nightly, R.string.Nightly)
)