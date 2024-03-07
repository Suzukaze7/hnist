<template>
    <div class="d-flex justify-content-center">
        <nav class="mt-4">
            <ul class="pagination">
                <li class="page-item" :class="{ 'disabled': pageNum === l || !pageSum }">
                    <a class="page-link" @click="$emit('getInfo', pageNum - 1)" href="#">
                        &laquo;
                    </a>
                </li>
                <div v-for="i in seq" class="d-flex">
                    <li class="page-item">
                        <a @click="$emit('getInfo', i)" class="page-link" :class="{ 'active': pageNum === i }" href="#">
                            {{ i }}
                        </a>
                    </li>
                </div>
                <li class="page-item">
                    <a class="page-link" @click="$emit('getInfo', pageNum + 1)"
                       :class="{ 'disabled': pageNum === r || !pageSum }" href="#">
                        &raquo;
                    </a>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script setup>
import { computed } from 'vue';

const props = defineProps({
    sum: Number,
    pageNum: Number,
    pageLen: Number
});
const emit = defineEmits(['getInfo']);

const pageSum = computed(() => Math.ceil(props.sum / props.pageLen));
const l = computed(() => Math.max(1, Math.min(pageSum.value - 4, props.pageNum - 2)));
const r = computed(() => Math.min(pageSum.value, Math.max(5, props.pageNum + 2)));
const seq = computed(() => {
    const res = [];
    for (let i = l.value; i <= r.value; i++)
        res.push(i);
    return res;
});
</script> 