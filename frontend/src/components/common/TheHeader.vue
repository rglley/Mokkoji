<template>
  <header
    :class="{ 'transparent-header': isTransparent }"
    class="fixed h-32 z-10 my-auto w-full bg-transparent"
  >
    <nav class="w-full flex px-[2vw]">
      <router-link to="/" class="h-28 flex items-center rtl:l:space-x-reverse">
        <img
          src="/src/assets/logo/mokkoji_logo.png"
          class="h-24 mx-5 transition ease-in-out hover:animate-pulse"
          alt="모꼬지 로고"
        />
        <!-- <span id="title-bold">모꼬지</span> -->
      </router-link>

      <div class="ml-auto mr-1 self-center">
        <ul class="font-medium flex md:flex-row ml-10">
          <li>
            <button id="button-header"><a href="/">홈으로</a></button>
          </li>
          <!-- <li v-if="!isLogin"> -->
          <li v-if="!isLogin">
            <button id="button-header" @click="showLoginModal">로그인</button>
            <ModalView v-if="isLoginModal" :show-modal="isLoginModal" @close-modal="showLoginModal">
              <LoginModal />
            </ModalView>
          </li>
          <li v-else>
            <img id="image-profile" :src="image" class="w-10" />
            <p>{{ name }}님</p>
            <button
              id="button-header"
              data-dropdown-toggle="dropdown"
              data-dropdown-trigger="click"
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
import { ref, onBeforeMount } from 'vue'
import { initFlowbite } from 'flowbite'
import { useRouter } from 'vue-router'
import ModalView from '@/views/ModalView.vue'
import LoginModal from '@/components/modal/home/LoginModal.vue'
import tokenService from '@/services/token.service'

const router = useRouter()
const isLoginModal = ref(false)
const isTransparent = ref(false)
const isLogin = ref(false)
const image = ref('')
const name = ref('')

const showLoginModal = () => {
  isLoginModal.value = !isLoginModal.value
}

const limitHeight = 200

const handleScroll = () => {
  if (scrollY > limitHeight) isTransparent.value = true
  if (scrollY < limitHeight) isTransparent.value = false
}

initFlowbite()

const logout = () => {
  tokenService.removeUser()
  isLogin.value = false
  router.push('/')
}

onBeforeMount(() => {
  window.addEventListener('scroll', handleScroll)
  console.log($cookies.get('user'))
  if ($cookies.isKey('user')) {
    isLogin.value = true
    image.value = $cookies.get('user').image
    name.value = $cookies.get('user').name
  }
})
</script>

<style>
.transparent-header {
  @apply opacity-0 transition-opacity duration-500;
}

li {
  @apply m-2 p-5;
}
</style>
