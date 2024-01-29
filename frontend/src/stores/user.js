import { defineStore } from 'pinia'
import { ref } from 'vue'
import axios from 'axios';
import { useRouter } from "vue-router";
const router = useRouter();
// eslint-disable-next-line no-undef
// const jwt = require('jsonwebtoken')
// const isAccessTokenExpired = (decodedToken) => {
//   if (!decodedToken || !decodedToken.exp) 
//       return true;
//   const currentTimestamp = Math.floor(Date.now() / 1000);
//   return decodedToken.exp < currentTimestamp;
// }

export const useUserStore = defineStore('user', () => {
  const API_URI = '';
  const name = ref('');
  const email = ref('');
  const image = ref('');

  const login = (provider) => {
    axios({
      url: API_URI + '/oauth2/authorization/' + provider,
      method: 'GET',
    })
    .then((res) => {
      // localStorage에 access token 저장
      const token = res.headers['Authorization'];
      localStorage.setItem('access-token', token);
   // decoding 해서 expiration date 확인할 지 협의할것
      // const decoded = jwt.verify(token, "AccessToken");

      const refreshToken = res.headers['Authorization-Refresh']
      if (refreshToken) {
        res.name = name.value;
        res.image = image.value;
        alert('로그인 성공!')
        router.push('/')
      }
      else {
        res.email = email.value
        res.name = name.value;
        res.image = image.value;
        alert('처음 시작한 회원')
        router.push('/signup')
      }

    })
    .catch((err) => {
      console.log(err);
    });
  };

  const withdraw = () => {
    axios.post({
      url: API_URI + '/withdraw',
      headers: {
        Authorization : localStorage.getItem('access-token'),
        // Authorization-Refresh : 'refreshToken'
      }    
    })
    .then(() => {
      alert('회원 탈퇴!')
      router.push('/')
    })
    .catch((err) => {
      alert(err.errorMsg)
      console.log(err);
    })
  }


  // 실제론 axios 요청으로 서버에서 가져옴

  return {
    login, name, email, image, API_URI, withdraw
    
  }
})
