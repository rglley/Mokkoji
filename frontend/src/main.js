// import '../src/style.css'
// import './style.css'
if (import.meta.env.MODE !== 'ssr') {
    import('./style.css').then((module) => {
        // You can use the `module` object if needed
    });
    import('aos/dist/aos.css').then((module) => {
        AOS.init();
    });
}
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import axios from 'axios'
//scroll animation
import AOS from 'aos'
// import 'aos/dist/aos.css'
// AOS.init()

import App from './App.vue'
import router from './router/index.js'
import VueCookies from 'vue-cookies'

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.mount('#app')

app.config.globalProperties.$axios = axios

// cookie
app.use(VueCookies, { expires: '7d' })

// axios interceptor
import setupInterceptors from './services/setupInterceptors.js'

setupInterceptors()
