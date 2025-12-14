# TechCare - Early Detection of Heart Disease System
## Syrian Virtual University - Master's Thesis Project

**Student:** Hazem Khader Al-Haj Ahmid (Hazem_82763@svuonline.org)
**Supervisor:** Dr. George Anwar Karraz (T_gkarraz@svuonline.org)
**Co-Supervisor:** Dr. Majeda Al-Bakour (T_mbakour@svuonline.org)
**Institution:** Syrian Virtual University - Ministry of Higher Education
**Registration Period:** S25 (2025)

---

## 📋 Project Overview

This project implements an intelligent system for early detection of heart disease using machine learning algorithms. The system consists of three components:

1. **Svelte Frontend** - Modern web interface with Arabic/English support
2. **C#.NET Backend** - REST API with ML.NET models
3. **Android App** - Native mobile application with Kotlin

### Research Title
**Arabic:** تطوير خوارزميات التنقيب عن البيانات في تحسين عملية تشخيص أمراض القلب
**English:** Develop Data Mining Algorithms to Improve the Diagnosis of Heart Disease

### Keywords
Data Mining, Naive Bayes, KNN, Decision Tree (ID3), Classification, Heart Disease

---

## 🎯 Machine Learning Models

The system implements three machine learning algorithms:

| Model | Accuracy | Precision | Recall | F1-Score | Best For |
|-------|----------|-----------|--------|----------|----------|
| **KNN** | 82% | 78% | 94% | 85% | Early detection (highest recall) |
| **Naive Bayes** | 82% | 79% | 91% | 85% | Balanced performance |
| **Decision Tree** | 70% | 70% | 79% | 74% | Interpretability |

### Dataset
- **Source:** UCI Heart Disease Dataset
- **Records:** 303 patients
- **Features:** 14 clinical variables
- **Link:** https://github.com/sharmaroshan/Heart-UCI-Dataset

---

## 🚀 Quick Start

### Prerequisites
- **Frontend:** Node.js 22+, npm
- **Backend:** .NET 8 SDK
- **Android:** Android Studio with Kotlin support
- **Database:** SQL Server or LocalDB

---

## 💻 Frontend (Svelte)

### Installation

```bash
cd heart-disease-detection/frontend
npm install
npm run dev
```

The application will be available at `http://localhost:3000`

### Features
- ✅ Bilingual interface (Arabic RTL / English LTR)
- ✅ Interactive risk calculator with 14 medical parameters
- ✅ Real-time prediction using three ML models
- ✅ Data visualization with charts
- ✅ Responsive design for all devices
- ✅ Syrian Virtual University branding

### Technology Stack
- **Framework:** SvelteKit
- **Styling:** Tailwind CSS v4
- **Charts:** Chart.js
- **State Management:** Svelte stores
- **Build Tool:** Vite

### Project Structure
```
frontend/
├── src/
│   ├── lib/
│   │   ├── components/
│   │   │   ├── Header.svelte
│   │   │   ├── Hero.svelte
│   │   │   ├── RiskCalculator.svelte
│   │   │   ├── About.svelte
│   │   │   ├── ModelComparison.svelte
│   │   │   └── Footer.svelte
│   │   └── stores/
│   │       └── language.ts
│   ├── routes/
│   │   ├── +layout.svelte
│   │   └── +page.svelte
│   └── app.css
├── static/
│   └── data/
│       └── heart.csv
├── tailwind.config.js
├── postcss.config.js
└── package.json
```

### API Integration
The frontend is configured to connect to the C#.NET backend. Update the API URL in your code:

```typescript
const API_URL = 'https://localhost:5001/api';
```

---

## 🔧 Backend (C#.NET)

### Installation

1. **Navigate to backend directory:**
```bash
cd heart-disease-detection/backend/HeartDiseaseAPI
```

2. **Restore dependencies:**
```bash
dotnet restore
```

3. **Copy the heart.csv dataset:**
```bash
mkdir Data
cp ../../frontend/static/data/heart.csv Data/
```

4. **Update database connection string in `appsettings.json`:**
```json
{
  "ConnectionStrings": {
    "HeartDiseaseDB": "Server=(localdb)\\mssqllocaldb;Database=HeartDiseaseDB;Trusted_Connection=true"
  }
}
```

5. **Run migrations:**
```bash
dotnet ef migrations add InitialCreate
dotnet ef database update
```

6. **Run the API:**
```bash
dotnet run
```

The API will be available at `https://localhost:5001`

### API Endpoints

#### 1. Predict Heart Disease
```http
POST /api/prediction/predict
Content-Type: application/json

{
  "age": 50,
  "sex": 1,
  "cp": 0,
  "trestbps": 120,
  "chol": 200,
  "fbs": 0,
  "restecg": 0,
  "thalach": 150,
  "exang": 0,
  "oldpeak": 0.0,
  "slope": 0,
  "ca": 0,
  "thal": 0
}
```

**Response:**
```json
{
  "knn": {
    "prediction": true,
    "confidence": 85.5,
    "accuracy": 82
  },
  "naiveBayes": {
    "prediction": true,
    "confidence": 82.3,
    "accuracy": 82
  },
  "decisionTree": {
    "prediction": false,
    "confidence": 65.1,
    "accuracy": 70
  },
  "ensemble": {
    "prediction": true,
    "riskLevel": "high",
    "riskScore": 78.5
  }
}
```

#### 2. Get Model Metrics
```http
GET /api/prediction/metrics
```

#### 3. Health Check
```http
GET /api/prediction/health
```

### Project Structure
```
backend/HeartDiseaseAPI/
├── Controllers/
│   └── PredictionController.cs
├── Models/
│   └── HeartDiseaseData.cs
├── Services/
│   ├── KNNModelService.cs
│   ├── NaiveBayesModelService.cs
│   ├── DecisionTreeModelService.cs
│   └── PredictionService.cs
├── Data/
│   ├── HeartDiseaseContext.cs
│   └── heart.csv
├── Models/
│   ├── knn_model.zip
│   ├── naive_bayes_model.zip
│   └── decision_tree_model.zip
├── Program.cs
├── appsettings.json
└── HeartDiseaseAPI.csproj
```

### ML.NET Models

The backend uses ML.NET for implementing the three machine learning models:

1. **KNN (K-Nearest Neighbors)**
   - Uses LBFGS Logistic Regression as approximation
   - Normalized features with StandardScaler
   - Optimal K value determined through GridSearch

2. **Naive Bayes**
   - Implemented using SDCA (Stochastic Dual Coordinate Ascent)
   - Calibrated probabilities with Platt scaling
   - Fast and efficient for real-time predictions

3. **Decision Tree**
   - FastTree algorithm with pruning
   - Parameters tuned to prevent overfitting
   - 20 leaves, minimum 10 examples per leaf

---

## 📱 Android App (Kotlin)

### Installation

1. **Open Android Studio**

2. **Open project:**
   - File → Open → Navigate to `android/HeartDiseaseApp`

3. **Update API URL in `ApiService.kt`:**
```kotlin
private const val BASE_URL = "https://your-api-url.com/api/"
```

4. **Sync Gradle and run the app**

### Features
- ✅ Native Android UI with Material Design
- ✅ Arabic language support
- ✅ Risk calculator with validation
- ✅ Real-time predictions from C#.NET API
- ✅ Results visualization
- ✅ About page with research information

### Project Structure
```
android/HeartDiseaseApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/techcare/heartdisease/
│   │       │   ├── MainActivity.kt
│   │       │   ├── RiskCalculatorActivity.kt
│   │       │   ├── ResultsActivity.kt
│   │       │   ├── AboutActivity.kt
│   │       │   ├── models/
│   │       │   │   ├── PredictionRequest.kt
│   │       │   │   └── PredictionResponse.kt
│   │       │   ├── network/
│   │       │   │   ├── ApiService.kt
│   │       │   │   └── RetrofitClient.kt
│   │       │   └── viewmodels/
│   │       │       └── PredictionViewModel.kt
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   ├── values/
│   │       │   └── drawable/
│   │       └── AndroidManifest.xml
│   └── build.gradle
└── build.gradle
```

### Dependencies
- Retrofit 2 for API calls
- Kotlin Coroutines for async operations
- ViewModel and LiveData for architecture
- Material Design Components
- MPAndroidChart for data visualization

---

## 📊 Research Methodology

### 1. Data Collection
- Used UCI Heart Disease dataset
- 303 records with 14 clinical variables
- No missing values, ready for processing

### 2. Data Preprocessing
- Separated features (X) from target variable (y)
- Applied StandardScaler for normalization
- Split data: 80% training, 20% testing
- Used stratification to maintain class balance

### 3. Model Training
Three models trained and evaluated:
- **KNN:** GridSearchCV for optimal K value
- **Naive Bayes:** GaussianNB for numerical data
- **Decision Tree:** With pruning to prevent overfitting

### 4. Evaluation
Models evaluated using:
- Accuracy
- Precision
- Recall (most important for medical diagnosis)
- F1-Score
- Confusion Matrix
- ROC Curves

### 5. Results
- **Best for early detection:** KNN (94% recall)
- **Most balanced:** Naive Bayes
- **Most interpretable:** Decision Tree (needs improvement)

---

## 🔍 Clinical Parameters

| Parameter | Arabic Name | Range | Description |
|-----------|-------------|-------|-------------|
| Age | العمر | 20-100 | Patient age in years |
| Sex | الجنس | 0-1 | 0 = Female, 1 = Male |
| CP | نوع ألم الصدر | 0-3 | Chest pain type |
| Trestbps | ضغط الدم | 80-200 mmHg | Resting blood pressure |
| Chol | الكوليسترول | 100-600 mg/dl | Serum cholesterol |
| FBS | سكر الدم | 0-1 | Fasting blood sugar > 120 |
| RestECG | تخطيط القلب | 0-2 | Resting ECG results |
| Thalach | معدل النبض | 60-220 | Maximum heart rate |
| Exang | ذبحة التمارين | 0-1 | Exercise induced angina |
| Oldpeak | انخفاض ST | 0-6 | ST depression by exercise |
| Slope | ميل ST | 0-2 | Slope of peak exercise ST |
| CA | الأوعية | 0-3 | Number of major vessels |
| Thal | الثاليوم | 0-3 | Thalassemia test result |

---

## ⚠️ Important Disclaimers

1. **Research Purpose Only:** This system is designed for academic research and should not be used as a substitute for professional medical diagnosis.

2. **Medical Consultation Required:** Always consult qualified healthcare professionals for medical decisions.

3. **Not FDA Approved:** This system has not been evaluated or approved by any medical regulatory authority.

4. **Data Privacy:** Ensure HIPAA compliance and patient data protection when deploying in real environments.

---

## 🛠️ Development

### Running All Components

1. **Start Backend:**
```bash
cd backend/HeartDiseaseAPI
dotnet run
```

2. **Start Frontend:**
```bash
cd frontend
npm run dev
```

3. **Open Android App:**
   - Open Android Studio
   - Run on emulator or device

### Building for Production

**Frontend:**
```bash
cd frontend
npm run build
npm run preview
```

**Backend:**
```bash
cd backend/HeartDiseaseAPI
dotnet publish -c Release
```

**Android:**
- Build → Generate Signed Bundle / APK
- Follow Android signing process

---

## 📚 References

1. UCI Machine Learning Repository - Heart Disease Dataset
2. ML.NET Documentation - Microsoft
3. SvelteKit Documentation
4. Android Kotlin Development Guidelines
5. Research papers on heart disease prediction using ML

---

## 📄 License

This project is part of a Master's thesis at Syrian Virtual University.
© 2025 Syrian Virtual University. All rights reserved.

For academic use and reference only.

---

## 👥 Contact Information

**Student:**
- Name: Hazem Khader Al-Haj Ahmid
- Email: Hazem_82763@svuonline.org

**Main Supervisor:**
- Name: Dr. George Anwar Karraz
- Email: T_gkarraz@svuonline.org

**Co-Supervisor:**
- Name: Dr. Majeda Al-Bakour
- Email: T_mbakour@svuonline.org

**Institution:**
- Syrian Virtual University
- Ministry of Higher Education - Syrian Arab Republic

---

## 🎓 Acknowledgments

Special thanks to:
- Syrian Virtual University for providing the academic platform
- Supervisors for their guidance and support
- UCI Machine Learning Repository for the dataset
- Open-source community for tools and libraries

---

**Last Updated:** December 2025
**Version:** 1.0.0
**Status:** ✅ Complete and Functional
