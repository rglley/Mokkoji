<template>
  <main class="mx-24 h-full">
    <!-- 회의 화면 -->
    <section class="pt-4 h-4/5 flex flex-row justify-center">
      <div class="w-4/5">
        <!-- 메인 회의 -->
        <MainMeeting v-if="isMain" />
        <!-- 소그룹 회의 -->
        <GroupMeeting v-else />
      </div>
      <!-- 참여자 목록, 채팅방-->
      <div v-if="isList || isChat" class="pl-2 w-1/5 flex flex-col">
        <div v-if="isList" class="basis-full bg-purple-200 rounded-xl">참여자 목록</div>
        <div v-if="isList && isChat" class="mb-2"></div>
        <div v-if="isChat" class="basis-full bg-purple-200 rounded-xl">채팅방</div>
      </div>
    </section>
    <!-- 기능 버튼 -->
    <section class="pt-10">
      <div class="flex">
        <div class="basis-2/12 flex flex-wrap">
          <button>아이콘</button>
          <button class="h-10 w-16 ml-2 bg-gray-400 text-white text-xs rounded-xl">
            화면 배치
          </button>
        </div>
        <div class="basis-11/12 flex flex-wrap justify-center space-x-10">
          <div id="button-container" class="flex flex-col items-center">
            <button id="button" class="bg-purple-100"></button>
            <span>마이크 ON</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-purple-100"></button>
            <span>카메라</span>
          </div>
          <div id="button-container" v-if="isMain">
            <button id="button" class="bg-pink-100" @click="changeMeetingType"></button>
            <span>소그룹</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-pink-100" @click="toggleLetterModal"></button>
            <span>편지쓰기</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-pink-100"></button>
            <span>선물하기</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-pink-100"></button>
            <span>사진찍기</span>
          </div>
        </div>
        <div class="ml-2 basis-3/12 flex flex-row flex-wrap space-x-3 justify-end">
          <div id="button-container">
            <button id="button" class="bg-purple-100" @click="changeListStatus"></button>
            <span>참여자 목록</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-purple-100" @click="changeChatStatus"></button>
            <span>채팅</span>
          </div>
          <div>
            <button class="w-20 h-10 bg-red-500 text-white rounded-3xl" @click="exitMeeting">
              나가기
            </button>
          </div>
        </div>
      </div>
    </section>
    <LetterModal v-if="showLetterModal" />
  </main>
</template>

<script setup>
import { ref } from 'vue'
import axios from 'axios'
import MainMeeting from '@/components/meeting/MainMeeting.vue'
import GroupMeeting from '@/components/meeting/GroupMeeting.vue'
import LetterModal from '@/components/modal/LetterModal.vue'

// 메인 회의
let isMain = ref(true)
// 참여자 목록
let isList = ref(false)
// 채팅
let isChat = ref(false)
// 편지쓰기
let showLetterModal = ref(false)

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

// 편지 쓰기 모달 생성
let toggleLetterModal = () => {
  showLetterModal.value = !showLetterModal.value
}

// 회의 나가기
let exitMeeting = () => {
  // 메인 화면에서 나가기
  if (isMain.value) {
    console.log('goToHome')
    // 소그룹에서 나가기
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
  margin-bottom: 7px;
  width: 35px;
  height: 35px;
  border-radius: 100%;
}

span {
  font-size: 13px;
  color: #888888;
}

::-webkit-scrollbar {
  background: #f9f1ff;
  width: 10px;
  height: 10px;
}

/* 스크롤바 막대 */
::-webkit-scrollbar-thumb {
  background: #e7c6ff; /* 스크롤바 막대 색상 */
  border-radius: 12px 12px 12px 12px;
}
</style>
