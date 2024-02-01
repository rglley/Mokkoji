<template>
  <GroupMeeting :session="mainSession" />
</template>

<script setup>
import { ref, onMounted } from 'vue'
import GroupMeeting from '@/components/meeting/GroupMeeting.vue'

const props = defineProps({
  mainSessionId: {
    type: String
  }
})

const emit = defineEmits(['create-meeting'])

const mainSession = ref({ sessionId: props.mainSessionId })

onMounted(() => {
  emit('create-meeting')
})
</script>

<style></style>
