
drop database if exists `jy_test`;
create database `jy_test`;

use `jy_test`;

drop table if exists `sys_user`;
CREATE TABLE `sys_user` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `username` VARCHAR(100) NULL DEFAULT NULL COMMENT "登录用户名",
    `nickname` VARCHAR(100) NULL DEFAULT NULL COMMENT "显示用户名",
    `password` VARCHAR(255) NULL DEFAULT NULL COMMENT "密码",
    `avatar` VARCHAR(255) NULL DEFAULT NULL COMMENT "头像",
    `birth` DATETIME(0) NULL DEFAULT NULL COMMENT '生日',
    `phone` VARCHAR(45) NULL DEFAULT NULL COMMENT '手机号码',
    `created_at` DATETIME(0) NULL DEFAULT NULL,
    `update_at` DATETIME(0) NULL DEFAULT NULL,
    PRIMARY KEY(`id`) USING BTREE,
    UNIQUE KEY(`username`) USING BTREE, 
    UNIQUE KEY(`phone`) USING BTREE
)engine=innoDB charset=utf8mb4 COMMENT="用户表" row_format=dynamic;

INSERT INTO `sys_user` VALUES ('e9ca23d68d884d4ebb19d07889727dae', 'admin', '管理员', 'cb362cfeefbf3d8d', 'http://minio.jeecg.com/otatest/temp/lgo33_1583397323099.png', '2018-12-05 00:00:00', '18611111111',  '2020-11-06 00:00:00','2020-11-06 00:00:00' );

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
	`id` VARCHAR(32) NOT NULL COMMENT "主键",
    `role_name` VARCHAR(100) NULL DEFAULT NULL COMMENT "角色名称",
    `role_code` VARCHAR(100) NOT NULL COMMENT "角色编码",
    `description` VARCHAR(255) NULL DEFAULT NULL COMMENT "备注",
    `create_by` VARCHAR(32) NULL DEFAULT NULL COMMENT "创建者",
    `created_at` DATETIME(0) NULL DEFAULT NULL COMMENT "创建时间",
    `update_at` DATETIME(0) NULL DEFAULT NULL COMMENT "更新时间"
) ENGINE = INNODB charset=utf8mb4 COMMENT="角色表" ROW_FORMAT=dynamic;

INSERT INTO `sys_role` VALUES ('f6817f48af4fb3af11b9e8bf182f618b', '管理员', 'admin', '管理员', 'admin', '2019-05-20 11:40:26', '2019-05-20 11:40:26');

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
	`id` VARCHAR(32) NOT NULL COMMENT '主键',
    `user_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '用户id',
    `role_id` VARCHAR(32) NULL DEFAULT NULL COMMENT '角色id',
    PRIMARY KEY(`id`) USING BTREE,
    INDEX(`user_id`) USING BTREE,
    INDEX(`role_id`) USING BTREE
) engine=innoDB charset=utf8mb4 COMMENT="权限表" row_format=dynamic;

INSERT INTO `sys_user_role` VALUES("e9ca23d68d884d4ebb19d07889727dae", "e9ca23d68d884d4ebb19d07889727dae", "f6817f48af4fb3af11b9e8bf182f618b");