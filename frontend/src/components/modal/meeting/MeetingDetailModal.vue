<template>
  <div class="fixed w-[26%] h-[30%] bottom-[11%] left-[13%]">
    <div
      id="gift-container"
      class="px-[2vw] h-[100%] bg-black flex flex-col justify-center items-start space-y-[1vh] rounded-r-lg"
    >
      <div class="flex">
        <div class="text-white text-r-md">{{}}님의 회의 정보</div>
        <button></button>
      </div>
      <div class="text-white text-r-sm">
        회의 주소 | http://localhost:5173/mainmeeting/{{ session.sessionId }}
      </div>
      <div class="text-white text-r-sm">회의ID | {{ session.sessionId }}</div>
      <div class="text-white text-r-sm">주최자 | {{ session.host }}</div>
      <div class="text-white text-r-sm">날짜 | {{ today }}</div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  session: {
    type: Object
  }
})

const date = new Date()
const today = date.toLocaleDateString()
</script>

<style></style>
