<template>
  <main class="mx-28 h-full">
    <!-- 회의 화면 -->
    <section class="pt-4 h-4/5 flex flex-row">
      <div class="w-full">
        <!-- 메인 회의 -->
        <MainMeeting v-if="isMain" />
        <!-- 소그룹 회의 -->
        <GroupMeeting v-else />
      </div>

      <!-- 참여자 목록, 채팅방-->
      <div v-if="isList || isChat" class="pl-2 w-1/4">
        <div v-if="isList" class="h-1/2 bg-purple-300 rounded-xl">참여자 목록</div>
        <div v-if="isChat" class="h-1/2 bg-purple-300 rounded-xl">채팅방</div>
      </div>
    </section>
    <!-- 기능 버튼 -->
    <section class="pt-10 h-1/5">
      <div class="flex">
        <div class="basis-1/6 flex flex-wrap">
          아이콘
          <!-- <button><img src="@/assets/icons/meetingdetail.svg" alt="회의 상세 정보" /></button> -->
          <button class="h-10 bg-gray-500 text-white rounded-xl ml-2">화면 배치</button>
        </div>
        <div class="basis-4/6 flex flex-wrap justify-center space-x-10">
          <div id="button-container" class="flex flex-col items-center">
            <button id="button"></button>
            <span>마이크 ON</span>
          </div>
          <div id="button-container">
            <button id="button"></button>
            <span>카메라</span>
          </div>
          <div id="button-container" v-if="isMain">
            <button id="button" @click="changeMeetingType"></button>
            <span>소그룹</span>
          </div>
          <div id="button-container">
            <button id="button"></button>
            <span>편지쓰기</span>
          </div>
          <div id="button-container">
            <button id="button"></button>
            <span>선물하기</span>
          </div>
          <div id="button-container">
            <button id="button"></button>
            <span>사진찍기</span>
          </div>
        </div>
        <div class="ml-2 basis-2/6 flex flex-row flex-wrap space-x-4 justify-end">
          <div id="button-container">
            <button id="button" @click="changeListStatus"></button>
            <span>참여자 목록</span>
          </div>
          <div id="button-container">
            <button id="button" @click="changeChatStatus"></button>
            <span>채팅</span>
          </div>
          <div>
            <button class="bg-red-500 rounded-3xl" @click="exitMeeting">나가기</button>
          </div>
        </div>
      </div>
    </section>
  </main>
</template>

<script setup>
import { ref } from 'vue'
import MainMeeting from '@/components/meeting/MainMeeting.vue'
import GroupMeeting from '@/components/meeting/GroupMeeting.vue'

// 메인 회의
let isMain = ref(true)
// 참여자 목록
let isList = ref(false)
// 채팅
let isChat = ref(false)

// 메인 회의 / 소그룹 이동
let changeMeetingType = () => {
  isMain.value = !isMain.value
}
// 채팅 on / off
let changeChatStatus = () => {
  isChat.value = !isChat.value
}
// 참여자 목록 on / off
let changeListStatus = () => {
  isList.value = !isList.value
}

let exitMeeting = () => {
  if (isMain.value) {
    console.log('goToHome')
  } else {
    console.log('goToMain')
    isMain.value = !isMain.value
  }
}
</script>

<style>
#button-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#button {
  border-radius: 100%;
}
</style>
