import { defineStore } from 'pinia'
import { ref } from 'vue'
import axios from 'axios';
import tokenService from '@/services/token.service';
import { useRouter } from 'vue-router';

export const useUserStore = defineStore('user', () => {
  const router = useRouter();

  const API_URI = 'localhost:8080';
  const name = ref('');
  const email = ref('');
  const image = ref('');
  const isLogin = ref(false);

  const login = (provider) => {
    axios({
      url: API_URI + 'oauth2/authorization/' + provider,
      method: 'GET',
    })
    .then((res) => {
      console.log(res)
      isLogin.value = true;
      const token = res.headers.get('Authorization');
      // cookie에 token 및 user 정보 저장
      tokenService.setLocalAccessToken(token);
      tokenService.setUser(res);

      const refreshToken = res.headers.get('Authorization-Refresh')
      // 기존 회원 로그인
      if (refreshToken) {
        tokenService.setLocalRefreshToken(token);
        res.name = name.value;
        res.image = image.value;
        alert('로그인 성공!')
        router.push('/')
      }
      // 신규 회원 로그인
      else {
        res.email = email.value
        res.name = name.value;
        res.image = image.value;
        alert('처음 시작한 회원')
        router.push('/signup')
      }

    })
    .catch((err) => {
      // isLogin.value = false;
      console.log(err);
    });
  };

  const withdraw = () => {
    axios.post({
      url: API_URI + '/withdraw', 
    })
    .then(() => {
      // cookie 정보 삭제
      tokenService.removeUser()
      alert('회원 탈퇴!')
      isLogin.value = false;
      router.push('/')
    })
    .catch((err) => {
      alert(err.errorMsg)
      console.log(err);
    })
  }

  return {
    name, email, image, API_URI, isLogin, 
    login, withdraw
  }
})
