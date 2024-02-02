<template>
  <div class="fixed w-full h-full flex justify-center items-center">
    <div
      class="p-[5vh] fixed w-[45vw] h-[90vh] top-[3%] bg-white border-sm border-gray rounded-r-lg"
    >
      <div class="pb-[4vh] h-[5%] flex items-center">
        <div class="basis-3/12 font-bold text-r-md">소그룹</div>
        <button class="ml-auto basis-2/12 flex justify-end" @click="$emit('remove-group-modal')">
          <IconCancelBlack class="size-[15%]" />
        </button>
      </div>
      <div class="mb-[1vh] p-[2vh] h-[90%] bg-purple-100 rounded-r-lg">
        <div class="pb-[5vh] h-[5%] font-bold text-r-sm">소그룹 생성하기</div>
        <div class="h-[90%] flex justify-center items-center space-x-[1vw]">
          <div class="w-[47%] h-full bg-white rounded-r-lg flex flex-col items-center">
            <div
              class="pl-[1vw] mb-[1vh] w-full h-[10%] bg-purple-200 font-bold text-r-sm rounded-r-md flex items-center"
            >
              참여 가능한 참여자
            </div>
            <input
              type="text"
              placeholder="참여자명 검색"
              class="mb-[1vh] w-[95%] h-[10%] border-sm bg-gray rounded-full"
              v-model="searchUserName"
            />
            <div class="w-full h-[80%] flex flex-col justify-start items-center">
              <user-list
                v-for="sub in subscribers"
                :key="sub"
                :stream-manager="sub"
                :search-user-name="searchUserName"
                :button-type="buttonType"
                class="mb-[1vh] w-[95%] h-[20%] border-sm border-purple-300 rounded-r-md"
              />
            </div>
          </div>
          <div class="w-[47%] h-full rounded-r-lg">
            <div
              class="pl-[1vw] h-[10%] bg-purple-200 font-bold text-r-sm rounded-r-md flex items-center"
            >
              나의 소그룹
            </div>
            <div></div>
          </div>
        </div>
      </div>
      <div class="h-[5%] flex justify-end">
        <button
          id="submit-button"
          class="w-[6vw] h-[6vh] bg-purple-200 font-bold text-r-sm text-center rounded-r-lg"
          @click="$emit('create-group-meeting')"
        >
          생성하기
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import UserList from '@/components/meeting/UserList.vue'
import IconCancelBlack from '@/icons/meeting/IconCancelBlack.vue'

const props = defineProps({
  subscribers: {
    type: Object
  },
  buttonType: {
    type: String
  }
})

defineEmits(['remove-group-modal']['create-group-meeting'])

const searchUserName = ref('')
</script>

<style>
input::placeholder {
  align-content: center;
  font-size: 1vw;
}

#submit-button:hover {
  background-color: #c084fc;
}

.bg-purple-200 {
  background-color: #e7c6ff;
}
</style>
