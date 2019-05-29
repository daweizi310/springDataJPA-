/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50562
 Source Host           : localhost:3306
 Source Schema         : jpa_demo

 Target Server Type    : MySQL
 Target Server Version : 50562
 File Encoding         : 65001

 Date: 29/05/2019 10:11:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `aid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`aid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1130727765232533504', '王源抽烟', '向公众道歉了', '1130759551480119296');
INSERT INTO `article` VALUES ('1130728055717445632', '向往的生活', '从爸爸去哪开始', '1130750260757041152');
INSERT INTO `article` VALUES ('1130748908366630912', 'string', 'string', NULL);
INSERT INTO `article` VALUES ('1130766000159653888', 'string', 'string', '1130759551480119296');
INSERT INTO `article` VALUES ('1130766068111572992', '111111111111111111', 'string', '1130750260757041152');
INSERT INTO `article` VALUES ('1130766113267449856', '222222222222222222222222', 'string', '1130759551480119296');

-- ----------------------------
-- Table structure for people
-- ----------------------------
DROP TABLE IF EXISTS `people`;
CREATE TABLE `people`  (
  `pid` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of people
-- ----------------------------
INSERT INTO `people` VALUES ('1130749536748867584', 'string', 'string');
INSERT INTO `people` VALUES ('1130750260757041152', 'string', 'string');
INSERT INTO `people` VALUES ('1130755380995612672', 'string', 'string');
INSERT INTO `people` VALUES ('1130759551480119296', '11111111111', '22222222222222');

SET FOREIGN_KEY_CHECKS = 1;
