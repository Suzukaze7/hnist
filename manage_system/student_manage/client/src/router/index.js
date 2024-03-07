import { createRouter, createWebHistory } from "vue-router"
import { userStore } from "@/stores/user"
import LoginView from "@/views/LoginView.vue"
import HomeView from "@/views/HomeView.vue"
import LeaveView from "@/views/leave/LeaveView.vue"
import LeaveAddView from "@/views/leave/LeaveAddView.vue";
import LeaveShowView from "@/views/leave/LeaveShowView.vue";
import LeaveStatisticsView from "@/views/leave/LeaveStatisticsView.vue"
import SchoolView from "@/views/SchoolView.vue"
import AdminView from "@/views/admin/AdminView.vue"
import AdminPermissionView from "@/views/admin/AdminPermissionView.vue"
import AdminAddView from "@/views/admin/AdminAddView.vue"
import LogView from "@/views/LogView.vue"

const router = createRouter({
	history: createWebHistory(import.meta.env.BASE_URL),
	routes: [
		{
			path: "",
			name: "home",
			component: HomeView
		},
		{
			path: "/login",
			name: "login",
			component: LoginView
		},
		{
			path: "/leave",
			name: "leave",
			component: LeaveView,
			children: [
				{
					path: "show/:state",
					name: "leaveShow",
					component: LeaveShowView
				},
				{
					path: "add",
					name: "leaveAdd",
					component: LeaveAddView
				},
				{
					path: "statistics",
					name: "leaveStatistics",
					component: LeaveStatisticsView
				}
			]
		},
		{
			path: "/school",
			name: "school",
			component: SchoolView
		},
		{
			path: "/admin",
			name: "admin",
			component: AdminView,
			children: [
				{
					path: "permission/:permission",
					name: "adminPermission",
					component: AdminPermissionView
				},
				{
					path: "add",
					name: "adminAdd",
					component: AdminAddView
				}
			]
		},
		{
			path: "/log",
			name: "log",
			component: LogView
		}
	]
})

router.beforeEach((to) => {
	const store = userStore();

	if (!store.isLogin && to.name !== "login" && to.name !== "register")
		return { name: "login" };
});

export default router