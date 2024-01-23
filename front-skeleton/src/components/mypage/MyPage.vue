<template>
  <div>
    <img src="@/assets/dummy_profile.jpg" class="h-50" />
  </div>
  <div>
    <p>{{ store.user.id }}님</p>
    <div v-if="store.user.isAccountLinked">계좌 연결됨</div>
    <div v-else>
      <p>계좌를 연결하면 ____</p>
      <router-link to="#">계좌 연결</router-link>
    </div>
    <div>
      <!-- 행사 기록 시각화 -->
    </div>
  </div>
</template>

<script setup>
import { onMounted } from 'vue'
import { useUserStore } from '../../stores/user'

const store = useUserStore()

// 로딩 시
onMounted(() => {
  // console.log(store.user.email)
})

console
</script>

<style lang="scss" scoped></style>
