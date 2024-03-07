const leaveTypeNames = ["事假", "病假", "公假"];
const leaveStateNames = ["未审核", "已通过", "被驳回"];
const pow = [1, 10, 100, 1000, 10000, 100000];
const permissionNames = ["转学", "休学", "复学", "退学", "请假", "管理员"];

class User {
    constructor(user) {
        if (user) {
            this.id = user.id
            this.username = user.username
            this.password = user.password;
            this.name = user.name;
            this.gender = user.gender;
            this.stuNum = user.stuNum;
            this.clazz = user.clazz;
            this.age = user.age;
            this.college = user.college;
            this.profession = user.profession;
            this.identity = user.identity;
            this.permission = user.permission;
            this.permissionStr = "";
            this.permissionArr = [];
            for (let i = 0; i < permissionNames.length; i++)
                if (this.getPermission(i)) {
                    this.permissionArr.push(i);
                    this.permissionStr += (this.permissionStr.length ? ", " : "") + permissionNames[i];
                }
        }
        else {
            this.id = 0;
            this.username = "";
            this.password = "";
            this.name = "";
            this.gender = "";
            this.stuNum = "";
            this.clazz = "";
            this.age = 0;
            this.college = "";
            this.profession = "";
            this.identity = "";
            this.permission = 0;
            this.permissionStr = "";
            this.permissionArr = [];
        }
    }

    getPermission(idx) {
        return Math.round(this.permission / pow[idx]) % 2 === 1;
    }

    updatePermissionFromPermissionArr() {
        for (const i of this.permissionArr)
            this.permission += pow[i];
    }
}

export { leaveTypeNames, leaveStateNames, pow, permissionNames, User }