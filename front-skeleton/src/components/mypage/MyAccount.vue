<template>
  <div class="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-4">
    <div class="rounded-lg border bg-card text-card-foreground shadow-sm w-full max-w-lg">
      <div class="flex flex-col p-6">
        <h3 class="text-2xl font-semibold whitespace-nowrap">현재 계좌 정보</h3>
      </div>
      <div class="p-6 space-y-4">
        <div class="space-y-2">
          <label
            class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
            >은행명</label
          >
          <div class="text-gray-600">{{ store.userAccount.bank }}</div>
        </div>
        <div class="space-y-2">
          <label
            class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
            >계좌번호</label
          >
          <div class="text-gray-600">{{ store.userAccount.accountNumber }}</div>
        </div>
      </div>
    </div>
    <div class="rounded-lg border bg-card text-card-foreground shadow-sm w-full max-w-lg mt-8">
      <div class="flex flex-col p-6">
        <h3 class="text-2xl font-semibold">계좌 등록 및 수정</h3>
        <p class="text-sm text-muted-foreground">계좌를 등록 또는 수정하세요</p>
      </div>
      <div class="p-6 space-y-4">
        <div class="space-y-2">
          <label
            class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
            for="bank"
            >은행명</label
          ><select
            v-model="bank"
            d="underline_select"
            class="block py-2.5 px-0 w-full text-sm text-gray-500 bg-transparent border-0 border-b-2 border-gray-200 appearance-none dark:text-gray-400 dark:border-gray-700 focus:outline-none focus:ring-0 focus:border-gray-200 peer"
            required
          >
            <option v-for="bank in banks" :key="bank" :value="bank">
              {{ bank }}
            </option>
          </select>
        </div>
        <div class="space-y-2">
          <label
            class="text-sm font-medium leading-none peer-disabled:cursor-not-allowed peer-disabled:opacity-70"
            for="account-number"
            >계좌번호</label
          ><input
            class="flex h-10 w-full rounded-md border border-input bg-background px-3 py-2 text-sm ring-offset-background file:border-0 placeholder:text-muted-foreground focus-visible:outline-none"
            id="account-number"
            placeholder="계좌번호를 입력하세요"
            v-model="accountNumber"
          />
          <p v-if="accountNumber.length > 0" :class="isValidAccount(accountNumber)? text-red-500: text-blue-500" v-text="isValidAccount(accountNumber)? '올바른 입력값임':'틀린 입력값임'"
          ></p>
        </div>
      </div>
      <div class="flex items-center p-6">
        <button type="submit" @submit.prevent="store.manageAccount">등록</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStore } from '../../stores/user'

const store = useUserStore()

const banks = ['KB', '농협', '기업', '카카오뱅크']

const bank = ref('')
const accountNumber = ref('')

const isValidAccount = (accountNumber) => {
  const accountRegex = /^(\d+|-)+$/;
  return accountRegex.test(accountNumber);
}

</script>

<style lang="scss" scoped>
</style>