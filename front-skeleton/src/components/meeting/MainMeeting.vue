<template>
  <section class="h-full flex flex-col">
    <div class="pb-2 basis-1/5 flex flex-row whitespace-nowrap overflow-x-scroll overflow-hidden">
      <div
        v-for="participant in participants"
        :key="participant"
        class="flex-none basis-1/4 bg-purple-300 rounded-xl space-x-3"
      >
        {{ participant }}
      </div>
    </div>
    <div id="host" class="basis-4/5 bg-purple-300 rounded-xl"></div>
  </section>
  <!-- <OvVideo /> -->
</template>

<script setup>
import OvVideo from './OvVideo.vue'

let participants = ['조용운', '박철준', '이경배', '한재훈', '김지은', '한재훈', '한재훈']
let host = '이정민'
</script>

<style></style>
