<template>
    <CardContent>
        <ul class="nav nav-tabs mb-4">
            <li v-if="!store.isStudent" class="nav-item">
                <router-link :to="{ name: 'leaveStatistics' }" :class="{ active: $route.name === 'leaveStatistics' }"
                             class="nav-link">统计信息</router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'leaveShow', params: { state: -1 } }"
                             :class="{ active: $route.name === 'leaveShow' && $route.params.state == -1 }" class="nav-link">
                    所有请假
                </router-link>
            </li>
            <li v-for="(stateName, index) in leaveStateNames" class="nav-item">
                <router-link :to="{ name: 'leaveShow', params: { state: index } }"
                             :class="{ active: $route.name === 'leaveShow' && $route.params.state == index }"
                             class="nav-link">
                    {{ stateName + "请假" }}
                </router-link>
            </li>
            <li v-if="store.isStudent" class="nav-item">
                <router-link :to="{ name: 'leaveAdd' }" :class="{ active: $route.name === 'leaveAdd' }" class="nav-link">
                    申请请假
                </router-link>
            </li>
        </ul>

        <router-view></router-view>
    </CardContent>
</template>

<script setup>
import { userStore } from '@/stores/user';
import { leaveStateNames } from "@/utils.js";

const store = userStore();
</script>