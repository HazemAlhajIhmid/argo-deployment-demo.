import { writable } from 'svelte/store';

export interface PatientData {
  age: number;
  sex: number; // 1 = male, 0 = female
  cp: number; // chest pain type (0-3)
  trestbps: number; // resting blood pressure
  chol: number; // serum cholesterol
  fbs: number; // fasting blood sugar > 120 mg/dl (1 = true, 0 = false)
  restecg: number; // resting electrocardiographic results (0-2)
  thalach: number; // maximum heart rate achieved
  exang: number; // exercise induced angina (1 = yes, 0 = no)
  oldpeak: number; // ST depression induced by exercise
  slope: number; // slope of the peak exercise ST segment (0-2)
  ca: number; // number of major vessels (0-3)
  thal: number; // thalassemia (0 = normal, 1 = fixed defect, 2 = reversible defect)
}

export interface PredictionResult {
  knn: {
    prediction: number;
    probability: number;
    accuracy: number;
  };
  naiveBayes: {
    prediction: number;
    probability: number;
    accuracy: number;
  };
  decisionTree: {
    prediction: number;
    probability: number;
    accuracy: number;
  };
  averageProbability: number;
  riskLevel: 'low' | 'moderate' | 'high';
}

export const patientData = writable<PatientData>({
  age: 50,
  sex: 1,
  cp: 0,
  trestbps: 120,
  chol: 200,
  fbs: 0,
  restecg: 0,
  thalach: 150,
  exang: 0,
  oldpeak: 0,
  slope: 0,
  ca: 0,
  thal: 0,
});

export const predictionResult = writable<PredictionResult | null>(null);
export const isCalculating = writable<boolean>(false);

export function resetForm() {
  patientData.set({
    age: 50,
    sex: 1,
    cp: 0,
    trestbps: 120,
    chol: 200,
    fbs: 0,
    restecg: 0,
    thalach: 150,
    exang: 0,
    oldpeak: 0,
    slope: 0,
    ca: 0,
    thal: 0,
  });
  predictionResult.set(null);
}

// Simulated ML prediction function (will be replaced by API call)
export async function calculateRisk(data: PatientData): Promise<PredictionResult> {
  isCalculating.set(true);

  // Simulate API call delay
  await new Promise(resolve => setTimeout(resolve, 1500));

  // Simple heuristic-based prediction for demonstration
  // In production, this will call the C#.NET API
  const riskScore = calculateRiskScore(data);
  const probability = Math.min(Math.max(riskScore / 100, 0), 1);

  const result: PredictionResult = {
    knn: {
      prediction: probability > 0.5 ? 1 : 0,
      probability: probability,
      accuracy: 0.82,
    },
    naiveBayes: {
      prediction: probability > 0.5 ? 1 : 0,
      probability: probability * 0.95,
      accuracy: 0.82,
    },
    decisionTree: {
      prediction: probability > 0.45 ? 1 : 0,
      probability: probability * 0.85,
      accuracy: 0.70,
    },
    averageProbability: probability,
    riskLevel: probability > 0.7 ? 'high' : probability > 0.4 ? 'moderate' : 'low',
  };

  isCalculating.set(false);
  predictionResult.set(result);
  return result;
}

function calculateRiskScore(data: PatientData): number {
  let score = 0;

  // Age factor
  if (data.age > 60) score += 20;
  else if (data.age > 50) score += 15;
  else if (data.age > 40) score += 10;

  // Sex factor (males higher risk)
  if (data.sex === 1) score += 10;

  // Chest pain type
  if (data.cp === 3) score += 20;
  else if (data.cp === 2) score += 15;
  else if (data.cp === 1) score += 10;
  else score += 5;

  // Blood pressure
  if (data.trestbps > 140) score += 15;
  else if (data.trestbps > 130) score += 10;

  // Cholesterol
  if (data.chol > 240) score += 15;
  else if (data.chol > 200) score += 10;

  // Fasting blood sugar
  if (data.fbs === 1) score += 10;

  // Resting ECG
  if (data.restecg === 2) score += 15;
  else if (data.restecg === 1) score += 10;

  // Max heart rate (lower is riskier)
  if (data.thalach < 100) score += 20;
  else if (data.thalach < 120) score += 15;
  else if (data.thalach < 140) score += 10;

  // Exercise induced angina
  if (data.exang === 1) score += 15;

  // ST depression
  if (data.oldpeak > 2) score += 15;
  else if (data.oldpeak > 1) score += 10;

  // Slope
  if (data.slope === 2) score += 15;
  else if (data.slope === 1) score += 10;

  // Number of major vessels
  score += data.ca * 10;

  // Thalassemia
  if (data.thal === 2) score += 15;
  else if (data.thal === 1) score += 10;

  return Math.min(score, 100);
}
