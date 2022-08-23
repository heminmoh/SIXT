package com.coding.sixt.remote

import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.coding.sixt.model.CarPreview
import com.coding.sixt.remote.APIInterface
import com.coding.sixt.remote.ServiceBuilder
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.await
import retrofit2.awaitResponse

@RunWith(AndroidJUnit4ClassRunner::class)
internal class CheckResponseCodeTest {
    private lateinit var instrumentationContext: Context
    @Test
    fun testretrofit() = runBlocking{
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        val instance = ServiceBuilder(instrumentationContext).buildService(APIInterface::class.java)
        var response = instance.getCars().awaitResponse()

        Assert.assertEquals(200,response.code())
    }
}
