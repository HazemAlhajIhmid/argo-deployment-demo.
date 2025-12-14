package com.svu.heartdisease.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.svu.heartdisease.models.CalculatorState
import com.svu.heartdisease.models.ModelMetrics
import com.svu.heartdisease.models.PatientData
import com.svu.heartdisease.network.HeartDiseaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel for Heart Disease Prediction
 * Manages UI state and API communication
 */
class HeartDiseaseViewModel(
    private val repository: HeartDiseaseRepository
) : ViewModel() {

    private val _calculatorState = MutableStateFlow(CalculatorState())
    val calculatorState: StateFlow<CalculatorState> = _calculatorState.asStateFlow()

    private val _modelMetrics = MutableStateFlow<List<ModelMetrics>>(emptyList())
    val modelMetrics: StateFlow<List<ModelMetrics>> = _modelMetrics.asStateFlow()

    /**
     * Make prediction for heart disease
     */
    fun predict(patientData: PatientData) {
        viewModelScope.launch {
            _calculatorState.value = CalculatorState(isLoading = true)

            repository.predictHeartDisease(patientData)
                .onSuccess { result ->
                    _calculatorState.value = CalculatorState(
                        isLoading = false,
                        result = result
                    )
                }
                .onFailure { error ->
                    _calculatorState.value = CalculatorState(
                        isLoading = false,
                        error = error.message ?: "حدث خطأ أثناء التنبؤ"
                    )
                }
        }
    }

    /**
     * Load model performance metrics
     */
    fun loadModelMetrics() {
        viewModelScope.launch {
            repository.getModelMetrics()
                .onSuccess { metrics ->
                    _modelMetrics.value = metrics
                }
                .onFailure { error ->
                    // Handle error silently or show toast
                }
        }
    }

    /**
     * Reset calculator state
     */
    fun resetCalculator() {
        _calculatorState.value = CalculatorState()
    }

    /**
     * Check API health
     */
    fun checkApiHealth(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.checkHealth()
                .onSuccess { isHealthy ->
                    onResult(isHealthy)
                }
                .onFailure {
                    onResult(false)
                }
        }
    }
}
