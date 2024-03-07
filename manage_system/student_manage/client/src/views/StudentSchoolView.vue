<template>
    <CardContent>
        <table class="table table-striped table-bordered border-dark-subtle table-hover">
            <thead>
                <tr>
                    <th scope="col">序号</th>
                    <th scope="col">学籍变动情况</th>
                    <th scope="col">日期</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(school, index) in schools">
                    <th scope="row">{{ index + 1 }}</th>
                    <td>{{ school.type }}</td>
                    <td>{{ school.date }}</td>
                </tr>
            </tbody>
        </table>

    </CardContent>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { userStore } from '@/stores/user';

const store = userStore();
const schools = ref([]);

const getSchool = () => {
    axios({
        url: store.ip + '/school/userId/',
        params: {
            userId: store.id
        }
    }).then((response) => {
        schools.value = response.data;
    });
};

getSchool();
</script>