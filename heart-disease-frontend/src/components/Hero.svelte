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

  function scrollToCalculator() {
    document.getElementById('calculator')?.scrollIntoView({ behavior: 'smooth' });
  }
</script>

<section id="home" class="hero-gradient text-white py-20">
  <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
    <div class="grid md:grid-cols-2 gap-12 items-center">
      <!-- Text Content -->
      <div class="text-center md:text-start">
        <h1 class="text-4xl md:text-5xl font-bold mb-6 leading-tight">
          {t('heroTitle')}
        </h1>
        <p class="text-lg md:text-xl mb-8 text-blue-100 leading-relaxed">
          {t('heroSubtitle')}
        </p>
        <div class="flex flex-col sm:flex-row gap-4 justify-center md:justify-start">
          <button on:click={scrollToCalculator} class="btn-primary bg-white text-primary-600 hover:bg-gray-100">
            {t('startAssessment')}
          </button>
          <a href="#about" class="btn-secondary border-white text-white hover:bg-white/10">
            {t('learnMore')}
          </a>
        </div>

        <!-- Research Badge -->
        <div class="mt-12 p-4 bg-white/10 backdrop-blur-sm rounded-xl border border-white/20">
          <p class="text-sm font-semibold mb-2">{t('researchTitle')}</p>
          <p class="text-xs text-blue-100">{t('university')}</p>
        </div>
      </div>

      <!-- Illustration -->
      <div class="relative">
        <div class="relative z-10 bg-white/10 backdrop-blur-md rounded-2xl p-8 border border-white/20">
          <!-- Heart Icon with Pulse Animation -->
          <div class="flex justify-center mb-6">
            <div class="relative">
              <svg class="w-32 h-32 text-white animate-pulse" fill="currentColor" viewBox="0 0 24 24">
                <path d="M12 21.35l-1.45-1.32C5.4 15.36 2 12.28 2 8.5 2 5.42 4.42 3 7.5 3c1.74 0 3.41.81 4.5 2.09C13.09 3.81 14.76 3 16.5 3 19.58 3 22 5.42 22 8.5c0 3.78-3.4 6.86-8.55 11.54L12 21.35z"/>
              </svg>
              <div class="absolute inset-0 flex items-center justify-center">
                <div class="w-24 h-24 border-4 border-white/30 rounded-full animate-ping"></div>
              </div>
            </div>
          </div>

          <!-- Stats -->
          <div class="grid grid-cols-3 gap-4 text-center">
            <div>
              <p class="text-3xl font-bold">82%</p>
              <p class="text-xs text-blue-100 mt-1">KNN دقة</p>
            </div>
            <div>
              <p class="text-3xl font-bold">82%</p>
              <p class="text-xs text-blue-100 mt-1">Naive Bayes</p>
            </div>
            <div>
              <p class="text-3xl font-bold">14</p>
              <p class="text-xs text-blue-100 mt-1">معاملات طبية</p>
            </div>
          </div>
        </div>

        <!-- Decorative Elements -->
        <div class="absolute top-0 -right-4 w-72 h-72 bg-blue-400/20 rounded-full blur-3xl"></div>
        <div class="absolute bottom-0 -left-4 w-72 h-72 bg-purple-400/20 rounded-full blur-3xl"></div>
      </div>
    </div>
  </div>
</section>
