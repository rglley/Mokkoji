<template>
  <GroupMeeting />
  <Meeting />
</template>

<script setup>
import { onMounted } from 'vue'
import Meeting from '@/components/meeting/Meeting.vue'
import GroupMeeting from '@/components/meeting/GroupMeeting.vue'

const emit = defineEmits(['create-meeting']['create-group-meeting'])

onMounted(() => {
  emit('create-meeting')
})
</script>

<style></style>
