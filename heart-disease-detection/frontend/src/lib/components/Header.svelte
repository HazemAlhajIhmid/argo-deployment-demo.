<script lang="ts">
  import { language, translations } from '$lib/stores/language';

  let currentLang: 'ar' | 'en' = 'ar';

  language.subscribe(value => {
    currentLang = value;
  });

  function toggleLanguage() {
    const newLang = currentLang === 'ar' ? 'en' : 'ar';
    language.set(newLang);
    if (typeof document !== 'undefined') {
      document.documentElement.setAttribute('dir', newLang === 'ar' ? 'rtl' : 'ltr');
      document.documentElement.setAttribute('lang', newLang);
    }
  }

  $: t = translations[currentLang];
</script>

<header class="bg-white shadow-md sticky top-0 z-50">
  <div class="container mx-auto px-4">
    <div class="flex items-center justify-between h-20">
      <!-- Logo -->
      <div class="flex items-center gap-3">
        <div class="w-12 h-12 bg-gradient-to-br from-primary-600 to-secondary-600 rounded-xl flex items-center justify-center">
          <svg class="w-7 h-7 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
          </svg>
        </div>
        <div>
          <h1 class="text-xl font-bold text-primary-600">TechCare</h1>
          <p class="text-xs text-gray-600">{t.university}</p>
        </div>
      </div>

      <!-- Navigation -->
      <nav class="hidden md:flex items-center gap-8">
        <a href="#home" class="text-gray-700 hover:text-primary-600 font-medium transition-colors">
          {t.home}
        </a>
        <a href="#calculator" class="text-gray-700 hover:text-primary-600 font-medium transition-colors">
          {t.riskCalculator}
        </a>
        <a href="#about" class="text-gray-700 hover:text-primary-600 font-medium transition-colors">
          {t.about}
        </a>
        <a href="#results" class="text-gray-700 hover:text-primary-600 font-medium transition-colors">
          {t.results}
        </a>
        <a href="#contact" class="text-gray-700 hover:text-primary-600 font-medium transition-colors">
          {t.contact}
        </a>
      </nav>

      <!-- Language Toggle -->
      <button
        on:click={toggleLanguage}
        class="btn btn-outline text-sm"
      >
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5h12M9 3v2m1.048 9.5A18.022 18.022 0 016.412 9m6.088 9h7M11 21l5-10 5 10M12.751 5C11.783 10.77 8.07 15.61 3 18.129" />
        </svg>
        {currentLang === 'ar' ? 'English' : 'العربية'}
      </button>
    </div>
  </div>
</header>

<style>
  header {
    backdrop-filter: blur(10px);
  }
</style>
