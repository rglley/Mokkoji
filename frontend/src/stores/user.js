import { defineStore } from 'pinia'
import { ref, onBeforeMount } from 'vue'
import tokenService from '@/services/token.service'

export const useUserStore = defineStore('user', () => {
  const name = ref('')
  const email = ref('')
  const image = ref('')
  const isLogin = ref(false)

  const getLoginStatus = () => {
    try {
      const user = tokenService.getUser()
      user.name = name.value
      user.email = email.value
      user.image = image.value
      isLogin.value = true
    } catch (err) {
      console.log(err);
    }
  }

  onBeforeMount(() => {
    getLoginStatus;
  
  })

  return {
    name,
    email,
    image,
    isLogin,
    getLoginStatus,
  }
})
