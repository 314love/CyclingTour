/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : tourby

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 30/08/2023 22:53:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for challenge
-- ----------------------------
DROP TABLE IF EXISTS `challenge`;
CREATE TABLE `challenge`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `trip_id` int(0) NULL DEFAULT NULL,
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of challenge
-- ----------------------------
INSERT INTO `challenge` VALUES (1, 1, 1);
INSERT INTO `challenge` VALUES (2, 2, 1);
INSERT INTO `challenge` VALUES (3, 2, 2);
INSERT INTO `challenge` VALUES (4, 4, 2);
INSERT INTO `challenge` VALUES (5, 3, 3);
INSERT INTO `challenge` VALUES (6, 4, 1);
INSERT INTO `challenge` VALUES (7, 3, 1);
INSERT INTO `challenge` VALUES (8, 4, 3);

-- ----------------------------
-- Table structure for trip_routes
-- ----------------------------
DROP TABLE IF EXISTS `trip_routes`;
CREATE TABLE `trip_routes`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `route_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `route_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `route_details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `rider_count` int(0) NULL DEFAULT NULL,
  `route_rating` int(0) NULL DEFAULT NULL,
  `route_comments` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of trip_routes
-- ----------------------------
INSERT INTO `trip_routes` VALUES (1, '乌鲁木齐~敦煌', 'https://pic1.zhimg.com/80/c97476c0c7e091e0c8e24827a53f834c_720w.webp', '乌鲁木齐市→达坂城区→吐鲁番市→鄯善县→一碗泉村→哈密市→骆驼圈子村→星星峡镇→甘肃省柳园镇→西湖乡→敦煌市。全程约990公里。', 33, 96, '从荒凉、干燥、寂寞的戈壁，到达水果丰盛和充满民族风情的绿洲，一种成就感会油然而生。');
INSERT INTO `trip_routes` VALUES (2, '环青海湖', 'https://pic3.zhimg.com/80/0bb59f5ff22ff2ac202e7e29d05fa876_720w.webp', '青海湖环湖,顺时针为黑马河乡→鸟岛→刚察县→西海镇→湟源县。全程约360公里。', 44, 80, '环游青海湖,是众多骑行路线中最经典的一条,深受大家的喜爱。青海湖是高原圣湖,景色优美,西北边的鸟岛栖堪称“鸟的王国”。');
INSERT INTO `trip_routes` VALUES (3, '兰州~敦煌', 'https://pic2.zhimg.com/80/59aa7bc8a6d0f55c9c1743a52bcea655_720w.webp', '兰州市→天祝藏族自治县→武威市→张掖市→酒泉市→嘉峪关市→玉门市→瓜州县→敦煌市。全程约1100公里。', 55, 90, '踏着前人的足迹,行走于绿洲、戈壁,永远不可能预料下一刻出现在你眼前的是怎样的风景。');
INSERT INTO `trip_routes` VALUES (4, '北京~杭州', 'https://pic2.zhimg.com/80/f48eb23f6227f15767876b1d8adbbd7d_720w.webp', '锡市→上海市→嘉兴市→杭州市。全程约1780公里。', 22, 70, '京杭大运河承载了厚重的历史,留下了数不清的珍贵文化遗产。沿着运河一路向南,既可以领略不同城市的风情,又能够感受中华民族深邃的文化底蕴。');
INSERT INTO `trip_routes` VALUES (5, '北京~漠河', 'https://pic4.zhimg.com/80/43ec8ce67abd6a2b5d6f6b41525d598f_720w.webp', '北京市→密云县→滦平县→隆化县→围场满蒙自治县→赤峰市→通辽市→乌兰浩特市→莫力达瓦达斡尔族自治旗(莫旗)→嫩江县→黑河市→塔河→漠河。全程约2800公里。', 10, 60, '一路向北,风情万种。从大都市到一望无际的大草原,再到郁郁葱葱的大森林,最终抵达我国最北端的漠河村,欣赏绚烂多彩的北极光,体验神奇的极昼现象。');
INSERT INTO `trip_routes` VALUES (6, '喀什~拉萨', 'https://pic3.zhimg.com/80/6bc816ef42d4b545f01337516f9c53a6_720w.webp', '喀什市→叶城县→普萨村→依格孜亚→康西瓦→甜水海→假桑玛日村→吉普村→日土县→噶尔县→巴噶乡→仲巴县→萨噶县→拉孜县→日喀则市→拉萨市。全程2850多公里。', 30, 75, '这是最艰苦、最考验毅力、环境最恶劣、人烟最稀少的极限骑行路线，难度是川藏线的两倍，走别人走不到的路,看别人不曾看过的风景,大概说出了选择新藏线的骑友的心声。');
INSERT INTO `trip_routes` VALUES (8, '海南环岛东线、中线骑行', 'https://pic4.zhimg.com/80/c810eaf06361e9550f17b18845c58cd7_720w.webp', '海口市→灵山镇→三江镇→文昌市→东郊镇椰林风景区→博鳌镇→兴隆热带植物园→三亚市→五指山市→琼中黎族苗族自治县→海口市。全程约650公里。', 66, 83, '冬季洒满温暖阳光的海南岛是骑友们的天堂。海浪、沙滩、椰林,风景如画,再加上难度适中的骑行路线,让无数骑友们涌向海南。');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `deleted` int(0) NULL DEFAULT 0,
  `roles` int(0) NULL DEFAULT 0 COMMENT '0普通用户\r\n1管理员',
  `advice` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', 'super@aliyun.com', '18677778888', 1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, 1, '我是大王');
INSERT INTO `user` VALUES (2, 'zhangsan', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', 'zhangsan@gmail.com', '13966667776', 1, 'https://s1.chu0.com/src/img/gif/c6/c6fc11b3ea1b4de5b9e6bcadb5db34cc.gif?imageMogr2/auto-orient/thumbnail/!240x240r/gravity/Center/crop/240x240/quality/85/&e=1735488000&token=1srnZGLKZ0Aqlz6dk7yF4SkiYf4eP-YrEOdM1sob:lRTrA4a7HHv6pOM7Cg7Kr18wVhU=', 0, 0, NULL);
INSERT INTO `user` VALUES (3, 'lisi', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', 'lisi@gmail.com', '13966667778', 1, 'https://s1.chu0.com/src/img/gif/94/94afe9ea1038403aa3ee0d3da0269eef.gif?e=1735488000&token=1srnZGLKZ0Aqlz6dk7yF4SkiYf4eP-YrEOdM1sob:tYPudA9r8yQW2Ps66PkITHVIDqA=', 0, 0, NULL);
INSERT INTO `user` VALUES (4, 'wangwu', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', 'wangwu@gmail.com', '13966667772', 1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, 0, NULL);
INSERT INTO `user` VALUES (5, 'zhaoer', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', 'zhaoer@gmail.com', '13966667776', 1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, 0, NULL);
INSERT INTO `user` VALUES (6, 'songliu', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', 'songliu@gmail.com', '13966667771', 1, 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif', 0, 0, NULL);
INSERT INTO `user` VALUES (7, 'zzzz', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', NULL, NULL, NULL, NULL, 1, 0, NULL);
INSERT INTO `user` VALUES (8, 'ddd', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', '1986318336@qq.com', '19895956565', NULL, 'https://tse2-mm.cn.bing.net/th/id/OIP-C.TOUWlZu2wdReuDooK6wCgQHaHj?w=162&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7', 0, 0, NULL);
INSERT INTO `user` VALUES (9, '2222', '$2a$10$vn/X5YS1vCkGN0otDYex0Ozp2eDC4UPqypial59xnlBSxLr7DO286', '3170540355@qq.com', '19890905753', NULL, 'https://tse2-mm.cn.bing.net/th/id/OIP-C.TOUWlZu2wdReuDooK6wCgQHaHj?w=162&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7', 0, 0, NULL);
INSERT INTO `user` VALUES (11, '000', '$2a$10$b.DsAaYtFMjrU9Qzs3TRKOkzNEB7jMRIZWyF0ORnoNHm4tCoOjw0i', '3170540355@qq.com', '19890905753', NULL, 'https://tse2-mm.cn.bing.net/th/id/OIP-C.TOUWlZu2wdReuDooK6wCgQHaHj?w=162&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7', 0, 0, NULL);

SET FOREIGN_KEY_CHECKS = 1;
