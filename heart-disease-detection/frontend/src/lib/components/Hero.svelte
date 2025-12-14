<script lang="ts">
  import { language, translations } from '$lib/stores/language';

  let currentLang: 'ar' | 'en' = 'ar';

  language.subscribe(value => {
    currentLang = value;
  });

  $: t = translations[currentLang];

  function scrollToCalculator() {
    document.getElementById('calculator')?.scrollIntoView({ behavior: 'smooth' });
  }

  function scrollToAbout() {
    document.getElementById('about')?.scrollIntoView({ behavior: 'smooth' });
  }
</script>

<section id="home" class="relative overflow-hidden">
  <!-- Background Gradient -->
  <div class="absolute inset-0 medical-gradient opacity-10"></div>

  <!-- Content -->
  <div class="container mx-auto px-4 py-20 relative">
    <div class="grid md:grid-cols-2 gap-12 items-center">
      <!-- Text Content -->
      <div class="text-center md:text-{currentLang === 'ar' ? 'right' : 'left'} animate-fade-in">
        <div class="inline-block mb-4 px-4 py-2 bg-primary-100 text-primary-700 rounded-full text-sm font-semibold">
          {t.ministry}
        </div>

        <h1 class="text-4xl md:text-5xl lg:text-6xl font-bold text-gray-900 mb-6 leading-tight">
          {t.heroTitle}
        </h1>

        <p class="text-lg md:text-xl text-gray-600 mb-8 leading-relaxed">
          {t.heroSubtitle}
        </p>

        <!-- CTA Buttons -->
        <div class="flex flex-col sm:flex-row gap-4 justify-center md:justify-{currentLang === 'ar' ? 'end' : 'start'}">
          <button on:click={scrollToCalculator} class="btn btn-primary text-lg">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
            </svg>
            {t.startAssessment}
          </button>

          <button on:click={scrollToAbout} class="btn btn-outline text-lg">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
            {t.learnMore}
          </button>
        </div>

        <!-- Research Info -->
        <div class="mt-12 p-6 bg-white/50 backdrop-blur rounded-xl shadow-lg">
          <h3 class="text-sm font-bold text-primary-600 mb-2">{t.researchTitle}</h3>
          <p class="text-sm text-gray-700 mb-1">{t.student}</p>
          <p class="text-xs text-gray-600">{t.supervisors}</p>
        </div>
      </div>

      <!-- Visual Elements -->
      <div class="relative animate-fade-in" style="animation-delay: 0.2s;">
        <!-- Heart Illustration -->
        <div class="relative w-full aspect-square max-w-lg mx-auto">
          <!-- Pulsing Circles -->
          <div class="absolute inset-0 flex items-center justify-center">
            <div class="w-64 h-64 rounded-full bg-primary-200 opacity-20 animate-pulse"></div>
          </div>
          <div class="absolute inset-0 flex items-center justify-center" style="animation-delay: 0.5s;">
            <div class="w-80 h-80 rounded-full bg-secondary-200 opacity-20 animate-pulse"></div>
          </div>

          <!-- Central Icon -->
          <div class="absolute inset-0 flex items-center justify-center">
            <div class="w-48 h-48 medical-gradient rounded-full flex items-center justify-center shadow-2xl">
              <svg class="w-32 h-32 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
              </svg>
            </div>
          </div>

          <!-- Stats Cards -->
          <div class="absolute top-4 {currentLang === 'ar' ? 'right' : 'left'}-4 card p-4 w-32 text-center">
            <div class="text-3xl font-bold text-primary-600">82%</div>
            <div class="text-xs text-gray-600">{t.accuracy}</div>
            <div class="text-xs text-gray-500">KNN & NB</div>
          </div>

          <div class="absolute bottom-4 {currentLang === 'ar' ? 'left' : 'right'}-4 card p-4 w-32 text-center">
            <div class="text-3xl font-bold text-secondary-600">303</div>
            <div class="text-xs text-gray-600">{currentLang === 'ar' ? 'سجل طبي' : 'Records'}</div>
            <div class="text-xs text-gray-500">UCI Dataset</div>
          </div>

          <div class="absolute top-1/2 {currentLang === 'ar' ? 'left' : 'right'}-8 transform -translate-y-1/2 card p-4 w-32 text-center">
            <div class="text-3xl font-bold text-orange-600">3</div>
            <div class="text-xs text-gray-600">{currentLang === 'ar' ? 'نماذج ML' : 'ML Models'}</div>
          </div>
        </div>
      </div>
    </div>
  </div>

  <!-- Wave Divider -->
  <div class="absolute bottom-0 left-0 right-0">
    <svg viewBox="0 0 1440 120" fill="none" xmlns="http://www.w3.org/2000/svg">
      <path d="M0,64L48,69.3C96,75,192,85,288,80C384,75,480,53,576,48C672,43,768,53,864,58.7C960,64,1056,64,1152,58.7C1248,53,1344,43,1392,37.3L1440,32L1440,120L1392,120C1344,120,1248,120,1152,120C1056,120,960,120,864,120C768,120,672,120,576,120C480,120,384,120,288,120C192,120,96,120,48,120L0,120Z" fill="#f8fafc"/>
    </svg>
  </div>
</section>

<style>
  @keyframes pulse {
    0%, 100% {
      transform: scale(1);
      opacity: 0.2;
    }
    50% {
      transform: scale(1.1);
      opacity: 0.3;
    }
  }

  .animate-pulse {
    animation: pulse 3s cubic-bezier(0.4, 0, 0.6, 1) infinite;
  }
</style>
