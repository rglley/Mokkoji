<template>
  <div class="h-[22rem] w-72 mx-8 my-4 rounded-xl bg-white border-2 border-[#ff81c8]">
    <p class="pl-2 pt-2 pb-2 text-black">
      <strong class="effect-purple">{{ recollection.eventDay }}</strong>
    </p>

    <!--액자, 미정-->
    <!-- <img
        :src="`src/assets/recollection/recollection_random_${imgNo}.png`"
        alt="recollection-random"
        class="m-auto z-0"
        height="200"
        width="200"
      /> -->
    <div class="flex justify-center items-center">
      <a href="/rollingpaper">
        <img
          src="@/assets/logo/mokkoji_logo_with_bg.png"
          alt="unregistered"
          height="180"
          width="180"
        />
      </a>
    </div>
    <div class="mt-5 text-center text-black">
      <p class="text-lg mb-2">
        <strong class="effect-purple">{{ recollection.eventType }}</strong>
      </p>
      <pre
        class="text-base whitespace-pre-wrap w-[284px] h-[72px] custom-border flex justify-center items-center"
        >{{ description }}</pre
      >
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps(['recollection'])

const description = props.recollection.description
const { VITE_RECOLLECTION_FRAME_COUNT } = import.meta.env

//액자, 미정
let imgNo = computed(() => {
  let no = props.recollection.eventId % VITE_RECOLLECTION_FRAME_COUNT
  return no === 0 ? VITE_RECOLLECTION_FRAME_COUNT : no
})
</script>

<style>
.effect-purple {
  box-shadow: inset 0 -5px 0 #f6d8fd;
  color: black;
}

.effect-pink {
  box-shadow: inset 0 -5px 0 #fea7d9;
  color: black;
}

.custom-border {
  border-top: solid 2px;
  border-color: #ff81c8;
}
</style>
