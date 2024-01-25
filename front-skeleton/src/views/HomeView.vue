<template>
  <section class="carousel" aria-label="Gallery">
    <ol class="carousel__viewport">
      <li id="carousel__slide1" tabindex="0" class="carousel__slide">
        <div class="carousel__snapper"></div>
      </li>
      <li id="carousel__slide2" tabindex="0" class="carousel__slide">
        <div class="carousel__snapper"></div>
      </li>
      <li id="carousel__slide3" tabindex="0" class="carousel__slide">
        <div class="carousel__snapper"></div>
      </li>
    </ol>
  </section>
  <section id="main-landing">
    <main>
      <!-- <section class="pb-12 pt-32 bg-[url('/src/assets/main_banner2.png')] mb-10"> -->
      <section class="pb-12 pt-32 bg-transparent mb-10 z-2 -mt-[600px]">
        <div id="container">
          <div class="text-center">
            <h1 id="title-bold">모꼬지 (Mokkoji)</h1>
            <p id="title-sub-bold">놀이나 잔치로 여러 사람이 모이는 것</p>
            <br />
            <p id="p-main">
              화상 모임 플랫폼 ‘모꼬지’를 통해 결혼식, 졸업식, 돌잔치 등 다양한 행사를 공간적인 제약
              없이 참여해보세요.
            </p>
            <p id="p-main">
              순간을 더욱 특별하게 추억하기 위한 롤링페이퍼, 포토 모자이크 기능을 제공합니다.
            </p>
            <br />
            <!-- TODO : 화면 가운데 div 박스 배치해서 회의 생성 버튼 및 input 을 넣기-->
            <div class="grid place-content-center">
              <button id="button" @click="generateMeeting">화상 모임 생성하기</button>

              <!-- TODO : submit 버튼 + invalid input 렌더링 -->
              <div>
                <div class="relative">
                  <input
                    type="text"
                    placeholder="회의 링크(ID)로 참여하기"
                    v-model="conferenceIdInput"
                    @keyup.enter="submitConferenceId"
                    class="pl-10 w-60 border-2 border-slate-500"
                  />
                  <div class="absolute top-2 right-2">
                    <button @click="submitConferenceId" class="rounded-full size-8 mt-2">
                      <img src="@/icons/send.png" class="" />
                    </button>
                  </div>
                  <ModalView v-if="showModal" :show-modal="showModal" @close-modal="toggleModal">
                    <MeetingJoinModal :conferenceIdInput="conferenceIdInput" />
                  </ModalView>
                </div>
              </div>
              <p v-if="ifInputError" style="color: red">올바른 회의 ID가 아닙니다</p>
            </div>
          </div>
        </div>
      </section>

      <!-- first -->
      <section class="my-16 mt-60 p-4">
        <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
          <div class="mx-5 px-5 text-center">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
            <br />
            <p id="p-main">모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요.</p>
            <p id="p-main">
              참여자는 모임중 언제나 텍스트, 음성, 영상 메시지를 기록할 수 있습니다.
            </p>
          </div>
          <div class="items-center">
            <img src="@/assets/main1.png" />
          </div>
        </div>
      </section>

      <!-- second -->
      <section class="my-16 p-4 bg-gray">
        <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
          <div class="justify-self-end mr-20">
            <img class="pl-0" src="@/assets/main2.png" />
          </div>
          <div class="text-center">
            <h2 id="title-sub-bold">포토 모자이크로 사진들을 한 눈에 구경하세요.</h2>
            <br />
            <p id="p-main">
              포토 모자이크 기능은 서로 다른 사진들을 조합하여 하나의 이미지를 표현합니다.
            </p>
            <p id="p-main">모임 사진을 업로드하고 포토 모자이크 기술을 경험해 보세요.</p>
          </div>
        </div>
      </section>

      <!-- third -->
      <section class="my-16 p-4">
        <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
          <div class="mx-5 mx-5 px-5 text-center">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
            <br />
            <p id="p-main">모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요.</p>
            <p id="p-main">
              참여자는 모임중 언제나 텍스트, 음성, 영상 메시지를 기록할 수 있습니다.
            </p>
          </div>
          <div class="flex-col items-center">
            <img src="@/assets/main3.png" />
          </div>
        </div>
      </section>

      <!-- fourth -->
      <section class="my-16 p-4 bg-gray">
        <div class="mt-5 grid grid-cols-2 gap-2 items-center" data-aos="fade-up">
          <div class="justify-self-end mr-20">
            <img src="@/assets/main4.png" />
          </div>
          <div class="mx-5 px-5 text-center">
            <h1 id="title-sub-bold">롤링페이퍼를 통해 친구들의 한 마디를 간직하세요.</h1>
            <br />
            <p id="p-main">모꼬지만의 템플릿을 이용하여 롤링페이퍼를 디자인하고 추억하세요.</p>
            <p id="p-main">
              참여자는 모임중 언제나 텍스트, 음성, 영상 메시지를 기록할 수 있습니다.
            </p>
          </div>
        </div>
      </section>

      <!--logo-->
      <section>
        <div class="grid grid-cols-2 gap-2 items-center">
          <div class="justify-self-end mr-20">
            <img src="@/assets/mokkoji_logo.png" class="max-w-xs" />
          </div>
          <div class="mx-5 mx-5 px-5 ml-10">
            <h1 id="title-sub-bold">모꼬지를 사용하고 싶으신가요?</h1>
            <button @click="toTop" class="animate-spin">처음으로 돌아가기</button>
          </div>
        </div>
      </section>
    </main>
  </section>
</template>

<script setup>
// TODO : 비회원 사용자 회의 시작 시 안내 모달 띄워주기
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter()

// toast : custom alert
import { toast } from 'vue3-toastify'
import 'vue3-toastify/dist/index.css'

import ModalView from './ModalView.vue'
import MeetingJoinModal from '../components/modal/MeetingJoinModal.vue'

let conferenceIdInput = ref('')
let ifInputError = ref(false)
let isLogin = ref(false) // 나중에 store에서 받을 예정

let showModal = ref(false)

const toggleModal = () => {
  showModal.value = !showModal.value
}

let submitConferenceId = () => {
  console.log(conferenceIdInput.value)
  // id input을 백엔드 서버로 axios 전송
  // axios 반응이 정상이면 input값 id로 참가
  // 아니면 알람
  // 임시로 만든 올바른 id ='qwer'
  let validId = 'qwer'
  if (conferenceIdInput.value == validId) {
    // 로그인하지 않았다면 모달
    if (!isLogin.value) {
      // 모달 띄우고
      showModal.value = true
    }
    ifInputError.value = false
    // 회의 이동
    router.push('/')
  } else {
    ifInputError.value = true
    conferenceIdInput.value = ''
  }
}

// 회의 생성 로직
// 로그인하지 않았다면 모달
// 로그인하지 않았으면 alert
let generateMeeting = () => {
  if (isLogin.value) {
    router.push('#') // 회의 리다이렉트(예정)
  } else {
    toast('로그인이 필요합니다', {
      theme: 'auto',
      type: 'warning',
      transition: 'flip',
      autoClose: 1000
    })
  }
}

let toTop = () => {
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  })
}
</script>

<style scoped>
@keyframes tonext {
  75% {
    left: 0;
  }
  95% {
    left: 100%;
  }
  98% {
    left: 100%;
  }
  99% {
    left: 0;
  }
}

@keyframes tostart {

  25% {
    left: -200%;
  }
  45% {
    left: -300%;
  }
  99% {
    left: 0%;
  }

}

@keyframes snap {
  96% {
    scroll-snap-align: center;
  }
  97% {
    scroll-snap-align: none;
  }
  99% {
    scroll-snap-align: none;
  }
  100% {
    scroll-snap-align: center;
  }
}

* {
  scrollbar-color: transparent transparent;
  scrollbar-width: 0px;
}

*::-webkit-scrollbar {
  width: 0;
}

*::-webkit-scrollbar-track {
  background: transparent;
}

*::-webkit-scrollbar-thumb {
  background: transparent;
  border: none;
}

* {
  -ms-overflow-style: none;
}

.carousel {
  position: relative;
  padding-top: 50%;
  filter: drop-shadow(0 0 10px #0003);
  perspective: 100px;
  z-index: -1;
}

.carousel__viewport {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  display: flex;
  overflow-x: scroll;
  counter-reset: item;
  scroll-behavior: smooth;
  scroll-snap-type: x mandatory;
}

.carousel__slide {
  position: relative;
  flex: 0 0 100%;
  width: 100%;
  counter-increment: item;
}

.carousel__slide:first-child {
  background-image: url('@/assets/dummy_profile.jpg');
}

.carousel__slide:nth-child(2) {
  background-image: url('@/assets/main_banner.png');
}

.carousel__slide:last-child {
  background-image: url('@/assets/dummy_profile.jpg');
}

.carousel__snapper {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

@media (hover: hover) {
  .carousel__snapper {
    animation-name: tonext, snap;
    animation-timing-function: ease-in-out;
    animation-duration: 20s;
    animation-iteration-count: infinite;
  }

  .carousel__slide:last-child .carousel__snapper {
    animation-delay: 20s;
    animation-timing-function: ease-in-out;
    animation-duration: 20s;
    animation-name: tostart, snap;
  }
}

.carousel:hover .carousel__snapper,
.carousel:focus-within .carousel__snapper {
  animation-name: none;
}
</style>