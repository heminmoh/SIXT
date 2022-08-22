package com.coding.sixt.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
) : Parcelable {

    companion object {
        val EMPTY = CarPreview(
            id = "123465",
            modelIdentifier = "empty",
            modelName = "none",
            name = "none",
            make = "placeholder",
            group = "placeholder",
            color = "placeholder",
            series = "placeholder",
            fuelType = "placeholder",
            fuelLevel = "placeholder",
            transmission = "placeholder",
            licensePlate = "placeholder",
            latitude = 0.0,
            longitude = 0.0,
            innerCleanliness = "placeholder",
            carImageUrl = "placeholder"
        )
    }
}

