<template>
  <div class="overflow-x-auto w-full">
    <div class="flex w-[100%]">
      <div class="w-[100%]">
        <div class="h-[200px] bg-slate-100">
          <div class="h-[100px]" />
          <div class="h-[100px] text-[50px] flex justify-center items-center">
            <router-link to="/rollingpaper"><p class="mr-10">롤링페이퍼</p></router-link>
            <router-link to="/photomosaic"><p class="ml-10">포토모자이크</p></router-link>
          </div>
        </div>
        <div class="h-[1100px] bg-slate-200 pt-10">
          <div class="flex justify-center items-center">
            <div class="w-[1000px] h-[1000px] border-2 border-black"></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import RecollectionList from '@/components/myevent/RecollectionList.vue'
import PhotoCard from '@/temp/result/photocard.json'
import IconPeople from '@/icons/result/IconPeople.vue'
import IconPhoto from '@/icons/result/IconPhoto.vue'
import IconLetter from '@/icons/result/IconLetter.vue'
import IconVideo from '@/icons/result/IconVideo.vue'
import IconAudio from '@/icons/result/IconAudio.vue'
import IconMailBox from '@/icons/result/IconMailBox.vue'
import IconLightBulb from '@/icons/result/IconLightBulb.vue'
import IconRightArrow from '@/icons/result/IconRightArrow.vue'
import IconLeftArrow from '@/icons/result/IconLeftArrow.vue'

const photocard = PhotoCard
const name = '이진영'
const design = 'basic'
const color = 'rainbow'
const isOpenShare = ref(false)
const isOpenDownload = ref(false)
const isRollingEdit = ref(true)
const isPhotoEdit = ref(false)

const photomosaic = ref(null)

const openDownLoadList = () => {
  isOpenDownload.value = !isOpenDownload.value
}

const openShareList = () => {
  isOpenShare.value = !isOpenShare.value
}

const rollingClick = () => {
  isRollingEdit.value = true
  if (isRollingEdit.value) {
    isPhotoEdit.value = false
  }
}

const photoClick = () => {
  isPhotoEdit.value = true
  if (isPhotoEdit.value) {
    isRollingEdit.value = false
  }
}

const optDownload = (value) => {
  isOpenDownload.value = false

  switch (value) {
    case '모두':
      break
    case '롤링페이퍼':
      break
    case '대표이미지':
      break
    case '포토모자이크':
      break
  }
}

const optShare = (value) => {
  isOpenShare.value = false
  switch (value) {
    case '클립보드':
      break
    case '카카오톡':
      break
  }
}
</script>

<style></style>
