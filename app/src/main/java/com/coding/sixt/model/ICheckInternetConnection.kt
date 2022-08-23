
/**
 * function of checkForInternet in this class check Internet connection
 * context is the enter param  and return a Boolean value which shows Internet Status
 *  2022-08-19  09:37
 */

package com.coding.sixt.model

import android.content.Context

interface ICheckInternetConnection {
    fun checkForInternet(context: Context): Boolean
}