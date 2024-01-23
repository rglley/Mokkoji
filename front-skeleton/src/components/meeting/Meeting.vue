<template>
  <main class="mx-24 h-full">
    <!-- 회의 화면 -->
    <section class="pt-4 h-4/5 flex flex-row justify-center">
      <div class="w-4/5">
        <!-- 메인 회의 -->
        <section id="main-container" class="w-full h-full flex flex-col">
          <div class="mb-2 pb-2 flex whitespace-nowrap overflow-x-scroll overflow-hidden gap-2">
            <!-- 참가자 화면 -->
            <div id="sub-video" class="basis-1/4 flex space-x-3 rounded-xl">
              <user-video
                v-for="sub in state.subscribers"
                :key="sub.stream.connection.connectionId"
                :stream-manager="sub"
                @click.native="updateMainVideoStreamManager(sub)"
                class="rounded-xl flex-none"
              />
            </div>
          </div>
          <!-- 메인 화면 -->
          <div id="main-video" class="flex justify-center rounded-xl">
            <user-video :stream-manager="state.mainStreamManager" class="rounded-xl" />
          </div>
        </section>
      </div>
      <!-- 참여자 목록, 채팅방-->
      <div v-if="isList || isChat" class="pl-2 w-1/5 flex flex-col">
        <div v-if="isList" class="basis-full bg-purple-200 rounded-xl">참여자 목록</div>
        <div v-if="isList && isChat" class="mb-2"></div>
        <div v-if="isChat" class="basis-full bg-purple-200 rounded-xl">채팅방</div>
      </div>
    </section>
    <!-- 기능 버튼 -->
    <section class="pt-10">
      <div class="flex">
        <div class="basis-2/12 flex flex-wrap">
          <button>아이콘</button>
          <button class="h-10 w-16 ml-2 bg-gray-400 text-white text-xs rounded-xl">
            화면 배치
          </button>
        </div>
        <div class="basis-11/12 flex flex-wrap justify-center space-x-10">
          <div id="button-container" class="flex flex-col items-center">
            <button id="button" class="bg-purple-100"></button>
            <span>마이크 ON</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-purple-100"></button>
            <span>카메라</span>
          </div>
          <div id="button-container" v-if="isMain">
            <button id="button" class="bg-pink-100" @click="changeMeetingType"></button>
            <span>소그룹</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-pink-100" @click="toggleLetterModal"></button>
            <span>편지쓰기</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-pink-100"></button>
            <span>선물하기</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-pink-100"></button>
            <span>사진찍기</span>
          </div>
        </div>
        <div class="ml-2 basis-3/12 flex flex-row flex-wrap space-x-3 justify-end">
          <div id="button-container">
            <button id="button" class="bg-purple-100" @click="changeListStatus"></button>
            <span>참여자 목록</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-purple-100" @click="changeChatStatus"></button>
            <span>채팅</span>
          </div>
          <div>
            <button class="w-20 h-10 bg-red-500 text-white rounded-3xl" @click="leaveSession">
              나가기
            </button>
          </div>
        </div>
      </div>
    </section>
    <LetterModal v-if="showLetterModal" />
  </main>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import axios from 'axios'
import { OpenVidu } from 'openvidu-browser'
import UserVideo from './UserVideo.vue'
import LetterModal from '@/components/modal/LetterModal.vue'
import router from '../../router'

// 메인 회의
let isMain = ref(true)
// 참여자 목록
let isList = ref(false)
// 채팅
let isChat = ref(false)
// 편지쓰기
let showLetterModal = ref(false)

// 메인 회의 / 소그룹 이동
let changeMeetingType = () => {
  isMain.value = !isMain.value
}

// 채팅 on / off
let changeChatStatus = () => {
  isChat.value = !isChat.value
}

// 참여자 목록 on / off
let changeListStatus = () => {
  isList.value = !isList.value
}

// 편지 쓰기 모달 생성
let toggleLetterModal = () => {
  showLetterModal.value = !showLetterModal.value
}

// ---------------- OpenVidu 관련 ----------------------

axios.defaults.headers.post['Content-Type'] = 'application/json'

const APPLICATION_SERVER_URL = 'http://localhost:5000/'

const state = reactive({
  // Openvidu 객체
  OV: undefined,
  session: undefined,
  mainStreamManager: undefined,
  publisher: undefined,
  subscribers: [],
  mySessionId: undefined,
  myUserName: 'participant' + Math.floor(Math.random() * 100),
  openviduToken: undefined,
  isMic: true,
  isCamera: true,
  isSpeaker: true,
  isChat: true
})

// 세션 참가하기
const joinSession = () => {
  // 1) Openvidu 객체 생성
  state.OV = new OpenVidu()

  // 2) 세션 시작
  state.session = state.OV.initSession()

  // 3) 세션에서 이벤트 발생 시 동작하는 행동 구체화

  // 새로운 참가자 입장
  state.session.on('streamCreated', ({ stream }) => {
    console.log('새로운 참가자 입장')
    const subscriber = state.session.subscribe(stream, undefined)
    state.subscribers.push(subscriber)
    console.log(subscriber)
  })

  // 참가자 퇴장
  state.session.on('streamDestroyed', ({ stream }) => {
    const index = state.subscribers.indexOf(stream.streamManager, 0)
    if (index >= 0) {
      state.subscribers.splice(index, 1)
    }
  })

  // 비동기 예외
  state.session.on('exception', ({ exception }) => {
    console.warn(exception)
  })

  // 4) 유효한 사용자 토큰으로 세션에 연결하기

  // OpenVidu 배포에서 토큰 가져오기
  getToken(state.mySessionId)
    .then((token) => {
      console.log(state.session)
      // 첫 번째 파라미터는 토큰이다. Second param can be retrieved by every user on event
      // 'streamCreated' (property Stream.connection.data), and will be appended to DOM as the user's nickname
      state.session.connect(token, { clientData: state.myUserName }).then(() => {
        let publisher = state.OV.initPublisher(undefined, {
          audioSource: undefined, // The source of audio. If undefined default microphone
          videoSource: undefined, // The source of video. If undefined default webcam
          publishAudio: true, // Whether you want to start publishing with your audio unmuted or not
          publishVideo: true, // Whether you want to start publishing with your video enabled or not
          allowTranscoding: true,
          resolution: '1200x480', // The resolution of your video
          frameRate: 60, // The frame rate of your video
          insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
          mirror: false
        })

        // Set the main video in the page to display our webcam and store our Publisher
        state.mainStreamManager = publisher
        state.publisher = publisher

        // --- 6) Publish your stream ---

        console.log(state.publisher)

        state.session.publish(publisher)
      })
    })
    .catch((error) => {
      console.log('세션에 연결하는 과정에서 에러가 발생했습니다.', error.code, error.message)
    })

  console.log(state.session)

  window.addEventListener('beforeunload', leaveSession)
}

// 세션 종료
const leaveSession = () => {
  if (state.session) state.session.disconnect()

  state.session = undefined
  state.mainStreamManager = undefined
  state.publisher = undefined
  state.subscribers = []
  state.OV = undefined

  window.removeEventListener('beforeunload', leaveSession)

  router.push('/')
  console.clear()
}

const updateMainVideoStreamManager = (stream) => {
  if (state.mainStreamManager === stream) return
  state.mainStreamManager = stream
}

/* APPLICATION SERVER로부터 토큰 얻기
아래의 메소드들은 세션과 토큰의 생성을 어플리케이션 서버에 요청합니다.
이로써 OpenVidu 배포를 안전하게 유지할 수 있습니다.
실제 제품 환경에서는 어플리케이션 서버가 사용자를 식별하여
엔드포인트에 액세스를 허용해야 합니다. */

const getToken = async (mySessionId) => {
  const sessionId = await createSession(mySessionId)
  return await createToken(sessionId)
}

const createSession = async (sessionId) => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions',
    { customSessionId: sessionId },
    {
      headers: { 'Content-Type': 'application/json' }
    }
  )

  return response.data // sessionId
}

const createToken = async (sessionId) => {
  const response = await axios.post(
    APPLICATION_SERVER_URL + 'api/sessions/' + sessionId + '/connections',
    {},
    {
      headers: { 'Content-Type': 'application/json' }
    }
  )
  console.log(response.data)
  return response.data // The token
}

onMounted(() => {
  joinSession()
  window.addEventListener('beforeunload', leaveSession)
})
</script>

<style>
#button-container {
  display: flex;
  flex-direction: column;
  align-items: center;
}

#button {
  margin-bottom: 7px;
  width: 35px;
  height: 35px;
  border-radius: 100%;
}

span {
  font-size: 13px;
  color: #888888;
}

::-webkit-scrollbar {
  background: #f9f1ff;
  width: 10px;
  height: 10px;
}

/* 스크롤바 막대 */
::-webkit-scrollbar-thumb {
  background: #e7c6ff; /* 스크롤바 막대 색상 */
  border-radius: 12px 12px 12px 12px;
}
</style>
