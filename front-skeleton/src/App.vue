<template>
  <div>
    <header class="flex items-center justify-between px-4 lg:px-6 h-30">
      <TheHeader />
    </header>
    <Router-view/>
  </div>
</template>

<script setup>
import TheHeader from './components/common/TheHeader.vue';

</script>

<style scoped>

</style>