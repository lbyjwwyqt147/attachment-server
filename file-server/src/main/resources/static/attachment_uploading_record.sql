/*
Navicat MySQL Data Transfer

Source Server         : 10.130.0.37.3307
Source Server Version : 50552
Source Host           : 10.130.0.37:3307
Source Database       : jwwl_attachment

Target Server Type    : MYSQL
Target Server Version : 50552
File Encoding         : 65001

Date: 2018-08-28 14:44:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attachment_uploading_record
-- ----------------------------
DROP TABLE IF EXISTS `attachment_uploading_record`;
CREATE TABLE `attachment_uploading_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attachment_original_name` varchar(255) DEFAULT NULL COMMENT '附件原始名称',
  `attachment_name` varchar(255) DEFAULT NULL COMMENT '附件上传后名称',
  `file_directory` varchar(100) DEFAULT NULL COMMENT '附件所在目录',
  `absolute_address` varchar(255) DEFAULT NULL COMMENT '附件绝对地址',
  `call_on_address` varchar(255) DEFAULT NULL COMMENT '附件访问地址',
  `attachment_postfix` char(10) DEFAULT NULL COMMENT '附件后缀',
  `attachment_type` tinyint(4) DEFAULT NULL COMMENT '附件类型1：图片 2：文档 3：视频 4:zip 5:其他',
  `attachment_size` bigint(20) DEFAULT NULL COMMENT '附件大小(kb)',
  `system_code` varchar(32) DEFAULT NULL COMMENT '系统编码 例如：1001：设备系统',
  `business_code` varchar(32) DEFAULT NULL COMMENT '业务编码 例如：1001001：访客管理',
  `business_type` varchar(32) DEFAULT NULL COMMENT '业务类型 例如： 1001001001：访客头像',
  `create_time` datetime DEFAULT NULL COMMENT '上传时间',
  `uploader_name` varchar(50) DEFAULT NULL COMMENT '上传者名称',
  `uploader_id` varchar(50) DEFAULT NULL COMMENT '上传者ID',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `attribute_one` varchar(100) DEFAULT NULL COMMENT '扩展字段1',
  `attribute_two` varchar(100) DEFAULT NULL COMMENT '扩展字段2',
  `attribute_three` varchar(100) DEFAULT NULL COMMENT '扩展字段3',
  `attribute_four` varchar(100) DEFAULT NULL COMMENT '扩展字段4',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='上传附件管理';
