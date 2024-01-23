<template>
  <main>
    <header class="flex min-w-max">
      <TheHeader />
    </header>

    <Router-view />
    <TheFooter />
  </main>
</template>

<script setup>
import TheHeader from './components/common/TheHeader.vue'
</script>

<style></style>
