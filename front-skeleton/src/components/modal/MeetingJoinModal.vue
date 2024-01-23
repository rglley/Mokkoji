<template>
  <div v-if="!confirm">
    <div
      class="text-black text-lg leading-7 max-w-xl self-center m-3 max-md:max-w-full text-justify"
    >
      <p class="text-3xl">잠깐!</p>
      모두가 행복한 모꼬지를 찾아 주셔서 감사합니다.<br /><br />
      <p class="text-primary3">회원가입</p>
      <p>후 더욱 즐겁게 참여할 수 있는 모꼬지만의 기능을 소개해 드릴게요!</p>
      <h1>직접 가진 못했지만 진심으로 축하해!!</h1>
      <p>오프라인으로 전하지 못하는 마음을 텍스트, 음성, 영상 메세지로 전달할 수 있어요.</p>
      <p>작성하신 메세지는 롤링페이퍼로 모꼬지의 주인공에게 전해집니다!</p>
      <p>
        주최하시는 행사에 초대하지 못한, 참석하지 못하는 지인이 있나요? 여러분만의 모꼬지를 기다리는
        지인들을 온라인으로 초대해보세요!
      </p>
      <p>
        참가자들의 마음과 생생한 현장이 담긴 롤링페이퍼, 사진첩을 추억으로 남겨 간직할 수 있어요.
      </p>
      <div>
        <button href="/">회원가입</button> <button @click="confirm = true">알겠어요</button>
      </div>
    </div>
  </div>
  <div v-else>
    <label>참가자들에게 성함을 알려주세요!</label><br />
    <input placeholder="미작성시 무작위 닉네임을 지정해 드립니다" v-model="userName" class="w-80"/><br/>
    <button @click="suubmitTempName" class="float-right">참가하기</button>
  </div>
</template>

<script setup>
import { ref } from 'vue'
let confirm = ref(false)
let userName = ref('')

let props = defineProps({
  conferenceIdInput: String
})

// 이름 랜덤 생성
let capFirst = (string) => string.charAt(0).toUpperCase() + string.slice(1);
let getRandomInt = (min, max) => Math.floor(Math.random() * (max - min)) + min;

let generateName = () => {
	let name1 = ["abandoned","able","absolute","adorable","adventurous","academic","acceptable","whole","whopping","wicked","wide","wide-eyed","wiggly","wild","willing","wilted","winding","windy","yummy","zany","zealous","zesty","zigzag","rocky"];
	let name2 = ["people","history","way","art","world","information","map","family","government","pop","punch","quit","reply","representative","resist","rip","rub","silly","smile","spell","stretch","stupid","tear","temporary","tomorrow","wake","wrap","yesterday","Thomas","Tom","Lieuwe"];

	var name = capFirst(name1[getRandomInt(0, name1.length + 1)]) + ' ' + capFirst(name2[getRandomInt(0, name2.length + 1)]);
	return name;
}

let suubmitTempName = (tempName) => {
  if (tempName.value == null || tempName.trim(' ').length == 0) {
    // tempName 조합 로직
    tempName = generateName();
  }
  // 서버에 회의 id, userName 전달 후 참가
  // 회의 id는 HomeView에서 받아옴
  alert(props.conferenceIdInput)
  alert(tempName)
  // alert(userName.value)
}
</script>

<style scoped>
</style>