<template>
  <header class="relative h-32 top-0 z-10 w-full bg-[#fbf8f4]">
    <!-- <nav class="w-full bg-opacity-35 p-2 bg-primary flex pl-5"> -->
    <nav class="w-full flex pl-5">
      <a href="/" class="flex items-center rtl:l:space-x-reverse">
        <img
          src="/src/assets/logo/mokkoji_logo.png"
          class="h-28 mx-5 transition ease-in-out hover:animate-pulse"
          alt="모꼬지 로고"
        />
        <!-- <span id="title-bold">모꼬지</span> -->
      </a>

      <div class="ml-auto mr-10 self-center">
        <ul class="font-medium flex md:flex-row ml-10">
          <li>
            <button id="button-header"><a href="/">HOME</a></button>
          </li>
          <li v-if="!isLogin">
            <button id="button-header" @click="showLoginModal">로그인</button>
            <ModalView
              v-if="isLoginModal"
              :show-modal="isLoginModal"
              @close-modal="showLoginModal"
            >
              <LoginModal />
            </ModalView>
          </li>
          <li v-else>
            <button
              id="button-header"
              data-dropdown-toggle="dropdownHover"
              data-dropdown-trigger="hover"
              to="/mypage"
            >
              내 서비스
            </button>
            <div
              id="dropdownHover"
              class="z-10 hidden bg-white divide-y divide-slate-200 rounded-lg w-32"
            >
              <ul aria-labelledby="dropdownHoverButton" class="w-50">
                <li id="li-dropdown">
                  <a href="mypage">마이페이지</a>
                </li>
                <li id="li-dropdown">
                  <a href="#">내 결과물</a>
                </li>
                <li id="li-dropdown">
                  <a @click="logout">로그아웃</a>
                </li>
              </ul>
            </div>
          </li>
        </ul>
      </div>
    </nav>
  </header>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { initFlowbite } from 'flowbite'

import ModalView from '@/views/ModalView.vue'
import LoginModal from '@/components/modal/LoginModal.vue'

onMounted(() => {
  initFlowbite()
})
const isLogin = ref(true)

const isLoginModal = ref(false)

const showLoginModal = () => {
  isLoginModal.value = !isLoginModal.value
}

const logout = () => {
  //sessionStorage.delete('login-token')
  isLogin.value = false
}
</script>

<style scoped></style>
