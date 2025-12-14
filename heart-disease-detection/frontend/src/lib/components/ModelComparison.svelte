<script lang="ts">
  import { onMount } from 'svelte';
  import { language, translations } from '$lib/stores/language';
  import { Chart, registerables } from 'chart.js';

  Chart.register(...registerables);

  let currentLang: 'ar' | 'en' = 'ar';
  language.subscribe(value => {
    currentLang = value;
  });
  $: t = translations[currentLang];

  let accuracyChartCanvas: HTMLCanvasElement;
  let metricsChartCanvas: HTMLCanvasElement;
  let accuracyChart: Chart;
  let metricsChart: Chart;

  const modelData = {
    knn: {
      accuracy: 82,
      precision: 78,
      recall: 94,
      f1Score: 85,
      color: '#3b82f6'
    },
    naiveBayes: {
      accuracy: 82,
      precision: 79,
      recall: 91,
      f1Score: 85,
      color: '#10b981'
    },
    decisionTree: {
      accuracy: 70,
      precision: 70,
      recall: 79,
      f1Score: 74,
      color: '#f59e0b'
    }
  };

  onMount(() => {
    // Accuracy Comparison Chart
    accuracyChart = new Chart(accuracyChartCanvas, {
      type: 'bar',
      data: {
        labels: ['KNN', 'Naive Bayes', 'Decision Tree'],
        datasets: [{
          label: t.accuracy,
          data: [
            modelData.knn.accuracy,
            modelData.naiveBayes.accuracy,
            modelData.decisionTree.accuracy
          ],
          backgroundColor: [
            modelData.knn.color,
            modelData.naiveBayes.color,
            modelData.decisionTree.color
          ],
          borderRadius: 8,
          borderWidth: 0
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: true,
        plugins: {
          legend: {
            display: false
          },
          title: {
            display: true,
            text: currentLang === 'ar' ? 'مقارنة دقة النماذج' : 'Model Accuracy Comparison',
            font: {
              size: 18,
              weight: 'bold'
            }
          }
        },
        scales: {
          y: {
            beginAtZero: true,
            max: 100,
            ticks: {
              callback: function(value) {
                return value + '%';
              }
            }
          }
        }
      }
    });

    // Metrics Comparison Chart
    metricsChart = new Chart(metricsChartCanvas, {
      type: 'radar',
      data: {
        labels: [t.accuracy, t.precision, t.recall, t.f1Score],
        datasets: [
          {
            label: 'KNN',
            data: [
              modelData.knn.accuracy,
              modelData.knn.precision,
              modelData.knn.recall,
              modelData.knn.f1Score
            ],
            backgroundColor: modelData.knn.color + '20',
            borderColor: modelData.knn.color,
            borderWidth: 2,
            pointBackgroundColor: modelData.knn.color
          },
          {
            label: 'Naive Bayes',
            data: [
              modelData.naiveBayes.accuracy,
              modelData.naiveBayes.precision,
              modelData.naiveBayes.recall,
              modelData.naiveBayes.f1Score
            ],
            backgroundColor: modelData.naiveBayes.color + '20',
            borderColor: modelData.naiveBayes.color,
            borderWidth: 2,
            pointBackgroundColor: modelData.naiveBayes.color
          },
          {
            label: 'Decision Tree',
            data: [
              modelData.decisionTree.accuracy,
              modelData.decisionTree.precision,
              modelData.decisionTree.recall,
              modelData.decisionTree.f1Score
            ],
            backgroundColor: modelData.decisionTree.color + '20',
            borderColor: modelData.decisionTree.color,
            borderWidth: 2,
            pointBackgroundColor: modelData.decisionTree.color
          }
        ]
      },
      options: {
        responsive: true,
        maintainAspectRatio: true,
        plugins: {
          title: {
            display: true,
            text: currentLang === 'ar' ? 'مقارنة مقاييس الأداء' : 'Performance Metrics Comparison',
            font: {
              size: 18,
              weight: 'bold'
            }
          },
          legend: {
            position: 'bottom'
          }
        },
        scales: {
          r: {
            beginAtZero: true,
            max: 100,
            ticks: {
              stepSize: 20
            }
          }
        }
      }
    });

    return () => {
      accuracyChart?.destroy();
      metricsChart?.destroy();
    };
  });
</script>

<section id="results" class="py-20 bg-gray-50">
  <div class="container mx-auto px-4">
    <!-- Section Header -->
    <div class="text-center mb-12">
      <h2 class="text-4xl font-bold text-gray-900 mb-4">{t.results}</h2>
      <p class="text-xl text-gray-600">{t.modelsTitle}</p>
    </div>

    <!-- Model Statistics Cards -->
    <div class="grid md:grid-cols-3 gap-6 mb-12">
      <!-- KNN Card -->
      <div class="card bg-gradient-to-br from-blue-50 to-blue-100 border-2 border-blue-200">
        <div class="flex items-center gap-3 mb-4">
          <div class="w-12 h-12 bg-blue-600 rounded-lg flex items-center justify-center">
            <span class="text-white font-bold text-xl">K</span>
          </div>
          <h3 class="text-xl font-bold text-gray-900">{t.knnModel}</h3>
        </div>

        <div class="space-y-3">
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.accuracy}</span>
            <span class="text-2xl font-bold text-blue-600">{modelData.knn.accuracy}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.precision}</span>
            <span class="text-xl font-bold text-blue-600">{modelData.knn.precision}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.recall}</span>
            <span class="text-xl font-bold text-blue-600">{modelData.knn.recall}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.f1Score}</span>
            <span class="text-xl font-bold text-blue-600">{modelData.knn.f1Score}%</span>
          </div>
        </div>

        <div class="mt-4 pt-4 border-t border-blue-200">
          <p class="text-sm text-gray-700">
            {currentLang === 'ar'
              ? 'أعلى معدل استرجاع (94%) - مثالي للكشف المبكر'
              : 'Highest recall (94%) - Ideal for early detection'
            }
          </p>
        </div>
      </div>

      <!-- Naive Bayes Card -->
      <div class="card bg-gradient-to-br from-green-50 to-green-100 border-2 border-green-200">
        <div class="flex items-center gap-3 mb-4">
          <div class="w-12 h-12 bg-green-600 rounded-lg flex items-center justify-center">
            <span class="text-white font-bold text-xl">NB</span>
          </div>
          <h3 class="text-xl font-bold text-gray-900">{t.naiveBayesModel}</h3>
        </div>

        <div class="space-y-3">
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.accuracy}</span>
            <span class="text-2xl font-bold text-green-600">{modelData.naiveBayes.accuracy}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.precision}</span>
            <span class="text-xl font-bold text-green-600">{modelData.naiveBayes.precision}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.recall}</span>
            <span class="text-xl font-bold text-green-600">{modelData.naiveBayes.recall}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.f1Score}</span>
            <span class="text-xl font-bold text-green-600">{modelData.naiveBayes.f1Score}%</span>
          </div>
        </div>

        <div class="mt-4 pt-4 border-t border-green-200">
          <p class="text-sm text-gray-700">
            {currentLang === 'ar'
              ? 'أداء متوازن وسريع - الأفضل للتطبيقات الفورية'
              : 'Balanced and fast performance - Best for real-time applications'
            }
          </p>
        </div>
      </div>

      <!-- Decision Tree Card -->
      <div class="card bg-gradient-to-br from-orange-50 to-orange-100 border-2 border-orange-200">
        <div class="flex items-center gap-3 mb-4">
          <div class="w-12 h-12 bg-orange-600 rounded-lg flex items-center justify-center">
            <span class="text-white font-bold text-xl">DT</span>
          </div>
          <h3 class="text-xl font-bold text-gray-900">{t.decisionTreeModel}</h3>
        </div>

        <div class="space-y-3">
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.accuracy}</span>
            <span class="text-2xl font-bold text-orange-600">{modelData.decisionTree.accuracy}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.precision}</span>
            <span class="text-xl font-bold text-orange-600">{modelData.decisionTree.precision}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.recall}</span>
            <span class="text-xl font-bold text-orange-600">{modelData.decisionTree.recall}%</span>
          </div>
          <div class="flex justify-between items-center">
            <span class="text-gray-700 font-medium">{t.f1Score}</span>
            <span class="text-xl font-bold text-orange-600">{modelData.decisionTree.f1Score}%</span>
          </div>
        </div>

        <div class="mt-4 pt-4 border-t border-orange-200">
          <p class="text-sm text-gray-700">
            {currentLang === 'ar'
              ? 'سهل التفسير - يحتاج إلى تحسين بالتقليم'
              : 'Easy to interpret - Needs improvement with pruning'
            }
          </p>
        </div>
      </div>
    </div>

    <!-- Charts -->
    <div class="grid lg:grid-cols-2 gap-8">
      <!-- Accuracy Chart -->
      <div class="card">
        <canvas bind:this={accuracyChartCanvas}></canvas>
      </div>

      <!-- Metrics Radar Chart -->
      <div class="card">
        <canvas bind:this={metricsChartCanvas}></canvas>
      </div>
    </div>

    <!-- Research Findings -->
    <div class="mt-12 card bg-gradient-to-r from-primary-600 to-secondary-600 text-white">
      <h3 class="text-2xl font-bold mb-6 text-center">
        {currentLang === 'ar' ? 'الاستنتاجات الرئيسية' : 'Key Findings'}
      </h3>

      <div class="grid md:grid-cols-2 gap-6">
        <div class="bg-white/10 rounded-lg p-4 backdrop-blur">
          <h4 class="font-bold mb-2 flex items-center gap-2">
            <span>🏆</span>
            {currentLang === 'ar' ? 'الأفضل للكشف المبكر' : 'Best for Early Detection'}
          </h4>
          <p class="text-sm text-white/90">
            {currentLang === 'ar'
              ? 'نموذج KNN حقق أعلى معدل استرجاع (94%) مما يجعله الأمثل للكشف المبكر عن أمراض القلب'
              : 'KNN model achieved the highest recall (94%), making it optimal for early heart disease detection'
            }
          </p>
        </div>

        <div class="bg-white/10 rounded-lg p-4 backdrop-blur">
          <h4 class="font-bold mb-2 flex items-center gap-2">
            <span>⚖️</span>
            {currentLang === 'ar' ? 'الأداء المتوازن' : 'Balanced Performance'}
          </h4>
          <p class="text-sm text-white/90">
            {currentLang === 'ar'
              ? 'Naive Bayes قدم أداءً متوازناً مع سرعة عالية في المعالجة'
              : 'Naive Bayes provided balanced performance with high processing speed'
            }
          </p>
        </div>

        <div class="bg-white/10 rounded-lg p-4 backdrop-blur">
          <h4 class="font-bold mb-2 flex items-center gap-2">
            <span>📊</span>
            {currentLang === 'ar' ? 'جودة البيانات' : 'Data Quality'}
          </h4>
          <p class="text-sm text-white/90">
            {currentLang === 'ar'
              ? 'معالجة البيانات والمعايرة كانت حاسمة في تحسين أداء النماذج'
              : 'Data preprocessing and standardization were crucial in improving model performance'
            }
          </p>
        </div>

        <div class="bg-white/10 rounded-lg p-4 backdrop-blur">
          <h4 class="font-bold mb-2 flex items-center gap-2">
            <span>🔮</span>
            {currentLang === 'ar' ? 'التطوير المستقبلي' : 'Future Development'}
          </h4>
          <p class="text-sm text-white/90">
            {currentLang === 'ar'
              ? 'يمكن تحسين الأداء باستخدام Random Forest وXGBoost وبيانات أكبر'
              : 'Performance can be improved using Random Forest, XGBoost, and larger datasets'
            }
          </p>
        </div>
      </div>
    </div>
  </div>
</section>
