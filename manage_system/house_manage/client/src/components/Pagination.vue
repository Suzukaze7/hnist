<template>
    <nav class="mt-4">
        <ul class="pagination">
            <li class="page-item" :class="{'disabled': cur === l}">
                <a class="page-link">
                    &laquo;
                </a>
            </li>
            <div v-for="i in seq" class="d-flex">
                <li class="page-item">
                    <a @click="$emit('get_info', i)"
                        class="page-link"
                        :class="{ 'active': cur === i }"
                        href="#">
                        {{ i }}
                    </a>
                </li>
            </div>
            <li class="page-item">
                <a class="page-link" :class="{'disabled': cur === r || !page_sum}" href="#">
                    &raquo;
                </a>
            </li>
        </ul>
    </nav>
</template>

<script setup>
    import { computed } from 'vue';

    const props = defineProps({
        sum: Number,
        page_len: Number,
        cur: Number
    });
    const emit = defineEmits(['get_info']);

    const page_sum = computed(() => Math.ceil(props.sum / 15));
    const l = computed (() => Math.max(1, Math.min(page_sum.value - 4, props.cur - 2)));
    const r = computed(() => Math.min(page_sum.value, Math.max(5, props.cur + 2)));
    const seq = computed(() => {
        const res = [];
        for (let i = l.value; i <= r.value; i++)
            res.push(i);
        return res;
    });
</script> 