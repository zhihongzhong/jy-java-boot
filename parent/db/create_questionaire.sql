
use `jy_test`;

DROP TABLE IF EXISTS `sys_questionaire`;
CREATE TABLE `sys_questionaire`  (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `questnaire_name` VARCHAR(100) NOT NULL COMMENT "问卷名称",
    `status` TINYINT(2) NOT NULL DEFAULT 1 COMMENT "删除状态， 1为激活，2为删除",
    `created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间",
     PRIMARY KEY(`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "问卷表单";


DROP TABLE IF EXISTS `sys_subject`;
CREATE TABLE `sys_subject` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键", 
    `sub_name` VARCHAR(200) NOT NULL COMMENT "题目名称", 
    `sub_type` int(3) NOT NULL COMMENT "题目类型， 1： 单选， 2：多选， 3：评分， 4： 填空",
    `sub_status` tinyint(2) NOT NULL DEFAULT 1 COMMENT "删除状态，1为激活， 2为删除",
    `is_nullable` tinyint(2) NOT NULL DEFAULT 1 COMMENT "是否可以为空 1 为可以， 2 为不行",
    `is_leap` tinyint(2) NOT NULL DEFAULT 1 COMMENT "规则1： 是否跳题， 1为跳题， 2为不跳",
    `leap_ques` VARCHAR(32) DEFAULT NULL COMMENT "规则2：跳到指定题目ID",
    `created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间",
     PRIMARY KEY(`id`)
) ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "主题";


DROP TABLE IF EXISTS `sys_subject_option`;
CREATE TABLE `sys_subject_option` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `min` INT NOT NULL DEFAULT "校验规则， 最小值",
    `max` INT NOT NULL DEFAULT "校验规则，最大值",
    `subject_id` VARCHAR(32) NOT NULL COMMENT "关联的主题ID",
    `option_name` VARCHAR(255) NOT NULL COMMENT "选项名称",  
    `created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间",
    PRIMARY KEY(`id`),
	UNIQUE INDEX(`subject_id`) USING BTREE
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "选项答案, 无论主题是什么类型的题目都会为其创建至少一条选项";


DROP TABLE IF EXISTS `sys_questionaire_answer`; 
CREATE TABLE `sys_questionaire_answer` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `subject_id` VARCHAR(32) NOT NULL COMMENT "主题ID",
    `questionaire_id` VARCHAR(32) NOT NULL COMMENT "问卷ID",
    `user_id` VARCHAR(32) NOT NULL COMMENT "受访人ID",
    `answer` VARCHAR(32) DEFAULT NULL COMMENT "答案",
     PRIMARY KEY(`id`),
     UNIQUE INDEX(`subject_id`) USING BTREE,
     UNIQUE INDEX(`questionaire_id`) USING BTREE,
     UNIQUE INDEX(`user_id`) USING BTREE 
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "问卷和题目关联表";
