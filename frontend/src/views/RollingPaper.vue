<template>
  <div class="overflow-x-auto w-full">
    <div class="flex w-[100%]">
      <div class="w-[100%]">
        <div class="h-[200px] bg-violet-100">
          <div class="h-[100px]" />
          <div class="h-[100px] text-[50px] flex justify-center items-center">
            <p class="mr-10">롤링페이퍼</p>
            <router-link to="/photomosaic"><p class="ml-10">포토모자이크</p></router-link>
          </div>
        </div>
        <div class="h-[1100px] bg-violet-200 pt-10">
          <div class="flex justify-center items-center">
            <p class="absolute z-20 text-[50px] pb-[800px]">{{ name }}님의 추억조각</p>
            <img
              :src="`src/assets/rollingtemplate/${design}.png`"
              :alt="`template_${design}`"
              class="z-10"
            />
            <img
              :src="`src/assets/rollingnote/${color}.png`"
              :alt="`template_${color}`"
              class="absolute z-20"
              width="700px"
              heigt="1000px"
            />
            <div class="absolute z-20 text-sm w-[150px] h-[150px] mb-[450px] mr-[460px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">진영아 결혼 축하해, 앞으로 꽃길만 걸어~!!~!!!!!!</p>
            </div>
            <div class="absolute z-20 text-sm w-[150px] h-[150px] mb-[450px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">진영아 결혼 축하해, 앞으로 꽃길만 걸어~!!~!!!!!!</p>
            </div>
            <div class="absolute z-20 text-sm w-[150px] h-[150px] mb-[450px] ml-[470px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">진영아 결혼 축하해, 앞으로 꽃길만 걸어~!!~!!!!!!</p>
            </div>
            <div class="absolute z-20 text-sm w-[150px] h-[150px] mt-[30px] mr-[460px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">진영아 결혼 축하해, 앞으로 꽃길만 걸어~!!~!!!!!!</p>
            </div>
            <div class="absolute z-20 w-[150px] mt-[30px] h-[150px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">
                진영아 결혼 축하해, 너랑 처음 만난 게 중학생인데 벌써 결혼이라니! 진짜 세월 빠르다
                항상 너랑 친구여서 즐겁고 행복했던 기억밖에 없네 남편 만나서 나랑 보낼 시간이
                줄어들텐데
              </p>
            </div>
            <div class="absolute z-20 text-sm w-[150px] mt-[30px] h-[150px] ml-[470px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">진영아 결혼 축하해, 앞으로 꽃길만 걸어~!!~!!!!!!</p>
            </div>
            <div class="absolute z-20 text-sm w-[150px] h-[150px] mt-[510px] mr-[460px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">진영아 결혼 축하해, 앞으로 꽃길만 걸어~!!~!!!!!!</p>
            </div>
            <div class="absolute z-20 text-sm w-[150px] h-[150px] mt-[510px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">진영아 결혼 축하해, 앞으로 꽃길만 걸어~!!~!!!!!!</p>
            </div>
            <div class="absolute z-20 text-sm w-[150px] h-[150px] mt-[510px] ml-[470px]">
              <p class="text-center text-xs mb-1">From 이진</p>
              <p class="text-[13px]">진영아 결혼 축하해, 앞으로 꽃길만 걸어~!!~!!!!!!</p>
            </div>
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
import RollingPaperData from '@/temp/rollingpaper.json'

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
