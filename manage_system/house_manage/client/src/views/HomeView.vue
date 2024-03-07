<template>
    <CardContent>
        <div v-if="!store.is_staff" class="dropdown">
            <button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false"
                    data-bs-auto-close="outside">
                提交危房申请
            </button>
            <div class="dropdown-menu p-4">
                <SubmitInfo class="mb-3" @update="get_info(cur)" />
            </div>
        </div>
        <table class="table table-striped table-hover">
            <thead>
                <tr>
                    <th>序号</th>
                    <td class="col-3 ">地址</td>
                    <td>持有人</td>
                    <td>是否有人居住</td>
                    <td>面积</td>
                    <td>现场实况</td>
                    <td>相关证件</td>
                    <td>审核状态</td>
                    <td v-if="store.is_staff">审核操作</td>
                    <td v-else>
                        修改信息
                    </td>
                </tr>
            </thead>

            <tbody>
                <tr v-for="(info, index) in infos" class="position-relative">
                    <th>{{ index + 1 }}</th>
                    <td>{{ info.address }}</td>
                    <td>{{ info.belong }}</td>
                    <td>{{ info.is_live }}</td>
                    <td>{{ info.square }}</td>
                    <td><a @click="info.show_image = true" href="#">现场图片</a></td>
                    <td><a @click="info.show_license = true" href="#">证件图片</a></td>

                    <td>
                        <span class="badge" :class="info.is_audit ? 'text-bg-success' : 'text-bg-warning'">
                            {{ info.is_audit ? "审核通过" : "审核中" }}
                        </span>
                    </td>

                    <td>
                        <button v-if="store.is_staff" class="btn"
                                :class="info.is_audit ? 'btn-outline-danger' : 'btn-outline-success'"
                                @click="change_audit(info)">
                            {{ info.is_audit ? "取消通过" : "通过" }}
                        </button>
                        <button v-else class="btn btn-outline-primary" data-bs-toggle="collapse"
                                :data-bs-target="'#' + index">
                            修改
                        </button>
                    </td>

                    <div class="collapse position-absolute start-0 top-100 shadow" style="z-index: 4;" :id="index">
                        <div class="card card-body">
                            <SubmitInfo :info="info" :index="index" modify @update="get_info(cur)" />
                        </div>
                    </div>
                    <Image v-if="info.show_image" :name="info.image" @close="info.show_image = false" />
                    <Image v-if="info.show_license" :name="info.license" @close="info.show_license = false" />

                </tr>
            </tbody>

        </table>
        <div class="d-flex justify-content-center">
            <Pagination v-bind="{ sum, page_len, cur }" @get_info="get_info" />
        </div>
    </CardContent>
</template>

<script setup>
import CardContent from '../components/CardContent.vue';
import Pagination from '../components/Pagination.vue';
import SubmitInfo from '../components/SubmitInfo.vue';
import { userStore } from '../stores/user';
import { ref } from 'vue';
import axios from 'axios';
import Image from '../components/Image.vue';

const store = userStore();
const sum = ref(0);
const page_len = ref(0);
const cur = ref(0);
const infos = ref([]);

const get_info = (page_num) => {
    axios({
        url: store.ip + '/info/',
        params: {
            is_staff: store.is_staff,
            id: store.id,
            page_num
        }
    }).then((response) => {
        const data = response.data;
        sum.value = data.sum;
        page_len.value = data.page_len;
        infos.value = data.infos;

        cur.value = page_num;
    });

};

const change_audit = (info) => {
    axios({
        url: store.ip + "/info/audit/" + info.id,
    }).then(() => {
        info.is_audit = !info.is_audit;
    });
}

get_info(1);
</script>