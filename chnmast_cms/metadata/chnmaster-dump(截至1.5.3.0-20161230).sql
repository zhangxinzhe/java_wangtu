/*
Navicat MySQL Data Transfer

Source Server         : DEV
Source Server Version : 50626
Source Host           : 192.168.20.4:3306
Source Database       : chnmaster

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2017-01-05 10:59:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_account`
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
  `ID` int(11) NOT NULL,
  `FUNDS` float(12,2) NOT NULL DEFAULT '0.00',
  `MODIFY_TIME` datetime NOT NULL,
  `FUNDS_LOCKED` float(12,2) DEFAULT '0.00',
  `STATUS` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account
-- ----------------------------

-- ----------------------------
-- Table structure for `t_account_record`
-- ----------------------------
DROP TABLE IF EXISTS `t_account_record`;
CREATE TABLE `t_account_record` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` int(11) NOT NULL,
  `RELATION_ID` varchar(100) NOT NULL,
  `RECORD_DATE` datetime NOT NULL,
  `AUDIT_USER_ID` int(11) DEFAULT NULL,
  `AUDIT_REAL_NAME` varchar(75) DEFAULT NULL,
  `CHANGE_TYPE` tinyint(4) NOT NULL,
  `CHANGE_FUNDS` float(12,2) NOT NULL,
  `REMAIN_FUNDS` float(12,2) NOT NULL,
  `DETAIL_TYPE` tinyint(4) DEFAULT NULL,
  `RECORD_TYPE` tinyint(4) NOT NULL,
  `REMARK` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_account_record
-- ----------------------------

-- ----------------------------
-- Table structure for `t_agency`
-- ----------------------------
DROP TABLE IF EXISTS `t_agency`;
CREATE TABLE `t_agency` (
  `ID` int(11) NOT NULL,
  `AGENCY_NAME` varchar(75) DEFAULT NULL,
  `REGIONCODE` varchar(6) DEFAULT NULL,
  `CONTACT_MAN` varchar(100) DEFAULT NULL,
  `CONTACT_PHONE` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `INTRODUCTION` varchar(4000) DEFAULT NULL,
  `AGENCY_TYPE` tinyint(4) NOT NULL DEFAULT '1',
  `IS_CANCEL` tinyint(4) NOT NULL DEFAULT '0',
  `AGENCY_SOURCE` tinyint(4) DEFAULT '0',
  `RANKING` tinyint(4) DEFAULT '0',
  `ISRECOMMEND` tinyint(4) DEFAULT '0',
  `RECOMMENDTIME` datetime DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_AGENCY` (`AGENCY_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_agency
-- ----------------------------

-- ----------------------------
-- Table structure for `t_agency_join`
-- ----------------------------
DROP TABLE IF EXISTS `t_agency_join`;
CREATE TABLE `t_agency_join` (
  `ID` int(11) NOT NULL,
  `AGENCY_NAME` varchar(75) DEFAULT NULL,
  `REGIONCODE` varchar(6) DEFAULT NULL,
  `CONTACT_MAN` varchar(100) DEFAULT NULL,
  `CONTACT_PHONE` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `INTRODUCTION` varchar(4000) DEFAULT NULL,
  `AGENCY_TYPE` tinyint(4) NOT NULL DEFAULT '1',
  `AGENCY_STATUS` tinyint(4) DEFAULT '0',
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_agency_join
-- ----------------------------

-- ----------------------------
-- Table structure for `t_college`
-- ----------------------------
DROP TABLE IF EXISTS `t_college`;
CREATE TABLE `t_college` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(75) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `BANNER_FILE` varchar(500) DEFAULT NULL,
  `LOGO_FILE` varchar(500) DEFAULT NULL,
  `INTRODUCTION` varchar(4000) DEFAULT NULL,
  `FEATURE` varchar(4000) DEFAULT NULL,
  `ISRECOMMEND` tinyint(4) DEFAULT '0',
  `DISPLAY_ORDER` int(11) DEFAULT '0',
  `CREATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_college
-- ----------------------------

-- ----------------------------
-- Table structure for `t_compete`
-- ----------------------------
DROP TABLE IF EXISTS `t_compete`;
CREATE TABLE `t_compete` (
  `ID` int(11) NOT NULL,
  `COMPETE_NAME` varchar(75) DEFAULT NULL,
  `COMPETE_TYPE` tinyint(4) DEFAULT '0',
  `COMPETE_BATCH` char(6) DEFAULT NULL,
  `RULE_FILE` varchar(200) DEFAULT NULL,
  `FORM_NAME` varchar(75) DEFAULT NULL,
  `FORM_FILE` varchar(200) DEFAULT NULL,
  `COMPETE_FEE` float(12,2) DEFAULT '0.00',
  `ATTEND_BEGINTIME` datetime DEFAULT NULL,
  `ATTEND_ENDTIME` datetime DEFAULT NULL,
  `VOTE_BEGINTIME` datetime DEFAULT NULL,
  `VOTE_ENDTIME` datetime DEFAULT NULL,
  `BEGINTIME` datetime DEFAULT NULL,
  `ENDTIME` datetime DEFAULT NULL,
  `COURSE_ID` int(11) DEFAULT '0',
  `CREATETIME` datetime NOT NULL,
  `IS_DOING` int(11) DEFAULT '0',
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_compete
-- ----------------------------

-- ----------------------------
-- Table structure for `t_compete_attend`
-- ----------------------------
DROP TABLE IF EXISTS `t_compete_attend`;
CREATE TABLE `t_compete_attend` (
  `ID` int(11) NOT NULL,
  `COMPETE_ID` int(11) NOT NULL,
  `USER_ID` int(11) DEFAULT '0',
  `REALNAME` varchar(75) DEFAULT NULL,
  `SEX` tinyint(4) DEFAULT '0',
  `BIRTHDAY` date DEFAULT NULL,
  `PHOTO_FILE` varchar(500) DEFAULT NULL,
  `MOBILEPHONE` varchar(25) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `SCHOOL_NAME` varchar(75) DEFAULT NULL,
  `UNIT_NAME` varchar(75) DEFAULT NULL,
  `APPLY_TYPE` tinyint(4) DEFAULT '0',
  `GROUP_TYPE` tinyint(4) DEFAULT '0',
  `ATTEND_CODE` varchar(11) DEFAULT NULL,
  `CREATION_TIME` datetime DEFAULT NULL,
  `PAY_AMOUNT` float(12,2) DEFAULT '0.00',
  `ORDER_STATUS` tinyint(4) DEFAULT '0',
  `VIDEO_URL` varchar(200) DEFAULT NULL,
  `AUDIT_STATUS` tinyint(4) DEFAULT '0',
  `VOTE_NUM` int(11) DEFAULT '0',
  `VOTE_ADD_NUM` int(11) DEFAULT '0',
  `PLAY_NUM` int(11) DEFAULT '0',
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_COMPETE_ATTEND_COMPETEID` (`COMPETE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_compete_attend
-- ----------------------------

-- ----------------------------
-- Table structure for `t_compete_attend_hlj`
-- ----------------------------
DROP TABLE IF EXISTS `t_compete_attend_hlj`;
CREATE TABLE `t_compete_attend_hlj` (
  `ID` int(11) NOT NULL,
  `COMPETE_ID` int(11) NOT NULL,
  `COMPETE_TYPE` tinyint(4) DEFAULT '20',
  `USER_ID` int(11) DEFAULT '0',
  `TEAMNAME` varchar(75) DEFAULT NULL,
  `TEAM_TYPE` tinyint(4) DEFAULT '0',
  `BAND_TYPE` varchar(50) DEFAULT NULL,
  `UNIT_NAME` varchar(75) DEFAULT NULL,
  `TEAM_LEADER` varchar(25) DEFAULT NULL,
  `TEAM_NUM` int(11) DEFAULT '0',
  `MAJOR_NAME` varchar(75) DEFAULT NULL,
  `MAJOR_NUM` int(11) DEFAULT '0',
  `DRUM_NAME` varchar(75) DEFAULT NULL,
  `DRUM_NUM` int(11) DEFAULT '0',
  `MANAGE_NUM` int(11) DEFAULT '0',
  `TOTAL_NUM` int(11) DEFAULT '0',
  `PHONE` varchar(25) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PLACE` varchar(225) DEFAULT NULL,
  `PHOTO_FILE` varchar(500) DEFAULT NULL,
  `TEAM_INTRO` varchar(4000) DEFAULT NULL,
  `MEMBERS_INTRO` varchar(4000) DEFAULT NULL,
  `APPLY_TYPE` tinyint(4) DEFAULT '0',
  `ATTEND_CODE` varchar(11) DEFAULT NULL,
  `CREATION_TIME` datetime DEFAULT NULL,
  `VOTE_NUM` int(11) DEFAULT '0',
  `VOTE_ADD_NUM` int(11) DEFAULT '0',
  `VOTE_SCORE` float(12,2) DEFAULT '0.00',
  `SONG_INTRO` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_compete_attend_hlj
-- ----------------------------

-- ----------------------------
-- Table structure for `t_compete_attend_song`
-- ----------------------------
DROP TABLE IF EXISTS `t_compete_attend_song`;
CREATE TABLE `t_compete_attend_song` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPETE_ID` int(11) NOT NULL,
  `ATTEND_ID` int(11) NOT NULL,
  `STAGE` tinyint(4) DEFAULT '0',
  `SONG_NAME` varchar(200) DEFAULT NULL,
  `SONG_AUTHOR` varchar(75) DEFAULT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_COMPETE_ATTEND_SONG_COMPETEID` (`COMPETE_ID`),
  KEY `IDX_T_COMPETE_ATTEND_SONG_ATTENDID` (`ATTEND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_compete_attend_song
-- ----------------------------

-- ----------------------------
-- Table structure for `t_compete_committee`
-- ----------------------------
DROP TABLE IF EXISTS `t_compete_committee`;
CREATE TABLE `t_compete_committee` (
  `ID` int(11) NOT NULL,
  `COMPETE_ID` int(11) NOT NULL,
  `REALNAME` varchar(75) DEFAULT NULL,
  `SEX` tinyint(4) DEFAULT '0',
  `UNIT_NAME` varchar(75) DEFAULT NULL,
  `TITLE` tinyint(4) NOT NULL,
  `JOB_TITLE` varchar(200) DEFAULT NULL,
  `INTRODUCTION` varchar(4000) DEFAULT NULL,
  `PHOTO_FILE` varchar(500) DEFAULT NULL,
  `ORDER_NO` int(11) NOT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_COMPETE_COMMITTEE_COMPETEID` (`COMPETE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_compete_committee
-- ----------------------------

-- ----------------------------
-- Table structure for `t_compete_video`
-- ----------------------------
DROP TABLE IF EXISTS `t_compete_video`;
CREATE TABLE `t_compete_video` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(75) DEFAULT NULL,
  `COMPETEID` int(11) DEFAULT NULL,
  `COMPETE_TYPE` tinyint(4) DEFAULT '10',
  `PIC_FILE` varchar(225) DEFAULT NULL,
  `VIDEO_FILE` varchar(225) DEFAULT NULL,
  `FILE_NAME` varchar(500) DEFAULT NULL,
  `FILE_EXT` varchar(50) DEFAULT NULL,
  `FILE_SIZE` int(11) DEFAULT '0',
  `VIDEO_DURATION` float(12,2) DEFAULT NULL,
  `IS_SHOW` tinyint(4) NOT NULL DEFAULT '0',
  `ORDER_NO` int(11) DEFAULT '0',
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_compete_video
-- ----------------------------

-- ----------------------------
-- Table structure for `t_compete_vote`
-- ----------------------------
DROP TABLE IF EXISTS `t_compete_vote`;
CREATE TABLE `t_compete_vote` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPETE_ID` int(11) NOT NULL,
  `ATTEND_ID` int(11) NOT NULL,
  `USER_ID` int(11) DEFAULT '0',
  `IP` varchar(20) DEFAULT NULL,
  `VOTE_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_compete_vote
-- ----------------------------

-- ----------------------------
-- Table structure for `t_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(75) DEFAULT NULL,
  `CREATE_TYPE` int(11) DEFAULT '0',
  `CREATE_NUM` int(11) DEFAULT '0',
  `BATCH_CODE` char(4) NOT NULL,
  `COUPON_TYPE` int(11) DEFAULT '0',
  `DISCOUNT` float(12,2) DEFAULT '1.00',
  `AMOUNT` float(12,2) DEFAULT '0.00',
  `USE_TYPE` int(11) NOT NULL DEFAULT '0',
  `BEGIN_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `OWNER_NAME` varchar(75) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_coupon
-- ----------------------------

-- ----------------------------
-- Table structure for `t_coupon_code`
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon_code`;
CREATE TABLE `t_coupon_code` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUPON_ID` int(11) NOT NULL,
  `COUPON_CODE` varchar(20) NOT NULL,
  `IS_CANCEL` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_coupon_code
-- ----------------------------

-- ----------------------------
-- Table structure for `t_coupon_code_rule`
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon_code_rule`;
CREATE TABLE `t_coupon_code_rule` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BATCH_CODE` char(4) NOT NULL,
  `CODE1` int(11) NOT NULL,
  `CODE2` int(11) NOT NULL,
  `STATUS` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_coupon_code_rule
-- ----------------------------

-- ----------------------------
-- Table structure for `t_coupon_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_coupon_course`;
CREATE TABLE `t_coupon_course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COUPON_ID` int(11) NOT NULL,
  `COURSE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_COUPON_COURSE_COUPONID` (`COUPON_ID`),
  KEY `IDX_T_COUPON_COURSE_COURSEID` (`COURSE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_coupon_course
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_course`;
CREATE TABLE `t_course` (
  `ID` int(11) NOT NULL,
  `COURSE_NAME` varchar(150) DEFAULT NULL,
  `INTRODUCTION` varchar(4000) DEFAULT NULL,
  `TEA_ID` int(11) DEFAULT NULL,
  `TEA_REALNAME` varchar(75) DEFAULT NULL,
  `ASS_ID` int(11) DEFAULT NULL,
  `ASS_REALNAME` varchar(75) DEFAULT NULL,
  `STUDENT_ID` int(11) DEFAULT '0',
  `COLLEGE_ID` int(11) DEFAULT NULL,
  `PICTURE_FILE` varchar(225) DEFAULT NULL,
  `BEGINTIME` datetime DEFAULT NULL,
  `ENDTIME` datetime DEFAULT NULL,
  `COURSE_OLDCOST` float(12,2) DEFAULT '0.00',
  `COURSE_NOWCOST` float(12,2) DEFAULT '0.00',
  `COURSE_NOWCOST_VIP` float(12,2) DEFAULT '0.00',
  `IS_USE_COUPON` tinyint(4) DEFAULT '1',
  `IS_BUY_ALL` tinyint(4) DEFAULT '0',
  `PLACE` varchar(225) DEFAULT NULL,
  `CONTENT_TYPE` tinyint(4) DEFAULT '1',
  `COURSE_TYPE` tinyint(4) DEFAULT '0',
  `WXBID` int(11) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT '0',
  `ISRECOMMEND` tinyint(4) DEFAULT '0',
  `RECOMMENDTIME` datetime DEFAULT NULL,
  `RECOMMENDSEQ` tinyint(4) DEFAULT '0',
  `CREATETIME` datetime DEFAULT NULL,
  `MODIFYTIME` datetime DEFAULT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  `PROGRESS` tinyint(4) DEFAULT '0',
  `BOOKINGTIME` datetime DEFAULT NULL,
  `SALETIME` datetime DEFAULT NULL,
  `COURSE_SOURCE` tinyint(4) DEFAULT '0',
  `APPLY_DATE` datetime DEFAULT NULL,
  `APPLY_INFO` varchar(4000) DEFAULT NULL,
  `AUDIT_STATUS` tinyint(4) NOT NULL DEFAULT '2',
  `AUDIT_DATE` datetime DEFAULT NULL,
  `AUDIT_REALNAME` varchar(75) DEFAULT NULL,
  `AUDIT_MSG` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_COURSE` (`CONTENT_TYPE`,`COURSE_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course_comment`
-- ----------------------------
DROP TABLE IF EXISTS `t_course_comment`;
CREATE TABLE `t_course_comment` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMMENT_TYPE` tinyint(4) NOT NULL DEFAULT '1',
  `COURSEID` int(11) NOT NULL,
  `TEA_ID` int(11) DEFAULT NULL,
  `OBS_ID` int(11) DEFAULT NULL,
  `OBS_NAME` varchar(50) DEFAULT NULL,
  `IP` varchar(50) DEFAULT NULL,
  `SCORE` int(11) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `COMMENTTIME` datetime DEFAULT NULL,
  `AUDIT_STATUS` tinyint(4) NOT NULL DEFAULT '2',
  `AUDIT_DATE` datetime DEFAULT NULL,
  `AUDIT_REALNAME` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course_price`
-- ----------------------------
DROP TABLE IF EXISTS `t_course_price`;
CREATE TABLE `t_course_price` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSEID` int(11) DEFAULT NULL,
  `COURSE_TIMEID` int(11) DEFAULT '0',
  `OLDPRICE` float(12,2) DEFAULT '0.00',
  `NOWPRICE` float(12,2) DEFAULT '0.00',
  `NOWPRICE_VIP` float(12,2) DEFAULT '0.00',
  `SEQ` int(11) DEFAULT NULL,
  `REMARK` varchar(225) DEFAULT NULL,
  `NUM` int(11) DEFAULT '0',
  `PAIN_NUM` int(11) DEFAULT '0',
  `REMAIN_NUM` int(11) DEFAULT '0',
  `STUDY_TYPE` tinyint(4) DEFAULT '0',
  `PRICE_TYPE` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `IDX_T_COURSE_PRICE_COURSEID` (`COURSEID`),
  KEY `IDX_T_COURSE_PRICE_TIMEID` (`COURSE_TIMEID`),
  KEY `IDX_T_COURSE_PRICE_CID_TID` (`COURSEID`,`COURSE_TIMEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course_price
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course_time`
-- ----------------------------
DROP TABLE IF EXISTS `t_course_time`;
CREATE TABLE `t_course_time` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSEID` int(11) DEFAULT NULL,
  `BEGINTIME` datetime DEFAULT NULL,
  `ENDTIME` datetime DEFAULT NULL,
  `SEQ` int(11) DEFAULT NULL,
  `TITLE` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_COURSE_TIME` (`COURSEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course_time
-- ----------------------------

-- ----------------------------
-- Table structure for `t_course_video`
-- ----------------------------
DROP TABLE IF EXISTS `t_course_video`;
CREATE TABLE `t_course_video` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COURSEID` int(11) DEFAULT NULL,
  `COURSE_TIMEID` int(11) DEFAULT '0',
  `VIDEO_DURATION` float(12,2) DEFAULT NULL,
  `VIDEO_FILE` varchar(225) DEFAULT NULL,
  `FILE_NAME` varchar(500) DEFAULT NULL,
  `FILE_EXT` varchar(50) DEFAULT NULL,
  `FILE_SIZE` int(11) DEFAULT '0',
  `PREDICT_TIME` datetime DEFAULT NULL,
  `MODIFY_TIME` datetime DEFAULT NULL,
  `PLAY_NUM` int(11) DEFAULT '0',
  `UPLOAD_TYPE` int(11) DEFAULT '0',
  `CONVERT_STATUS` int(11) DEFAULT '0',
  `CONVERT_ID` varchar(32) DEFAULT NULL,
  `CONVERT_RUN_TIME` datetime DEFAULT NULL,
  `CONVERT_END_TIME` datetime DEFAULT NULL,
  `CONVERT_MSG` varchar(1000) DEFAULT NULL,
  `REMARK` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_COURSE_VIDEO_COURSEID` (`COURSEID`),
  KEY `IDX_T_COURSE_VIDEO_COURSE_TIMEID` (`COURSE_TIMEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_course_video
-- ----------------------------

-- ----------------------------
-- Table structure for `t_help_catalog`
-- ----------------------------
DROP TABLE IF EXISTS `t_help_catalog`;
CREATE TABLE `t_help_catalog` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATALOGNAME` varchar(60) NOT NULL,
  `PARENTID` int(11) DEFAULT NULL,
  `DISPLAYORDER` int(11) DEFAULT '0',
  `IS_SHOW` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_help_catalog
-- ----------------------------

-- ----------------------------
-- Table structure for `t_help_theme`
-- ----------------------------
DROP TABLE IF EXISTS `t_help_theme`;
CREATE TABLE `t_help_theme` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATALOG_ID` int(11) NOT NULL,
  `THEMENAME` varchar(60) NOT NULL,
  `DISPLAYORDER` int(11) DEFAULT '0',
  `LINKTYPE` tinyint(4) DEFAULT NULL,
  `LINKURL` varchar(200) DEFAULT NULL,
  `CONTENTFILE` varchar(200) DEFAULT NULL,
  `IS_SHOW` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `IDX_T_HELP_THEME` (`CATALOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_help_theme
-- ----------------------------

-- ----------------------------
-- Table structure for `t_index_adv`
-- ----------------------------
DROP TABLE IF EXISTS `t_index_adv`;
CREATE TABLE `t_index_adv` (
  `ID` int(11) NOT NULL,
  `TITLE` varchar(450) NOT NULL,
  `FIELD_TYPE` tinyint(4) NOT NULL DEFAULT '0',
  `COMPETE_ID` int(11) DEFAULT '0',
  `ADV_SOURCE` tinyint(4) NOT NULL DEFAULT '0',
  `ADV_TYPE_ID` int(11) DEFAULT NULL,
  `CONTENT_TYPE` tinyint(4) NOT NULL DEFAULT '0',
  `LINK_URL` varchar(200) DEFAULT NULL,
  `LOCAL_FILE` varchar(200) DEFAULT NULL,
  `IS_SHOW` tinyint(4) NOT NULL DEFAULT '0',
  `PICTURE_FILE` varchar(200) DEFAULT NULL,
  `DISPLAY_ORDER` int(11) DEFAULT '0',
  `CREATE_DATE` datetime DEFAULT NULL,
  `REMARK` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_index_adv
-- ----------------------------

-- ----------------------------
-- Table structure for `t_index_adv_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_index_adv_type`;
CREATE TABLE `t_index_adv_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(450) NOT NULL,
  `FIELD_TYPE` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_index_adv_type
-- ----------------------------

-- ----------------------------
-- Table structure for `t_index_footer`
-- ----------------------------
DROP TABLE IF EXISTS `t_index_footer`;
CREATE TABLE `t_index_footer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(450) NOT NULL,
  `CONTENT_TYPE` tinyint(4) NOT NULL,
  `LINK_URL` varchar(200) DEFAULT NULL,
  `LOCAL_FILE` varchar(200) DEFAULT NULL,
  `IS_SHOW` tinyint(4) NOT NULL DEFAULT '0',
  `DISPLAY_ORDER` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_index_footer
-- ----------------------------
INSERT INTO `t_index_footer` VALUES ('1', '关于我们', '1', 'http://www.chnmaster.com/aboutus.htm', '', '1', '1');
INSERT INTO `t_index_footer` VALUES ('2', '加入我们', '1', 'http://www.chnmaster.com/joinus.htm', '', '1', '2');
INSERT INTO `t_index_footer` VALUES ('3', '联系我们', '2', '', '', '1', '3');
INSERT INTO `t_index_footer` VALUES ('4', '帮助中心', '1', 'http://www.chnmaster.com/help.htm', '', '1', '4');
INSERT INTO `t_index_footer` VALUES ('5', '联盟通讯平台', '1', 'http://www.chnmaster.com/touch.htm', '', '1', '5');

-- ----------------------------
-- Table structure for `t_index_info`
-- ----------------------------
DROP TABLE IF EXISTS `t_index_info`;
CREATE TABLE `t_index_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `COMPETE_ID` int(11) DEFAULT '0',
  `TITLE` varchar(450) NOT NULL,
  `INFO_TYPE` tinyint(4) NOT NULL,
  `INFO_SHORT` varchar(450) DEFAULT NULL,
  `CONTENT_TYPE` tinyint(4) NOT NULL,
  `LINK_URL` varchar(200) DEFAULT NULL,
  `LOCAL_FILE` varchar(200) DEFAULT NULL,
  `IS_SHOW` tinyint(4) NOT NULL,
  `IS_LIGHT` tinyint(4) DEFAULT NULL,
  `IS_URGENT` tinyint(4) NOT NULL DEFAULT '0',
  `NEW_DAYS` int(11) DEFAULT NULL,
  `ORDER_NO` int(11) DEFAULT '0',
  `EDITOR` varchar(50) DEFAULT NULL,
  `COME_FROM` varchar(150) DEFAULT NULL,
  `COME_URL` varchar(150) DEFAULT NULL,
  `INFO_DATE` datetime DEFAULT NULL,
  `SHARE_PIC_FILE` varchar(200) DEFAULT NULL,
  `CREATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_index_info
-- ----------------------------

-- ----------------------------
-- Table structure for `t_index_student`
-- ----------------------------
DROP TABLE IF EXISTS `t_index_student`;
CREATE TABLE `t_index_student` (
  `ID` int(11) NOT NULL,
  `STU_NAME` varchar(75) DEFAULT NULL,
  `PHOTO_FILE` varchar(500) DEFAULT NULL,
  `UNIT_NAME` varchar(75) DEFAULT NULL,
  `INTRODUCTION` varchar(4000) DEFAULT NULL,
  `IS_SHOW` tinyint(4) NOT NULL,
  `ORDER_NO` int(11) DEFAULT '0',
  `CREATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_index_student
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `ID` int(11) NOT NULL,
  `TRADE_NO` varchar(50) NOT NULL,
  `USER_ID` int(11) NOT NULL,
  `TOTAL_AMOUNT` float(12,2) DEFAULT '0.00',
  `ANNUL_AMOUNT` float(12,2) DEFAULT '0.00',
  `PAY_AMOUNT` float(12,2) DEFAULT '0.00',
  `CREATION_TIME` datetime DEFAULT NULL,
  `PAYMENT_TIME` datetime DEFAULT NULL,
  `CLOSE_TIME` datetime DEFAULT NULL,
  `OPERATOR_ID` int(11) DEFAULT NULL,
  `OPERATOR_NAME` varchar(75) DEFAULT NULL,
  `ORDER_STATUS` tinyint(4) DEFAULT '0',
  `STATUS_TYPE` tinyint(4) DEFAULT '0',
  `PAY_TYPE` tinyint(4) DEFAULT '0',
  `ORDER_TYPE` tinyint(4) DEFAULT '0',
  `CHECK_STATUS` tinyint(4) DEFAULT '0',
  `CHECK_RESULT` varchar(1000) DEFAULT NULL,
  `CHECK_NUM` int(11) DEFAULT '0',
  `CREATE_DEVICE` tinyint(4) DEFAULT '0',
  `MODIFY_DEVICE` tinyint(4) DEFAULT '0',
  `PAY_DEVICE` tinyint(4) NOT NULL DEFAULT '0',
  `BUY_TYPE` tinyint(4) DEFAULT '0',
  `ORDER_KIND` tinyint(4) DEFAULT '0',
  `HIFI_ORDER` varchar(50) DEFAULT NULL,
  `HIFI_CHECK_STATUS` tinyint(4) DEFAULT '0',
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `T_ORDER_USERID` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_alipay`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_alipay`;
CREATE TABLE `t_order_alipay` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) DEFAULT NULL,
  `TRADE_NO` varchar(50) NOT NULL,
  `ALIPAY_ID` varchar(64) DEFAULT NULL,
  `NOTIFY_ID` varchar(100) DEFAULT NULL,
  `FORM_STATE` tinyint(4) NOT NULL DEFAULT '0',
  `FORM_AMOUNT` float(12,2) NOT NULL DEFAULT '0.00',
  `USERID` int(11) NOT NULL,
  `CREATION_TIME` datetime NOT NULL,
  `MODIFY_TIME` datetime NOT NULL,
  `FORM_TYPE` tinyint(4) DEFAULT '0',
  `ISDEAL` tinyint(4) DEFAULT '0',
  `OPERATOR_ID` int(11) DEFAULT NULL,
  `OPERATOR_NAME` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `T_ORDER_ALIPAY_TRADENO` (`TRADE_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_alipay
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_alipay_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_alipay_log`;
CREATE TABLE `t_order_alipay_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TRADE_NO` varchar(32) NOT NULL,
  `LOG_CONTENT` varchar(1500) DEFAULT NULL,
  `CREATION_TIME` datetime DEFAULT NULL,
  `LOG_TYPE` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`ID`),
  KEY `T_ORDER_ALIPAY_LOG_TRADENO` (`TRADE_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_alipay_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_annul`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_annul`;
CREATE TABLE `t_order_annul` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) DEFAULT NULL,
  `ANNUL_TYPE` tinyint(4) DEFAULT '0',
  `COUPON_CODE` varchar(20) DEFAULT NULL,
  `ANNUL_AMOUNT` float(12,2) DEFAULT '0.00',
  `OPE_USER_ID` int(11) NOT NULL,
  `OPE_REAL_NAME` varchar(75) DEFAULT NULL,
  `OPE_TIME` datetime NOT NULL,
  `REMARK` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_ORDER_ANNUL_ORDERID` (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_annul
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_cashpay`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_cashpay`;
CREATE TABLE `t_order_cashpay` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) DEFAULT NULL,
  `OPE_USERID` int(11) DEFAULT NULL,
  `OPE_REALNAME` varchar(75) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `CASH_REALNAME` varchar(75) DEFAULT NULL,
  `CASH_TIME` datetime DEFAULT NULL,
  `CASH_AMOUNT` float(12,2) DEFAULT '0.00',
  `CHARGE_TYPE` varchar(100) DEFAULT NULL,
  `REMARK` varchar(750) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `T_ORDER_CASHPAY_ORDERID` (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_cashpay
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_detail`;
CREATE TABLE `t_order_detail` (
  `ID` int(11) NOT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  `WARE_ID` int(11) NOT NULL,
  `WARE_TIMEID` int(11) DEFAULT '0',
  `WARE_PRICEID` int(11) DEFAULT '0',
  `WARE_TYPE` tinyint(4) DEFAULT '0',
  `WARE_NAME` varchar(150) DEFAULT NULL,
  `WARE_NUM` int(11) DEFAULT '1',
  `PAYABLE_PER` float(12,2) DEFAULT '0.00',
  `ACTUAL_PER` float(12,2) DEFAULT '0.00',
  `PAYABLE_AMOUNT` float(12,2) DEFAULT '0.00',
  `ACTUAL_AMOUNT` float(12,2) DEFAULT '0.00',
  `HAS_GOOD` tinyint(4) DEFAULT '0',
  `STATUS` tinyint(4) DEFAULT '0',
  `REMARK` varchar(750) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `T_ORDER_DETAIL_ORDERID` (`ORDER_ID`),
  KEY `T_ORDER_DETAIL_ORDERID_TID` (`ORDER_ID`,`WARE_TIMEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_funds`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_funds`;
CREATE TABLE `t_order_funds` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` int(11) NOT NULL,
  `RELATION_ID` varchar(100) NOT NULL,
  `WARE_TYPE` tinyint(4) DEFAULT '0',
  `RECORD_DATE` datetime NOT NULL,
  `OPE_USER_ID` int(11) NOT NULL,
  `OPE_REAL_NAME` varchar(75) DEFAULT NULL,
  `CHANGE_TYPE` tinyint(4) NOT NULL,
  `CHANGE_FUNDS` float(12,2) NOT NULL,
  `REMAIN_FUNDS` float(12,2) DEFAULT NULL,
  `DETAIL_TYPE` tinyint(4) DEFAULT NULL,
  `REMARK` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `T_ORDER_FUNDS_USERID` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_funds
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_report`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_report`;
CREATE TABLE `t_order_report` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) DEFAULT NULL,
  `DETAIL_ID` int(11) DEFAULT NULL,
  `USER_ID` int(11) DEFAULT NULL,
  `REALNAME` varchar(75) DEFAULT NULL,
  `COURSEID` int(11) DEFAULT NULL,
  `COURSE_TIMEID` int(11) DEFAULT '0',
  `COURSE_PRICEID` int(11) DEFAULT '0',
  `STUDY_TYPE` tinyint(4) DEFAULT '1',
  `COURSE_CONTENT_TYPE` tinyint(4) DEFAULT '1',
  `REPORTTIME` datetime DEFAULT NULL,
  `REPORTTYPE` tinyint(4) DEFAULT '0',
  `PAYABLE_AMOUNT` float(12,2) DEFAULT '0.00',
  `ACTUAL_AMOUNT` float(12,2) DEFAULT '0.00',
  `IS_RETURN_COURSE` tinyint(4) DEFAULT '0',
  `IS_RETURN_MONEY` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `T_ORDER_REPORT_USERID` (`USER_ID`),
  KEY `T_ORDER_REPORT_TID` (`COURSE_TIMEID`),
  KEY `T_ORDER_REPORT_PID` (`COURSE_PRICEID`),
  KEY `T_ORDER_REPORT_UID_CID` (`USER_ID`,`COURSEID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_report
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_return`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_return`;
CREATE TABLE `t_order_return` (
  `ID` int(11) NOT NULL,
  `ORDER_ID` int(11) DEFAULT NULL,
  `DETAIL_ID` int(11) DEFAULT NULL,
  `USERID` int(11) DEFAULT NULL,
  `REALNAME` varchar(75) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `REASON` tinyint(4) DEFAULT '0',
  `REASON_DES` varchar(385) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT '10',
  `STATUS_TYPE` tinyint(4) DEFAULT '0',
  `COURSE_USERID` int(11) DEFAULT NULL,
  `COURSE_REALNAME` varchar(75) DEFAULT NULL,
  `COURSE_TIME` datetime DEFAULT NULL,
  `COURSE_REMARK` varchar(750) DEFAULT NULL,
  `MONEY_AMOUNT` float(12,2) DEFAULT '0.00',
  `MONEY_USERID` int(11) DEFAULT NULL,
  `MONEY_REALNAME` varchar(75) DEFAULT NULL,
  `MONEY_TIME` datetime DEFAULT NULL,
  `MONEY_REMARK` varchar(750) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `T_ORDER_RETURN_USERID` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_return
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_verifycode`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_verifycode`;
CREATE TABLE `t_order_verifycode` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) NOT NULL,
  `ORDER_REPORT_ID` int(11) NOT NULL,
  `COURSE_ID` int(11) NOT NULL,
  `COURSE_TIMEID` int(11) NOT NULL,
  `COURSE_CONTENT_TYPE` tinyint(4) DEFAULT '1',
  `VERIFY_CODE` int(11) NOT NULL,
  `IS_USED` tinyint(4) DEFAULT '0',
  `USED_DATE` datetime DEFAULT NULL,
  `REMARK` varchar(1500) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDX_T_ORDER_VERIFYCODE_UN` (`COURSE_ID`,`COURSE_TIMEID`,`VERIFY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_verifycode
-- ----------------------------

-- ----------------------------
-- Table structure for `t_order_wechatpay`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_wechatpay`;
CREATE TABLE `t_order_wechatpay` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ORDER_ID` int(11) DEFAULT NULL,
  `TRADE_NO` varchar(50) NOT NULL,
  `TRANSACTION_ID` varchar(32) DEFAULT NULL,
  `PREPAY_ID` varchar(64) DEFAULT NULL,
  `OPEN_ID` varchar(128) DEFAULT NULL,
  `TRADE_TYPE` varchar(100) DEFAULT NULL,
  `CODE_URL` varchar(255) DEFAULT NULL,
  `FORM_STATE` tinyint(4) NOT NULL DEFAULT '0',
  `FORM_AMOUNT` float(12,2) NOT NULL DEFAULT '0.00',
  `USERID` int(11) NOT NULL,
  `CREATION_TIME` datetime NOT NULL,
  `MODIFY_TIME` datetime NOT NULL,
  `FORM_TYPE` tinyint(4) DEFAULT '0',
  `ISDEAL` tinyint(4) DEFAULT '0',
  `OPERATOR_ID` int(11) DEFAULT NULL,
  `OPERATOR_NAME` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_ORDER_WECHATPAY_TRADENO` (`TRADE_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_wechatpay
-- ----------------------------

-- ----------------------------
-- Table structure for `t_picture`
-- ----------------------------
DROP TABLE IF EXISTS `t_picture`;
CREATE TABLE `t_picture` (
  `ID` int(11) NOT NULL,
  `OBJECT_ID` int(11) NOT NULL,
  `OBJECT_TYPE` tinyint(4) DEFAULT NULL,
  `PICTURE_NAME` varchar(100) DEFAULT NULL,
  `PICTURE_FILE` varchar(100) DEFAULT NULL,
  `VIDEO_FILE` varchar(100) DEFAULT NULL,
  `FILE_TYPE` tinyint(4) DEFAULT '1',
  `FILE_SIZE` int(11) DEFAULT NULL,
  `FILE_EXT` varchar(50) DEFAULT NULL,
  `IS_SHOW` tinyint(4) DEFAULT '1',
  `SHOW_ORDER` int(11) DEFAULT '0',
  `CREATETIME` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_PICTURE` (`OBJECT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_picture
-- ----------------------------

-- ----------------------------
-- Table structure for `t_school`
-- ----------------------------
DROP TABLE IF EXISTS `t_school`;
CREATE TABLE `t_school` (
  `ID` int(11) NOT NULL,
  `SCHOOL_NAME` varchar(75) DEFAULT NULL,
  `SHORTSPELL` varchar(50) DEFAULT NULL,
  `REGIONCODE` varchar(6) DEFAULT NULL,
  `CONTACT_MAN` varchar(100) DEFAULT NULL,
  `CONTACT_PHONE` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `INTRODUCTION` varchar(4000) DEFAULT NULL,
  `IS_CANCEL` tinyint(4) NOT NULL DEFAULT '0',
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_school
-- ----------------------------
INSERT INTO `t_school` VALUES ('1', '其他学校', null, null, null, null, null, null, null, '0', null, '其他学校');

-- ----------------------------
-- Table structure for `t_seq_agency`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_agency`;
CREATE TABLE `t_seq_agency` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_agency
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_college`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_college`;
CREATE TABLE `t_seq_college` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_college
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_common`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_common`;
CREATE TABLE `t_seq_common` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_common
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_compete`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_compete`;
CREATE TABLE `t_seq_compete` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_compete
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_compete_attend`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_compete_attend`;
CREATE TABLE `t_seq_compete_attend` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_compete_attend
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_coupon`;
CREATE TABLE `t_seq_coupon` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_coupon
-- ----------------------------
INSERT INTO `t_seq_coupon` VALUES ('1', 'T_COUPON');

-- ----------------------------
-- Table structure for `t_seq_course`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_course`;
CREATE TABLE `t_seq_course` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_course
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_order`;
CREATE TABLE `t_seq_order` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_order
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_order_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_order_detail`;
CREATE TABLE `t_seq_order_detail` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_order_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_user`;
CREATE TABLE `t_seq_user` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_user_union_member`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_user_union_member`;
CREATE TABLE `t_seq_user_union_member` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_user_union_member
-- ----------------------------

-- ----------------------------
-- Table structure for `t_seq_wxb`
-- ----------------------------
DROP TABLE IF EXISTS `t_seq_wxb`;
CREATE TABLE `t_seq_wxb` (
  `max_seq` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `table_name` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`max_seq`),
  UNIQUE KEY `table_name` (`table_name`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_seq_wxb
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sms_send`
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_send`;
CREATE TABLE `t_sms_send` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `send_userid` int(11) DEFAULT NULL,
  `send_realname` varchar(75) DEFAULT NULL,
  `send_date` datetime DEFAULT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `sms_type` int(11) DEFAULT '0',
  `send_status` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sms_send
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sms_send_batch`
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_send_batch`;
CREATE TABLE `t_sms_send_batch` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BATCH_ID` varchar(64) NOT NULL,
  `ACCOUNT_NAME` varchar(64) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  `CHECK_TIMES` int(11) DEFAULT '0',
  `CHECK_DATE` datetime DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sms_send_batch
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sms_send_detail`
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_send_detail`;
CREATE TABLE `t_sms_send_detail` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `send_id` int(11) NOT NULL,
  `receive_userid` int(11) DEFAULT NULL,
  `receive_username` varchar(50) DEFAULT NULL,
  `receive_realname` varchar(75) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `receive_status` int(11) DEFAULT '0',
  `status_desc` varchar(500) DEFAULT NULL,
  `sequence` varchar(100) DEFAULT NULL,
  `BATCH_ID` varchar(64) DEFAULT NULL,
  `gateway_type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sms_send_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_sms_send_verify_code`
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_send_verify_code`;
CREATE TABLE `t_sms_send_verify_code` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `MAIN_PHONE` varchar(13) NOT NULL,
  `VERIFY_CODE` varchar(10) NOT NULL,
  `SEND_TIME` datetime NOT NULL,
  `VERIFY_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sms_send_verify_code
-- ----------------------------

-- ----------------------------
-- Table structure for `t_system_app`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_app`;
CREATE TABLE `t_system_app` (
  `ID` int(11) NOT NULL,
  `APP_NAME` varchar(500) DEFAULT NULL,
  `APP_DES` varchar(500) DEFAULT NULL,
  `INDEX_URL` varchar(500) DEFAULT NULL,
  `IS_USING` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_app
-- ----------------------------
INSERT INTO `t_system_app` VALUES ('1', '中国音乐大师网', null, '/index.htm', '1');

-- ----------------------------
-- Table structure for `t_system_config`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_config`;
CREATE TABLE `t_system_config` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(50) NOT NULL,
  `NAME` varchar(150) NOT NULL,
  `VALUE` varchar(2000) NOT NULL,
  `DESCRIPTION` varchar(450) DEFAULT NULL,
  `CAN_VIEW` tinyint(4) NOT NULL DEFAULT '1',
  `CAN_EDIT` tinyint(4) NOT NULL DEFAULT '1',
  `VALIDATE` varchar(50) DEFAULT NULL,
  `ORDER_NO` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_config
-- ----------------------------
INSERT INTO `t_system_config` VALUES ('1', 'site.home_site_name', '前台名称', '中国音乐大师网', '显示在前台页面上的标题名称', '1', '1', 'config-max-length-50', '10');
INSERT INTO `t_system_config` VALUES ('2', 'site.cms_site_name', '后台名称', '中国音乐大师网-后台管理', '后台管理系统名称，显示在页面上的标题名称', '1', '1', 'config-max-length-50', '20');
INSERT INTO `t_system_config` VALUES ('3', 'domain.home', '前台域名地址', 'http://www.chnmaster.com', '前台主应用访问地址，必须是域名，必须以http://开头', '1', '1', 'validate-url', '30');
INSERT INTO `t_system_config` VALUES ('4', 'domain.cms', '后台域名地址', 'http://cms.chnmaster.com', '后台管理系统访问地址，必须是域名，必须以http://开头', '1', '1', 'validate-url', '40');
INSERT INTO `t_system_config` VALUES ('5', 'domain.file', '系统静态文件域名地址', 'http://file.chnmaster.com', '静态文件（如js,css,image等）的访问地址，必须是域名，必须以http://开头', '1', '1', 'validate-url', '50');
INSERT INTO `t_system_config` VALUES ('6', 'domain.upload_file', '用户上传文件域名地址', 'http://uploadfile.chnmaster.com', '用户上传文件（如upload目录）和发布时自带的系统文件（如sysfile目录）访问地址，必须是域名，必须以http://开头', '1', '1', 'validate-url', '60');
INSERT INTO `t_system_config` VALUES ('7', 'dispatch.switch', '调度中心开关', '1', '课程编号是否统一由调度中心来分配，0否，1是', '1', '1', 'config-int-range-0-1', '70');
INSERT INTO `t_system_config` VALUES ('8', 'dispatch.domain', '调度中心地址', 'http://dispatch.kehou.com', '调度中心应用的访问地址，从调度中心获取课程编号时使用', '1', '1', 'validate-url', '80');
INSERT INTO `t_system_config` VALUES ('9', 'wxb.course_before_time', '课堂提前时间', '15', '可提前多长时间进入无限宝课堂（单位：分钟）', '1', '1', 'config-int-range-0-600', '90');
INSERT INTO `t_system_config` VALUES ('10', 'wxb.course_delay_time', '课堂延迟时间', '15', '可延迟多长时间结束无限宝课堂（单位：分钟）', '1', '1', 'config-int-range-0-600', '100');
INSERT INTO `t_system_config` VALUES ('11', 'wxb.update', '无限定是否自动升级', '1', '无限定客户端和辅助登录工具是否自动升级，0不升级，1升级', '0', '0', 'config-int-range-0-1', '110');
INSERT INTO `t_system_config` VALUES ('12', 'wxb.call_record', '是否记录无限宝回调日志', '1', '无限宝向web端发送数据时，web端本地是否记录log4j日志。0不记录，1记录', '1', '1', 'config-int-range-0-1', '120');
INSERT INTO `t_system_config` VALUES ('13', 'system.stat_script', '访问量统计脚本', '<script src=\"http://s95.cnzz.com/stat.php?id=1256819091&web_id=1256819091\" language=\"JavaScript\"></script>', 'cnzz或其他第三方网站访问量的统计脚本', '1', '0', '', '300');
INSERT INTO `t_system_config` VALUES ('23', 'alipay.subject', '商品名称', '支付宝—音乐大师网', '支付宝支付时显示的商品名称', '1', '1', 'config-max-length-50', '130');
INSERT INTO `t_system_config` VALUES ('24', 'alipay.partner', '支付宝合作伙伴id', '2088121921902551', '支付宝合作伙伴id (账户内提取)', '0', '0', 'required', '132');
INSERT INTO `t_system_config` VALUES ('25', 'alipay.key', '支付宝密钥', 'hx6e2tkxvtmyo5vdl8p675h9wt9m382t', '支付宝密钥', '0', '0', 'required', '134');
INSERT INTO `t_system_config` VALUES ('26', 'alipay.seller_email', '卖家支付宝帐号', 'pay@chnmaster.com', '音乐大师网卖家支付宝帐号', '0', '0', 'required', '136');
INSERT INTO `t_system_config` VALUES ('29', 'domain.mobile', '手机端地址', 'http://m.chnmaster.com', '手机版访问地址，必须是域名，必须以http://开头', '1', '1', 'validate-url', '30');
INSERT INTO `t_system_config` VALUES ('38', 'wechatpay.app.app_id', '微信公众账号id（app）', 'wx67f3425b01d4122a', '微信公众账号id（app）', '1', '0', 'config-max-length-50', '150');
INSERT INTO `t_system_config` VALUES ('39', 'wechatpay.app.app_secret', '微信公众账号应用密钥（app）', '7eb20d1e8f354e3aa8da7cb497e77113', '微信公众账号应用密钥（app）', '1', '0', 'config-max-length-50', '150');
INSERT INTO `t_system_config` VALUES ('40', 'wechatpay.app.mch_id', '微信商户号id（app）', '1353618502', '微信商户号id（app）', '1', '0', 'config-max-length-50', '150');
INSERT INTO `t_system_config` VALUES ('41', 'wechatpay.app.verify_key', '微信安全验证key（app）', '75ec8c2f935742c2b7c9e871013b71fa', '微信安全验证key（app）', '1', '0', 'config-max-length-50', '150');
INSERT INTO `t_system_config` VALUES ('42', 'alipay.rsa.private.key', '公司支付宝RSA私钥', 'MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM4JA24PKDsKU2IpTPO+fOokOF99ZldY/hjm3bZmWZFgPp0JLKWYFem64Ukq8Htan1i8ZOZKiyiN04XSO4k5YmvJ312Z5ZARfS8hKOPl2WGuuM7CFUPyPBW+sA+u17IFtKnMR1UC2jOhUA9bSvBF/qPffziDFj9c8sXLm3siPFQdAgMBAAECgYASEHQ+iBGs64DYEGrwy/jDxp0yshnw+pp8sHEr9tlPh7K1rgI+GpGBRqNN+PfQuOdTrmDiWBLPYW/0QbR6mY9pX5WcBZkCs/gtOsLCE2p5iN3cF09fx1KdupMdk91NTeNnuKOj5M/6KWFJhxHKzuXacsmco68zQT+tECWfFIAUgQJBAPSSkawsSjSJ9dT89FlklzioZXU+t48NkxcC+p3klcwE/QGEVQlI8pHbfedK/nmvZ82fwyQbGCQQ6hZayfsd1TECQQDXqXvbra0A6NWL+MB+Q4/yv+gLjC+Y+9oDrzbLJBDx7LrHlRY7J57qxLkAA8gQ5Ci0k6e/ONyIJQelGBgnPuKtAkAPuOBedhZrHDdOcthuarYB8WlcSSTZsGC1SP69abVrgSKWl2A0EZluYPLODJchUXJV2KBd0NQTdXbm6v0zBG7xAkEAy6DDyhCaoZk2yQr+9jlk2ZhJyV18M77Zg1EVM4nCVitn0Lr8Wq52ZsWIyHLJnOlRZe9lHDDcSdBs/31YkPXXoQJADQxK7uY1hh3xXzx8hoBOyjYAxiIrUJ725I0MzN5P6jNZEI21EOCih/wdD/Sk6GTKIh+954y5gHrL2THZxrcqHg==', '公司支付宝账户私钥', '0', '0', 'required', '138');
INSERT INTO `t_system_config` VALUES ('43', 'alipay.rsa.public.key', '支付宝RSA公钥', 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOCQNuDyg7ClNiKUzzvnzqJDhffWZXWP4Y5t22ZlmRYD6dCSylmBXpuuFJKvB7Wp9YvGTmSosojdOF0juJOWJryd9dmeWQEX0vISjj5dlhrrjOwhVD8jwVvrAPrteyBbSpzEdVAtozoVAPW0rwRf6j3384gxY/XPLFy5t7IjxUHQIDAQAB', '支付宝RSA公钥', '0', '0', 'required', '140');
INSERT INTO `t_system_config` VALUES ('44', 'system_web_monitor', 'web管理', '0', '后台首页是否显示web管理功能，0否，1是', '1', '1', 'config-int-range-0-1', '131');
INSERT INTO `t_system_config` VALUES ('45', 'footer.index_pc_value', 'PC版页脚显示', 'CopyRight 北京国臻教育科技有限公司 版权所有<span class=\"mx-10\">|</span><a href=\"http://www.miitbeian.gov.cn/\" target=\"_blank\">京ICP备16013122</a>', 'PC版页脚显示内容', '1', '1', 'config-max-length-1000', '200');
INSERT INTO `t_system_config` VALUES ('46', 'footer.compete_pc_value', 'PC版活动专题页脚显示', 'CopyRight 北京国臻教育科技有限公司 版权所有 | 京ICP备16013122', 'PC版活动专题页脚显示内容', '1', '1', 'config-max-length-1000', '201');
INSERT INTO `t_system_config` VALUES ('48', 'hifi.member_monthly_price', 'Hifi会员包月价', '5', 'Hifi会员包月价，单位元，大于0', '1', '1', '', '501');
INSERT INTO `t_system_config` VALUES ('49', 'vpweb.allowHLS', '支持网页直播开关', '0', '设置是否支持网页直播功能 1表示允许，0或者不设置表示不允许', '1', '1', 'config-int-range-0-1', '220');
INSERT INTO `t_system_config` VALUES ('50', 'vpweb.invoke_url', '进入无限宝网页版地址', '', '进入无限宝网页版地址（请维护完整地址，格式如：http://vpweb.netstudy5.dev/vpweb/invoke.htm）', '1', '1', 'validate-url', '221');
INSERT INTO `t_system_config` VALUES ('51', 'wxb.ios_url', '无限宝IOS版下载链接', 'https://itunes.apple.com/us/app/wu-xian-bao-yuan-cheng-jiao/id1126626050?l=zh&ls=1&mt=8', '无限宝IOS版下载链接地址', '1', '0', 'validate-url', '121');
INSERT INTO `t_system_config` VALUES ('56', 'wechatpay.app_id', '微信公众账号id', 'wxda745a15ce29404c', '微信公众账号id', '1', '0', 'config-max-length-50', '150');
INSERT INTO `t_system_config` VALUES ('57', 'wechatpay.app_secret', '微信公众账号应用密钥', '8f8d92154a16bb58e344565cc795801a', '微信公众账号应用密钥', '1', '0', 'config-max-length-50', '150');
INSERT INTO `t_system_config` VALUES ('58', 'wechatpay.mch_id', '微信商户号id', '1325102101', '微信商户号id', '1', '0', 'config-max-length-50', '150');
INSERT INTO `t_system_config` VALUES ('59', 'wechatpay.verify_key', '微信安全验证key', 'a9770e412fb309cf8de2c8b7cf6948d0', '微信安全验证key', '1', '0', 'config-max-length-50', '150');
INSERT INTO `t_system_config` VALUES ('60', 'chnmaster.hifi_domain', 'Hifi接口主域名', 'http://if2.zhenxian.fm/interface2', '外网环境，hifi正式接口', '0', '0', '', '500');
INSERT INTO `t_system_config` VALUES ('70', 'phone.wolun_username', '沃伦短信账号', '242', '用于发送沃伦短信内容', '1', '0', 'config-max-length-50', '400');
INSERT INTO `t_system_config` VALUES ('71', 'phone.wolun_password', '沃伦短信账号密码', 'zgyydsw242', '用于发送沃伦短信内容', '0', '0', 'config-max-length-50', '401');
INSERT INTO `t_system_config` VALUES ('72', 'phone.public_content_gateway', '固定格式短信内容使用网关', '1', '发送找回密码，发送用户名，验证码等短信内容网关，0表示玄武网关，1表示沃伦网关', '1', '0', 'config-int-range-0-1', '402');
INSERT INTO `t_system_config` VALUES ('73', 'phone.wolun_extno', '沃伦短信子帐号', '01', '沃伦短信子帐号', '1', '0', 'config-max-length-50', '403');
INSERT INTO `t_system_config` VALUES ('74', 'phone.defined_content_username', '玄武自定义短信账号', '', '通过玄武短信网关发送自定义短信内容', '1', '0', 'config-max-length-50', '404');
INSERT INTO `t_system_config` VALUES ('75', 'phone.defined_content_password', '玄武自定义短信账号密码', '', '通过玄武短信网关发送自定义短信内容', '0', '0', 'config-max-length-50', '405');
INSERT INTO `t_system_config` VALUES ('76', 'phone.public_content_username', '玄武固定格式短信账号', '', '通过玄武短信网关发送找回密码，发送用户名，验证码等短信内容', '1', '0', 'config-max-length-50', '406');
INSERT INTO `t_system_config` VALUES ('77', 'phone.public_content_password', '玄武固定格式短信账号密码', '', '通过玄武短信网关发送找回密码，发送用户名，验证码等短信内容', '0', '0', 'config-max-length-50', '407');
INSERT INTO `t_system_config` VALUES ('78', 'phone.xuanwu_send_report', '是否获取玄武短信网关发送结果信息', '1', '是否获取发送结果信息：0不获取 1获取, 默认不获取', '1', '1', 'config-max-length-50', '408');
INSERT INTO `t_system_config` VALUES ('79', 'wxb.module_params', '无限宝参数控制', 'NoDocShare:1, NoWebShare:1, NoSysCheck:0, NoAppShare:1, NoPlayMedia:0, NoClassTest:1, NoClassMgr:0, NoNetDisk:1, NoRecord:0, NoRollCall:1, NoVote:0, NoAskQ:0, NoSEG:1', '无限宝PC客户端模块配置，隐藏/显示一些可定义的模块\r\n请用键值对写法，以英文\":\"隔开，参数之间以英文\",\"隔开\r\n如（param1:value1,param2:value2,param3:value3...）', '1', '1', 'config-max-length-2000', '121');

-- ----------------------------
-- Table structure for `t_system_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_log`;
CREATE TABLE `t_system_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` int(11) DEFAULT NULL,
  `OPERATE` varchar(1500) DEFAULT NULL,
  `OPERATETIME` datetime DEFAULT NULL,
  `OPERATEIP` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_SYSTEM_LOG` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_system_module`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_module`;
CREATE TABLE `t_system_module` (
  `ID` int(11) NOT NULL,
  `APP_ID` int(11) NOT NULL,
  `NAME` varchar(150) DEFAULT NULL,
  `URL` varchar(500) DEFAULT NULL,
  `DESCRIPTION` varchar(1500) DEFAULT NULL,
  `ISSHOW` tinyint(4) DEFAULT '0',
  `PARENTID` int(11) DEFAULT '0',
  `ISCOMMON` tinyint(4) DEFAULT '0',
  `ISOPERATE` tinyint(4) DEFAULT '0',
  `SEQ` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_module
-- ----------------------------
INSERT INTO `t_system_module` VALUES ('1100000', '1', '首页', '/index.htm', null, '1', '0', '1', '0', '1100000');
INSERT INTO `t_system_module` VALUES ('1200000', '1', '基础数据', null, null, '1', '0', '0', '0', '1200000');
INSERT INTO `t_system_module` VALUES ('1200100', '1', '培训基地', '/basic/agencyManage.htm', null, '1', '1200000', '0', '0', '1200100');
INSERT INTO `t_system_module` VALUES ('1200101', '1', '新增', '/basic/addAgency.htm', null, '0', '1200100', '0', '1', '1200101');
INSERT INTO `t_system_module` VALUES ('1200102', '1', '修改', '/basic/editAgency.htm', null, '0', '1200100', '0', '1', '1200102');
INSERT INTO `t_system_module` VALUES ('1200103', '1', '删除', '/basic/delAgency.htm', null, '0', '1200100', '0', '1', '1200103');
INSERT INTO `t_system_module` VALUES ('1200104', '1', '注销', '/basic/cancelAgency.htm', null, '0', '1200100', '0', '1', '1200104');
INSERT INTO `t_system_module` VALUES ('1200105', '1', '恢复注销', '/basic/recoverAgency.htm', null, '0', '1200100', '0', '1', '1200105');
INSERT INTO `t_system_module` VALUES ('1200106', '1', '重置账号密码', '/basic/resetAgencyPwd.htm', null, '0', '1200100', '0', '1', '1200106');
INSERT INTO `t_system_module` VALUES ('1200107', '1', '展示图片', '/basic/agencyPicture.htm', null, '0', '1200100', '0', '1', '1200107');
INSERT INTO `t_system_module` VALUES ('1200108', '1', '维护展示图片', '/basic/editAgencyPicture.htm', null, '0', '1200100', '0', '1', '1200108');
INSERT INTO `t_system_module` VALUES ('1200200', '1', '名师大家', '/basic/teacherManage.htm', null, '1', '1200000', '0', '0', '1200200');
INSERT INTO `t_system_module` VALUES ('1200201', '1', '新增', '/basic/addTeacher.htm', null, '0', '1200200', '0', '1', '1200201');
INSERT INTO `t_system_module` VALUES ('1200202', '1', '修改', '/basic/editTeacher.htm', null, '0', '1200200', '0', '1', '1200202');
INSERT INTO `t_system_module` VALUES ('1200203', '1', '删除', '/basic/delTeacher.htm', null, '0', '1200200', '0', '1', '1200203');
INSERT INTO `t_system_module` VALUES ('1200204', '1', '注销', '/basic/cancelTeacher.htm', null, '0', '1200200', '0', '1', '1200204');
INSERT INTO `t_system_module` VALUES ('1200205', '1', '恢复注销', '/basic/recoverTeacher.htm', null, '0', '1200200', '0', '1', '1200205');
INSERT INTO `t_system_module` VALUES ('1200206', '1', '风采展示', '/basic/teacherStyle.htm', null, '0', '1200200', '0', '1', '1200206');
INSERT INTO `t_system_module` VALUES ('1200207', '1', '维护风采展示', '/basic/editTeacherStyle.htm', null, '0', '1200200', '0', '1', '1200207');
INSERT INTO `t_system_module` VALUES ('1200300', '1', '个人用户', '/basic/studentManage.htm', null, '1', '1200000', '0', '0', '1200300');
INSERT INTO `t_system_module` VALUES ('1200400', '1', '助教管理', '/basic/assistantManage.htm', null, '1', '1200000', '0', '0', '1200400');
INSERT INTO `t_system_module` VALUES ('1200401', '1', '新增', '/basic/assistantAdd.htm', null, '1', '1200400', '0', '1', '1200401');
INSERT INTO `t_system_module` VALUES ('1200402', '1', '修改', '/basic/assistantEdit.htm', null, '1', '1200400', '0', '1', '1200402');
INSERT INTO `t_system_module` VALUES ('1200403', '1', '删除', '/basic/assistantDelete.htm', null, '1', '1200400', '0', '1', '1200403');
INSERT INTO `t_system_module` VALUES ('1200404', '1', '恢复注销', '/basic/assistantRecover.htm', null, '1', '1200400', '0', '1', '1200404');
INSERT INTO `t_system_module` VALUES ('1200600', '1', '学校维护', '/basic/schoolManage.htm', null, '0', '1200000', '0', '0', '1200600');
INSERT INTO `t_system_module` VALUES ('1200601', '1', '新增', '/basic/schoolAdd.htm', null, '1', '1200600', '0', '1', '1200601');
INSERT INTO `t_system_module` VALUES ('1200602', '1', '修改', '/basic/schoolEdit.htm', null, '1', '1200600', '0', '1', '1200602');
INSERT INTO `t_system_module` VALUES ('1200603', '1', '删除', '/basic/schoolDelete.htm', null, '1', '1200600', '0', '1', '1200603');
INSERT INTO `t_system_module` VALUES ('1200604', '1', '导入', '/basic/schoolImport.htm', null, '1', '1200600', '0', '1', '1200604');
INSERT INTO `t_system_module` VALUES ('1200700', '1', '音乐导师', '/basic/musicTutorManage.htm', null, '1', '1200000', '0', '0', '1200700');
INSERT INTO `t_system_module` VALUES ('1200701', '1', '查看', '/basic/viewMusicTutor.htm', null, '0', '1200700', '0', '1', '1200701');
INSERT INTO `t_system_module` VALUES ('1200702', '1', '新增', '/basic/addMusicTutor.htm', null, '0', '1200700', '0', '1', '1200702');
INSERT INTO `t_system_module` VALUES ('1200703', '1', '修改', '/basic/editMusicTutor.htm', null, '0', '1200700', '0', '1', '1200703');
INSERT INTO `t_system_module` VALUES ('1200704', '1', '审核', '/basic/auditMusicTutor.htm', null, '0', '1200700', '0', '1', '1200704');
INSERT INTO `t_system_module` VALUES ('1250000', '1', '审核管理', null, null, '1', '0', '0', '0', '1250000');
INSERT INTO `t_system_module` VALUES ('1250100', '1', '加入联盟', '/basic/agencyJoinManage.htm', null, '1', '1250000', '0', '0', '1250100');
INSERT INTO `t_system_module` VALUES ('1250101', '1', '查看', '/basic/viewAgencyJoin.htm', null, '1', '1250100', '0', '1', '1250101');
INSERT INTO `t_system_module` VALUES ('1250102', '1', '修改', '/basic/editAgencyJoin.htm', null, '1', '1250100', '0', '1', '1250102');
INSERT INTO `t_system_module` VALUES ('1250103', '1', '删除', '/basic/delAgencyJoin.htm', null, '1', '1250100', '0', '1', '1250103');
INSERT INTO `t_system_module` VALUES ('1250104', '1', '同意加盟', '/basic/checkAgencyJoin.htm', null, '1', '1250100', '0', '1', '1250104');
INSERT INTO `t_system_module` VALUES ('1250200', '1', '联合会会员', '/basic/unionMemberManage.htm', null, '1', '1250000', '0', '0', '1250200');
INSERT INTO `t_system_module` VALUES ('1250201', '1', '查看', '/basic/viewUnionMember.htm', null, '0', '1250200', '0', '1', '1250201');
INSERT INTO `t_system_module` VALUES ('1250202', '1', '新增', '/basic/addUnionMember.htm', null, '0', '1250200', '0', '1', '1250202');
INSERT INTO `t_system_module` VALUES ('1250203', '1', '修改', '/basic/editUnionMember.htm', null, '0', '1250200', '0', '1', '1250203');
INSERT INTO `t_system_module` VALUES ('1250204', '1', '删除', '/basic/delUnionMember.htm', null, '0', '1250200', '0', '1', '1250204');
INSERT INTO `t_system_module` VALUES ('1250205', '1', '审核', '/basic/auditUnionMember.htm', null, '0', '1250200', '0', '1', '1250205');
INSERT INTO `t_system_module` VALUES ('1250300', '1', '导师审核', '/audit/tutorAuditManage.htm', null, '0', '1250000', '0', '0', '1250300');
INSERT INTO `t_system_module` VALUES ('1250400', '1', '评论审核', '/audit/commentAuditManage.htm', null, '1', '1250000', '0', '0', '1250400');
INSERT INTO `t_system_module` VALUES ('1300000', '1', '课程管理', null, null, '1', '0', '0', '0', '1300000');
INSERT INTO `t_system_module` VALUES ('1300100', '1', '基地课程', '/course/courseManage.htm', null, '1', '1300000', '0', '0', '1300100');
INSERT INTO `t_system_module` VALUES ('1300101', '1', '新增', '/course/addCourse.htm', null, '0', '1300100', '0', '1', '1300101');
INSERT INTO `t_system_module` VALUES ('1300102', '1', '修改', '/course/editCourse.htm', null, '0', '1300100', '0', '1', '1300102');
INSERT INTO `t_system_module` VALUES ('1300103', '1', '删除', '/course/delCourse.htm', null, '0', '1300100', '0', '1', '1300103');
INSERT INTO `t_system_module` VALUES ('1300200', '1', '音乐会', '/concert/concertManage.htm', null, '1', '1300000', '0', '0', '1300200');
INSERT INTO `t_system_module` VALUES ('1300201', '1', '新增', '/concert/addConcert.htm', null, '0', '1300200', '0', '1', '1300201');
INSERT INTO `t_system_module` VALUES ('1300202', '1', '修改', '/concert/editConcert.htm', null, '0', '1300200', '0', '1', '1300202');
INSERT INTO `t_system_module` VALUES ('1300203', '1', '删除', '/concert/delConcert.htm', null, '0', '1300200', '0', '1', '1300203');
INSERT INTO `t_system_module` VALUES ('1300300', '1', '点播视频', '/vod/vodManage.htm', null, '1', '1300000', '0', '0', '1300300');
INSERT INTO `t_system_module` VALUES ('1300301', '1', '新增', '/vod/addVod.htm', null, '0', '1300300', '0', '1', '1300301');
INSERT INTO `t_system_module` VALUES ('1300302', '1', '修改', '/vod/editVod.htm', null, '0', '1300300', '0', '1', '1300302');
INSERT INTO `t_system_module` VALUES ('1300303', '1', '删除', '/vod/delVod.htm', null, '0', '1300300', '0', '1', '1300303');
INSERT INTO `t_system_module` VALUES ('1300400', '1', '活动专题', '/homepage/compete/competeManage.htm', null, '1', '1300000', '0', '0', '1300400');
INSERT INTO `t_system_module` VALUES ('1400000', '1', '首页管理', null, null, '1', '0', '0', '0', '1400000');
INSERT INTO `t_system_module` VALUES ('1400100', '1', '图片音视频', '/homepage/adv/advManage.htm', null, '1', '1400000', '0', '0', '1400100');
INSERT INTO `t_system_module` VALUES ('1400101', '1', '新增', '/homepage/adv/addAdv.htm', null, '1', '1400100', '0', '1', '1400101');
INSERT INTO `t_system_module` VALUES ('1400102', '1', '修改', '/homepage/adv/updateAdv.htm', null, '1', '1400100', '0', '1', '1400102');
INSERT INTO `t_system_module` VALUES ('1400103', '1', '删除', '/homepage/adv/indexAdvDelete.htm', null, '1', '1400100', '0', '1', '1400103');
INSERT INTO `t_system_module` VALUES ('1400200', '1', '基地课程推荐', '/homepage/course/courseRec.htm', null, '1', '1400000', '0', '0', '1400200');
INSERT INTO `t_system_module` VALUES ('1400300', '1', '音乐会推荐', '/homepage/concert/concertRec.htm', null, '1', '1400000', '0', '0', '1400300');
INSERT INTO `t_system_module` VALUES ('1400350', '1', '点播视频推荐', '/homepage/vod/vodRec.htm', null, '1', '1400000', '0', '0', '1400350');
INSERT INTO `t_system_module` VALUES ('1400400', '1', '培训基地推荐', '/homepage/agency/agencyRec.htm', null, '1', '1400000', '0', '0', '1400400');
INSERT INTO `t_system_module` VALUES ('1400500', '1', '名师大家推荐', '/homepage/tea/teacherRec.htm', null, '1', '1400000', '0', '0', '1400500');
INSERT INTO `t_system_module` VALUES ('1400550', '1', '音乐导师推荐', '/homepage/tutor/tutorRec.htm', null, '1', '1400000', '0', '0', '1400550');
INSERT INTO `t_system_module` VALUES ('1400560', '1', '高校风采展示', '/homepage/college/collegeManage.htm', null, '1', '1400000', '0', '0', '1400560');
INSERT INTO `t_system_module` VALUES ('1400561', '1', '新增', '/homepage/college/collegeAdd.htm', null, '0', '1400560', '0', '1', '1400561');
INSERT INTO `t_system_module` VALUES ('1400562', '1', '修改', '/homepage/college/collegeEdit.htm', null, '0', '1400560', '0', '1', '1400562');
INSERT INTO `t_system_module` VALUES ('1400563', '1', '删除', '/homepage/college/collegeDel.htm', null, '0', '1400560', '0', '1', '1400563');
INSERT INTO `t_system_module` VALUES ('1400600', '1', '优秀学员', '/homepage/stu/studentManage.htm', null, '1', '1400000', '0', '0', '1400600');
INSERT INTO `t_system_module` VALUES ('1400601', '1', '新增', '/homepage/stu/addStudent.htm', null, '1', '1400600', '0', '1', '1400601');
INSERT INTO `t_system_module` VALUES ('1400602', '1', '修改', '/homepage/stu/updateStudent.htm', null, '1', '1400600', '0', '1', '1400602');
INSERT INTO `t_system_module` VALUES ('1400603', '1', '删除', '/homepage/stu/studentDelete.htm', null, '1', '1400600', '0', '1', '1400603');
INSERT INTO `t_system_module` VALUES ('1400604', '1', '展示图片', '/homepage/stu/studentPicManage.htm', null, '0', '1400600', '0', '1', '1400604');
INSERT INTO `t_system_module` VALUES ('1400605', '1', '维护展示图片', '/homepage/stu/addOrUpdateStuPicture.htm', null, '0', '1400600', '0', '1', '1400605');
INSERT INTO `t_system_module` VALUES ('1400700', '1', '新闻公告', '/homepage/info/infoManage.htm', null, '1', '1400000', '0', '0', '1400700');
INSERT INTO `t_system_module` VALUES ('1400701', '1', '新增', '/homepage/info/infoAdd.htm', null, '1', '1400700', '0', '1', '1400701');
INSERT INTO `t_system_module` VALUES ('1400702', '1', '修改', '/homepage/info/infoEdit.htm', null, '1', '1400700', '0', '1', '1400702');
INSERT INTO `t_system_module` VALUES ('1400703', '1', '删除', '/homepage/info/infoDelete.htm', null, '1', '1400700', '0', '1', '1400703');
INSERT INTO `t_system_module` VALUES ('1400800', '1', '视频回顾', '/homepage/info/videoReManage.htm', null, '1', '1400000', '0', '0', '1400800');
INSERT INTO `t_system_module` VALUES ('1400801', '1', '新增', '/homepage/info/videoReAdd.htm', null, '1', '1400800', '0', '1', '1400801');
INSERT INTO `t_system_module` VALUES ('1400802', '1', '修改', '/homepage/info/videoReEdit.htm', null, '1', '1400800', '0', '1', '1400802');
INSERT INTO `t_system_module` VALUES ('1400803', '1', '删除', '/homepage/info/videoReDel.htm', null, '1', '1400800', '0', '1', '1400803');
INSERT INTO `t_system_module` VALUES ('1400900', '1', '帮助管理', '/homepage/help/helpManage.htm', null, '1', '1400000', '0', '0', '1400900');
INSERT INTO `t_system_module` VALUES ('1400950', '1', '页脚维护', '/homepage/index/footerManage.htm', null, '1', '1400000', '0', '0', '1400950');
INSERT INTO `t_system_module` VALUES ('1500000', '1', '业务管理', null, null, '1', '0', '0', '0', '1500000');
INSERT INTO `t_system_module` VALUES ('1500100', '1', '支付处理', '/account/payDeal.htm', null, '1', '1500000', '0', '0', '1500100');
INSERT INTO `t_system_module` VALUES ('1500300', '1', '票务验证', '/account/codeDeal.htm', null, '1', '1500000', '0', '0', '1500300');
INSERT INTO `t_system_module` VALUES ('1500400', '1', '订单优惠', '/account/orderAnnul.htm', null, '1', '1500000', '0', '0', '1500400');
INSERT INTO `t_system_module` VALUES ('1500600', '1', '选课报名', '/account/stuReport.htm', null, '1', '1500000', '0', '0', '1500600');
INSERT INTO `t_system_module` VALUES ('1500900', '1', '优惠券管理', '/account/couponManage.htm', null, '1', '1500000', '0', '0', '1500900');
INSERT INTO `t_system_module` VALUES ('1500901', '1', '（批量）新增', '/account/addCoupon.htm', null, '0', '1500900', '0', '1', '1500901');
INSERT INTO `t_system_module` VALUES ('1500902', '1', '修改', '/account/editCoupon.htm', null, '0', '1500900', '0', '1', '1500902');
INSERT INTO `t_system_module` VALUES ('1500903', '1', '删除', '/account/delCoupon.htm', null, '0', '1500900', '0', '1', '1500903');
INSERT INTO `t_system_module` VALUES ('1500904', '1', '导出', '/account/exportCoupon.htm', null, '0', '1500900', '0', '1', '1500904');
INSERT INTO `t_system_module` VALUES ('1500905', '1', '注销', '/account/cancelCoupon.htm', null, '0', '1500900', '0', '1', '1500905');
INSERT INTO `t_system_module` VALUES ('1501000', '1', 'HIFI会员管理', '/account/hifiMemberManage.htm', null, '1', '1500000', '0', '0', '1501000');
INSERT INTO `t_system_module` VALUES ('1600000', '1', '运维管理', null, null, '1', '0', '0', '0', '1600000');
INSERT INTO `t_system_module` VALUES ('1600100', '1', '服务器维护', '/wxb/serverManage.htm', null, '1', '1600000', '0', '0', '1600100');
INSERT INTO `t_system_module` VALUES ('1600101', '1', '新增', '/wxb/addServer.htm', null, '0', '1600100', '0', '1', '1600101');
INSERT INTO `t_system_module` VALUES ('1600102', '1', '修改', '/wxb/editServer.htm', null, '0', '1600100', '0', '1', '1600102');
INSERT INTO `t_system_module` VALUES ('1600103', '1', '查看', '/wxb/serverInfo.htm', null, '0', '1600100', '0', '1', '1600103');
INSERT INTO `t_system_module` VALUES ('1600104', '1', '删除', '/wxb/delServer.htm', null, '0', '1600100', '0', '1', '1600104');
INSERT INTO `t_system_module` VALUES ('1600105', '1', '开启/停用服务器', null, null, '0', '1600100', '0', '1', '1600105');
INSERT INTO `t_system_module` VALUES ('1700000', '1', '系统管理', null, null, '1', '0', '0', '0', '1700000');
INSERT INTO `t_system_module` VALUES ('1700100', '1', '用户管理', '/system/systemUserManage.htm', null, '1', '1700000', '0', '0', '1700100');
INSERT INTO `t_system_module` VALUES ('1700101', '1', '新增用户', '/system/addSystemUser.htm', null, '0', '1700100', '0', '1', '1700101');
INSERT INTO `t_system_module` VALUES ('1700102', '1', '查看用户', '/system/viewSystemUser.htm', null, '0', '1700100', '0', '1', '1700102');
INSERT INTO `t_system_module` VALUES ('1700103', '1', '冻结用户', '/system/cancelSystemUser.htm', null, '0', '1700100', '0', '1', '1700103');
INSERT INTO `t_system_module` VALUES ('1700104', '1', '解冻用户', '/system/backSystemUser.htm', null, '0', '1700100', '0', '1', '1700104');
INSERT INTO `t_system_module` VALUES ('1700105', '1', '分配权限', '/system/assignSystemRole.htm', null, '0', '1700100', '0', '1', '1700105');
INSERT INTO `t_system_module` VALUES ('1700106', '1', '删除用户', '/system/delSystemUser.htm', null, '0', '1700100', '0', '1', '1700106');
INSERT INTO `t_system_module` VALUES ('1700107', '1', '修改用户', '/system/editSystemUser.htm', null, '0', '1700100', '0', '1', '1700107');
INSERT INTO `t_system_module` VALUES ('1700200', '1', '角色管理', '/system/systemRoleManage.htm', null, '1', '1700000', '0', '0', '1700200');
INSERT INTO `t_system_module` VALUES ('1700201', '1', '新增角色', '/system/addSystemRole.htm', null, '0', '1700200', '0', '1', '1700201');
INSERT INTO `t_system_module` VALUES ('1700203', '1', '分配用户', '/system/assignSystemUser.htm', null, '0', '1700200', '0', '1', '1700203');
INSERT INTO `t_system_module` VALUES ('1700204', '1', '删除角色', '/system/delSystemRole.htm', null, '0', '1700200', '0', '1', '1700204');
INSERT INTO `t_system_module` VALUES ('1700205', '1', '查看角色', '/system/showSystemRole.htm', null, '0', '1700200', '0', '1', '1700205');
INSERT INTO `t_system_module` VALUES ('1700206', '1', '修改角色', '/system/editSystemRole.htm', null, '0', '1700200', '0', '1', '1700206');
INSERT INTO `t_system_module` VALUES ('1700300', '1', '系统日志', '/system/systemLog.htm', null, '1', '1700000', '0', '0', '1700300');
INSERT INTO `t_system_module` VALUES ('1700400', '1', '系统参数', '/system/systemConfigManage.htm', null, '1', '1700000', '0', '0', '1700400');
INSERT INTO `t_system_module` VALUES ('1700401', '1', '修改系统参数', '/system/editSystemConfig.htm', null, '0', '1700400', '0', '1', '1700401');
INSERT INTO `t_system_module` VALUES ('1800000', '1', '统计查询', null, null, '1', '0', '0', '0', '1800000');
INSERT INTO `t_system_module` VALUES ('1800100', '1', '报名统计', '/stat/reportStat.htm', null, '1', '1800000', '0', '0', '1800100');
INSERT INTO `t_system_module` VALUES ('1800200', '1', '上课统计', '/stat/inclassStat.htm', null, '1', '1800000', '0', '0', '1800200');
INSERT INTO `t_system_module` VALUES ('1800300', '1', '订单查询', '/stat/orderStat.htm', null, '1', '1800000', '0', '0', '1800300');
INSERT INTO `t_system_module` VALUES ('1800700', '1', '收支统计', '/stat/fundsStat.htm', null, '1', '1800000', '0', '0', '1800700');
INSERT INTO `t_system_module` VALUES ('1800800', '1', 'ios收支统计', '/stat/iospayStat.htm', null, '1', '1800000', '0', '0', '1800800');
INSERT INTO `t_system_module` VALUES ('1800900', '1', '短信查询', '/stat/smsStat.htm', null, '1', '1800000', '0', '0', '1800900');

-- ----------------------------
-- Table structure for `t_system_region`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_region`;
CREATE TABLE `t_system_region` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `REGIONCODE` varchar(6) DEFAULT NULL,
  `REGIONNAME` varchar(75) DEFAULT NULL,
  `FULLCODE` char(6) DEFAULT NULL,
  `FULLNAME` varchar(750) DEFAULT NULL,
  `SHORTSPELL` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_SYSTEM_REGION_REGIONCODE` (`REGIONCODE`),
  KEY `IDX_T_SYSTEM_REGION_FULLCODE` (`FULLCODE`)
) ENGINE=InnoDB AUTO_INCREMENT=619903 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_region
-- ----------------------------
INSERT INTO `t_system_region` VALUES ('4072', '11', '北京市', '110000', '北京市', 'bjs');
INSERT INTO `t_system_region` VALUES ('4073', '1101', '市辖区', '110100', '北京市市辖区', 'bjssxq');
INSERT INTO `t_system_region` VALUES ('4074', '110101', '东城区', '110101', '北京市东城区', 'bjsdcq');
INSERT INTO `t_system_region` VALUES ('4075', '110102', '西城区', '110102', '北京市西城区', 'bjsxcq');
INSERT INTO `t_system_region` VALUES ('4076', '110103', '崇文区', '110103', '北京市崇文区', 'bjscwq');
INSERT INTO `t_system_region` VALUES ('4077', '110104', '宣武区', '110104', '北京市宣武区', 'bjsxwq');
INSERT INTO `t_system_region` VALUES ('4078', '110105', '朝阳区', '110105', '北京市朝阳区', 'bjscyq');
INSERT INTO `t_system_region` VALUES ('4079', '110106', '丰台区', '110106', '北京市丰台区', 'bjsftq');
INSERT INTO `t_system_region` VALUES ('4080', '110107', '石景山区', '110107', '北京市石景山区', 'bjssjsq');
INSERT INTO `t_system_region` VALUES ('4081', '110108', '海淀区', '110108', '北京市海淀区', 'bjshdq');
INSERT INTO `t_system_region` VALUES ('4082', '110109', '门头沟区', '110109', '北京市门头沟区', 'bjsmtgq');
INSERT INTO `t_system_region` VALUES ('4083', '110110', '燕山区', '110110', '北京市燕山区', 'bjsysq');
INSERT INTO `t_system_region` VALUES ('4084', '110111', '房山区', '110111', '北京市房山区', 'bjsfsq');
INSERT INTO `t_system_region` VALUES ('4085', '110112', '通州区', '110112', '北京市通州区', 'bjstzq');
INSERT INTO `t_system_region` VALUES ('4086', '110113', '顺义区', '110113', '北京市顺义区', 'bjssyq');
INSERT INTO `t_system_region` VALUES ('4087', '110114', '昌平区', '110114', '北京市昌平区', 'bjscpq');
INSERT INTO `t_system_region` VALUES ('4088', '110115', '大兴区', '110115', '北京市大兴区', 'bjsdxq');
INSERT INTO `t_system_region` VALUES ('4089', '110116', '怀柔区', '110116', '北京市怀柔区', 'bjshrq');
INSERT INTO `t_system_region` VALUES ('4090', '110117', '平谷区', '110117', '北京市平谷区', 'bjspgq');
INSERT INTO `t_system_region` VALUES ('4091', '1102', '县', '110200', '北京市县', 'bjsx');
INSERT INTO `t_system_region` VALUES ('4092', '110228', '密云县', '110228', '北京市密云县', 'bjsmyx');
INSERT INTO `t_system_region` VALUES ('4093', '110229', '延庆县', '110229', '北京市延庆县', 'bjsyqx');
INSERT INTO `t_system_region` VALUES ('4094', '12', '天津市', '120000', '天津市', 'tjs');
INSERT INTO `t_system_region` VALUES ('4096', '1201', '市辖区', '120100', '天津市市辖区', 'tjssxq');
INSERT INTO `t_system_region` VALUES ('4097', '120101', '和平区', '120101', '天津市和平区', 'tjshpq');
INSERT INTO `t_system_region` VALUES ('4098', '120102', '河东区', '120102', '天津市河东区', 'tjshdq');
INSERT INTO `t_system_region` VALUES ('4099', '120103', '河西区', '120103', '天津市河西区', 'tjshxq');
INSERT INTO `t_system_region` VALUES ('4100', '120104', '南开区', '120104', '天津市南开区', 'tjsnkq');
INSERT INTO `t_system_region` VALUES ('4101', '120105', '河北区', '120105', '天津市河北区', 'tjshbq');
INSERT INTO `t_system_region` VALUES ('4102', '120106', '红桥区', '120106', '天津市红桥区', 'tjshqq');
INSERT INTO `t_system_region` VALUES ('4103', '120107', '塘沽区', '120107', '天津市塘沽区', 'tjstgq');
INSERT INTO `t_system_region` VALUES ('4104', '120108', '汉沽区', '120108', '天津市汉沽区', 'tjshgq');
INSERT INTO `t_system_region` VALUES ('4105', '120109', '大港区', '120109', '天津市大港区', 'tjsdgq');
INSERT INTO `t_system_region` VALUES ('4106', '120110', '东丽区', '120110', '天津市东丽区', 'tjsdlq');
INSERT INTO `t_system_region` VALUES ('4107', '120111', '西青区', '120111', '天津市西青区', 'tjsxqq');
INSERT INTO `t_system_region` VALUES ('4108', '120112', '津南区', '120112', '天津市津南区', 'tjsjnq');
INSERT INTO `t_system_region` VALUES ('4109', '120113', '北辰区', '120113', '天津市北辰区', 'tjsbcq');
INSERT INTO `t_system_region` VALUES ('4110', '120114', '武清区', '120114', '天津市武清区', 'tjswqq');
INSERT INTO `t_system_region` VALUES ('4111', '120115', '宝坻区', '120115', '天津市宝坻区', 'tjsbcq');
INSERT INTO `t_system_region` VALUES ('4112', '1202', '县', '120200', '天津市县', 'tjsx');
INSERT INTO `t_system_region` VALUES ('4113', '120221', '宁河县', '120221', '天津市宁河县', 'tjsnhx');
INSERT INTO `t_system_region` VALUES ('4114', '120223', '静海县', '120223', '天津市静海县', 'tjsjhx');
INSERT INTO `t_system_region` VALUES ('4115', '120225', '蓟县', '120225', '天津市蓟县', 'tjsjx');
INSERT INTO `t_system_region` VALUES ('4116', '13', '河北省', '130000', '河北省', 'hbs');
INSERT INTO `t_system_region` VALUES ('4117', '1301', '石家庄市', '130100', '河北省石家庄市', 'hbssjzs');
INSERT INTO `t_system_region` VALUES ('4118', '130101', '市辖区', '130101', '河北省石家庄市市辖区', 'hbssjzssxq');
INSERT INTO `t_system_region` VALUES ('4119', '130102', '长安区', '130102', '河北省石家庄市长安区', 'hbssjzszaq');
INSERT INTO `t_system_region` VALUES ('4120', '130103', '桥东区', '130103', '河北省石家庄市桥东区', 'hbssjzsqdq');
INSERT INTO `t_system_region` VALUES ('4121', '130104', '桥西区', '130104', '河北省石家庄市桥西区', 'hbssjzsqxq');
INSERT INTO `t_system_region` VALUES ('4122', '130105', '新华区', '130105', '河北省石家庄市新华区', 'hbssjzsxhq');
INSERT INTO `t_system_region` VALUES ('4123', '130107', '井陉矿区', '130107', '河北省石家庄市井陉矿区', 'hbssjzsjxkq');
INSERT INTO `t_system_region` VALUES ('4124', '130108', '裕华区', '130108', '河北省石家庄市裕华区', 'hbssjzsyhq');
INSERT INTO `t_system_region` VALUES ('4125', '130121', '井陉县', '130121', '河北省石家庄市井陉县', 'hbssjzsjxx');
INSERT INTO `t_system_region` VALUES ('4126', '130123', '正定县', '130123', '河北省石家庄市正定县', 'hbssjzszdx');
INSERT INTO `t_system_region` VALUES ('4127', '130124', '栾城县', '130124', '河北省石家庄市栾城县', 'hbssjzslcx');
INSERT INTO `t_system_region` VALUES ('4128', '130125', '行唐县', '130125', '河北省石家庄市行唐县', 'hbssjzsxtx');
INSERT INTO `t_system_region` VALUES ('4129', '130126', '灵寿县', '130126', '河北省石家庄市灵寿县', 'hbssjzslsx');
INSERT INTO `t_system_region` VALUES ('4130', '130127', '高邑县', '130127', '河北省石家庄市高邑县', 'hbssjzsgyx');
INSERT INTO `t_system_region` VALUES ('4131', '130128', '深泽县', '130128', '河北省石家庄市深泽县', 'hbssjzsszx');
INSERT INTO `t_system_region` VALUES ('4132', '130129', '赞皇县', '130129', '河北省石家庄市赞皇县', 'hbssjzszhx');
INSERT INTO `t_system_region` VALUES ('4133', '130130', '无极县', '130130', '河北省石家庄市无极县', 'hbssjzswjx');
INSERT INTO `t_system_region` VALUES ('4134', '130131', '平山县', '130131', '河北省石家庄市平山县', 'hbssjzspsx');
INSERT INTO `t_system_region` VALUES ('4135', '130132', '元氏县', '130132', '河北省石家庄市元氏县', 'hbssjzsysx');
INSERT INTO `t_system_region` VALUES ('4136', '130133', '赵县', '130133', '河北省石家庄市赵县', 'hbssjzszx');
INSERT INTO `t_system_region` VALUES ('4137', '130181', '辛集市', '130181', '河北省石家庄市辛集市', 'hbssjzsxjs');
INSERT INTO `t_system_region` VALUES ('4138', '130182', '藁城市', '130182', '河北省石家庄市藁城市', 'hbssjzsgcs');
INSERT INTO `t_system_region` VALUES ('4139', '130183', '晋州市', '130183', '河北省石家庄市晋州市', 'hbssjzsjzs');
INSERT INTO `t_system_region` VALUES ('4140', '130184', '新乐市', '130184', '河北省石家庄市新乐市', 'hbssjzsxls');
INSERT INTO `t_system_region` VALUES ('4141', '130185', '鹿泉市', '130185', '河北省石家庄市鹿泉市', 'hbssjzslqs');
INSERT INTO `t_system_region` VALUES ('4142', '1302', '唐山市', '130200', '河北省唐山市', 'hbstss');
INSERT INTO `t_system_region` VALUES ('4143', '130201', '市辖区', '130201', '河北省唐山市市辖区', 'hbstsssxq');
INSERT INTO `t_system_region` VALUES ('4144', '130202', '路南区', '130202', '河北省唐山市路南区', 'hbstsslnq');
INSERT INTO `t_system_region` VALUES ('4145', '130203', '路北区', '130203', '河北省唐山市路北区', 'hbstsslbq');
INSERT INTO `t_system_region` VALUES ('4146', '130204', '古冶区', '130204', '河北省唐山市古冶区', 'hbstssgyq');
INSERT INTO `t_system_region` VALUES ('4147', '130205', '开平区', '130205', '河北省唐山市开平区', 'hbstsskpq');
INSERT INTO `t_system_region` VALUES ('4148', '130207', '丰南区', '130207', '河北省唐山市丰南区', 'hbstssfnq');
INSERT INTO `t_system_region` VALUES ('4149', '130208', '丰润区', '130208', '河北省唐山市丰润区', 'hbstssfrq');
INSERT INTO `t_system_region` VALUES ('4150', '130210', '芦台经济开发区', '130210', '河北省唐山市芦台经济开发区', 'hbstssltjjkfq');
INSERT INTO `t_system_region` VALUES ('4151', '130211', '汉沽管理区', '130211', '河北省唐山市汉沽管理区', 'hbstsshgglq');
INSERT INTO `t_system_region` VALUES ('4152', '130223', '滦县', '130223', '河北省唐山市滦县', 'hbstsslx');
INSERT INTO `t_system_region` VALUES ('4153', '130224', '滦南县', '130224', '河北省唐山市滦南县', 'hbstsslnx');
INSERT INTO `t_system_region` VALUES ('4154', '130225', '乐亭县', '130225', '河北省唐山市乐亭县', 'hbstssltx');
INSERT INTO `t_system_region` VALUES ('4155', '130227', '迁西县', '130227', '河北省唐山市迁西县', 'hbstssqxx');
INSERT INTO `t_system_region` VALUES ('4156', '130229', '玉田县', '130229', '河北省唐山市玉田县', 'hbstssytx');
INSERT INTO `t_system_region` VALUES ('4157', '130230', '唐海县', '130230', '河北省唐山市唐海县', 'hbstssthx');
INSERT INTO `t_system_region` VALUES ('4158', '130281', '遵化市', '130281', '河北省唐山市遵化市', 'hbstsszhs');
INSERT INTO `t_system_region` VALUES ('4159', '130283', '迁安市', '130283', '河北省唐山市迁安市', 'hbstssqas');
INSERT INTO `t_system_region` VALUES ('4160', '1303', '秦皇岛市', '130300', '河北省秦皇岛市', 'hbsqhds');
INSERT INTO `t_system_region` VALUES ('4161', '130301', '市辖区', '130301', '河北省秦皇岛市市辖区', 'hbsqhdssxq');
INSERT INTO `t_system_region` VALUES ('4162', '130302', '海港区', '130302', '河北省秦皇岛市海港区', 'hbsqhdshgq');
INSERT INTO `t_system_region` VALUES ('4163', '130303', '山海关区', '130303', '河北省秦皇岛市山海关区', 'hbsqhdsshgq');
INSERT INTO `t_system_region` VALUES ('4164', '130304', '北戴河区', '130304', '河北省秦皇岛市北戴河区', 'hbsqhdsbdhq');
INSERT INTO `t_system_region` VALUES ('4165', '130305', '秦皇岛市开发区', '130305', '河北省秦皇岛市秦皇岛市开发区', 'hbsqhdsqhdskfq');
INSERT INTO `t_system_region` VALUES ('4166', '130321', '青龙满族自治县', '130321', '河北省秦皇岛市青龙满族自治县', 'hbsqhdsqlmzzzx');
INSERT INTO `t_system_region` VALUES ('4167', '130322', '昌黎县', '130322', '河北省秦皇岛市昌黎县', 'hbsqhdsclx');
INSERT INTO `t_system_region` VALUES ('4168', '130323', '抚宁县', '130323', '河北省秦皇岛市抚宁县', 'hbsqhdsfnx');
INSERT INTO `t_system_region` VALUES ('4169', '130324', '卢龙县', '130324', '河北省秦皇岛市卢龙县', 'hbsqhdsllx');
INSERT INTO `t_system_region` VALUES ('4170', '1304', '邯郸市', '130400', '河北省邯郸市', 'hbshds');
INSERT INTO `t_system_region` VALUES ('4171', '130401', '市辖区', '130401', '河北省邯郸市市辖区', 'hbshdssxq');
INSERT INTO `t_system_region` VALUES ('4172', '130402', '邯山区', '130402', '河北省邯郸市邯山区', 'hbshdshsq');
INSERT INTO `t_system_region` VALUES ('4173', '130403', '丛台区', '130403', '河北省邯郸市丛台区', 'hbshdsctq');
INSERT INTO `t_system_region` VALUES ('4174', '130404', '复兴区', '130404', '河北省邯郸市复兴区', 'hbshdsfxq');
INSERT INTO `t_system_region` VALUES ('4175', '130406', '峰峰矿区', '130406', '河北省邯郸市峰峰矿区', 'hbshdsffkq');
INSERT INTO `t_system_region` VALUES ('4176', '130407', '邯郸市开发区', '130407', '河北省邯郸市邯郸市开发区', 'hbshdshdskfq');
INSERT INTO `t_system_region` VALUES ('4177', '130408', '邯郸市马头生态工业城', '130408', '河北省邯郸市邯郸市马头生态工业城', 'hbshdshdsmtstgyc');
INSERT INTO `t_system_region` VALUES ('4178', '130421', '邯郸县', '130421', '河北省邯郸市邯郸县', 'hbshdshdx');
INSERT INTO `t_system_region` VALUES ('4179', '130423', '临漳县', '130423', '河北省邯郸市临漳县', 'hbshdslzx');
INSERT INTO `t_system_region` VALUES ('4180', '130424', '成安县', '130424', '河北省邯郸市成安县', 'hbshdscax');
INSERT INTO `t_system_region` VALUES ('4181', '130425', '大名县', '130425', '河北省邯郸市大名县', 'hbshdsdmx');
INSERT INTO `t_system_region` VALUES ('4182', '130426', '涉县', '130426', '河北省邯郸市涉县', 'hbshdssx');
INSERT INTO `t_system_region` VALUES ('4183', '130427', '磁县', '130427', '河北省邯郸市磁县', 'hbshdscx');
INSERT INTO `t_system_region` VALUES ('4184', '130428', '肥乡县', '130428', '河北省邯郸市肥乡县', 'hbshdsfxx');
INSERT INTO `t_system_region` VALUES ('4185', '130429', '永年县', '130429', '河北省邯郸市永年县', 'hbshdsynx');
INSERT INTO `t_system_region` VALUES ('4186', '130430', '邱县', '130430', '河北省邯郸市邱县', 'hbshdsqx');
INSERT INTO `t_system_region` VALUES ('4187', '130431', '鸡泽县', '130431', '河北省邯郸市鸡泽县', 'hbshdsjzx');
INSERT INTO `t_system_region` VALUES ('4188', '130432', '广平县', '130432', '河北省邯郸市广平县', 'hbshdsgpx');
INSERT INTO `t_system_region` VALUES ('4189', '130433', '馆陶县', '130433', '河北省邯郸市馆陶县', 'hbshdsgtx');
INSERT INTO `t_system_region` VALUES ('4190', '130434', '魏县', '130434', '河北省邯郸市魏县', 'hbshdswx');
INSERT INTO `t_system_region` VALUES ('4191', '130435', '曲周县', '130435', '河北省邯郸市曲周县', 'hbshdsqzx');
INSERT INTO `t_system_region` VALUES ('4192', '130481', '武安市', '130481', '河北省邯郸市武安市', 'hbshdswas');
INSERT INTO `t_system_region` VALUES ('4193', '1305', '邢台市', '130500', '河北省邢台市', 'hbsxts');
INSERT INTO `t_system_region` VALUES ('4194', '130501', '市辖区', '130501', '河北省邢台市市辖区', 'hbsxtssxq');
INSERT INTO `t_system_region` VALUES ('4195', '130502', '桥东区', '130502', '河北省邢台市桥东区', 'hbsxtsqdq');
INSERT INTO `t_system_region` VALUES ('4196', '130503', '桥西区', '130503', '河北省邢台市桥西区', 'hbsxtsqxq');
INSERT INTO `t_system_region` VALUES ('4197', '130504', '邢台市开发区', '130504', '河北省邢台市邢台市开发区', 'hbsxtsxtskfq');
INSERT INTO `t_system_region` VALUES ('4198', '130521', '邢台县', '130521', '河北省邢台市邢台县', 'hbsxtsxtx');
INSERT INTO `t_system_region` VALUES ('4199', '130522', '临城县', '130522', '河北省邢台市临城县', 'hbsxtslcx');
INSERT INTO `t_system_region` VALUES ('4200', '130523', '内丘县', '130523', '河北省邢台市内丘县', 'hbsxtsnqx');
INSERT INTO `t_system_region` VALUES ('4201', '130524', '柏乡县', '130524', '河北省邢台市柏乡县', 'hbsxtsbxx');
INSERT INTO `t_system_region` VALUES ('4202', '130525', '隆尧县', '130525', '河北省邢台市隆尧县', 'hbsxtslyx');
INSERT INTO `t_system_region` VALUES ('4203', '130526', '任县', '130526', '河北省邢台市任县', 'hbsxtsrx');
INSERT INTO `t_system_region` VALUES ('4204', '130527', '南和县', '130527', '河北省邢台市南和县', 'hbsxtsnhx');
INSERT INTO `t_system_region` VALUES ('4205', '130528', '宁晋县', '130528', '河北省邢台市宁晋县', 'hbsxtsnjx');
INSERT INTO `t_system_region` VALUES ('4206', '130529', '巨鹿县', '130529', '河北省邢台市巨鹿县', 'hbsxtsjlx');
INSERT INTO `t_system_region` VALUES ('4207', '130530', '新河县', '130530', '河北省邢台市新河县', 'hbsxtsxhx');
INSERT INTO `t_system_region` VALUES ('4208', '130531', '广宗县', '130531', '河北省邢台市广宗县', 'hbsxtsgzx');
INSERT INTO `t_system_region` VALUES ('4209', '130532', '平乡县', '130532', '河北省邢台市平乡县', 'hbsxtspxx');
INSERT INTO `t_system_region` VALUES ('4210', '130533', '威县', '130533', '河北省邢台市威县', 'hbsxtswx');
INSERT INTO `t_system_region` VALUES ('4211', '130534', '清河县', '130534', '河北省邢台市清河县', 'hbsxtsqhx');
INSERT INTO `t_system_region` VALUES ('4212', '130535', '临西县', '130535', '河北省邢台市临西县', 'hbsxtslxx');
INSERT INTO `t_system_region` VALUES ('4213', '130536', '大曹庄管理区', '130536', '河北省邢台市大曹庄管理区', 'hbsxtsdczglq');
INSERT INTO `t_system_region` VALUES ('4214', '130581', '南宫市', '130581', '河北省邢台市南宫市', 'hbsxtsngs');
INSERT INTO `t_system_region` VALUES ('4215', '130582', '沙河市', '130582', '河北省邢台市沙河市', 'hbsxtsshs');
INSERT INTO `t_system_region` VALUES ('4216', '1306', '保定市', '130600', '河北省保定市', 'hbsbds');
INSERT INTO `t_system_region` VALUES ('4217', '130601', '市辖区', '130601', '河北省保定市市辖区', 'hbsbdssxq');
INSERT INTO `t_system_region` VALUES ('4218', '130602', '新市区', '130602', '河北省保定市新市区', 'hbsbdsxsq');
INSERT INTO `t_system_region` VALUES ('4219', '130603', '北市区', '130603', '河北省保定市北市区', 'hbsbdsbsq');
INSERT INTO `t_system_region` VALUES ('4220', '130604', '南市区', '130604', '河北省保定市南市区', 'hbsbdsnsq');
INSERT INTO `t_system_region` VALUES ('4221', '130621', '满城县', '130621', '河北省保定市满城县', 'hbsbdsmcx');
INSERT INTO `t_system_region` VALUES ('4222', '130622', '清苑县', '130622', '河北省保定市清苑县', 'hbsbdsqyx');
INSERT INTO `t_system_region` VALUES ('4223', '130623', '涞水县', '130623', '河北省保定市涞水县', 'hbsbdslsx');
INSERT INTO `t_system_region` VALUES ('4224', '130624', '阜平县', '130624', '河北省保定市阜平县', 'hbsbdsfpx');
INSERT INTO `t_system_region` VALUES ('4225', '130625', '徐水县', '130625', '河北省保定市徐水县', 'hbsbdsxsx');
INSERT INTO `t_system_region` VALUES ('4226', '130626', '定兴县', '130626', '河北省保定市定兴县', 'hbsbdsdxx');
INSERT INTO `t_system_region` VALUES ('4227', '130627', '唐县', '130627', '河北省保定市唐县', 'hbsbdstx');
INSERT INTO `t_system_region` VALUES ('4228', '130628', '高阳县', '130628', '河北省保定市高阳县', 'hbsbdsgyx');
INSERT INTO `t_system_region` VALUES ('4229', '130629', '容城县', '130629', '河北省保定市容城县', 'hbsbdsrcx');
INSERT INTO `t_system_region` VALUES ('4230', '130630', '涞源县', '130630', '河北省保定市涞源县', 'hbsbdslyx');
INSERT INTO `t_system_region` VALUES ('4231', '130631', '望都县', '130631', '河北省保定市望都县', 'hbsbdswdx');
INSERT INTO `t_system_region` VALUES ('4232', '130632', '安新县', '130632', '河北省保定市安新县', 'hbsbdsaxx');
INSERT INTO `t_system_region` VALUES ('4233', '130633', '易县', '130633', '河北省保定市易县', 'hbsbdsyx');
INSERT INTO `t_system_region` VALUES ('4234', '130634', '曲阳县', '130634', '河北省保定市曲阳县', 'hbsbdsqyx');
INSERT INTO `t_system_region` VALUES ('4235', '130635', '蠡县', '130635', '河北省保定市蠡县', 'hbsbdslx');
INSERT INTO `t_system_region` VALUES ('4236', '130636', '顺平县', '130636', '河北省保定市顺平县', 'hbsbdsspx');
INSERT INTO `t_system_region` VALUES ('4237', '130637', '博野县', '130637', '河北省保定市博野县', 'hbsbdsbyx');
INSERT INTO `t_system_region` VALUES ('4238', '130638', '雄县', '130638', '河北省保定市雄县', 'hbsbdsxx');
INSERT INTO `t_system_region` VALUES ('4239', '130681', '涿州市', '130681', '河北省保定市涿州市', 'hbsbdszzs');
INSERT INTO `t_system_region` VALUES ('4240', '130682', '定州市', '130682', '河北省保定市定州市', 'hbsbdsdzs');
INSERT INTO `t_system_region` VALUES ('4241', '130683', '安国市', '130683', '河北省保定市安国市', 'hbsbdsags');
INSERT INTO `t_system_region` VALUES ('4242', '130684', '高碑店市', '130684', '河北省保定市高碑店市', 'hbsbdsgbds');
INSERT INTO `t_system_region` VALUES ('4243', '130698', '高新区', '130698', '河北省保定市高新区', 'hbsbdsgxq');
INSERT INTO `t_system_region` VALUES ('4244', '130699', '保定市高新区', '130699', '河北省保定市保定市高新区', 'hbsbdsbdsgxq');
INSERT INTO `t_system_region` VALUES ('4245', '1307', '张家口市', '130700', '河北省张家口市', 'hbszjks');
INSERT INTO `t_system_region` VALUES ('4246', '130701', '市辖区', '130701', '河北省张家口市市辖区', 'hbszjkssxq');
INSERT INTO `t_system_region` VALUES ('4247', '130702', '桥东区', '130702', '河北省张家口市桥东区', 'hbszjksqdq');
INSERT INTO `t_system_region` VALUES ('4248', '130703', '桥西区', '130703', '河北省张家口市桥西区', 'hbszjksqxq');
INSERT INTO `t_system_region` VALUES ('4249', '130705', '宣化区', '130705', '河北省张家口市宣化区', 'hbszjksxhq');
INSERT INTO `t_system_region` VALUES ('4250', '130706', '下花园区', '130706', '河北省张家口市下花园区', 'hbszjksxhyq');
INSERT INTO `t_system_region` VALUES ('4251', '130707', '察北管理区', '130707', '河北省张家口市察北管理区', 'hbszjkscbglq');
INSERT INTO `t_system_region` VALUES ('4252', '130708', '塞北管理区', '130708', '河北省张家口市塞北管理区', 'hbszjkssbglq');
INSERT INTO `t_system_region` VALUES ('4253', '130709', '张家口市高新区', '130709', '河北省张家口市张家口市高新区', 'hbszjkszjksgxq');
INSERT INTO `t_system_region` VALUES ('4254', '130721', '宣化县', '130721', '河北省张家口市宣化县', 'hbszjksxhx');
INSERT INTO `t_system_region` VALUES ('4255', '130722', '张北县', '130722', '河北省张家口市张北县', 'hbszjkszbx');
INSERT INTO `t_system_region` VALUES ('4256', '130723', '康保县', '130723', '河北省张家口市康保县', 'hbszjkskbx');
INSERT INTO `t_system_region` VALUES ('4257', '130724', '沽源县', '130724', '河北省张家口市沽源县', 'hbszjksgyx');
INSERT INTO `t_system_region` VALUES ('4258', '130725', '尚义县', '130725', '河北省张家口市尚义县', 'hbszjkssyx');
INSERT INTO `t_system_region` VALUES ('4259', '130726', '蔚县', '130726', '河北省张家口市蔚县', 'hbszjksyx');
INSERT INTO `t_system_region` VALUES ('4260', '130727', '阳原县', '130727', '河北省张家口市阳原县', 'hbszjksyyx');
INSERT INTO `t_system_region` VALUES ('4261', '130728', '怀安县', '130728', '河北省张家口市怀安县', 'hbszjkshax');
INSERT INTO `t_system_region` VALUES ('4262', '130729', '万全县', '130729', '河北省张家口市万全县', 'hbszjkswqx');
INSERT INTO `t_system_region` VALUES ('4263', '130730', '怀来县', '130730', '河北省张家口市怀来县', 'hbszjkshlx');
INSERT INTO `t_system_region` VALUES ('4264', '130731', '涿鹿县', '130731', '河北省张家口市涿鹿县', 'hbszjkszlx');
INSERT INTO `t_system_region` VALUES ('4265', '130732', '赤城县', '130732', '河北省张家口市赤城县', 'hbszjksccx');
INSERT INTO `t_system_region` VALUES ('4266', '130733', '崇礼县', '130733', '河北省张家口市崇礼县', 'hbszjksclx');
INSERT INTO `t_system_region` VALUES ('4267', '1308', '承德市', '130800', '河北省承德市', 'hbscds');
INSERT INTO `t_system_region` VALUES ('4268', '130801', '市辖区', '130801', '河北省承德市市辖区', 'hbscdssxq');
INSERT INTO `t_system_region` VALUES ('4269', '130802', '双桥区', '130802', '河北省承德市双桥区', 'hbscdssqq');
INSERT INTO `t_system_region` VALUES ('4270', '130803', '双滦区', '130803', '河北省承德市双滦区', 'hbscdsslq');
INSERT INTO `t_system_region` VALUES ('4271', '130804', '鹰手营子矿区', '130804', '河北省承德市鹰手营子矿区', 'hbscdsysyzkq');
INSERT INTO `t_system_region` VALUES ('4272', '130805', '承德市开发区', '130805', '河北省承德市承德市开发区', 'hbscdscdskfq');
INSERT INTO `t_system_region` VALUES ('4273', '130821', '承德县', '130821', '河北省承德市承德县', 'hbscdscdx');
INSERT INTO `t_system_region` VALUES ('4274', '130822', '兴隆县', '130822', '河北省承德市兴隆县', 'hbscdsxlx');
INSERT INTO `t_system_region` VALUES ('4275', '130823', '平泉县', '130823', '河北省承德市平泉县', 'hbscdspqx');
INSERT INTO `t_system_region` VALUES ('4276', '130824', '滦平县', '130824', '河北省承德市滦平县', 'hbscdslpx');
INSERT INTO `t_system_region` VALUES ('4277', '130825', '隆化县', '130825', '河北省承德市隆化县', 'hbscdslhx');
INSERT INTO `t_system_region` VALUES ('4278', '130826', '丰宁满族自治县', '130826', '河北省承德市丰宁满族自治县', 'hbscdsfnmzzzx');
INSERT INTO `t_system_region` VALUES ('4279', '130827', '宽城满族自治县', '130827', '河北省承德市宽城满族自治县', 'hbscdskcmzzzx');
INSERT INTO `t_system_region` VALUES ('4280', '130828', '围场满族蒙古族自治县', '130828', '河北省承德市围场满族蒙古族自治县', 'hbscdswcmzmgzzzx');
INSERT INTO `t_system_region` VALUES ('4281', '1309', '沧州市', '130900', '河北省沧州市', 'hbsczs');
INSERT INTO `t_system_region` VALUES ('4282', '130901', '市辖区', '130901', '河北省沧州市市辖区', 'hbsczssxq');
INSERT INTO `t_system_region` VALUES ('4283', '130902', '新华区', '130902', '河北省沧州市新华区', 'hbsczsxhq');
INSERT INTO `t_system_region` VALUES ('4284', '130903', '运河区', '130903', '河北省沧州市运河区', 'hbsczsyhq');
INSERT INTO `t_system_region` VALUES ('4285', '130904', '临港区', '130904', '河北省沧州市临港区', 'hbsczslgq');
INSERT INTO `t_system_region` VALUES ('4286', '130921', '沧县', '130921', '河北省沧州市沧县', 'hbsczscx');
INSERT INTO `t_system_region` VALUES ('4287', '130922', '青县', '130922', '河北省沧州市青县', 'hbsczsqx');
INSERT INTO `t_system_region` VALUES ('4288', '130923', '东光县', '130923', '河北省沧州市东光县', 'hbsczsdgx');
INSERT INTO `t_system_region` VALUES ('4289', '130924', '海兴县', '130924', '河北省沧州市海兴县', 'hbsczshxx');
INSERT INTO `t_system_region` VALUES ('4290', '130925', '盐山县', '130925', '河北省沧州市盐山县', 'hbsczsysx');
INSERT INTO `t_system_region` VALUES ('4291', '130926', '肃宁县', '130926', '河北省沧州市肃宁县', 'hbsczssnx');
INSERT INTO `t_system_region` VALUES ('4292', '130927', '南皮县', '130927', '河北省沧州市南皮县', 'hbsczsnpx');
INSERT INTO `t_system_region` VALUES ('4293', '130928', '吴桥县', '130928', '河北省沧州市吴桥县', 'hbsczswqx');
INSERT INTO `t_system_region` VALUES ('4294', '130929', '献县', '130929', '河北省沧州市献县', 'hbsczsxx');
INSERT INTO `t_system_region` VALUES ('4295', '130930', '孟村回族自治县', '130930', '河北省沧州市孟村回族自治县', 'hbsczsmchzzzx');
INSERT INTO `t_system_region` VALUES ('4296', '130931', '华北油田', '130931', '河北省沧州市华北油田', 'hbsczshbyt');
INSERT INTO `t_system_region` VALUES ('4297', '130932', '沧州市南大港', '130932', '河北省沧州市沧州市南大港', 'hbsczsczsndg');
INSERT INTO `t_system_region` VALUES ('4298', '130934', '渤海新区黄骅港开发区', '130934', '河北省沧州市渤海新区黄骅港开发区', 'hbsczsbhxqhhgkfq');
INSERT INTO `t_system_region` VALUES ('4299', '130981', '泊头市', '130981', '河北省沧州市泊头市', 'hbsczsbts');
INSERT INTO `t_system_region` VALUES ('4300', '130982', '任丘市', '130982', '河北省沧州市任丘市', 'hbsczsrqs');
INSERT INTO `t_system_region` VALUES ('4301', '130983', '黄骅市', '130983', '河北省沧州市黄骅市', 'hbsczshhs');
INSERT INTO `t_system_region` VALUES ('4302', '130984', '河间市', '130984', '河北省沧州市河间市', 'hbsczshjs');
INSERT INTO `t_system_region` VALUES ('4303', '1310', '廊坊市', '131000', '河北省廊坊市', 'hbslfs');
INSERT INTO `t_system_region` VALUES ('4304', '131001', '市辖区', '131001', '河北省廊坊市市辖区', 'hbslfssxq');
INSERT INTO `t_system_region` VALUES ('4305', '131002', '安次区', '131002', '河北省廊坊市安次区', 'hbslfsacq');
INSERT INTO `t_system_region` VALUES ('4306', '131003', '广阳区', '131003', '河北省廊坊市广阳区', 'hbslfsgyq');
INSERT INTO `t_system_region` VALUES ('4307', '131005', '廊坊经济技术开发区', '131005', '河北省廊坊市廊坊经济技术开发区', 'hbslfslfjjjskfq');
INSERT INTO `t_system_region` VALUES ('4308', '131022', '固安县', '131022', '河北省廊坊市固安县', 'hbslfsgax');
INSERT INTO `t_system_region` VALUES ('4309', '131023', '永清县', '131023', '河北省廊坊市永清县', 'hbslfsyqx');
INSERT INTO `t_system_region` VALUES ('4310', '131024', '香河县', '131024', '河北省廊坊市香河县', 'hbslfsxhx');
INSERT INTO `t_system_region` VALUES ('4311', '131025', '大城县', '131025', '河北省廊坊市大城县', 'hbslfsdcx');
INSERT INTO `t_system_region` VALUES ('4312', '131026', '文安县', '131026', '河北省廊坊市文安县', 'hbslfswax');
INSERT INTO `t_system_region` VALUES ('4313', '131028', '大厂回族自治县', '131028', '河北省廊坊市大厂回族自治县', 'hbslfsdchzzzx');
INSERT INTO `t_system_region` VALUES ('4314', '131081', '霸州市', '131081', '河北省廊坊市霸州市', 'hbslfsbzs');
INSERT INTO `t_system_region` VALUES ('4315', '131082', '三河市', '131082', '河北省廊坊市三河市', 'hbslfsshs');
INSERT INTO `t_system_region` VALUES ('4316', '1311', '衡水市', '131100', '河北省衡水市', 'hbshss');
INSERT INTO `t_system_region` VALUES ('4317', '131101', '市辖区', '131101', '河北省衡水市市辖区', 'hbshsssxq');
INSERT INTO `t_system_region` VALUES ('4318', '131102', '桃城区', '131102', '河北省衡水市桃城区', 'hbshsstcq');
INSERT INTO `t_system_region` VALUES ('4319', '131105', '衡水市高新区', '131105', '河北省衡水市衡水市高新区', 'hbshsshssgxq');
INSERT INTO `t_system_region` VALUES ('4320', '131106', '衡水湖开发区', '131106', '河北省衡水市衡水湖开发区', 'hbshsshshkfq');
INSERT INTO `t_system_region` VALUES ('4321', '131121', '枣强县', '131121', '河北省衡水市枣强县', 'hbshsszqx');
INSERT INTO `t_system_region` VALUES ('4322', '131122', '武邑县', '131122', '河北省衡水市武邑县', 'hbshsswyx');
INSERT INTO `t_system_region` VALUES ('4323', '131123', '武强县', '131123', '河北省衡水市武强县', 'hbshsswqx');
INSERT INTO `t_system_region` VALUES ('4324', '131124', '饶阳县', '131124', '河北省衡水市饶阳县', 'hbshssryx');
INSERT INTO `t_system_region` VALUES ('4325', '131125', '安平县', '131125', '河北省衡水市安平县', 'hbshssapx');
INSERT INTO `t_system_region` VALUES ('4326', '131126', '故城县', '131126', '河北省衡水市故城县', 'hbshssgcx');
INSERT INTO `t_system_region` VALUES ('4327', '131127', '景县', '131127', '河北省衡水市景县', 'hbshssjx');
INSERT INTO `t_system_region` VALUES ('4328', '131128', '阜城县', '131128', '河北省衡水市阜城县', 'hbshssfcx');
INSERT INTO `t_system_region` VALUES ('4329', '131181', '冀州市', '131181', '河北省衡水市冀州市', 'hbshssjzs');
INSERT INTO `t_system_region` VALUES ('4330', '131182', '深州市', '131182', '河北省衡水市深州市', 'hbshssszs');
INSERT INTO `t_system_region` VALUES ('4331', '14', '山西省', '140000', '山西省', 'sxs');
INSERT INTO `t_system_region` VALUES ('4333', '1401', '太原市', '140100', '山西省太原市', 'sxstys');
INSERT INTO `t_system_region` VALUES ('4334', '140101', '市辖区', '140101', '山西省太原市市辖区', 'sxstyssxq');
INSERT INTO `t_system_region` VALUES ('4335', '140105', '小店区', '140105', '山西省太原市小店区', 'sxstysxdq');
INSERT INTO `t_system_region` VALUES ('4336', '140106', '迎泽区', '140106', '山西省太原市迎泽区', 'sxstysyzq');
INSERT INTO `t_system_region` VALUES ('4337', '140107', '杏花岭区', '140107', '山西省太原市杏花岭区', 'sxstysxhlq');
INSERT INTO `t_system_region` VALUES ('4338', '140108', '尖草坪区', '140108', '山西省太原市尖草坪区', 'sxstysjcpq');
INSERT INTO `t_system_region` VALUES ('4339', '140109', '万柏林区', '140109', '山西省太原市万柏林区', 'sxstyswblq');
INSERT INTO `t_system_region` VALUES ('4340', '140110', '晋源区', '140110', '山西省太原市晋源区', 'sxstysjyq');
INSERT INTO `t_system_region` VALUES ('4341', '140121', '清徐县', '140121', '山西省太原市清徐县', 'sxstysqxx');
INSERT INTO `t_system_region` VALUES ('4342', '140122', '阳曲县', '140122', '山西省太原市阳曲县', 'sxstysyqx');
INSERT INTO `t_system_region` VALUES ('4343', '140123', '娄烦县', '140123', '山西省太原市娄烦县', 'sxstyslfx');
INSERT INTO `t_system_region` VALUES ('4344', '140181', '古交市', '140181', '山西省太原市古交市', 'sxstysgjs');
INSERT INTO `t_system_region` VALUES ('4345', '1402', '大同市', '140200', '山西省大同市', 'sxsdts');
INSERT INTO `t_system_region` VALUES ('4346', '140201', '市辖区', '140201', '山西省大同市市辖区', 'sxsdtssxq');
INSERT INTO `t_system_region` VALUES ('4347', '140202', '城区', '140202', '山西省大同市城区', 'sxsdtscq');
INSERT INTO `t_system_region` VALUES ('4348', '140203', '矿区', '140203', '山西省大同市矿区', 'sxsdtskq');
INSERT INTO `t_system_region` VALUES ('4349', '140211', '南郊区', '140211', '山西省大同市南郊区', 'sxsdtsnjq');
INSERT INTO `t_system_region` VALUES ('4350', '140212', '新荣区', '140212', '山西省大同市新荣区', 'sxsdtsxrq');
INSERT INTO `t_system_region` VALUES ('4351', '140221', '阳高县', '140221', '山西省大同市阳高县', 'sxsdtsygx');
INSERT INTO `t_system_region` VALUES ('4352', '140222', '天镇县', '140222', '山西省大同市天镇县', 'sxsdtstzx');
INSERT INTO `t_system_region` VALUES ('4353', '140223', '广灵县', '140223', '山西省大同市广灵县', 'sxsdtsglx');
INSERT INTO `t_system_region` VALUES ('4354', '140224', '灵丘县', '140224', '山西省大同市灵丘县', 'sxsdtslqx');
INSERT INTO `t_system_region` VALUES ('4355', '140225', '浑源县', '140225', '山西省大同市浑源县', 'sxsdtshyx');
INSERT INTO `t_system_region` VALUES ('4356', '140226', '左云县', '140226', '山西省大同市左云县', 'sxsdtszyx');
INSERT INTO `t_system_region` VALUES ('4357', '140227', '大同县', '140227', '山西省大同市大同县', 'sxsdtsdtx');
INSERT INTO `t_system_region` VALUES ('4358', '1403', '阳泉市', '140300', '山西省阳泉市', 'sxsyqs');
INSERT INTO `t_system_region` VALUES ('4359', '140301', '市辖区', '140301', '山西省阳泉市市辖区', 'sxsyqssxq');
INSERT INTO `t_system_region` VALUES ('4360', '140302', '城区', '140302', '山西省阳泉市城区', 'sxsyqscq');
INSERT INTO `t_system_region` VALUES ('4361', '140303', '矿区', '140303', '山西省阳泉市矿区', 'sxsyqskq');
INSERT INTO `t_system_region` VALUES ('4362', '140311', '郊区', '140311', '山西省阳泉市郊区', 'sxsyqsjq');
INSERT INTO `t_system_region` VALUES ('4363', '140321', '平定县', '140321', '山西省阳泉市平定县', 'sxsyqspdx');
INSERT INTO `t_system_region` VALUES ('4364', '140322', '盂县', '140322', '山西省阳泉市盂县', 'sxsyqsyx');
INSERT INTO `t_system_region` VALUES ('4365', '1404', '长治市', '140400', '山西省长治市', 'sxszzs');
INSERT INTO `t_system_region` VALUES ('4366', '140401', '市辖区', '140401', '山西省长治市市辖区', 'sxszzssxq');
INSERT INTO `t_system_region` VALUES ('4367', '140402', '城区', '140402', '山西省长治市城区', 'sxszzscq');
INSERT INTO `t_system_region` VALUES ('4368', '140411', '郊区', '140411', '山西省长治市郊区', 'sxszzsjq');
INSERT INTO `t_system_region` VALUES ('4369', '140421', '长治县', '140421', '山西省长治市长治县', 'sxszzszzx');
INSERT INTO `t_system_region` VALUES ('4370', '140423', '襄垣县', '140423', '山西省长治市襄垣县', 'sxszzsxyx');
INSERT INTO `t_system_region` VALUES ('4371', '140424', '屯留县', '140424', '山西省长治市屯留县', 'sxszzstlx');
INSERT INTO `t_system_region` VALUES ('4372', '140425', '平顺县', '140425', '山西省长治市平顺县', 'sxszzspsx');
INSERT INTO `t_system_region` VALUES ('4373', '140426', '黎城县', '140426', '山西省长治市黎城县', 'sxszzslcx');
INSERT INTO `t_system_region` VALUES ('4374', '140427', '壶关县', '140427', '山西省长治市壶关县', 'sxszzshgx');
INSERT INTO `t_system_region` VALUES ('4375', '140428', '长子县', '140428', '山西省长治市长子县', 'sxszzszzx');
INSERT INTO `t_system_region` VALUES ('4376', '140429', '武乡县', '140429', '山西省长治市武乡县', 'sxszzswxx');
INSERT INTO `t_system_region` VALUES ('4377', '140430', '沁县', '140430', '山西省长治市沁县', 'sxszzsqx');
INSERT INTO `t_system_region` VALUES ('4378', '140431', '沁源县', '140431', '山西省长治市沁源县', 'sxszzsqyx');
INSERT INTO `t_system_region` VALUES ('4379', '140481', '潞城市', '140481', '山西省长治市潞城市', 'sxszzslcs');
INSERT INTO `t_system_region` VALUES ('4380', '1405', '晋城市', '140500', '山西省晋城市', 'sxsjcs');
INSERT INTO `t_system_region` VALUES ('4381', '140501', '市辖区', '140501', '山西省晋城市市辖区', 'sxsjcssxq');
INSERT INTO `t_system_region` VALUES ('4382', '140502', '城区', '140502', '山西省晋城市城区', 'sxsjcscq');
INSERT INTO `t_system_region` VALUES ('4383', '140521', '沁水县', '140521', '山西省晋城市沁水县', 'sxsjcsqsx');
INSERT INTO `t_system_region` VALUES ('4384', '140522', '阳城县', '140522', '山西省晋城市阳城县', 'sxsjcsycx');
INSERT INTO `t_system_region` VALUES ('4385', '140524', '陵川县', '140524', '山西省晋城市陵川县', 'sxsjcslcx');
INSERT INTO `t_system_region` VALUES ('4386', '140525', '泽州县', '140525', '山西省晋城市泽州县', 'sxsjcszzx');
INSERT INTO `t_system_region` VALUES ('4387', '140581', '高平市', '140581', '山西省晋城市高平市', 'sxsjcsgps');
INSERT INTO `t_system_region` VALUES ('4388', '1406', '朔州市', '140600', '山西省朔州市', 'sxsszs');
INSERT INTO `t_system_region` VALUES ('4389', '140601', '市辖区', '140601', '山西省朔州市市辖区', 'sxsszssxq');
INSERT INTO `t_system_region` VALUES ('4390', '140602', '朔城区', '140602', '山西省朔州市朔城区', 'sxsszsscq');
INSERT INTO `t_system_region` VALUES ('4391', '140603', '平鲁区', '140603', '山西省朔州市平鲁区', 'sxsszsplq');
INSERT INTO `t_system_region` VALUES ('4392', '140621', '山阴县', '140621', '山西省朔州市山阴县', 'sxsszssyx');
INSERT INTO `t_system_region` VALUES ('4393', '140622', '应县', '140622', '山西省朔州市应县', 'sxsszsyx');
INSERT INTO `t_system_region` VALUES ('4394', '140623', '右玉县', '140623', '山西省朔州市右玉县', 'sxsszsyyx');
INSERT INTO `t_system_region` VALUES ('4395', '140624', '怀仁县', '140624', '山西省朔州市怀仁县', 'sxsszshrx');
INSERT INTO `t_system_region` VALUES ('4396', '1407', '晋中市', '140700', '山西省晋中市', 'sxsjzs');
INSERT INTO `t_system_region` VALUES ('4397', '140701', '市辖区', '140701', '山西省晋中市市辖区', 'sxsjzssxq');
INSERT INTO `t_system_region` VALUES ('4398', '140702', '榆次区', '140702', '山西省晋中市榆次区', 'sxsjzsycq');
INSERT INTO `t_system_region` VALUES ('4399', '140721', '榆社县', '140721', '山西省晋中市榆社县', 'sxsjzsysx');
INSERT INTO `t_system_region` VALUES ('4400', '140722', '左权县', '140722', '山西省晋中市左权县', 'sxsjzszqx');
INSERT INTO `t_system_region` VALUES ('4401', '140723', '和顺县', '140723', '山西省晋中市和顺县', 'sxsjzshsx');
INSERT INTO `t_system_region` VALUES ('4402', '140724', '昔阳县', '140724', '山西省晋中市昔阳县', 'sxsjzsxyx');
INSERT INTO `t_system_region` VALUES ('4403', '140725', '寿阳县', '140725', '山西省晋中市寿阳县', 'sxsjzssyx');
INSERT INTO `t_system_region` VALUES ('4404', '140726', '太谷县', '140726', '山西省晋中市太谷县', 'sxsjzstgx');
INSERT INTO `t_system_region` VALUES ('4405', '140727', '祁县', '140727', '山西省晋中市祁县', 'sxsjzsqx');
INSERT INTO `t_system_region` VALUES ('4406', '140728', '平遥县', '140728', '山西省晋中市平遥县', 'sxsjzspyx');
INSERT INTO `t_system_region` VALUES ('4407', '140729', '灵石县', '140729', '山西省晋中市灵石县', 'sxsjzslsx');
INSERT INTO `t_system_region` VALUES ('4408', '140781', '介休市', '140781', '山西省晋中市介休市', 'sxsjzsjxs');
INSERT INTO `t_system_region` VALUES ('4409', '1408', '运城市', '140800', '山西省运城市', 'sxsycs');
INSERT INTO `t_system_region` VALUES ('4410', '140801', '市辖区', '140801', '山西省运城市市辖区', 'sxsycssxq');
INSERT INTO `t_system_region` VALUES ('4411', '140802', '盐湖区', '140802', '山西省运城市盐湖区', 'sxsycsyhq');
INSERT INTO `t_system_region` VALUES ('4412', '140821', '临猗县', '140821', '山西省运城市临猗县', 'sxsycslyx');
INSERT INTO `t_system_region` VALUES ('4413', '140822', '万荣县', '140822', '山西省运城市万荣县', 'sxsycswrx');
INSERT INTO `t_system_region` VALUES ('4414', '140823', '闻喜县', '140823', '山西省运城市闻喜县', 'sxsycswxx');
INSERT INTO `t_system_region` VALUES ('4415', '140824', '稷山县', '140824', '山西省运城市稷山县', 'sxsycsjsx');
INSERT INTO `t_system_region` VALUES ('4416', '140825', '新绛县', '140825', '山西省运城市新绛县', 'sxsycsxjx');
INSERT INTO `t_system_region` VALUES ('4417', '140826', '绛县', '140826', '山西省运城市绛县', 'sxsycsjx');
INSERT INTO `t_system_region` VALUES ('4418', '140827', '垣曲县', '140827', '山西省运城市垣曲县', 'sxsycsyqx');
INSERT INTO `t_system_region` VALUES ('4419', '140828', '夏县', '140828', '山西省运城市夏县', 'sxsycsxx');
INSERT INTO `t_system_region` VALUES ('4420', '140829', '平陆县', '140829', '山西省运城市平陆县', 'sxsycsplx');
INSERT INTO `t_system_region` VALUES ('4421', '140830', '芮城县', '140830', '山西省运城市芮城县', 'sxsycsrcx');
INSERT INTO `t_system_region` VALUES ('4422', '140881', '永济市', '140881', '山西省运城市永济市', 'sxsycsyjs');
INSERT INTO `t_system_region` VALUES ('4423', '140882', '河津市', '140882', '山西省运城市河津市', 'sxsycshjs');
INSERT INTO `t_system_region` VALUES ('4424', '1409', '忻州市', '140900', '山西省忻州市', 'sxsxzs');
INSERT INTO `t_system_region` VALUES ('4425', '140901', '市辖区', '140901', '山西省忻州市市辖区', 'sxsxzssxq');
INSERT INTO `t_system_region` VALUES ('4426', '140902', '忻府区', '140902', '山西省忻州市忻府区', 'sxsxzsxfq');
INSERT INTO `t_system_region` VALUES ('4427', '140921', '定襄县', '140921', '山西省忻州市定襄县', 'sxsxzsdxx');
INSERT INTO `t_system_region` VALUES ('4428', '140922', '五台县', '140922', '山西省忻州市五台县', 'sxsxzswtx');
INSERT INTO `t_system_region` VALUES ('4429', '140923', '代县', '140923', '山西省忻州市代县', 'sxsxzsdx');
INSERT INTO `t_system_region` VALUES ('4430', '140924', '繁峙县', '140924', '山西省忻州市繁峙县', 'sxsxzsfzx');
INSERT INTO `t_system_region` VALUES ('4431', '140925', '宁武县', '140925', '山西省忻州市宁武县', 'sxsxzsnwx');
INSERT INTO `t_system_region` VALUES ('4432', '140926', '静乐县', '140926', '山西省忻州市静乐县', 'sxsxzsjlx');
INSERT INTO `t_system_region` VALUES ('4433', '140927', '神池县', '140927', '山西省忻州市神池县', 'sxsxzsscx');
INSERT INTO `t_system_region` VALUES ('4434', '140928', '五寨县', '140928', '山西省忻州市五寨县', 'sxsxzswzx');
INSERT INTO `t_system_region` VALUES ('4435', '140929', '岢岚县', '140929', '山西省忻州市岢岚县', 'sxsxzsklx');
INSERT INTO `t_system_region` VALUES ('4436', '140930', '河曲县', '140930', '山西省忻州市河曲县', 'sxsxzshqx');
INSERT INTO `t_system_region` VALUES ('4437', '140931', '保德县', '140931', '山西省忻州市保德县', 'sxsxzsbdx');
INSERT INTO `t_system_region` VALUES ('4438', '140932', '偏关县', '140932', '山西省忻州市偏关县', 'sxsxzspgx');
INSERT INTO `t_system_region` VALUES ('4439', '140981', '原平市', '140981', '山西省忻州市原平市', 'sxsxzsyps');
INSERT INTO `t_system_region` VALUES ('4440', '1410', '临汾市', '141000', '山西省临汾市', 'sxslfs');
INSERT INTO `t_system_region` VALUES ('4441', '141001', '市辖区', '141001', '山西省临汾市市辖区', 'sxslfssxq');
INSERT INTO `t_system_region` VALUES ('4442', '141002', '尧都区', '141002', '山西省临汾市尧都区', 'sxslfsydq');
INSERT INTO `t_system_region` VALUES ('4443', '141021', '曲沃县', '141021', '山西省临汾市曲沃县', 'sxslfsqwx');
INSERT INTO `t_system_region` VALUES ('4444', '141022', '翼城县', '141022', '山西省临汾市翼城县', 'sxslfsycx');
INSERT INTO `t_system_region` VALUES ('4445', '141023', '襄汾县', '141023', '山西省临汾市襄汾县', 'sxslfsxfx');
INSERT INTO `t_system_region` VALUES ('4446', '141024', '洪洞县', '141024', '山西省临汾市洪洞县', 'sxslfshdx');
INSERT INTO `t_system_region` VALUES ('4447', '141025', '古县', '141025', '山西省临汾市古县', 'sxslfsgx');
INSERT INTO `t_system_region` VALUES ('4448', '141026', '安泽县', '141026', '山西省临汾市安泽县', 'sxslfsazx');
INSERT INTO `t_system_region` VALUES ('4449', '141027', '浮山县', '141027', '山西省临汾市浮山县', 'sxslfsfsx');
INSERT INTO `t_system_region` VALUES ('4450', '141028', '吉县', '141028', '山西省临汾市吉县', 'sxslfsjx');
INSERT INTO `t_system_region` VALUES ('4451', '141029', '乡宁县', '141029', '山西省临汾市乡宁县', 'sxslfsxnx');
INSERT INTO `t_system_region` VALUES ('4452', '141030', '大宁县', '141030', '山西省临汾市大宁县', 'sxslfsdnx');
INSERT INTO `t_system_region` VALUES ('4453', '141031', '隰县', '141031', '山西省临汾市隰县', 'sxslfsxx');
INSERT INTO `t_system_region` VALUES ('4454', '141032', '永和县', '141032', '山西省临汾市永和县', 'sxslfsyhx');
INSERT INTO `t_system_region` VALUES ('4455', '141033', '蒲县', '141033', '山西省临汾市蒲县', 'sxslfspx');
INSERT INTO `t_system_region` VALUES ('4456', '141034', '汾西县', '141034', '山西省临汾市汾西县', 'sxslfsfxx');
INSERT INTO `t_system_region` VALUES ('4457', '141081', '侯马市', '141081', '山西省临汾市侯马市', 'sxslfshms');
INSERT INTO `t_system_region` VALUES ('4458', '141082', '霍州市', '141082', '山西省临汾市霍州市', 'sxslfshzs');
INSERT INTO `t_system_region` VALUES ('4459', '1411', '吕梁市', '141100', '山西省吕梁市', 'sxslls');
INSERT INTO `t_system_region` VALUES ('4460', '141101', '市辖区', '141101', '山西省吕梁市市辖区', 'sxsllssxq');
INSERT INTO `t_system_region` VALUES ('4461', '141102', '离石区', '141102', '山西省吕梁市离石区', 'sxsllslsq');
INSERT INTO `t_system_region` VALUES ('4462', '141121', '文水县', '141121', '山西省吕梁市文水县', 'sxsllswsx');
INSERT INTO `t_system_region` VALUES ('4463', '141122', '交城县', '141122', '山西省吕梁市交城县', 'sxsllsjcx');
INSERT INTO `t_system_region` VALUES ('4464', '141123', '兴县', '141123', '山西省吕梁市兴县', 'sxsllsxx');
INSERT INTO `t_system_region` VALUES ('4465', '141124', '临县', '141124', '山西省吕梁市临县', 'sxsllslx');
INSERT INTO `t_system_region` VALUES ('4466', '141125', '柳林县', '141125', '山西省吕梁市柳林县', 'sxsllsllx');
INSERT INTO `t_system_region` VALUES ('4467', '141126', '石楼县', '141126', '山西省吕梁市石楼县', 'sxsllsslx');
INSERT INTO `t_system_region` VALUES ('4468', '141127', '岚县', '141127', '山西省吕梁市岚县', 'sxsllslx');
INSERT INTO `t_system_region` VALUES ('4469', '141128', '方山县', '141128', '山西省吕梁市方山县', 'sxsllsfsx');
INSERT INTO `t_system_region` VALUES ('4470', '141129', '中阳县', '141129', '山西省吕梁市中阳县', 'sxsllszyx');
INSERT INTO `t_system_region` VALUES ('4471', '141130', '交口县', '141130', '山西省吕梁市交口县', 'sxsllsjkx');
INSERT INTO `t_system_region` VALUES ('4472', '141181', '孝义市', '141181', '山西省吕梁市孝义市', 'sxsllsxys');
INSERT INTO `t_system_region` VALUES ('4473', '141182', '汾阳市', '141182', '山西省吕梁市汾阳市', 'sxsllsfys');
INSERT INTO `t_system_region` VALUES ('4474', '15', '内蒙古自治区', '150000', '内蒙古自治区', 'nmgzzq');
INSERT INTO `t_system_region` VALUES ('4475', '1501', '呼和浩特市', '150100', '内蒙古自治区呼和浩特市', 'nmgzzqhhhts');
INSERT INTO `t_system_region` VALUES ('4476', '150101', '市辖区', '150101', '内蒙古自治区呼和浩特市市辖区', 'nmgzzqhhhtssxq');
INSERT INTO `t_system_region` VALUES ('4477', '150102', '新城区', '150102', '内蒙古自治区呼和浩特市新城区', 'nmgzzqhhhtsxcq');
INSERT INTO `t_system_region` VALUES ('4478', '150103', '回民区', '150103', '内蒙古自治区呼和浩特市回民区', 'nmgzzqhhhtshmq');
INSERT INTO `t_system_region` VALUES ('4479', '150104', '玉泉区', '150104', '内蒙古自治区呼和浩特市玉泉区', 'nmgzzqhhhtsyqq');
INSERT INTO `t_system_region` VALUES ('4480', '150105', '赛罕区', '150105', '内蒙古自治区呼和浩特市赛罕区', 'nmgzzqhhhtsshq');
INSERT INTO `t_system_region` VALUES ('4481', '150121', '土默特左旗', '150121', '内蒙古自治区呼和浩特市土默特左旗', 'nmgzzqhhhtstmtzq');
INSERT INTO `t_system_region` VALUES ('4482', '150122', '托克托县', '150122', '内蒙古自治区呼和浩特市托克托县', 'nmgzzqhhhtstktx');
INSERT INTO `t_system_region` VALUES ('4483', '150123', '和林格尔县', '150123', '内蒙古自治区呼和浩特市和林格尔县', 'nmgzzqhhhtshlgex');
INSERT INTO `t_system_region` VALUES ('4484', '150124', '清水河县', '150124', '内蒙古自治区呼和浩特市清水河县', 'nmgzzqhhhtsqshx');
INSERT INTO `t_system_region` VALUES ('4485', '150125', '武川县', '150125', '内蒙古自治区呼和浩特市武川县', 'nmgzzqhhhtswcx');
INSERT INTO `t_system_region` VALUES ('4486', '1502', '包头市', '150200', '内蒙古自治区包头市', 'nmgzzqbts');
INSERT INTO `t_system_region` VALUES ('4487', '150201', '市辖区', '150201', '内蒙古自治区包头市市辖区', 'nmgzzqbtssxq');
INSERT INTO `t_system_region` VALUES ('4488', '150202', '东河区', '150202', '内蒙古自治区包头市东河区', 'nmgzzqbtsdhq');
INSERT INTO `t_system_region` VALUES ('4489', '150203', '昆都仑区', '150203', '内蒙古自治区包头市昆都仑区', 'nmgzzqbtskdlq');
INSERT INTO `t_system_region` VALUES ('4490', '150204', '青山区', '150204', '内蒙古自治区包头市青山区', 'nmgzzqbtsqsq');
INSERT INTO `t_system_region` VALUES ('4491', '150205', '石拐区', '150205', '内蒙古自治区包头市石拐区', 'nmgzzqbtssgq');
INSERT INTO `t_system_region` VALUES ('4492', '150206', '白云矿区', '150206', '内蒙古自治区包头市白云矿区', 'nmgzzqbtsbykq');
INSERT INTO `t_system_region` VALUES ('4493', '150207', '九原区', '150207', '内蒙古自治区包头市九原区', 'nmgzzqbtsjyq');
INSERT INTO `t_system_region` VALUES ('4494', '150221', '土默特右旗', '150221', '内蒙古自治区包头市土默特右旗', 'nmgzzqbtstmtyq');
INSERT INTO `t_system_region` VALUES ('4495', '150222', '固阳县', '150222', '内蒙古自治区包头市固阳县', 'nmgzzqbtsgyx');
INSERT INTO `t_system_region` VALUES ('4496', '150223', '达尔罕茂明安联合旗', '150223', '内蒙古自治区包头市达尔罕茂明安联合旗', 'nmgzzqbtsdehmmalhq');
INSERT INTO `t_system_region` VALUES ('4497', '1503', '乌海市', '150300', '内蒙古自治区乌海市', 'nmgzzqwhs');
INSERT INTO `t_system_region` VALUES ('4498', '150301', '市辖区', '150301', '内蒙古自治区乌海市市辖区', 'nmgzzqwhssxq');
INSERT INTO `t_system_region` VALUES ('4499', '150302', '海勃湾区', '150302', '内蒙古自治区乌海市海勃湾区', 'nmgzzqwhshbwq');
INSERT INTO `t_system_region` VALUES ('4500', '150303', '海南区', '150303', '内蒙古自治区乌海市海南区', 'nmgzzqwhshnq');
INSERT INTO `t_system_region` VALUES ('4501', '150304', '乌达区', '150304', '内蒙古自治区乌海市乌达区', 'nmgzzqwhswdq');
INSERT INTO `t_system_region` VALUES ('4502', '1504', '赤峰市', '150400', '内蒙古自治区赤峰市', 'nmgzzqcfs');
INSERT INTO `t_system_region` VALUES ('4503', '150401', '市辖区', '150401', '内蒙古自治区赤峰市市辖区', 'nmgzzqcfssxq');
INSERT INTO `t_system_region` VALUES ('4504', '150402', '红山区', '150402', '内蒙古自治区赤峰市红山区', 'nmgzzqcfshsq');
INSERT INTO `t_system_region` VALUES ('4505', '150403', '元宝山区', '150403', '内蒙古自治区赤峰市元宝山区', 'nmgzzqcfsybsq');
INSERT INTO `t_system_region` VALUES ('4506', '150404', '松山区', '150404', '内蒙古自治区赤峰市松山区', 'nmgzzqcfsssq');
INSERT INTO `t_system_region` VALUES ('4507', '150421', '阿鲁科尔沁旗', '150421', '内蒙古自治区赤峰市阿鲁科尔沁旗', 'nmgzzqcfsalkeqq');
INSERT INTO `t_system_region` VALUES ('4508', '150422', '巴林左旗', '150422', '内蒙古自治区赤峰市巴林左旗', 'nmgzzqcfsblzq');
INSERT INTO `t_system_region` VALUES ('4509', '150423', '巴林右旗', '150423', '内蒙古自治区赤峰市巴林右旗', 'nmgzzqcfsblyq');
INSERT INTO `t_system_region` VALUES ('4510', '150424', '林西县', '150424', '内蒙古自治区赤峰市林西县', 'nmgzzqcfslxx');
INSERT INTO `t_system_region` VALUES ('4511', '150425', '克什克腾旗', '150425', '内蒙古自治区赤峰市克什克腾旗', 'nmgzzqcfsksktq');
INSERT INTO `t_system_region` VALUES ('4512', '150426', '翁牛特旗', '150426', '内蒙古自治区赤峰市翁牛特旗', 'nmgzzqcfswntq');
INSERT INTO `t_system_region` VALUES ('4513', '150428', '喀喇沁旗', '150428', '内蒙古自治区赤峰市喀喇沁旗', 'nmgzzqcfsklqq');
INSERT INTO `t_system_region` VALUES ('4514', '150429', '宁城县', '150429', '内蒙古自治区赤峰市宁城县', 'nmgzzqcfsncx');
INSERT INTO `t_system_region` VALUES ('4515', '150430', '敖汉旗', '150430', '内蒙古自治区赤峰市敖汉旗', 'nmgzzqcfsahq');
INSERT INTO `t_system_region` VALUES ('4516', '1505', '通辽市', '150500', '内蒙古自治区通辽市', 'nmgzzqtls');
INSERT INTO `t_system_region` VALUES ('4517', '150501', '市辖区', '150501', '内蒙古自治区通辽市市辖区', 'nmgzzqtlssxq');
INSERT INTO `t_system_region` VALUES ('4518', '150502', '科尔沁区', '150502', '内蒙古自治区通辽市科尔沁区', 'nmgzzqtlskeqq');
INSERT INTO `t_system_region` VALUES ('4519', '150521', '科尔沁左翼中旗', '150521', '内蒙古自治区通辽市科尔沁左翼中旗', 'nmgzzqtlskeqzyzq');
INSERT INTO `t_system_region` VALUES ('4520', '150522', '科尔沁左翼后旗', '150522', '内蒙古自治区通辽市科尔沁左翼后旗', 'nmgzzqtlskeqzyhq');
INSERT INTO `t_system_region` VALUES ('4521', '150523', '开鲁县', '150523', '内蒙古自治区通辽市开鲁县', 'nmgzzqtlsklx');
INSERT INTO `t_system_region` VALUES ('4522', '150524', '库伦旗', '150524', '内蒙古自治区通辽市库伦旗', 'nmgzzqtlsklq');
INSERT INTO `t_system_region` VALUES ('4523', '150525', '奈曼旗', '150525', '内蒙古自治区通辽市奈曼旗', 'nmgzzqtlsnmq');
INSERT INTO `t_system_region` VALUES ('4524', '150526', '扎鲁特旗', '150526', '内蒙古自治区通辽市扎鲁特旗', 'nmgzzqtlszltq');
INSERT INTO `t_system_region` VALUES ('4525', '150581', '霍林郭勒市', '150581', '内蒙古自治区通辽市霍林郭勒市', 'nmgzzqtlshlgls');
INSERT INTO `t_system_region` VALUES ('4526', '1506', '鄂尔多斯市', '150600', '内蒙古自治区鄂尔多斯市', 'nmgzzqeedss');
INSERT INTO `t_system_region` VALUES ('4527', '150601', '市辖区', '150601', '内蒙古自治区鄂尔多斯市市辖区', 'nmgzzqeedsssxq');
INSERT INTO `t_system_region` VALUES ('4528', '150602', '东胜区', '150602', '内蒙古自治区鄂尔多斯市东胜区', 'nmgzzqeedssdsq');
INSERT INTO `t_system_region` VALUES ('4529', '150621', '达拉特旗', '150621', '内蒙古自治区鄂尔多斯市达拉特旗', 'nmgzzqeedssdltq');
INSERT INTO `t_system_region` VALUES ('4530', '150622', '准格尔旗', '150622', '内蒙古自治区鄂尔多斯市准格尔旗', 'nmgzzqeedsszgeq');
INSERT INTO `t_system_region` VALUES ('4531', '150623', '鄂托克前旗', '150623', '内蒙古自治区鄂尔多斯市鄂托克前旗', 'nmgzzqeedssetkqq');
INSERT INTO `t_system_region` VALUES ('4532', '150624', '鄂托克旗', '150624', '内蒙古自治区鄂尔多斯市鄂托克旗', 'nmgzzqeedssetkq');
INSERT INTO `t_system_region` VALUES ('4533', '150625', '杭锦旗', '150625', '内蒙古自治区鄂尔多斯市杭锦旗', 'nmgzzqeedsshjq');
INSERT INTO `t_system_region` VALUES ('4534', '150626', '乌审旗', '150626', '内蒙古自治区鄂尔多斯市乌审旗', 'nmgzzqeedsswsq');
INSERT INTO `t_system_region` VALUES ('4535', '150627', '伊金霍洛旗', '150627', '内蒙古自治区鄂尔多斯市伊金霍洛旗', 'nmgzzqeedssyjhlq');
INSERT INTO `t_system_region` VALUES ('4536', '1507', '呼伦贝尔市', '150700', '内蒙古自治区呼伦贝尔市', 'nmgzzqhlbes');
INSERT INTO `t_system_region` VALUES ('4537', '150701', '市辖区', '150701', '内蒙古自治区呼伦贝尔市市辖区', 'nmgzzqhlbessxq');
INSERT INTO `t_system_region` VALUES ('4538', '150702', '海拉尔区', '150702', '内蒙古自治区呼伦贝尔市海拉尔区', 'nmgzzqhlbeshleq');
INSERT INTO `t_system_region` VALUES ('4539', '150721', '阿荣旗', '150721', '内蒙古自治区呼伦贝尔市阿荣旗', 'nmgzzqhlbesarq');
INSERT INTO `t_system_region` VALUES ('4540', '150722', '莫力达瓦达斡尔族自治旗', '150722', '内蒙古自治区呼伦贝尔市莫力达瓦达斡尔族自治旗', 'nmgzzqhlbesmldwdwezzzq');
INSERT INTO `t_system_region` VALUES ('4541', '150723', '鄂伦春自治旗', '150723', '内蒙古自治区呼伦贝尔市鄂伦春自治旗', 'nmgzzqhlbeselczzq');
INSERT INTO `t_system_region` VALUES ('4542', '150724', '鄂温克族自治旗', '150724', '内蒙古自治区呼伦贝尔市鄂温克族自治旗', 'nmgzzqhlbesewkzzzq');
INSERT INTO `t_system_region` VALUES ('4543', '150725', '陈巴尔虎旗', '150725', '内蒙古自治区呼伦贝尔市陈巴尔虎旗', 'nmgzzqhlbescbehq');
INSERT INTO `t_system_region` VALUES ('4544', '150726', '新巴尔虎左旗', '150726', '内蒙古自治区呼伦贝尔市新巴尔虎左旗', 'nmgzzqhlbesxbehzq');
INSERT INTO `t_system_region` VALUES ('4545', '150727', '新巴尔虎右旗', '150727', '内蒙古自治区呼伦贝尔市新巴尔虎右旗', 'nmgzzqhlbesxbehyq');
INSERT INTO `t_system_region` VALUES ('4546', '150781', '满洲里市', '150781', '内蒙古自治区呼伦贝尔市满洲里市', 'nmgzzqhlbesmzls');
INSERT INTO `t_system_region` VALUES ('4547', '150782', '牙克石市', '150782', '内蒙古自治区呼伦贝尔市牙克石市', 'nmgzzqhlbesykss');
INSERT INTO `t_system_region` VALUES ('4548', '150783', '扎兰屯市', '150783', '内蒙古自治区呼伦贝尔市扎兰屯市', 'nmgzzqhlbeszlts');
INSERT INTO `t_system_region` VALUES ('4549', '150784', '额尔古纳市', '150784', '内蒙古自治区呼伦贝尔市额尔古纳市', 'nmgzzqhlbeseegns');
INSERT INTO `t_system_region` VALUES ('4550', '150785', '根河市', '150785', '内蒙古自治区呼伦贝尔市根河市', 'nmgzzqhlbesghs');
INSERT INTO `t_system_region` VALUES ('4551', '1508', '巴彦淖尔市', '150800', '内蒙古自治区巴彦淖尔市', 'nmgzzqbynes');
INSERT INTO `t_system_region` VALUES ('4552', '150801', '市辖区', '150801', '内蒙古自治区巴彦淖尔市市辖区', 'nmgzzqbynessxq');
INSERT INTO `t_system_region` VALUES ('4553', '150802', '临河区', '150802', '内蒙古自治区巴彦淖尔市临河区', 'nmgzzqbyneslhq');
INSERT INTO `t_system_region` VALUES ('4554', '150821', '五原县', '150821', '内蒙古自治区巴彦淖尔市五原县', 'nmgzzqbyneswyx');
INSERT INTO `t_system_region` VALUES ('4555', '150822', '磴口县', '150822', '内蒙古自治区巴彦淖尔市磴口县', 'nmgzzqbynesdkx');
INSERT INTO `t_system_region` VALUES ('4556', '150823', '乌拉特前旗', '150823', '内蒙古自治区巴彦淖尔市乌拉特前旗', 'nmgzzqbyneswltqq');
INSERT INTO `t_system_region` VALUES ('4557', '150824', '乌拉特中旗', '150824', '内蒙古自治区巴彦淖尔市乌拉特中旗', 'nmgzzqbyneswltzq');
INSERT INTO `t_system_region` VALUES ('4558', '150825', '乌拉特后旗', '150825', '内蒙古自治区巴彦淖尔市乌拉特后旗', 'nmgzzqbyneswlthq');
INSERT INTO `t_system_region` VALUES ('4559', '150826', '杭锦后旗', '150826', '内蒙古自治区巴彦淖尔市杭锦后旗', 'nmgzzqbyneshjhq');
INSERT INTO `t_system_region` VALUES ('4560', '1509', '乌兰察布市', '150900', '内蒙古自治区乌兰察布市', 'nmgzzqwlcbs');
INSERT INTO `t_system_region` VALUES ('4561', '150901', '市辖区', '150901', '内蒙古自治区乌兰察布市市辖区', 'nmgzzqwlcbssxq');
INSERT INTO `t_system_region` VALUES ('4562', '150902', '集宁区', '150902', '内蒙古自治区乌兰察布市集宁区', 'nmgzzqwlcbsjnq');
INSERT INTO `t_system_region` VALUES ('4563', '150921', '卓资县', '150921', '内蒙古自治区乌兰察布市卓资县', 'nmgzzqwlcbszzx');
INSERT INTO `t_system_region` VALUES ('4564', '150922', '化德县', '150922', '内蒙古自治区乌兰察布市化德县', 'nmgzzqwlcbshdx');
INSERT INTO `t_system_region` VALUES ('4565', '150923', '商都县', '150923', '内蒙古自治区乌兰察布市商都县', 'nmgzzqwlcbssdx');
INSERT INTO `t_system_region` VALUES ('4566', '150924', '兴和县', '150924', '内蒙古自治区乌兰察布市兴和县', 'nmgzzqwlcbsxhx');
INSERT INTO `t_system_region` VALUES ('4567', '150925', '凉城县', '150925', '内蒙古自治区乌兰察布市凉城县', 'nmgzzqwlcbslcx');
INSERT INTO `t_system_region` VALUES ('4568', '150926', '察哈尔右翼前旗', '150926', '内蒙古自治区乌兰察布市察哈尔右翼前旗', 'nmgzzqwlcbscheyyqq');
INSERT INTO `t_system_region` VALUES ('4569', '150927', '察哈尔右翼中旗', '150927', '内蒙古自治区乌兰察布市察哈尔右翼中旗', 'nmgzzqwlcbscheyyzq');
INSERT INTO `t_system_region` VALUES ('4570', '150928', '察哈尔右翼后旗', '150928', '内蒙古自治区乌兰察布市察哈尔右翼后旗', 'nmgzzqwlcbscheyyhq');
INSERT INTO `t_system_region` VALUES ('4571', '150929', '四子王旗', '150929', '内蒙古自治区乌兰察布市四子王旗', 'nmgzzqwlcbsszwq');
INSERT INTO `t_system_region` VALUES ('4572', '150981', '丰镇市', '150981', '内蒙古自治区乌兰察布市丰镇市', 'nmgzzqwlcbsfzs');
INSERT INTO `t_system_region` VALUES ('4573', '1522', '兴安盟', '152200', '内蒙古自治区兴安盟', 'nmgzzqxam');
INSERT INTO `t_system_region` VALUES ('4574', '152201', '乌兰浩特市', '152201', '内蒙古自治区兴安盟乌兰浩特市', 'nmgzzqxamwlhts');
INSERT INTO `t_system_region` VALUES ('4575', '152202', '阿尔山市', '152202', '内蒙古自治区兴安盟阿尔山市', 'nmgzzqxamaess');
INSERT INTO `t_system_region` VALUES ('4576', '152221', '科尔沁右翼前旗', '152221', '内蒙古自治区兴安盟科尔沁右翼前旗', 'nmgzzqxamkeqyyqq');
INSERT INTO `t_system_region` VALUES ('4577', '152222', '科尔沁右翼中旗', '152222', '内蒙古自治区兴安盟科尔沁右翼中旗', 'nmgzzqxamkeqyyzq');
INSERT INTO `t_system_region` VALUES ('4578', '152223', '扎赉特旗', '152223', '内蒙古自治区兴安盟扎赉特旗', 'nmgzzqxamzltq');
INSERT INTO `t_system_region` VALUES ('4579', '152224', '突泉县', '152224', '内蒙古自治区兴安盟突泉县', 'nmgzzqxamtqx');
INSERT INTO `t_system_region` VALUES ('4580', '1525', '锡林郭勒盟', '152500', '内蒙古自治区锡林郭勒盟', 'nmgzzqxlglm');
INSERT INTO `t_system_region` VALUES ('4581', '152501', '二连浩特市', '152501', '内蒙古自治区锡林郭勒盟二连浩特市', 'nmgzzqxlglmelhts');
INSERT INTO `t_system_region` VALUES ('4582', '152502', '锡林浩特市', '152502', '内蒙古自治区锡林郭勒盟锡林浩特市', 'nmgzzqxlglmxlhts');
INSERT INTO `t_system_region` VALUES ('4583', '152522', '阿巴嘎旗', '152522', '内蒙古自治区锡林郭勒盟阿巴嘎旗', 'nmgzzqxlglmabgq');
INSERT INTO `t_system_region` VALUES ('4584', '152523', '苏尼特左旗', '152523', '内蒙古自治区锡林郭勒盟苏尼特左旗', 'nmgzzqxlglmsntzq');
INSERT INTO `t_system_region` VALUES ('4585', '152524', '苏尼特右旗', '152524', '内蒙古自治区锡林郭勒盟苏尼特右旗', 'nmgzzqxlglmsntyq');
INSERT INTO `t_system_region` VALUES ('4586', '152525', '东乌珠穆沁旗', '152525', '内蒙古自治区锡林郭勒盟东乌珠穆沁旗', 'nmgzzqxlglmdwzmqq');
INSERT INTO `t_system_region` VALUES ('4587', '152526', '西乌珠穆沁旗', '152526', '内蒙古自治区锡林郭勒盟西乌珠穆沁旗', 'nmgzzqxlglmxwzmqq');
INSERT INTO `t_system_region` VALUES ('4588', '152527', '太仆寺旗', '152527', '内蒙古自治区锡林郭勒盟太仆寺旗', 'nmgzzqxlglmtpsq');
INSERT INTO `t_system_region` VALUES ('4589', '152528', '镶黄旗', '152528', '内蒙古自治区锡林郭勒盟镶黄旗', 'nmgzzqxlglmxhq');
INSERT INTO `t_system_region` VALUES ('4590', '152529', '正镶白旗', '152529', '内蒙古自治区锡林郭勒盟正镶白旗', 'nmgzzqxlglmzxbq');
INSERT INTO `t_system_region` VALUES ('4591', '152530', '正蓝旗', '152530', '内蒙古自治区锡林郭勒盟正蓝旗', 'nmgzzqxlglmzlq');
INSERT INTO `t_system_region` VALUES ('4592', '152531', '多伦县', '152531', '内蒙古自治区锡林郭勒盟多伦县', 'nmgzzqxlglmdlx');
INSERT INTO `t_system_region` VALUES ('4593', '1529', '阿拉善盟', '152900', '内蒙古自治区阿拉善盟', 'nmgzzqalsm');
INSERT INTO `t_system_region` VALUES ('4594', '152921', '阿拉善左旗', '152921', '内蒙古自治区阿拉善盟阿拉善左旗', 'nmgzzqalsmalszq');
INSERT INTO `t_system_region` VALUES ('4595', '152922', '阿拉善右旗', '152922', '内蒙古自治区阿拉善盟阿拉善右旗', 'nmgzzqalsmalsyq');
INSERT INTO `t_system_region` VALUES ('4596', '152923', '额济纳旗', '152923', '内蒙古自治区阿拉善盟额济纳旗', 'nmgzzqalsmejnq');
INSERT INTO `t_system_region` VALUES ('4597', '21', '辽宁省', '210000', '辽宁省', 'lns');
INSERT INTO `t_system_region` VALUES ('4598', '2101', '沈阳市', '210100', '辽宁省沈阳市', 'lnssys');
INSERT INTO `t_system_region` VALUES ('4599', '210101', '市辖区', '210101', '辽宁省沈阳市市辖区', 'lnssyssxq');
INSERT INTO `t_system_region` VALUES ('4600', '210102', '和平区', '210102', '辽宁省沈阳市和平区', 'lnssyshpq');
INSERT INTO `t_system_region` VALUES ('4601', '210103', '沈河区', '210103', '辽宁省沈阳市沈河区', 'lnssysshq');
INSERT INTO `t_system_region` VALUES ('4602', '210104', '大东区', '210104', '辽宁省沈阳市大东区', 'lnssysddq');
INSERT INTO `t_system_region` VALUES ('4603', '210105', '皇姑区', '210105', '辽宁省沈阳市皇姑区', 'lnssyshgq');
INSERT INTO `t_system_region` VALUES ('4604', '210106', '铁西区', '210106', '辽宁省沈阳市铁西区', 'lnssystxq');
INSERT INTO `t_system_region` VALUES ('4605', '210111', '苏家屯区', '210111', '辽宁省沈阳市苏家屯区', 'lnssyssjtq');
INSERT INTO `t_system_region` VALUES ('4606', '210112', '东陵区', '210112', '辽宁省沈阳市东陵区', 'lnssysdlq');
INSERT INTO `t_system_region` VALUES ('4607', '210113', '新城子区', '210113', '辽宁省沈阳市新城子区', 'lnssysxczq');
INSERT INTO `t_system_region` VALUES ('4608', '210114', '于洪区', '210114', '辽宁省沈阳市于洪区', 'lnssysyhq');
INSERT INTO `t_system_region` VALUES ('4609', '210115', '浑南新区', '210115', '辽宁省沈阳市浑南新区', 'lnssyshnxq');
INSERT INTO `t_system_region` VALUES ('4610', '210116', '棋盘山区', '210116', '辽宁省沈阳市棋盘山区', 'lnssysqpsq');
INSERT INTO `t_system_region` VALUES ('4611', '210122', '辽中县', '210122', '辽宁省沈阳市辽中县', 'lnssyslzx');
INSERT INTO `t_system_region` VALUES ('4612', '210123', '康平县', '210123', '辽宁省沈阳市康平县', 'lnssyskpx');
INSERT INTO `t_system_region` VALUES ('4613', '210124', '法库县', '210124', '辽宁省沈阳市法库县', 'lnssysfkx');
INSERT INTO `t_system_region` VALUES ('4614', '210181', '新民市', '210181', '辽宁省沈阳市新民市', 'lnssysxms');
INSERT INTO `t_system_region` VALUES ('4615', '2102', '大连市', '210200', '辽宁省大连市', 'lnsdls');
INSERT INTO `t_system_region` VALUES ('4616', '210201', '市辖区', '210201', '辽宁省大连市市辖区', 'lnsdlssxq');
INSERT INTO `t_system_region` VALUES ('4617', '210202', '中山区', '210202', '辽宁省大连市中山区', 'lnsdlszsq');
INSERT INTO `t_system_region` VALUES ('4618', '210203', '西岗区', '210203', '辽宁省大连市西岗区', 'lnsdlsxgq');
INSERT INTO `t_system_region` VALUES ('4619', '210204', '沙河口区', '210204', '辽宁省大连市沙河口区', 'lnsdlsshkq');
INSERT INTO `t_system_region` VALUES ('4620', '210211', '甘井子区', '210211', '辽宁省大连市甘井子区', 'lnsdlsgjzq');
INSERT INTO `t_system_region` VALUES ('4621', '210212', '旅顺口区', '210212', '辽宁省大连市旅顺口区', 'lnsdlslskq');
INSERT INTO `t_system_region` VALUES ('4622', '210213', '金州区', '210213', '辽宁省大连市金州区', 'lnsdlsjzq');
INSERT INTO `t_system_region` VALUES ('4623', '210214', '保税区', '210214', '辽宁省大连市保税区', 'lnsdlsbsq');
INSERT INTO `t_system_region` VALUES ('4624', '210215', '长兴岛临港工业区', '210215', '辽宁省大连市长兴岛临港工业区', 'lnsdlszxdlggyq');
INSERT INTO `t_system_region` VALUES ('4625', '210216', '大连市高新园区', '210216', '辽宁省大连市大连市高新园区', 'lnsdlsdlsgxyq');
INSERT INTO `t_system_region` VALUES ('4626', '210217', '大连市花园口经济区', '210217', '辽宁省大连市大连市花园口经济区', 'lnsdlsdlshykjjq');
INSERT INTO `t_system_region` VALUES ('4627', '210224', '长海县', '210224', '辽宁省大连市长海县', 'lnsdlszhx');
INSERT INTO `t_system_region` VALUES ('4628', '210281', '瓦房店市', '210281', '辽宁省大连市瓦房店市', 'lnsdlswfds');
INSERT INTO `t_system_region` VALUES ('4629', '210282', '普兰店市', '210282', '辽宁省大连市普兰店市', 'lnsdlsplds');
INSERT INTO `t_system_region` VALUES ('4630', '210283', '庄河市', '210283', '辽宁省大连市庄河市', 'lnsdlszhs');
INSERT INTO `t_system_region` VALUES ('4631', '2103', '鞍山市', '210300', '辽宁省鞍山市', 'lnsass');
INSERT INTO `t_system_region` VALUES ('4632', '210301', '市辖区', '210301', '辽宁省鞍山市市辖区', 'lnsasssxq');
INSERT INTO `t_system_region` VALUES ('4633', '210302', '铁东区', '210302', '辽宁省鞍山市铁东区', 'lnsasstdq');
INSERT INTO `t_system_region` VALUES ('4634', '210303', '铁西区', '210303', '辽宁省鞍山市铁西区', 'lnsasstxq');
INSERT INTO `t_system_region` VALUES ('4635', '210304', '立山区', '210304', '辽宁省鞍山市立山区', 'lnsasslsq');
INSERT INTO `t_system_region` VALUES ('4636', '210311', '千山区', '210311', '辽宁省鞍山市千山区', 'lnsassqsq');
INSERT INTO `t_system_region` VALUES ('4637', '210321', '台安县', '210321', '辽宁省鞍山市台安县', 'lnsasstax');
INSERT INTO `t_system_region` VALUES ('4638', '210323', '岫岩满族自治县', '210323', '辽宁省鞍山市岫岩满族自治县', 'lnsassxymzzzx');
INSERT INTO `t_system_region` VALUES ('4639', '210381', '海城市', '210381', '辽宁省鞍山市海城市', 'lnsasshcs');
INSERT INTO `t_system_region` VALUES ('4640', '2104', '抚顺市', '210400', '辽宁省抚顺市', 'lnsfss');
INSERT INTO `t_system_region` VALUES ('4641', '210401', '市辖区', '210401', '辽宁省抚顺市市辖区', 'lnsfsssxq');
INSERT INTO `t_system_region` VALUES ('4642', '210402', '新抚区', '210402', '辽宁省抚顺市新抚区', 'lnsfssxfq');
INSERT INTO `t_system_region` VALUES ('4643', '210403', '东洲区', '210403', '辽宁省抚顺市东洲区', 'lnsfssdzq');
INSERT INTO `t_system_region` VALUES ('4644', '210404', '望花区', '210404', '辽宁省抚顺市望花区', 'lnsfsswhq');
INSERT INTO `t_system_region` VALUES ('4645', '210411', '顺城区', '210411', '辽宁省抚顺市顺城区', 'lnsfssscq');
INSERT INTO `t_system_region` VALUES ('4646', '210413', '经济开发区', '210413', '辽宁省抚顺市经济开发区', 'lnsfssjjkfq');
INSERT INTO `t_system_region` VALUES ('4647', '210421', '抚顺县', '210421', '辽宁省抚顺市抚顺县', 'lnsfssfsx');
INSERT INTO `t_system_region` VALUES ('4648', '210422', '新宾满族自治县', '210422', '辽宁省抚顺市新宾满族自治县', 'lnsfssxbmzzzx');
INSERT INTO `t_system_region` VALUES ('4649', '210423', '清原满族自治县', '210423', '辽宁省抚顺市清原满族自治县', 'lnsfssqymzzzx');
INSERT INTO `t_system_region` VALUES ('4650', '2105', '本溪市', '210500', '辽宁省本溪市', 'lnsbxs');
INSERT INTO `t_system_region` VALUES ('4651', '210501', '市辖区', '210501', '辽宁省本溪市市辖区', 'lnsbxssxq');
INSERT INTO `t_system_region` VALUES ('4652', '210502', '平山区', '210502', '辽宁省本溪市平山区', 'lnsbxspsq');
INSERT INTO `t_system_region` VALUES ('4653', '210503', '溪湖区', '210503', '辽宁省本溪市溪湖区', 'lnsbxsxhq');
INSERT INTO `t_system_region` VALUES ('4654', '210504', '明山区', '210504', '辽宁省本溪市明山区', 'lnsbxsmsq');
INSERT INTO `t_system_region` VALUES ('4655', '210505', '南芬区', '210505', '辽宁省本溪市南芬区', 'lnsbxsnfq');
INSERT INTO `t_system_region` VALUES ('4656', '210515', '石桥子开发区', '210515', '辽宁省本溪市石桥子开发区', 'lnsbxssqzkfq');
INSERT INTO `t_system_region` VALUES ('4657', '210521', '本溪满族自治县', '210521', '辽宁省本溪市本溪满族自治县', 'lnsbxsbxmzzzx');
INSERT INTO `t_system_region` VALUES ('4658', '210522', '桓仁满族自治县', '210522', '辽宁省本溪市桓仁满族自治县', 'lnsbxshrmzzzx');
INSERT INTO `t_system_region` VALUES ('4659', '2106', '丹东市', '210600', '辽宁省丹东市', 'lnsdds');
INSERT INTO `t_system_region` VALUES ('4660', '210601', '市辖区', '210601', '辽宁省丹东市市辖区', 'lnsddssxq');
INSERT INTO `t_system_region` VALUES ('4661', '210602', '元宝区', '210602', '辽宁省丹东市元宝区', 'lnsddsybq');
INSERT INTO `t_system_region` VALUES ('4662', '210603', '振兴区', '210603', '辽宁省丹东市振兴区', 'lnsddszxq');
INSERT INTO `t_system_region` VALUES ('4663', '210604', '振安区', '210604', '辽宁省丹东市振安区', 'lnsddszaq');
INSERT INTO `t_system_region` VALUES ('4664', '210612', '开发区', '210612', '辽宁省丹东市开发区', 'lnsddskfq');
INSERT INTO `t_system_region` VALUES ('4665', '210624', '宽甸满族自治县', '210624', '辽宁省丹东市宽甸满族自治县', 'lnsddskdmzzzx');
INSERT INTO `t_system_region` VALUES ('4666', '210681', '东港市', '210681', '辽宁省丹东市东港市', 'lnsddsdgs');
INSERT INTO `t_system_region` VALUES ('4667', '210682', '凤城市', '210682', '辽宁省丹东市凤城市', 'lnsddsfcs');
INSERT INTO `t_system_region` VALUES ('4668', '2107', '锦州市', '210700', '辽宁省锦州市', 'lnsjzs');
INSERT INTO `t_system_region` VALUES ('4669', '210701', '市辖区', '210701', '辽宁省锦州市市辖区', 'lnsjzssxq');
INSERT INTO `t_system_region` VALUES ('4670', '210702', '古塔区', '210702', '辽宁省锦州市古塔区', 'lnsjzsgtq');
INSERT INTO `t_system_region` VALUES ('4671', '210703', '凌河区', '210703', '辽宁省锦州市凌河区', 'lnsjzslhq');
INSERT INTO `t_system_region` VALUES ('4672', '210711', '太和区', '210711', '辽宁省锦州市太和区', 'lnsjzsthq');
INSERT INTO `t_system_region` VALUES ('4673', '210712', '开发区', '210712', '辽宁省锦州市开发区', 'lnsjzskfq');
INSERT INTO `t_system_region` VALUES ('4674', '210714', '松山新区', '210714', '辽宁省锦州市松山新区', 'lnsjzsssxq');
INSERT INTO `t_system_region` VALUES ('4675', '210726', '黑山县', '210726', '辽宁省锦州市黑山县', 'lnsjzshsx');
INSERT INTO `t_system_region` VALUES ('4676', '210727', '义县', '210727', '辽宁省锦州市义县', 'lnsjzsyx');
INSERT INTO `t_system_region` VALUES ('4677', '210781', '凌海市', '210781', '辽宁省锦州市凌海市', 'lnsjzslhs');
INSERT INTO `t_system_region` VALUES ('4678', '210782', '北宁市', '210782', '辽宁省锦州市北宁市', 'lnsjzsbns');
INSERT INTO `t_system_region` VALUES ('4679', '2108', '营口市', '210800', '辽宁省营口市', 'lnsyks');
INSERT INTO `t_system_region` VALUES ('4680', '210801', '市辖区', '210801', '辽宁省营口市市辖区', 'lnsykssxq');
INSERT INTO `t_system_region` VALUES ('4681', '210802', '站前区', '210802', '辽宁省营口市站前区', 'lnsykszqq');
INSERT INTO `t_system_region` VALUES ('4682', '210803', '西市区', '210803', '辽宁省营口市西市区', 'lnsyksxsq');
INSERT INTO `t_system_region` VALUES ('4683', '210804', '鲅鱼圈区', '210804', '辽宁省营口市鲅鱼圈区', 'lnsyksbyqq');
INSERT INTO `t_system_region` VALUES ('4684', '210811', '老边区', '210811', '辽宁省营口市老边区', 'lnsykslbq');
INSERT INTO `t_system_region` VALUES ('4685', '210881', '盖州市', '210881', '辽宁省营口市盖州市', 'lnsyksgzs');
INSERT INTO `t_system_region` VALUES ('4686', '210882', '大石桥市', '210882', '辽宁省营口市大石桥市', 'lnsyksdsqs');
INSERT INTO `t_system_region` VALUES ('4687', '2109', '阜新市', '210900', '辽宁省阜新市', 'lnsfxs');
INSERT INTO `t_system_region` VALUES ('4688', '210901', '市辖区', '210901', '辽宁省阜新市市辖区', 'lnsfxssxq');
INSERT INTO `t_system_region` VALUES ('4689', '210902', '海州区', '210902', '辽宁省阜新市海州区', 'lnsfxshzq');
INSERT INTO `t_system_region` VALUES ('4690', '210903', '新邱区', '210903', '辽宁省阜新市新邱区', 'lnsfxsxqq');
INSERT INTO `t_system_region` VALUES ('4691', '210904', '太平区', '210904', '辽宁省阜新市太平区', 'lnsfxstpq');
INSERT INTO `t_system_region` VALUES ('4692', '210905', '清河门区', '210905', '辽宁省阜新市清河门区', 'lnsfxsqhmq');
INSERT INTO `t_system_region` VALUES ('4693', '210911', '细河区', '210911', '辽宁省阜新市细河区', 'lnsfxsxhq');
INSERT INTO `t_system_region` VALUES ('4694', '210921', '阜新蒙古族自治县', '210921', '辽宁省阜新市阜新蒙古族自治县', 'lnsfxsfxmgzzzx');
INSERT INTO `t_system_region` VALUES ('4695', '210922', '彰武县', '210922', '辽宁省阜新市彰武县', 'lnsfxszwx');
INSERT INTO `t_system_region` VALUES ('4696', '2110', '辽阳市', '211000', '辽宁省辽阳市', 'lnslys');
INSERT INTO `t_system_region` VALUES ('4697', '211001', '市辖区', '211001', '辽宁省辽阳市市辖区', 'lnslyssxq');
INSERT INTO `t_system_region` VALUES ('4698', '211002', '白塔区', '211002', '辽宁省辽阳市白塔区', 'lnslysbtq');
INSERT INTO `t_system_region` VALUES ('4699', '211003', '文圣区', '211003', '辽宁省辽阳市文圣区', 'lnslyswsq');
INSERT INTO `t_system_region` VALUES ('4700', '211004', '宏伟区', '211004', '辽宁省辽阳市宏伟区', 'lnslyshwq');
INSERT INTO `t_system_region` VALUES ('4701', '211005', '弓长岭区', '211005', '辽宁省辽阳市弓长岭区', 'lnslysgzlq');
INSERT INTO `t_system_region` VALUES ('4702', '211011', '太子河区', '211011', '辽宁省辽阳市太子河区', 'lnslystzhq');
INSERT INTO `t_system_region` VALUES ('4703', '211021', '辽阳县', '211021', '辽宁省辽阳市辽阳县', 'lnslyslyx');
INSERT INTO `t_system_region` VALUES ('4704', '211081', '灯塔市', '211081', '辽宁省辽阳市灯塔市', 'lnslysdts');
INSERT INTO `t_system_region` VALUES ('4705', '2111', '盘锦市', '211100', '辽宁省盘锦市', 'lnspjs');
INSERT INTO `t_system_region` VALUES ('4706', '211101', '市辖区', '211101', '辽宁省盘锦市市辖区', 'lnspjssxq');
INSERT INTO `t_system_region` VALUES ('4707', '211102', '双台子区', '211102', '辽宁省盘锦市双台子区', 'lnspjsstzq');
INSERT INTO `t_system_region` VALUES ('4708', '211103', '兴隆台区', '211103', '辽宁省盘锦市兴隆台区', 'lnspjsxltq');
INSERT INTO `t_system_region` VALUES ('4709', '211121', '大洼县', '211121', '辽宁省盘锦市大洼县', 'lnspjsdwx');
INSERT INTO `t_system_region` VALUES ('4710', '211122', '盘山县', '211122', '辽宁省盘锦市盘山县', 'lnspjspsx');
INSERT INTO `t_system_region` VALUES ('4711', '211199', '辽河油田', '211199', '辽宁省盘锦市辽河油田', 'lnspjslhyt');
INSERT INTO `t_system_region` VALUES ('4712', '2112', '铁岭市', '211200', '辽宁省铁岭市', 'lnstls');
INSERT INTO `t_system_region` VALUES ('4713', '211201', '市辖区', '211201', '辽宁省铁岭市市辖区', 'lnstlssxq');
INSERT INTO `t_system_region` VALUES ('4714', '211202', '银州区', '211202', '辽宁省铁岭市银州区', 'lnstlsyzq');
INSERT INTO `t_system_region` VALUES ('4715', '211204', '清河区', '211204', '辽宁省铁岭市清河区', 'lnstlsqhq');
INSERT INTO `t_system_region` VALUES ('4716', '211213', '开发区', '211213', '辽宁省铁岭市开发区', 'lnstlskfq');
INSERT INTO `t_system_region` VALUES ('4717', '211221', '铁岭县', '211221', '辽宁省铁岭市铁岭县', 'lnstlstlx');
INSERT INTO `t_system_region` VALUES ('4718', '211223', '西丰县', '211223', '辽宁省铁岭市西丰县', 'lnstlsxfx');
INSERT INTO `t_system_region` VALUES ('4719', '211224', '昌图县', '211224', '辽宁省铁岭市昌图县', 'lnstlsctx');
INSERT INTO `t_system_region` VALUES ('4720', '211281', '调兵山市', '211281', '辽宁省铁岭市调兵山市', 'lnstlsdbss');
INSERT INTO `t_system_region` VALUES ('4721', '211282', '开原市', '211282', '辽宁省铁岭市开原市', 'lnstlskys');
INSERT INTO `t_system_region` VALUES ('4722', '2113', '朝阳市', '211300', '辽宁省朝阳市', 'lnscys');
INSERT INTO `t_system_region` VALUES ('4723', '211301', '市辖区', '211301', '辽宁省朝阳市市辖区', 'lnscyssxq');
INSERT INTO `t_system_region` VALUES ('4724', '211302', '双塔区', '211302', '辽宁省朝阳市双塔区', 'lnscysstq');
INSERT INTO `t_system_region` VALUES ('4725', '211303', '龙城区', '211303', '辽宁省朝阳市龙城区', 'lnscyslcq');
INSERT INTO `t_system_region` VALUES ('4726', '211313', '开发区', '211313', '辽宁省朝阳市开发区', 'lnscyskfq');
INSERT INTO `t_system_region` VALUES ('4727', '211321', '朝阳县', '211321', '辽宁省朝阳市朝阳县', 'lnscyscyx');
INSERT INTO `t_system_region` VALUES ('4728', '211322', '建平县', '211322', '辽宁省朝阳市建平县', 'lnscysjpx');
INSERT INTO `t_system_region` VALUES ('4729', '211324', '喀喇沁左翼蒙古族自治县', '211324', '辽宁省朝阳市喀喇沁左翼蒙古族自治县', 'lnscysklqzymgzzzx');
INSERT INTO `t_system_region` VALUES ('4730', '211381', '北票市', '211381', '辽宁省朝阳市北票市', 'lnscysbps');
INSERT INTO `t_system_region` VALUES ('4731', '211382', '凌源市', '211382', '辽宁省朝阳市凌源市', 'lnscyslys');
INSERT INTO `t_system_region` VALUES ('4732', '2114', '葫芦岛市', '211400', '辽宁省葫芦岛市', 'lnshlds');
INSERT INTO `t_system_region` VALUES ('4733', '211401', '市辖区', '211401', '辽宁省葫芦岛市市辖区', 'lnshldssxq');
INSERT INTO `t_system_region` VALUES ('4734', '211402', '连山区', '211402', '辽宁省葫芦岛市连山区', 'lnshldslsq');
INSERT INTO `t_system_region` VALUES ('4735', '211403', '龙港区', '211403', '辽宁省葫芦岛市龙港区', 'lnshldslgq');
INSERT INTO `t_system_region` VALUES ('4736', '211404', '南票区', '211404', '辽宁省葫芦岛市南票区', 'lnshldsnpq');
INSERT INTO `t_system_region` VALUES ('4737', '211421', '绥中县', '211421', '辽宁省葫芦岛市绥中县', 'lnshldsszx');
INSERT INTO `t_system_region` VALUES ('4738', '211422', '建昌县', '211422', '辽宁省葫芦岛市建昌县', 'lnshldsjcx');
INSERT INTO `t_system_region` VALUES ('4739', '211481', '兴城市', '211481', '辽宁省葫芦岛市兴城市', 'lnshldsxcs');
INSERT INTO `t_system_region` VALUES ('4740', '22', '吉林省', '220000', '吉林省', 'jls');
INSERT INTO `t_system_region` VALUES ('4741', '2201', '长春市', '220100', '吉林省长春市', 'jlszcs');
INSERT INTO `t_system_region` VALUES ('4742', '220101', '市辖区', '220101', '吉林省长春市市辖区', 'jlszcssxq');
INSERT INTO `t_system_region` VALUES ('4743', '220102', '南关区', '220102', '吉林省长春市南关区', 'jlszcsngq');
INSERT INTO `t_system_region` VALUES ('4744', '220103', '宽城区', '220103', '吉林省长春市宽城区', 'jlszcskcq');
INSERT INTO `t_system_region` VALUES ('4745', '220104', '朝阳区', '220104', '吉林省长春市朝阳区', 'jlszcscyq');
INSERT INTO `t_system_region` VALUES ('4746', '220105', '二道区', '220105', '吉林省长春市二道区', 'jlszcsedq');
INSERT INTO `t_system_region` VALUES ('4747', '220106', '绿园区', '220106', '吉林省长春市绿园区', 'jlszcslyq');
INSERT INTO `t_system_region` VALUES ('4748', '220112', '双阳区', '220112', '吉林省长春市双阳区', 'jlszcssyq');
INSERT INTO `t_system_region` VALUES ('4749', '220122', '农安县', '220122', '吉林省长春市农安县', 'jlszcsnax');
INSERT INTO `t_system_region` VALUES ('4750', '220181', '九台市', '220181', '吉林省长春市九台市', 'jlszcsjts');
INSERT INTO `t_system_region` VALUES ('4751', '220182', '榆树市', '220182', '吉林省长春市榆树市', 'jlszcsyss');
INSERT INTO `t_system_region` VALUES ('4752', '220183', '德惠市', '220183', '吉林省长春市德惠市', 'jlszcsdhs');
INSERT INTO `t_system_region` VALUES ('4753', '2202', '吉林市', '220200', '吉林省吉林市', 'jlsjls');
INSERT INTO `t_system_region` VALUES ('4754', '220201', '市辖区', '220201', '吉林省吉林市市辖区', 'jlsjlssxq');
INSERT INTO `t_system_region` VALUES ('4755', '220202', '昌邑区', '220202', '吉林省吉林市昌邑区', 'jlsjlscyq');
INSERT INTO `t_system_region` VALUES ('4756', '220203', '龙潭区', '220203', '吉林省吉林市龙潭区', 'jlsjlsltq');
INSERT INTO `t_system_region` VALUES ('4757', '220204', '船营区', '220204', '吉林省吉林市船营区', 'jlsjlscyq');
INSERT INTO `t_system_region` VALUES ('4758', '220211', '丰满区', '220211', '吉林省吉林市丰满区', 'jlsjlsfmq');
INSERT INTO `t_system_region` VALUES ('4759', '220221', '永吉县', '220221', '吉林省吉林市永吉县', 'jlsjlsyjx');
INSERT INTO `t_system_region` VALUES ('4760', '220281', '蛟河市', '220281', '吉林省吉林市蛟河市', 'jlsjlsjhs');
INSERT INTO `t_system_region` VALUES ('4761', '220282', '桦甸市', '220282', '吉林省吉林市桦甸市', 'jlsjlshds');
INSERT INTO `t_system_region` VALUES ('4762', '220283', '舒兰市', '220283', '吉林省吉林市舒兰市', 'jlsjlssls');
INSERT INTO `t_system_region` VALUES ('4763', '220284', '磐石市', '220284', '吉林省吉林市磐石市', 'jlsjlspss');
INSERT INTO `t_system_region` VALUES ('4764', '2203', '四平市', '220300', '吉林省四平市', 'jlssps');
INSERT INTO `t_system_region` VALUES ('4765', '220301', '市辖区', '220301', '吉林省四平市市辖区', 'jlsspssxq');
INSERT INTO `t_system_region` VALUES ('4766', '220302', '铁西区', '220302', '吉林省四平市铁西区', 'jlsspstxq');
INSERT INTO `t_system_region` VALUES ('4767', '220303', '铁东区', '220303', '吉林省四平市铁东区', 'jlsspstdq');
INSERT INTO `t_system_region` VALUES ('4768', '220322', '梨树县', '220322', '吉林省四平市梨树县', 'jlsspslsx');
INSERT INTO `t_system_region` VALUES ('4769', '220323', '伊通满族自治县', '220323', '吉林省四平市伊通满族自治县', 'jlsspsytmzzzx');
INSERT INTO `t_system_region` VALUES ('4770', '220381', '公主岭市', '220381', '吉林省四平市公主岭市', 'jlsspsgzls');
INSERT INTO `t_system_region` VALUES ('4771', '220382', '双辽市', '220382', '吉林省四平市双辽市', 'jlsspssls');
INSERT INTO `t_system_region` VALUES ('4772', '2204', '辽源市', '220400', '吉林省辽源市', 'jlslys');
INSERT INTO `t_system_region` VALUES ('4773', '220401', '市辖区', '220401', '吉林省辽源市市辖区', 'jlslyssxq');
INSERT INTO `t_system_region` VALUES ('4774', '220402', '龙山区', '220402', '吉林省辽源市龙山区', 'jlslyslsq');
INSERT INTO `t_system_region` VALUES ('4775', '220403', '西安区', '220403', '吉林省辽源市西安区', 'jlslysxaq');
INSERT INTO `t_system_region` VALUES ('4776', '220421', '东丰县', '220421', '吉林省辽源市东丰县', 'jlslysdfx');
INSERT INTO `t_system_region` VALUES ('4777', '220422', '东辽县', '220422', '吉林省辽源市东辽县', 'jlslysdlx');
INSERT INTO `t_system_region` VALUES ('4778', '2205', '通化市', '220500', '吉林省通化市', 'jlsths');
INSERT INTO `t_system_region` VALUES ('4779', '220501', '市辖区', '220501', '吉林省通化市市辖区', 'jlsthssxq');
INSERT INTO `t_system_region` VALUES ('4780', '220502', '东昌区', '220502', '吉林省通化市东昌区', 'jlsthsdcq');
INSERT INTO `t_system_region` VALUES ('4781', '220503', '二道江区', '220503', '吉林省通化市二道江区', 'jlsthsedjq');
INSERT INTO `t_system_region` VALUES ('4782', '220521', '通化县', '220521', '吉林省通化市通化县', 'jlsthsthx');
INSERT INTO `t_system_region` VALUES ('4783', '220523', '辉南县', '220523', '吉林省通化市辉南县', 'jlsthshnx');
INSERT INTO `t_system_region` VALUES ('4784', '220524', '柳河县', '220524', '吉林省通化市柳河县', 'jlsthslhx');
INSERT INTO `t_system_region` VALUES ('4785', '220581', '梅河口市', '220581', '吉林省通化市梅河口市', 'jlsthsmhks');
INSERT INTO `t_system_region` VALUES ('4786', '220582', '集安市', '220582', '吉林省通化市集安市', 'jlsthsjas');
INSERT INTO `t_system_region` VALUES ('4787', '2206', '白山市', '220600', '吉林省白山市', 'jlsbss');
INSERT INTO `t_system_region` VALUES ('4788', '220601', '市辖区', '220601', '吉林省白山市市辖区', 'jlsbsssxq');
INSERT INTO `t_system_region` VALUES ('4789', '220602', '八道江区', '220602', '吉林省白山市八道江区', 'jlsbssbdjq');
INSERT INTO `t_system_region` VALUES ('4790', '220605', '江源区', '220605', '吉林省白山市江源区', 'jlsbssjyq');
INSERT INTO `t_system_region` VALUES ('4791', '220621', '抚松县', '220621', '吉林省白山市抚松县', 'jlsbssfsx');
INSERT INTO `t_system_region` VALUES ('4792', '220622', '靖宇县', '220622', '吉林省白山市靖宇县', 'jlsbssjyx');
INSERT INTO `t_system_region` VALUES ('4793', '220623', '长白朝鲜族自治县', '220623', '吉林省白山市长白朝鲜族自治县', 'jlsbsszbcxzzzx');
INSERT INTO `t_system_region` VALUES ('4794', '220681', '临江市', '220681', '吉林省白山市临江市', 'jlsbssljs');
INSERT INTO `t_system_region` VALUES ('4795', '2207', '松原市', '220700', '吉林省松原市', 'jlssys');
INSERT INTO `t_system_region` VALUES ('4796', '220701', '市辖区', '220701', '吉林省松原市市辖区', 'jlssyssxq');
INSERT INTO `t_system_region` VALUES ('4797', '220702', '宁江区', '220702', '吉林省松原市宁江区', 'jlssysnjq');
INSERT INTO `t_system_region` VALUES ('4798', '220721', '前郭尔罗斯蒙古族自治县', '220721', '吉林省松原市前郭尔罗斯蒙古族自治县', 'jlssysqgelsmgzzzx');
INSERT INTO `t_system_region` VALUES ('4799', '220722', '长岭县', '220722', '吉林省松原市长岭县', 'jlssyszlx');
INSERT INTO `t_system_region` VALUES ('4800', '220723', '乾安县', '220723', '吉林省松原市乾安县', 'jlssysqax');
INSERT INTO `t_system_region` VALUES ('4801', '220724', '扶余县', '220724', '吉林省松原市扶余县', 'jlssysfyx');
INSERT INTO `t_system_region` VALUES ('4802', '2208', '白城市', '220800', '吉林省白城市', 'jlsbcs');
INSERT INTO `t_system_region` VALUES ('4803', '220801', '市辖区', '220801', '吉林省白城市市辖区', 'jlsbcssxq');
INSERT INTO `t_system_region` VALUES ('4804', '220802', '洮北区', '220802', '吉林省白城市洮北区', 'jlsbcstbq');
INSERT INTO `t_system_region` VALUES ('4805', '220821', '镇赉县', '220821', '吉林省白城市镇赉县', 'jlsbcszlx');
INSERT INTO `t_system_region` VALUES ('4806', '220822', '通榆县', '220822', '吉林省白城市通榆县', 'jlsbcstyx');
INSERT INTO `t_system_region` VALUES ('4807', '220881', '洮南市', '220881', '吉林省白城市洮南市', 'jlsbcstns');
INSERT INTO `t_system_region` VALUES ('4808', '220882', '大安市', '220882', '吉林省白城市大安市', 'jlsbcsdas');
INSERT INTO `t_system_region` VALUES ('4809', '2224', '延边朝鲜族自治州', '222400', '吉林省延边朝鲜族自治州', 'jlsybcxzzzz');
INSERT INTO `t_system_region` VALUES ('4810', '222401', '延吉市', '222401', '吉林省延边朝鲜族自治州延吉市', 'jlsybcxzzzzyjs');
INSERT INTO `t_system_region` VALUES ('4811', '222402', '图们市', '222402', '吉林省延边朝鲜族自治州图们市', 'jlsybcxzzzztms');
INSERT INTO `t_system_region` VALUES ('4812', '222403', '敦化市', '222403', '吉林省延边朝鲜族自治州敦化市', 'jlsybcxzzzzdhs');
INSERT INTO `t_system_region` VALUES ('4813', '222404', '珲春市', '222404', '吉林省延边朝鲜族自治州珲春市', 'jlsybcxzzzzhcs');
INSERT INTO `t_system_region` VALUES ('4814', '222405', '龙井市', '222405', '吉林省延边朝鲜族自治州龙井市', 'jlsybcxzzzzljs');
INSERT INTO `t_system_region` VALUES ('4815', '222406', '和龙市', '222406', '吉林省延边朝鲜族自治州和龙市', 'jlsybcxzzzzhls');
INSERT INTO `t_system_region` VALUES ('4816', '222424', '汪清县', '222424', '吉林省延边朝鲜族自治州汪清县', 'jlsybcxzzzzwqx');
INSERT INTO `t_system_region` VALUES ('4817', '222426', '安图县', '222426', '吉林省延边朝鲜族自治州安图县', 'jlsybcxzzzzatx');
INSERT INTO `t_system_region` VALUES ('4818', '23', '黑龙江省', '230000', '黑龙江省', 'hljs');
INSERT INTO `t_system_region` VALUES ('4820', '2301', '哈尔滨市', '230100', '黑龙江省哈尔滨市', 'hljshebs');
INSERT INTO `t_system_region` VALUES ('4821', '230101', '市辖区', '230101', '黑龙江省哈尔滨市市辖区', 'hljshebssxq');
INSERT INTO `t_system_region` VALUES ('4822', '230102', '道里区', '230102', '黑龙江省哈尔滨市道里区', 'hljshebsdlq');
INSERT INTO `t_system_region` VALUES ('4823', '230103', '南岗区', '230103', '黑龙江省哈尔滨市南岗区', 'hljshebsngq');
INSERT INTO `t_system_region` VALUES ('4824', '230104', '道外区', '230104', '黑龙江省哈尔滨市道外区', 'hljshebsdwq');
INSERT INTO `t_system_region` VALUES ('4825', '230106', '香坊区', '230106', '黑龙江省哈尔滨市香坊区', 'hljshebsxfq');
INSERT INTO `t_system_region` VALUES ('4826', '230108', '平房区', '230108', '黑龙江省哈尔滨市平房区', 'hljshebspfq');
INSERT INTO `t_system_region` VALUES ('4827', '230109', '松北区', '230109', '黑龙江省哈尔滨市松北区', 'hljshebssbq');
INSERT INTO `t_system_region` VALUES ('4828', '230111', '呼兰区', '230111', '黑龙江省哈尔滨市呼兰区', 'hljshebshlq');
INSERT INTO `t_system_region` VALUES ('4829', '230112', '阿城区', '230112', '黑龙江省哈尔滨市阿城区', 'hljshebsacq');
INSERT INTO `t_system_region` VALUES ('4830', '230123', '依兰县', '230123', '黑龙江省哈尔滨市依兰县', 'hljshebsylx');
INSERT INTO `t_system_region` VALUES ('4831', '230124', '方正县', '230124', '黑龙江省哈尔滨市方正县', 'hljshebsfzx');
INSERT INTO `t_system_region` VALUES ('4832', '230125', '宾县', '230125', '黑龙江省哈尔滨市宾县', 'hljshebsbx');
INSERT INTO `t_system_region` VALUES ('4833', '230126', '巴彦县', '230126', '黑龙江省哈尔滨市巴彦县', 'hljshebsbyx');
INSERT INTO `t_system_region` VALUES ('4834', '230127', '木兰县', '230127', '黑龙江省哈尔滨市木兰县', 'hljshebsmlx');
INSERT INTO `t_system_region` VALUES ('4835', '230128', '通河县', '230128', '黑龙江省哈尔滨市通河县', 'hljshebsthx');
INSERT INTO `t_system_region` VALUES ('4836', '230129', '延寿县', '230129', '黑龙江省哈尔滨市延寿县', 'hljshebsysx');
INSERT INTO `t_system_region` VALUES ('4837', '230182', '双城市', '230182', '黑龙江省哈尔滨市双城市', 'hljshebsscs');
INSERT INTO `t_system_region` VALUES ('4838', '230183', '尚志市', '230183', '黑龙江省哈尔滨市尚志市', 'hljshebsszs');
INSERT INTO `t_system_region` VALUES ('4839', '230184', '五常市', '230184', '黑龙江省哈尔滨市五常市', 'hljshebswcs');
INSERT INTO `t_system_region` VALUES ('4840', '2302', '齐齐哈尔市', '230200', '黑龙江省齐齐哈尔市', 'hljsqqhes');
INSERT INTO `t_system_region` VALUES ('4841', '230201', '市辖区', '230201', '黑龙江省齐齐哈尔市市辖区', 'hljsqqhessxq');
INSERT INTO `t_system_region` VALUES ('4842', '230202', '龙沙区', '230202', '黑龙江省齐齐哈尔市龙沙区', 'hljsqqheslsq');
INSERT INTO `t_system_region` VALUES ('4843', '230203', '建华区', '230203', '黑龙江省齐齐哈尔市建华区', 'hljsqqhesjhq');
INSERT INTO `t_system_region` VALUES ('4844', '230204', '铁锋区', '230204', '黑龙江省齐齐哈尔市铁锋区', 'hljsqqhestfq');
INSERT INTO `t_system_region` VALUES ('4845', '230205', '昂昂溪区', '230205', '黑龙江省齐齐哈尔市昂昂溪区', 'hljsqqhesaaxq');
INSERT INTO `t_system_region` VALUES ('4846', '230206', '富拉尔基区', '230206', '黑龙江省齐齐哈尔市富拉尔基区', 'hljsqqhesflejq');
INSERT INTO `t_system_region` VALUES ('4847', '230207', '碾子山区', '230207', '黑龙江省齐齐哈尔市碾子山区', 'hljsqqhesnzsq');
INSERT INTO `t_system_region` VALUES ('4848', '230208', '梅里斯达斡尔族区', '230208', '黑龙江省齐齐哈尔市梅里斯达斡尔族区', 'hljsqqhesmlsdwezq');
INSERT INTO `t_system_region` VALUES ('4849', '230221', '龙江县', '230221', '黑龙江省齐齐哈尔市龙江县', 'hljsqqhesljx');
INSERT INTO `t_system_region` VALUES ('4850', '230223', '依安县', '230223', '黑龙江省齐齐哈尔市依安县', 'hljsqqhesyax');
INSERT INTO `t_system_region` VALUES ('4851', '230224', '泰来县', '230224', '黑龙江省齐齐哈尔市泰来县', 'hljsqqhestlx');
INSERT INTO `t_system_region` VALUES ('4852', '230225', '甘南县', '230225', '黑龙江省齐齐哈尔市甘南县', 'hljsqqhesgnx');
INSERT INTO `t_system_region` VALUES ('4853', '230227', '富裕县', '230227', '黑龙江省齐齐哈尔市富裕县', 'hljsqqhesfyx');
INSERT INTO `t_system_region` VALUES ('4854', '230229', '克山县', '230229', '黑龙江省齐齐哈尔市克山县', 'hljsqqhesksx');
INSERT INTO `t_system_region` VALUES ('4855', '230230', '克东县', '230230', '黑龙江省齐齐哈尔市克东县', 'hljsqqheskdx');
INSERT INTO `t_system_region` VALUES ('4856', '230231', '拜泉县', '230231', '黑龙江省齐齐哈尔市拜泉县', 'hljsqqhesbqx');
INSERT INTO `t_system_region` VALUES ('4857', '230281', '讷河市', '230281', '黑龙江省齐齐哈尔市讷河市', 'hljsqqhesnhs');
INSERT INTO `t_system_region` VALUES ('4858', '2303', '鸡西市', '230300', '黑龙江省鸡西市', 'hljsjxs');
INSERT INTO `t_system_region` VALUES ('4859', '230301', '市辖区', '230301', '黑龙江省鸡西市市辖区', 'hljsjxssxq');
INSERT INTO `t_system_region` VALUES ('4860', '230302', '鸡冠区', '230302', '黑龙江省鸡西市鸡冠区', 'hljsjxsjgq');
INSERT INTO `t_system_region` VALUES ('4861', '230303', '恒山区', '230303', '黑龙江省鸡西市恒山区', 'hljsjxshsq');
INSERT INTO `t_system_region` VALUES ('4862', '230304', '滴道区', '230304', '黑龙江省鸡西市滴道区', 'hljsjxsddq');
INSERT INTO `t_system_region` VALUES ('4863', '230305', '梨树区', '230305', '黑龙江省鸡西市梨树区', 'hljsjxslsq');
INSERT INTO `t_system_region` VALUES ('4864', '230306', '城子河区', '230306', '黑龙江省鸡西市城子河区', 'hljsjxsczhq');
INSERT INTO `t_system_region` VALUES ('4865', '230307', '麻山区', '230307', '黑龙江省鸡西市麻山区', 'hljsjxsmsq');
INSERT INTO `t_system_region` VALUES ('4866', '230321', '鸡东县', '230321', '黑龙江省鸡西市鸡东县', 'hljsjxsjdx');
INSERT INTO `t_system_region` VALUES ('4867', '230381', '虎林市', '230381', '黑龙江省鸡西市虎林市', 'hljsjxshls');
INSERT INTO `t_system_region` VALUES ('4868', '230382', '密山市', '230382', '黑龙江省鸡西市密山市', 'hljsjxsmss');
INSERT INTO `t_system_region` VALUES ('4869', '2304', '鹤岗市', '230400', '黑龙江省鹤岗市', 'hljshgs');
INSERT INTO `t_system_region` VALUES ('4870', '230401', '市辖区', '230401', '黑龙江省鹤岗市市辖区', 'hljshgssxq');
INSERT INTO `t_system_region` VALUES ('4871', '230402', '向阳区', '230402', '黑龙江省鹤岗市向阳区', 'hljshgsxyq');
INSERT INTO `t_system_region` VALUES ('4872', '230403', '工农区', '230403', '黑龙江省鹤岗市工农区', 'hljshgsgnq');
INSERT INTO `t_system_region` VALUES ('4873', '230404', '南山区', '230404', '黑龙江省鹤岗市南山区', 'hljshgsnsq');
INSERT INTO `t_system_region` VALUES ('4874', '230405', '兴安区', '230405', '黑龙江省鹤岗市兴安区', 'hljshgsxaq');
INSERT INTO `t_system_region` VALUES ('4875', '230406', '东山区', '230406', '黑龙江省鹤岗市东山区', 'hljshgsdsq');
INSERT INTO `t_system_region` VALUES ('4876', '230407', '兴山区', '230407', '黑龙江省鹤岗市兴山区', 'hljshgsxsq');
INSERT INTO `t_system_region` VALUES ('4877', '230421', '萝北县', '230421', '黑龙江省鹤岗市萝北县', 'hljshgslbx');
INSERT INTO `t_system_region` VALUES ('4878', '230422', '绥滨县', '230422', '黑龙江省鹤岗市绥滨县', 'hljshgssbx');
INSERT INTO `t_system_region` VALUES ('4879', '2305', '双鸭山市', '230500', '黑龙江省双鸭山市', 'hljssyss');
INSERT INTO `t_system_region` VALUES ('4880', '230501', '市辖区', '230501', '黑龙江省双鸭山市市辖区', 'hljssysssxq');
INSERT INTO `t_system_region` VALUES ('4881', '230502', '尖山区', '230502', '黑龙江省双鸭山市尖山区', 'hljssyssjsq');
INSERT INTO `t_system_region` VALUES ('4882', '230503', '岭东区', '230503', '黑龙江省双鸭山市岭东区', 'hljssyssldq');
INSERT INTO `t_system_region` VALUES ('4883', '230505', '四方台区', '230505', '黑龙江省双鸭山市四方台区', 'hljssysssftq');
INSERT INTO `t_system_region` VALUES ('4884', '230506', '宝山区', '230506', '黑龙江省双鸭山市宝山区', 'hljssyssbsq');
INSERT INTO `t_system_region` VALUES ('4885', '230521', '集贤县', '230521', '黑龙江省双鸭山市集贤县', 'hljssyssjxx');
INSERT INTO `t_system_region` VALUES ('4886', '230522', '友谊县', '230522', '黑龙江省双鸭山市友谊县', 'hljssyssyyx');
INSERT INTO `t_system_region` VALUES ('4887', '230523', '宝清县', '230523', '黑龙江省双鸭山市宝清县', 'hljssyssbqx');
INSERT INTO `t_system_region` VALUES ('4888', '230524', '饶河县', '230524', '黑龙江省双鸭山市饶河县', 'hljssyssrhx');
INSERT INTO `t_system_region` VALUES ('4889', '2306', '大庆市', '230600', '黑龙江省大庆市', 'hljsdqs');
INSERT INTO `t_system_region` VALUES ('4890', '230601', '市辖区', '230601', '黑龙江省大庆市市辖区', 'hljsdqssxq');
INSERT INTO `t_system_region` VALUES ('4891', '230602', '萨尔图区', '230602', '黑龙江省大庆市萨尔图区', 'hljsdqssetq');
INSERT INTO `t_system_region` VALUES ('4892', '230603', '龙凤区', '230603', '黑龙江省大庆市龙凤区', 'hljsdqslfq');
INSERT INTO `t_system_region` VALUES ('4893', '230604', '让胡路区', '230604', '黑龙江省大庆市让胡路区', 'hljsdqsrhlq');
INSERT INTO `t_system_region` VALUES ('4894', '230605', '红岗区', '230605', '黑龙江省大庆市红岗区', 'hljsdqshgq');
INSERT INTO `t_system_region` VALUES ('4895', '230606', '大同区', '230606', '黑龙江省大庆市大同区', 'hljsdqsdtq');
INSERT INTO `t_system_region` VALUES ('4896', '230621', '肇州县', '230621', '黑龙江省大庆市肇州县', 'hljsdqszzx');
INSERT INTO `t_system_region` VALUES ('4897', '230622', '肇源县', '230622', '黑龙江省大庆市肇源县', 'hljsdqszyx');
INSERT INTO `t_system_region` VALUES ('4898', '230623', '林甸县', '230623', '黑龙江省大庆市林甸县', 'hljsdqsldx');
INSERT INTO `t_system_region` VALUES ('4899', '230624', '杜尔伯特蒙古族自治县', '230624', '黑龙江省大庆市杜尔伯特蒙古族自治县', 'hljsdqsdebtmgzzzx');
INSERT INTO `t_system_region` VALUES ('4900', '2307', '伊春市', '230700', '黑龙江省伊春市', 'hljsycs');
INSERT INTO `t_system_region` VALUES ('4901', '230701', '市辖区', '230701', '黑龙江省伊春市市辖区', 'hljsycssxq');
INSERT INTO `t_system_region` VALUES ('4902', '230702', '伊春区', '230702', '黑龙江省伊春市伊春区', 'hljsycsycq');
INSERT INTO `t_system_region` VALUES ('4903', '230703', '南岔区', '230703', '黑龙江省伊春市南岔区', 'hljsycsncq');
INSERT INTO `t_system_region` VALUES ('4904', '230704', '友好区', '230704', '黑龙江省伊春市友好区', 'hljsycsyhq');
INSERT INTO `t_system_region` VALUES ('4905', '230705', '西林区', '230705', '黑龙江省伊春市西林区', 'hljsycsxlq');
INSERT INTO `t_system_region` VALUES ('4906', '230706', '翠峦区', '230706', '黑龙江省伊春市翠峦区', 'hljsycsclq');
INSERT INTO `t_system_region` VALUES ('4907', '230707', '新青区', '230707', '黑龙江省伊春市新青区', 'hljsycsxqq');
INSERT INTO `t_system_region` VALUES ('4908', '230708', '美溪区', '230708', '黑龙江省伊春市美溪区', 'hljsycsmxq');
INSERT INTO `t_system_region` VALUES ('4909', '230709', '金山屯区', '230709', '黑龙江省伊春市金山屯区', 'hljsycsjstq');
INSERT INTO `t_system_region` VALUES ('4910', '230710', '五营区', '230710', '黑龙江省伊春市五营区', 'hljsycswyq');
INSERT INTO `t_system_region` VALUES ('4911', '230711', '乌马河区', '230711', '黑龙江省伊春市乌马河区', 'hljsycswmhq');
INSERT INTO `t_system_region` VALUES ('4912', '230712', '汤旺河区', '230712', '黑龙江省伊春市汤旺河区', 'hljsycstwhq');
INSERT INTO `t_system_region` VALUES ('4913', '230713', '带岭区', '230713', '黑龙江省伊春市带岭区', 'hljsycsdlq');
INSERT INTO `t_system_region` VALUES ('4914', '230714', '乌伊岭区', '230714', '黑龙江省伊春市乌伊岭区', 'hljsycswylq');
INSERT INTO `t_system_region` VALUES ('4915', '230715', '红星区', '230715', '黑龙江省伊春市红星区', 'hljsycshxq');
INSERT INTO `t_system_region` VALUES ('4916', '230716', '上甘岭区', '230716', '黑龙江省伊春市上甘岭区', 'hljsycssglq');
INSERT INTO `t_system_region` VALUES ('4917', '230722', '嘉荫县', '230722', '黑龙江省伊春市嘉荫县', 'hljsycsjyx');
INSERT INTO `t_system_region` VALUES ('4918', '230781', '铁力市', '230781', '黑龙江省伊春市铁力市', 'hljsycstls');
INSERT INTO `t_system_region` VALUES ('4919', '230791', '铁力林业局', '230791', '黑龙江省伊春市铁力林业局', 'hljsycstllyj');
INSERT INTO `t_system_region` VALUES ('4920', '230792', '双丰林业局', '230792', '黑龙江省伊春市双丰林业局', 'hljsycssflyj');
INSERT INTO `t_system_region` VALUES ('4921', '230793', '桃山林业局', '230793', '黑龙江省伊春市桃山林业局', 'hljsycstslyj');
INSERT INTO `t_system_region` VALUES ('4922', '230794', '郎乡林业局', '230794', '黑龙江省伊春市郎乡林业局', 'hljsycslxlyj');
INSERT INTO `t_system_region` VALUES ('4923', '2308', '佳木斯市', '230800', '黑龙江省佳木斯市', 'hljsjmss');
INSERT INTO `t_system_region` VALUES ('4924', '230801', '市辖区', '230801', '黑龙江省佳木斯市市辖区', 'hljsjmsssxq');
INSERT INTO `t_system_region` VALUES ('4925', '230803', '向阳区', '230803', '黑龙江省佳木斯市向阳区', 'hljsjmssxyq');
INSERT INTO `t_system_region` VALUES ('4926', '230804', '前进区', '230804', '黑龙江省佳木斯市前进区', 'hljsjmssqjq');
INSERT INTO `t_system_region` VALUES ('4927', '230805', '东风区', '230805', '黑龙江省佳木斯市东风区', 'hljsjmssdfq');
INSERT INTO `t_system_region` VALUES ('4928', '230811', '郊区', '230811', '黑龙江省佳木斯市郊区', 'hljsjmssjq');
INSERT INTO `t_system_region` VALUES ('4929', '230822', '桦南县', '230822', '黑龙江省佳木斯市桦南县', 'hljsjmsshnx');
INSERT INTO `t_system_region` VALUES ('4930', '230826', '桦川县', '230826', '黑龙江省佳木斯市桦川县', 'hljsjmsshcx');
INSERT INTO `t_system_region` VALUES ('4931', '230828', '汤原县', '230828', '黑龙江省佳木斯市汤原县', 'hljsjmsstyx');
INSERT INTO `t_system_region` VALUES ('4932', '230833', '抚远县', '230833', '黑龙江省佳木斯市抚远县', 'hljsjmssfyx');
INSERT INTO `t_system_region` VALUES ('4933', '230881', '同江市', '230881', '黑龙江省佳木斯市同江市', 'hljsjmsstjs');
INSERT INTO `t_system_region` VALUES ('4934', '230882', '富锦市', '230882', '黑龙江省佳木斯市富锦市', 'hljsjmssfjs');
INSERT INTO `t_system_region` VALUES ('4935', '2309', '七台河市', '230900', '黑龙江省七台河市', 'hljsqths');
INSERT INTO `t_system_region` VALUES ('4936', '230901', '市辖区', '230901', '黑龙江省七台河市市辖区', 'hljsqthssxq');
INSERT INTO `t_system_region` VALUES ('4937', '230902', '新兴区', '230902', '黑龙江省七台河市新兴区', 'hljsqthsxxq');
INSERT INTO `t_system_region` VALUES ('4938', '230903', '桃山区', '230903', '黑龙江省七台河市桃山区', 'hljsqthstsq');
INSERT INTO `t_system_region` VALUES ('4939', '230904', '茄子河区', '230904', '黑龙江省七台河市茄子河区', 'hljsqthsqzhq');
INSERT INTO `t_system_region` VALUES ('4940', '230921', '勃利县', '230921', '黑龙江省七台河市勃利县', 'hljsqthsblx');
INSERT INTO `t_system_region` VALUES ('4941', '2310', '牡丹江市', '231000', '黑龙江省牡丹江市', 'hljsmdjs');
INSERT INTO `t_system_region` VALUES ('4942', '231001', '市辖区', '231001', '黑龙江省牡丹江市市辖区', 'hljsmdjssxq');
INSERT INTO `t_system_region` VALUES ('4943', '231002', '东安区', '231002', '黑龙江省牡丹江市东安区', 'hljsmdjsdaq');
INSERT INTO `t_system_region` VALUES ('4944', '231003', '阳明区', '231003', '黑龙江省牡丹江市阳明区', 'hljsmdjsymq');
INSERT INTO `t_system_region` VALUES ('4945', '231004', '爱民区', '231004', '黑龙江省牡丹江市爱民区', 'hljsmdjsamq');
INSERT INTO `t_system_region` VALUES ('4946', '231005', '西安区', '231005', '黑龙江省牡丹江市西安区', 'hljsmdjsxaq');
INSERT INTO `t_system_region` VALUES ('4947', '231024', '东宁县', '231024', '黑龙江省牡丹江市东宁县', 'hljsmdjsdnx');
INSERT INTO `t_system_region` VALUES ('4948', '231025', '林口县', '231025', '黑龙江省牡丹江市林口县', 'hljsmdjslkx');
INSERT INTO `t_system_region` VALUES ('4949', '231081', '绥芬河市', '231081', '黑龙江省牡丹江市绥芬河市', 'hljsmdjssfhs');
INSERT INTO `t_system_region` VALUES ('4950', '231083', '海林市', '231083', '黑龙江省牡丹江市海林市', 'hljsmdjshls');
INSERT INTO `t_system_region` VALUES ('4951', '231084', '宁安市', '231084', '黑龙江省牡丹江市宁安市', 'hljsmdjsnas');
INSERT INTO `t_system_region` VALUES ('4952', '231085', '穆棱市', '231085', '黑龙江省牡丹江市穆棱市', 'hljsmdjsmls');
INSERT INTO `t_system_region` VALUES ('4953', '2311', '黑河市', '231100', '黑龙江省黑河市', 'hljshhs');
INSERT INTO `t_system_region` VALUES ('4954', '231101', '市辖区', '231101', '黑龙江省黑河市市辖区', 'hljshhssxq');
INSERT INTO `t_system_region` VALUES ('4955', '231102', '爱辉区', '231102', '黑龙江省黑河市爱辉区', 'hljshhsahq');
INSERT INTO `t_system_region` VALUES ('4956', '231121', '嫩江县', '231121', '黑龙江省黑河市嫩江县', 'hljshhsnjx');
INSERT INTO `t_system_region` VALUES ('4957', '231123', '逊克县', '231123', '黑龙江省黑河市逊克县', 'hljshhsxkx');
INSERT INTO `t_system_region` VALUES ('4958', '231124', '孙吴县', '231124', '黑龙江省黑河市孙吴县', 'hljshhsswx');
INSERT INTO `t_system_region` VALUES ('4959', '231181', '北安市', '231181', '黑龙江省黑河市北安市', 'hljshhsbas');
INSERT INTO `t_system_region` VALUES ('4960', '231182', '五大连池市', '231182', '黑龙江省黑河市五大连池市', 'hljshhswdlcs');
INSERT INTO `t_system_region` VALUES ('4961', '231183', '五大连池风景区', '231183', '黑龙江省黑河市五大连池风景区', 'hljshhswdlcfjq');
INSERT INTO `t_system_region` VALUES ('4962', '2312', '绥化市', '231200', '黑龙江省绥化市', 'hljsshs');
INSERT INTO `t_system_region` VALUES ('4963', '231201', '市辖区', '231201', '黑龙江省绥化市市辖区', 'hljsshssxq');
INSERT INTO `t_system_region` VALUES ('4964', '231202', '北林区', '231202', '黑龙江省绥化市北林区', 'hljsshsblq');
INSERT INTO `t_system_region` VALUES ('4965', '231221', '望奎县', '231221', '黑龙江省绥化市望奎县', 'hljsshswkx');
INSERT INTO `t_system_region` VALUES ('4966', '231222', '兰西县', '231222', '黑龙江省绥化市兰西县', 'hljsshslxx');
INSERT INTO `t_system_region` VALUES ('4967', '231223', '青冈县', '231223', '黑龙江省绥化市青冈县', 'hljsshsqgx');
INSERT INTO `t_system_region` VALUES ('4968', '231224', '庆安县', '231224', '黑龙江省绥化市庆安县', 'hljsshsqax');
INSERT INTO `t_system_region` VALUES ('4969', '231225', '明水县', '231225', '黑龙江省绥化市明水县', 'hljsshsmsx');
INSERT INTO `t_system_region` VALUES ('4970', '231226', '绥棱县', '231226', '黑龙江省绥化市绥棱县', 'hljsshsslx');
INSERT INTO `t_system_region` VALUES ('4971', '231281', '安达市', '231281', '黑龙江省绥化市安达市', 'hljsshsads');
INSERT INTO `t_system_region` VALUES ('4972', '231282', '肇东市', '231282', '黑龙江省绥化市肇东市', 'hljsshszds');
INSERT INTO `t_system_region` VALUES ('4973', '231283', '海伦市', '231283', '黑龙江省绥化市海伦市', 'hljsshshls');
INSERT INTO `t_system_region` VALUES ('4974', '2327', '大兴安岭地区', '232700', '黑龙江省大兴安岭地区', 'hljsdxaldq');
INSERT INTO `t_system_region` VALUES ('4975', '232701', '加格达奇区', '232701', '黑龙江省大兴安岭地区加格达奇区', 'hljsdxaldqjgdqq');
INSERT INTO `t_system_region` VALUES ('4976', '232702', '松岭区', '232702', '黑龙江省大兴安岭地区松岭区', 'hljsdxaldqslq');
INSERT INTO `t_system_region` VALUES ('4977', '232703', '新林区', '232703', '黑龙江省大兴安岭地区新林区', 'hljsdxaldqxlq');
INSERT INTO `t_system_region` VALUES ('4978', '232704', '呼中区', '232704', '黑龙江省大兴安岭地区呼中区', 'hljsdxaldqhzq');
INSERT INTO `t_system_region` VALUES ('4979', '232705', '大兴安岭地区本级', '232705', '黑龙江省大兴安岭地区大兴安岭地区本级', 'hljsdxaldqdxaldqbj');
INSERT INTO `t_system_region` VALUES ('4980', '232721', '呼玛县', '232721', '黑龙江省大兴安岭地区呼玛县', 'hljsdxaldqhmx');
INSERT INTO `t_system_region` VALUES ('4981', '232722', '塔河县', '232722', '黑龙江省大兴安岭地区塔河县', 'hljsdxaldqthx');
INSERT INTO `t_system_region` VALUES ('4982', '232723', '漠河县', '232723', '黑龙江省大兴安岭地区漠河县', 'hljsdxaldqmhx');
INSERT INTO `t_system_region` VALUES ('4983', '2331', '牡丹江林管局', '233100', '黑龙江省牡丹江林管局', 'hljsmdjlgj');
INSERT INTO `t_system_region` VALUES ('4984', '233102', '穆棱林业局', '233102', '黑龙江省牡丹江林管局穆棱林业局', 'hljsmdjlgjmllyj');
INSERT INTO `t_system_region` VALUES ('4985', '233103', '大海林林业局', '233103', '黑龙江省牡丹江林管局大海林林业局', 'hljsmdjlgjdhllyj');
INSERT INTO `t_system_region` VALUES ('4986', '233104', '海林林业局', '233104', '黑龙江省牡丹江林管局海林林业局', 'hljsmdjlgjhllyj');
INSERT INTO `t_system_region` VALUES ('4987', '233105', '柴河林业局', '233105', '黑龙江省牡丹江林管局柴河林业局', 'hljsmdjlgjchlyj');
INSERT INTO `t_system_region` VALUES ('4988', '233106', '东京城林业局', '233106', '黑龙江省牡丹江林管局东京城林业局', 'hljsmdjlgjdjclyj');
INSERT INTO `t_system_region` VALUES ('4989', '233107', '林口林业局', '233107', '黑龙江省牡丹江林管局林口林业局', 'hljsmdjlgjlklyj');
INSERT INTO `t_system_region` VALUES ('4990', '233108', '绥阳林业局', '233108', '黑龙江省牡丹江林管局绥阳林业局', 'hljsmdjlgjsylyj');
INSERT INTO `t_system_region` VALUES ('4991', '233109', '八面通林业局', '233109', '黑龙江省牡丹江林管局八面通林业局', 'hljsmdjlgjbmtlyj');
INSERT INTO `t_system_region` VALUES ('4992', '2332', '松花江林管局', '233200', '黑龙江省松花江林管局', 'hljsshjlgj');
INSERT INTO `t_system_region` VALUES ('4993', '233202', '山河屯林业局', '233202', '黑龙江省松花江林管局山河屯林业局', 'hljsshjlgjshtlyj');
INSERT INTO `t_system_region` VALUES ('4994', '233203', '通北林业局', '233203', '黑龙江省松花江林管局通北林业局', 'hljsshjlgjtblyj');
INSERT INTO `t_system_region` VALUES ('4995', '233204', '兴隆林业局', '233204', '黑龙江省松花江林管局兴隆林业局', 'hljsshjlgjxllyj');
INSERT INTO `t_system_region` VALUES ('4996', '233205', '苇河林业局', '233205', '黑龙江省松花江林管局苇河林业局', 'hljsshjlgjwhlyj');
INSERT INTO `t_system_region` VALUES ('4997', '233206', '亚布力林业局', '233206', '黑龙江省松花江林管局亚布力林业局', 'hljsshjlgjybllyj');
INSERT INTO `t_system_region` VALUES ('4998', '233207', '方正林业局', '233207', '黑龙江省松花江林管局方正林业局', 'hljsshjlgjfzlyj');
INSERT INTO `t_system_region` VALUES ('4999', '233208', '沾河林业局', '233208', '黑龙江省松花江林管局沾河林业局', 'hljsshjlgjzhlyj');
INSERT INTO `t_system_region` VALUES ('5000', '233209', '绥棱林业局', '233209', '黑龙江省松花江林管局绥棱林业局', 'hljsshjlgjsllyj');
INSERT INTO `t_system_region` VALUES ('5001', '2334', '合江林管局', '233400', '黑龙江省合江林管局', 'hljshjlgj');
INSERT INTO `t_system_region` VALUES ('5002', '233402', '双鸭山林业局', '233402', '黑龙江省合江林管局双鸭山林业局', 'hljshjlgjsyslyj');
INSERT INTO `t_system_region` VALUES ('5003', '233403', '鹤立林业局', '233403', '黑龙江省合江林管局鹤立林业局', 'hljshjlgjhllyj');
INSERT INTO `t_system_region` VALUES ('5004', '233404', '桦南林业局', '233404', '黑龙江省合江林管局桦南林业局', 'hljshjlgjhnlyj');
INSERT INTO `t_system_region` VALUES ('5005', '233405', '鹤北林业局', '233405', '黑龙江省合江林管局鹤北林业局', 'hljshjlgjhblyj');
INSERT INTO `t_system_region` VALUES ('5006', '233406', '东方红林业局', '233406', '黑龙江省合江林管局东方红林业局', 'hljshjlgjdfhlyj');
INSERT INTO `t_system_region` VALUES ('5007', '233407', '迎春林业局', '233407', '黑龙江省合江林管局迎春林业局', 'hljshjlgjyclyj');
INSERT INTO `t_system_region` VALUES ('5008', '233408', '清河林业局', '233408', '黑龙江省合江林管局清河林业局', 'hljshjlgjqhlyj');
INSERT INTO `t_system_region` VALUES ('5009', '2351', '黑龙江省农垦总局', '235100', '黑龙江省农垦总局', 'hljsnkzj');
INSERT INTO `t_system_region` VALUES ('5010', '235101', '黑龙江省农垦总局延军农场', '235101', '黑龙江省农垦总局延军农场', 'hljsnkzjyjnc');
INSERT INTO `t_system_region` VALUES ('5011', '235102', '黑龙江省农垦总局梧桐河农场', '235102', '黑龙江省农垦总局梧桐河农场', 'hljsnkzjwthnc');
INSERT INTO `t_system_region` VALUES ('5012', '235103', '黑龙江省农垦总局双鸭山农场', '235103', '黑龙江省农垦总局双鸭山农场', 'hljsnkzjsysnc');
INSERT INTO `t_system_region` VALUES ('5013', '235104', '黑龙江省农垦总局前哨农场', '235104', '黑龙江省农垦总局前哨农场', 'hljsnkzjqsnc');
INSERT INTO `t_system_region` VALUES ('5014', '235105', '黑龙江省农垦总局前锋农场', '235105', '黑龙江省农垦总局前锋农场', 'hljsnkzjqfnc');
INSERT INTO `t_system_region` VALUES ('5015', '235106', '黑龙江省农垦总局鸭绿河农场', '235106', '黑龙江省农垦总局鸭绿河农场', 'hljsnkzjylhnc');
INSERT INTO `t_system_region` VALUES ('5016', '235107', '黑龙江省农垦总局海伦农场', '235107', '黑龙江省农垦总局海伦农场', 'hljsnkzjhlnc');
INSERT INTO `t_system_region` VALUES ('5017', '235108', '黑龙江省农垦总局红旗农场', '235108', '黑龙江省农垦总局红旗农场', 'hljsnkzjhqnc');
INSERT INTO `t_system_region` VALUES ('5018', '235109', '黑龙江省农垦总局引龙河农场', '235109', '黑龙江省农垦总局引龙河农场', 'hljsnkzjylhnc');
INSERT INTO `t_system_region` VALUES ('5019', '235110', '黑龙江省农垦总局长水河农场', '235110', '黑龙江省农垦总局长水河农场', 'hljsnkzjzshnc');
INSERT INTO `t_system_region` VALUES ('5020', '235111', '黑龙江省农垦总局建设农场', '235111', '黑龙江省农垦总局建设农场', 'hljsnkzjjsnc');
INSERT INTO `t_system_region` VALUES ('5021', '235112', '黑龙江省农垦总局锦河农场', '235112', '黑龙江省农垦总局锦河农场', 'hljsnkzjjhnc');
INSERT INTO `t_system_region` VALUES ('5022', '235113', '黑龙江省农垦总局跃进农场', '235113', '黑龙江省农垦总局跃进农场', 'hljsnkzjyjnc');
INSERT INTO `t_system_region` VALUES ('5023', '235114', '黑龙江省农垦总局泰来农场', '235114', '黑龙江省农垦总局泰来农场', 'hljsnkzjtlnc');
INSERT INTO `t_system_region` VALUES ('5024', '235115', '黑龙江省农垦总局富裕牧场', '235115', '黑龙江省农垦总局富裕牧场', 'hljsnkzjfymc');
INSERT INTO `t_system_region` VALUES ('5025', '235116', '黑龙江省农垦总局五九七农场', '235116', '黑龙江省农垦总局五九七农场', 'hljsnkzjwjqnc');
INSERT INTO `t_system_region` VALUES ('5026', '235117', '黑龙江省农垦总局红五月农场', '235117', '黑龙江省农垦总局红五月农场', 'hljsnkzjhwync');
INSERT INTO `t_system_region` VALUES ('5027', '235118', '黑龙江省农垦总局建边农场', '235118', '黑龙江省农垦总局建边农场', 'hljsnkzjjbnc');
INSERT INTO `t_system_region` VALUES ('5028', '235119', '黑龙江省农垦总局和平牧场', '235119', '黑龙江省农垦总局和平牧场', 'hljsnkzjhpmc');
INSERT INTO `t_system_region` VALUES ('5029', '235120', '黑龙江省农垦总局岔林河农场', '235120', '黑龙江省农垦总局岔林河农场', 'hljsnkzjclhnc');
INSERT INTO `t_system_region` VALUES ('5030', '235121', '黑龙江省农垦总局八五一零农场', '235121', '黑龙江省农垦总局八五一零农场', 'hljsnkzjbwylnc');
INSERT INTO `t_system_region` VALUES ('5031', '235122', '黑龙江省农垦总局红色边疆农场', '235122', '黑龙江省农垦总局红色边疆农场', 'hljsnkzjhsbjnc');
INSERT INTO `t_system_region` VALUES ('5032', '235123', '黑龙江省农垦总局龙镇农场', '235123', '黑龙江省农垦总局龙镇农场', 'hljsnkzjlznc');
INSERT INTO `t_system_region` VALUES ('5033', '235124', '黑龙江省农垦总局襄河农场', '235124', '黑龙江省农垦总局襄河农场', 'hljsnkzjxhnc');
INSERT INTO `t_system_region` VALUES ('5034', '235125', '黑龙江省农垦总局逊克农场', '235125', '黑龙江省农垦总局逊克农场', 'hljsnkzjxknc');
INSERT INTO `t_system_region` VALUES ('5035', '235126', '黑龙江省农垦总局鹤山农场', '235126', '黑龙江省农垦总局鹤山农场', 'hljsnkzjhsnc');
INSERT INTO `t_system_region` VALUES ('5036', '235127', '黑龙江省农垦总局嫩江农场', '235127', '黑龙江省农垦总局嫩江农场', 'hljsnkzjnjnc');
INSERT INTO `t_system_region` VALUES ('5037', '235128', '黑龙江省农垦总局宝泉岭农场', '235128', '黑龙江省农垦总局宝泉岭农场', 'hljsnkzjbqlnc');
INSERT INTO `t_system_region` VALUES ('5038', '235129', '黑龙江省农垦总局二九零农场', '235129', '黑龙江省农垦总局二九零农场', 'hljsnkzjejlnc');
INSERT INTO `t_system_region` VALUES ('5039', '235130', '黑龙江省农垦总局新华农场', '235130', '黑龙江省农垦总局新华农场', 'hljsnkzjxhnc');
INSERT INTO `t_system_region` VALUES ('5040', '235131', '黑龙江省农垦总局军川农场', '235131', '黑龙江省农垦总局军川农场', 'hljsnkzjjcnc');
INSERT INTO `t_system_region` VALUES ('5041', '235132', '黑龙江省农垦总局共青农场', '235132', '黑龙江省农垦总局共青农场', 'hljsnkzjgqnc');
INSERT INTO `t_system_region` VALUES ('5042', '235133', '黑龙江省农垦总局绥滨农场', '235133', '黑龙江省农垦总局绥滨农场', 'hljsnkzjsbnc');
INSERT INTO `t_system_region` VALUES ('5043', '235134', '黑龙江省农垦总局江滨农场　', '235134', '黑龙江省农垦总局江滨农场　', 'hljsnkzjjbnc');
INSERT INTO `t_system_region` VALUES ('5044', '235135', '黑龙江省农垦总局普阳农场', '235135', '黑龙江省农垦总局普阳农场', 'hljsnkzjpync');
INSERT INTO `t_system_region` VALUES ('5045', '235136', '黑龙江省农垦总局名山农场', '235136', '黑龙江省农垦总局名山农场', 'hljsnkzjmsnc');
INSERT INTO `t_system_region` VALUES ('5046', '235137', '黑龙江省农垦总局汤原农场', '235137', '黑龙江省农垦总局汤原农场', 'hljsnkzjtync');
INSERT INTO `t_system_region` VALUES ('5047', '235138', '黑龙江省农垦总局依兰农场', '235138', '黑龙江省农垦总局依兰农场', 'hljsnkzjylnc');
INSERT INTO `t_system_region` VALUES ('5048', '235139', '黑龙江省农垦总局八五二农场', '235139', '黑龙江省农垦总局八五二农场', 'hljsnkzjbwenc');
INSERT INTO `t_system_region` VALUES ('5049', '235140', '黑龙江省农垦总局八五三农场', '235140', '黑龙江省农垦总局八五三农场', 'hljsnkzjbwsnc');
INSERT INTO `t_system_region` VALUES ('5050', '235141', '黑龙江省农垦总局饶河农场', '235141', '黑龙江省农垦总局饶河农场', 'hljsnkzjrhnc');
INSERT INTO `t_system_region` VALUES ('5051', '235142', '黑龙江省农垦总局宝山农场', '235142', '黑龙江省农垦总局宝山农场', 'hljsnkzjbsnc');
INSERT INTO `t_system_region` VALUES ('5052', '235143', '黑龙江省农垦总局北兴农场', '235143', '黑龙江省农垦总局北兴农场', 'hljsnkzjbxnc');
INSERT INTO `t_system_region` VALUES ('5053', '235144', '黑龙江省农垦总局老柞山金矿', '235144', '黑龙江省农垦总局老柞山金矿', 'hljsnkzjlzsjk');
INSERT INTO `t_system_region` VALUES ('5054', '235145', '黑龙江省农垦总局红旗岭农场', '235145', '黑龙江省农垦总局红旗岭农场', 'hljsnkzjhqlnc');
INSERT INTO `t_system_region` VALUES ('5055', '235146', '黑龙江省农垦总局江川农场', '235146', '黑龙江省农垦总局江川农场', 'hljsnkzjjcnc');
INSERT INTO `t_system_region` VALUES ('5056', '235147', '黑龙江省农垦总局曙光农场', '235147', '黑龙江省农垦总局曙光农场', 'hljsnkzjsgnc');
INSERT INTO `t_system_region` VALUES ('5057', '235148', '黑龙江省农垦总局二九一农场', '235148', '黑龙江省农垦总局二九一农场', 'hljsnkzjejync');
INSERT INTO `t_system_region` VALUES ('5058', '235149', '黑龙江省农垦总局七星农场', '235149', '黑龙江省农垦总局七星农场', 'hljsnkzjqxnc');
INSERT INTO `t_system_region` VALUES ('5059', '235150', '黑龙江省农垦总局八五九农场', '235150', '黑龙江省农垦总局八五九农场', 'hljsnkzjbwjnc');
INSERT INTO `t_system_region` VALUES ('5060', '235151', '黑龙江省农垦总局创业农场', '235151', '黑龙江省农垦总局创业农场', 'hljsnkzjcync');
INSERT INTO `t_system_region` VALUES ('5061', '235152', '黑龙江省农垦总局大兴农场', '235152', '黑龙江省农垦总局大兴农场', 'hljsnkzjdxnc');
INSERT INTO `t_system_region` VALUES ('5062', '235153', '黑龙江省农垦总局二道河农场', '235153', '黑龙江省农垦总局二道河农场', 'hljsnkzjedhnc');
INSERT INTO `t_system_region` VALUES ('5063', '235154', '黑龙江省农垦总局浓江农场', '235154', '黑龙江省农垦总局浓江农场', 'hljsnkzjnjnc');
INSERT INTO `t_system_region` VALUES ('5064', '235155', '黑龙江省农垦总局红卫农场', '235155', '黑龙江省农垦总局红卫农场', 'hljsnkzjhwnc');
INSERT INTO `t_system_region` VALUES ('5065', '235156', '黑龙江省农垦总局洪河农场', '235156', '黑龙江省农垦总局洪河农场', 'hljsnkzjhhnc');
INSERT INTO `t_system_region` VALUES ('5066', '235157', '黑龙江省农垦总局青龙山农场', '235157', '黑龙江省农垦总局青龙山农场', 'hljsnkzjqlsnc');
INSERT INTO `t_system_region` VALUES ('5067', '235158', '黑龙江省农垦总局前进农场　', '235158', '黑龙江省农垦总局前进农场　', 'hljsnkzjqjnc');
INSERT INTO `t_system_region` VALUES ('5068', '235159', '黑龙江省农垦总局勤得利农场　', '235159', '黑龙江省农垦总局勤得利农场　', 'hljsnkzjqdlnc');
INSERT INTO `t_system_region` VALUES ('5069', '235160', '黑龙江省农垦总局胜利农场', '235160', '黑龙江省农垦总局胜利农场', 'hljsnkzjslnc');
INSERT INTO `t_system_region` VALUES ('5070', '235161', '黑龙江省农垦总局八五零农场　', '235161', '黑龙江省农垦总局八五零农场　', 'hljsnkzjbwlnc');
INSERT INTO `t_system_region` VALUES ('5071', '235162', '黑龙江省农垦总局八五四农场', '235162', '黑龙江省农垦总局八五四农场', 'hljsnkzjbwsnc');
INSERT INTO `t_system_region` VALUES ('5072', '235163', '黑龙江省农垦总局八五五农场', '235163', '黑龙江省农垦总局八五五农场', 'hljsnkzjbwwnc');
INSERT INTO `t_system_region` VALUES ('5073', '235164', '黑龙江省农垦总局八五六农场', '235164', '黑龙江省农垦总局八五六农场', 'hljsnkzjbwlnc');
INSERT INTO `t_system_region` VALUES ('5074', '235165', '黑龙江省农垦总局八五七农场', '235165', '黑龙江省农垦总局八五七农场', 'hljsnkzjbwqnc');
INSERT INTO `t_system_region` VALUES ('5075', '235166', '黑龙江省农垦总局八五八农场', '235166', '黑龙江省农垦总局八五八农场', 'hljsnkzjbwbnc');
INSERT INTO `t_system_region` VALUES ('5076', '235167', '黑龙江省农垦总局八五一一农场', '235167', '黑龙江省农垦总局八五一一农场', 'hljsnkzjbwyync');
INSERT INTO `t_system_region` VALUES ('5077', '235168', '黑龙江省农垦总局庆丰农场', '235168', '黑龙江省农垦总局庆丰农场', 'hljsnkzjqfnc');
INSERT INTO `t_system_region` VALUES ('5078', '235169', '黑龙江省农垦总局云山农场', '235169', '黑龙江省农垦总局云山农场', 'hljsnkzjysnc');
INSERT INTO `t_system_region` VALUES ('5079', '235170', '黑龙江省农垦总局兴凯湖农场', '235170', '黑龙江省农垦总局兴凯湖农场', 'hljsnkzjxkhnc');
INSERT INTO `t_system_region` VALUES ('5080', '235171', '黑龙江省农垦总局海林农场', '235171', '黑龙江省农垦总局海林农场', 'hljsnkzjhlnc');
INSERT INTO `t_system_region` VALUES ('5081', '235172', '黑龙江省农垦总局宁安农场', '235172', '黑龙江省农垦总局宁安农场', 'hljsnkzjnanc');
INSERT INTO `t_system_region` VALUES ('5082', '235173', '黑龙江省农垦总局龙门农场', '235173', '黑龙江省农垦总局龙门农场', 'hljsnkzjlmnc');
INSERT INTO `t_system_region` VALUES ('5083', '235174', '黑龙江省农垦总局二龙山农场', '235174', '黑龙江省农垦总局二龙山农场', 'hljsnkzjelsnc');
INSERT INTO `t_system_region` VALUES ('5084', '235175', '黑龙江省农垦总局尾山农场', '235175', '黑龙江省农垦总局尾山农场', 'hljsnkzjwsnc');
INSERT INTO `t_system_region` VALUES ('5085', '235176', '黑龙江省农垦总局格球山农场　', '235176', '黑龙江省农垦总局格球山农场　', 'hljsnkzjgqsnc');
INSERT INTO `t_system_region` VALUES ('5086', '235177', '黑龙江省农垦总局赵光农场', '235177', '黑龙江省农垦总局赵光农场', 'hljsnkzjzgnc');
INSERT INTO `t_system_region` VALUES ('5087', '235178', '黑龙江省农垦总局红星农场', '235178', '黑龙江省农垦总局红星农场', 'hljsnkzjhxnc');
INSERT INTO `t_system_region` VALUES ('5088', '235179', '黑龙江省农垦总局大西江农场', '235179', '黑龙江省农垦总局大西江农场', 'hljsnkzjdxjnc');
INSERT INTO `t_system_region` VALUES ('5089', '235180', '黑龙江省农垦总局尖山农场', '235180', '黑龙江省农垦总局尖山农场', 'hljsnkzjjsnc');
INSERT INTO `t_system_region` VALUES ('5090', '235181', '黑龙江省农垦总局山河农场', '235181', '黑龙江省农垦总局山河农场', 'hljsnkzjshnc');
INSERT INTO `t_system_region` VALUES ('5091', '235182', '黑龙江省农垦总局嫩北农场', '235182', '黑龙江省农垦总局嫩北农场', 'hljsnkzjnbnc');
INSERT INTO `t_system_region` VALUES ('5092', '235183', '黑龙江省农垦总局哈拉海农场', '235183', '黑龙江省农垦总局哈拉海农场', 'hljsnkzjhlhnc');
INSERT INTO `t_system_region` VALUES ('5093', '235184', '黑龙江省农垦总局七星泡农场', '235184', '黑龙江省农垦总局七星泡农场', 'hljsnkzjqxpnc');
INSERT INTO `t_system_region` VALUES ('5094', '235185', '黑龙江省农垦总局荣军农场', '235185', '黑龙江省农垦总局荣军农场', 'hljsnkzjrjnc');
INSERT INTO `t_system_region` VALUES ('5095', '235186', '黑龙江省农垦总局查哈阳农场', '235186', '黑龙江省农垦总局查哈阳农场', 'hljsnkzjchync');
INSERT INTO `t_system_region` VALUES ('5096', '235187', '黑龙江省农垦总局克山农场　', '235187', '黑龙江省农垦总局克山农场　', 'hljsnkzjksnc');
INSERT INTO `t_system_region` VALUES ('5097', '235188', '黑龙江省农垦总局绿色草原牧场', '235188', '黑龙江省农垦总局绿色草原牧场', 'hljsnkzjlscymc');
INSERT INTO `t_system_region` VALUES ('5098', '235189', '黑龙江省农垦总局依安农场', '235189', '黑龙江省农垦总局依安农场', 'hljsnkzjyanc');
INSERT INTO `t_system_region` VALUES ('5099', '235190', '黑龙江省农垦总局巨浪牧场　', '235190', '黑龙江省农垦总局巨浪牧场　', 'hljsnkzjjlmc');
INSERT INTO `t_system_region` VALUES ('5100', '235191', '黑龙江省农垦总局铁力农场', '235191', '黑龙江省农垦总局铁力农场', 'hljsnkzjtlnc');
INSERT INTO `t_system_region` VALUES ('5101', '235192', '黑龙江省农垦总局嘉荫农场', '235192', '黑龙江省农垦总局嘉荫农场', 'hljsnkzjjync');
INSERT INTO `t_system_region` VALUES ('5102', '235193', '黑龙江省农垦总局安达畜牧场', '235193', '黑龙江省农垦总局安达畜牧场', 'hljsnkzjadcmc');
INSERT INTO `t_system_region` VALUES ('5103', '235194', '黑龙江省农垦总局肇源农场', '235194', '黑龙江省农垦总局肇源农场', 'hljsnkzjzync');
INSERT INTO `t_system_region` VALUES ('5104', '235195', '黑龙江省农垦总局红光农场', '235195', '黑龙江省农垦总局红光农场', 'hljsnkzjhgnc');
INSERT INTO `t_system_region` VALUES ('5105', '235196', '黑龙江省农垦总局绥棱农场', '235196', '黑龙江省农垦总局绥棱农场', 'hljsnkzjslnc');
INSERT INTO `t_system_region` VALUES ('5106', '235197', '黑龙江省农垦总局柳河农场　', '235197', '黑龙江省农垦总局柳河农场　', 'hljsnkzjlhnc');
INSERT INTO `t_system_region` VALUES ('5107', '235198', '黑龙江省农垦总局香坊实验农场', '235198', '黑龙江省农垦总局香坊实验农场', 'hljsnkzjxfsync');
INSERT INTO `t_system_region` VALUES ('5108', '235199', '黑龙江省农垦总局闫家岗农场', '235199', '黑龙江省农垦总局闫家岗农场', 'hljsnkzjyjgnc');
INSERT INTO `t_system_region` VALUES ('5109', '2351A1', '黑龙江省农垦总局庆阳农场', '2351A1', '黑龙江省农垦总局庆阳农场', 'hljsnkzjqync');
INSERT INTO `t_system_region` VALUES ('5110', '2351A2', '黑龙江省农垦总局沙河农场', '2351A2', '黑龙江省农垦总局沙河农场', 'hljsnkzjshnc');
INSERT INTO `t_system_region` VALUES ('5111', '2351A3', '黑龙江省农垦总局青年农场', '2351A3', '黑龙江省农垦总局青年农场', 'hljsnkzjqnnc');
INSERT INTO `t_system_region` VALUES ('5112', '2351A4', '黑龙江省农垦总局四方山农场', '2351A4', '黑龙江省农垦总局四方山农场', 'hljsnkzjsfsnc');
INSERT INTO `t_system_region` VALUES ('5113', '2351A5', '黑龙江省农垦总局松花江农场', '2351A5', '黑龙江省农垦总局松花江农场', 'hljsnkzjshjnc');
INSERT INTO `t_system_region` VALUES ('5114', '2351A6', '黑龙江省农垦总局小岭水泥厂', '2351A6', '黑龙江省农垦总局小岭水泥厂', 'hljsnkzjxlsnc');
INSERT INTO `t_system_region` VALUES ('5115', '2351A7', '黑龙江省农垦总局浩良河化肥厂', '2351A7', '黑龙江省农垦总局浩良河化肥厂', 'hljsnkzjhlhhfc');
INSERT INTO `t_system_region` VALUES ('5116', '2351A8', '黑龙江省农垦总局局直', '2351A8', '黑龙江省农垦总局局直', 'hljsnkzjjz');
INSERT INTO `t_system_region` VALUES ('5117', '2351A9', '黑龙江省农垦总局宝泉岭分局局直', '2351A9', '黑龙江省农垦总局宝泉岭分局局直', 'hljsnkzjbqlfjjz');
INSERT INTO `t_system_region` VALUES ('5118', '2351B1', '黑龙江省农垦总局红兴隆分局局直', '2351B1', '黑龙江省农垦总局红兴隆分局局直', 'hljsnkzjhxlfjjz');
INSERT INTO `t_system_region` VALUES ('5119', '2351B2', '黑龙江省农垦总局建三江分局局直', '2351B2', '黑龙江省农垦总局建三江分局局直', 'hljsnkzjjsjfjjz');
INSERT INTO `t_system_region` VALUES ('5120', '2351B3', '黑龙江省农垦总局牡丹江分局局直', '2351B3', '黑龙江省农垦总局牡丹江分局局直', 'hljsnkzjmdjfjjz');
INSERT INTO `t_system_region` VALUES ('5121', '2351B4', '黑龙江省农垦总局北安分局局直', '2351B4', '黑龙江省农垦总局北安分局局直', 'hljsnkzjbafjjz');
INSERT INTO `t_system_region` VALUES ('5122', '2351B5', '黑龙江省农垦总局九三分局局直', '2351B5', '黑龙江省农垦总局九三分局局直', 'hljsnkzjjsfjjz');
INSERT INTO `t_system_region` VALUES ('5123', '2351B6', '黑龙江省农垦总局齐齐哈尔分局局直', '2351B6', '黑龙江省农垦总局齐齐哈尔分局局直', 'hljsnkzjqqhefjjz');
INSERT INTO `t_system_region` VALUES ('5124', '2351B7', '黑龙江省农垦总局绥化分局局直', '2351B7', '黑龙江省农垦总局绥化分局局直', 'hljsnkzjshfjjz');
INSERT INTO `t_system_region` VALUES ('5125', '2351B8', '黑龙江省农垦总局哈尔滨分局局直', '2351B8', '黑龙江省农垦总局哈尔滨分局局直', 'hljsnkzjhebfjjz');
INSERT INTO `t_system_region` VALUES ('5126', '31', '上海市', '310000', '上海市', 'shs');
INSERT INTO `t_system_region` VALUES ('5127', '3101', '市辖区', '310100', '上海市市辖区', 'shssxq');
INSERT INTO `t_system_region` VALUES ('5128', '310101', '黄浦区', '310101', '上海市黄浦区', 'shshpq');
INSERT INTO `t_system_region` VALUES ('5129', '310103', '卢湾区', '310103', '上海市卢湾区', 'shslwq');
INSERT INTO `t_system_region` VALUES ('5130', '310104', '徐汇区', '310104', '上海市徐汇区', 'shsxhq');
INSERT INTO `t_system_region` VALUES ('5131', '310105', '长宁区', '310105', '上海市长宁区', 'shsznq');
INSERT INTO `t_system_region` VALUES ('5132', '310106', '静安区', '310106', '上海市静安区', 'shsjaq');
INSERT INTO `t_system_region` VALUES ('5133', '310107', '普陀区', '310107', '上海市普陀区', 'shsptq');
INSERT INTO `t_system_region` VALUES ('5134', '310108', '闸北区', '310108', '上海市闸北区', 'shszbq');
INSERT INTO `t_system_region` VALUES ('5135', '310109', '虹口区', '310109', '上海市虹口区', 'shshkq');
INSERT INTO `t_system_region` VALUES ('5136', '310110', '杨浦区', '310110', '上海市杨浦区', 'shsypq');
INSERT INTO `t_system_region` VALUES ('5137', '310112', '闵行区', '310112', '上海市闵行区', 'shsmxq');
INSERT INTO `t_system_region` VALUES ('5138', '310113', '宝山区', '310113', '上海市宝山区', 'shsbsq');
INSERT INTO `t_system_region` VALUES ('5139', '310114', '嘉定区', '310114', '上海市嘉定区', 'shsjdq');
INSERT INTO `t_system_region` VALUES ('5140', '310115', '浦东新区', '310115', '上海市浦东新区', 'shspdxq');
INSERT INTO `t_system_region` VALUES ('5141', '310116', '金山区', '310116', '上海市金山区', 'shsjsq');
INSERT INTO `t_system_region` VALUES ('5142', '310117', '松江区', '310117', '上海市松江区', 'shssjq');
INSERT INTO `t_system_region` VALUES ('5143', '310118', '青浦区', '310118', '上海市青浦区', 'shsqpq');
INSERT INTO `t_system_region` VALUES ('5144', '310119', '南汇区', '310119', '上海市南汇区', 'shsnhq');
INSERT INTO `t_system_region` VALUES ('5145', '310120', '奉贤区', '310120', '上海市奉贤区', 'shsfxq');
INSERT INTO `t_system_region` VALUES ('5146', '3102', '县', '310200', '上海市县', 'shsx');
INSERT INTO `t_system_region` VALUES ('5147', '310230', '崇明县', '310230', '上海市崇明县', 'shscmx');
INSERT INTO `t_system_region` VALUES ('5148', '32', '江苏省', '320000', '江苏省', 'jss');
INSERT INTO `t_system_region` VALUES ('5149', '3201', '南京市', '320100', '江苏省南京市', 'jssnjs');
INSERT INTO `t_system_region` VALUES ('5150', '320101', '市辖区', '320101', '江苏省南京市市辖区', 'jssnjssxq');
INSERT INTO `t_system_region` VALUES ('5151', '320102', '玄武区', '320102', '江苏省南京市玄武区', 'jssnjsxwq');
INSERT INTO `t_system_region` VALUES ('5152', '320103', '白下区', '320103', '江苏省南京市白下区', 'jssnjsbxq');
INSERT INTO `t_system_region` VALUES ('5153', '320104', '秦淮区', '320104', '江苏省南京市秦淮区', 'jssnjsqhq');
INSERT INTO `t_system_region` VALUES ('5154', '320105', '建邺区', '320105', '江苏省南京市建邺区', 'jssnjsjyq');
INSERT INTO `t_system_region` VALUES ('5155', '320106', '鼓楼区', '320106', '江苏省南京市鼓楼区', 'jssnjsglq');
INSERT INTO `t_system_region` VALUES ('5156', '320107', '下关区', '320107', '江苏省南京市下关区', 'jssnjsxgq');
INSERT INTO `t_system_region` VALUES ('5157', '320111', '浦口区', '320111', '江苏省南京市浦口区', 'jssnjspkq');
INSERT INTO `t_system_region` VALUES ('5158', '320113', '栖霞区', '320113', '江苏省南京市栖霞区', 'jssnjsqxq');
INSERT INTO `t_system_region` VALUES ('5159', '320114', '雨花台区', '320114', '江苏省南京市雨花台区', 'jssnjsyhtq');
INSERT INTO `t_system_region` VALUES ('5160', '320115', '江宁区', '320115', '江苏省南京市江宁区', 'jssnjsjnq');
INSERT INTO `t_system_region` VALUES ('5161', '320116', '六合区', '320116', '江苏省南京市六合区', 'jssnjslhq');
INSERT INTO `t_system_region` VALUES ('5162', '320124', '溧水县', '320124', '江苏省南京市溧水县', 'jssnjslsx');
INSERT INTO `t_system_region` VALUES ('5163', '320125', '高淳县', '320125', '江苏省南京市高淳县', 'jssnjsgcx');
INSERT INTO `t_system_region` VALUES ('5164', '3202', '无锡市', '320200', '江苏省无锡市', 'jsswxs');
INSERT INTO `t_system_region` VALUES ('5165', '320201', '市辖区', '320201', '江苏省无锡市市辖区', 'jsswxssxq');
INSERT INTO `t_system_region` VALUES ('5166', '320202', '崇安区', '320202', '江苏省无锡市崇安区', 'jsswxscaq');
INSERT INTO `t_system_region` VALUES ('5167', '320203', '南长区', '320203', '江苏省无锡市南长区', 'jsswxsnzq');
INSERT INTO `t_system_region` VALUES ('5168', '320204', '北塘区', '320204', '江苏省无锡市北塘区', 'jsswxsbtq');
INSERT INTO `t_system_region` VALUES ('5169', '320205', '锡山区', '320205', '江苏省无锡市锡山区', 'jsswxsxsq');
INSERT INTO `t_system_region` VALUES ('5170', '320206', '惠山区', '320206', '江苏省无锡市惠山区', 'jsswxshsq');
INSERT INTO `t_system_region` VALUES ('5171', '320211', '滨湖区', '320211', '江苏省无锡市滨湖区', 'jsswxsbhq');
INSERT INTO `t_system_region` VALUES ('5172', '320233', '无锡市新区', '320233', '江苏省无锡市无锡市新区', 'jsswxswxsxq');
INSERT INTO `t_system_region` VALUES ('5173', '320281', '江阴市', '320281', '江苏省无锡市江阴市', 'jsswxsjys');
INSERT INTO `t_system_region` VALUES ('5174', '320282', '宜兴市', '320282', '江苏省无锡市宜兴市', 'jsswxsyxs');
INSERT INTO `t_system_region` VALUES ('5175', '3203', '徐州市', '320300', '江苏省徐州市', 'jssxzs');
INSERT INTO `t_system_region` VALUES ('5176', '320301', '市辖区', '320301', '江苏省徐州市市辖区', 'jssxzssxq');
INSERT INTO `t_system_region` VALUES ('5177', '320302', '鼓楼区', '320302', '江苏省徐州市鼓楼区', 'jssxzsglq');
INSERT INTO `t_system_region` VALUES ('5178', '320303', '云龙区', '320303', '江苏省徐州市云龙区', 'jssxzsylq');
INSERT INTO `t_system_region` VALUES ('5179', '320304', '九里区', '320304', '江苏省徐州市九里区', 'jssxzsjlq');
INSERT INTO `t_system_region` VALUES ('5180', '320305', '贾汪区', '320305', '江苏省徐州市贾汪区', 'jssxzsjwq');
INSERT INTO `t_system_region` VALUES ('5181', '320307', '徐州市经济开发区', '320307', '江苏省徐州市徐州市经济开发区', 'jssxzsxzsjjkfq');
INSERT INTO `t_system_region` VALUES ('5182', '320311', '泉山区', '320311', '江苏省徐州市泉山区', 'jssxzsqsq');
INSERT INTO `t_system_region` VALUES ('5183', '320321', '丰县', '320321', '江苏省徐州市丰县', 'jssxzsfx');
INSERT INTO `t_system_region` VALUES ('5184', '320322', '沛县', '320322', '江苏省徐州市沛县', 'jssxzspx');
INSERT INTO `t_system_region` VALUES ('5185', '320323', '铜山县', '320323', '江苏省徐州市铜山县', 'jssxzstsx');
INSERT INTO `t_system_region` VALUES ('5186', '320324', '睢宁县', '320324', '江苏省徐州市睢宁县', 'jssxzssnx');
INSERT INTO `t_system_region` VALUES ('5187', '320381', '新沂市', '320381', '江苏省徐州市新沂市', 'jssxzsxys');
INSERT INTO `t_system_region` VALUES ('5188', '320382', '邳州市', '320382', '江苏省徐州市邳州市', 'jssxzspzs');
INSERT INTO `t_system_region` VALUES ('5189', '3204', '常州市', '320400', '江苏省常州市', 'jssczs');
INSERT INTO `t_system_region` VALUES ('5190', '320401', '市辖区', '320401', '江苏省常州市市辖区', 'jssczssxq');
INSERT INTO `t_system_region` VALUES ('5191', '320402', '天宁区', '320402', '江苏省常州市天宁区', 'jssczstnq');
INSERT INTO `t_system_region` VALUES ('5192', '320404', '钟楼区', '320404', '江苏省常州市钟楼区', 'jssczszlq');
INSERT INTO `t_system_region` VALUES ('5193', '320405', '戚墅堰区', '320405', '江苏省常州市戚墅堰区', 'jssczsqsyq');
INSERT INTO `t_system_region` VALUES ('5194', '320411', '新北区', '320411', '江苏省常州市新北区', 'jssczsxbq');
INSERT INTO `t_system_region` VALUES ('5195', '320412', '武进区', '320412', '江苏省常州市武进区', 'jssczswjq');
INSERT INTO `t_system_region` VALUES ('5196', '320481', '溧阳市', '320481', '江苏省常州市溧阳市', 'jssczslys');
INSERT INTO `t_system_region` VALUES ('5197', '320482', '金坛市', '320482', '江苏省常州市金坛市', 'jssczsjts');
INSERT INTO `t_system_region` VALUES ('5198', '3205', '苏州市', '320500', '江苏省苏州市', 'jssszs');
INSERT INTO `t_system_region` VALUES ('5199', '320501', '市辖区', '320501', '江苏省苏州市市辖区', 'jssszssxq');
INSERT INTO `t_system_region` VALUES ('5200', '320502', '沧浪区', '320502', '江苏省苏州市沧浪区', 'jssszsclq');
INSERT INTO `t_system_region` VALUES ('5201', '320503', '平江区', '320503', '江苏省苏州市平江区', 'jssszspjq');
INSERT INTO `t_system_region` VALUES ('5202', '320504', '金阊区', '320504', '江苏省苏州市金阊区', 'jssszsjcq');
INSERT INTO `t_system_region` VALUES ('5203', '320505', '虎丘区', '320505', '江苏省苏州市虎丘区', 'jssszshqq');
INSERT INTO `t_system_region` VALUES ('5204', '320506', '吴中区', '320506', '江苏省苏州市吴中区', 'jssszswzq');
INSERT INTO `t_system_region` VALUES ('5205', '320507', '相城区', '320507', '江苏省苏州市相城区', 'jssszsxcq');
INSERT INTO `t_system_region` VALUES ('5206', '320508', '苏州市高新区', '320508', '江苏省苏州市高新区', 'jssszsgxq');
INSERT INTO `t_system_region` VALUES ('5207', '320581', '常熟市', '320581', '江苏省苏州市常熟市', 'jssszscss');
INSERT INTO `t_system_region` VALUES ('5208', '320582', '张家港市', '320582', '江苏省苏州市张家港市', 'jssszszjgs');
INSERT INTO `t_system_region` VALUES ('5209', '320583', '昆山市', '320583', '江苏省苏州市昆山市', 'jssszskss');
INSERT INTO `t_system_region` VALUES ('5210', '320584', '吴江市', '320584', '江苏省苏州市吴江市', 'jssszswjs');
INSERT INTO `t_system_region` VALUES ('5211', '320585', '太仓市', '320585', '江苏省苏州市太仓市', 'jssszstcs');
INSERT INTO `t_system_region` VALUES ('5212', '3206', '南通市', '320600', '江苏省南通市', 'jssnts');
INSERT INTO `t_system_region` VALUES ('5213', '320601', '市辖区', '320601', '江苏省南通市市辖区', 'jssntssxq');
INSERT INTO `t_system_region` VALUES ('5214', '320602', '崇川区', '320602', '江苏省南通市崇川区', 'jssntsccq');
INSERT INTO `t_system_region` VALUES ('5215', '320611', '港闸区', '320611', '江苏省南通市港闸区', 'jssntsgzq');
INSERT INTO `t_system_region` VALUES ('5216', '320618', '南通市开发区', '320618', '江苏省南通市南通市开发区', 'jssntsntskfq');
INSERT INTO `t_system_region` VALUES ('5217', '320621', '海安县', '320621', '江苏省南通市海安县', 'jssntshax');
INSERT INTO `t_system_region` VALUES ('5218', '320623', '如东县', '320623', '江苏省南通市如东县', 'jssntsrdx');
INSERT INTO `t_system_region` VALUES ('5219', '320681', '启东市', '320681', '江苏省南通市启东市', 'jssntsqds');
INSERT INTO `t_system_region` VALUES ('5220', '320682', '如皋市', '320682', '江苏省南通市如皋市', 'jssntsrgs');
INSERT INTO `t_system_region` VALUES ('5221', '320683', '通州市', '320683', '江苏省南通市通州市', 'jssntstzs');
INSERT INTO `t_system_region` VALUES ('5222', '320684', '海门市', '320684', '江苏省南通市海门市', 'jssntshms');
INSERT INTO `t_system_region` VALUES ('5223', '3207', '连云港市', '320700', '江苏省连云港市', 'jsslygs');
INSERT INTO `t_system_region` VALUES ('5224', '320701', '市辖区', '320701', '江苏省连云港市市辖区', 'jsslygssxq');
INSERT INTO `t_system_region` VALUES ('5225', '320703', '连云区', '320703', '江苏省连云港市连云区', 'jsslygslyq');
INSERT INTO `t_system_region` VALUES ('5226', '320705', '新浦区', '320705', '江苏省连云港市新浦区', 'jsslygsxpq');
INSERT INTO `t_system_region` VALUES ('5227', '320706', '海州区', '320706', '江苏省连云港市海州区', 'jsslygshzq');
INSERT INTO `t_system_region` VALUES ('5228', '320707', '连云港市开发区', '320707', '江苏省连云港市连云港市开发区', 'jsslygslygskfq');
INSERT INTO `t_system_region` VALUES ('5229', '320721', '赣榆县', '320721', '江苏省连云港市赣榆县', 'jsslygsgyx');
INSERT INTO `t_system_region` VALUES ('5230', '320722', '东海县', '320722', '江苏省连云港市东海县', 'jsslygsdhx');
INSERT INTO `t_system_region` VALUES ('5231', '320723', '灌云县', '320723', '江苏省连云港市灌云县', 'jsslygsgyx');
INSERT INTO `t_system_region` VALUES ('5232', '320724', '灌南县', '320724', '江苏省连云港市灌南县', 'jsslygsgnx');
INSERT INTO `t_system_region` VALUES ('5233', '3208', '淮安市', '320800', '江苏省淮安市', 'jsshas');
INSERT INTO `t_system_region` VALUES ('5234', '320801', '市辖区', '320801', '江苏省淮安市市辖区', 'jsshassxq');
INSERT INTO `t_system_region` VALUES ('5235', '320802', '清河区', '320802', '江苏省淮安市清河区', 'jsshasqhq');
INSERT INTO `t_system_region` VALUES ('5236', '320803', '楚州区', '320803', '江苏省淮安市楚州区', 'jsshasczq');
INSERT INTO `t_system_region` VALUES ('5237', '320804', '淮阴区', '320804', '江苏省淮安市淮阴区', 'jsshashyq');
INSERT INTO `t_system_region` VALUES ('5238', '320805', '淮安市开发区', '320805', '江苏省淮安市淮安市开发区', 'jsshashaskfq');
INSERT INTO `t_system_region` VALUES ('5239', '320811', '清浦区', '320811', '江苏省淮安市清浦区', 'jsshasqpq');
INSERT INTO `t_system_region` VALUES ('5240', '320826', '涟水县', '320826', '江苏省淮安市涟水县', 'jsshaslsx');
INSERT INTO `t_system_region` VALUES ('5241', '320829', '洪泽县', '320829', '江苏省淮安市洪泽县', 'jsshashzx');
INSERT INTO `t_system_region` VALUES ('5242', '320830', '盱眙县', '320830', '江苏省淮安市盱眙县', 'jsshasxyx');
INSERT INTO `t_system_region` VALUES ('5243', '320831', '金湖县', '320831', '江苏省淮安市金湖县', 'jsshasjhx');
INSERT INTO `t_system_region` VALUES ('5244', '3209', '盐城市', '320900', '江苏省盐城市', 'jssycs');
INSERT INTO `t_system_region` VALUES ('5245', '320901', '市辖区', '320901', '江苏省盐城市市辖区', 'jssycssxq');
INSERT INTO `t_system_region` VALUES ('5246', '320902', '亭湖区', '320902', '江苏省盐城市亭湖区', 'jssycsthq');
INSERT INTO `t_system_region` VALUES ('5247', '320903', '盐都区', '320903', '江苏省盐城市盐都区', 'jssycsydq');
INSERT INTO `t_system_region` VALUES ('5248', '320904', '开发区', '320904', '江苏省盐城市开发区', 'jssycskfq');
INSERT INTO `t_system_region` VALUES ('5249', '320921', '响水县', '320921', '江苏省盐城市响水县', 'jssycsxsx');
INSERT INTO `t_system_region` VALUES ('5250', '320922', '滨海县', '320922', '江苏省盐城市滨海县', 'jssycsbhx');
INSERT INTO `t_system_region` VALUES ('5251', '320923', '阜宁县', '320923', '江苏省盐城市阜宁县', 'jssycsfnx');
INSERT INTO `t_system_region` VALUES ('5252', '320924', '射阳县', '320924', '江苏省盐城市射阳县', 'jssycssyx');
INSERT INTO `t_system_region` VALUES ('5253', '320925', '建湖县', '320925', '江苏省盐城市建湖县', 'jssycsjhx');
INSERT INTO `t_system_region` VALUES ('5254', '320981', '东台市', '320981', '江苏省盐城市东台市', 'jssycsdts');
INSERT INTO `t_system_region` VALUES ('5255', '320982', '大丰市', '320982', '江苏省盐城市大丰市', 'jssycsdfs');
INSERT INTO `t_system_region` VALUES ('5256', '3210', '扬州市', '321000', '江苏省扬州市', 'jssyzs');
INSERT INTO `t_system_region` VALUES ('5257', '321001', '市辖区', '321001', '江苏省扬州市市辖区', 'jssyzssxq');
INSERT INTO `t_system_region` VALUES ('5258', '321002', '广陵区', '321002', '江苏省扬州市广陵区', 'jssyzsglq');
INSERT INTO `t_system_region` VALUES ('5259', '321003', '邗江区', '321003', '江苏省扬州市邗江区', 'jssyzshjq');
INSERT INTO `t_system_region` VALUES ('5260', '321004', '开发区', '321004', '江苏省扬州市开发区', 'jssyzskfq');
INSERT INTO `t_system_region` VALUES ('5261', '321011', '维扬区', '321011', '江苏省扬州市维扬区', 'jssyzswyq');
INSERT INTO `t_system_region` VALUES ('5262', '321023', '宝应县', '321023', '江苏省扬州市宝应县', 'jssyzsbyx');
INSERT INTO `t_system_region` VALUES ('5263', '321081', '仪征市', '321081', '江苏省扬州市仪征市', 'jssyzsyzs');
INSERT INTO `t_system_region` VALUES ('5264', '321084', '高邮市', '321084', '江苏省扬州市高邮市', 'jssyzsgys');
INSERT INTO `t_system_region` VALUES ('5265', '321088', '江都市', '321088', '江苏省扬州市江都市', 'jssyzsjds');
INSERT INTO `t_system_region` VALUES ('5266', '3211', '镇江市', '321100', '江苏省镇江市', 'jsszjs');
INSERT INTO `t_system_region` VALUES ('5267', '321101', '市辖区', '321101', '江苏省镇江市市辖区', 'jsszjssxq');
INSERT INTO `t_system_region` VALUES ('5268', '321102', '京口区', '321102', '江苏省镇江市京口区', 'jsszjsjkq');
INSERT INTO `t_system_region` VALUES ('5269', '321103', '镇江市新区', '321103', '江苏省镇江市镇江市新区', 'jsszjszjsxq');
INSERT INTO `t_system_region` VALUES ('5270', '321111', '润州区', '321111', '江苏省镇江市润州区', 'jsszjsrzq');
INSERT INTO `t_system_region` VALUES ('5271', '321112', '丹徒区', '321112', '江苏省镇江市丹徒区', 'jsszjsdtq');
INSERT INTO `t_system_region` VALUES ('5272', '321181', '丹阳市', '321181', '江苏省镇江市丹阳市', 'jsszjsdys');
INSERT INTO `t_system_region` VALUES ('5273', '321182', '扬中市', '321182', '江苏省镇江市扬中市', 'jsszjsyzs');
INSERT INTO `t_system_region` VALUES ('5274', '321183', '句容市', '321183', '江苏省镇江市句容市', 'jsszjsjrs');
INSERT INTO `t_system_region` VALUES ('5275', '3212', '泰州市', '321200', '江苏省泰州市', 'jsstzs');
INSERT INTO `t_system_region` VALUES ('5276', '321201', '市辖区', '321201', '江苏省泰州市市辖区', 'jsstzssxq');
INSERT INTO `t_system_region` VALUES ('5277', '321202', '海陵区', '321202', '江苏省泰州市海陵区', 'jsstzshlq');
INSERT INTO `t_system_region` VALUES ('5278', '321203', '高港区', '321203', '江苏省泰州市高港区', 'jsstzsggq');
INSERT INTO `t_system_region` VALUES ('5279', '321204', '开发区', '321204', '江苏省泰州市开发区', 'jsstzskfq');
INSERT INTO `t_system_region` VALUES ('5280', '321205', '市直', '321205', '江苏省泰州市市直', 'jsstzssz');
INSERT INTO `t_system_region` VALUES ('5281', '321281', '兴化市', '321281', '江苏省泰州市兴化市', 'jsstzsxhs');
INSERT INTO `t_system_region` VALUES ('5282', '321282', '靖江市', '321282', '江苏省泰州市靖江市', 'jsstzsjjs');
INSERT INTO `t_system_region` VALUES ('5283', '321283', '泰兴市', '321283', '江苏省泰州市泰兴市', 'jsstzstxs');
INSERT INTO `t_system_region` VALUES ('5284', '321284', '姜堰市', '321284', '江苏省泰州市姜堰市', 'jsstzsjys');
INSERT INTO `t_system_region` VALUES ('5285', '3213', '宿迁市', '321300', '江苏省宿迁市', 'jsssqs');
INSERT INTO `t_system_region` VALUES ('5286', '321301', '市辖区', '321301', '江苏省宿迁市市辖区', 'jsssqssxq');
INSERT INTO `t_system_region` VALUES ('5287', '321302', '宿城区', '321302', '江苏省宿迁市宿城区', 'jsssqsscq');
INSERT INTO `t_system_region` VALUES ('5288', '321311', '宿豫区', '321311', '江苏省宿迁市宿豫区', 'jsssqssyq');
INSERT INTO `t_system_region` VALUES ('5289', '321322', '沭阳县', '321322', '江苏省宿迁市沭阳县', 'jsssqssyx');
INSERT INTO `t_system_region` VALUES ('5290', '321323', '泗阳县', '321323', '江苏省宿迁市泗阳县', 'jsssqssyx');
INSERT INTO `t_system_region` VALUES ('5291', '321324', '泗洪县', '321324', '江苏省宿迁市泗洪县', 'jsssqsshx');
INSERT INTO `t_system_region` VALUES ('5292', '33', '浙江省', '330000', '浙江省', 'zjs');
INSERT INTO `t_system_region` VALUES ('5293', '3301', '杭州市', '330100', '浙江省杭州市', 'zjshzs');
INSERT INTO `t_system_region` VALUES ('5294', '330101', '市辖区', '330101', '浙江省杭州市市辖区', 'zjshzssxq');
INSERT INTO `t_system_region` VALUES ('5295', '330102', '上城区', '330102', '浙江省杭州市上城区', 'zjshzsscq');
INSERT INTO `t_system_region` VALUES ('5296', '330103', '下城区', '330103', '浙江省杭州市下城区', 'zjshzsxcq');
INSERT INTO `t_system_region` VALUES ('5297', '330104', '江干区', '330104', '浙江省杭州市江干区', 'zjshzsjgq');
INSERT INTO `t_system_region` VALUES ('5298', '330105', '拱墅区', '330105', '浙江省杭州市拱墅区', 'zjshzsgsq');
INSERT INTO `t_system_region` VALUES ('5299', '330106', '西湖区', '330106', '浙江省杭州市西湖区', 'zjshzsxhq');
INSERT INTO `t_system_region` VALUES ('5300', '330108', '滨江区', '330108', '浙江省杭州市滨江区', 'zjshzsbjq');
INSERT INTO `t_system_region` VALUES ('5301', '330109', '萧山区', '330109', '浙江省杭州市萧山区', 'zjshzsxsq');
INSERT INTO `t_system_region` VALUES ('5302', '330110', '余杭区', '330110', '浙江省杭州市余杭区', 'zjshzsyhq');
INSERT INTO `t_system_region` VALUES ('5303', '330122', '桐庐县', '330122', '浙江省杭州市桐庐县', 'zjshzstlx');
INSERT INTO `t_system_region` VALUES ('5304', '330127', '淳安县', '330127', '浙江省杭州市淳安县', 'zjshzscax');
INSERT INTO `t_system_region` VALUES ('5305', '330182', '建德市', '330182', '浙江省杭州市建德市', 'zjshzsjds');
INSERT INTO `t_system_region` VALUES ('5306', '330183', '富阳市', '330183', '浙江省杭州市富阳市', 'zjshzsfys');
INSERT INTO `t_system_region` VALUES ('5307', '330185', '临安市', '330185', '浙江省杭州市临安市', 'zjshzslas');
INSERT INTO `t_system_region` VALUES ('5308', '3302', '宁波市', '330200', '浙江省宁波市', 'zjsnbs');
INSERT INTO `t_system_region` VALUES ('5309', '330201', '市辖区', '330201', '浙江省宁波市市辖区', 'zjsnbssxq');
INSERT INTO `t_system_region` VALUES ('5310', '330203', '海曙区', '330203', '浙江省宁波市海曙区', 'zjsnbshsq');
INSERT INTO `t_system_region` VALUES ('5311', '330204', '江东区', '330204', '浙江省宁波市江东区', 'zjsnbsjdq');
INSERT INTO `t_system_region` VALUES ('5312', '330205', '江北区', '330205', '浙江省宁波市江北区', 'zjsnbsjbq');
INSERT INTO `t_system_region` VALUES ('5313', '330206', '北仑区', '330206', '浙江省宁波市北仑区', 'zjsnbsblq');
INSERT INTO `t_system_region` VALUES ('5314', '330211', '镇海区', '330211', '浙江省宁波市镇海区', 'zjsnbszhq');
INSERT INTO `t_system_region` VALUES ('5315', '330212', '鄞州区', '330212', '浙江省宁波市鄞州区', 'zjsnbsyzq');
INSERT INTO `t_system_region` VALUES ('5316', '330225', '象山县', '330225', '浙江省宁波市象山县', 'zjsnbsxsx');
INSERT INTO `t_system_region` VALUES ('5317', '330226', '宁海县', '330226', '浙江省宁波市宁海县', 'zjsnbsnhx');
INSERT INTO `t_system_region` VALUES ('5318', '330281', '余姚市', '330281', '浙江省宁波市余姚市', 'zjsnbsyys');
INSERT INTO `t_system_region` VALUES ('5319', '330282', '慈溪市', '330282', '浙江省宁波市慈溪市', 'zjsnbscxs');
INSERT INTO `t_system_region` VALUES ('5320', '330283', '奉化市', '330283', '浙江省宁波市奉化市', 'zjsnbsfhs');
INSERT INTO `t_system_region` VALUES ('5321', '3303', '温州市', '330300', '浙江省温州市', 'zjswzs');
INSERT INTO `t_system_region` VALUES ('5322', '330301', '市辖区', '330301', '浙江省温州市市辖区', 'zjswzssxq');
INSERT INTO `t_system_region` VALUES ('5323', '330302', '鹿城区', '330302', '浙江省温州市鹿城区', 'zjswzslcq');
INSERT INTO `t_system_region` VALUES ('5324', '330303', '龙湾区', '330303', '浙江省温州市龙湾区', 'zjswzslwq');
INSERT INTO `t_system_region` VALUES ('5325', '330304', '瓯海区', '330304', '浙江省温州市瓯海区', 'zjswzsohq');
INSERT INTO `t_system_region` VALUES ('5326', '330322', '洞头县', '330322', '浙江省温州市洞头县', 'zjswzsdtx');
INSERT INTO `t_system_region` VALUES ('5327', '330324', '永嘉县', '330324', '浙江省温州市永嘉县', 'zjswzsyjx');
INSERT INTO `t_system_region` VALUES ('5328', '330326', '平阳县', '330326', '浙江省温州市平阳县', 'zjswzspyx');
INSERT INTO `t_system_region` VALUES ('5329', '330327', '苍南县', '330327', '浙江省温州市苍南县', 'zjswzscnx');
INSERT INTO `t_system_region` VALUES ('5330', '330328', '文成县', '330328', '浙江省温州市文成县', 'zjswzswcx');
INSERT INTO `t_system_region` VALUES ('5331', '330329', '泰顺县', '330329', '浙江省温州市泰顺县', 'zjswzstsx');
INSERT INTO `t_system_region` VALUES ('5332', '330381', '瑞安市', '330381', '浙江省温州市瑞安市', 'zjswzsras');
INSERT INTO `t_system_region` VALUES ('5333', '330382', '乐清市', '330382', '浙江省温州市乐清市', 'zjswzslqs');
INSERT INTO `t_system_region` VALUES ('5334', '3304', '嘉兴市', '330400', '浙江省嘉兴市', 'zjsjxs');
INSERT INTO `t_system_region` VALUES ('5335', '330401', '市辖区', '330401', '浙江省嘉兴市市辖区', 'zjsjxssxq');
INSERT INTO `t_system_region` VALUES ('5336', '330402', '秀城区', '330402', '浙江省嘉兴市秀城区', 'zjsjxsxcq');
INSERT INTO `t_system_region` VALUES ('5337', '330411', '秀洲区', '330411', '浙江省嘉兴市秀洲区', 'zjsjxsxzq');
INSERT INTO `t_system_region` VALUES ('5338', '330421', '嘉善县', '330421', '浙江省嘉兴市嘉善县', 'zjsjxsjsx');
INSERT INTO `t_system_region` VALUES ('5339', '330424', '海盐县', '330424', '浙江省嘉兴市海盐县', 'zjsjxshyx');
INSERT INTO `t_system_region` VALUES ('5340', '330481', '海宁市', '330481', '浙江省嘉兴市海宁市', 'zjsjxshns');
INSERT INTO `t_system_region` VALUES ('5341', '330482', '平湖市', '330482', '浙江省嘉兴市平湖市', 'zjsjxsphs');
INSERT INTO `t_system_region` VALUES ('5342', '330483', '桐乡市', '330483', '浙江省嘉兴市桐乡市', 'zjsjxstxs');
INSERT INTO `t_system_region` VALUES ('5343', '3305', '湖州市', '330500', '浙江省湖州市', 'zjshzs');
INSERT INTO `t_system_region` VALUES ('5344', '330501', '市辖区', '330501', '浙江省湖州市市辖区', 'zjshzssxq');
INSERT INTO `t_system_region` VALUES ('5345', '330502', '吴兴区', '330502', '浙江省湖州市吴兴区', 'zjshzswxq');
INSERT INTO `t_system_region` VALUES ('5346', '330503', '南浔区', '330503', '浙江省湖州市南浔区', 'zjshzsnxq');
INSERT INTO `t_system_region` VALUES ('5347', '330521', '德清县', '330521', '浙江省湖州市德清县', 'zjshzsdqx');
INSERT INTO `t_system_region` VALUES ('5348', '330522', '长兴县', '330522', '浙江省湖州市长兴县', 'zjshzszxx');
INSERT INTO `t_system_region` VALUES ('5349', '330523', '安吉县', '330523', '浙江省湖州市安吉县', 'zjshzsajx');
INSERT INTO `t_system_region` VALUES ('5350', '3306', '绍兴市', '330600', '浙江省绍兴市', 'zjssxs');
INSERT INTO `t_system_region` VALUES ('5351', '330601', '市辖区', '330601', '浙江省绍兴市市辖区', 'zjssxssxq');
INSERT INTO `t_system_region` VALUES ('5352', '330602', '越城区', '330602', '浙江省绍兴市越城区', 'zjssxsycq');
INSERT INTO `t_system_region` VALUES ('5353', '330621', '绍兴县', '330621', '浙江省绍兴市绍兴县', 'zjssxssxx');
INSERT INTO `t_system_region` VALUES ('5354', '330624', '新昌县', '330624', '浙江省绍兴市新昌县', 'zjssxsxcx');
INSERT INTO `t_system_region` VALUES ('5355', '330681', '诸暨市', '330681', '浙江省绍兴市诸暨市', 'zjssxszjs');
INSERT INTO `t_system_region` VALUES ('5356', '330682', '上虞市', '330682', '浙江省绍兴市上虞市', 'zjssxssys');
INSERT INTO `t_system_region` VALUES ('5357', '330683', '嵊州市', '330683', '浙江省绍兴市嵊州市', 'zjssxsszs');
INSERT INTO `t_system_region` VALUES ('5358', '3307', '金华市', '330700', '浙江省金华市', 'zjsjhs');
INSERT INTO `t_system_region` VALUES ('5359', '330701', '市辖区', '330701', '浙江省金华市市辖区', 'zjsjhssxq');
INSERT INTO `t_system_region` VALUES ('5360', '330702', '婺城区', '330702', '浙江省金华市婺城区', 'zjsjhswcq');
INSERT INTO `t_system_region` VALUES ('5361', '330703', '金东区', '330703', '浙江省金华市金东区', 'zjsjhsjdq');
INSERT INTO `t_system_region` VALUES ('5362', '330723', '武义县', '330723', '浙江省金华市武义县', 'zjsjhswyx');
INSERT INTO `t_system_region` VALUES ('5363', '330726', '浦江县', '330726', '浙江省金华市浦江县', 'zjsjhspjx');
INSERT INTO `t_system_region` VALUES ('5364', '330727', '磐安县', '330727', '浙江省金华市磐安县', 'zjsjhspax');
INSERT INTO `t_system_region` VALUES ('5365', '330781', '兰溪市', '330781', '浙江省金华市兰溪市', 'zjsjhslxs');
INSERT INTO `t_system_region` VALUES ('5366', '330782', '义乌市', '330782', '浙江省金华市义乌市', 'zjsjhsyws');
INSERT INTO `t_system_region` VALUES ('5367', '330783', '东阳市', '330783', '浙江省金华市东阳市', 'zjsjhsdys');
INSERT INTO `t_system_region` VALUES ('5368', '330784', '永康市', '330784', '浙江省金华市永康市', 'zjsjhsyks');
INSERT INTO `t_system_region` VALUES ('5369', '3308', '衢州市', '330800', '浙江省衢州市', 'zjsqzs');
INSERT INTO `t_system_region` VALUES ('5370', '330801', '市辖区', '330801', '浙江省衢州市市辖区', 'zjsqzssxq');
INSERT INTO `t_system_region` VALUES ('5371', '330802', '柯城区', '330802', '浙江省衢州市柯城区', 'zjsqzskcq');
INSERT INTO `t_system_region` VALUES ('5372', '330803', '衢江区', '330803', '浙江省衢州市衢江区', 'zjsqzsqjq');
INSERT INTO `t_system_region` VALUES ('5373', '330822', '常山县', '330822', '浙江省衢州市常山县', 'zjsqzscsx');
INSERT INTO `t_system_region` VALUES ('5374', '330824', '开化县', '330824', '浙江省衢州市开化县', 'zjsqzskhx');
INSERT INTO `t_system_region` VALUES ('5375', '330825', '龙游县', '330825', '浙江省衢州市龙游县', 'zjsqzslyx');
INSERT INTO `t_system_region` VALUES ('5376', '330881', '江山市', '330881', '浙江省衢州市江山市', 'zjsqzsjss');
INSERT INTO `t_system_region` VALUES ('5377', '3309', '舟山市', '330900', '浙江省舟山市', 'zjszss');
INSERT INTO `t_system_region` VALUES ('5378', '330901', '市辖区', '330901', '浙江省舟山市市辖区', 'zjszsssxq');
INSERT INTO `t_system_region` VALUES ('5379', '330902', '定海区', '330902', '浙江省舟山市定海区', 'zjszssdhq');
INSERT INTO `t_system_region` VALUES ('5380', '330903', '普陀区', '330903', '浙江省舟山市普陀区', 'zjszssptq');
INSERT INTO `t_system_region` VALUES ('5381', '330921', '岱山县', '330921', '浙江省舟山市岱山县', 'zjszssdsx');
INSERT INTO `t_system_region` VALUES ('5382', '330922', '嵊泗县', '330922', '浙江省舟山市嵊泗县', 'zjszssssx');
INSERT INTO `t_system_region` VALUES ('5383', '3310', '台州市', '331000', '浙江省台州市', 'zjstzs');
INSERT INTO `t_system_region` VALUES ('5384', '331001', '市辖区', '331001', '浙江省台州市市辖区', 'zjstzssxq');
INSERT INTO `t_system_region` VALUES ('5385', '331002', '椒江区', '331002', '浙江省台州市椒江区', 'zjstzsjjq');
INSERT INTO `t_system_region` VALUES ('5386', '331003', '黄岩区', '331003', '浙江省台州市黄岩区', 'zjstzshyq');
INSERT INTO `t_system_region` VALUES ('5387', '331004', '路桥区', '331004', '浙江省台州市路桥区', 'zjstzslqq');
INSERT INTO `t_system_region` VALUES ('5388', '331021', '玉环县', '331021', '浙江省台州市玉环县', 'zjstzsyhx');
INSERT INTO `t_system_region` VALUES ('5389', '331022', '三门县', '331022', '浙江省台州市三门县', 'zjstzssmx');
INSERT INTO `t_system_region` VALUES ('5390', '331023', '天台县', '331023', '浙江省台州市天台县', 'zjstzsttx');
INSERT INTO `t_system_region` VALUES ('5391', '331024', '仙居县', '331024', '浙江省台州市仙居县', 'zjstzsxjx');
INSERT INTO `t_system_region` VALUES ('5392', '331081', '温岭市', '331081', '浙江省台州市温岭市', 'zjstzswls');
INSERT INTO `t_system_region` VALUES ('5393', '331082', '临海市', '331082', '浙江省台州市临海市', 'zjstzslhs');
INSERT INTO `t_system_region` VALUES ('5394', '3311', '丽水市', '331100', '浙江省丽水市', 'zjslss');
INSERT INTO `t_system_region` VALUES ('5395', '331101', '市辖区', '331101', '浙江省丽水市市辖区', 'zjslsssxq');
INSERT INTO `t_system_region` VALUES ('5396', '331102', '莲都区', '331102', '浙江省丽水市莲都区', 'zjslssldq');
INSERT INTO `t_system_region` VALUES ('5397', '331121', '青田县', '331121', '浙江省丽水市青田县', 'zjslssqtx');
INSERT INTO `t_system_region` VALUES ('5398', '331122', '缙云县', '331122', '浙江省丽水市缙云县', 'zjslssjyx');
INSERT INTO `t_system_region` VALUES ('5399', '331123', '遂昌县', '331123', '浙江省丽水市遂昌县', 'zjslssscx');
INSERT INTO `t_system_region` VALUES ('5400', '331124', '松阳县', '331124', '浙江省丽水市松阳县', 'zjslsssyx');
INSERT INTO `t_system_region` VALUES ('5401', '331125', '云和县', '331125', '浙江省丽水市云和县', 'zjslssyhx');
INSERT INTO `t_system_region` VALUES ('5402', '331126', '庆元县', '331126', '浙江省丽水市庆元县', 'zjslssqyx');
INSERT INTO `t_system_region` VALUES ('5403', '331127', '景宁畲族自治县', '331127', '浙江省丽水市景宁畲族自治县', 'zjslssjnszzzx');
INSERT INTO `t_system_region` VALUES ('5404', '331181', '龙泉市', '331181', '浙江省丽水市龙泉市', 'zjslsslqs');
INSERT INTO `t_system_region` VALUES ('5405', '34', '安徽省', '340000', '安徽省', 'ahs');
INSERT INTO `t_system_region` VALUES ('5406', '3401', '合肥市', '340100', '安徽省合肥市', 'ahshfs');
INSERT INTO `t_system_region` VALUES ('5407', '340101', '市辖区', '340101', '安徽省合肥市市辖区', 'ahshfssxq');
INSERT INTO `t_system_region` VALUES ('5408', '340102', '瑶海区', '340102', '安徽省合肥市瑶海区', 'ahshfsyhq');
INSERT INTO `t_system_region` VALUES ('5409', '340103', '庐阳区', '340103', '安徽省合肥市庐阳区', 'ahshfslyq');
INSERT INTO `t_system_region` VALUES ('5410', '340104', '蜀山区', '340104', '安徽省合肥市蜀山区', 'ahshfsssq');
INSERT INTO `t_system_region` VALUES ('5411', '340111', '包河区', '340111', '安徽省合肥市包河区', 'ahshfsbhq');
INSERT INTO `t_system_region` VALUES ('5412', '340121', '长丰县', '340121', '安徽省合肥市长丰县', 'ahshfszfx');
INSERT INTO `t_system_region` VALUES ('5413', '340122', '肥东县', '340122', '安徽省合肥市肥东县', 'ahshfsfdx');
INSERT INTO `t_system_region` VALUES ('5414', '340123', '肥西县', '340123', '安徽省合肥市肥西县', 'ahshfsfxx');
INSERT INTO `t_system_region` VALUES ('5415', '3402', '芜湖市', '340200', '安徽省芜湖市', 'ahswhs');
INSERT INTO `t_system_region` VALUES ('5416', '340201', '市辖区', '340201', '安徽省芜湖市市辖区', 'ahswhssxq');
INSERT INTO `t_system_region` VALUES ('5417', '340202', '镜湖区', '340202', '安徽省芜湖市镜湖区', 'ahswhsjhq');
INSERT INTO `t_system_region` VALUES ('5418', '340203', '弋江区', '340203', '安徽省芜湖市弋江区', 'ahswhsyjq');
INSERT INTO `t_system_region` VALUES ('5419', '340207', '鸠江区', '340207', '安徽省芜湖市鸠江区', 'ahswhsjjq');
INSERT INTO `t_system_region` VALUES ('5420', '340208', '三山区', '340208', '安徽省芜湖市三山区', 'ahswhsssq');
INSERT INTO `t_system_region` VALUES ('5421', '340221', '芜湖县', '340221', '安徽省芜湖市芜湖县', 'ahswhswhx');
INSERT INTO `t_system_region` VALUES ('5422', '340222', '繁昌县', '340222', '安徽省芜湖市繁昌县', 'ahswhsfcx');
INSERT INTO `t_system_region` VALUES ('5423', '340223', '南陵县', '340223', '安徽省芜湖市南陵县', 'ahswhsnlx');
INSERT INTO `t_system_region` VALUES ('5424', '3403', '蚌埠市', '340300', '安徽省蚌埠市', 'ahsbbs');
INSERT INTO `t_system_region` VALUES ('5425', '340301', '市辖区', '340301', '安徽省蚌埠市市辖区', 'ahsbbssxq');
INSERT INTO `t_system_region` VALUES ('5426', '340302', '龙子湖区', '340302', '安徽省蚌埠市龙子湖区', 'ahsbbslzhq');
INSERT INTO `t_system_region` VALUES ('5427', '340303', '蚌山区', '340303', '安徽省蚌埠市蚌山区', 'ahsbbsbsq');
INSERT INTO `t_system_region` VALUES ('5428', '340304', '禹会区', '340304', '安徽省蚌埠市禹会区', 'ahsbbsyhq');
INSERT INTO `t_system_region` VALUES ('5429', '340311', '淮上区', '340311', '安徽省蚌埠市淮上区', 'ahsbbshsq');
INSERT INTO `t_system_region` VALUES ('5430', '340321', '怀远县', '340321', '安徽省蚌埠市怀远县', 'ahsbbshyx');
INSERT INTO `t_system_region` VALUES ('5431', '340322', '五河县', '340322', '安徽省蚌埠市五河县', 'ahsbbswhx');
INSERT INTO `t_system_region` VALUES ('5432', '340323', '固镇县', '340323', '安徽省蚌埠市固镇县', 'ahsbbsgzx');
INSERT INTO `t_system_region` VALUES ('5433', '3404', '淮南市', '340400', '安徽省淮南市', 'ahshns');
INSERT INTO `t_system_region` VALUES ('5434', '340401', '市辖区', '340401', '安徽省淮南市市辖区', 'ahshnssxq');
INSERT INTO `t_system_region` VALUES ('5435', '340402', '大通区', '340402', '安徽省淮南市大通区', 'ahshnsdtq');
INSERT INTO `t_system_region` VALUES ('5436', '340403', '田家庵区', '340403', '安徽省淮南市田家庵区', 'ahshnstjaq');
INSERT INTO `t_system_region` VALUES ('5437', '340404', '谢家集区', '340404', '安徽省淮南市谢家集区', 'ahshnsxjjq');
INSERT INTO `t_system_region` VALUES ('5438', '340405', '八公山区', '340405', '安徽省淮南市八公山区', 'ahshnsbgsq');
INSERT INTO `t_system_region` VALUES ('5439', '340406', '潘集区', '340406', '安徽省淮南市潘集区', 'ahshnspjq');
INSERT INTO `t_system_region` VALUES ('5440', '340421', '凤台县', '340421', '安徽省淮南市凤台县', 'ahshnsftx');
INSERT INTO `t_system_region` VALUES ('5441', '340481', '毛集实验区', '340481', '安徽省淮南市毛集实验区', 'ahshnsmjsyq');
INSERT INTO `t_system_region` VALUES ('5442', '3405', '马鞍山市', '340500', '安徽省马鞍山市', 'ahsmass');
INSERT INTO `t_system_region` VALUES ('5443', '340501', '市辖区', '340501', '安徽省马鞍山市市辖区', 'ahsmasssxq');
INSERT INTO `t_system_region` VALUES ('5444', '340502', '金家庄区', '340502', '安徽省马鞍山市金家庄区', 'ahsmassjjzq');
INSERT INTO `t_system_region` VALUES ('5445', '340503', '花山区', '340503', '安徽省马鞍山市花山区', 'ahsmasshsq');
INSERT INTO `t_system_region` VALUES ('5446', '340504', '雨山区', '340504', '安徽省马鞍山市雨山区', 'ahsmassysq');
INSERT INTO `t_system_region` VALUES ('5447', '340521', '当涂县', '340521', '安徽省马鞍山市当涂县', 'ahsmassdtx');
INSERT INTO `t_system_region` VALUES ('5448', '3406', '淮北市', '340600', '安徽省淮北市', 'ahshbs');
INSERT INTO `t_system_region` VALUES ('5449', '340601', '市辖区', '340601', '安徽省淮北市市辖区', 'ahshbssxq');
INSERT INTO `t_system_region` VALUES ('5450', '340602', '杜集区', '340602', '安徽省淮北市杜集区', 'ahshbsdjq');
INSERT INTO `t_system_region` VALUES ('5451', '340603', '相山区', '340603', '安徽省淮北市相山区', 'ahshbsxsq');
INSERT INTO `t_system_region` VALUES ('5452', '340604', '烈山区', '340604', '安徽省淮北市烈山区', 'ahshbslsq');
INSERT INTO `t_system_region` VALUES ('5453', '340621', '濉溪县', '340621', '安徽省淮北市濉溪县', 'ahshbssxx');
INSERT INTO `t_system_region` VALUES ('5454', '3407', '铜陵市', '340700', '安徽省铜陵市', 'ahstls');
INSERT INTO `t_system_region` VALUES ('5455', '340701', '市辖区', '340701', '安徽省铜陵市市辖区', 'ahstlssxq');
INSERT INTO `t_system_region` VALUES ('5456', '340702', '铜官山区', '340702', '安徽省铜陵市铜官山区', 'ahstlstgsq');
INSERT INTO `t_system_region` VALUES ('5457', '340703', '狮子山区', '340703', '安徽省铜陵市狮子山区', 'ahstlsszsq');
INSERT INTO `t_system_region` VALUES ('5458', '340711', '郊区', '340711', '安徽省铜陵市郊区', 'ahstlsjq');
INSERT INTO `t_system_region` VALUES ('5459', '340721', '铜陵县', '340721', '安徽省铜陵市铜陵县', 'ahstlstlx');
INSERT INTO `t_system_region` VALUES ('5460', '3408', '安庆市', '340800', '安徽省安庆市', 'ahsaqs');
INSERT INTO `t_system_region` VALUES ('5461', '340801', '市辖区', '340801', '安徽省安庆市市辖区', 'ahsaqssxq');
INSERT INTO `t_system_region` VALUES ('5462', '340802', '迎江区', '340802', '安徽省安庆市迎江区', 'ahsaqsyjq');
INSERT INTO `t_system_region` VALUES ('5463', '340803', '大观区', '340803', '安徽省安庆市大观区', 'ahsaqsdgq');
INSERT INTO `t_system_region` VALUES ('5464', '340811', '宜秀区', '340811', '安徽省安庆市宜秀区', 'ahsaqsyxq');
INSERT INTO `t_system_region` VALUES ('5465', '340822', '怀宁县', '340822', '安徽省安庆市怀宁县', 'ahsaqshnx');
INSERT INTO `t_system_region` VALUES ('5466', '340823', '枞阳县', '340823', '安徽省安庆市枞阳县', 'ahsaqszyx');
INSERT INTO `t_system_region` VALUES ('5467', '340824', '潜山县', '340824', '安徽省安庆市潜山县', 'ahsaqsqsx');
INSERT INTO `t_system_region` VALUES ('5468', '340825', '太湖县', '340825', '安徽省安庆市太湖县', 'ahsaqsthx');
INSERT INTO `t_system_region` VALUES ('5469', '340826', '宿松县', '340826', '安徽省安庆市宿松县', 'ahsaqsssx');
INSERT INTO `t_system_region` VALUES ('5470', '340827', '望江县', '340827', '安徽省安庆市望江县', 'ahsaqswjx');
INSERT INTO `t_system_region` VALUES ('5471', '340828', '岳西县', '340828', '安徽省安庆市岳西县', 'ahsaqsyxx');
INSERT INTO `t_system_region` VALUES ('5472', '340881', '桐城市', '340881', '安徽省安庆市桐城市', 'ahsaqstcs');
INSERT INTO `t_system_region` VALUES ('5473', '3410', '黄山市', '341000', '安徽省黄山市', 'ahshss');
INSERT INTO `t_system_region` VALUES ('5474', '341001', '市辖区', '341001', '安徽省黄山市市辖区', 'ahshsssxq');
INSERT INTO `t_system_region` VALUES ('5475', '341002', '屯溪区', '341002', '安徽省黄山市屯溪区', 'ahshsstxq');
INSERT INTO `t_system_region` VALUES ('5476', '341003', '黄山区', '341003', '安徽省黄山市黄山区', 'ahshsshsq');
INSERT INTO `t_system_region` VALUES ('5477', '341004', '徽州区', '341004', '安徽省黄山市徽州区', 'ahshsshzq');
INSERT INTO `t_system_region` VALUES ('5478', '341021', '歙县', '341021', '安徽省黄山市歙县', 'ahshsssx');
INSERT INTO `t_system_region` VALUES ('5479', '341022', '休宁县', '341022', '安徽省黄山市休宁县', 'ahshssxnx');
INSERT INTO `t_system_region` VALUES ('5480', '341023', '黟县', '341023', '安徽省黄山市黟县', 'ahshssyx');
INSERT INTO `t_system_region` VALUES ('5481', '341024', '祁门县', '341024', '安徽省黄山市祁门县', 'ahshssqmx');
INSERT INTO `t_system_region` VALUES ('5482', '3411', '滁州市', '341100', '安徽省滁州市', 'ahsczs');
INSERT INTO `t_system_region` VALUES ('5483', '341101', '市辖区', '341101', '安徽省滁州市市辖区', 'ahsczssxq');
INSERT INTO `t_system_region` VALUES ('5484', '341102', '琅琊区', '341102', '安徽省滁州市琅琊区', 'ahsczslyq');
INSERT INTO `t_system_region` VALUES ('5485', '341103', '南谯区', '341103', '安徽省滁州市南谯区', 'ahsczsnqq');
INSERT INTO `t_system_region` VALUES ('5486', '341122', '来安县', '341122', '安徽省滁州市来安县', 'ahsczslax');
INSERT INTO `t_system_region` VALUES ('5487', '341124', '全椒县', '341124', '安徽省滁州市全椒县', 'ahsczsqjx');
INSERT INTO `t_system_region` VALUES ('5488', '341125', '定远县', '341125', '安徽省滁州市定远县', 'ahsczsdyx');
INSERT INTO `t_system_region` VALUES ('5489', '341126', '凤阳县', '341126', '安徽省滁州市凤阳县', 'ahsczsfyx');
INSERT INTO `t_system_region` VALUES ('5490', '341181', '天长市', '341181', '安徽省滁州市天长市', 'ahsczstzs');
INSERT INTO `t_system_region` VALUES ('5491', '341182', '明光市', '341182', '安徽省滁州市明光市', 'ahsczsmgs');
INSERT INTO `t_system_region` VALUES ('5492', '3412', '阜阳市', '341200', '安徽省阜阳市', 'ahsfys');
INSERT INTO `t_system_region` VALUES ('5493', '341201', '市辖区', '341201', '安徽省阜阳市市辖区', 'ahsfyssxq');
INSERT INTO `t_system_region` VALUES ('5494', '341202', '颍州区', '341202', '安徽省阜阳市颍州区', 'ahsfysyzq');
INSERT INTO `t_system_region` VALUES ('5495', '341203', '颍东区', '341203', '安徽省阜阳市颍东区', 'ahsfysydq');
INSERT INTO `t_system_region` VALUES ('5496', '341204', '颍泉区', '341204', '安徽省阜阳市颍泉区', 'ahsfysyqq');
INSERT INTO `t_system_region` VALUES ('5497', '341221', '临泉县', '341221', '安徽省阜阳市临泉县', 'ahsfyslqx');
INSERT INTO `t_system_region` VALUES ('5498', '341222', '太和县', '341222', '安徽省阜阳市太和县', 'ahsfysthx');
INSERT INTO `t_system_region` VALUES ('5499', '341225', '阜南县', '341225', '安徽省阜阳市阜南县', 'ahsfysfnx');
INSERT INTO `t_system_region` VALUES ('5500', '341226', '颍上县', '341226', '安徽省阜阳市颍上县', 'ahsfysysx');
INSERT INTO `t_system_region` VALUES ('5501', '341282', '界首市', '341282', '安徽省阜阳市界首市', 'ahsfysjss');
INSERT INTO `t_system_region` VALUES ('5502', '3413', '宿州市', '341300', '安徽省宿州市', 'ahsszs');
INSERT INTO `t_system_region` VALUES ('5503', '341301', '市辖区', '341301', '安徽省宿州市市辖区', 'ahsszssxq');
INSERT INTO `t_system_region` VALUES ('5504', '341302', '埇桥区', '341302', '安徽省宿州市埇桥区', 'ahsszsyqq');
INSERT INTO `t_system_region` VALUES ('5505', '341321', '砀山县', '341321', '安徽省宿州市砀山县', 'ahsszsdsx');
INSERT INTO `t_system_region` VALUES ('5506', '341322', '萧县', '341322', '安徽省宿州市萧县', 'ahsszsxx');
INSERT INTO `t_system_region` VALUES ('5507', '341323', '灵璧县', '341323', '安徽省宿州市灵璧县', 'ahsszslbx');
INSERT INTO `t_system_region` VALUES ('5508', '341324', '泗县', '341324', '安徽省宿州市泗县', 'ahsszssx');
INSERT INTO `t_system_region` VALUES ('5509', '3414', '巢湖市', '341400', '安徽省巢湖市', 'ahschs');
INSERT INTO `t_system_region` VALUES ('5510', '341401', '市辖区', '341401', '安徽省巢湖市市辖区', 'ahschssxq');
INSERT INTO `t_system_region` VALUES ('5511', '341402', '居巢区', '341402', '安徽省巢湖市居巢区', 'ahschsjcq');
INSERT INTO `t_system_region` VALUES ('5512', '341421', '庐江县', '341421', '安徽省巢湖市庐江县', 'ahschsljx');
INSERT INTO `t_system_region` VALUES ('5513', '341422', '无为县', '341422', '安徽省巢湖市无为县', 'ahschswwx');
INSERT INTO `t_system_region` VALUES ('5514', '341423', '含山县', '341423', '安徽省巢湖市含山县', 'ahschshsx');
INSERT INTO `t_system_region` VALUES ('5515', '341424', '和县', '341424', '安徽省巢湖市和县', 'ahschshx');
INSERT INTO `t_system_region` VALUES ('5516', '3415', '六安市', '341500', '安徽省六安市', 'ahslas');
INSERT INTO `t_system_region` VALUES ('5517', '341501', '市辖区', '341501', '安徽省六安市市辖区', 'ahslassxq');
INSERT INTO `t_system_region` VALUES ('5518', '341502', '金安区', '341502', '安徽省六安市金安区', 'ahslasjaq');
INSERT INTO `t_system_region` VALUES ('5519', '341503', '裕安区', '341503', '安徽省六安市裕安区', 'ahslasyaq');
INSERT INTO `t_system_region` VALUES ('5520', '341521', '寿县', '341521', '安徽省六安市寿县', 'ahslassx');
INSERT INTO `t_system_region` VALUES ('5521', '341522', '霍邱县', '341522', '安徽省六安市霍邱县', 'ahslashqx');
INSERT INTO `t_system_region` VALUES ('5522', '341523', '舒城县', '341523', '安徽省六安市舒城县', 'ahslasscx');
INSERT INTO `t_system_region` VALUES ('5523', '341524', '金寨县', '341524', '安徽省六安市金寨县', 'ahslasjzx');
INSERT INTO `t_system_region` VALUES ('5524', '341525', '霍山县', '341525', '安徽省六安市霍山县', 'ahslashsx');
INSERT INTO `t_system_region` VALUES ('5525', '341581', '叶集区', '341581', '安徽省六安市叶集区', 'ahslasyjq');
INSERT INTO `t_system_region` VALUES ('5526', '3416', '亳州市', '341600', '安徽省亳州市', 'ahsbzs');
INSERT INTO `t_system_region` VALUES ('5527', '341601', '市辖区', '341601', '安徽省亳州市市辖区', 'ahsbzssxq');
INSERT INTO `t_system_region` VALUES ('5528', '341602', '谯城区', '341602', '安徽省亳州市谯城区', 'ahsbzsqcq');
INSERT INTO `t_system_region` VALUES ('5529', '341621', '涡阳县', '341621', '安徽省亳州市涡阳县', 'ahsbzswyx');
INSERT INTO `t_system_region` VALUES ('5530', '341622', '蒙城县', '341622', '安徽省亳州市蒙城县', 'ahsbzsmcx');
INSERT INTO `t_system_region` VALUES ('5531', '341623', '利辛县', '341623', '安徽省亳州市利辛县', 'ahsbzslxx');
INSERT INTO `t_system_region` VALUES ('5532', '3417', '池州市', '341700', '安徽省池州市', 'ahsczs');
INSERT INTO `t_system_region` VALUES ('5533', '341701', '市辖区', '341701', '安徽省池州市市辖区', 'ahsczssxq');
INSERT INTO `t_system_region` VALUES ('5534', '341702', '贵池区', '341702', '安徽省池州市贵池区', 'ahsczsgcq');
INSERT INTO `t_system_region` VALUES ('5535', '341721', '东至县', '341721', '安徽省池州市东至县', 'ahsczsdzx');
INSERT INTO `t_system_region` VALUES ('5536', '341722', '石台县', '341722', '安徽省池州市石台县', 'ahsczsstx');
INSERT INTO `t_system_region` VALUES ('5537', '341723', '青阳县', '341723', '安徽省池州市青阳县', 'ahsczsqyx');
INSERT INTO `t_system_region` VALUES ('5538', '341781', '九华山风景区', '341781', '安徽省池州市九华山风景区', 'ahsczsjhsfjq');
INSERT INTO `t_system_region` VALUES ('5539', '3418', '宣城市', '341800', '安徽省宣城市', 'ahsxcs');
INSERT INTO `t_system_region` VALUES ('5540', '341801', '市辖区', '341801', '安徽省宣城市市辖区', 'ahsxcssxq');
INSERT INTO `t_system_region` VALUES ('5541', '341802', '宣州区', '341802', '安徽省宣城市宣州区', 'ahsxcsxzq');
INSERT INTO `t_system_region` VALUES ('5542', '341821', '郎溪县', '341821', '安徽省宣城市郎溪县', 'ahsxcslxx');
INSERT INTO `t_system_region` VALUES ('5543', '341822', '广德县', '341822', '安徽省宣城市广德县', 'ahsxcsgdx');
INSERT INTO `t_system_region` VALUES ('5544', '341823', '泾县', '341823', '安徽省宣城市泾县', 'ahsxcsjx');
INSERT INTO `t_system_region` VALUES ('5545', '341824', '绩溪县', '341824', '安徽省宣城市绩溪县', 'ahsxcsjxx');
INSERT INTO `t_system_region` VALUES ('5546', '341825', '旌德县', '341825', '安徽省宣城市旌德县', 'ahsxcsjdx');
INSERT INTO `t_system_region` VALUES ('5547', '341881', '宁国市', '341881', '安徽省宣城市宁国市', 'ahsxcsngs');
INSERT INTO `t_system_region` VALUES ('5548', '3499', '省直(市级)', '349900', '安徽省省直(市级)', 'ahssz(sj)');
INSERT INTO `t_system_region` VALUES ('5549', '349901', '省直(县级)', '349901', '安徽省省直(市级)省直(县级)', 'ahssz(sj)sz(xj)');
INSERT INTO `t_system_region` VALUES ('5550', '35', '福建省', '350000', '福建省', 'fjs');
INSERT INTO `t_system_region` VALUES ('5552', '3501', '福州市', '350100', '福建省福州市', 'fjsfzs');
INSERT INTO `t_system_region` VALUES ('5553', '350101', '市辖区', '350101', '福建省福州市市辖区', 'fjsfzssxq');
INSERT INTO `t_system_region` VALUES ('5554', '350102', '鼓楼区', '350102', '福建省福州市鼓楼区', 'fjsfzsglq');
INSERT INTO `t_system_region` VALUES ('5555', '350103', '台江区', '350103', '福建省福州市台江区', 'fjsfzstjq');
INSERT INTO `t_system_region` VALUES ('5556', '350104', '仓山区', '350104', '福建省福州市仓山区', 'fjsfzscsq');
INSERT INTO `t_system_region` VALUES ('5557', '350105', '马尾区', '350105', '福建省福州市马尾区', 'fjsfzsmwq');
INSERT INTO `t_system_region` VALUES ('5558', '350106', '琅岐开发区', '350106', '福建省福州市琅岐开发区', 'fjsfzslqkfq');
INSERT INTO `t_system_region` VALUES ('5559', '350111', '晋安区', '350111', '福建省福州市晋安区', 'fjsfzsjaq');
INSERT INTO `t_system_region` VALUES ('5560', '350121', '闽侯县', '350121', '福建省福州市闽侯县', 'fjsfzsmhx');
INSERT INTO `t_system_region` VALUES ('5561', '350122', '连江县', '350122', '福建省福州市连江县', 'fjsfzsljx');
INSERT INTO `t_system_region` VALUES ('5562', '350123', '罗源县', '350123', '福建省福州市罗源县', 'fjsfzslyx');
INSERT INTO `t_system_region` VALUES ('5563', '350124', '闽清县', '350124', '福建省福州市闽清县', 'fjsfzsmqx');
INSERT INTO `t_system_region` VALUES ('5564', '350125', '永泰县', '350125', '福建省福州市永泰县', 'fjsfzsytx');
INSERT INTO `t_system_region` VALUES ('5565', '350128', '平潭县', '350128', '福建省福州市平潭县', 'fjsfzsptx');
INSERT INTO `t_system_region` VALUES ('5566', '350181', '福清市', '350181', '福建省福州市福清市', 'fjsfzsfqs');
INSERT INTO `t_system_region` VALUES ('5567', '350182', '长乐市', '350182', '福建省福州市长乐市', 'fjsfzszls');
INSERT INTO `t_system_region` VALUES ('5568', '3502', '厦门市', '350200', '福建省厦门市', 'fjssms');
INSERT INTO `t_system_region` VALUES ('5569', '350201', '市辖区', '350201', '福建省厦门市市辖区', 'fjssmssxq');
INSERT INTO `t_system_region` VALUES ('5570', '350203', '思明区', '350203', '福建省厦门市思明区', 'fjssmssmq');
INSERT INTO `t_system_region` VALUES ('5571', '350205', '海沧区', '350205', '福建省厦门市海沧区', 'fjssmshcq');
INSERT INTO `t_system_region` VALUES ('5572', '350206', '湖里区', '350206', '福建省厦门市湖里区', 'fjssmshlq');
INSERT INTO `t_system_region` VALUES ('5573', '350211', '集美区', '350211', '福建省厦门市集美区', 'fjssmsjmq');
INSERT INTO `t_system_region` VALUES ('5574', '350212', '同安区', '350212', '福建省厦门市同安区', 'fjssmstaq');
INSERT INTO `t_system_region` VALUES ('5575', '350213', '翔安区', '350213', '福建省厦门市翔安区', 'fjssmsxaq');
INSERT INTO `t_system_region` VALUES ('5576', '3503', '莆田市', '350300', '福建省莆田市', 'fjspts');
INSERT INTO `t_system_region` VALUES ('5577', '350301', '市辖区', '350301', '福建省莆田市市辖区', 'fjsptssxq');
INSERT INTO `t_system_region` VALUES ('5578', '350302', '城厢区', '350302', '福建省莆田市城厢区', 'fjsptscxq');
INSERT INTO `t_system_region` VALUES ('5579', '350303', '涵江区', '350303', '福建省莆田市涵江区', 'fjsptshjq');
INSERT INTO `t_system_region` VALUES ('5580', '350304', '荔城区', '350304', '福建省莆田市荔城区', 'fjsptslcq');
INSERT INTO `t_system_region` VALUES ('5581', '350305', '秀屿区', '350305', '福建省莆田市秀屿区', 'fjsptsxyq');
INSERT INTO `t_system_region` VALUES ('5582', '350306', '湄州岛', '350306', '福建省莆田市湄州岛', 'fjsptsmzd');
INSERT INTO `t_system_region` VALUES ('5583', '350307', '北岸', '350307', '福建省莆田市北岸', 'fjsptsba');
INSERT INTO `t_system_region` VALUES ('5584', '350322', '仙游县', '350322', '福建省莆田市仙游县', 'fjsptsxyx');
INSERT INTO `t_system_region` VALUES ('5585', '3504', '三明市', '350400', '福建省三明市', 'fjssms');
INSERT INTO `t_system_region` VALUES ('5586', '350401', '市辖区', '350401', '福建省三明市市辖区', 'fjssmssxq');
INSERT INTO `t_system_region` VALUES ('5587', '350402', '梅列区', '350402', '福建省三明市梅列区', 'fjssmsmlq');
INSERT INTO `t_system_region` VALUES ('5588', '350403', '三元区', '350403', '福建省三明市三元区', 'fjssmssyq');
INSERT INTO `t_system_region` VALUES ('5589', '350421', '明溪县', '350421', '福建省三明市明溪县', 'fjssmsmxx');
INSERT INTO `t_system_region` VALUES ('5590', '350423', '清流县', '350423', '福建省三明市清流县', 'fjssmsqlx');
INSERT INTO `t_system_region` VALUES ('5591', '350424', '宁化县', '350424', '福建省三明市宁化县', 'fjssmsnhx');
INSERT INTO `t_system_region` VALUES ('5592', '350425', '大田县', '350425', '福建省三明市大田县', 'fjssmsdtx');
INSERT INTO `t_system_region` VALUES ('5593', '350426', '尤溪县', '350426', '福建省三明市尤溪县', 'fjssmsyxx');
INSERT INTO `t_system_region` VALUES ('5594', '350427', '沙县', '350427', '福建省三明市沙县', 'fjssmssx');
INSERT INTO `t_system_region` VALUES ('5595', '350428', '将乐县', '350428', '福建省三明市将乐县', 'fjssmsjlx');
INSERT INTO `t_system_region` VALUES ('5596', '350429', '泰宁县', '350429', '福建省三明市泰宁县', 'fjssmstnx');
INSERT INTO `t_system_region` VALUES ('5597', '350430', '建宁县', '350430', '福建省三明市建宁县', 'fjssmsjnx');
INSERT INTO `t_system_region` VALUES ('5598', '350481', '永安市', '350481', '福建省三明市永安市', 'fjssmsyas');
INSERT INTO `t_system_region` VALUES ('5599', '3505', '泉州市', '350500', '福建省泉州市', 'fjsqzs');
INSERT INTO `t_system_region` VALUES ('5600', '350501', '市辖区', '350501', '福建省泉州市市辖区', 'fjsqzssxq');
INSERT INTO `t_system_region` VALUES ('5601', '350502', '鲤城区', '350502', '福建省泉州市鲤城区', 'fjsqzslcq');
INSERT INTO `t_system_region` VALUES ('5602', '350503', '丰泽区', '350503', '福建省泉州市丰泽区', 'fjsqzsfzq');
INSERT INTO `t_system_region` VALUES ('5603', '350504', '洛江区', '350504', '福建省泉州市洛江区', 'fjsqzsljq');
INSERT INTO `t_system_region` VALUES ('5604', '350505', '泉港区', '350505', '福建省泉州市泉港区', 'fjsqzsqgq');
INSERT INTO `t_system_region` VALUES ('5605', '350521', '惠安县', '350521', '福建省泉州市惠安县', 'fjsqzshax');
INSERT INTO `t_system_region` VALUES ('5606', '350524', '安溪县', '350524', '福建省泉州市安溪县', 'fjsqzsaxx');
INSERT INTO `t_system_region` VALUES ('5607', '350525', '永春县', '350525', '福建省泉州市永春县', 'fjsqzsycx');
INSERT INTO `t_system_region` VALUES ('5608', '350526', '德化县', '350526', '福建省泉州市德化县', 'fjsqzsdhx');
INSERT INTO `t_system_region` VALUES ('5609', '350527', '金门县', '350527', '福建省泉州市金门县', 'fjsqzsjmx');
INSERT INTO `t_system_region` VALUES ('5610', '350581', '石狮市', '350581', '福建省泉州市石狮市', 'fjsqzssss');
INSERT INTO `t_system_region` VALUES ('5611', '350582', '晋江市', '350582', '福建省泉州市晋江市', 'fjsqzsjjs');
INSERT INTO `t_system_region` VALUES ('5612', '350583', '南安市', '350583', '福建省泉州市南安市', 'fjsqzsnas');
INSERT INTO `t_system_region` VALUES ('5613', '3506', '漳州市', '350600', '福建省漳州市', 'fjszzs');
INSERT INTO `t_system_region` VALUES ('5614', '350601', '市辖区', '350601', '福建省漳州市市辖区', 'fjszzssxq');
INSERT INTO `t_system_region` VALUES ('5615', '350602', '芗城区', '350602', '福建省漳州市芗城区', 'fjszzsxcq');
INSERT INTO `t_system_region` VALUES ('5616', '350603', '龙文区', '350603', '福建省漳州市龙文区', 'fjszzslwq');
INSERT INTO `t_system_region` VALUES ('5617', '350622', '云霄县', '350622', '福建省漳州市云霄县', 'fjszzsyxx');
INSERT INTO `t_system_region` VALUES ('5618', '350623', '漳浦县', '350623', '福建省漳州市漳浦县', 'fjszzszpx');
INSERT INTO `t_system_region` VALUES ('5619', '350624', '诏安县', '350624', '福建省漳州市诏安县', 'fjszzszax');
INSERT INTO `t_system_region` VALUES ('5620', '350625', '长泰县', '350625', '福建省漳州市长泰县', 'fjszzsztx');
INSERT INTO `t_system_region` VALUES ('5621', '350626', '东山县', '350626', '福建省漳州市东山县', 'fjszzsdsx');
INSERT INTO `t_system_region` VALUES ('5622', '350627', '南靖县', '350627', '福建省漳州市南靖县', 'fjszzsnjx');
INSERT INTO `t_system_region` VALUES ('5623', '350628', '平和县', '350628', '福建省漳州市平和县', 'fjszzsphx');
INSERT INTO `t_system_region` VALUES ('5624', '350629', '华安县', '350629', '福建省漳州市华安县', 'fjszzshax');
INSERT INTO `t_system_region` VALUES ('5625', '350681', '龙海市', '350681', '福建省漳州市龙海市', 'fjszzslhs');
INSERT INTO `t_system_region` VALUES ('5626', '350682', '常山开发区', '350682', '福建省漳州市常山开发区', 'fjszzscskfq');
INSERT INTO `t_system_region` VALUES ('5627', '3507', '南平市', '350700', '福建省南平市', 'fjsnps');
INSERT INTO `t_system_region` VALUES ('5628', '350701', '市辖区', '350701', '福建省南平市市辖区', 'fjsnpssxq');
INSERT INTO `t_system_region` VALUES ('5629', '350702', '延平区', '350702', '福建省南平市延平区', 'fjsnpsypq');
INSERT INTO `t_system_region` VALUES ('5630', '350721', '顺昌县', '350721', '福建省南平市顺昌县', 'fjsnpsscx');
INSERT INTO `t_system_region` VALUES ('5631', '350722', '浦城县', '350722', '福建省南平市浦城县', 'fjsnpspcx');
INSERT INTO `t_system_region` VALUES ('5632', '350723', '光泽县', '350723', '福建省南平市光泽县', 'fjsnpsgzx');
INSERT INTO `t_system_region` VALUES ('5633', '350724', '松溪县', '350724', '福建省南平市松溪县', 'fjsnpssxx');
INSERT INTO `t_system_region` VALUES ('5634', '350725', '政和县', '350725', '福建省南平市政和县', 'fjsnpszhx');
INSERT INTO `t_system_region` VALUES ('5635', '350781', '邵武市', '350781', '福建省南平市邵武市', 'fjsnpssws');
INSERT INTO `t_system_region` VALUES ('5636', '350782', '武夷山市', '350782', '福建省南平市武夷山市', 'fjsnpswyss');
INSERT INTO `t_system_region` VALUES ('5637', '350783', '建瓯市', '350783', '福建省南平市建瓯市', 'fjsnpsjos');
INSERT INTO `t_system_region` VALUES ('5638', '350784', '建阳市', '350784', '福建省南平市建阳市', 'fjsnpsjys');
INSERT INTO `t_system_region` VALUES ('5639', '3508', '龙岩市', '350800', '福建省龙岩市', 'fjslys');
INSERT INTO `t_system_region` VALUES ('5640', '350801', '市辖区', '350801', '福建省龙岩市市辖区', 'fjslyssxq');
INSERT INTO `t_system_region` VALUES ('5641', '350802', '新罗区', '350802', '福建省龙岩市新罗区', 'fjslysxlq');
INSERT INTO `t_system_region` VALUES ('5642', '350821', '长汀县', '350821', '福建省龙岩市长汀县', 'fjslysztx');
INSERT INTO `t_system_region` VALUES ('5643', '350822', '永定县', '350822', '福建省龙岩市永定县', 'fjslysydx');
INSERT INTO `t_system_region` VALUES ('5644', '350823', '上杭县', '350823', '福建省龙岩市上杭县', 'fjslysshx');
INSERT INTO `t_system_region` VALUES ('5645', '350824', '武平县', '350824', '福建省龙岩市武平县', 'fjslyswpx');
INSERT INTO `t_system_region` VALUES ('5646', '350825', '连城县', '350825', '福建省龙岩市连城县', 'fjslyslcx');
INSERT INTO `t_system_region` VALUES ('5647', '350881', '漳平市', '350881', '福建省龙岩市漳平市', 'fjslyszps');
INSERT INTO `t_system_region` VALUES ('5648', '3509', '宁德市', '350900', '福建省宁德市', 'fjsnds');
INSERT INTO `t_system_region` VALUES ('5649', '350901', '市辖区', '350901', '福建省宁德市市辖区', 'fjsndssxq');
INSERT INTO `t_system_region` VALUES ('5650', '350902', '蕉城区', '350902', '福建省宁德市蕉城区', 'fjsndsjcq');
INSERT INTO `t_system_region` VALUES ('5651', '350921', '霞浦县', '350921', '福建省宁德市霞浦县', 'fjsndsxpx');
INSERT INTO `t_system_region` VALUES ('5652', '350922', '古田县', '350922', '福建省宁德市古田县', 'fjsndsgtx');
INSERT INTO `t_system_region` VALUES ('5653', '350923', '屏南县', '350923', '福建省宁德市屏南县', 'fjsndspnx');
INSERT INTO `t_system_region` VALUES ('5654', '350924', '寿宁县', '350924', '福建省宁德市寿宁县', 'fjsndssnx');
INSERT INTO `t_system_region` VALUES ('5655', '350925', '周宁县', '350925', '福建省宁德市周宁县', 'fjsndsznx');
INSERT INTO `t_system_region` VALUES ('5656', '350926', '柘荣县', '350926', '福建省宁德市柘荣县', 'fjsndszrx');
INSERT INTO `t_system_region` VALUES ('5657', '350981', '福安市', '350981', '福建省宁德市福安市', 'fjsndsfas');
INSERT INTO `t_system_region` VALUES ('5658', '350982', '福鼎市', '350982', '福建省宁德市福鼎市', 'fjsndsfds');
INSERT INTO `t_system_region` VALUES ('5659', '36', '江西省', '360000', '江西省', 'jxs');
INSERT INTO `t_system_region` VALUES ('5660', '3601', '南昌市', '360100', '江西省南昌市', 'jxsncs');
INSERT INTO `t_system_region` VALUES ('5661', '360101', '市辖区', '360101', '江西省南昌市市辖区', 'jxsncssxq');
INSERT INTO `t_system_region` VALUES ('5662', '360102', '东湖区', '360102', '江西省南昌市东湖区', 'jxsncsdhq');
INSERT INTO `t_system_region` VALUES ('5663', '360103', '西湖区', '360103', '江西省南昌市西湖区', 'jxsncsxhq');
INSERT INTO `t_system_region` VALUES ('5664', '360104', '青云谱区', '360104', '江西省南昌市青云谱区', 'jxsncsqypq');
INSERT INTO `t_system_region` VALUES ('5665', '360105', '湾里区', '360105', '江西省南昌市湾里区', 'jxsncswlq');
INSERT INTO `t_system_region` VALUES ('5666', '360106', '南昌经济技术开发区', '360106', '江西省南昌市南昌经济技术开发区', 'jxsncsncjjjskfq');
INSERT INTO `t_system_region` VALUES ('5667', '360108', '红谷滩新区', '360108', '江西省南昌市红谷滩新区', 'jxsncshgtxq');
INSERT INTO `t_system_region` VALUES ('5668', '360109', '南昌高新区', '360109', '江西省南昌市南昌高新区', 'jxsncsncgxq');
INSERT INTO `t_system_region` VALUES ('5669', '360111', '青山湖区', '360111', '江西省南昌市青山湖区', 'jxsncsqshq');
INSERT INTO `t_system_region` VALUES ('5670', '360121', '南昌县', '360121', '江西省南昌市南昌县', 'jxsncsncx');
INSERT INTO `t_system_region` VALUES ('5671', '360122', '新建县', '360122', '江西省南昌市新建县', 'jxsncsxjx');
INSERT INTO `t_system_region` VALUES ('5672', '360123', '安义县', '360123', '江西省南昌市安义县', 'jxsncsayx');
INSERT INTO `t_system_region` VALUES ('5673', '360124', '进贤县', '360124', '江西省南昌市进贤县', 'jxsncsjxx');
INSERT INTO `t_system_region` VALUES ('5674', '360125', '厂办学校', '360125', '江西省南昌市厂办学校', 'jxsncscbxx');
INSERT INTO `t_system_region` VALUES ('5675', '360132', '英雄开发区', '360132', '江西省南昌市英雄开发区', 'jxsncsyxkfq');
INSERT INTO `t_system_region` VALUES ('5676', '360142', '桑海经济技术开发区', '360142', '江西省南昌市桑海经济技术开发区', 'jxsncsshjjjskfq');
INSERT INTO `t_system_region` VALUES ('5677', '3602', '景德镇市', '360200', '江西省景德镇市', 'jxsjdzs');
INSERT INTO `t_system_region` VALUES ('5678', '360201', '市辖区', '360201', '江西省景德镇市市辖区', 'jxsjdzssxq');
INSERT INTO `t_system_region` VALUES ('5679', '360202', '昌江区', '360202', '江西省景德镇市昌江区', 'jxsjdzscjq');
INSERT INTO `t_system_region` VALUES ('5680', '360203', '珠山区', '360203', '江西省景德镇市珠山区', 'jxsjdzszsq');
INSERT INTO `t_system_region` VALUES ('5681', '360204', '陶瓷工业园区', '360204', '江西省景德镇市陶瓷工业园区', 'jxsjdzstcgyyq');
INSERT INTO `t_system_region` VALUES ('5682', '360205', '景德镇市高新区', '360205', '江西省景德镇市景德镇市高新区', 'jxsjdzsjdzsgxq');
INSERT INTO `t_system_region` VALUES ('5683', '360222', '浮梁县', '360222', '江西省景德镇市浮梁县', 'jxsjdzsflx');
INSERT INTO `t_system_region` VALUES ('5684', '360281', '乐平市', '360281', '江西省景德镇市乐平市', 'jxsjdzslps');
INSERT INTO `t_system_region` VALUES ('5685', '3603', '萍乡市', '360300', '江西省萍乡市', 'jxspxs');
INSERT INTO `t_system_region` VALUES ('5686', '360301', '市辖区', '360301', '江西省萍乡市市辖区', 'jxspxssxq');
INSERT INTO `t_system_region` VALUES ('5687', '360302', '安源区', '360302', '江西省萍乡市安源区', 'jxspxsayq');
INSERT INTO `t_system_region` VALUES ('5688', '360303', '萍乡市开发区', '360303', '江西省萍乡市萍乡市开发区', 'jxspxspxskfq');
INSERT INTO `t_system_region` VALUES ('5689', '360313', '湘东区', '360313', '江西省萍乡市湘东区', 'jxspxsxdq');
INSERT INTO `t_system_region` VALUES ('5690', '360321', '莲花县', '360321', '江西省萍乡市莲花县', 'jxspxslhx');
INSERT INTO `t_system_region` VALUES ('5691', '360322', '上栗县', '360322', '江西省萍乡市上栗县', 'jxspxsslx');
INSERT INTO `t_system_region` VALUES ('5692', '360323', '芦溪县', '360323', '江西省萍乡市芦溪县', 'jxspxslxx');
INSERT INTO `t_system_region` VALUES ('5693', '3604', '九江市', '360400', '江西省九江市', 'jxsjjs');
INSERT INTO `t_system_region` VALUES ('5694', '360401', '市辖区', '360401', '江西省九江市市辖区', 'jxsjjssxq');
INSERT INTO `t_system_region` VALUES ('5695', '360402', '庐山区', '360402', '江西省九江市庐山区', 'jxsjjslsq');
INSERT INTO `t_system_region` VALUES ('5696', '360403', '浔阳区', '360403', '江西省九江市浔阳区', 'jxsjjsxyq');
INSERT INTO `t_system_region` VALUES ('5697', '360404', '九江经济开发区', '360404', '江西省九江市九江经济开发区', 'jxsjjsjjjjkfq');
INSERT INTO `t_system_region` VALUES ('5698', '360421', '九江县', '360421', '江西省九江市九江县', 'jxsjjsjjx');
INSERT INTO `t_system_region` VALUES ('5699', '360422', '庐山管理局', '360422', '江西省九江市庐山管理局', 'jxsjjslsglj');
INSERT INTO `t_system_region` VALUES ('5700', '360423', '武宁县', '360423', '江西省九江市武宁县', 'jxsjjswnx');
INSERT INTO `t_system_region` VALUES ('5701', '360424', '修水县', '360424', '江西省九江市修水县', 'jxsjjsxsx');
INSERT INTO `t_system_region` VALUES ('5702', '360425', '永修县', '360425', '江西省九江市永修县', 'jxsjjsyxx');
INSERT INTO `t_system_region` VALUES ('5703', '360426', '德安县', '360426', '江西省九江市德安县', 'jxsjjsdax');
INSERT INTO `t_system_region` VALUES ('5704', '360427', '星子县', '360427', '江西省九江市星子县', 'jxsjjsxzx');
INSERT INTO `t_system_region` VALUES ('5705', '360428', '都昌县', '360428', '江西省九江市都昌县', 'jxsjjsdcx');
INSERT INTO `t_system_region` VALUES ('5706', '360429', '湖口县', '360429', '江西省九江市湖口县', 'jxsjjshkx');
INSERT INTO `t_system_region` VALUES ('5707', '360430', '彭泽县', '360430', '江西省九江市彭泽县', 'jxsjjspzx');
INSERT INTO `t_system_region` VALUES ('5708', '360481', '瑞昌市', '360481', '江西省九江市瑞昌市', 'jxsjjsrcs');
INSERT INTO `t_system_region` VALUES ('5709', '360491', '共青城', '360491', '江西省九江市共青城', 'jxsjjsgqc');
INSERT INTO `t_system_region` VALUES ('5710', '3605', '新余市', '360500', '江西省新余市', 'jxsxys');
INSERT INTO `t_system_region` VALUES ('5711', '360501', '市辖区', '360501', '江西省新余市市辖区', 'jxsxyssxq');
INSERT INTO `t_system_region` VALUES ('5712', '360502', '渝水区', '360502', '江西省新余市渝水区', 'jxsxysysq');
INSERT INTO `t_system_region` VALUES ('5713', '360503', '仙女湖区', '360503', '江西省新余市仙女湖区', 'jxsxysxnhq');
INSERT INTO `t_system_region` VALUES ('5714', '360505', '新余经济开发区', '360505', '江西省新余市新余经济开发区', 'jxsxysxyjjkfq');
INSERT INTO `t_system_region` VALUES ('5715', '360521', '分宜县', '360521', '江西省新余市分宜县', 'jxsxysfyx');
INSERT INTO `t_system_region` VALUES ('5716', '3606', '鹰潭市', '360600', '江西省鹰潭市', 'jxsyts');
INSERT INTO `t_system_region` VALUES ('5717', '360601', '市辖区', '360601', '江西省鹰潭市市辖区', 'jxsytssxq');
INSERT INTO `t_system_region` VALUES ('5718', '360602', '月湖区', '360602', '江西省鹰潭市月湖区', 'jxsytsyhq');
INSERT INTO `t_system_region` VALUES ('5719', '360603', '鹰潭经济技术开发区', '360603', '江西省鹰潭市鹰潭经济技术开发区', 'jxsytsytjjjskfq');
INSERT INTO `t_system_region` VALUES ('5720', '360622', '余江县', '360622', '江西省鹰潭市余江县', 'jxsytsyjx');
INSERT INTO `t_system_region` VALUES ('5721', '360623', '龙虎风景旅游区', '360623', '江西省鹰潭市龙虎风景旅游区', 'jxsytslhfjlyq');
INSERT INTO `t_system_region` VALUES ('5722', '360681', '贵溪市', '360681', '江西省鹰潭市贵溪市', 'jxsytsgxs');
INSERT INTO `t_system_region` VALUES ('5723', '3607', '赣州市', '360700', '江西省赣州市', 'jxsgzs');
INSERT INTO `t_system_region` VALUES ('5724', '360701', '市辖区', '360701', '江西省赣州市市辖区', 'jxsgzssxq');
INSERT INTO `t_system_region` VALUES ('5725', '360702', '章贡区', '360702', '江西省赣州市章贡区', 'jxsgzszgq');
INSERT INTO `t_system_region` VALUES ('5726', '360703', '赣州经济技术开发区', '360703', '江西省赣州市赣州经济技术开发区', 'jxsgzsgzjjjskfq');
INSERT INTO `t_system_region` VALUES ('5727', '360721', '赣县', '360721', '江西省赣州市赣县', 'jxsgzsgx');
INSERT INTO `t_system_region` VALUES ('5728', '360722', '信丰县', '360722', '江西省赣州市信丰县', 'jxsgzsxfx');
INSERT INTO `t_system_region` VALUES ('5729', '360723', '大余县', '360723', '江西省赣州市大余县', 'jxsgzsdyx');
INSERT INTO `t_system_region` VALUES ('5730', '360724', '上犹县', '360724', '江西省赣州市上犹县', 'jxsgzssyx');
INSERT INTO `t_system_region` VALUES ('5731', '360725', '崇义县', '360725', '江西省赣州市崇义县', 'jxsgzscyx');
INSERT INTO `t_system_region` VALUES ('5732', '360726', '安远县', '360726', '江西省赣州市安远县', 'jxsgzsayx');
INSERT INTO `t_system_region` VALUES ('5733', '360727', '龙南县', '360727', '江西省赣州市龙南县', 'jxsgzslnx');
INSERT INTO `t_system_region` VALUES ('5734', '360728', '定南县', '360728', '江西省赣州市定南县', 'jxsgzsdnx');
INSERT INTO `t_system_region` VALUES ('5735', '360729', '全南县', '360729', '江西省赣州市全南县', 'jxsgzsqnx');
INSERT INTO `t_system_region` VALUES ('5736', '360730', '宁都县', '360730', '江西省赣州市宁都县', 'jxsgzsndx');
INSERT INTO `t_system_region` VALUES ('5737', '360731', '于都县', '360731', '江西省赣州市于都县', 'jxsgzsydx');
INSERT INTO `t_system_region` VALUES ('5738', '360732', '兴国县', '360732', '江西省赣州市兴国县', 'jxsgzsxgx');
INSERT INTO `t_system_region` VALUES ('5739', '360733', '会昌县', '360733', '江西省赣州市会昌县', 'jxsgzshcx');
INSERT INTO `t_system_region` VALUES ('5740', '360734', '寻乌县', '360734', '江西省赣州市寻乌县', 'jxsgzsxwx');
INSERT INTO `t_system_region` VALUES ('5741', '360735', '石城县', '360735', '江西省赣州市石城县', 'jxsgzsscx');
INSERT INTO `t_system_region` VALUES ('5742', '360781', '瑞金市', '360781', '江西省赣州市瑞金市', 'jxsgzsrjs');
INSERT INTO `t_system_region` VALUES ('5743', '360782', '南康市', '360782', '江西省赣州市南康市', 'jxsgzsnks');
INSERT INTO `t_system_region` VALUES ('5744', '3608', '吉安市', '360800', '江西省吉安市', 'jxsjas');
INSERT INTO `t_system_region` VALUES ('5745', '360801', '市辖区', '360801', '江西省吉安市市辖区', 'jxsjassxq');
INSERT INTO `t_system_region` VALUES ('5746', '360802', '吉州区', '360802', '江西省吉安市吉州区', 'jxsjasjzq');
INSERT INTO `t_system_region` VALUES ('5747', '360803', '青原区', '360803', '江西省吉安市青原区', 'jxsjasqyq');
INSERT INTO `t_system_region` VALUES ('5748', '360821', '吉安县', '360821', '江西省吉安市吉安县', 'jxsjasjax');
INSERT INTO `t_system_region` VALUES ('5749', '360822', '吉水县', '360822', '江西省吉安市吉水县', 'jxsjasjsx');
INSERT INTO `t_system_region` VALUES ('5750', '360823', '峡江县', '360823', '江西省吉安市峡江县', 'jxsjasxjx');
INSERT INTO `t_system_region` VALUES ('5751', '360824', '新干县', '360824', '江西省吉安市新干县', 'jxsjasxgx');
INSERT INTO `t_system_region` VALUES ('5752', '360825', '永丰县', '360825', '江西省吉安市永丰县', 'jxsjasyfx');
INSERT INTO `t_system_region` VALUES ('5753', '360826', '泰和县', '360826', '江西省吉安市泰和县', 'jxsjasthx');
INSERT INTO `t_system_region` VALUES ('5754', '360827', '遂川县', '360827', '江西省吉安市遂川县', 'jxsjasscx');
INSERT INTO `t_system_region` VALUES ('5755', '360828', '万安县', '360828', '江西省吉安市万安县', 'jxsjaswax');
INSERT INTO `t_system_region` VALUES ('5756', '360829', '安福县', '360829', '江西省吉安市安福县', 'jxsjasafx');
INSERT INTO `t_system_region` VALUES ('5757', '360830', '永新县', '360830', '江西省吉安市永新县', 'jxsjasyxx');
INSERT INTO `t_system_region` VALUES ('5758', '360881', '井冈山市', '360881', '江西省吉安市井冈山市', 'jxsjasjgss');
INSERT INTO `t_system_region` VALUES ('5759', '3609', '宜春市', '360900', '江西省宜春市', 'jxsycs');
INSERT INTO `t_system_region` VALUES ('5760', '360901', '市辖区', '360901', '江西省宜春市市辖区', 'jxsycssxq');
INSERT INTO `t_system_region` VALUES ('5761', '360902', '袁州区', '360902', '江西省宜春市袁州区', 'jxsycsyzq');
INSERT INTO `t_system_region` VALUES ('5762', '360921', '奉新县', '360921', '江西省宜春市奉新县', 'jxsycsfxx');
INSERT INTO `t_system_region` VALUES ('5763', '360922', '万载县', '360922', '江西省宜春市万载县', 'jxsycswzx');
INSERT INTO `t_system_region` VALUES ('5764', '360923', '上高县', '360923', '江西省宜春市上高县', 'jxsycssgx');
INSERT INTO `t_system_region` VALUES ('5765', '360924', '宜丰县', '360924', '江西省宜春市宜丰县', 'jxsycsyfx');
INSERT INTO `t_system_region` VALUES ('5766', '360925', '靖安县', '360925', '江西省宜春市靖安县', 'jxsycsjax');
INSERT INTO `t_system_region` VALUES ('5767', '360926', '铜鼓县', '360926', '江西省宜春市铜鼓县', 'jxsycstgx');
INSERT INTO `t_system_region` VALUES ('5768', '360981', '丰城市', '360981', '江西省宜春市丰城市', 'jxsycsfcs');
INSERT INTO `t_system_region` VALUES ('5769', '360982', '樟树市', '360982', '江西省宜春市樟树市', 'jxsycszss');
INSERT INTO `t_system_region` VALUES ('5770', '360983', '高安市', '360983', '江西省宜春市高安市', 'jxsycsgas');
INSERT INTO `t_system_region` VALUES ('5771', '3610', '抚州市', '361000', '江西省抚州市', 'jxsfzs');
INSERT INTO `t_system_region` VALUES ('5772', '361001', '市辖区', '361001', '江西省抚州市市辖区', 'jxsfzssxq');
INSERT INTO `t_system_region` VALUES ('5773', '361002', '临川区', '361002', '江西省抚州市临川区', 'jxsfzslcq');
INSERT INTO `t_system_region` VALUES ('5774', '361003', '抚州金巢经济开发区', '361003', '江西省抚州市抚州金巢经济开发区', 'jxsfzsfzjcjjkfq');
INSERT INTO `t_system_region` VALUES ('5775', '361021', '南城县', '361021', '江西省抚州市南城县', 'jxsfzsncx');
INSERT INTO `t_system_region` VALUES ('5776', '361022', '黎川县', '361022', '江西省抚州市黎川县', 'jxsfzslcx');
INSERT INTO `t_system_region` VALUES ('5777', '361023', '南丰县', '361023', '江西省抚州市南丰县', 'jxsfzsnfx');
INSERT INTO `t_system_region` VALUES ('5778', '361024', '崇仁县', '361024', '江西省抚州市崇仁县', 'jxsfzscrx');
INSERT INTO `t_system_region` VALUES ('5779', '361025', '乐安县', '361025', '江西省抚州市乐安县', 'jxsfzslax');
INSERT INTO `t_system_region` VALUES ('5780', '361026', '宜黄县', '361026', '江西省抚州市宜黄县', 'jxsfzsyhx');
INSERT INTO `t_system_region` VALUES ('5781', '361027', '金溪县', '361027', '江西省抚州市金溪县', 'jxsfzsjxx');
INSERT INTO `t_system_region` VALUES ('5782', '361028', '资溪县', '361028', '江西省抚州市资溪县', 'jxsfzszxx');
INSERT INTO `t_system_region` VALUES ('5783', '361029', '东乡县', '361029', '江西省抚州市东乡县', 'jxsfzsdxx');
INSERT INTO `t_system_region` VALUES ('5784', '361030', '广昌县', '361030', '江西省抚州市广昌县', 'jxsfzsgcx');
INSERT INTO `t_system_region` VALUES ('5785', '3611', '上饶市', '361100', '江西省上饶市', 'jxssrs');
INSERT INTO `t_system_region` VALUES ('5786', '361101', '市辖区', '361101', '江西省上饶市市辖区', 'jxssrssxq');
INSERT INTO `t_system_region` VALUES ('5787', '361102', '信州区', '361102', '江西省上饶市信州区', 'jxssrsxzq');
INSERT INTO `t_system_region` VALUES ('5788', '361103', '三管会', '361103', '江西省上饶市三管会', 'jxssrssgh');
INSERT INTO `t_system_region` VALUES ('5789', '361104', '上饶市经济开发区', '361104', '江西省上饶市上饶市经济开发区', 'jxssrssrsjjkfq');
INSERT INTO `t_system_region` VALUES ('5790', '361121', '上饶县', '361121', '江西省上饶市上饶县', 'jxssrssrx');
INSERT INTO `t_system_region` VALUES ('5791', '361122', '广丰县', '361122', '江西省上饶市广丰县', 'jxssrsgfx');
INSERT INTO `t_system_region` VALUES ('5792', '361123', '玉山县', '361123', '江西省上饶市玉山县', 'jxssrsysx');
INSERT INTO `t_system_region` VALUES ('5793', '361124', '铅山县', '361124', '江西省上饶市铅山县', 'jxssrsqsx');
INSERT INTO `t_system_region` VALUES ('5794', '361125', '横峰县', '361125', '江西省上饶市横峰县', 'jxssrshfx');
INSERT INTO `t_system_region` VALUES ('5795', '361126', '弋阳县', '361126', '江西省上饶市弋阳县', 'jxssrsyyx');
INSERT INTO `t_system_region` VALUES ('5796', '361127', '余干县', '361127', '江西省上饶市余干县', 'jxssrsygx');
INSERT INTO `t_system_region` VALUES ('5797', '361128', '鄱阳县', '361128', '江西省上饶市鄱阳县', 'jxssrspyx');
INSERT INTO `t_system_region` VALUES ('5798', '361129', '万年县', '361129', '江西省上饶市万年县', 'jxssrswnx');
INSERT INTO `t_system_region` VALUES ('5799', '361130', '婺源县', '361130', '江西省上饶市婺源县', 'jxssrswyx');
INSERT INTO `t_system_region` VALUES ('5800', '361181', '德兴市', '361181', '江西省上饶市德兴市', 'jxssrsdxs');
INSERT INTO `t_system_region` VALUES ('5801', '37', '山东省', '370000', '山东省', 'sds');
INSERT INTO `t_system_region` VALUES ('5803', '3701', '济南市', '370100', '山东省济南市', 'sdsjns');
INSERT INTO `t_system_region` VALUES ('5804', '370101', '市辖区', '370101', '山东省济南市市辖区', 'sdsjnssxq');
INSERT INTO `t_system_region` VALUES ('5805', '370102', '历下区', '370102', '山东省济南市历下区', 'sdsjnslxq');
INSERT INTO `t_system_region` VALUES ('5806', '370103', '市中区', '370103', '山东省济南市市中区', 'sdsjnsszq');
INSERT INTO `t_system_region` VALUES ('5807', '370104', '槐荫区', '370104', '山东省济南市槐荫区', 'sdsjnshyq');
INSERT INTO `t_system_region` VALUES ('5808', '370105', '天桥区', '370105', '山东省济南市天桥区', 'sdsjnstqq');
INSERT INTO `t_system_region` VALUES ('5809', '370106', '济南市高新区', '370106', '山东省济南市济南市高新区', 'sdsjnsjnsgxq');
INSERT INTO `t_system_region` VALUES ('5810', '370112', '历城区', '370112', '山东省济南市历城区', 'sdsjnslcq');
INSERT INTO `t_system_region` VALUES ('5811', '370113', '长清区', '370113', '山东省济南市长清区', 'sdsjnszqq');
INSERT INTO `t_system_region` VALUES ('5812', '370124', '平阴县', '370124', '山东省济南市平阴县', 'sdsjnspyx');
INSERT INTO `t_system_region` VALUES ('5813', '370125', '济阳县', '370125', '山东省济南市济阳县', 'sdsjnsjyx');
INSERT INTO `t_system_region` VALUES ('5814', '370126', '商河县', '370126', '山东省济南市商河县', 'sdsjnsshx');
INSERT INTO `t_system_region` VALUES ('5815', '370181', '章丘市', '370181', '山东省济南市章丘市', 'sdsjnszqs');
INSERT INTO `t_system_region` VALUES ('5816', '3702', '青岛市', '370200', '山东省青岛市', 'sdsqds');
INSERT INTO `t_system_region` VALUES ('5817', '370201', '市辖区', '370201', '山东省青岛市市辖区', 'sdsqdssxq');
INSERT INTO `t_system_region` VALUES ('5818', '370202', '市南区', '370202', '山东省青岛市市南区', 'sdsqdssnq');
INSERT INTO `t_system_region` VALUES ('5819', '370203', '市北区', '370203', '山东省青岛市市北区', 'sdsqdssbq');
INSERT INTO `t_system_region` VALUES ('5820', '370205', '四方区', '370205', '山东省青岛市四方区', 'sdsqdssfq');
INSERT INTO `t_system_region` VALUES ('5821', '370211', '黄岛区', '370211', '山东省青岛市黄岛区', 'sdsqdshdq');
INSERT INTO `t_system_region` VALUES ('5822', '370212', '崂山区', '370212', '山东省青岛市崂山区', 'sdsqdslsq');
INSERT INTO `t_system_region` VALUES ('5823', '370213', '李沧区', '370213', '山东省青岛市李沧区', 'sdsqdslcq');
INSERT INTO `t_system_region` VALUES ('5824', '370214', '城阳区', '370214', '山东省青岛市城阳区', 'sdsqdscyq');
INSERT INTO `t_system_region` VALUES ('5825', '370281', '胶州市', '370281', '山东省青岛市胶州市', 'sdsqdsjzs');
INSERT INTO `t_system_region` VALUES ('5826', '370282', '即墨市', '370282', '山东省青岛市即墨市', 'sdsqdsjms');
INSERT INTO `t_system_region` VALUES ('5827', '370283', '平度市', '370283', '山东省青岛市平度市', 'sdsqdspds');
INSERT INTO `t_system_region` VALUES ('5828', '370284', '胶南市', '370284', '山东省青岛市胶南市', 'sdsqdsjns');
INSERT INTO `t_system_region` VALUES ('5829', '370285', '莱西市', '370285', '山东省青岛市莱西市', 'sdsqdslxs');
INSERT INTO `t_system_region` VALUES ('5830', '3703', '淄博市', '370300', '山东省淄博市', 'sdszbs');
INSERT INTO `t_system_region` VALUES ('5831', '370301', '市辖区', '370301', '山东省淄博市市辖区', 'sdszbssxq');
INSERT INTO `t_system_region` VALUES ('5832', '370302', '淄川区', '370302', '山东省淄博市淄川区', 'sdszbszcq');
INSERT INTO `t_system_region` VALUES ('5833', '370303', '张店区', '370303', '山东省淄博市张店区', 'sdszbszdq');
INSERT INTO `t_system_region` VALUES ('5834', '370304', '博山区', '370304', '山东省淄博市博山区', 'sdszbsbsq');
INSERT INTO `t_system_region` VALUES ('5835', '370305', '临淄区', '370305', '山东省淄博市临淄区', 'sdszbslzq');
INSERT INTO `t_system_region` VALUES ('5836', '370306', '周村区', '370306', '山东省淄博市周村区', 'sdszbszcq');
INSERT INTO `t_system_region` VALUES ('5837', '370307', '淄博市高新区', '370307', '山东省淄博市淄博市高新区', 'sdszbszbsgxq');
INSERT INTO `t_system_region` VALUES ('5838', '370321', '桓台县', '370321', '山东省淄博市桓台县', 'sdszbshtx');
INSERT INTO `t_system_region` VALUES ('5839', '370322', '高青县', '370322', '山东省淄博市高青县', 'sdszbsgqx');
INSERT INTO `t_system_region` VALUES ('5840', '370323', '沂源县', '370323', '山东省淄博市沂源县', 'sdszbsyyx');
INSERT INTO `t_system_region` VALUES ('5841', '3704', '枣庄市', '370400', '山东省枣庄市', 'sdszzs');
INSERT INTO `t_system_region` VALUES ('5842', '370401', '市辖区', '370401', '山东省枣庄市市辖区', 'sdszzssxq');
INSERT INTO `t_system_region` VALUES ('5843', '370402', '市中区', '370402', '山东省枣庄市市中区', 'sdszzsszq');
INSERT INTO `t_system_region` VALUES ('5844', '370403', '薛城区', '370403', '山东省枣庄市薛城区', 'sdszzsxcq');
INSERT INTO `t_system_region` VALUES ('5845', '370404', '峄城区', '370404', '山东省枣庄市峄城区', 'sdszzsycq');
INSERT INTO `t_system_region` VALUES ('5846', '370405', '台儿庄区', '370405', '山东省枣庄市台儿庄区', 'sdszzstezq');
INSERT INTO `t_system_region` VALUES ('5847', '370406', '山亭区', '370406', '山东省枣庄市山亭区', 'sdszzsstq');
INSERT INTO `t_system_region` VALUES ('5848', '370407', '枣庄市高新开发区', '370407', '山东省枣庄市枣庄市高新开发区', 'sdszzszzsgxkfq');
INSERT INTO `t_system_region` VALUES ('5849', '370481', '滕州市', '370481', '山东省枣庄市滕州市', 'sdszzstzs');
INSERT INTO `t_system_region` VALUES ('5850', '3705', '东营市', '370500', '山东省东营市', 'sdsdys');
INSERT INTO `t_system_region` VALUES ('5851', '370501', '市辖区', '370501', '山东省东营市市辖区', 'sdsdyssxq');
INSERT INTO `t_system_region` VALUES ('5852', '370502', '东营区', '370502', '山东省东营市东营区', 'sdsdysdyq');
INSERT INTO `t_system_region` VALUES ('5853', '370503', '河口区', '370503', '山东省东营市河口区', 'sdsdyshkq');
INSERT INTO `t_system_region` VALUES ('5854', '370505', '胜利石油教管中心', '370505', '山东省东营市胜利石油教管中心', 'sdsdysslsyjgzx');
INSERT INTO `t_system_region` VALUES ('5855', '370521', '垦利县', '370521', '山东省东营市垦利县', 'sdsdysklx');
INSERT INTO `t_system_region` VALUES ('5856', '370522', '利津县', '370522', '山东省东营市利津县', 'sdsdysljx');
INSERT INTO `t_system_region` VALUES ('5857', '370523', '广饶县', '370523', '山东省东营市广饶县', 'sdsdysgrx');
INSERT INTO `t_system_region` VALUES ('5858', '3706', '烟台市', '370600', '山东省烟台市', 'sdsyts');
INSERT INTO `t_system_region` VALUES ('5859', '370601', '市辖区', '370601', '山东省烟台市市辖区', 'sdsytssxq');
INSERT INTO `t_system_region` VALUES ('5860', '370602', '芝罘区', '370602', '山东省烟台市芝罘区', 'sdsytszfq');
INSERT INTO `t_system_region` VALUES ('5861', '370603', '烟台市开发区', '370603', '山东省烟台市烟台市开发区', 'sdsytsytskfq');
INSERT INTO `t_system_region` VALUES ('5862', '370604', '烟强市高新区', '370604', '山东省烟台市烟强市高新区', 'sdsytsyqsgxq');
INSERT INTO `t_system_region` VALUES ('5863', '370611', '福山区', '370611', '山东省烟台市福山区', 'sdsytsfsq');
INSERT INTO `t_system_region` VALUES ('5864', '370612', '牟平区', '370612', '山东省烟台市牟平区', 'sdsytsmpq');
INSERT INTO `t_system_region` VALUES ('5865', '370613', '莱山区', '370613', '山东省烟台市莱山区', 'sdsytslsq');
INSERT INTO `t_system_region` VALUES ('5866', '370634', '长岛县', '370634', '山东省烟台市长岛县', 'sdsytszdx');
INSERT INTO `t_system_region` VALUES ('5867', '370681', '龙口市', '370681', '山东省烟台市龙口市', 'sdsytslks');
INSERT INTO `t_system_region` VALUES ('5868', '370682', '莱阳市', '370682', '山东省烟台市莱阳市', 'sdsytslys');
INSERT INTO `t_system_region` VALUES ('5869', '370683', '莱州市', '370683', '山东省烟台市莱州市', 'sdsytslzs');
INSERT INTO `t_system_region` VALUES ('5870', '370684', '蓬莱市', '370684', '山东省烟台市蓬莱市', 'sdsytspls');
INSERT INTO `t_system_region` VALUES ('5871', '370685', '招远市', '370685', '山东省烟台市招远市', 'sdsytszys');
INSERT INTO `t_system_region` VALUES ('5872', '370686', '栖霞市', '370686', '山东省烟台市栖霞市', 'sdsytsqxs');
INSERT INTO `t_system_region` VALUES ('5873', '370687', '海阳市', '370687', '山东省烟台市海阳市', 'sdsytshys');
INSERT INTO `t_system_region` VALUES ('5874', '3707', '潍坊市', '370700', '山东省潍坊市', 'sdswfs');
INSERT INTO `t_system_region` VALUES ('5875', '370701', '市辖区', '370701', '山东省潍坊市市辖区', 'sdswfssxq');
INSERT INTO `t_system_region` VALUES ('5876', '370702', '潍城区', '370702', '山东省潍坊市潍城区', 'sdswfswcq');
INSERT INTO `t_system_region` VALUES ('5877', '370703', '寒亭区', '370703', '山东省潍坊市寒亭区', 'sdswfshtq');
INSERT INTO `t_system_region` VALUES ('5878', '370704', '坊子区', '370704', '山东省潍坊市坊子区', 'sdswfsfzq');
INSERT INTO `t_system_region` VALUES ('5879', '370705', '奎文区', '370705', '山东省潍坊市奎文区', 'sdswfskwq');
INSERT INTO `t_system_region` VALUES ('5880', '370706', '潍坊市高新区', '370706', '山东省潍坊市潍坊市高新区', 'sdswfswfsgxq');
INSERT INTO `t_system_region` VALUES ('5881', '370707', '潍坊市滨海区', '370707', '山东省潍坊市潍坊市滨海区', 'sdswfswfsbhq');
INSERT INTO `t_system_region` VALUES ('5882', '370708', '潍坊市经济区', '370708', '山东省潍坊市潍坊市经济区', 'sdswfswfsjjq');
INSERT INTO `t_system_region` VALUES ('5883', '370709', '潍坊市峡山区', '370709', '山东省潍坊市潍坊市峡山区', 'sdswfswfsxsq');
INSERT INTO `t_system_region` VALUES ('5884', '370724', '临朐县', '370724', '山东省潍坊市临朐县', 'sdswfslqx');
INSERT INTO `t_system_region` VALUES ('5885', '370725', '昌乐县', '370725', '山东省潍坊市昌乐县', 'sdswfsclx');
INSERT INTO `t_system_region` VALUES ('5886', '370781', '青州市', '370781', '山东省潍坊市青州市', 'sdswfsqzs');
INSERT INTO `t_system_region` VALUES ('5887', '370782', '诸城市', '370782', '山东省潍坊市诸城市', 'sdswfszcs');
INSERT INTO `t_system_region` VALUES ('5888', '370783', '寿光市', '370783', '山东省潍坊市寿光市', 'sdswfssgs');
INSERT INTO `t_system_region` VALUES ('5889', '370784', '安丘市', '370784', '山东省潍坊市安丘市', 'sdswfsaqs');
INSERT INTO `t_system_region` VALUES ('5890', '370785', '高密市', '370785', '山东省潍坊市高密市', 'sdswfsgms');
INSERT INTO `t_system_region` VALUES ('5891', '370786', '昌邑市', '370786', '山东省潍坊市昌邑市', 'sdswfscys');
INSERT INTO `t_system_region` VALUES ('5892', '3708', '济宁市', '370800', '山东省济宁市', 'sdsjns');
INSERT INTO `t_system_region` VALUES ('5893', '370801', '市辖区', '370801', '山东省济宁市市辖区', 'sdsjnssxq');
INSERT INTO `t_system_region` VALUES ('5894', '370802', '市中区', '370802', '山东省济宁市市中区', 'sdsjnsszq');
INSERT INTO `t_system_region` VALUES ('5895', '370803', '济宁市高新区', '370803', '山东省济宁市济宁市高新区', 'sdsjnsjnsgxq');
INSERT INTO `t_system_region` VALUES ('5896', '370804', '济宁市北湖区', '370804', '山东省济宁市济宁市北湖区', 'sdsjnsjnsbhq');
INSERT INTO `t_system_region` VALUES ('5897', '370811', '任城区', '370811', '山东省济宁市任城区', 'sdsjnsrcq');
INSERT INTO `t_system_region` VALUES ('5898', '370826', '微山县', '370826', '山东省济宁市微山县', 'sdsjnswsx');
INSERT INTO `t_system_region` VALUES ('5899', '370827', '鱼台县', '370827', '山东省济宁市鱼台县', 'sdsjnsytx');
INSERT INTO `t_system_region` VALUES ('5900', '370828', '金乡县', '370828', '山东省济宁市金乡县', 'sdsjnsjxx');
INSERT INTO `t_system_region` VALUES ('5901', '370829', '嘉祥县', '370829', '山东省济宁市嘉祥县', 'sdsjnsjxx');
INSERT INTO `t_system_region` VALUES ('5902', '370830', '汶上县', '370830', '山东省济宁市汶上县', 'sdsjnswsx');
INSERT INTO `t_system_region` VALUES ('5903', '370831', '泗水县', '370831', '山东省济宁市泗水县', 'sdsjnsssx');
INSERT INTO `t_system_region` VALUES ('5904', '370832', '梁山县', '370832', '山东省济宁市梁山县', 'sdsjnslsx');
INSERT INTO `t_system_region` VALUES ('5905', '370881', '曲阜市', '370881', '山东省济宁市曲阜市', 'sdsjnsqfs');
INSERT INTO `t_system_region` VALUES ('5906', '370882', '兖州市', '370882', '山东省济宁市兖州市', 'sdsjnsyzs');
INSERT INTO `t_system_region` VALUES ('5907', '370883', '邹城市', '370883', '山东省济宁市邹城市', 'sdsjnszcs');
INSERT INTO `t_system_region` VALUES ('5908', '3709', '泰安市', '370900', '山东省泰安市', 'sdstas');
INSERT INTO `t_system_region` VALUES ('5909', '370901', '市辖区', '370901', '山东省泰安市市辖区', 'sdstassxq');
INSERT INTO `t_system_region` VALUES ('5910', '370902', '泰山区', '370902', '山东省泰安市泰山区', 'sdstastsq');
INSERT INTO `t_system_region` VALUES ('5911', '370904', '泰安市高新区', '370904', '山东省泰安市泰安市高新区', 'sdstastasgxq');
INSERT INTO `t_system_region` VALUES ('5912', '370905', '泰安市泰山管委', '370905', '山东省泰安市泰安市泰山管委', 'sdstastastsgw');
INSERT INTO `t_system_region` VALUES ('5913', '370911', '岱岳区', '370911', '山东省泰安市岱岳区', 'sdstasdyq');
INSERT INTO `t_system_region` VALUES ('5914', '370921', '宁阳县', '370921', '山东省泰安市宁阳县', 'sdstasnyx');
INSERT INTO `t_system_region` VALUES ('5915', '370923', '东平县', '370923', '山东省泰安市东平县', 'sdstasdpx');
INSERT INTO `t_system_region` VALUES ('5916', '370982', '新泰市', '370982', '山东省泰安市新泰市', 'sdstasxts');
INSERT INTO `t_system_region` VALUES ('5917', '370983', '肥城市', '370983', '山东省泰安市肥城市', 'sdstasfcs');
INSERT INTO `t_system_region` VALUES ('5918', '3710', '威海市', '371000', '山东省威海市', 'sdswhs');
INSERT INTO `t_system_region` VALUES ('5919', '371001', '市辖区', '371001', '山东省威海市市辖区', 'sdswhssxq');
INSERT INTO `t_system_region` VALUES ('5920', '371002', '环翠区', '371002', '山东省威海市环翠区', 'sdswhshcq');
INSERT INTO `t_system_region` VALUES ('5921', '371003', '威海市高新开发区', '371003', '山东省威海市威海市高新开发区', 'sdswhswhsgxkfq');
INSERT INTO `t_system_region` VALUES ('5922', '371004', '威海市经济开发区', '371004', '山东省威海市威海市经济开发区', 'sdswhswhsjjkfq');
INSERT INTO `t_system_region` VALUES ('5923', '371005', '威海市工业新区', '371005', '山东省威海市威海市工业新区', 'sdswhswhsgyxq');
INSERT INTO `t_system_region` VALUES ('5924', '371081', '文登市', '371081', '山东省威海市文登市', 'sdswhswds');
INSERT INTO `t_system_region` VALUES ('5925', '371082', '荣成市', '371082', '山东省威海市荣成市', 'sdswhsrcs');
INSERT INTO `t_system_region` VALUES ('5926', '371083', '乳山市', '371083', '山东省威海市乳山市', 'sdswhsrss');
INSERT INTO `t_system_region` VALUES ('5927', '3711', '日照市', '371100', '山东省日照市', 'sdsrzs');
INSERT INTO `t_system_region` VALUES ('5928', '371101', '市辖区', '371101', '山东省日照市市辖区', 'sdsrzssxq');
INSERT INTO `t_system_region` VALUES ('5929', '371102', '东港区', '371102', '山东省日照市东港区', 'sdsrzsdgq');
INSERT INTO `t_system_region` VALUES ('5930', '371103', '岚山区', '371103', '山东省日照市岚山区', 'sdsrzslsq');
INSERT INTO `t_system_region` VALUES ('5931', '371104', '日照市开发区', '371104', '山东省日照市日照市开发区', 'sdsrzsrzskfq');
INSERT INTO `t_system_region` VALUES ('5932', '371121', '五莲县', '371121', '山东省日照市五莲县', 'sdsrzswlx');
INSERT INTO `t_system_region` VALUES ('5933', '371122', '莒县', '371122', '山东省日照市莒县', 'sdsrzsjx');
INSERT INTO `t_system_region` VALUES ('5934', '3712', '莱芜市', '371200', '山东省莱芜市', 'sdslws');
INSERT INTO `t_system_region` VALUES ('5935', '371201', '市辖区', '371201', '山东省莱芜市市辖区', 'sdslwssxq');
INSERT INTO `t_system_region` VALUES ('5936', '371202', '莱城区', '371202', '山东省莱芜市莱城区', 'sdslwslcq');
INSERT INTO `t_system_region` VALUES ('5937', '371203', '钢城区', '371203', '山东省莱芜市钢城区', 'sdslwsgcq');
INSERT INTO `t_system_region` VALUES ('5938', '371204', '莱芜市高新开发区', '371204', '山东省莱芜市莱芜市高新开发区', 'sdslwslwsgxkfq');
INSERT INTO `t_system_region` VALUES ('5939', '371205', '莱芜市泰钢工业园', '371205', '山东省莱芜市莱芜市泰钢工业园', 'sdslwslwstggyy');
INSERT INTO `t_system_region` VALUES ('5940', '371206', '莱芜市雪野旅游区', '371206', '山东省莱芜市莱芜市雪野旅游区', 'sdslwslwsxylyq');
INSERT INTO `t_system_region` VALUES ('5941', '3713', '临沂市', '371300', '山东省临沂市', 'sdslys');
INSERT INTO `t_system_region` VALUES ('5942', '371301', '市辖区', '371301', '山东省临沂市市辖区', 'sdslyssxq');
INSERT INTO `t_system_region` VALUES ('5943', '371302', '兰山区', '371302', '山东省临沂市兰山区', 'sdslyslsq');
INSERT INTO `t_system_region` VALUES ('5944', '371303', '临沂市高新开发区', '371303', '山东省临沂市临沂市高新开发区', 'sdslyslysgxkfq');
INSERT INTO `t_system_region` VALUES ('5945', '371304', '临沂市经济开发区', '371304', '山东省临沂市临沂市经济开发区', 'sdslyslysjjkfq');
INSERT INTO `t_system_region` VALUES ('5946', '371305', '临沂市临港产业区', '371305', '山东省临沂市临沂市临港产业区', 'sdslyslyslgcyq');
INSERT INTO `t_system_region` VALUES ('5947', '371311', '罗庄区', '371311', '山东省临沂市罗庄区', 'sdslyslzq');
INSERT INTO `t_system_region` VALUES ('5948', '371312', '河东区', '371312', '山东省临沂市河东区', 'sdslyshdq');
INSERT INTO `t_system_region` VALUES ('5949', '371321', '沂南县', '371321', '山东省临沂市沂南县', 'sdslysynx');
INSERT INTO `t_system_region` VALUES ('5950', '371322', '郯城县', '371322', '山东省临沂市郯城县', 'sdslystcx');
INSERT INTO `t_system_region` VALUES ('5951', '371323', '沂水县', '371323', '山东省临沂市沂水县', 'sdslysysx');
INSERT INTO `t_system_region` VALUES ('5952', '371324', '苍山县', '371324', '山东省临沂市苍山县', 'sdslyscsx');
INSERT INTO `t_system_region` VALUES ('5953', '371325', '费县', '371325', '山东省临沂市费县', 'sdslysfx');
INSERT INTO `t_system_region` VALUES ('5954', '371326', '平邑县', '371326', '山东省临沂市平邑县', 'sdslyspyx');
INSERT INTO `t_system_region` VALUES ('5955', '371327', '莒南县', '371327', '山东省临沂市莒南县', 'sdslysjnx');
INSERT INTO `t_system_region` VALUES ('5956', '371328', '蒙阴县', '371328', '山东省临沂市蒙阴县', 'sdslysmyx');
INSERT INTO `t_system_region` VALUES ('5957', '371329', '临沭县', '371329', '山东省临沂市临沭县', 'sdslyslsx');
INSERT INTO `t_system_region` VALUES ('5958', '3714', '德州市', '371400', '山东省德州市', 'sdsdzs');
INSERT INTO `t_system_region` VALUES ('5959', '371401', '市辖区', '371401', '山东省德州市市辖区', 'sdsdzssxq');
INSERT INTO `t_system_region` VALUES ('5960', '371402', '德城区', '371402', '山东省德州市德城区', 'sdsdzsdcq');
INSERT INTO `t_system_region` VALUES ('5961', '371403', '德州市经济开发区', '371403', '山东省德州市德州市经济开发区', 'sdsdzsdzsjjkfq');
INSERT INTO `t_system_region` VALUES ('5962', '371404', '德州市运河开发区', '371404', '山东省德州市德州市运河开发区', 'sdsdzsdzsyhkfq');
INSERT INTO `t_system_region` VALUES ('5963', '371421', '陵县', '371421', '山东省德州市陵县', 'sdsdzslx');
INSERT INTO `t_system_region` VALUES ('5964', '371422', '宁津县', '371422', '山东省德州市宁津县', 'sdsdzsnjx');
INSERT INTO `t_system_region` VALUES ('5965', '371423', '庆云县', '371423', '山东省德州市庆云县', 'sdsdzsqyx');
INSERT INTO `t_system_region` VALUES ('5966', '371424', '临邑县', '371424', '山东省德州市临邑县', 'sdsdzslyx');
INSERT INTO `t_system_region` VALUES ('5967', '371425', '齐河县', '371425', '山东省德州市齐河县', 'sdsdzsqhx');
INSERT INTO `t_system_region` VALUES ('5968', '371426', '平原县', '371426', '山东省德州市平原县', 'sdsdzspyx');
INSERT INTO `t_system_region` VALUES ('5969', '371427', '夏津县', '371427', '山东省德州市夏津县', 'sdsdzsxjx');
INSERT INTO `t_system_region` VALUES ('5970', '371428', '武城县', '371428', '山东省德州市武城县', 'sdsdzswcx');
INSERT INTO `t_system_region` VALUES ('5971', '371481', '乐陵市', '371481', '山东省德州市乐陵市', 'sdsdzslls');
INSERT INTO `t_system_region` VALUES ('5972', '371482', '禹城市', '371482', '山东省德州市禹城市', 'sdsdzsycs');
INSERT INTO `t_system_region` VALUES ('5973', '3715', '聊城市', '371500', '山东省聊城市', 'sdslcs');
INSERT INTO `t_system_region` VALUES ('5974', '371501', '市辖区', '371501', '山东省聊城市市辖区', 'sdslcssxq');
INSERT INTO `t_system_region` VALUES ('5975', '371502', '东昌府区', '371502', '山东省聊城市东昌府区', 'sdslcsdcfq');
INSERT INTO `t_system_region` VALUES ('5976', '371503', '聊城市开发区', '371503', '山东省聊城市聊城市开发区', 'sdslcslcskfq');
INSERT INTO `t_system_region` VALUES ('5977', '371521', '阳谷县', '371521', '山东省聊城市阳谷县', 'sdslcsygx');
INSERT INTO `t_system_region` VALUES ('5978', '371522', '莘县', '371522', '山东省聊城市莘县', 'sdslcsxx');
INSERT INTO `t_system_region` VALUES ('5979', '371523', '茌平县', '371523', '山东省聊城市茌平县', 'sdslcscpx');
INSERT INTO `t_system_region` VALUES ('5980', '371524', '东阿县', '371524', '山东省聊城市东阿县', 'sdslcsdax');
INSERT INTO `t_system_region` VALUES ('5981', '371525', '冠县', '371525', '山东省聊城市冠县', 'sdslcsgx');
INSERT INTO `t_system_region` VALUES ('5982', '371526', '高唐县', '371526', '山东省聊城市高唐县', 'sdslcsgtx');
INSERT INTO `t_system_region` VALUES ('5983', '371581', '临清市', '371581', '山东省聊城市临清市', 'sdslcslqs');
INSERT INTO `t_system_region` VALUES ('5984', '3716', '滨州市', '371600', '山东省滨州市', 'sdsbzs');
INSERT INTO `t_system_region` VALUES ('5985', '371601', '市辖区', '371601', '山东省滨州市市辖区', 'sdsbzssxq');
INSERT INTO `t_system_region` VALUES ('5986', '371602', '滨城区', '371602', '山东省滨州市滨城区', 'sdsbzsbcq');
INSERT INTO `t_system_region` VALUES ('5987', '371604', '滨州市开发区', '371604', '山东省滨州市滨州市开发区', 'sdsbzsbzskfq');
INSERT INTO `t_system_region` VALUES ('5988', '371621', '惠民县', '371621', '山东省滨州市惠民县', 'sdsbzshmx');
INSERT INTO `t_system_region` VALUES ('5989', '371622', '阳信县', '371622', '山东省滨州市阳信县', 'sdsbzsyxx');
INSERT INTO `t_system_region` VALUES ('5990', '371623', '无棣县', '371623', '山东省滨州市无棣县', 'sdsbzswdx');
INSERT INTO `t_system_region` VALUES ('5991', '371624', '沾化县', '371624', '山东省滨州市沾化县', 'sdsbzszhx');
INSERT INTO `t_system_region` VALUES ('5992', '371625', '博兴县', '371625', '山东省滨州市博兴县', 'sdsbzsbxx');
INSERT INTO `t_system_region` VALUES ('5993', '371626', '邹平县', '371626', '山东省滨州市邹平县', 'sdsbzszpx');
INSERT INTO `t_system_region` VALUES ('5994', '3717', '菏泽市', '371700', '山东省菏泽市', 'sdshzs');
INSERT INTO `t_system_region` VALUES ('5995', '371701', '市辖区', '371701', '山东省菏泽市市辖区', 'sdshzssxq');
INSERT INTO `t_system_region` VALUES ('5996', '371702', '牡丹区', '371702', '山东省菏泽市牡丹区', 'sdshzsmdq');
INSERT INTO `t_system_region` VALUES ('5997', '371703', '菏泽市开发区', '371703', '山东省菏泽市菏泽市开发区', 'sdshzshzskfq');
INSERT INTO `t_system_region` VALUES ('5998', '371721', '曹县', '371721', '山东省菏泽市曹县', 'sdshzscx');
INSERT INTO `t_system_region` VALUES ('5999', '371722', '单县', '371722', '山东省菏泽市单县', 'sdshzsdx');
INSERT INTO `t_system_region` VALUES ('6000', '371723', '成武县', '371723', '山东省菏泽市成武县', 'sdshzscwx');
INSERT INTO `t_system_region` VALUES ('6001', '371724', '巨野县', '371724', '山东省菏泽市巨野县', 'sdshzsjyx');
INSERT INTO `t_system_region` VALUES ('6002', '371725', '郓城县', '371725', '山东省菏泽市郓城县', 'sdshzsycx');
INSERT INTO `t_system_region` VALUES ('6003', '371726', '鄄城县', '371726', '山东省菏泽市鄄城县', 'sdshzsjcx');
INSERT INTO `t_system_region` VALUES ('6004', '371727', '定陶县', '371727', '山东省菏泽市定陶县', 'sdshzsdtx');
INSERT INTO `t_system_region` VALUES ('6005', '371728', '东明县', '371728', '山东省菏泽市东明县', 'sdshzsdmx');
INSERT INTO `t_system_region` VALUES ('6006', '41', '河南省', '410000', '河南省', 'hns');
INSERT INTO `t_system_region` VALUES ('6007', '4101', '郑州市', '410100', '河南省郑州市', 'hnszzs');
INSERT INTO `t_system_region` VALUES ('6008', '410101', '市辖区', '410101', '河南省郑州市市辖区', 'hnszzssxq');
INSERT INTO `t_system_region` VALUES ('6009', '410102', '中原区', '410102', '河南省郑州市中原区', 'hnszzszyq');
INSERT INTO `t_system_region` VALUES ('6010', '410103', '二七区', '410103', '河南省郑州市二七区', 'hnszzseqq');
INSERT INTO `t_system_region` VALUES ('6011', '410104', '管城回族区', '410104', '河南省郑州市管城回族区', 'hnszzsgchzq');
INSERT INTO `t_system_region` VALUES ('6012', '410105', '金水区', '410105', '河南省郑州市金水区', 'hnszzsjsq');
INSERT INTO `t_system_region` VALUES ('6013', '410106', '上街区', '410106', '河南省郑州市上街区', 'hnszzssjq');
INSERT INTO `t_system_region` VALUES ('6014', '410108', '惠济区', '410108', '河南省郑州市惠济区', 'hnszzshjq');
INSERT INTO `t_system_region` VALUES ('6015', '410109', '郑州市高新技术开发区', '410109', '河南省郑州市郑州市高新技术开发区', 'hnszzszzsgxjskfq');
INSERT INTO `t_system_region` VALUES ('6016', '410122', '中牟县', '410122', '河南省郑州市中牟县', 'hnszzszmx');
INSERT INTO `t_system_region` VALUES ('6017', '410181', '巩义市', '410181', '河南省郑州市巩义市', 'hnszzsgys');
INSERT INTO `t_system_region` VALUES ('6018', '410182', '荥阳市', '410182', '河南省郑州市荥阳市', 'hnszzsyys');
INSERT INTO `t_system_region` VALUES ('6019', '410183', '新密市', '410183', '河南省郑州市新密市', 'hnszzsxms');
INSERT INTO `t_system_region` VALUES ('6020', '410184', '新郑市', '410184', '河南省郑州市新郑市', 'hnszzsxzs');
INSERT INTO `t_system_region` VALUES ('6021', '410185', '登封市', '410185', '河南省郑州市登封市', 'hnszzsdfs');
INSERT INTO `t_system_region` VALUES ('6022', '4102', '开封市', '410200', '河南省开封市', 'hnskfs');
INSERT INTO `t_system_region` VALUES ('6023', '410201', '市辖区', '410201', '河南省开封市市辖区', 'hnskfssxq');
INSERT INTO `t_system_region` VALUES ('6024', '410202', '龙亭区', '410202', '河南省开封市龙亭区', 'hnskfsltq');
INSERT INTO `t_system_region` VALUES ('6025', '410203', '顺河回族区', '410203', '河南省开封市顺河回族区', 'hnskfsshhzq');
INSERT INTO `t_system_region` VALUES ('6026', '410204', '鼓楼区', '410204', '河南省开封市鼓楼区', 'hnskfsglq');
INSERT INTO `t_system_region` VALUES ('6027', '410205', '禹王台区', '410205', '河南省开封市禹王台区', 'hnskfsywtq');
INSERT INTO `t_system_region` VALUES ('6028', '410211', '金明区', '410211', '河南省开封市金明区', 'hnskfsjmq');
INSERT INTO `t_system_region` VALUES ('6029', '410212', '开封市开发区', '410212', '河南省开封市开封市开发区', 'hnskfskfskfq');
INSERT INTO `t_system_region` VALUES ('6030', '410221', '杞县', '410221', '河南省开封市杞县', 'hnskfsqx');
INSERT INTO `t_system_region` VALUES ('6031', '410222', '通许县', '410222', '河南省开封市通许县', 'hnskfstxx');
INSERT INTO `t_system_region` VALUES ('6032', '410223', '尉氏县', '410223', '河南省开封市尉氏县', 'hnskfswsx');
INSERT INTO `t_system_region` VALUES ('6033', '410224', '开封县', '410224', '河南省开封市开封县', 'hnskfskfx');
INSERT INTO `t_system_region` VALUES ('6034', '410225', '兰考县', '410225', '河南省开封市兰考县', 'hnskfslkx');
INSERT INTO `t_system_region` VALUES ('6035', '4103', '洛阳市', '410300', '河南省洛阳市', 'hnslys');
INSERT INTO `t_system_region` VALUES ('6036', '410301', '市辖区', '410301', '河南省洛阳市市辖区', 'hnslyssxq');
INSERT INTO `t_system_region` VALUES ('6037', '410302', '老城区', '410302', '河南省洛阳市老城区', 'hnslyslcq');
INSERT INTO `t_system_region` VALUES ('6038', '410303', '西工区', '410303', '河南省洛阳市西工区', 'hnslysxgq');
INSERT INTO `t_system_region` VALUES ('6039', '410304', '廛河回族区', '410304', '河南省洛阳市廛河回族区', 'hnslyschhzq');
INSERT INTO `t_system_region` VALUES ('6040', '410305', '涧西区', '410305', '河南省洛阳市涧西区', 'hnslysjxq');
INSERT INTO `t_system_region` VALUES ('6041', '410306', '吉利区', '410306', '河南省洛阳市吉利区', 'hnslysjlq');
INSERT INTO `t_system_region` VALUES ('6042', '410311', '洛龙区', '410311', '河南省洛阳市洛龙区', 'hnslysllq');
INSERT INTO `t_system_region` VALUES ('6043', '410322', '孟津县', '410322', '河南省洛阳市孟津县', 'hnslysmjx');
INSERT INTO `t_system_region` VALUES ('6044', '410323', '新安县', '410323', '河南省洛阳市新安县', 'hnslysxax');
INSERT INTO `t_system_region` VALUES ('6045', '410324', '栾川县', '410324', '河南省洛阳市栾川县', 'hnslyslcx');
INSERT INTO `t_system_region` VALUES ('6046', '410325', '嵩县', '410325', '河南省洛阳市嵩县', 'hnslyssx');
INSERT INTO `t_system_region` VALUES ('6047', '410326', '汝阳县', '410326', '河南省洛阳市汝阳县', 'hnslysryx');
INSERT INTO `t_system_region` VALUES ('6048', '410327', '宜阳县', '410327', '河南省洛阳市宜阳县', 'hnslysyyx');
INSERT INTO `t_system_region` VALUES ('6049', '410328', '洛宁县', '410328', '河南省洛阳市洛宁县', 'hnslyslnx');
INSERT INTO `t_system_region` VALUES ('6050', '410329', '伊川县', '410329', '河南省洛阳市伊川县', 'hnslysycx');
INSERT INTO `t_system_region` VALUES ('6051', '410331', '高新技术产业开发区', '410331', '河南省洛阳市高新技术产业开发区', 'hnslysgxjscykfq');
INSERT INTO `t_system_region` VALUES ('6052', '410332', '龙门文化旅游园区', '410332', '河南省洛阳市龙门文化旅游园区', 'hnslyslmwhlyyq');
INSERT INTO `t_system_region` VALUES ('6053', '410333', '伊洛工业园区', '410333', '河南省洛阳市伊洛工业园区', 'hnslysylgyyq');
INSERT INTO `t_system_region` VALUES ('6054', '410381', '偃师市', '410381', '河南省洛阳市偃师市', 'hnslysyss');
INSERT INTO `t_system_region` VALUES ('6055', '4104', '平顶山市', '410400', '河南省平顶山市', 'hnspdss');
INSERT INTO `t_system_region` VALUES ('6056', '410401', '市辖区', '410401', '河南省平顶山市市辖区', 'hnspdsssxq');
INSERT INTO `t_system_region` VALUES ('6057', '410402', '新华区', '410402', '河南省平顶山市新华区', 'hnspdssxhq');
INSERT INTO `t_system_region` VALUES ('6058', '410403', '卫东区', '410403', '河南省平顶山市卫东区', 'hnspdsswdq');
INSERT INTO `t_system_region` VALUES ('6059', '410404', '石龙区', '410404', '河南省平顶山市石龙区', 'hnspdssslq');
INSERT INTO `t_system_region` VALUES ('6060', '410411', '湛河区', '410411', '河南省平顶山市湛河区', 'hnspdsszhq');
INSERT INTO `t_system_region` VALUES ('6061', '410421', '宝丰县', '410421', '河南省平顶山市宝丰县', 'hnspdssbfx');
INSERT INTO `t_system_region` VALUES ('6062', '410422', '叶县', '410422', '河南省平顶山市叶县', 'hnspdssyx');
INSERT INTO `t_system_region` VALUES ('6063', '410423', '鲁山县', '410423', '河南省平顶山市鲁山县', 'hnspdsslsx');
INSERT INTO `t_system_region` VALUES ('6064', '410425', '郏县', '410425', '河南省平顶山市郏县', 'hnspdssjx');
INSERT INTO `t_system_region` VALUES ('6065', '410481', '舞钢市', '410481', '河南省平顶山市舞钢市', 'hnspdsswgs');
INSERT INTO `t_system_region` VALUES ('6066', '410482', '汝州市', '410482', '河南省平顶山市汝州市', 'hnspdssrzs');
INSERT INTO `t_system_region` VALUES ('6067', '4105', '安阳市', '410500', '河南省安阳市', 'hnsays');
INSERT INTO `t_system_region` VALUES ('6068', '410501', '市辖区', '410501', '河南省安阳市市辖区', 'hnsayssxq');
INSERT INTO `t_system_region` VALUES ('6069', '410502', '文峰区', '410502', '河南省安阳市文峰区', 'hnsayswfq');
INSERT INTO `t_system_region` VALUES ('6070', '410503', '北关区', '410503', '河南省安阳市北关区', 'hnsaysbgq');
INSERT INTO `t_system_region` VALUES ('6071', '410505', '殷都区', '410505', '河南省安阳市殷都区', 'hnsaysydq');
INSERT INTO `t_system_region` VALUES ('6072', '410506', '龙安区', '410506', '河南省安阳市龙安区', 'hnsayslaq');
INSERT INTO `t_system_region` VALUES ('6073', '410522', '安阳县', '410522', '河南省安阳市安阳县', 'hnsaysayx');
INSERT INTO `t_system_region` VALUES ('6074', '410523', '汤阴县', '410523', '河南省安阳市汤阴县', 'hnsaystyx');
INSERT INTO `t_system_region` VALUES ('6075', '410526', '滑县', '410526', '河南省安阳市滑县', 'hnsayshx');
INSERT INTO `t_system_region` VALUES ('6076', '410527', '内黄县', '410527', '河南省安阳市内黄县', 'hnsaysnhx');
INSERT INTO `t_system_region` VALUES ('6077', '410581', '林州市', '410581', '河南省安阳市林州市', 'hnsayslzs');
INSERT INTO `t_system_region` VALUES ('6078', '4106', '鹤壁市', '410600', '河南省鹤壁市', 'hnshbs');
INSERT INTO `t_system_region` VALUES ('6079', '410601', '市辖区', '410601', '河南省鹤壁市市辖区', 'hnshbssxq');
INSERT INTO `t_system_region` VALUES ('6080', '410602', '鹤山区', '410602', '河南省鹤壁市鹤山区', 'hnshbshsq');
INSERT INTO `t_system_region` VALUES ('6081', '410603', '山城区', '410603', '河南省鹤壁市山城区', 'hnshbsscq');
INSERT INTO `t_system_region` VALUES ('6082', '410611', '淇滨区', '410611', '河南省鹤壁市淇滨区', 'hnshbsqbq');
INSERT INTO `t_system_region` VALUES ('6083', '410621', '浚县', '410621', '河南省鹤壁市浚县', 'hnshbsjx');
INSERT INTO `t_system_region` VALUES ('6084', '410622', '淇县', '410622', '河南省鹤壁市淇县', 'hnshbsqx');
INSERT INTO `t_system_region` VALUES ('6085', '4107', '新乡市', '410700', '河南省新乡市', 'hnsxxs');
INSERT INTO `t_system_region` VALUES ('6086', '410701', '市辖区', '410701', '河南省新乡市市辖区', 'hnsxxssxq');
INSERT INTO `t_system_region` VALUES ('6087', '410702', '红旗区', '410702', '河南省新乡市红旗区', 'hnsxxshqq');
INSERT INTO `t_system_region` VALUES ('6088', '410703', '卫滨区', '410703', '河南省新乡市卫滨区', 'hnsxxswbq');
INSERT INTO `t_system_region` VALUES ('6089', '410704', '凤泉区', '410704', '河南省新乡市凤泉区', 'hnsxxsfqq');
INSERT INTO `t_system_region` VALUES ('6090', '410711', '牧野区', '410711', '河南省新乡市牧野区', 'hnsxxsmyq');
INSERT INTO `t_system_region` VALUES ('6091', '410721', '新乡县', '410721', '河南省新乡市新乡县', 'hnsxxsxxx');
INSERT INTO `t_system_region` VALUES ('6092', '410724', '获嘉县', '410724', '河南省新乡市获嘉县', 'hnsxxshjx');
INSERT INTO `t_system_region` VALUES ('6093', '410725', '原阳县', '410725', '河南省新乡市原阳县', 'hnsxxsyyx');
INSERT INTO `t_system_region` VALUES ('6094', '410726', '延津县', '410726', '河南省新乡市延津县', 'hnsxxsyjx');
INSERT INTO `t_system_region` VALUES ('6095', '410727', '封丘县', '410727', '河南省新乡市封丘县', 'hnsxxsfqx');
INSERT INTO `t_system_region` VALUES ('6096', '410728', '长垣县', '410728', '河南省新乡市长垣县', 'hnsxxszyx');
INSERT INTO `t_system_region` VALUES ('6097', '410781', '卫辉市', '410781', '河南省新乡市卫辉市', 'hnsxxswhs');
INSERT INTO `t_system_region` VALUES ('6098', '410782', '辉县市', '410782', '河南省新乡市辉县市', 'hnsxxshxs');
INSERT INTO `t_system_region` VALUES ('6099', '4108', '焦作市', '410800', '河南省焦作市', 'hnsjzs');
INSERT INTO `t_system_region` VALUES ('6100', '410801', '市辖区', '410801', '河南省焦作市市辖区', 'hnsjzssxq');
INSERT INTO `t_system_region` VALUES ('6101', '410802', '解放区', '410802', '河南省焦作市解放区', 'hnsjzsjfq');
INSERT INTO `t_system_region` VALUES ('6102', '410803', '中站区', '410803', '河南省焦作市中站区', 'hnsjzszzq');
INSERT INTO `t_system_region` VALUES ('6103', '410804', '马村区', '410804', '河南省焦作市马村区', 'hnsjzsmcq');
INSERT INTO `t_system_region` VALUES ('6104', '410806', '焦作市高新区', '410806', '河南省焦作市焦作市高新区', 'hnsjzsjzsgxq');
INSERT INTO `t_system_region` VALUES ('6105', '410811', '山阳区', '410811', '河南省焦作市山阳区', 'hnsjzssyq');
INSERT INTO `t_system_region` VALUES ('6106', '410821', '修武县', '410821', '河南省焦作市修武县', 'hnsjzsxwx');
INSERT INTO `t_system_region` VALUES ('6107', '410822', '博爱县', '410822', '河南省焦作市博爱县', 'hnsjzsbax');
INSERT INTO `t_system_region` VALUES ('6108', '410823', '武陟县', '410823', '河南省焦作市武陟县', 'hnsjzswzx');
INSERT INTO `t_system_region` VALUES ('6109', '410825', '温县', '410825', '河南省焦作市温县', 'hnsjzswx');
INSERT INTO `t_system_region` VALUES ('6110', '410881', '济源市', '410881', '河南省焦作市济源市', 'hnsjzsjys');
INSERT INTO `t_system_region` VALUES ('6111', '410882', '沁阳市', '410882', '河南省焦作市沁阳市', 'hnsjzsqys');
INSERT INTO `t_system_region` VALUES ('6112', '410883', '孟州市', '410883', '河南省焦作市孟州市', 'hnsjzsmzs');
INSERT INTO `t_system_region` VALUES ('6113', '4109', '濮阳市', '410900', '河南省濮阳市', 'hnspys');
INSERT INTO `t_system_region` VALUES ('6114', '410901', '市辖区', '410901', '河南省濮阳市市辖区', 'hnspyssxq');
INSERT INTO `t_system_region` VALUES ('6115', '410902', '华龙区', '410902', '河南省濮阳市华龙区', 'hnspyshlq');
INSERT INTO `t_system_region` VALUES ('6116', '410903', '濮阳市高新区', '410903', '河南省濮阳市濮阳市高新区', 'hnspyspysgxq');
INSERT INTO `t_system_region` VALUES ('6117', '410922', '清丰县', '410922', '河南省濮阳市清丰县', 'hnspysqfx');
INSERT INTO `t_system_region` VALUES ('6118', '410923', '南乐县', '410923', '河南省濮阳市南乐县', 'hnspysnlx');
INSERT INTO `t_system_region` VALUES ('6119', '410926', '范县', '410926', '河南省濮阳市范县', 'hnspysfx');
INSERT INTO `t_system_region` VALUES ('6120', '410927', '台前县', '410927', '河南省濮阳市台前县', 'hnspystqx');
INSERT INTO `t_system_region` VALUES ('6121', '410928', '濮阳县', '410928', '河南省濮阳市濮阳县', 'hnspyspyx');
INSERT INTO `t_system_region` VALUES ('6122', '4110', '许昌市', '411000', '河南省许昌市', 'hnsxcs');
INSERT INTO `t_system_region` VALUES ('6123', '411001', '市辖区', '411001', '河南省许昌市市辖区', 'hnsxcssxq');
INSERT INTO `t_system_region` VALUES ('6124', '411002', '魏都区', '411002', '河南省许昌市魏都区', 'hnsxcswdq');
INSERT INTO `t_system_region` VALUES ('6125', '411023', '许昌县', '411023', '河南省许昌市许昌县', 'hnsxcsxcx');
INSERT INTO `t_system_region` VALUES ('6126', '411024', '鄢陵县', '411024', '河南省许昌市鄢陵县', 'hnsxcsylx');
INSERT INTO `t_system_region` VALUES ('6127', '411025', '襄城县', '411025', '河南省许昌市襄城县', 'hnsxcsxcx');
INSERT INTO `t_system_region` VALUES ('6128', '411081', '禹州市', '411081', '河南省许昌市禹州市', 'hnsxcsyzs');
INSERT INTO `t_system_region` VALUES ('6129', '411082', '长葛市', '411082', '河南省许昌市长葛市', 'hnsxcszgs');
INSERT INTO `t_system_region` VALUES ('6130', '4111', '漯河市', '411100', '河南省漯河市', 'hnslhs');
INSERT INTO `t_system_region` VALUES ('6131', '411101', '市辖区', '411101', '河南省漯河市市辖区', 'hnslhssxq');
INSERT INTO `t_system_region` VALUES ('6132', '411102', '源汇区', '411102', '河南省漯河市源汇区', 'hnslhsyhq');
INSERT INTO `t_system_region` VALUES ('6133', '411103', '郾城区', '411103', '河南省漯河市郾城区', 'hnslhsycq');
INSERT INTO `t_system_region` VALUES ('6134', '411104', '召陵区', '411104', '河南省漯河市召陵区', 'hnslhszlq');
INSERT INTO `t_system_region` VALUES ('6135', '411121', '舞阳县', '411121', '河南省漯河市舞阳县', 'hnslhswyx');
INSERT INTO `t_system_region` VALUES ('6136', '411122', '临颍县', '411122', '河南省漯河市临颍县', 'hnslhslyx');
INSERT INTO `t_system_region` VALUES ('6137', '4112', '三门峡市', '411200', '河南省三门峡市', 'hnssmxs');
INSERT INTO `t_system_region` VALUES ('6138', '411201', '市辖区', '411201', '河南省三门峡市市辖区', 'hnssmxssxq');
INSERT INTO `t_system_region` VALUES ('6139', '411202', '湖滨区', '411202', '河南省三门峡市湖滨区', 'hnssmxshbq');
INSERT INTO `t_system_region` VALUES ('6140', '411221', '渑池县', '411221', '河南省三门峡市渑池县', 'hnssmxsmcx');
INSERT INTO `t_system_region` VALUES ('6141', '411222', '陕县', '411222', '河南省三门峡市陕县', 'hnssmxssx');
INSERT INTO `t_system_region` VALUES ('6142', '411224', '卢氏县', '411224', '河南省三门峡市卢氏县', 'hnssmxslsx');
INSERT INTO `t_system_region` VALUES ('6143', '411281', '义马市', '411281', '河南省三门峡市义马市', 'hnssmxsyms');
INSERT INTO `t_system_region` VALUES ('6144', '411282', '灵宝市', '411282', '河南省三门峡市灵宝市', 'hnssmxslbs');
INSERT INTO `t_system_region` VALUES ('6145', '4113', '南阳市', '411300', '河南省南阳市', 'hnsnys');
INSERT INTO `t_system_region` VALUES ('6146', '411301', '市辖区', '411301', '河南省南阳市市辖区', 'hnsnyssxq');
INSERT INTO `t_system_region` VALUES ('6147', '411302', '宛城区', '411302', '河南省南阳市宛城区', 'hnsnyswcq');
INSERT INTO `t_system_region` VALUES ('6148', '411303', '卧龙区', '411303', '河南省南阳市卧龙区', 'hnsnyswlq');
INSERT INTO `t_system_region` VALUES ('6149', '411321', '南召县', '411321', '河南省南阳市南召县', 'hnsnysnzx');
INSERT INTO `t_system_region` VALUES ('6150', '411322', '方城县', '411322', '河南省南阳市方城县', 'hnsnysfcx');
INSERT INTO `t_system_region` VALUES ('6151', '411323', '西峡县', '411323', '河南省南阳市西峡县', 'hnsnysxxx');
INSERT INTO `t_system_region` VALUES ('6152', '411324', '镇平县', '411324', '河南省南阳市镇平县', 'hnsnyszpx');
INSERT INTO `t_system_region` VALUES ('6153', '411325', '内乡县', '411325', '河南省南阳市内乡县', 'hnsnysnxx');
INSERT INTO `t_system_region` VALUES ('6154', '411326', '淅川县', '411326', '河南省南阳市淅川县', 'hnsnysxcx');
INSERT INTO `t_system_region` VALUES ('6155', '411327', '社旗县', '411327', '河南省南阳市社旗县', 'hnsnyssqx');
INSERT INTO `t_system_region` VALUES ('6156', '411328', '唐河县', '411328', '河南省南阳市唐河县', 'hnsnysthx');
INSERT INTO `t_system_region` VALUES ('6157', '411329', '新野县', '411329', '河南省南阳市新野县', 'hnsnysxyx');
INSERT INTO `t_system_region` VALUES ('6158', '411330', '桐柏县', '411330', '河南省南阳市桐柏县', 'hnsnystbx');
INSERT INTO `t_system_region` VALUES ('6159', '411381', '邓州市', '411381', '河南省南阳市邓州市', 'hnsnysdzs');
INSERT INTO `t_system_region` VALUES ('6160', '4114', '商丘市', '411400', '河南省商丘市', 'hnssqs');
INSERT INTO `t_system_region` VALUES ('6161', '411401', '市辖区', '411401', '河南省商丘市市辖区', 'hnssqssxq');
INSERT INTO `t_system_region` VALUES ('6162', '411402', '梁园区', '411402', '河南省商丘市梁园区', 'hnssqslyq');
INSERT INTO `t_system_region` VALUES ('6163', '411403', '睢阳区', '411403', '河南省商丘市睢阳区', 'hnssqssyq');
INSERT INTO `t_system_region` VALUES ('6164', '411404', '经济技术开发区', '411404', '河南省商丘市经济技术开发区', 'hnssqsjjjskfq');
INSERT INTO `t_system_region` VALUES ('6165', '411421', '民权县', '411421', '河南省商丘市民权县', 'hnssqsmqx');
INSERT INTO `t_system_region` VALUES ('6166', '411422', '睢县', '411422', '河南省商丘市睢县', 'hnssqssx');
INSERT INTO `t_system_region` VALUES ('6167', '411423', '宁陵县', '411423', '河南省商丘市宁陵县', 'hnssqsnlx');
INSERT INTO `t_system_region` VALUES ('6168', '411424', '柘城县', '411424', '河南省商丘市柘城县', 'hnssqszcx');
INSERT INTO `t_system_region` VALUES ('6169', '411425', '虞城县', '411425', '河南省商丘市虞城县', 'hnssqsycx');
INSERT INTO `t_system_region` VALUES ('6170', '411426', '夏邑县', '411426', '河南省商丘市夏邑县', 'hnssqsxyx');
INSERT INTO `t_system_region` VALUES ('6171', '411481', '永城市', '411481', '河南省商丘市永城市', 'hnssqsycs');
INSERT INTO `t_system_region` VALUES ('6172', '4115', '信阳市', '411500', '河南省信阳市', 'hnsxys');
INSERT INTO `t_system_region` VALUES ('6173', '411501', '市辖区', '411501', '河南省信阳市市辖区', 'hnsxyssxq');
INSERT INTO `t_system_region` VALUES ('6174', '411502', '浉河区', '411502', '河南省信阳市浉河区', 'hnsxysshq');
INSERT INTO `t_system_region` VALUES ('6175', '411503', '平桥区', '411503', '河南省信阳市平桥区', 'hnsxyspqq');
INSERT INTO `t_system_region` VALUES ('6176', '411511', '南湾管理区', '411511', '河南省信阳市南湾管理区', 'hnsxysnwglq');
INSERT INTO `t_system_region` VALUES ('6177', '411512', '鸡公山管理区', '411512', '河南省信阳市鸡公山管理区', 'hnsxysjgsglq');
INSERT INTO `t_system_region` VALUES ('6178', '411513', '信阳市工业城', '411513', '河南省信阳市信阳市工业城', 'hnsxysxysgyc');
INSERT INTO `t_system_region` VALUES ('6179', '411514', '上天梯管理区', '411514', '河南省信阳市上天梯管理区', 'hnsxyssttglq');
INSERT INTO `t_system_region` VALUES ('6180', '411515', '羊山新区', '411515', '河南省信阳市羊山新区', 'hnsxysysxq');
INSERT INTO `t_system_region` VALUES ('6181', '411521', '罗山县', '411521', '河南省信阳市罗山县', 'hnsxyslsx');
INSERT INTO `t_system_region` VALUES ('6182', '411522', '光山县', '411522', '河南省信阳市光山县', 'hnsxysgsx');
INSERT INTO `t_system_region` VALUES ('6183', '411523', '新县', '411523', '河南省信阳市新县', 'hnsxysxx');
INSERT INTO `t_system_region` VALUES ('6184', '411524', '商城县', '411524', '河南省信阳市商城县', 'hnsxysscx');
INSERT INTO `t_system_region` VALUES ('6185', '411525', '固始县', '411525', '河南省信阳市固始县', 'hnsxysgsx');
INSERT INTO `t_system_region` VALUES ('6186', '411526', '潢川县', '411526', '河南省信阳市潢川县', 'hnsxyshcx');
INSERT INTO `t_system_region` VALUES ('6187', '411527', '淮滨县', '411527', '河南省信阳市淮滨县', 'hnsxyshbx');
INSERT INTO `t_system_region` VALUES ('6188', '411528', '息县', '411528', '河南省信阳市息县', 'hnsxysxx');
INSERT INTO `t_system_region` VALUES ('6189', '4116', '周口市', '411600', '河南省周口市', 'hnszks');
INSERT INTO `t_system_region` VALUES ('6190', '411601', '市辖区', '411601', '河南省周口市市辖区', 'hnszkssxq');
INSERT INTO `t_system_region` VALUES ('6191', '411602', '川汇区', '411602', '河南省周口市川汇区', 'hnszkschq');
INSERT INTO `t_system_region` VALUES ('6192', '411621', '扶沟县', '411621', '河南省周口市扶沟县', 'hnszksfgx');
INSERT INTO `t_system_region` VALUES ('6193', '411622', '西华县', '411622', '河南省周口市西华县', 'hnszksxhx');
INSERT INTO `t_system_region` VALUES ('6194', '411623', '商水县', '411623', '河南省周口市商水县', 'hnszksssx');
INSERT INTO `t_system_region` VALUES ('6195', '411624', '沈丘县', '411624', '河南省周口市沈丘县', 'hnszkssqx');
INSERT INTO `t_system_region` VALUES ('6196', '411625', '郸城县', '411625', '河南省周口市郸城县', 'hnszksdcx');
INSERT INTO `t_system_region` VALUES ('6197', '411626', '淮阳县', '411626', '河南省周口市淮阳县', 'hnszkshyx');
INSERT INTO `t_system_region` VALUES ('6198', '411627', '太康县', '411627', '河南省周口市太康县', 'hnszkstkx');
INSERT INTO `t_system_region` VALUES ('6199', '411628', '鹿邑县', '411628', '河南省周口市鹿邑县', 'hnszkslyx');
INSERT INTO `t_system_region` VALUES ('6200', '411681', '项城市', '411681', '河南省周口市项城市', 'hnszksxcs');
INSERT INTO `t_system_region` VALUES ('6201', '4117', '驻马店市', '411700', '河南省驻马店市', 'hnszmds');
INSERT INTO `t_system_region` VALUES ('6202', '411701', '市辖区', '411701', '河南省驻马店市市辖区', 'hnszmdssxq');
INSERT INTO `t_system_region` VALUES ('6203', '411702', '驿城区', '411702', '河南省驻马店市驿城区', 'hnszmdsycq');
INSERT INTO `t_system_region` VALUES ('6204', '411703', '开发区', '411703', '河南省驻马店市开发区', 'hnszmdskfq');
INSERT INTO `t_system_region` VALUES ('6205', '411721', '西平县', '411721', '河南省驻马店市西平县', 'hnszmdsxpx');
INSERT INTO `t_system_region` VALUES ('6206', '411722', '上蔡县', '411722', '河南省驻马店市上蔡县', 'hnszmdsscx');
INSERT INTO `t_system_region` VALUES ('6207', '411723', '平舆县', '411723', '河南省驻马店市平舆县', 'hnszmdspyx');
INSERT INTO `t_system_region` VALUES ('6208', '411724', '正阳县', '411724', '河南省驻马店市正阳县', 'hnszmdszyx');
INSERT INTO `t_system_region` VALUES ('6209', '411725', '确山县', '411725', '河南省驻马店市确山县', 'hnszmdsqsx');
INSERT INTO `t_system_region` VALUES ('6210', '411726', '泌阳县', '411726', '河南省驻马店市泌阳县', 'hnszmdsmyx');
INSERT INTO `t_system_region` VALUES ('6211', '411727', '汝南县', '411727', '河南省驻马店市汝南县', 'hnszmdsrnx');
INSERT INTO `t_system_region` VALUES ('6212', '411728', '遂平县', '411728', '河南省驻马店市遂平县', 'hnszmdsspx');
INSERT INTO `t_system_region` VALUES ('6213', '411729', '新蔡县', '411729', '河南省驻马店市新蔡县', 'hnszmdsxcx');
INSERT INTO `t_system_region` VALUES ('6214', '4190', '省直辖县级行政区划(*)', '419000', '河南省省直辖县级行政区划(*)', 'hnsszxxjxzqh(*)');
INSERT INTO `t_system_region` VALUES ('6215', '419001', '济源市(*)', '419001', '河南省省直辖县级行政区划(*)济源市(*)', 'hnsszxxjxzqh(*)jys(*)');
INSERT INTO `t_system_region` VALUES ('6216', '42', '湖北省', '420000', '湖北省', 'hbs');
INSERT INTO `t_system_region` VALUES ('6217', '4201', '武汉市', '420100', '湖北省武汉市', 'hbswhs');
INSERT INTO `t_system_region` VALUES ('6218', '420101', '市辖区', '420101', '湖北省武汉市市辖区', 'hbswhssxq');
INSERT INTO `t_system_region` VALUES ('6219', '420102', '江岸区', '420102', '湖北省武汉市江岸区', 'hbswhsjaq');
INSERT INTO `t_system_region` VALUES ('6220', '420103', '江汉区', '420103', '湖北省武汉市江汉区', 'hbswhsjhq');
INSERT INTO `t_system_region` VALUES ('6221', '420104', '硚口区', '420104', '湖北省武汉市硚口区', 'hbswhsqkq');
INSERT INTO `t_system_region` VALUES ('6222', '420105', '汉阳区', '420105', '湖北省武汉市汉阳区', 'hbswhshyq');
INSERT INTO `t_system_region` VALUES ('6223', '420106', '武昌区', '420106', '湖北省武汉市武昌区', 'hbswhswcq');
INSERT INTO `t_system_region` VALUES ('6224', '420107', '青山区', '420107', '湖北省武汉市青山区', 'hbswhsqsq');
INSERT INTO `t_system_region` VALUES ('6225', '420108', '武汉市经济技术开发区', '420108', '湖北省武汉市武汉市经济技术开发区', 'hbswhswhsjjjskfq');
INSERT INTO `t_system_region` VALUES ('6226', '420109', '武汉市东湖新技术开发区', '420109', '湖北省武汉市武汉市东湖新技术开发区', 'hbswhswhsdhxjskfq');
INSERT INTO `t_system_region` VALUES ('6227', '420111', '洪山区', '420111', '湖北省武汉市洪山区', 'hbswhshsq');
INSERT INTO `t_system_region` VALUES ('6228', '420112', '东西湖区', '420112', '湖北省武汉市东西湖区', 'hbswhsdxhq');
INSERT INTO `t_system_region` VALUES ('6229', '420113', '汉南区', '420113', '湖北省武汉市汉南区', 'hbswhshnq');
INSERT INTO `t_system_region` VALUES ('6230', '420114', '蔡甸区', '420114', '湖北省武汉市蔡甸区', 'hbswhscdq');
INSERT INTO `t_system_region` VALUES ('6231', '420115', '江夏区', '420115', '湖北省武汉市江夏区', 'hbswhsjxq');
INSERT INTO `t_system_region` VALUES ('6232', '420116', '黄陂区', '420116', '湖北省武汉市黄陂区', 'hbswhshpq');
INSERT INTO `t_system_region` VALUES ('6233', '420117', '新洲区', '420117', '湖北省武汉市新洲区', 'hbswhsxzq');
INSERT INTO `t_system_region` VALUES ('6234', '4202', '黄石市', '420200', '湖北省黄石市', 'hbshss');
INSERT INTO `t_system_region` VALUES ('6235', '420201', '市辖区', '420201', '湖北省黄石市市辖区', 'hbshsssxq');
INSERT INTO `t_system_region` VALUES ('6236', '420202', '黄石港区', '420202', '湖北省黄石市黄石港区', 'hbshsshsgq');
INSERT INTO `t_system_region` VALUES ('6237', '420203', '西塞山区', '420203', '湖北省黄石市西塞山区', 'hbshssxssq');
INSERT INTO `t_system_region` VALUES ('6238', '420204', '下陆区', '420204', '湖北省黄石市下陆区', 'hbshssxlq');
INSERT INTO `t_system_region` VALUES ('6239', '420205', '铁山区', '420205', '湖北省黄石市铁山区', 'hbshsstsq');
INSERT INTO `t_system_region` VALUES ('6240', '420206', '开发区', '420206', '湖北省黄石市开发区', 'hbshsskfq');
INSERT INTO `t_system_region` VALUES ('6241', '420222', '阳新县', '420222', '湖北省黄石市阳新县', 'hbshssyxx');
INSERT INTO `t_system_region` VALUES ('6242', '420281', '大冶市', '420281', '湖北省黄石市大冶市', 'hbshssdys');
INSERT INTO `t_system_region` VALUES ('6243', '4203', '十堰市', '420300', '湖北省十堰市', 'hbssys');
INSERT INTO `t_system_region` VALUES ('6244', '420301', '市辖区', '420301', '湖北省十堰市市辖区', 'hbssyssxq');
INSERT INTO `t_system_region` VALUES ('6245', '420302', '茅箭区', '420302', '湖北省十堰市茅箭区', 'hbssysmjq');
INSERT INTO `t_system_region` VALUES ('6246', '420303', '张湾区', '420303', '湖北省十堰市张湾区', 'hbssyszwq');
INSERT INTO `t_system_region` VALUES ('6247', '420304', '十堰经济开发区', '420304', '湖北省十堰市十堰经济开发区', 'hbssyssyjjkfq');
INSERT INTO `t_system_region` VALUES ('6248', '420305', '武当山', '420305', '湖北省十堰市武当山', 'hbssyswds');
INSERT INTO `t_system_region` VALUES ('6249', '420306', '东风区', '420306', '湖北省十堰市东风区', 'hbssysdfq');
INSERT INTO `t_system_region` VALUES ('6250', '420321', '郧县', '420321', '湖北省十堰市郧县', 'hbssysyx');
INSERT INTO `t_system_region` VALUES ('6251', '420322', '郧西县', '420322', '湖北省十堰市郧西县', 'hbssysyxx');
INSERT INTO `t_system_region` VALUES ('6252', '420323', '竹山县', '420323', '湖北省十堰市竹山县', 'hbssyszsx');
INSERT INTO `t_system_region` VALUES ('6253', '420324', '竹溪县', '420324', '湖北省十堰市竹溪县', 'hbssyszxx');
INSERT INTO `t_system_region` VALUES ('6254', '420325', '房县', '420325', '湖北省十堰市房县', 'hbssysfx');
INSERT INTO `t_system_region` VALUES ('6255', '420381', '丹江口市', '420381', '湖北省十堰市丹江口市', 'hbssysdjks');
INSERT INTO `t_system_region` VALUES ('6256', '4205', '宜昌市', '420500', '湖北省宜昌市', 'hbsycs');
INSERT INTO `t_system_region` VALUES ('6257', '420501', '市辖区', '420501', '湖北省宜昌市市辖区', 'hbsycssxq');
INSERT INTO `t_system_region` VALUES ('6258', '420502', '西陵区', '420502', '湖北省宜昌市西陵区', 'hbsycsxlq');
INSERT INTO `t_system_region` VALUES ('6259', '420503', '伍家岗区', '420503', '湖北省宜昌市伍家岗区', 'hbsycswjgq');
INSERT INTO `t_system_region` VALUES ('6260', '420504', '点军区', '420504', '湖北省宜昌市点军区', 'hbsycsdjq');
INSERT INTO `t_system_region` VALUES ('6261', '420505', '猇亭区', '420505', '湖北省宜昌市猇亭区', 'hbsycsytq');
INSERT INTO `t_system_region` VALUES ('6262', '420506', '夷陵区', '420506', '湖北省宜昌市夷陵区', 'hbsycsylq');
INSERT INTO `t_system_region` VALUES ('6263', '420525', '远安县', '420525', '湖北省宜昌市远安县', 'hbsycsyax');
INSERT INTO `t_system_region` VALUES ('6264', '420526', '兴山县', '420526', '湖北省宜昌市兴山县', 'hbsycsxsx');
INSERT INTO `t_system_region` VALUES ('6265', '420527', '秭归县', '420527', '湖北省宜昌市秭归县', 'hbsycszgx');
INSERT INTO `t_system_region` VALUES ('6266', '420528', '长阳土家族自治县', '420528', '湖北省宜昌市长阳土家族自治县', 'hbsycszytjzzzx');
INSERT INTO `t_system_region` VALUES ('6267', '420529', '五峰土家族自治县', '420529', '湖北省宜昌市五峰土家族自治县', 'hbsycswftjzzzx');
INSERT INTO `t_system_region` VALUES ('6268', '420581', '宜都市', '420581', '湖北省宜昌市宜都市', 'hbsycsyds');
INSERT INTO `t_system_region` VALUES ('6269', '420582', '当阳市', '420582', '湖北省宜昌市当阳市', 'hbsycsdys');
INSERT INTO `t_system_region` VALUES ('6270', '420583', '枝江市', '420583', '湖北省宜昌市枝江市', 'hbsycszjs');
INSERT INTO `t_system_region` VALUES ('6271', '420584', '开发区', '420584', '湖北省宜昌市开发区', 'hbsycskfq');
INSERT INTO `t_system_region` VALUES ('6272', '4206', '襄樊市', '420600', '湖北省襄樊市', 'hbsxfs');
INSERT INTO `t_system_region` VALUES ('6273', '420601', '市辖区', '420601', '湖北省襄樊市市辖区', 'hbsxfssxq');
INSERT INTO `t_system_region` VALUES ('6274', '420602', '襄城区', '420602', '湖北省襄樊市襄城区', 'hbsxfsxcq');
INSERT INTO `t_system_region` VALUES ('6275', '420604', '高新区', '420604', '湖北省襄樊市高新区', 'hbsxfsgxq');
INSERT INTO `t_system_region` VALUES ('6276', '420606', '樊城区', '420606', '湖北省襄樊市樊城区', 'hbsxfsfcq');
INSERT INTO `t_system_region` VALUES ('6277', '420607', '襄阳区', '420607', '湖北省襄樊市襄阳区', 'hbsxfsxyq');
INSERT INTO `t_system_region` VALUES ('6278', '420624', '南漳县', '420624', '湖北省襄樊市南漳县', 'hbsxfsnzx');
INSERT INTO `t_system_region` VALUES ('6279', '420625', '谷城县', '420625', '湖北省襄樊市谷城县', 'hbsxfsgcx');
INSERT INTO `t_system_region` VALUES ('6280', '420626', '保康县', '420626', '湖北省襄樊市保康县', 'hbsxfsbkx');
INSERT INTO `t_system_region` VALUES ('6281', '420682', '老河口市', '420682', '湖北省襄樊市老河口市', 'hbsxfslhks');
INSERT INTO `t_system_region` VALUES ('6282', '420683', '枣阳市', '420683', '湖北省襄樊市枣阳市', 'hbsxfszys');
INSERT INTO `t_system_region` VALUES ('6283', '420684', '宜城市', '420684', '湖北省襄樊市宜城市', 'hbsxfsycs');
INSERT INTO `t_system_region` VALUES ('6284', '4207', '鄂州市', '420700', '湖北省鄂州市', 'hbsezs');
INSERT INTO `t_system_region` VALUES ('6285', '420701', '市辖区', '420701', '湖北省鄂州市市辖区', 'hbsezssxq');
INSERT INTO `t_system_region` VALUES ('6286', '420702', '梁子湖区', '420702', '湖北省鄂州市梁子湖区', 'hbsezslzhq');
INSERT INTO `t_system_region` VALUES ('6287', '420703', '华容区', '420703', '湖北省鄂州市华容区', 'hbsezshrq');
INSERT INTO `t_system_region` VALUES ('6288', '420704', '鄂城区', '420704', '湖北省鄂州市鄂城区', 'hbsezsecq');
INSERT INTO `t_system_region` VALUES ('6289', '4208', '荆门市', '420800', '湖北省荆门市', 'hbsjms');
INSERT INTO `t_system_region` VALUES ('6290', '420801', '市辖区', '420801', '湖北省荆门市市辖区', 'hbsjmssxq');
INSERT INTO `t_system_region` VALUES ('6291', '420802', '东宝区', '420802', '湖北省荆门市东宝区', 'hbsjmsdbq');
INSERT INTO `t_system_region` VALUES ('6292', '420803', '荆门屈家岭管理区', '420803', '湖北省荆门市荆门屈家岭管理区', 'hbsjmsjmqjlglq');
INSERT INTO `t_system_region` VALUES ('6293', '420804', '掇刀区', '420804', '湖北省荆门市掇刀区', 'hbsjmsddq');
INSERT INTO `t_system_region` VALUES ('6294', '420821', '京山县', '420821', '湖北省荆门市京山县', 'hbsjmsjsx');
INSERT INTO `t_system_region` VALUES ('6295', '420822', '沙洋县', '420822', '湖北省荆门市沙洋县', 'hbsjmssyx');
INSERT INTO `t_system_region` VALUES ('6296', '420881', '钟祥市', '420881', '湖北省荆门市钟祥市', 'hbsjmszxs');
INSERT INTO `t_system_region` VALUES ('6297', '4209', '孝感市', '420900', '湖北省孝感市', 'hbsxgs');
INSERT INTO `t_system_region` VALUES ('6298', '420901', '市辖区', '420901', '湖北省孝感市市辖区', 'hbsxgssxq');
INSERT INTO `t_system_region` VALUES ('6299', '420902', '孝南区', '420902', '湖北省孝感市孝南区', 'hbsxgsxnq');
INSERT INTO `t_system_region` VALUES ('6300', '420921', '孝昌县', '420921', '湖北省孝感市孝昌县', 'hbsxgsxcx');
INSERT INTO `t_system_region` VALUES ('6301', '420922', '大悟县', '420922', '湖北省孝感市大悟县', 'hbsxgsdwx');
INSERT INTO `t_system_region` VALUES ('6302', '420923', '云梦县', '420923', '湖北省孝感市云梦县', 'hbsxgsymx');
INSERT INTO `t_system_region` VALUES ('6303', '420981', '应城市', '420981', '湖北省孝感市应城市', 'hbsxgsycs');
INSERT INTO `t_system_region` VALUES ('6304', '420982', '安陆市', '420982', '湖北省孝感市安陆市', 'hbsxgsals');
INSERT INTO `t_system_region` VALUES ('6305', '420984', '汉川市', '420984', '湖北省孝感市汉川市', 'hbsxgshcs');
INSERT INTO `t_system_region` VALUES ('6306', '420985', '双峰山旅游度假区', '420985', '湖北省孝感市双峰山旅游度假区', 'hbsxgssfslydjq');
INSERT INTO `t_system_region` VALUES ('6307', '420986', '高新技术开发区', '420986', '湖北省孝感市高新技术开发区', 'hbsxgsgxjskfq');
INSERT INTO `t_system_region` VALUES ('6308', '4210', '荆州市', '421000', '湖北省荆州市', 'hbsjzs');
INSERT INTO `t_system_region` VALUES ('6309', '421001', '市辖区', '421001', '湖北省荆州市市辖区', 'hbsjzssxq');
INSERT INTO `t_system_region` VALUES ('6310', '421002', '沙市区', '421002', '湖北省荆州市沙市区', 'hbsjzsssq');
INSERT INTO `t_system_region` VALUES ('6311', '421003', '荆州区', '421003', '湖北省荆州市荆州区', 'hbsjzsjzq');
INSERT INTO `t_system_region` VALUES ('6312', '421004', '荆州开发区', '421004', '湖北省荆州市荆州开发区', 'hbsjzsjzkfq');
INSERT INTO `t_system_region` VALUES ('6313', '421022', '公安县', '421022', '湖北省荆州市公安县', 'hbsjzsgax');
INSERT INTO `t_system_region` VALUES ('6314', '421023', '监利县', '421023', '湖北省荆州市监利县', 'hbsjzsjlx');
INSERT INTO `t_system_region` VALUES ('6315', '421024', '江陵县', '421024', '湖北省荆州市江陵县', 'hbsjzsjlx');
INSERT INTO `t_system_region` VALUES ('6316', '421025', '沙洋监狱管理局', '421025', '湖北省荆州市沙洋监狱管理局', 'hbsjzssyjyglj');
INSERT INTO `t_system_region` VALUES ('6317', '421081', '石首市', '421081', '湖北省荆州市石首市', 'hbsjzssss');
INSERT INTO `t_system_region` VALUES ('6318', '421083', '洪湖市', '421083', '湖北省荆州市洪湖市', 'hbsjzshhs');
INSERT INTO `t_system_region` VALUES ('6319', '421087', '松滋市', '421087', '湖北省荆州市松滋市', 'hbsjzsszs');
INSERT INTO `t_system_region` VALUES ('6320', '4211', '黄冈市', '421100', '湖北省黄冈市', 'hbshgs');
INSERT INTO `t_system_region` VALUES ('6321', '421101', '市辖区', '421101', '湖北省黄冈市市辖区', 'hbshgssxq');
INSERT INTO `t_system_region` VALUES ('6322', '421102', '黄州区', '421102', '湖北省黄冈市黄州区', 'hbshgshzq');
INSERT INTO `t_system_region` VALUES ('6323', '421121', '团风县', '421121', '湖北省黄冈市团风县', 'hbshgstfx');
INSERT INTO `t_system_region` VALUES ('6324', '421122', '红安县', '421122', '湖北省黄冈市红安县', 'hbshgshax');
INSERT INTO `t_system_region` VALUES ('6325', '421123', '罗田县', '421123', '湖北省黄冈市罗田县', 'hbshgsltx');
INSERT INTO `t_system_region` VALUES ('6326', '421124', '英山县', '421124', '湖北省黄冈市英山县', 'hbshgsysx');
INSERT INTO `t_system_region` VALUES ('6327', '421125', '浠水县', '421125', '湖北省黄冈市浠水县', 'hbshgsxsx');
INSERT INTO `t_system_region` VALUES ('6328', '421126', '蕲春县', '421126', '湖北省黄冈市蕲春县', 'hbshgsqcx');
INSERT INTO `t_system_region` VALUES ('6329', '421127', '黄梅县', '421127', '湖北省黄冈市黄梅县', 'hbshgshmx');
INSERT INTO `t_system_region` VALUES ('6330', '421129', '龙感湖管理区', '421129', '湖北省黄冈市龙感湖管理区', 'hbshgslghglq');
INSERT INTO `t_system_region` VALUES ('6331', '421181', '麻城市', '421181', '湖北省黄冈市麻城市', 'hbshgsmcs');
INSERT INTO `t_system_region` VALUES ('6332', '421182', '武穴市', '421182', '湖北省黄冈市武穴市', 'hbshgswxs');
INSERT INTO `t_system_region` VALUES ('6333', '421183', '经济技术开发区', '421183', '湖北省黄冈市经济技术开发区', 'hbshgsjjjskfq');
INSERT INTO `t_system_region` VALUES ('6334', '4212', '咸宁市', '421200', '湖北省咸宁市', 'hbsxns');
INSERT INTO `t_system_region` VALUES ('6335', '421201', '市辖区', '421201', '湖北省咸宁市市辖区', 'hbsxnssxq');
INSERT INTO `t_system_region` VALUES ('6336', '421202', '咸安区', '421202', '湖北省咸宁市咸安区', 'hbsxnsxaq');
INSERT INTO `t_system_region` VALUES ('6337', '421221', '嘉鱼县', '421221', '湖北省咸宁市嘉鱼县', 'hbsxnsjyx');
INSERT INTO `t_system_region` VALUES ('6338', '421222', '通城县', '421222', '湖北省咸宁市通城县', 'hbsxnstcx');
INSERT INTO `t_system_region` VALUES ('6339', '421223', '崇阳县', '421223', '湖北省咸宁市崇阳县', 'hbsxnscyx');
INSERT INTO `t_system_region` VALUES ('6340', '421224', '通山县', '421224', '湖北省咸宁市通山县', 'hbsxnstsx');
INSERT INTO `t_system_region` VALUES ('6341', '421281', '赤壁市', '421281', '湖北省咸宁市赤壁市', 'hbsxnscbs');
INSERT INTO `t_system_region` VALUES ('6342', '4213', '随州市', '421300', '湖北省随州市', 'hbsszs');
INSERT INTO `t_system_region` VALUES ('6343', '421301', '市辖区', '421301', '湖北省随州市市辖区', 'hbsszssxq');
INSERT INTO `t_system_region` VALUES ('6344', '421302', '曾都区', '421302', '湖北省随州市曾都区', 'hbsszscdq');
INSERT INTO `t_system_region` VALUES ('6345', '421321', '随县', '421321', '湖北省随州市随县', 'hbsszssx');
INSERT INTO `t_system_region` VALUES ('6346', '421381', '广水市', '421381', '湖北省随州市广水市', 'hbsszsgss');
INSERT INTO `t_system_region` VALUES ('6347', '4228', '恩施土家族苗族自治州', '422800', '湖北省恩施土家族苗族自治州', 'hbsestjzmzzzz');
INSERT INTO `t_system_region` VALUES ('6348', '422801', '恩施市', '422801', '湖北省恩施土家族苗族自治州恩施市', 'hbsestjzmzzzzess');
INSERT INTO `t_system_region` VALUES ('6349', '422802', '利川市', '422802', '湖北省恩施土家族苗族自治州利川市', 'hbsestjzmzzzzlcs');
INSERT INTO `t_system_region` VALUES ('6350', '422822', '建始县', '422822', '湖北省恩施土家族苗族自治州建始县', 'hbsestjzmzzzzjsx');
INSERT INTO `t_system_region` VALUES ('6351', '422823', '巴东县', '422823', '湖北省恩施土家族苗族自治州巴东县', 'hbsestjzmzzzzbdx');
INSERT INTO `t_system_region` VALUES ('6352', '422825', '宣恩县', '422825', '湖北省恩施土家族苗族自治州宣恩县', 'hbsestjzmzzzzxex');
INSERT INTO `t_system_region` VALUES ('6353', '422826', '咸丰县', '422826', '湖北省恩施土家族苗族自治州咸丰县', 'hbsestjzmzzzzxfx');
INSERT INTO `t_system_region` VALUES ('6354', '422827', '来凤县', '422827', '湖北省恩施土家族苗族自治州来凤县', 'hbsestjzmzzzzlfx');
INSERT INTO `t_system_region` VALUES ('6355', '422828', '鹤峰县', '422828', '湖北省恩施土家族苗族自治州鹤峰县', 'hbsestjzmzzzzhfx');
INSERT INTO `t_system_region` VALUES ('6356', '422891', '恩施州州直', '422891', '湖北省恩施土家族苗族自治州恩施州州直', 'hbsestjzmzzzzeszzz');
INSERT INTO `t_system_region` VALUES ('6357', '4290', '省直辖行政单位', '429000', '湖北省省直辖行政单位', 'hbsszxxzdw');
INSERT INTO `t_system_region` VALUES ('6358', '429004', '仙桃市', '429004', '湖北省省直辖行政单位仙桃市', 'hbsszxxzdwxts');
INSERT INTO `t_system_region` VALUES ('6359', '429005', '潜江市', '429005', '湖北省省直辖行政单位潜江市', 'hbsszxxzdwqjs');
INSERT INTO `t_system_region` VALUES ('6360', '429006', '天门市', '429006', '湖北省省直辖行政单位天门市', 'hbsszxxzdwtms');
INSERT INTO `t_system_region` VALUES ('6361', '429021', '神农架林区', '429021', '湖北省省直辖行政单位神农架林区', 'hbsszxxzdwsnjlq');
INSERT INTO `t_system_region` VALUES ('6362', '429022', '江汉油田', '429022', '湖北省省直辖行政单位江汉油田', 'hbsszxxzdwjhyt');
INSERT INTO `t_system_region` VALUES ('6363', '43', '湖南省', '430000', '湖南省', 'hns');
INSERT INTO `t_system_region` VALUES ('6364', '4301', '长沙市', '430100', '湖南省长沙市', 'hnszss');
INSERT INTO `t_system_region` VALUES ('6365', '430101', '市辖区', '430101', '湖南省长沙市市辖区', 'hnszsssxq');
INSERT INTO `t_system_region` VALUES ('6366', '430102', '芙蓉区', '430102', '湖南省长沙市芙蓉区', 'hnszssfrq');
INSERT INTO `t_system_region` VALUES ('6367', '430103', '天心区', '430103', '湖南省长沙市天心区', 'hnszsstxq');
INSERT INTO `t_system_region` VALUES ('6368', '430104', '岳麓区', '430104', '湖南省长沙市岳麓区', 'hnszssylq');
INSERT INTO `t_system_region` VALUES ('6369', '430105', '开福区', '430105', '湖南省长沙市开福区', 'hnszsskfq');
INSERT INTO `t_system_region` VALUES ('6370', '430111', '雨花区', '430111', '湖南省长沙市雨花区', 'hnszssyhq');
INSERT INTO `t_system_region` VALUES ('6371', '430121', '长沙县', '430121', '湖南省长沙市长沙县', 'hnszsszsx');
INSERT INTO `t_system_region` VALUES ('6372', '430122', '望城县', '430122', '湖南省长沙市望城县', 'hnszsswcx');
INSERT INTO `t_system_region` VALUES ('6373', '430124', '宁乡县', '430124', '湖南省长沙市宁乡县', 'hnszssnxx');
INSERT INTO `t_system_region` VALUES ('6374', '430181', '浏阳市', '430181', '湖南省长沙市浏阳市', 'hnszsslys');
INSERT INTO `t_system_region` VALUES ('6375', '4302', '株洲市', '430200', '湖南省株洲市', 'hnszzs');
INSERT INTO `t_system_region` VALUES ('6376', '430201', '市辖区', '430201', '湖南省株洲市市辖区', 'hnszzssxq');
INSERT INTO `t_system_region` VALUES ('6377', '430202', '荷塘区', '430202', '湖南省株洲市荷塘区', 'hnszzshtq');
INSERT INTO `t_system_region` VALUES ('6378', '430203', '芦淞区', '430203', '湖南省株洲市芦淞区', 'hnszzslsq');
INSERT INTO `t_system_region` VALUES ('6379', '430204', '石峰区', '430204', '湖南省株洲市石峰区', 'hnszzssfq');
INSERT INTO `t_system_region` VALUES ('6380', '430211', '天元区', '430211', '湖南省株洲市天元区', 'hnszzstyq');
INSERT INTO `t_system_region` VALUES ('6381', '430221', '株洲县', '430221', '湖南省株洲市株洲县', 'hnszzszzx');
INSERT INTO `t_system_region` VALUES ('6382', '430223', '攸县', '430223', '湖南省株洲市攸县', 'hnszzsyx');
INSERT INTO `t_system_region` VALUES ('6383', '430224', '茶陵县', '430224', '湖南省株洲市茶陵县', 'hnszzsclx');
INSERT INTO `t_system_region` VALUES ('6384', '430225', '炎陵县', '430225', '湖南省株洲市炎陵县', 'hnszzsylx');
INSERT INTO `t_system_region` VALUES ('6385', '430281', '醴陵市', '430281', '湖南省株洲市醴陵市', 'hnszzslls');
INSERT INTO `t_system_region` VALUES ('6386', '4303', '湘潭市', '430300', '湖南省湘潭市', 'hnsxts');
INSERT INTO `t_system_region` VALUES ('6387', '430301', '市辖区', '430301', '湖南省湘潭市市辖区', 'hnsxtssxq');
INSERT INTO `t_system_region` VALUES ('6388', '430302', '雨湖区', '430302', '湖南省湘潭市雨湖区', 'hnsxtsyhq');
INSERT INTO `t_system_region` VALUES ('6389', '430304', '岳塘区', '430304', '湖南省湘潭市岳塘区', 'hnsxtsytq');
INSERT INTO `t_system_region` VALUES ('6390', '430321', '湘潭县', '430321', '湖南省湘潭市湘潭县', 'hnsxtsxtx');
INSERT INTO `t_system_region` VALUES ('6391', '430381', '湘乡市', '430381', '湖南省湘潭市湘乡市', 'hnsxtsxxs');
INSERT INTO `t_system_region` VALUES ('6392', '430382', '韶山市', '430382', '湖南省湘潭市韶山市', 'hnsxtssss');
INSERT INTO `t_system_region` VALUES ('6393', '4304', '衡阳市', '430400', '湖南省衡阳市', 'hnshys');
INSERT INTO `t_system_region` VALUES ('6394', '430401', '市辖区', '430401', '湖南省衡阳市市辖区', 'hnshyssxq');
INSERT INTO `t_system_region` VALUES ('6395', '430405', '珠晖区', '430405', '湖南省衡阳市珠晖区', 'hnshyszhq');
INSERT INTO `t_system_region` VALUES ('6396', '430406', '雁峰区', '430406', '湖南省衡阳市雁峰区', 'hnshysyfq');
INSERT INTO `t_system_region` VALUES ('6397', '430407', '石鼓区', '430407', '湖南省衡阳市石鼓区', 'hnshyssgq');
INSERT INTO `t_system_region` VALUES ('6398', '430408', '蒸湘区', '430408', '湖南省衡阳市蒸湘区', 'hnshyszxq');
INSERT INTO `t_system_region` VALUES ('6399', '430412', '南岳区', '430412', '湖南省衡阳市南岳区', 'hnshysnyq');
INSERT INTO `t_system_region` VALUES ('6400', '430421', '衡阳县', '430421', '湖南省衡阳市衡阳县', 'hnshyshyx');
INSERT INTO `t_system_region` VALUES ('6401', '430422', '衡南县', '430422', '湖南省衡阳市衡南县', 'hnshyshnx');
INSERT INTO `t_system_region` VALUES ('6402', '430423', '衡山县', '430423', '湖南省衡阳市衡山县', 'hnshyshsx');
INSERT INTO `t_system_region` VALUES ('6403', '430424', '衡东县', '430424', '湖南省衡阳市衡东县', 'hnshyshdx');
INSERT INTO `t_system_region` VALUES ('6404', '430426', '祁东县', '430426', '湖南省衡阳市祁东县', 'hnshysqdx');
INSERT INTO `t_system_region` VALUES ('6405', '430481', '耒阳市', '430481', '湖南省衡阳市耒阳市', 'hnshyslys');
INSERT INTO `t_system_region` VALUES ('6406', '430482', '常宁市', '430482', '湖南省衡阳市常宁市', 'hnshyscns');
INSERT INTO `t_system_region` VALUES ('6407', '4305', '邵阳市', '430500', '湖南省邵阳市', 'hnssys');
INSERT INTO `t_system_region` VALUES ('6408', '430501', '市辖区', '430501', '湖南省邵阳市市辖区', 'hnssyssxq');
INSERT INTO `t_system_region` VALUES ('6409', '430502', '双清区', '430502', '湖南省邵阳市双清区', 'hnssyssqq');
INSERT INTO `t_system_region` VALUES ('6410', '430503', '大祥区', '430503', '湖南省邵阳市大祥区', 'hnssysdxq');
INSERT INTO `t_system_region` VALUES ('6411', '430511', '北塔区', '430511', '湖南省邵阳市北塔区', 'hnssysbtq');
INSERT INTO `t_system_region` VALUES ('6412', '430521', '邵东县', '430521', '湖南省邵阳市邵东县', 'hnssyssdx');
INSERT INTO `t_system_region` VALUES ('6413', '430522', '新邵县', '430522', '湖南省邵阳市新邵县', 'hnssysxsx');
INSERT INTO `t_system_region` VALUES ('6414', '430523', '邵阳县', '430523', '湖南省邵阳市邵阳县', 'hnssyssyx');
INSERT INTO `t_system_region` VALUES ('6415', '430524', '隆回县', '430524', '湖南省邵阳市隆回县', 'hnssyslhx');
INSERT INTO `t_system_region` VALUES ('6416', '430525', '洞口县', '430525', '湖南省邵阳市洞口县', 'hnssysdkx');
INSERT INTO `t_system_region` VALUES ('6417', '430527', '绥宁县', '430527', '湖南省邵阳市绥宁县', 'hnssyssnx');
INSERT INTO `t_system_region` VALUES ('6418', '430528', '新宁县', '430528', '湖南省邵阳市新宁县', 'hnssysxnx');
INSERT INTO `t_system_region` VALUES ('6419', '430529', '城步苗族自治县', '430529', '湖南省邵阳市城步苗族自治县', 'hnssyscbmzzzx');
INSERT INTO `t_system_region` VALUES ('6420', '430581', '武冈市', '430581', '湖南省邵阳市武冈市', 'hnssyswgs');
INSERT INTO `t_system_region` VALUES ('6421', '4306', '岳阳市', '430600', '湖南省岳阳市', 'hnsyys');
INSERT INTO `t_system_region` VALUES ('6422', '430601', '市辖区', '430601', '湖南省岳阳市市辖区', 'hnsyyssxq');
INSERT INTO `t_system_region` VALUES ('6423', '430602', '岳阳楼区', '430602', '湖南省岳阳市岳阳楼区', 'hnsyysyylq');
INSERT INTO `t_system_region` VALUES ('6424', '430603', '云溪区', '430603', '湖南省岳阳市云溪区', 'hnsyysyxq');
INSERT INTO `t_system_region` VALUES ('6425', '430611', '君山区', '430611', '湖南省岳阳市君山区', 'hnsyysjsq');
INSERT INTO `t_system_region` VALUES ('6426', '430621', '岳阳县', '430621', '湖南省岳阳市岳阳县', 'hnsyysyyx');
INSERT INTO `t_system_region` VALUES ('6427', '430623', '华容县', '430623', '湖南省岳阳市华容县', 'hnsyyshrx');
INSERT INTO `t_system_region` VALUES ('6428', '430624', '湘阴县', '430624', '湖南省岳阳市湘阴县', 'hnsyysxyx');
INSERT INTO `t_system_region` VALUES ('6429', '430626', '平江县', '430626', '湖南省岳阳市平江县', 'hnsyyspjx');
INSERT INTO `t_system_region` VALUES ('6430', '430681', '汨罗市', '430681', '湖南省岳阳市汨罗市', 'hnsyysmls');
INSERT INTO `t_system_region` VALUES ('6431', '430682', '临湘市', '430682', '湖南省岳阳市临湘市', 'hnsyyslxs');
INSERT INTO `t_system_region` VALUES ('6432', '4307', '常德市', '430700', '湖南省常德市', 'hnscds');
INSERT INTO `t_system_region` VALUES ('6433', '430701', '市辖区', '430701', '湖南省常德市市辖区', 'hnscdssxq');
INSERT INTO `t_system_region` VALUES ('6434', '430702', '武陵区', '430702', '湖南省常德市武陵区', 'hnscdswlq');
INSERT INTO `t_system_region` VALUES ('6435', '430703', '鼎城区', '430703', '湖南省常德市鼎城区', 'hnscdsdcq');
INSERT INTO `t_system_region` VALUES ('6436', '430721', '安乡县', '430721', '湖南省常德市安乡县', 'hnscdsaxx');
INSERT INTO `t_system_region` VALUES ('6437', '430722', '汉寿县', '430722', '湖南省常德市汉寿县', 'hnscdshsx');
INSERT INTO `t_system_region` VALUES ('6438', '430723', '澧县', '430723', '湖南省常德市澧县', 'hnscdslx');
INSERT INTO `t_system_region` VALUES ('6439', '430724', '临澧县', '430724', '湖南省常德市临澧县', 'hnscdsllx');
INSERT INTO `t_system_region` VALUES ('6440', '430725', '桃源县', '430725', '湖南省常德市桃源县', 'hnscdstyx');
INSERT INTO `t_system_region` VALUES ('6441', '430726', '石门县', '430726', '湖南省常德市石门县', 'hnscdssmx');
INSERT INTO `t_system_region` VALUES ('6442', '430781', '津市市', '430781', '湖南省常德市津市市', 'hnscdsjss');
INSERT INTO `t_system_region` VALUES ('6443', '4308', '张家界市', '430800', '湖南省张家界市', 'hnszjjs');
INSERT INTO `t_system_region` VALUES ('6444', '430801', '市辖区', '430801', '湖南省张家界市市辖区', 'hnszjjssxq');
INSERT INTO `t_system_region` VALUES ('6445', '430802', '永定区', '430802', '湖南省张家界市永定区', 'hnszjjsydq');
INSERT INTO `t_system_region` VALUES ('6446', '430811', '武陵源区', '430811', '湖南省张家界市武陵源区', 'hnszjjswlyq');
INSERT INTO `t_system_region` VALUES ('6447', '430821', '慈利县', '430821', '湖南省张家界市慈利县', 'hnszjjsclx');
INSERT INTO `t_system_region` VALUES ('6448', '430822', '桑植县', '430822', '湖南省张家界市桑植县', 'hnszjjsszx');
INSERT INTO `t_system_region` VALUES ('6449', '4309', '益阳市', '430900', '湖南省益阳市', 'hnsyys');
INSERT INTO `t_system_region` VALUES ('6450', '430901', '市辖区', '430901', '湖南省益阳市市辖区', 'hnsyyssxq');
INSERT INTO `t_system_region` VALUES ('6451', '430902', '资阳区', '430902', '湖南省益阳市资阳区', 'hnsyyszyq');
INSERT INTO `t_system_region` VALUES ('6452', '430903', '赫山区', '430903', '湖南省益阳市赫山区', 'hnsyyshsq');
INSERT INTO `t_system_region` VALUES ('6453', '430921', '南县', '430921', '湖南省益阳市南县', 'hnsyysnx');
INSERT INTO `t_system_region` VALUES ('6454', '430922', '桃江县', '430922', '湖南省益阳市桃江县', 'hnsyystjx');
INSERT INTO `t_system_region` VALUES ('6455', '430923', '安化县', '430923', '湖南省益阳市安化县', 'hnsyysahx');
INSERT INTO `t_system_region` VALUES ('6456', '430981', '沅江市', '430981', '湖南省益阳市沅江市', 'hnsyysyjs');
INSERT INTO `t_system_region` VALUES ('6457', '4310', '郴州市', '431000', '湖南省郴州市', 'hnsczs');
INSERT INTO `t_system_region` VALUES ('6458', '431001', '市辖区', '431001', '湖南省郴州市市辖区', 'hnsczssxq');
INSERT INTO `t_system_region` VALUES ('6459', '431002', '北湖区', '431002', '湖南省郴州市北湖区', 'hnsczsbhq');
INSERT INTO `t_system_region` VALUES ('6460', '431003', '苏仙区', '431003', '湖南省郴州市苏仙区', 'hnsczssxq');
INSERT INTO `t_system_region` VALUES ('6461', '431021', '桂阳县', '431021', '湖南省郴州市桂阳县', 'hnsczsgyx');
INSERT INTO `t_system_region` VALUES ('6462', '431022', '宜章县', '431022', '湖南省郴州市宜章县', 'hnsczsyzx');
INSERT INTO `t_system_region` VALUES ('6463', '431023', '永兴县', '431023', '湖南省郴州市永兴县', 'hnsczsyxx');
INSERT INTO `t_system_region` VALUES ('6464', '431024', '嘉禾县', '431024', '湖南省郴州市嘉禾县', 'hnsczsjhx');
INSERT INTO `t_system_region` VALUES ('6465', '431025', '临武县', '431025', '湖南省郴州市临武县', 'hnsczslwx');
INSERT INTO `t_system_region` VALUES ('6466', '431026', '汝城县', '431026', '湖南省郴州市汝城县', 'hnsczsrcx');
INSERT INTO `t_system_region` VALUES ('6467', '431027', '桂东县', '431027', '湖南省郴州市桂东县', 'hnsczsgdx');
INSERT INTO `t_system_region` VALUES ('6468', '431028', '安仁县', '431028', '湖南省郴州市安仁县', 'hnsczsarx');
INSERT INTO `t_system_region` VALUES ('6469', '431081', '资兴市', '431081', '湖南省郴州市资兴市', 'hnsczszxs');
INSERT INTO `t_system_region` VALUES ('6470', '4311', '永州市', '431100', '湖南省永州市', 'hnsyzs');
INSERT INTO `t_system_region` VALUES ('6471', '431101', '市辖区', '431101', '湖南省永州市市辖区', 'hnsyzssxq');
INSERT INTO `t_system_region` VALUES ('6472', '431102', '零陵区', '431102', '湖南省永州市零陵区', 'hnsyzsllq');
INSERT INTO `t_system_region` VALUES ('6473', '431103', '冷水滩区', '431103', '湖南省永州市冷水滩区', 'hnsyzslstq');
INSERT INTO `t_system_region` VALUES ('6474', '431121', '祁阳县', '431121', '湖南省永州市祁阳县', 'hnsyzsqyx');
INSERT INTO `t_system_region` VALUES ('6475', '431122', '东安县', '431122', '湖南省永州市东安县', 'hnsyzsdax');
INSERT INTO `t_system_region` VALUES ('6476', '431123', '双牌县', '431123', '湖南省永州市双牌县', 'hnsyzsspx');
INSERT INTO `t_system_region` VALUES ('6477', '431124', '道县', '431124', '湖南省永州市道县', 'hnsyzsdx');
INSERT INTO `t_system_region` VALUES ('6478', '431125', '江永县', '431125', '湖南省永州市江永县', 'hnsyzsjyx');
INSERT INTO `t_system_region` VALUES ('6479', '431126', '宁远县', '431126', '湖南省永州市宁远县', 'hnsyzsnyx');
INSERT INTO `t_system_region` VALUES ('6480', '431127', '蓝山县', '431127', '湖南省永州市蓝山县', 'hnsyzslsx');
INSERT INTO `t_system_region` VALUES ('6481', '431128', '新田县', '431128', '湖南省永州市新田县', 'hnsyzsxtx');
INSERT INTO `t_system_region` VALUES ('6482', '431129', '江华瑶族自治县', '431129', '湖南省永州市江华瑶族自治县', 'hnsyzsjhyzzzx');
INSERT INTO `t_system_region` VALUES ('6483', '4312', '怀化市', '431200', '湖南省怀化市', 'hnshhs');
INSERT INTO `t_system_region` VALUES ('6484', '431201', '市辖区', '431201', '湖南省怀化市市辖区', 'hnshhssxq');
INSERT INTO `t_system_region` VALUES ('6485', '431202', '鹤城区', '431202', '湖南省怀化市鹤城区', 'hnshhshcq');
INSERT INTO `t_system_region` VALUES ('6486', '431221', '中方县', '431221', '湖南省怀化市中方县', 'hnshhszfx');
INSERT INTO `t_system_region` VALUES ('6487', '431222', '沅陵县', '431222', '湖南省怀化市沅陵县', 'hnshhsylx');
INSERT INTO `t_system_region` VALUES ('6488', '431223', '辰溪县', '431223', '湖南省怀化市辰溪县', 'hnshhscxx');
INSERT INTO `t_system_region` VALUES ('6489', '431224', '溆浦县', '431224', '湖南省怀化市溆浦县', 'hnshhsxpx');
INSERT INTO `t_system_region` VALUES ('6490', '431225', '会同县', '431225', '湖南省怀化市会同县', 'hnshhshtx');
INSERT INTO `t_system_region` VALUES ('6491', '431226', '麻阳苗族自治县', '431226', '湖南省怀化市麻阳苗族自治县', 'hnshhsmymzzzx');
INSERT INTO `t_system_region` VALUES ('6492', '431227', '新晃侗族自治县', '431227', '湖南省怀化市新晃侗族自治县', 'hnshhsxhdzzzx');
INSERT INTO `t_system_region` VALUES ('6493', '431228', '芷江侗族自治县', '431228', '湖南省怀化市芷江侗族自治县', 'hnshhszjdzzzx');
INSERT INTO `t_system_region` VALUES ('6494', '431229', '靖州苗族侗族自治县', '431229', '湖南省怀化市靖州苗族侗族自治县', 'hnshhsjzmzdzzzx');
INSERT INTO `t_system_region` VALUES ('6495', '431230', '通道侗族自治县', '431230', '湖南省怀化市通道侗族自治县', 'hnshhstddzzzx');
INSERT INTO `t_system_region` VALUES ('6496', '431281', '洪江市', '431281', '湖南省怀化市洪江市', 'hnshhshjs');
INSERT INTO `t_system_region` VALUES ('6497', '4313', '娄底市', '431300', '湖南省娄底市', 'hnslds');
INSERT INTO `t_system_region` VALUES ('6498', '431301', '市辖区', '431301', '湖南省娄底市市辖区', 'hnsldssxq');
INSERT INTO `t_system_region` VALUES ('6499', '431302', '娄星区', '431302', '湖南省娄底市娄星区', 'hnsldslxq');
INSERT INTO `t_system_region` VALUES ('6500', '431321', '双峰县', '431321', '湖南省娄底市双峰县', 'hnsldssfx');
INSERT INTO `t_system_region` VALUES ('6501', '431322', '新化县', '431322', '湖南省娄底市新化县', 'hnsldsxhx');
INSERT INTO `t_system_region` VALUES ('6502', '431381', '冷水江市', '431381', '湖南省娄底市冷水江市', 'hnsldslsjs');
INSERT INTO `t_system_region` VALUES ('6503', '431382', '涟源市', '431382', '湖南省娄底市涟源市', 'hnsldslys');
INSERT INTO `t_system_region` VALUES ('6504', '4331', '湘西土家族苗族自治州', '433100', '湖南省湘西土家族苗族自治州', 'hnsxxtjzmzzzz');
INSERT INTO `t_system_region` VALUES ('6505', '433101', '吉首市', '433101', '湖南省湘西土家族苗族自治州吉首市', 'hnsxxtjzmzzzzjss');
INSERT INTO `t_system_region` VALUES ('6506', '433122', '泸溪县', '433122', '湖南省湘西土家族苗族自治州泸溪县', 'hnsxxtjzmzzzzlxx');
INSERT INTO `t_system_region` VALUES ('6507', '433123', '凤凰县', '433123', '湖南省湘西土家族苗族自治州凤凰县', 'hnsxxtjzmzzzzfhx');
INSERT INTO `t_system_region` VALUES ('6508', '433124', '花垣县', '433124', '湖南省湘西土家族苗族自治州花垣县', 'hnsxxtjzmzzzzhyx');
INSERT INTO `t_system_region` VALUES ('6509', '433125', '保靖县', '433125', '湖南省湘西土家族苗族自治州保靖县', 'hnsxxtjzmzzzzbjx');
INSERT INTO `t_system_region` VALUES ('6510', '433126', '古丈县', '433126', '湖南省湘西土家族苗族自治州古丈县', 'hnsxxtjzmzzzzgzx');
INSERT INTO `t_system_region` VALUES ('6511', '433127', '永顺县', '433127', '湖南省湘西土家族苗族自治州永顺县', 'hnsxxtjzmzzzzysx');
INSERT INTO `t_system_region` VALUES ('6512', '433130', '龙山县', '433130', '湖南省湘西土家族苗族自治州龙山县', 'hnsxxtjzmzzzzlsx');
INSERT INTO `t_system_region` VALUES ('6513', '44', '广东省', '440000', '广东省', 'gds');
INSERT INTO `t_system_region` VALUES ('6514', '4401', '广州市', '440100', '广东省广州市', 'gdsgzs');
INSERT INTO `t_system_region` VALUES ('6515', '440101', '市辖区', '440101', '广东省广州市市辖区', 'gdsgzssxq');
INSERT INTO `t_system_region` VALUES ('6516', '440103', '荔湾区', '440103', '广东省广州市荔湾区', 'gdsgzslwq');
INSERT INTO `t_system_region` VALUES ('6517', '440104', '越秀区', '440104', '广东省广州市越秀区', 'gdsgzsyxq');
INSERT INTO `t_system_region` VALUES ('6518', '440105', '海珠区', '440105', '广东省广州市海珠区', 'gdsgzshzq');
INSERT INTO `t_system_region` VALUES ('6519', '440106', '天河区', '440106', '广东省广州市天河区', 'gdsgzsthq');
INSERT INTO `t_system_region` VALUES ('6520', '440111', '白云区', '440111', '广东省广州市白云区', 'gdsgzsbyq');
INSERT INTO `t_system_region` VALUES ('6521', '440112', '黄埔区', '440112', '广东省广州市黄埔区', 'gdsgzshpq');
INSERT INTO `t_system_region` VALUES ('6522', '440113', '番禺区', '440113', '广东省广州市番禺区', 'gdsgzsfyq');
INSERT INTO `t_system_region` VALUES ('6523', '440114', '花都区', '440114', '广东省广州市花都区', 'gdsgzshdq');
INSERT INTO `t_system_region` VALUES ('6524', '440115', '南沙区', '440115', '广东省广州市南沙区', 'gdsgzsnsq');
INSERT INTO `t_system_region` VALUES ('6525', '440116', '萝岗区', '440116', '广东省广州市萝岗区', 'gdsgzslgq');
INSERT INTO `t_system_region` VALUES ('6526', '440183', '增城市', '440183', '广东省广州市增城市', 'gdsgzszcs');
INSERT INTO `t_system_region` VALUES ('6527', '440184', '从化市', '440184', '广东省广州市从化市', 'gdsgzschs');
INSERT INTO `t_system_region` VALUES ('6528', '4402', '韶关市', '440200', '广东省韶关市', 'gdssgs');
INSERT INTO `t_system_region` VALUES ('6529', '440201', '市辖区', '440201', '广东省韶关市市辖区', 'gdssgssxq');
INSERT INTO `t_system_region` VALUES ('6530', '440203', '武江区', '440203', '广东省韶关市武江区', 'gdssgswjq');
INSERT INTO `t_system_region` VALUES ('6531', '440204', '浈江区', '440204', '广东省韶关市浈江区', 'gdssgszjq');
INSERT INTO `t_system_region` VALUES ('6532', '440205', '曲江区', '440205', '广东省韶关市曲江区', 'gdssgsqjq');
INSERT INTO `t_system_region` VALUES ('6533', '440222', '始兴县', '440222', '广东省韶关市始兴县', 'gdssgssxx');
INSERT INTO `t_system_region` VALUES ('6534', '440224', '仁化县', '440224', '广东省韶关市仁化县', 'gdssgsrhx');
INSERT INTO `t_system_region` VALUES ('6535', '440229', '翁源县', '440229', '广东省韶关市翁源县', 'gdssgswyx');
INSERT INTO `t_system_region` VALUES ('6536', '440232', '乳源瑶族自治县', '440232', '广东省韶关市乳源瑶族自治县', 'gdssgsryyzzzx');
INSERT INTO `t_system_region` VALUES ('6537', '440233', '新丰县', '440233', '广东省韶关市新丰县', 'gdssgsxfx');
INSERT INTO `t_system_region` VALUES ('6538', '440281', '乐昌市', '440281', '广东省韶关市乐昌市', 'gdssgslcs');
INSERT INTO `t_system_region` VALUES ('6539', '440282', '南雄市', '440282', '广东省韶关市南雄市', 'gdssgsnxs');
INSERT INTO `t_system_region` VALUES ('6540', '4403', '深圳市', '440300', '广东省深圳市', 'gdsszs');
INSERT INTO `t_system_region` VALUES ('6541', '440301', '市辖区', '440301', '广东省深圳市市辖区', 'gdsszssxq');
INSERT INTO `t_system_region` VALUES ('6542', '440302', '深圳市市本级', '440302', '广东省深圳市深圳市市本级', 'gdsszsszssbj');
INSERT INTO `t_system_region` VALUES ('6543', '440303', '罗湖区', '440303', '广东省深圳市罗湖区', 'gdsszslhq');
INSERT INTO `t_system_region` VALUES ('6544', '440304', '福田区', '440304', '广东省深圳市福田区', 'gdsszsftq');
INSERT INTO `t_system_region` VALUES ('6545', '440305', '南山区', '440305', '广东省深圳市南山区', 'gdsszsnsq');
INSERT INTO `t_system_region` VALUES ('6546', '440306', '宝安区', '440306', '广东省深圳市宝安区', 'gdsszsbaq');
INSERT INTO `t_system_region` VALUES ('6547', '440307', '龙岗区', '440307', '广东省深圳市龙岗区', 'gdsszslgq');
INSERT INTO `t_system_region` VALUES ('6548', '440308', '盐田区', '440308', '广东省深圳市盐田区', 'gdsszsytq');
INSERT INTO `t_system_region` VALUES ('6549', '440309', '光明新区', '440309', '广东省深圳市光明新区', 'gdsszsgmxq');
INSERT INTO `t_system_region` VALUES ('6550', '440310', '深圳市坪山新区', '440310', '广东省深圳市深圳市坪山新区', 'gdsszsszspsxq');
INSERT INTO `t_system_region` VALUES ('6551', '4404', '珠海市', '440400', '广东省珠海市', 'gdszhs');
INSERT INTO `t_system_region` VALUES ('6552', '440401', '市辖区', '440401', '广东省珠海市市辖区', 'gdszhssxq');
INSERT INTO `t_system_region` VALUES ('6553', '440402', '香洲区', '440402', '广东省珠海市香洲区', 'gdszhsxzq');
INSERT INTO `t_system_region` VALUES ('6554', '440403', '斗门区', '440403', '广东省珠海市斗门区', 'gdszhsdmq');
INSERT INTO `t_system_region` VALUES ('6555', '440404', '金湾区', '440404', '广东省珠海市金湾区', 'gdszhsjwq');
INSERT INTO `t_system_region` VALUES ('6556', '4405', '汕头市', '440500', '广东省汕头市', 'gdssts');
INSERT INTO `t_system_region` VALUES ('6557', '440501', '市辖区', '440501', '广东省汕头市市辖区', 'gdsstssxq');
INSERT INTO `t_system_region` VALUES ('6558', '440507', '龙湖区', '440507', '广东省汕头市龙湖区', 'gdsstslhq');
INSERT INTO `t_system_region` VALUES ('6559', '440511', '金平区', '440511', '广东省汕头市金平区', 'gdsstsjpq');
INSERT INTO `t_system_region` VALUES ('6560', '440512', '濠江区', '440512', '广东省汕头市濠江区', 'gdsstshjq');
INSERT INTO `t_system_region` VALUES ('6561', '440513', '潮阳区', '440513', '广东省汕头市潮阳区', 'gdsstscyq');
INSERT INTO `t_system_region` VALUES ('6562', '440514', '潮南区', '440514', '广东省汕头市潮南区', 'gdsstscnq');
INSERT INTO `t_system_region` VALUES ('6563', '440515', '澄海区', '440515', '广东省汕头市澄海区', 'gdsstschq');
INSERT INTO `t_system_region` VALUES ('6564', '440523', '南澳县', '440523', '广东省汕头市南澳县', 'gdsstsnax');
INSERT INTO `t_system_region` VALUES ('6565', '4406', '佛山市', '440600', '广东省佛山市', 'gdsfss');
INSERT INTO `t_system_region` VALUES ('6566', '440601', '市辖区', '440601', '广东省佛山市市辖区', 'gdsfsssxq');
INSERT INTO `t_system_region` VALUES ('6567', '440604', '禅城区', '440604', '广东省佛山市禅城区', 'gdsfssscq');
INSERT INTO `t_system_region` VALUES ('6568', '440605', '南海区', '440605', '广东省佛山市南海区', 'gdsfssnhq');
INSERT INTO `t_system_region` VALUES ('6569', '440606', '顺德区', '440606', '广东省佛山市顺德区', 'gdsfsssdq');
INSERT INTO `t_system_region` VALUES ('6570', '440607', '三水区', '440607', '广东省佛山市三水区', 'gdsfssssq');
INSERT INTO `t_system_region` VALUES ('6571', '440608', '高明区', '440608', '广东省佛山市高明区', 'gdsfssgmq');
INSERT INTO `t_system_region` VALUES ('6572', '4407', '江门市', '440700', '广东省江门市', 'gdsjms');
INSERT INTO `t_system_region` VALUES ('6573', '440701', '市辖区', '440701', '广东省江门市市辖区', 'gdsjmssxq');
INSERT INTO `t_system_region` VALUES ('6574', '440703', '蓬江区', '440703', '广东省江门市蓬江区', 'gdsjmspjq');
INSERT INTO `t_system_region` VALUES ('6575', '440704', '江海区', '440704', '广东省江门市江海区', 'gdsjmsjhq');
INSERT INTO `t_system_region` VALUES ('6576', '440705', '新会区', '440705', '广东省江门市新会区', 'gdsjmsxhq');
INSERT INTO `t_system_region` VALUES ('6577', '440781', '台山市', '440781', '广东省江门市台山市', 'gdsjmstss');
INSERT INTO `t_system_region` VALUES ('6578', '440783', '开平市', '440783', '广东省江门市开平市', 'gdsjmskps');
INSERT INTO `t_system_region` VALUES ('6579', '440784', '鹤山市', '440784', '广东省江门市鹤山市', 'gdsjmshss');
INSERT INTO `t_system_region` VALUES ('6580', '440785', '恩平市', '440785', '广东省江门市恩平市', 'gdsjmseps');
INSERT INTO `t_system_region` VALUES ('6581', '4408', '湛江市', '440800', '广东省湛江市', 'gdszjs');
INSERT INTO `t_system_region` VALUES ('6582', '440801', '市辖区', '440801', '广东省湛江市市辖区', 'gdszjssxq');
INSERT INTO `t_system_region` VALUES ('6583', '440802', '赤坎区', '440802', '广东省湛江市赤坎区', 'gdszjsckq');
INSERT INTO `t_system_region` VALUES ('6584', '440803', '霞山区', '440803', '广东省湛江市霞山区', 'gdszjsxsq');
INSERT INTO `t_system_region` VALUES ('6585', '440804', '坡头区', '440804', '广东省湛江市坡头区', 'gdszjsptq');
INSERT INTO `t_system_region` VALUES ('6586', '440805', '东海区', '440805', '广东省湛江市东海区', 'gdszjsdhq');
INSERT INTO `t_system_region` VALUES ('6587', '440806', '开发区', '440806', '广东省湛江市开发区', 'gdszjskfq');
INSERT INTO `t_system_region` VALUES ('6588', '440811', '麻章区', '440811', '广东省湛江市麻章区', 'gdszjsmzq');
INSERT INTO `t_system_region` VALUES ('6589', '440823', '遂溪县', '440823', '广东省湛江市遂溪县', 'gdszjssxx');
INSERT INTO `t_system_region` VALUES ('6590', '440825', '徐闻县', '440825', '广东省湛江市徐闻县', 'gdszjsxwx');
INSERT INTO `t_system_region` VALUES ('6591', '440830', '湛江农垦', '440830', '广东省湛江市湛江农垦', 'gdszjszjnk');
INSERT INTO `t_system_region` VALUES ('6592', '440881', '廉江市', '440881', '广东省湛江市廉江市', 'gdszjsljs');
INSERT INTO `t_system_region` VALUES ('6593', '440882', '雷州市', '440882', '广东省湛江市雷州市', 'gdszjslzs');
INSERT INTO `t_system_region` VALUES ('6594', '440883', '吴川市', '440883', '广东省湛江市吴川市', 'gdszjswcs');
INSERT INTO `t_system_region` VALUES ('6595', '4409', '茂名市', '440900', '广东省茂名市', 'gdsmms');
INSERT INTO `t_system_region` VALUES ('6596', '440901', '市辖区', '440901', '广东省茂名市市辖区', 'gdsmmssxq');
INSERT INTO `t_system_region` VALUES ('6597', '440902', '茂南区', '440902', '广东省茂名市茂南区', 'gdsmmsmnq');
INSERT INTO `t_system_region` VALUES ('6598', '440903', '茂港区', '440903', '广东省茂名市茂港区', 'gdsmmsmgq');
INSERT INTO `t_system_region` VALUES ('6599', '440923', '电白县', '440923', '广东省茂名市电白县', 'gdsmmsdbx');
INSERT INTO `t_system_region` VALUES ('6600', '440981', '高州市', '440981', '广东省茂名市高州市', 'gdsmmsgzs');
INSERT INTO `t_system_region` VALUES ('6601', '440982', '化州市', '440982', '广东省茂名市化州市', 'gdsmmshzs');
INSERT INTO `t_system_region` VALUES ('6602', '440983', '信宜市', '440983', '广东省茂名市信宜市', 'gdsmmsxys');
INSERT INTO `t_system_region` VALUES ('6603', '4412', '肇庆市', '441200', '广东省肇庆市', 'gdszqs');
INSERT INTO `t_system_region` VALUES ('6604', '441201', '市辖区', '441201', '广东省肇庆市市辖区', 'gdszqssxq');
INSERT INTO `t_system_region` VALUES ('6605', '441202', '端州区', '441202', '广东省肇庆市端州区', 'gdszqsdzq');
INSERT INTO `t_system_region` VALUES ('6606', '441203', '鼎湖区', '441203', '广东省肇庆市鼎湖区', 'gdszqsdhq');
INSERT INTO `t_system_region` VALUES ('6607', '441223', '广宁县', '441223', '广东省肇庆市广宁县', 'gdszqsgnx');
INSERT INTO `t_system_region` VALUES ('6608', '441224', '怀集县', '441224', '广东省肇庆市怀集县', 'gdszqshjx');
INSERT INTO `t_system_region` VALUES ('6609', '441225', '封开县', '441225', '广东省肇庆市封开县', 'gdszqsfkx');
INSERT INTO `t_system_region` VALUES ('6610', '441226', '德庆县', '441226', '广东省肇庆市德庆县', 'gdszqsdqx');
INSERT INTO `t_system_region` VALUES ('6611', '441227', '高新区', '441227', '广东省肇庆市高新区', 'gdszqsgxq');
INSERT INTO `t_system_region` VALUES ('6612', '441283', '高要市', '441283', '广东省肇庆市高要市', 'gdszqsgys');
INSERT INTO `t_system_region` VALUES ('6613', '441284', '四会市', '441284', '广东省肇庆市四会市', 'gdszqsshs');
INSERT INTO `t_system_region` VALUES ('6614', '4413', '惠州市', '441300', '广东省惠州市', 'gdshzs');
INSERT INTO `t_system_region` VALUES ('6615', '441301', '市辖区', '441301', '广东省惠州市市辖区', 'gdshzssxq');
INSERT INTO `t_system_region` VALUES ('6616', '441302', '惠城区', '441302', '广东省惠州市惠城区', 'gdshzshcq');
INSERT INTO `t_system_region` VALUES ('6617', '441303', '惠阳区', '441303', '广东省惠州市惠阳区', 'gdshzshyq');
INSERT INTO `t_system_region` VALUES ('6618', '441322', '博罗县', '441322', '广东省惠州市博罗县', 'gdshzsblx');
INSERT INTO `t_system_region` VALUES ('6619', '441323', '惠东县', '441323', '广东省惠州市惠东县', 'gdshzshdx');
INSERT INTO `t_system_region` VALUES ('6620', '441324', '龙门县', '441324', '广东省惠州市龙门县', 'gdshzslmx');
INSERT INTO `t_system_region` VALUES ('6621', '4414', '梅州市', '441400', '广东省梅州市', 'gdsmzs');
INSERT INTO `t_system_region` VALUES ('6622', '441401', '市辖区', '441401', '广东省梅州市市辖区', 'gdsmzssxq');
INSERT INTO `t_system_region` VALUES ('6623', '441402', '梅江区', '441402', '广东省梅州市梅江区', 'gdsmzsmjq');
INSERT INTO `t_system_region` VALUES ('6624', '441421', '梅县', '441421', '广东省梅州市梅县', 'gdsmzsmx');
INSERT INTO `t_system_region` VALUES ('6625', '441422', '大埔县', '441422', '广东省梅州市大埔县', 'gdsmzsdpx');
INSERT INTO `t_system_region` VALUES ('6626', '441423', '丰顺县', '441423', '广东省梅州市丰顺县', 'gdsmzsfsx');
INSERT INTO `t_system_region` VALUES ('6627', '441424', '五华县', '441424', '广东省梅州市五华县', 'gdsmzswhx');
INSERT INTO `t_system_region` VALUES ('6628', '441426', '平远县', '441426', '广东省梅州市平远县', 'gdsmzspyx');
INSERT INTO `t_system_region` VALUES ('6629', '441427', '蕉岭县', '441427', '广东省梅州市蕉岭县', 'gdsmzsjlx');
INSERT INTO `t_system_region` VALUES ('6630', '441481', '兴宁市', '441481', '广东省梅州市兴宁市', 'gdsmzsxns');
INSERT INTO `t_system_region` VALUES ('6631', '4415', '汕尾市', '441500', '广东省汕尾市', 'gdssws');
INSERT INTO `t_system_region` VALUES ('6632', '441501', '市辖区', '441501', '广东省汕尾市市辖区', 'gdsswssxq');
INSERT INTO `t_system_region` VALUES ('6633', '441502', '城区', '441502', '广东省汕尾市城区', 'gdsswscq');
INSERT INTO `t_system_region` VALUES ('6634', '441503', '红海湾区', '441503', '广东省汕尾市红海湾区', 'gdsswshhwq');
INSERT INTO `t_system_region` VALUES ('6635', '441521', '海丰县', '441521', '广东省汕尾市海丰县', 'gdsswshfx');
INSERT INTO `t_system_region` VALUES ('6636', '441523', '陆河县', '441523', '广东省汕尾市陆河县', 'gdsswslhx');
INSERT INTO `t_system_region` VALUES ('6637', '441581', '陆丰市', '441581', '广东省汕尾市陆丰市', 'gdsswslfs');
INSERT INTO `t_system_region` VALUES ('6638', '4416', '河源市', '441600', '广东省河源市', 'gdshys');
INSERT INTO `t_system_region` VALUES ('6639', '441601', '市辖区', '441601', '广东省河源市市辖区', 'gdshyssxq');
INSERT INTO `t_system_region` VALUES ('6640', '441602', '源城区', '441602', '广东省河源市源城区', 'gdshysycq');
INSERT INTO `t_system_region` VALUES ('6641', '441621', '紫金县', '441621', '广东省河源市紫金县', 'gdshyszjx');
INSERT INTO `t_system_region` VALUES ('6642', '441622', '龙川县', '441622', '广东省河源市龙川县', 'gdshyslcx');
INSERT INTO `t_system_region` VALUES ('6643', '441623', '连平县', '441623', '广东省河源市连平县', 'gdshyslpx');
INSERT INTO `t_system_region` VALUES ('6644', '441624', '和平县', '441624', '广东省河源市和平县', 'gdshyshpx');
INSERT INTO `t_system_region` VALUES ('6645', '441625', '东源县', '441625', '广东省河源市东源县', 'gdshysdyx');
INSERT INTO `t_system_region` VALUES ('6646', '4417', '阳江市', '441700', '广东省阳江市', 'gdsyjs');
INSERT INTO `t_system_region` VALUES ('6647', '441701', '市辖区', '441701', '广东省阳江市市辖区', 'gdsyjssxq');
INSERT INTO `t_system_region` VALUES ('6648', '441702', '江城区', '441702', '广东省阳江市江城区', 'gdsyjsjcq');
INSERT INTO `t_system_region` VALUES ('6649', '441703', '海陵岛试验区', '441703', '广东省阳江市海陵岛试验区', 'gdsyjshldsyq');
INSERT INTO `t_system_region` VALUES ('6650', '441705', '阳江农垦局', '441705', '广东省阳江市阳江农垦局', 'gdsyjsyjnkj');
INSERT INTO `t_system_region` VALUES ('6651', '441706', '阳江高新区', '441706', '广东省阳江市阳江高新区', 'gdsyjsyjgxq');
INSERT INTO `t_system_region` VALUES ('6652', '441721', '阳西县', '441721', '广东省阳江市阳西县', 'gdsyjsyxx');
INSERT INTO `t_system_region` VALUES ('6653', '441723', '阳东县', '441723', '广东省阳江市阳东县', 'gdsyjsydx');
INSERT INTO `t_system_region` VALUES ('6654', '441781', '阳春市', '441781', '广东省阳江市阳春市', 'gdsyjsycs');
INSERT INTO `t_system_region` VALUES ('6655', '4418', '清远市', '441800', '广东省清远市', 'gdsqys');
INSERT INTO `t_system_region` VALUES ('6656', '441801', '市辖区', '441801', '广东省清远市市辖区', 'gdsqyssxq');
INSERT INTO `t_system_region` VALUES ('6657', '441802', '清城区', '441802', '广东省清远市清城区', 'gdsqysqcq');
INSERT INTO `t_system_region` VALUES ('6658', '441821', '佛冈县', '441821', '广东省清远市佛冈县', 'gdsqysfgx');
INSERT INTO `t_system_region` VALUES ('6659', '441823', '阳山县', '441823', '广东省清远市阳山县', 'gdsqysysx');
INSERT INTO `t_system_region` VALUES ('6660', '441825', '连山壮族瑶族自治县', '441825', '广东省清远市连山壮族瑶族自治县', 'gdsqyslszzyzzzx');
INSERT INTO `t_system_region` VALUES ('6661', '441826', '连南瑶族自治县', '441826', '广东省清远市连南瑶族自治县', 'gdsqyslnyzzzx');
INSERT INTO `t_system_region` VALUES ('6662', '441827', '清新县', '441827', '广东省清远市清新县', 'gdsqysqxx');
INSERT INTO `t_system_region` VALUES ('6663', '441881', '英德市', '441881', '广东省清远市英德市', 'gdsqysyds');
INSERT INTO `t_system_region` VALUES ('6664', '441882', '连州市', '441882', '广东省清远市连州市', 'gdsqyslzs');
INSERT INTO `t_system_region` VALUES ('6665', '4419', '东莞市', '441900', '广东省东莞市', 'gdsdgs');
INSERT INTO `t_system_region` VALUES ('6666', '441901', '东莞市辖区', '441901', '广东省东莞市东莞东莞市辖区', 'gdsdgsdgdgsxq');
INSERT INTO `t_system_region` VALUES ('6667', '4420', '中山市', '442000', '广东省中山市', 'gdszss');
INSERT INTO `t_system_region` VALUES ('6668', '442001', '中山市辖区', '442001', '广东省中山市中山中山市辖区', 'gdszsszszssxq');
INSERT INTO `t_system_region` VALUES ('6669', '4451', '潮州市', '445100', '广东省潮州市', 'gdsczs');
INSERT INTO `t_system_region` VALUES ('6670', '445101', '市辖区', '445101', '广东省潮州市市辖区', 'gdsczssxq');
INSERT INTO `t_system_region` VALUES ('6671', '445102', '湘桥区', '445102', '广东省潮州市湘桥区', 'gdsczsxqq');
INSERT INTO `t_system_region` VALUES ('6672', '445103', '枫溪区', '445103', '广东省潮州市枫溪区', 'gdsczsfxq');
INSERT INTO `t_system_region` VALUES ('6673', '445121', '潮安县', '445121', '广东省潮州市潮安县', 'gdsczscax');
INSERT INTO `t_system_region` VALUES ('6674', '445122', '饶平县', '445122', '广东省潮州市饶平县', 'gdsczsrpx');
INSERT INTO `t_system_region` VALUES ('6675', '4452', '揭阳市', '445200', '广东省揭阳市', 'gdsjys');
INSERT INTO `t_system_region` VALUES ('6676', '445201', '市辖区', '445201', '广东省揭阳市市辖区', 'gdsjyssxq');
INSERT INTO `t_system_region` VALUES ('6677', '445202', '榕城区', '445202', '广东省揭阳市榕城区', 'gdsjysrcq');
INSERT INTO `t_system_region` VALUES ('6678', '445221', '揭东县', '445221', '广东省揭阳市揭东县', 'gdsjysjdx');
INSERT INTO `t_system_region` VALUES ('6679', '445222', '揭西县', '445222', '广东省揭阳市揭西县', 'gdsjysjxx');
INSERT INTO `t_system_region` VALUES ('6680', '445224', '惠来县', '445224', '广东省揭阳市惠来县', 'gdsjyshlx');
INSERT INTO `t_system_region` VALUES ('6681', '445281', '普宁市', '445281', '广东省揭阳市普宁市', 'gdsjyspns');
INSERT INTO `t_system_region` VALUES ('6682', '4453', '云浮市', '445300', '广东省云浮市', 'gdsyfs');
INSERT INTO `t_system_region` VALUES ('6683', '445301', '市辖区', '445301', '广东省云浮市市辖区', 'gdsyfssxq');
INSERT INTO `t_system_region` VALUES ('6684', '445302', '云城区', '445302', '广东省云浮市云城区', 'gdsyfsycq');
INSERT INTO `t_system_region` VALUES ('6685', '445321', '新兴县', '445321', '广东省云浮市新兴县', 'gdsyfsxxx');
INSERT INTO `t_system_region` VALUES ('6686', '445322', '郁南县', '445322', '广东省云浮市郁南县', 'gdsyfsynx');
INSERT INTO `t_system_region` VALUES ('6687', '445323', '云安县', '445323', '广东省云浮市云安县', 'gdsyfsyax');
INSERT INTO `t_system_region` VALUES ('6688', '445381', '罗定市', '445381', '广东省云浮市罗定市', 'gdsyfslds');
INSERT INTO `t_system_region` VALUES ('6689', '45', '广西壮族自治区', '450000', '广西壮族自治区', 'gxzzzzq');
INSERT INTO `t_system_region` VALUES ('6690', '4501', '南宁市', '450100', '广西壮族自治区南宁市', 'gxzzzzqnns');
INSERT INTO `t_system_region` VALUES ('6691', '450101', '市辖区', '450101', '广西壮族自治区南宁市市辖区', 'gxzzzzqnnssxq');
INSERT INTO `t_system_region` VALUES ('6692', '450102', '兴宁区', '450102', '广西壮族自治区南宁市兴宁区', 'gxzzzzqnnsxnq');
INSERT INTO `t_system_region` VALUES ('6693', '450103', '青秀区', '450103', '广西壮族自治区南宁市青秀区', 'gxzzzzqnnsqxq');
INSERT INTO `t_system_region` VALUES ('6694', '450105', '江南区', '450105', '广西壮族自治区南宁市江南区', 'gxzzzzqnnsjnq');
INSERT INTO `t_system_region` VALUES ('6695', '450107', '西乡塘区', '450107', '广西壮族自治区南宁市西乡塘区', 'gxzzzzqnnsxxtq');
INSERT INTO `t_system_region` VALUES ('6696', '450108', '良庆区', '450108', '广西壮族自治区南宁市良庆区', 'gxzzzzqnnslqq');
INSERT INTO `t_system_region` VALUES ('6697', '450109', '邕宁区', '450109', '广西壮族自治区南宁市邕宁区', 'gxzzzzqnnsynq');
INSERT INTO `t_system_region` VALUES ('6698', '450122', '武鸣县', '450122', '广西壮族自治区南宁市武鸣县', 'gxzzzzqnnswmx');
INSERT INTO `t_system_region` VALUES ('6699', '450123', '隆安县', '450123', '广西壮族自治区南宁市隆安县', 'gxzzzzqnnslax');
INSERT INTO `t_system_region` VALUES ('6700', '450124', '马山县', '450124', '广西壮族自治区南宁市马山县', 'gxzzzzqnnsmsx');
INSERT INTO `t_system_region` VALUES ('6701', '450125', '上林县', '450125', '广西壮族自治区南宁市上林县', 'gxzzzzqnnsslx');
INSERT INTO `t_system_region` VALUES ('6702', '450126', '宾阳县', '450126', '广西壮族自治区南宁市宾阳县', 'gxzzzzqnnsbyx');
INSERT INTO `t_system_region` VALUES ('6703', '450127', '横县', '450127', '广西壮族自治区南宁市横县', 'gxzzzzqnnshx');
INSERT INTO `t_system_region` VALUES ('6704', '4502', '柳州市', '450200', '广西壮族自治区柳州市', 'gxzzzzqlzs');
INSERT INTO `t_system_region` VALUES ('6705', '450201', '市辖区', '450201', '广西壮族自治区柳州市市辖区', 'gxzzzzqlzssxq');
INSERT INTO `t_system_region` VALUES ('6706', '450202', '城中区', '450202', '广西壮族自治区柳州市城中区', 'gxzzzzqlzsczq');
INSERT INTO `t_system_region` VALUES ('6707', '450203', '鱼峰区', '450203', '广西壮族自治区柳州市鱼峰区', 'gxzzzzqlzsyfq');
INSERT INTO `t_system_region` VALUES ('6708', '450204', '柳南区', '450204', '广西壮族自治区柳州市柳南区', 'gxzzzzqlzslnq');
INSERT INTO `t_system_region` VALUES ('6709', '450205', '柳北区', '450205', '广西壮族自治区柳州市柳北区', 'gxzzzzqlzslbq');
INSERT INTO `t_system_region` VALUES ('6710', '450221', '柳江县', '450221', '广西壮族自治区柳州市柳江县', 'gxzzzzqlzsljx');
INSERT INTO `t_system_region` VALUES ('6711', '450222', '柳城县', '450222', '广西壮族自治区柳州市柳城县', 'gxzzzzqlzslcx');
INSERT INTO `t_system_region` VALUES ('6712', '450223', '鹿寨县', '450223', '广西壮族自治区柳州市鹿寨县', 'gxzzzzqlzslzx');
INSERT INTO `t_system_region` VALUES ('6713', '450224', '融安县', '450224', '广西壮族自治区柳州市融安县', 'gxzzzzqlzsrax');
INSERT INTO `t_system_region` VALUES ('6714', '450225', '融水苗族自治县', '450225', '广西壮族自治区柳州市融水苗族自治县', 'gxzzzzqlzsrsmzzzx');
INSERT INTO `t_system_region` VALUES ('6715', '450226', '三江侗族自治县', '450226', '广西壮族自治区柳州市三江侗族自治县', 'gxzzzzqlzssjdzzzx');
INSERT INTO `t_system_region` VALUES ('6716', '4503', '桂林市', '450300', '广西壮族自治区桂林市', 'gxzzzzqgls');
INSERT INTO `t_system_region` VALUES ('6717', '450301', '市辖区', '450301', '广西壮族自治区桂林市市辖区', 'gxzzzzqglssxq');
INSERT INTO `t_system_region` VALUES ('6718', '450302', '秀峰区', '450302', '广西壮族自治区桂林市秀峰区', 'gxzzzzqglsxfq');
INSERT INTO `t_system_region` VALUES ('6719', '450303', '叠彩区', '450303', '广西壮族自治区桂林市叠彩区', 'gxzzzzqglsdcq');
INSERT INTO `t_system_region` VALUES ('6720', '450304', '象山区', '450304', '广西壮族自治区桂林市象山区', 'gxzzzzqglsxsq');
INSERT INTO `t_system_region` VALUES ('6721', '450305', '七星区', '450305', '广西壮族自治区桂林市七星区', 'gxzzzzqglsqxq');
INSERT INTO `t_system_region` VALUES ('6722', '450311', '雁山区', '450311', '广西壮族自治区桂林市雁山区', 'gxzzzzqglsysq');
INSERT INTO `t_system_region` VALUES ('6723', '450321', '阳朔县', '450321', '广西壮族自治区桂林市阳朔县', 'gxzzzzqglsysx');
INSERT INTO `t_system_region` VALUES ('6724', '450322', '临桂县', '450322', '广西壮族自治区桂林市临桂县', 'gxzzzzqglslgx');
INSERT INTO `t_system_region` VALUES ('6725', '450323', '灵川县', '450323', '广西壮族自治区桂林市灵川县', 'gxzzzzqglslcx');
INSERT INTO `t_system_region` VALUES ('6726', '450324', '全州县', '450324', '广西壮族自治区桂林市全州县', 'gxzzzzqglsqzx');
INSERT INTO `t_system_region` VALUES ('6727', '450325', '兴安县', '450325', '广西壮族自治区桂林市兴安县', 'gxzzzzqglsxax');
INSERT INTO `t_system_region` VALUES ('6728', '450326', '永福县', '450326', '广西壮族自治区桂林市永福县', 'gxzzzzqglsyfx');
INSERT INTO `t_system_region` VALUES ('6729', '450327', '灌阳县', '450327', '广西壮族自治区桂林市灌阳县', 'gxzzzzqglsgyx');
INSERT INTO `t_system_region` VALUES ('6730', '450328', '龙胜各族自治县', '450328', '广西壮族自治区桂林市龙胜各族自治县', 'gxzzzzqglslsgzzzx');
INSERT INTO `t_system_region` VALUES ('6731', '450329', '资源县', '450329', '广西壮族自治区桂林市资源县', 'gxzzzzqglszyx');
INSERT INTO `t_system_region` VALUES ('6732', '450330', '平乐县', '450330', '广西壮族自治区桂林市平乐县', 'gxzzzzqglsplx');
INSERT INTO `t_system_region` VALUES ('6733', '450331', '荔蒲县', '450331', '广西壮族自治区桂林市荔蒲县', 'gxzzzzqglslpx');
INSERT INTO `t_system_region` VALUES ('6734', '450332', '恭城瑶族自治县', '450332', '广西壮族自治区桂林市恭城瑶族自治县', 'gxzzzzqglsgcyzzzx');
INSERT INTO `t_system_region` VALUES ('6735', '4504', '梧州市', '450400', '广西壮族自治区梧州市', 'gxzzzzqwzs');
INSERT INTO `t_system_region` VALUES ('6736', '450401', '市辖区', '450401', '广西壮族自治区梧州市市辖区', 'gxzzzzqwzssxq');
INSERT INTO `t_system_region` VALUES ('6737', '450403', '万秀区', '450403', '广西壮族自治区梧州市万秀区', 'gxzzzzqwzswxq');
INSERT INTO `t_system_region` VALUES ('6738', '450404', '蝶山区', '450404', '广西壮族自治区梧州市蝶山区', 'gxzzzzqwzsdsq');
INSERT INTO `t_system_region` VALUES ('6739', '450405', '长洲区', '450405', '广西壮族自治区梧州市长洲区', 'gxzzzzqwzszzq');
INSERT INTO `t_system_region` VALUES ('6740', '450421', '苍梧县', '450421', '广西壮族自治区梧州市苍梧县', 'gxzzzzqwzscwx');
INSERT INTO `t_system_region` VALUES ('6741', '450422', '藤县', '450422', '广西壮族自治区梧州市藤县', 'gxzzzzqwzstx');
INSERT INTO `t_system_region` VALUES ('6742', '450423', '蒙山县', '450423', '广西壮族自治区梧州市蒙山县', 'gxzzzzqwzsmsx');
INSERT INTO `t_system_region` VALUES ('6743', '450481', '岑溪市', '450481', '广西壮族自治区梧州市岑溪市', 'gxzzzzqwzscxs');
INSERT INTO `t_system_region` VALUES ('6744', '4505', '北海市', '450500', '广西壮族自治区北海市', 'gxzzzzqbhs');
INSERT INTO `t_system_region` VALUES ('6745', '450501', '市辖区', '450501', '广西壮族自治区北海市市辖区', 'gxzzzzqbhssxq');
INSERT INTO `t_system_region` VALUES ('6746', '450502', '海城区', '450502', '广西壮族自治区北海市海城区', 'gxzzzzqbhshcq');
INSERT INTO `t_system_region` VALUES ('6747', '450503', '银海区', '450503', '广西壮族自治区北海市银海区', 'gxzzzzqbhsyhq');
INSERT INTO `t_system_region` VALUES ('6748', '450512', '铁山港区', '450512', '广西壮族自治区北海市铁山港区', 'gxzzzzqbhstsgq');
INSERT INTO `t_system_region` VALUES ('6749', '450521', '合浦县', '450521', '广西壮族自治区北海市合浦县', 'gxzzzzqbhshpx');
INSERT INTO `t_system_region` VALUES ('6750', '4506', '防城港市', '450600', '广西壮族自治区防城港市', 'gxzzzzqfcgs');
INSERT INTO `t_system_region` VALUES ('6751', '450601', '市辖区', '450601', '广西壮族自治区防城港市市辖区', 'gxzzzzqfcgssxq');
INSERT INTO `t_system_region` VALUES ('6752', '450602', '港口区', '450602', '广西壮族自治区防城港市港口区', 'gxzzzzqfcgsgkq');
INSERT INTO `t_system_region` VALUES ('6753', '450603', '防城区', '450603', '广西壮族自治区防城港市防城区', 'gxzzzzqfcgsfcq');
INSERT INTO `t_system_region` VALUES ('6754', '450621', '上思县', '450621', '广西壮族自治区防城港市上思县', 'gxzzzzqfcgsssx');
INSERT INTO `t_system_region` VALUES ('6755', '450681', '东兴市', '450681', '广西壮族自治区防城港市东兴市', 'gxzzzzqfcgsdxs');
INSERT INTO `t_system_region` VALUES ('6756', '4507', '钦州市', '450700', '广西壮族自治区钦州市', 'gxzzzzqqzs');
INSERT INTO `t_system_region` VALUES ('6757', '450701', '市辖区', '450701', '广西壮族自治区钦州市市辖区', 'gxzzzzqqzssxq');
INSERT INTO `t_system_region` VALUES ('6758', '450702', '钦南区', '450702', '广西壮族自治区钦州市钦南区', 'gxzzzzqqzsqnq');
INSERT INTO `t_system_region` VALUES ('6759', '450703', '钦北区', '450703', '广西壮族自治区钦州市钦北区', 'gxzzzzqqzsqbq');
INSERT INTO `t_system_region` VALUES ('6760', '450721', '灵山县', '450721', '广西壮族自治区钦州市灵山县', 'gxzzzzqqzslsx');
INSERT INTO `t_system_region` VALUES ('6761', '450722', '浦北县', '450722', '广西壮族自治区钦州市浦北县', 'gxzzzzqqzspbx');
INSERT INTO `t_system_region` VALUES ('6762', '4508', '贵港市', '450800', '广西壮族自治区贵港市', 'gxzzzzqggs');
INSERT INTO `t_system_region` VALUES ('6763', '450801', '市辖区', '450801', '广西壮族自治区贵港市市辖区', 'gxzzzzqggssxq');
INSERT INTO `t_system_region` VALUES ('6764', '450802', '港北区', '450802', '广西壮族自治区贵港市港北区', 'gxzzzzqggsgbq');
INSERT INTO `t_system_region` VALUES ('6765', '450803', '港南区', '450803', '广西壮族自治区贵港市港南区', 'gxzzzzqggsgnq');
INSERT INTO `t_system_region` VALUES ('6766', '450804', '覃塘区', '450804', '广西壮族自治区贵港市覃塘区', 'gxzzzzqggsttq');
INSERT INTO `t_system_region` VALUES ('6767', '450821', '平南县', '450821', '广西壮族自治区贵港市平南县', 'gxzzzzqggspnx');
INSERT INTO `t_system_region` VALUES ('6768', '450881', '桂平市', '450881', '广西壮族自治区贵港市桂平市', 'gxzzzzqggsgps');
INSERT INTO `t_system_region` VALUES ('6769', '4509', '玉林市', '450900', '广西壮族自治区玉林市', 'gxzzzzqyls');
INSERT INTO `t_system_region` VALUES ('6770', '450901', '市辖区', '450901', '广西壮族自治区玉林市市辖区', 'gxzzzzqylssxq');
INSERT INTO `t_system_region` VALUES ('6771', '450902', '玉州区', '450902', '广西壮族自治区玉林市玉州区', 'gxzzzzqylsyzq');
INSERT INTO `t_system_region` VALUES ('6772', '450921', '容县', '450921', '广西壮族自治区玉林市容县', 'gxzzzzqylsrx');
INSERT INTO `t_system_region` VALUES ('6773', '450922', '陆川县', '450922', '广西壮族自治区玉林市陆川县', 'gxzzzzqylslcx');
INSERT INTO `t_system_region` VALUES ('6774', '450923', '博白县', '450923', '广西壮族自治区玉林市博白县', 'gxzzzzqylsbbx');
INSERT INTO `t_system_region` VALUES ('6775', '450924', '兴业县', '450924', '广西壮族自治区玉林市兴业县', 'gxzzzzqylsxyx');
INSERT INTO `t_system_region` VALUES ('6776', '450981', '北流市', '450981', '广西壮族自治区玉林市北流市', 'gxzzzzqylsbls');
INSERT INTO `t_system_region` VALUES ('6777', '4510', '百色市', '451000', '广西壮族自治区百色市', 'gxzzzzqbss');
INSERT INTO `t_system_region` VALUES ('6778', '451001', '市辖区', '451001', '广西壮族自治区百色市市辖区', 'gxzzzzqbsssxq');
INSERT INTO `t_system_region` VALUES ('6779', '451002', '右江区', '451002', '广西壮族自治区百色市右江区', 'gxzzzzqbssyjq');
INSERT INTO `t_system_region` VALUES ('6780', '451021', '田阳县', '451021', '广西壮族自治区百色市田阳县', 'gxzzzzqbsstyx');
INSERT INTO `t_system_region` VALUES ('6781', '451022', '田东县', '451022', '广西壮族自治区百色市田东县', 'gxzzzzqbsstdx');
INSERT INTO `t_system_region` VALUES ('6782', '451023', '平果县', '451023', '广西壮族自治区百色市平果县', 'gxzzzzqbsspgx');
INSERT INTO `t_system_region` VALUES ('6783', '451024', '德保县', '451024', '广西壮族自治区百色市德保县', 'gxzzzzqbssdbx');
INSERT INTO `t_system_region` VALUES ('6784', '451025', '靖西县', '451025', '广西壮族自治区百色市靖西县', 'gxzzzzqbssjxx');
INSERT INTO `t_system_region` VALUES ('6785', '451026', '那坡县', '451026', '广西壮族自治区百色市那坡县', 'gxzzzzqbssnpx');
INSERT INTO `t_system_region` VALUES ('6786', '451027', '凌云县', '451027', '广西壮族自治区百色市凌云县', 'gxzzzzqbsslyx');
INSERT INTO `t_system_region` VALUES ('6787', '451028', '乐业县', '451028', '广西壮族自治区百色市乐业县', 'gxzzzzqbsslyx');
INSERT INTO `t_system_region` VALUES ('6788', '451029', '田林县', '451029', '广西壮族自治区百色市田林县', 'gxzzzzqbsstlx');
INSERT INTO `t_system_region` VALUES ('6789', '451030', '西林县', '451030', '广西壮族自治区百色市西林县', 'gxzzzzqbssxlx');
INSERT INTO `t_system_region` VALUES ('6790', '451031', '隆林各族自治县', '451031', '广西壮族自治区百色市隆林各族自治县', 'gxzzzzqbssllgzzzx');
INSERT INTO `t_system_region` VALUES ('6791', '4511', '贺州市', '451100', '广西壮族自治区贺州市', 'gxzzzzqhzs');
INSERT INTO `t_system_region` VALUES ('6792', '451101', '市辖区', '451101', '广西壮族自治区贺州市市辖区', 'gxzzzzqhzssxq');
INSERT INTO `t_system_region` VALUES ('6793', '451102', '八步区', '451102', '广西壮族自治区贺州市八步区', 'gxzzzzqhzsbbq');
INSERT INTO `t_system_region` VALUES ('6794', '451121', '昭平县', '451121', '广西壮族自治区贺州市昭平县', 'gxzzzzqhzszpx');
INSERT INTO `t_system_region` VALUES ('6795', '451122', '钟山县', '451122', '广西壮族自治区贺州市钟山县', 'gxzzzzqhzszsx');
INSERT INTO `t_system_region` VALUES ('6796', '451123', '富川瑶族自治县', '451123', '广西壮族自治区贺州市富川瑶族自治县', 'gxzzzzqhzsfcyzzzx');
INSERT INTO `t_system_region` VALUES ('6797', '4512', '河池市', '451200', '广西壮族自治区河池市', 'gxzzzzqhcs');
INSERT INTO `t_system_region` VALUES ('6798', '451201', '市辖区', '451201', '广西壮族自治区河池市市辖区', 'gxzzzzqhcssxq');
INSERT INTO `t_system_region` VALUES ('6799', '451202', '金城江区', '451202', '广西壮族自治区河池市金城江区', 'gxzzzzqhcsjcjq');
INSERT INTO `t_system_region` VALUES ('6800', '451221', '南丹县', '451221', '广西壮族自治区河池市南丹县', 'gxzzzzqhcsndx');
INSERT INTO `t_system_region` VALUES ('6801', '451222', '天峨县', '451222', '广西壮族自治区河池市天峨县', 'gxzzzzqhcstex');
INSERT INTO `t_system_region` VALUES ('6802', '451223', '凤山县', '451223', '广西壮族自治区河池市凤山县', 'gxzzzzqhcsfsx');
INSERT INTO `t_system_region` VALUES ('6803', '451224', '东兰县', '451224', '广西壮族自治区河池市东兰县', 'gxzzzzqhcsdlx');
INSERT INTO `t_system_region` VALUES ('6804', '451225', '罗城仫佬族自治县', '451225', '广西壮族自治区河池市罗城仫佬族自治县', 'gxzzzzqhcslcmlzzzx');
INSERT INTO `t_system_region` VALUES ('6805', '451226', '环江毛南族自治县', '451226', '广西壮族自治区河池市环江毛南族自治县', 'gxzzzzqhcshjmnzzzx');
INSERT INTO `t_system_region` VALUES ('6806', '451227', '巴马瑶族自治县', '451227', '广西壮族自治区河池市巴马瑶族自治县', 'gxzzzzqhcsbmyzzzx');
INSERT INTO `t_system_region` VALUES ('6807', '451228', '都安瑶族自治县', '451228', '广西壮族自治区河池市都安瑶族自治县', 'gxzzzzqhcsdayzzzx');
INSERT INTO `t_system_region` VALUES ('6808', '451229', '大化瑶族自治县', '451229', '广西壮族自治区河池市大化瑶族自治县', 'gxzzzzqhcsdhyzzzx');
INSERT INTO `t_system_region` VALUES ('6809', '451281', '宜州市', '451281', '广西壮族自治区河池市宜州市', 'gxzzzzqhcsyzs');
INSERT INTO `t_system_region` VALUES ('6810', '4513', '来宾市', '451300', '广西壮族自治区来宾市', 'gxzzzzqlbs');
INSERT INTO `t_system_region` VALUES ('6811', '451301', '市辖区', '451301', '广西壮族自治区来宾市市辖区', 'gxzzzzqlbssxq');
INSERT INTO `t_system_region` VALUES ('6812', '451302', '兴宾区', '451302', '广西壮族自治区来宾市兴宾区', 'gxzzzzqlbsxbq');
INSERT INTO `t_system_region` VALUES ('6813', '451321', '忻城县', '451321', '广西壮族自治区来宾市忻城县', 'gxzzzzqlbsxcx');
INSERT INTO `t_system_region` VALUES ('6814', '451322', '象州县', '451322', '广西壮族自治区来宾市象州县', 'gxzzzzqlbsxzx');
INSERT INTO `t_system_region` VALUES ('6815', '451323', '武宣县', '451323', '广西壮族自治区来宾市武宣县', 'gxzzzzqlbswxx');
INSERT INTO `t_system_region` VALUES ('6816', '451324', '金秀瑶族自治县', '451324', '广西壮族自治区来宾市金秀瑶族自治县', 'gxzzzzqlbsjxyzzzx');
INSERT INTO `t_system_region` VALUES ('6817', '451381', '合山市', '451381', '广西壮族自治区来宾市合山市', 'gxzzzzqlbshss');
INSERT INTO `t_system_region` VALUES ('6818', '4514', '崇左市', '451400', '广西壮族自治区崇左市', 'gxzzzzqczs');
INSERT INTO `t_system_region` VALUES ('6819', '451401', '市辖区', '451401', '广西壮族自治区崇左市市辖区', 'gxzzzzqczssxq');
INSERT INTO `t_system_region` VALUES ('6820', '451402', '江洲区', '451402', '广西壮族自治区崇左市江洲区', 'gxzzzzqczsjzq');
INSERT INTO `t_system_region` VALUES ('6821', '451421', '扶绥县', '451421', '广西壮族自治区崇左市扶绥县', 'gxzzzzqczsfsx');
INSERT INTO `t_system_region` VALUES ('6822', '451422', '宁明县', '451422', '广西壮族自治区崇左市宁明县', 'gxzzzzqczsnmx');
INSERT INTO `t_system_region` VALUES ('6823', '451423', '龙州县', '451423', '广西壮族自治区崇左市龙州县', 'gxzzzzqczslzx');
INSERT INTO `t_system_region` VALUES ('6824', '451424', '大新县', '451424', '广西壮族自治区崇左市大新县', 'gxzzzzqczsdxx');
INSERT INTO `t_system_region` VALUES ('6825', '451425', '天等县', '451425', '广西壮族自治区崇左市天等县', 'gxzzzzqczstdx');
INSERT INTO `t_system_region` VALUES ('6826', '451481', '凭祥市', '451481', '广西壮族自治区崇左市凭祥市', 'gxzzzzqczspxs');
INSERT INTO `t_system_region` VALUES ('6827', '46', '海南省', '460000', '海南省', 'hns');
INSERT INTO `t_system_region` VALUES ('6828', '4601', '海口市', '460100', '海南省海口市', 'hnshks');
INSERT INTO `t_system_region` VALUES ('6829', '460101', '市辖区', '460101', '海南省海口市市辖区', 'hnshkssxq');
INSERT INTO `t_system_region` VALUES ('6830', '460105', '秀英区', '460105', '海南省海口市秀英区', 'hnshksxyq');
INSERT INTO `t_system_region` VALUES ('6831', '460106', '龙华区', '460106', '海南省海口市龙华区', 'hnshkslhq');
INSERT INTO `t_system_region` VALUES ('6832', '460107', '琼山区', '460107', '海南省海口市琼山区', 'hnshksqsq');
INSERT INTO `t_system_region` VALUES ('6833', '460108', '美兰区', '460108', '海南省海口市美兰区', 'hnshksmlq');
INSERT INTO `t_system_region` VALUES ('6834', '4602', '三亚市', '460200', '海南省三亚市', 'hnssys');
INSERT INTO `t_system_region` VALUES ('6835', '460201', '市辖区', '460201', '海南省三亚市市辖区', 'hnssyssxq');
INSERT INTO `t_system_region` VALUES ('6836', '4690', '省直辖县级行政单位', '469000', '海南省省直辖县级行政单位', 'hnsszxxjxzdw');
INSERT INTO `t_system_region` VALUES ('6837', '469001', '五指山市', '469001', '海南省省直辖县级行政单位五指山市', 'hnsszxxjxzdwwzss');
INSERT INTO `t_system_region` VALUES ('6838', '469002', '琼海市', '469002', '海南省省直辖县级行政单位琼海市', 'hnsszxxjxzdwqhs');
INSERT INTO `t_system_region` VALUES ('6839', '469003', '儋州市', '469003', '海南省省直辖县级行政单位儋州市', 'hnsszxxjxzdwdzs');
INSERT INTO `t_system_region` VALUES ('6840', '469005', '文昌市', '469005', '海南省省直辖县级行政单位文昌市', 'hnsszxxjxzdwwcs');
INSERT INTO `t_system_region` VALUES ('6841', '469006', '万宁市', '469006', '海南省省直辖县级行政单位万宁市', 'hnsszxxjxzdwwns');
INSERT INTO `t_system_region` VALUES ('6842', '469007', '东方市', '469007', '海南省省直辖县级行政单位东方市', 'hnsszxxjxzdwdfs');
INSERT INTO `t_system_region` VALUES ('6843', '469021', '定安县', '469021', '海南省省直辖县级行政单位定安县', 'hnsszxxjxzdwdax');
INSERT INTO `t_system_region` VALUES ('6844', '469022', '屯昌县', '469022', '海南省省直辖县级行政单位屯昌县', 'hnsszxxjxzdwtcx');
INSERT INTO `t_system_region` VALUES ('6845', '469023', '澄迈县', '469023', '海南省省直辖县级行政单位澄迈县', 'hnsszxxjxzdwcmx');
INSERT INTO `t_system_region` VALUES ('6846', '469024', '临高县', '469024', '海南省省直辖县级行政单位临高县', 'hnsszxxjxzdwlgx');
INSERT INTO `t_system_region` VALUES ('6847', '469025', '定安县', '469025', '海南省省直辖县级行政单位定安县', 'hnsszxxjxzdwdax');
INSERT INTO `t_system_region` VALUES ('6848', '469026', '屯昌县', '469026', '海南省省直辖县级行政单位屯昌县', 'hnsszxxjxzdwtcx');
INSERT INTO `t_system_region` VALUES ('6849', '469027', '澄迈县', '469027', '海南省省直辖县级行政单位澄迈县', 'hnsszxxjxzdwcmx');
INSERT INTO `t_system_region` VALUES ('6850', '469028', '临高县', '469028', '海南省省直辖县级行政单位临高县', 'hnsszxxjxzdwlgx');
INSERT INTO `t_system_region` VALUES ('6851', '469029', '保亭黎族苗族自治县', '469029', '海南省省直辖县级行政单位保亭黎族苗族自治县', 'hnsszxxjxzdwbtlzmzzzx');
INSERT INTO `t_system_region` VALUES ('6852', '469030', '白沙黎族自治县', '469030', '海南省省直辖县级行政单位白沙黎族自治县', 'hnsszxxjxzdwbslzzzx');
INSERT INTO `t_system_region` VALUES ('6853', '469031', '昌江黎族自治县', '469031', '海南省省直辖县级行政单位昌江黎族自治县', 'hnsszxxjxzdwcjlzzzx');
INSERT INTO `t_system_region` VALUES ('6854', '469032', '南沙群岛', '469032', '海南省省直辖县级行政单位南沙群岛', 'hnsszxxjxzdwnsqd');
INSERT INTO `t_system_region` VALUES ('6855', '469033', '乐东黎族自治县', '469033', '海南省省直辖县级行政单位乐东黎族自治县', 'hnsszxxjxzdwldlzzzx');
INSERT INTO `t_system_region` VALUES ('6856', '50', '重庆市', '500000', '重庆市', 'zqs');
INSERT INTO `t_system_region` VALUES ('6857', '5001', '市辖区', '500100', '重庆市市辖区', 'zqssxq');
INSERT INTO `t_system_region` VALUES ('6858', '500101', '万州区', '500101', '重庆市万州区', 'zqswzq');
INSERT INTO `t_system_region` VALUES ('6859', '500102', '涪陵区', '500102', '重庆市涪陵区', 'zqsflq');
INSERT INTO `t_system_region` VALUES ('6860', '500103', '渝中区', '500103', '重庆市渝中区', 'zqsyzq');
INSERT INTO `t_system_region` VALUES ('6861', '500104', '大渡口区', '500104', '重庆市大渡口区', 'zqsddkq');
INSERT INTO `t_system_region` VALUES ('6862', '500105', '江北区', '500105', '重庆市江北区', 'zqsjbq');
INSERT INTO `t_system_region` VALUES ('6863', '500106', '沙坪坝区', '500106', '重庆市沙坪坝区', 'zqsspbq');
INSERT INTO `t_system_region` VALUES ('6864', '500107', '九龙坡区', '500107', '重庆市九龙坡区', 'zqsjlpq');
INSERT INTO `t_system_region` VALUES ('6865', '500108', '南岸区', '500108', '重庆市南岸区', 'zqsnaq');
INSERT INTO `t_system_region` VALUES ('6866', '500109', '北碚区', '500109', '重庆市北碚区', 'zqsbbq');
INSERT INTO `t_system_region` VALUES ('6867', '500110', '万盛区', '500110', '重庆市万盛区', 'zqswsq');
INSERT INTO `t_system_region` VALUES ('6868', '500111', '双桥区', '500111', '重庆市双桥区', 'zqssqq');
INSERT INTO `t_system_region` VALUES ('6869', '500112', '渝北区', '500112', '重庆市渝北区', 'zqsybq');
INSERT INTO `t_system_region` VALUES ('6870', '500113', '巴南区', '500113', '重庆市巴南区', 'zqsbnq');
INSERT INTO `t_system_region` VALUES ('6871', '500114', '黔江区', '500114', '重庆市黔江区', 'zqsqjq');
INSERT INTO `t_system_region` VALUES ('6872', '500115', '长寿区', '500115', '重庆市长寿区', 'zqszsq');
INSERT INTO `t_system_region` VALUES ('6873', '500116', '江津区', '500116', '重庆市江津区', 'zqsjjq');
INSERT INTO `t_system_region` VALUES ('6874', '500117', '合川区', '500117', '重庆市合川区', 'zqshcq');
INSERT INTO `t_system_region` VALUES ('6875', '500118', '永川区', '500118', '重庆市永川区', 'zqsycq');
INSERT INTO `t_system_region` VALUES ('6876', '500119', '南川区', '500119', '重庆市南川区', 'zqsncq');
INSERT INTO `t_system_region` VALUES ('6877', '5002', '县', '500200', '重庆市县', 'zqsx');
INSERT INTO `t_system_region` VALUES ('6878', '500222', '綦江县', '500222', '重庆市綦江县', 'zqsqjx');
INSERT INTO `t_system_region` VALUES ('6879', '500223', '潼南县', '500223', '重庆市潼南县', 'zqstnx');
INSERT INTO `t_system_region` VALUES ('6880', '500224', '铜梁县', '500224', '重庆市铜梁县', 'zqstlx');
INSERT INTO `t_system_region` VALUES ('6881', '500225', '大足县', '500225', '重庆市大足县', 'zqsdzx');
INSERT INTO `t_system_region` VALUES ('6882', '500226', '荣昌县', '500226', '重庆市荣昌县', 'zqsrcx');
INSERT INTO `t_system_region` VALUES ('6883', '500227', '璧山县', '500227', '重庆市璧山县', 'zqsbsx');
INSERT INTO `t_system_region` VALUES ('6884', '500228', '梁平县', '500228', '重庆市梁平县', 'zqslpx');
INSERT INTO `t_system_region` VALUES ('6885', '500229', '城口县', '500229', '重庆市城口县', 'zqsckx');
INSERT INTO `t_system_region` VALUES ('6886', '500230', '丰都县', '500230', '重庆市丰都县', 'zqsfdx');
INSERT INTO `t_system_region` VALUES ('6887', '500231', '垫江县', '500231', '重庆市垫江县', 'zqsdjx');
INSERT INTO `t_system_region` VALUES ('6888', '500232', '武隆县', '500232', '重庆市武隆县', 'zqswlx');
INSERT INTO `t_system_region` VALUES ('6889', '500233', '忠县', '500233', '重庆市忠县', 'zqszx');
INSERT INTO `t_system_region` VALUES ('6890', '500234', '开县', '500234', '重庆市开县', 'zqskx');
INSERT INTO `t_system_region` VALUES ('6891', '500235', '云阳县', '500235', '重庆市云阳县', 'zqsyyx');
INSERT INTO `t_system_region` VALUES ('6892', '500236', '奉节县', '500236', '重庆市奉节县', 'zqsfjx');
INSERT INTO `t_system_region` VALUES ('6893', '500237', '巫山县', '500237', '重庆市巫山县', 'zqswsx');
INSERT INTO `t_system_region` VALUES ('6894', '500238', '巫溪县', '500238', '重庆市巫溪县', 'zqswxx');
INSERT INTO `t_system_region` VALUES ('6895', '500240', '石柱土家族自治县', '500240', '重庆市石柱土家族自治县', 'zqssztjzzzx');
INSERT INTO `t_system_region` VALUES ('6896', '500241', '秀山土家族苗族自治县', '500241', '重庆市秀山土家族苗族自治县', 'zqsxstjzmzzzx');
INSERT INTO `t_system_region` VALUES ('6897', '500242', '酉阳土家族苗族自治县', '500242', '重庆市酉阳土家族苗族自治县', 'zqsyytjzmzzzx');
INSERT INTO `t_system_region` VALUES ('6898', '500243', '彭水苗族土家族自治县', '500243', '重庆市彭水苗族土家族自治县', 'zqspsmztjzzzx');
INSERT INTO `t_system_region` VALUES ('6899', '500244', '北部新区', '500244', '重庆市北部新区', 'zqsbbxq');
INSERT INTO `t_system_region` VALUES ('6900', '51', '四川省', '510000', '四川省', 'scs');
INSERT INTO `t_system_region` VALUES ('6901', '5101', '成都市', '510100', '四川省成都市', 'scscds');
INSERT INTO `t_system_region` VALUES ('6902', '510101', '市辖区', '510101', '四川省成都市市辖区', 'scscdssxq');
INSERT INTO `t_system_region` VALUES ('6903', '510104', '锦江区', '510104', '四川省成都市锦江区', 'scscdsjjq');
INSERT INTO `t_system_region` VALUES ('6904', '510105', '青羊区', '510105', '四川省成都市青羊区', 'scscdsqyq');
INSERT INTO `t_system_region` VALUES ('6905', '510106', '金牛区', '510106', '四川省成都市金牛区', 'scscdsjnq');
INSERT INTO `t_system_region` VALUES ('6906', '510107', '武侯区', '510107', '四川省成都市武侯区', 'scscdswhq');
INSERT INTO `t_system_region` VALUES ('6907', '510108', '成华区', '510108', '四川省成都市成华区', 'scscdschq');
INSERT INTO `t_system_region` VALUES ('6908', '510112', '龙泉驿区', '510112', '四川省成都市龙泉驿区', 'scscdslqyq');
INSERT INTO `t_system_region` VALUES ('6909', '510113', '青白江区', '510113', '四川省成都市青白江区', 'scscdsqbjq');
INSERT INTO `t_system_region` VALUES ('6910', '510114', '新都区', '510114', '四川省成都市新都区', 'scscdsxdq');
INSERT INTO `t_system_region` VALUES ('6911', '510115', '温江区', '510115', '四川省成都市温江区', 'scscdswjq');
INSERT INTO `t_system_region` VALUES ('6912', '510121', '金堂县', '510121', '四川省成都市金堂县', 'scscdsjtx');
INSERT INTO `t_system_region` VALUES ('6913', '510122', '双流县', '510122', '四川省成都市双流县', 'scscdsslx');
INSERT INTO `t_system_region` VALUES ('6914', '510124', '郫县', '510124', '四川省成都市郫县', 'scscdspx');
INSERT INTO `t_system_region` VALUES ('6915', '510129', '大邑县', '510129', '四川省成都市大邑县', 'scscdsdyx');
INSERT INTO `t_system_region` VALUES ('6916', '510131', '蒲江县', '510131', '四川省成都市蒲江县', 'scscdspjx');
INSERT INTO `t_system_region` VALUES ('6917', '510132', '新津县', '510132', '四川省成都市新津县', 'scscdsxjx');
INSERT INTO `t_system_region` VALUES ('6918', '510181', '都江堰市', '510181', '四川省成都市都江堰市', 'scscdsdjys');
INSERT INTO `t_system_region` VALUES ('6919', '510182', '彭州市', '510182', '四川省成都市彭州市', 'scscdspzs');
INSERT INTO `t_system_region` VALUES ('6920', '510183', '邛崃市', '510183', '四川省成都市邛崃市', 'scscdsqls');
INSERT INTO `t_system_region` VALUES ('6921', '510184', '崇州市', '510184', '四川省成都市崇州市', 'scscdsczs');
INSERT INTO `t_system_region` VALUES ('6922', '5103', '自贡市', '510300', '四川省自贡市', 'scszgs');
INSERT INTO `t_system_region` VALUES ('6923', '510301', '市辖区', '510301', '四川省自贡市市辖区', 'scszgssxq');
INSERT INTO `t_system_region` VALUES ('6924', '510302', '自流井区', '510302', '四川省自贡市自流井区', 'scszgszljq');
INSERT INTO `t_system_region` VALUES ('6925', '510303', '贡井区', '510303', '四川省自贡市贡井区', 'scszgsgjq');
INSERT INTO `t_system_region` VALUES ('6926', '510304', '大安区', '510304', '四川省自贡市大安区', 'scszgsdaq');
INSERT INTO `t_system_region` VALUES ('6927', '510311', '沿滩区', '510311', '四川省自贡市沿滩区', 'scszgsytq');
INSERT INTO `t_system_region` VALUES ('6928', '510321', '荣县', '510321', '四川省自贡市荣县', 'scszgsrx');
INSERT INTO `t_system_region` VALUES ('6929', '510322', '富顺县', '510322', '四川省自贡市富顺县', 'scszgsfsx');
INSERT INTO `t_system_region` VALUES ('6930', '5104', '攀枝花市', '510400', '四川省攀枝花市', 'scspzhs');
INSERT INTO `t_system_region` VALUES ('6931', '510401', '市辖区', '510401', '四川省攀枝花市市辖区', 'scspzhssxq');
INSERT INTO `t_system_region` VALUES ('6932', '510402', '东区', '510402', '四川省攀枝花市东区', 'scspzhsdq');
INSERT INTO `t_system_region` VALUES ('6933', '510403', '西区', '510403', '四川省攀枝花市西区', 'scspzhsxq');
INSERT INTO `t_system_region` VALUES ('6934', '510411', '仁和区', '510411', '四川省攀枝花市仁和区', 'scspzhsrhq');
INSERT INTO `t_system_region` VALUES ('6935', '510421', '米易县', '510421', '四川省攀枝花市米易县', 'scspzhsmyx');
INSERT INTO `t_system_region` VALUES ('6936', '510422', '盐边县', '510422', '四川省攀枝花市盐边县', 'scspzhsybx');
INSERT INTO `t_system_region` VALUES ('6937', '5105', '泸州市', '510500', '四川省泸州市', 'scslzs');
INSERT INTO `t_system_region` VALUES ('6938', '510501', '市辖区', '510501', '四川省泸州市市辖区', 'scslzssxq');
INSERT INTO `t_system_region` VALUES ('6939', '510502', '江阳区', '510502', '四川省泸州市江阳区', 'scslzsjyq');
INSERT INTO `t_system_region` VALUES ('6940', '510503', '纳溪区', '510503', '四川省泸州市纳溪区', 'scslzsnxq');
INSERT INTO `t_system_region` VALUES ('6941', '510504', '龙马潭区', '510504', '四川省泸州市龙马潭区', 'scslzslmtq');
INSERT INTO `t_system_region` VALUES ('6942', '510521', '泸县', '510521', '四川省泸州市泸县', 'scslzslx');
INSERT INTO `t_system_region` VALUES ('6943', '510522', '合江县', '510522', '四川省泸州市合江县', 'scslzshjx');
INSERT INTO `t_system_region` VALUES ('6944', '510524', '叙永县', '510524', '四川省泸州市叙永县', 'scslzsxyx');
INSERT INTO `t_system_region` VALUES ('6945', '510525', '古蔺县', '510525', '四川省泸州市古蔺县', 'scslzsglx');
INSERT INTO `t_system_region` VALUES ('6946', '5106', '德阳市', '510600', '四川省德阳市', 'scsdys');
INSERT INTO `t_system_region` VALUES ('6947', '510601', '市辖区', '510601', '四川省德阳市市辖区', 'scsdyssxq');
INSERT INTO `t_system_region` VALUES ('6948', '510603', '旌阳区', '510603', '四川省德阳市旌阳区', 'scsdysjyq');
INSERT INTO `t_system_region` VALUES ('6949', '510623', '中江县', '510623', '四川省德阳市中江县', 'scsdyszjx');
INSERT INTO `t_system_region` VALUES ('6950', '510626', '罗江县', '510626', '四川省德阳市罗江县', 'scsdysljx');
INSERT INTO `t_system_region` VALUES ('6951', '510681', '广汉市', '510681', '四川省德阳市广汉市', 'scsdysghs');
INSERT INTO `t_system_region` VALUES ('6952', '510682', '什邡市', '510682', '四川省德阳市什邡市', 'scsdyssfs');
INSERT INTO `t_system_region` VALUES ('6953', '510683', '绵竹市', '510683', '四川省德阳市绵竹市', 'scsdysmzs');
INSERT INTO `t_system_region` VALUES ('6954', '5107', '绵阳市', '510700', '四川省绵阳市', 'scsmys');
INSERT INTO `t_system_region` VALUES ('6955', '510701', '市辖区', '510701', '四川省绵阳市市辖区', 'scsmyssxq');
INSERT INTO `t_system_region` VALUES ('6956', '510703', '涪城区', '510703', '四川省绵阳市涪城区', 'scsmysfcq');
INSERT INTO `t_system_region` VALUES ('6957', '510704', '游仙区', '510704', '四川省绵阳市游仙区', 'scsmysyxq');
INSERT INTO `t_system_region` VALUES ('6958', '510722', '三台县', '510722', '四川省绵阳市三台县', 'scsmysstx');
INSERT INTO `t_system_region` VALUES ('6959', '510723', '盐亭县', '510723', '四川省绵阳市盐亭县', 'scsmysytx');
INSERT INTO `t_system_region` VALUES ('6960', '510724', '安县', '510724', '四川省绵阳市安县', 'scsmysax');
INSERT INTO `t_system_region` VALUES ('6961', '510725', '梓潼县', '510725', '四川省绵阳市梓潼县', 'scsmysztx');
INSERT INTO `t_system_region` VALUES ('6962', '510726', '北川羌族自治县', '510726', '四川省绵阳市北川羌族自治县', 'scsmysbcqzzzx');
INSERT INTO `t_system_region` VALUES ('6963', '510727', '平武县', '510727', '四川省绵阳市平武县', 'scsmyspwx');
INSERT INTO `t_system_region` VALUES ('6964', '510781', '江油市', '510781', '四川省绵阳市江油市', 'scsmysjys');
INSERT INTO `t_system_region` VALUES ('6965', '5108', '广元市', '510800', '四川省广元市', 'scsgys');
INSERT INTO `t_system_region` VALUES ('6966', '510801', '市辖区', '510801', '四川省广元市市辖区', 'scsgyssxq');
INSERT INTO `t_system_region` VALUES ('6967', '510802', '市中区', '510802', '四川省广元市市中区', 'scsgysszq');
INSERT INTO `t_system_region` VALUES ('6968', '510811', '元坝区', '510811', '四川省广元市元坝区', 'scsgysybq');
INSERT INTO `t_system_region` VALUES ('6969', '510812', '朝天区', '510812', '四川省广元市朝天区', 'scsgysctq');
INSERT INTO `t_system_region` VALUES ('6970', '510821', '旺苍县', '510821', '四川省广元市旺苍县', 'scsgyswcx');
INSERT INTO `t_system_region` VALUES ('6971', '510822', '青川县', '510822', '四川省广元市青川县', 'scsgysqcx');
INSERT INTO `t_system_region` VALUES ('6972', '510823', '剑阁县', '510823', '四川省广元市剑阁县', 'scsgysjgx');
INSERT INTO `t_system_region` VALUES ('6973', '510824', '苍溪县', '510824', '四川省广元市苍溪县', 'scsgyscxx');
INSERT INTO `t_system_region` VALUES ('6974', '5109', '遂宁市', '510900', '四川省遂宁市', 'scssns');
INSERT INTO `t_system_region` VALUES ('6975', '510901', '市辖区', '510901', '四川省遂宁市市辖区', 'scssnssxq');
INSERT INTO `t_system_region` VALUES ('6976', '510903', '船山区', '510903', '四川省遂宁市船山区', 'scssnscsq');
INSERT INTO `t_system_region` VALUES ('6977', '510904', '安居区', '510904', '四川省遂宁市安居区', 'scssnsajq');
INSERT INTO `t_system_region` VALUES ('6978', '510921', '蓬溪县', '510921', '四川省遂宁市蓬溪县', 'scssnspxx');
INSERT INTO `t_system_region` VALUES ('6979', '510922', '射洪县', '510922', '四川省遂宁市射洪县', 'scssnsshx');
INSERT INTO `t_system_region` VALUES ('6980', '510923', '大英县', '510923', '四川省遂宁市大英县', 'scssnsdyx');
INSERT INTO `t_system_region` VALUES ('6981', '5110', '内江市', '511000', '四川省内江市', 'scsnjs');
INSERT INTO `t_system_region` VALUES ('6982', '511001', '市辖区', '511001', '四川省内江市市辖区', 'scsnjssxq');
INSERT INTO `t_system_region` VALUES ('6983', '511002', '市中区', '511002', '四川省内江市市中区', 'scsnjsszq');
INSERT INTO `t_system_region` VALUES ('6984', '511011', '东兴区', '511011', '四川省内江市东兴区', 'scsnjsdxq');
INSERT INTO `t_system_region` VALUES ('6985', '511024', '威远县', '511024', '四川省内江市威远县', 'scsnjswyx');
INSERT INTO `t_system_region` VALUES ('6986', '511025', '资中县', '511025', '四川省内江市资中县', 'scsnjszzx');
INSERT INTO `t_system_region` VALUES ('6987', '511028', '隆昌县', '511028', '四川省内江市隆昌县', 'scsnjslcx');
INSERT INTO `t_system_region` VALUES ('6988', '5111', '乐山市', '511100', '四川省乐山市', 'scslss');
INSERT INTO `t_system_region` VALUES ('6989', '511101', '市辖区', '511101', '四川省乐山市市辖区', 'scslsssxq');
INSERT INTO `t_system_region` VALUES ('6990', '511102', '市中区', '511102', '四川省乐山市市中区', 'scslssszq');
INSERT INTO `t_system_region` VALUES ('6991', '511111', '沙湾区', '511111', '四川省乐山市沙湾区', 'scslssswq');
INSERT INTO `t_system_region` VALUES ('6992', '511112', '五通桥区', '511112', '四川省乐山市五通桥区', 'scslsswtqq');
INSERT INTO `t_system_region` VALUES ('6993', '511113', '金口河区', '511113', '四川省乐山市金口河区', 'scslssjkhq');
INSERT INTO `t_system_region` VALUES ('6994', '511123', '犍为县', '511123', '四川省乐山市犍为县', 'scslssjwx');
INSERT INTO `t_system_region` VALUES ('6995', '511124', '井研县', '511124', '四川省乐山市井研县', 'scslssjyx');
INSERT INTO `t_system_region` VALUES ('6996', '511126', '夹江县', '511126', '四川省乐山市夹江县', 'scslssjjx');
INSERT INTO `t_system_region` VALUES ('6997', '511129', '沐川县', '511129', '四川省乐山市沐川县', 'scslssmcx');
INSERT INTO `t_system_region` VALUES ('6998', '511132', '峨边彝族自治县', '511132', '四川省乐山市峨边彝族自治县', 'scslssebyzzzx');
INSERT INTO `t_system_region` VALUES ('6999', '511133', '马边彝族自治县', '511133', '四川省乐山市马边彝族自治县', 'scslssmbyzzzx');
INSERT INTO `t_system_region` VALUES ('7000', '511181', '峨眉山市', '511181', '四川省乐山市峨眉山市', 'scslssemss');
INSERT INTO `t_system_region` VALUES ('7001', '5113', '南充市', '511300', '四川省南充市', 'scsncs');
INSERT INTO `t_system_region` VALUES ('7002', '511301', '市辖区', '511301', '四川省南充市市辖区', 'scsncssxq');
INSERT INTO `t_system_region` VALUES ('7003', '511302', '顺庆区', '511302', '四川省南充市顺庆区', 'scsncssqq');
INSERT INTO `t_system_region` VALUES ('7004', '511303', '高坪区', '511303', '四川省南充市高坪区', 'scsncsgpq');
INSERT INTO `t_system_region` VALUES ('7005', '511304', '嘉陵区', '511304', '四川省南充市嘉陵区', 'scsncsjlq');
INSERT INTO `t_system_region` VALUES ('7006', '511321', '南部县', '511321', '四川省南充市南部县', 'scsncsnbx');
INSERT INTO `t_system_region` VALUES ('7007', '511322', '营山县', '511322', '四川省南充市营山县', 'scsncsysx');
INSERT INTO `t_system_region` VALUES ('7008', '511323', '蓬安县', '511323', '四川省南充市蓬安县', 'scsncspax');
INSERT INTO `t_system_region` VALUES ('7009', '511324', '仪陇县', '511324', '四川省南充市仪陇县', 'scsncsylx');
INSERT INTO `t_system_region` VALUES ('7010', '511325', '西充县', '511325', '四川省南充市西充县', 'scsncsxcx');
INSERT INTO `t_system_region` VALUES ('7011', '511381', '阆中市', '511381', '四川省南充市阆中市', 'scsncslzs');
INSERT INTO `t_system_region` VALUES ('7012', '5114', '眉山市', '511400', '四川省眉山市', 'scsmss');
INSERT INTO `t_system_region` VALUES ('7013', '511401', '市辖区', '511401', '四川省眉山市市辖区', 'scsmsssxq');
INSERT INTO `t_system_region` VALUES ('7014', '511402', '东坡区', '511402', '四川省眉山市东坡区', 'scsmssdpq');
INSERT INTO `t_system_region` VALUES ('7015', '511421', '仁寿县', '511421', '四川省眉山市仁寿县', 'scsmssrsx');
INSERT INTO `t_system_region` VALUES ('7016', '511422', '彭山县', '511422', '四川省眉山市彭山县', 'scsmsspsx');
INSERT INTO `t_system_region` VALUES ('7017', '511423', '洪雅县', '511423', '四川省眉山市洪雅县', 'scsmsshyx');
INSERT INTO `t_system_region` VALUES ('7018', '511424', '丹棱县', '511424', '四川省眉山市丹棱县', 'scsmssdlx');
INSERT INTO `t_system_region` VALUES ('7019', '511425', '青神县', '511425', '四川省眉山市青神县', 'scsmssqsx');
INSERT INTO `t_system_region` VALUES ('7020', '5115', '宜宾市', '511500', '四川省宜宾市', 'scsybs');
INSERT INTO `t_system_region` VALUES ('7021', '511501', '市辖区', '511501', '四川省宜宾市市辖区', 'scsybssxq');
INSERT INTO `t_system_region` VALUES ('7022', '511502', '翠屏区', '511502', '四川省宜宾市翠屏区', 'scsybscpq');
INSERT INTO `t_system_region` VALUES ('7023', '511521', '宜宾县', '511521', '四川省宜宾市宜宾县', 'scsybsybx');
INSERT INTO `t_system_region` VALUES ('7024', '511522', '南溪县', '511522', '四川省宜宾市南溪县', 'scsybsnxx');
INSERT INTO `t_system_region` VALUES ('7025', '511523', '江安县', '511523', '四川省宜宾市江安县', 'scsybsjax');
INSERT INTO `t_system_region` VALUES ('7026', '511524', '长宁县', '511524', '四川省宜宾市长宁县', 'scsybsznx');
INSERT INTO `t_system_region` VALUES ('7027', '511525', '高县', '511525', '四川省宜宾市高县', 'scsybsgx');
INSERT INTO `t_system_region` VALUES ('7028', '511526', '珙县', '511526', '四川省宜宾市珙县', 'scsybsgx');
INSERT INTO `t_system_region` VALUES ('7029', '511527', '筠连县', '511527', '四川省宜宾市筠连县', 'scsybsylx');
INSERT INTO `t_system_region` VALUES ('7030', '511528', '兴文县', '511528', '四川省宜宾市兴文县', 'scsybsxwx');
INSERT INTO `t_system_region` VALUES ('7031', '511529', '屏山县', '511529', '四川省宜宾市屏山县', 'scsybspsx');
INSERT INTO `t_system_region` VALUES ('7032', '5116', '广安市', '511600', '四川省广安市', 'scsgas');
INSERT INTO `t_system_region` VALUES ('7033', '511601', '市辖区', '511601', '四川省广安市市辖区', 'scsgassxq');
INSERT INTO `t_system_region` VALUES ('7034', '511602', '广安区', '511602', '四川省广安市广安区', 'scsgasgaq');
INSERT INTO `t_system_region` VALUES ('7035', '511621', '岳池县', '511621', '四川省广安市岳池县', 'scsgasycx');
INSERT INTO `t_system_region` VALUES ('7036', '511622', '武胜县', '511622', '四川省广安市武胜县', 'scsgaswsx');
INSERT INTO `t_system_region` VALUES ('7037', '511623', '邻水县', '511623', '四川省广安市邻水县', 'scsgaslsx');
INSERT INTO `t_system_region` VALUES ('7038', '511681', '华蓥市', '511681', '四川省广安市华蓥市', 'scsgashys');
INSERT INTO `t_system_region` VALUES ('7039', '5117', '达州市', '511700', '四川省达州市', 'scsdzs');
INSERT INTO `t_system_region` VALUES ('7040', '511701', '市辖区', '511701', '四川省达州市市辖区', 'scsdzssxq');
INSERT INTO `t_system_region` VALUES ('7041', '511702', '通川区', '511702', '四川省达州市通川区', 'scsdzstcq');
INSERT INTO `t_system_region` VALUES ('7042', '511721', '达县', '511721', '四川省达州市达县', 'scsdzsdx');
INSERT INTO `t_system_region` VALUES ('7043', '511722', '宣汉县', '511722', '四川省达州市宣汉县', 'scsdzsxhx');
INSERT INTO `t_system_region` VALUES ('7044', '511723', '开江县', '511723', '四川省达州市开江县', 'scsdzskjx');
INSERT INTO `t_system_region` VALUES ('7045', '511724', '大竹县', '511724', '四川省达州市大竹县', 'scsdzsdzx');
INSERT INTO `t_system_region` VALUES ('7046', '511725', '渠县', '511725', '四川省达州市渠县', 'scsdzsqx');
INSERT INTO `t_system_region` VALUES ('7047', '511781', '万源市', '511781', '四川省达州市万源市', 'scsdzswys');
INSERT INTO `t_system_region` VALUES ('7048', '5118', '雅安市', '511800', '四川省雅安市', 'scsyas');
INSERT INTO `t_system_region` VALUES ('7049', '511801', '市辖区', '511801', '四川省雅安市市辖区', 'scsyassxq');
INSERT INTO `t_system_region` VALUES ('7050', '511802', '雨城区', '511802', '四川省雅安市雨城区', 'scsyasycq');
INSERT INTO `t_system_region` VALUES ('7051', '511821', '名山县', '511821', '四川省雅安市名山县', 'scsyasmsx');
INSERT INTO `t_system_region` VALUES ('7052', '511822', '荥经县', '511822', '四川省雅安市荥经县', 'scsyasyjx');
INSERT INTO `t_system_region` VALUES ('7053', '511823', '汉源县', '511823', '四川省雅安市汉源县', 'scsyashyx');
INSERT INTO `t_system_region` VALUES ('7054', '511824', '石棉县', '511824', '四川省雅安市石棉县', 'scsyassmx');
INSERT INTO `t_system_region` VALUES ('7055', '511825', '天全县', '511825', '四川省雅安市天全县', 'scsyastqx');
INSERT INTO `t_system_region` VALUES ('7056', '511826', '芦山县', '511826', '四川省雅安市芦山县', 'scsyaslsx');
INSERT INTO `t_system_region` VALUES ('7057', '511827', '宝兴县', '511827', '四川省雅安市宝兴县', 'scsyasbxx');
INSERT INTO `t_system_region` VALUES ('7058', '5119', '巴中市', '511900', '四川省巴中市', 'scsbzs');
INSERT INTO `t_system_region` VALUES ('7059', '511901', '市辖区', '511901', '四川省巴中市市辖区', 'scsbzssxq');
INSERT INTO `t_system_region` VALUES ('7060', '511902', '巴州区', '511902', '四川省巴中市巴州区', 'scsbzsbzq');
INSERT INTO `t_system_region` VALUES ('7061', '511921', '通江县', '511921', '四川省巴中市通江县', 'scsbzstjx');
INSERT INTO `t_system_region` VALUES ('7062', '511922', '南江县', '511922', '四川省巴中市南江县', 'scsbzsnjx');
INSERT INTO `t_system_region` VALUES ('7063', '511923', '平昌县', '511923', '四川省巴中市平昌县', 'scsbzspcx');
INSERT INTO `t_system_region` VALUES ('7064', '5120', '资阳市', '512000', '四川省资阳市', 'scszys');
INSERT INTO `t_system_region` VALUES ('7065', '512001', '市辖区', '512001', '四川省资阳市市辖区', 'scszyssxq');
INSERT INTO `t_system_region` VALUES ('7066', '512002', '雁江区', '512002', '四川省资阳市雁江区', 'scszysyjq');
INSERT INTO `t_system_region` VALUES ('7067', '512021', '安岳县', '512021', '四川省资阳市安岳县', 'scszysayx');
INSERT INTO `t_system_region` VALUES ('7068', '512022', '乐至县', '512022', '四川省资阳市乐至县', 'scszyslzx');
INSERT INTO `t_system_region` VALUES ('7069', '512081', '简阳市', '512081', '四川省资阳市简阳市', 'scszysjys');
INSERT INTO `t_system_region` VALUES ('7070', '5132', '阿坝藏族羌族自治州', '513200', '四川省阿坝藏族羌族自治州', 'scsabzzqzzzz');
INSERT INTO `t_system_region` VALUES ('7071', '513221', '汶川县', '513221', '四川省阿坝藏族羌族自治州汶川县', 'scsabzzqzzzzwcx');
INSERT INTO `t_system_region` VALUES ('7072', '513222', '理县', '513222', '四川省阿坝藏族羌族自治州理县', 'scsabzzqzzzzlx');
INSERT INTO `t_system_region` VALUES ('7073', '513223', '茂县', '513223', '四川省阿坝藏族羌族自治州茂县', 'scsabzzqzzzzmx');
INSERT INTO `t_system_region` VALUES ('7074', '513224', '松潘县', '513224', '四川省阿坝藏族羌族自治州松潘县', 'scsabzzqzzzzspx');
INSERT INTO `t_system_region` VALUES ('7075', '513225', '九寨沟县', '513225', '四川省阿坝藏族羌族自治州九寨沟县', 'scsabzzqzzzzjzgx');
INSERT INTO `t_system_region` VALUES ('7076', '513226', '金川县', '513226', '四川省阿坝藏族羌族自治州金川县', 'scsabzzqzzzzjcx');
INSERT INTO `t_system_region` VALUES ('7077', '513227', '小金县', '513227', '四川省阿坝藏族羌族自治州小金县', 'scsabzzqzzzzxjx');
INSERT INTO `t_system_region` VALUES ('7078', '513228', '黑水县', '513228', '四川省阿坝藏族羌族自治州黑水县', 'scsabzzqzzzzhsx');
INSERT INTO `t_system_region` VALUES ('7079', '513229', '马尔康县', '513229', '四川省阿坝藏族羌族自治州马尔康县', 'scsabzzqzzzzmekx');
INSERT INTO `t_system_region` VALUES ('7080', '513230', '壤塘县', '513230', '四川省阿坝藏族羌族自治州壤塘县', 'scsabzzqzzzzrtx');
INSERT INTO `t_system_region` VALUES ('7081', '513231', '阿坝县', '513231', '四川省阿坝藏族羌族自治州阿坝县', 'scsabzzqzzzzabx');
INSERT INTO `t_system_region` VALUES ('7082', '513232', '若尔盖县', '513232', '四川省阿坝藏族羌族自治州若尔盖县', 'scsabzzqzzzzregx');
INSERT INTO `t_system_region` VALUES ('7083', '513233', '红原县', '513233', '四川省阿坝藏族羌族自治州红原县', 'scsabzzqzzzzhyx');
INSERT INTO `t_system_region` VALUES ('7084', '5133', '甘孜藏族自治州', '513300', '四川省甘孜藏族自治州', 'scsgzzzzzz');
INSERT INTO `t_system_region` VALUES ('7085', '513321', '康定县', '513321', '四川省甘孜藏族自治州康定县', 'scsgzzzzzzkdx');
INSERT INTO `t_system_region` VALUES ('7086', '513322', '泸定县', '513322', '四川省甘孜藏族自治州泸定县', 'scsgzzzzzzldx');
INSERT INTO `t_system_region` VALUES ('7087', '513323', '丹巴县', '513323', '四川省甘孜藏族自治州丹巴县', 'scsgzzzzzzdbx');
INSERT INTO `t_system_region` VALUES ('7088', '513324', '九龙县', '513324', '四川省甘孜藏族自治州九龙县', 'scsgzzzzzzjlx');
INSERT INTO `t_system_region` VALUES ('7089', '513325', '雅江县', '513325', '四川省甘孜藏族自治州雅江县', 'scsgzzzzzzyjx');
INSERT INTO `t_system_region` VALUES ('7090', '513326', '道孚县', '513326', '四川省甘孜藏族自治州道孚县', 'scsgzzzzzzdfx');
INSERT INTO `t_system_region` VALUES ('7091', '513327', '炉霍县', '513327', '四川省甘孜藏族自治州炉霍县', 'scsgzzzzzzlhx');
INSERT INTO `t_system_region` VALUES ('7092', '513328', '甘孜县', '513328', '四川省甘孜藏族自治州甘孜县', 'scsgzzzzzzgzx');
INSERT INTO `t_system_region` VALUES ('7093', '513329', '新龙县', '513329', '四川省甘孜藏族自治州新龙县', 'scsgzzzzzzxlx');
INSERT INTO `t_system_region` VALUES ('7094', '513330', '德格县', '513330', '四川省甘孜藏族自治州德格县', 'scsgzzzzzzdgx');
INSERT INTO `t_system_region` VALUES ('7095', '513331', '白玉县', '513331', '四川省甘孜藏族自治州白玉县', 'scsgzzzzzzbyx');
INSERT INTO `t_system_region` VALUES ('7096', '513332', '石渠县', '513332', '四川省甘孜藏族自治州石渠县', 'scsgzzzzzzsqx');
INSERT INTO `t_system_region` VALUES ('7097', '513333', '色达县', '513333', '四川省甘孜藏族自治州色达县', 'scsgzzzzzzsdx');
INSERT INTO `t_system_region` VALUES ('7098', '513334', '理塘县', '513334', '四川省甘孜藏族自治州理塘县', 'scsgzzzzzzltx');
INSERT INTO `t_system_region` VALUES ('7099', '513335', '巴塘县', '513335', '四川省甘孜藏族自治州巴塘县', 'scsgzzzzzzbtx');
INSERT INTO `t_system_region` VALUES ('7100', '513336', '乡城县', '513336', '四川省甘孜藏族自治州乡城县', 'scsgzzzzzzxcx');
INSERT INTO `t_system_region` VALUES ('7101', '513337', '稻城县', '513337', '四川省甘孜藏族自治州稻城县', 'scsgzzzzzzdcx');
INSERT INTO `t_system_region` VALUES ('7102', '513338', '得荣县', '513338', '四川省甘孜藏族自治州得荣县', 'scsgzzzzzzdrx');
INSERT INTO `t_system_region` VALUES ('7103', '5134', '凉山彝族自治州', '513400', '四川省凉山彝族自治州', 'scslsyzzzz');
INSERT INTO `t_system_region` VALUES ('7104', '513401', '西昌市', '513401', '四川省凉山彝族自治州西昌市', 'scslsyzzzzxcs');
INSERT INTO `t_system_region` VALUES ('7105', '513422', '木里藏族自治县', '513422', '四川省凉山彝族自治州木里藏族自治县', 'scslsyzzzzmlzzzzx');
INSERT INTO `t_system_region` VALUES ('7106', '513423', '盐源县', '513423', '四川省凉山彝族自治州盐源县', 'scslsyzzzzyyx');
INSERT INTO `t_system_region` VALUES ('7107', '513424', '德昌县', '513424', '四川省凉山彝族自治州德昌县', 'scslsyzzzzdcx');
INSERT INTO `t_system_region` VALUES ('7108', '513425', '会理县', '513425', '四川省凉山彝族自治州会理县', 'scslsyzzzzhlx');
INSERT INTO `t_system_region` VALUES ('7109', '513426', '会东县', '513426', '四川省凉山彝族自治州会东县', 'scslsyzzzzhdx');
INSERT INTO `t_system_region` VALUES ('7110', '513427', '宁南县', '513427', '四川省凉山彝族自治州宁南县', 'scslsyzzzznnx');
INSERT INTO `t_system_region` VALUES ('7111', '513428', '普格县', '513428', '四川省凉山彝族自治州普格县', 'scslsyzzzzpgx');
INSERT INTO `t_system_region` VALUES ('7112', '513429', '布拖县', '513429', '四川省凉山彝族自治州布拖县', 'scslsyzzzzbtx');
INSERT INTO `t_system_region` VALUES ('7113', '513430', '金阳县', '513430', '四川省凉山彝族自治州金阳县', 'scslsyzzzzjyx');
INSERT INTO `t_system_region` VALUES ('7114', '513431', '昭觉县', '513431', '四川省凉山彝族自治州昭觉县', 'scslsyzzzzzjx');
INSERT INTO `t_system_region` VALUES ('7115', '513432', '喜德县', '513432', '四川省凉山彝族自治州喜德县', 'scslsyzzzzxdx');
INSERT INTO `t_system_region` VALUES ('7116', '513433', '冕宁县', '513433', '四川省凉山彝族自治州冕宁县', 'scslsyzzzzmnx');
INSERT INTO `t_system_region` VALUES ('7117', '513434', '越西县', '513434', '四川省凉山彝族自治州越西县', 'scslsyzzzzyxx');
INSERT INTO `t_system_region` VALUES ('7118', '513435', '甘洛县', '513435', '四川省凉山彝族自治州甘洛县', 'scslsyzzzzglx');
INSERT INTO `t_system_region` VALUES ('7119', '513436', '美姑县', '513436', '四川省凉山彝族自治州美姑县', 'scslsyzzzzmgx');
INSERT INTO `t_system_region` VALUES ('7120', '513437', '雷波县', '513437', '四川省凉山彝族自治州雷波县', 'scslsyzzzzlbx');
INSERT INTO `t_system_region` VALUES ('7121', '52', '贵州省', '520000', '贵州省', 'gzs');
INSERT INTO `t_system_region` VALUES ('7122', '5201', '贵阳市', '520100', '贵州省贵阳市', 'gzsgys');
INSERT INTO `t_system_region` VALUES ('7123', '520101', '市辖区', '520101', '贵州省贵阳市市辖区', 'gzsgyssxq');
INSERT INTO `t_system_region` VALUES ('7124', '520102', '南明区', '520102', '贵州省贵阳市南明区', 'gzsgysnmq');
INSERT INTO `t_system_region` VALUES ('7125', '520103', '云岩区', '520103', '贵州省贵阳市云岩区', 'gzsgysyyq');
INSERT INTO `t_system_region` VALUES ('7126', '520111', '花溪区', '520111', '贵州省贵阳市花溪区', 'gzsgyshxq');
INSERT INTO `t_system_region` VALUES ('7127', '520112', '乌当区', '520112', '贵州省贵阳市乌当区', 'gzsgyswdq');
INSERT INTO `t_system_region` VALUES ('7128', '520113', '白云区', '520113', '贵州省贵阳市白云区', 'gzsgysbyq');
INSERT INTO `t_system_region` VALUES ('7129', '520114', '小河区', '520114', '贵州省贵阳市小河区', 'gzsgysxhq');
INSERT INTO `t_system_region` VALUES ('7130', '520121', '开阳县', '520121', '贵州省贵阳市开阳县', 'gzsgyskyx');
INSERT INTO `t_system_region` VALUES ('7131', '520122', '息烽县', '520122', '贵州省贵阳市息烽县', 'gzsgysxfx');
INSERT INTO `t_system_region` VALUES ('7132', '520123', '修文县', '520123', '贵州省贵阳市修文县', 'gzsgysxwx');
INSERT INTO `t_system_region` VALUES ('7133', '520181', '清镇市', '520181', '贵州省贵阳市清镇市', 'gzsgysqzs');
INSERT INTO `t_system_region` VALUES ('7134', '5202', '六盘水市', '520200', '贵州省六盘水市', 'gzslpss');
INSERT INTO `t_system_region` VALUES ('7135', '520201', '钟山区', '520201', '贵州省六盘水市钟山区', 'gzslpsszsq');
INSERT INTO `t_system_region` VALUES ('7136', '520203', '六枝特区', '520203', '贵州省六盘水市六枝特区', 'gzslpsslztq');
INSERT INTO `t_system_region` VALUES ('7137', '520221', '水城县', '520221', '贵州省六盘水市水城县', 'gzslpssscx');
INSERT INTO `t_system_region` VALUES ('7138', '520222', '盘县', '520222', '贵州省六盘水市盘县', 'gzslpsspx');
INSERT INTO `t_system_region` VALUES ('7139', '5203', '遵义市', '520300', '贵州省遵义市', 'gzszys');
INSERT INTO `t_system_region` VALUES ('7140', '520301', '市辖区', '520301', '贵州省遵义市市辖区', 'gzszyssxq');
INSERT INTO `t_system_region` VALUES ('7141', '520302', '红花岗区', '520302', '贵州省遵义市红花岗区', 'gzszyshhgq');
INSERT INTO `t_system_region` VALUES ('7142', '520303', '汇川区', '520303', '贵州省遵义市汇川区', 'gzszyshcq');
INSERT INTO `t_system_region` VALUES ('7143', '520321', '遵义县', '520321', '贵州省遵义市遵义县', 'gzszyszyx');
INSERT INTO `t_system_region` VALUES ('7144', '520322', '桐梓县', '520322', '贵州省遵义市桐梓县', 'gzszystzx');
INSERT INTO `t_system_region` VALUES ('7145', '520323', '绥阳县', '520323', '贵州省遵义市绥阳县', 'gzszyssyx');
INSERT INTO `t_system_region` VALUES ('7146', '520324', '正安县', '520324', '贵州省遵义市正安县', 'gzszyszax');
INSERT INTO `t_system_region` VALUES ('7147', '520325', '道真仡佬族苗族自治县', '520325', '贵州省遵义市道真仡佬族苗族自治县', 'gzszysdzglzmzzzx');
INSERT INTO `t_system_region` VALUES ('7148', '520326', '务川仡佬族苗族自治县', '520326', '贵州省遵义市务川仡佬族苗族自治县', 'gzszyswcglzmzzzx');
INSERT INTO `t_system_region` VALUES ('7149', '520327', '凤冈县', '520327', '贵州省遵义市凤冈县', 'gzszysfgx');
INSERT INTO `t_system_region` VALUES ('7150', '520328', '湄潭县', '520328', '贵州省遵义市湄潭县', 'gzszysmtx');
INSERT INTO `t_system_region` VALUES ('7151', '520329', '余庆县', '520329', '贵州省遵义市余庆县', 'gzszysyqx');
INSERT INTO `t_system_region` VALUES ('7152', '520330', '习水县', '520330', '贵州省遵义市习水县', 'gzszysxsx');
INSERT INTO `t_system_region` VALUES ('7153', '520381', '赤水市', '520381', '贵州省遵义市赤水市', 'gzszyscss');
INSERT INTO `t_system_region` VALUES ('7154', '520382', '仁怀市', '520382', '贵州省遵义市仁怀市', 'gzszysrhs');
INSERT INTO `t_system_region` VALUES ('7155', '5204', '安顺市', '520400', '贵州省安顺市', 'gzsass');
INSERT INTO `t_system_region` VALUES ('7156', '520401', '市辖区', '520401', '贵州省安顺市市辖区', 'gzsasssxq');
INSERT INTO `t_system_region` VALUES ('7157', '520402', '西秀区', '520402', '贵州省安顺市西秀区', 'gzsassxxq');
INSERT INTO `t_system_region` VALUES ('7158', '520421', '平坝县', '520421', '贵州省安顺市平坝县', 'gzsasspbx');
INSERT INTO `t_system_region` VALUES ('7159', '520422', '普定县', '520422', '贵州省安顺市普定县', 'gzsasspdx');
INSERT INTO `t_system_region` VALUES ('7160', '520423', '镇宁布依族苗族自治县', '520423', '贵州省安顺市镇宁布依族苗族自治县', 'gzsassznbyzmzzzx');
INSERT INTO `t_system_region` VALUES ('7161', '520424', '关岭布依族苗族自治县', '520424', '贵州省安顺市关岭布依族苗族自治县', 'gzsassglbyzmzzzx');
INSERT INTO `t_system_region` VALUES ('7162', '520425', '紫云苗族布依族自治县', '520425', '贵州省安顺市紫云苗族布依族自治县', 'gzsasszymzbyzzzx');
INSERT INTO `t_system_region` VALUES ('7163', '520426', '安顺市开发区', '520426', '贵州省安顺市安顺市开发区', 'gzsassasskfq');
INSERT INTO `t_system_region` VALUES ('7164', '5222', '铜仁地区', '522200', '贵州省铜仁地区', 'gzstrdq');
INSERT INTO `t_system_region` VALUES ('7165', '522201', '铜仁市', '522201', '贵州省铜仁地区铜仁市', 'gzstrdqtrs');
INSERT INTO `t_system_region` VALUES ('7166', '522222', '江口县', '522222', '贵州省铜仁地区江口县', 'gzstrdqjkx');
INSERT INTO `t_system_region` VALUES ('7167', '522223', '玉屏侗族自治县', '522223', '贵州省铜仁地区玉屏侗族自治县', 'gzstrdqypdzzzx');
INSERT INTO `t_system_region` VALUES ('7168', '522224', '石阡县', '522224', '贵州省铜仁地区石阡县', 'gzstrdqsqx');
INSERT INTO `t_system_region` VALUES ('7169', '522225', '思南县', '522225', '贵州省铜仁地区思南县', 'gzstrdqsnx');
INSERT INTO `t_system_region` VALUES ('7170', '522226', '印江土家族苗族自治县', '522226', '贵州省铜仁地区印江土家族苗族自治县', 'gzstrdqyjtjzmzzzx');
INSERT INTO `t_system_region` VALUES ('7171', '522227', '德江县', '522227', '贵州省铜仁地区德江县', 'gzstrdqdjx');
INSERT INTO `t_system_region` VALUES ('7172', '522228', '沿河土家族自治县', '522228', '贵州省铜仁地区沿河土家族自治县', 'gzstrdqyhtjzzzx');
INSERT INTO `t_system_region` VALUES ('7173', '522229', '松桃苗族自治县', '522229', '贵州省铜仁地区松桃苗族自治县', 'gzstrdqstmzzzx');
INSERT INTO `t_system_region` VALUES ('7174', '522230', '万山特区', '522230', '贵州省铜仁地区万山特区', 'gzstrdqwstq');
INSERT INTO `t_system_region` VALUES ('7175', '5223', '黔西南布依族苗族自治州', '522300', '贵州省黔西南布依族苗族自治州', 'gzsqxnbyzmzzzz');
INSERT INTO `t_system_region` VALUES ('7176', '522301', '兴义市', '522301', '贵州省黔西南布依族苗族自治州兴义市', 'gzsqxnbyzmzzzzxys');
INSERT INTO `t_system_region` VALUES ('7177', '522322', '兴仁县', '522322', '贵州省黔西南布依族苗族自治州兴仁县', 'gzsqxnbyzmzzzzxrx');
INSERT INTO `t_system_region` VALUES ('7178', '522323', '普安县', '522323', '贵州省黔西南布依族苗族自治州普安县', 'gzsqxnbyzmzzzzpax');
INSERT INTO `t_system_region` VALUES ('7179', '522324', '晴隆县', '522324', '贵州省黔西南布依族苗族自治州晴隆县', 'gzsqxnbyzmzzzzqlx');
INSERT INTO `t_system_region` VALUES ('7180', '522325', '贞丰县', '522325', '贵州省黔西南布依族苗族自治州贞丰县', 'gzsqxnbyzmzzzzzfx');
INSERT INTO `t_system_region` VALUES ('7181', '522326', '望谟县', '522326', '贵州省黔西南布依族苗族自治州望谟县', 'gzsqxnbyzmzzzzwmx');
INSERT INTO `t_system_region` VALUES ('7182', '522327', '册亨县', '522327', '贵州省黔西南布依族苗族自治州册亨县', 'gzsqxnbyzmzzzzchx');
INSERT INTO `t_system_region` VALUES ('7183', '522328', '安龙县', '522328', '贵州省黔西南布依族苗族自治州安龙县', 'gzsqxnbyzmzzzzalx');
INSERT INTO `t_system_region` VALUES ('7184', '5224', '毕节地区', '522400', '贵州省毕节地区', 'gzsbjdq');
INSERT INTO `t_system_region` VALUES ('7185', '522401', '毕节市', '522401', '贵州省毕节地区毕节市', 'gzsbjdqbjs');
INSERT INTO `t_system_region` VALUES ('7186', '522422', '大方县', '522422', '贵州省毕节地区大方县', 'gzsbjdqdfx');
INSERT INTO `t_system_region` VALUES ('7187', '522423', '黔西县', '522423', '贵州省毕节地区黔西县', 'gzsbjdqqxx');
INSERT INTO `t_system_region` VALUES ('7188', '522424', '金沙县', '522424', '贵州省毕节地区金沙县', 'gzsbjdqjsx');
INSERT INTO `t_system_region` VALUES ('7189', '522425', '织金县', '522425', '贵州省毕节地区织金县', 'gzsbjdqzjx');
INSERT INTO `t_system_region` VALUES ('7190', '522426', '纳雍县', '522426', '贵州省毕节地区纳雍县', 'gzsbjdqnyx');
INSERT INTO `t_system_region` VALUES ('7191', '522427', '威宁彝族回族苗族自治县', '522427', '贵州省毕节地区威宁彝族回族苗族自治县', 'gzsbjdqwnyzhzmzzzx');
INSERT INTO `t_system_region` VALUES ('7192', '522428', '赫章县', '522428', '贵州省毕节地区赫章县', 'gzsbjdqhzx');
INSERT INTO `t_system_region` VALUES ('7193', '5226', '黔东南苗族侗族自治州', '522600', '贵州省黔东南苗族侗族自治州', 'gzsqdnmzdzzzz');
INSERT INTO `t_system_region` VALUES ('7194', '522601', '凯里市', '522601', '贵州省黔东南苗族侗族自治州凯里市', 'gzsqdnmzdzzzzkls');
INSERT INTO `t_system_region` VALUES ('7195', '522622', '黄平县', '522622', '贵州省黔东南苗族侗族自治州黄平县', 'gzsqdnmzdzzzzhpx');
INSERT INTO `t_system_region` VALUES ('7196', '522623', '施秉县', '522623', '贵州省黔东南苗族侗族自治州施秉县', 'gzsqdnmzdzzzzsbx');
INSERT INTO `t_system_region` VALUES ('7197', '522624', '三穗县', '522624', '贵州省黔东南苗族侗族自治州三穗县', 'gzsqdnmzdzzzzssx');
INSERT INTO `t_system_region` VALUES ('7198', '522625', '镇远县', '522625', '贵州省黔东南苗族侗族自治州镇远县', 'gzsqdnmzdzzzzzyx');
INSERT INTO `t_system_region` VALUES ('7199', '522626', '岑巩县', '522626', '贵州省黔东南苗族侗族自治州岑巩县', 'gzsqdnmzdzzzzcgx');
INSERT INTO `t_system_region` VALUES ('7200', '522627', '天柱县', '522627', '贵州省黔东南苗族侗族自治州天柱县', 'gzsqdnmzdzzzztzx');
INSERT INTO `t_system_region` VALUES ('7201', '522628', '锦屏县', '522628', '贵州省黔东南苗族侗族自治州锦屏县', 'gzsqdnmzdzzzzjpx');
INSERT INTO `t_system_region` VALUES ('7202', '522629', '剑河县', '522629', '贵州省黔东南苗族侗族自治州剑河县', 'gzsqdnmzdzzzzjhx');
INSERT INTO `t_system_region` VALUES ('7203', '522630', '台江县', '522630', '贵州省黔东南苗族侗族自治州台江县', 'gzsqdnmzdzzzztjx');
INSERT INTO `t_system_region` VALUES ('7204', '522631', '黎平县', '522631', '贵州省黔东南苗族侗族自治州黎平县', 'gzsqdnmzdzzzzlpx');
INSERT INTO `t_system_region` VALUES ('7205', '522632', '榕江县', '522632', '贵州省黔东南苗族侗族自治州榕江县', 'gzsqdnmzdzzzzrjx');
INSERT INTO `t_system_region` VALUES ('7206', '522633', '从江县', '522633', '贵州省黔东南苗族侗族自治州从江县', 'gzsqdnmzdzzzzcjx');
INSERT INTO `t_system_region` VALUES ('7207', '522634', '雷山县', '522634', '贵州省黔东南苗族侗族自治州雷山县', 'gzsqdnmzdzzzzlsx');
INSERT INTO `t_system_region` VALUES ('7208', '522635', '麻江县', '522635', '贵州省黔东南苗族侗族自治州麻江县', 'gzsqdnmzdzzzzmjx');
INSERT INTO `t_system_region` VALUES ('7209', '522636', '丹寨县', '522636', '贵州省黔东南苗族侗族自治州丹寨县', 'gzsqdnmzdzzzzdzx');
INSERT INTO `t_system_region` VALUES ('7210', '5227', '黔南布依族苗族自治州', '522700', '贵州省黔南布依族苗族自治州', 'gzsqnbyzmzzzz');
INSERT INTO `t_system_region` VALUES ('7211', '522701', '都匀市', '522701', '贵州省黔南布依族苗族自治州都匀市', 'gzsqnbyzmzzzzdys');
INSERT INTO `t_system_region` VALUES ('7212', '522702', '福泉市', '522702', '贵州省黔南布依族苗族自治州福泉市', 'gzsqnbyzmzzzzfqs');
INSERT INTO `t_system_region` VALUES ('7213', '522722', '荔波县', '522722', '贵州省黔南布依族苗族自治州荔波县', 'gzsqnbyzmzzzzlbx');
INSERT INTO `t_system_region` VALUES ('7214', '522723', '贵定县', '522723', '贵州省黔南布依族苗族自治州贵定县', 'gzsqnbyzmzzzzgdx');
INSERT INTO `t_system_region` VALUES ('7215', '522725', '瓮安县', '522725', '贵州省黔南布依族苗族自治州瓮安县', 'gzsqnbyzmzzzzwax');
INSERT INTO `t_system_region` VALUES ('7216', '522726', '独山县', '522726', '贵州省黔南布依族苗族自治州独山县', 'gzsqnbyzmzzzzdsx');
INSERT INTO `t_system_region` VALUES ('7217', '522727', '平塘县', '522727', '贵州省黔南布依族苗族自治州平塘县', 'gzsqnbyzmzzzzptx');
INSERT INTO `t_system_region` VALUES ('7218', '522728', '罗甸县', '522728', '贵州省黔南布依族苗族自治州罗甸县', 'gzsqnbyzmzzzzldx');
INSERT INTO `t_system_region` VALUES ('7219', '522729', '长顺县', '522729', '贵州省黔南布依族苗族自治州长顺县', 'gzsqnbyzmzzzzzsx');
INSERT INTO `t_system_region` VALUES ('7220', '522730', '龙里县', '522730', '贵州省黔南布依族苗族自治州龙里县', 'gzsqnbyzmzzzzllx');
INSERT INTO `t_system_region` VALUES ('7221', '522731', '惠水县', '522731', '贵州省黔南布依族苗族自治州惠水县', 'gzsqnbyzmzzzzhsx');
INSERT INTO `t_system_region` VALUES ('7222', '522732', '三都水族自治县', '522732', '贵州省黔南布依族苗族自治州三都水族自治县', 'gzsqnbyzmzzzzsdszzzx');
INSERT INTO `t_system_region` VALUES ('7223', '53', '云南省', '530000', '云南省', 'yns');
INSERT INTO `t_system_region` VALUES ('7224', '5301', '昆明市', '530100', '云南省昆明市', 'ynskms');
INSERT INTO `t_system_region` VALUES ('7225', '530101', '市辖区', '530101', '云南省昆明市市辖区', 'ynskmssxq');
INSERT INTO `t_system_region` VALUES ('7226', '530102', '五华区', '530102', '云南省昆明市五华区', 'ynskmswhq');
INSERT INTO `t_system_region` VALUES ('7227', '530103', '盘龙区', '530103', '云南省昆明市盘龙区', 'ynskmsplq');
INSERT INTO `t_system_region` VALUES ('7228', '530111', '官渡区', '530111', '云南省昆明市官渡区', 'ynskmsgdq');
INSERT INTO `t_system_region` VALUES ('7229', '530112', '西山区', '530112', '云南省昆明市西山区', 'ynskmsxsq');
INSERT INTO `t_system_region` VALUES ('7230', '530113', '东川区', '530113', '云南省昆明市东川区', 'ynskmsdcq');
INSERT INTO `t_system_region` VALUES ('7231', '530121', '呈贡县', '530121', '云南省昆明市呈贡县', 'ynskmscgx');
INSERT INTO `t_system_region` VALUES ('7232', '530122', '晋宁县', '530122', '云南省昆明市晋宁县', 'ynskmsjnx');
INSERT INTO `t_system_region` VALUES ('7233', '530124', '富民县', '530124', '云南省昆明市富民县', 'ynskmsfmx');
INSERT INTO `t_system_region` VALUES ('7234', '530125', '宜良县', '530125', '云南省昆明市宜良县', 'ynskmsylx');
INSERT INTO `t_system_region` VALUES ('7235', '530126', '石林彝族自治县', '530126', '云南省昆明市石林彝族自治县', 'ynskmsslyzzzx');
INSERT INTO `t_system_region` VALUES ('7236', '530127', '嵩明县', '530127', '云南省昆明市嵩明县', 'ynskmssmx');
INSERT INTO `t_system_region` VALUES ('7237', '530128', '禄劝彝族苗族自治县', '530128', '云南省昆明市禄劝彝族苗族自治县', 'ynskmslqyzmzzzx');
INSERT INTO `t_system_region` VALUES ('7238', '530129', '寻甸回族彝族自治县', '530129', '云南省昆明市寻甸回族彝族自治县', 'ynskmsxdhzyzzzx');
INSERT INTO `t_system_region` VALUES ('7239', '530181', '安宁市', '530181', '云南省昆明市安宁市', 'ynskmsans');
INSERT INTO `t_system_region` VALUES ('7240', '5303', '曲靖市', '530300', '云南省曲靖市', 'ynsqjs');
INSERT INTO `t_system_region` VALUES ('7241', '530301', '市辖区', '530301', '云南省曲靖市市辖区', 'ynsqjssxq');
INSERT INTO `t_system_region` VALUES ('7242', '530302', '麒麟区', '530302', '云南省曲靖市麒麟区', 'ynsqjsqlq');
INSERT INTO `t_system_region` VALUES ('7243', '530321', '马龙县', '530321', '云南省曲靖市马龙县', 'ynsqjsmlx');
INSERT INTO `t_system_region` VALUES ('7244', '530322', '陆良县', '530322', '云南省曲靖市陆良县', 'ynsqjsllx');
INSERT INTO `t_system_region` VALUES ('7245', '530323', '师宗县', '530323', '云南省曲靖市师宗县', 'ynsqjsszx');
INSERT INTO `t_system_region` VALUES ('7246', '530324', '罗平县', '530324', '云南省曲靖市罗平县', 'ynsqjslpx');
INSERT INTO `t_system_region` VALUES ('7247', '530325', '富源县', '530325', '云南省曲靖市富源县', 'ynsqjsfyx');
INSERT INTO `t_system_region` VALUES ('7248', '530326', '会泽县', '530326', '云南省曲靖市会泽县', 'ynsqjshzx');
INSERT INTO `t_system_region` VALUES ('7249', '530328', '沾益县', '530328', '云南省曲靖市沾益县', 'ynsqjszyx');
INSERT INTO `t_system_region` VALUES ('7250', '530381', '宣威市', '530381', '云南省曲靖市宣威市', 'ynsqjsxws');
INSERT INTO `t_system_region` VALUES ('7251', '5304', '玉溪市', '530400', '云南省玉溪市', 'ynsyxs');
INSERT INTO `t_system_region` VALUES ('7252', '530401', '市辖区', '530401', '云南省玉溪市市辖区', 'ynsyxssxq');
INSERT INTO `t_system_region` VALUES ('7253', '530402', '红塔区', '530402', '云南省玉溪市红塔区', 'ynsyxshtq');
INSERT INTO `t_system_region` VALUES ('7254', '530421', '江川县', '530421', '云南省玉溪市江川县', 'ynsyxsjcx');
INSERT INTO `t_system_region` VALUES ('7255', '530422', '澄江县', '530422', '云南省玉溪市澄江县', 'ynsyxscjx');
INSERT INTO `t_system_region` VALUES ('7256', '530423', '通海县', '530423', '云南省玉溪市通海县', 'ynsyxsthx');
INSERT INTO `t_system_region` VALUES ('7257', '530424', '华宁县', '530424', '云南省玉溪市华宁县', 'ynsyxshnx');
INSERT INTO `t_system_region` VALUES ('7258', '530425', '易门县', '530425', '云南省玉溪市易门县', 'ynsyxsymx');
INSERT INTO `t_system_region` VALUES ('7259', '530426', '峨山彝族自治县', '530426', '云南省玉溪市峨山彝族自治县', 'ynsyxsesyzzzx');
INSERT INTO `t_system_region` VALUES ('7260', '530427', '新平彝族傣族自治县', '530427', '云南省玉溪市新平彝族傣族自治县', 'ynsyxsxpyzdzzzx');
INSERT INTO `t_system_region` VALUES ('7261', '530428', '元江哈尼族彝族傣族自治县', '530428', '云南省玉溪市元江哈尼族彝族傣族自治县', 'ynsyxsyjhnzyzdzzzx');
INSERT INTO `t_system_region` VALUES ('7262', '5305', '保山市', '530500', '云南省保山市', 'ynsbss');
INSERT INTO `t_system_region` VALUES ('7263', '530501', '市辖区', '530501', '云南省保山市市辖区', 'ynsbsssxq');
INSERT INTO `t_system_region` VALUES ('7264', '530502', '隆阳区', '530502', '云南省保山市隆阳区', 'ynsbsslyq');
INSERT INTO `t_system_region` VALUES ('7265', '530521', '施甸县', '530521', '云南省保山市施甸县', 'ynsbsssdx');
INSERT INTO `t_system_region` VALUES ('7266', '530522', '腾冲县', '530522', '云南省保山市腾冲县', 'ynsbsstcx');
INSERT INTO `t_system_region` VALUES ('7267', '530523', '龙陵县', '530523', '云南省保山市龙陵县', 'ynsbssllx');
INSERT INTO `t_system_region` VALUES ('7268', '530524', '昌宁县', '530524', '云南省保山市昌宁县', 'ynsbsscnx');
INSERT INTO `t_system_region` VALUES ('7269', '5306', '昭通市', '530600', '云南省昭通市', 'ynszts');
INSERT INTO `t_system_region` VALUES ('7270', '530601', '市辖区', '530601', '云南省昭通市市辖区', 'ynsztssxq');
INSERT INTO `t_system_region` VALUES ('7271', '530602', '昭阳区', '530602', '云南省昭通市昭阳区', 'ynsztszyq');
INSERT INTO `t_system_region` VALUES ('7272', '530621', '鲁甸县', '530621', '云南省昭通市鲁甸县', 'ynsztsldx');
INSERT INTO `t_system_region` VALUES ('7273', '530622', '巧家县', '530622', '云南省昭通市巧家县', 'ynsztsqjx');
INSERT INTO `t_system_region` VALUES ('7274', '530623', '盐津县', '530623', '云南省昭通市盐津县', 'ynsztsyjx');
INSERT INTO `t_system_region` VALUES ('7275', '530624', '大关县', '530624', '云南省昭通市大关县', 'ynsztsdgx');
INSERT INTO `t_system_region` VALUES ('7276', '530625', '永善县', '530625', '云南省昭通市永善县', 'ynsztsysx');
INSERT INTO `t_system_region` VALUES ('7277', '530626', '绥江县', '530626', '云南省昭通市绥江县', 'ynsztssjx');
INSERT INTO `t_system_region` VALUES ('7278', '530627', '镇雄县', '530627', '云南省昭通市镇雄县', 'ynsztszxx');
INSERT INTO `t_system_region` VALUES ('7279', '530628', '彝良县', '530628', '云南省昭通市彝良县', 'ynsztsylx');
INSERT INTO `t_system_region` VALUES ('7280', '530629', '威信县', '530629', '云南省昭通市威信县', 'ynsztswxx');
INSERT INTO `t_system_region` VALUES ('7281', '530630', '水富县', '530630', '云南省昭通市水富县', 'ynsztssfx');
INSERT INTO `t_system_region` VALUES ('7282', '5307', '丽江市', '530700', '云南省丽江市', 'ynsljs');
INSERT INTO `t_system_region` VALUES ('7283', '530701', '市辖区', '530701', '云南省丽江市市辖区', 'ynsljssxq');
INSERT INTO `t_system_region` VALUES ('7284', '530702', '古城区', '530702', '云南省丽江市古城区', 'ynsljsgcq');
INSERT INTO `t_system_region` VALUES ('7285', '530721', '玉龙纳西族自治县', '530721', '云南省丽江市玉龙纳西族自治县', 'ynsljsylnxzzzx');
INSERT INTO `t_system_region` VALUES ('7286', '530722', '永胜县', '530722', '云南省丽江市永胜县', 'ynsljsysx');
INSERT INTO `t_system_region` VALUES ('7287', '530723', '华坪县', '530723', '云南省丽江市华坪县', 'ynsljshpx');
INSERT INTO `t_system_region` VALUES ('7288', '530724', '宁蒗彝族自治县', '530724', '云南省丽江市宁蒗彝族自治县', 'ynsljsnlyzzzx');
INSERT INTO `t_system_region` VALUES ('7289', '5308', '思茅市', '530800', '云南省思茅市', 'ynssms');
INSERT INTO `t_system_region` VALUES ('7290', '530801', '市辖区', '530801', '云南省思茅市市辖区', 'ynssmssxq');
INSERT INTO `t_system_region` VALUES ('7291', '530802', '翠云区', '530802', '云南省思茅市翠云区', 'ynssmscyq');
INSERT INTO `t_system_region` VALUES ('7292', '530821', '普洱哈尼族彝族自治县', '530821', '云南省思茅市普洱哈尼族彝族自治县', 'ynssmspehnzyzzzx');
INSERT INTO `t_system_region` VALUES ('7293', '530822', '墨江哈尼族自治县', '530822', '云南省思茅市墨江哈尼族自治县', 'ynssmsmjhnzzzx');
INSERT INTO `t_system_region` VALUES ('7294', '530823', '景东彝族自治县', '530823', '云南省思茅市景东彝族自治县', 'ynssmsjdyzzzx');
INSERT INTO `t_system_region` VALUES ('7295', '530824', '景谷傣族彝族自治县', '530824', '云南省思茅市景谷傣族彝族自治县', 'ynssmsjgdzyzzzx');
INSERT INTO `t_system_region` VALUES ('7296', '530825', '镇沅彝族哈尼族拉祜族自治县', '530825', '云南省思茅市镇沅彝族哈尼族拉祜族自治县', 'ynssmszyyzhnzlhzzzx');
INSERT INTO `t_system_region` VALUES ('7297', '530826', '江城哈尼族彝族自治县', '530826', '云南省思茅市江城哈尼族彝族自治县', 'ynssmsjchnzyzzzx');
INSERT INTO `t_system_region` VALUES ('7298', '530827', '孟连傣族拉祜族佤族自治县', '530827', '云南省思茅市孟连傣族拉祜族佤族自治县', 'ynssmsmldzlhzwzzzx');
INSERT INTO `t_system_region` VALUES ('7299', '530828', '澜沧拉祜族自治县', '530828', '云南省思茅市澜沧拉祜族自治县', 'ynssmslclhzzzx');
INSERT INTO `t_system_region` VALUES ('7300', '530829', '西盟佤族自治县', '530829', '云南省思茅市西盟佤族自治县', 'ynssmsxmwzzzx');
INSERT INTO `t_system_region` VALUES ('7301', '5309', '临沧市', '530900', '云南省临沧市', 'ynslcs');
INSERT INTO `t_system_region` VALUES ('7302', '530901', '市辖区', '530901', '云南省临沧市市辖区', 'ynslcssxq');
INSERT INTO `t_system_region` VALUES ('7303', '530902', '临翔区', '530902', '云南省临沧市临翔区', 'ynslcslxq');
INSERT INTO `t_system_region` VALUES ('7304', '530921', '凤庆县', '530921', '云南省临沧市凤庆县', 'ynslcsfqx');
INSERT INTO `t_system_region` VALUES ('7305', '530922', '云县', '530922', '云南省临沧市云县', 'ynslcsyx');
INSERT INTO `t_system_region` VALUES ('7306', '530923', '永德县', '530923', '云南省临沧市永德县', 'ynslcsydx');
INSERT INTO `t_system_region` VALUES ('7307', '530924', '镇康县', '530924', '云南省临沧市镇康县', 'ynslcszkx');
INSERT INTO `t_system_region` VALUES ('7308', '530925', '双江拉祜族佤族布朗族傣族自治县', '530925', '云南省临沧市双江拉祜族佤族布朗族傣族自治县', 'ynslcssjlhzwzblzdzzzx');
INSERT INTO `t_system_region` VALUES ('7309', '530926', '耿马傣族佤族自治县', '530926', '云南省临沧市耿马傣族佤族自治县', 'ynslcsgmdzwzzzx');
INSERT INTO `t_system_region` VALUES ('7310', '530927', '沧源佤族自治县', '530927', '云南省临沧市沧源佤族自治县', 'ynslcscywzzzx');
INSERT INTO `t_system_region` VALUES ('7311', '5323', '楚雄彝族自治州', '532300', '云南省楚雄彝族自治州', 'ynscxyzzzz');
INSERT INTO `t_system_region` VALUES ('7312', '532301', '楚雄市', '532301', '云南省楚雄彝族自治州楚雄市', 'ynscxyzzzzcxs');
INSERT INTO `t_system_region` VALUES ('7313', '532322', '双柏县', '532322', '云南省楚雄彝族自治州双柏县', 'ynscxyzzzzsbx');
INSERT INTO `t_system_region` VALUES ('7314', '532323', '牟定县', '532323', '云南省楚雄彝族自治州牟定县', 'ynscxyzzzzmdx');
INSERT INTO `t_system_region` VALUES ('7315', '532324', '南华县', '532324', '云南省楚雄彝族自治州南华县', 'ynscxyzzzznhx');
INSERT INTO `t_system_region` VALUES ('7316', '532325', '姚安县', '532325', '云南省楚雄彝族自治州姚安县', 'ynscxyzzzzyax');
INSERT INTO `t_system_region` VALUES ('7317', '532326', '大姚县', '532326', '云南省楚雄彝族自治州大姚县', 'ynscxyzzzzdyx');
INSERT INTO `t_system_region` VALUES ('7318', '532327', '永仁县', '532327', '云南省楚雄彝族自治州永仁县', 'ynscxyzzzzyrx');
INSERT INTO `t_system_region` VALUES ('7319', '532328', '元谋县', '532328', '云南省楚雄彝族自治州元谋县', 'ynscxyzzzzymx');
INSERT INTO `t_system_region` VALUES ('7320', '532329', '武定县', '532329', '云南省楚雄彝族自治州武定县', 'ynscxyzzzzwdx');
INSERT INTO `t_system_region` VALUES ('7321', '532331', '禄丰县', '532331', '云南省楚雄彝族自治州禄丰县', 'ynscxyzzzzlfx');
INSERT INTO `t_system_region` VALUES ('7322', '5325', '红河哈尼族彝族自治州', '532500', '云南省红河哈尼族彝族自治州', 'ynshhhnzyzzzz');
INSERT INTO `t_system_region` VALUES ('7323', '532501', '个旧市', '532501', '云南省红河哈尼族彝族自治州个旧市', 'ynshhhnzyzzzzgjs');
INSERT INTO `t_system_region` VALUES ('7324', '532502', '开远市', '532502', '云南省红河哈尼族彝族自治州开远市', 'ynshhhnzyzzzzkys');
INSERT INTO `t_system_region` VALUES ('7325', '532522', '蒙自县', '532522', '云南省红河哈尼族彝族自治州蒙自县', 'ynshhhnzyzzzzmzx');
INSERT INTO `t_system_region` VALUES ('7326', '532523', '屏边苗族自治县', '532523', '云南省红河哈尼族彝族自治州屏边苗族自治县', 'ynshhhnzyzzzzpbmzzzx');
INSERT INTO `t_system_region` VALUES ('7327', '532524', '建水县', '532524', '云南省红河哈尼族彝族自治州建水县', 'ynshhhnzyzzzzjsx');
INSERT INTO `t_system_region` VALUES ('7328', '532525', '石屏县', '532525', '云南省红河哈尼族彝族自治州石屏县', 'ynshhhnzyzzzzspx');
INSERT INTO `t_system_region` VALUES ('7329', '532526', '弥勒县', '532526', '云南省红河哈尼族彝族自治州弥勒县', 'ynshhhnzyzzzzmlx');
INSERT INTO `t_system_region` VALUES ('7330', '532527', '泸西县', '532527', '云南省红河哈尼族彝族自治州泸西县', 'ynshhhnzyzzzzlxx');
INSERT INTO `t_system_region` VALUES ('7331', '532528', '元阳县', '532528', '云南省红河哈尼族彝族自治州元阳县', 'ynshhhnzyzzzzyyx');
INSERT INTO `t_system_region` VALUES ('7332', '532529', '红河县', '532529', '云南省红河哈尼族彝族自治州红河县', 'ynshhhnzyzzzzhhx');
INSERT INTO `t_system_region` VALUES ('7333', '532530', '金平苗族瑶族傣族自治县', '532530', '云南省红河哈尼族彝族自治州金平苗族瑶族傣族自治县', 'ynshhhnzyzzzzjpmzyzdzzzx');
INSERT INTO `t_system_region` VALUES ('7334', '532531', '绿春县', '532531', '云南省红河哈尼族彝族自治州绿春县', 'ynshhhnzyzzzzlcx');
INSERT INTO `t_system_region` VALUES ('7335', '532532', '河口瑶族自治县', '532532', '云南省红河哈尼族彝族自治州河口瑶族自治县', 'ynshhhnzyzzzzhkyzzzx');
INSERT INTO `t_system_region` VALUES ('7336', '5326', '文山壮族苗族自治州', '532600', '云南省文山壮族苗族自治州', 'ynswszzmzzzz');
INSERT INTO `t_system_region` VALUES ('7337', '532621', '文山县', '532621', '云南省文山壮族苗族自治州文山县', 'ynswszzmzzzzwsx');
INSERT INTO `t_system_region` VALUES ('7338', '532622', '砚山县', '532622', '云南省文山壮族苗族自治州砚山县', 'ynswszzmzzzzysx');
INSERT INTO `t_system_region` VALUES ('7339', '532623', '西畴县', '532623', '云南省文山壮族苗族自治州西畴县', 'ynswszzmzzzzxcx');
INSERT INTO `t_system_region` VALUES ('7340', '532624', '麻栗坡县', '532624', '云南省文山壮族苗族自治州麻栗坡县', 'ynswszzmzzzzmlpx');
INSERT INTO `t_system_region` VALUES ('7341', '532625', '马关县', '532625', '云南省文山壮族苗族自治州马关县', 'ynswszzmzzzzmgx');
INSERT INTO `t_system_region` VALUES ('7342', '532626', '丘北县', '532626', '云南省文山壮族苗族自治州丘北县', 'ynswszzmzzzzqbx');
INSERT INTO `t_system_region` VALUES ('7343', '532627', '广南县', '532627', '云南省文山壮族苗族自治州广南县', 'ynswszzmzzzzgnx');
INSERT INTO `t_system_region` VALUES ('7344', '532628', '富宁县', '532628', '云南省文山壮族苗族自治州富宁县', 'ynswszzmzzzzfnx');
INSERT INTO `t_system_region` VALUES ('7345', '5328', '西双版纳傣族自治州', '532800', '云南省西双版纳傣族自治州', 'ynsxsbndzzzz');
INSERT INTO `t_system_region` VALUES ('7346', '532801', '景洪市', '532801', '云南省西双版纳傣族自治州景洪市', 'ynsxsbndzzzzjhs');
INSERT INTO `t_system_region` VALUES ('7347', '532822', '勐海县', '532822', '云南省西双版纳傣族自治州勐海县', 'ynsxsbndzzzzmhx');
INSERT INTO `t_system_region` VALUES ('7348', '532823', '勐腊县', '532823', '云南省西双版纳傣族自治州勐腊县', 'ynsxsbndzzzzmlx');
INSERT INTO `t_system_region` VALUES ('7349', '5329', '大理白族自治州', '532900', '云南省大理白族自治州', 'ynsdlbzzzz');
INSERT INTO `t_system_region` VALUES ('7350', '532901', '大理市', '532901', '云南省大理白族自治州大理市', 'ynsdlbzzzzdls');
INSERT INTO `t_system_region` VALUES ('7351', '532922', '漾濞彝族自治县', '532922', '云南省大理白族自治州漾濞彝族自治县', 'ynsdlbzzzzybyzzzx');
INSERT INTO `t_system_region` VALUES ('7352', '532923', '祥云县', '532923', '云南省大理白族自治州祥云县', 'ynsdlbzzzzxyx');
INSERT INTO `t_system_region` VALUES ('7353', '532924', '宾川县', '532924', '云南省大理白族自治州宾川县', 'ynsdlbzzzzbcx');
INSERT INTO `t_system_region` VALUES ('7354', '532925', '弥渡县', '532925', '云南省大理白族自治州弥渡县', 'ynsdlbzzzzmdx');
INSERT INTO `t_system_region` VALUES ('7355', '532926', '南涧彝族自治县', '532926', '云南省大理白族自治州南涧彝族自治县', 'ynsdlbzzzznjyzzzx');
INSERT INTO `t_system_region` VALUES ('7356', '532927', '巍山彝族回族自治县', '532927', '云南省大理白族自治州巍山彝族回族自治县', 'ynsdlbzzzzwsyzhzzzx');
INSERT INTO `t_system_region` VALUES ('7357', '532928', '永平县', '532928', '云南省大理白族自治州永平县', 'ynsdlbzzzzypx');
INSERT INTO `t_system_region` VALUES ('7358', '532929', '云龙县', '532929', '云南省大理白族自治州云龙县', 'ynsdlbzzzzylx');
INSERT INTO `t_system_region` VALUES ('7359', '532930', '洱源县', '532930', '云南省大理白族自治州洱源县', 'ynsdlbzzzzeyx');
INSERT INTO `t_system_region` VALUES ('7360', '532931', '剑川县', '532931', '云南省大理白族自治州剑川县', 'ynsdlbzzzzjcx');
INSERT INTO `t_system_region` VALUES ('7361', '532932', '鹤庆县', '532932', '云南省大理白族自治州鹤庆县', 'ynsdlbzzzzhqx');
INSERT INTO `t_system_region` VALUES ('7362', '5331', '德宏傣族景颇族自治州', '533100', '云南省德宏傣族景颇族自治州', 'ynsdhdzjpzzzz');
INSERT INTO `t_system_region` VALUES ('7363', '533102', '瑞丽市', '533102', '云南省德宏傣族景颇族自治州瑞丽市', 'ynsdhdzjpzzzzrls');
INSERT INTO `t_system_region` VALUES ('7364', '533103', '潞西市', '533103', '云南省德宏傣族景颇族自治州潞西市', 'ynsdhdzjpzzzzlxs');
INSERT INTO `t_system_region` VALUES ('7365', '533122', '梁河县', '533122', '云南省德宏傣族景颇族自治州梁河县', 'ynsdhdzjpzzzzlhx');
INSERT INTO `t_system_region` VALUES ('7366', '533123', '盈江县', '533123', '云南省德宏傣族景颇族自治州盈江县', 'ynsdhdzjpzzzzyjx');
INSERT INTO `t_system_region` VALUES ('7367', '533124', '陇川县', '533124', '云南省德宏傣族景颇族自治州陇川县', 'ynsdhdzjpzzzzlcx');
INSERT INTO `t_system_region` VALUES ('7368', '5333', '怒江傈僳族自治州', '533300', '云南省怒江傈僳族自治州', 'ynsnjlszzzz');
INSERT INTO `t_system_region` VALUES ('7369', '533321', '泸水县', '533321', '云南省怒江傈僳族自治州泸水县', 'ynsnjlszzzzlsx');
INSERT INTO `t_system_region` VALUES ('7370', '533323', '福贡县', '533323', '云南省怒江傈僳族自治州福贡县', 'ynsnjlszzzzfgx');
INSERT INTO `t_system_region` VALUES ('7371', '533324', '贡山独龙族怒族自治县', '533324', '云南省怒江傈僳族自治州贡山独龙族怒族自治县', 'ynsnjlszzzzgsdlznzzzx');
INSERT INTO `t_system_region` VALUES ('7372', '533325', '兰坪白族普米族自治县', '533325', '云南省怒江傈僳族自治州兰坪白族普米族自治县', 'ynsnjlszzzzlpbzpmzzzx');
INSERT INTO `t_system_region` VALUES ('7373', '5334', '迪庆藏族自治州', '533400', '云南省迪庆藏族自治州', 'ynsdqzzzzz');
INSERT INTO `t_system_region` VALUES ('7374', '533421', '香格里拉县', '533421', '云南省迪庆藏族自治州香格里拉县', 'ynsdqzzzzzxgllx');
INSERT INTO `t_system_region` VALUES ('7375', '533422', '德钦县', '533422', '云南省迪庆藏族自治州德钦县', 'ynsdqzzzzzdqx');
INSERT INTO `t_system_region` VALUES ('7376', '533423', '维西傈僳族自治县', '533423', '云南省迪庆藏族自治州维西傈僳族自治县', 'ynsdqzzzzzwxlszzzx');
INSERT INTO `t_system_region` VALUES ('7377', '54', '西藏自治区', '540000', '西藏自治区', 'xzzzq');
INSERT INTO `t_system_region` VALUES ('7378', '5401', '拉萨市', '540100', '西藏自治区拉萨市', 'xzzzqlss');
INSERT INTO `t_system_region` VALUES ('7379', '540101', '市辖区', '540101', '西藏自治区拉萨市市辖区', 'xzzzqlsssxq');
INSERT INTO `t_system_region` VALUES ('7380', '540102', '城关区', '540102', '西藏自治区拉萨市城关区', 'xzzzqlsscgq');
INSERT INTO `t_system_region` VALUES ('7381', '540121', '林周县', '540121', '西藏自治区拉萨市林周县', 'xzzzqlsslzx');
INSERT INTO `t_system_region` VALUES ('7382', '540122', '当雄县', '540122', '西藏自治区拉萨市当雄县', 'xzzzqlssdxx');
INSERT INTO `t_system_region` VALUES ('7383', '540123', '尼木县', '540123', '西藏自治区拉萨市尼木县', 'xzzzqlssnmx');
INSERT INTO `t_system_region` VALUES ('7384', '540124', '曲水县', '540124', '西藏自治区拉萨市曲水县', 'xzzzqlssqsx');
INSERT INTO `t_system_region` VALUES ('7385', '540125', '堆龙德庆县', '540125', '西藏自治区拉萨市堆龙德庆县', 'xzzzqlssdldqx');
INSERT INTO `t_system_region` VALUES ('7386', '540126', '达孜县', '540126', '西藏自治区拉萨市达孜县', 'xzzzqlssdzx');
INSERT INTO `t_system_region` VALUES ('7387', '540127', '墨竹工卡县', '540127', '西藏自治区拉萨市墨竹工卡县', 'xzzzqlssmzgkx');
INSERT INTO `t_system_region` VALUES ('7388', '5421', '昌都地区', '542100', '西藏自治区昌都地区', 'xzzzqcddq');
INSERT INTO `t_system_region` VALUES ('7389', '542121', '昌都县', '542121', '西藏自治区昌都地区昌都县', 'xzzzqcddqcdx');
INSERT INTO `t_system_region` VALUES ('7390', '542122', '江达县', '542122', '西藏自治区昌都地区江达县', 'xzzzqcddqjdx');
INSERT INTO `t_system_region` VALUES ('7391', '542123', '贡觉县', '542123', '西藏自治区昌都地区贡觉县', 'xzzzqcddqgjx');
INSERT INTO `t_system_region` VALUES ('7392', '542124', '类乌齐县', '542124', '西藏自治区昌都地区类乌齐县', 'xzzzqcddqlwqx');
INSERT INTO `t_system_region` VALUES ('7393', '542125', '丁青县', '542125', '西藏自治区昌都地区丁青县', 'xzzzqcddqdqx');
INSERT INTO `t_system_region` VALUES ('7394', '542126', '察雅县', '542126', '西藏自治区昌都地区察雅县', 'xzzzqcddqcyx');
INSERT INTO `t_system_region` VALUES ('7395', '542127', '八宿县', '542127', '西藏自治区昌都地区八宿县', 'xzzzqcddqbsx');
INSERT INTO `t_system_region` VALUES ('7396', '542128', '左贡县', '542128', '西藏自治区昌都地区左贡县', 'xzzzqcddqzgx');
INSERT INTO `t_system_region` VALUES ('7397', '542129', '芒康县', '542129', '西藏自治区昌都地区芒康县', 'xzzzqcddqmkx');
INSERT INTO `t_system_region` VALUES ('7398', '542132', '洛隆县', '542132', '西藏自治区昌都地区洛隆县', 'xzzzqcddqllx');
INSERT INTO `t_system_region` VALUES ('7399', '542133', '边坝县', '542133', '西藏自治区昌都地区边坝县', 'xzzzqcddqbbx');
INSERT INTO `t_system_region` VALUES ('7400', '5422', '山南地区', '542200', '西藏自治区山南地区', 'xzzzqsndq');
INSERT INTO `t_system_region` VALUES ('7401', '542221', '乃东县', '542221', '西藏自治区山南地区乃东县', 'xzzzqsndqndx');
INSERT INTO `t_system_region` VALUES ('7402', '542222', '扎囊县', '542222', '西藏自治区山南地区扎囊县', 'xzzzqsndqznx');
INSERT INTO `t_system_region` VALUES ('7403', '542223', '贡嘎县', '542223', '西藏自治区山南地区贡嘎县', 'xzzzqsndqggx');
INSERT INTO `t_system_region` VALUES ('7404', '542224', '桑日县', '542224', '西藏自治区山南地区桑日县', 'xzzzqsndqsrx');
INSERT INTO `t_system_region` VALUES ('7405', '542225', '琼结县', '542225', '西藏自治区山南地区琼结县', 'xzzzqsndqqjx');
INSERT INTO `t_system_region` VALUES ('7406', '542226', '曲松县', '542226', '西藏自治区山南地区曲松县', 'xzzzqsndqqsx');
INSERT INTO `t_system_region` VALUES ('7407', '542227', '措美县', '542227', '西藏自治区山南地区措美县', 'xzzzqsndqcmx');
INSERT INTO `t_system_region` VALUES ('7408', '542228', '洛扎县', '542228', '西藏自治区山南地区洛扎县', 'xzzzqsndqlzx');
INSERT INTO `t_system_region` VALUES ('7409', '542229', '加查县', '542229', '西藏自治区山南地区加查县', 'xzzzqsndqjcx');
INSERT INTO `t_system_region` VALUES ('7410', '542231', '隆子县', '542231', '西藏自治区山南地区隆子县', 'xzzzqsndqlzx');
INSERT INTO `t_system_region` VALUES ('7411', '542232', '错那县', '542232', '西藏自治区山南地区错那县', 'xzzzqsndqcnx');
INSERT INTO `t_system_region` VALUES ('7412', '542233', '浪卡子县', '542233', '西藏自治区山南地区浪卡子县', 'xzzzqsndqlkzx');
INSERT INTO `t_system_region` VALUES ('7413', '5423', '日喀则地区', '542300', '西藏自治区日喀则地区', 'xzzzqrkzdq');
INSERT INTO `t_system_region` VALUES ('7414', '542301', '日喀则市', '542301', '西藏自治区日喀则地区日喀则市', 'xzzzqrkzdqrkzs');
INSERT INTO `t_system_region` VALUES ('7415', '542322', '南木林县', '542322', '西藏自治区日喀则地区南木林县', 'xzzzqrkzdqnmlx');
INSERT INTO `t_system_region` VALUES ('7416', '542323', '江孜县', '542323', '西藏自治区日喀则地区江孜县', 'xzzzqrkzdqjzx');
INSERT INTO `t_system_region` VALUES ('7417', '542324', '定日县', '542324', '西藏自治区日喀则地区定日县', 'xzzzqrkzdqdrx');
INSERT INTO `t_system_region` VALUES ('7418', '542325', '萨迦县', '542325', '西藏自治区日喀则地区萨迦县', 'xzzzqrkzdqsjx');
INSERT INTO `t_system_region` VALUES ('7419', '542326', '拉孜县', '542326', '西藏自治区日喀则地区拉孜县', 'xzzzqrkzdqlzx');
INSERT INTO `t_system_region` VALUES ('7420', '542327', '昂仁县', '542327', '西藏自治区日喀则地区昂仁县', 'xzzzqrkzdqarx');
INSERT INTO `t_system_region` VALUES ('7421', '542328', '谢通门县', '542328', '西藏自治区日喀则地区谢通门县', 'xzzzqrkzdqxtmx');
INSERT INTO `t_system_region` VALUES ('7422', '542329', '白朗县', '542329', '西藏自治区日喀则地区白朗县', 'xzzzqrkzdqblx');
INSERT INTO `t_system_region` VALUES ('7423', '542330', '仁布县', '542330', '西藏自治区日喀则地区仁布县', 'xzzzqrkzdqrbx');
INSERT INTO `t_system_region` VALUES ('7424', '542331', '康马县', '542331', '西藏自治区日喀则地区康马县', 'xzzzqrkzdqkmx');
INSERT INTO `t_system_region` VALUES ('7425', '542332', '定结县', '542332', '西藏自治区日喀则地区定结县', 'xzzzqrkzdqdjx');
INSERT INTO `t_system_region` VALUES ('7426', '542333', '仲巴县', '542333', '西藏自治区日喀则地区仲巴县', 'xzzzqrkzdqzbx');
INSERT INTO `t_system_region` VALUES ('7427', '542334', '亚东县', '542334', '西藏自治区日喀则地区亚东县', 'xzzzqrkzdqydx');
INSERT INTO `t_system_region` VALUES ('7428', '542335', '吉隆县', '542335', '西藏自治区日喀则地区吉隆县', 'xzzzqrkzdqjlx');
INSERT INTO `t_system_region` VALUES ('7429', '542336', '聂拉木县', '542336', '西藏自治区日喀则地区聂拉木县', 'xzzzqrkzdqnlmx');
INSERT INTO `t_system_region` VALUES ('7430', '542337', '萨嘎县', '542337', '西藏自治区日喀则地区萨嘎县', 'xzzzqrkzdqsgx');
INSERT INTO `t_system_region` VALUES ('7431', '542338', '岗巴县', '542338', '西藏自治区日喀则地区岗巴县', 'xzzzqrkzdqgbx');
INSERT INTO `t_system_region` VALUES ('7432', '5424', '那曲地区', '542400', '西藏自治区那曲地区', 'xzzzqnqdq');
INSERT INTO `t_system_region` VALUES ('7433', '542421', '那曲县', '542421', '西藏自治区那曲地区那曲县', 'xzzzqnqdqnqx');
INSERT INTO `t_system_region` VALUES ('7434', '542422', '嘉黎县', '542422', '西藏自治区那曲地区嘉黎县', 'xzzzqnqdqjlx');
INSERT INTO `t_system_region` VALUES ('7435', '542423', '比如县', '542423', '西藏自治区那曲地区比如县', 'xzzzqnqdqbrx');
INSERT INTO `t_system_region` VALUES ('7436', '542424', '聂荣县', '542424', '西藏自治区那曲地区聂荣县', 'xzzzqnqdqnrx');
INSERT INTO `t_system_region` VALUES ('7437', '542425', '安多县', '542425', '西藏自治区那曲地区安多县', 'xzzzqnqdqadx');
INSERT INTO `t_system_region` VALUES ('7438', '542426', '申扎县', '542426', '西藏自治区那曲地区申扎县', 'xzzzqnqdqszx');
INSERT INTO `t_system_region` VALUES ('7439', '542427', '索县', '542427', '西藏自治区那曲地区索县', 'xzzzqnqdqsx');
INSERT INTO `t_system_region` VALUES ('7440', '542428', '班戈县', '542428', '西藏自治区那曲地区班戈县', 'xzzzqnqdqbgx');
INSERT INTO `t_system_region` VALUES ('7441', '542429', '巴青县', '542429', '西藏自治区那曲地区巴青县', 'xzzzqnqdqbqx');
INSERT INTO `t_system_region` VALUES ('7442', '542430', '尼玛县', '542430', '西藏自治区那曲地区尼玛县', 'xzzzqnqdqnmx');
INSERT INTO `t_system_region` VALUES ('7443', '542431', '双湖特别区', '542431', '西藏自治区那曲地区双湖特别区', 'xzzzqnqdqshtbq');
INSERT INTO `t_system_region` VALUES ('7444', '5425', '阿里地区', '542500', '西藏自治区阿里地区', 'xzzzqaldq');
INSERT INTO `t_system_region` VALUES ('7445', '542521', '普兰县', '542521', '西藏自治区阿里地区普兰县', 'xzzzqaldqplx');
INSERT INTO `t_system_region` VALUES ('7446', '542522', '札达县', '542522', '西藏自治区阿里地区札达县', 'xzzzqaldqzdx');
INSERT INTO `t_system_region` VALUES ('7447', '542523', '噶尔县', '542523', '西藏自治区阿里地区噶尔县', 'xzzzqaldqgex');
INSERT INTO `t_system_region` VALUES ('7448', '542524', '日土县', '542524', '西藏自治区阿里地区日土县', 'xzzzqaldqrtx');
INSERT INTO `t_system_region` VALUES ('7449', '542525', '革吉县', '542525', '西藏自治区阿里地区革吉县', 'xzzzqaldqgjx');
INSERT INTO `t_system_region` VALUES ('7450', '542526', '改则县', '542526', '西藏自治区阿里地区改则县', 'xzzzqaldqgzx');
INSERT INTO `t_system_region` VALUES ('7451', '542527', '措勤县', '542527', '西藏自治区阿里地区措勤县', 'xzzzqaldqcqx');
INSERT INTO `t_system_region` VALUES ('7452', '5426', '林芝地区', '542600', '西藏自治区林芝地区', 'xzzzqlzdq');
INSERT INTO `t_system_region` VALUES ('7453', '542621', '林芝县', '542621', '西藏自治区林芝地区林芝县', 'xzzzqlzdqlzx');
INSERT INTO `t_system_region` VALUES ('7454', '542622', '工布江达县', '542622', '西藏自治区林芝地区工布江达县', 'xzzzqlzdqgbjdx');
INSERT INTO `t_system_region` VALUES ('7455', '542623', '米林县', '542623', '西藏自治区林芝地区米林县', 'xzzzqlzdqmlx');
INSERT INTO `t_system_region` VALUES ('7456', '542624', '墨脱县', '542624', '西藏自治区林芝地区墨脱县', 'xzzzqlzdqmtx');
INSERT INTO `t_system_region` VALUES ('7457', '542625', '波密县', '542625', '西藏自治区林芝地区波密县', 'xzzzqlzdqbmx');
INSERT INTO `t_system_region` VALUES ('7458', '542626', '察隅县', '542626', '西藏自治区林芝地区察隅县', 'xzzzqlzdqcyx');
INSERT INTO `t_system_region` VALUES ('7459', '542627', '朗县', '542627', '西藏自治区林芝地区朗县', 'xzzzqlzdqlx');
INSERT INTO `t_system_region` VALUES ('7460', '5427', '西藏驻格尔木办事处市级', '542700', '西藏自治区西藏驻格尔木办事处市级', 'xzzzqxzzgembscsj');
INSERT INTO `t_system_region` VALUES ('7461', '542701', '西藏驻格尔木办事处县级', '542701', '西藏自治区西藏驻格尔木办事处市级西藏驻格尔木办事处县级', 'xzzzqxzzgembscsjxzzgembscxj');
INSERT INTO `t_system_region` VALUES ('7462', '61', '陕西省', '610000', '陕西省', 'sxs');
INSERT INTO `t_system_region` VALUES ('7463', '6101', '西安市', '610100', '陕西省西安市', 'sxsxas');
INSERT INTO `t_system_region` VALUES ('7464', '610101', '市辖区', '610101', '陕西省西安市市辖区', 'sxsxassxq');
INSERT INTO `t_system_region` VALUES ('7465', '610102', '新城区', '610102', '陕西省西安市新城区', 'sxsxasxcq');
INSERT INTO `t_system_region` VALUES ('7466', '610103', '碑林区', '610103', '陕西省西安市碑林区', 'sxsxasblq');
INSERT INTO `t_system_region` VALUES ('7467', '610104', '莲湖区', '610104', '陕西省西安市莲湖区', 'sxsxaslhq');
INSERT INTO `t_system_region` VALUES ('7468', '610111', '灞桥区', '610111', '陕西省西安市灞桥区', 'sxsxasbqq');
INSERT INTO `t_system_region` VALUES ('7469', '610112', '未央区', '610112', '陕西省西安市未央区', 'sxsxaswyq');
INSERT INTO `t_system_region` VALUES ('7470', '610113', '雁塔区', '610113', '陕西省西安市雁塔区', 'sxsxasytq');
INSERT INTO `t_system_region` VALUES ('7471', '610114', '阎良区', '610114', '陕西省西安市阎良区', 'sxsxasylq');
INSERT INTO `t_system_region` VALUES ('7472', '610115', '临潼区', '610115', '陕西省西安市临潼区', 'sxsxasltq');
INSERT INTO `t_system_region` VALUES ('7473', '610116', '长安区', '610116', '陕西省西安市长安区', 'sxsxaszaq');
INSERT INTO `t_system_region` VALUES ('7474', '610122', '蓝田县', '610122', '陕西省西安市蓝田县', 'sxsxasltx');
INSERT INTO `t_system_region` VALUES ('7475', '610124', '周至县', '610124', '陕西省西安市周至县', 'sxsxaszzx');
INSERT INTO `t_system_region` VALUES ('7476', '610125', '户县', '610125', '陕西省西安市户县', 'sxsxashx');
INSERT INTO `t_system_region` VALUES ('7477', '610126', '高陵县', '610126', '陕西省西安市高陵县', 'sxsxasglx');
INSERT INTO `t_system_region` VALUES ('7478', '6102', '铜川市', '610200', '陕西省铜川市', 'sxstcs');
INSERT INTO `t_system_region` VALUES ('7479', '610201', '市辖区', '610201', '陕西省铜川市市辖区', 'sxstcssxq');
INSERT INTO `t_system_region` VALUES ('7480', '610202', '王益区', '610202', '陕西省铜川市王益区', 'sxstcswyq');
INSERT INTO `t_system_region` VALUES ('7481', '610203', '印台区', '610203', '陕西省铜川市印台区', 'sxstcsytq');
INSERT INTO `t_system_region` VALUES ('7482', '610204', '耀州区', '610204', '陕西省铜川市耀州区', 'sxstcsyzq');
INSERT INTO `t_system_region` VALUES ('7483', '610222', '宜君县', '610222', '陕西省铜川市宜君县', 'sxstcsyjx');
INSERT INTO `t_system_region` VALUES ('7484', '6103', '宝鸡市', '610300', '陕西省宝鸡市', 'sxsbjs');
INSERT INTO `t_system_region` VALUES ('7485', '610301', '市辖区', '610301', '陕西省宝鸡市市辖区', 'sxsbjssxq');
INSERT INTO `t_system_region` VALUES ('7486', '610302', '渭滨区', '610302', '陕西省宝鸡市渭滨区', 'sxsbjswbq');
INSERT INTO `t_system_region` VALUES ('7487', '610303', '金台区', '610303', '陕西省宝鸡市金台区', 'sxsbjsjtq');
INSERT INTO `t_system_region` VALUES ('7488', '610304', '陈仓区', '610304', '陕西省宝鸡市陈仓区', 'sxsbjsccq');
INSERT INTO `t_system_region` VALUES ('7489', '610322', '凤翔县', '610322', '陕西省宝鸡市凤翔县', 'sxsbjsfxx');
INSERT INTO `t_system_region` VALUES ('7490', '610323', '岐山县', '610323', '陕西省宝鸡市岐山县', 'sxsbjsqsx');
INSERT INTO `t_system_region` VALUES ('7491', '610324', '扶风县', '610324', '陕西省宝鸡市扶风县', 'sxsbjsffx');
INSERT INTO `t_system_region` VALUES ('7492', '610326', '眉县', '610326', '陕西省宝鸡市眉县', 'sxsbjsmx');
INSERT INTO `t_system_region` VALUES ('7493', '610327', '陇县', '610327', '陕西省宝鸡市陇县', 'sxsbjslx');
INSERT INTO `t_system_region` VALUES ('7494', '610328', '千阳县', '610328', '陕西省宝鸡市千阳县', 'sxsbjsqyx');
INSERT INTO `t_system_region` VALUES ('7495', '610329', '麟游县', '610329', '陕西省宝鸡市麟游县', 'sxsbjslyx');
INSERT INTO `t_system_region` VALUES ('7496', '610330', '凤县', '610330', '陕西省宝鸡市凤县', 'sxsbjsfx');
INSERT INTO `t_system_region` VALUES ('7497', '610331', '太白县', '610331', '陕西省宝鸡市太白县', 'sxsbjstbx');
INSERT INTO `t_system_region` VALUES ('7498', '6104', '咸阳市', '610400', '陕西省咸阳市', 'sxsxys');
INSERT INTO `t_system_region` VALUES ('7499', '610401', '市辖区', '610401', '陕西省咸阳市市辖区', 'sxsxyssxq');
INSERT INTO `t_system_region` VALUES ('7500', '610402', '秦都区', '610402', '陕西省咸阳市秦都区', 'sxsxysqdq');
INSERT INTO `t_system_region` VALUES ('7502', '610404', '渭城区', '610404', '陕西省咸阳市渭城区', 'sxsxyswcq');
INSERT INTO `t_system_region` VALUES ('7503', '610422', '三原县', '610422', '陕西省咸阳市三原县', 'sxsxyssyx');
INSERT INTO `t_system_region` VALUES ('7504', '610423', '泾阳县', '610423', '陕西省咸阳市泾阳县', 'sxsxysjyx');
INSERT INTO `t_system_region` VALUES ('7505', '610424', '乾县', '610424', '陕西省咸阳市乾县', 'sxsxysqx');
INSERT INTO `t_system_region` VALUES ('7506', '610425', '礼泉县', '610425', '陕西省咸阳市礼泉县', 'sxsxyslqx');
INSERT INTO `t_system_region` VALUES ('7507', '610426', '永寿县', '610426', '陕西省咸阳市永寿县', 'sxsxysysx');
INSERT INTO `t_system_region` VALUES ('7508', '610427', '彬县', '610427', '陕西省咸阳市彬县', 'sxsxysbx');
INSERT INTO `t_system_region` VALUES ('7509', '610428', '长武县', '610428', '陕西省咸阳市长武县', 'sxsxyszwx');
INSERT INTO `t_system_region` VALUES ('7510', '610429', '旬邑县', '610429', '陕西省咸阳市旬邑县', 'sxsxysxyx');
INSERT INTO `t_system_region` VALUES ('7511', '610430', '淳化县', '610430', '陕西省咸阳市淳化县', 'sxsxyschx');
INSERT INTO `t_system_region` VALUES ('7512', '610431', '武功县', '610431', '陕西省咸阳市武功县', 'sxsxyswgx');
INSERT INTO `t_system_region` VALUES ('7513', '610481', '兴平市', '610481', '陕西省咸阳市兴平市', 'sxsxysxps');
INSERT INTO `t_system_region` VALUES ('7514', '6105', '渭南市', '610500', '陕西省渭南市', 'sxswns');
INSERT INTO `t_system_region` VALUES ('7515', '610501', '市辖区', '610501', '陕西省渭南市市辖区', 'sxswnssxq');
INSERT INTO `t_system_region` VALUES ('7516', '610502', '临渭区', '610502', '陕西省渭南市临渭区', 'sxswnslwq');
INSERT INTO `t_system_region` VALUES ('7517', '610521', '华县', '610521', '陕西省渭南市华县', 'sxswnshx');
INSERT INTO `t_system_region` VALUES ('7518', '610522', '潼关县', '610522', '陕西省渭南市潼关县', 'sxswnstgx');
INSERT INTO `t_system_region` VALUES ('7519', '610523', '大荔县', '610523', '陕西省渭南市大荔县', 'sxswnsdlx');
INSERT INTO `t_system_region` VALUES ('7520', '610524', '合阳县', '610524', '陕西省渭南市合阳县', 'sxswnshyx');
INSERT INTO `t_system_region` VALUES ('7521', '610525', '澄城县', '610525', '陕西省渭南市澄城县', 'sxswnsccx');
INSERT INTO `t_system_region` VALUES ('7522', '610526', '蒲城县', '610526', '陕西省渭南市蒲城县', 'sxswnspcx');
INSERT INTO `t_system_region` VALUES ('7523', '610527', '白水县', '610527', '陕西省渭南市白水县', 'sxswnsbsx');
INSERT INTO `t_system_region` VALUES ('7524', '610528', '富平县', '610528', '陕西省渭南市富平县', 'sxswnsfpx');
INSERT INTO `t_system_region` VALUES ('7525', '610581', '韩城市', '610581', '陕西省渭南市韩城市', 'sxswnshcs');
INSERT INTO `t_system_region` VALUES ('7526', '610582', '华阴市', '610582', '陕西省渭南市华阴市', 'sxswnshys');
INSERT INTO `t_system_region` VALUES ('7527', '6106', '延安市', '610600', '陕西省延安市', 'sxsyas');
INSERT INTO `t_system_region` VALUES ('7528', '610601', '市辖区', '610601', '陕西省延安市市辖区', 'sxsyassxq');
INSERT INTO `t_system_region` VALUES ('7529', '610602', '宝塔区', '610602', '陕西省延安市宝塔区', 'sxsyasbtq');
INSERT INTO `t_system_region` VALUES ('7530', '610621', '延长县', '610621', '陕西省延安市延长县', 'sxsyasyzx');
INSERT INTO `t_system_region` VALUES ('7531', '610622', '延川县', '610622', '陕西省延安市延川县', 'sxsyasycx');
INSERT INTO `t_system_region` VALUES ('7532', '610623', '子长县', '610623', '陕西省延安市子长县', 'sxsyaszzx');
INSERT INTO `t_system_region` VALUES ('7533', '610624', '安塞县', '610624', '陕西省延安市安塞县', 'sxsyasasx');
INSERT INTO `t_system_region` VALUES ('7534', '610625', '志丹县', '610625', '陕西省延安市志丹县', 'sxsyaszdx');
INSERT INTO `t_system_region` VALUES ('7535', '610626', '吴起县', '610626', '陕西省延安市吴起县', 'sxsyaswqx');
INSERT INTO `t_system_region` VALUES ('7536', '610627', '甘泉县', '610627', '陕西省延安市甘泉县', 'sxsyasgqx');
INSERT INTO `t_system_region` VALUES ('7537', '610628', '富县', '610628', '陕西省延安市富县', 'sxsyasfx');
INSERT INTO `t_system_region` VALUES ('7538', '610629', '洛川县', '610629', '陕西省延安市洛川县', 'sxsyaslcx');
INSERT INTO `t_system_region` VALUES ('7539', '610630', '宜川县', '610630', '陕西省延安市宜川县', 'sxsyasycx');
INSERT INTO `t_system_region` VALUES ('7540', '610631', '黄龙县', '610631', '陕西省延安市黄龙县', 'sxsyashlx');
INSERT INTO `t_system_region` VALUES ('7541', '610632', '黄陵县', '610632', '陕西省延安市黄陵县', 'sxsyashlx');
INSERT INTO `t_system_region` VALUES ('7542', '6107', '汉中市', '610700', '陕西省汉中市', 'sxshzs');
INSERT INTO `t_system_region` VALUES ('7543', '610701', '市辖区', '610701', '陕西省汉中市市辖区', 'sxshzssxq');
INSERT INTO `t_system_region` VALUES ('7544', '610702', '汉台区', '610702', '陕西省汉中市汉台区', 'sxshzshtq');
INSERT INTO `t_system_region` VALUES ('7545', '610721', '南郑县', '610721', '陕西省汉中市南郑县', 'sxshzsnzx');
INSERT INTO `t_system_region` VALUES ('7546', '610722', '城固县', '610722', '陕西省汉中市城固县', 'sxshzscgx');
INSERT INTO `t_system_region` VALUES ('7547', '610723', '洋县', '610723', '陕西省汉中市洋县', 'sxshzsyx');
INSERT INTO `t_system_region` VALUES ('7548', '610724', '西乡县', '610724', '陕西省汉中市西乡县', 'sxshzsxxx');
INSERT INTO `t_system_region` VALUES ('7549', '610725', '勉县', '610725', '陕西省汉中市勉县', 'sxshzsmx');
INSERT INTO `t_system_region` VALUES ('7550', '610726', '宁强县', '610726', '陕西省汉中市宁强县', 'sxshzsnqx');
INSERT INTO `t_system_region` VALUES ('7551', '610727', '略阳县', '610727', '陕西省汉中市略阳县', 'sxshzslyx');
INSERT INTO `t_system_region` VALUES ('7552', '610728', '镇巴县', '610728', '陕西省汉中市镇巴县', 'sxshzszbx');
INSERT INTO `t_system_region` VALUES ('7553', '610729', '留坝县', '610729', '陕西省汉中市留坝县', 'sxshzslbx');
INSERT INTO `t_system_region` VALUES ('7554', '610730', '佛坪县', '610730', '陕西省汉中市佛坪县', 'sxshzsfpx');
INSERT INTO `t_system_region` VALUES ('7555', '6108', '榆林市', '610800', '陕西省榆林市', 'sxsyls');
INSERT INTO `t_system_region` VALUES ('7556', '610801', '市辖区', '610801', '陕西省榆林市市辖区', 'sxsylssxq');
INSERT INTO `t_system_region` VALUES ('7557', '610802', '榆阳区', '610802', '陕西省榆林市榆阳区', 'sxsylsyyq');
INSERT INTO `t_system_region` VALUES ('7558', '610821', '神木县', '610821', '陕西省榆林市神木县', 'sxsylssmx');
INSERT INTO `t_system_region` VALUES ('7559', '610822', '府谷县', '610822', '陕西省榆林市府谷县', 'sxsylsfgx');
INSERT INTO `t_system_region` VALUES ('7560', '610823', '横山县', '610823', '陕西省榆林市横山县', 'sxsylshsx');
INSERT INTO `t_system_region` VALUES ('7561', '610824', '靖边县', '610824', '陕西省榆林市靖边县', 'sxsylsjbx');
INSERT INTO `t_system_region` VALUES ('7562', '610825', '定边县', '610825', '陕西省榆林市定边县', 'sxsylsdbx');
INSERT INTO `t_system_region` VALUES ('7563', '610826', '绥德县', '610826', '陕西省榆林市绥德县', 'sxsylssdx');
INSERT INTO `t_system_region` VALUES ('7564', '610827', '米脂县', '610827', '陕西省榆林市米脂县', 'sxsylsmzx');
INSERT INTO `t_system_region` VALUES ('7565', '610828', '佳县', '610828', '陕西省榆林市佳县', 'sxsylsjx');
INSERT INTO `t_system_region` VALUES ('7566', '610829', '吴堡县', '610829', '陕西省榆林市吴堡县', 'sxsylswbx');
INSERT INTO `t_system_region` VALUES ('7567', '610830', '清涧县', '610830', '陕西省榆林市清涧县', 'sxsylsqjx');
INSERT INTO `t_system_region` VALUES ('7568', '610831', '子洲县', '610831', '陕西省榆林市子洲县', 'sxsylszzx');
INSERT INTO `t_system_region` VALUES ('7569', '6109', '安康市', '610900', '陕西省安康市', 'sxsaks');
INSERT INTO `t_system_region` VALUES ('7570', '610901', '市辖区', '610901', '陕西省安康市市辖区', 'sxsakssxq');
INSERT INTO `t_system_region` VALUES ('7571', '610902', '汉滨区', '610902', '陕西省安康市汉滨区', 'sxsakshbq');
INSERT INTO `t_system_region` VALUES ('7572', '610921', '汉阴县', '610921', '陕西省安康市汉阴县', 'sxsakshyx');
INSERT INTO `t_system_region` VALUES ('7573', '610922', '石泉县', '610922', '陕西省安康市石泉县', 'sxsakssqx');
INSERT INTO `t_system_region` VALUES ('7574', '610923', '宁陕县', '610923', '陕西省安康市宁陕县', 'sxsaksnsx');
INSERT INTO `t_system_region` VALUES ('7575', '610924', '紫阳县', '610924', '陕西省安康市紫阳县', 'sxsakszyx');
INSERT INTO `t_system_region` VALUES ('7576', '610925', '岚皋县', '610925', '陕西省安康市岚皋县', 'sxsakslgx');
INSERT INTO `t_system_region` VALUES ('7577', '610926', '平利县', '610926', '陕西省安康市平利县', 'sxsaksplx');
INSERT INTO `t_system_region` VALUES ('7578', '610927', '镇坪县', '610927', '陕西省安康市镇坪县', 'sxsakszpx');
INSERT INTO `t_system_region` VALUES ('7579', '610928', '旬阳县', '610928', '陕西省安康市旬阳县', 'sxsaksxyx');
INSERT INTO `t_system_region` VALUES ('7580', '610929', '白河县', '610929', '陕西省安康市白河县', 'sxsaksbhx');
INSERT INTO `t_system_region` VALUES ('7581', '6110', '商洛市', '611000', '陕西省商洛市', 'sxssls');
INSERT INTO `t_system_region` VALUES ('7582', '611001', '市辖区', '611001', '陕西省商洛市市辖区', 'sxsslssxq');
INSERT INTO `t_system_region` VALUES ('7583', '611002', '商州区', '611002', '陕西省商洛市商州区', 'sxsslsszq');
INSERT INTO `t_system_region` VALUES ('7584', '611021', '洛南县', '611021', '陕西省商洛市洛南县', 'sxsslslnx');
INSERT INTO `t_system_region` VALUES ('7585', '611022', '丹凤县', '611022', '陕西省商洛市丹凤县', 'sxsslsdfx');
INSERT INTO `t_system_region` VALUES ('7586', '611023', '商南县', '611023', '陕西省商洛市商南县', 'sxsslssnx');
INSERT INTO `t_system_region` VALUES ('7587', '611024', '山阳县', '611024', '陕西省商洛市山阳县', 'sxsslssyx');
INSERT INTO `t_system_region` VALUES ('7588', '611025', '镇安县', '611025', '陕西省商洛市镇安县', 'sxsslszax');
INSERT INTO `t_system_region` VALUES ('7589', '611026', '柞水县', '611026', '陕西省商洛市柞水县', 'sxsslszsx');
INSERT INTO `t_system_region` VALUES ('7590', '6111', '杨凌示范区', '611100', '陕西省杨凌示范区', 'sxsylsfq');
INSERT INTO `t_system_region` VALUES ('7591', '611101', '杨凌示范区杨陵区', '611101', '陕西省杨凌示范区杨凌示范区杨陵区', 'sxsylsfqylsfqylq');
INSERT INTO `t_system_region` VALUES ('7592', '62', '甘肃省', '620000', '甘肃省', 'gss');
INSERT INTO `t_system_region` VALUES ('7593', '6201', '兰州市', '620100', '甘肃省兰州市', 'gsslzs');
INSERT INTO `t_system_region` VALUES ('7594', '620101', '市辖区', '620101', '甘肃省兰州市市辖区', 'gsslzssxq');
INSERT INTO `t_system_region` VALUES ('7595', '620102', '城关区', '620102', '甘肃省兰州市城关区', 'gsslzscgq');
INSERT INTO `t_system_region` VALUES ('7596', '620103', '七里河区', '620103', '甘肃省兰州市七里河区', 'gsslzsqlhq');
INSERT INTO `t_system_region` VALUES ('7597', '620104', '西固区', '620104', '甘肃省兰州市西固区', 'gsslzsxgq');
INSERT INTO `t_system_region` VALUES ('7598', '620105', '安宁区', '620105', '甘肃省兰州市安宁区', 'gsslzsanq');
INSERT INTO `t_system_region` VALUES ('7599', '620111', '红古区', '620111', '甘肃省兰州市红古区', 'gsslzshgq');
INSERT INTO `t_system_region` VALUES ('7600', '620121', '永登县', '620121', '甘肃省兰州市永登县', 'gsslzsydx');
INSERT INTO `t_system_region` VALUES ('7601', '620122', '皋兰县', '620122', '甘肃省兰州市皋兰县', 'gsslzsglx');
INSERT INTO `t_system_region` VALUES ('7602', '620123', '榆中县', '620123', '甘肃省兰州市榆中县', 'gsslzsyzx');
INSERT INTO `t_system_region` VALUES ('7603', '6202', '嘉峪关市', '620200', '甘肃省嘉峪关市', 'gssjygs');
INSERT INTO `t_system_region` VALUES ('7604', '620201', '市辖区', '620201', '甘肃省嘉峪关市市辖区', 'gssjygssxq');
INSERT INTO `t_system_region` VALUES ('7605', '6203', '金昌市', '620300', '甘肃省金昌市', 'gssjcs');
INSERT INTO `t_system_region` VALUES ('7606', '620301', '市辖区', '620301', '甘肃省金昌市市辖区', 'gssjcssxq');
INSERT INTO `t_system_region` VALUES ('7607', '620302', '金川区', '620302', '甘肃省金昌市金川区', 'gssjcsjcq');
INSERT INTO `t_system_region` VALUES ('7608', '620321', '永昌县', '620321', '甘肃省金昌市永昌县', 'gssjcsycx');
INSERT INTO `t_system_region` VALUES ('7609', '6204', '白银市', '620400', '甘肃省白银市', 'gssbys');
INSERT INTO `t_system_region` VALUES ('7610', '620401', '市辖区', '620401', '甘肃省白银市市辖区', 'gssbyssxq');
INSERT INTO `t_system_region` VALUES ('7611', '620402', '白银区', '620402', '甘肃省白银市白银区', 'gssbysbyq');
INSERT INTO `t_system_region` VALUES ('7612', '620403', '平川区', '620403', '甘肃省白银市平川区', 'gssbyspcq');
INSERT INTO `t_system_region` VALUES ('7613', '620421', '靖远县', '620421', '甘肃省白银市靖远县', 'gssbysjyx');
INSERT INTO `t_system_region` VALUES ('7614', '620422', '会宁县', '620422', '甘肃省白银市会宁县', 'gssbyshnx');
INSERT INTO `t_system_region` VALUES ('7615', '620423', '景泰县', '620423', '甘肃省白银市景泰县', 'gssbysjtx');
INSERT INTO `t_system_region` VALUES ('7616', '6205', '天水市', '620500', '甘肃省天水市', 'gsstss');
INSERT INTO `t_system_region` VALUES ('7617', '620501', '市辖区', '620501', '甘肃省天水市市辖区', 'gsstsssxq');
INSERT INTO `t_system_region` VALUES ('7618', '620502', '秦城区', '620502', '甘肃省天水市秦城区', 'gsstssqcq');
INSERT INTO `t_system_region` VALUES ('7619', '620503', '北道区', '620503', '甘肃省天水市北道区', 'gsstssbdq');
INSERT INTO `t_system_region` VALUES ('7620', '620521', '清水县', '620521', '甘肃省天水市清水县', 'gsstssqsx');
INSERT INTO `t_system_region` VALUES ('7621', '620522', '秦安县', '620522', '甘肃省天水市秦安县', 'gsstssqax');
INSERT INTO `t_system_region` VALUES ('7622', '620523', '甘谷县', '620523', '甘肃省天水市甘谷县', 'gsstssggx');
INSERT INTO `t_system_region` VALUES ('7623', '620524', '武山县', '620524', '甘肃省天水市武山县', 'gsstsswsx');
INSERT INTO `t_system_region` VALUES ('7624', '620525', '张家川回族自治县', '620525', '甘肃省天水市张家川回族自治县', 'gsstsszjchzzzx');
INSERT INTO `t_system_region` VALUES ('7625', '6206', '武威市', '620600', '甘肃省武威市', 'gsswws');
INSERT INTO `t_system_region` VALUES ('7626', '620601', '市辖区', '620601', '甘肃省武威市市辖区', 'gsswwssxq');
INSERT INTO `t_system_region` VALUES ('7627', '620602', '凉州区', '620602', '甘肃省武威市凉州区', 'gsswwslzq');
INSERT INTO `t_system_region` VALUES ('7628', '620621', '民勤县', '620621', '甘肃省武威市民勤县', 'gsswwsmqx');
INSERT INTO `t_system_region` VALUES ('7629', '620622', '古浪县', '620622', '甘肃省武威市古浪县', 'gsswwsglx');
INSERT INTO `t_system_region` VALUES ('7630', '620623', '天祝藏族自治县', '620623', '甘肃省武威市天祝藏族自治县', 'gsswwstzzzzzx');
INSERT INTO `t_system_region` VALUES ('7631', '6207', '张掖市', '620700', '甘肃省张掖市', 'gsszys');
INSERT INTO `t_system_region` VALUES ('7632', '620701', '市辖区', '620701', '甘肃省张掖市市辖区', 'gsszyssxq');
INSERT INTO `t_system_region` VALUES ('7633', '620702', '甘州区', '620702', '甘肃省张掖市甘州区', 'gsszysgzq');
INSERT INTO `t_system_region` VALUES ('7634', '620721', '肃南裕固族自治县', '620721', '甘肃省张掖市肃南裕固族自治县', 'gsszyssnygzzzx');
INSERT INTO `t_system_region` VALUES ('7635', '620722', '民乐县', '620722', '甘肃省张掖市民乐县', 'gsszysmlx');
INSERT INTO `t_system_region` VALUES ('7636', '620723', '临泽县', '620723', '甘肃省张掖市临泽县', 'gsszyslzx');
INSERT INTO `t_system_region` VALUES ('7637', '620724', '高台县', '620724', '甘肃省张掖市高台县', 'gsszysgtx');
INSERT INTO `t_system_region` VALUES ('7638', '620725', '山丹县', '620725', '甘肃省张掖市山丹县', 'gsszyssdx');
INSERT INTO `t_system_region` VALUES ('7639', '6208', '平凉市', '620800', '甘肃省平凉市', 'gsspls');
INSERT INTO `t_system_region` VALUES ('7640', '620801', '市辖区', '620801', '甘肃省平凉市市辖区', 'gssplssxq');
INSERT INTO `t_system_region` VALUES ('7641', '620802', '崆峒区', '620802', '甘肃省平凉市崆峒区', 'gssplsktq');
INSERT INTO `t_system_region` VALUES ('7642', '620821', '泾川县', '620821', '甘肃省平凉市泾川县', 'gssplsjcx');
INSERT INTO `t_system_region` VALUES ('7643', '620822', '灵台县', '620822', '甘肃省平凉市灵台县', 'gssplsltx');
INSERT INTO `t_system_region` VALUES ('7644', '620823', '崇信县', '620823', '甘肃省平凉市崇信县', 'gssplscxx');
INSERT INTO `t_system_region` VALUES ('7645', '620824', '华亭县', '620824', '甘肃省平凉市华亭县', 'gssplshtx');
INSERT INTO `t_system_region` VALUES ('7646', '620825', '庄浪县', '620825', '甘肃省平凉市庄浪县', 'gssplszlx');
INSERT INTO `t_system_region` VALUES ('7647', '620826', '静宁县', '620826', '甘肃省平凉市静宁县', 'gssplsjnx');
INSERT INTO `t_system_region` VALUES ('7648', '6209', '酒泉市', '620900', '甘肃省酒泉市', 'gssjqs');
INSERT INTO `t_system_region` VALUES ('7649', '620901', '市辖区', '620901', '甘肃省酒泉市市辖区', 'gssjqssxq');
INSERT INTO `t_system_region` VALUES ('7650', '620902', '肃州区', '620902', '甘肃省酒泉市肃州区', 'gssjqsszq');
INSERT INTO `t_system_region` VALUES ('7651', '620921', '金塔县', '620921', '甘肃省酒泉市金塔县', 'gssjqsjtx');
INSERT INTO `t_system_region` VALUES ('7652', '620922', '安西县', '620922', '甘肃省酒泉市安西县', 'gssjqsaxx');
INSERT INTO `t_system_region` VALUES ('7653', '620923', '肃北蒙古族自治县', '620923', '甘肃省酒泉市肃北蒙古族自治县', 'gssjqssbmgzzzx');
INSERT INTO `t_system_region` VALUES ('7654', '620924', '阿克塞哈萨克族自治县', '620924', '甘肃省酒泉市阿克塞哈萨克族自治县', 'gssjqsakshskzzzx');
INSERT INTO `t_system_region` VALUES ('7655', '620981', '玉门市', '620981', '甘肃省酒泉市玉门市', 'gssjqsyms');
INSERT INTO `t_system_region` VALUES ('7656', '620982', '敦煌市', '620982', '甘肃省酒泉市敦煌市', 'gssjqsdhs');
INSERT INTO `t_system_region` VALUES ('7657', '6210', '庆阳市', '621000', '甘肃省庆阳市', 'gssqys');
INSERT INTO `t_system_region` VALUES ('7658', '621001', '市辖区', '621001', '甘肃省庆阳市市辖区', 'gssqyssxq');
INSERT INTO `t_system_region` VALUES ('7659', '621002', '西峰区', '621002', '甘肃省庆阳市西峰区', 'gssqysxfq');
INSERT INTO `t_system_region` VALUES ('7660', '621021', '庆城县', '621021', '甘肃省庆阳市庆城县', 'gssqysqcx');
INSERT INTO `t_system_region` VALUES ('7661', '621022', '环县', '621022', '甘肃省庆阳市环县', 'gssqyshx');
INSERT INTO `t_system_region` VALUES ('7662', '621023', '华池县', '621023', '甘肃省庆阳市华池县', 'gssqyshcx');
INSERT INTO `t_system_region` VALUES ('7663', '621024', '合水县', '621024', '甘肃省庆阳市合水县', 'gssqyshsx');
INSERT INTO `t_system_region` VALUES ('7664', '621025', '正宁县', '621025', '甘肃省庆阳市正宁县', 'gssqysznx');
INSERT INTO `t_system_region` VALUES ('7665', '621026', '宁县', '621026', '甘肃省庆阳市宁县', 'gssqysnx');
INSERT INTO `t_system_region` VALUES ('7666', '621027', '镇原县', '621027', '甘肃省庆阳市镇原县', 'gssqyszyx');
INSERT INTO `t_system_region` VALUES ('7667', '6211', '定西市', '621100', '甘肃省定西市', 'gssdxs');
INSERT INTO `t_system_region` VALUES ('7668', '621101', '市辖区', '621101', '甘肃省定西市市辖区', 'gssdxssxq');
INSERT INTO `t_system_region` VALUES ('7669', '621102', '安定区', '621102', '甘肃省定西市安定区', 'gssdxsadq');
INSERT INTO `t_system_region` VALUES ('7670', '621121', '通渭县', '621121', '甘肃省定西市通渭县', 'gssdxstwx');
INSERT INTO `t_system_region` VALUES ('7671', '621122', '陇西县', '621122', '甘肃省定西市陇西县', 'gssdxslxx');
INSERT INTO `t_system_region` VALUES ('7672', '621123', '渭源县', '621123', '甘肃省定西市渭源县', 'gssdxswyx');
INSERT INTO `t_system_region` VALUES ('7673', '621124', '临洮县', '621124', '甘肃省定西市临洮县', 'gssdxsltx');
INSERT INTO `t_system_region` VALUES ('7674', '621125', '漳县', '621125', '甘肃省定西市漳县', 'gssdxszx');
INSERT INTO `t_system_region` VALUES ('7675', '621126', '岷县', '621126', '甘肃省定西市岷县', 'gssdxsmx');
INSERT INTO `t_system_region` VALUES ('7676', '6212', '陇南市', '621200', '甘肃省陇南市', 'gsslns');
INSERT INTO `t_system_region` VALUES ('7677', '621201', '市辖区', '621201', '甘肃省陇南市市辖区', 'gsslnssxq');
INSERT INTO `t_system_region` VALUES ('7678', '621202', '武都区', '621202', '甘肃省陇南市武都区', 'gsslnswdq');
INSERT INTO `t_system_region` VALUES ('7679', '621221', '成县', '621221', '甘肃省陇南市成县', 'gsslnscx');
INSERT INTO `t_system_region` VALUES ('7680', '621222', '文县', '621222', '甘肃省陇南市文县', 'gsslnswx');
INSERT INTO `t_system_region` VALUES ('7681', '621223', '宕昌县', '621223', '甘肃省陇南市宕昌县', 'gsslnsdcx');
INSERT INTO `t_system_region` VALUES ('7682', '621224', '康县', '621224', '甘肃省陇南市康县', 'gsslnskx');
INSERT INTO `t_system_region` VALUES ('7683', '621225', '西和县', '621225', '甘肃省陇南市西和县', 'gsslnsxhx');
INSERT INTO `t_system_region` VALUES ('7684', '621226', '礼县', '621226', '甘肃省陇南市礼县', 'gsslnslx');
INSERT INTO `t_system_region` VALUES ('7685', '621227', '徽县', '621227', '甘肃省陇南市徽县', 'gsslnshx');
INSERT INTO `t_system_region` VALUES ('7686', '621228', '两当县', '621228', '甘肃省陇南市两当县', 'gsslnsldx');
INSERT INTO `t_system_region` VALUES ('7687', '6229', '临夏回族自治州', '622900', '甘肃省临夏回族自治州', 'gsslxhzzzz');
INSERT INTO `t_system_region` VALUES ('7688', '622901', '临夏市', '622901', '甘肃省临夏回族自治州临夏市', 'gsslxhzzzzlxs');
INSERT INTO `t_system_region` VALUES ('7689', '622921', '临夏县', '622921', '甘肃省临夏回族自治州临夏县', 'gsslxhzzzzlxx');
INSERT INTO `t_system_region` VALUES ('7690', '622922', '康乐县', '622922', '甘肃省临夏回族自治州康乐县', 'gsslxhzzzzklx');
INSERT INTO `t_system_region` VALUES ('7691', '622923', '永靖县', '622923', '甘肃省临夏回族自治州永靖县', 'gsslxhzzzzyjx');
INSERT INTO `t_system_region` VALUES ('7692', '622924', '广河县', '622924', '甘肃省临夏回族自治州广河县', 'gsslxhzzzzghx');
INSERT INTO `t_system_region` VALUES ('7693', '622925', '和政县', '622925', '甘肃省临夏回族自治州和政县', 'gsslxhzzzzhzx');
INSERT INTO `t_system_region` VALUES ('7694', '622926', '东乡族自治县', '622926', '甘肃省临夏回族自治州东乡族自治县', 'gsslxhzzzzdxzzzx');
INSERT INTO `t_system_region` VALUES ('7695', '622927', '积石山保安族东乡族撒拉族自治县', '622927', '甘肃省临夏回族自治州积石山保安族东乡族撒拉族自治县', 'gsslxhzzzzjssbazdxzslzzzx');
INSERT INTO `t_system_region` VALUES ('7696', '6230', '甘南藏族自治州', '623000', '甘肃省甘南藏族自治州', 'gssgnzzzzz');
INSERT INTO `t_system_region` VALUES ('7697', '623001', '合作市', '623001', '甘肃省甘南藏族自治州合作市', 'gssgnzzzzzhzs');
INSERT INTO `t_system_region` VALUES ('7698', '623021', '临潭县', '623021', '甘肃省甘南藏族自治州临潭县', 'gssgnzzzzzltx');
INSERT INTO `t_system_region` VALUES ('7699', '623022', '卓尼县', '623022', '甘肃省甘南藏族自治州卓尼县', 'gssgnzzzzzznx');
INSERT INTO `t_system_region` VALUES ('7700', '623023', '舟曲县', '623023', '甘肃省甘南藏族自治州舟曲县', 'gssgnzzzzzzqx');
INSERT INTO `t_system_region` VALUES ('7701', '623024', '迭部县', '623024', '甘肃省甘南藏族自治州迭部县', 'gssgnzzzzzdbx');
INSERT INTO `t_system_region` VALUES ('7702', '623025', '玛曲县', '623025', '甘肃省甘南藏族自治州玛曲县', 'gssgnzzzzzmqx');
INSERT INTO `t_system_region` VALUES ('7703', '623026', '碌曲县', '623026', '甘肃省甘南藏族自治州碌曲县', 'gssgnzzzzzlqx');
INSERT INTO `t_system_region` VALUES ('7704', '623027', '夏河县', '623027', '甘肃省甘南藏族自治州夏河县', 'gssgnzzzzzxhx');
INSERT INTO `t_system_region` VALUES ('7705', '63', '青海省', '630000', '青海省', 'qhs');
INSERT INTO `t_system_region` VALUES ('7706', '6301', '西宁市', '630100', '青海省西宁市', 'qhsxns');
INSERT INTO `t_system_region` VALUES ('7707', '630101', '市辖区', '630101', '青海省西宁市市辖区', 'qhsxnssxq');
INSERT INTO `t_system_region` VALUES ('7708', '630102', '城东区', '630102', '青海省西宁市城东区', 'qhsxnscdq');
INSERT INTO `t_system_region` VALUES ('7709', '630103', '城中区', '630103', '青海省西宁市城中区', 'qhsxnsczq');
INSERT INTO `t_system_region` VALUES ('7710', '630104', '城西区', '630104', '青海省西宁市城西区', 'qhsxnscxq');
INSERT INTO `t_system_region` VALUES ('7711', '630105', '城北区', '630105', '青海省西宁市城北区', 'qhsxnscbq');
INSERT INTO `t_system_region` VALUES ('7712', '630121', '大通回族土族自治县', '630121', '青海省西宁市大通回族土族自治县', 'qhsxnsdthztzzzx');
INSERT INTO `t_system_region` VALUES ('7713', '630122', '湟中县', '630122', '青海省西宁市湟中县', 'qhsxnshzx');
INSERT INTO `t_system_region` VALUES ('7714', '630123', '湟源县', '630123', '青海省西宁市湟源县', 'qhsxnshyx');
INSERT INTO `t_system_region` VALUES ('7715', '6321', '海东地区', '632100', '青海省海东地区', 'qhshddq');
INSERT INTO `t_system_region` VALUES ('7716', '632121', '平安县', '632121', '青海省海东地区平安县', 'qhshddqpax');
INSERT INTO `t_system_region` VALUES ('7717', '632122', '民和回族土族自治县', '632122', '青海省海东地区民和回族土族自治县', 'qhshddqmhhztzzzx');
INSERT INTO `t_system_region` VALUES ('7718', '632123', '乐都县', '632123', '青海省海东地区乐都县', 'qhshddqldx');
INSERT INTO `t_system_region` VALUES ('7719', '632126', '互助土族自治县', '632126', '青海省海东地区互助土族自治县', 'qhshddqhztzzzx');
INSERT INTO `t_system_region` VALUES ('7720', '632127', '化隆回族自治县', '632127', '青海省海东地区化隆回族自治县', 'qhshddqhlhzzzx');
INSERT INTO `t_system_region` VALUES ('7721', '632128', '循化撒拉族自治县', '632128', '青海省海东地区循化撒拉族自治县', 'qhshddqxhslzzzx');
INSERT INTO `t_system_region` VALUES ('7722', '6322', '海北藏族自治州', '632200', '青海省海北藏族自治州', 'qhshbzzzzz');
INSERT INTO `t_system_region` VALUES ('7723', '632221', '门源回族自治县', '632221', '青海省海北藏族自治州门源回族自治县', 'qhshbzzzzzmyhzzzx');
INSERT INTO `t_system_region` VALUES ('7724', '632222', '祁连县', '632222', '青海省海北藏族自治州祁连县', 'qhshbzzzzzqlx');
INSERT INTO `t_system_region` VALUES ('7725', '632223', '海晏县', '632223', '青海省海北藏族自治州海晏县', 'qhshbzzzzzhyx');
INSERT INTO `t_system_region` VALUES ('7726', '632224', '刚察县', '632224', '青海省海北藏族自治州刚察县', 'qhshbzzzzzgcx');
INSERT INTO `t_system_region` VALUES ('7727', '6323', '黄南藏族自治州', '632300', '青海省黄南藏族自治州', 'qhshnzzzzz');
INSERT INTO `t_system_region` VALUES ('7728', '632321', '同仁县', '632321', '青海省黄南藏族自治州同仁县', 'qhshnzzzzztrx');
INSERT INTO `t_system_region` VALUES ('7729', '632322', '尖扎县', '632322', '青海省黄南藏族自治州尖扎县', 'qhshnzzzzzjzx');
INSERT INTO `t_system_region` VALUES ('7730', '632323', '泽库县', '632323', '青海省黄南藏族自治州泽库县', 'qhshnzzzzzzkx');
INSERT INTO `t_system_region` VALUES ('7731', '632324', '河南蒙古族自治县', '632324', '青海省黄南藏族自治州河南蒙古族自治县', 'qhshnzzzzzhnmgzzzx');
INSERT INTO `t_system_region` VALUES ('7732', '6325', '海南藏族自治州', '632500', '青海省海南藏族自治州', 'qhshnzzzzz');
INSERT INTO `t_system_region` VALUES ('7733', '632521', '共和县', '632521', '青海省海南藏族自治州共和县', 'qhshnzzzzzghx');
INSERT INTO `t_system_region` VALUES ('7734', '632522', '同德县', '632522', '青海省海南藏族自治州同德县', 'qhshnzzzzztdx');
INSERT INTO `t_system_region` VALUES ('7735', '632523', '贵德县', '632523', '青海省海南藏族自治州贵德县', 'qhshnzzzzzgdx');
INSERT INTO `t_system_region` VALUES ('7736', '632524', '兴海县', '632524', '青海省海南藏族自治州兴海县', 'qhshnzzzzzxhx');
INSERT INTO `t_system_region` VALUES ('7737', '632525', '贵南县', '632525', '青海省海南藏族自治州贵南县', 'qhshnzzzzzgnx');
INSERT INTO `t_system_region` VALUES ('7738', '6326', '果洛藏族自治州', '632600', '青海省果洛藏族自治州', 'qhsglzzzzz');
INSERT INTO `t_system_region` VALUES ('7739', '632621', '玛沁县', '632621', '青海省果洛藏族自治州玛沁县', 'qhsglzzzzzmqx');
INSERT INTO `t_system_region` VALUES ('7740', '632622', '班玛县', '632622', '青海省果洛藏族自治州班玛县', 'qhsglzzzzzbmx');
INSERT INTO `t_system_region` VALUES ('7741', '632623', '甘德县', '632623', '青海省果洛藏族自治州甘德县', 'qhsglzzzzzgdx');
INSERT INTO `t_system_region` VALUES ('7742', '632624', '达日县', '632624', '青海省果洛藏族自治州达日县', 'qhsglzzzzzdrx');
INSERT INTO `t_system_region` VALUES ('7743', '632625', '久治县', '632625', '青海省果洛藏族自治州久治县', 'qhsglzzzzzjzx');
INSERT INTO `t_system_region` VALUES ('7744', '632626', '玛多县', '632626', '青海省果洛藏族自治州玛多县', 'qhsglzzzzzmdx');
INSERT INTO `t_system_region` VALUES ('7745', '6327', '玉树藏族自治州', '632700', '青海省玉树藏族自治州', 'qhsyszzzzz');
INSERT INTO `t_system_region` VALUES ('7746', '632721', '玉树县', '632721', '青海省玉树藏族自治州玉树县', 'qhsyszzzzzysx');
INSERT INTO `t_system_region` VALUES ('7747', '632722', '杂多县', '632722', '青海省玉树藏族自治州杂多县', 'qhsyszzzzzzdx');
INSERT INTO `t_system_region` VALUES ('7748', '632723', '称多县', '632723', '青海省玉树藏族自治州称多县', 'qhsyszzzzzcdx');
INSERT INTO `t_system_region` VALUES ('7749', '632724', '治多县', '632724', '青海省玉树藏族自治州治多县', 'qhsyszzzzzzdx');
INSERT INTO `t_system_region` VALUES ('7750', '632725', '囊谦县', '632725', '青海省玉树藏族自治州囊谦县', 'qhsyszzzzznqx');
INSERT INTO `t_system_region` VALUES ('7751', '632726', '曲麻莱县', '632726', '青海省玉树藏族自治州曲麻莱县', 'qhsyszzzzzqmlx');
INSERT INTO `t_system_region` VALUES ('7752', '6328', '海西蒙古族藏族自治州', '632800', '青海省海西蒙古族藏族自治州', 'qhshxmgzzzzzz');
INSERT INTO `t_system_region` VALUES ('7753', '632801', '格尔木市', '632801', '青海省海西蒙古族藏族自治州格尔木市', 'qhshxmgzzzzzzgems');
INSERT INTO `t_system_region` VALUES ('7754', '632802', '德令哈市', '632802', '青海省海西蒙古族藏族自治州德令哈市', 'qhshxmgzzzzzzdlhs');
INSERT INTO `t_system_region` VALUES ('7755', '632821', '乌兰县', '632821', '青海省海西蒙古族藏族自治州乌兰县', 'qhshxmgzzzzzzwlx');
INSERT INTO `t_system_region` VALUES ('7756', '632822', '都兰县', '632822', '青海省海西蒙古族藏族自治州都兰县', 'qhshxmgzzzzzzdlx');
INSERT INTO `t_system_region` VALUES ('7757', '632823', '天峻县', '632823', '青海省海西蒙古族藏族自治州天峻县', 'qhshxmgzzzzzztjx');
INSERT INTO `t_system_region` VALUES ('7758', '632824', '大柴旦', '632824', '青海省海西蒙古族藏族自治州大柴旦', 'qhshxmgzzzzzzdcd');
INSERT INTO `t_system_region` VALUES ('7759', '632825', '冷湖', '632825', '青海省海西蒙古族藏族自治州冷湖', 'qhshxmgzzzzzzlh');
INSERT INTO `t_system_region` VALUES ('7760', '632826', '茫崖', '632826', '青海省海西蒙古族藏族自治州茫崖', 'qhshxmgzzzzzzmy');
INSERT INTO `t_system_region` VALUES ('7761', '64', '宁夏回族自治区', '640000', '宁夏回族自治区', 'nxhzzzq');
INSERT INTO `t_system_region` VALUES ('7762', '6401', '银川市', '640100', '宁夏回族自治区银川市', 'nxhzzzqycs');
INSERT INTO `t_system_region` VALUES ('7763', '640101', '市辖区', '640101', '宁夏回族自治区银川市市辖区', 'nxhzzzqycssxq');
INSERT INTO `t_system_region` VALUES ('7764', '640104', '兴庆区', '640104', '宁夏回族自治区银川市兴庆区', 'nxhzzzqycsxqq');
INSERT INTO `t_system_region` VALUES ('7765', '640105', '西夏区', '640105', '宁夏回族自治区银川市西夏区', 'nxhzzzqycsxxq');
INSERT INTO `t_system_region` VALUES ('7766', '640106', '金凤区', '640106', '宁夏回族自治区银川市金凤区', 'nxhzzzqycsjfq');
INSERT INTO `t_system_region` VALUES ('7767', '640121', '永宁县', '640121', '宁夏回族自治区银川市永宁县', 'nxhzzzqycsynx');
INSERT INTO `t_system_region` VALUES ('7768', '640122', '贺兰县', '640122', '宁夏回族自治区银川市贺兰县', 'nxhzzzqycshlx');
INSERT INTO `t_system_region` VALUES ('7769', '640181', '灵武市', '640181', '宁夏回族自治区银川市灵武市', 'nxhzzzqycslws');
INSERT INTO `t_system_region` VALUES ('7770', '6402', '石嘴山市', '640200', '宁夏回族自治区石嘴山市', 'nxhzzzqszss');
INSERT INTO `t_system_region` VALUES ('7771', '640201', '市辖区', '640201', '宁夏回族自治区石嘴山市市辖区', 'nxhzzzqszsssxq');
INSERT INTO `t_system_region` VALUES ('7772', '640202', '大武口区', '640202', '宁夏回族自治区石嘴山市大武口区', 'nxhzzzqszssdwkq');
INSERT INTO `t_system_region` VALUES ('7773', '640205', '惠农区', '640205', '宁夏回族自治区石嘴山市惠农区', 'nxhzzzqszsshnq');
INSERT INTO `t_system_region` VALUES ('7774', '640221', '平罗县', '640221', '宁夏回族自治区石嘴山市平罗县', 'nxhzzzqszssplx');
INSERT INTO `t_system_region` VALUES ('7775', '6403', '吴忠市', '640300', '宁夏回族自治区吴忠市', 'nxhzzzqwzs');
INSERT INTO `t_system_region` VALUES ('7776', '640301', '市辖区', '640301', '宁夏回族自治区吴忠市市辖区', 'nxhzzzqwzssxq');
INSERT INTO `t_system_region` VALUES ('7777', '640302', '利通区', '640302', '宁夏回族自治区吴忠市利通区', 'nxhzzzqwzsltq');
INSERT INTO `t_system_region` VALUES ('7778', '640303', '红寺堡开发区', '640303', '宁夏回族自治区吴忠市红寺堡开发区', 'nxhzzzqwzshsbkfq');
INSERT INTO `t_system_region` VALUES ('7779', '640323', '盐池县', '640323', '宁夏回族自治区吴忠市盐池县', 'nxhzzzqwzsycx');
INSERT INTO `t_system_region` VALUES ('7780', '640324', '同心县', '640324', '宁夏回族自治区吴忠市同心县', 'nxhzzzqwzstxx');
INSERT INTO `t_system_region` VALUES ('7781', '640381', '青铜峡市', '640381', '宁夏回族自治区吴忠市青铜峡市', 'nxhzzzqwzsqtxs');
INSERT INTO `t_system_region` VALUES ('7782', '6404', '固原市', '640400', '宁夏回族自治区固原市', 'nxhzzzqgys');
INSERT INTO `t_system_region` VALUES ('7783', '640401', '市辖区', '640401', '宁夏回族自治区固原市市辖区', 'nxhzzzqgyssxq');
INSERT INTO `t_system_region` VALUES ('7784', '640402', '原州区', '640402', '宁夏回族自治区固原市原州区', 'nxhzzzqgysyzq');
INSERT INTO `t_system_region` VALUES ('7785', '640422', '西吉县', '640422', '宁夏回族自治区固原市西吉县', 'nxhzzzqgysxjx');
INSERT INTO `t_system_region` VALUES ('7786', '640423', '隆德县', '640423', '宁夏回族自治区固原市隆德县', 'nxhzzzqgysldx');
INSERT INTO `t_system_region` VALUES ('7787', '640424', '泾源县', '640424', '宁夏回族自治区固原市泾源县', 'nxhzzzqgysjyx');
INSERT INTO `t_system_region` VALUES ('7788', '640425', '彭阳县', '640425', '宁夏回族自治区固原市彭阳县', 'nxhzzzqgyspyx');
INSERT INTO `t_system_region` VALUES ('7789', '6405', '中卫市', '640500', '宁夏回族自治区中卫市', 'nxhzzzqzws');
INSERT INTO `t_system_region` VALUES ('7790', '640501', '市辖区', '640501', '宁夏回族自治区中卫市市辖区', 'nxhzzzqzwssxq');
INSERT INTO `t_system_region` VALUES ('7791', '640502', '沙坡头区', '640502', '宁夏回族自治区中卫市沙坡头区', 'nxhzzzqzwssptq');
INSERT INTO `t_system_region` VALUES ('7792', '640521', '中宁县', '640521', '宁夏回族自治区中卫市中宁县', 'nxhzzzqzwsznx');
INSERT INTO `t_system_region` VALUES ('7793', '640522', '海原县', '640522', '宁夏回族自治区中卫市海原县', 'nxhzzzqzwshyx');
INSERT INTO `t_system_region` VALUES ('7794', '65', '新疆维吾尔自治区', '650000', '新疆维吾尔自治区', 'xjwwezzq');
INSERT INTO `t_system_region` VALUES ('7795', '6501', '乌鲁木齐市', '650100', '新疆维吾尔自治区乌鲁木齐市', 'xjwwezzqwlmqs');
INSERT INTO `t_system_region` VALUES ('7796', '650101', '市辖区', '650101', '新疆维吾尔自治区乌鲁木齐市市辖区', 'xjwwezzqwlmqssxq');
INSERT INTO `t_system_region` VALUES ('7797', '650102', '天山区', '650102', '新疆维吾尔自治区乌鲁木齐市天山区', 'xjwwezzqwlmqstsq');
INSERT INTO `t_system_region` VALUES ('7798', '650103', '沙依巴克区', '650103', '新疆维吾尔自治区乌鲁木齐市沙依巴克区', 'xjwwezzqwlmqssybkq');
INSERT INTO `t_system_region` VALUES ('7799', '650104', '新市区', '650104', '新疆维吾尔自治区乌鲁木齐市新市区', 'xjwwezzqwlmqsxsq');
INSERT INTO `t_system_region` VALUES ('7800', '650105', '水磨沟区', '650105', '新疆维吾尔自治区乌鲁木齐市水磨沟区', 'xjwwezzqwlmqssmgq');
INSERT INTO `t_system_region` VALUES ('7801', '650106', '头屯河区', '650106', '新疆维吾尔自治区乌鲁木齐市头屯河区', 'xjwwezzqwlmqstthq');
INSERT INTO `t_system_region` VALUES ('7802', '650107', '达坂城区', '650107', '新疆维吾尔自治区乌鲁木齐市达坂城区', 'xjwwezzqwlmqsdbcq');
INSERT INTO `t_system_region` VALUES ('7803', '650109', '米东区', '650109', '新疆维吾尔自治区乌鲁木齐市米东区', 'xjwwezzqwlmqsmdq');
INSERT INTO `t_system_region` VALUES ('7804', '650121', '乌鲁木齐县', '650121', '新疆维吾尔自治区乌鲁木齐市乌鲁木齐县', 'xjwwezzqwlmqswlmqx');
INSERT INTO `t_system_region` VALUES ('7805', '6502', '克拉玛依市', '650200', '新疆维吾尔自治区克拉玛依市', 'xjwwezzqklmys');
INSERT INTO `t_system_region` VALUES ('7806', '650201', '市辖区', '650201', '新疆维吾尔自治区克拉玛依市市辖区', 'xjwwezzqklmyssxq');
INSERT INTO `t_system_region` VALUES ('7807', '650202', '独山子区', '650202', '新疆维吾尔自治区克拉玛依市独山子区', 'xjwwezzqklmysdszq');
INSERT INTO `t_system_region` VALUES ('7808', '650203', '克拉玛依区', '650203', '新疆维吾尔自治区克拉玛依市克拉玛依区', 'xjwwezzqklmysklmyq');
INSERT INTO `t_system_region` VALUES ('7809', '650204', '白碱滩区', '650204', '新疆维吾尔自治区克拉玛依市白碱滩区', 'xjwwezzqklmysbjtq');
INSERT INTO `t_system_region` VALUES ('7810', '650205', '乌尔禾区', '650205', '新疆维吾尔自治区克拉玛依市乌尔禾区', 'xjwwezzqklmyswehq');
INSERT INTO `t_system_region` VALUES ('7811', '6521', '吐鲁番地区', '652100', '新疆维吾尔自治区吐鲁番地区', 'xjwwezzqtlfdq');
INSERT INTO `t_system_region` VALUES ('7812', '652101', '吐鲁番市', '652101', '新疆维吾尔自治区吐鲁番地区吐鲁番市', 'xjwwezzqtlfdqtlfs');
INSERT INTO `t_system_region` VALUES ('7813', '652122', '鄯善县', '652122', '新疆维吾尔自治区吐鲁番地区鄯善县', 'xjwwezzqtlfdqssx');
INSERT INTO `t_system_region` VALUES ('7814', '652123', '托克逊县', '652123', '新疆维吾尔自治区吐鲁番地区托克逊县', 'xjwwezzqtlfdqtkxx');
INSERT INTO `t_system_region` VALUES ('7815', '6522', '哈密地区', '652200', '新疆维吾尔自治区哈密地区', 'xjwwezzqhmdq');
INSERT INTO `t_system_region` VALUES ('7816', '652201', '哈密市', '652201', '新疆维吾尔自治区哈密地区哈密市', 'xjwwezzqhmdqhms');
INSERT INTO `t_system_region` VALUES ('7817', '652222', '巴里坤哈萨克自治县', '652222', '新疆维吾尔自治区哈密地区巴里坤哈萨克自治县', 'xjwwezzqhmdqblkhskzzx');
INSERT INTO `t_system_region` VALUES ('7818', '652223', '伊吾县', '652223', '新疆维吾尔自治区哈密地区伊吾县', 'xjwwezzqhmdqywx');
INSERT INTO `t_system_region` VALUES ('7819', '652224', '哈密地直', '652224', '新疆维吾尔自治区哈密地区哈密地直', 'xjwwezzqhmdqhmdz');
INSERT INTO `t_system_region` VALUES ('7820', '6523', '昌吉回族自治州', '652300', '新疆维吾尔自治区昌吉回族自治州', 'xjwwezzqcjhzzzz');
INSERT INTO `t_system_region` VALUES ('7821', '652301', '昌吉市', '652301', '新疆维吾尔自治区昌吉回族自治州昌吉市', 'xjwwezzqcjhzzzzcjs');
INSERT INTO `t_system_region` VALUES ('7822', '652302', '阜康市', '652302', '新疆维吾尔自治区昌吉回族自治州阜康市', 'xjwwezzqcjhzzzzfks');
INSERT INTO `t_system_region` VALUES ('7823', '652323', '呼图壁县', '652323', '新疆维吾尔自治区昌吉回族自治州呼图壁县', 'xjwwezzqcjhzzzzhtbx');
INSERT INTO `t_system_region` VALUES ('7824', '652324', '玛纳斯县', '652324', '新疆维吾尔自治区昌吉回族自治州玛纳斯县', 'xjwwezzqcjhzzzzmnsx');
INSERT INTO `t_system_region` VALUES ('7825', '652325', '奇台县', '652325', '新疆维吾尔自治区昌吉回族自治州奇台县', 'xjwwezzqcjhzzzzqtx');
INSERT INTO `t_system_region` VALUES ('7826', '652327', '吉木萨尔县', '652327', '新疆维吾尔自治区昌吉回族自治州吉木萨尔县', 'xjwwezzqcjhzzzzjmsex');
INSERT INTO `t_system_region` VALUES ('7827', '652328', '木垒哈萨克自治县', '652328', '新疆维吾尔自治区昌吉回族自治州木垒哈萨克自治县', 'xjwwezzqcjhzzzzmlhskzzx');
INSERT INTO `t_system_region` VALUES ('7828', '652329', '昌吉州直', '652329', '新疆维吾尔自治区昌吉回族自治州昌吉州直', 'xjwwezzqcjhzzzzcjzz');
INSERT INTO `t_system_region` VALUES ('7829', '6527', '博尔塔拉蒙古自治州', '652700', '新疆维吾尔自治区博尔塔拉蒙古自治州', 'xjwwezzqbetlmgzzz');
INSERT INTO `t_system_region` VALUES ('7830', '652701', '博乐市', '652701', '新疆维吾尔自治区博尔塔拉蒙古自治州博乐市', 'xjwwezzqbetlmgzzzbls');
INSERT INTO `t_system_region` VALUES ('7831', '652704', '州本级', '652704', '新疆维吾尔自治区博尔塔拉蒙古自治州州本级', 'xjwwezzqbetlmgzzzzbj');
INSERT INTO `t_system_region` VALUES ('7832', '652722', '精河县', '652722', '新疆维吾尔自治区博尔塔拉蒙古自治州精河县', 'xjwwezzqbetlmgzzzjhx');
INSERT INTO `t_system_region` VALUES ('7833', '652723', '温泉县', '652723', '新疆维吾尔自治区博尔塔拉蒙古自治州温泉县', 'xjwwezzqbetlmgzzzwqx');
INSERT INTO `t_system_region` VALUES ('7834', '6528', '巴音郭楞蒙古自治州', '652800', '新疆维吾尔自治区巴音郭楞蒙古自治州', 'xjwwezzqbyglmgzzz');
INSERT INTO `t_system_region` VALUES ('7835', '652801', '库尔勒市', '652801', '新疆维吾尔自治区巴音郭楞蒙古自治州库尔勒市', 'xjwwezzqbyglmgzzzkels');
INSERT INTO `t_system_region` VALUES ('7836', '652802', '巴州直属', '652802', '新疆维吾尔自治区巴音郭楞蒙古自治州巴州直属', 'xjwwezzqbyglmgzzzbzzs');
INSERT INTO `t_system_region` VALUES ('7837', '652803', '巴州石油教育分局', '652803', '新疆维吾尔自治区巴音郭楞蒙古自治州巴州石油教育分局', 'xjwwezzqbyglmgzzzbzsyjyfj');
INSERT INTO `t_system_region` VALUES ('7838', '652804', '库尔勒市经济技术开发区', '652804', '新疆维吾尔自治区巴音郭楞蒙古自治州库尔勒市经济技术开发区', 'xjwwezzqbyglmgzzzkelsjjjskfq');
INSERT INTO `t_system_region` VALUES ('7839', '652822', '轮台县', '652822', '新疆维吾尔自治区巴音郭楞蒙古自治州轮台县', 'xjwwezzqbyglmgzzzltx');
INSERT INTO `t_system_region` VALUES ('7840', '652823', '尉犁县', '652823', '新疆维吾尔自治区巴音郭楞蒙古自治州尉犁县', 'xjwwezzqbyglmgzzzwlx');
INSERT INTO `t_system_region` VALUES ('7841', '652824', '若羌县', '652824', '新疆维吾尔自治区巴音郭楞蒙古自治州若羌县', 'xjwwezzqbyglmgzzzrqx');
INSERT INTO `t_system_region` VALUES ('7842', '652825', '且末县', '652825', '新疆维吾尔自治区巴音郭楞蒙古自治州且末县', 'xjwwezzqbyglmgzzzqmx');
INSERT INTO `t_system_region` VALUES ('7843', '652826', '焉耆回族自治县', '652826', '新疆维吾尔自治区巴音郭楞蒙古自治州焉耆回族自治县', 'xjwwezzqbyglmgzzzyqhzzzx');
INSERT INTO `t_system_region` VALUES ('7844', '652827', '和静县', '652827', '新疆维吾尔自治区巴音郭楞蒙古自治州和静县', 'xjwwezzqbyglmgzzzhjx');
INSERT INTO `t_system_region` VALUES ('7845', '652828', '和硕县', '652828', '新疆维吾尔自治区巴音郭楞蒙古自治州和硕县', 'xjwwezzqbyglmgzzzhsx');
INSERT INTO `t_system_region` VALUES ('7846', '652829', '博湖县', '652829', '新疆维吾尔自治区巴音郭楞蒙古自治州博湖县', 'xjwwezzqbyglmgzzzbhx');
INSERT INTO `t_system_region` VALUES ('7847', '6529', '阿克苏地区', '652900', '新疆维吾尔自治区阿克苏地区', 'xjwwezzqaksdq');
INSERT INTO `t_system_region` VALUES ('7848', '652901', '阿克苏市', '652901', '新疆维吾尔自治区阿克苏地区阿克苏市', 'xjwwezzqaksdqakss');
INSERT INTO `t_system_region` VALUES ('7849', '652922', '温宿县', '652922', '新疆维吾尔自治区阿克苏地区温宿县', 'xjwwezzqaksdqwsx');
INSERT INTO `t_system_region` VALUES ('7850', '652923', '库车县', '652923', '新疆维吾尔自治区阿克苏地区库车县', 'xjwwezzqaksdqkcx');
INSERT INTO `t_system_region` VALUES ('7851', '652924', '沙雅县', '652924', '新疆维吾尔自治区阿克苏地区沙雅县', 'xjwwezzqaksdqsyx');
INSERT INTO `t_system_region` VALUES ('7852', '652925', '新和县', '652925', '新疆维吾尔自治区阿克苏地区新和县', 'xjwwezzqaksdqxhx');
INSERT INTO `t_system_region` VALUES ('7853', '652926', '拜城县', '652926', '新疆维吾尔自治区阿克苏地区拜城县', 'xjwwezzqaksdqbcx');
INSERT INTO `t_system_region` VALUES ('7854', '652927', '乌什县', '652927', '新疆维吾尔自治区阿克苏地区乌什县', 'xjwwezzqaksdqwsx');
INSERT INTO `t_system_region` VALUES ('7855', '652928', '阿瓦提县', '652928', '新疆维吾尔自治区阿克苏地区阿瓦提县', 'xjwwezzqaksdqawtx');
INSERT INTO `t_system_region` VALUES ('7856', '652929', '柯坪县', '652929', '新疆维吾尔自治区阿克苏地区柯坪县', 'xjwwezzqaksdqkpx');
INSERT INTO `t_system_region` VALUES ('7857', '652930', '阿克苏地区直属', '652930', '新疆维吾尔自治区阿克苏地区阿克苏地区直属', 'xjwwezzqaksdqaksdqzs');
INSERT INTO `t_system_region` VALUES ('7858', '6530', '克孜勒苏柯尔克孜自治州', '653000', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州', 'xjwwezzqkzlskekzzzz');
INSERT INTO `t_system_region` VALUES ('7859', '653001', '阿图什市', '653001', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿图什市', 'xjwwezzqkzlskekzzzzatss');
INSERT INTO `t_system_region` VALUES ('7860', '653022', '阿克陶县', '653022', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿克陶县', 'xjwwezzqkzlskekzzzzaktx');
INSERT INTO `t_system_region` VALUES ('7861', '653023', '阿合奇县', '653023', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州阿合奇县', 'xjwwezzqkzlskekzzzzahqx');
INSERT INTO `t_system_region` VALUES ('7862', '653024', '乌恰县', '653024', '新疆维吾尔自治区克孜勒苏柯尔克孜自治州乌恰县', 'xjwwezzqkzlskekzzzzwqx');
INSERT INTO `t_system_region` VALUES ('7863', '6531', '喀什地区', '653100', '新疆维吾尔自治区喀什地区', 'xjwwezzqksdq');
INSERT INTO `t_system_region` VALUES ('7864', '653101', '喀什市', '653101', '新疆维吾尔自治区喀什地区喀什市', 'xjwwezzqksdqkss');
INSERT INTO `t_system_region` VALUES ('7865', '653111', '喀什地区本级', '653111', '新疆维吾尔自治区喀什地区喀什地区本级', 'xjwwezzqksdqksdqbj');
INSERT INTO `t_system_region` VALUES ('7866', '653121', '疏附县', '653121', '新疆维吾尔自治区喀什地区疏附县', 'xjwwezzqksdqsfx');
INSERT INTO `t_system_region` VALUES ('7867', '653122', '疏勒县', '653122', '新疆维吾尔自治区喀什地区疏勒县', 'xjwwezzqksdqslx');
INSERT INTO `t_system_region` VALUES ('7868', '653123', '英吉沙县', '653123', '新疆维吾尔自治区喀什地区英吉沙县', 'xjwwezzqksdqyjsx');
INSERT INTO `t_system_region` VALUES ('7869', '653124', '泽普县', '653124', '新疆维吾尔自治区喀什地区泽普县', 'xjwwezzqksdqzpx');
INSERT INTO `t_system_region` VALUES ('7870', '653125', '莎车县', '653125', '新疆维吾尔自治区喀什地区莎车县', 'xjwwezzqksdqscx');
INSERT INTO `t_system_region` VALUES ('7871', '653126', '叶城县', '653126', '新疆维吾尔自治区喀什地区叶城县', 'xjwwezzqksdqycx');
INSERT INTO `t_system_region` VALUES ('7872', '653127', '麦盖提县', '653127', '新疆维吾尔自治区喀什地区麦盖提县', 'xjwwezzqksdqmgtx');
INSERT INTO `t_system_region` VALUES ('7873', '653128', '岳普湖县', '653128', '新疆维吾尔自治区喀什地区岳普湖县', 'xjwwezzqksdqyphx');
INSERT INTO `t_system_region` VALUES ('7874', '653129', '伽师县', '653129', '新疆维吾尔自治区喀什地区伽师县', 'xjwwezzqksdqjsx');
INSERT INTO `t_system_region` VALUES ('7875', '653130', '巴楚县', '653130', '新疆维吾尔自治区喀什地区巴楚县', 'xjwwezzqksdqbcx');
INSERT INTO `t_system_region` VALUES ('7876', '653131', '塔什库尔干塔吉克自治县', '653131', '新疆维吾尔自治区喀什地区塔什库尔干塔吉克自治县', 'xjwwezzqksdqtskegtjkzzx');
INSERT INTO `t_system_region` VALUES ('7877', '6532', '和田地区', '653200', '新疆维吾尔自治区和田地区', 'xjwwezzqhtdq');
INSERT INTO `t_system_region` VALUES ('7878', '653201', '和田市', '653201', '新疆维吾尔自治区和田地区和田市', 'xjwwezzqhtdqhts');
INSERT INTO `t_system_region` VALUES ('7879', '653221', '和田县', '653221', '新疆维吾尔自治区和田地区和田县', 'xjwwezzqhtdqhtx');
INSERT INTO `t_system_region` VALUES ('7880', '653222', '墨玉县', '653222', '新疆维吾尔自治区和田地区墨玉县', 'xjwwezzqhtdqmyx');
INSERT INTO `t_system_region` VALUES ('7881', '653223', '皮山县', '653223', '新疆维吾尔自治区和田地区皮山县', 'xjwwezzqhtdqpsx');
INSERT INTO `t_system_region` VALUES ('7882', '653224', '洛浦县', '653224', '新疆维吾尔自治区和田地区洛浦县', 'xjwwezzqhtdqlpx');
INSERT INTO `t_system_region` VALUES ('7883', '653225', '策勒县', '653225', '新疆维吾尔自治区和田地区策勒县', 'xjwwezzqhtdqclx');
INSERT INTO `t_system_region` VALUES ('7884', '653226', '于田县', '653226', '新疆维吾尔自治区和田地区于田县', 'xjwwezzqhtdqytx');
INSERT INTO `t_system_region` VALUES ('7885', '653227', '民丰县', '653227', '新疆维吾尔自治区和田地区民丰县', 'xjwwezzqhtdqmfx');
INSERT INTO `t_system_region` VALUES ('7886', '653228', '和田地直', '653228', '新疆维吾尔自治区和田地区和田地直', 'xjwwezzqhtdqhtdz');
INSERT INTO `t_system_region` VALUES ('7887', '6540', '伊犁哈萨克自治州', '654000', '新疆维吾尔自治区伊犁哈萨克自治州', 'xjwwezzqylhskzzz');
INSERT INTO `t_system_region` VALUES ('7888', '654001', '伊犁州直属', '654001', '新疆维吾尔自治区伊犁哈萨克自治州伊犁州直属', 'xjwwezzqylhskzzzylzzs');
INSERT INTO `t_system_region` VALUES ('7889', '654002', '伊宁市', '654002', '新疆维吾尔自治区伊犁哈萨克自治州伊宁市', 'xjwwezzqylhskzzzyns');
INSERT INTO `t_system_region` VALUES ('7890', '654003', '奎屯市', '654003', '新疆维吾尔自治区伊犁哈萨克自治州奎屯市', 'xjwwezzqylhskzzzkts');
INSERT INTO `t_system_region` VALUES ('7891', '654021', '伊宁县', '654021', '新疆维吾尔自治区伊犁哈萨克自治州伊宁县', 'xjwwezzqylhskzzzynx');
INSERT INTO `t_system_region` VALUES ('7892', '654022', '察布查尔锡伯自治县', '654022', '新疆维吾尔自治区伊犁哈萨克自治州察布查尔锡伯自治县', 'xjwwezzqylhskzzzcbcexbzzx');
INSERT INTO `t_system_region` VALUES ('7893', '654023', '霍城县', '654023', '新疆维吾尔自治区伊犁哈萨克自治州霍城县', 'xjwwezzqylhskzzzhcx');
INSERT INTO `t_system_region` VALUES ('7894', '654024', '巩留县', '654024', '新疆维吾尔自治区伊犁哈萨克自治州巩留县', 'xjwwezzqylhskzzzglx');
INSERT INTO `t_system_region` VALUES ('7895', '654025', '新源县', '654025', '新疆维吾尔自治区伊犁哈萨克自治州新源县', 'xjwwezzqylhskzzzxyx');
INSERT INTO `t_system_region` VALUES ('7896', '654026', '昭苏县', '654026', '新疆维吾尔自治区伊犁哈萨克自治州昭苏县', 'xjwwezzqylhskzzzzsx');
INSERT INTO `t_system_region` VALUES ('7897', '654027', '特克斯县', '654027', '新疆维吾尔自治区伊犁哈萨克自治州特克斯县', 'xjwwezzqylhskzzztksx');
INSERT INTO `t_system_region` VALUES ('7898', '654028', '尼勒克县', '654028', '新疆维吾尔自治区伊犁哈萨克自治州尼勒克县', 'xjwwezzqylhskzzznlkx');
INSERT INTO `t_system_region` VALUES ('7899', '6542', '塔城地区', '654200', '新疆维吾尔自治区塔城地区', 'xjwwezzqtcdq');
INSERT INTO `t_system_region` VALUES ('7900', '654201', '塔城市', '654201', '新疆维吾尔自治区塔城地区塔城市', 'xjwwezzqtcdqtcs');
INSERT INTO `t_system_region` VALUES ('7901', '654202', '乌苏市', '654202', '新疆维吾尔自治区塔城地区乌苏市', 'xjwwezzqtcdqwss');
INSERT INTO `t_system_region` VALUES ('7902', '654203', '塔城地直', '654203', '新疆维吾尔自治区塔城地区塔城地直', 'xjwwezzqtcdqtcdz');
INSERT INTO `t_system_region` VALUES ('7903', '654221', '额敏县', '654221', '新疆维吾尔自治区塔城地区额敏县', 'xjwwezzqtcdqemx');
INSERT INTO `t_system_region` VALUES ('7904', '654223', '沙湾县', '654223', '新疆维吾尔自治区塔城地区沙湾县', 'xjwwezzqtcdqswx');
INSERT INTO `t_system_region` VALUES ('7905', '654224', '托里县', '654224', '新疆维吾尔自治区塔城地区托里县', 'xjwwezzqtcdqtlx');
INSERT INTO `t_system_region` VALUES ('7906', '654225', '裕民县', '654225', '新疆维吾尔自治区塔城地区裕民县', 'xjwwezzqtcdqymx');
INSERT INTO `t_system_region` VALUES ('7907', '654226', '和布克赛尔蒙古自治县', '654226', '新疆维吾尔自治区塔城地区和布克赛尔蒙古自治县', 'xjwwezzqtcdqhbksemgzzx');
INSERT INTO `t_system_region` VALUES ('7908', '6543', '阿勒泰地区', '654300', '新疆维吾尔自治区阿勒泰地区', 'xjwwezzqaltdq');
INSERT INTO `t_system_region` VALUES ('7909', '654301', '阿勒泰市', '654301', '新疆维吾尔自治区阿勒泰地区阿勒泰市', 'xjwwezzqaltdqalts');
INSERT INTO `t_system_region` VALUES ('7910', '654321', '布尔津县', '654321', '新疆维吾尔自治区阿勒泰地区布尔津县', 'xjwwezzqaltdqbejx');
INSERT INTO `t_system_region` VALUES ('7911', '654322', '富蕴县', '654322', '新疆维吾尔自治区阿勒泰地区富蕴县', 'xjwwezzqaltdqfyx');
INSERT INTO `t_system_region` VALUES ('7912', '654323', '福海县', '654323', '新疆维吾尔自治区阿勒泰地区福海县', 'xjwwezzqaltdqfhx');
INSERT INTO `t_system_region` VALUES ('7913', '654324', '哈巴河县', '654324', '新疆维吾尔自治区阿勒泰地区哈巴河县', 'xjwwezzqaltdqhbhx');
INSERT INTO `t_system_region` VALUES ('7914', '654325', '青河县', '654325', '新疆维吾尔自治区阿勒泰地区青河县', 'xjwwezzqaltdqqhx');
INSERT INTO `t_system_region` VALUES ('7915', '654326', '吉木乃县', '654326', '新疆维吾尔自治区阿勒泰地区吉木乃县', 'xjwwezzqaltdqjmnx');
INSERT INTO `t_system_region` VALUES ('7916', '654327', '阿勒泰地直', '654327', '新疆维吾尔自治区阿勒泰地区阿勒泰地直', 'xjwwezzqaltdqaltdz');
INSERT INTO `t_system_region` VALUES ('7917', '6590', '省直辖行政单位', '659000', '新疆维吾尔自治区省直辖行政单位', 'xjwwezzqszxxzdw');
INSERT INTO `t_system_region` VALUES ('7918', '659001', '石河子市', '659001', '新疆维吾尔自治区省直辖行政单位石河子市', 'xjwwezzqszxxzdwshzs');
INSERT INTO `t_system_region` VALUES ('7919', '659002', '阿拉尔市', '659002', '新疆维吾尔自治区省直辖行政单位阿拉尔市', 'xjwwezzqszxxzdwales');
INSERT INTO `t_system_region` VALUES ('7920', '659003', '图木舒克市', '659003', '新疆维吾尔自治区省直辖行政单位图木舒克市', 'xjwwezzqszxxzdwtmsks');
INSERT INTO `t_system_region` VALUES ('7921', '659004', '五家渠市', '659004', '新疆维吾尔自治区省直辖行政单位五家渠市', 'xjwwezzqszxxzdwwjqs');
INSERT INTO `t_system_region` VALUES ('7922', '66', '新疆生产建设兵团', '660000', '新疆生产建设兵团', 'xjscjsbt');
INSERT INTO `t_system_region` VALUES ('7923', '6601', '兵团农一师', '660100', '新疆生产建设兵团农一师', 'xjscjsbtnys');
INSERT INTO `t_system_region` VALUES ('7924', '660101', '1团', '660101', '新疆生产建设兵团农一师1团', 'xjscjsbtnys1t');
INSERT INTO `t_system_region` VALUES ('7925', '660102', '2团', '660102', '新疆生产建设兵团农一师2团', 'xjscjsbtnys2t');
INSERT INTO `t_system_region` VALUES ('7926', '660103', '3团', '660103', '新疆生产建设兵团农一师3团', 'xjscjsbtnys3t');
INSERT INTO `t_system_region` VALUES ('7927', '660104', '4团', '660104', '新疆生产建设兵团农一师4团', 'xjscjsbtnys4t');
INSERT INTO `t_system_region` VALUES ('7928', '660105', '5团', '660105', '新疆生产建设兵团农一师5团', 'xjscjsbtnys5t');
INSERT INTO `t_system_region` VALUES ('7929', '660106', '6团', '660106', '新疆生产建设兵团农一师6团', 'xjscjsbtnys6t');
INSERT INTO `t_system_region` VALUES ('7930', '660107', '7团', '660107', '新疆生产建设兵团农一师7团', 'xjscjsbtnys7t');
INSERT INTO `t_system_region` VALUES ('7931', '660108', '8团', '660108', '新疆生产建设兵团农一师8团', 'xjscjsbtnys8t');
INSERT INTO `t_system_region` VALUES ('7932', '660109', '9团', '660109', '新疆生产建设兵团农一师9团', 'xjscjsbtnys9t');
INSERT INTO `t_system_region` VALUES ('7933', '660110', '10团', '660110', '新疆生产建设兵团农一师10团', 'xjscjsbtnys10t');
INSERT INTO `t_system_region` VALUES ('7934', '660111', '11团', '660111', '新疆生产建设兵团农一师11团', 'xjscjsbtnys11t');
INSERT INTO `t_system_region` VALUES ('7935', '660112', '12团', '660112', '新疆生产建设兵团农一师12团', 'xjscjsbtnys12t');
INSERT INTO `t_system_region` VALUES ('7936', '660113', '13团', '660113', '新疆生产建设兵团农一师13团', 'xjscjsbtnys13t');
INSERT INTO `t_system_region` VALUES ('7937', '660114', '14团', '660114', '新疆生产建设兵团农一师14团', 'xjscjsbtnys14t');
INSERT INTO `t_system_region` VALUES ('7938', '660115', '15团', '660115', '新疆生产建设兵团农一师15团', 'xjscjsbtnys15t');
INSERT INTO `t_system_region` VALUES ('7939', '660116', '16团', '660116', '新疆生产建设兵团农一师16团', 'xjscjsbtnys16t');
INSERT INTO `t_system_region` VALUES ('7940', '660117', '塔水处', '660117', '新疆生产建设兵团农一师塔水处', 'xjscjsbtnystsc');
INSERT INTO `t_system_region` VALUES ('7941', '660118', '水工处', '660118', '新疆生产建设兵团农一师水工处', 'xjscjsbtnyssgc');
INSERT INTO `t_system_region` VALUES ('7942', '660119', '沙井子民族学校', '660119', '新疆生产建设兵团农一师沙井子民族学校', 'xjscjsbtnyssjzmzxx');
INSERT INTO `t_system_region` VALUES ('7943', '660122', '农一师师直', '660122', '新疆生产建设兵团农一师农一师师直', 'xjscjsbtnysnyssz');
INSERT INTO `t_system_region` VALUES ('7944', '6602', '兵团农二师', '660200', '新疆生产建设兵团农二师', 'xjscjsbtnes');
INSERT INTO `t_system_region` VALUES ('7945', '660201', '21团', '660201', '新疆生产建设兵团农二师21团', 'xjscjsbtnes21t');
INSERT INTO `t_system_region` VALUES ('7946', '660202', '22团', '660202', '新疆生产建设兵团农二师22团', 'xjscjsbtnes22t');
INSERT INTO `t_system_region` VALUES ('7947', '660203', '23团', '660203', '新疆生产建设兵团农二师23团', 'xjscjsbtnes23t');
INSERT INTO `t_system_region` VALUES ('7948', '660204', '24团', '660204', '新疆生产建设兵团农二师24团', 'xjscjsbtnes24t');
INSERT INTO `t_system_region` VALUES ('7949', '660205', '25团', '660205', '新疆生产建设兵团农二师25团', 'xjscjsbtnes25t');
INSERT INTO `t_system_region` VALUES ('7950', '660206', '26团', '660206', '新疆生产建设兵团农二师26团', 'xjscjsbtnes26t');
INSERT INTO `t_system_region` VALUES ('7951', '660207', '27团', '660207', '新疆生产建设兵团农二师27团', 'xjscjsbtnes27t');
INSERT INTO `t_system_region` VALUES ('7952', '660208', '28团', '660208', '新疆生产建设兵团农二师28团', 'xjscjsbtnes28t');
INSERT INTO `t_system_region` VALUES ('7953', '660209', '29团', '660209', '新疆生产建设兵团农二师29团', 'xjscjsbtnes29t');
INSERT INTO `t_system_region` VALUES ('7954', '660210', '30团', '660210', '新疆生产建设兵团农二师30团', 'xjscjsbtnes30t');
INSERT INTO `t_system_region` VALUES ('7955', '660211', '31团', '660211', '新疆生产建设兵团农二师31团', 'xjscjsbtnes31t');
INSERT INTO `t_system_region` VALUES ('7956', '660212', '32团', '660212', '新疆生产建设兵团农二师32团', 'xjscjsbtnes32t');
INSERT INTO `t_system_region` VALUES ('7957', '660213', '33团', '660213', '新疆生产建设兵团农二师33团', 'xjscjsbtnes33t');
INSERT INTO `t_system_region` VALUES ('7958', '660214', '34团', '660214', '新疆生产建设兵团农二师34团', 'xjscjsbtnes34t');
INSERT INTO `t_system_region` VALUES ('7959', '660215', '35团', '660215', '新疆生产建设兵团农二师35团', 'xjscjsbtnes35t');
INSERT INTO `t_system_region` VALUES ('7960', '660216', '36团', '660216', '新疆生产建设兵团农二师36团', 'xjscjsbtnes36t');
INSERT INTO `t_system_region` VALUES ('7961', '660217', '223团', '660217', '新疆生产建设兵团农二师223团', 'xjscjsbtnes223t');
INSERT INTO `t_system_region` VALUES ('7962', '660218', '塔什店', '660218', '新疆生产建设兵团农二师塔什店', 'xjscjsbtnestsd');
INSERT INTO `t_system_region` VALUES ('7963', '660219', '且末支队', '660219', '新疆生产建设兵团农二师且末支队', 'xjscjsbtnesqmzd');
INSERT INTO `t_system_region` VALUES ('7964', '660221', '十一农场', '660221', '新疆生产建设兵团农二师十一农场', 'xjscjsbtnessync');
INSERT INTO `t_system_region` VALUES ('7965', '660222', '三建', '660222', '新疆生产建设兵团农二师三建', 'xjscjsbtnessj');
INSERT INTO `t_system_region` VALUES ('7966', '660224', '农二师师直', '660224', '新疆生产建设兵团农二师农二师师直', 'xjscjsbtnesnessz');
INSERT INTO `t_system_region` VALUES ('7967', '6603', '兵团农三师', '660300', '新疆生产建设兵团农三师', 'xjscjsbtnss');
INSERT INTO `t_system_region` VALUES ('7968', '660301', '41团', '660301', '新疆生产建设兵团农三师41团', 'xjscjsbtnss41t');
INSERT INTO `t_system_region` VALUES ('7969', '660302', '42团', '660302', '新疆生产建设兵团农三师42团', 'xjscjsbtnss42t');
INSERT INTO `t_system_region` VALUES ('7970', '660303', '43团', '660303', '新疆生产建设兵团农三师43团', 'xjscjsbtnss43t');
INSERT INTO `t_system_region` VALUES ('7971', '660304', '44团', '660304', '新疆生产建设兵团农三师44团', 'xjscjsbtnss44t');
INSERT INTO `t_system_region` VALUES ('7972', '660305', '45团', '660305', '新疆生产建设兵团农三师45团', 'xjscjsbtnss45t');
INSERT INTO `t_system_region` VALUES ('7973', '660306', '46团', '660306', '新疆生产建设兵团农三师46团', 'xjscjsbtnss46t');
INSERT INTO `t_system_region` VALUES ('7974', '660307', '48团', '660307', '新疆生产建设兵团农三师48团', 'xjscjsbtnss48t');
INSERT INTO `t_system_region` VALUES ('7975', '660308', '49团', '660308', '新疆生产建设兵团农三师49团', 'xjscjsbtnss49t');
INSERT INTO `t_system_region` VALUES ('7976', '660309', '50团', '660309', '新疆生产建设兵团农三师50团', 'xjscjsbtnss50t');
INSERT INTO `t_system_region` VALUES ('7977', '660310', '51团', '660310', '新疆生产建设兵团农三师51团', 'xjscjsbtnss51t');
INSERT INTO `t_system_region` VALUES ('7978', '660311', '52团', '660311', '新疆生产建设兵团农三师52团', 'xjscjsbtnss52t');
INSERT INTO `t_system_region` VALUES ('7979', '660312', '53团', '660312', '新疆生产建设兵团农三师53团', 'xjscjsbtnss53t');
INSERT INTO `t_system_region` VALUES ('7980', '660313', '托云牧场', '660313', '新疆生产建设兵团农三师托云牧场', 'xjscjsbtnsstymc');
INSERT INTO `t_system_region` VALUES ('7981', '660314', '东风农场', '660314', '新疆生产建设兵团农三师东风农场', 'xjscjsbtnssdfnc');
INSERT INTO `t_system_region` VALUES ('7982', '660315', '伽师总场', '660315', '新疆生产建设兵团农三师伽师总场', 'xjscjsbtnssjszc');
INSERT INTO `t_system_region` VALUES ('7983', '660316', '红旗农场', '660316', '新疆生产建设兵团农三师红旗农场', 'xjscjsbtnsshqnc');
INSERT INTO `t_system_region` VALUES ('7984', '660317', '叶城二牧场', '660317', '新疆生产建设兵团农三师叶城二牧场', 'xjscjsbtnssycemc');
INSERT INTO `t_system_region` VALUES ('7985', '660319', '农三师师直', '660319', '新疆生产建设兵团农三师农三师师直', 'xjscjsbtnssnsssz');
INSERT INTO `t_system_region` VALUES ('7986', '6604', '兵团农四师', '660400', '新疆生产建设兵团农四师', 'xjscjsbtnss');
INSERT INTO `t_system_region` VALUES ('7987', '660401', '61团', '660401', '新疆生产建设兵团农四师61团', 'xjscjsbtnss61t');
INSERT INTO `t_system_region` VALUES ('7988', '660402', '62团', '660402', '新疆生产建设兵团农四师62团', 'xjscjsbtnss62t');
INSERT INTO `t_system_region` VALUES ('7989', '660403', '63团', '660403', '新疆生产建设兵团农四师63团', 'xjscjsbtnss63t');
INSERT INTO `t_system_region` VALUES ('7990', '660404', '64团', '660404', '新疆生产建设兵团农四师64团', 'xjscjsbtnss64t');
INSERT INTO `t_system_region` VALUES ('7991', '660405', '65团', '660405', '新疆生产建设兵团农四师65团', 'xjscjsbtnss65t');
INSERT INTO `t_system_region` VALUES ('7992', '660406', '66团', '660406', '新疆生产建设兵团农四师66团', 'xjscjsbtnss66t');
INSERT INTO `t_system_region` VALUES ('7993', '660407', '67团', '660407', '新疆生产建设兵团农四师67团', 'xjscjsbtnss67t');
INSERT INTO `t_system_region` VALUES ('7994', '660408', '68团', '660408', '新疆生产建设兵团农四师68团', 'xjscjsbtnss68t');
INSERT INTO `t_system_region` VALUES ('7995', '660409', '69团', '660409', '新疆生产建设兵团农四师69团', 'xjscjsbtnss69t');
INSERT INTO `t_system_region` VALUES ('7996', '660410', '70团', '660410', '新疆生产建设兵团农四师70团', 'xjscjsbtnss70t');
INSERT INTO `t_system_region` VALUES ('7997', '660411', '71团', '660411', '新疆生产建设兵团农四师71团', 'xjscjsbtnss71t');
INSERT INTO `t_system_region` VALUES ('7998', '660412', '72团', '660412', '新疆生产建设兵团农四师72团', 'xjscjsbtnss72t');
INSERT INTO `t_system_region` VALUES ('7999', '660413', '73团', '660413', '新疆生产建设兵团农四师73团', 'xjscjsbtnss73t');
INSERT INTO `t_system_region` VALUES ('8000', '660414', '74团', '660414', '新疆生产建设兵团农四师74团', 'xjscjsbtnss74t');
INSERT INTO `t_system_region` VALUES ('8001', '660415', '75团', '660415', '新疆生产建设兵团农四师75团', 'xjscjsbtnss75t');
INSERT INTO `t_system_region` VALUES ('8002', '660416', '76团', '660416', '新疆生产建设兵团农四师76团', 'xjscjsbtnss76t');
INSERT INTO `t_system_region` VALUES ('8003', '660417', '77团', '660417', '新疆生产建设兵团农四师77团', 'xjscjsbtnss77t');
INSERT INTO `t_system_region` VALUES ('8004', '660418', '78团', '660418', '新疆生产建设兵团农四师78团', 'xjscjsbtnss78t');
INSERT INTO `t_system_region` VALUES ('8005', '660419', '79团', '660419', '新疆生产建设兵团农四师79团', 'xjscjsbtnss79t');
INSERT INTO `t_system_region` VALUES ('8006', '660420', '拜什墩', '660420', '新疆生产建设兵团农四师拜什墩', 'xjscjsbtnssbsd');
INSERT INTO `t_system_region` VALUES ('8007', '660421', '良繁场', '660421', '新疆生产建设兵团农四师良繁场', 'xjscjsbtnsslfc');
INSERT INTO `t_system_region` VALUES ('8008', '660422', '农四师师直', '660422', '新疆生产建设兵团农四师农四师师直', 'xjscjsbtnssnsssz');
INSERT INTO `t_system_region` VALUES ('8009', '6605', '兵团农五师', '660500', '新疆生产建设兵团农五师', 'xjscjsbtnws');
INSERT INTO `t_system_region` VALUES ('8010', '660501', '81团', '660501', '新疆生产建设兵团农五师81团', 'xjscjsbtnws81t');
INSERT INTO `t_system_region` VALUES ('8011', '660502', '82团', '660502', '新疆生产建设兵团农五师82团', 'xjscjsbtnws82t');
INSERT INTO `t_system_region` VALUES ('8012', '660503', '83团', '660503', '新疆生产建设兵团农五师83团', 'xjscjsbtnws83t');
INSERT INTO `t_system_region` VALUES ('8013', '660504', '84团', '660504', '新疆生产建设兵团农五师84团', 'xjscjsbtnws84t');
INSERT INTO `t_system_region` VALUES ('8014', '660505', '85团', '660505', '新疆生产建设兵团农五师85团', 'xjscjsbtnws85t');
INSERT INTO `t_system_region` VALUES ('8015', '660506', '86团', '660506', '新疆生产建设兵团农五师86团', 'xjscjsbtnws86t');
INSERT INTO `t_system_region` VALUES ('8016', '660507', '87团', '660507', '新疆生产建设兵团农五师87团', 'xjscjsbtnws87t');
INSERT INTO `t_system_region` VALUES ('8017', '660508', '88团', '660508', '新疆生产建设兵团农五师88团', 'xjscjsbtnws88t');
INSERT INTO `t_system_region` VALUES ('8018', '660509', '89团', '660509', '新疆生产建设兵团农五师89团', 'xjscjsbtnws89t');
INSERT INTO `t_system_region` VALUES ('8019', '660510', '90团', '660510', '新疆生产建设兵团农五师90团', 'xjscjsbtnws90t');
INSERT INTO `t_system_region` VALUES ('8020', '660511', '91团', '660511', '新疆生产建设兵团农五师91团', 'xjscjsbtnws91t');
INSERT INTO `t_system_region` VALUES ('8021', '660512', '农五师师直', '660512', '新疆生产建设兵团农五师农五师师直', 'xjscjsbtnwsnwssz');
INSERT INTO `t_system_region` VALUES ('8022', '6606', '兵团农六师', '660600', '新疆生产建设兵团农六师', 'xjscjsbtnls');
INSERT INTO `t_system_region` VALUES ('8023', '660601', '101团', '660601', '新疆生产建设兵团农六师101团', 'xjscjsbtnls101t');
INSERT INTO `t_system_region` VALUES ('8024', '660602', '102团', '660602', '新疆生产建设兵团农六师102团', 'xjscjsbtnls102t');
INSERT INTO `t_system_region` VALUES ('8025', '660603', '103团', '660603', '新疆生产建设兵团农六师103团', 'xjscjsbtnls103t');
INSERT INTO `t_system_region` VALUES ('8026', '660604', '105团', '660604', '新疆生产建设兵团农六师105团', 'xjscjsbtnls105t');
INSERT INTO `t_system_region` VALUES ('8027', '660605', '106团', '660605', '新疆生产建设兵团农六师106团', 'xjscjsbtnls106t');
INSERT INTO `t_system_region` VALUES ('8028', '660606', '107团', '660606', '新疆生产建设兵团农六师107团', 'xjscjsbtnls107t');
INSERT INTO `t_system_region` VALUES ('8029', '660607', '108团', '660607', '新疆生产建设兵团农六师108团', 'xjscjsbtnls108t');
INSERT INTO `t_system_region` VALUES ('8030', '660608', '109团', '660608', '新疆生产建设兵团农六师109团', 'xjscjsbtnls109t');
INSERT INTO `t_system_region` VALUES ('8031', '660609', '110团', '660609', '新疆生产建设兵团农六师110团', 'xjscjsbtnls110t');
INSERT INTO `t_system_region` VALUES ('8032', '660610', '111团', '660610', '新疆生产建设兵团农六师111团', 'xjscjsbtnls111t');
INSERT INTO `t_system_region` VALUES ('8033', '660611', '芳草湖', '660611', '新疆生产建设兵团农六师芳草湖', 'xjscjsbtnlsfch');
INSERT INTO `t_system_region` VALUES ('8034', '660612', '新湖', '660612', '新疆生产建设兵团农六师新湖', 'xjscjsbtnlsxh');
INSERT INTO `t_system_region` VALUES ('8035', '660613', '军户', '660613', '新疆生产建设兵团农六师军户', 'xjscjsbtnlsjh');
INSERT INTO `t_system_region` VALUES ('8036', '660614', '共青团', '660614', '新疆生产建设兵团农六师共青团', 'xjscjsbtnlsgqt');
INSERT INTO `t_system_region` VALUES ('8037', '660615', '六运湖', '660615', '新疆生产建设兵团农六师六运湖', 'xjscjsbtnlslyh');
INSERT INTO `t_system_region` VALUES ('8038', '660616', '土墩子', '660616', '新疆生产建设兵团农六师土墩子', 'xjscjsbtnlstdz');
INSERT INTO `t_system_region` VALUES ('8039', '660617', '红旗', '660617', '新疆生产建设兵团农六师红旗', 'xjscjsbtnlshq');
INSERT INTO `t_system_region` VALUES ('8040', '660618', '奇台', '660618', '新疆生产建设兵团农六师奇台', 'xjscjsbtnlsqt');
INSERT INTO `t_system_region` VALUES ('8041', '660619', '北塔山', '660619', '新疆生产建设兵团农六师北塔山', 'xjscjsbtnlsbts');
INSERT INTO `t_system_region` VALUES ('8042', '660620', '大黄山学校', '660620', '新疆生产建设兵团农六师大黄山学校', 'xjscjsbtnlsdhsxx');
INSERT INTO `t_system_region` VALUES ('8043', '660621', '十三户学校', '660621', '新疆生产建设兵团农六师十三户学校', 'xjscjsbtnlssshxx');
INSERT INTO `t_system_region` VALUES ('8044', '660622', '农六师师直', '660622', '新疆生产建设兵团农六师农六师师直', 'xjscjsbtnlsnlssz');
INSERT INTO `t_system_region` VALUES ('8045', '6607', '兵团农七师', '660700', '新疆生产建设兵团农七师', 'xjscjsbtnqs');
INSERT INTO `t_system_region` VALUES ('8046', '660701', '123团', '660701', '新疆生产建设兵团农七师123团', 'xjscjsbtnqs123t');
INSERT INTO `t_system_region` VALUES ('8047', '660702', '124团', '660702', '新疆生产建设兵团农七师124团', 'xjscjsbtnqs124t');
INSERT INTO `t_system_region` VALUES ('8048', '660703', '125团', '660703', '新疆生产建设兵团农七师125团', 'xjscjsbtnqs125t');
INSERT INTO `t_system_region` VALUES ('8049', '660704', '126团', '660704', '新疆生产建设兵团农七师126团', 'xjscjsbtnqs126t');
INSERT INTO `t_system_region` VALUES ('8050', '660705', '127团', '660705', '新疆生产建设兵团农七师127团', 'xjscjsbtnqs127t');
INSERT INTO `t_system_region` VALUES ('8051', '660706', '128团', '660706', '新疆生产建设兵团农七师128团', 'xjscjsbtnqs128t');
INSERT INTO `t_system_region` VALUES ('8052', '660707', '129团', '660707', '新疆生产建设兵团农七师129团', 'xjscjsbtnqs129t');
INSERT INTO `t_system_region` VALUES ('8053', '660708', '130团', '660708', '新疆生产建设兵团农七师130团', 'xjscjsbtnqs130t');
INSERT INTO `t_system_region` VALUES ('8054', '660709', '131团', '660709', '新疆生产建设兵团农七师131团', 'xjscjsbtnqs131t');
INSERT INTO `t_system_region` VALUES ('8055', '660710', '137团', '660710', '新疆生产建设兵团农七师137团', 'xjscjsbtnqs137t');
INSERT INTO `t_system_region` VALUES ('8056', '660712', '农七师奎管处', '660712', '新疆生产建设兵团农七师农七师奎管处', 'xjscjsbtnqsnqskgc');
INSERT INTO `t_system_region` VALUES ('8057', '660716', '农七师奎东农场', '660716', '新疆生产建设兵团农七师农七师奎东农场', 'xjscjsbtnqsnqskdnc');
INSERT INTO `t_system_region` VALUES ('8058', '660717', '农七师师直', '660717', '新疆生产建设兵团农七师农七师师直', 'xjscjsbtnqsnqssz');
INSERT INTO `t_system_region` VALUES ('8059', '6608', '兵团农八师', '660800', '新疆生产建设兵团农八师', 'xjscjsbtnbs');
INSERT INTO `t_system_region` VALUES ('8060', '660801', '121团', '660801', '新疆生产建设兵团农八师121团', 'xjscjsbtnbs121t');
INSERT INTO `t_system_region` VALUES ('8061', '660802', '122团', '660802', '新疆生产建设兵团农八师122团', 'xjscjsbtnbs122t');
INSERT INTO `t_system_region` VALUES ('8062', '660804', '133团', '660804', '新疆生产建设兵团农八师133团', 'xjscjsbtnbs133t');
INSERT INTO `t_system_region` VALUES ('8063', '660805', '134团', '660805', '新疆生产建设兵团农八师134团', 'xjscjsbtnbs134t');
INSERT INTO `t_system_region` VALUES ('8064', '660806', '135团', '660806', '新疆生产建设兵团农八师135团', 'xjscjsbtnbs135t');
INSERT INTO `t_system_region` VALUES ('8065', '660807', '136团', '660807', '新疆生产建设兵团农八师136团', 'xjscjsbtnbs136t');
INSERT INTO `t_system_region` VALUES ('8066', '660808', '141团', '660808', '新疆生产建设兵团农八师141团', 'xjscjsbtnbs141t');
INSERT INTO `t_system_region` VALUES ('8067', '660809', '142团', '660809', '新疆生产建设兵团农八师142团', 'xjscjsbtnbs142t');
INSERT INTO `t_system_region` VALUES ('8068', '660810', '143团', '660810', '新疆生产建设兵团农八师143团', 'xjscjsbtnbs143t');
INSERT INTO `t_system_region` VALUES ('8069', '660811', '144团', '660811', '新疆生产建设兵团农八师144团', 'xjscjsbtnbs144t');
INSERT INTO `t_system_region` VALUES ('8070', '660812', '石总场', '660812', '新疆生产建设兵团农八师石总场', 'xjscjsbtnbsszc');
INSERT INTO `t_system_region` VALUES ('8071', '660813', '147团', '660813', '新疆生产建设兵团农八师147团', 'xjscjsbtnbs147t');
INSERT INTO `t_system_region` VALUES ('8072', '660814', '148团', '660814', '新疆生产建设兵团农八师148团', 'xjscjsbtnbs148t');
INSERT INTO `t_system_region` VALUES ('8073', '660815', '149团', '660815', '新疆生产建设兵团农八师149团', 'xjscjsbtnbs149t');
INSERT INTO `t_system_region` VALUES ('8074', '660816', '150团', '660816', '新疆生产建设兵团农八师150团', 'xjscjsbtnbs150t');
INSERT INTO `t_system_region` VALUES ('8075', '660818', '152团', '660818', '新疆生产建设兵团农八师152团', 'xjscjsbtnbs152t');
INSERT INTO `t_system_region` VALUES ('8076', '660821', '通联中学', '660821', '新疆生产建设兵团农八师通联中学', 'xjscjsbtnbstlzx');
INSERT INTO `t_system_region` VALUES ('8077', '660822', '柴油机厂学校', '660822', '新疆生产建设兵团农八师柴油机厂学校', 'xjscjsbtnbscyjcxx');
INSERT INTO `t_system_region` VALUES ('8078', '660823', '玛管处中学', '660823', '新疆生产建设兵团农八师玛管处中学', 'xjscjsbtnbsmgczx');
INSERT INTO `t_system_region` VALUES ('8079', '660824', '天富红山嘴学校', '660824', '新疆生产建设兵团农八师天富红山嘴学校', 'xjscjsbtnbstfhszxx');
INSERT INTO `t_system_region` VALUES ('8080', '660825', '南山中学', '660825', '新疆生产建设兵团农八师南山中学', 'xjscjsbtnbsnszx');
INSERT INTO `t_system_region` VALUES ('8081', '660826', '六建', '660826', '新疆生产建设兵团农八师六建', 'xjscjsbtnbslj');
INSERT INTO `t_system_region` VALUES ('8082', '660827', '路桥中学', '660827', '新疆生产建设兵团农八师路桥中学', 'xjscjsbtnbslqzx');
INSERT INTO `t_system_region` VALUES ('8083', '660829', '农八师师直', '660829', '新疆生产建设兵团农八师农八师师直', 'xjscjsbtnbsnbssz');
INSERT INTO `t_system_region` VALUES ('8084', '6609', '兵团农九师', '660900', '新疆生产建设兵团农九师', 'xjscjsbtnjs');
INSERT INTO `t_system_region` VALUES ('8085', '660901', '161团', '660901', '新疆生产建设兵团农九师161团', 'xjscjsbtnjs161t');
INSERT INTO `t_system_region` VALUES ('8086', '660902', '162团', '660902', '新疆生产建设兵团农九师162团', 'xjscjsbtnjs162t');
INSERT INTO `t_system_region` VALUES ('8087', '660903', '163团', '660903', '新疆生产建设兵团农九师163团', 'xjscjsbtnjs163t');
INSERT INTO `t_system_region` VALUES ('8088', '660904', '164团', '660904', '新疆生产建设兵团农九师164团', 'xjscjsbtnjs164t');
INSERT INTO `t_system_region` VALUES ('8089', '660905', '165团', '660905', '新疆生产建设兵团农九师165团', 'xjscjsbtnjs165t');
INSERT INTO `t_system_region` VALUES ('8090', '660906', '166团', '660906', '新疆生产建设兵团农九师166团', 'xjscjsbtnjs166t');
INSERT INTO `t_system_region` VALUES ('8091', '660907', '167团', '660907', '新疆生产建设兵团农九师167团', 'xjscjsbtnjs167t');
INSERT INTO `t_system_region` VALUES ('8092', '660908', '168团', '660908', '新疆生产建设兵团农九师168团', 'xjscjsbtnjs168t');
INSERT INTO `t_system_region` VALUES ('8093', '660909', '169团', '660909', '新疆生产建设兵团农九师169团', 'xjscjsbtnjs169t');
INSERT INTO `t_system_region` VALUES ('8094', '660910', '170团', '660910', '新疆生产建设兵团农九师170团', 'xjscjsbtnjs170t');
INSERT INTO `t_system_region` VALUES ('8095', '660911', '团结农场', '660911', '新疆生产建设兵团农九师团结农场', 'xjscjsbtnjstjnc');
INSERT INTO `t_system_region` VALUES ('8096', '660912', '农九师师直', '660912', '新疆生产建设兵团农九师农九师师直', 'xjscjsbtnjsnjssz');
INSERT INTO `t_system_region` VALUES ('8097', '6610', '兵团农十师', '661000', '新疆生产建设兵团农十师', 'xjscjsbtnss');
INSERT INTO `t_system_region` VALUES ('8098', '661001', '181团', '661001', '新疆生产建设兵团农十师181团', 'xjscjsbtnss181t');
INSERT INTO `t_system_region` VALUES ('8099', '661002', '182团', '661002', '新疆生产建设兵团农十师182团', 'xjscjsbtnss182t');
INSERT INTO `t_system_region` VALUES ('8100', '661003', '183团', '661003', '新疆生产建设兵团农十师183团', 'xjscjsbtnss183t');
INSERT INTO `t_system_region` VALUES ('8101', '661004', '184团', '661004', '新疆生产建设兵团农十师184团', 'xjscjsbtnss184t');
INSERT INTO `t_system_region` VALUES ('8102', '661005', '185团', '661005', '新疆生产建设兵团农十师185团', 'xjscjsbtnss185t');
INSERT INTO `t_system_region` VALUES ('8103', '661006', '186团', '661006', '新疆生产建设兵团农十师186团', 'xjscjsbtnss186t');
INSERT INTO `t_system_region` VALUES ('8104', '661007', '187团', '661007', '新疆生产建设兵团农十师187团', 'xjscjsbtnss187t');
INSERT INTO `t_system_region` VALUES ('8105', '661008', '188团', '661008', '新疆生产建设兵团农十师188团', 'xjscjsbtnss188t');
INSERT INTO `t_system_region` VALUES ('8106', '661010', '190团', '661010', '新疆生产建设兵团农十师190团', 'xjscjsbtnss190t');
INSERT INTO `t_system_region` VALUES ('8107', '661013', '农十师师直', '661013', '新疆生产建设兵团农十师农十师师直', 'xjscjsbtnssnsssz');
INSERT INTO `t_system_region` VALUES ('8108', '6611', '兵团建工师', '661100', '新疆生产建设兵团建工师', 'xjscjsbtjgs');
INSERT INTO `t_system_region` VALUES ('8109', '661101', '建工师师属', '661101', '新疆生产建设兵团建工师建工师师属', 'xjscjsbtjgsjgsss');
INSERT INTO `t_system_region` VALUES ('8110', '6612', '兵团农十二师', '661200', '新疆生产建设兵团农十二师', 'xjscjsbtnses');
INSERT INTO `t_system_region` VALUES ('8111', '661201', '养禽场', '661201', '新疆生产建设兵团农十二师养禽场', 'xjscjsbtnsesyqc');
INSERT INTO `t_system_region` VALUES ('8112', '661202', '五一农场', '661202', '新疆生产建设兵团农十二师五一农场', 'xjscjsbtnseswync');
INSERT INTO `t_system_region` VALUES ('8113', '661203', '三坪农场', '661203', '新疆生产建设兵团农十二师三坪农场', 'xjscjsbtnsesspnc');
INSERT INTO `t_system_region` VALUES ('8114', '661204', '头屯河农场', '661204', '新疆生产建设兵团农十二师头屯河农场', 'xjscjsbtnsestthnc');
INSERT INTO `t_system_region` VALUES ('8115', '661205', '一零四团', '661205', '新疆生产建设兵团农十二师一零四团', 'xjscjsbtnsesylst');
INSERT INTO `t_system_region` VALUES ('8116', '661206', '西山农场', '661206', '新疆生产建设兵团农十二师西山农场', 'xjscjsbtnsesxsnc');
INSERT INTO `t_system_region` VALUES ('8117', '661207', '二二一团', '661207', '新疆生产建设兵团农十二师二二一团', 'xjscjsbtnseseeyt');
INSERT INTO `t_system_region` VALUES ('8118', '661208', '农十二师师直', '661208', '新疆生产建设兵团农十二师农十二师师直', 'xjscjsbtnsesnsessz');
INSERT INTO `t_system_region` VALUES ('8119', '6613', '兵团农十三师', '661300', '新疆生产建设兵团农十三师', 'xjscjsbtnsss');
INSERT INTO `t_system_region` VALUES ('8120', '661301', '红星二牧场', '661301', '新疆生产建设兵团农十三师红星二牧场', 'xjscjsbtnssshxemc');
INSERT INTO `t_system_region` VALUES ('8121', '661302', '红星一牧场', '661302', '新疆生产建设兵团农十三师红星一牧场', 'xjscjsbtnssshxymc');
INSERT INTO `t_system_region` VALUES ('8122', '661303', '淖毛湖农场', '661303', '新疆生产建设兵团农十三师淖毛湖农场', 'xjscjsbtnsssnmhnc');
INSERT INTO `t_system_region` VALUES ('8123', '661304', '红星二场', '661304', '新疆生产建设兵团农十三师红星二场', 'xjscjsbtnssshxec');
INSERT INTO `t_system_region` VALUES ('8124', '661305', '黄田农场', '661305', '新疆生产建设兵团农十三师黄田农场', 'xjscjsbtnssshtnc');
INSERT INTO `t_system_region` VALUES ('8125', '661306', '红星三场', '661306', '新疆生产建设兵团农十三师红星三场', 'xjscjsbtnssshxsc');
INSERT INTO `t_system_region` VALUES ('8126', '661307', '红山农场', '661307', '新疆生产建设兵团农十三师红山农场', 'xjscjsbtnssshsnc');
INSERT INTO `t_system_region` VALUES ('8127', '661308', '柳树泉农场', '661308', '新疆生产建设兵团农十三师柳树泉农场', 'xjscjsbtnssslsqnc');
INSERT INTO `t_system_region` VALUES ('8128', '661309', '红星一场', '661309', '新疆生产建设兵团农十三师红星一场', 'xjscjsbtnssshxyc');
INSERT INTO `t_system_region` VALUES ('8129', '661310', '红星四场', '661310', '新疆生产建设兵团农十三师红星四场', 'xjscjsbtnssshxsc');
INSERT INTO `t_system_region` VALUES ('8130', '661311', '火箭农场', '661311', '新疆生产建设兵团农十三师火箭农场', 'xjscjsbtnssshjnc');
INSERT INTO `t_system_region` VALUES ('8131', '661312', '农十三师师直', '661312', '新疆生产建设兵团农十三师农十三师师直', 'xjscjsbtnsssnssssz');
INSERT INTO `t_system_region` VALUES ('8132', '6614', '兵团农十四师', '661400', '新疆生产建设兵团农十四师', 'xjscjsbtnsss');
INSERT INTO `t_system_region` VALUES ('8133', '661401', '四十七团', '661401', '新疆生产建设兵团农十四师四十七团', 'xjscjsbtnsssssqt');
INSERT INTO `t_system_region` VALUES ('8134', '661402', '皮山农场', '661402', '新疆生产建设兵团农十四师皮山农场', 'xjscjsbtnssspsnc');
INSERT INTO `t_system_region` VALUES ('8135', '661403', '一牧场', '661403', '新疆生产建设兵团农十四师一牧场', 'xjscjsbtnsssymc');
INSERT INTO `t_system_region` VALUES ('8136', '661404', '224团', '661404', '新疆生产建设兵团农十四师224团', 'xjscjsbtnsss224t');
INSERT INTO `t_system_region` VALUES ('8137', '6615', '兵团直属', '661500', '新疆生产建设兵团直属', 'xjscjsbtzs');
INSERT INTO `t_system_region` VALUES ('8138', '661501', '兵团直属', '661501', '新疆生产建设兵团直属兵团直属', 'xjscjsbtzsbtzs');
INSERT INTO `t_system_region` VALUES ('8139', '662201', '222团', '662201', '新疆生产建设兵团农二十二师222团', 'xjscjsbtneses222t');
INSERT INTO `t_system_region` VALUES ('8140', '71', '台湾省', '710000', '台湾省', 'tws');
INSERT INTO `t_system_region` VALUES ('8141', '81', '香港特别行政区', '810000', '香港特别行政区', 'xgtbxzq');
INSERT INTO `t_system_region` VALUES ('8142', '82', '澳门特别行政区', '820000', '澳门特别行政区', 'amtbxzq');
INSERT INTO `t_system_region` VALUES ('610131', '610131', '沣东新城', '610131', '陕西省西安市沣东新城', 'sxsxasfdxc');
INSERT INTO `t_system_region` VALUES ('610503', '610503', '渭南市高新区', '610503', '陕西省渭南市高新区', 'sxswnsgxq');
INSERT INTO `t_system_region` VALUES ('610832', '610832', '榆林市高新区', '610832', '陕西省榆林市高新区', 'sxsylsgxq');
INSERT INTO `t_system_region` VALUES ('611200', '6112', '石油普教中心', '611200', '石油普教中心', 'sypjzx');
INSERT INTO `t_system_region` VALUES ('619900', '6199', '演示地市', '619900', '演示地市', 'sxsysds');
INSERT INTO `t_system_region` VALUES ('619901', '619901', '演示县区1', '619901', '演示县区1', 'ysds1');
INSERT INTO `t_system_region` VALUES ('619902', '619902', '演示地市2', '619902', '演示地市2', 'ysds2');

-- ----------------------------
-- Table structure for `t_system_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role`;
CREATE TABLE `t_system_role` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(150) DEFAULT NULL,
  `DESCRIPTION` varchar(1500) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `ROLETYPE` int(11) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_role
-- ----------------------------
INSERT INTO `t_system_role` VALUES ('1', '超级管理员角色', '超级管理员角色', '2015-11-15 11:16:22', '1');

-- ----------------------------
-- Table structure for `t_system_role_module`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_role_module`;
CREATE TABLE `t_system_role_module` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLEID` int(11) DEFAULT NULL,
  `MODULEID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_SYSTEM_ROLE_MODULE_ROLEID` (`ROLEID`)
) ENGINE=InnoDB AUTO_INCREMENT=2899 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_role_module
-- ----------------------------
INSERT INTO `t_system_role_module` VALUES ('2644', '1', '1100000');
INSERT INTO `t_system_role_module` VALUES ('2645', '1', '1200000');
INSERT INTO `t_system_role_module` VALUES ('2646', '1', '1200100');
INSERT INTO `t_system_role_module` VALUES ('2647', '1', '1200101');
INSERT INTO `t_system_role_module` VALUES ('2648', '1', '1200102');
INSERT INTO `t_system_role_module` VALUES ('2649', '1', '1200103');
INSERT INTO `t_system_role_module` VALUES ('2650', '1', '1200104');
INSERT INTO `t_system_role_module` VALUES ('2651', '1', '1200105');
INSERT INTO `t_system_role_module` VALUES ('2652', '1', '1200106');
INSERT INTO `t_system_role_module` VALUES ('2653', '1', '1200107');
INSERT INTO `t_system_role_module` VALUES ('2654', '1', '1200108');
INSERT INTO `t_system_role_module` VALUES ('2655', '1', '1200200');
INSERT INTO `t_system_role_module` VALUES ('2656', '1', '1200201');
INSERT INTO `t_system_role_module` VALUES ('2657', '1', '1200202');
INSERT INTO `t_system_role_module` VALUES ('2658', '1', '1200203');
INSERT INTO `t_system_role_module` VALUES ('2659', '1', '1200204');
INSERT INTO `t_system_role_module` VALUES ('2660', '1', '1200205');
INSERT INTO `t_system_role_module` VALUES ('2661', '1', '1200206');
INSERT INTO `t_system_role_module` VALUES ('2662', '1', '1200207');
INSERT INTO `t_system_role_module` VALUES ('2663', '1', '1200300');
INSERT INTO `t_system_role_module` VALUES ('2664', '1', '1200400');
INSERT INTO `t_system_role_module` VALUES ('2665', '1', '1200401');
INSERT INTO `t_system_role_module` VALUES ('2666', '1', '1200402');
INSERT INTO `t_system_role_module` VALUES ('2667', '1', '1200403');
INSERT INTO `t_system_role_module` VALUES ('2668', '1', '1200404');
INSERT INTO `t_system_role_module` VALUES ('2669', '1', '1200600');
INSERT INTO `t_system_role_module` VALUES ('2670', '1', '1200601');
INSERT INTO `t_system_role_module` VALUES ('2671', '1', '1200602');
INSERT INTO `t_system_role_module` VALUES ('2672', '1', '1200603');
INSERT INTO `t_system_role_module` VALUES ('2673', '1', '1200604');
INSERT INTO `t_system_role_module` VALUES ('2674', '1', '1200700');
INSERT INTO `t_system_role_module` VALUES ('2675', '1', '1200701');
INSERT INTO `t_system_role_module` VALUES ('2676', '1', '1200702');
INSERT INTO `t_system_role_module` VALUES ('2677', '1', '1200703');
INSERT INTO `t_system_role_module` VALUES ('2678', '1', '1200704');
INSERT INTO `t_system_role_module` VALUES ('2679', '1', '1250000');
INSERT INTO `t_system_role_module` VALUES ('2680', '1', '1250100');
INSERT INTO `t_system_role_module` VALUES ('2681', '1', '1250101');
INSERT INTO `t_system_role_module` VALUES ('2682', '1', '1250102');
INSERT INTO `t_system_role_module` VALUES ('2683', '1', '1250103');
INSERT INTO `t_system_role_module` VALUES ('2684', '1', '1250104');
INSERT INTO `t_system_role_module` VALUES ('2685', '1', '1250200');
INSERT INTO `t_system_role_module` VALUES ('2686', '1', '1250201');
INSERT INTO `t_system_role_module` VALUES ('2687', '1', '1250202');
INSERT INTO `t_system_role_module` VALUES ('2688', '1', '1250203');
INSERT INTO `t_system_role_module` VALUES ('2689', '1', '1250204');
INSERT INTO `t_system_role_module` VALUES ('2690', '1', '1250205');
INSERT INTO `t_system_role_module` VALUES ('2691', '1', '1250300');
INSERT INTO `t_system_role_module` VALUES ('2692', '1', '1250400');
INSERT INTO `t_system_role_module` VALUES ('2693', '1', '1300000');
INSERT INTO `t_system_role_module` VALUES ('2694', '1', '1300100');
INSERT INTO `t_system_role_module` VALUES ('2695', '1', '1300101');
INSERT INTO `t_system_role_module` VALUES ('2696', '1', '1300102');
INSERT INTO `t_system_role_module` VALUES ('2697', '1', '1300103');
INSERT INTO `t_system_role_module` VALUES ('2698', '1', '1300200');
INSERT INTO `t_system_role_module` VALUES ('2699', '1', '1300201');
INSERT INTO `t_system_role_module` VALUES ('2700', '1', '1300202');
INSERT INTO `t_system_role_module` VALUES ('2701', '1', '1300203');
INSERT INTO `t_system_role_module` VALUES ('2702', '1', '1300300');
INSERT INTO `t_system_role_module` VALUES ('2703', '1', '1300301');
INSERT INTO `t_system_role_module` VALUES ('2704', '1', '1300302');
INSERT INTO `t_system_role_module` VALUES ('2705', '1', '1300303');
INSERT INTO `t_system_role_module` VALUES ('2706', '1', '1300400');
INSERT INTO `t_system_role_module` VALUES ('2707', '1', '1400000');
INSERT INTO `t_system_role_module` VALUES ('2708', '1', '1400100');
INSERT INTO `t_system_role_module` VALUES ('2709', '1', '1400101');
INSERT INTO `t_system_role_module` VALUES ('2710', '1', '1400102');
INSERT INTO `t_system_role_module` VALUES ('2711', '1', '1400103');
INSERT INTO `t_system_role_module` VALUES ('2712', '1', '1400200');
INSERT INTO `t_system_role_module` VALUES ('2713', '1', '1400300');
INSERT INTO `t_system_role_module` VALUES ('2714', '1', '1400350');
INSERT INTO `t_system_role_module` VALUES ('2715', '1', '1400400');
INSERT INTO `t_system_role_module` VALUES ('2716', '1', '1400500');
INSERT INTO `t_system_role_module` VALUES ('2717', '1', '1400550');
INSERT INTO `t_system_role_module` VALUES ('2718', '1', '1400560');
INSERT INTO `t_system_role_module` VALUES ('2719', '1', '1400561');
INSERT INTO `t_system_role_module` VALUES ('2720', '1', '1400562');
INSERT INTO `t_system_role_module` VALUES ('2721', '1', '1400563');
INSERT INTO `t_system_role_module` VALUES ('2722', '1', '1400600');
INSERT INTO `t_system_role_module` VALUES ('2723', '1', '1400601');
INSERT INTO `t_system_role_module` VALUES ('2724', '1', '1400602');
INSERT INTO `t_system_role_module` VALUES ('2725', '1', '1400603');
INSERT INTO `t_system_role_module` VALUES ('2726', '1', '1400604');
INSERT INTO `t_system_role_module` VALUES ('2727', '1', '1400605');
INSERT INTO `t_system_role_module` VALUES ('2728', '1', '1400700');
INSERT INTO `t_system_role_module` VALUES ('2729', '1', '1400701');
INSERT INTO `t_system_role_module` VALUES ('2730', '1', '1400702');
INSERT INTO `t_system_role_module` VALUES ('2731', '1', '1400703');
INSERT INTO `t_system_role_module` VALUES ('2732', '1', '1400800');
INSERT INTO `t_system_role_module` VALUES ('2733', '1', '1400801');
INSERT INTO `t_system_role_module` VALUES ('2734', '1', '1400802');
INSERT INTO `t_system_role_module` VALUES ('2735', '1', '1400803');
INSERT INTO `t_system_role_module` VALUES ('2736', '1', '1400900');
INSERT INTO `t_system_role_module` VALUES ('2737', '1', '1400950');
INSERT INTO `t_system_role_module` VALUES ('2738', '1', '1500000');
INSERT INTO `t_system_role_module` VALUES ('2739', '1', '1500100');
INSERT INTO `t_system_role_module` VALUES ('2740', '1', '1500300');
INSERT INTO `t_system_role_module` VALUES ('2741', '1', '1500400');
INSERT INTO `t_system_role_module` VALUES ('2742', '1', '1500600');
INSERT INTO `t_system_role_module` VALUES ('2743', '1', '1500900');
INSERT INTO `t_system_role_module` VALUES ('2744', '1', '1500901');
INSERT INTO `t_system_role_module` VALUES ('2745', '1', '1500902');
INSERT INTO `t_system_role_module` VALUES ('2746', '1', '1500903');
INSERT INTO `t_system_role_module` VALUES ('2747', '1', '1500904');
INSERT INTO `t_system_role_module` VALUES ('2748', '1', '1500905');
INSERT INTO `t_system_role_module` VALUES ('2749', '1', '1501000');
INSERT INTO `t_system_role_module` VALUES ('2750', '1', '1600000');
INSERT INTO `t_system_role_module` VALUES ('2751', '1', '1600100');
INSERT INTO `t_system_role_module` VALUES ('2752', '1', '1600101');
INSERT INTO `t_system_role_module` VALUES ('2753', '1', '1600102');
INSERT INTO `t_system_role_module` VALUES ('2754', '1', '1600103');
INSERT INTO `t_system_role_module` VALUES ('2755', '1', '1600104');
INSERT INTO `t_system_role_module` VALUES ('2756', '1', '1600105');
INSERT INTO `t_system_role_module` VALUES ('2757', '1', '1700000');
INSERT INTO `t_system_role_module` VALUES ('2758', '1', '1700100');
INSERT INTO `t_system_role_module` VALUES ('2759', '1', '1700101');
INSERT INTO `t_system_role_module` VALUES ('2760', '1', '1700102');
INSERT INTO `t_system_role_module` VALUES ('2761', '1', '1700103');
INSERT INTO `t_system_role_module` VALUES ('2762', '1', '1700104');
INSERT INTO `t_system_role_module` VALUES ('2763', '1', '1700105');
INSERT INTO `t_system_role_module` VALUES ('2764', '1', '1700106');
INSERT INTO `t_system_role_module` VALUES ('2765', '1', '1700107');
INSERT INTO `t_system_role_module` VALUES ('2766', '1', '1700200');
INSERT INTO `t_system_role_module` VALUES ('2767', '1', '1700201');
INSERT INTO `t_system_role_module` VALUES ('2768', '1', '1700203');
INSERT INTO `t_system_role_module` VALUES ('2769', '1', '1700204');
INSERT INTO `t_system_role_module` VALUES ('2770', '1', '1700205');
INSERT INTO `t_system_role_module` VALUES ('2771', '1', '1700206');
INSERT INTO `t_system_role_module` VALUES ('2772', '1', '1700300');
INSERT INTO `t_system_role_module` VALUES ('2773', '1', '1700400');
INSERT INTO `t_system_role_module` VALUES ('2774', '1', '1700401');
INSERT INTO `t_system_role_module` VALUES ('2775', '1', '1800000');
INSERT INTO `t_system_role_module` VALUES ('2776', '1', '1800100');
INSERT INTO `t_system_role_module` VALUES ('2777', '1', '1800200');
INSERT INTO `t_system_role_module` VALUES ('2778', '1', '1800300');
INSERT INTO `t_system_role_module` VALUES ('2779', '1', '1800700');
INSERT INTO `t_system_role_module` VALUES ('2780', '1', '1800800');
INSERT INTO `t_system_role_module` VALUES ('2781', '1', '1800900');

-- ----------------------------
-- Table structure for `t_system_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user`;
CREATE TABLE `t_system_user` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `REALNAME` varchar(75) DEFAULT NULL,
  `REMARK` varchar(2000) DEFAULT NULL,
  `BINDINGEMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `USERTYPE` tinyint(4) DEFAULT NULL,
  `ISFREEZE` tinyint(4) DEFAULT '0',
  `ISDELETED` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `IDX_T_SYSTEM_USER` (`USERNAME`,`PASSWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_user
-- ----------------------------
INSERT INTO `t_system_user` VALUES ('1', 'chnmaster', '超级管理员', null, null, 'e10adc3949ba59abbe56e057f20f883e', '2015-11-15 11:16:22', '1', '0', '0');

-- ----------------------------
-- Table structure for `t_system_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_user_role`;
CREATE TABLE `t_system_user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` int(11) DEFAULT NULL,
  `ROLEID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `IDX_T_SYSTEM_USER_ROLE_USERID` (`USERID`),
  KEY `IDX_T_SYSTEM_USER_ROLE_ROLEID` (`ROLEID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_user_role
-- ----------------------------
INSERT INTO `t_system_user_role` VALUES ('1', '1', '1');

-- ----------------------------
-- Table structure for `t_system_version`
-- ----------------------------
DROP TABLE IF EXISTS `t_system_version`;
CREATE TABLE `t_system_version` (
  `ID` int(11) NOT NULL,
  `EDITION` varchar(150) DEFAULT NULL,
  `CLIENT` varchar(150) DEFAULT NULL,
  `DESCRIPTION` varchar(1200) DEFAULT NULL,
  `UPGRADEDATE` datetime NOT NULL,
  `VERSION` varchar(30) DEFAULT NULL,
  `BUILD` varchar(30) DEFAULT NULL,
  `DESC_FILE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_system_version
-- ----------------------------
INSERT INTO `t_system_version` VALUES ('1', '标准版', '公司', '全新开发', '2015-11-15 11:16:23', '1.0.0.0', '151026', null);
INSERT INTO `t_system_version` VALUES ('2', '标准版', '公司', '前期功能完善', '2016-06-21 15:20:41', '1.1.0.0', '151124', null);
INSERT INTO `t_system_version` VALUES ('3', '标准版', '公司', '功能完善，报名、支付', '2016-06-21 15:22:31', '1.2.0.0', '151218', null);
INSERT INTO `t_system_version` VALUES ('4', '标准版', '公司', '购买支付', '2016-06-21 15:23:46', '1.3.0.0', '160315', null);
INSERT INTO `t_system_version` VALUES ('5', '标准版', '公司', '退课', '2016-06-21 15:25:42', '1.3.1.0', '', null);
INSERT INTO `t_system_version` VALUES ('6', '神州唱响定制版', '国臻', '神州唱响', '2016-06-21 15:30:41', '1.3.2.0', '', null);
INSERT INTO `t_system_version` VALUES ('7', '标准版', '公司', '1310、1320、推广码', '2016-06-21 15:27:21', '1.3.3.0', '', null);
INSERT INTO `t_system_version` VALUES ('8', '黄龙奖定制版', '公司', '黄龙奖赛事活动', '2016-06-21 15:32:29', '1.3.4.0', '', null);
INSERT INTO `t_system_version` VALUES ('9', '标准版', '音乐联盟', '添加点播、音乐导师认证和申请开课以及联合会员申请等', '2016-06-21 15:33:27', '1.4.0.0', '', null);
INSERT INTO `t_system_version` VALUES ('10', '标准版', '音乐联盟', '安卓APP、ftp上传方式', '2016-07-06 17:25:25', '1.4.1.0', '20160624', null);
INSERT INTO `t_system_version` VALUES ('11', '标准版', '音乐联盟', '音乐网安卓APP、断点续传、课程评论', '2016-07-06 17:25:25', '1.4.1.1', '20160701', null);
INSERT INTO `t_system_version` VALUES ('12', '标准版', '音乐联盟', '音乐导师申请手机版', '2016-07-06 17:25:49', '1.4.1.2', '20160706', null);
INSERT INTO `t_system_version` VALUES ('13', '标准版', '音乐联盟', '微课对接、找回密码、优惠券功能', '2017-01-05 00:39:10', '1.4.2.0', '20160805', null);
INSERT INTO `t_system_version` VALUES ('14', '标准版', '音乐联盟', 'hifi音乐', '2017-01-05 00:43:20', '1.5.0.0', '20161024', null);
INSERT INTO `t_system_version` VALUES ('15', '标准版', '音乐联盟', '视频回顾、在线评论', '2017-01-05 00:43:39', '1.5.1.0', '20161111', null);
INSERT INTO `t_system_version` VALUES ('16', '标准版', '音乐联盟', 'Hifi会员购买过程', '2017-01-05 00:44:49', '1.5.1.1', '20161125', null);
INSERT INTO `t_system_version` VALUES ('17', '标准版', '音乐联盟', '神州唱响、分享功能、无限宝网页版', '2017-01-05 00:47:04', '1.5.2.0', '20161124', null);
INSERT INTO `t_system_version` VALUES ('18', '标准版', '音乐联盟', '高校联盟，ios支付', '2017-01-05 00:47:28', '1.5.3.0', '20161201', null);
INSERT INTO `t_system_version` VALUES ('19', '标准版', '音乐联盟', 'ios支付', '2017-01-05 00:47:47', '1.5.3.0', '20161230', null);

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ID` int(11) NOT NULL,
  `USERNAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(50) DEFAULT NULL,
  `QQ` varchar(11) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `REALNAME` varchar(75) DEFAULT NULL,
  `SEX` tinyint(4) DEFAULT '0',
  `SCHOOL_ID` int(11) DEFAULT NULL,
  `SCHOOLNAME` varchar(500) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `HOMEPAGE` varchar(50) DEFAULT NULL,
  `TELEPHONE` varchar(25) DEFAULT NULL,
  `USER_TYPE` tinyint(4) NOT NULL DEFAULT '1',
  `REGISTER_TYPE` tinyint(4) NOT NULL DEFAULT '0',
  `IS_CANCEL` tinyint(4) NOT NULL DEFAULT '0',
  `IS_MEMBER` tinyint(4) NOT NULL DEFAULT '0',
  `IS_UNION_MEMBER` tinyint(4) DEFAULT '0',
  `IS_TUTOR` tinyint(4) DEFAULT '0',
  `IS_HIFI_MEMBER` tinyint(4) NOT NULL DEFAULT '0',
  `HIFI_MEMBER_DATE` datetime DEFAULT NULL,
  `GROUP_TYPE_ID` int(11) DEFAULT NULL,
  `AVATAR_FILE` varchar(500) DEFAULT NULL,
  `AVATAR_ORIGINAL_FILE` varchar(500) DEFAULT NULL,
  `FINDPWD_DATE` char(8) DEFAULT NULL,
  `FINDPWD_TIMES` smallint(6) DEFAULT '0',
  `SPELLNAME` varchar(150) DEFAULT NULL,
  `SHORTSPELL` varchar(50) DEFAULT NULL,
  `AGENCY_ID` int(11) DEFAULT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDX_T_USER_UNIQUE_USERNAME` (`USERNAME`),
  KEY `IDX_T_USER` (`USER_TYPE`),
  KEY `IDX_T_USER_NAME_PWD` (`USERNAME`,`PASSWORD`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_bind`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_bind`;
CREATE TABLE `t_user_bind` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `BIND_ID` char(100) NOT NULL,
  `NICK_NAME` varchar(60) DEFAULT NULL,
  `USER_ID` int(11) NOT NULL,
  `NEW_USERID` varchar(50) DEFAULT NULL,
  `PLAT_TYPE` tinyint(4) NOT NULL DEFAULT '0',
  `BIND_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDX_T_USER_BIND_BINDID_USERID` (`BIND_ID`,`USER_ID`),
  KEY `IDX_T_USER_BIND_PB` (`PLAT_TYPE`,`BIND_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_bind
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_group_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_group_type`;
CREATE TABLE `t_user_group_type` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(75) NOT NULL,
  `IS_CAN_DEL` tinyint(4) DEFAULT '1',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_group_type
-- ----------------------------
INSERT INTO `t_user_group_type` VALUES ('1', '默认分类', '0');

-- ----------------------------
-- Table structure for `t_user_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_teacher`;
CREATE TABLE `t_user_teacher` (
  `ID` int(11) NOT NULL,
  `REALNAME` varchar(75) DEFAULT NULL,
  `TITLE` varchar(75) DEFAULT NULL,
  `IDCODE` varchar(18) DEFAULT NULL,
  `INTRODUCTION` varchar(4000) DEFAULT NULL,
  `PHOTO_FILE` varchar(500) DEFAULT NULL,
  `TEACH_SUBJECT` varchar(500) DEFAULT NULL,
  `TEACH_TYPE` tinyint(4) NOT NULL DEFAULT '1',
  `REGIONCODE` varchar(6) DEFAULT NULL,
  `ISRECOMMEND` tinyint(4) DEFAULT '0',
  `RECOMMENDTIME` datetime DEFAULT NULL,
  `RECOMMENDSEQ` tinyint(4) DEFAULT '0',
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  `NATION` varchar(20) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `POLITICAL` varchar(20) DEFAULT NULL,
  `WORK_UNIT` varchar(75) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `PHONE` varchar(15) DEFAULT NULL,
  `MASTER_SCHOOL` varchar(75) DEFAULT NULL,
  `DOCTOR_SCHOOL` varchar(75) DEFAULT NULL,
  `IDCARD_FILE` varchar(500) DEFAULT NULL,
  `HAND_IDCARD_FILE` varchar(500) DEFAULT NULL,
  `CARD_PHOTO_FILE` varchar(500) DEFAULT NULL,
  `TEA_SOURCE` tinyint(4) DEFAULT '0',
  `APPLY_DATE` datetime DEFAULT NULL,
  `AUDIT_STATUS` tinyint(4) NOT NULL DEFAULT '2',
  `AUDIT_DATE` datetime DEFAULT NULL,
  `AUDIT_REALNAME` varchar(75) DEFAULT NULL,
  `AUDIT_MSG` varchar(500) DEFAULT NULL,
  `SHOW_GUIDE` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `IDX_T_USER_TEACHER` (`TEACH_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user_union_member`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_union_member`;
CREATE TABLE `t_user_union_member` (
  `ID` int(11) NOT NULL,
  `USERID` int(11) NOT NULL,
  `REALNAME` varchar(75) DEFAULT NULL,
  `UNION_TYPE` tinyint(4) NOT NULL DEFAULT '0',
  `UNION_CODE` varchar(20) DEFAULT NULL,
  `SEX` tinyint(4) DEFAULT '0',
  `NATION` varchar(20) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `DEGREE` varchar(20) DEFAULT NULL,
  `GRADUATE_SCHOOL` varchar(75) DEFAULT NULL,
  `MAJOR` varchar(75) DEFAULT NULL,
  `TEACH_YEAR` float(5,1) DEFAULT '0.0',
  `POLITICAL` varchar(20) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `WORK_UNIT` varchar(75) DEFAULT NULL,
  `WORK_DEPT` varchar(75) DEFAULT NULL,
  `WORK_DUTY` varchar(75) DEFAULT NULL,
  `TELEPHONE` varchar(20) DEFAULT NULL,
  `REGIONCODE` varchar(6) DEFAULT NULL,
  `ADDRESS` varchar(100) DEFAULT NULL,
  `REFERRER` varchar(75) DEFAULT NULL,
  `INTRO` varchar(4000) DEFAULT NULL,
  `OPINION` varchar(500) DEFAULT NULL,
  `PHOTO_FILE` varchar(500) DEFAULT NULL,
  `PAPER_FILE` varchar(100) DEFAULT NULL,
  `AGENCY_NAME` varchar(100) DEFAULT NULL,
  `AGENCY_ADDRESS` varchar(100) DEFAULT NULL,
  `AGENCY_YEAR` float(5,1) DEFAULT '0.0',
  `AGENCY_FUND` varchar(20) DEFAULT NULL,
  `AGENCY_BRANCH_NUM` int(11) DEFAULT NULL,
  `AGENCY_BRAND` varchar(75) DEFAULT NULL,
  `AGENCY_MODE` varchar(20) DEFAULT NULL,
  `AGENCY_FEATURE` varchar(500) DEFAULT NULL,
  `AGENCY_EMPLOYEE_NUM` int(11) DEFAULT NULL,
  `AGENCY_TRAINEE_NUM` int(11) DEFAULT NULL,
  `AGENCY_PRINCIPAL` varchar(75) DEFAULT NULL,
  `APPLY_DATE` datetime DEFAULT NULL,
  `AUDIT_STATUS` tinyint(4) NOT NULL DEFAULT '0',
  `AUDIT_DATE` datetime DEFAULT NULL,
  `AUDIT_REALNAME` varchar(75) DEFAULT NULL,
  `AUDIT_MSG` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `IDX_T_USER_UNION_MEMBER_USERID_UNIONTYPE` (`USERID`,`UNION_TYPE`),
  KEY `IDX_T_USER_UNION_MEMBER_USERID` (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_union_member
-- ----------------------------

-- ----------------------------
-- Table structure for `t_wxb`
-- ----------------------------
DROP TABLE IF EXISTS `t_wxb`;
CREATE TABLE `t_wxb` (
  `ID` int(11) NOT NULL,
  `NAME` varchar(150) DEFAULT NULL,
  `REGION` char(6) DEFAULT NULL,
  `MASTER_LOGIN_URL` varchar(500) DEFAULT NULL,
  `SLAVE_LOGIN_URL` varchar(500) DEFAULT NULL,
  `TRANSFER_LOGIN_URL` varchar(500) DEFAULT NULL,
  `LOGINPORT` int(11) DEFAULT NULL,
  `MONITORPORT` int(11) DEFAULT NULL,
  `COMMANDPORT` int(11) DEFAULT '8087',
  `MAXCAPACITY` int(11) DEFAULT NULL,
  `FTPURL` varchar(100) DEFAULT NULL,
  `FTPPORT` int(11) DEFAULT NULL,
  `FTPACCOUNT` varchar(100) DEFAULT NULL,
  `FTPPASSWORD` varchar(100) DEFAULT NULL,
  `STATUS` tinyint(4) DEFAULT NULL,
  `REMARK` varchar(4000) DEFAULT NULL,
  `CUSERID` int(11) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `DOG_NAME` varchar(400) DEFAULT NULL,
  `DOG_NO` varchar(64) DEFAULT NULL,
  `DOG_NOEXPIRE` tinyint(4) DEFAULT '0',
  `DOG_ENDTIME` datetime DEFAULT NULL,
  `PROXY_NUM` int(11) DEFAULT '0',
  `LAST_SYN_DATE` datetime DEFAULT NULL,
  `BAND_WIDTH` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wxb
-- ----------------------------

-- ----------------------------
-- Table structure for `t_wxb_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_wxb_log`;
CREATE TABLE `t_wxb_log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `WXBID` int(11) DEFAULT NULL,
  `COURSEID` int(11) DEFAULT NULL,
  `USERID` int(11) DEFAULT NULL,
  `REALNAME` varchar(75) DEFAULT NULL,
  `USERTYPE` tinyint(4) DEFAULT NULL,
  `LOGINTIME` datetime DEFAULT NULL,
  `LOGOUTTIME` datetime DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `REMARK` varchar(2000) DEFAULT '',
  `SEQ` int(11) DEFAULT NULL,
  `LOGINFLAG` varchar(32) DEFAULT NULL,
  `NO_SERIOUS_TIME` int(11) DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `IDX_T_WXB_LOG` (`COURSEID`,`SEQ`),
  KEY `IDX_T_WXB_LOG_USERID` (`USERID`),
  KEY `IDX_T_WXB_LOG_LOGINFLAG` (`LOGINFLAG`),
  KEY `IDX_T_WXB_LOG_LOGINTIME` (`LOGINTIME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_wxb_log
-- ----------------------------

-- ----------------------------
-- Function structure for `fun_next_seq`
-- ----------------------------
DROP FUNCTION IF EXISTS `fun_next_seq`;
DELIMITER ;;
CREATE DEFINER=`root`@`%` FUNCTION `fun_next_seq`(in_table_name varchar(50)) RETURNS int(11)
BEGIN
    select upper(in_table_name) into in_table_name;

    if in_table_name='T_USER' then
        REPLACE INTO t_seq_user (table_name) VALUES (in_table_name);
    elseif in_table_name='T_AGENCY' then
        REPLACE INTO t_seq_agency (table_name) VALUES (in_table_name);  
    elseif in_table_name='T_USER_UNION_MEMBER' then
        REPLACE INTO t_seq_user_union_member (table_name) VALUES (in_table_name);
    elseif in_table_name='T_COLLEGE' then
        REPLACE INTO t_seq_college (table_name) VALUES (in_table_name);
    elseif in_table_name='T_COURSE' then
        REPLACE INTO t_seq_course (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_COUPON' then
        REPLACE INTO t_seq_coupon (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_ORDER' then
        REPLACE INTO t_seq_order (table_name) VALUES (in_table_name);
    elseif in_table_name='T_ORDER_DETAIL' then
        REPLACE INTO t_seq_order_detail (table_name) VALUES (in_table_name);
    elseif in_table_name='T_ORDER_REPORT' then
        REPLACE INTO t_seq_order_report (table_name) VALUES (in_table_name);
    elseif in_table_name='T_WXB' then
        REPLACE INTO t_seq_wxb (table_name) VALUES (in_table_name); 
    elseif in_table_name='T_COMPETE' or in_table_name='T_COMPETE_COMMITTEE' then
        REPLACE INTO t_seq_compete (table_name) VALUES (in_table_name);
    elseif in_table_name='T_COMPETE_ATTEND' or in_table_name='T_COMPETE_ATTEND_HLJ' then
        REPLACE INTO t_seq_compete_attend (table_name) VALUES (in_table_name);
    elseif in_table_name='T_SYSTEM_USER' or in_table_name='T_SYSTEM_ROLE' or in_table_name='T_PICTURE' or in_table_name='T_INDEX_ADV' or in_table_name='T_INDEX_STUDENT' then
        REPLACE INTO t_seq_common (table_name) VALUES (in_table_name);
    else
        RETURN 0;
    end if;
    RETURN LAST_INSERT_ID();
    
END
;;
DELIMITER ;
