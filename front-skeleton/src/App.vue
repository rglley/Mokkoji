<template>
  <main>
    <header class="flex items-center justify-between px-4 lg:px-6 h-30">
      <TheHeader />
    </header>

    <Router-view />
  </main>
</template>

<script setup>
import TheHeader from './components/common/TheHeader.vue'
</script>

<style scoped>
/* @import "../src/assets/scss/main.css"; */
</style>