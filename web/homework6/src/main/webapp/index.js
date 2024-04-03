const {createApp} = Vue;

const app = createApp({
    data() {
        return {
            form: {
                username: '',
                password: '',
            },
            isLogin: false,
            error: '',
            name: '',
            currentRequest: 0,
            totalVisit: 0,
        }
    },
    methods: {
        login() {
            if (this.form.username.length === 0 || this.form.password.length === 0) {
                this.error = '账号或密码为空';
                return;
            }

            axios.get('loginHandle.jsp', {
                params: {
                    username: this.form.username,
                    password: this.form.password
                }
            }).then(response => {
                const data = response.data;
                if (data.status === 'ok') {
                    this.isLogin = true;
                    this.name = data.name;
                    this.currentRequest = data.currentRequest;
                    this.totalVisit = data.totalVisit;
                } else {
                    this.error = data.status;
                }
            })
        }
    }
});

app.use(ElementPlus);
app.mount('#app');

export default app;