import { createRouter, createWebHistory } from "vue-router"
import { userStore } from "../stores/user"
import axios from "axios"
import HomeView from "../views/HomeView.vue"
import UserView from "../views/UserView.vue"
import LoginView from "../views/LoginView.vue"
import RegisterView from "../views/RegisterView.vue"

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "/",
			name: "home",
			component: HomeView
		},
		{
			path: "/user",
			name: "user",
			component: UserView
		},
		{
			path: "/login",
			name: "login",
			component: LoginView
		},
		{
			path: "/register",
			name: "register",
			component: RegisterView
		}
	]
})

router.beforeEach(async (to) => {
	const store = userStore();

	if (!store.is_login)
		await axios({
			url: store.ip + "/user/"
		}).then((response) => {
			const data = response.data;
			if (data.state === "success") {
				store.is_login = true;
				store.$patch(data);

				router.push({ name: "home" });
			}
			else if (to.name !== "login" && to.name !== "register")
				router.push({ name: "login" });
		})
	else if (to.name === "login" || to.name === "register")
		router.push({ name: "home" });

	return true;
});

export default router