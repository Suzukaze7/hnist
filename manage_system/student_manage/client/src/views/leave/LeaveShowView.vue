<template>
    <table class="table table-striped table-bordered border-dark-subtle table-hover">
        <thead>
            <tr>
                <th scope="col" class="col-1">序号</th>
                <th v-if="!store.isStudent">姓名</th>
                <th v-if="!store.isStudent">学号</th>
                <th v-if="!store.isStudent">班级</th>
                <th scope="col">请假类别</th>
                <th scope="col">起终时间</th>
                <th scope="col">请假事由</th>
                <th scope="col">状态</th>
                <th scope="col" v-if="store.leavePermission">操作</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(leave, index) in leaves">
                <th scope="row">{{ index + 1 }}</th>
                <td v-if="!store.isStudent">{{ leave.user.name }}</td>
                <td v-if="!store.isStudent">{{ leave.user.stuNum }}</td>
                <td v-if="!store.isStudent">{{ leave.user.clazz }}</td>
                <td>{{ leaveTypeNames[leave.type] }}</td>
                <td>
                    <span>{{ leave.start + "至" }}</span>
                    <br />
                    <span>{{ leave.end }} </span>
                </td>
                <td>{{ leave.reason }}</td>
                <td>
                    <span v-if="leave.state === 0" class="badge text-bg-info">审核中</span>
                    <span v-else-if="leave.state === 1" class="badge text-bg-success">通过</span>
                    <span v-else class="badge text-bg-danger">驳回</span>
                </td>
                <td v-if="store.leavePermission">
                    <button @click="modifyLeave(leave.leave.id, 1)" class="btn btn-outline-success me-2">通过</button>
                    <button @click="modifyLeave(leave.leave.id, 2)" class="btn btn-outline-danger">驳回</button>
                </td>
            </tr>
        </tbody>
    </table>

    <Pagination v-bind:="{ sum, pageNum, pageLen }" @getInfo="getLeave" />
</template>

<script setup>
import axios from 'axios';
import { ref, watch, watchEffect } from 'vue';
import { userStore } from '@/stores/user';
import { useRoute } from 'vue-router';
import { leaveTypeNames } from '@/utils';

const store = userStore();
const pageNum = ref(1);
const sum = ref(0);
const pageLen = ref(0);
const leaves = ref([]);
const route = useRoute();

const getLeave = (_pageNum) => {
    axios({
        url: store.ip + '/leave/userId/state/',
        params: {
            pageNum: _pageNum,
            state: route.params.state,
            userId: store.isStudent ? store.id : -1
        }
    }).then((response) => {
        const data = response.data;
        sum.value = data.sum;
        pageLen.value = data.pageLen;
        leaves.value = data.leaves;

        pageNum.value = _pageNum;
    });
};

const modifyLeave = (id, state) => {
    axios({
        url: store.ip + "/leave/modify/",
        params: {
            id,
            state
        }
    }).then(() => getLeave(pageNum.value));
};

watchEffect(() => {
    if (route.params.state !== undefined)
        getLeave(1);
});
</script>