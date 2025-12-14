using Microsoft.AspNetCore.Mvc;
using HeartDiseaseAPI.Models;
using HeartDiseaseAPI.Services;

namespace HeartDiseaseAPI.Controllers
{
    /// <summary>
    /// Heart Disease Prediction API Controller
    /// Syrian Virtual University Master's Thesis
    /// Student: Hazem Kheder Al-Haj Ahmid
    /// </summary>
    [ApiController]
    [Route("api/[controller]")]
    public class PredictionController : ControllerBase
    {
        private readonly HeartDiseasePredictionService _predictionService;
        private readonly ILogger<PredictionController> _logger;

        public PredictionController(
            HeartDiseasePredictionService predictionService,
            ILogger<PredictionController> logger)
        {
            _predictionService = predictionService;
            _logger = logger;
        }

        /// <summary>
        /// Predict heart disease risk using all three ML models
        /// </summary>
        /// <param name="request">Patient medical data</param>
        /// <returns>Prediction results from KNN, Naive Bayes, and Decision Tree models</returns>
        [HttpPost("predict")]
        [ProducesResponseType(typeof(PredictionResponse), StatusCodes.Status200OK)]
        [ProducesResponseType(StatusCodes.Status400BadRequest)]
        [ProducesResponseType(StatusCodes.Status500InternalServerError)]
        public ActionResult<PredictionResponse> Predict([FromBody] PredictionRequest request)
        {
            try
            {
                _logger.LogInformation("Prediction request received for age: {Age}", request.Age);

                // Validate request
                if (!IsValidRequest(request))
                {
                    return BadRequest("Invalid input data. Please check all parameters.");
                }

                var result = _predictionService.Predict(request);

                _logger.LogInformation("Prediction completed. Risk Level: {RiskLevel}", result.RiskLevel);

                return Ok(result);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error during prediction");
                return StatusCode(500, "An error occurred during prediction. Please try again.");
            }
        }

        /// <summary>
        /// Get model performance metrics
        /// </summary>
        /// <returns>Performance metrics for all three models</returns>
        [HttpGet("metrics")]
        [ProducesResponseType(typeof(List<ModelMetrics>), StatusCodes.Status200OK)]
        public ActionResult<List<ModelMetrics>> GetMetrics()
        {
            try
            {
                var metrics = _predictionService.GetModelMetrics();
                return Ok(metrics);
            }
            catch (Exception ex)
            {
                _logger.LogError(ex, "Error retrieving metrics");
                return StatusCode(500, "An error occurred while retrieving metrics.");
            }
        }

        /// <summary>
        /// Health check endpoint
        /// </summary>
        [HttpGet("health")]
        public ActionResult<object> HealthCheck()
        {
            return Ok(new
            {
                Status = "Healthy",
                Message = "Heart Disease Prediction API is running",
                University = "Syrian Virtual University",
                Student = "Hazem Kheder Al-Haj Ahmid",
                Thesis = "Develop Data Mining Algorithms to Improve the Diagnosis of Heart Disease",
                Models = new[] { "KNN", "Naive Bayes", "Decision Tree" },
                Timestamp = DateTime.UtcNow
            });
        }

        /// <summary>
        /// Validate prediction request
        /// </summary>
        private bool IsValidRequest(PredictionRequest request)
        {
            return request.Age > 0 && request.Age < 120 &&
                   request.Sex >= 0 && request.Sex <= 1 &&
                   request.ChestPainType >= 0 && request.ChestPainType <= 3 &&
                   request.RestingBloodPressure > 0 && request.RestingBloodPressure < 300 &&
                   request.SerumCholesterol > 0 && request.SerumCholesterol < 1000 &&
                   request.FastingBloodSugar >= 0 && request.FastingBloodSugar <= 1 &&
                   request.RestingECG >= 0 && request.RestingECG <= 2 &&
                   request.MaxHeartRate > 0 && request.MaxHeartRate < 250 &&
                   request.ExerciseInducedAngina >= 0 && request.ExerciseInducedAngina <= 1 &&
                   request.STDepression >= 0 && request.STDepression <= 10 &&
                   request.SlopeOfPeakExercise >= 0 && request.SlopeOfPeakExercise <= 2 &&
                   request.NumberOfMajorVessels >= 0 && request.NumberOfMajorVessels <= 3 &&
                   request.Thalassemia >= 0 && request.Thalassemia <= 2;
        }
    }
}
