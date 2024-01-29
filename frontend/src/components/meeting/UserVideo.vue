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

// 사용자 데이터 가져오기
const clientData = () => {
  const { clientData } = getConnectionData()
  userName.value = clientData
}

const getConnectionData = () => {
  const { connection } = props.streamManager.stream
  return JSON.parse(connection.data)
}
</script>

<style></style>
