package com.jmarser.vehiclemanager.presentation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Project: Vehicle manager
 * File: MainDispatcherRule
 * Author: Tu Jmarser <aenur32@gmail.com>
 * Created: 23/12/2025
 */

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule (
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
): TestWatcher() {

    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}