package com.svu.heartdisease.models

import com.google.gson.annotations.SerializedName

/**
 * Heart Disease Patient Data Model
 * Syrian Virtual University Master's Thesis Project
 */
data class PatientData(
    @SerializedName("age") val age: Float,
    @SerializedName("sex") val sex: Float, // 1 = male, 0 = female
    @SerializedName("chestPainType") val chestPainType: Float, // 0-3
    @SerializedName("restingBloodPressure") val restingBloodPressure: Float,
    @SerializedName("serumCholesterol") val serumCholesterol: Float,
    @SerializedName("fastingBloodSugar") val fastingBloodSugar: Float, // 0 or 1
    @SerializedName("restingECG") val restingECG: Float, // 0-2
    @SerializedName("maxHeartRate") val maxHeartRate: Float,
    @SerializedName("exerciseInducedAngina") val exerciseInducedAngina: Float, // 0 or 1
    @SerializedName("stDepression") val stDepression: Float, // oldpeak
    @SerializedName("slopeOfPeakExercise") val slopeOfPeakExercise: Float, // 0-2
    @SerializedName("numberOfMajorVessels") val numberOfMajorVessels: Float, // 0-3
    @SerializedName("thalassemia") val thalassemia: Float // 0-2
)

/**
 * Individual Model Prediction Result
 */
data class ModelPrediction(
    @SerializedName("prediction") val prediction: Int, // 1 = disease, 0 = no disease
    @SerializedName("probability") val probability: Float,
    @SerializedName("accuracy") val accuracy: Float
)

/**
 * Complete Prediction Response from API
 */
data class PredictionResponse(
    @SerializedName("knn") val knn: ModelPrediction,
    @SerializedName("naiveBayes") val naiveBayes: ModelPrediction,
    @SerializedName("decisionTree") val decisionTree: ModelPrediction,
    @SerializedName("averageProbability") val averageProbability: Float,
    @SerializedName("riskLevel") val riskLevel: String // "low", "moderate", "high"
)

/**
 * Model Performance Metrics
 */
data class ModelMetrics(
    @SerializedName("model") val model: String,
    @SerializedName("accuracy") val accuracy: Float,
    @SerializedName("precision") val precision: Float,
    @SerializedName("recall") val recall: Float,
    @SerializedName("f1Score") val f1Score: Float
)

/**
 * UI State for Risk Calculator
 */
data class CalculatorState(
    val isLoading: Boolean = false,
    val result: PredictionResponse? = null,
    val error: String? = null
)
