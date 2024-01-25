<template>
  <div class="w-80 items-center m-5 flex flex-col gap-2 mb-16">
    <div id="title-sub-bold" class="ml-auto mr-auto mb-5">모꼬지 시작하기</div>
    <div id="box-shadow">
      <div>
        <button
          type="button"
          class="text-white bg-[#4285F4] hover:bg-[#4285F4]/90 focus:ring-4 focus:outline-none focus:ring-[#4285F4]/50 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:focus:ring-[#4285F4]/55 me-2 mb-2"
          @click="googleLogin"
        >
          <svg
            class="w-4 h-4 me-2"
            aria-hidden="true"
            xmlns="http://www.w3.org/2000/svg"
            fill="currentColor"
            viewBox="0 0 18 19"
          >
            <path
              fill-rule="evenodd"
              d="M8.842 18.083a8.8 8.8 0 0 1-8.65-8.948 8.841 8.841 0 0 1 8.8-8.652h.153a8.464 8.464 0 0 1 5.7 2.257l-2.193 2.038A5.27 5.27 0 0 0 9.09 3.4a5.882 5.882 0 0 0-.2 11.76h.124a5.091 5.091 0 0 0 5.248-4.057L14.3 11H9V8h8.34c.066.543.095 1.09.088 1.636-.086 5.053-3.463 8.449-8.4 8.449l-.186-.002Z"
              clip-rule="evenodd"
            />
          </svg>
          구글 계정으로 시작하기
        </button>
      </div>
      <div>
        <button
          type="button"
          class="text-white bg-green-400 hover:bg-green-500 me-2 mb-2"
          @click="naverLogin"
        >
          <img src="@/assets/logoN_naver.svg" class="w-4 h-4 inline-block mb-2 ml-2 mt-1 mr-2" />

          네이버 계정으로 시작하기
        </button>
      </div>
    </div>
  </div>
</template>
  
<script setup>
import axios from 'axios'

let naverLogin = () => {
  axios({
    url: '/oauth2/authorization/google',
    method: postMessage,
    data: {
      dummy: 'dummy'
    }
  })
}

let googleLogin = () => {
  axios({
    url: '/oauth2/authorization/google',
    method: postMessage,
    data: {
      dummy: 'dummy'
    }
  })
}
</script>
  
<style scoped>
</style>  