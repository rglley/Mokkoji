<template>
  <div class="flex mt-10 ml-10">
    <div class="w-[55%]">
      <div class="flex justify-end items-end">
        <button
          class="opacity-70 text-lg border-[#5da2bd] pt-1 rounded-lg hover:opacity-100 border-2 border-solid mt-10 mb-5 px-2 py-1 effect-button-two"
          @click="showPhotoUploadModal"
        >
          <IconPhotoAdd /> 사진 추가
        </button>
      </div>
      <div class="ml-4 h-[808px] border-2 rounded-lg border-[#5da2bd]">
        <div ref="scrollContainer" class="custom-scroll-container">
          <div ref="scrollComponent" class="custom-scroll-content">
            <ul class="photo-grid">
              <li v-for="(photo, index) in photos" :key="index" @click="selectedPhoto(photo)">
                <img :src="`${photo}`" />
              </li>
            </ul>
          </div>
          <div v-if="isPreviewOpen" class="photo-preview-modal" @click="closePhotoPreview">
            <img :src="previewPhoto" class="preview-image" />
          </div>
        </div>
      </div>
    </div>
    <div class="w-[5%]"></div>
    <div class="w-[35%] pt-[100px] h-[900px]">
      <p class="flex justify-center items-center text-[40px]">선택한 사진</p>
      <div class="flex justify-center items-center mt-5 mx-24 border-[10px] border-[#5da2bd]">
        <img
          :src="selectedImage"
          alt="Selected Image"
          width="80%"
          height="80%"
          class="mx-10 my-10"
        />
      </div>
      <div class="">
        <div class="flex justify-center items-center">
          <div
            class="mt-12 opacity-70 border-2 justify-center items-center h-[100px] flex border-[#5da2bd] rounded-lg w-[150px] mr-5 hover:cursor-pointer hover:opacity-100"
          >
            <p class="text-[25px] justify-center flex text-center">
              추억카드 <br />미리보기 <IconSearch />
            </p>
          </div>

          <div
            class="mt-12 opacity-70 border-2 justify-center items-center h-[100px] flex border-[#5da2bd] rounded-lg w-[150px] ml-5 hover:cursor-pointer hover:opacity-100"
          >
            <p class="text-[25px] justify-center flex text-center">
              포토모자이크 <br />미리보기 <IconSearch />
            </p>
          </div>
        </div>
        <div class="flex justify-center items-center">
          <div
            class="mt-10 opacity-70 border-2 rounded-lg border-[#5da2bd] justify-center items-center h-[100px] mr-5 flex w-[150px] hover:cursor-pointer hover:opacity-100"
            @click="showSaved('대표이미지')"
          >
            <p class="text-[25px] justify-center flex text-center">
              대표이미지 <br />
              설정하기
            </p>
          </div>

          <div
            class="mt-10 opacity-70 border-2 rounded-lg border-[#5da2bd] justify-center items-center h-[100px] ml-5 flex w-[150px] hover:cursor-pointer hover:opacity-100"
            @click="showSaved('포토모자이크')"
          >
            <p class="text-[25px] justify-center flex text-center">포토모자이크 <br />생성하기</p>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- 재확인 모달 ex. 저장되었습니다 -->
  <transition name="modal-fade">
    <div
      v-if="isSaved"
      class="fixed bottom-[50%] left-[50%] custom-translate rounded-lg bg-slate-50 px-14 py-3 z-30"
    >
      <p class="flex text-[30px]">{{ alertText }} <IconConfetti /></p>
    </div>
  </transition>
  <!-- 사진 업로드 모달 -->
  <!-- <transition name="modal-fade"> -->
  <div
    v-if="isPhotoUploadModal"
    class="fixed bg-white bottom-[100px] left-[500px] w-[800px] h-[500px] border-2 rounded-lg border-[#5da2bd] z-20 bg-[#f5fcff]"
  >
    <div class="flex">
      <p class="text-lg my-5 ml-32">
        원하는 이미지를 정사각형 형태로 자른 뒤, 이미지 업로드를 진행해 주세요
      </p>
      <button class="ml-32 mb-4" @click="showPhotoUploadModal"><IconClose /></button>
    </div>
    <div class="flex content-center items-center">
      <ImgUpload />

      <!--성공, 실패 여부에 따라 메시지 출력 필요.-->
    </div>
  </div>
  <!-- </transition> -->
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import getImages from '@/api/get_images'
import { mainImageStore } from '@/stores/result.js'
import IconConfetti from '@/icons/result/IconConfetti.vue'
import IconPhotoAdd from '@/icons/result/IconPhotoAdd.vue'
import IconClose from '@/icons/result/IconClose.vue'
import ImgUpload from '@/components/myedit/ImgUpload.vue'

const store = mainImageStore()
const selectedImage = ref('src/assets/edit/no_image.png')

const alertText = ref('')
const isSaved = ref(false)
const isPhotoUploadModal = ref(false)

const pageSize = 30
const photos = ref([])
const totalPhotos = ref(0)
const scrollContainer = ref(null)
const scrollComponent = ref(null)
const isPreviewOpen = ref(false)
const previewPhoto = ref('')

const showPhotoUploadModal = () => {
  isPhotoUploadModal.value = !isPhotoUploadModal.value
}

const loadPhotos = (count) => {
  const newImages = getImages(count)
  photos.value = newImages
  totalPhotos.value = newImages.length
}

const loadMorePhotos = () => {
  const remainingPhotosCount = totalPhotos.value - photos.value.length
  const newImages = getImages(Math.min(10, remainingPhotosCount))
  photos.value.push(...newImages)
}

const handleScroll = () => {
  const component = scrollComponent.value
  const container = scrollContainer.value
  if (container.scrollTop + container.clientHeight >= component.offsetHeight) {
    loadMorePhotos()
  }
}

const selectedPhoto = (photo) => {
  //accesssing state
  console.log(store.selectedImage)
  store.selectedImage(photo) //data 보내기
  //accessing getters
  console.log(store.getSelectedImage)

  selectedImage.value = store.getSelectedImage
}

const showSaved = (e) => {
  isSaved.value = true
  setTimeout(() => {
    isSaved.value = false
  }, 2000)
  switch (e) {
    case '템플릿':
      alertText.value = '선택한 템플릿을 저장했어요'
      break
    case '포토모자이크':
      alertText.value = '포토모자이크를 생성했어요'
      break
    case '대표이미지':
      alertText.value = '대표 이미지를 설정했어요'
      break
  }
}

onMounted(() => {
  loadPhotos(pageSize)
  scrollContainer.value.addEventListener('scroll', handleScroll)
})

onUnmounted(() => {
  scrollContainer.value.removeEventListener('scroll', handleScroll)
})
</script>
<style>
.custom-scroll-container {
  overflow-y: scroll;
  height: 800px;
  scrollbar-width: thin; /* For Firefox */
  scrollbar-color: rgb(255, 255, 255) rgb(190, 212, 244); /* For Firefox */
}

.photo-grid {
  list-style: none;
  padding: 0;
  margin: 0;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 0px;
}

.custom-scroll-container::-webkit-scrollbar {
  width: 8px;
}

.custom-scroll-container::-webkit-scrollbar-thumb {
  background-color: rgb(121, 146, 220);
}

.custom-scroll-container::-webkit-scrollbar-track {
  background-color: rgb(60, 169, 208);
}

.photo-grid li {
  flex: 0 0 calc(20% - 16px); /* 20% width with a 16px gap */
  margin-bottom: 16px;
}

.photo-grid img {
  width: 100%;
  height: auto;
  display: block;
  border-radius: 8px; /* Add border radius for rounded corners */
}

.photo-preview-modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
}

.preview-image {
  max-width: 90%;
  max-height: 90%;
  border-radius: 8px;
}
</style>
