<template>
  <div>
    <img :src="imageURL" alt="S3 Image" />
  </div>
</template>
<script setup>
import AWS from 'aws-sdk'
import { onMounted, ref } from 'vue'

AWS.config.update({
  accessKeyId: 'AKIAVHLP3OU66KFNOEEI',
  secretAccessKey: '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',
  region: 'ap-northeast-2'
})

const s3 = new AWS.S3()

let imageURL = ref('')

onMounted(() => {
  const params = {
    Bucket: 'mokkoji-bucket',
    Key: '배경지/rolling_baby.png'
  }
  s3.getSignedUrl('getObject', params, (err, url) => {
    if (err) {
      console.error(err)
    } else {
      console.log(url)
      imageURL.value = url
    }
  })
})
</script>
<style></style>
