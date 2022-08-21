package com.coding.sixt.utilitiy

import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class CheckInternetConnectionTest
{
        @JvmField
        var connectionInternetConnection= CheckInternetConnection()
        private lateinit var instrumentationContext: Context
        @org.junit.Test
        fun checkForInternet() {
            instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
            val connectionStatus = connectionInternetConnection.checkForInternet(instrumentationContext)
            print(connectionStatus)
            Assert.assertEquals(true, connectionStatus)
         }
}
