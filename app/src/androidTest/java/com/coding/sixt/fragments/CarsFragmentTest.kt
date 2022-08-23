package com.coding.sixt.fragments

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.coding.sixt.databinding.FragmentCarsBinding
import com.coding.sixt.model.CarPreview
import org.junit.Assert
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
internal class CarsFragmentTest {

//    @Rule
//    @JvmField
//    var mActivityTestRule = ActivityTestRule(FragmentActivity::class.java)
    private lateinit var mockModel : CarPreview
     private var mockModelList :  ArrayList<CarPreview> = ArrayList<CarPreview>()

    @org.junit.Test
    fun makeViewDesign() {

        mockModel = CarPreview("WMWSU31070T077232","mini","MINI","Regine","BMW","MINI",
            "midnight_black","MINI","P", 0.55,"M","",0.0,0.0,
            "CLEAN","https://cdn.sixt.io/codingtask/images/mini.png")
        mockModelList.add(mockModel)
        print(mockModelList[0].id)

        Assert.assertEquals(mockModelList[0].id ,"WMWSU31070T077232" )
    }

}