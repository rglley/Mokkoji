<template>
  <div
    class="bg-white flex mt-0 w-full flex-col justify-center items-center px-16 py-12 max-md:max-w-full max-md:px-5"
  >
    <div id="card-div">
      <div class="absolute -mt-28 mx-10 justify-center">
        <img alt="프로필 사진" id="image-profile" :src="userData.image" />
      </div>
      <div class="gap-5 flex max-md:flex-col max-md:gap-2 mt-20">
        <div class="flex flex-col w-6/12 ml-10">
          <span class="flex flex-col mt-10 max-md:mt-10"
            ><div class="text-black text-3xl font-bold self-stretch">
              {{ userData.name }}님
            </div>
            <div class="text-black text-xl self-stretch mt-2.5">{{ userData.email }}</div>
            <span class="mt-16 pl-2 pr-4"
              ><div class="text-black text-3xl">
                계좌 등록
                <a class="text-2xl text-red-500" v-if="userData.accountRegistered">O</a>
                <a class="text-2xl text-red-500" v-else>X </a>
              </div>
            </span>
            <div class="text-sm font-light m-2 text-wrap max-w-52">
              계좌를 등록하시면 참가자들의 마음을 받을 수 있어요
            </div>
            <div class="mt-20">
              <a href="/mypage/detail" id="button-submit">회원정보 수정</a>
            </div>
          </span>
        </div>
        <div class="flex flex-col justify-around items-stretch mr-10">
          <div>
            <span class="flex items-stretch justify-between">
              <div class="text-black text-2xl font-bold">활동 기록</div>
              <!-- TODO : 결과물 보기 링크 -->
              <a href="#" class="text-1xl">상세보기 ></a>
            </span>
          </div>
          <div class="flex items-stretch justify-between gap-5 mt-6 pr-1.5">
            <div id="div-stat">
              모꼬지 주최<br /><span class="text-purple-400">{{
                userData.eventCount
              }}</span
              >번<br />
            </div>
            <div id="div-stat">
              참여자 수<br /><span class="text-purple-400">{{
                userData.totalParticipant
              }}</span
              >명
            </div>
          </div>
          <div class="flex items-stretch justify-between gap-5 mt-6 pr-1.5">
            <div id="div-stat">
              모꼬지 <br /><span class="text-purple-400">{{ userData.totalTime }}</span
              >시간
              <!-- <span class="text-purple-400">{{ totalMin }}</span -->
              <!-- >분 -->
            </div>
            <div id="div-stat">
              받은 메세지<br /><span class="text-purple-400">{{
                userData.totalMessage
              }}</span
              >개
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from '@/services/api'

const userData  = ref({})
const num = 0;
const getUserDetail = () => {
-    axios.get(import.meta.env.VITE_SERVER + '/users/mypage')
    .then((res) => {
      userData.value = res.data;
    })
  .catch((err) => {
    console.error(err)
  })
}

onMounted(() => {
  getUserDetail()
})
</script>

<style></style>
