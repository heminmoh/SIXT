
/**
* Mapping transmission and fuelType data to to give meaning of different words and sign to user.
 * 2022-08-21 21:00
 */

package com.coding.sixt.utilitiy

class Mapping {
    private var transmission = mapOf("M" to "Manual", "A" to "Automatic")
    private var fuelType = mapOf("P" to "Gasoline", "E" to "Ethanol", "D" to "Diesel")

    fun transmissionMapping(data : String) : String
    {
        var transmissionResult : String
        transmissionResult = transmission[data].toString()
        if( transmissionResult == "null") {
            transmissionResult = "Unknown"
        }
        return  transmissionResult
    }

    fun fuelTypeMapping(data : String) : String
    {
        var fuelTypeResult : String
        fuelTypeResult = fuelType[data].toString()
        if( fuelTypeResult == "null") {
            fuelTypeResult = "Unknown"
        }
        return  fuelTypeResult
    }
}