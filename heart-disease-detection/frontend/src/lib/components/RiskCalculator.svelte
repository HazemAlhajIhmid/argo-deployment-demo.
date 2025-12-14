<script lang="ts">
  import { language, translations } from '$lib/stores/language';

  let currentLang: 'ar' | 'en' = 'ar';
  language.subscribe(value => {
    currentLang = value;
  });
  $: t = translations[currentLang];

  // Form Data
  let formData = {
    age: 50,
    sex: 1, // 1 = male, 0 = female
    cp: 0, // chest pain type
    trestbps: 120,
    chol: 200,
    fbs: 0, // fasting blood sugar > 120 mg/dl
    restecg: 0,
    thalach: 150,
    exang: 0, // exercise induced angina
    oldpeak: 0.0,
    slope: 0,
    ca: 0, // number of major vessels
    thal: 0
  };

  let isCalculating = false;
  let predictionResult: any = null;

  async function calculateRisk() {
    isCalculating = true;
    predictionResult = null;

    // Simulate API call - In production, this would call the C# .NET backend
    await new Promise(resolve => setTimeout(resolve, 1500));

    // Mock prediction results (in production, these come from ML models)
    const riskScore = Math.random() * 100;

    predictionResult = {
      knn: {
        prediction: riskScore > 40 ? 1 : 0,
        confidence: riskScore > 40 ? riskScore : 100 - riskScore,
        accuracy: 82
      },
      naiveBayes: {
        prediction: riskScore > 45 ? 1 : 0,
        confidence: riskScore > 45 ? riskScore : 100 - riskScore,
        accuracy: 82
      },
      decisionTree: {
        prediction: riskScore > 50 ? 1 : 0,
        confidence: riskScore > 50 ? riskScore : 100 - riskScore,
        accuracy: 70
      },
      ensemble: {
        prediction: riskScore > 45 ? 1 : 0,
        riskLevel: riskScore > 70 ? 'high' : riskScore > 40 ? 'moderate' : 'low',
        riskScore: riskScore.toFixed(1)
      }
    };

    isCalculating = false;
  }

  function resetForm() {
    formData = {
      age: 50,
      sex: 1,
      cp: 0,
      trestbps: 120,
      chol: 200,
      fbs: 0,
      restecg: 0,
      thalach: 150,
      exang: 0,
      oldpeak: 0.0,
      slope: 0,
      ca: 0,
      thal: 0
    };
    predictionResult = null;
  }

  function getRiskLevelColor(level: string) {
    switch(level) {
      case 'low': return 'text-green-600 bg-green-100';
      case 'moderate': return 'text-orange-600 bg-orange-100';
      case 'high': return 'text-red-600 bg-red-100';
      default: return 'text-gray-600 bg-gray-100';
    }
  }
</script>

<section id="calculator" class="py-20 bg-gray-50">
  <div class="container mx-auto px-4">
    <div class="text-center mb-12">
      <h2 class="text-4xl font-bold text-gray-900 mb-4">{t.calculatorTitle}</h2>
      <p class="text-xl text-gray-600">{t.calculatorSubtitle}</p>
    </div>

    <div class="grid lg:grid-cols-3 gap-8">
      <!-- Form Section -->
      <div class="lg:col-span-2">
        <div class="card">
          <form on:submit|preventDefault={calculateRisk}>
            <div class="grid md:grid-cols-2 gap-6">
              <!-- Age -->
              <div>
                <label class="label">{t.age}</label>
                <input
                  type="number"
                  bind:value={formData.age}
                  min="20"
                  max="100"
                  class="input-field"
                  required
                />
              </div>

              <!-- Sex -->
              <div>
                <label class="label">{t.sex}</label>
                <select bind:value={formData.sex} class="input-field">
                  <option value={1}>{t.male}</option>
                  <option value={0}>{t.female}</option>
                </select>
              </div>

              <!-- Chest Pain Type -->
              <div>
                <label class="label">{t.cp}</label>
                <select bind:value={formData.cp} class="input-field">
                  <option value={0}>{t.cpType1}</option>
                  <option value={1}>{t.cpType2}</option>
                  <option value={2}>{t.cpType3}</option>
                  <option value={3}>{t.cpType4}</option>
                </select>
              </div>

              <!-- Resting Blood Pressure -->
              <div>
                <label class="label">{t.trestbps}</label>
                <input
                  type="number"
                  bind:value={formData.trestbps}
                  min="80"
                  max="200"
                  class="input-field"
                  required
                />
              </div>

              <!-- Cholesterol -->
              <div>
                <label class="label">{t.chol}</label>
                <input
                  type="number"
                  bind:value={formData.chol}
                  min="100"
                  max="600"
                  class="input-field"
                  required
                />
              </div>

              <!-- Fasting Blood Sugar -->
              <div>
                <label class="label">{t.fbs}</label>
                <select bind:value={formData.fbs} class="input-field">
                  <option value={0}>{t.no}</option>
                  <option value={1}>{t.yes}</option>
                </select>
              </div>

              <!-- Resting ECG -->
              <div>
                <label class="label">{t.restecg}</label>
                <select bind:value={formData.restecg} class="input-field">
                  <option value={0}>{t.restecgType0}</option>
                  <option value={1}>{t.restecgType1}</option>
                  <option value={2}>{t.restecgType2}</option>
                </select>
              </div>

              <!-- Max Heart Rate -->
              <div>
                <label class="label">{t.thalach}</label>
                <input
                  type="number"
                  bind:value={formData.thalach}
                  min="60"
                  max="220"
                  class="input-field"
                  required
                />
              </div>

              <!-- Exercise Induced Angina -->
              <div>
                <label class="label">{t.exang}</label>
                <select bind:value={formData.exang} class="input-field">
                  <option value={0}>{t.no}</option>
                  <option value={1}>{t.yes}</option>
                </select>
              </div>

              <!-- Oldpeak -->
              <div>
                <label class="label">{t.oldpeak}</label>
                <input
                  type="number"
                  bind:value={formData.oldpeak}
                  min="0"
                  max="6"
                  step="0.1"
                  class="input-field"
                  required
                />
              </div>

              <!-- Slope -->
              <div>
                <label class="label">{t.slope}</label>
                <select bind:value={formData.slope} class="input-field">
                  <option value={0}>{t.slopeType0}</option>
                  <option value={1}>{t.slopeType1}</option>
                  <option value={2}>{t.slopeType2}</option>
                </select>
              </div>

              <!-- CA -->
              <div>
                <label class="label">{t.ca}</label>
                <select bind:value={formData.ca} class="input-field">
                  <option value={0}>0</option>
                  <option value={1}>1</option>
                  <option value={2}>2</option>
                  <option value={3}>3</option>
                </select>
              </div>

              <!-- Thal -->
              <div>
                <label class="label">{t.thal}</label>
                <select bind:value={formData.thal} class="input-field">
                  <option value={0}>{t.thalType0}</option>
                  <option value={1}>{t.thalType1}</option>
                  <option value={2}>{t.thalType2}</option>
                  <option value={3}>{t.thalType3}</option>
                </select>
              </div>
            </div>

            <!-- Buttons -->
            <div class="flex gap-4 mt-8">
              <button
                type="submit"
                class="btn btn-primary flex-1"
                disabled={isCalculating}
              >
                {#if isCalculating}
                  <svg class="animate-spin w-5 h-5" fill="none" viewBox="0 0 24 24">
                    <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                    <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
                  </svg>
                  {t.calculating}
                {:else}
                  {t.calculate}
                {/if}
              </button>

              <button
                type="button"
                on:click={resetForm}
                class="btn btn-outline"
              >
                {t.reset}
              </button>
            </div>
          </form>
        </div>
      </div>

      <!-- Results Section -->
      <div class="lg:col-span-1">
        {#if predictionResult}
          <div class="space-y-4">
            <!-- Overall Risk -->
            <div class="card bg-gradient-to-br from-primary-50 to-secondary-50 border-2 border-primary-200">
              <h3 class="text-xl font-bold text-gray-900 mb-4">{t.riskLevel}</h3>

              <div class="text-center mb-6">
                <div class="text-6xl font-bold mb-2 {getRiskLevelColor(predictionResult.ensemble.riskLevel)}">
                  {predictionResult.ensemble.riskScore}%
                </div>
                <div class="inline-block px-4 py-2 rounded-full font-bold {getRiskLevelColor(predictionResult.ensemble.riskLevel)}">
                  {t[`${predictionResult.ensemble.riskLevel}Risk`] || predictionResult.ensemble.riskLevel}
                </div>
              </div>

              <div class="text-center text-gray-700">
                {predictionResult.ensemble.prediction === 1 ? t.hasDisease : t.noDisease}
              </div>
            </div>

            <!-- Model Predictions -->
            <div class="card">
              <h3 class="text-lg font-bold text-gray-900 mb-4">{t.modelsTitle}</h3>

              <!-- KNN -->
              <div class="mb-3 p-3 bg-blue-50 rounded-lg">
                <div class="flex justify-between items-center mb-1">
                  <span class="font-semibold text-gray-700">{t.knnModel}</span>
                  <span class="text-sm text-gray-600">{t.accuracy}: {predictionResult.knn.accuracy}%</span>
                </div>
                <div class="text-sm">
                  {t.confidence}: <span class="font-bold text-blue-600">{predictionResult.knn.confidence.toFixed(1)}%</span>
                </div>
              </div>

              <!-- Naive Bayes -->
              <div class="mb-3 p-3 bg-green-50 rounded-lg">
                <div class="flex justify-between items-center mb-1">
                  <span class="font-semibold text-gray-700">{t.naiveBayesModel}</span>
                  <span class="text-sm text-gray-600">{t.accuracy}: {predictionResult.naiveBayes.accuracy}%</span>
                </div>
                <div class="text-sm">
                  {t.confidence}: <span class="font-bold text-green-600">{predictionResult.naiveBayes.confidence.toFixed(1)}%</span>
                </div>
              </div>

              <!-- Decision Tree -->
              <div class="p-3 bg-orange-50 rounded-lg">
                <div class="flex justify-between items-center mb-1">
                  <span class="font-semibold text-gray-700">{t.decisionTreeModel}</span>
                  <span class="text-sm text-gray-600">{t.accuracy}: {predictionResult.decisionTree.accuracy}%</span>
                </div>
                <div class="text-sm">
                  {t.confidence}: <span class="font-bold text-orange-600">{predictionResult.decisionTree.confidence.toFixed(1)}%</span>
                </div>
              </div>
            </div>

            <!-- Disclaimer -->
            <div class="card bg-yellow-50 border-yellow-200 border">
              <p class="text-xs text-gray-700">
                {currentLang === 'ar'
                  ? '⚠️ هذا النظام للأغراض البحثية فقط ولا يحل محل الاستشارة الطبية المتخصصة.'
                  : '⚠️ This system is for research purposes only and does not replace professional medical consultation.'
                }
              </p>
            </div>
          </div>
        {:else}
          <div class="card text-center py-12">
            <svg class="w-24 h-24 mx-auto text-gray-300 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4" />
            </svg>
            <p class="text-gray-500">
              {currentLang === 'ar'
                ? 'أدخل البيانات الطبية واضغط على "احسب المخاطر" للحصول على التقييم'
                : 'Enter medical data and click "Calculate Risk" to get assessment'
              }
            </p>
          </div>
        {/if}
      </div>
    </div>
  </div>
</section>
