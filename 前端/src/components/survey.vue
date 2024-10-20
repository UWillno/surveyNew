<template>
	<div>
		<!-- 多条件搜索表单 -->
		<el-form :inline="true" :model="selectForm" class="demo-form-inline">
			<el-form-item label="调查内容">
				<el-input clearable v-model="selectForm.surveyText" placeholder="请输入调查内容"></el-input>
			</el-form-item>
			<el-form-item>
				<el-button type="primary" @click="selectSurvey()">查询</el-button>
				<el-button type="success" @click="dialogFormVisible=true;survey={}">新增</el-button>
			</el-form-item>
		</el-form>

		<!-- 多条件分页查询调查列表 -->
		<el-table :data="surveyList" stripe style="width: 1200px">
			<!-- 展开调查项 -->
			<el-table-column type="expand">
			      <template slot-scope="props">
			        <el-form label-position="left" inline class="demo-table-expand">
			          <el-form-item label="">
			            <span>1.发烧</span>
			            <span>2.不发烧</span>
			            <span>3.不知道</span>
			          </el-form-item>
			          
			        </el-form>
			      </template>
			    </el-table-column>
			<el-table-column label="序号" type="index" :index="getIndex" width="100" align="center">
			</el-table-column>
			<el-table-column prop="surveyText" label="调查内容" align="center">
			</el-table-column>
			<el-table-column prop="score" label="分值" width="80" align="center">
				<!-- 表格中取值需要用插槽 -->
				<!-- <template slot-scope="scope">
					<el-tag v-if="scope.row.gender==1">男</el-tag>
					<el-tag v-if="scope.row.gender==0" type="danger">女</el-tag>
				</template> -->
			</el-table-column>
			<!-- 是否启动, 0未启动, 1启动 -->
			<el-table-column label="是否启动" width="100px" align="center">
				<template slot-scope="scope">
					<el-switch
					  v-model="scope.row.checked" 
					  :active-value="1" 
					  :inactive-value="0"
					  active-color="#13ce66"
					  inactive-color="#bdbdbd">
					</el-switch>
				</template>
			</el-table-column>
			<el-table-column prop="createTime" label="创建时间" width="100px" align="center">
			</el-table-column>
			<el-table-column label="操作" width="200px"  align="center">
				<template slot-scope="scope">
					<!-- teacher=scope.row,等同于直接指向数组中的对象,改变teacher数组跟着一起变.应该指向克隆体teacher={...scope.row} -->
					<el-button style="mini" type="warning" @click="dialogFormVisible=true;survey={...scope.row};">修改</el-button>
				
				<!-- 气泡确认删除 -->
					<el-popconfirm
					  @confirm="deleteTeacher(scope.row.id)" 
					  confirm-button-text='确定'
					  cancel-button-text='取消'
					  confirm-button-type='danger' 
					  cancel-button-type='success'
					  icon="el-icon-info"
					  icon-color="red" 
					  :title="'确定要删除[ '+scope.row.surveyText+' ]吗？'"
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
		<el-dialog :title="survey.id?'修改':'新增'  " :visible.sync="dialogFormVisible">
		  <el-form :model="survey">
				<el-form-item label="调查内容" :label-width="formLabelWidth">
				  <el-input v-model="survey.surveyText" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="分值" :label-width="formLabelWidth">
				  <el-input v-model="survey.score" autocomplete="off"></el-input>
				</el-form-item>
				<el-form-item label="是否启动" :label-width="formLabelWidth">
				  <el-input v-model="survey.checked" autocomplete="off"></el-input>
				</el-form-item>
		  </el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button @click="dialogFormVisible = false">取 消</el-button>
		    <el-button type="primary" @click="editSurvey()">确 定</el-button>
		  </div>
		</el-dialog>
	</div>
</template>

<script>
	import surveyApi from '../api/surveyApi'
	
	export default {
		data() {
			return {
				formLabelWidth:"80px",//label宽度
				survey:{}, 		//新增或修改的调查对象
				dialogFormVisible: false, //true显示对话框
				total:0, 	//总页数
				surveyList:[] ,   //调查数组
				selectForm: {//搜索调查表单对象(条件对象),包含分页信息
					page: 1,   //当前页
					size: 5	   //每页条数
				} 
			}
		},
		created() {
			this.selectSurvey();//窗体加载调用查询
		},
		methods:{  //方法
			deleteSurvey(id){  //删除调查
				surveyApi.delete(id)
				.then(({data})=>{
					this.$message({  //消息提示
					  message: '删除成功!',
					  type: 'success',
					  duration:1000
					});
					this.selectSurvey();//重新查询
				})
			},
			editSurvey(){		//新增或修改调查项
				//判断是新增还是修改
				if(this.survey.id){ //修改
					surveyApi.update(this.survey)
					.then(({data})=>{
						this.$message({  //消息提示
						  message: '修改成功!',
						  type: 'success',
						  duration:1000
						});
						this.selectSurvey();//重新查询
					})
				}else{  //新增
					surveyApi.insert(this.survey)
					.then(({data})=>{
						this.$message({  //消息提示
						  message: '新增成功!',
						  type: 'success',
						  duration:1000
						});
						this.selectSurvey();//重新查询
					})
				}
				this.dialogFormVisible = false;//关闭弹窗
			},
			handleCurrentChange(value){  //当前页改变事件
				this.selectForm.page = value;
				this.selectSurvey();//重新查询
			},
			handleSizeChange(value){  //每页条数改变事件
				this.selectForm.size = value;
				this.selectSurvey();//重新查询
			},
			getIndex(value){   //获取正确的序号
				return  (this.selectForm.page - 1)*this.selectForm.size +value + 1;
			},
			selectSurvey(){  //查询方法
				surveyApi.list(this.selectForm)
				.then(({data})=>{
					//console.log(data);
					this.surveyList = data.data.list;//获取调查集合
					this.total = data.data.total;//获取总页数
				})   //({data})等同于resp.data, 析构后的
			}
		}
	}
</script>

<style>
</style>
