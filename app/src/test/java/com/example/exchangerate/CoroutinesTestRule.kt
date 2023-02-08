package com.example.exchangerate

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

// https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/README.md@ExperimentalCoroutinesApi
class CoroutinesTestRule : TestWatcher() {

    override fun starting(description: Description) {
        super.starting(description)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    override fun finished(description: Description) {
        super.finished(description)
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
    }
}