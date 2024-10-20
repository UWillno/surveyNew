<template>
	<div class="login">
		<!-- <h1>登录</h1> -->
		<el-form ref="loginForm" :rules="rules" :model="manager" label-width="">
		  <el-form-item label="" prop="username">
		    <el-input v-model="manager.username" placeholder="请输入用户名"></el-input>
		  </el-form-item>
		  <el-form-item label="" prop="password">
		    <el-input v-model="manager.password" placeholder="请输入密码"></el-input>
		  </el-form-item>
		  <el-form-item style="text-align: center;">
		    <el-button type="primary" @click="login()">登录</el-button>
		    <el-button>取消</el-button>
		  </el-form-item>
		</el-form>
	</div>
</template>

<script>
	//导入api
	import managerApi from '../api/managerApi'
	
	export default {  //默认导出
		data(){    //数据
			return {  //返回,后面加空格
				rules:{  //定义验证规则
					username:[
						{required:true , message:'请输入用户名', trigger:'blur'}
					],
					password:[
						{required:true , message:'请输入密码', trigger:'blur'},
						{min:3, max:6, message:'长度在 3 到 6 个字符', trigger:'blur'}
					]
				},
				manager:{}    //搜索绑定的用户对象  {}  空对象
			}
		},
		methods:{  //函数(方法)
			login(){  //登录
				/* this.$refs["loginForm"].validate((valid)=>{
					console.log(valid);
				}) */
				this.$refs.loginForm.validate()
				.then((resp)=>{
					//console.log(resp);
					if(resp){
						//发送登录请求
						managerApi.login(this.manager.username, this.manager.password)
						.then(resp=>{
							//console.log(resp.data.data);
							//判断登录是否成功,data不为空返回true
							if(resp.data.data){
								
								this.$message({  //消息提示
								          message: '登录成功!',
								          type: 'success',
										  duration:1000
								        });
										
								//把token和用户名保存至浏览器cookie中		
								let manager1 = resp.data.data;
								this.$cookie.set("token-survey19045",manager1.token);
								this.$cookie.set("username-survey19045",manager1.username);
								//路由跳转至主页
								this.$router.push({
									path:'/manager/home'
								})
							}else{
								this.$message({  //消息提示
								          message: '登录失败!',
								          type: 'error',
										  duration:1000
								        });
							}
							
							
						})
					}
				})
			}
		}
	}
</script>

<!-- scoped表示样式只对当前组件有效 -->
<style scoped>
	.login{
		background-image: url("../assets/login0.jpg");
		width: 100%;
		height: 100%;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	/* element标签本身就是类选择器 */
	.el-form{  
		width: 300px;
		height: 180px;
		/* 半透明白色 */
		background-color: rgba(255, 255, 255, 0.5);
		border-radius: 20px;
		padding: 50px;
	}
</style>