<template>
  <div class="h-[22rem] w-72 mx-8 my-4 rounded-lg border-violet-400 border-4 border-solid bg-white">
    <p class="pl-2 pt-2 pb-2">{{ recollection.eventDay }}</p>
    <div class="relative">
      <img
        :src="`src/assets/recollection/recollection_random_${imgNo}.png`"
        alt="recollection-random"
        class="m-auto z-0"
        height="200"
        width="200"
      />
      <div class="absolute bottom-4 right-10 left-14">
        <img
          src="@/assets/mokkoji_logo_with_bg.png"
          alt="unregistered"
          height="170"
          width="170"
          class="z-10"
        />
      </div>
    </div>
    <div class="mt-2 text-center">
      <p class="text-lg mb-2">{{ recollection.eventType }}</p>
      <pre class="text-base whitespace-pre-wrap">{{ description }}</pre>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'
let props = defineProps(['recollection'])
let description = props.recollection.description
const { VITE_RECOLLECTION_FRAME_COUNT } = import.meta.env

let imgNo = computed(() => {
  let no = props.recollection.eventId % VITE_RECOLLECTION_FRAME_COUNT
  return no === 0 ? VITE_RECOLLECTION_FRAME_COUNT : no
})
</script>

<style></style>
