<template>
	<!-- 左侧导航使用100%,所以父元素必须有固定高度 -->
	<div style="height: 100%;">
		<myhead></myhead>
		<mynav></mynav>
		<myhome></myhome>
	</div>
</template>

<script>
	/* 导入子组件 */
	import myhead from './myhead.vue'
	import mynav from './mynav.vue'
	import myhome from './myhome.vue'
	
	//默认导出
	export default {
		components:{
			myhead,
			mynav,
			myhome
		}
	}
</script>

<style>
</style>