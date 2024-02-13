if (import.meta.env.MODE !== 'ssr') {
  import('./style.css')
  import('aos/dist/aos.css').then(() => {
    AOS.init()
  })
  import('./App.vue')
}
import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from 'pinia-plugin-persistedstate'
//scroll animation
import AOS from 'aos'
import App from './App.vue'
import router from './router/index.js'
import VueCookies from 'vue-cookies'
const pinia = createPinia()

pinia.use(piniaPluginPersistedstate)
const app = createApp(App)

app.use(pinia)
app.use(router)
app.use(VueCookies, { expires: '7d' })

app.mount('#app')
