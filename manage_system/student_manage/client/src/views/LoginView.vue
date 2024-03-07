<template>
    <div class="bg-img"></div>
    <div class="cover container d-flex justify-content-center text-light">
        <CardContent class="col-3" style="background-color: rgba(0, 0, 0, 0.5);">
            <h1 style="text-align: center;" class="mb-3">登录</h1>
            <div class="row justify-content-center">
                <form>
                    <div class="mb-3">
                        <label for="username" class="form-label">账号</label>
                        <input v-model="username" type="text" id="username" class="form-control" placeholder="username"
                               required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" id="password" class="form-control" placeholder="password"
                               required>
                    </div>
                    <div class="form-text text-danger mb-2">{{ errorMsg }}</div>
                    <div class="row justify-content-center">
                        <button @click="login" type="button" class="btn btn-outline-light col-4">登录</button>
                    </div>
                </form>
            </div>
        </CardContent>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from "vue";
import { useRouter } from "vue-router";
import { userStore } from "@/stores/user";

const store = userStore();
const router = useRouter();
const username = ref("");
const password = ref("");
const errorMsg = ref("");

const login = () => {
    if (!(username.value && password.value)) {
        errorMsg.value = '请填写账号和密码';
        return;
    }

    axios({
        url: store.ip + '/user/login/',
        params: {
            username: username.value,
            password: password.value
        }
    }).then((response) => {
        const data = response.data;

        if (data.state === 'success') {
            store.isLogin = true;
            store.$patch(data.user);

            router.push({ name: 'home' });
        }
        else
            errorMsg.value = response.data.state;
    });
};
</script>

<style scoped>
.bg-img {
    background-image: url('/static/loginBackground.jpg');
    width: 100%;
    height: 100%;
    background-size: cover;
    position: fixed;
    left: 0;
    top: 0;
}

.cover {
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    backdrop-filter: blur(2px);
}
</style>