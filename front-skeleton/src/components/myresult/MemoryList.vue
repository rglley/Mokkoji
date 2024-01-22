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
        <button class="text-[10px] text-white bg-violet-400 float-right mr-6 rounded-lg h-6 w-12">
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
</template>

<script setup>
import { computed } from 'vue'

import IconCalendar from '@/icons/IconCalendar.vue'
import IconCalen from '@/icons/IconCalen.vue'
import IconClock from '@/icons/IconClock.vue'
import IconPeople from '@/icons/IconPeople.vue'
import IconSandTimer from '@/icons/IconSandTimer.vue'
import IconCheckMark from '@/icons/IconCheckMark.vue'

const { VITE_MEMORY_IMG_COUNT } = import.meta.env
const isRollingDone = true
const isPhotoDone = false

const props = defineProps(['memory'])

const imgNo = computed(() => {
  const no = props.memory.eventId % VITE_MEMORY_IMG_COUNT
  return no === 0 ? VITE_MEMORY_IMG_COUNT : no
})
console.log(imgNo)

const lastDayToEdit = computed(() => {
  const eventDate = new Date(props.memory.eventDay) //편집 마감 기한 계산하기
  let expireMonth = eventDate.getMonth() + 2 //0 - 11월
  const expireDay = eventDate.getDate()
  let expireYear = eventDate.getYear() + 1900
  if (expireMonth === 13) {
    //13월 -> 다음년도 1월
    expireMonth = 1
    expireYear = expireYear + 1
  }
  const currentDate = new Date()
  const expireDate = new Date(expireYear, expireMonth - 1, expireDay)

  console.log(expireDate)

  let editablePeriod = Math.abs(expireDate.getTime() - currentDate.getTime())
  editablePeriod = Math.ceil(editablePeriod / (1000 * 60 * 60 * 24))
  return {
    editablePeriod: editablePeriod,
    expireDay: '~' + expireMonth + '/' + expireDay,
    dayOfWeek: eventDate.getDay()
  }
})
</script>

<style></style>
