package net.zdsoft.common.enums;

/**
 * 文件路径类型<br>
 *
 * @author dongzk
 *
 */
public enum PathType {
    BULLETIN {
        @Override
        public String getValue() {
            return BULLETIN_VALUE;
        }

        @Override
        public String getNameValue() {
            return BULLETIN_NAME;
        }

    },
    INFORMATION {
        @Override
        public String getValue() {
            return INFORMATION_VALUE;
        }

        @Override
        public String getNameValue() {
            return INFORMATION_NAME;
        }

    },
    INFO {
        @Override
        public String getValue() {
            return INFO_VALUE;
        }

        @Override
        public String getNameValue() {
            return INFO_NAME;
        }

    },
    FOOTER {
        @Override
        public String getValue() {
            return FOOTER_VALUE;
        }

        @Override
        public String getNameValue() {
            return FOOTER_NAME;
        }

    },
    FIELD {
        @Override
        public String getValue() {
            return FIELD_VALUE;
        }

        @Override
        public String getNameValue() {
            return FIELD_NAME;
        }
    },
    AUDITION {
        @Override
        public String getValue() {
            return AUDITION_VALUE;
        }

        @Override
        public String getNameValue() {
            return AUDITION_NAME;
        }
    },
    HELP {
        @Override
        public String getValue() {
            return HELP_VALUE;
        }

        @Override
        public String getNameValue() {
            return HELP_NAME;
        }
    },
    ADVERT {
        @Override
        public String getValue() {
            return ADVERT_VALUE;
        }

        @Override
        public String getNameValue() {
            return ADVERT_NAME;
        }
    },
    USERAVATAR {
        @Override
        public String getValue() {
            return USERAVATAR_VALUE;
        }

        @Override
        public String getNameValue() {
            return USERAVATAR_NAME;
        }
    },
    STUDENTPHOTO {
        @Override
        public String getValue() {
            return STUDENTPHOTO_VALUE;
        }

        @Override
        public String getNameValue() {
            return STUDENTPHOTO_NAME;
        }
    },
    TEACHERPHOTO {
        @Override
        public String getValue() {
            return TEACHERPHOTO_VALUE;
        }

        @Override
        public String getNameValue() {
            return TEACHERPHOTO_NAME;
        }
    },
    COURSEPIC {
        @Override
        public String getValue() {
            return COURSEPIC_VALUE;
        }

        @Override
        public String getNameValue() {
            return COURSEPIC_NAME;
        }
    },
    VIDEO {
        @Override
        public String getValue() {
            return VIDEO_VALUE;
        }

        @Override
        public String getNameValue() {
            return VIDEO_NAME;
        }
    },
    REPORT {
        @Override
        public String getValue() {
            return REPORT_VALUE;
        }

        @Override
        public String getNameValue() {
            return REPORT_NAME;
        }
    },
    EDITOR {

        @Override
        public String getValue() {
            return EDITOR_VALUE;
        }

        @Override
        public String getNameValue() {
            return EDITOR_NAME;
        }

    },
    IMAGES {

        @Override
        public String getValue() {
            return IMAGES_VALUE;
        }

        @Override
        public String getNameValue() {
            return IMAGES_NAME;
        }

    },
    AGENCY {

        @Override
        public String getValue() {
            return AGENCY_VALUE;
        }

        @Override
        public String getNameValue() {
            return AGENCY_NAME;
        }

    },
    ACCOUNT_BATCH_FILE {

        @Override
        public String getValue() {
            return ACCOUNT_BATCH_FILE_VALUE;
        }

        @Override
        public String getNameValue() {
            return ACCOUNT_BATCH_FILE_NAME;
        }

    },
    BANK_BATCH_FILE {

        @Override
        public String getValue() {
            return BANK_BATCH_FILE_VALUE;
        }

        @Override
        public String getNameValue() {
            return BANK_BATCH_FILE_NAME;
        }

    },
    IMP_REPORT {
        @Override
        public String getValue() {
            return IMP_REPORT_VALUE;
        }

        @Override
        public String getNameValue() {
            return IMP_REPORT_NAME;
        }
    },
    TEMP {
        @Override
        public String getValue() {
            return TEMP_VALUE;
        }

        @Override
        public String getNameValue() {
            return TEMP_NAME;
        }
    },
    IMP_ALIPAY {
        @Override
        public String getValue() {
            return ALIPAYREPORT_VALUE;
        }

        @Override
        public String getNameValue() {
            return ALIPAYREPORT_NAME;
        }
    },
    IMP_SCHOOL {
        @Override
        public String getValue() {
            return SCHOOL_VALUE;
        }

        @Override
        public String getNameValue() {
            return SCHOOL_NAME;
        }
    },

    IMP_TEACHER {
        @Override
        public String getValue() {
            return TEACHER_VALUE;
        }

        @Override
        public String getNameValue() {
            return TEACHER_NAME;
        }
    },

    IMP_STUDENT {
        @Override
        public String getValue() {
            return STUDENT_VALUE;
        }

        @Override
        public String getNameValue() {
            return STUDENT_NAME;
        }
    },

    IMP_SERVERLOG {
        @Override
        public String getValue() {
            return SERVERLOG_VALUE;
        }

        @Override
        public String getNameValue() {
            return SERVERLOG_NAME;
        }
    },

    COURSE_FILE {
        @Override
        public String getValue() {
            return COURSE_FILE_VALUE;
        }

        @Override
        public String getNameValue() {
            return COURSE_FILE_NAME;
        }
    },
    SCREEN_SHOT {

        @Override
        public String getValue() {
            return SCREEN_SHOT_VALUE;
        }

        @Override
        public String getNameValue() {
            return SCREEN_SHOT_NAME;
        }

    },

    // 新加的
    INDEX_ADV {
        @Override
        public String getValue() {
            return INDEX_ADV_VALUE;
        }

        @Override
        public String getNameValue() {
            return INDEX_ADV_NAME;
        }
    },
    COMPETE {
        @Override
        public String getValue() {
            return COMPETE_VALUE;
        }

        @Override
        public String getNameValue() {
            return COMPETE_NAME;
        }
    },
    COMMITTEE {
        @Override
        public String getValue() {
            return COMMITTEE_VALUE;
        }

        @Override
        public String getNameValue() {
            return COMMITTEE_NAME;
        }
    },
    COLLEGE {
        @Override
        public String getValue() {
            return COLLEGE_VALUE;
        }

        @Override
        public String getNameValue() {
            return COLLEGE_NAME;
        }
    },
    UNION {
        @Override
        public String getValue() {
            return UNION_VALUE;
        }

        @Override
        public String getNameValue() {
            return UNION_NAME;
        }
    };

    /**
     * 得到类型的整数值
     *
     * @return
     */
    public abstract String getValue();

    public abstract String getNameValue();

    /**
     * 根据值得到是否类型
     *
     * @param value
     * @return 如果没有得到对应的是否类型，返回null
     */
    public static PathType get(String value) {
        for (PathType type : PathType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }

        return null;
    }

    private static String BULLETIN_VALUE = "bulletin";
    private static String BULLETIN_NAME = "公告";
    private static String INFORMATION_VALUE = "information";
    private static String INFORMATION_NAME = "新闻";
    private static String INFO_VALUE = "info";
    private static String INFO_NAME = "视频回顾";
    private static String FOOTER_VALUE = "footer";
    private static String FOOTER_NAME = "页脚链接";
    private static String FIELD_VALUE = "field";
    private static String FIELD_NAME = "首页栏目";
    private static String AUDITION_VALUE = "audition";
    private static String AUDITION_NAME = "试听课程";
    private static String HELP_VALUE = "help";
    private static String HELP_NAME = "帮助";
    private static String ADVERT_VALUE = "banner";
    private static String ADVERT_NAME = "无限宝广告";
    private static String USERAVATAR_VALUE = "userAvatar";
    private static String USERAVATAR_NAME = "用户头像";
    private static String STUDENTPHOTO_VALUE = "stuPhoto";
    private static String STUDENTPHOTO_NAME = "学生照片";
    private static String TEACHERPHOTO_VALUE = "teaPhoto";
    private static String TEACHERPHOTO_NAME = "教师照片";
    private static String COURSEPIC_VALUE = "coursePic";
    private static String COURSEPIC_NAME = "课程图片";
    private static String VIDEO_VALUE = "video";
    private static String VIDEO_NAME = "课堂视频";
    private static String REPORT_VALUE = "report";
    private static String REPORT_NAME = "报名文件";
    private static String EDITOR_VALUE = "editor";
    private static String EDITOR_NAME = "编辑器文件";
    private static String IMAGES_VALUE = "images";
    private static String IMAGES_NAME = "图片路径";
    private static String AGENCY_VALUE = "agency";
    private static String AGENCY_NAME = "机构简介图片";
    private static String ACCOUNT_BATCH_FILE_VALUE = "accountsFile";
    private static String ACCOUNT_BATCH_FILE_NAME = "学员充值机构批量转账充值结果文件";
    private static String BANK_BATCH_FILE_VALUE = "importedBankFile";
    private static String BANK_BATCH_FILE_NAME = "银行汇款审核批量导入充值记录生成结果文件";
    private static String IMP_REPORT_VALUE = "impReport";
    private static String IMP_REPORT_NAME = "导入报名结果文件路径";
    private static String TEMP_VALUE = "temp";
    private static String TEMP_NAME = "临时文件";
    private static String ALIPAYREPORT_VALUE = "alipay";
    private static String ALIPAYREPORT_NAME = "支付宝导入文件";
    private static String SCHOOL_VALUE = "school";
    private static String SCHOOL_NAME = "学校导入文件";
    private static String TEACHER_VALUE = "teacher";
    private static String TEACHER_NAME = "教师导入文件";
    private static String STUDENT_VALUE = "student";
    private static String STUDENT_NAME = "学员导入文件";
    private static String SERVERLOG_VALUE = "serverlog";
    private static String SERVERLOG_NAME = "无限宝登录日志导入文件";
    private static String COURSE_FILE_VALUE = "Presenter";
    private static String COURSE_FILE_NAME = "课程资料";
    private static String SCREEN_SHOT_VALUE = "screenShot";
    private static String SCREEN_SHOT_NAME = "无限宝截图";

    private static String INDEX_ADV_VALUE = "indexAdv";
    private static String INDEX_ADV_NAME = "轮播图片";

    private static String COMPETE_VALUE = "compete";
    private static String COMPETE_NAME = "活动";
    private static String COMMITTEE_VALUE = "comittee";
    private static String COMMITTEE_NAME = "组委会人员";

    private static String UNION_VALUE = "union";
    private static String UNION_NAME = "联合会员";

    private static String COLLEGE_VALUE = "college";
    private static String COLLEGE_NAME = "高校风采";
}
