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
import VueCookies from 'vue-cookies'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

app.config.globalProperties.$axios = axios

// cookie
app.use(VueCookies, { expires: '7d' })

// axios interceptor
import setupInterceptors from './services/setupInterceptors'

setupInterceptors()