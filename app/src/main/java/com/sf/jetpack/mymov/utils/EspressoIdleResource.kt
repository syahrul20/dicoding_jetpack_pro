package com.sf.jetpack.mymov.utils

import androidx.test.espresso.idling.CountingIdlingResource


object EspressoIdleResource {
    private const val RESOURCE = "GLOBAL"
    val idlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingResource.increment()
    }

    fun decrement() {
        idlingResource.decrement()
    }
}