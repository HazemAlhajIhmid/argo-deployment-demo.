<script lang="ts">
  import Header from '$lib/components/Header.svelte';
  import Hero from '$lib/components/Hero.svelte';
  import RiskCalculator from '$lib/components/RiskCalculator.svelte';
  import About from '$lib/components/About.svelte';
  import ModelComparison from '$lib/components/ModelComparison.svelte';
  import Footer from '$lib/components/Footer.svelte';
</script>

<div class="min-h-screen flex flex-col">
  <Header />
  <main class="flex-grow">
    <Hero />
    <RiskCalculator />
    <About />
    <ModelComparison />
  </main>
  <Footer />
</div>
