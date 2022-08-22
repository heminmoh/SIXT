package com.coding.sixt.utilitiy

class Mapping {
    private var transmission = mapOf("M" to "Manual", "A" to "Automatic")
    private var fuelType = mapOf("P" to "Gasoline", "E" to "Ethanol", "D" to "Diesel")

    fun transmissionMapping(data : String) : String
    {
        var transmissionResult : String
        transmissionResult = transmission[data].toString()
        if( transmissionResult == "null") {
            transmissionResult = "Manual"
        }
        return  transmissionResult
    }

    fun fuelTypeMapping(data : String) : String
    {
        var fuelTypeResult : String
        fuelTypeResult = fuelType[data].toString()
        if( fuelTypeResult == "null") {
            fuelTypeResult = "Gasoline"
        }
        return  fuelTypeResult
    }
}