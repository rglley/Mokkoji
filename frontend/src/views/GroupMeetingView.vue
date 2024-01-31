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

const mainSession = ref({ sessionId: props.mainSessionId })

const emit = defineEmits(['create-meeting'])

onMounted(() => {
  emit('create-meeting')
})
</script>

<style></style>
