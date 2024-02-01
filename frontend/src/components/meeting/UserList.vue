<template>
  <form action="" class="flex items-center">
    <div v-if="streamManager && userName.includes(props.searchUserName)" class="flex items-center">
      <img src="@/assets/dummy_profile.jpg" alt="" class="ml-[0.5vw] w-[3vw] rounded-full" />
      <div class="ml-[1vw] text-r-sm">
        {{ userName }}
      </div>
      <input type="checkbox" name="check" id="user-check" v-if="buttonType === 'group'" />
      <label for="user-check" class="ml-[4vw] w-full h-full flex justify-center items-center">
        <span class="h-full flex justify-center items-center">
          <IconCheck class="size-[100%]" />
        </span>
      </label>
    </div>
  </form>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import IconCheck from '@/icons/meeting/IconCheck.vue'

const props = defineProps({
  streamManager: {
    type: Object
  },
  searchUserName: {
    type: String
  },
  buttonType: {
    type: String
  }
})

const userName = ref('')

// 사용자 데이터 가져오기
const clientData = () => {
  const { clientData } = getConnectionData()
  userName.value = clientData
}

const getConnectionData = () => {
  const { connection } = props.streamManager.stream
  return JSON.parse(connection.data)
}

onMounted(() => {
  clientData()
})
</script>

<style>
input#user-check {
  display: none;
}

input#user-check + label span {
  display: inline-block;
  height: 4vh;
  aspect-ratio: 1/1;
  vertical-align: middle;
  border-radius: 1vb;
  cursor: pointer;
  border: 0.3vb solid #e7c6ff;
}

input#user-check:checked + label span {
  background: #e7c6ff;
}
</style>
