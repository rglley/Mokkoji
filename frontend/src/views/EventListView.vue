<template>
  <div class="text-3xl flex px-2 justify-center">
    member31님의 모꼬지 <IconBouquet /> <IconBou />
  </div>

  <div class="text-2xl px-2 flex">
    기억
    <p class="text-sm px-2 py-2.5">결과물 편집을 마치면 추억으로 간직할 수 있어요</p>
    <button @click="openModalOne"><IconQuestionMarkTwo /></button>
    <transition name="modal-fade">
      <div
        v-if="isOpenOne"
        class="fixed bottom-56 left-96 w-full h-full flex content-center items-center z-20"
      >
        <div class="bg-white relative px-28 py-36 border-black border-2 border-solid rounded-lg">
          <div>
            <button
              @click="closeModalOne"
              class="text-sm absolute px-2 py-1 bottom-64 right-2 cursor-pointer bg-violet-100 rounded-lg"
            >
              확인
            </button>
            <div>
              <img
                src="@/assets/photomosaic_ex.png"
                class="absolute bottom-24 right-12 w-32 h-42"
              />
            </div>
            <p class="absolute text-base bottom-16 right-14">포토 모자이크란?</p>
            <p class="absolute text-sm bottom-10 right-4">여러 장의 사진을 색상에 맞게 배열해</p>
            <p class="absolute text-sm bottom-4 right-1">한 장의 큰 이미지를 표현하는 것입니다.</p>
          </div>
        </div>
      </div>
    </transition>
  </div>
  <div class="h-96 border-violet-50 border-0 rounded-lg border-solid bg-violet-100 flex">
    <MemoryList v-for="memory in MemoryData" :key="memory.eventId" :memory="memory" />
  </div>
  <PageNavigation />
  <div class="text-2xl flex px-2">
    추억
    <p class="text-sm px-2 py-2.5">행복했던 그날로 돌아가 볼가요?</p>
    <button @click="openModalTwo"><IconQuestionMarkTwo /></button>
    <transition name="modal-fade">
      <div
        v-if="isOpenTwo"
        class="fixed top-36 left-72 w-full h-full flex content-center items-center z-20"
      >
        <div class="bg-white relative px-28 py-36 border-black border-2 border-solid rounded-lg">
          <div>
            <button
              @click="closeModalTwo"
              class="text-sm absolute px-2 py-1 bottom-64 right-2 cursor-pointer bg-violet-100 rounded-lg"
            >
              확인
            </button>
            <div>
              <img
                src="@/assets/recollection_ex.png"
                class="absolute bottom-24 right-12 w-32 h-42"
              />
            </div>
            <p class="absolute text-base bottom-16 right-24">TIP!</p>
            <p class="absolute text-sm bottom-10 right-6">대표 이미지를 클릭하면 완성된</p>
            <p class="absolute text-sm bottom-4 right-12">결과물로 이동합니다.</p>
          </div>
        </div>
      </div>
    </transition>
  </div>
  <div class="h-96 border-slate-950 border-2 border-solid bg-violet-100 flex">
    <RecollectionList
      v-for="recollection in RecollectionData"
      :key="recollection.eventId"
      :recollection="recollection"
    />
  </div>
  <PageNavigation />
</template>

<script setup>
import IconBouquet from '@/icons/IconBouquet.vue'
import IconBou from '@/icons/IconBou.vue'
import IconQuestionMarkTwo from '@/icons/IconQuestionMark.vue'
import MemoryList from '@/components/myevent/MemoryList.vue'
import MemoryData from '@/temp/memory.json'
import RecollectionData from '@/temp/recollection.json'
import PageNavigation from '@/components/myevent/PageNavigation.vue'
import RecollectionList from '@/components/myevent/RecollectionList.vue'

import { ref } from 'vue'
let isOpenOne = ref(false)
let isOpenTwo = ref(false)
let closeModalOne = () => {
  isOpenOne.value = false
}
let openModalOne = () => {
  isOpenOne.value = !isOpenOne.value
}

let closeModalTwo = () => {
  isOpenTwo.value = false
}

let openModalTwo = () => {
  isOpenTwo.value = !isOpenTwo.value
}
</script>
<style scoped>
.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.3s ease-in-out;
}

.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}
</style>
