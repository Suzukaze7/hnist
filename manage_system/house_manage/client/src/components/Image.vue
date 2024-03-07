<template>
    <div style="z-index: 10;" class="position-fixed top-0 start-0 w-100 h-100 d-flex justify-content-center">
        <div @click="$emit('close')" class="position-absolute end-0 z-1" style="cursor: pointer;">
            <img width="40" src="../assets/x-lg.svg" alt="">
        </div>

        <img class="img-fluid z-3" :src="store.ip + '/media/' + name">
        <div class="position-absolute bg-secondary opacity-50 w-100 h-100"></div>
    </div>
</template>

<script setup>
import { userStore } from '../stores/user';

const props = defineProps({
    name: String
});
const store = userStore();
</script>