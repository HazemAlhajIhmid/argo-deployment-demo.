using Microsoft.ML.Data;

namespace HeartDiseaseAPI.Models
{
    /// <summary>
    /// Heart Disease Patient Data Model
    /// Represents the 14 medical parameters used for prediction
    /// </summary>
    public class HeartDiseaseData
    {
        [LoadColumn(0)]
        public float Age { get; set; }

        [LoadColumn(1)]
        public float Sex { get; set; } // 1 = male, 0 = female

        [LoadColumn(2)]
        public float ChestPainType { get; set; } // 0-3

        [LoadColumn(3)]
        public float RestingBloodPressure { get; set; } // mm Hg

        [LoadColumn(4)]
        public float SerumCholesterol { get; set; } // mg/dl

        [LoadColumn(5)]
        public float FastingBloodSugar { get; set; } // > 120 mg/dl (1 = true, 0 = false)

        [LoadColumn(6)]
        public float RestingECG { get; set; } // 0-2

        [LoadColumn(7)]
        public float MaxHeartRate { get; set; } // achieved

        [LoadColumn(8)]
        public float ExerciseInducedAngina { get; set; } // 1 = yes, 0 = no

        [LoadColumn(9)]
        public float STDepression { get; set; } // oldpeak

        [LoadColumn(10)]
        public float SlopeOfPeakExercise { get; set; } // 0-2

        [LoadColumn(11)]
        public float NumberOfMajorVessels { get; set; } // 0-3

        [LoadColumn(12)]
        public float Thalassemia { get; set; } // 0-2

        [LoadColumn(13)]
        [ColumnName("Label")]
        public bool Target { get; set; } // 1 = disease, 0 = no disease
    }

    /// <summary>
    /// Prediction result from ML model
    /// </summary>
    public class HeartDiseasePrediction
    {
        [ColumnName("PredictedLabel")]
        public bool Prediction { get; set; }

        [ColumnName("Probability")]
        public float Probability { get; set; }

        [ColumnName("Score")]
        public float Score { get; set; }
    }

    /// <summary>
    /// Request model for prediction API
    /// </summary>
    public class PredictionRequest
    {
        public float Age { get; set; }
        public float Sex { get; set; }
        public float ChestPainType { get; set; }
        public float RestingBloodPressure { get; set; }
        public float SerumCholesterol { get; set; }
        public float FastingBloodSugar { get; set; }
        public float RestingECG { get; set; }
        public float MaxHeartRate { get; set; }
        public float ExerciseInducedAngina { get; set; }
        public float STDepression { get; set; }
        public float SlopeOfPeakExercise { get; set; }
        public float NumberOfMajorVessels { get; set; }
        public float Thalassemia { get; set; }
    }

    /// <summary>
    /// Complete prediction response with all three models
    /// </summary>
    public class PredictionResponse
    {
        public ModelPrediction KNN { get; set; } = new();
        public ModelPrediction NaiveBayes { get; set; } = new();
        public ModelPrediction DecisionTree { get; set; } = new();
        public float AverageProbability { get; set; }
        public string RiskLevel { get; set; } = string.Empty;
    }

    /// <summary>
    /// Individual model prediction result
    /// </summary>
    public class ModelPrediction
    {
        public int Prediction { get; set; } // 1 = disease, 0 = no disease
        public float Probability { get; set; }
        public float Accuracy { get; set; }
    }
}
