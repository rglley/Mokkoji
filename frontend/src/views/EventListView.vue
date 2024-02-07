<template>
  <!--섹션 1/3, 유저 이름 -->
  <div class="text-[50px] flex px-2 h-[500px] items-center pl-32 bg-violet-50">
    <a v-if="isNotShown" class=""><strong>이진영</strong>님의 모꼬지</a>
    <a v-if="isShown" class="effect-pink"><strong>이진영</strong>님의 모꼬지</a>
    <div class="flex">
      <div v-if="isNotShownTwo" class=""><IconFlowers /></div>
      <div v-if="isShownTwo" class=""><IconFlowersColored /></div>
    </div>
  </div>
  <!-- 섹션 2/3, 기억: Memory -->
  <div class="flex">
    <div class="justify-center h-[500px] mt-10 ml-24 w-[40%]">
      <div class="flex">
        <strong class="text-[40px]">기억 </strong>
        <p class="text-3xl mt-3">: Memory</p>
      </div>
      <p class="pt-2 text-[20px]">편집 버튼으로 롤링페이퍼와 포토모자이크를 만드세요.</p>
      <p class="pt-2 text-[20px] pb-10">추억 생성을 통해 모꼬지를 추억으로 간직하세요.</p>
      <!-- 도움말 -->
      <a class="mr-4"
        ><span class="text-[#610091] text-sm highlight-pink" @mouseover="hoverPhotomosaic"
          >포토 모자이크란?</span
        ></a
      >
      <a class="mr-4"
        ><span class="text-[#610091] text-sm highlight-green" @mouseover="hoverRollingPaper"
          >롤링페이퍼란?</span
        ></a
      >
      <a
        ><span class="text-[#610091] text-sm highlight-white" @mouseover="hoverBeCareful"
          >기억 주의사항</span
        ></a
      >
      <p />

      <div v-if="isHoveredPhotoMosaic" data-aos="fade-up" data-aos-duration="2000">
        <div class="flex">
          <div class="w-1/4 pl-5">
            <img src="@/assets/eventlist/photomosaic_ex.png" class="pt-10" />
          </div>
          <div class="w-3/4 pl-2">
            <p class="pt-20">여러 장의 사진을 색상에 맞게 배열해</p>
            <p class="">한 장의 큰 이미지를 표현하는 것입니다.</p>
          </div>
        </div>
      </div>

      <div v-if="isHoveredRollingPaper" data-aos="fade-up" data-aos-duration="2000">
        <div class="flex">
          <div class="w-1/4 pl-5">
            <img src="@/assets/eventlist/rollingpaper_ex.png" class="pt-10 w-24 h-40" />
          </div>
          <div class="w-3/4 pl-2">
            <p class="pt-20">참여자가 남긴 텍스트, 영상, 음성 메시지를</p>
            <p class="">모꼬지 전용 템플릿에서 한 눈에 확인해보세요.</p>
          </div>
        </div>
      </div>

      <div v-if="isHoveredBeCareful" data-aos="fade-up" data-aos-duration="2000">
        <div class="flex">
          <div class="w-1/4 pl-5">
            <img src="@/assets/eventlist/warning.png" class="pt-10 w-24 h-36" />
          </div>
          <div class="w-3/4">
            <p class="pt-20">편집 가능 기간 <strong>(모임 주최일로부터 한 달)</strong>이 지나면</p>
            <p class="">
              롤링페이퍼, 포토 모자이크 생성 및 추억 생성이
              <strong class="text-red-400">불가</strong>합니다.
            </p>
          </div>
        </div>
      </div>
    </div>
    <div class="w-2/3 h-[400px] mt-12 mr-5 overflow-scroll" ref="verticalScrollWrap">
      <!-- <div class="">
        
        <div class="inline-block"> -->
      <div class="whitespace-nowrap flex">
        <MemoryList
          v-for="memory in MemoryData"
          :key="memory.id"
          :memory="memory"
          class="inline-block"
        >
        </MemoryList>
      </div>
    </div>
    <!-- </div>
    </div> -->
  </div>
  <!-- 섹션 3/3, 추억: Recollection -->
  <div class="h-[600px] bg-[#ffeff8] pt-10">
    <div class="justify-center ml-24">
      <div class="flex">
        <strong class="text-[40px]">추억 </strong>
        <p class="text-3xl mt-3">: Recollection</p>
      </div>
      <p class="pt-2 text-[20px]">
        대표 이미지를 클릭하면 완성된 포토 모자이크와 롤링페이퍼를 확인할 수 있어요.
      </p>
    </div>

    <div class="h-96 flex justify-center items-center mt-10">
      <!--추억 컴포넌트-->
      <RecollectionList
        v-for="recollection in RecollectionData"
        :key="recollection.eventId"
        :recollection="recollection"
      />
    </div>
  </div>

  <!--더 이상 사용하지 않는 모달-->
  <!-- 
  <transition name="modal-fade">
    <div
      v-if="isOpenTwo"
      class="fixed top-36 left-72 w-full h-full flex content-center items-center z-20"
    >
      <div class="bg-white relative px-28 py-36 border-black border-2 border-solid rounded-lg">
        <div>
          <button
            @click="showModalTwo"
            class="text-sm absolute px-2 py-1 bottom-64 right-2 cursor-pointer bg-violet-100 rounded-lg"
          >
            확인
          </button>
          <div>
            <img
              src="@/assets/eventlist/recollection_ex.png"
              class="absolute bottom-24 right-12 w-32 h-42"
            />
          </div>
          <p class="absolute text-base bottom-16 right-24">TIP!</p>
          <p class="absolute text-sm bottom-10 right-6">대표 이미지를 클릭하면 완성된</p>
          <p class="absolute text-sm bottom-4 right-12">결과물로 이동합니다.</p>
        </div>
      </div>
    </div>
  </transition> -->
</template>

<script setup>
import { ref, onMounted } from 'vue'

import { eventList } from '@/api/result'
import IconFlowers from '@/icons/result/IconFlowers.vue'
import IconFlowersColored from '@/icons/result/IconFlowersColored.vue'
import MemoryList from '@/components/myevent/MemoryList.vue'
import MemoryData from '@/temp/memory.json'
import RecollectionData from '@/temp/recollection.json'
import RecollectionList from '@/components/myevent/RecollectionList.vue'

const isHoveredPhotoMosaic = ref(false)
const isHoveredRollingPaper = ref(false)
const isHoveredBeCareful = ref(true)
const isShown = ref(false)
const isNotShown = ref(true)
const isShownTwo = ref(false)
const isNotShownTwo = ref(true)

const isMouseDown = ref(false)
const startX = ref(0)
const scrollLeft = ref(0)

let memories = ref([])
let recollections = []

const verticalScrollWrap = ref(null)

const hoverPhotomosaic = () => {
  isHoveredPhotoMosaic.value = true
  isHoveredRollingPaper.value = false
  isHoveredBeCareful.value = false
}

const hoverRollingPaper = () => {
  isHoveredPhotoMosaic.value = false
  isHoveredRollingPaper.value = true
  isHoveredBeCareful.value = false
}

const hoverBeCareful = () => {
  isHoveredPhotoMosaic.value = false
  isHoveredRollingPaper.value = false
  isHoveredBeCareful.value = true
}

const setShow = () => {
  setTimeout(() => {
    isShown.value = true
    isNotShown.value = false
  }, 500)
}

const setShowTwo = () => {
  setTimeout(() => {
    isShownTwo.value = true
    isNotShownTwo.value = false
  }, 1000)
}

const getEventList = () => {
  console.log('user이름님의 event list 불러오기')
  //API 호출
  eventList(
    (res) => {
      memories.value = res.data.memoryList
      recollections = res.data.recollectionList
    },
    (error) => {
      console.log(error)
    }
  )
}

onMounted(() => {
  console.log("I'm here")
  setShow()
  setShowTwo()
  getEventList()
  window.onload = () => {
    console.log("I'm here")
    console.log(verticalScrollWrap.value)
    verticalScrollWrap.value.addEventListener('mousedown', (e) => {
      isMouseDown.value = true
      startX.value = e.pageX - verticalScrollWrap.value.offsetLeft
      scrollLeft.value = verticalScrollWrap.value.scrollLeft
    })

    verticalScrollWrap.value.addEventListener('mouseleave', () => {
      isMouseDown.value = false
    })

    verticalScrollWrap.value.addEventListener('mouseup', () => {
      isMouseDown.value = false
    })

    verticalScrollWrap.value.addEventListener('mousemove', (e) => {
      if (!isMouseDown.value) return

      e.preventDefault()
      const x = e.pageX - verticalScrollWrap.value.offsetLeft
      const beforeScrollLeft = (x - startX.value) * 1
      verticalScrollWrap.value.scrollLeft = scrollLeft.value - beforeScrollLeft
    })
  }
})
</script>

<style>
.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.3s ease-in-out;
}

.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}

span {
  box-shadow: inset 0 0 0 0 #000608;
  color: #610091;
  margin: 0 -0.25rem;
  padding: 0 0.25rem;
  transition:
    color 0.3s ease-in-out,
    box-shadow 0.3s ease-in-out;
}
.effect-pink {
  box-shadow: inset 0 -10px 0 #d486fe;
  color: black;
}

.highlight-green:hover {
  box-shadow: inset 0 -3px 0 hsl(123, 92%, 76%);
  color: black;
}

.highlight-pink:hover {
  box-shadow: inset 0 -3px 0 #ffa4dc;
  color: black;
}
.highlight-white:hover {
  box-shadow: inset 0 -3px 0 #bab9b9;
  color: black;
}
</style>
