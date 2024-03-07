<template>
    <div :id="'studentInfoModal' + id" class="modal fade" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
        <div class="modal-dialog modal-lg modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">学生个人信息</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">

                    <table class="mb-3 table table-striped-columns table-bordered">
                        <tbody>
                            <tr>
                                <td>姓名</td>
                                <td>{{ user.name }}</td>
                                <td>性别</td>
                                <td>{{ user.gender }}</td>
                                <td>年龄</td>
                                <td>{{ user.age }}</td>
                            </tr>
                            <tr>
                                <td>院系</td>
                                <td>{{ user.college }}</td>
                                <td>专业</td>
                                <td>{{ user.profession }}</td>
                                <td>班级</td>
                                <td>{{ user.clazz }}</td>
                            </tr>
                            <tr>
                                <td>学号</td>
                                <td>{{ user.stuNum }}</td>
                                <td>身份证号</td>
                                <td>{{ user.identity }}</td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>

                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">序号</th>
                                <th scope="col">学籍变动情况</th>
                                <th scope="col">时间</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(school, index) in user.schools">
                                <th scope="row">{{ index + 1 }}</th>
                                <td>{{ school.type }}</td>
                                <td>{{ school.date }}</td>
                            </tr>
                        </tbody>
                    </table>

                    <slot></slot>

                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
const props = defineProps({
    id: String,
    user: Object
});
</script>