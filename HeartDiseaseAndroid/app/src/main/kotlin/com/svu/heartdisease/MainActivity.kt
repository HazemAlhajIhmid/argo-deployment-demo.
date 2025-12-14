package com.svu.heartdisease

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.svu.heartdisease.models.PatientData
import com.svu.heartdisease.network.HeartDiseaseApiService
import com.svu.heartdisease.network.HeartDiseaseRepository
import com.svu.heartdisease.ui.theme.HeartDiseaseTheme
import com.svu.heartdisease.viewmodel.HeartDiseaseViewModel

/**
 * Main Activity for Heart Disease Prediction App
 * Syrian Virtual University Master's Thesis
 * Student: Hazem Kheder Al-Haj Ahmid
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize API and Repository
        val apiService = HeartDiseaseApiService.createLocal()
        val repository = HeartDiseaseRepository(apiService)

        setContent {
            HeartDiseaseTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HeartDiseaseApp(repository)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeartDiseaseApp(repository: HeartDiseaseRepository) {
    val viewModel: HeartDiseaseViewModel = viewModel(
        factory = HeartDiseaseViewModelFactory(repository)
    )

    val calculatorState by viewModel.calculatorState.collectAsState()
    var currentScreen by remember { mutableStateOf("home") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            "TechCare",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Text(
                            "الكشف المبكر عن أمراض القلب",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Text("🏠") },
                    label = { Text("الرئيسية") },
                    selected = currentScreen == "home",
                    onClick = { currentScreen = "home" }
                )
                NavigationBarItem(
                    icon = { Text("🧮") },
                    label = { Text("الحاسبة") },
                    selected = currentScreen == "calculator",
                    onClick = { currentScreen = "calculator" }
                )
                NavigationBarItem(
                    icon = { Text("ℹ️") },
                    label = { Text("حول") },
                    selected = currentScreen == "about",
                    onClick = { currentScreen = "about" }
                )
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            when (currentScreen) {
                "home" -> HomeScreen()
                "calculator" -> CalculatorScreen(viewModel, calculatorState)
                "about" -> AboutScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "❤️",
            fontSize = 80.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "حاسبة أمراض القلب والشرايين",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "نظام تشخيصي ذكي يعتمد على خوارزميات التعلم الآلي",
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("نموذج KNN", fontWeight = FontWeight.Bold)
                Text("الدقة: 82%", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("نموذج Naive Bayes", fontWeight = FontWeight.Bold)
                Text("الدقة: 82%", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("نموذج Decision Tree", fontWeight = FontWeight.Bold)
                Text("الدقة: 70%", fontSize = 14.sp, color = Color.Gray)
            }
        }
    }
}

@Composable
fun CalculatorScreen(viewModel: HeartDiseaseViewModel, state: CalculatorState) {
    // Form state
    var age by remember { mutableStateOf("50") }
    var sex by remember { mutableStateOf(1f) }
    var cp by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "أدخل البيانات الطبية",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("العمر") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        // Add more input fields here...

        Button(
            onClick = {
                val patientData = PatientData(
                    age = age.toFloatOrNull() ?: 50f,
                    sex = sex,
                    chestPainType = cp,
                    restingBloodPressure = 120f,
                    serumCholesterol = 200f,
                    fastingBloodSugar = 0f,
                    restingECG = 0f,
                    maxHeartRate = 150f,
                    exerciseInducedAngina = 0f,
                    stDepression = 0f,
                    slopeOfPeakExercise = 0f,
                    numberOfMajorVessels = 0f,
                    thalassemia = 0f
                )
                viewModel.predict(patientData)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            enabled = !state.isLoading
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = Color.White
                )
            } else {
                Text("احسب المخاطر", fontSize = 16.sp)
            }
        }

        // Display results
        state.result?.let { result ->
            ResultsCard(result)
        }

        state.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            "حول المشروع",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        InfoCard(
            title = "الجامعة",
            content = "الجامعة الافتراضية السورية\nوزارة التعليم العالي"
        )

        InfoCard(
            title = "الطالب",
            content = "حازم خضر الحاج احميد\nHazem_82763@svuonline.org"
        )

        InfoCard(
            title = "المشرف الأساسي",
            content = "د.م. جورج أنور كراز\nT_gkarraz@svuonline.org"
        )

        InfoCard(
            title = "المشرف المشارك",
            content = "د. ماجدة البكور\nT_mbakour@svuonline.org"
        )

        InfoCard(
            title = "عنوان الرسالة",
            content = "تطوير خوارزميات التنقيب عن البيانات في تحسين عملية تشخيص أمراض القلب"
        )
    }
}

@Composable
fun InfoCard(title: String, content: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text(content, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun ResultsCard(result: com.svu.heartdisease.models.PredictionResponse) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (result.riskLevel) {
                "high" -> Color(0xFFFFEBEE)
                "moderate" -> Color(0xFFFFF3E0)
                else -> Color(0xFFE8F5E9)
            }
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                "نتائج التقييم",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("مستوى المخاطر: ${result.riskLevel}")
            Text("الاحتمالية: ${(result.averageProbability * 100).toInt()}%")

            Spacer(modifier = Modifier.height(16.dp))

            ModelResultRow("KNN", result.knn)
            ModelResultRow("Naive Bayes", result.naiveBayes)
            ModelResultRow("Decision Tree", result.decisionTree)
        }
    }
}

@Composable
fun ModelResultRow(modelName: String, prediction: com.svu.heartdisease.models.ModelPrediction) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(modelName, fontWeight = FontWeight.SemiBold)
        Text("${(prediction.probability * 100).toInt()}%")
    }
}
