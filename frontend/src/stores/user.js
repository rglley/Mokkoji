import { defineStore } from 'pinia'
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import tokenService from '@/services/token.service'

export const useUserStore = defineStore('user', () => {
  const router = useRouter()
  const name = ref('')
  const email = ref('')
  const image = ref('')
  const isLogin = ref(false)

  const logout = () => {
    tokenService.removeUser()
    isLogin.value = false
    router.push('/')
  }

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

  onMounted(() => {
    getLoginStatus;
  
  })

  return {
    name,
    email,
    image,
    isLogin,
    logout,
    getLoginStatus,
  }
})
