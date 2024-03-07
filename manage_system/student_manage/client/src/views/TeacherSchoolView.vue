<template>
    <CardContent>
        <table class="table table-striped table-bordered border-dark-subtle table-hover">
            <thead>
                <tr>
                    <th scope="col">序号</th>
                    <th scope="col">姓名</th>
                    <th scope="col">性别</th>
                    <th scope="col">学号</th>
                    <th scope="col">班级</th>
                    <th scope="col">当前学籍状态</th>
                    <th scope="col">操作</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(user, index) in users">
                    <th scope="row">{{ index + 1 }}</th>
                    <td>{{ user.name }}</td>
                    <td>{{ user.gender }}</td>
                    <td>{{ user.stuNum }}</td>
                    <td>{{ user.clazz }}</td>
                    <td>
                        {{ user.schools[user.schools.length - 1].type + " " + user.schools[user.schools.length - 1].date }}
                    </td>

                    <td class="col-2">
                        <div class="d-flex">
                            <button type="button" class="btn btn-outline-primary me-2" data-bs-toggle="modal"
                                    :data-bs-target="'#studentInfoModal' + index">查看详细信息</button>
                        </div>
                    </td>
                    <StudentInfoModal v-bind="{ id: index, user }">
                        <div class="d-flex justify-content-center gap-3">
                            <button v-if="store.schoolPermission(0)" @click="modifySchool(user.id, '转学')"
                                    class="btn btn-outline-warning">转学</button>
                            <button v-if="store.schoolPermission(1)" @click="modifySchool(user.id, '休学')"
                                    class="btn btn-outline-info">休学</button>
                            <button v-if="store.schoolPermission(2)" @click="modifySchool(user.id, '复学')"
                                    class="btn btn-outline-success">复学</button>
                            <button v-if="store.schoolPermission(3)" @click="modifySchool(user.id, '退学')"
                                    class="btn btn-outline-danger">退学</button>
                        </div>
                    </StudentInfoModal>

                </tr>
            </tbody>
        </table>

        <Pagination v-bind:="{ sum, pageNum, pageLen }" @getInfo="getUser" />
    </CardContent>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { userStore } from '@/stores/user';
import StudentInfoModal from '@/components/StudentInfoModal.vue';

const store = userStore();
const pageNum = ref(1);
const sum = ref(0);
const pageLen = ref(0);
const users = ref([]);

const getUser = (_pageNum) => {
    axios({
        url: store.ip + '/user/student/',
        params: {
            pageNum: _pageNum
        }
    }).then((response) => {
        const data = response.data;
        sum.value = data.sum;
        pageLen.value = data.pageLen;
        users.value = data.users;

        pageNum.value = _pageNum;
    });
};

const modifySchool = (userId, type) => {
    axios({
        url: store.ip + "/school/add/",
        params: {
            userId,
            type
        }
    }).then(() => getUser(pageNum.value));
};

getUser(1);
</script>