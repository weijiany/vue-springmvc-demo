<template>
    <el-container>
        <el-header>
            <el-row>
                <el-col :span="6">
                    <div>
                        <p class="title">设备预约系统</p>
                    </div>
                </el-col>
                <el-col :span="3" :offset="15" style="text-align: right; padding: 1%">
                    <div>
                        <span style="margin-right: 1%;">{{ name }}</span>
                        <el-dropdown>
                        <span class="el-dropdown-link" style="color: #337ab7;">
                            <i class="fa fa-user"></i><i class="el-icon-arrow-down el-icon--right"></i>
                        </span>
                            <el-dropdown-menu slot="dropdown">
                                <el-dropdown-item><i class="fa fa-home"></i> 首页</el-dropdown-item>
                                <el-dropdown-item><p @click="logout"><i class="fa fa-sign-out"></i> Logout</p></el-dropdown-item>
                            </el-dropdown-menu>
                        </el-dropdown>
                    </div>
                </el-col>
            </el-row>
        </el-header>

        <el-container>
            <el-aside :width="elAsideWidth">
                <el-row class="tac">
                    <el-col>
                        <el-menu
                                :router="router"
                                default-active="1"
                                class="el-menu-vertical-demo">
                            <el-menu-item :key="aside.id"
                                          :index="aside.id"
                                          :route="aside.url"
                                          v-for="aside in asides">
                                <template slot="title">
                                    <i :class="aside.clazz"></i>
                                    <span>{{aside.name}}</span>
                                </template>
                            </el-menu-item>
                        </el-menu>
                    </el-col>
                </el-row>
            </el-aside>

            <el-container>
                <el-main>
                    <transition name="aaaa">
                        <router-view></router-view>
                    </transition>
                </el-main>

                <el-footer>
                    <div class="container">
                        <p>&copy; 2018 <b>华北理工大学--智慧软件应用研发工作室</b></p>
                    </div>
                </el-footer>
            </el-container>
        </el-container>
    </el-container>
</template>

<script>
    export default {
		name: 'adminBase',
		data() {
			return {
				elAsideWidth: "230px",
				router: true,
				name: "超级管理员",
				asides: [
					{id: "1", clazz: "fa fa-comments fa-fw", name: "公告管理", url: {name: 'information'}},
					{id: "2", clazz: "fa fa-user fa-fw", name: "人员管理", url: {name: 'people'}},
					{id: "3", clazz: "fa fa-file-text-o fa-fw", name: "设备类别管理", url: {name: 'equipmentType'}},
					{id: "4", clazz: "fa fa-safari fa-fw", name: "导航四", url: "/d"},
				],
			};
		},
        methods: {
			logout: function () {
				this.$store.commit("updateToken", "");
				this.$http.post(this.$store.state.domain + "/logout").then(res => {
					let data = res.bodyText;
					if (data === '1000') {
						this.$router.push({name: "login"});
                    } else {
						this.$notify({
                            message: '服务器异常',
                            type: 'warning'
                        });
                    }
                }, res => {
					console.log(res);
                });

			}
        }
	}
</script>

<style>
    .aaaa-enter-active {
        transition: all .8s cubic-bezier(1.0, 0.5, 0.8, 1.0);
        opacity: 0.8;
    }
    .aaaa-leave-active {
        transition: all .3s ease;
        opacity: 0;
    }
    .aaaa-enter, .aaaa-leave-to
        /* .aaaa-leave-active for below version 2.1.8 */ {
        transform: translateX(10px);
        opacity: 0;
    }
</style>