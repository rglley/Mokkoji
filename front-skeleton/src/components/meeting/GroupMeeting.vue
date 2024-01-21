<template>
  <section class="w-full h-full overflow-x-scroll overflow-hidden">
    <div class="w-full h-full flex flex-nowrap gap-2">
      <div
        v-for="participant in participants"
        :key="participant"
        class="w-1/2 h-1/2 flex-none bg-purple-200 rounded-xl p-3"
      >
        {{ participant }}
      </div>
    </div>
  </section>
  <!-- <OvVideo /> -->
</template>

<script setup>
import OvVideo from './OvVideo.vue'

let participants = ['조용운', '박철준', '이경배', '한재훈', '김지은', '한재훈', '한재훈']
let host = '이정민'
</script>

<style></style>
