<template>
  <div id="main-gradient2" class="py-20">
    <div class="mx-auto w-1/3 bg-white rounded-lg shadow-md p-20 pb-5">
      <div class="space-y-2 text-center">
        {{ email }}
        <h1 class="text-3xl font-bold">{{ name }}님의 정보</h1>
        <p class="text-slate-400">회원정보를 수정해요</p>
      </div>
      <div class="flex items-center space-x-4">
        <div class="flex-auto w-max m-5">
          <label>
            <img id="image-profile" src="@/assets/landing/profile_icon.jpg" />
          </label>
          <input
            class="mx-auto h-10 w-full rounded-md border-2 border-slate-200 bg-background px-1 py-2 text-sm file:border-0 file:bg-transparent file:text-sm"
            id="profile-picture"
            type="file"
            @change="getFileName($event.target.files)"
          />
        </div>
      </div>
      <div>
        <div class="p-2">
          <label class="text-sm font-light"> 계좌번호 </label>
          <div class="flex flex-row items-baseline">
            <select
              v-model="bank"
              id="input"
              aria-placeholder="은행명"
              required
              class="border-2 border-slate-200 py-[4px] mx-2 rounded-xl"
            >
              <option v-for="bank in banks" :key="bank" :value="bank">
                {{ bank }}
              </option>
            </select>
            <input
              id="input"
              class="w-64"
              placeholder="계좌번호를 입력하세요"
              v-model="accountNumber"
            />
          </div>
        </div>
      </div>
      <button
        class="float-right bg-natural-beige border-2 border-yellow-600 rounded-xl p-2"
        @click="update"
      >
        정보 수정하기
      </button>
      <button class="w-fit rounded-xl text-red-400 my-10 p-2" @click="withdraw">
        회원 탈퇴
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onBeforeMount } from 'vue'
import { useUserStore } from '../../stores/user'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const store = useUserStore()

const name = ref('')
const image = ref('')
const fileName = ref('')
const banks = ['KB', '농협', '기업', '카카오뱅크']
const bank = ref('')
const accountNumber = ref('')
const email = ref('')

const update = () => {
  axios
    .put('http://localhost:8080/users', {
      name: name.value,
      image: image.value,
      bank: bank.value,
      accountNumber: accountNumber.value,
      email : email.value
    }, {
      headers: {
        Authorization: $cookies.get('token')
      }
    })
    .then(() => {
      alert('회원 정보 수정!');
      router.go(-1);
    })
    .catch((err) => {
      alert(err);
    });
};

const getFileName = async (files) => {
  fileName.value = files[0].name
  await base64(files[0])
}

const base64 = (file) => {
  return new Promise((resolve) => {
    const reader = new FileReader()
    reader.onload = (e) => {
      resolve(e.target.result)
      const previewImage = document.getElementById('preview')
      previewImage.src = e.target.result
    }
    reader.readAsDataURL(file)
  })
}

const withdraw = async () => {
    try {
      const res = await axios.delete('http://localhost:8080/users', {
        headers: {
          Authorization: $cookies.get('token')
        }
      })
      .then((res) => {
        console.log(res)
        store.isLogin.value = false;
        router.push('/')
      })
    } catch (err) {
      alert(err.errorMsg)
      console.error(err)
    }
  }


onBeforeMount(() => {
  axios
    .get('http://localhost:8080/users/userinfo', {
      headers: {
        Authorization: $cookies.get('token')
      }
    })
    .then((res) => {
      console.log(res);
      name.value = res.data.name
      email.value = res.data.email
      bank.value = res.data.bank
      accountNumber.value = res.data.accountNumber
      image.value = res.data.image
    })
    .catch((error) => {
      console.error(error)
    })
})
</script>

<style></style>
