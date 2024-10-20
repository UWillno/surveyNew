<template>
	<div>
		<!-- 班级管理多条件搜索表单 -->
		<el-form :inline="true" :model="selectForm" class="demo-form-inline">
			
			<el-form-item label="班级名称">
				<el-input clearable v-model="selectForm.className" placeholder="请输入班级名称"></el-input>
			</el-form-item>
			<el-form-item label="状态">
				<el-select clearable v-model="selectForm.status" placeholder="请选择班级状态">
					<el-option v-for="item in statusList" :key="item.id" :label="item.text" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item label="年级">
				<el-select clearable v-model="selectForm.gradeId" placeholder="请选择年级">
					<el-option v-for="item in gradeList" :key="item.id" :label="item.gradeName" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="selectClazz()">查询</el-button>
				<el-button type="success" @click="dialogFormVisible=true;clazz={}">新增</el-button>
			</el-form-item>
		</el-form>
		
		<!-- 多条件分页查询班级列表 -->
		<el-table :data="clazzList" stripe style="width: 1200px">
			
			<el-table-column label="序号" type="index" :index="getIndex" width="80">
			</el-table-column>
			<el-table-column prop="className" label="班级名称" width="180">
			</el-table-column>
			<el-table-column prop="gradeName" label="年级" width="80">
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间">
			</el-table-column>
			<el-table-column prop="status" label="状态">
				<template slot-scope="scope">
					<el-tag v-if="scope.row.status == 1">正常</el-tag>
					<el-tag v-if="scope.row.status == 2" type="success">实习</el-tag>
					<el-tag v-if="scope.row.status == 3" type="warning">毕业</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="count"  label="班级人数">
			</el-table-column>
			<el-table-column   label="带班老师"  width="200px">
				<template slot-scope="scope">
					<!-- 带移除的标签,  移除带班老师事件(班级id, 老师id) -->
					<el-tag
					  v-for="item in scope.row.teacherList"
					  :key="item.id"
					  @close="removeTeacher(scope.row.id,item.id)" 
					  closable
					  type="danger">
					  {{item.teacherName}}
					</el-tag>

				</template>
			</el-table-column>
			<el-table-column label="操作" width="350px">
				<template slot-scope="scope">
					<!-- 设置带班老师 , 传入班级id,  思路:  根据cid查询带这个班的老师id, 筛选不带这个班的老师id, 根据id查询不带这个班的老师集合 -->
					<el-button style="mini"  type="primary" @click="showTeacher(scope.row.id)">设置带班老师</el-button>
					<!-- clazz=scope.row,等同于直接指向数组中的对象,改变teacher数组跟着一起变.应该指向克隆体teacher={...scope.row} -->
					<el-button style="mini" type="warning">修改</el-button>
					<el-button style="mini"  type="danger" >删除</el-button>
				
				</template>
			</el-table-column>
		</el-table>
		
		<!-- 分页 -->
		 <el-pagination
		      @size-change="handleSizeChange"
		      @current-change="handleCurrentChange"
		      :current-page="selectForm.page"
		      :page-sizes="[5, 10, 20, 50]"
		      :page-size="selectForm.size"
		      layout="total, sizes, prev, pager, next, jumper"
		      :total="total">
		</el-pagination>
		
		<!-- 设置带班老师弹框, 表格  ,保存选中的状态 :reserve-selection="true"  和 row-key="id" -->
		<!-- @selection-change选中多选按钮触发事件 -->
		<el-dialog title="添加带班老师" :visible.sync="dialogTableVisible">
		  <el-table :data="teacherList" row-key="id"  @selection-change="handleSelectionChange">
			  <!-- 多选 -->
			<el-table-column
				:reserve-selection="true"
				type="selection"
				width="55">
			</el-table-column>
				  
			<el-table-column type="index" :index="getIndex1" label="序号" width="150"></el-table-column>
		    <el-table-column property="teacherName" label="老师姓名" width="150"></el-table-column>
		    <el-table-column property="phonenumber" label="手机号" width="200"></el-table-column>
		    <el-table-column property="gender" label="性别"></el-table-column>
		  </el-table>
		  
		  <!-- 带班老师分页 -->
		   <el-pagination
		        @size-change="handleSizeChangeTeacher"
		        @current-change="handleCurrentChangeTeacher"
		        :current-page="teacherForm.page"
		        :page-sizes="[5, 10, 20, 50]"
		        :page-size="teacherForm.size"
		        layout="total, sizes, prev, pager, next, jumper"
		        :total="teacherForm.total">
		  </el-pagination>
		  <div style="text-align: right;">
		  		<!-- clazz=scope.row,等同于直接指向数组中的对象,改变teacher数组跟着一起变.应该指向克隆体teacher={...scope.row} -->
		  		<el-button style="mini" type="info">取消</el-button>
		  		<el-button style="mini"  type="primary" @click="addTeacher()">确定</el-button>
		  </div>
		</el-dialog>
	</div>
</template>

<script>
	
	import clazzApi from '../api/clazzApi'
	import gradeApi from '../api/gradeApi'
	import clazzTeacherApi from '../api/clazzTeacherApi'
	import teacherApi from '../api/teacherApi'
	export default {
		data() {
			return {
				
				checkTeacher:[],  //保存选中的多个带班老师
				teacherForm:{   //带班老师分页查询条件
					page:1,
					size:5,
					total:0   //总页数, 封装在查询条件
				},
				teacherList:[] ,  //设置带班老师的集合
				dialogTableVisible:false, //带班老师弹框
				total:0,		//总条数
				clazzList:[], //班级数组
				gradeList:[],  //下拉框绑定的年级数组
				statusList: [ //状态数组
					{
						id: 1,
						text: "正常"
					},
					{
						id: 2,
						text: "实习"
					},
					{
						id: 3,
						text: "毕业"
					}
				],
				selectForm: {//搜索班级表单对象(条件对象),包含分页信息
					page: 1,   //当前页
					size: 5	   //每页条数
				} 
			}
		},
		created() {
			this.selectClazz();//窗体加载调用查询班级
			this.selectGrade();//查询年级列表
		},
		methods:{  //方法
			addTeacher(){  //添加带班老师 ,  cid, 多个老师tid
				//获取班级和多个老师id的集合
				let clazzTeacherIdList = this.checkTeacher.map(t=>{  //t表示遍历后的每个老师对象
					return {
						classId: this.teacherForm.cid,  //键最好和数据库保持一致
						teacherId: t.id
					}
				})
				console.log(clazzTeacherIdList);
				clazzTeacherApi.insert(clazzTeacherIdList)
				.then(({data})=>{
					this.$message({  //消息提示
					  message: '添加老师成功!',
					  type: 'success',
					  duration:1000
					});
					this.selectClazz();
				})
			},
			
			handleSelectionChange(value){  //选中带班老师多选按钮触发
				this.checkTeacher  = value;
			},
			
			handleCurrentChangeTeacher(value){  //老师对话框当前页改变事件
				this.teacherForm.page = value;
				this.selectTeacher();//重新查询老师
			},
			handleSizeChangeTeacher(value){  //老师对话框每页条数改变事件
				this.teacherForm.size = value;
				this.selectTeacher();//重新查询老师
			},
		
			selectTeacher(){ //多条件分页查询老师
				teacherApi.list(this.teacherForm)
				.then(({data})=>{
					this.teacherList = data.data.list;//获取P对象老师集合
					this.teacherForm.total = data.data.total;//获取总页数封装teacherForm
				})
			},
			
			getIndex1(value){  //获取老师弹窗正确序号
				return (this.teacherForm.page - 1) * this.teacherForm.size +value +1;
			},
			
			showTeacher(cid){  //显示带班老师对话框和表格, 传入cid
				this.dialogTableVisible =true ;//显示带班老师对话框
				this.teacherForm.cid = cid;//把班级id封装至多条件中
				this.selectTeacher();//查询带班老师
			},
			
			removeTeacher(cid , tid ){  //移除带班老师, 传入班级id和老师id
				//console.log(cid+","+tid);
				clazzTeacherApi.removeTeacher(cid,tid)
				.then(({data})=>{
					if(data.data){
						this.$message({  //消息提示
						  message: '移除成功!',
						  type: 'success',
						  duration:1000
						});
						this.selectClazz();
					}
				})
			},
			selectGrade(){  //查询年级列表
				gradeApi.gradeList()
				.then(({data})=>{
					if(data.data){
						this.gradeList = data.data;
					}
				})
			},
			getIndex(value){   //获取正确的序号
				return  (this.selectForm.page - 1)*this.selectForm.size +value + 1;
			},
			selectClazz(){  //查询班级列表方法
				clazzApi.list(this.selectForm)
				.then(({data})=>{
					console.log(data);
					this.clazzList = data.data.list;//获取班级集合
					this.total = data.data.total;//获取总页数
				})   //({data})等同于resp.data, 析构后的
			},
			handleCurrentChange(value){  //当前页改变事件
				this.selectForm.page = value;
				this.selectClazz();//重新查询
			},
			handleSizeChange(value){  //每页条数改变事件
				this.selectForm.size = value;
				this.selectClazz();//重新查询
			}
		}
	}
</script>

<style>
</style>