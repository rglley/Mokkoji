<template>
  <Meeting />
</template>

<script setup>
import { onMounted } from 'vue'
import Meeting from '@/components/meeting/Meeting.vue'

const emit = defineEmits(['create-meeting'])

onMounted(() => {
  emit('create-meeting')
})
</script>

<style></style>
