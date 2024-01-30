<template>
  <div class="fixed w-[10%] h-[8%] top-[77%] left-[34.5%]">
    <div
      id="gift-container"
      class="p-8 h-[100%] bg-purple-100 flex flex-col justify-center items-center rounded-xl"
    >
      <div v-if="isCamera" class="text-sm md:text-sm lg:text-md xl:text-base">카메라 ON</div>
      <div v-if="!isCamera" class="text-sm md:text-sm lg:text-md xl:text-base">카메라 OFF</div>
    </div>
  </div>
</template>

<script setup>
const props = defineProps({
  isCamera: {
    type: Boolean
  }
})
</script>
