<template>
    <a v-if="$route.params.permission == -1" :href="store.ip + '/user/download/'" class="link-info">下载用户信息</a>
    <table class="table table-striped table-bordered border-dark-subtle table-hover">
        <thead>
            <tr>
                <th scope="col">序号</th>
                <th scope="col">姓名</th>
                <th scope="col">账号</th>
                <th scope="col">密码</th>
                <th scope="col">权限</th>
                <th scope="col">操作</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(user, index) in users">
                <th scope="row">{{ index + 1 }}</th>
                <td>{{ user.name }}</td>
                <td>{{ user.username }}</td>
                <td>{{ user.password }}</td>
                <td>{{ user.permissionStr }}</td>

                <td class="col-2">
                    <div class="d-flex">
                        <button type="button" class="btn btn-outline-primary me-2" data-bs-toggle="modal"
                                :data-bs-target="'#modal' + index">修改信息</button>

                        <button @click="deleteUser(user.id)" class="btn btn-outline-danger">
                            删除
                        </button>
                    </div>
                </td>

                <div :id="'modal' + index" class="modal fade" data-bs-backdrop="static" data-bs-keyboard="false"
                     tabindex="-1">
                    <div class="modal-dialog modal-xl modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title">学生个人信息</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">

                                <table class="mb-3 table table-striped-columns table-bordered">
                                    <tbody>
                                        <tr>
                                            <td>账号</td>
                                            <td>
                                                <input v-model="user.user_tmp.username" class="form-control">
                                            </td>
                                            <td>密码</td>
                                            <td>
                                                <input v-model="user.user_tmp.password" class="form-control">
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                        <tr>
                                            <td>姓名</td>
                                            <td>
                                                <input v-model="user.user_tmp.name" class="form-control">
                                            </td>
                                            <td>性别</td>
                                            <td>
                                                <input v-model="user.user_tmp.gender" class="form-control">
                                            </td>
                                            <td>年龄</td>
                                            <td>
                                                <input v-model="user.user_tmp.age" class="form-control">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>院系</td>
                                            <td>
                                                <input v-model="user.user_tmp.college" class="form-control">
                                            </td>
                                            <td>专业</td>
                                            <td>
                                                <input v-model="user.user_tmp.profession" class="form-control">
                                            </td>
                                            <td>班级</td>
                                            <td>
                                                <input v-model="user.user_tmp.clazz" class="form-control">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>学号</td>
                                            <td>
                                                <input v-model="user.user_tmp.stuNum" class="form-control">
                                            </td>
                                            <td>身份证号</td>
                                            <td>
                                                <input v-model="user.user_tmp.identity" class="form-control">
                                            </td>
                                            <td></td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>

                                <div class="mb-2">权限</div>
                                <div class="row">
                                    <div class="col-2" v-for="(permissionName, index) in permissionNames">
                                        <div class="form-check">
                                            <input class="form-check-input" type="checkbox" :value="'modify' + index"
                                                   :id="index">
                                            <label class="form-check-label" :for="'modify' + index">
                                                {{ permissionName }}
                                            </label>
                                        </div>
                                    </div>
                                </div>

                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">取消</button>
                                <button @click="modify_user(user.user_tmp)" type="button" class="btn btn-outline-primary"
                                        data-bs-dismiss="modal">确认修改</button>
                            </div>
                        </div>
                    </div>
                </div>

            </tr>
        </tbody>
    </table>

    <Pagination v-bind:="{ sum, pageNum, pageLen }" @getInfo="getUser" />
</template>

<script setup>
import axios from 'axios';
import { ref, watch } from 'vue';
import { userStore } from '@/stores/user';
import { permissionNames, User } from '@/utils';
import { useRoute } from 'vue-router';

const store = userStore();
const pageNum = ref(1);
const sum = ref(0);
const pageLen = ref(0);
const users = ref([]);
const route = useRoute();

const getUser = (_pageNum) => {
    axios({
        url: store.ip + '/user/permission/',
        params: {
            pageNum: _pageNum,
            permission: route.params.permission
        }
    }).then((response) => {
        const data = response.data;
        sum.value = data.sum;
        pageLen.value = data.pageLen;
        users.value = data.users;
        pageNum.value = _pageNum;

        for (let i = 0; i < users.value.length; i++) {
            users.value[i] = new User(users.value[i]);
            users.value[i].user_tmp = new User(users.value[i]);
        }
    });
};

const modify_user = (user) => {
    axios({
        url: store.ip + "/user/modify/",
        params: user
    }).then(() => getUser(pageNum.value));
};

const deleteUser = (id) => {
    axios({
        url: store.ip + "/user/delete/" + id + "/"
    }).then(() => getUser(pageNum.value));
}

watch(() => {
    if (route.params.permission !== undefined)
        getUser(1);
})
</script>