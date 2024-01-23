<template>
  <div
    class="fixed w-full bg-black bg-opacity-30 h-screen top-0 left-0 z-10 flex justify-center items-center px-8"
  >
    <div
      id="letter-container"
      class="p-8 w-1/3 h-fit bg-yellow-100 flex flex-col justify-center rounded-xl"
    >
      <div class="flex pb-6">
        <h1 class="text-black text-2xl font-bold">롤링페이퍼 작성</h1>
      </div>
      <form action="" method="post" id="letterForm" class="flex flex-col justify-center">
        <div class="p-6 flex flex-col border-2 rounded-xl">
          <textarea
            name=""
            id="input-text"
            value=""
            cols="30"
            rows="10"
            placeholder="메시지를 입력하세요"
            class="bg-yellow-100 rounded-xl resize-none"
          >
          </textarea>
          <div class="flex">
            <div class="basis-1/2 flex justify-start">
              <label className="input-voice-button" for="input-voice" class="h-8 pr-2">
                <IconVoice />.
              </label>
              <input type="file" id="input-voice" class="hidden" @change="showFileName" />
              <label for="input-video">
                <IconVideo />
              </label>
              <input type="file" id="input-video" class="hidden" @change="showFileName" />
            </div>
            <div class="basis-1/2 flex justify-end">
              <button type="button" class="" @click="removeContents">
                <IconRemove />
              </button>
            </div>
          </div>
        </div>
        <div class="pt-2 pb-4 flex flex-wrap">
          <span
            v-for="(fileName, index) in fileNames"
            :key="fileName"
            class="text-gray-800 w-40 mr-2 p-1 border-2 rounded-lg flex items-center overflow-hidden"
          >
            <img src="@/assets/clip.png" alt="파일 클립 이미지" class="mr-1 w-4" /> {{ fileName }}
            <img
              src="@/assets/delete.png"
              alt="삭제 버튼 이미지"
              class="w-4 ml-auto"
              @click="removeFile(index)"
            />
          </span>
        </div>
        <button
          type="submit"
          form="letterForm"
          class="w-1/5 h-10 bg-red-200 border-2 rounded-xl self-end"
        >
          작성하기
        </button>
      </form>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import IconVoice from '@/icons/IconVoice.vue'
import IconVideo from '@/icons/IconVideo.vue'
import IconRemove from '@/icons/IconRemove.vue'

let fileNames = ref([])

let removeContents = () => {
  document.getElementById('input-text').value = ''
}

let showFileName = (event) => {
  const files = event.target.files

  if (files.length > 0) {
    for (let idx = 0; idx < files.length; idx++) {
      fileNames.value.push(files[0].name)
    }
  } else {
    fileNames.value = []
  }
}

let removeFile = (index) => {
  fileNames.value.splice(index, 1)
}
</script>

<style></style>
