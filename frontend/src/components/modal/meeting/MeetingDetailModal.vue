<template>
  <div class="fixed w-[23%] h-[30%] top-[59%] left-[16%]">
    <div
      id="gift-container"
      class="p-8 h-[100%] bg-black opacity-70 flex flex-col justify-center items-start space-y-2 rounded-xl"
    >
      <div class="text-white text-md md:text-md lg:text-base xl:text-lg">{{}}님의 회의 정보</div>
      <div class="text-white text-sm md:text-sm lg:text-md xl:text-base">회의 주소 |</div>
      <div class="text-white text-sm md:text-sm lg:text-md xl:text-base">
        회의ID | {{ session.sessionId }}
      </div>
      <div class="text-white text-sm md:text-sm lg:text-md xl:text-base">
        주최자 | {{ session.host }}
      </div>
      <div class="text-white text-sm md:text-sm lg:text-md xl:text-base">날짜 | {{ today }}</div>
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
