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
</script>

<footer class="bg-gray-900 text-white py-12">
  <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
    <div class="grid md:grid-cols-3 gap-8 mb-8">
      <!-- Brand -->
      <div>
        <div class="flex items-center space-x-3 rtl:space-x-reverse mb-4">
          <div class="bg-primary-600 p-2 rounded-lg">
            <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
            </svg>
          </div>
          <div>
            <h1 class="text-xl font-bold">TechCare</h1>
            <p class="text-xs text-gray-400">{t('tagline')}</p>
          </div>
        </div>
        <p class="text-sm text-gray-400 leading-relaxed">
          نظام ذكي للكشف المبكر عن أمراض القلب باستخدام خوارزميات التعلم الآلي المتقدمة
        </p>
      </div>

      <!-- University Info -->
      <div>
        <h3 class="text-lg font-bold mb-4">الجامعة الافتراضية السورية</h3>
        <div class="space-y-2 text-sm text-gray-400">
          <p>وزارة التعليم العالي</p>
          <p>الجمهورية العربية السورية</p>
          <p class="mt-4">ماجستير علوم الويب</p>
          <p>تطوير خوارزميات التنقيب عن البيانات</p>
        </div>
      </div>

      <!-- Contact -->
      <div>
        <h3 class="text-lg font-bold mb-4">معلومات التواصل</h3>
        <div class="space-y-3 text-sm">
          <div class="flex items-start space-x-2 rtl:space-x-reverse">
            <svg class="w-5 h-5 text-primary-400 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
            </svg>
            <div class="text-gray-400">
              <p>T_gkarraz@svuonline.org</p>
              <p>T_mbakour@svuonline.org</p>
            </div>
          </div>
          <div class="flex items-start space-x-2 rtl:space-x-reverse">
            <svg class="w-5 h-5 text-primary-400 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 12a9 9 0 01-9 9m9-9a9 9 0 00-9-9m9 9H3m9 9a9 9 0 01-9-9m9 9c1.657 0 3-4.03 3-9s-1.343-9-3-9m0 18c-1.657 0-3-4.03-3-9s1.343-9 3-9m-9 9a9 9 0 019-9" />
            </svg>
            <p class="text-gray-400">www.svuonline.org</p>
          </div>
        </div>
      </div>
    </div>

    <!-- Bottom Bar -->
    <div class="border-t border-gray-800 pt-8">
      <div class="flex flex-col md:flex-row justify-between items-center">
        <p class="text-sm text-gray-400 mb-4 md:mb-0">
          {t('footerText')}
        </p>
        <div class="flex items-center space-x-4 rtl:space-x-reverse text-sm text-gray-400">
          <span>{t('developedBy')}</span>
          <span class="font-semibold text-primary-400">حازم خضر الحاج احميد</span>
        </div>
      </div>
      <div class="mt-4 text-center">
        <p class="text-xs text-gray-500">
          تنويه: هذا النظام للأغراض البحثية والتعليمية فقط. يرجى استشارة طبيب مختص للتشخيص الطبي الدقيق.
        </p>
      </div>
    </div>
  </div>
</footer>
