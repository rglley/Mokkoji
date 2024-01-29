<template>
  <div v-if="streamManager">{{ clientData() }}</div>
</template>

<script setup>
import { defineProps } from 'vue'

const props = defineProps({
  streamManager: {
    type: Object
  }
})

// 사용자 데이터 가져오기
const clientData = () => {
  const { clientData } = getConnectionData()
  return clientData
}

const getConnectionData = () => {
  const { connection } = props.streamManager.stream
  console.log(connection)
  return JSON.parse(connection.data)
}
</script>

<style></style>
