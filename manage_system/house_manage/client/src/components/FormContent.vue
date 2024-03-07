<template>
    <CardContent>
        <div class="row justify-content-center">
            <div class="col-4">
                <slot></slot>
            </div>
        </div>
    </CardContent>
</template>

<script setup>
    import CardContent from './CardContent.vue';
</script>