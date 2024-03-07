<template>
    <div class="modal fade" :id="id" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">提示</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <p>{{ warning_info }}</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">取消</button>
                    <button @click="$emit('submit')" type="button" class="btn btn-outline-primary"
                            data-bs-dismiss="modal">确认</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
const props = defineProps(["id", "warning_info"]);
</script>