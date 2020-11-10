
use `jy_test`;

DROP TABLE IF EXISTS `local_file`;

CREATE TABLE `local_file`  (
  `id` varchar(32) NOT NULL COMMENT '主键id',
  `file_name` varchar(255) NULL DEFAULT NULL COMMENT '文件名称',
  `url` varchar(255) NULL DEFAULT NULL COMMENT '文件地址',
  `create_by` varchar(32) NULL DEFAULT NULL COMMENT '创建人登录名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = 'local File';