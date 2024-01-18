<template>
  <!-- 메인 회의 -->
  <MainMeeting v-if="isMain" />
  <!-- 소그룹 회의 -->
  <GroupMeeting v-else />
  <button v-if="isMain" @click="attendGroupMeeting">소그룹 참여</button>
  <button v-else @click="attendGroupMeeting">나가기</button>
</template>

<script setup>
import { ref } from 'vue'
import MainMeeting from '@/components/meeting/MainMeeting.vue'
import GroupMeeting from '@/components/meeting/GroupMeeting.vue'

let isMain = ref(true)

let attendGroupMeeting = () => {
  isMain.value = !isMain.value
}
</script>

<style lang="scss" scoped></style>
