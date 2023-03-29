import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import { ApiService } from '@/services/api.service';
import { StoreService } from '@/services/store.service';

ApiService.initialise();
StoreService.initialise();

createApp(App).use(router).mount('#app');
