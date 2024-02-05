<template>
  <div class="w-1/2 ml-5 justify-center items-center">
    <div class="border-[10px] w-80 flex justify-center items-center ml-16 mt-5 border-[#5da2bd]">
      <img ref="image" :src="imageUrl" class="w-64 h-64" />
    </div>
    <input
      class="ml-14 mt-5 h-10 rounded-md border-2 border-slate-200 bg-background px-1 py-2 text-sm file:border-2 file:rounded-lg file:border-slate-300 file:bg-transparent file:text-sm file:bg-slate-100 file:mr-5"
      type="file"
      @change="handleFileChange"
    />
    <button
      class="ml-44 mx-auto opacity-70 text-sm border-[#5da2bd] pt-1 rounded-lg hover:opacity-100 border-2 border-solid mb-5 px-2 py-1"
      @click="cropImage"
    >
      이미지 자르기
    </button>
  </div>
  <div class="w-1/2">
    <p class="absolute left-[585px] top-10 mt-5">미리보기</p>
    <div class="ml-5 h-2/3 pl-10 pb-24" v-if="croppedImage">
      <img :src="croppedImage" class="w-64 h-64 border-2 border-[#5da2bd]" />
    </div>
    <div class="h-1/3">
      <button
        class="absolute left-[560px] bottom-[-10px] opacity-70 text-sm border-[#5da2bd] flex pt-1 rounded-lg hover:opacity-100 border-2 border-solid mb-5 px-2 py-1"
        @click="uploadImage"
      >
        이미지 업로드
      </button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Cropper from 'cropperjs'
import 'cropperjs/dist/cropper.css'

const image = ref(null)
const cropper = ref(null)
const croppedImage = ref(null)
const imageUrl = ref('src/assets/edit/no_image.png') //처음 이미지

const cropImage = () => {
  const croppedDataUrl = cropper.value.getCroppedCanvas().toDataURL('image/png')
  croppedImage.value = croppedDataUrl
}

const handleFileChange = (event) => {
  const file = event.target.files[0]
  if (file) {
    imageUrl.value = URL.createObjectURL(file)
    cropper.value.replace(imageUrl.value)
  }
}

onMounted(() => {
  cropper.value = new Cropper(image.value, {
    aspectRatio: 1 / 1
  })
})
</script>
<style></style>
