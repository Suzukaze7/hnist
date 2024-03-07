import { defineStore } from 'pinia'

export const userStore = defineStore('user', {

    state: () => ({
        ip: 'http://localhost:8000',

        is_login: false,
        id: 0,
        is_staff: false,
        name: '',
        gender: '',
        work_num: '',
        post: '',
        is_out: '',
        solve_num: 0
    }),
})
