<!-- 사용자 비디오 -->
<template>
  <video ref="videoElement" autoplay />
</template>

<script setup>
import { ref, onMounted, defineProps } from 'vue'
import html2canvas from 'html2canvas'

const props = defineProps({
  streamManager: {
    type: Object
  }
})

const captureImg = () => {
  const target = videoElement.value
  if (!target) {
    return alert('결과 저장 실패')
  }
  target.focus()
  html2canvas(target).then((canvas) => {
    onSaveAs(canvas.toDataURL('image/png'), 'test.png')
  })
}

const onSaveAs = (uri, filename) => {
  const link = document.createElement('a')
  document.body.appendChild(link)
  link.href = uri
  link.download = filename
  link.click()
  document.body.removeChild(link)
}

const videoElement = ref(null)

onMounted(() => {
  if (videoElement.value && props.streamManager) {
    props.streamManager.addVideoElement(videoElement.value)
  }
})
</script>
