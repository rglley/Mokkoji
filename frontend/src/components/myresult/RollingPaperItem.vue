<template>
  <div class="relative z-20">
    <div class="w-[100px] h-[100px] top-30 left-10">
      <p class="text-center text-[8px] mb-1">From {{ msg[0].name }}</p>
      <p class="text-[9px]">{{ msg[0].content }}</p>
    </div>
    <!-- <div class="absolute w-[100px] h-[100px]">
      <p class="text-center text-[8px] mb-1">From {{ msg[1].name }}</p>
      <p class="text-[9px]">{{ msg[1].content }}</p>
    </div>
    <div class="absolute w-[100px] h-[100px] mb-[300px] mr-[0px]">
      <p class="text-center text-[8px] mb-1">From {{ msg[2].name }}</p>
      <p class="text-[9px]">{{ msg[2].content }}</p>
    </div>
    <div class="absolute w-[100px] h-[100px] mt-[20px] mr-[300px]">
      <p class="text-center text-[8px] mb-1">From {{ msg[3].name }}</p>
      <p class="text-[9px]">{{ msg[3].content }}</p>
    </div>
    <div class="absolute w-[100px] h-[100px] mt-[20px] ml-[10px]">
      <p class="text-center text-[8px] mb-1">From {{ msg[4].name }}</p>
      <p class="text-[9px]">
        {{ msg[4].content }}
      </p>
    </div>
    <div class="absolute w-[100px] h-[100px] mt-[20px] ml-[310px]">
      <p class="text-center text-[8px] mb-1">From {{ msg[5].name }}</p>
      <p class="text-[9px]">{{ msg[5].content }}</p>
    </div>
    <div class="absolute w-[100px] h-[100px] mt-[340px] mr-[300px]">
      <p class="text-center text-[8px] mb-1">From {{ msg[6].name }}</p>
      <p class="text-[9px]">{{ msg[6].content }}</p>
    </div>
    <div class="absolute w-[100px] h-[100px] mt-[340px]">
      <p class="text-center text-[8px] mb-1">From {{ msg[7].name }}</p>
      <p class="text-[9px]">{{ msg[7].content }}</p>
    </div>
    <div class="absolute w-[100px] h-[100px] mt-[340px] ml-[310px]">
      <p class="text-center text-[8px] mb-1">From {{ msg[8].name }}</p>
      <p class="text-[9px]">{{ msg[8].content }}</p>
    </div> -->
  </div>
</template>
<script setup>
const props = defineProps(['msg'])
</script>
