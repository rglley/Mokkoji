<!-- 사용자 비디오 -->
<template>
  <video ref="videoElement" autoplay />
</template>

<script setup>
import { defineProps, ref, onMounted } from 'vue'

const props = defineProps({
  streamManager: {
    type: Object
  }
})

const videoElement = ref(null)

onMounted(() => {
  if (videoElement.value && props.streamManager) {
    props.streamManager.addVideoElement(videoElement.value)
  }
})
</script>
