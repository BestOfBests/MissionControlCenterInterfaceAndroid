package com.example.missioncontrolcenterinterface.domain

import com.example.missioncontrolcenterinterface.model.Station
import com.example.missioncontrolcenterinterface.network.MissionAPI
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class StationRepositoryImpl() : StationRepository {
    override suspend fun getStation(): Flow<Station> =
        callbackFlow {
            delay(2000)
            trySendBlocking(
                MissionAPI.api.getStation()
            )
            awaitClose()
        }

    override suspend fun changeName(name: String) {
        MissionAPI.api.changeName(name = name).execute()
    }

    override suspend fun changeLinearSpeed(linearSpeed: Double) {
        MissionAPI.api.changeLinearSpeed(mapOf("requiredLinearSpeed" to linearSpeed)).execute()
    }

    override suspend fun changeRotationSpeed(rotationSpeed: Double) {
        MissionAPI.api.changeRotationSpeed(mapOf("requiredRotationSpeedClockwiseDegrees" to rotationSpeed)).execute()
    }
}

