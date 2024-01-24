<template>
  <main class="px-4 w-screen h-screen flex flex-col justify-center">
    <!-- 회의 화면 -->
    <section class="w-full h-full flex justify-center items-center">
      <!-- 참가자 화면 -->
      <div class="max-h-[550px] basis-2/5 flex flex-col overflow-y-scroll overflow-y-hidden gap-2">
        <user-video
          v-for="sub in state.subscribers"
          :key="sub.stream.connection.connectionId"
          :stream-manager="sub"
          @click.native="updateMainVideoStreamManager(sub)"
          class="min-w-[270px] max-w-fit min-h-[180px] max-h-fit basis-1/4 rounded-xl flex-none"
        />
      </div>
      <!-- 메인 화면 -->
      <div
        id="main-video"
        class="min-w-[800px] max-w-7xl min-h-[550px] max-h-3xl basis-3/5 rounded-xl flex justify-center"
        data-aos="fade-left"
      >
        <user-video
          :stream-manager="state.mainStreamManager"
          class="min-w-[800px] max-w-7xl min-h-[550px] max-h-3xl rounded-xl"
        />
      </div>
      <!-- 참여자 목록, 채팅방-->
      <div class="pl-2 h-[550px] basis-1/5 self-center flex flex-col justify-center">
        <div
          v-if="isSubList"
          id="sub-list"
          class="min-w-[270px] max-w-fit h-full border-2 border-gray rounded-xl flex flex-col"
          data-aos="fade-left"
        >
          <div class="bg-white w-full basis-1/12 flex items-center">
            <div class="ml-2 text-lg font-bold flex-nowrap overflow-hidden">참여자</div>
            <div class="ml-10 flex space-x-2 overflow-hidden">
              <div class="text-base text-purple-400 text-end">초대하기</div>
              <button class="">
                <IconInvite />
              </button>
            </div>
            <button class="flex overflow-hidden" @click="showList">
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
                class="min-w-4 min-h-4 max-h-10 max-w-10 rounded-full bg-purple-200 flex justify-center items-center"
              >
                <IconSearch />
              </button>
            </form>
          </div>
        </div>
        <div v-if="isSubList && isChat" class="mb-2"></div>
        <div
          v-if="isChat"
          id="chat-list"
          class="w-full h-full border-2 border-gray rounded-xl flex flex-col"
          data-aos="fade-left"
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
    <section class="mx-32 pt-2 min-h-[130px] flex items-center">
      <div class="flex justify-center items-center space-x-20">
        <div class="min-w-[110px] flex items-center space-x-2">
          <button class="w-10 h-10 rounded-full bg-white hover:bg-slate-200">
            <IconInfo />
          </button>
          <button
            class="min-h-10 min-w-24 bg-neutral-500 hover:bg-neutral-800 text-white text-sm rounded-xl"
          >
            <div>
              <IconLayout />
            </div>
            <div class="mx-1">화면 배치</div>
          </button>
        </div>
        <div class="min-w-[500px] flex flex-wrap justify-center items-center space-x-8">
          <div id="button-container" class="flex flex-col items-center">
            <button id="button-mic" class="hover:bg-purple-400" @click="setMicrophoneState">
              <IconMicOn v-if="isMic" />
              <IconMicOff v-else />
            </button>
            <span v-if="isMic" @click="setMicrophoneState">마이크 ON</span>
            <span v-else @click="setMicrophoneState">마이크 OFF</span>
          </div>
          <div id="button-container">
            <button id="button-camera" @click="setCameraState">
              <IconVideoOn v-if="isCamera" />
              <IconVideoOff v-else />
            </button>
            <span v-if="isCamera" @click="setCameraState">카메라 ON</span>
            <span v-else @click="setCameraState">카메라 OFF</span>
          </div>
          <div id="button-container" v-if="isMain">
            <button id="button-group" @click="createGroup">
              <IconGroup />
            </button>
            <span @click="createGroup">소그룹</span>
          </div>
          <div id="button-container">
            <button id="button-letter" @click="toggleLetterModal">
              <IconLetter />
            </button>
            <span @click="toggleLetterModal">편지쓰기</span>
          </div>
          <div id="button-container">
            <button id="button-gift">
              <IconGift />
            </button>
            <span>선물하기</span>
          </div>
          <div id="button-container">
            <button id="button-picture">
              <IconCamera />
            </button>
            <span>사진찍기</span>
          </div>
        </div>
        <div class="ml-2 min-w-[220px] flex space-x-6 justify-end">
          <div id="button-container">
            <button id="button-subList" @click="setSubListState">
              <IconSubList />
            </button>
            <span @click="setSubListState">참여자 목록</span>
          </div>
          <div id="button-container">
            <button id="button-chat" @click="setChatState">
              <IconChat />
            </button>
            <span @click="setChatState">채팅</span>
          </div>
          <div class="flex items-center">
            <button
              class="w-20 h-10 bg-red-500 hover:bg-red-400 text-white rounded-3xl"
              @click="leaveSession"
            >
              나가기
            </button>
          </div>
        </div>
      </div>
    </section>
    <LetterModal v-if="isLetterModal" />
  </main>
</template>

<script setup>
import { ref, reactive, onMounted, defineProps } from 'vue'
import { OpenVidu } from 'openvidu-browser'
import router from '../../router'
import axios from 'axios'
import UserList from './UserList.vue'
import UserVideo from './UserVideo.vue'
import IconInfo from '@/icons/meeting/IconInfo.vue'
import IconSearch from '@/icons/meeting/IconSearch.vue'
import IconInvite from '@/icons/meeting/IconInvite.vue'
import IconCancel from '@/icons/meeting/IconCancel.vue'
import IconLayout from '@/icons/meeting/IconLayout.vue'
import IconMicOn from '@/icons/meeting/IconMicOn.vue'
import IconMicOff from '@/icons/meeting/IconMicOff.vue'
import IconVideoOn from '@/icons/meeting/IconVideoOn.vue'
import IconVideoOff from '@/icons/meeting/IconVideoOff.vue'
import IconGroup from '@/icons/meeting/IconGroup.vue'
import IconGift from '@/icons/meeting/IconGift.vue'
import IconLetter from '@/icons/meeting/IconLetter.vue'
import IconCamera from '@/icons/meeting/IconCamera.vue'
import IconSubList from '@/icons/meeting/IconSubList.vue'
import IconChat from '@/icons/meeting/IconChat.vue'
import IconMessageSend from '@/icons/meeting/IconMessageSend.vue'
import LetterModal from '@/components/modal/LetterModal.vue'

const props = defineProps({
  accessType: {
    type: String
  }
})

// 메인 회의
let isMain = ref(true)
// 참여자 목록
let isSubList = ref(false)
// 채팅
let isChat = ref(false)
// 마이크
let isMic = ref(true)
// 비디오
let isCamera = ref(true)
// 편지쓰기
let isLetterModal = ref(false)

// 마이크 on / off
let setMicrophoneState = () => {
  isMic.value = !isMic.value

  if (isMic.value) document.getElementById('button-mic').style.backgroundColor = '#e9d5ff'
  else document.getElementById('button-mic').style.backgroundColor = '#EF4444'
}

// 비디오 on / off
let setCameraState = () => {
  isCamera.value = !isCamera.value

  if (isCamera.value) document.getElementById('button-camera').style.backgroundColor = '#e9d5ff'
  else document.getElementById('button-camera').style.backgroundColor = '#EF4444'
}

// 메인 회의 / 소그룹 이동
let createGroup = () => {
  isMain.value = !isMain.value
}

// 참여자 목록 on / off
let setSubListState = () => {
  isSubList.value = !isSubList.value

  if (isSubList.value) document.getElementById('button-subList').style.backgroundColor = '#c084fc'
  else document.getElementById('button-subList').style.backgroundColor = '#e9d5ff'
}

// 채팅 on / off
let setChatState = () => {
  isChat.value = !isChat.value

  if (isChat.value) document.getElementById('button-chat').style.backgroundColor = '#c084fc'
  else document.getElementById('button-chat').style.backgroundColor = '#e9d5ff'
}

// 편지 쓰기 모달 생성
let toggleLetterModal = () => {
  isLetterModal.value = !isLetterModal.value

  if (isLetterModal.value)
    document.getElementById('button-letter').style.backgroundColor = '#c084fc'
  else document.getElementById('button-letter').style.backgroundColor = '#e9d5ff'
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

const width = window.innerWidth
const height = window.innerHeight

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
          resolution: '800x550', // The resolution of your video
          frameRate: 30, // The frame rate of your video
          insertMode: 'APPEND', // How the video is inserted in the target element 'video-container'
          mirror: true
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
  justify-content: center;
  align-items: center;
}

#button-container > button {
  margin-bottom: 7px;
  width: 45px;
  height: 45px;
  border-radius: 100%;
  background-color: #e9d5ff;
}

#button-container > button:hover {
  background-color: #c084fc;
}

span {
  color: black;
  font-size: 13px;
  min-width: 59px;
  text-align: center;
}

span:hover {
  cursor: pointer;
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
