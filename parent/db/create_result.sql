
use `jy_test`;

DROP TABLE IF EXISTS `sys_result`;

CREATE TABLE `sys_result`(
    `id` VARCHAR(32) NOT NULL COMMENT "主键ID",
    `result_name` VARCHAR(200) NOT NULL COMMENT "诊断结果",
    `status` TINYINT(2) NOT NULL DEFAULT 1 COMMENT "删除状态， 1为激活，2为删除",
    `created_at` datetime(0) DEFAULT NULL COMMENT "创建时间",
    `update_at` datetime(0) DEFAULT NULL COMMENT "修改时间",
    PRIMARY KEY (`id`) USING BTREE
) engine=innoDB charset=utf8mb4 comment="存储结果表格";

DROP TABLE IF EXISTS `sys_res_option`;

CREATE TABLE `sys_res_option`(
    `id` VARCHAR(32)  NOT NULL COMMENT "主键ID",
    `res_option_name` VARCHAR(200) NOT NULL COMMENT "选项名称",
    `res_option_operation` int DEFAULT 0 COMMENT "选项对应的操作",
    PRIMARY KEY (`id`) USING  BTREE
)engine=innoDB charset=utf8mb4 comment="存储每个对应结果的可选操作";

DROP TABLE IF EXISTS `sys_res_option_result`;

CREATE TABLE `sys_res_option_result`(
    `id` VARCHAR(32) NOT NULL COMMENT "主键ID",
    `option_id` VARCHAR(32) NOT NULL COMMENT "选项ID",
    `result_id` VARCHAR(32) NOT NULL COMMENT "结果ID",
    PRIMARY KEY (`id`) USING BTREE ,
    INDEX (`result_id`) USING BTREE ,
    INDEX(`option_id`) USING BTREE
) engine = innoDB charset=utf8mb4 comment ="存储结果页和对应选项";