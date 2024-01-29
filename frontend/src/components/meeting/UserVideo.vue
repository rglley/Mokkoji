<template>
  <ov-video v-if="streamManager" :stream-manager="streamManager" />
</template>

<script setup>
import { defineProps } from 'vue'
import OvVideo from './OvVideo.vue'

const props = defineProps({
  streamManager: {
    type: Object
  }
})
</script>

<style></style>
