<script lang="ts">
  import { currentLanguage, toggleLanguage } from '../stores/language';
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

<header class="bg-white shadow-md sticky top-0 z-50">
  <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
    <div class="flex justify-between items-center py-4">
      <!-- Logo and Brand -->
      <div class="flex items-center space-x-3 rtl:space-x-reverse">
        <div class="bg-primary-600 p-2 rounded-lg">
          <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
          </svg>
        </div>
        <div>
          <h1 class="text-xl font-bold text-primary-700">TechCare</h1>
          <p class="text-xs text-gray-600">{t('tagline')}</p>
        </div>
      </div>

      <!-- Navigation -->
      <nav class="hidden md:flex items-center space-x-6 rtl:space-x-reverse">
        <a href="#home" class="text-gray-700 hover:text-primary-600 font-semibold transition-colors">{t('home')}</a>
        <a href="#calculator" class="text-gray-700 hover:text-primary-600 font-semibold transition-colors">{t('calculator')}</a>
        <a href="#about" class="text-gray-700 hover:text-primary-600 font-semibold transition-colors">{t('about')}</a>
        <a href="#results" class="text-gray-700 hover:text-primary-600 font-semibold transition-colors">{t('results')}</a>
      </nav>

      <!-- Language Toggle -->
      <button
        on:click={toggleLanguage}
        class="flex items-center space-x-2 rtl:space-x-reverse px-4 py-2 bg-primary-100 hover:bg-primary-200 text-primary-700 rounded-lg font-semibold transition-all"
      >
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5h12M9 3v2m1.048 9.5A18.022 18.022 0 016.412 9m6.088 9h7M11 21l5-10 5 10M12.751 5C11.783 10.77 8.07 15.61 3 18.129" />
        </svg>
        <span>{lang === 'ar' ? 'English' : 'العربية'}</span>
      </button>
    </div>
  </div>
</header>
