<template>
  <main class="px-2 w-screen h-screen flex flex-col justify-center">
    <section class="w-full h-[85%] flex justify-center">
      <!-- 스포트라이트 레이아웃 -->
      <div v-if="!isGrid" class="w-full h-full flex basis-3/4">
        <!-- 회의 화면 -->
        <div
          id="meeting-container"
          ref="groupVideo"
          class="w-full h-full flex justify-center items-center"
        >
          <!-- 참가자 화면 -->
          <div
            v-if="state.subscribers.length > 0"
            class="h-[90%] w-[100%] basis-1/3 flex-col overflow-y-scroll overflow-hidden space-y-2"
          >
            <user-Video
              v-for="sub in state.subscribers"
              :key="sub.stream.connection.connectionId"
              :stream-manager="sub"
              @click.native="updateMainVideoStreamManager(sub)"
              class="min-w-[100%] max-w-[100%] rounded-2xl flex-none"
            />
          </div>
          <!-- 메인 화면 -->
          <div ref="myVideo" class="max-h-[100%] basis-2/3 flex justify-center items-center">
            <user-Video
              :stream-manager="state.mainStreamManager"
              class="px-2 min-w-[100%] max-w-[100%] min-h-[100%] max-h-[100%] rounded-3xl"
            />
          </div>
        </div>
      </div>
      <!-- 그리드 레이아웃 -->
      <div v-else class="w-full h-full flex basis-3/4">
        <!-- 회의 화면 -->
        <div id="grid-container" ref="groupVideo">
          <!-- 참가자 화면 -->
          <user-Video
            v-for="sub in state.subscribers"
            :key="sub.stream.connection.connectionId"
            :stream-manager="sub"
            @click="updateMainVideoStreamManager(sub)"
            class="rounded-2xl"
          />
          <!-- 메인 화면 -->
          <user-Video
            id="main-video"
            :stream-manager="state.mainStreamManager"
            class="rounded-2xl"
          />
        </div>
      </div>
      <!-- 참여자 목록, 채팅방-->
      <transition-group name="side">
        <div
          v-if="isUserList || isChat"
          class="h-full basis-1/4 self-center flex flex-col justify-end"
        >
          <transition-group name="side">
            <div
              v-if="isUserList"
              class="w-full h-full border-2 border-gray rounded-xl flex flex-col resize"
            >
              <div class="px-4 w-full min-h-16 max-h-[20%] flex items-center">
                <div class="w-[30%] font-bold text-xs md:text-md lg:text-lg xl:text-xl">참여자</div>
                <div class="w-[70%] flex just overflow-hidden">
                  <button class="w-[70%]">
                    <div class="text-xs md:text-xs lg:text-md xl:text-lg text-purple-400 text-end">
                      초대하기
                    </div>
                    <IconInvite class="size-[40%]" />
                  </button>
                  <button class="ml-auto flex overflow-hidden" @click="setUserListState">
                    <IconCancel class="size-{80%]" />
                  </button>
                </div>
              </div>
              <div
                class="bg-gray h-[80%] flex flex-col justify-center items-center space-y-2 overflow-hidden"
              >
                <div></div>
                <user-list
                  v-for="sub in state.subscribers"
                  :key="sub.stream.connection.connectionId"
                  :stream-manager="sub"
                  :search-user-name="searchUserName"
                  class="bg-white w-[90%] min-h-16 rounded-lg text-base flex justify-center items-center font-semibold"
                />
              </div>
              <div class="min-h-16 max-h-[20%] flex flex-col justify-center items-center">
                <div class="bg-gray w-[90%] h-[70%] rounded-3xl flex items-center">
                  <input
                    type="text"
                    name=""
                    id="search-name"
                    placeholder="참여자명 검색"
                    class="bg-gray w-[80%] h-[100%] border-0 rounded-full"
                    v-model="searchUserName"
                  />
                  <button
                    class="w-[17%] h-[90%] rounded-full bg-purple-200 flex justify-center items-center"
                  >
                    <IconSearch class="size-[60%]" />
                  </button>
                </div>
              </div>
            </div>
          </transition-group>
          <div v-if="isUserList && isChat" class="mb-2"></div>
          <transition-group name="side"
            ><div
              v-if="isChat"
              id="chat-container"
              class="w-full h-full border-2 border-gray rounded-xl flex flex-col resize"
            >
              <div class="px-4 w-full min-h-16 max-h-[20%] flex items-center">
                <div class="basis-3/12 font-bold text-xs md:text-md lg:text-lg xl:text-xl">
                  채팅
                </div>
                <button class="ml-auto basis-2/12 flex justify-end" @click="showChat">
                  <IconCancel @click="setChatState" />
                </button>
              </div>
              <div
                class="bg-gray h-[80%] flex flex-col justify-center items-center space-y-2 overflow-y-scroll"
              >
                <ChatLog :chat-log="chatMessages" />
              </div>
              <div class="min-h-16 max-h-[20%] flex flex-col justify-center items-center">
                <div
                  action="/meeting"
                  class="bg-gray w-[90%] h-[70%] rounded-3xl flex items-center"
                >
                  <input
                    v-model="chatMessage"
                    type="text"
                    name=""
                    id="chat-message"
                    placeholder="메시지 보내기"
                    class="bg-gray w-[80%] h-[100%] border-0 rounded-full"
                  />
                  <button
                    @click="sendMessage()"
                    type="submit"
                    class="w-[17%] h-[90%] rounded-full bg-purple-200 flex justify-center items-center"
                  >
                    <IconMessageSend class="size-[60%]" />
                  </button>
                </div>
              </div>
            </div>
          </transition-group>
        </div>
      </transition-group>
    </section>
    <!-- 기능 버튼 -->
    <section class="mx-32 pt-2 h-[25%] flex justify-center items-center">
      <div class="h-full flex justify-center items-center space-x-20">
        <div class="min-w-[110px] flex items-center space-x-2">
          <button class="w-10 h-10 rounded-full bg-white hover:bg-slate-200">
            <IconInfo />
          </button>
          <button
            class="min-h-10 min-w-24 bg-neutral-500 hover:bg-neutral-800 text-white text-sm rounded-xl"
            @click="setLayoutState"
          >
            <div>
              <IconLayout />
            </div>
            <div class="mx-1">화면 배치</div>
          </button>
        </div>
        <div
          id="button-container"
          class="min-w-[500px] flex flex-wrap justify-center items-center space-x-4"
        >
          <div class="flex flex-col items-center">
            <button
              id="button-mic"
              :class="{ 'bg-purple-200': isMic, 'bg-red-500': !isMic }"
              @click="setMicrophoneState"
            >
              <IconMicOn v-if="isMic" />
              <IconMicOff v-else />
            </button>
            <span v-if="isMic" @click="setMicrophoneState">ON</span>
            <span v-else @click="setMicrophoneState">OFF</span>
          </div>
          <div>
            <button
              id="button-camera"
              :class="{ 'bg-purple-200': isCamera, 'bg-red-500': !isCamera }"
              @click="setCameraState"
            >
              <IconVideoOn v-if="isCamera" />
              <IconVideoOff v-else />
            </button>
            <span v-if="isCamera" @click="setCameraState">ON</span>
            <span v-else @click="setCameraState">OFF</span>
          </div>
          <div v-if="isMain">
            <button id="button-group" class="bg-purple-200" @click="createGroup">
              <IconGroup />
            </button>
            <span @click="createGroup">소그룹</span>
          </div>
          <div>
            <button
              id="button-letter"
              :class="{ 'bg-purple-400': isLetterModal, 'bg-purple-200': !isLetterModal }"
              @click="setLetterModalState"
            >
              <IconLetter />
            </button>
            <span @click="setLetterModalState">편지쓰기</span>
          </div>
          <div>
            <button
              id="button-gift"
              :class="{ 'bg-purple-400': isGiftModal, 'bg-purple-200': !isGiftModal }"
              @click="setGiftState"
            >
              <IconGift />
            </button>
            <span>선물하기</span>
          </div>
          <div>
            <button
              id="button-picture"
              :class="{ 'bg-purple-400': isCapture, 'bg-purple-200': !isCapture }"
              @click="captureScreen"
            >
              <IconCamera />
            </button>
            <span>사진찍기</span>
          </div>
        </div>
        <div id="button-container" class="ml-2 min-w-[220px] flex space-x-6 justify-end">
          <div>
            <button
              id="button-subList"
              :class="{ 'bg-purple-400': isUserList, 'bg-purple-200': !isUserList }"
              @click="setUserListState"
            >
              <IconUserList />
            </button>
            <span @click="setUserListState">참여자</span>
          </div>
          <div>
            <button
              id="button-chat"
              :class="{ 'bg-purple-400': isChat, 'bg-purple-200': !isChat }"
              @click="setChatState"
            >
              <IconChat />
            </button>
            <span @click="setChatState">채팅</span>
          </div>
          <div>
            <div class="flex items-center">
              <button
                id="button-quit"
                class="w-20 h-10 bg-red-500 hover:bg-red-400 text-white rounded-3xl"
                @click="leaveMeeting"
              >
                나가기
              </button>
            </div>
          </div>
        </div>
      </div>
    </section>
    <LetterModal v-if="isLetterModal" />
    <transition-group name="gift-modal">
      <GiftModal v-if="isGiftModal" />
    </transition-group>
  </main>
</template>

<script setup>
import { ref, reactive, onMounted, defineProps } from 'vue'
import { OpenVidu } from 'openvidu-browser'
import { io } from 'socket.io-client'
import html2canvas from 'html2canvas'
import router from '../../router'
import axios from 'axios'
import UserList from './UserList.vue'
import UserVideo from './UserVideo.vue'
import ChatLog from './ChatLog.vue'
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
import IconUserList from '@/icons/meeting/IconUserList.vue'
import IconChat from '@/icons/meeting/IconChat.vue'
import IconMessageSend from '@/icons/meeting/IconMessageSend.vue'
import LetterModal from '@/components/modal/LetterModal.vue'
import GiftModal from '@/components/modal/GiftModal.vue'

const props = defineProps({
  accessType: {
    type: String
  }
})

const emit = defineEmits(['leave-meeting'])

const isGrid = ref(false)
const isMic = ref(true)
const isCamera = ref(true)
const isMain = ref(true)
const isLetterModal = ref(false)
const isGiftModal = ref(false)
const isCapture = ref(false)
const isUserList = ref(false)
const isChat = ref(false)

const searchUserName = ref('')
const chatMessage = ref('')
const chatMessages = ref([])
const groupVideo = ref(null)

const captureScreen = () => {
  const target = groupVideo.value
  if (!target) {
    return alert('결과 저장 실패')
  }
  html2canvas(target).then((canvas) => {
    onSaveAs(canvas.toDataURL('image/png'), 'test.png')
  })
}

// 캡쳐 사진 저장
const onSaveAs = (uri, filename) => {
  const link = document.createElement('a')
  document.body.appendChild(link)
  link.href = uri
  link.download = filename
  link.click()
  document.body.removeChild(link)
}

const setLayoutState = () => {
  isGrid.value = !isGrid.value
}

const setMicrophoneState = () => {
  isMic.value = !isMic.value

  if (isMic.value) {
    state.publisher.publishAudio(true)
  } else {
    state.publisher.publishAudio(false)
  }
}

const setCameraState = () => {
  isCamera.value = !isCamera.value

  if (isCamera.value) {
    state.publisher.publishVideo(true)
  } else {
    state.publisher.publishVideo(false)
  }
}

const setLetterModalState = () => {
  isLetterModal.value = !isLetterModal.value
}

const setGiftState = () => {
  isGiftModal.value = !isGiftModal.value
}

const setCaptureState = () => {
  isCapture.value = !isCapture.value
}

const createGroup = () => {
  isMain.value = !isMain.value
}

const setUserListState = () => {
  isUserList.value = !isUserList.value
}

const setChatState = () => {
  isChat.value = !isChat.value
}

// ---------------- OpenVidu 관련 ----------------

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
const joinSession = () => {
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
    console.log(state.subscribers)
  })

  // 시그널링 서버로부터 수신된 채팅 메시지 처리
  state.session.on('signal:chat', (event) => {
    const sender = JSON.parse(event.from.data)

    const chatData = {
      sender: sender.clientData,
      content: event.data
    }
    chatMessages.value.push(chatData)
  })

  // 참가자 퇴장
  state.session.on('streamDestroyed', ({ stream }) => {
    const index = state.subscribers.indexOf(stream.streamManager, 0)
    if (index >= 0) {
      state.subscribers.splice(index, 1)
    }
    console.log(state.subscribers)
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
          resolution: '800x600', // The resolution of your video
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

  window.addEventListener('beforeunload', leaveMeeting)
}

// 세션 종료
const leaveMeeting = () => {
  if (state.session) state.session.disconnect()

  state.session = undefined
  state.mainStreamManager = undefined
  state.publisher = undefined
  state.subscribers = []
  state.OV = undefined

  window.removeEventListener('beforeunload', leaveMeeting)

  emit('leave-meeting')
  router.push('/')
}

const updateMainVideoStreamManager = (stream) => {
  if (state.mainStreamManager === stream) return

  state.mainStreamManager = stream
}

/* APPLICATION SERVER로부터 토큰 얻기
아래의 메소드들은 세션과 토큰의 생성을 어플리케이션 서버에 요청한다. */

const getToken = async (mySessionId) => {
  const sessionId = await createSession(mySessionId)
  return await createToken(sessionId)
}

// 세션 생성
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

// 토큰 생성
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

const sendMessage = () => {
  if (chatMessage.value.trim() !== '') {
    // OpenVidu 시그널링 서버를 통해 채팅 메시지를 보낸다.
    state.session.signal({
      data: chatMessage.value,
      to: [],
      type: 'chat' // 채팅 메시지 타입
    })
    chatMessage.value = '' // 메시지 전송 후 입력 필드 비우기
  }
}

onMounted(() => {
  if (props.accessType === 'host') joinSession()
  else joinSession()

  window.addEventListener('beforeunload', leaveMeeting)
})
</script>

<style>
#button-container > div {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

#button-container > div > button {
  margin-bottom: 7px;
  width: 45px;
  height: 45px;
  border-radius: 100%;
}

#button-container > div > button:hover {
  background-color: #c084fc;
}

#grid-container {
  width: 100%;
  height: 100%;
  display: grid;
  grid-template-columns: repeat(auto-fit, 33%);
  align-items: center;
  justify-content: center;
  gap: 1rem;
  overflow-x: scroll;
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
  font-size: 100%;
}

::-webkit-scrollbar {
  width: 0;
}

::-webkit-scrollbar-thumb {
  background-color: transparent;
  border: none;
}

.side-enter-active {
  transition: all 0.5s;
}

.side-leave-active {
  transition: all 0.5s;
}

.side-enter-from,
.side-leave-to {
  transform: translateX(100px);
  opacity: 0;
}

.gift-modal-enter-active {
  transition: all 0.3s;
}

.gift-modal-leave-active {
  transition: all 0.3s;
}

.gift-modal-enter-from,
.gift-modal-leave-to {
  transform: translateY(10px);
  opacity: 0;
}
</style>
