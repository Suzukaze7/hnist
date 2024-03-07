<template>
    <div class="p-4 d-flex justify-content-center">
        <div class="col-8 row g-3">
            <div class="col-6">
                <label for="username" class="form-label">账号</label>
                <input v-model="addUserInfo.username" class="form-control" id="username">
            </div>
            <div class="col-6">
                <label for="password" class="form-label">密码</label>
                <input v-model="addUserInfo.password" type="password" class="form-control" id="password">
            </div>
            <div class="col-4">
                <label for="name" class="form-label">姓名</label>
                <input v-model="addUserInfo.name" id="name" class="form-control">
            </div>
            <div class="col-4">
                <label for="gender" class="form-label">性别</label>
                <select v-model="addUserInfo.gender" id="gender" class="form-select">
                    <option>男</option>
                    <option>女</option>
                </select>
            </div>
            <div class="col-4">
                <label for="age" class="form-label">年龄</label>
                <input v-model="addUserInfo.age" id="age" type="number" class="form-control">
            </div>
            <div class="col-6">
                <label for="stuNum" class="form-label">学号</label>
                <input v-model="addUserInfo.stuNum" id="stuNum" class="form-control">
            </div>
            <div class="col-6">
                <label for="identity" class="form-label">身份证号</label>
                <input v-model="addUserInfo.identity" id="identity" class="form-control">
            </div>
            <div class="col-4">
                <label for="college" class="form-label">院系</label>
                <input v-model="addUserInfo.college" id="college" class="form-control">
            </div>
            <div class="col-4">
                <label for="profession" class="form-label">专业</label>
                <input v-model="addUserInfo.profession" id="profession" class="form-control">
            </div>
            <div class="col-4">
                <label for="clazz" class="form-label">班级</label>
                <input v-model="addUserInfo.clazz" id="clazz" class="form-control">
            </div>

            <div class="col-12">
                <div class="row g-2">
                    <span>权限</span>
                    <div v-for="(permissionName, index) in permissionNames" class="form-check col-2">
                        <input v-model="addUserInfo.permissionArr" class="form-check-input" type="checkbox" :value="index"
                               :id="'add' + index">
                        <label class="form-check-label" :for="'add' + index">
                            {{ permissionName }}
                        </label>
                    </div>
                </div>
            </div>

            <div class="d-flex justify-content-center">
                <button @click="addUser" type="button" class="btn btn-outline-primary col-4">
                    添加用户
                </button>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { userStore } from '@/stores/user';
import { permissionNames, User } from '@/utils';

const store = userStore();
const addUserInfo = ref(new User());

const addUser = () => {
    addUserInfo.value.updatePermissionFromPermissionArr();
    delete addUserInfo.value.permissionArr;
    delete addUserInfo.value.permissionStr;

    axios({
        url: store.ip + "/user/add/",
        params: addUserInfo.value
    }).then(() => {
        addUserInfo.value = new User();
    });
}
</script>