import { defineStore } from 'pinia'
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const { VITE_APPLICATION_SERVER_URL } = import.meta.env

export const findSession = defineStore(sessionId, () => {
  axios
    .get(VITE_APPLICATION_SERVER_URL + `api/sessions/${sessiondId}`)
    .then((res) => {
      console.log('sessionId get 성공')
      return res.data // sessionId
    })
    .catch((res) => {
      console.error(res)
    })
})
