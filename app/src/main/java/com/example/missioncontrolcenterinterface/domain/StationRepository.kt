@file:Suppress("Since15")

package com.example.missioncontrolcenterinterface.domain

import com.example.missioncontrolcenterinterface.model.Station
import kotlinx.coroutines.flow.Flow


interface StationRepository {
    suspend fun getStation(): Flow<Station>
    suspend fun changeName(name: String)
    suspend fun changeLinearSpeed(linearSpeed: Double)
    suspend fun changeRotationSpeed(rotationSpeed: Double)

}