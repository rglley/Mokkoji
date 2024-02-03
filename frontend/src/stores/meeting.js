import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const { VITE_APPLICATION_SERVER_URL } = import.meta.env

export const useSessionStore = defineStore('session', () => {
  const findSession = async (sessionId) => {
    axios
      .get(VITE_APPLICATION_SERVER_URL + `api/meetings/sessions/${sessionId}`)
      .then((res) => {
        if (res.data.sessionId !== undefined) {
          console.log('sessionId get 성공')
          return 'success'
        } else {
          console.log('sessionId get 실패')
          return 'fail'
        }
      })
      .catch((res) => {
        console.error(res)
      })
  }

  return {
    findSession
  }
})
