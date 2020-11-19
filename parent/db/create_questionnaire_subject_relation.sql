
use `jy_test`;

DROP TABLE IF EXISTS  `questionnaire_subject`;
CREATE TABLE `questionnaire_subject` (
    `id` VARCHAR(32) NOT NULL COMMENT "主键",
    `questionnaire_id` VARCHAR(32) NOT NULL COMMENT "问卷ID",
    `subject_id` VARCHAR(32) NOT NULL COMMENT "题目ID", 
    `option_id` VARCHAR(32) NOT NULL COMMENT "选项ID",
    `child_id` VARCHAR(32) NOT NULL COMMENT "与option_id 对应的 child_id",
    `is_root` TINYINT(2) NOT NULL COMMENT "是否为root， 1为是，2为否",
    PRIMARY KEY(`id`)
) engine=innodb charset=utf8mb4 comment="问卷题目关联表";