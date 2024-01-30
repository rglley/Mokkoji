<template>
  <div v-if="streamManager && userName.includes(props.searchUserName)">{{ userName }}</div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const props = defineProps({
  streamManager: {
    type: Object
  },
  searchUserName: {
    type: String
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

onMounted(() => {
  clientData()
})
</script>

<style></style>
