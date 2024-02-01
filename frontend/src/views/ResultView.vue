<template>
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
  <div class="mt-10 flex" v-if="isRollingEdit">
    <!-- photo card and event info -->
    <div class="w-1/4">
      <RecollectionList :key="photocard.eventId" :recollection="photocard" />
      <div class="ml-10">
        <p class="flex"><IconPeople />{{ photocard.participants }} 명</p>
        <p class="flex"><IconLetter />{{ photocard.letters }} 개</p>
        <p class="flex"><IconPhoto />{{ photocard.photos }} 장</p>
      </div>
    </div>
    <!-- rolling paper with pagination -->
    <div
      class="min-w-[710px] min-h-[1050px] mx-10"
      :style="{ backgroundImage: `url('src/assets/rolling_template/${design}.png')` }"
    >
      <RollingLetter />
    </div>

    <!-- button and messages -->
    <div class="w-1/4">
      <div class="flex mb-1 relative">
        <button
          class="border-violet-200 border-4 border-solid rounded-lg mr-5 hover:bg-violet-200 h-12 w-40"
          @click="openDownLoadList"
        >
          다운로드
        </button>
        <button
          class="border-violet-200 border-4 border-solid rounded-lg mr-5 hover:bg-violet-200 h-12 w-40"
          @click="openShareList"
        >
          공유하기
        </button>
      </div>
      <div class="flex">
        <div v-if="isOpenDownload" class="mr-5">
          <ul class="w-40 mt-1 border-gray-200 bg-violet-300 border-2 border-solid rounded-lg">
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="w-44" @click="optDownload('모두')">모두 다운로드</button>
            </li>
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="w-44" @click="optDownload('롤링페이퍼')">롤링페이퍼 다운로드</button>
            </li>
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="w-44" @click="optDownload('대표이미지')">대표 이미지 다운로드</button>
            </li>
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="w-44" @click="optDownload('포토모자이크')">
                포토모자이크 다운로드
              </button>
            </li>
          </ul>
        </div>
        <div v-else class="mr-[180px]" />

        <div v-if="isOpenShare" class="mr-5">
          <ul class="w-40 mt-1 border-gray-200 bg-violet-300 border-2 border-solid rounded-lg">
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="w-44" @click="optShare('클립보드')">클립보드 복사</button>
            </li>
            <li class="h-10 hover:bg-violet-100 rounded-lg flex z-40">
              <button class="w-44" @click="optShare('카카오톡')">카카오톡으로 공유하기</button>
            </li>
          </ul>
        </div>
      </div>
      <div class="mt-10">
        <p class="flex mb-2">
          <IconAudio /> 클릭하면 저장된 음성메시지가 재생돼요 (재클릭 시 정지)
        </p>
        <p class="flex mb-2"><IconVideo /> 클릭하면 저장된 영상메시지가 재생돼요 (플레이어 오픈)</p>
        <p class="flex"><IconLightBulb /> 메시지를 클릭하면 하나씩 더 크게 볼 수 있어요.</p>
      </div>
      <div class="mt-24">
        <p class="flex"><IconMailBox /> 총 6개의 메세지가 기록 됐어요.</p>
        <p class="ml-5">텍스트 메세지 : 3개</p>
        <p class="ml-5">음성 메세지 : 2개</p>
        <p class="ml-5">영상 메세지 : 2개</p>
      </div>
    </div>
  </div>

  <div class="mt-10" v-if="isPhotoEdit"></div>
</template>

<script setup>
import RecollectionList from '@/components/myevent/RecollectionList.vue'
import PhotoCard from '@/temp/result/photocard.json'
import IconPeople from '@/icons/result/IconPeople.vue'
import IconPhoto from '@/icons/result/IconPhoto.vue'
import IconLetter from '@/icons/result/IconLetter.vue'
import IconVideo from '@/icons/result/IconVideo.vue'
import IconAudio from '@/icons/result/IconAudio.vue'
import IconMailBox from '@/icons/result/IconMailBox.vue'
import IconLightBulb from '@/icons/result/IconLightBulb.vue'
import { ref } from 'vue'

const photocard = PhotoCard
const design = 'basic'
const isOpenShare = ref(false)
const isOpenDownload = ref(false)
const isRollingEdit = ref(true)
const isPhotoEdit = ref(false)

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

<style scoped></style>
