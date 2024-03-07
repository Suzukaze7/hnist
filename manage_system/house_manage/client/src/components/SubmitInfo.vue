<template>
    <div class="row">
        <div class="col-12 mb-3">
            <label class="form-label" for="address">地址</label>
            <input v-if="modify" v-model="info_tmp.address" :placeholder="info.address" class="form-control" id="address">
            <input v-else v-model="info_tmp.address" class="form-control" id="address">
        </div>
        <div class="col-3 mb-3">
            <label class="form-label" for="is_live">是否有人居住</label>
            <select v-model="info_tmp.is_live" class="form-select" id="is_live">
                <option>是</option>
                <option>否</option>
            </select>
        </div>
        <div class="col-3 mb-3">
            <label class="form-label" for="square">面积</label>
            <input v-if="modify" v-model="info_tmp.square" type="number" id="square" class="form-control"
                   :placeholder="info.square">
            <input v-else v-model="info_tmp.square" type="number" id="square" class="form-control">
        </div>
        <div class="mb-3">
            <label for="image" class="form-label">现场实况</label>
            <input @change="input_image" class="form-control" id="image" type="file">
        </div>
        <div class="mb-3">
            <label for="license" class="form-label">相关证件</label>
            <input @change="input_license" class="form-control" id="license" type="file">
        </div>

        <div class="d-flex justify-content-center">
            <button @click="submit_info" :disabled="diabled" class="btn btn-outline-info">
                {{ modify ? "提交修改" : "提交申请" }}
            </button>
            <button v-if="modify" @click="delete_info" class="btn btn-outline-danger ms-4">删除</button>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { computed, ref } from 'vue';
import { userStore } from '../stores/user';

const store = userStore();
const props = defineProps({
    info: Object,
    index: Number,
    modify: Boolean
});
const emit = defineEmits(["update"]);
const info_tmp = ref({});
if (props.modify) {
    for (const i in props.info)
        info_tmp.value[i] = props.info[i];
    info_tmp.value.image = info_tmp.value.license = null;
}
const diabled = computed(() => {
    const value = info_tmp.value;
    return !(value.address && value.is_live && value.square && (value.modify || (value.image && value.license)));
});

const input_image = (event) => {
    info_tmp.value.image = event.target.files[0];
};
const input_license = (event) => {
    info_tmp.value.license = event.target.files[0];
}

const submit_info = () => {
    const data = new FormData();
    const value = info_tmp.value;

    data.append("is_modify", props.modify);

    if (props.modify)
        data.append("id", value.id);
    else
        data.append("person_id", store.id);
    data.append("address", value.address);
    data.append("is_live", value.is_live);
    data.append("square", value.square);
    data.append("has_image", !!value.image);
    data.append("has_license", !!value.license);
    if (value.image)
        data.append("image", info_tmp.value.image);
    if (value.license)
        data.append("license", info_tmp.value.license);

    axios({
        method: "post",
        url: store.ip + "/info/submit/",
        data,
    }).then(() => {
        emit("update");
    });
}

const delete_info = () => {
    axios({
        url: store.ip + "/info/delete/" + props.info.id
    }).then(() => {
        emit("update");
    });
};
</script>