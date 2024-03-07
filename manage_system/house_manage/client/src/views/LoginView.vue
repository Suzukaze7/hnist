<template>
    <FormContent>
        <form>
            <div class="mb-3">
                <label for="username" class="form-label">账号</label>
                <input v-model="username" type="text" id="username" class="form-control" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">密码</label>
                <input v-model="password" type="password" id="password" class="form-control" required>
            </div>
            <div class="form-text text-danger mb-2">{{error_msg}}</div>
            <div class="row justify-content-center">
                <button @click="login" type="button" class="btn btn-outline-primary col-4">登录</button>
            </div>
        </form>
    </FormContent>
</template>

<script setup>
    import axios from 'axios';
    import { ref } from "vue";
    import { useRouter } from "vue-router";
    import { userStore }  from "../stores/user";
    import FormContent  from "../components/FormContent.vue";

    const store = userStore();
    const router = useRouter();
    const username = ref("");
    const password = ref("");
    const error_msg = ref("");

    const login = () => {
        if (!(username.value && password.value))
        {
            error_msg.value = '请填写账号和密码';
            return;
        }

        axios({
            url : store.ip + '/login/',
            params: {
                username: username.value,
                password: password.value
            }
        }).then((response) => {

            const data = response.data;
            if (data.state === 'success')
            {
                store.is_login = true;
                store.$patch(data);
                router.push({name : 'home'});
            }
            else
                error_msg.value = response.data.state;
        });
    };
</script>
