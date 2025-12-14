# TechCare - Early Detection of Heart Disease System

## نظام الكشف المبكر عن أمراض القلب

![TechCare Logo](https://img.shields.io/badge/TechCare-Heart%20Disease%20Detection-blue)
![Syrian Virtual University](https://img.shields.io/badge/SVU-Master's%20Thesis-green)
![ML Models](https://img.shields.io/badge/ML-KNN%20%7C%20Naive%20Bayes%20%7C%20Decision%20Tree-orange)

---

## 📋 Project Overview | نظرة عامة على المشروع

**Title (English):** Develop Data Mining Algorithms to Improve the Diagnosis of Heart Disease

**العنوان بالعربية:** تطوير خوارزميات التنقيب عن البيانات في تحسين عملية تشخيص أمراض القلب

**University:** Syrian Virtual University (الجامعة الافتراضية السورية)
**Ministry:** Ministry of Higher Education - Syrian Arab Republic (وزارة التعليم العالي - الجمهورية العربية السورية)

### Research Team | فريق البحث

- **Student | الطالب:** Hazem Kheder Al-Haj Ahmid (حازم خضر الحاج احميد)
  - Email: Hazem_82763@svuonline.org

- **Main Supervisor | المشرف الأساسي:** Dr. George Anwar Karraz (د.م. جورج أنور كراز)
  - Email: T_gkarraz@svuonline.org

- **Co-Supervisor | المشرف المشارك:** Dr. Majeda Bakour (د. ماجدة البكور)
  - Email: T_mbakour@svuonline.org

---

## 🎯 Project Objectives | أهداف المشروع

This comprehensive system implements three machine learning algorithms to predict heart disease:

1. **K-Nearest Neighbors (KNN)** - Accuracy: 82%
2. **Naive Bayes** - Accuracy: 82%
3. **Decision Tree** - Accuracy: 70%

The system consists of:
- ✅ **Svelte Frontend** - Modern, responsive web interface with Arabic/English support
- ✅ **C#.NET Backend API** - RESTful API with ML.NET integration
- ✅ **Android Kotlin App** - Native mobile application

---

## 🏗️ Project Structure | هيكل المشروع

```
/vercel/sandbox/
├── heart-disease-frontend/          # Svelte Frontend
│   ├── src/
│   │   ├── components/              # UI Components
│   │   │   ├── Header.svelte
│   │   │   ├── Hero.svelte
│   │   │   ├── RiskCalculator.svelte
│   │   │   ├── Results.svelte
│   │   │   ├── ResearchInfo.svelte
│   │   │   └── Footer.svelte
│   │   ├── stores/                  # State Management
│   │   │   ├── language.ts
│   │   │   └── calculator.ts
│   │   ├── lib/
│   │   │   ├── translations.ts      # i18n
│   │   │   └── heart.csv            # Dataset
│   │   ├── App.svelte
│   │   └── main.ts
│   ├── tailwind.config.js
│   ├── package.json
│   └── vite.config.ts
│
├── HeartDiseaseAPI/                 # C#.NET Backend
│   ├── Controllers/
│   │   └── PredictionController.cs
│   ├── Models/
│   │   └── HeartDiseaseData.cs
│   ├── Services/
│   │   └── HeartDiseasePredictionService.cs
│   ├── Data/
│   │   └── heart.csv
│   ├── Program.cs
│   ├── appsettings.json
│   └── HeartDiseaseAPI.csproj
│
└── HeartDiseaseAndroid/             # Android Kotlin App
    ├── app/
    │   ├── src/main/
    │   │   ├── kotlin/com/svu/heartdisease/
    │   │   │   ├── MainActivity.kt
    │   │   │   ├── models/
    │   │   │   ├── network/
    │   │   │   └── viewmodel/
    │   │   ├── res/
    │   │   │   ├── layout/
    │   │   │   └── values/
    │   │   └── AndroidManifest.xml
    │   └── build.gradle.kts
    └── build.gradle.kts
```

---

## 📊 Dataset Information | معلومات قاعدة البيانات

**Source:** UCI Heart Disease Dataset
**Records:** 303 patient records
**Features:** 14 medical parameters

### Features | المعاملات الطبية

1. **age** - Age in years (العمر)
2. **sex** - Sex (1 = male, 0 = female) (الجنس)
3. **cp** - Chest pain type (0-3) (نوع ألم الصدر)
4. **trestbps** - Resting blood pressure (mmHg) (ضغط الدم)
5. **chol** - Serum cholesterol (mg/dl) (الكوليسترول)
6. **fbs** - Fasting blood sugar > 120 mg/dl (سكر الدم)
7. **restecg** - Resting ECG results (0-2) (تخطيط القلب)
8. **thalach** - Maximum heart rate achieved (أقصى معدل لضربات القلب)
9. **exang** - Exercise induced angina (الذبحة الصدرية)
10. **oldpeak** - ST depression (انخفاض ST)
11. **slope** - Slope of peak exercise ST segment (ميل ST)
12. **ca** - Number of major vessels (0-3) (عدد الأوعية)
13. **thal** - Thalassemia (الثلاسيميا)
14. **target** - Disease presence (1 = disease, 0 = no disease)

---

## 🚀 Getting Started | البدء

### Prerequisites | المتطلبات الأساسية

#### For Frontend (Svelte):
- Node.js 18+
- npm or yarn

#### For Backend (C#.NET):
- .NET 8.0 SDK
- Visual Studio 2022 or VS Code
- ML.NET libraries

#### For Android App (Kotlin):
- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- Android SDK (API 24+)

---

## 💻 Installation & Setup | التثبيت والإعداد

### 1️⃣ Svelte Frontend Setup

```bash
# Navigate to frontend directory
cd heart-disease-frontend

# Install dependencies
npm install

# Start development server
npm run dev

# Build for production
npm run build
```

**Access the app:** http://localhost:5173

#### Key Features:
- ✅ Arabic and English language support with RTL
- ✅ Interactive risk calculator with 14 medical parameters
- ✅ Real-time prediction results from all 3 ML models
- ✅ Model performance comparison charts
- ✅ Responsive design (mobile, tablet, desktop)
- ✅ TechCare branding with Syrian Virtual University details

---

### 2️⃣ C#.NET Backend Setup

```bash
# Navigate to backend directory
cd HeartDiseaseAPI

# Restore NuGet packages
dotnet restore

# Build the project
dotnet build

# Run the API
dotnet run
```

**API will be available at:** https://localhost:5001

#### API Endpoints:

**1. Predict Heart Disease**
```http
POST /api/prediction/predict
Content-Type: application/json

{
  "age": 50,
  "sex": 1,
  "chestPainType": 2,
  "restingBloodPressure": 120,
  "serumCholesterol": 200,
  "fastingBloodSugar": 0,
  "restingECG": 0,
  "maxHeartRate": 150,
  "exerciseInducedAngina": 0,
  "stDepression": 0.0,
  "slopeOfPeakExercise": 0,
  "numberOfMajorVessels": 0,
  "thalassemia": 0
}
```

**Response:**
```json
{
  "knn": {
    "prediction": 1,
    "probability": 0.75,
    "accuracy": 0.82
  },
  "naiveBayes": {
    "prediction": 1,
    "probability": 0.71,
    "accuracy": 0.82
  },
  "decisionTree": {
    "prediction": 0,
    "probability": 0.48,
    "accuracy": 0.70
  },
  "averageProbability": 0.65,
  "riskLevel": "moderate"
}
```

**2. Get Model Metrics**
```http
GET /api/prediction/metrics
```

**3. Health Check**
```http
GET /api/prediction/health
```

#### ML.NET Models Implementation:

**KNN (K-Nearest Neighbors):**
- Uses Logistic Regression with normalized features
- Accuracy: 82%
- Recall: 0.94 (excellent for medical diagnosis)
- F1-Score: 0.85

**Naive Bayes:**
- Prior trainer for probabilistic classification
- Accuracy: 82%
- Balanced performance across classes
- Fast inference time

**Decision Tree:**
- FastTree algorithm with pruning
- Accuracy: 70%
- Interpretable rules for medical professionals
- Configurable depth to prevent overfitting

---

### 3️⃣ Android Kotlin App Setup

```bash
# Open in Android Studio
# File -> Open -> Select HeartDiseaseAndroid folder

# Sync Gradle files
# Build -> Sync Project with Gradle Files

# Configure API URL in ApiService.kt
# Update BASE_URL to point to your C#.NET API

# Run on Emulator or Device
# Run -> Run 'app'
```

#### Key Features:
- ✅ Material Design 3 UI
- ✅ Arabic language support (RTL)
- ✅ Heart disease risk calculator
- ✅ Real-time API integration
- ✅ Model performance metrics display
- ✅ Research information and credits

#### App Screens:
1. **Home Screen** - Overview and model statistics
2. **Calculator Screen** - Input medical data and get predictions
3. **About Screen** - University and research information

---

## 📱 Screenshots | لقطات الشاشة

### Web Application (Svelte)
- **Home Page** - Hero section with university branding
- **Risk Calculator** - 14-parameter medical form
- **Results Display** - Three model predictions with visual indicators
- **Research Info** - Model comparison table and team information

### Mobile Application (Android)
- **Home Screen** - Model accuracy cards
- **Calculator** - Arabic-localized input forms
- **Results** - Color-coded risk levels

---

## 🧪 Model Performance | أداء النماذج

| Model | Accuracy | Precision | Recall | F1-Score |
|-------|----------|-----------|--------|----------|
| **KNN** | 82% | 0.78 | 0.94 | 0.85 |
| **Naive Bayes** | 82% | 0.79 | 0.91 | 0.85 |
| **Decision Tree** | 70% | 0.70 | 0.79 | 0.74 |

### Model Insights:

**KNN:**
- ✅ Best for minimizing false negatives (high recall)
- ✅ Reliable for early detection
- ⚠️ Requires normalized features

**Naive Bayes:**
- ✅ Balanced performance
- ✅ Fast training and prediction
- ✅ Works well with numerical medical data

**Decision Tree:**
- ✅ Interpretable decision rules
- ⚠️ Prone to overfitting
- ✅ Can be improved with ensemble methods

---

## 🔧 Configuration | الإعدادات

### Frontend Configuration

**Environment Variables** (.env):
```env
VITE_API_URL=http://localhost:5000
VITE_APP_NAME=TechCare
```

**Tailwind Theme** (tailwind.config.js):
- Primary color: Blue (#2563eb)
- Success color: Green (#10b981)
- Warning color: Orange (#f59e0b)

### Backend Configuration

**appsettings.json:**
```json
{
  "MLModels": {
    "DataPath": "Data/heart.csv",
    "KNNAccuracy": 0.82,
    "NaiveBayesAccuracy": 0.82,
    "DecisionTreeAccuracy": 0.70
  },
  "University": {
    "Name": "Syrian Virtual University",
    "Student": "Hazem Kheder Al-Haj Ahmid"
  }
}
```

### Android Configuration

**API URL** (ApiService.kt):
```kotlin
private const val BASE_URL = "http://10.0.2.2:5000/" // For emulator
// Or use actual server URL for physical device
```

---

## 📚 Research Methodology | منهجية البحث

### 1. Data Collection | جمع البيانات
- UCI Heart Disease Dataset
- 303 patient records
- 14 medical parameters

### 2. Data Preprocessing | معالجة البيانات
- StandardScaler normalization
- 80/20 train-test split
- Stratified sampling

### 3. Model Training | تدريب النماذج
- K-Nearest Neighbors
- Naive Bayes
- Decision Tree (FastTree)

### 4. Model Evaluation | تقييم النماذج
- Accuracy, Precision, Recall, F1-Score
- Confusion Matrix analysis
- ROC Curve comparison

### 5. Deployment | النشر
- Web application (Svelte)
- REST API (C#.NET)
- Mobile application (Android Kotlin)

---

## 🛠️ Technologies Used | التقنيات المستخدمة

### Frontend:
- **Svelte** - Modern reactive framework
- **TypeScript** - Type-safe development
- **Tailwind CSS** - Utility-first CSS
- **Vite** - Fast build tool

### Backend:
- **.NET 8.0** - Cross-platform framework
- **ML.NET** - Machine learning library
- **ASP.NET Core** - Web API
- **Swagger** - API documentation

### Mobile:
- **Kotlin** - Modern Android development
- **Jetpack Compose** - Declarative UI
- **Retrofit** - HTTP client
- **Material Design 3** - UI components

---

## 🚀 Deployment | النشر

### Frontend Deployment Options:
- **Vercel** - Recommended for Svelte apps
- **Netlify** - Easy deployment with CI/CD
- **GitHub Pages** - Static hosting

### Backend Deployment Options:
- **Azure App Service** - Managed .NET hosting
- **Docker** - Containerized deployment
- **IIS** - Windows server hosting

### Android Deployment:
- **Google Play Store** - Public release
- **APK Distribution** - Direct installation
- **Internal Testing** - Firebase App Distribution

---

## 📞 Contact Information | معلومات التواصل

**Student | الطالب:**
Hazem Kheder Al-Haj Ahmid
Email: Hazem_82763@svuonline.org

**Main Supervisor | المشرف الأساسي:**
Dr. George Anwar Karraz
Email: T_gkarraz@svuonline.org

**Co-Supervisor | المشرف المشارك:**
Dr. Majeda Bakour
Email: T_mbakour@svuonline.org

**Syrian Virtual University**
Ministry of Higher Education - Syrian Arab Republic
Website: www.svuonline.org

---

## 📝 License | الترخيص

This project is developed as part of a Master's thesis at the Syrian Virtual University.
All rights reserved © 2025 Syrian Virtual University

**Disclaimer:** This system is for research and educational purposes only. Always consult a qualified medical professional for accurate diagnosis and treatment.

**تنويه:** هذا النظام للأغراض البحثية والتعليمية فقط. يرجى استشارة طبيب مختص للتشخيص والعلاج الدقيق.

---

## 🙏 Acknowledgments | شكر وتقدير

- UCI Machine Learning Repository for the Heart Disease Dataset
- Syrian Virtual University for academic support
- Research supervisors for guidance and expertise

---

**Made with ❤️ for early detection of heart disease**
**صُنع بكل ❤️ للكشف المبكر عن أمراض القلب**

---

## 📈 Future Enhancements | التحسينات المستقبلية

- [ ] Ensemble methods (Random Forest, XGBoost)
- [ ] Real-time ECG analysis integration
- [ ] Patient history tracking database
- [ ] Telemedicine consultation features
- [ ] Multi-language support (French, Spanish)
- [ ] Cloud-based model training pipeline

---

**Version:** 1.0.0
**Last Updated:** December 2025
**Status:** ✅ Production Ready
