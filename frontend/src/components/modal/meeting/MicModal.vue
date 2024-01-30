<template>
  <div class="fixed w-[10%] h-[8%] top-[77%] left-[29.5%]">
    <div
      id="gift-container"
      class="p-8 h-[100%] bg-purple-100 flex flex-col justify-center items-center rounded-xl"
    >
      <div v-if="isMic" class="text-sm md:text-sm lg:text-md xl:text-base">마이크 ON</div>
      <div v-else class="text-sm md:text-sm lg:text-md xl:text-base">마이크 OFF</div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  isMic: {
    type: Boolean
  }
})
</script>
