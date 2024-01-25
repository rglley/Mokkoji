<template>
  <!--title area-->
  <div class="flex mb-5 w-2/3 pl-5">
    <div class="font-bold text-xl flex mr-10"><IconPhotoList />전체 사진 목록</div>
    <button
      class="text-sm flex pt-1 hover:bg-violet-200 rounded-lg bg-violet-100 border-2 border-solid"
    >
      <IconPhotoAdd /> 사진 추가
    </button>
    <button class="ml-auto"><IconQuestionMark /></button>
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
import IconPhotoList from '@/icons/IconPhotoList.vue'
import IconPhotoAdd from '@/icons/IconPhotoAdd.vue'
import IconQuestionMark from '@/icons/IconQuestionMark.vue'
import IconLightBulb from '@/icons/IconLightBulb.vue'
import IconMarkPhoto from '@/icons/IconMarkPhoto.vue'
import Gallery from '@/components/myedit/Gallery.vue'
</script>

<style scoped></style>
