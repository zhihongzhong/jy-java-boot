
use `jy_test`;

DROP TABLE IF EXISTS `sys_questionnaire`;
CREATE TABLE `sys_questionnaire`  (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `questionnaire_name` VARCHAR(100) NOT NULL COMMENT "问卷名称",
    `status` TINYINT(2) NOT NULL DEFAULT 1 COMMENT "删除状态， 1为激活，2为删除",
    `created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间",
     PRIMARY KEY(`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "问卷表单";

INSERT INTO `sys_questionnaire` VALUES("13b0715912a5951965feb3b19bd4b94c", "测试问卷名称", 1, now(), now());

DROP TABLE IF EXISTS `sys_subject`;
CREATE TABLE `sys_subject` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键", 
    `sub_name` VARCHAR(200) NOT NULL COMMENT "题目名称", 
    `sub_type` int(3) NOT NULL COMMENT "题目类型， 1： 单选， 2：多选， 3：评分， 4： 填空",
    `sub_status` tinyint(2) NOT NULL DEFAULT 1 COMMENT "删除状态，1为激活， 2为删除",
    `is_nullable` tinyint(2) NOT NULL DEFAULT 1 COMMENT "是否可以为空 1 为可以， 2 为不行",
    `is_leap` tinyint(2) NOT NULL DEFAULT 1 COMMENT "规则1： 是否跳题， 1为跳题， 2为不跳",
    `leap_ques` VARCHAR(32) DEFAULT NULL COMMENT "规则2：跳到指定题目ID",
    `min` INT NOT NULL COMMENT "校验规则， 最小值, 填空题为0， 单选题为1",
    `max` INT NOT NULL COMMENT "校验规则，最大值，填空题为0， 单选题为1",
    `created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间",
     PRIMARY KEY(`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "主题";

INSERT INTO `sys_subject` VALUES("36efdcb4a7643f2eaf737e3cd3afdd6b", "问题1", 1, 1, 2, 2, NULL, 1, 1, now(), now());

DROP TABLE IF EXISTS `sys_subject_option`;
CREATE TABLE `sys_subject_option` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `subject_id` VARCHAR(32) NOT NULL COMMENT "关联的主题ID",
    `option_name` VARCHAR(255) NOT NULL COMMENT "选项名称",  
    `created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间",
    PRIMARY KEY(`id`),
	INDEX(`subject_id`) USING BTREE
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "选项答案, 无论主题是什么类型的题目都会为其创建至少一条选项";

INSERT INTO `sys_subject_option` VALUES("3b436f5a532ca763623751838bfec109", "36efdcb4a7643f2eaf737e3cd3afdd6b", "选项1", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("b01b8005230511eb958d525400fd7bf7", "36efdcb4a7643f2eaf737e3cd3afdd6b", "选项2", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("02b4625bb809ac15be560a67ea6eb24b", "36efdcb4a7643f2eaf737e3cd3afdd6b", "选项3", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("3603d53f16734a904c93af3d798a8495", "36efdcb4a7643f2eaf737e3cd3afdd6b", "选项4", now(), now()); 

DROP TABLE IF EXISTS `sys_questionnaire_subject`; 
CREATE TABLE `sys_questionnaire_subject` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `questionnaire_id` VARCHAR(32) NOT NULL COMMENT "问卷ID",
    `subject_id` VARCHAR(32) NOT NULL COMMENT "主题ID",
     PRIMARY KEY(`id`),
	INDEX(`subject_id`) USING BTREE,
	INDEX(`questionnaire_id`) USING BTREE
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "问卷和题目关联表";

INSERT INTO `sys_questionnaire_subject` values("17627ace233911eb958d525400fd7bf7", "13b0715912a5951965feb3b19bd4b94c", "36efdcb4a7643f2eaf737e3cd3afdd6b");

DROP TABLE IF EXISTS `sys_questionnaire_answer`;

CREATE TABLE `sys_questionnaire_answer`(
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `questionnaire_subject_id` VARCHAR(32) NOT NULL COMMENT "问卷，问题关联表ID",
    `user_id` VARCHAR(32)  NULL COMMENT "受访人ID",
    `answer` VARCHAR(32) DEFAULT NULL COMMENT "答案",
	`created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间"
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "答案问卷";


