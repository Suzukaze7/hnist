<template>
    <CardContent>
        <a class="link-info" :href="store.ip + '/log/download/'">下载日志文件</a>
        <table class="table table-hover table-striped">
            <thead>
                <tr>
                    <th scope="col" class="col-1">序号</th>
                    <th scope="col">日志</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(log, index) in logs">
                    <th scope="row">{{ index + 1 }}</th>
                    <td>{{ log }}</td>
                </tr>
            </tbody>
        </table>
        <Pagination v-bind="{ pageNum, sum, pageLen }" @getInfo="getLog" />
    </CardContent>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { userStore } from '@/stores/user';

const store = userStore();
const pageNum = ref(1);
const sum = ref(0);
const pageLen = ref(0);
const logs = ref([]);

const getLog = (_pageNum) => {
    axios({
        url: store.ip + "/log/web/",
        params: {
            pageNum: _pageNum
        }
    }).then((response) => {
        const data = response.data;
        sum.value = data.sum;
        pageLen.value = data.pageLen;
        logs.value = data.logs;

        pageNum.value = _pageNum;
    });
};

getLog(1);
</script>