<template>
  <section class="w-full h-full flex flex-col">
    <div class="mb-2 pb-2 basis-2/6 flex whitespace-nowrap overflow-x-scroll overflow-hidden gap-2">
      <div
        v-for="participant in participants"
        :key="participant"
        class="basis-1/4 bg-purple-200 rounded-xl flex-none p-3"
      >
        {{ participant }}
      </div>
    </div>
    <div id="host" class="basis-4/5 bg-purple-200 rounded-xl"></div>
  </section>
  <!-- <OvVideo /> -->
</template>

<script setup>
import OvVideo from './OvVideo.vue'

let participants = ['조용운', '박철준', '이경배', '한재훈', '김지은', '한재훈', '한재훈']
let host = '이정민'
</script>

<style></style>
