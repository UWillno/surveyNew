<template>
	<div>
		<!-- 多条件搜索表单 -->
		<el-form :inline="true" :model="selectForm" class="demo-form-inline">
			<el-form-item  label="编号">
				<el-input clearable v-model="selectForm.id" placeholder="请输入老师编号"></el-input>
			</el-form-item>
			<el-form-item label="姓名">
				<el-input clearable v-model="selectForm.teacherName" placeholder="请输入老师姓名"></el-input>
			</el-form-item>
			<el-form-item label="状态">
				<el-select clearable v-model="selectForm.status" placeholder="请选择老师状态">
					<el-option v-for="item in statusList" :key="item.id" :label="item.text" :value="item.id">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="selectTeacher()">查询</el-button>
				<el-button type="success" @click="dialogFormVisible=true;teacher={}">新增</el-button>
			</el-form-item>
		</el-form>

		<!-- 多条件分页查询老师列表 -->
		<el-table :data="teacherList" stripe style="width: 1200px">
			
			<el-table-column label="序号" type="index" :index="getIndex" width="80">
			</el-table-column>
			<el-table-column prop="teacherName" label="姓名" width="180">
			</el-table-column>
			<el-table-column prop="gender" label="性别" width="180">
				<!-- 表格中取值需要用插槽 -->
				<template slot-scope="scope">
					<el-tag v-if="scope.row.gender==1">男</el-tag>
					<el-tag v-if="scope.row.gender==0" type="danger">女</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="bornday" label="出生日期">
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间">
			</el-table-column>
			<el-table-column prop="status" label="状态">
				<template slot-scope="scope">
					<el-tag v-if="scope.row.status == 1">正常</el-tag>
					<el-tag v-if="scope.row.status == 2" type="success">休假</el-tag>
					<el-tag v-if="scope.row.status == 3" type="warning">离职</el-tag>
					<el-tag v-if="scope.row.status == 4" type="danger">退休</el-tag>
				</template>
			</el-table-column>
			<el-table-column prop="level" label="权限级别">
			</el-table-column>
			<el-table-column label="操作" width="200px">
				<template slot-scope="scope">
					<!-- teacher=scope.row,等同于直接指向数组中的对象,改变teacher数组跟着一起变.应该指向克隆体teacher={...scope.row} -->
					<el-button style="mini" type="warning" @click="dialogFormVisible=true;teacher={...scope.row};">修改</el-button>
				
				<!-- 气泡确认删除 -->
					<el-popconfirm
					  @confirm="deleteTeacher(scope.row.id)" 
					  confirm-button-text='确定'
					  cancel-button-text='取消'
					  confirm-button-type='danger' 
					  cancel-button-type='success'
					  icon="el-icon-info"
					  icon-color="red" 
					  :title="'确定要删除[ '+scope.row.teacherName+' ]老师吗？'"
					>
					  <el-button slot="reference"  style="mini"  type="danger" >删除</el-button>
					</el-popconfirm>
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
		
		<!-- 新增和修改的对话框 -->
		<el-dialog :title="teacher.id?'修改':'新增'  " :visible.sync="dialogFormVisible">
		  <el-form :model="teacher">
		    <el-form-item label="姓名" :label-width="formLabelWidth">
		      <el-input v-model="teacher.teacherName" autocomplete="off"></el-input>
		    </el-form-item>
			<el-form-item label="性别" :label-width="formLabelWidth">
			  <el-input v-model="teacher.gender" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="手机号" :label-width="formLabelWidth">
			  <el-input v-model="teacher.phonenumber" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="生日" :label-width="formLabelWidth">
			  <el-input v-model="teacher.bornday" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="密码" :label-width="formLabelWidth">
			  <el-input v-model="teacher.password" autocomplete="off"></el-input>
			</el-form-item>
			<el-form-item label="状态" :label-width="formLabelWidth">
			  <el-input v-model="teacher.status" autocomplete="off"></el-input>
			</el-form-item>
		   <!-- <el-form-item label="活动区域" :label-width="formLabelWidth">
		      <el-select v-model="form.region" placeholder="请选择活动区域">
		        <el-option label="区域一" value="shanghai"></el-option>
		        <el-option label="区域二" value="beijing"></el-option>
		      </el-select>
		    </el-form-item> -->
		  </el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button @click="dialogFormVisible = false">取 消</el-button>
		    <el-button type="primary" @click="editTeacher()">确 定</el-button>
		  </div>
		</el-dialog>
	</div>
</template>

<script>
	import teacherApi from '../api/teacherApi'
	
	export default {
		data() {
			return {
				formLabelWidth:"80px",//label宽度
				teacher:{}, 		//新增或修改的老师对象
				dialogFormVisible: false, //true显示对话框
				total:0, 	//总页数
				teacherList:[] ,   //老师数组
				statusList: [ //状态数组
					{
						id: 1,
						text: "正常"
					},
					{
						id: 2,
						text: "休假"
					},
					{
						id: 3,
						text: "离职"
					},
					{
						id: 4,
						text: "退休"
					}
				],
				selectForm: {//搜索老师表单对象(条件对象),包含分页信息
					page: 1,   //当前页
					size: 5	   //每页条数
				} 
			}
		},
		created() {
			this.selectTeacher();//窗体加载调用查询
		},
		methods:{  //方法
			deleteTeacher(id){  //删除老师
				teacherApi.delete(id)
				.then(({data})=>{
					this.$message({  //消息提示
					  message: '删除成功!',
					  type: 'success',
					  duration:1000
					});
					this.selectTeacher();//重新查询
				})
			},
			editTeacher(){		//新增或修改老师
				//判断是新增还是修改
				if(this.teacher.id){ //修改
					teacherApi.update(this.teacher)
					.then(({data})=>{
						this.$message({  //消息提示
						  message: '修改成功!',
						  type: 'success',
						  duration:1000
						});
						this.selectTeacher();//重新查询
					})
				}else{  //新增
					teacherApi.insert(this.teacher)
					.then(({data})=>{
						this.$message({  //消息提示
						  message: '新增成功!',
						  type: 'success',
						  duration:1000
						});
						this.selectTeacher();//重新查询
					})
				}
				this.dialogFormVisible = false;//关闭弹窗
			},
			handleCurrentChange(value){  //当前页改变事件
				this.selectForm.page = value;
				this.selectTeacher();//重新查询
			},
			handleSizeChange(value){  //每页条数改变事件
				this.selectForm.size = value;
				this.selectTeacher();//重新查询
			},
			getIndex(value){   //获取正确的序号
				return  (this.selectForm.page - 1)*this.selectForm.size +value + 1;
			},
			selectTeacher(){  //查询方法
				teacherApi.list(this.selectForm)
				.then(({data})=>{
					//console.log(data);
					this.teacherList = data.data.list;//获取老师集合
					this.total = data.data.total;//获取总页数
				})   //({data})等同于resp.data, 析构后的
			}
		}
	}
</script>

<style>
</style>
