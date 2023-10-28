package com.example.missioncontrolcenterinterface.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.missioncontrolcenterinterface.domain.StationRepository
import com.example.missioncontrolcenterinterface.domain.StationRepositoryImpl
import com.example.missioncontrolcenterinterface.model.Station
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StationViewModel(private val stationRepository: StationRepository = StationRepositoryImpl()) :
    ViewModel() {
    private val _stationUiState =
        MutableStateFlow(Station("default", 1.0f, 0.0, 0.0, mapOf("x" to 0.0, "y" to 0.0)))
    val stationUiState = _stationUiState.asStateFlow()
    var targetRotationSpeed = 0.0
    var targerLinearSpeed = 0.0

    fun getStation() {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }) {
            stationRepository.getStation()
                .collect { station ->
                    _stationUiState.value = station
                }
        }
    }

    fun changeName(name: String) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }) {
            stationRepository.changeName(name)
        }
    }

    fun changeLinearSpeed(linearSpeed: Double) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }) {
            stationRepository.changeLinearSpeed(linearSpeed)
        }
    }

    fun changeRotationSpeed(rotationSpeed: Double) {
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            println("CoroutineExceptionHandler got $exception")
        }) {
            stationRepository.changeRotationSpeed(rotationSpeed)
        }
    }
}