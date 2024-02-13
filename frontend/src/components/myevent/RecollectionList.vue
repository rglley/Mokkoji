<template>
  <div class="h-[22rem] w-72 mx-8 my-4 rounded-xl bg-white border-2 border-slate-300">
    <p class="pl-2 pt-2 pb-2 text-black flex">
      <strong class="effect-pink-recollection">{{ recollection.date }}</strong>
      <button class="ml-40"></button>
    </p>
    <div class="bg-custom">
      <div class="flex justify-center items-center">
        <a href="/resultPage">
          <img :src="mainImage" alt="unregistered" height="180" width="180" />
        </a>
      </div>
      <p class="text-lg mt-2 text-center">
        <strong class="effect-blank-recollection">{{ recollection.name }}</strong>
      </p>
    </div>
    <div class="mt-5 text-center text-black">
      <pre
        class="text-base whitespace-pre-wrap w-[284px] h-[72px] custom-border flex justify-center items-center"
      >
        {{ description }} <br>
        {{ participantCount }} 명이 {{ messageCount }} 개의 메시지를 보내줬어요.</pre>
    </div>
  </div>
</template>

<script setup>
import { computed, onMounted, ref } from 'vue'
import { useResultIDStore } from '@/stores/result'
import IconModify from '@/icons/result/IconModify.vue'
import axiosJwt from '@/services/api'
const props = defineProps(['recollection'])

const resultIDStore = useResultIDStore()

const description = props.recollection.content
const { VITE_RECOLLECTION_FRAME_COUNT } = import.meta.env

const backgroundTemplate = ref('')
const postitTemplate = ref('')
const messageList = ref([])
const participantCount = ref(0)
const messageCount = ref(0)
const photomosaic = ref('')

//액자, 미정
let imgNo = computed(() => {
  let no = props.recollection.eventId % VITE_RECOLLECTION_FRAME_COUNT
  return no === 0 ? VITE_RECOLLECTION_FRAME_COUNT : no
})

onMounted(() => {
  axiosJwt
    .get('/results/recollection/' + resultIDStore.getID)
    .then((res) => {
      backgroundTemplate.value = res.data.backgroundTemplate
      postitTemplate.value = res.data.postitTemplate
      messageList.value = res.data.messageList
      participantCount.value = res.data.participantCount
      messageCount.value = res.data.messageCount
      photomosaic.value = res.data.photomosaic
    })
    .catch((err) => console.error(err))
})
</script>

<style>
.effect-pink-recollection {
  box-shadow: inset 0 -5px 0 #fcd8f0;
  color: black;
}

.effect-blacnk-recollection {
  box-shadow: inset 0 -2px 0 #000000;
  color: black;
}

.custom-border {
  border-top: solid 2px;
  border-color: #ffffff;
}

.bg-custom {
  background-image: linear-gradient(rgba(255, 255, 255, 0.4), rgb(255, 255, 255)),
    url('src/assets/eventlist/recollection_background_2.png');
  background-color: white;
}
</style>
