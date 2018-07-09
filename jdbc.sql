/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.15.128_3306
 Source Server Type    : MySQL
 Source Server Version : 80011
 Source Host           : 192.168.15.128:3306
 Source Schema         : jdbc

 Target Server Type    : MySQL
 Target Server Version : 80011
 File Encoding         : 65001

 Date: 09/07/2018 14:57:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `r_id` bigint(20) NOT NULL,
  `r_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES (1, 'VIP1');
INSERT INTO `roles` VALUES (2, 'VIP2');
INSERT INTO `roles` VALUES (3, 'VIP3');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `ur_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ur_user_id` bigint(20) NOT NULL,
  `ur_role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`ur_id`) USING BTREE,
  INDEX `FKog5l1l7qu12d4addmee1x7o3g`(`ur_role_id`) USING BTREE,
  INDEX `FKbuig1h3lu7g2rv79lxb9dbnu6`(`ur_user_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES (1, 1, 2);
INSERT INTO `user_roles` VALUES (2, 2, 1);
INSERT INTO `user_roles` VALUES (3, 1, 1);
INSERT INTO `user_roles` VALUES (4, 2, 3);
INSERT INTO `user_roles` VALUES (5, 3, 2);
INSERT INTO `user_roles` VALUES (6, 3, 3);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `u_id` bigint(20) NOT NULL,
  `u_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `u_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`u_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'user1', '$2a$10$AbRFV0e/kCpqBwYiZrbeiOvHSrOwRF8Bs5soevklA.vaK7K3uROci');
INSERT INTO `users` VALUES (2, 'user2', '$2a$10$iIZZeX54UEO04lLuRZtmnes4pzPvLDw0iQPnj9XzFmmLI121hgrem');
INSERT INTO `users` VALUES (3, 'user3', '123');

SET FOREIGN_KEY_CHECKS = 1;
