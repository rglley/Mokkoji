import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'
import MyPageView from '@/views/MyPageView.vue'
import MyPage from '@/components/mypage/MyPage.vue'
import EventListPage from '@/views/EventListView.vue'
import ResultPage from '@/views/ResultView.vue'
import MyDetail from '@/components/mypage/MyDetail.vue'
import MainMeetingView from '@/views/MainMeetingView.vue'
import GroupMeetingView from '@/views/GroupMeetingView.vue'
import Error404 from '@/components/common/Error404.vue'
import ErrorSession from '@/components/common/ErrorSession.vue'
import EditPage from '@/views/EditView.vue'
import HandleCallback from '@/components/common/HandleCallback.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/oauth2/redirect',
      component: HandleCallback
    },
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/signup',
      name: 'signup',
      component: () => import('@/components/common/SignUp.vue')
    },
    {
      path: '/mypage',
      name: 'mypage',
      component: MyPageView,
      children: [
        {
          path: '',
          name: 'mypage',
          component: MyPage
        },
        {
          path: 'detail',
          name: 'mydetail',
          component: MyDetail
        }
      ]
    },
    {
      path: '/mainmeeting/:sessionId',
      name: 'mainmeeting',
      component: MainMeetingView,
      props: true
    },
    {
      path: '/groupmeeting/:mainSessionId',
      name: 'groupmeeting',
      component: GroupMeetingView,
      props: true
    },
    {
      path: '/resultpage',
      name: 'resultpage',
      component: ResultPage
    },
    {
      path: '/eventlist',
      name: 'eventlist',
      component: EventListPage
    },
    {
      path: '/editpage',
      name: 'editpage',
      component: EditPage
    },
    {
      path: '/:pathMatch(.*)*',
      redirect: '404'
    },
    {
      path: '/404',
      name: 'Error404',
      component: Error404
    },
    {
      path: '/errorsession',
      name: 'Errorsession',
      component: ErrorSession
    }
  ]
})

export default router
