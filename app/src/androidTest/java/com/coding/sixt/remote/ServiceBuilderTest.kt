package com.coding.sixt.remote

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test

internal class ServiceBuilderTest
{
    private lateinit var instrumentationContext: Context
    @Test
    fun testRetrofitInstance() {
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        val instance = ServiceBuilder(instrumentationContext)
        print(instance.retrofit.baseUrl())
        Assert.assertEquals( instance.retrofit.baseUrl().toString(),"https://cdn.sixt.io/codingtask/")
    }
}