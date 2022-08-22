package com.coding.sixt.utilitiy

import okhttp3.Cache

object HelperSIXT {
  const val BASE_URL = "https://cdn.sixt.io/codingtask/"
  const val CACHE_SIZE = (10 * 1024 * 1024).toLong()
  const val WEEK_TIME = 60 * 60 * 24 * 7
  const val CAR_OBJECT_BUNDLE =  "object"
  const val NoDataFetched =  "NoDataFetched"
}