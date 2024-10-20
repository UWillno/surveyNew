/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : survey

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 12/09/2022 17:19:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for class_teacher
-- ----------------------------
DROP TABLE IF EXISTS `class_teacher`;
CREATE TABLE `class_teacher`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '班级老师编号',
  `class_id` int NOT NULL COMMENT '班级编号',
  `teacher_id` int NOT NULL COMMENT '老师编号',
  `create_time` date NOT NULL COMMENT '创建时间',
  `version` int NOT NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of class_teacher
-- ----------------------------
INSERT INTO `class_teacher` VALUES (1, 1, 1, '2022-06-01', 1);
INSERT INTO `class_teacher` VALUES (3, 2, 1, '2022-03-03', 1);
INSERT INTO `class_teacher` VALUES (5, 3, 1, '2022-06-01', 1);
INSERT INTO `class_teacher` VALUES (8, 4, 1, '2022-06-23', 1);
INSERT INTO `class_teacher` VALUES (9, 4, 2, '2022-06-23', 1);
INSERT INTO `class_teacher` VALUES (10, 4, 4, '2022-06-23', 1);
INSERT INTO `class_teacher` VALUES (11, 1, 3, '2022-06-23', 1);
INSERT INTO `class_teacher` VALUES (12, 2, 4, '2022-06-23', 1);
INSERT INTO `class_teacher` VALUES (13, 5, 1, '2022-06-23', 1);

-- ----------------------------
-- Table structure for clazz
-- ----------------------------
DROP TABLE IF EXISTS `clazz`;
CREATE TABLE `clazz`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '班级的id',
  `class_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级名称',
  `grade_id` int NOT NULL COMMENT '年级id',
  `create_time` date NOT NULL COMMENT '创建修改事件',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '逻辑删除,0为正常,1为删除',
  `status` int NOT NULL DEFAULT 1 COMMENT '班级状态,1为正常,2为实习,3为毕业',
  `version` int NOT NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of clazz
-- ----------------------------
INSERT INTO `clazz` VALUES (1, '全日制软件技术08班', 1, '2022-05-06', 0, 3, 1);
INSERT INTO `clazz` VALUES (2, '全日制软件技术09班', 2, '2022-05-15', 0, 1, 1);
INSERT INTO `clazz` VALUES (3, '全日制大数据技术17班', 3, '2022-05-15', 0, 1, 1);
INSERT INTO `clazz` VALUES (4, '全日制移动技术2001班', 2, '2022-05-15', 0, 1, 1);
INSERT INTO `clazz` VALUES (5, '全日制移动技术2002班', 2, '2022-05-18', 0, 1, 1);
INSERT INTO `clazz` VALUES (6, '全日制软件技术10班', 2, '2022-06-06', 0, 1, 1);
INSERT INTO `clazz` VALUES (7, '大数据10班', 7, '2022-06-21', 1, 1, 1);

-- ----------------------------
-- Table structure for grade
-- ----------------------------
DROP TABLE IF EXISTS `grade`;
CREATE TABLE `grade`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '年级的id',
  `grade_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '年级名称',
  `create_time` date NOT NULL COMMENT '创建时间',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态,1正常,2实习,3毕业',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int NOT NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of grade
-- ----------------------------
INSERT INTO `grade` VALUES (1, '2019', '2022-05-06', 3, 0, 1);
INSERT INTO `grade` VALUES (2, '2020', '2022-05-07', 1, 0, 1);
INSERT INTO `grade` VALUES (3, '2021', '2022-05-07', 1, 0, 1);
INSERT INTO `grade` VALUES (4, '2018', '2022-05-07', 1, 0, 1);
INSERT INTO `grade` VALUES (6, '2017', '2022-05-07', 1, 0, 1);
INSERT INTO `grade` VALUES (7, '2022', '2022-05-07', 1, 0, 1);

-- ----------------------------
-- Table structure for item_options
-- ----------------------------
DROP TABLE IF EXISTS `item_options`;
CREATE TABLE `item_options`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '选项id',
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选项内容',
  `score` int NOT NULL COMMENT '选项分值',
  `item_id` int NOT NULL COMMENT '调查项的id',
  `create_time` date NOT NULL COMMENT '创建时间',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '逻辑删除,0正常,1删除',
  `version` int NOT NULL DEFAULT 1 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of item_options
-- ----------------------------
INSERT INTO `item_options` VALUES (1, '有', 0, 1, '2022-05-14', 1, 1);
INSERT INTO `item_options` VALUES (2, '无', 10, 1, '2022-05-14', 1, 1);
INSERT INTO `item_options` VALUES (3, '不知道', 0, 1, '2022-05-14', 1, 1);
INSERT INTO `item_options` VALUES (4, '发烧', 0, 1, '2022-05-14', 1, 1);
INSERT INTO `item_options` VALUES (5, '不发烧', 10, 1, '2022-05-14', 1, 1);
INSERT INTO `item_options` VALUES (6, '不知道', 0, 1, '2022-05-14', 1, 1);
INSERT INTO `item_options` VALUES (7, '发烧', 0, 1, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (8, '不发烧', 10, 1, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (9, '去过', 10, 2, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (10, '没去过', 0, 2, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (11, '红', 0, 3, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (12, '灰', 5, 3, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (13, '绿', 10, 3, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (14, '刘德华', 5, 4, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (15, '赵本山', 0, 4, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (16, '王一博', 10, 4, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (17, '学院', 10, 5, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (18, '清华大学', 5, 5, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (19, '北京大学', 5, 5, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (20, '哈佛大学', 0, 5, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (21, '俄罗斯', 10, 6, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (22, '乌克兰', 0, 6, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (23, '10000以上', 10, 7, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (24, '5000以上', 5, 7, '2022-05-14', 0, 1);
INSERT INTO `item_options` VALUES (25, '2000以上', 0, 7, '2022-05-14', 0, 1);

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'token',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES (1, 'admin', '$2a$10$06NH7RGtu7ZY3MqPV8qvm.VpLvuYWjLCRHCw36DBP67EUZQgH1VpC', 'd2f9ed41-c8a8-4ea8-8c89-be5e6ac22cf11662870555151');
INSERT INTO `manager` VALUES (2, 'aaa', '1234', 'ccf2c051-3523-405d-bea5-e029981de54c1662860752567');
INSERT INTO `manager` VALUES (3, 'bbb', '$2a$10$fYf97tqQca2Xo4svL.oGBeU0DVBuZl.IbYLNtXD.Vx.zAiEXLA.B2', 'b79f33d9-d1fb-4fb1-ba14-f7be4b3949411661591506957');
INSERT INTO `manager` VALUES (4, 'hjj', '$2a$10$t/TGq5R5.FU1SMfFbFJNjOxuGS1GpZYfWD7pefPm7f.Enue6pfyze', 'f2ae5db9-80ed-474c-b962-39e2329a45011662282815910');
INSERT INTO `manager` VALUES (5, 'lan', '$2a$10$6iRuCQxc/Ph1737EV5Swce58K6b7jVrXBWdbp48xXUgiQ1OYXQh5q', 'bbe0432f-0dd6-4c85-93ad-605fcbaa42211662283416292');
INSERT INTO `manager` VALUES (6, 'ccc', '$2a$10$5rjaiIURTXsKkJgMWGDhx.hX/MbXyZMtb4r7xm3X18kn.L2mXiEey', '37ed2b0c-b05d-480e-b738-0bb0b0a1bb611662862672798');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `studnet_id` int NOT NULL AUTO_INCREMENT COMMENT '学生的id',
  `student_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生的学号',
  `student_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `gender` int NULL DEFAULT NULL COMMENT '学生性别,0女,1男',
  `phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学生手机号码',
  `bornday` date NULL DEFAULT NULL COMMENT '出生日期',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '123456' COMMENT '登录密码',
  `class_id` int NULL DEFAULT NULL COMMENT '班级编号',
  `status` int NULL DEFAULT 1 COMMENT '状态,1正常,2请假,3休学,4毕业',
  `create_time` date NOT NULL COMMENT '创建时间',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '逻辑删除,0正常,1删除',
  `version` int NOT NULL DEFAULT 1 COMMENT '乐观所',
  `openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信的唯一Id',
  `sessionkey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户微信的密钥',
  PRIMARY KEY (`studnet_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (3, '1001', '陆睿', 1, '1991823121', NULL, '123456', 1, 1, '2022-05-15', 0, 1, 'o6DKU5YUfU5hpbx9JKcdHwYrKY0I', 'rxkd1W7Qlx/0kAgwFa1k2w==');
INSERT INTO `student` VALUES (4, '1002', '小蓝', 1, '15071161152', NULL, '123456', 1, 1, '2022-06-06', 0, 1, NULL, NULL);

-- ----------------------------
-- Table structure for survey_item
-- ----------------------------
DROP TABLE IF EXISTS `survey_item`;
CREATE TABLE `survey_item`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '调查项id',
  `survey_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '调查内容',
  `score` int NOT NULL COMMENT '分值',
  `create_time` date NOT NULL COMMENT '创建时间',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '逻辑删除,0正常,1删除',
  `version` int NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `checked` int NOT NULL COMMENT '是否启动,0未启用, 1启用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of survey_item
-- ----------------------------
INSERT INTO `survey_item` VALUES (1, '这两天发烧了吗?', 10, '2022-05-14', 0, 1, 1);
INSERT INTO `survey_item` VALUES (2, '这14天是否出过中高风险地区?', 10, '2022-06-05', 0, 1, 1);
INSERT INTO `survey_item` VALUES (3, '健康码是什么颜色?', 10, '2022-06-05', 0, 1, 1);
INSERT INTO `survey_item` VALUES (4, '你觉得下面哪个谁最帅?', 10, '2022-06-05', 0, 1, 1);
INSERT INTO `survey_item` VALUES (5, '你心中哪个大学最好?', 10, '2022-05-14', 0, 1, 1);
INSERT INTO `survey_item` VALUES (6, '你支持俄罗斯还是乌克兰?', 10, '2022-05-14', 0, 1, 1);
INSERT INTO `survey_item` VALUES (7, '你的收入是?', 10, '2022-05-14', 0, 1, 1);

-- ----------------------------
-- Table structure for survey_record
-- ----------------------------
DROP TABLE IF EXISTS `survey_record`;
CREATE TABLE `survey_record`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `item_id` int NOT NULL COMMENT '调查项id',
  `total_score` int NOT NULL COMMENT '满分',
  `options_id` int NOT NULL COMMENT '选项id',
  `options_score` int NOT NULL COMMENT '选项得分',
  `is_ok` int NOT NULL COMMENT '是否合格,0合格,1不合格',
  `create_time` date NOT NULL COMMENT '创建时间',
  `is_delete` int NOT NULL DEFAULT 0 COMMENT '逻辑删除0正常,1删除',
  `version` int NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `student_id` int NOT NULL COMMENT '学生编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of survey_record
-- ----------------------------
INSERT INTO `survey_record` VALUES (1, 1, 10, 7, 0, 0, '2022-06-04', 0, 1, 3);
INSERT INTO `survey_record` VALUES (2, 2, 10, 9, 10, 1, '2022-06-04', 0, 1, 3);
INSERT INTO `survey_record` VALUES (3, 3, 10, 12, 5, 0, '2022-06-04', 0, 1, 3);
INSERT INTO `survey_record` VALUES (4, 4, 10, 16, 10, 1, '2022-06-04', 0, 1, 3);
INSERT INTO `survey_record` VALUES (5, 5, 10, 17, 10, 1, '2022-06-04', 0, 1, 3);
INSERT INTO `survey_record` VALUES (6, 6, 10, 21, 10, 1, '2022-06-04', 0, 1, 3);
INSERT INTO `survey_record` VALUES (7, 7, 10, 23, 10, 1, '2022-06-04', 0, 1, 3);
INSERT INTO `survey_record` VALUES (8, 1, 10, 7, 0, 0, '2022-06-05', 0, 1, 3);
INSERT INTO `survey_record` VALUES (9, 2, 10, 9, 10, 1, '2022-06-05', 0, 1, 3);
INSERT INTO `survey_record` VALUES (10, 3, 10, 12, 5, 0, '2022-06-05', 0, 1, 3);
INSERT INTO `survey_record` VALUES (11, 4, 10, 16, 10, 1, '2022-06-05', 0, 1, 3);
INSERT INTO `survey_record` VALUES (12, 5, 10, 17, 10, 1, '2022-06-05', 0, 1, 3);
INSERT INTO `survey_record` VALUES (13, 6, 10, 21, 10, 1, '2022-06-05', 0, 1, 3);
INSERT INTO `survey_record` VALUES (14, 7, 10, 23, 10, 1, '2022-06-05', 0, 1, 3);

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '老师id',
  `teacher_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师姓名',
  `gender` int NOT NULL COMMENT '老师性别,0女,1男',
  `phonenumber` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '老师手机号码(登录账号)',
  `bornday` date NULL DEFAULT NULL COMMENT '出生日期',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456' COMMENT '登录密码',
  `create_time` date NOT NULL COMMENT '创建时间',
  `status` int NOT NULL DEFAULT 1 COMMENT '状态,1正常,2休假,3离职,4退休',
  `is_delete` int UNSIGNED NOT NULL DEFAULT 0 COMMENT '逻辑删除,0正常,1删除',
  `version` int NOT NULL DEFAULT 1 COMMENT '乐观锁',
  `level` int NOT NULL COMMENT '权限等级,0为管理员,1为老师',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录令牌',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES (1, '赵志红', 1, '18888888888', '2022-05-05', '123456', '2022-05-05', 1, 0, 1, 0, '88ea89ce-cc43-4a90-946c-d742682846f01654396156855');
INSERT INTO `teacher` VALUES (2, '凌一', 0, '15071161122', '1999-01-01', '123456', '2022-06-10', 1, 0, 1, 0, NULL);
INSERT INTO `teacher` VALUES (3, '冰冰', 0, '13890123451', '1996-10-06', '123456', '2022-06-01', 2, 0, 1, 0, NULL);
INSERT INTO `teacher` VALUES (4, '刘小明', 1, '19918273161', '1990-02-02', '123456', '2022-06-01', 1, 0, 1, 0, NULL);
INSERT INTO `teacher` VALUES (5, '刘莉', 0, '18888888888', '2022-05-06', '123456', '2022-05-06', 3, 0, 1, 0, '');
INSERT INTO `teacher` VALUES (6, '朱贤贤', 1, '15071161122', '2000-12-31', '123456', '2022-06-09', 1, 0, 1, 0, NULL);
INSERT INTO `teacher` VALUES (7, '周俊', 1, '13786549860', '1988-10-10', '123456', '2022-06-10', 1, 0, 1, 0, NULL);
INSERT INTO `teacher` VALUES (8, '沈露', 0, '18945236790', '2000-01-12', '123456', '2022-06-12', 1, 0, 1, 0, NULL);
INSERT INTO `teacher` VALUES (9, '贾英红', 0, '13667213477', '1999-10-20', '123456', '2022-06-18', 1, 0, 1, 0, NULL);
INSERT INTO `teacher` VALUES (10, '范冰冰', 0, '18900110022', '1987-04-02', '123456', '2022-06-20', 1, 0, 1, 0, NULL);
INSERT INTO `teacher` VALUES (15, '郭靖', 1, '15622223333', '2022-09-09', '123456', '2022-09-09', 1, 0, 0, 1, '');
INSERT INTO `teacher` VALUES (16, '蓝文成1', 1, '15623556355', '2022-09-10', '123456', '2022-09-10', 1, 0, 0, 1, '');
INSERT INTO `teacher` VALUES (17, '米格', 0, '15622223333', '2022-09-10', '123456', '2022-09-10', 1, 0, 0, 1, '');

SET FOREIGN_KEY_CHECKS = 1;
