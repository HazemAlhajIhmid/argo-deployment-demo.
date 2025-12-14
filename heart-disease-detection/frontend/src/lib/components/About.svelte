<script lang="ts">
  import { language, translations } from '$lib/stores/language';

  let currentLang: 'ar' | 'en' = 'ar';
  language.subscribe(value => {
    currentLang = value;
  });
  $: t = translations[currentLang];

  const methodology = [
    {
      icon: '📊',
      key: 'dataCollection',
      description: 'dataCollectionDesc'
    },
    {
      icon: '⚙️',
      key: 'preprocessing',
      description: 'preprocessingDesc'
    },
    {
      icon: '🤖',
      key: 'modelTraining',
      description: 'modelTrainingDesc'
    },
    {
      icon: '📈',
      key: 'evaluation',
      description: 'evaluationDesc'
    }
  ];
</script>

<section id="about" class="py-20 bg-white">
  <div class="container mx-auto px-4">
    <!-- Section Header -->
    <div class="text-center mb-16">
      <h2 class="text-4xl font-bold text-gray-900 mb-4">{t.about}</h2>
      <p class="text-xl text-gray-600 max-w-3xl mx-auto">
        {t.researchTitle}
      </p>

      <!-- University Badge -->
      <div class="mt-8 inline-block p-6 bg-gradient-to-r from-primary-50 to-secondary-50 rounded-2xl border-2 border-primary-200">
        <div class="flex items-center gap-4">
          <div class="w-16 h-16 bg-primary-600 rounded-full flex items-center justify-center">
            <svg class="w-10 h-10 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253" />
            </svg>
          </div>
          <div class="text-{currentLang === 'ar' ? 'right' : 'left'}">
            <h3 class="font-bold text-lg text-gray-900">{t.university}</h3>
            <p class="text-sm text-gray-600">{t.ministry}</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Research Team -->
    <div class="mb-16">
      <div class="card max-w-4xl mx-auto">
        <div class="grid md:grid-cols-2 gap-8">
          <!-- Student -->
          <div class="text-center p-6 bg-primary-50 rounded-xl">
            <div class="w-20 h-20 bg-primary-600 rounded-full flex items-center justify-center mx-auto mb-4">
              <svg class="w-12 h-12 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
              </svg>
            </div>
            <h4 class="font-bold text-lg text-gray-900 mb-2">
              {currentLang === 'ar' ? 'حازم خضر الحاج احميد' : 'Hazem Khader Al-Haj Ahmid'}
            </h4>
            <p class="text-sm text-gray-600 mb-2">{currentLang === 'ar' ? 'طالب ماجستير - علوم الويب' : 'MSc Student - Web Science'}</p>
            <p class="text-xs text-gray-500">Hazem_82763@svuonline.org</p>
          </div>

          <!-- Supervisors -->
          <div class="space-y-4">
            <div class="p-4 bg-secondary-50 rounded-xl">
              <h4 class="font-semibold text-gray-900 mb-1">
                {currentLang === 'ar' ? 'د.م. جورج أنور كراز' : 'Dr. George Anwar Karraz'}
              </h4>
              <p class="text-sm text-gray-600 mb-1">{currentLang === 'ar' ? 'المشرف الأساسي' : 'Main Supervisor'}</p>
              <p class="text-xs text-gray-500">T_gkarraz@svuonline.org</p>
            </div>

            <div class="p-4 bg-secondary-50 rounded-xl">
              <h4 class="font-semibold text-gray-900 mb-1">
                {currentLang === 'ar' ? 'د. ماجدة البكور' : 'Dr. Majeda Al-Bakour'}
              </h4>
              <p class="text-sm text-gray-600 mb-1">{currentLang === 'ar' ? 'المشرف المشارك' : 'Co-Supervisor'}</p>
              <p class="text-xs text-gray-500">T_mbakour@svuonline.org</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Research Methodology -->
    <div>
      <h3 class="text-3xl font-bold text-center text-gray-900 mb-12">{t.researchMethodology}</h3>

      <div class="grid md:grid-cols-2 lg:grid-cols-4 gap-6">
        {#each methodology as step}
          <div class="health-card text-center">
            <div class="text-5xl mb-4">{step.icon}</div>
            <h4 class="font-bold text-lg text-gray-900 mb-3">{t[step.key]}</h4>
            <p class="text-sm text-gray-600 leading-relaxed">{t[step.description]}</p>
          </div>
        {/each}
      </div>
    </div>

    <!-- Key Features -->
    <div class="mt-16">
      <div class="card bg-gradient-to-br from-primary-600 to-secondary-600 text-white">
        <h3 class="text-2xl font-bold mb-6 text-center">{currentLang === 'ar' ? 'المميزات الرئيسية' : 'Key Features'}</h3>

        <div class="grid md:grid-cols-3 gap-6">
          <div class="text-center">
            <div class="text-4xl mb-3">🎯</div>
            <h4 class="font-bold mb-2">{currentLang === 'ar' ? 'دقة عالية' : 'High Accuracy'}</h4>
            <p class="text-sm text-white/80">{currentLang === 'ar' ? 'دقة تصل إلى 82% مع نماذج KNN و Naive Bayes' : 'Up to 82% accuracy with KNN and Naive Bayes models'}</p>
          </div>

          <div class="text-center">
            <div class="text-4xl mb-3">⚡</div>
            <h4 class="font-bold mb-2">{currentLang === 'ar' ? 'تنبؤ سريع' : 'Fast Prediction'}</h4>
            <p class="text-sm text-white/80">{currentLang === 'ar' ? 'نتائج فورية باستخدام خوارزميات محسّنة' : 'Instant results using optimized algorithms'}</p>
          </div>

          <div class="text-center">
            <div class="text-4xl mb-3">🔬</div>
            <h4 class="font-bold mb-2">{currentLang === 'ar' ? 'بيانات موثوقة' : 'Reliable Data'}</h4>
            <p class="text-sm text-white/80">{currentLang === 'ar' ? 'بيانات UCI المعتمدة عالمياً' : 'Globally recognized UCI dataset'}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
