<template>
  <img src="@/assets/logo/mokkoji_logo.png" alt="" class="fixed h-[80vh] top-[10%] left-[10%]" />
  <div class="fixed top-[40%] text-r-md font-bold">회의에서 사용할 닉네임을 입력해주세요!</div>
</template>

<script setup>
import { onMounted, onBeforeMount } from 'vue'

const emit = defineEmits(['waiting-room']['leave-meeting'])

onMounted(() => {
  emit('waiting-room')
})

onBeforeMount(() => {
  emit('leave-meeting')
})
</script>

<style></style>
