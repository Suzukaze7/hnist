<template>
    <CardContent>
        <h1>Hello！欢迎进入个人信息界面</h1>
        <div class="row g-3">
            <div class="col-4">
                <label for="name" class="form-label">姓名</label>
                <input v-model="user.name" :placeholder="store.name" :disabled="!is_modify" class="form-control" id="name">
            </div>
            <div class="col-4">
                <label for="gender" class="form-label">性别</label>
                <select v-model="user.gender" :placeholder="store.gender" :disabled="!is_modify" class="form-select"
                        id="gender">
                    <option>男</option>
                    <option>女</option>
                </select>
            </div>
            <div v-if="store.is_staff" class="col-4">
                <label for="work_num" class="form-label">工号</label>
                <div class="input-group">
                    <span class="input-group-text">@</span>
                    <input v-model="user.work_num" :placeholder="store.work_num" :disabled="!is_modify" class="form-control"
                           id="work_num">
                </div>
            </div>
            <div v-if="store.is_staff" class="col-4">
                <label for="post" class="form-label">职务</label>
                <input v-model="user.post" :placeholder="store.post" :disabled="!is_modify" class="form-control" id="post">
            </div>
            <div v-if="store.is_staff" class="col-4">
                <label for="is_out" class="form-label">是否出外勤</label>
                <select v-model="user.is_out" :disabled="!is_modify" class="form-select" id="is_out">
                    <option>是</option>
                    <option>否</option>
                </select>
            </div>
            <div v-if="store.is_staff" class="col-4">
                <label for="solve_num" class="form-label">本周处理事务数量</label>
                <input v-model="user.solve_num" :placeholder="store.solve_num" :disabled="!is_modify" type="number"
                       class="form-control" id="solve_num">
            </div>

            <div class="col-12">
                <button @click="is_modify = !is_modify" class="btn btn-outline-primary me-3" type="button">
                    {{ is_modify ? "取消修改" : "修改信息" }}
                </button>
                <button v-if="is_modify" @click="modify_user" class="btn btn-outline-success" type="button">提交修改</button>
            </div>
        </div>
    </CardContent>
</template>

<script setup>
import CardContent from "../components/CardContent.vue";
import { userStore } from "../stores/user";
import { ref, watch } from "vue";
import axios from "axios";

const store = userStore();
const is_modify = ref(false);
const user = ref({});

const update_person = () => {
    user.value = {
        id: store.id,
        name: store.name,
        gender: store.gender,
        work_num: store.work_num,
        post: store.post,
        is_out: store.is_out,
        solve_num: store.solve_num
    };
};

update_person();
watch(is_modify, (new_value) => {
    if (!new_value)
        update_person();
})

const modify_user = () => {
    axios({
        url: store.ip + "/user/",
        params: user.value
    }).then(() => {
        is_modify.value = !is_modify.value;
        for (const i in user.value)
            store[i] = user.value[i];
    });
};

</script>