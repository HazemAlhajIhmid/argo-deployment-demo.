import { writable } from 'svelte/store';

export type Language = 'ar' | 'en';

export const currentLanguage = writable<Language>('ar');
export const currentDirection = writable<'rtl' | 'ltr'>('rtl');

export function toggleLanguage() {
  currentLanguage.update(lang => {
    const newLang = lang === 'ar' ? 'en' : 'ar';
    currentDirection.set(newLang === 'ar' ? 'rtl' : 'ltr');
    return newLang;
  });
}
