/*
 Navicat Premium Data Transfer

 Source Server         : MySQL8.0.33
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : crowd_control_ubuntu

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001

 Date: 05/05/2023 22:30:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chat
-- ----------------------------
DROP TABLE IF EXISTS `chat`;
CREATE TABLE `chat`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `systemName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `fromRole` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `toRole` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `statue` int NULL DEFAULT NULL,
  `time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat
-- ----------------------------
INSERT INTO `chat` VALUES (1, '系统6841', '指挥中心', '交警', '12', '1', 0, NULL);
INSERT INTO `chat` VALUES (2, '系统6841', '指挥中心', '交警', '12', '1', 0, NULL);
INSERT INTO `chat` VALUES (3, '系统6841', '指挥中心', '交警', '156', '1', 0, NULL);
INSERT INTO `chat` VALUES (4, '系统6841', '指挥中心', '交警', '156', '1', 0, NULL);
INSERT INTO `chat` VALUES (5, '系统6841', '交警', '交警', '111', '1', 0, NULL);
INSERT INTO `chat` VALUES (6, '系统6841', '交警', '交警', '11111', '1', 0, NULL);
INSERT INTO `chat` VALUES (7, '系统6841', '指挥中心', '交警', '12', '1', 0, NULL);
INSERT INTO `chat` VALUES (8, '系统6841', '指挥中心', '交警', '12', '1', 0, NULL);
INSERT INTO `chat` VALUES (9, '系统6841', '指挥中心', '交警', '123', '1', 0, NULL);
INSERT INTO `chat` VALUES (10, '系统6841', '交警', '交警', '123', '1', 0, NULL);
INSERT INTO `chat` VALUES (11, '系统6841', '指挥中心', '指挥中心', '12', '1', 0, NULL);
INSERT INTO `chat` VALUES (12, '系统6841', '指挥中心', '交警', '1', '1', 0, NULL);
INSERT INTO `chat` VALUES (18, '系统6841', '指挥中心', '交警', '123', '1', 0, NULL);
INSERT INTO `chat` VALUES (19, '系统6841', '指挥中心', '交警', '命令一', '2', 1, NULL);
INSERT INTO `chat` VALUES (20, '系统6841', '指挥中心', '交警', '命令三', '2', 1, NULL);
INSERT INTO `chat` VALUES (21, '系统6841', '指挥中心', '交警', '命令一', '2', 1, NULL);
INSERT INTO `chat` VALUES (22, '系统6841', '交警', '指挥中心', '', '1', 0, NULL);
INSERT INTO `chat` VALUES (23, '系统6841', '交警', '指挥中心', 'dsa', '1', 0, NULL);
INSERT INTO `chat` VALUES (24, '系统6841', '交警', '指挥中心', 'dsa', '1', 0, NULL);
INSERT INTO `chat` VALUES (25, '系统6841', '指挥中心', '交警', 'dsaasd', '1', 0, NULL);
INSERT INTO `chat` VALUES (26, '系统6841', '交警', '指挥中心', 'dsadsa', '1', 0, NULL);
INSERT INTO `chat` VALUES (27, '系统6841', '指挥中心', '交警', '命令三', '2', 1, NULL);
INSERT INTO `chat` VALUES (28, '系统6841', '指挥中心', '交警', '命令二', '2', 1, NULL);
INSERT INTO `chat` VALUES (29, '系统6841', '指挥中心', '交警', '命令二', '2', 1, NULL);
INSERT INTO `chat` VALUES (30, '系统6841', '指挥中心', '交警', '命令二', '2', 1, NULL);
INSERT INTO `chat` VALUES (31, '系统6841', '指挥中心', '交警', '命令二', '2', 1, NULL);
INSERT INTO `chat` VALUES (32, '系统6841', '指挥中心', '交警', '命令二', '2', 1, NULL);
INSERT INTO `chat` VALUES (33, '系统6841', '交警', '指挥中心', '36', '1', 0, NULL);
INSERT INTO `chat` VALUES (34, '系统6841', '指挥中心', '交警', '命令三', '2', 1, NULL);
INSERT INTO `chat` VALUES (35, '系统6841', '指挥中心', '交警', '123456', '1', 0, NULL);
INSERT INTO `chat` VALUES (36, '系统6841', '交警', '指挥中心', '36', '1', 0, NULL);
INSERT INTO `chat` VALUES (37, '系统6841', '指挥中心', '交警', '命令一', '2', 1, NULL);
INSERT INTO `chat` VALUES (38, '系统6841', '指挥中心', '交警', '命令二', '2', 1, NULL);
INSERT INTO `chat` VALUES (39, '系统6841', '指挥中心', '交警', '三大', '1', 0, NULL);
INSERT INTO `chat` VALUES (40, '系统6841', '指挥中心', '交警', '命令二', '2', 1, NULL);
INSERT INTO `chat` VALUES (41, '系统6841', '指挥中心', '交警', '命令三', '2', 0, NULL);
INSERT INTO `chat` VALUES (42, '系统6841', '交警', '指挥中心', 'dsada', '2', 0, NULL);
INSERT INTO `chat` VALUES (43, '系统6841', '指挥中心', '交警', 'dsads', '2', 0, NULL);

-- ----------------------------
-- Table structure for deploy
-- ----------------------------
DROP TABLE IF EXISTS `deploy`;
CREATE TABLE `deploy`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sysName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `ga` int NULL DEFAULT NULL,
  `ga_lng` double NULL DEFAULT NULL COMMENT '经度',
  `ga_lat` double NULL DEFAULT NULL COMMENT '纬度',
  `jj` int NULL DEFAULT NULL,
  `jj_lng` double NULL DEFAULT NULL,
  `jj_lat` double NULL DEFAULT NULL,
  `cg` int NULL DEFAULT NULL,
  `cg_lng` double NULL DEFAULT NULL,
  `cg_lat` double NULL DEFAULT NULL,
  `zyz` int NULL DEFAULT NULL,
  `zyz_lat` double NULL DEFAULT NULL,
  `zyz_lng` double NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of deploy
-- ----------------------------
INSERT INTO `deploy` VALUES (27, '系统6841', 26, 120.164273, 30.248522, 121, 120.164273, 30.248522, 0, 120.164273, 30.248522, 10, 30.248522, 120.164273);
INSERT INTO `deploy` VALUES (28, '系统6841', 0, 120.166338, 30.249134, 0, 120.166338, 30.249134, 0, 120.166338, 30.249134, 10, 30.249134, 120.166338);
INSERT INTO `deploy` VALUES (29, '系统6841', 0, 120.166327, 30.250394, 0, 120.166327, 30.250394, 0, 120.166327, 30.250394, 10, 30.250394, 120.166327);
INSERT INTO `deploy` VALUES (30, '系统6841', 0, 120.162964, 30.250237, 0, 120.162964, 30.250237, 0, 120.162964, 30.250237, 10, 30.250237, 120.162964);
INSERT INTO `deploy` VALUES (31, '系统6841', 0, 120.164208, 30.250316, 0, 120.164208, 30.250316, 0, 120.164208, 30.250316, 10, 30.250316, 120.164208);
INSERT INTO `deploy` VALUES (32, '系统6841', 0, 120.158726, 30.258179, 0, 120.158726, 30.258179, 0, 120.158726, 30.258179, 10, 30.258179, 120.158726);
INSERT INTO `deploy` VALUES (33, '系统6841', 0, 120.161741, 30.258193, 0, 120.161741, 30.258193, 0, 120.161741, 30.258193, 10, 30.258193, 120.161741);
INSERT INTO `deploy` VALUES (34, '系统6841', 0, 120.163854, 30.258351, 0, 120.163854, 30.258351, 0, 120.163854, 30.258351, 10, 30.258351, 120.163854);
INSERT INTO `deploy` VALUES (35, '系统6841', 0, 120.163951, 30.257021, 0, 120.163951, 30.257021, 0, 120.163951, 30.257021, 10, 30.257021, 120.163951);
INSERT INTO `deploy` VALUES (36, '系统6841', 0, 120.162615, 30.256932, 0, 120.162615, 30.256932, 0, 120.162615, 30.256932, 10, 30.256932, 120.162615);
INSERT INTO `deploy` VALUES (37, '系统6841', 0, 120.162894, 30.255561, 0, 120.162894, 30.255561, 0, 120.162894, 30.255561, 10, 30.255561, 120.162894);
INSERT INTO `deploy` VALUES (38, '系统6841', 0, 120.162878, 30.253996, 0, 120.162878, 30.253996, 0, 120.162878, 30.253996, 10, 30.253996, 120.162878);
INSERT INTO `deploy` VALUES (39, '系统6841', 0, 120.162968, 30.25275, 0, 120.162968, 30.25275, 0, 120.162968, 30.25275, 10, 30.25275, 120.162968);
INSERT INTO `deploy` VALUES (40, '系统7065', 0, 120.164273, 30.248522, 18, 120.164273, 30.248522, 0, 120.164273, 30.248522, 10, 30.248522, 120.164273);
INSERT INTO `deploy` VALUES (41, '系统7065', 0, 120.166338, 30.249134, 0, 120.166338, 30.249134, 0, 120.166338, 30.249134, 10, 30.249134, 120.166338);
INSERT INTO `deploy` VALUES (42, '系统7065', 0, 120.166327, 30.250394, 0, 120.166327, 30.250394, 0, 120.166327, 30.250394, 10, 30.250394, 120.166327);
INSERT INTO `deploy` VALUES (43, '系统7065', 0, 120.162964, 30.250237, 0, 120.162964, 30.250237, 0, 120.162964, 30.250237, 10, 30.250237, 120.162964);
INSERT INTO `deploy` VALUES (44, '系统7065', 0, 120.164208, 30.250316, 0, 120.164208, 30.250316, 0, 120.164208, 30.250316, 10, 30.250316, 120.164208);
INSERT INTO `deploy` VALUES (45, '系统7065', 0, 120.158726, 30.258179, 0, 120.158726, 30.258179, 0, 120.158726, 30.258179, 10, 30.258179, 120.158726);
INSERT INTO `deploy` VALUES (46, '系统7065', 0, 120.161741, 30.258193, 0, 120.161741, 30.258193, 0, 120.161741, 30.258193, 10, 30.258193, 120.161741);
INSERT INTO `deploy` VALUES (47, '系统7065', 0, 120.163854, 30.258351, 0, 120.163854, 30.258351, 0, 120.163854, 30.258351, 10, 30.258351, 120.163854);
INSERT INTO `deploy` VALUES (48, '系统7065', 0, 120.163951, 30.257021, 0, 120.163951, 30.257021, 0, 120.163951, 30.257021, 10, 30.257021, 120.163951);
INSERT INTO `deploy` VALUES (49, '系统7065', 0, 120.162615, 30.256932, 0, 120.162615, 30.256932, 0, 120.162615, 30.256932, 10, 30.256932, 120.162615);
INSERT INTO `deploy` VALUES (50, '系统7065', 0, 120.162894, 30.255561, 0, 120.162894, 30.255561, 0, 120.162894, 30.255561, 10, 30.255561, 120.162894);
INSERT INTO `deploy` VALUES (51, '系统7065', 0, 120.162878, 30.253996, 0, 120.162878, 30.253996, 0, 120.162878, 30.253996, 10, 30.253996, 120.162878);
INSERT INTO `deploy` VALUES (52, '系统7065', 0, 120.162968, 30.25275, 0, 120.162968, 30.25275, 0, 120.162968, 30.25275, 10, 30.25275, 120.162968);

-- ----------------------------
-- Table structure for login_record
-- ----------------------------
DROP TABLE IF EXISTS `login_record`;
CREATE TABLE `login_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `systemName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `loginTime` datetime NULL DEFAULT NULL,
  `logoutTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_record
-- ----------------------------
INSERT INTO `login_record` VALUES (3, '1', '系统6841', '2023-05-04 02:42:15', '2023-05-04 02:42:19');

-- ----------------------------
-- Table structure for number_of_people
-- ----------------------------
DROP TABLE IF EXISTS `number_of_people`;
CREATE TABLE `number_of_people`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '区域',
  `number` int NULL DEFAULT NULL COMMENT '人数',
  `longitude` double NULL DEFAULT NULL COMMENT '经度',
  `latitude` double NULL DEFAULT NULL COMMENT '纬度',
  `area` int NULL DEFAULT NULL COMMENT '面积',
  `vicinity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '相邻区域',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态',
  `systemName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of number_of_people
-- ----------------------------
INSERT INTO `number_of_people` VALUES (19, '钱塘里', 1157, 120.160988, 30.257241, 45263, '长生里;劝业里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (20, '长生里', 2956, 120.163306, 30.256641, 42258, '钱塘里;劝业里;学士里;龙翔里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (21, '劝业里', 2463, 120.161679, 30.256041, 44568, '钱塘里;长生里;学士里;龙翔里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (22, '学士里', 2052, 120.162178, 30.254551, 32754, '长生里;劝业里;龙翔里;仁和里;东坡里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (23, '龙翔里', 1450, 120.163452, 30.254666, 18703, '长生里;劝业里;学士里;仁和里;东坡里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (24, '仁和里', 1304, 120.162555, 30.252937, 19095, '学士里;龙翔里;东坡里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (25, '东坡里', 3864, 120.163585, 30.252126, 43396, '学士里;龙翔里;仁和里;将军里;泗水里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (26, '将军里', 2343, 120.162707, 30.249315, 47562, '东坡里;泗水里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (27, '泗水里', 2335, 120.165169, 30.249562, 38837, '东坡里;将军里', 'green', '系统6841');
INSERT INTO `number_of_people` VALUES (28, '钱塘里', 2163, 120.160988, 30.257241, 45263, '长生里;劝业里', 'green', '系统7065');
INSERT INTO `number_of_people` VALUES (29, '长生里', 2321, 120.163306, 30.256641, 42258, '钱塘里;劝业里;学士里;龙翔里', 'green', '系统7065');
INSERT INTO `number_of_people` VALUES (30, '劝业里', 2886, 120.161679, 30.256041, 44568, '钱塘里;长生里;学士里;龙翔里', 'green', '系统7065');
INSERT INTO `number_of_people` VALUES (31, '学士里', 2160, 120.162178, 30.254551, 32754, '长生里;劝业里;龙翔里;仁和里;东坡里', 'green', '系统7065');
INSERT INTO `number_of_people` VALUES (32, '龙翔里', 1867, 120.163452, 30.254666, 18703, '长生里;劝业里;学士里;仁和里;东坡里', 'green', '系统7065');
INSERT INTO `number_of_people` VALUES (33, '仁和里', 2014, 120.162555, 30.252937, 19095, '学士里;龙翔里;东坡里', 'green', '系统7065');
INSERT INTO `number_of_people` VALUES (34, '东坡里', 3495, 120.163585, 30.252126, 43396, '学士里;龙翔里;仁和里;将军里;泗水里', 'green', '系统7065');
INSERT INTO `number_of_people` VALUES (35, '将军里', 2309, 120.162707, 30.249315, 47562, '东坡里;泗水里', 'green', '系统7065');
INSERT INTO `number_of_people` VALUES (36, '泗水里', 2070, 120.165169, 30.249562, 38837, '东坡里;将军里', 'green', '系统7065');

-- ----------------------------
-- Table structure for number_of_people_init
-- ----------------------------
DROP TABLE IF EXISTS `number_of_people_init`;
CREATE TABLE `number_of_people_init`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `region` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '区域',
  `number` int NULL DEFAULT NULL COMMENT '人数',
  `longitude` double NULL DEFAULT NULL COMMENT '经度',
  `latitude` double NULL DEFAULT NULL COMMENT '纬度',
  `area` int NULL DEFAULT NULL COMMENT '面积',
  `vicinity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '相邻区域',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '状态',
  `systemName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of number_of_people_init
-- ----------------------------
INSERT INTO `number_of_people_init` VALUES (1, '钱塘里', 2000, 120.160988, 30.257241, 45263, '长生里;劝业里', 'green', NULL);
INSERT INTO `number_of_people_init` VALUES (2, '长生里', 2150, 120.163306, 30.256641, 42258, '钱塘里;劝业里;学士里;龙翔里', 'green', NULL);
INSERT INTO `number_of_people_init` VALUES (3, '劝业里', 2460, 120.161679, 30.256041, 44568, '钱塘里;长生里;学士里;龙翔里', 'green', NULL);
INSERT INTO `number_of_people_init` VALUES (4, '学士里', 2310, 120.162178, 30.254551, 32754, '长生里;劝业里;龙翔里;仁和里;东坡里', 'green', NULL);
INSERT INTO `number_of_people_init` VALUES (5, '龙翔里', 1650, 120.163452, 30.254666, 18703, '长生里;劝业里;学士里;仁和里;东坡里', 'green', NULL);
INSERT INTO `number_of_people_init` VALUES (6, '仁和里', 1680, 120.162555, 30.252937, 19095, '学士里;龙翔里;东坡里', 'green', NULL);
INSERT INTO `number_of_people_init` VALUES (7, '东坡里', 3620, 120.163585, 30.252126, 43396, '学士里;龙翔里;仁和里;将军里;泗水里', 'green', NULL);
INSERT INTO `number_of_people_init` VALUES (8, '将军里', 2400, 120.162707, 30.249315, 47562, '东坡里;泗水里', 'green', NULL);
INSERT INTO `number_of_people_init` VALUES (9, '泗水里', 2550, 120.165169, 30.249562, 38837, '东坡里;将军里', 'green', NULL);

-- ----------------------------
-- Table structure for question_choice
-- ----------------------------
DROP TABLE IF EXISTS `question_choice`;
CREATE TABLE `question_choice`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `answer_A` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `answer_B` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `answer_C` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `answer_D` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `right_answer` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `analysis` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `score` smallint UNSIGNED NOT NULL,
  `classify` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of question_choice
-- ----------------------------
INSERT INTO `question_choice` VALUES (1, '当某处定位图标变黄色时，市民_____', '只进不出', '可出可进', '只出不进', '不可出亦不可进', 'B', '黄色图标是警告', 5, '1');
INSERT INTO `question_choice` VALUES (2, '某地图表变红，此时市民', '只进不出', '只出不进', '可出可进', '不可出亦不可进', 'C', '没有警力干涉是无法控制进出的', 5, '市民');
INSERT INTO `question_choice` VALUES (3, '此系统共有_____个角色。', '4', '5', '6', '7', 'D', '交警、指挥中心、市民、公交地铁、志愿者、城管、主办单位', 5, '指挥中心');
INSERT INTO `question_choice` VALUES (4, '下列哪种说法是错误的？', '在警力部署除志愿者时，若图标变黄则不能进入区域', '即使部署志愿者，无论什么状态，都可进可出', '部署交警，就不能进出此区域', '部署公安时，代表此地不是红色区域', 'D', '部署公安时，代表此地不是绿色标记区域，红色与黄色却是不能被判断的。', 5, '交警');
INSERT INTO `question_choice` VALUES (5, '主办单位可以发挥什么作用？', '吸引人流', '集中人流', '发送活动通知', '以上均是', 'D', '暂无', 5, '城管');
INSERT INTO `question_choice` VALUES (6, '管控策略：实时人流达多少实行一级响应？', '80000以上', '60000~80000', '40000~60000', '40000以下', 'A', '实时人流>80000人 步行街警力全员上岗，成立临时指挥部，由公安机关进行警力调度。', 5, '公安');
INSERT INTO `question_choice` VALUES (7, '应对人流管控，下列可采取的做法是？', '分割区域，有序管控', '停止活动举办或者放行', '全副武装的警力部署', '以上都可', 'D', '现场严密管控：\r\n美国：分割区域，有序管控——纽约时报广场每年举办跨年活动，百万人参与。纽约警方将报广场分成若干区域，用障碍物隔开，游客一旦进入一个区域，便不能随便出入。一个区域被占满后，才开放下一个区域，降低人员随意流动性。\r\n\r\n英国：专人负责统计人数——2013年新年烟火表演，25万人到泰晤士河边观看。组织方有专人负责统计人数。如果人数达到各区块所能承载人数的上限，将停止放行。\r\n\r\n法国：全副武装的警力部署——2012年跨年，全长2.2公里的香榭丽舍大街迎来30万人聚会，警车以及大量三人一组巡逻的防暴警察随处可见，头盔、护具、枪械一样不少。', 5, '主办单位');
INSERT INTO `question_choice` VALUES (8, '部署公安、交警、城管以后，以下哪一项操作是合理的？', '继续在此区域部署警力', '市民进入此区域', '市民转移到此区域', '在此区域举办活动', 'A', '部署警力之后，市民操作均不可执行，举办活动会吸引人流，加大人流密度', 2, '志愿者');
INSERT INTO `question_choice` VALUES (9, '人流根据以下哪种方法计算最有效？', '移动通信服务', '实时监控', '图像识别', '人流密度计算', 'A', '暂无', 5, '志愿者');
INSERT INTO `question_choice` VALUES (10, '哪个角色可以发指挥通知给其他任何一个角色？', '交警', '指挥中心', '主办方', '市民', 'B', '暂无', 5, '志愿者');
INSERT INTO `question_choice` VALUES (12, '此系统共有_____个角色。====A', '4', '5', '6', '7', 'D', '交警、指挥中心、市民、公交地铁、志愿者、城管、主办单位', 5, '指挥中心');
INSERT INTO `question_choice` VALUES (13, '某地图表变红，此时市民====A', '只进不出', '只出不进', '可出可进', '不可出亦不可进', 'C', '没有警力干涉是无法控制进出的', 5, '市民');
INSERT INTO `question_choice` VALUES (14, '下列哪种说法是错误的？====A', '在警力部署除志愿者时，若图标变黄则不能进入区域', '即使部署志愿者，无论什么状态，都可进可出', '部署交警，就不能进出此区域', '部署公安时，代表此地不是红色区域', 'D', '部署公安时，代表此地不是绿色标记区域，红色与黄色却是不能被判断的。', 5, '交警');
INSERT INTO `question_choice` VALUES (15, '主办单位可以发挥什么作用？====A', '吸引人流', '集中人流', '发送活动通知', '以上均是', 'D', '暂无', 5, '城管');
INSERT INTO `question_choice` VALUES (16, '管控策略：实时人流达多少实行一级响应？====A', '80000以上', '60000~80000', '40000~60000', '40000以下', 'A', '实时人流>80000人 步行街警力全员上岗，成立临时指挥部，由公安机关进行警力调度。', 5, '公安');
INSERT INTO `question_choice` VALUES (17, '应对人流管控，下列可采取的做法是？====A', '分割区域，有序管控', '停止活动举办或者放行', '全副武装的警力部署', '以上都可', 'D', '现场严密管控：\r\n美国：分割区域，有序管控——纽约时报广场每年举办跨年活动，百万人参与。纽约警方将报广场分成若干区域，用障碍物隔开，游客一旦进入一个区域，便不能随便出入。一个区域被占满后，才开放下一个区域，降低人员随意流动性。\r\n\r\n英国：专人负责统计人数——2013年新年烟火表演，25万人到泰晤士河边观看。组织方有专人负责统计人数。如果人数达到各区块所能承载人数的上限，将停止放行。\r\n\r\n法国：全副武装的警力部署——2012年跨年，全长2.2公里的香榭丽舍大街迎来30万人聚会，警车以及大量三人一组巡逻的防暴警察随处可见，头盔、护具、枪械一样不少。', 5, '主办单位');
INSERT INTO `question_choice` VALUES (18, '部署公安、交警、城管以后，以下哪一项操作是合理的？====A', '继续在此区域部署警力', '市民进入此区域', '市民转移到此区域', '在此区域举办活动', 'A', '部署警力之后，市民操作均不可执行，举办活动会吸引人流，加大人流密度', 2, '志愿者');
INSERT INTO `question_choice` VALUES (19, '人流根据以下哪种方法计算最有效？====A', '移动通信服务', '实时监控', '图像识别', '人流密度计算', 'A', '暂无', 5, '志愿者');
INSERT INTO `question_choice` VALUES (20, '哪个角色可以发指挥通知给其他任何一个角色？====A', '交警', '指挥中心', '主办方', '市民', 'B', '暂无', 5, '志愿者');

-- ----------------------------
-- Table structure for systems
-- ----------------------------
DROP TABLE IF EXISTS `systems`;
CREATE TABLE `systems`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `systemname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `speed` int NULL DEFAULT NULL,
  `runtime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '保存实验进行到哪一时刻',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of systems
-- ----------------------------
INSERT INTO `systems` VALUES (14, '系统6841', '', 1, 'Fri Dec 31 20:30:33 CST 2021');
INSERT INTO `systems` VALUES (15, '系统7065', '', 1, 'Fri Dec 31 17:00:00 CST 2021');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '角色',
  `salt` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '盐值',
  `status` int NULL DEFAULT 0 COMMENT '是否登录',
  `systemname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'a', 'a', '1', 'asdf', 0, NULL);
INSERT INTO `user` VALUES (2, 'b', 'b', '2', 'qwer', 0, NULL);
INSERT INTO `user` VALUES (3, 'c', 'c', '3', 'zxcv', 0, NULL);
INSERT INTO `user` VALUES (35, 'q', 'q', '指挥中心', 'T6T4vY6b', 0, '1');
INSERT INTO `user` VALUES (36, '1', '1', '指挥中心', 'd5KIEC4Y', 0, '');
INSERT INTO `user` VALUES (37, 'liushuliang', 'aae0723f835c878b044659231bac37d5', NULL, 'HhU2&K9d', 0, NULL);

-- ----------------------------
-- Table structure for user_score
-- ----------------------------
DROP TABLE IF EXISTS `user_score`;
CREATE TABLE `user_score`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `systemName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NULL DEFAULT NULL,
  `score` int NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_bin NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_score
-- ----------------------------
INSERT INTO `user_score` VALUES (1, '1', '1', 1, '2');
INSERT INTO `user_score` VALUES (2, '系统6841', '指挥中心', 93, '1');
INSERT INTO `user_score` VALUES (3, '系统6841', '公安', 0, NULL);
INSERT INTO `user_score` VALUES (4, '系统6841', '交警', 0, NULL);
INSERT INTO `user_score` VALUES (5, '系统6841', '城管', 0, NULL);
INSERT INTO `user_score` VALUES (6, '系统6841', '公交地铁', 0, NULL);
INSERT INTO `user_score` VALUES (7, '系统6841', '主办单位', 0, NULL);
INSERT INTO `user_score` VALUES (8, '系统6841', '志愿者', 0, NULL);
INSERT INTO `user_score` VALUES (9, '系统6841', '市民', 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
