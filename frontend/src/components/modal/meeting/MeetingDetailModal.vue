<template>
  <div class="fixed w-[26%] h-[33%] bottom-[11%] left-[13%]">
    <div
      id="gift-container"
      class="px-[2vw] h-[100%] bg-black flex flex-col justify-center items-start space-y-[1vh] rounded-r-lg"
    >
      <div class="flex w-[23vw] items-center h-[5vh]">
        <div class="text-white text-r-md">{{}}님의 회의 정보</div>
        <button
          class="ml-auto w-[2.5vw] aspect-square size-[100%] flex justify-center items-center hover:bg-neutral-500 rounded-full"
        >
          <IconCancelWhite class="w-[2vw] size-[40%]" @click="$emit('remove-detail-modal')" />
        </button>
      </div>
      <div class="text-white text-r-sm">
        <div>회의 주소 | {{ address }}</div>
      </div>
      <button
        class="ml-[4vw] w-[5vw] text-white text-[0.8vw] hover:bg-neutral-500 rounded-r-lg"
        @click="copySessionInfo(address), $emit('copy-address-info')"
      >
        <IconCopy class="mr-[0.3vw]" />주소 복사
      </button>

      <div class="text-white text-r-sm flex">
        회의ID
        <div class="text-white ml-[1.2vw] flex">| {{ sessionId }}</div>
      </div>
      <button
        class="ml-[4vw] w-[4.3vw] text-white text-[0.8vw] hover:bg-neutral-500 rounded-r-lg"
        @click="copySessionInfo(sessionId), $emit('copy-session-id-info')"
      >
        <IconCopy class="mr-[0.3vw]" />ID 복사
      </button>
      <div class="text-white text-r-sm flex">
        주최자
        <div class="text-white ml-[1.3vw]">| {{}}</div>
      </div>
      <div class="text-white text-r-sm flex">
        <div>날짜</div>
        <div class="text-white ml-[2.2vw] text-r-sm">| {{ today }}</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import IconCopy from '@/icons/meeting/IconCopy.vue'
import IconCancelWhite from '@/icons/meeting/IconCancelWhite.vue'

const address = ref(`https://mokkoji.online/meetings/${sessionStorage.getItem('sessionId')}`)
const sessionId = ref(sessionStorage.getItem('sessionId'))
const date = new Date()
const today = date.toLocaleDateString()

const copySessionInfo = (sessionInfo) => {
  navigator.clipboard.writeText(sessionInfo)
}
</script>

<style></style>
