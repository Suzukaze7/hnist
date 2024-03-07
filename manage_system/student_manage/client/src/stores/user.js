import { defineStore } from 'pinia'

export const userStore = defineStore('user', {
    state: () => ({
        ip: "http://localhost:8080",

        isLogin: false,
        id: 0,
        username: "",
        password: "",
        name: "",
        gender: "",
        stuNum: "",
        clazz: "",
        age: 0,
        college: "",
        profession: "",
        identity: "",
        permission: 0,
        pow: [1, 10, 100, 1000, 10000, 100000]
    }),
    getters: {
        getPermission(state) {
            return (idx) => Math.round(state.permission / state.pow[idx]) % 2 === 1;
        },
        isStudent() {
            for (let i = 0; i < 6; i++)
                if (this.getPermission(i))
                    return false;
            return true;
        },
        adminPermission(state) {
            return this.getPermission(5);
        },
        schoolPermission(state) {
            return (idx) => this.getPermission(idx);
        },
        leavePermission(state) {
            return this.getPermission(4);
        }
    }
})
