<template>
  <main class="w-screen h-screen flex flex-col justify-center">
    <!-- 회의 화면 -->
    <section class="h-4/5 flex justify-center">
      <div class="w-full">
        <!-- 메인 회의 -->
        <section id="main-container" class="w-full h-full flex">
          <div class="mb-2 pb-2 flex whitespace-nowrap overflow-x-scroll overflow-hidden gap-2">
            <!-- 참가자 화면 -->
            <div
              id="sub-video"
              class="min-w-[268px] min-h-[107px] basis-1/4 flex space-x-3 rounded-xl"
            >
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
          <div id="main-video" class="flex min-w-[1000px] justify-center rounded-xl">
            <user-video
              :stream-manager="state.mainStreamManager"
              class="min-w-[1000px] max-w-fit min-h-[430px] max-h-fit rounded-xl"
            />
          </div>
        </section>
      </div>
      <!-- 참여자 목록, 채팅방-->
      <div v-if="isList || isChat" class="pl-2 min-w-[245px] min-h-[563px] max-h-fit flex flex-col">
        <div
          id="user-list"
          class="h-full border-2 border-gray rounded-xl flex flex-col"
          v-if="isList"
        >
          <div class="bg-white w-full basis-1/12 flex items-center">
            <div class="ml-2 text-lg font-bold flex-nowrap overflow-hidden">참여자</div>
            <div class="ml-10 flex space-x-2 overflow-hidden">
              <div class="text-base text-purple-400 text-end">초대하기</div>
              <button class="">
                <IconInvite />
              </button>
            </div>
            <button class="ml-2 flex overflow-hidden" @click="showList">
              <IconCancel />
            </button>
          </div>
          <div
            class="bg-gray w-full basis-10/12 flex flex-col justify-center items-center space-y-2 overflow-scroll"
          >
            <user-list
              v-for="sub in state.subscribers"
              :key="sub.stream.connection.connectionId"
              :stream-manager="sub"
              class="bg-white w-11/12 basis-1/12 rounded-lg text-base flex justify-center items-center font-semibold"
            />
          </div>
          <div class="w-full basis-1/12 flex flex-col justify-center items-center">
            <form
              action="/meeting"
              class="bg-gray w-11/12 min-h-6 max-h-12 rounded-3xl flex items-center"
            >
              <input
                type="text"
                name=""
                id="search-name"
                placeholder="참여자명 검색"
                class="bg-gray w-10/12 rounded-3xl"
              />
              <button
                type="submit"
                class="min-w-4 min-h-4 max-h-10 max-w-10 rounded-full bg-purple-300 flex justify-center items-center"
              >
                <IconSearch />
              </button>
            </form>
          </div>
        </div>
        <div v-if="isList && isChat" class="mb-2"></div>
        <div
          id="chat-list"
          class="w-full h-full border-2 border-gray rounded-xl flex flex-col"
          v-if="isChat"
        >
          <div class="p-4 bg-white w-full h-15 flex items-center">
            <div class="basis-3/12 text-lg font-bold">채팅</div>
            <button class="ml-auto basis-2/12 flex justify-end" @click="showChat">
              <IconCancel />
            </button>
          </div>
          <div
            class="bg-gray w-full h-5/6 flex flex-col justify-center items-center space-y-2 overflow-scroll"
          >
            채팅
          </div>
          <div class="w-full h-20 flex justify-center items-center">
            <form action="/meeting" class="p-4 bg-gray w-11/12 h-12 rounded-3xl flex items-center">
              <input
                type="text"
                name=""
                id="search-name"
                placeholder="메시지 보내기"
                class="bg-gray w-11/12"
              />
              <button
                type="submit"
                class="p-2 w-10 h-10 rounded-full bg-purple-200 flex justify-center items-center"
              >
                <IconMessageSend />
              </button>
            </form>
          </div>
        </div>
      </div>
    </section>
    <!-- 기능 버튼 -->
    <section class="pt-4">
      <div class="flex">
        <div class="min-w-[200px] flex flex-wrap">
          <button>아이콘</button>
          <button class="h-10 w-16 ml-2 bg-gray-400 text-white text-xs rounded-xl">
            화면 배치
          </button>
        </div>
        <div class="min-w-[900px] flex flex-wrap justify-center space-x-10">
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
        <div class="ml-2 min-w-[200px] flex flex-row flex-wrap space-x-3 justify-end">
          <div id="button-container">
            <button id="button" class="bg-purple-100" @click="showList"></button>
            <span>참여자 목록</span>
          </div>
          <div id="button-container">
            <button id="button" class="bg-purple-100" @click="showChat"></button>
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
import { ref, reactive, onMounted, defineProps } from 'vue'
import { OpenVidu } from 'openvidu-browser'
import router from '../../router'
import axios from 'axios'
import UserList from './UserList.vue'
import UserVideo from './UserVideo.vue'
import IconSearch from '@/icons/IconSearch.vue'
import IconInvite from '@/icons/IconInvite.vue'
import IconCancel from '@/icons/IconCancel.vue'
import IconMessageSend from '@/icons/IconMessageSend.vue'
import LetterModal from '@/components/modal/LetterModal.vue'

const props = defineProps({
  accessType: {
    type: String
  }
})

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
let showChat = () => {
  isChat.value = !isChat.value
}

// 참여자 목록 on / off
let showList = () => {
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
  mySessionId: 'SessionA',
  myUserName: 'participant' + Math.floor(Math.random() * 100),
  openviduToken: undefined,
  isMic: true,
  isCamera: true,
  isSpeaker: true,
  isChat: true
})

// 세션 참가하기
const joinSession = (accessType) => {
  // 1) Openvidu 객체 생성
  state.OV = new OpenVidu()

  // 2) 세션 시작
  state.session = state.OV.initSession()

  // 3) 세션에서 이벤트 발생 시 동작하는 행동 구체화

  // 비동기 예외
  state.session.on('exception', ({ exception }) => {
    console.warn(exception)
  })

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
          resolution: '1000x480', // The resolution of your video
          frameRate: 30, // The frame rate of your video
          insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
          mirror: false
        })

        // Set the main video in the page to display our webcam and store our Publisher
        state.mainStreamManager = publisher
        state.publisher = publisher

        // --- 6) Publish your stream ---

        state.session.publish(publisher)
      })
    })
    .catch((error) => {
      console.log('세션에 연결하는 과정에서 에러가 발생했습니다.', error.code, error.message)
    })

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
  if (props.accessType === 'host') joinSession('host')
  else joinSession('sub')

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

input::placeholder {
  align-content: center;
  font-size: 0.8em;
}

::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

/* 스크롤바 막대 */
::-webkit-scrollbar-thumb {
  background: #e7c6ff; /* 스크롤바 막대 색상 */
  border-radius: 12px 12px 12px 12px;
}
</style>
