# Complete Setup Guide
## TechCare - Heart Disease Detection System

This guide will help you set up all three components of the system: Svelte Frontend, C#.NET Backend, and Android App.

---

## Table of Contents
1. [System Requirements](#system-requirements)
2. [Frontend Setup](#frontend-setup)
3. [Backend Setup](#backend-setup)
4. [Android App Setup](#android-app-setup)
5. [Troubleshooting](#troubleshooting)

---

## System Requirements

### For Frontend Development
- **Node.js:** 22.x or higher
- **npm:** 10.x or higher
- **Modern Web Browser:** Chrome, Firefox, or Edge
- **Memory:** 4GB RAM minimum
- **Storage:** 500MB free space

### For Backend Development
- **.NET SDK:** 8.0 or higher
- **SQL Server:** LocalDB, SQL Server Express, or SQL Server
- **Memory:** 8GB RAM minimum
- **Storage:** 2GB free space
- **IDE:** Visual Studio 2022 or VS Code with C# extension

### For Android Development
- **Android Studio:** Latest version (Hedgehog or newer)
- **JDK:** 17 or higher
- **Android SDK:** API Level 24+ (Android 7.0+)
- **Memory:** 8GB RAM minimum (16GB recommended)
- **Storage:** 10GB free space
- **Emulator or Physical Device:** Android 7.0 or higher

---

## Frontend Setup

### Step 1: Navigate to Frontend Directory
```bash
cd heart-disease-detection/frontend
```

### Step 2: Install Dependencies
```bash
npm install
```

This will install:
- SvelteKit
- Tailwind CSS v4
- Chart.js
- All other required dependencies

### Step 3: Verify Dataset
Ensure `static/data/heart.csv` exists. The file should contain 303 records.

```bash
ls static/data/heart.csv
# Should show: static/data/heart.csv
```

### Step 4: Start Development Server
```bash
npm run dev -- --port 3000 --host 0.0.0.0
```

Expected output:
```
VITE v7.2.7  ready in XXX ms

➜  Local:   http://localhost:3000/
➜  Network: http://192.168.x.x:3000/
```

### Step 5: Access the Application
Open your browser and navigate to:
```
http://localhost:3000
```

You should see the TechCare homepage in Arabic with RTL layout.

### Step 6: Test Language Toggle
Click the "English" button in the header to switch languages.

### Step 7: Test Risk Calculator
1. Click "ابدأ التقييم الآن" (Start Assessment Now)
2. Fill in the medical parameters
3. Click "احسب المخاطر" (Calculate Risk)
4. Verify the prediction results appear

### Frontend Configuration

**API URL Configuration:**
To connect to the C#.NET backend, update the API URL in your components:

Location: `src/lib/components/RiskCalculator.svelte`

```typescript
const API_URL = 'https://localhost:5001/api';
```

**Build for Production:**
```bash
npm run build
```

Output will be in the `build/` directory.

**Preview Production Build:**
```bash
npm run preview
```

---

## Backend Setup

### Step 1: Install .NET SDK
Download and install .NET 8 SDK from:
https://dotnet.microsoft.com/download/dotnet/8.0

Verify installation:
```bash
dotnet --version
# Should output: 8.x.x
```

### Step 2: Navigate to Backend Directory
```bash
cd heart-disease-detection/backend/HeartDiseaseAPI
```

### Step 3: Restore NuGet Packages
```bash
dotnet restore
```

This installs:
- Microsoft.EntityFrameworkCore
- Microsoft.ML
- Swashbuckle (Swagger)
- All other dependencies

### Step 4: Setup Database

**Option A: SQL Server LocalDB (Recommended for Development)**
LocalDB is installed with Visual Studio. The connection string in `appsettings.json` is already configured:

```json
{
  "ConnectionStrings": {
    "HeartDiseaseDB": "Server=(localdb)\\mssqllocaldb;Database=HeartDiseaseDB;Trusted_Connection=true"
  }
}
```

**Option B: SQL Server Express**
Update `appsettings.json`:
```json
{
  "ConnectionStrings": {
    "HeartDiseaseDB": "Server=.\\SQLEXPRESS;Database=HeartDiseaseDB;Trusted_Connection=true"
  }
}
```

**Option C: Full SQL Server**
```json
{
  "ConnectionStrings": {
    "HeartDiseaseDB": "Server=YOUR_SERVER;Database=HeartDiseaseDB;User Id=YOUR_USER;Password=YOUR_PASSWORD"
  }
}
```

### Step 5: Copy Dataset
Create the Data directory and copy the heart disease dataset:

```bash
mkdir -p Data
cp ../../frontend/static/data/heart.csv Data/
```

Verify:
```bash
ls Data/heart.csv
# Should show: Data/heart.csv
```

### Step 6: Create Database
Install EF Core tools if not already installed:
```bash
dotnet tool install --global dotnet-ef
```

Create initial migration:
```bash
dotnet ef migrations add InitialCreate
```

Apply migration to database:
```bash
dotnet ef database update
```

### Step 7: Run the API
```bash
dotnet run
```

Expected output:
```
info: Microsoft.Hosting.Lifetime[14]
      Now listening on: https://localhost:5001
info: Microsoft.Hosting.Lifetime[14]
      Now listening on: http://localhost:5000
```

### Step 8: Access Swagger UI
Open your browser and navigate to:
```
https://localhost:5001/swagger
```

You should see the Swagger UI with three endpoints:
- POST `/api/prediction/predict`
- GET `/api/prediction/metrics`
- GET `/api/prediction/health`

### Step 9: Test the API

**Test Health Check:**
```bash
curl https://localhost:5001/api/prediction/health -k
```

Expected response:
```json
{
  "status": "healthy",
  "timestamp": "2025-12-14T...",
  "version": "1.0.0",
  "models": {
    "knn": "loaded",
    "naiveBayes": "loaded",
    "decisionTree": "loaded"
  }
}
```

**Test Prediction:**
```bash
curl -X POST https://localhost:5001/api/prediction/predict -k \
  -H "Content-Type: application/json" \
  -d '{
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
  }'
```

### Step 10: Train ML Models (First Run Only)
On first run, the models will be automatically trained and saved to the `Models/` directory. This may take a few minutes.

Watch for console output:
```
Training KNN model...
KNN Model Accuracy: 82.00%
Training Naive Bayes model...
Naive Bayes Model Accuracy: 82.00%
Training Decision Tree model...
Decision Tree Model Accuracy: 70.00%
```

### Backend Configuration

**CORS Settings:**
The API is configured to allow requests from:
- http://localhost:3000 (Svelte frontend)
- http://localhost:5173 (Vite default)

To add more origins, edit `Program.cs`:
```csharp
policy.WithOrigins(
    "http://localhost:3000",
    "http://localhost:5173",
    "http://your-domain.com"
)
```

**Build for Production:**
```bash
dotnet publish -c Release -o ./publish
```

---

## Android App Setup

### Step 1: Install Android Studio
Download from: https://developer.android.com/studio

### Step 2: Open Project
1. Launch Android Studio
2. Click "Open an Existing Project"
3. Navigate to `heart-disease-detection/android/HeartDiseaseApp`
4. Click "OK"

### Step 3: Gradle Sync
Android Studio will automatically sync Gradle files.

If prompted, accept:
- SDK licenses
- Gradle plugin updates
- Kotlin plugin updates

### Step 4: Configure API URL

**For Emulator:**
Edit `app/src/main/java/com/techcare/heartdisease/network/ApiService.kt`:

```kotlin
private const val BASE_URL = "http://10.0.2.2:5001/api/"
```

Note: `10.0.2.2` is the special IP that refers to the host machine from Android emulator.

**For Physical Device:**
Replace with your computer's IP address:
```kotlin
private const val BASE_URL = "http://192.168.1.100:5001/api/"
```

Find your IP:
- Windows: `ipconfig`
- Mac/Linux: `ifconfig`

### Step 5: Update strings.xml
File: `app/src/main/res/values/strings.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="app_name">TechCare</string>
    <string name="risk_calculator">حاسبة المخاطر</string>
    <string name="prediction_results">نتائج التنبؤ</string>
    <string name="about_project">حول المشروع</string>
    <!-- Add more strings as needed -->
</resources>
```

### Step 6: Create Emulator
1. Tools → Device Manager
2. Create Device
3. Select Phone (e.g., Pixel 6)
4. Download System Image (API 34 recommended)
5. Click "Finish"

### Step 7: Run the App
1. Select emulator or connected device
2. Click Run (green play button)
3. Wait for build to complete
4. App should launch on device/emulator

### Step 8: Test the App

**Home Screen:**
- Verify TechCare logo and branding
- Check statistics display (82%, 82%, 70%)
- Test navigation cards

**Risk Calculator:**
- Fill in all medical parameters
- Click "Calculate Risk" button
- Verify prediction results appear

**Results Screen:**
- Check all three model predictions
- Verify confidence scores
- Check ensemble result and risk level

**About Screen:**
- Verify university information
- Check supervisor and student details
- Review research methodology

### Android Troubleshooting

**Build Errors:**
1. Clean project: Build → Clean Project
2. Rebuild: Build → Rebuild Project
3. Invalidate caches: File → Invalidate Caches / Restart

**Network Errors:**
1. Ensure backend is running
2. Check firewall settings
3. Verify IP address is correct
4. Add network security config if using HTTP

**Permission Issues:**
Ensure AndroidManifest.xml has:
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

---

## Troubleshooting

### Frontend Issues

**Problem:** Port 3000 already in use
**Solution:**
```bash
npm run dev -- --port 3001
```

**Problem:** Tailwind classes not working
**Solution:**
```bash
rm -rf node_modules .svelte-kit
npm install
npm run dev
```

**Problem:** Module not found errors
**Solution:**
```bash
npm install
```

### Backend Issues

**Problem:** Database connection failed
**Solution:**
1. Verify SQL Server is running
2. Check connection string in appsettings.json
3. Run: `dotnet ef database update`

**Problem:** Port 5001 already in use
**Solution:**
Edit `Properties/launchSettings.json` and change port:
```json
"applicationUrl": "https://localhost:5002;http://localhost:5003"
```

**Problem:** ML models not training
**Solution:**
1. Ensure Data/heart.csv exists
2. Check console for error messages
3. Delete Models folder and restart

**Problem:** CORS errors from frontend
**Solution:**
Update CORS policy in Program.cs to include your frontend URL.

### Android Issues

**Problem:** Cannot connect to localhost
**Solution:**
Use `10.0.2.2` for emulator, or your machine's IP for physical device.

**Problem:** SSL/TLS errors
**Solution:**
For development, you can use HTTP instead of HTTPS, or configure network security config:

Create `res/xml/network_security_config.xml`:
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="true">
        <trust-anchors>
            <certificates src="system" />
        </trust-anchors>
    </base-config>
</network-security-config>
```

Add to AndroidManifest.xml:
```xml
<application
    android:networkSecurityConfig="@xml/network_security_config"
    ...>
```

**Problem:** Gradle sync failed
**Solution:**
1. File → Sync Project with Gradle Files
2. Clear Gradle cache: `./gradlew clean`
3. Update Gradle wrapper

---

## Testing the Complete System

### End-to-End Test

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

3. **Test Web Interface:**
   - Open http://localhost:3000
   - Use risk calculator
   - Verify results from all 3 models

4. **Test API Directly:**
```bash
curl -X POST https://localhost:5001/api/prediction/predict -k \
  -H "Content-Type: application/json" \
  -d '{"age": 50, "sex": 1, ...}'
```

5. **Launch Android App:**
   - Run from Android Studio
   - Complete risk assessment
   - Verify results match web interface

### Validation Checklist

- [ ] Frontend loads in Arabic RTL
- [ ] Language toggle works (Arabic ↔ English)
- [ ] Risk calculator accepts all 14 parameters
- [ ] Backend API returns predictions
- [ ] All 3 models return results
- [ ] Ensemble prediction calculates correctly
- [ ] Android app connects to API
- [ ] Mobile predictions match web predictions
- [ ] Charts and visualizations display
- [ ] About page shows research information

---

## Production Deployment

### Frontend Deployment
1. Build: `npm run build`
2. Deploy `build/` folder to:
   - Vercel
   - Netlify
   - Custom server (Node.js)

### Backend Deployment
1. Publish: `dotnet publish -c Release`
2. Deploy to:
   - Azure App Service
   - IIS
   - Docker container

### Android Deployment
1. Generate signed APK/Bundle
2. Publish to:
   - Google Play Store
   - Internal distribution
   - APK direct download

---

## Support

For questions or issues:
- **Email:** Hazem_82763@svuonline.org
- **Supervisors:** T_gkarraz@svuonline.org, T_mbakour@svuonline.org
- **Institution:** Syrian Virtual University

---

**Setup Complete! 🎉**

You now have a fully functional heart disease detection system running on web, API, and mobile platforms.
