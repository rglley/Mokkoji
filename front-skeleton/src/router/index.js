import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import MyPageView from '@/views/MyPageView.vue'
import MyPage from '@/components/mypage/MyPage.vue'
import MeetingView from '@/views/MeetingView.vue'
import MainMeeting from '@/components/meeting/MainMeeting.vue'
import GroupMeeting from '@/components/meeting/GroupMeeting.vue'

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
      path: '/meeting',
      name: 'meeting',
      component: MeetingView,
      children: [
        {
          path: '/main',
          name: MainMeeting,
          component: MainMeeting
        },
        {
          path: '/group',
          name: GroupMeeting,
          component: GroupMeeting
        }
      ]
    }
  ]
})

export default router
