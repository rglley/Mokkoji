<template>
  <div
    v-for="chatMessage in props.chatLog"
    :key="chatMessage"
    class="bg-white w-[90%] min-h-16 rounded-lg text-base flex justify-center items-center font-semibold"
  >
    {{ chatMessage.sender + ' : ' + chatMessage.content }}
  </div>
</template>

<script setup>
const props = defineProps({
  chatLog: {
    type: Array
  }
})

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
