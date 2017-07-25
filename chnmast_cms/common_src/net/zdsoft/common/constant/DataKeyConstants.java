package net.zdsoft.common.constant;

/**
 * 缓存数据的key常量类
 *
 * @author dongzk
 *
 */
public class DataKeyConstants {

    /**
     * 系统参数
     *
     * @see cms/base_src/net/zdsoft/netstudy/base/service/impl/SystemConfigServiceImpl.java
     * @see remote/src/net/zdsoft/netstudy/remote/train/service/impl/RemoteSystemConfigServiceImpl.java
     */
    public static final String GET_SYSTEMCONFIG_MAP = "getSystemConfigMap"; // 所有系统参数map

    /** 手机端配置 **/
    public static final String GET_MOBILE_CONFIG = "getMobileConfig"; // 手机端配置

    /**
     * 系统版本号
     *
     * @see cms/base_src/net/zdsoft/netstudy/base/service/impl/SystemVersionServiceImpl.java
     * @see remote/src/net/zdsoft/netstudy/remote/train/service/impl/RemoteSystemVersionServiceImpl.java
     */
    public static final String GET_MAX_SYSTEMVERSION_ID = "getMaxSystemVersionId"; // 当前最大的版本号id
    public static final String GET_MAX_SYSTEMVERSION_NO = "getMaxSystemVersionNo"; // 当前最大的版本号，返回格式：5.0.0.0 build:131001

    /**
     * 系统行政区划
     *
     * @see cms/base_src/net/zdsoft/netstudy/base/service/impl/SystemRegionServiceImpl.java
     */
    public static final String GET_SYSTEM_REGIONS = "getSystemRegions"; // 所有行政区划list
    public static final String GET_SYSTEM_REGIONS_MAP = "getSystemRegionsMap"; // 获取所有行政区划的map

    /**
     * 机构
     *
     * @see cms/src/net/zdsoft/netstudy/service/agency/impl/AgencyServiceImpl.java
     * @see remote/src/net/zdsoft/netstudy/remote/train/service/impl/RemoteAgencyServiceImpl.java
     * @see train/src/net/zdsoft/netstudy/service/agency/impl/AgencyServiceImpl.java
     */
    public static final String GET_AGENCYS = "getAgencys"; // 所有未软删除的机构列表
    public static final String GET_AGENCY_BYID = "getAgencyByid"; // 指定id的机构（包括软删的）
    public static final String GET_AGENCY = "getAgency";// 指定id的机构（不包括软删的）
    public static final String GET_AGENCY_NORMAL = "getAgencyNormal"; // 正常状态的机构（未注销，未软删）
    public static final String GET_HOMESHOW_AGENCYS = "getHomeshowAgencys"; // 能在home页面显示的所有机构列表

    /**
     * 机构公告
     *
     * @see train/src/net/zdsoft/netstudy/service/agency/impl/AgencyBulletinServiceImpl.java
     * @see remote/src/net/zdsoft/netstudy/remote/train/service/impl/RemoteAgencyBulletinServiceImpl.java
     */
    public static final String INDEX_AGENCY_BULLETINS = "getIndexAgencyBulletins"; // 一定条数的机构公告
    public static final String INDEX_AGENCY_NEWS = "getIndexAgencyNews"; // 一定条数的机构资讯
    public static final String INDEX_URGENT_BULLETIN = "getIndexUrgentBulletin"; // 首页平台紧急公告
    public static final String FIRST_URGENT_BULLETIN = "firstUrgentBulletin"; // 机构最新一条紧急公告

    /**
     * 机构频道
     *
     * @see train/src/net/zdsoft/netstudy/service/agency/impl/AgencyChannelServiceImpl.java
     * @see remote/src/net/zdsoft/netstudy/remote/train/service/impl/RemoteAgencyChannelServiceImpl.java
     */
    public static final String GETAGENCYCHANNELS = "getAgencyChannels"; // 机构在用频道列表
    public static final String GETAGENCYINDEXCHANNELURL = "getAgencyIndexChannelUrl"; // 机构的首页频道地址
    public static final String GETCURRENTCHANNELID = "getCurrentChannelId"; // 机构某地址对应的频道id

    /**
     * 机构参数
     *
     * @see cms/base_src/net/zdsoft/netstudy/base/service/impl/AgencyConfigServiceImpl.java
     * @see remote/src/net/zdsoft/netstudy/remote/train/service/impl/RemoteAgencyConfigServiceImpl.java
     */
    public static final String GET_AGENCYCONFIG_MAP = "getAgencyConfigMap"; // 机构参数map
    public static final String GET_SZXY_AGENCYID = "getSzxyAgencyId"; // 与数字校园对接的机构id

    /**
     * 机构栏目配置
     *
     * @see train/src/net/zdsoft/netstudy/service/agency/impl/AgencyFieldServiceImpl.java
     * @see remote/src/net/zdsoft/netstudy/remote/train/service/impl/RemoteAgencyFieldServiceImpl.java
     */
    public static final String GET_AGENCY_FIELDS = "getAgencyFields";

    /**
     * 机构科目
     *
     * @see cms/base_src/net/zdsoft/netstudy/base/service/impl/CourseSubjectServiceImpl.java
     */
    public static final String GET_COURSE_SUBJECT_MAP = "getCourseSubjectMap"; // 机构的科目map
    public static final String GET_COURSE_SUBJECTS = "getCourseSubjects";// 机构的科目list

    /**
     * 前台-首页内容
     *
     * @see cms/base_src/net/zdsoft/netstudy/base/service/impl/AgencyConfigServiceImpl.java
     * @see train/src/net/zdsoft/netstudy/service/course/impl/CourseServiceImpl.java
     */
    public static final String GET_INDEX_COURSES = "getIndexCourses"; // 首页热门课程
    public static final String GET_RECOMMEND_COURSE = "getRecommendCourses"; // 首页推荐课程
    /**
     * @see train/src/net/zdsoft/netstudy/service/agency/impl/AgencyAuditionServiceImpl.java
     */
    public static final String INDEX_AGENCY_AUDITION = "getIndexAgencyAudition"; // 首页试听课程
    /**
     * @ee train/src/net/zdsoft/netstudy/service/user/impl/UserTeacherServiceImpl.java
     * @see cms/src/net/zdsoft/netstudy/service/user/impl/UserTeacherServiceImpl.java
     */
    public static final String INDEX_HOT_TEACHER = "getIndexHotTeacher"; // 首页授课教师

    /**
     * 后台-首页内容
     *
     * @see cms/src/net/zdsoft/netstudy/service/course/impl/CourseCalendarServiceImpl.java
     */
    public static final String GET_TODAY_CALENDARS_CACHE = "getTodayCalendarsCache_";// 今日开课数据
    public static final String GET_ALLAGENCY_COURSE_CALENDARS = "getUserAllAgencyCourseCalendars_";// 多个机构的开课日历
    public static final String GET_ONEAGENCY_COURSE_CALENDARS = "getUserAllAgencyCourseCalendars_";// 一个机构的开课日历
    public static final String GET_AGENCY_STUDENTNUM_BYDAY = "getAgencyStudentNumByDay_";// 一个机构一天的上课人数

    /**
     * 后台-web管理
     *
     * @see cms/src/net/zdsoft/netstudy/service/monitor/impl/MonitorServerServiceImpl.java
     */
    public static final String GET_MONITOR_SERVERS = "getMonitorServers"; // 集群中的服务器相关信息
    public static final String GET_STATDATE = "getStatDate"; // 统计时间

    /**
     * 后台-模块
     *
     * @see cms/src/net/zdsoft/netstudy/service/system/impl/SystemModuleServiceImpl.java
     */
    public static final String LIST_SYSTEM_MODULE = "listSystemModule"; // 模块列表

    /**
     * 在线练习
     *
     * @see exer/src/net/zdsoft/netstudy/exer/service/homework/impl/ExerHomeworkServiceImpl.java
     */
    public static final String SYNC_HOMEWORK_PREX = "sync_exer_homework_info_"; // 同步练习题并发控制

    /**
     * login-应用
     *
     * @see login/src/net/zdsoft/netstudy/login/service/server/impl/ServerServiceImpl.java
     */
    public static final String GET_SERVERS = "GET_SERVERS"; // 已注册的所有服务list
    public static final String GET_SERVER_MAP = "GET_SERVER_MAP"; // 已注册的所有服务map

    /** 无限宝PC客户端路径 **/
    public static final String GET_WXB_PC_CLIENT_URL = "getWxbPcClientUrl_";
    /** 无限宝PC客户端更新路径 **/
    public static final String GET_WXB_CLIENT_UPDATE_URL = "getWxbClientUpdateUrl_";
    /** 无限宝Android客户端路径 **/
    public static final String GET_WXB_ANDROID_CLIENT_URL = "getWxbAndroidClientUrl_";

    /** 页脚链接 **/
    public static final String GET_SYSTEM_INDEX_FOOTER = "systemIndexFooter";

}
