# TechCare Android App - Heart Disease Detection

## نظام الكشف المبكر عن أمراض القلب - تطبيق أندرويد

Android application for the TechCare Heart Disease Early Detection System, built with Kotlin and Jetpack Compose.

## Features

- ✅ Modern Material Design 3 UI
- ✅ Arabic language support with RTL layout
- ✅ Heart disease risk calculator (14 medical parameters)
- ✅ Real-time predictions from 3 ML models
- ✅ Model performance metrics display
- ✅ Syrian Virtual University branding
- ✅ Offline-capable design

## Requirements

- Android Studio Hedgehog (2023.1.1) or newer
- JDK 17
- Android SDK (API 24+, targeting API 34)
- Android device or emulator
- Internet connection for API calls

## Installation & Setup

### 1. Open Project in Android Studio

```bash
# Clone or copy the HeartDiseaseAndroid folder
# Open Android Studio -> File -> Open
# Select the HeartDiseaseAndroid folder
```

### 2. Sync Gradle

```
Build -> Sync Project with Gradle Files
```

Wait for Gradle to download all dependencies.

### 3. Configure API URL

Edit `app/src/main/kotlin/com/svu/heartdisease/network/ApiService.kt`:

```kotlin
companion object {
    // For production
    private const val BASE_URL = "https://your-api-url.com/"

    // For local development with emulator
    private const val BASE_URL = "http://10.0.2.2:5000/"

    // For local development with physical device
    private const val BASE_URL = "http://YOUR_COMPUTER_IP:5000/"
}
```

**Note:** Replace `YOUR_COMPUTER_IP` with your actual local IP address (e.g., 192.168.1.100)

### 4. Build and Run

```
Run -> Run 'app'
```

Or click the green play button in the toolbar.

## Project Structure

```
HeartDiseaseAndroid/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/com/svu/heartdisease/
│   │   │   │   ├── MainActivity.kt           # Main UI with Compose
│   │   │   │   ├── models/
│   │   │   │   │   └── Models.kt             # Data models
│   │   │   │   ├── network/
│   │   │   │   │   └── ApiService.kt         # Retrofit API client
│   │   │   │   └── viewmodel/
│   │   │   │       └── HeartDiseaseViewModel.kt  # ViewModel
│   │   │   ├── res/
│   │   │   │   ├── layout/                   # XML layouts (if any)
│   │   │   │   ├── values/
│   │   │   │   │   ├── strings.xml           # Arabic strings
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   └── themes.xml
│   │   │   └── AndroidManifest.xml
│   │   └── test/                             # Unit tests
│   └── build.gradle.kts                      # App-level Gradle
├── build.gradle.kts                          # Project-level Gradle
└── settings.gradle.kts
```

## App Screens

### 1. Home Screen
- Welcome message
- Model accuracy cards (KNN, Naive Bayes, Decision Tree)
- Quick stats display
- University branding

### 2. Calculator Screen
- 14 medical parameter input fields
- Arabic labels and hints
- Real-time validation
- Calculate button with loading state
- Results display

### 3. About Screen
- University information
- Research team details
- Thesis title
- Contact information

## Key Components

### MainActivity.kt

Main entry point with Jetpack Compose UI:

```kotlin
@Composable
fun HeartDiseaseApp(repository: HeartDiseaseRepository) {
    // Navigation between screens
    // State management with ViewModel
    // Material Design 3 components
}
```

### Models.kt

Data classes for API communication:

```kotlin
data class PatientData(...)        // Request model
data class PredictionResponse(...) // Response model
data class ModelMetrics(...)       // Metrics model
```

### ApiService.kt

Retrofit interface for API calls:

```kotlin
interface HeartDiseaseApiService {
    @POST("api/prediction/predict")
    suspend fun predictHeartDisease(@Body patientData: PatientData): Response<PredictionResponse>

    @GET("api/prediction/metrics")
    suspend fun getModelMetrics(): Response<List<ModelMetrics>>

    @GET("api/prediction/health")
    suspend fun healthCheck(): Response<Map<String, Any>>
}
```

### HeartDiseaseViewModel.kt

State management and business logic:

```kotlin
class HeartDiseaseViewModel(private val repository: HeartDiseaseRepository) : ViewModel() {
    fun predict(patientData: PatientData) { ... }
    fun loadModelMetrics() { ... }
    fun resetCalculator() { ... }
}
```

## Dependencies

### Core Dependencies

```kotlin
// Jetpack Compose
implementation("androidx.compose.ui:ui:1.5.4")
implementation("androidx.compose.material3:material3:1.1.2")
implementation("androidx.activity:activity-compose:1.8.2")

// Networking
implementation("com.squareup.retrofit2:retrofit:2.9.0")
implementation("com.squareup.retrofit2:converter-gson:2.9.0")

// Coroutines
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

// Material Design
implementation("com.google.android.material:material:1.11.0")
```

## Building APK

### Debug Build

```bash
# From command line
./gradlew assembleDebug

# APK location: app/build/outputs/apk/debug/app-debug.apk
```

### Release Build

```bash
# Create keystore (first time only)
keytool -genkey -v -keystore heart-disease.keystore -alias techcare -keyalg RSA -keysize 2048 -validity 10000

# Build release APK
./gradlew assembleRelease

# APK location: app/build/outputs/apk/release/app-release.apk
```

## Testing

### Running Unit Tests

```bash
./gradlew test
```

### Running Instrumentation Tests

```bash
./gradlew connectedAndroidTest
```

## Arabic Language Support

All user-facing text is in Arabic:

```xml
<!-- strings.xml -->
<string name="app_name">TechCare - أمراض القلب</string>
<string name="age">العمر</string>
<string name="sex">الجنس</string>
<!-- ... more strings -->
```

The app automatically uses RTL layout for Arabic content.

## Network Configuration

### AndroidManifest.xml Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />

<!-- For local development -->
<application
    ...
    android:usesCleartextTraffic="true">
```

**Security Note:** Set `usesCleartextTraffic="false"` in production and use HTTPS only.

## Testing the App

### 1. With Android Emulator

1. Start the C#.NET API on your computer
2. Use API URL: `http://10.0.2.2:5000/`
3. Run the app in the emulator
4. Enter test data and make predictions

### 2. With Physical Device

1. Connect device via USB or use wireless debugging
2. Ensure device and computer are on the same network
3. Find your computer's local IP: `ipconfig` (Windows) or `ifconfig` (Mac/Linux)
4. Update API URL to: `http://YOUR_IP:5000/`
5. Run the app on device

### Sample Test Data

```
Age: 55
Sex: Male (1)
Chest Pain: Atypical Angina (1)
Blood Pressure: 145 mmHg
Cholesterol: 250 mg/dl
Fasting Blood Sugar: Yes (1)
Resting ECG: Normal (0)
Max Heart Rate: 140
Exercise Angina: Yes (1)
ST Depression: 1.5
Slope: Downsloping (2)
Vessels: 2
Thalassemia: Reversible Defect (2)
```

Expected Result: High Risk

## Troubleshooting

### Network Errors

**Problem:** "Unable to connect to API"

**Solutions:**
- Verify C#.NET API is running
- Check API URL in ApiService.kt
- Ensure device/emulator has internet access
- Disable VPN if active
- Check firewall settings

### Build Errors

**Problem:** "Failed to resolve dependencies"

**Solutions:**
```bash
# Clear Gradle cache
./gradlew clean
./gradlew build --refresh-dependencies

# Invalidate caches in Android Studio
File -> Invalidate Caches -> Invalidate and Restart
```

### Emulator Issues

**Problem:** Emulator too slow

**Solutions:**
- Use x86 system image
- Enable hardware acceleration (Intel HAXM or AMD-V)
- Increase RAM allocation for emulator
- Use physical device instead

## Deployment

### Google Play Store

1. Create release build with signing key
2. Generate signed APK or App Bundle (.aab)
3. Create Google Play Console account
4. Upload APK/Bundle
5. Complete store listing
6. Submit for review

### Internal Distribution

1. Build release APK
2. Share via:
   - Email
   - Cloud storage (Drive, Dropbox)
   - Firebase App Distribution
   - Direct download link

## Future Enhancements

- [ ] Offline mode with local ML models
- [ ] Patient history database (Room)
- [ ] Export results as PDF
- [ ] Push notifications for reminders
- [ ] Dark mode support
- [ ] English language support
- [ ] Biometric authentication

## Support & Contact

**Student:** Hazem Kheder Al-Haj Ahmid
Email: Hazem_82763@svuonline.org

**Supervisor:** Dr. George Anwar Karraz
Email: T_gkarraz@svuonline.org

**University:** Syrian Virtual University
Website: www.svuonline.org

## License

© 2025 Syrian Virtual University
Master's Thesis Project - Educational and Research Use

**Disclaimer:** This app is for research purposes only. Consult a medical professional for actual diagnosis.

---

**Made with Kotlin and ❤️ in Syria**
