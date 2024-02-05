<template>
  <!-- 섹션 1/6 편집할 대상 선택 -->

  <div class="bg-violet-50 h-[600px] justify-center items-center" ref="top">
    <div class="text-[30px] justify-center items-center flex pb-10 pt-20">
      <strong
        ><p class="effect-black flex">
          <IconFlowersColoredTwo />편집할 대상을 선택해주세요 <IconFlowersColoredTwo />
        </p>
      </strong>
    </div>
    <div class="text-[50px] flex justify-center items-center pt-10">
      <div
        class="w-[450px] rounded-3xl border-solid border-[#ffbbd2] p-10 shadow-lg shadow-[#ffbbd2] hover:cursor-pointer flex flex-col justify-center items-center"
        id="btn"
        @click="scrollToRollingPaper"
      >
        <p class="mb-[1vh]"><IconLoveLetterColored /></p>
        <button>롤링페이퍼 편집</button>
      </div>
      <div class="mx-16"></div>
      <div
        class="w-[450px] rounded-3xl border-solid border-[#ba44de] p-10 shadow-lg shadow-[#b95fd4] hover:cursor-pointer flex flex-col justify-center items-center"
        id="btn"
        @click="scrollToPhotoMosaic"
      >
        <p class="mb-[1vh]"><IconGalleryColored /></p>
        <button>포토모자이크 편집</button>
      </div>
    </div>
  </div>
  <!-- 섹션 2/6 모꼬지 정보, 내려가기 -->
  <div class="bg-[#ffffff] h-[500px] pt-5">
    <div class="justify-center flex" @click="scrollToRollingPaper"><IconDownArrowGray /></div>
    <p class="justify-center flex text-slate-500">롤링페이퍼 편집</p>
    <p class="justify-center flex pt-20 text-[#c0aac7]"><IconVideoConference />Team MOKKOJI</p>
    <p class="justify-center flex pt-5 text-[#c0aac7]">
      <IconLocation />서울특별시 강남구 언주로 508
    </p>
    <p class="justify-center flex pt-5 text-[#c0aac7]"><IconInternet />https://mokkoji.online/</p>
    <p class="justify-center flex pt-5 text-[#c0aac7]"><IconWifi />i10a401.p.ssafy.io</p>
  </div>
  <!-- 섹션 3/6 롤링페이퍼 편집 -->
  <div class="bg-slate-100 h-[1200px] flex" ref="rollingpaper">
    <div class="w-1/3">
      <div class="h-[35%]">
        <p class="flex items-center justify-center pt-[70px]"><IconLoveLetterBlue /></p>
        <p class="flex items-center text-[40px] justify-center">원하는 템플릿을 선택한 후</p>
        <div class="flex items-center text-[40px] justify-center">'저장하기'를 눌러주세요</div>
      </div>
      <div class="h-[40%] flex">
        <div class="w-1/2">
          <p class="justify-center flex text-[30px]">배경지</p>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-blue flex" v-if="isSelectedBasic">기본 <IconCheckBlue /></p>
            <p class="highlight-blue" v-if="isNotSelectedBasic" @click="selectOpt('기본')">기본</p>
          </div>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-blue flex" v-if="isSelectedWedding">결혼식<IconCheckBlue /></p>
            <p class="highlight-blue" v-if="isNotSelectedWedding" @click="selectOpt('결혼식')">
              결혼식
            </p>
          </div>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-blue flex" v-if="isSelectedSchool">졸업식 <IconCheckBlue /></p>
            <p class="highlight-blue" v-if="isNotSelectedSchool" @click="selectOpt('졸업식')">
              졸업식
            </p>
          </div>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-blue flex" v-if="isSelectedBaby">돌잔치 <IconCheckBlue /></p>
            <p class="highlight-blue" v-if="isNotSelectedBaby" @click="selectOpt('돌잔치')">
              돌잔치
            </p>
          </div>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-blue flex" v-if="isSelectedLunar">명절 <IconCheckBlue /></p>
            <p class="highlight-blue" v-if="isNotSelectedLunar" @click="selectOpt('명절')">명절</p>
          </div>
        </div>
        <div class="w-1/2">
          <p class="justify-center flex text-[30px]">속지</p>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-skyblue flex" v-if="isSelectedPastel">파스텔 <IconCheckSkyBlue /></p>
            <p class="highlight-skyblue" v-if="isNotSelectedPastel" @click="selectOptTwo('파스텔')">
              파스텔
            </p>
          </div>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-skyblue flex" v-if="isSelectedGreen">초록색 <IconCheckSkyBlue /></p>
            <p class="highlight-skyblue" v-if="isNotSelectedGreen" @click="selectOptTwo('초록색')">
              초록색
            </p>
          </div>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-skyblue flex" v-if="isSelectedBlue">파랑색 <IconCheckSkyBlue /></p>
            <p class="highlight-skyblue" v-if="isNotSelectedBlue" @click="selectOptTwo('파랑색')">
              파랑색
            </p>
          </div>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-skyblue flex" v-if="isSelectedPink">분홍색 <IconCheckSkyBlue /></p>
            <p class="highlight-skyblue" v-if="isNotSelectedPink" @click="selectOptTwo('분홍색')">
              분홍색
            </p>
          </div>
          <div class="justify-center flex text-[25px] mt-5 hover:cursor-pointer">
            <p class="effect-skyblue flex" v-if="isSelectedYellow">노랑색 <IconCheckSkyBlue /></p>
            <p class="highlight-skyblue" v-if="isNotSelectedYellow" @click="selectOptTwo('노랑색')">
              노랑색
            </p>
          </div>
        </div>
      </div>
      <div class="h-[30%]">
        <div
          class="opacity-70 border-2 rounded-lg w-64 mx-auto hover:cursor-pointer hover:opacity-100 effect-button"
        >
          <p class="text-[30px] justify-center ml-2 flex">미리보기 <IconSearch /></p>
        </div>
        <div
          class="opacity-70 border-2 rounded-lg w-64 mx-auto mt-10 hover:cursor-pointer hover:opacity-100 effect-button"
          @click="showSaved('템플릿')"
        >
          <p class="text-[30px] justify-center ml-2 flex">저장하기 <IconCheckMark /></p>
        </div>
      </div>
    </div>

    <div class="w-2/3 flex justify-center items-center">
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
    </div>
    <!-- 재확인 모달 ex. 저장되었습니다 -->
    <transition name="modal-fade">
      <div
        v-if="isSaved"
        class="fixed bottom-[50%] left-[50%] custom-translate rounded-lg bg-slate-50 px-14 py-3 z-30"
      >
        <p class="flex text-[30px]">{{ alertText }} <IconConfetti /></p>
      </div>
    </transition>
  </div>
  <!-- 섹션 4/6 도움말, 올라가기, 내려가기 -->
  <div class="h-[800px] pt-5">
    <div class="flex justify-center items-center h-[120px]">
      <div class="mr-5" @click="scrollToPhotoMosaic"><IconDownArrowBlue /></div>
      <div class="ml-5" @click="scrollToTop"><IconUpArrowPurple /></div>
    </div>
    <div class="flex justify-center items-center h-[20px]">
      <div class="mr-20 text-slate-500" @click="scrollToPhotoMosaic">포토모자이크 편집</div>
      <div class="mr-8 text-slate-500" @click="scrollToTop">처음으로</div>
    </div>
  </div>
  <!-- 섹션 5/6 포토모자이크 편집 -->
  <div class="bg-primary2 h-[1600px]" ref="photomosaic">
    <div class="text-[25px] flex items-center justify-end pt-5 pr-5" v-if="isNotHoveredHelp">
      <p class="flex hover:cursor-pointer" @mouseover="onHelp">도움말<IconQuestionMark /></p>
    </div>
    <div class="text-[25px] flex items-center justify-end pt-5 pr-5" v-if="isHoveredHelp">
      <p class="effect flex hover:cursor-pointer" @mouseleave="outHelp">
        클릭!<IconQuestionMark />
      </p>
    </div>

    <div class="h-[350px] flex">
      <div class="mx-auto">
        <p class="flex items-center justify-center pt-[70px]"><IconGalleryColored /></p>
        <p class="flex items-center text-[45px] justify-center pt-5">대표 이미지를 지정하고</p>
        <div class="flex items-center text-[45px] justify-center">포토 모자이크를 생성해주세요</div>
      </div>
    </div>

    <div class="">
      <Gallery />
    </div>
  </div>
  <!-- 섹션 6/6 올라가기 -->
  <div class="h-[200px]">
    <div class="justify-center flex" @click="scrollToRollingPaper"><IconUpArrowPurple /></div>
    <div class="text-slate-500 justify-center flex" @click="scrollToTop">처음으로</div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

import IconLoveLetterColored from '@/icons/result/IconLoveLetterColored.vue'
import IconGalleryColored from '@/icons/result/IconGalleryColored.vue'
import IconFlowersColoredTwo from '@/icons/result/IconFlowersColoredTwo.vue'
import IconDownArrowBlue from '@/icons/result/IconScrollDownBlue.vue'
import IconUpArrowPurple from '@/icons/result/IconScrollUpPurple.vue'
import IconDownArrowGray from '@/icons/result/IconScrollDownGray.vue'
import IconInternet from '@/icons/result/IconInternet.vue'
import IconVideoConference from '@/icons/result/IconVideoConference.vue'
import IconLocation from '@/icons/result/IconLocation.vue'
import IconWifi from '@/icons/result/IconWifi.vue'
import IconLoveLetterBlue from '@/icons/result/IconLoveLetterBlue.vue'
import IconCheckMark from '@/icons/result/IconCheck.vue'
import IconSearch from '@/icons/result/IconSearch.vue'
import IconConfetti from '@/icons/result/IconConfetti.vue'
import IconQuestionMark from '@/icons/result/IconQuestionMark.vue'
import IconCheckSkyBlue from '@/icons/result/IconCheckSkyBlue.vue'
import IconCheckBlue from '@/icons/result/IconCheckBlue.vue'
import Gallery from '@/components/myedit/Gallery.vue'

const isSaved = ref(false)
const isHoveredHelp = ref(false)
const isNotHoveredHelp = ref(true)

const rollingpaper = ref(null)
const photomosaic = ref(null)
const top = ref(null)
const canvas = ref(null)

const design = ref('basic')
const color = ref('rainbow')
const fileName = ref('')
const alertText = ref('')

const isSelectedBasic = ref(true)
const isSelectedBaby = ref(false)
const isSelectedWedding = ref(false)
const isSelectedLunar = ref(false)
const isSelectedSchool = ref(false)

const isNotSelectedBasic = ref(false)
const isNotSelectedBaby = ref(true)
const isNotSelectedWedding = ref(true)
const isNotSelectedLunar = ref(true)
const isNotSelectedSchool = ref(true)

const isSelectedPastel = ref(true)
const isSelectedYellow = ref(false)
const isSelectedBlue = ref(false)
const isSelectedGreen = ref(false)
const isSelectedPink = ref(false)

const isNotSelectedPastel = ref(false)
const isNotSelectedBlue = ref(true)
const isNotSelectedYellow = ref(true)
const isNotSelectedGreen = ref(true)
const isNotSelectedPink = ref(true)

const onHelp = () => {
  isHoveredHelp.value = true
  isNotHoveredHelp.value = false
}

const outHelp = () => {
  isHoveredHelp.value = false
  isNotHoveredHelp.value = true
}

const scrollToTop = () => {
  top.value.scrollIntoView({ behavior: 'smooth' })
}

const scrollToRollingPaper = () => {
  rollingpaper.value.scrollIntoView({ behavior: 'smooth' })
}

const scrollToPhotoMosaic = () => {
  photomosaic.value.scrollIntoView({ behavior: 'smooth' })
}

const getFileName = async (files) => {
  const maxFileSize = 1024 * 1024 * 2
  if (files[0].size > maxFileSize) {
    alert('파일 크기가 2MB를 초과했습니다')
    return
  }

  fileName.value = files[0].name
  await base64(files[0])
}

const base64 = (file) => {
  return new Promise((resolve) => {
    const reader = new FileReader()
    reader.onload = (e) => {
      const image = new Image()
      image.onload = () => {
        const context = canvas.value.getContext('2d')
        let size = 200

        canvas.value.width = size
        canvas.value.height = size

        const x = image.width > image.height ? (image.width - size) / 2 : 0
        const y = image.width > image.height ? 0 : (image.height - size) / 2

        context.drawImage(image, x, y, size, size, 0, 0, size, size)
      }
      resolve(e.target.result)
      // const previewImage = document.getElementById('imgMosaic')
      // previewImage.src = e.target.result
      // image.value = previewImage.src
      image.src = e.target.result
      const previewImage = document.getElementById('imgMosaic')
      previewImage.src = e.target.result
    }
    reader.readAsDataURL(file)
  })
}

const showSaved = (e) => {
  isSaved.value = true
  setTimeout(() => {
    isSaved.value = false
  }, 2000)
  switch (e) {
    case '템플릿':
      alertText.value = '선택한 템플릿을 저장했어요'
      break
    case '포토모자이크':
      alertText.value = '포토모자이크를 생성했어요'
      break
    case '대표이미지':
      alertText.value = '대표 이미지를 설정했어요'
      break
  }
}

//배경지
const selectOpt = (value) => {
  isSelectedBasic.value = false
  isSelectedBaby.value = false
  isSelectedWedding.value = false
  isSelectedLunar.value = false
  isSelectedSchool.value = false

  isNotSelectedBasic.value = true
  isNotSelectedBaby.value = true
  isNotSelectedWedding.value = true
  isNotSelectedLunar.value = true
  isNotSelectedSchool.value = true

  switch (value) {
    case '기본':
      design.value = 'basic'
      isSelectedBasic.value = true
      isNotSelectedBasic.value = false
      break
    case '결혼식':
      design.value = 'wedding'
      isSelectedWedding.value = true
      isNotSelectedWedding.value = false
      break
    case '졸업식':
      design.value = 'school'
      isSelectedSchool.value = true
      isNotSelectedSchool.value = false
      break
    case '명절':
      design.value = 'lunar'
      isSelectedLunar.value = true
      isNotSelectedLunar.value = false
      break
    case '돌잔치':
      design.value = 'baby'
      isSelectedBaby.value = true
      isNotSelectedBaby.value = false
      break
  }
}
//속지
const selectOptTwo = (value) => {
  isSelectedPastel.value = false
  isSelectedYellow.value = false
  isSelectedBlue.value = false
  isSelectedGreen.value = false
  isSelectedPink.value = false

  isNotSelectedPastel.value = true
  isNotSelectedYellow.value = true
  isNotSelectedBlue.value = true
  isNotSelectedGreen.value = true
  isNotSelectedPink.value = true
  switch (value) {
    case '파스텔':
      color.value = 'rainbow'
      isNotSelectedPastel.value = false
      isSelectedPastel.value = true
      break
    case '초록색':
      color.value = 'green'
      isNotSelectedGreen.value = false
      isSelectedGreen.value = true
      break
    case '파랑색':
      color.value = 'blue'
      isNotSelectedBlue.value = false
      isSelectedBlue.value = true
      break
    case '분홍색':
      color.value = 'pink'
      isNotSelectedPink.value = false
      isSelectedPink.value = true
      break
    case '노랑색':
      color.value = 'yellow'
      isNotSelectedYellow.value = false
      isSelectedYellow.value = true
      break
  }
}

//처음으로
onMounted(() => {
  window.scrollTo(0, 0)
})
</script>

<style>
.effect-black {
  box-shadow: inset 0 -2px 0 #070700;
  color: black;
}

.effect {
  box-shadow: inset 0 -6px 0 #57b5be;
  color: black;
}

.effect-button:hover {
  box-shadow: inset 0 -50px 0 #e7ebff;
  color: black;
}

.effect-button-two:hover {
  box-shadow: inset 0 -50px 0 #c8eaed;
  color: black;
}

.effect-blue {
  box-shadow: inset 0 -2px 0 hsl(257, 91%, 25%);
  color: black;
}

.highlight-blue:hover {
  box-shadow: inset 0 -2px 0 hsl(257, 91%, 25%);
  color: black;
}

.highlight-skyblue:hover {
  box-shadow: inset 0 -2px 0 black;
  color: black;
}

.effect-skyblue {
  box-shadow: inset 0 -2px 0 black;
  color: black;
}

#btn:active {
  margin-top: 5px;
  box-shadow: none;
}

.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.5s;
}
.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}

.custom-translate {
  transform: translate(-50%, -50%);
}
</style>
