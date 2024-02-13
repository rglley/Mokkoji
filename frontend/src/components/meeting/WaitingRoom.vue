<template>
  <img src="@/assets/logo/mokkoji_logo.png" alt="" class="fixed h-[80vh] top-[10%] left-[10%]" />
  <div
    class="fixed h-full flex flex-col justify-center items-center right-[8%] text-r-md font-bold"
  >
    <h1 class="text-[8lvh]">모꼬지에 오신걸 환영합니다!</h1>
    <div class="">회의에서 사용할 닉네임을 입력해주세요!</div>
    <input type="text" name="" id="" />
    <button>행사 참가</button>
  </div>
</template>

<script setup>
import { onMounted, onBeforeMount } from 'vue'
import { useRouter } from 'vue-router'

const emit = defineEmits(['waiting-room']['leave-meeting'])

const router = useRouter()

const goToMeeting = () => {
  router.push('/meetings')
}

onMounted(() => {
  emit('waiting-room')
})

onBeforeMount(() => {
  emit('leave-meeting')
})
</script>

<style></style>
