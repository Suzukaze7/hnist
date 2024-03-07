<template>
    <StudentSchoolView v-if="store.isStudent" />
    <TeacherSchoolView v-else />
</template>

<script setup>
import { userStore } from '@/stores/user';
import StudentSchoolView from '@/views/StudentSchoolView.vue';
import TeacherSchoolView from '@/views/TeacherSchoolView.vue';

const store = userStore();
</script>