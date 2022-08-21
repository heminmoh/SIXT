package com.coding.sixt.utilitiy

import org.junit.jupiter.api.Assertions.*

internal class MappingTransmissionTest
{
    private val mappingTransmission: Mapping = Mapping()
    @org.junit.Test
    fun convertTransmissionMapping() {
        val originalFormat = "A"
        val expected = "Automatic"
        print(mappingTransmission.transmissionMapping(originalFormat))
        assertEquals(expected, originalFormat.let { mappingTransmission.transmissionMapping(it) })
    }
}