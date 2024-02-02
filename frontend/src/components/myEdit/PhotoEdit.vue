<template>
  <!--title area-->
  <div class="flex mb-5 w-2/3 pl-5">
    <div class="font-bold text-xl flex mr-10"><IconPhotoList />전체 사진 목록</div>
    <button
      class="text-sm flex pt-1 hover:bg-violet-200 rounded-lg bg-violet-100 border-2 border-solid mr-3"
    >
      <IconPhotoAdd /> 사진 추가
    </button>
    <button @click="openModalFive"><IconQuestionMark /></button>
    <!--modal area-->
    <transition name="modal-fade">
      <div
        v-if="isOpenFive"
        class="fixed bottom-0 left-[500px] w-full h-full flex content-center items-center z-20"
      >
        <div
          class="bg-violet-200 relative px-60 py-36 border-violet-300 border-2 border-solid rounded-lg"
        >
          <div>
            <button
              @click="closeModalFive"
              class="text-sm absolute px-2 py-1 bottom-64 right-2 cursor-pointer bg-violet-100 rounded-lg"
            >
              확인
            </button>
          </div>
          <div>
            <p class="absolute left-[20px] bottom-[200px] text-lg">
              1. 행사를 대표할 사진을 선택해주세요
            </p>
            <p class="absolute left-[20px] bottom-[140px] text-lg">
              2. 기본 저장된 사진들은 참가자들이 남겨준 사진들과 자동 저장된 사진들이에요
            </p>
            <p class="absolute left-[20px] bottom-[100px] text-lg">
              3. 포토 모자이크에 원하는 사진들을 추가할 수 있어요.
            </p>
            <p class="absolute left-[20px] bottom-[40px] text-lg">
              4. '포토모자이크 생성'을 클릭하시면 대표 이미지로 모자이크가 생성돼요
            </p>
          </div>
        </div>
      </div>
    </transition>
  </div>

  <!--photo area-->
  <div class="flex">
    <div class="border-2 border-solid border-black h-1/2 w-2/3 ml-5"><Gallery /></div>
    <div class="w-1/3 ml-10">
      <p class="flex text-lg mb-10"><IconLightBulb />대표 이미지를 설정해주세요!</p>
      <p class="flex text-2xl"><IconMarkPhoto />대표 사진</p>
      <img
        src="@/assets/no_image.png"
        alt="no_img"
        class="ml-4 mx-2 my-2 border-violet-500 border-4 border-solid"
        height="250px"
        width="250px"
      />
      <div class="flex mt-10">
        <button
          class="border-violet-200 border-4 border-solid rounded-lg mr-2 py-2 px-2 hover:bg-violet-200"
        >
          대표 이미지만 설정
        </button>
        <button
          class="border-violet-200 border-4 border-solid rounded-lg py-2 px-2 hover:bg-violet-200"
        >
          포토모자이크 생성
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import IconPhotoList from '@/icons/result/IconPhotoList.vue'
import IconPhotoAdd from '@/icons/result/IconPhotoAdd.vue'
import IconQuestionMark from '@/icons/result/IconQuestionMark.vue'
import IconLightBulb from '@/icons/result/IconLightBulb.vue'
import IconMarkPhoto from '@/icons/result/IconMarkPhoto.vue'
import Gallery from '@/components/myedit/Gallery.vue'

const isOpenFive = ref(false)

const openModalFive = () => {
  isOpenFive.value = !isOpenFive.value
}

const closeModalFive = () => {
  isOpenFive.value = false
}
</script>

<style>
.modal-fade-enter-active,
.modal-fade-leave-active {
  transition: opacity 0.3s ease-in-out;
}

.modal-fade-enter,
.modal-fade-leave-to {
  opacity: 0;
}
</style>
