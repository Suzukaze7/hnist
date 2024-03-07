<template>
    <nav class="navbar bg-body-tertiary">
        <div v-if="store.is_login" class="container">
            <router-link class="navbar-brand" :to="{ name: 'home' }">
                主页
            </router-link>
            <router-link class="nav-link" :to="{ name: 'user' }">
                个人信息
            </router-link>
        </div>
        <div v-else class="container d-flex justify-content-end">
            <router-link class="nav-link p-2" :to="{ name: 'login' }">
                登录
            </router-link>
            <router-link class="nav-link" :to="{ name: 'register' }">
                注册
            </router-link>
        </div>
    </nav>
</template>

<script setup>
import { userStore } from '../stores/user';

const store = userStore()
</script>