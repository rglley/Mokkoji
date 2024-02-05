import { defineStore } from 'pinia'
import { reactive, ref } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const { VITE_API_URL } = import.meta.env

export const useSessionStore = defineStore('session', () => {
  const router = useRouter()
  const isHost = ref(false)
  const mainSessionId = ref('')

  const createSession = () => {
    isHost.value = true
    router.push('/meetings')
  }

  const findSession = async (sessionId) => {
    try {
      const res = await axios.get(VITE_API_URL + `api/v1/meetings/sessions/${sessionId}`)
      if (res.data.sessionId !== undefined) {
        mainSessionId.value = res.data.sessionId
        return 'success'
      } else {
        return 'fail'
      }
    } catch (error) {
      console.error(error)
    }
  }

  const deleteSession = async (sessionId) => {
    try {
      const res = await axios.delete(VITE_API_URL + `api/v1/meetings/sessions/${sessionId}`)
    } catch (error) {
      console.error(error)
    }
  }

  return {
    isHost,
    mainSessionId,
    createSession,
    findSession,
    deleteSession
  }
})
