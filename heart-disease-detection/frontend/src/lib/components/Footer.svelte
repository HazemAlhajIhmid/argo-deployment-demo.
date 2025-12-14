<script lang="ts">
  import { language, translations } from '$lib/stores/language';

  let currentLang: 'ar' | 'en' = 'ar';
  language.subscribe(value => {
    currentLang = value;
  });
  $: t = translations[currentLang];

  const currentYear = new Date().getFullYear();
</script>

<footer id="contact" class="bg-gray-900 text-white py-12">
  <div class="container mx-auto px-4">
    <div class="grid md:grid-cols-3 gap-8 mb-8">
      <!-- About Column -->
      <div>
        <div class="flex items-center gap-3 mb-4">
          <div class="w-12 h-12 bg-gradient-to-br from-primary-500 to-secondary-500 rounded-xl flex items-center justify-center">
            <svg class="w-7 h-7 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
            </svg>
          </div>
          <div>
            <h3 class="text-xl font-bold">TechCare</h3>
            <p class="text-sm text-gray-400">{currentLang === 'ar' ? 'الكشف المبكر' : 'Early Detection'}</p>
          </div>
        </div>
        <p class="text-gray-400 text-sm leading-relaxed">
          {currentLang === 'ar'
            ? 'نظام ذكي للكشف المبكر عن أمراض القلب باستخدام خوارزميات تعلم الآلة المتقدمة.'
            : 'Intelligent system for early detection of heart disease using advanced machine learning algorithms.'
          }
        </p>
      </div>

      <!-- University Column -->
      <div>
        <h4 class="text-lg font-bold mb-4">{t.university}</h4>
        <ul class="space-y-2 text-sm text-gray-400">
          <li class="flex items-start gap-2">
            <svg class="w-5 h-5 text-primary-400 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17.657 16.657L13.414 20.9a1.998 1.998 0 01-2.827 0l-4.244-4.243a8 8 0 1111.314 0z" />
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 11a3 3 0 11-6 0 3 3 0 016 0z" />
            </svg>
            <span>{currentLang === 'ar' ? 'الجمهورية العربية السورية' : 'Syrian Arab Republic'}</span>
          </li>
          <li class="flex items-start gap-2">
            <svg class="w-5 h-5 text-primary-400 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6.253v13m0-13C10.832 5.477 9.246 5 7.5 5S4.168 5.477 3 6.253v13C4.168 18.477 5.754 18 7.5 18s3.332.477 4.5 1.253m0-13C13.168 5.477 14.754 5 16.5 5c1.747 0 3.332.477 4.5 1.253v13C19.832 18.477 18.247 18 16.5 18c-1.746 0-3.332.477-4.5 1.253" />
            </svg>
            <span>{currentLang === 'ar' ? 'وزارة التعليم العالي' : 'Ministry of Higher Education'}</span>
          </li>
          <li class="flex items-start gap-2">
            <svg class="w-5 h-5 text-primary-400 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.75 17L9 20l-1 1h8l-1-1-.75-3M3 13h18M5 17h14a2 2 0 002-2V5a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
            </svg>
            <span>{currentLang === 'ar' ? 'ماجستير علوم الويب' : 'MSc in Web Science'}</span>
          </li>
        </ul>
      </div>

      <!-- Contact Column -->
      <div>
        <h4 class="text-lg font-bold mb-4">{t.contactInfo}</h4>
        <ul class="space-y-3 text-sm text-gray-400">
          <li>
            <div class="flex items-start gap-2">
              <svg class="w-5 h-5 text-primary-400 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
              </svg>
              <div>
                <p class="font-medium text-white">{currentLang === 'ar' ? 'حازم خضر الحاج احميد' : 'Hazem Khader'}</p>
                <p class="text-xs">Hazem_82763@svuonline.org</p>
              </div>
            </div>
          </li>
          <li>
            <div class="flex items-start gap-2">
              <svg class="w-5 h-5 text-primary-400 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
              </svg>
              <div>
                <p class="font-medium text-white">{currentLang === 'ar' ? 'د.م. جورج أنور كراز' : 'Dr. George Karraz'}</p>
                <p class="text-xs">T_gkarraz@svuonline.org</p>
              </div>
            </div>
          </li>
          <li>
            <div class="flex items-start gap-2">
              <svg class="w-5 h-5 text-primary-400 flex-shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
              </svg>
              <div>
                <p class="font-medium text-white">{currentLang === 'ar' ? 'د. ماجدة البكور' : 'Dr. Majeda Al-Bakour'}</p>
                <p class="text-xs">T_mbakour@svuonline.org</p>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>

    <!-- Bottom Bar -->
    <div class="border-t border-gray-800 pt-8">
      <div class="flex flex-col md:flex-row justify-between items-center gap-4">
        <p class="text-sm text-gray-400">
          © {currentYear} {t.university}. {t.rightsReserved}
        </p>

        <div class="flex items-center gap-6 text-sm text-gray-400">
          <span>{currentLang === 'ar' ? 'S25 - 2025' : 'S25 - 2025'}</span>
          <span class="flex items-center gap-2">
            <span class="w-2 h-2 bg-green-500 rounded-full animate-pulse"></span>
            {currentLang === 'ar' ? 'نظام نشط' : 'System Active'}
          </span>
        </div>
      </div>

      <!-- Disclaimer -->
      <div class="mt-6 p-4 bg-yellow-900/20 border border-yellow-700/30 rounded-lg">
        <p class="text-xs text-yellow-200 text-center">
          {currentLang === 'ar'
            ? '⚠️ تنويه: هذا النظام مصمم لأغراض البحث الأكاديمي فقط ولا يجب استخدامه كبديل عن الاستشارة الطبية المتخصصة. يُرجى استشارة الطبيب المختص لأي قرارات طبية.'
            : '⚠️ Disclaimer: This system is designed for academic research purposes only and should not be used as a substitute for professional medical consultation. Please consult a qualified physician for any medical decisions.'
          }
        </p>
      </div>
    </div>
  </div>
</footer>
