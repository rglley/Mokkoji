<template>
  <header :class="{ 'transparent-header': isTransparent }" class="fixed h-32 z-10 my-auto w-full bg-transparent">
    <nav class="w-full flex pl-5">
      <a href="/" class="flex items-center rtl:l:space-x-reverse">
        <img
          src="/src/assets/logo/mokkoji_logo.png"
          class="h-28 mx-5 transition ease-in-out hover:animate-pulse"
          alt="모꼬지 로고"
        />
        <!-- <span id="title-bold">모꼬지</span> -->
      </a>

      <div class="ml-auto mr-1 self-center">
        <ul class="font-medium flex md:flex-row ml-10">
          <li>
            <button id="button-header"><a href="/">HOME</a></button>
          </li>
          <li v-if="!isLogin">
            <!-- <li v-if="store.isLogin"> -->
            <button id="button-header" @click="showLoginModal">로그인</button>
            <ModalView v-if="isLoginModal" :show-modal="isLoginModal" @close-modal="showLoginModal">
              <LoginModal />
            </ModalView>
          </li>
          <li v-else>
            <button
              id="button-header"
              data-dropdown-toggle="dropdown"
              data-dropdown-trigger="click"
              to="/mypage"
            >
              내 서비스
            </button>
            <div
              id="dropdown"
              class="z-10 hidden bg-white divide-y divide-slate-200 rounded-lg w-32"
            >
              <ul aria-labelledby="dropdownHoverButton" class="w-50">
                <li id="li-dropdown">
                  <router-link to="mypage">마이페이지</router-link>
                </li>
                <li id="li-dropdown">
                  <router-link to="eventlist">내 결과물</router-link>
                </li>
                <li id="li-dropdown">
                  <a @click="logout">로그아웃</a>
                  <!-- <a @click="store.logout">로그아웃</a> -->
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
import { useUserStore } from '@/stores/user'
import router from '@/router'
import ModalView from '@/views/ModalView.vue'
import LoginModal from '@/components/modal/home/LoginModal.vue'

const store = useUserStore()

const isLogin = ref(true)
const isLoginModal = ref(false)
const isTransparent = ref(false)

const showLoginModal = () => {
  isLoginModal.value = !isLoginModal.value
}

const logout = () => {
  isLogin.value = false
}

const limitHeight = 500;

const handleScroll = () => {
  if (scrollY > limitHeight)
    isTransparent.value = true;
  if (scrollY < limitHeight)
    isTransparent.value = false;
}

onMounted(() => {
  initFlowbite()
  window.addEventListener('scroll', handleScroll);
})
</script>

<style>
.transparent-header {
  @apply opacity-0 transition-opacity duration-500
}

li {
  @apply m-2 p-5
}
</style>
