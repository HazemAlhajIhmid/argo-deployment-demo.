<script lang="ts">
  import { currentLanguage } from '../stores/language';
  import { translations } from '../lib/translations';

  let lang: 'ar' | 'en';
  currentLanguage.subscribe(value => lang = value);

  function t(key: string): string {
    const keys = key.split('.');
    let value: any = translations[lang];
    for (const k of keys) {
      value = value?.[k];
    }
    return value || key;
  }

  const modelComparison = [
    { model: 'KNN', accuracy: 0.82, precision: 0.78, recall: 0.94, f1: 0.85 },
    { model: 'Naive Bayes', accuracy: 0.82, precision: 0.79, recall: 0.91, f1: 0.85 },
    { model: 'Decision Tree', accuracy: 0.70, precision: 0.70, recall: 0.79, f1: 0.74 },
  ];
</script>

<section id="about" class="py-16 bg-gradient-to-br from-primary-50 to-blue-50">
  <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
    <!-- Research Header -->
    <div class="text-center mb-12">
      <div class="inline-block bg-white px-6 py-3 rounded-full shadow-md mb-6">
        <p class="text-primary-700 font-bold">{t('researchTitle')}</p>
      </div>
      <h2 class="text-3xl md:text-4xl font-bold text-gray-900 mb-4">
        {t('thesisTitle')}
      </h2>
      <p class="text-lg text-gray-700 max-w-3xl mx-auto">
        {t('university')}
      </p>
      <p class="text-sm text-gray-600 mt-2">
        {t('ministry')}
      </p>
    </div>

    <!-- Research Team -->
    <div class="grid md:grid-cols-3 gap-6 mb-12">
      <!-- Student -->
      <div class="card text-center hover:shadow-xl transition-shadow">
        <div class="w-20 h-20 bg-primary-100 rounded-full flex items-center justify-center mx-auto mb-4">
          <svg class="w-10 h-10 text-primary-600" fill="currentColor" viewBox="0 0 20 20">
            <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd" />
          </svg>
        </div>
        <h3 class="font-bold text-lg text-gray-900 mb-2">حازم خضر الحاج احميد</h3>
        <p class="text-sm text-gray-600">باحث - ماجستير علوم الويب</p>
        <p class="text-xs text-gray-500 mt-2">Hazem_82763@svuonline.org</p>
      </div>

      <!-- Main Supervisor -->
      <div class="card text-center hover:shadow-xl transition-shadow">
        <div class="w-20 h-20 bg-success-100 rounded-full flex items-center justify-center mx-auto mb-4">
          <svg class="w-10 h-10 text-success-600" fill="currentColor" viewBox="0 0 20 20">
            <path d="M10.394 2.08a1 1 0 00-.788 0l-7 3a1 1 0 000 1.84L5.25 8.051a.999.999 0 01.356-.257l4-1.714a1 1 0 11.788 1.838L7.667 9.088l1.94.831a1 1 0 00.787 0l7-3a1 1 0 000-1.838l-7-3zM3.31 9.397L5 10.12v4.102a8.969 8.969 0 00-1.05-.174 1 1 0 01-.89-.89 11.115 11.115 0 01.25-3.762zM9.3 16.573A9.026 9.026 0 007 14.935v-3.957l1.818.78a3 3 0 002.364 0l5.508-2.361a11.026 11.026 0 01.25 3.762 1 1 0 01-.89.89 8.968 8.968 0 00-5.35 2.524 1 1 0 01-1.4 0zM6 18a1 1 0 001-1v-2.065a8.935 8.935 0 00-2-.712V17a1 1 0 001 1z" />
          </svg>
        </div>
        <h3 class="font-bold text-lg text-gray-900 mb-2">د.م. جورج أنور كراز</h3>
        <p class="text-sm text-gray-600">المشرف الأساسي</p>
        <p class="text-xs text-gray-500 mt-2">T_gkarraz@svuonline.org</p>
      </div>

      <!-- Co-Supervisor -->
      <div class="card text-center hover:shadow-xl transition-shadow">
        <div class="w-20 h-20 bg-warning-100 rounded-full flex items-center justify-center mx-auto mb-4">
          <svg class="w-10 h-10 text-warning-600" fill="currentColor" viewBox="0 0 20 20">
            <path d="M10.394 2.08a1 1 0 00-.788 0l-7 3a1 1 0 000 1.84L5.25 8.051a.999.999 0 01.356-.257l4-1.714a1 1 0 11.788 1.838L7.667 9.088l1.94.831a1 1 0 00.787 0l7-3a1 1 0 000-1.838l-7-3zM3.31 9.397L5 10.12v4.102a8.969 8.969 0 00-1.05-.174 1 1 0 01-.89-.89 11.115 11.115 0 01.25-3.762zM9.3 16.573A9.026 9.026 0 007 14.935v-3.957l1.818.78a3 3 0 002.364 0l5.508-2.361a11.026 11.026 0 01.25 3.762 1 1 0 01-.89.89 8.968 8.968 0 00-5.35 2.524 1 1 0 01-1.4 0zM6 18a1 1 0 001-1v-2.065a8.935 8.935 0 00-2-.712V17a1 1 0 001 1z" />
          </svg>
        </div>
        <h3 class="font-bold text-lg text-gray-900 mb-2">د. ماجدة البكور</h3>
        <p class="text-sm text-gray-600">المشرف المشارك</p>
        <p class="text-xs text-gray-500 mt-2">T_mbakour@svuonline.org</p>
      </div>
    </div>

    <!-- Model Performance Comparison -->
    <div class="card mb-12">
      <h3 class="text-2xl font-bold text-gray-900 mb-6 text-center">{t('modelComparisonTitle')}</h3>
      <div class="overflow-x-auto">
        <table class="w-full">
          <thead>
            <tr class="bg-primary-50 border-b-2 border-primary-200">
              <th class="px-6 py-4 text-right font-bold text-primary-900">النموذج</th>
              <th class="px-6 py-4 text-center font-bold text-primary-900">الدقة<br/>(Accuracy)</th>
              <th class="px-6 py-4 text-center font-bold text-primary-900">{t('precision')}</th>
              <th class="px-6 py-4 text-center font-bold text-primary-900">{t('recall')}</th>
              <th class="px-6 py-4 text-center font-bold text-primary-900">{t('f1Score')}</th>
            </tr>
          </thead>
          <tbody>
            {#each modelComparison as model, i}
              <tr class={`border-b border-gray-200 hover:bg-gray-50 transition-colors ${i === 0 || i === 1 ? 'bg-success-50/30' : ''}`}>
                <td class="px-6 py-4 font-bold text-gray-900">{model.model}</td>
                <td class="px-6 py-4 text-center">
                  <span class="inline-block px-4 py-2 bg-primary-100 text-primary-700 rounded-full font-bold">
                    {(model.accuracy * 100).toFixed(0)}%
                  </span>
                </td>
                <td class="px-6 py-4 text-center font-semibold">{model.precision.toFixed(2)}</td>
                <td class="px-6 py-4 text-center font-semibold">{model.recall.toFixed(2)}</td>
                <td class="px-6 py-4 text-center font-semibold">{model.f1.toFixed(2)}</td>
              </tr>
            {/each}
          </tbody>
        </table>
      </div>
    </div>

    <!-- Dataset Information -->
    <div class="grid md:grid-cols-2 gap-6">
      <div class="card bg-gradient-to-br from-primary-500 to-blue-600 text-white">
        <h3 class="text-xl font-bold mb-4">مصدر البيانات</h3>
        <div class="space-y-3">
          <div class="flex items-start space-x-3 rtl:space-x-reverse">
            <svg class="w-6 h-6 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
            </svg>
            <div>
              <p class="font-semibold">UCI Heart Disease Dataset</p>
              <p class="text-sm text-blue-100">303 سجل طبي</p>
            </div>
          </div>
          <div class="flex items-start space-x-3 rtl:space-x-reverse">
            <svg class="w-6 h-6 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 12l3-3 3 3 4-4M8 21l4-4 4 4M3 4h18M4 4h16v12a1 1 0 01-1 1H5a1 1 0 01-1-1V4z" />
            </svg>
            <div>
              <p class="font-semibold">14 معاملاً طبياً</p>
              <p class="text-sm text-blue-100">بيانات سريرية حقيقية</p>
            </div>
          </div>
        </div>
      </div>

      <div class="card bg-gradient-to-br from-success-500 to-green-600 text-white">
        <h3 class="text-xl font-bold mb-4">منهجية البحث</h3>
        <div class="space-y-3">
          <div class="flex items-start space-x-3 rtl:space-x-reverse">
            <svg class="w-6 h-6 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4" />
            </svg>
            <div>
              <p class="font-semibold">Data Mining</p>
              <p class="text-sm text-green-100">خوارزميات التنقيب عن البيانات</p>
            </div>
          </div>
          <div class="flex items-start space-x-3 rtl:space-x-reverse">
            <svg class="w-6 h-6 flex-shrink-0" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z" />
            </svg>
            <div>
              <p class="font-semibold">Machine Learning</p>
              <p class="text-sm text-green-100">ثلاثة نماذج ذكاء اصطناعي</p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>
