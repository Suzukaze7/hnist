<template>
    <FormContent>
        <form class="row">
            <div class="mb-3">
                <label for="username" class="form-label">账号</label>
                <input v-model="user_info.username" type="text" class="form-control" id="username">
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">密码</label>
                <input v-model="user_info.password" type="password" class="form-control" id="password">
            </div>
            <div class="mb-3">
                <label for="password-confirm" class="form-label">确认密码</label>
                <input v-model="user_info.password_confirm" type="password" class="form-control" id="password-confirm">
            </div>
            <div class="col-8">
                <label for="name" class="form-label">姓名</label>
                <input v-model="user_info.name" type="text" id="name" class="form-control">
            </div>
            <div class="col-4">
                <label for="gender" class="form-label">性别</label>
                <select v-model="user_info.gender" id="gender" class="form-select">
                    <option>男</option>
                    <option>女</option>
                </select>
            </div>
            <div class="form-text text-danger">{{ user_info.error_msg }}</div>
            <div class="mt-2 d-flex justify-content-center">
                <button @click="register" type="button" class="btn btn-outline-primary col-4">注册</button>
            </div>
        </form>
    </FormContent>
</template>


<script setup>
import axios from "axios";
import { reactive } from "vue";
import { userStore } from "../stores/user";
import { useRouter } from "vue-router";
import FormContent from "../components/FormContent.vue";

const router = useRouter();
const store = userStore();
const user_info = reactive({});

const register = () => {
    if (!(user_info.username && user_info.password && user_info.password_confirm && user_info.name && user_info.gender)) {
        user_info.error_msg = '请填写完整信息';
        return;
    }
    if (user_info.password !== user_info.password_confirm) {
        user_info.error_msg = "两次密码不一致";
        return;
    }

    axios({
        url: store.ip + '/register',
        params: {
            username: user_info.username,
            password: user_info.password,
            name: user_info.name,
            gender: user_info.gender
        }
    }).then((response) => {
        if (response.data.state === 'success') {
            store.is_login = true;
            store.id = response.data.id;
            store.$patch(user_info);

            router.push({ name: 'home' });
        }
        else
            user_info.error_msg = response.data.state;
    });
};
</script>
