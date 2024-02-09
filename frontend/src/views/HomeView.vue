<template>
  <main id="main-landing">
    <section class="pt-[20vh] z-2 h-max pb-[20lvh]" id="main-gradient2">
      <div id="flex" class="ml-[3vw] flex justify-center items-center">
        <div
          class="text-center ml-12 w-1/2 flex flex-col justify-center items-center animate-in fade-in-10 delay-1000 duration-1000 text-xl whitespace-wrap"
        >
          <div>
            <h1 id="title">모꼬지</h1>
            <br />
            <div class="text-2xl">
              <p>
                화상 모임 플랫폼 ‘모꼬지’를 통해
                <strong class="text-purple-500">결혼식, 졸업식, 돌잔치</strong> 등
              </p>
              <p>
                다양한 행사를
                <strong class="text-green-500">공간적인 제약 없이</strong> 참여해보세요.
              </p>
              <br />
              <p>순간을 더욱 특별하게 추억하기 위한</p>
              <p>
                <strong class="text-purple-500">롤링페이퍼, 포토 모자이크 기능</strong> 을
                제공합니다.
              </p>
            </div>
          </div>
          <div class="flex flex-col mt-[7vh] w-2/3 text-base justify-center items-center">
            <button
              class="my-2 py-2 rounded-xl hover:bg-purple-500 text-white duration-300 w-60 bg-purple-400"
              @click="createMeeting"
            >
              화상 모임 생성하기
            </button>
            <div>
              <div class="relative">
                <input
                  type="text"
                  placeholder="회의 링크(ID)로 참여하기"
                  v-model="conferenceIdInput"
                  @keyup.enter="submitConferenceId"
                  class="mx-0 pl-10 w-60 border-2 border-slate-500 rounded-xl"
                />
                <div class="absolute top-[1.5vh] right-1">
                  <button
                    @click="submitConferenceId"
                    class="rounded-full size-8 mt-2 z-10 bg-primary hover:bg-primary3 duration-300"
                  >
                    <img src="@/assets/landing/send.png" />
                  </button>
                </div>
                <ModalView v-if="isModal" :show-modal="isModal" @close-modal="showModal">
                  <MeetingJoinModal :conferenceIdInput="conferenceIdInput" />
                </ModalView>
              </div>
              <p v-if="isInputError" style="color: red">올바른 회의 ID가 아닙니다</p>
            </div>
          </div>
        </div>
        <div class="w-1/2">
          <!-- <img src="@/assets/landing/wedding.svg" /> -->
          <div class="w-[70lvh] h-[70lvh] border-8 border-white cursor-grab rounded-r-xl">
            <swiper
              :spaceBetween="30"
              :autoplay="{
                delay: 2500,
                pauseOnMouseEnter: true,
                waitForTransition: 200
              }"
              :pagination="{
                clickable: true
              }"
              :loop="true"
              :modules="modules"
              class="rounded-r-xl"
            >
              <swiper-slide v-for="photo in photos" :key="photo">
                <img :src="`src/assets/landing/${photo}`" class="rounded-r-xl" />
              </swiper-slide>
            </swiper>
          </div>
        </div>
      </div>
    </section>

    <!-- first -->
    <section id="section-main">
      <div class="w-full overflow-hidden">
        <svg
          id="svg-curve-1"
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 1200 120"
          preserveAspectRatio="none"
        >
          <path
            d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
          ></path>
        </svg>
      </div>
      <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
        <div class="mx-5 px-5 text-center">
          <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의</h1>
          <h1 id="title-sub-bold">한 마디를 간직하세요.</h1>

          <br />
          <p id="p-main">모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요.</p>
          <p id="p-main">참여자는 모임중 언제나 텍스트, 음성, 영상 메시지를 기록할 수 있습니다.</p>
        </div>
        <div class="items-center">
          <img src="@/assets/landing/main1.png" />
        </div>
      </div>
      <div id="space"></div>
    </section>

    <!-- second -->
    <section id="section-main" class="bg-natural-yellow">
      <div class="w-full overflow-hidden">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 1200 120"
          preserveAspectRatio="none"
          id="svg-curve-2"
        >
          <path
            d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
            class="shape-fill"
          ></path>
        </svg>
      </div>
      <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
        <div class="justify-self-end mr-20">
          <img class="pl-0" src="@/assets/landing/main2.png" />
        </div>
        <div class="text-center">
          <h2 id="title-sub-bold">포토 모자이크로 사진들을</h2>
          <h2 id="title-sub-bold">한 눈에 구경하세요.</h2>

          <br />
          <p id="p-main">
            포토 모자이크 기능은 서로 다른 사진들을 조합하여 하나의 이미지를 표현합니다.
          </p>
          <p id="p-main">모임 사진을 업로드하고 포토 모자이크 기술을 경험해 보세요.</p>
        </div>
      </div>
      <div id="space"></div>
    </section>

    <!-- third -->
    <section id="section-main" class="bg-natural-beige">
      <div class="w-full overflow-hidden">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 1200 120"
          preserveAspectRatio="none"
          id="svg-curve-3"
        >
          <path
            d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
            class="shape-fill"
          ></path>
        </svg>
      </div>
      <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
        <div class="mx-5 text-center">
          <h1 id="title-sub-bold">소그룹을 형성하여 친구들과</h1>
          <h1 id="title-sub-bold">자유롭게 소통하세요.</h1>

          <br />
          <p id="p-main">소그룹 기능을 이용하여 소통하고 싶은 다른 참여자들과</p>
          <p id="p-main">사적인 공간에서 자유롭게 이야기 할 수 있습니다.</p>
        </div>
        <div class="flex-col items-center">
          <img src="@/assets/landing/main3.png" />
        </div>
      </div>
      <div id="space"></div>
    </section>

    <!-- fourth -->
    <section id="section-main" class="bg-primary2">
      <div class="w-full overflow-hidden">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          viewBox="0 0 1200 120"
          preserveAspectRatio="none"
          id="svg-curve-4"
        >
          <path
            d="M321.39,56.44c58-10.79,114.16-30.13,172-41.86,82.39-16.72,168.19-17.73,250.45-.39C823.78,31,906.67,72,985.66,92.83c70.05,18.48,146.53,26.09,214.34,3V0H0V27.35A600.21,600.21,0,0,0,321.39,56.44Z"
            class="shape-fill"
          ></path>
        </svg>
      </div>
      <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
        <div class="justify-self-end mr-20">
          <img src="@/assets/landing/main4.png" />
        </div>
        <div class="mx-5 px-5 text-center">
          <h1 id="title-sub-bold">QR코드를 통해 축하금을</h1>
          <h1 id="title-sub-bold">간편하게 보내세요.</h1>

          <br />
          <p id="p-main">화상 모임 내에서 주최자의 계좌와 연동된 QR코드가 제공됩니다.</p>
          <p id="p-main">메세지로 다 담지 못한 축하하는 마음을 전달해보세요.</p>
        </div>
      </div>
      <div id="space"></div>
    </section>

    <!--logo-->
    <section class="flex items-center justify-center py-20 bg-primary2">
      <img src="@/assets/logo/mokkoji_logo.png" class="w-[25vh] px-[5vh]" />

      <h1 class="text-4xl font-bold">모꼬지를 사용하고 싶으신가요?</h1>
      <button
        @click="toTop"
        class="animate-bounce duration-700 my-2 py-2 rounded-xl hover:bg-blue-500 text-white duration-300 w-fit px-[2vh] mx-[2vh] bg-blue-400"
      >
        처음으로 돌아가기
      </button>
    </section>
  </main>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { toast } from 'vue3-toastify'
import { Swiper, SwiperSlide } from 'swiper/vue'
import { Autoplay, Pagination } from 'swiper/modules'
import { useSessionStore } from '@/stores/meeting'
import ModalView from './ModalView.vue'
import MeetingJoinModal from '../components/modal/home/MeetingJoinModal.vue'
import 'vue3-toastify/dist/index.css'
import 'swiper/css'
import 'swiper/css/pagination'

const emit = defineEmits(['load-home'])

const store = useSessionStore()
const router = useRouter()

const conferenceIdInput = ref('')
const isInputError = ref(false)
const isModal = ref(false)

const showModal = () => {
  isModal.value = !isModal.value
}

const submitConferenceId = async () => {
  const result = await store.findSession(conferenceIdInput.value)

  if (result === 'success') {
    isInputError.value = false

    if ($cookies.get('user') !== null) {
      router.push('/meetings')
    } else {
      isModal.value = true
    }
  } else {
    isInputError.value = true
    conferenceIdInput.value = ''
  }
}

const createMeeting = () => {
  store.createSession()

  // if ($cookies.get('user') !== null) {
  //   store.createSession()
  // } else {
  //   toast('로그인이 필요합니다', {
  //     theme: 'auto',
  //     type: 'warning',
  //     transition: 'flip',
  //     autoClose: 1000
  //   })
  // }
}

const toTop = () => {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  })
}
const modules = [Autoplay, Pagination]

const photos = ['carousel1.png', 'carousel2.png', 'carousel3.png', 'carousel4.png']

onMounted(() => {
  emit('load-home')
})
</script>

<style></style>
