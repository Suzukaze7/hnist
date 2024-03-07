<template>
    <div ref="typeChartDom" class="chartDiv"></div>
    <div ref="stateChartDom" class="chartDiv"></div>
</template>

<script setup>
import axios from "axios";
import * as echarts from "echarts";
import { onMounted, ref } from "vue";
import { userStore } from "@/stores/user";
import { leaveTypeNames, leaveStateNames } from "@/utils";

const store = userStore();
const typeChartDom = ref(null);
const stateChartDom = ref(null);

onMounted(async () => {
    let data = [];
    await axios({
        url: store.ip + "/leave/statistics/type/"
    }).then((response) => {
        for (let i = 0; i <= 2; i++)
            data.push({
                name: leaveTypeNames[i] + ":" + response.data[i],
                value: response.data[i]
            });
    });
    const typeChart = echarts.init(typeChartDom.value);
    typeChart.setOption({
        series: [
            {
                type: "pie",
                data
            }
        ]
    });

    data = [];
    await axios({
        url: store.ip + "/leave/statistics/state/"
    }).then((response) => {
        for (let i = 0; i <= 2; i++)
            data.push({
                name: leaveStateNames[i] + ":" + response.data[i],
                value: response.data[i]
            });
    });
    const stateChart = echarts.init(stateChartDom.value);
    stateChart.setOption({
        series: [
            {
                type: "pie",
                data
            }
        ]
    });
});
</script>

<style scoped>
.chartDiv {
    display: inline-block;
    width: 50%;
    height: 500px;
}
</style>