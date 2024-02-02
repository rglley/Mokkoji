<template>
  <div>!!!!!!!!!!!!!</div>
</template>

<script setup>
import { onMounted, onBeforeMount, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import axios from 'axios'

const router = useRouter()
const route = useRoute()
const store = useUserStore();
const naverquerycode = ref('')

const parseJwt = (token) => {
  const base64Url = token.split('.')[1]
  const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/')
  const jsonPayload = decodeURIComponent(
    atob(base64)
      .split('')
      .map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2)
      })
      .join('')
  )
  return JSON.parse(jsonPayload)
}

onBeforeMount(() => {
  naverquerycode.value = route.query.code
})

onMounted(() => {
  axios({
    method: 'GET',
    url: 'http://localhost:8080/oauth2/naver/' + naverquerycode.value,
  }).then((res) => {
    const token = res.headers['authorization']
    const decodedToken = parseJwt(token);
    const exp = new Date(decodedToken['exp'] * 1000);
    // eslint-disable-next-line no-undef
    $cookies.set('token', token, exp)
     // res 받고 store에 저장해서 기존/신규회원 구분 후 라우팅
     store.name = res.data.name;
     store.email = res.data.email;
     store.image= res.data.image;

     if (res.data.first == true) {
      router.push('/signup')
    } else {
      store.isLogin = true;
      alert('로그인이 완료!')
      router.push('/')
    }
  })
  .catch((err) => {
    console.log(err)
  })
})
</script>

<style></style>
