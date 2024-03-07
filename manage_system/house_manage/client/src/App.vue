<template>
	<NavBar />
	<router-view />
</template>

<script setup>
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap/dist/js/bootstrap.js";
import NavBar from "./components/NavBar.vue";
import Image from "./components/Image.vue";
</script>