package com.coding.sixt.model

data class CarPreview(
    val id: String,
    val modelIdentifier: String,
    val modelName: String,
    val name: String,
    val make: String,
    val group: String,
    val color: String,
    val series: String,
    val fuelType: String,
    val fuelLevel: String,
    val transmission: String,
    val licensePlate: String,
    val latitude: Double,
    val longitude: Double,
    val innerCleanliness: String,
    val carImageUrl: String
) {

    companion object {
        val EMPTY = CarPreview(
            id = "123465",
            modelIdentifier = "empty",
            modelName = "none",
            name = "none",
            make = "placeholder",
            group = "forever",
            color = "universe",
            series = "universe",
            fuelType = "universe",
            fuelLevel = "universe",
            transmission = "placeholder",
            licensePlate = "placeholder",
            latitude = 48.134557,
            longitude = 11.576921,
            innerCleanliness = "placeholder",
            carImageUrl = "placeholder"
        )
    }
}

