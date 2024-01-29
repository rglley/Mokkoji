<template>
  <div class="h-[22rem] w-72 mx-8 my-4 rounded-lg border-violet-400 border-4 border-solid bg-white">
    <div class="flex h-1/3">
      <div class="w-2/5">
        <img
          :src="`src/assets/memory/memory_random_${imgNo}.png`"
          alt="memory-random"
          class="mx-2 my-2"
        />
      </div>
      <div class="w-3/5 ml-6 mt-8">
        <p class="text-xs mb-1 flex" v-if="isRollingDone">롤링페이퍼: 편집 완료<IconCheckMark /></p>
        <p class="text-xs mb-1 flex" v-else>롤링페이퍼: 편집 대기중<IconSandTimer /></p>
        <p class="text-xs mb-1 flex" v-if="isPhotoDone">
          포토 모자이크: 생성 완료<IconCheckMark />
        </p>
        <p class="text-xs mb-1 flex" v-else>포토 모자이크: 생성 대기중<IconSandTimer /></p>
        <button
          @click="openModalThree"
          class="text-[10px] text-white bg-violet-400 float-right mr-6 rounded-lg h-6 w-12"
        >
          추억생성
        </button>
      </div>
    </div>
    <div class="h-2/3 flex justify-center">
      <div class="h-[95%] w-[90%] pl-1 pt-2 rounded-lg border-violet-200 border-4 border-solid">
        <p class="flex mb-1">
          <IconCalendar /><Icon-calen />{{ memory.eventDay }} ({{ lastDayToEdit.dayOfWeek }})
        </p>
        <p class="flex"><IconClock />{{ memory.eventRunTime }}</p>
        <p class="flex"><IconPeople />{{ memory.participants }}</p>
        <p class="text-center mt-8">
          편집 가능 기간이 {{ lastDayToEdit.editablePeriod }}일 남았어요
        </p>
        <p class="text-center">{{ lastDayToEdit.expireDay }}</p>
        <button class="text-md text-white bg-violet-300 float-right mr-3 rounded-lg h-8 w-11 mt-2">
          편집
        </button>
      </div>
    </div>
  </div>
  <!--모달 시작-->
  <transition
    name="modal-fade"
    class="fixed top-0 bottom-0 h-full w-full border-solid custom-modal-bg z-20 flex items-center justify-center"
  >
    <div v-if="isOpenThree" class="">
      <div class="bg-violet-50 fixed w-[800px] h-[500px] rounded-lg custom-background">
        <div
          class="absolute h-[22rem] w-72 top-[80px] left-[420px] rounded-lg border-violet-400 border-4 border-solid bg-white"
        >
          <div class="flex h-1/3">
            <div class="w-2/5">
              <img
                :src="`src/assets/memory/memory_random_${imgNo}.png`"
                alt="memory-random"
                class="mx-2 my-2"
              />
            </div>
            <div class="w-3/5 ml-6 mt-8">
              <p class="text-xs mb-1 flex" v-if="isRollingDone">
                롤링페이퍼: 편집 완료<IconCheckMark />
              </p>
              <p class="text-xs mb-1 flex" v-else>롤링페이퍼: 편집 대기중<IconSandTimer /></p>
              <p class="text-xs mb-1 flex" v-if="isPhotoDone">
                포토 모자이크: 생성 완료<IconCheckMark />
              </p>
              <p class="text-xs mb-1 flex" v-else>포토 모자이크: 생성 대기중<IconSandTimer /></p>
              <button
                class="text-[10px] text-white bg-violet-400 float-right mr-6 rounded-lg h-6 w-12"
              >
                추억생성
              </button>
            </div>
          </div>
          <div class="h-2/3 flex justify-center">
            <div
              class="h-[95%] w-[90%] pl-1 pt-2 rounded-lg border-violet-200 border-4 border-solid"
            >
              <p class="flex mb-1">
                <IconCalendar /><Icon-calen />{{ memory.eventDay }} ({{ lastDayToEdit.dayOfWeek }})
              </p>
              <p class="flex"><IconClock />{{ memory.eventRunTime }}</p>
              <p class="flex"><IconPeople />{{ memory.participants }}</p>
              <p class="text-center mt-8">
                편집 가능 기간이 {{ lastDayToEdit.editablePeriod }}일 남았어요
              </p>
              <p class="text-center">{{ lastDayToEdit.expireDay }}</p>
              <button
                class="text-md text-white bg-violet-300 float-right mr-3 rounded-lg h-8 w-11 mt-2"
              >
                편집
              </button>
            </div>
          </div>
        </div>
        <div>
          <p class="absolute flex text-3xl top-[150px] right-[540px]"><IconWarning /> 주의</p>
          <p class="absolute text-2xl top-[200px] right-[500px]">추억으로 생성되면</p>
          <p class="absolute text-2xl top-[240px] right-[480px]">결과물 편집이 어려워요</p>
          <p class="absolute text-2xl top-[280px] right-[480px]">충분히 확인하셨나요?</p>
        </div>
        <div>
          <button
            @click="openModalFour"
            class="text-white rounded-2xl mr-5 bg-green-300 hover:bg-green-400 h-8 w-12 absolute top-[330px] left-[150px]"
          >
            네!
          </button>
          <button
            @click="closeModalThree"
            class="text-white h-8 w-12 rounded-2xl mr-5 bg-red-300 hover:bg-red-400 absolute top-[330px] left-[230px]"
          >
            아니요
          </button>
        </div>
      </div>
    </div>
  </transition>
  <!--모달 끝-->
  <!-- 네번째 모달 시작-->
  <transition
    name="modal-fade"
    class="fixed top-0 bottom-0 h-full w-full border-solid z-20 flex custom-modal-bg items-center justify-center"
  >
    <div v-if="isOpenFour" class="">
      <div class="bg-violet-50 fixed w-[600px] h-[600px] rounded-lg custom-background">
        <p class="absolute top-[20px] text-3xl left-[110px] font-bold">행사 정보를 추가로 입력해주세요</p>
        <p class="absolute top-[60px] text-xl left-[150px] text-gray-500">
          입력하신 내용은 추억 카드에 기록됩니다
        </p>

        <p class="absolute top-[120px] text-3xl left-[260px]">행사명</p>
        <input type="text" placeholder="ex. 결혼식" class="bg-white absolute top-[180px] text-3xl left-[50px] rounded-lg w-[500px] border-violet-300  border-4 border-solid" ></input>
        <p class="absolute top-[240px] text-3xl left-[250px]">행사설명</p>
        <textarea placeholder="ex. 검은 머리 파뿌리 될때까지... &#13;ex. Will you marry me? 가장 많이 웃은 날" class="bg-white absolute top-[300px] text-3xl left-[50px] rounded-lg w-[500px] h-[200px] border-violet-300  border-4 border-solid"></textarea>
        <button @click="closeModalFour" class="absolute top-[530px] text-lg left-[210px] bg-violet-400 rounded-lg px-4 py-2 text-white hover:bg-violet-600">입력 완료 및 추억 생성</button>
      </div>
    </div>
  </transition>
</template>

<script setup>
import { computed, ref } from 'vue'

import IconCalendar from '@/icons/IconCalendar.vue'
import IconCalen from '@/icons/IconCalen.vue'
import IconClock from '@/icons/IconClock.vue'
import IconPeople from '@/icons/IconPeople.vue'
import IconSandTimer from '@/icons/IconSandTimer.vue'
import IconCheckMark from '@/icons/IconCheckMark.vue'
import IconWarning from '@/icons/IconWarning.vue'

const { VITE_MEMORY_IMG_COUNT } = import.meta.env
let isRollingDone = true
let isPhotoDone = false
let isOpenThree = ref(false)
let isOpenFour = ref(false)

let props = defineProps(['memory'])

let imgNo = computed(() => {
  let no = props.memory.eventId % VITE_MEMORY_IMG_COUNT
  return no === 0 ? VITE_MEMORY_IMG_COUNT : no
})
console.log(imgNo)

let lastDayToEdit = computed(() => {
  let eventDate = new Date(props.memory.eventDay) //편집 마감 기한 계산하기
  let expireMonth = eventDate.getMonth() + 2 //0 - 11월
  let expireDay = eventDate.getDate()
  let expireYear = eventDate.getYear() + 1900
  if (expireMonth === 13) {
    //13월 -> 다음년도 1월
    expireMonth = 1
    expireYear = expireYear + 1
  }
  let currentDate = new Date()
  let expireDate = new Date(expireYear, expireMonth - 1, expireDay)

  console.log(expireDate)

  let editablePeriod = Math.abs(expireDate.getTime() - currentDate.getTime())
  editablePeriod = Math.ceil(editablePeriod / (1000 * 60 * 60 * 24))
  return {
    editablePeriod: editablePeriod,
    expireDay: '~' + expireMonth + '/' + expireDay,
    dayOfWeek: eventDate.getDay()
  }
})

let closeModalThree = () => {
  isOpenThree.value = false
}

let openModalThree = () => {
  isOpenThree.value = !isOpenThree.value
}

let openModalFour = () => {
  isOpenThree.value = false
  console.log(isOpenFour.value)
  isOpenFour.value = !isOpenFour.value
  console.log(isOpenFour.value)
}
let closeModalFour = () => {
  isOpenFour.value = false
}
</script>

<style>
.custom-modal {
  transform: translate(-50%, -50%);
}

.custom-modal-bg {
  background-color: rgba(0, 0, 0, 0.5);
}

.custom-background {
  overflow: auto;
}
</style>
