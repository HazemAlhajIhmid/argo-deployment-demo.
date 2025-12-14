<script lang="ts">
  import { currentDirection } from './stores/language';
  import Header from './components/Header.svelte';
  import Hero from './components/Hero.svelte';
  import RiskCalculator from './components/RiskCalculator.svelte';
  import Results from './components/Results.svelte';
  import ResearchInfo from './components/ResearchInfo.svelte';
  import Footer from './components/Footer.svelte';
  import './app.css';

  let direction: 'rtl' | 'ltr';
  currentDirection.subscribe(value => direction = value);
</script>

<div dir={direction} class="min-h-screen">
  <Header />
  <main>
    <Hero />
    <RiskCalculator />
    <Results />
    <ResearchInfo />
  </main>
  <Footer />
</div>
