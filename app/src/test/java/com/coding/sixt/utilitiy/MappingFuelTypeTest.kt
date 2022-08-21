package com.coding.sixt.utilitiy

import org.junit.jupiter.api.Assertions.*

internal class MappingFuelTypeTest{
    private val mappingFuelType: Mapping = Mapping()
    @org.junit.Test
    fun convertFuelTypeMapping() {
        val originalFormat = "E"
        val expected = "Ethanol"
        print(mappingFuelType.fuelTypeMapping(originalFormat))
        assertEquals(expected, originalFormat?.let { mappingFuelType.fuelTypeMapping(it) })
    }
}