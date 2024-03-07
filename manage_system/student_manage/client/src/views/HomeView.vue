<template>
    <FormContent>
        <div class="row g-3">
            <div class="col-12">
                <label for="username" class="form-label">账号</label>
                <input v-model="userTmp.username" :placeholder="store.username" disabled class="form-control" id="username">
            </div>
            <div class="col-6">
                <label for="name" class="form-label">姓名</label>
                <input v-model="userTmp.name" :placeholder="store.name" :disabled="!isModify" class="form-control"
                       id="name">
            </div>
            <div class="col-3">
                <label for="gender" class="form-label">性别</label>
                <select v-model="userTmp.gender" :placeholder="store.gender" :disabled="!isModify" class="form-select"
                        id="gender">
                    <option>男</option>
                    <option>女</option>
                </select>
            </div>
            <div class="col-3">
                <label for="age" class="form-label">年龄</label>
                <input v-model="userTmp.age" :placeholder="store.age" :disabled="!isModify" class="form-control" id="age"
                       type="number">
            </div>
            <div class="col-6">
                <label for="stuNum" class="form-label">学号</label>
                <input v-model="userTmp.stuNum" :placeholder="store.stuNum" :disabled="!isModify" class="form-control"
                       id="stuNum">
            </div>
            <div class="col-6">
                <label for="clazz" class="form-label">班级</label>
                <input v-model="userTmp.clazz" :placeholder="store.clazz" :disabled="!isModify" class="form-control"
                       id="clazz">
            </div>
            <div class="col-6">
                <label for="college" class="form-label">学院</label>
                <input v-model="userTmp.college" :placeholder="store.college" :disabled="!isModify" class="form-control"
                       id="college">
            </div>
            <div class="col-6">
                <label for="profession" class="form-label">专业</label>
                <input v-model="userTmp.profession" :placeholder="store.profession" :disabled="!isModify"
                       class="form-control" id="profession">
            </div>
            <div class="col-12">
                <label for="identity" class="form-label">身份证号</label>
                <input v-model="userTmp.identity" :placeholder="store.identity" :disabled="!isModify" class="form-control"
                       id="identity">
            </div>

            <div v-if="isModify" class="col-12">
                <label for="password" class="form-label">密码</label>
                <input v-model="userTmp.password" :placeholder="store.password" :disabled="!isModify" class="form-control"
                       type="password" id="password">
            </div>

            <div class="col-12">
                <button @click="isModify = !isModify" class="btn btn-outline-primary me-3" type="button">
                    {{ isModify ? "取消修改" : "修改信息" }}
                </button>
                <button v-if="isModify" class="btn btn-outline-success" data-bs-toggle="modal"
                        data-bs-target="#infoModifyConfirm">
                    提交修改
                </button>
                <WarningModal v-bind="{ id: 'infoModifyConfirm', warning_info: '是否提交修改信息' }" @submit="modifyUser" />
            </div>
        </div>
    </FormContent>
</template>

<script setup>
import FormContent from "@/components/FormContent.vue";
import WarningModal from "@/components/WarningModal.vue";
import { userStore } from "@/stores/user";
import { ref, watch } from "vue";
import axios from "axios";
import { User } from "@/utils";

const store = userStore();
const isModify = ref(false);
const userTmp = ref(new User(store));

watch(isModify, (newValue) => {
    if (!newValue)
        userTmp.value = new User(store.user);
})

const modifyUser = () => {
    axios({
        url: store.ip + "/user/modify/",
        params: userTmp.value
    }).then(() => {
        isModify.value = !isModify.value;
        store.$patch(userTmp.value);
    });
};
</script>