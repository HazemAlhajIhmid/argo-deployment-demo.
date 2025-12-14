# Quick Start Guide - TechCare Heart Disease Detection System

## دليل البدء السريع - نظام TechCare للكشف عن أمراض القلب

---

## 🚀 5-Minute Quick Start

Follow these steps to get the entire system running quickly.

---

## ✅ Prerequisites

Make sure you have installed:
- [x] Node.js 18+ (for Svelte frontend)
- [x] .NET 8.0 SDK (for C# backend)
- [x] Android Studio (for Android app)

---

## 🌐 Step 1: Start the Svelte Frontend (2 minutes)

```bash
# Navigate to frontend directory
cd heart-disease-frontend

# Install dependencies (first time only)
npm install

# Start development server
npm run dev
```

✅ **Frontend running at:** http://localhost:5173

**What you'll see:**
- Arabic/English bilingual interface
- TechCare hero section
- Heart disease risk calculator
- Syrian Virtual University research details

---

## 🔧 Step 2: Start the C#.NET Backend (3 minutes)

```bash
# Navigate to backend directory
cd ../HeartDiseaseAPI

# Restore packages (first time only)
dotnet restore

# Run the API
dotnet run
```

✅ **API running at:** https://localhost:5001

**What happens:**
- ML models automatically train on startup
- Three models ready: KNN, Naive Bayes, Decision Tree
- API endpoints active

**Test the API:**
```bash
# Health check
curl https://localhost:5001/api/prediction/health

# View Swagger docs
# Open: https://localhost:5001/swagger
```

---

## 📱 Step 3: Run the Android App (Optional)

```bash
# Open Android Studio
# File -> Open -> Select HeartDiseaseAndroid folder

# Configure API URL in ApiService.kt:
# - Emulator: http://10.0.2.2:5000/
# - Physical device: http://YOUR_IP:5000/

# Click Run button or Shift+F10
```

✅ **App installed on device/emulator**

---

## 🧪 Step 4: Test the Complete System

### Test Case: 55-Year-Old Male with High Risk Factors

**Frontend Test:**
1. Open http://localhost:5173
2. Scroll to "حاسبة المخاطر" (Risk Calculator)
3. Enter these values:
   - Age: 55
   - Sex: Male (ذكر)
   - Chest Pain Type: Asymptomatic (بدون أعراض)
   - Blood Pressure: 145
   - Cholesterol: 250
   - Fasting Blood Sugar: Yes (نعم)
   - All other fields: Use default values
4. Click "احسب المخاطر" (Calculate Risk)
5. **Expected Result:** High Risk (خطر مرتفع) with 70-85% probability

**API Test (cURL):**
```bash
curl -X POST https://localhost:5001/api/prediction/predict \
  -H "Content-Type: application/json" \
  -k \
  -d '{
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
  }'
```

**Android Test:**
1. Open app on device/emulator
2. Navigate to Calculator tab (الحاسبة)
3. Enter same values as above
4. Tap "احسب المخاطر"
5. View results from all 3 models

---

## 🎯 System Architecture

```
┌─────────────────────┐
│  Svelte Frontend    │  Port: 5173
│  (Arabic/English)   │
└──────────┬──────────┘
           │
           │ HTTP/HTTPS
           │
┌──────────▼──────────┐
│  C#.NET API         │  Port: 5001
│  ML.NET Models      │
│  - KNN (82%)        │
│  - Naive Bayes (82%)│
│  - Decision Tree    │
└─────────────────────┘
           ▲
           │
           │ REST API
           │
┌──────────┴──────────┐
│  Android App        │
│  (Kotlin/Compose)   │
└─────────────────────┘
```

---

## 📊 Expected Model Performance

| Model | Accuracy | Precision | Recall | F1-Score |
|-------|----------|-----------|--------|----------|
| KNN | 82% | 0.78 | 0.94 | 0.85 |
| Naive Bayes | 82% | 0.79 | 0.91 | 0.85 |
| Decision Tree | 70% | 0.70 | 0.79 | 0.74 |

---

## 🔍 Verification Checklist

**Frontend:**
- [ ] Page loads without errors
- [ ] Can switch between Arabic/English
- [ ] Calculator accepts input
- [ ] Results display after calculation
- [ ] Model comparison table shows

**Backend:**
- [ ] API starts successfully
- [ ] Models train on startup (check console logs)
- [ ] Swagger UI accessible
- [ ] Health endpoint returns 200
- [ ] Prediction endpoint returns valid JSON

**Android:**
- [ ] App installs and launches
- [ ] Navigation works (Home, Calculator, About)
- [ ] Can enter medical data
- [ ] API connection works
- [ ] Results display correctly

---

## 🛠️ Troubleshooting

### Frontend Issues

**Problem:** "npm install" fails
```bash
# Solution: Clear cache and retry
rm -rf node_modules package-lock.json
npm cache clean --force
npm install
```

**Problem:** Port 5173 already in use
```bash
# Solution: Use different port
npm run dev -- --port 3000
```

### Backend Issues

**Problem:** .NET SDK not found
```bash
# Solution: Install .NET 8.0 SDK
# Download from: https://dotnet.microsoft.com/download/dotnet/8.0
```

**Problem:** Models fail to train
```bash
# Solution: Check dataset exists
ls -la Data/heart.csv

# If missing, copy from frontend:
cp ../heart-disease-frontend/src/lib/heart.csv Data/
```

**Problem:** CORS errors
```bash
# Solution: Verify frontend origin in Program.cs
# Should include: http://localhost:5173
```

### Android Issues

**Problem:** Cannot connect to API
```bash
# Solution for Emulator:
# Use: http://10.0.2.2:5000/

# Solution for Physical Device:
# 1. Find your computer IP: ipconfig (Windows) or ifconfig (Mac/Linux)
# 2. Use: http://YOUR_IP:5000/
# 3. Ensure device and computer on same network
```

---

## 📚 Next Steps

After getting everything running:

1. **Explore the Code:**
   - Frontend: Check `src/components/` for UI components
   - Backend: Review ML model implementation in `Services/`
   - Android: Explore Jetpack Compose UI in `MainActivity.kt`

2. **Customize:**
   - Modify UI colors in `tailwind.config.js`
   - Adjust model parameters in `HeartDiseasePredictionService.cs`
   - Add more features to Android app

3. **Deploy:**
   - Frontend: Deploy to Vercel or Netlify
   - Backend: Deploy to Azure App Service
   - Android: Build release APK for distribution

4. **Extend:**
   - Add more ML models (Random Forest, XGBoost)
   - Implement patient history database
   - Add real-time ECG analysis
   - Create admin dashboard

---

## 💡 Tips for Development

**Frontend (Svelte):**
```bash
# Auto-format code
npm run format

# Type checking
npm run check

# Build for production
npm run build
```

**Backend (C#.NET):**
```bash
# Watch mode (auto-restart on changes)
dotnet watch run

# Run tests
dotnet test

# Generate production build
dotnet publish -c Release
```

**Android (Kotlin):**
```bash
# Clean build
./gradlew clean build

# Run tests
./gradlew test

# Build release APK
./gradlew assembleRelease
```

---

## 📞 Getting Help

**Documentation:**
- Main README: `/README.md`
- Backend README: `/HeartDiseaseAPI/README.md`
- Android README: `/HeartDiseaseAndroid/README.md`

**Contact:**
- Student: Hazem_82763@svuonline.org
- Supervisor: T_gkarraz@svuonline.org
- University: www.svuonline.org

---

## 🎓 About This Project

**Institution:** Syrian Virtual University (الجامعة الافتراضية السورية)
**Degree:** Master of Web Science
**Student:** Hazem Kheder Al-Haj Ahmid
**Supervisors:** Dr. George Anwar Karraz, Dr. Majeda Bakour

**Thesis:** Develop Data Mining Algorithms to Improve the Diagnosis of Heart Disease

**Keywords:** Data Mining, KNN, Naive Bayes, Decision Tree, Heart Disease, Machine Learning

---

## ⚠️ Important Notice

This system is designed for **research and educational purposes only**.

**Disclaimer:**
- Not a substitute for professional medical diagnosis
- Always consult qualified healthcare providers
- Results are probabilistic, not definitive
- Based on limited dataset (303 samples)

---

## ✨ Success!

If you've followed all steps, you now have:
✅ A working web application with Arabic interface
✅ A trained ML model API with 3 algorithms
✅ A native Android app (if built)
✅ Complete source code for all components

**Congratulations! 🎉**

You're ready to explore, modify, and extend the TechCare Heart Disease Detection System.

---

**Built with ❤️ for early detection of heart disease**
**صُنع بكل ❤️ للكشف المبكر عن أمراض القلب**

**Version:** 1.0.0
**Last Updated:** December 2025
