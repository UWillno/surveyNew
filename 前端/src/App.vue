<template>
  <div id="app">
    <!-- 3.使用子组件标签 -->
	<!-- <login></login> -->
	
	<!-- 指定路由渲染出口 -->
	<router-view></router-view>
  </div>
</template>

<script>
/* 1.导入子组件 */
import login from './components/login.vue'

export default {
  name: 'app',
  components: {  //2.引用子组件
    login
  }
}
</script>

<style>
#app,html,body {
	/* 设置登录的父元素html高度为100%, 登录页面100%生效 */
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
