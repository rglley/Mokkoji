<template>
  <ov-video v-if="streamManager" :stream-manager="streamManager" />
</template>

<script setup>
import { ref } from 'vue'
import OvVideo from './OvVideo.vue'

const props = defineProps({
  streamManager: {
    type: Object
  }
})

const userName = ref('')
</script>

<style></style>
