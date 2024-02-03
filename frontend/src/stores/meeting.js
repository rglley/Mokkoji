import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const { VITE_APPLICATION_SERVER_URL } = import.meta.env

export const useSessionStore = defineStore('session', () => {
  const findResult = ref('')
  const findSession = (sessionId) => {
    axios
      .get(VITE_APPLICATION_SERVER_URL + `api/meetings/sessions/${sessionId}`)
      .then((res) => {
        if (res.data.sessionId !== undefined) {
          console.log('sessionId get 성공')
          findResult.value = 'success'
        } else {
          console.log('sessionId get 실패')
          findResult.value = 'fail'
        }
      })
      .catch((res) => {
        console.error(res)
      })
  }

  return {
    findSession,
    findResult
  }
})
