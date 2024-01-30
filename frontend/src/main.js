import '../src/style.css'
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import axios from 'axios'
//scroll animation
import AOS from 'aos'
import 'aos/dist/aos.css'
AOS.init()

import App from './App.vue'
import router from './router'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

app.config.globalProperties.$axios = axios
