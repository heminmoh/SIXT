package com.coding.sixt.fragments

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
internal class CarsFragmentTest {



    private lateinit var instrumentationContext: Context
    private lateinit var mockObject : CarPreview

    @Test
    fun testretrofit() = runBlocking{
        mockObject = CarPreview("WMWSW31030T222518","mini","MINI","Regine","BMW","MINI",
            "midnight_black","MINI","P", 0.55,"M","",0.0,0.0,
            "CLEAN","https://cdn.sixt.io/codingtask/images/mini.png")
        instrumentationContext = InstrumentationRegistry.getInstrumentation().targetContext
        val instance = ServiceBuilder(instrumentationContext).buildService(APIInterface::class.java)
        var response = instance.getCars().awaitResponse()
        print(response.body()?.get(0)?.id)

        Assert.assertEquals(response.body()?.get(0)?.id,mockObject.id)
    }
}
