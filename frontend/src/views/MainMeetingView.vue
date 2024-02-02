<template>
  <MainMeeting @create-group-meeting="createGroupMeeting" />
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'
import MainMeeting from '@/components/meeting/MainMeeting.vue'

const emit = defineEmits(['create-meeting'])

const router = useRouter()

const createGroupMeeting = (sessions) => {
  console.log(sessions)
  router.push({
    path: `/groupmeeting/${sessions.sessionId}/${sessions.groupNumber}`
  })
}

onMounted(() => {
  emit('create-meeting')
})
</script>

<style></style>
