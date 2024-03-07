<template>
    <CardContent>
        <ul class="nav nav-tabs mb-3">
            <li class="nav-item">
                <router-link :to="{ name: 'adminPermission', params: { permission: -1 } }"
                             :class="{ active: $route.name === 'adminPermission' && $route.params.permission == -1 }"
                             class="nav-link">
                    所有用户
                </router-link>
            </li>
            <li v-for="(type, index) in permissionNames" class="nav-item">
                <router-link :to="{ name: 'adminPermission', params: { permission: index } }"
                             :class="{ active: $route.name === 'adminPermission' && $route.params.permission == index }"
                             class="nav-link">
                    {{ type + "权限" }}
                </router-link>
            </li>
            <li class="nav-item">
                <router-link :to="{ name: 'adminAdd' }" :class="{ active: $route.name === 'adminAdd' }" class="nav-link">
                    添加用户
                </router-link>
            </li>
        </ul>

        <router-view></router-view>
    </CardContent>
</template>

<script setup>
import { permissionNames } from '@/utils';
</script>