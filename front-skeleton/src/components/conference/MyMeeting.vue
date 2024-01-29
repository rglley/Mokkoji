<template>
  <section>
    <div class="flex flex-row w-auto h-">
      <div
        v-for="participant in participants"
        :key="participant"
        class="mb-2 w-1/5 h-36 bg-purple-300 rounded-lg"
      >
        {{ participant }}
      </div>
    </div>
    <div id="host" class="h-80 bg-purple-300 rounded-lg">호스트</div>
  </section>
  <!-- <OvVideo /> -->
</template>

<script setup>
// import OvVideo from './OvVideo.vue'
let participants = ['조용운', '박철준', '이경배', '한재훈', '김지은', '한재훈', '한재훈']
// let host = '이정민'
</script>


  <style></style>