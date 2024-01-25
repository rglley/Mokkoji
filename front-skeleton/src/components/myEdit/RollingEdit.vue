<template>
  <!--Select box-->
  <div class="flex mb-2">
    <div class="m-auto">
      <div class="relative">
        <div class="flex">
          <button
            class="flex rounded-lg border-gray-200 border-2 border-solid py-3 pl-3 w-48 h-12 relative"
            @click="openList"
          >
            <div id="selected">{{ selected }}</div>
            <p class="ml-auto" v-if="isOpen"><IconUpArrow /></p>
            <p class="ml-auto" v-else><IconDownArrow /></p>
          </button>
          <button
            class="border-4 border-solid border-violet-500 hover:bg-violet-200 rounded-lg px-3 py-1 my-1 ml-4"
          >
            편집 완료
          </button>
        </div>
        <div v-if="isOpen" class="absolute left-0 mt-2 z-30">
          <ul class="w-48 mt-1 border-gray-200 bg-violet-300 border-2 border-solid rounded-lg">
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="pr-16 w-44" @click="selectOpt('기본')">기본</button>
            </li>
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="pr-16 w-44" @click="selectOpt('결혼식')">결혼식</button>
            </li>
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="pr-16 w-44" @click="selectOpt('졸업식')">졸업식</button>
            </li>
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="pr-16 w-44" @click="selectOpt('명절')">명절</button>
            </li>
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="pr-16 w-44" @click="selectOpt('돌잔치')">돌잔치</button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
  <!--rolling paper-->
  <div>
    <img
      :src="`src/assets/rolling_template/${design}.png`"
      :alt="`template_${design}`"
      class="m-auto z-20 relative0"
    />
  </div>
  <RollingLetter />
</template>
<script setup>
import { ref } from 'vue'
import IconDownArrow from '@/icons/IconDownArrow.vue'
import IconUpArrow from '@/icons/IconUpArrow.vue'
import RollingLetter from '@/components/myedit/RollingLetter.vue'

let isOpen = ref(false)
let selected = ref('템플릿 디자인 선택')
let design = ref('basic')

let openList = () => {
  isOpen.value = !isOpen.value
}

let selectOpt = (value) => {
  selected.value = value
  isOpen.value = false

  switch (value) {
    case '기본':
      design.value = 'basic'
      break
    case '결혼식':
      design.value = 'wedding'
      break
    case '졸업식':
      design.value = 'school'
      break
    case '명절':
      design.value = 'lunar'
      break
    case '돌잔치':
      design.value = 'baby'
      break
  }
}
</script>
<style scoped></style>
