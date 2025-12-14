package com.svu.heartdisease.network

import com.svu.heartdisease.models.ModelMetrics
import com.svu.heartdisease.models.PatientData
import com.svu.heartdisease.models.PredictionResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Retrofit API Service for Heart Disease Prediction
 * Connects to C#.NET Backend API
 */
interface HeartDiseaseApiService {

    @POST("api/prediction/predict")
    suspend fun predictHeartDisease(@Body patientData: PatientData): Response<PredictionResponse>

    @GET("api/prediction/metrics")
    suspend fun getModelMetrics(): Response<List<ModelMetrics>>

    @GET("api/prediction/health")
    suspend fun healthCheck(): Response<Map<String, Any>>

    companion object {
        private const val BASE_URL = "https://your-api-url.com/" // Replace with actual API URL

        fun create(): HeartDiseaseApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(HeartDiseaseApiService::class.java)
        }

        // For local development
        fun createLocal(): HeartDiseaseApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://10.0.2.2:5000/") // Android emulator localhost
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(HeartDiseaseApiService::class.java)
        }
    }
}

/**
 * Repository for API calls
 */
class HeartDiseaseRepository(private val apiService: HeartDiseaseApiService) {

    suspend fun predictHeartDisease(patientData: PatientData): Result<PredictionResponse> {
        return try {
            val response = apiService.predictHeartDisease(patientData)
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Prediction failed: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getModelMetrics(): Result<List<ModelMetrics>> {
        return try {
            val response = apiService.getModelMetrics()
            if (response.isSuccessful && response.body() != null) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to get metrics: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun checkHealth(): Result<Boolean> {
        return try {
            val response = apiService.healthCheck()
            Result.success(response.isSuccessful)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
