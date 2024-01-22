import { defineStore } from 'pinia'
import { ref } from 'vue'
//import axios from 'axios';

export const useUserStore = defineStore('user', () => {
  // 더미데이터
  let eventRecord = ref({
    eventTotalCnt: 5,
    eventTotalMinute: 150,
    eventPplCnt: 100,
    eventMsgCnt: 200
  })

  let user = ref({
    id: "ssafy1234",
    nickname: "싸피",
    email: 'ssafy@ssafy.ssafy',
    isAccountLinked : true,
  })

  let userAccount = ref({
    bank: 'KB',
    accountNumber: '123-123-11111'
  })

  // 실제론 axios 요청으로 서버에서 가져옴

  return {
    user, userAccount,
    eventRecord,
  }
})
