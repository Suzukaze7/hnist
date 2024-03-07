import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import CardContent from "./components/CardContent.vue"
import Pagination from './components/Pagination.vue';

const app = createApp(App)

app.use(createPinia())
app.use(router)

app.component("CardContent", CardContent)
app.component("Pagination", Pagination)

app.mount('#app')
