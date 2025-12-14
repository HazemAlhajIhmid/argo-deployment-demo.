<script lang="ts">
  import { currentLanguage } from '../stores/language';
  import { translations } from '../lib/translations';
  import { predictionResult } from '../stores/calculator';
  import type { PredictionResult } from '../stores/calculator';

  let lang: 'ar' | 'en';
  currentLanguage.subscribe(value => lang = value);

  let result: PredictionResult | null;
  predictionResult.subscribe(value => result = value);

  function t(key: string): string {
    const keys = key.split('.');
    let value: any = translations[lang];
    for (const k of keys) {
      value = value?.[k];
    }
    return value || key;
  }

  function getRiskColor(level: string): string {
    switch(level) {
      case 'low': return 'bg-success-100 text-success-600 border-success-300';
      case 'moderate': return 'bg-warning-100 text-warning-600 border-warning-300';
      case 'high': return 'bg-red-100 text-red-600 border-red-300';
      default: return 'bg-gray-100 text-gray-600 border-gray-300';
    }
  }

  function getRiskIcon(level: string): string {
    switch(level) {
      case 'low': return 'M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z';
      case 'moderate': return 'M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z';
      case 'high': return 'M10 14l2-2m0 0l2-2m-2 2l-2-2m2 2l2 2m7-2a9 9 0 11-18 0 9 9 0 0118 0z';
      default: return '';
    }
  }

  function getRiskRecommendation(level: string): string {
    switch(level) {
      case 'low': return t('lowRiskRec');
      case 'moderate': return t('moderateRiskRec');
      case 'high': return t('highRiskRec');
      default: return '';
    }
  }
</script>

{#if result}
<section id="results" class="py-16 bg-white">
  <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
    <div class="text-center mb-12">
      <h2 class="text-3xl md:text-4xl font-bold text-gray-900 mb-4">
        {t('resultsTitle')}
      </h2>
    </div>

    <!-- Overall Risk Level -->
    <div class="max-w-3xl mx-auto mb-12">
      <div class={`card border-4 ${getRiskColor(result.riskLevel)}`}>
        <div class="flex items-center justify-between">
          <div>
            <h3 class="text-2xl font-bold mb-2">{t('riskLevel')}</h3>
            <p class="text-4xl font-extrabold">
              {t(`${result.riskLevel}Risk`)} - {(result.averageProbability * 100).toFixed(1)}%
            </p>
          </div>
          <svg class="w-24 h-24 opacity-20" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d={getRiskIcon(result.riskLevel)} />
          </svg>
        </div>
      </div>
    </div>

    <!-- Model Predictions -->
    <div class="mb-12">
      <h3 class="text-2xl font-bold text-gray-900 mb-6 text-center">{t('modelPredictions')}</h3>
      <div class="grid md:grid-cols-3 gap-6">
        <!-- KNN Model -->
        <div class="card hover:shadow-xl transition-shadow">
          <div class="flex items-center justify-between mb-4">
            <h4 class="text-xl font-bold text-primary-700">{t('knnModel')}</h4>
            <span class="text-xs bg-primary-100 text-primary-700 px-3 py-1 rounded-full font-semibold">
              {t('accuracy')}: {(result.knn.accuracy * 100).toFixed(0)}%
            </span>
          </div>
          <div class="mb-4">
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm text-gray-600">{t('prediction')}</span>
              <span class={`px-3 py-1 rounded-full text-sm font-semibold ${result.knn.prediction === 1 ? 'bg-red-100 text-red-700' : 'bg-success-100 text-success-700'}`}>
                {result.knn.prediction === 1 ? t('positive') : t('negative')}
              </span>
            </div>
            <div class="w-full bg-gray-200 rounded-full h-4 overflow-hidden">
              <div
                class={`h-full rounded-full transition-all duration-1000 ${result.knn.prediction === 1 ? 'bg-red-500' : 'bg-success-500'}`}
                style="width: {result.knn.probability * 100}%"
              ></div>
            </div>
            <p class="text-right text-sm text-gray-600 mt-1">{(result.knn.probability * 100).toFixed(1)}%</p>
          </div>
          <div class="pt-4 border-t border-gray-200">
            <p class="text-xs text-gray-500">
              Recall: 0.94 | F1-score: 0.85
            </p>
          </div>
        </div>

        <!-- Naive Bayes Model -->
        <div class="card hover:shadow-xl transition-shadow">
          <div class="flex items-center justify-between mb-4">
            <h4 class="text-xl font-bold text-primary-700">{t('naiveBayesModel')}</h4>
            <span class="text-xs bg-primary-100 text-primary-700 px-3 py-1 rounded-full font-semibold">
              {t('accuracy')}: {(result.naiveBayes.accuracy * 100).toFixed(0)}%
            </span>
          </div>
          <div class="mb-4">
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm text-gray-600">{t('prediction')}</span>
              <span class={`px-3 py-1 rounded-full text-sm font-semibold ${result.naiveBayes.prediction === 1 ? 'bg-red-100 text-red-700' : 'bg-success-100 text-success-700'}`}>
                {result.naiveBayes.prediction === 1 ? t('positive') : t('negative')}
              </span>
            </div>
            <div class="w-full bg-gray-200 rounded-full h-4 overflow-hidden">
              <div
                class={`h-full rounded-full transition-all duration-1000 ${result.naiveBayes.prediction === 1 ? 'bg-red-500' : 'bg-success-500'}`}
                style="width: {result.naiveBayes.probability * 100}%"
              ></div>
            </div>
            <p class="text-right text-sm text-gray-600 mt-1">{(result.naiveBayes.probability * 100).toFixed(1)}%</p>
          </div>
          <div class="pt-4 border-t border-gray-200">
            <p class="text-xs text-gray-500">
              Recall: 0.91 | F1-score: 0.85
            </p>
          </div>
        </div>

        <!-- Decision Tree Model -->
        <div class="card hover:shadow-xl transition-shadow">
          <div class="flex items-center justify-between mb-4">
            <h4 class="text-xl font-bold text-primary-700">{t('decisionTreeModel')}</h4>
            <span class="text-xs bg-primary-100 text-primary-700 px-3 py-1 rounded-full font-semibold">
              {t('accuracy')}: {(result.decisionTree.accuracy * 100).toFixed(0)}%
            </span>
          </div>
          <div class="mb-4">
            <div class="flex items-center justify-between mb-2">
              <span class="text-sm text-gray-600">{t('prediction')}</span>
              <span class={`px-3 py-1 rounded-full text-sm font-semibold ${result.decisionTree.prediction === 1 ? 'bg-red-100 text-red-700' : 'bg-success-100 text-success-700'}`}>
                {result.decisionTree.prediction === 1 ? t('positive') : t('negative')}
              </span>
            </div>
            <div class="w-full bg-gray-200 rounded-full h-4 overflow-hidden">
              <div
                class={`h-full rounded-full transition-all duration-1000 ${result.decisionTree.prediction === 1 ? 'bg-red-500' : 'bg-success-500'}`}
                style="width: {result.decisionTree.probability * 100}%"
              ></div>
            </div>
            <p class="text-right text-sm text-gray-600 mt-1">{(result.decisionTree.probability * 100).toFixed(1)}%</p>
          </div>
          <div class="pt-4 border-t border-gray-200">
            <p class="text-xs text-gray-500">
              Recall: 0.79 | F1-score: 0.74
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- Recommendations -->
    <div class="max-w-3xl mx-auto">
      <div class={`card border-l-4 ${result.riskLevel === 'high' ? 'border-red-500 bg-red-50' : result.riskLevel === 'moderate' ? 'border-warning-500 bg-warning-50' : 'border-success-500 bg-success-50'}`}>
        <div class="flex items-start space-x-4 rtl:space-x-reverse">
          <svg class="w-8 h-8 flex-shrink-0 text-current" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <div>
            <h4 class="text-xl font-bold mb-2">{t('recommendationsTitle')}</h4>
            <p class="text-lg leading-relaxed">
              {getRiskRecommendation(result.riskLevel)}
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
{/if}
