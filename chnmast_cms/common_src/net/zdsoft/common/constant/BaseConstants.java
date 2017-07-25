package net.zdsoft.common.constant;

import net.zdsoft.common.enums.PathType;

/**
 * 系统中的一些常量
 *
 * @author winupon
 *
 */
public class BaseConstants {
    public static final String TEMP = "temp";
    public static final String URL_ENCODE_TYPE = "UTF-8";
    public static final String URL_ENCODE_TYPE_GBK = "GBK";
    public static final String URL_ENCODE_TYPE_GB2312 = "GB2312";
    /** 连接链接符号"/" **/
    public static final String SEPERATOR = "/";
    public static final int ZERO = 0;
    public static final String JPG_EXTNAME = "jpg";
    public static final String WMV_EXTNAME = "wmv";
    public static final String FLV_EXTNAME = "flv";
    public static final String UNDERLINE = "_";
    public static final String QUESTION_MARK = "?";
    public static final String POINT = ".";
    public static final String IMAGE_PATH = PathType.IMAGES.getValue() + BaseConstants.SEPERATOR;
    public static final String LOGIN_KEY = "chnmaster.login.key";
    public static final String TEMP_USER_UUID = "chnmaster.temp_user_uuid";

    // ========================== memcached 参数信息 ========================
    /** 最大缓存天数 */
    public static final int CACHE_MAX_DAY = 30;
    /** 普通缓存对象的缓存时间，注意：时间单位自己定（例如分钟 小时等） */
    public static final int CACHE_TIME = 1;
    /** memcached缓存前辍字符串 */
    public static final String CACHE_PREFIX = "chnmaster";
    /** memcached缓存服务器地址 */
    public static final String CACHE_SERVER = "cache.server_url";

    // ========================== 文件系统 参数信息 ========================
    /** 文件系统类型 */
    public static final String CHNMASTER_FILE_TYPE = "file.file_type";
    /** 文件的存储目录 */
    public static final String CHNMASTER_FILE_PATH = "file.file_path";
    /** 用户文件存放子目录 */
    public static final String CHNMASTER_USER_FILE_CONTEXT = "file.user_file_context";
    /** 系统文件存放子目录 */
    public static final String CHNMASTER_SYSTEM_FILE_CONTEXT = "file.system_file_context";
    /** 文件地址替换符 */
    public static final String DOMAIN_UPLOAD_FILE_MARK = "\\$\\{DOMAIN_UPLOAD_FILE\\}";

    /** 本地文件上传地址 */
    public static final String LOCAL_UPLOAD_URL = "file.local_upload_url";

    // ========================== 平台安全性 参数信息 ========================
    /** 平台安全性开关常量标志 */
    public static final String SECURITY_TURN = "security.turn";
    /** 平台安全性级别 */
    public static final String SECURITY_MODEL = "security.model";
    /** 平台安全性可忽略地址 */
    public static final String SECURITY_IGNORE = "security.ignore";

    // ========================== 平台参数信息 ========================
    /** 前台名称 **/
    public static final String SITE_HOME_SITE_NAME = "site.home_site_name";
    /** 后台名称 **/
    public static final String SITE_CMS_SITE_NAME = "site.cms_site_name";

    /** 前台域名地址 **/
    public static final String DOMAIN_HOME = "domain.home";
    /** 后台域名地址 **/
    public static final String DOMAIN_CMS = "domain.cms";
    /** 手机域名地址 **/
    public static final String DOMAIN_MOBILE = "domain.mobile";
    /** 系统静态文件域名地址 **/
    public static final String DOMAIN_FILE = "domain.file";
    /** 用户上传文件域名地址 **/
    public static final String DOMAIN_UPLOAD_FILE = "domain.upload_file";

    // ========================== 支付宝参数信息 ========================
    /** 商品名称 **/
    public static final String ALIPAY_SUBJECT = "alipay.subject";
    /** 支付宝合作伙伴id **/
    public static final String ALIPAY_PARTNER = "alipay.partner";
    /** 支付宝密钥 **/
    public static final String ALIPAY_KEY = "alipay.key";
    /** 卖家支付宝帐号 **/
    public static final String ALIPAY_SELLER_EMAIL = "alipay.seller_email";
    /** 公司支付宝RSA私钥 **/
    public static final String ALIPAY_RSA_PRIVATE_KEY = "alipay.rsa.private.key";
    /** 支付宝RSA公钥 **/
    public static final String ALIPAY_RSA_PUBLIC_KEY = "alipay.rsa.public.key";

    // ========================== 无限宝使用参数信息 ========================
    /** 调度中心开关 **/
    public static final String DISPATCH_SWITCH = "dispatch.switch";
    /** 调度中心地址 **/
    public static final String DISPATCHER_DOMAIN = "dispatch.domain";
    /** 课堂提前时间 **/
    public static final String WXB_COURSE_BEFORE_TIME = "wxb.course_before_time";
    /** 课堂延迟时间 **/
    public static final String WXB_COURSE_DELAY_TIME = "wxb.course_delay_time";

    /** 是否记录无限宝回调日志 **/
    public static final String WXB_CALL_RECORD = "wxb.call_record";

    /** 无线宝文件目录 */
    public static final String WXB_CLIENT_DIRECTORY = "wxb_client_directory";
    /** 无线宝文件名称 */
    public static final String WXB_PC_CLIENT_FILE_NAME = "wxb_pc_client_file_name";
    public static final String WXB_ANDROID_CLIENT_FILE_NAME = "wxb_android_client_file_name";

    /** 无限宝IOS版安装路径 */
    public static final String WXB_IOS_URL = "wxb.ios_url";

    /** 无限宝获取的key */
    public static final String WXB_PC_CLIENT_KEY = "wxb_pc_client_key";
    public static final String WXB_ANDROID_CLIENT_KEY = "wxb_android_client_key";
    public static final String WXB_CLIENT_UPDATE_KEY = "wxb_client_update_key";

    /** 无限定是否自动升级 **/
    public static final String WXB_UPDATE = "wxb.update";

    /** 无限宝PC客户端模块配置参数，隐藏/显示一些可定义的模块 **/
    public static final String WXB_MODULE_PARAMS = "wxb.module_params";

    /** 无限宝获取参数的固定字符串 */
    public static final String VIZPOWER = "VIZPOWER";
    /** 启动无线宝-是否验证验证码信息 */
    public static final String IS_REMOVE_VERIFY_CODE = "is_remove_verify_code";
    /** 课堂点名是否禁用（1禁用，0打开） */
    public static final String NoRollCall = "NoRollCall";
    /** 是否提醒安装摄像头 */
    public static final String CAMERA_REMIND = "camera.remind";
    /** 是否允许监控摄像头内容 */
    public static final String CAMERA_SNAP = "camera.snap";
    /** 测试课程是否启用自动录像功能 */
    public static final String COURSE_TEST_COURSE_AUTO_RECORD = "course.test_course_auto_record";
    /** 自动录像提醒 */
    public static final String COURSE_AUTO_RECORD_PROMPT = "course.auto_record_prompt";
    /** 无线宝客户端类型 */
    public static final String WXB_CLIENTTYPE = "wxb.clienttype";
    /** 设置无限宝资料下载区的文件大小限制，值格式如：20;200，两个数字间用分号（;）隔开，分别表示单个文件限制和课堂限制（单位M） */
    public static final String NDConf = "NDConf";
    /** 启动无线宝-控制是否在PC客户端显示网络摄像头（1表示开启，0表示关闭） */
    public static final String IPVCAMERA = "IPVCAMERA";
    /** 是否开启答疑模式（1答疑模式;0关闭答疑;默认关闭答疑模式）,此参数针对人人通项目是否启用答疑模式 */
    public static final String QAMODE = "QAMode";
    /** web启动无限宝客户端时，添加支持网页直播功能开关;1表示允许，0或者不设置表示不允许 **/
    public static final String AllowHLS = "vpweb.allowHLS";

    // 对接无限宝，安全验证参数
    public static final String PRESIDENT_KEY = "zdsoft@netstudy.com";
    public static final String WINUPON_PRESIDENTKEY = "Winupon.Vizpower.PresidentKey";
    public static final String WINUPON_VERIFYKEY = "Winupon.Vizpower.VerifyKey";

    // ========================== 首页参数 ========================
    /**
     * 首页轮播图片数目
     */
    public static final String INDEX_PLAY_IMAGE = "index_image_play";
    /**
     * 首页友情链接
     */
    public static final String INDEX_FRIEND_LINK = "index_friend_link";
    /**
     * 首页客服电话
     */
    public static final String INDEX_SERVICE_TEL = "index_service_tel";
    /**
     * 页脚内容-PC
     */
    public static final String FOOTER_INDEX_PC = "footer.index_pc_value";
    /**
     * 页脚内容-PC-活动专题
     */
    public static final String FOOTER_COMPETE_PC = "footer.compete_pc_value";

    // ========================== 订单参数 ========================
    /**
     * 待支付订单过期时间[天]
     */
    public static final String order_order_over_time = "order.order_over_time";

    // ========================== 其他参数信息 ========================
    /** 域名过滤时可忽略域名 **/
    public static final String IGNORE_FILTER_DOMAIN = "filter.domain";

    /** 访问量统计脚本 */
    public static final String SYSTEM_STAT_SCRIPT = "system.stat_script";

    /** 登录验证码信息Cookie的key */
    public static final String LOGIN_VERIFY_CODE_COOKIE_KEY = "login_cookie_verify_code";

    /** 找回密码验证码信息Cookie的key */
    public static final String FINDPWD_VERIFY_CODE_COOKIE_KEY = "findpwd_verify_code_cookie_key";

    /** 找回密码后，直接登录，安全验证 */
    public static final String FINDPWD_UUID_SESSION_KEY = "findpwd_uuid_session_key";

    /** 系统用户重置密码 */
    public static final String SYSTEM_USER_RESET_PASSWORD = "system.user_reset_password";

    /** 注册验证码信息Session的key */
    public static final String REGISTER_VERIFY_CODE_SESSION_KEY = "register_session_verify_code";

    /** 系统定时任务jvm虚拟机参数 */
    public static final String JVM_NETSTUDY_MONITOR = "chnmaster.monitor";

    /** 是否开放自注册 **/
    public static final String USER_REGISTER_TYPE = "user_register_type";

    /** 后台批量导入用户，发送短信模版 **/
    public static final String CMS_BATCH_IMPORT_USER = "cms.batch_import_user";

    /** ==================================玄武短信相关S */
    public static final String PHONE_CM_HOST = "phone.xuanwu_cm_host"; // Cmhost
    public static final String PHONE_WS_HOST = "phone.xuanwu_ws_host"; // Wshost
    public static final String PHONE_CM_PORT = "phone.xuanwu_cm_port"; // Cm端口
    public static final String PHONE_WS_PORT = "phone.xuanwu_ws_port"; // Ws端口
    public static final String PHONE_DEFINED_CONTENT_USERNAME = "phone.defined_content_username";
    public static final String PHONE_DEFINED_CONTENT_PASSWORD = "phone.defined_content_password";
    public static final String PHONE_PUBLIC_CONTENT_USERNAME = "phone.public_content_username";
    public static final String PHONE_PUBLIC_CONTENT_PASSWORD = "phone.public_content_password";
    /** 发送短信后默认20分钟去检查发送情况 **/
    public static final String PHONE_XUANWU_CHECK_MINIUTE = "phone.xuanwu_check_miniute";
    /** 一天发送验证次数 **/
    public static final String PHONE_XUANWU_VERIFY_CODE_NUM = "phone.xuanwu_verify_code_num";
    /** 短信间隔时间 秒 **/
    public static final String PHONE_XUANWU_VERIFY_CODE_TIME = "phone.xuanwu_verify_code_time";
    /** 发送短信最多检查次数 **/
    public static final String PHONE_XUANWU_CHECK_TIMES = "phone.xuanwu_check_times";
    /** 是否获取发送结果信息 0不获取 1获取 **/
    public static final String PHONE_XUANWU_SEND_REPORT = "phone.xuanwu_send_report";
    /** ==================================玄武短信相关E */

    /** 沃德短信参数信息 **/
    public static final String PHONE_WOLUN_USERNAME = "phone.wolun_username";
    public static final String PHONE_WOLUN_PASSWORD = "phone.wolun_password";
    public static final String PHONE_WOLUN_EXTNO = "phone.wolun_extno";

    /** 固定格式短信网关 **/
    public static final String PHONE_PUBLIC_CONTENT_GATEWAY = "phone.public_content_gateway";

    /** ============================== qq相关 S =========================== **/
    /** qq登录的head标签 **/
    public final static String QQ_APP_HEAD = "qq.app.head";
    /** qq登录的appid **/
    public final static String QQ_APP_ID = "qq.app.id";

    /** qq登录的app key **/
    public final static String QQ_APP_KEY = "qq.app.key";

    /** 手机端 qq登录的app key **/
    public final static String QQ_MOBILE_APP_KEY = "qq.mobile_app_key";
    /** 手机端 qq登录的appid **/
    public final static String QQ_MOBILE_APP_ID = "qq.mobile_app_id";
    /** ============================== qq相关E =========================== **/

    /** ============================== 微信相关S ========================= **/
    /** 微信公众账号id **/
    public final static String WECHATPAY_APP_ID = "wechatpay.app_id";
    /** 微信公众账号应用密钥 **/
    public final static String WECHATPAY_APP_SECRET = "wechatpay.app_secret";
    /** 微信商户号id **/
    public final static String WECHATPAY_MCH_ID = "wechatpay.mch_id";
    /** 微信安全校验码 **/
    public final static String WECHATPAY_VERIFY_KEY = "wechatpay.verify_key";

    /** 微信App_id(APP) **/
    public final static String WECHATPAY_APP_APP_ID = "wechatpay.app.app_id";
    /** 微信应用密钥(APP) **/
    public final static String WECHATPAY_APP_APP_SECRET = "wechatpay.app.app_secret";
    /** 微信商户号id(APP) **/
    public final static String WECHATPAY_APP_MCH_ID = "wechatpay.app.mch_id";
    /** 微信安全校验码(APP) **/
    public final static String WECHATPAY_APP_VERIFY_KEY = "wechatpay.app.verify_key";
    /** ============================== 微信相关E ========================= **/

    /** ============================== hifi相关S ======================== **/
    /** hifi接口主域名地址 **/
    public static final String HIFI_DOMAIN = "chnmaster.hifi_domain";
    public static final String HIFI_APIKEY_NO_LOGIN = "chnmaster.hifi.apikey.nologin";
    public static final String HIFI_APIKEY_LOGIN = "chnmaster.hifi.apikey.login";
    public final static String HIFI_VIP_AMOUNT = "hifi.member_monthly_price";
    public final static String HIFI_MEMBER_PROJECT_ID = "hifi_member_project_id";
    /** ============================== hifi相关S ======================== **/

}
