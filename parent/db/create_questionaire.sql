
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

INSERT INTO `sys_subject` VALUES("36efdcb4a7643f2eaf737e3cd3afdd6b", "这是一道单选题", 1, 1, 2, 2, NULL, 1, 1, now(), now());
INSERT INTO `sys_subject` VALUES("c7f9ac6423ee11eb958d525400fd7bf7", "这是一道单选题", 1, 1, 2, 2, NULL, 1, 1, now(), now());
INSERT INTO `sys_subject` VALUES("f8d639c99794a849e4720776c0812c70", "这是一道多选题", 2, 1, 2, 2, NULL, 1, 1, now(), now());

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

INSERT INTO `sys_subject_option` VALUES("d9285faac6ea98f9127b6e3068c5fb88", "c7f9ac6423ee11eb958d525400fd7bf7", "选项1", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("14c2345a1f2360fca9097c7780cf4e9c", "c7f9ac6423ee11eb958d525400fd7bf7", "选项2", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("9a204c3afa857d9ae582cfab608ea53a", "c7f9ac6423ee11eb958d525400fd7bf7", "选项3", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("a7e74f02dcfdbb5d7f1b2bc406fc3a54", "c7f9ac6423ee11eb958d525400fd7bf7", "选项4", now(), now()); 

INSERT INTO `sys_subject_option` VALUES("7ff68fa03dbdca8a86ae5d389a720f41", "f8d639c99794a849e4720776c0812c70", "选项1", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("dee5cdd51876850639854cefec3b81aa", "f8d639c99794a849e4720776c0812c70", "选项2", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("0f84713147919a9a16c00ada099e1593", "f8d639c99794a849e4720776c0812c70", "选项3", now(), now()); 
INSERT INTO `sys_subject_option` VALUES("233f9fbbd70264ddd3f3b513739b5caa", "f8d639c99794a849e4720776c0812c70", "选项4", now(), now()); 

DROP TABLE IF EXISTS `sys_questionnaire_subject`; 
CREATE TABLE `sys_questionnaire_subject` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `questionnaire_id` VARCHAR(32) NOT NULL COMMENT "问卷ID",
    `subject_id` VARCHAR(32) NOT NULL COMMENT "主题ID",
    `is_root` INT NOT NULL COMMENT "是否为root", 
    `option_id` VARCHAR(32) NOT NULL COMMENT "选项ID",
    `to_subject_id` VARCHAR(32) NOT NULL COMMENT "跳题ID",
     PRIMARY KEY(`id`),
	INDEX(`subject_id`) USING BTREE,
	INDEX(`questionnaire_id`) USING BTREE,
    UNIQUE INDEX(`option_id`) USING BTREE
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "问卷和题目关联表";


DROP TABLE IF EXISTS `sys_questionnaire_answer`;

CREATE TABLE `sys_questionnaire_answer`(
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `questionaire_id` VARCHAR(32) NOT NULL COMMENT "问卷ID",
    `questionnaire_subject_id` VARCHAR(32) NOT NULL COMMENT "问卷，问题关联表ID",
    `user_id` VARCHAR(32)  NULL COMMENT "受访人ID",
    `answer` VARCHAR(32) DEFAULT NULL COMMENT "答案",
	`created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间"
)ENGINE=InnoDB CHARSET=utf8mb4 COMMENT "答案问卷";


