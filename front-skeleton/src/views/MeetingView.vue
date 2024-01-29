<template>
  <main class="mx-16 my-6 overflow-hidden">
    <!-- 회의 화면 -->
    <section class="flex flex-row">
      <div class="w-4/5 h-full">
        <div class="flex mb-1">
          아이콘
          <!-- <button><img src="@/assets/icons/meetingdetail.svg" alt="회의 상세 정보" /></button> -->
          <button class="w-30 h-10 bg-gray-500 text-white rounded-xl ml-2">화면 배치</button>
        </div>
        <!-- 메인 회의 -->
        <MyMeeting v-if="isMain" />
        <!-- 소그룹 회의 -->
        <!-- <GroupMeeting v-else /> -->
      </div>
      <!-- 참여자 목록, 채팅방-->
      <div class="w-1/5">
        <div class="bg-purple-300 ml-2">참여자 목록</div>
        <div class="bg-purple-300 ml-2">채팅방</div>
      </div>
    </section>
    <!-- 기능 버튼들 -->
    <section class="flex">
      <button>마이크</button>
      <button>카메라</button>
      <button v-if="isMain" @click="changeMeetingType">소그룹</button>
      <button>편지쓰기</button>
      <button>선물하기</button>
      <button>사진찍기</button>
      <button>참여자 목록</button>
      <button>채팅</button>
      <button v-if="!isMain" @click="changeMeetingType">나가기</button>
    </section>
  </main>
</template>
  <script setup>
import { ref } from 'vue'
import MyMeeting from '@/components/conference/MyMeeting.vue'
// import GroupMeeting from '@/components/meeting/GroupMeeting.vue'
let isMain = ref(true)
let changeMeetingType = () => {
  isMain.value = !isMain.value
}
</script>
  <style></style>