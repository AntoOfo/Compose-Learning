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
    DrawableStringPair(R.drawable.stretchpng, R.string.Stretching),
    DrawableStringPair(R.drawable.tabatapng, R.string.Tabata),
    DrawableStringPair(R.drawable.hiitpng, R.string.Hiit),
    DrawableStringPair(R.drawable.natalpng, R.string.Natal)
)

// defines images and string resources we'll use in our Lazy Horizontal Grid
val favoriteCollectionsData = listOf(
    DrawableStringPair(R.drawable.leavespng, R.string.NatureMeditations),
    DrawableStringPair(R.drawable.mantrapng, R.string.Mantras),
    DrawableStringPair(R.drawable.stresspng, R.string.Stress),
    DrawableStringPair(R.drawable.massagepng, R.string.Massage),
    DrawableStringPair(R.drawable.overwhelmedpng, R.string.Overwhelmed),
    DrawableStringPair(R.drawable.nightlypng, R.string.Nightly)
)