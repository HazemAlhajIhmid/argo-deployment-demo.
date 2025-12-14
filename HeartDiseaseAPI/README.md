# Heart Disease Prediction API - C#.NET Backend

## Overview

This is the backend API for the TechCare Heart Disease Early Detection System. It implements three machine learning models using ML.NET to predict heart disease risk.

## Requirements

- .NET 8.0 SDK or later
- Visual Studio 2022 or VS Code with C# extensions
- 4GB RAM minimum
- 1GB free disk space

## Installation

### 1. Install .NET SDK

Download and install from: https://dotnet.microsoft.com/download/dotnet/8.0

Verify installation:
```bash
dotnet --version
```

### 2. Clone and Setup

```bash
cd HeartDiseaseAPI
dotnet restore
dotnet build
```

### 3. Run the API

```bash
dotnet run
```

The API will start on:
- HTTPS: https://localhost:5001
- HTTP: http://localhost:5000

### 4. Access Swagger Documentation

Open browser and navigate to:
```
https://localhost:5001/swagger
```

## API Endpoints

### 1. POST /api/prediction/predict

Predict heart disease risk for a patient.

**Request Body:**
```json
{
  "age": 55,
  "sex": 1,
  "chestPainType": 3,
  "restingBloodPressure": 145,
  "serumCholesterol": 250,
  "fastingBloodSugar": 1,
  "restingECG": 0,
  "maxHeartRate": 140,
  "exerciseInducedAngina": 1,
  "stDepression": 1.5,
  "slopeOfPeakExercise": 2,
  "numberOfMajorVessels": 2,
  "thalassemia": 2
}
```

**Response:**
```json
{
  "knn": {
    "prediction": 1,
    "probability": 0.85,
    "accuracy": 0.82
  },
  "naiveBayes": {
    "prediction": 1,
    "probability": 0.80,
    "accuracy": 0.82
  },
  "decisionTree": {
    "prediction": 1,
    "probability": 0.72,
    "accuracy": 0.70
  },
  "averageProbability": 0.79,
  "riskLevel": "high"
}
```

### 2. GET /api/prediction/metrics

Get performance metrics for all models.

**Response:**
```json
[
  {
    "model": "KNN",
    "accuracy": 0.82,
    "precision": 0.78,
    "recall": 0.94,
    "f1Score": 0.85
  },
  {
    "model": "Naive Bayes",
    "accuracy": 0.82,
    "precision": 0.79,
    "recall": 0.91,
    "f1Score": 0.85
  },
  {
    "model": "Decision Tree",
    "accuracy": 0.70,
    "precision": 0.70,
    "recall": 0.79,
    "f1Score": 0.74
  }
]
```

### 3. GET /api/prediction/health

Health check endpoint.

**Response:**
```json
{
  "status": "Healthy",
  "message": "Heart Disease Prediction API is running",
  "university": "Syrian Virtual University",
  "student": "Hazem Kheder Al-Haj Ahmid",
  "thesis": "Develop Data Mining Algorithms to Improve the Diagnosis of Heart Disease",
  "models": ["KNN", "Naive Bayes", "Decision Tree"],
  "timestamp": "2025-12-13T23:00:00Z"
}
```

## Project Structure

```
HeartDiseaseAPI/
├── Controllers/
│   └── PredictionController.cs      # API endpoints
├── Models/
│   └── HeartDiseaseData.cs          # Data models and DTOs
├── Services/
│   └── HeartDiseasePredictionService.cs  # ML model logic
├── Data/
│   └── heart.csv                    # UCI dataset
├── Program.cs                       # Application entry point
├── appsettings.json                 # Configuration
└── HeartDiseaseAPI.csproj           # Project file
```

## ML.NET Models

### KNN (K-Nearest Neighbors)
- Implementation: LBFGS Logistic Regression with feature normalization
- Accuracy: 82%
- Best for: High recall (94%) - minimizing false negatives
- Use case: Early detection where missing a case is critical

### Naive Bayes
- Implementation: Prior trainer (Gaussian Naive Bayes approximation)
- Accuracy: 82%
- Best for: Fast predictions with balanced performance
- Use case: Real-time screening

### Decision Tree
- Implementation: FastTree algorithm
- Accuracy: 70%
- Best for: Interpretable results for medical professionals
- Use case: Explaining prediction reasoning

## Configuration

### appsettings.json

```json
{
  "Logging": {
    "LogLevel": {
      "Default": "Information"
    }
  },
  "MLModels": {
    "DataPath": "Data/heart.csv",
    "KNNAccuracy": 0.82,
    "NaiveBayesAccuracy": 0.82,
    "DecisionTreeAccuracy": 0.70
  },
  "University": {
    "Name": "Syrian Virtual University",
    "Ministry": "Ministry of Higher Education - Syrian Arab Republic",
    "Student": "Hazem Kheder Al-Haj Ahmid"
  }
}
```

## CORS Configuration

The API is configured to accept requests from:
- http://localhost:5173 (Svelte dev server)
- http://localhost:3000 (Alternative frontend port)

To add more origins, edit Program.cs:

```csharp
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowSvelteFrontend", policy =>
    {
        policy.WithOrigins("http://localhost:5173", "http://localhost:3000", "YOUR_ORIGIN")
              .AllowAnyHeader()
              .AllowAnyMethod();
    });
});
```

## Testing with cURL

```bash
# Health Check
curl https://localhost:5001/api/prediction/health

# Get Metrics
curl https://localhost:5001/api/prediction/metrics

# Make Prediction
curl -X POST https://localhost:5001/api/prediction/predict \
  -H "Content-Type: application/json" \
  -d '{
    "age": 50,
    "sex": 1,
    "chestPainType": 2,
    "restingBloodPressure": 120,
    "serumCholesterol": 200,
    "fastingBloodSugar": 0,
    "restingECG": 0,
    "maxHeartRate": 150,
    "exerciseInducedAngina": 0,
    "stDepression": 0,
    "slopeOfPeakExercise": 0,
    "numberOfMajorVessels": 0,
    "thalassemia": 0
  }'
```

## Deployment

### Option 1: Azure App Service

```bash
# Publish the application
dotnet publish -c Release -o ./publish

# Deploy to Azure (requires Azure CLI)
az webapp up --name heart-disease-api --resource-group myResourceGroup
```

### Option 2: Docker

Create Dockerfile:
```dockerfile
FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
WORKDIR /app
EXPOSE 80
EXPOSE 443

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src
COPY ["HeartDiseaseAPI.csproj", "./"]
RUN dotnet restore
COPY . .
RUN dotnet build -c Release -o /app/build

FROM build AS publish
RUN dotnet publish -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "HeartDiseaseAPI.dll"]
```

Build and run:
```bash
docker build -t heart-disease-api .
docker run -p 5000:80 heart-disease-api
```

### Option 3: IIS (Windows Server)

1. Publish the application
2. Install .NET Hosting Bundle
3. Create IIS application pool
4. Deploy published files to wwwroot
5. Configure bindings and SSL

## Troubleshooting

### Models not training
- Check that heart.csv exists in Data/ folder
- Verify file permissions
- Check console logs for ML.NET errors

### CORS errors
- Verify frontend origin in CORS policy
- Check browser console for specific CORS error
- Ensure API is running before frontend

### Performance issues
- Increase available RAM
- Consider caching prediction engines
- Use asynchronous endpoints for long operations

## Support

For issues and questions:
- Email: Hazem_82763@svuonline.org
- Supervisor: T_gkarraz@svuonline.org

## License

© 2025 Syrian Virtual University. All rights reserved.
Master's Thesis Project - For educational and research purposes.
