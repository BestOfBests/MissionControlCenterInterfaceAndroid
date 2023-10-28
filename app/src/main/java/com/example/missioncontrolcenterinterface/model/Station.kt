package com.example.missioncontrolcenterinterface.model

import com.google.gson.annotations.SerializedName

data class Station(
    @SerializedName("name")
    val name: String,
    @SerializedName("batteryLevel")
    val batteryLevel: Float,
    @SerializedName("linearSpeedAcceleration")
    val linearSpeedAcceleration: Double,
    @SerializedName("rotationSpeedDegreesAcceleration")
    val rotationSpeedDegreesAcceleration: Double,
    @SerializedName("transform")
    val transform: Map<String, Double>,
    )
