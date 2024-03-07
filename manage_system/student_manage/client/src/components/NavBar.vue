<template>
    <nav class="navbar navbar-expand-lg mb-3 bg-dark text-light" data-bs-theme="dark">
        <div class="container">
            <router-link class="navbar-brand" :to="{ name: 'home' }">
                个人中心
            </router-link>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link class="nav-link active"
                                     :to="store.isStudent ? { name: 'leaveShow', params: { state: -1 } } : { name: 'leaveStatistics' }">
                            请假管理
                        </router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link active" :to="{ name: 'school' }">
                            学籍管理
                        </router-link>
                    </li>
                    <li v-if="store.adminPermission" class="nav-item">
                        <router-link class="nav-link active" :to="{ name: 'adminPermission', params: { permission: -1 } }">
                            用户管理
                        </router-link>
                    </li>
                    <li v-if="store.adminPermission" class="nav-item">
                        <router-link class="nav-link active" :to="{ name: 'log' }">
                            日志管理
                        </router-link>
                    </li>
                </ul>

                <span class="nav-text me-3">{{ store.name }}</span>
                <a @click="logout" class="nav-link" href="#">
                    注销
                </a>
            </div>

        </div>
    </nav>
</template>

<script setup>
import { useRouter } from "vue-router";
import { userStore } from '../stores/user.js';

const store = userStore();
const router = useRouter();

const logout = () => {
    store.is_login = false;
    router.push({ name: "login" });
};
</script>