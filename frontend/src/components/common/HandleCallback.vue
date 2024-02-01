<template>
  <div>!!!!!!!!!!!!!</div>
</template>

<script setup>
import { onMounted, onBeforeMount, ref } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
// const store = useUserStore()

// const state = {
//   // client id 보안처리로 env 넣기
//   naverClientId: 'Y_veSn1KXI8bYwTv29AA',
//   clientSecret: 'ZrcTvy2t84',
//   callbackUrl: 'http://localhost:8080/login/oauth2',
//   code: route.query.code,
//   states: route.query.state, // csrf 공격을 방지하기 위해 애플리케이션에서 생성한 상태 토큰값으로 URL 인코딩을 적용한 값을 사용
//   access_token: '', // 발급받은 access_token 저장을 위한 변수
//   refresh_token: '', // 발급받은 refresh_token 저장을 위한 변수
//   userid: '',
//   image: ''
// }

// // callback url
// const naverCallback = async () => {
//   console.log('route.query.code => ', route.query.code) //파라미터로 전달받은 code값
//   console.log('route.query.states => ', route.query.state) //파라미터로 전달받은 state값

//   const url = `/oauth2.0/token?grant_type=authorization_code&client_id=${state.naverClientId}&client_secret=${state.clientSecret}&code=${state.code}&state=${state.states}`
//   const headers = {
//     'X-Naver-Client-Id': state.naverClientId,
//     'X-Naver-Client-Secret': state.clientSecret
//   }
//   const { data } = await axios.get(url, { headers })

//   console.log('data => ', data)

//   console.log('data.access_token => ', data.access_token)
//   state.access_token = data.access_token

//   console.log('data.refresh_token => ', data.refresh_token)
//   state.refresh_token = data.refresh_token

//   naverUserInfo()
// }

// // 사용자 정보 전달받기
// const naverUserInfo = async () => {
//   const url = `/v1/nid/me`
//   let header = 'Bearer ' + state.access_token
//   const headers = { Authorization: header }
//   console.log('headers => ', headers)
//   const { data } = await axios.get(url, { headers })
//   console.log('*****naverUserInfo data***** => ', data)

//   // 변수에 값 넣기
//   state.nickname = data.response.nickname
//   state.image = data.response.profile_image

//   if (data.response.email === null) {
//     alert('가입이력이 없습니다.')
//     localStorage.setItem('state', state);
//     // router.push('/signup')
//   }
//   else {
//     state.userid = data.response.email
//   }
// }

const naverquerycode = ref('')

onBeforeMount(() => {
  console.log(route)
  console.log(route.query.state)
  console.log(route.query.code)
  naverquerycode.value = route.query.code;
})

onMounted(() => {
  axios({
    method: 'GET',
    url: 'http://localhost:8080/oauth2/naver/' + naverquerycode.value,
  })
  .then((res) => {
    console.log(res)
  })
})

</script>

<style></style>
