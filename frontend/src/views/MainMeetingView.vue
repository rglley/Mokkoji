<template>
  <MainMeeting @create-group-meeting="createGroupMeeting" />
</template>

<script setup>
import { onMounted } from 'vue'
import router from '../router'
import MainMeeting from '@/components/meeting/MainMeeting.vue'

const emit = defineEmits(['create-meeting'])

const createGroupMeeting = (payload) => {
  router.push({
    path: `/groupmeeting/${payload.sessionId}`
  })
}

onMounted(() => {
  emit('create-meeting')
})
</script>

<style></style>
