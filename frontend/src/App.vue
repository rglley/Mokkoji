<template>
  <main>
    <TheHeader />
    <RouterView />
    <TheFooter />
  </main>
</template>

<script setup>
import TheHeader from "./components/common/TheHeader.vue";
import TheFooter from "./components/common/TheFooter.vue";
import { RouterView } from "vue-router";
</script>

<style scoped></style>
