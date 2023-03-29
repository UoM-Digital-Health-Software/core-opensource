import { createRouter, createWebHistory } from 'vue-router';
import Main from '../views/Main';
import Help from '../views/Help.vue';

const routes = [
  {
    path: '/',
    name: 'Main',
    component: Main,
  },
  {
    path: '/help/',
    name: 'Help',
    component: Help,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
