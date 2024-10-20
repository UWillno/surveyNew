<template>
	<div class="myhead">
		<!-- logo -->
		<div class="logo">
			<img src="../assets/logo1.png" alt="" height="60px">
		</div>
		<!-- 主体文字 -->
		<div class="title">学院调查管理系统</div>
		<!-- 欢迎文字, 悬浮账号,显示修改密码和注销 -->
		<div class="welcome">
			
			<el-dropdown @command="handleCommand">
			  <span class="el-dropdown-link">
			    欢迎你: admin  <i class="el-icon-arrow-down el-icon--right"></i>
			  </span>
			  <el-dropdown-menu slot="dropdown">
			    <el-dropdown-item command="a">修改密码</el-dropdown-item>
			    <el-dropdown-item command="b">注销</el-dropdown-item>
			  </el-dropdown-menu>
			</el-dropdown>
		</div>
	</div>
</template>

<script>
	export default {
		data(){
			return {
				
			}
		},
		methods:{
			handleCommand(value){
				if(value == 'a'){
					alert("修改密码")
				}else if(value == 'b'){
					alert("注销")
				}
			}
		}
	}
</script>

<style>
	*{
		margin: 0;
		padding: 0;
	}
	.myhead{
		height: 100px;
		background-color:#2a2e32;
		color: white;
	}
	.logo{
		width: 350px;
		height: 100px;
		padding: 20px 0 0 20px;
		float: left;
		/* 拯救布局 */
		box-sizing: border-box;
	}
	.title{
		width: 800px;
		text-align: center;
		height: 100px;
		line-height: 100px;
		font-size: 30px;
		font-weight: bold;
		float: left;
		/* 拯救布局 */
		box-sizing: border-box;
	}
	.welcome{
		text-align: right;
		width: 200px;
		height: 100px;
		line-height: 100px;
		float: left;
	}
	.el-dropdown-link {
	    cursor: pointer;
	    color: #409EFF;
	  }
	  .el-icon-arrow-down {
	    font-size: 12px;
	  }
</style>