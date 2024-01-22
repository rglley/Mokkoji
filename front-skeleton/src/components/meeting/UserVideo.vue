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

const clientData = () => {
  const { clientData } = getConnectionData()
  return clientData
}

const getConnectionData = () => {
  const { connection } = props.streamManager.stream
  return JSON.parse(connection.data)
}
</script>

<style></style>
