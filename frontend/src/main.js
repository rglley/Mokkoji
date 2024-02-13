if (import.meta.env.MODE !== 'ssr') {
  import('./style.css')
  import('aos/dist/aos.css').then(() => {
    AOS.init()
  })
  import('./App.vue')
}
import { createApp } from 'vue'
import { createPinia } from 'pinia'
//scroll animation
import AOS from 'aos'
import App from './App.vue'
import router from './router/index.js'
import VueCookies from 'vue-cookies'

const app = createApp(App)

app.use(createPinia())
app.use(router)
app.use(VueCookies, { expires: '7d' })
// VueCookies.keys().forEach((cookie) => VueCookies.remove(cookie));

app.mount('#app')
