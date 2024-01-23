<template>
  <!-- wrapper -->
  <div class="flex text-center">
    <!-- tab bar -->

    <div class="m-auto flex text-center border-gray-300 border-2 border-solid rounded-lg">
      <div
        class="h-12 w-36 cursor-pointer hover:bg-violet-100 flex-col flex content-center justify-center"
        @click="rollingClick"
      >
        롤링 페이퍼
      </div>
      <div class="h-12 border-[1.5px] border-solid border-gray-300"></div>
      <div
        class="h-12 w-36 cursor-pointer hover:bg-violet-100 flex-col flex content-center justify-center"
        @click="photoClick"
      >
        포토 모자이크
      </div>
    </div>
  </div>
  <!-- components -->
  <div class="mt-10" v-if="isRollingEdit"><RollingEdit /></div>
  <div class="mt-10" v-if="isPhotoEdit"><PhotoEdit /></div>
</template>

<script setup>
import { ref } from 'vue'
import RollingEdit from '@/components/myEdit/RollingEdit.vue'
import PhotoEdit from '@/components/myEdit/PhotoEdit.vue'

let isRollingEdit = ref(true)
let isPhotoEdit = ref(false)

let rollingClick = () => {
  isRollingEdit.value = true
  if (isRollingEdit.value) {
    isPhotoEdit.value = false
  }
  console.log(`롤링페이퍼: ${isRollingEdit.value} 포토 모자이크: ${isPhotoEdit.value}`)
}

let photoClick = () => {
  isPhotoEdit.value = true
  if (isPhotoEdit.value) {
    isRollingEdit.value = false
  }
  console.log(`롤링페이퍼: ${isRollingEdit.value} 포토 모자이크: ${isPhotoEdit.value}`)
}
</script>

<style scoped></style>
