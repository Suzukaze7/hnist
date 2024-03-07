<template>
    <div class="d-flex justify-content-center">
        <div class="p-4 w-50">
            <div class="row">
                <div class="col-3 mb-3">
                    <label for="type" class="form-label">请假类别</label>
                    <select v-model="addLeaveInfo.type" id="type" class="form-select">
                        <option value="0">事假</option>
                        <option value="1">病假</option>
                        <option value="2">公假</option>
                    </select>
                </div>
            </div>
            <div class="row">
                <div class="col-6 mb-3">
                    <label for="start" class="form-label">起始日期</label>
                    <input v-model="addLeaveInfo.start" type="date" class="form-control" id="start">
                </div>
                <div class="col-6 mb-3">
                    <label for="end" class="form-label">结束日期</label>
                    <input v-model="addLeaveInfo.end" type="date" class="form-control" id="end">
                </div>
                <div class="col mb-3">
                    <label for="reason" class="form-label">请假事由</label>
                    <textarea v-model="addLeaveInfo.reason" id="reason" class="form-control"></textarea>
                </div>

                <div class="d-flex justify-content-center">
                    <button @click="addLeave" type="button" class="btn btn-outline-primary col-4">
                        添加申请
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
import { userStore } from '@/stores/user';

const store = userStore();
const addLeaveInfo = ref({ userId: store.id });

const addLeave = () => {
    axios({
        url: store.ip + "/leave/add/",
        params: addLeaveInfo.value
    }).then(() => addLeaveInfo.value = { userId: store.id });
};

</script>