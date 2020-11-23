
use `jy_test`;

DROP TABLE IF EXISTS  `subject_tree`;
CREATE TABLE `subject_tree` (
    `id` VARCHAR(32) NOT NULL COMMENT "主键",
    `questionnaire_id` VARCHAR(32) NOT NULL COMMENT "问卷ID",
    `subject_id` VARCHAR(32) NOT NULL COMMENT "题目ID",
    `option_id` VARCHAR(32) NOT NULL COMMENT "选项ID",
    `to_subject_id` VARCHAR(32) NOT NULL COMMENT "跳题ID",
    UNIQUE INDEX(`option_id`) USING BTREE, 
    PRIMARY KEY(`id`)
) engine=innodb charset=utf8mb4 comment="问卷题目关联表";
