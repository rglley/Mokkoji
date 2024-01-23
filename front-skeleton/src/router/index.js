import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import MyPageView from '@/views/MyPageView.vue'
import MyPage from '@/components/mypage/MyPage.vue'
import ResultPage from '@/views/ResultView.vue'
import EditPage from '@/views/EditView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      // path: '/${userId}',
      path: '/mypage',
      name: 'mypage',
      component: MyPageView,
      children: [
        {
          path: '',
          name: MyPage,
          component: MyPage
        }
      ]
    },
    {
      path: '/resultpage',
      name: 'resultpage',
      component: ResultPage
    },
    {
      path: '/editpage',
      name: 'editpage',
      component: EditPage
    }
  ]
})

export default router
