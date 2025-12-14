<script lang="ts">
  import { currentLanguage } from '../stores/language';
  import { translations } from '../lib/translations';
  import { patientData, calculateRisk, resetForm, isCalculating, predictionResult } from '../stores/calculator';
  import type { PatientData } from '../stores/calculator';

  let lang: 'ar' | 'en';
  currentLanguage.subscribe(value => lang = value);

  let formData: PatientData;
  patientData.subscribe(value => formData = value);

  let calculating: boolean;
  isCalculating.subscribe(value => calculating = value);

  function t(key: string): string {
    const keys = key.split('.');
    let value: any = translations[lang];
    for (const k of keys) {
      value = value?.[k];
    }
    return value || key;
  }

  async function handleSubmit() {
    await calculateRisk(formData);
    document.getElementById('results')?.scrollIntoView({ behavior: 'smooth' });
  }

  function handleReset() {
    resetForm();
  }
</script>

<section id="calculator" class="py-16 bg-gray-50">
  <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
    <div class="text-center mb-12">
      <h2 class="text-3xl md:text-4xl font-bold text-gray-900 mb-4">
        {t('calculatorTitle')}
      </h2>
      <p class="text-lg text-gray-600 max-w-3xl mx-auto">
        {t('calculatorDesc')}
      </p>
    </div>

    <div class="card max-w-5xl mx-auto">
      <form on:submit|preventDefault={handleSubmit}>
        <div class="grid md:grid-cols-2 gap-6">
          <!-- Age -->
          <div>
            <label class="label-text">{t('age')}</label>
            <input
              type="number"
              bind:value={formData.age}
              min="1"
              max="120"
              class="input-field"
              placeholder={t('ageDesc')}
              required
            />
          </div>

          <!-- Sex -->
          <div>
            <label class="label-text">{t('sex')}</label>
            <select bind:value={formData.sex} class="input-field" required>
              <option value={1}>{t('male')}</option>
              <option value={0}>{t('female')}</option>
            </select>
          </div>

          <!-- Chest Pain Type -->
          <div>
            <label class="label-text">{t('cp')}</label>
            <select bind:value={formData.cp} class="input-field" required>
              <option value={0}>{t('cpType0')}</option>
              <option value={1}>{t('cpType1')}</option>
              <option value={2}>{t('cpType2')}</option>
              <option value={3}>{t('cpType3')}</option>
            </select>
          </div>

          <!-- Resting Blood Pressure -->
          <div>
            <label class="label-text">{t('trestbps')}</label>
            <input
              type="number"
              bind:value={formData.trestbps}
              min="80"
              max="200"
              class="input-field"
              placeholder={t('trestbpsDesc')}
              required
            />
          </div>

          <!-- Cholesterol -->
          <div>
            <label class="label-text">{t('chol')}</label>
            <input
              type="number"
              bind:value={formData.chol}
              min="100"
              max="600"
              class="input-field"
              placeholder={t('cholDesc')}
              required
            />
          </div>

          <!-- Fasting Blood Sugar -->
          <div>
            <label class="label-text">{t('fbs')}</label>
            <select bind:value={formData.fbs} class="input-field" required>
              <option value={0}>{t('no')}</option>
              <option value={1}>{t('yes')}</option>
            </select>
            <p class="text-xs text-gray-500 mt-1">{t('fbsDesc')}</p>
          </div>

          <!-- Resting ECG -->
          <div>
            <label class="label-text">{t('restecg')}</label>
            <select bind:value={formData.restecg} class="input-field" required>
              <option value={0}>{t('restecgType0')}</option>
              <option value={1}>{t('restecgType1')}</option>
              <option value={2}>{t('restecgType2')}</option>
            </select>
          </div>

          <!-- Maximum Heart Rate -->
          <div>
            <label class="label-text">{t('thalach')}</label>
            <input
              type="number"
              bind:value={formData.thalach}
              min="60"
              max="220"
              class="input-field"
              placeholder={t('thalachDesc')}
              required
            />
          </div>

          <!-- Exercise Induced Angina -->
          <div>
            <label class="label-text">{t('exang')}</label>
            <select bind:value={formData.exang} class="input-field" required>
              <option value={0}>{t('no')}</option>
              <option value={1}>{t('yes')}</option>
            </select>
            <p class="text-xs text-gray-500 mt-1">{t('exangDesc')}</p>
          </div>

          <!-- ST Depression (Oldpeak) -->
          <div>
            <label class="label-text">{t('oldpeak')}</label>
            <input
              type="number"
              bind:value={formData.oldpeak}
              min="0"
              max="10"
              step="0.1"
              class="input-field"
              placeholder={t('oldpeakDesc')}
              required
            />
          </div>

          <!-- Slope -->
          <div>
            <label class="label-text">{t('slope')}</label>
            <select bind:value={formData.slope} class="input-field" required>
              <option value={0}>{t('slopeType0')}</option>
              <option value={1}>{t('slopeType1')}</option>
              <option value={2}>{t('slopeType2')}</option>
            </select>
          </div>

          <!-- Number of Major Vessels (CA) -->
          <div>
            <label class="label-text">{t('ca')}</label>
            <input
              type="number"
              bind:value={formData.ca}
              min="0"
              max="3"
              class="input-field"
              placeholder={t('caDesc')}
              required
            />
          </div>

          <!-- Thalassemia -->
          <div>
            <label class="label-text">{t('thal')}</label>
            <select bind:value={formData.thal} class="input-field" required>
              <option value={0}>{t('thalType0')}</option>
              <option value={1}>{t('thalType1')}</option>
              <option value={2}>{t('thalType2')}</option>
            </select>
          </div>
        </div>

        <!-- Action Buttons -->
        <div class="flex flex-col sm:flex-row gap-4 mt-8 justify-center">
          <button
            type="submit"
            disabled={calculating}
            class="btn-primary disabled:opacity-50 disabled:cursor-not-allowed flex items-center justify-center space-x-2 rtl:space-x-reverse"
          >
            {#if calculating}
              <svg class="animate-spin h-5 w-5 text-white" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              <span>جاري الحساب...</span>
            {:else}
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 7h6m0 10v-3m-3 3h.01M9 17h.01M9 14h.01M12 14h.01M15 11h.01M12 11h.01M9 11h.01M7 21h10a2 2 0 002-2V5a2 2 0 00-2-2H7a2 2 0 00-2 2v14a2 2 0 002 2z" />
              </svg>
              <span>{t('calculateRisk')}</span>
            {/if}
          </button>

          <button
            type="button"
            on:click={handleReset}
            class="btn-secondary"
          >
            <svg class="w-5 h-5 inline-block mr-2 rtl:ml-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
            </svg>
            {t('reset')}
          </button>
        </div>
      </form>
    </div>
  </div>
</section>