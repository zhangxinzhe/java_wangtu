package net.zdsoft.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.properties.PropertiesHelper;
import net.zdsoft.keel.util.RandomUtils;
import net.zdsoft.keel.util.SecurityUtils;
import net.zdsoft.keel.util.Validators;

/**
 * 工具类
 *
 * @author winupon
 *
 */
public class Util {
    // 用于检验Emial的正则表达式
    public static final String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";
    // 用于检验手机的正则表达式
    public static final String PHONE_REGEX = "^([1][34578])\\d{9}$";
    // 用于检验手机的正则表达式（包括港澳台）
    public static final String PHONE_ALL_REGEX = "^[1][3-8]\\d{9}$|^([6|9])\\d{7}$|^[0][9]\\d{8}$|^[6]([8|6])\\d{5}$";
    // 用户验证正文的正则表达式
    public static final String CHINESE_REGEX = "[\u4e00-\u9fa5]";
    // 用户验证URL的正则表达式
    // ^(http|https|ftp)?(://)?(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*((:\\d+)?)(/(\\w+(-\\w+)*))*(\\.?(\\w)*)(\\?)?(((\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*(\\w*%)*(\\w*\\?)*(\\w*:)*(\\w*\\+)*(\\w*\\.)*(\\w*&)*(\\w*-)*(\\w*=)*)*(\\w*)*)$
    public static final String URL_REGEX = "^((https|http|ftp|rtsp|mms)?://)([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";
    // 验证固话格式的正则表达式
    public static final String TELEPHONE_REGEX = "^((0[1-9]{2}[0-9])?(0[12][0-9])?[-])?\\d{6,8}$";
    // 验证QQ格式的正则表达式
    public static final String QQ_REGEX = "^[1-9]\\d{4,10}$";
    // 验证邮编格式的正则表达式
    public static final String POSTCODE_REGEX = "[1-9]\\d{5}(?!\\d)";
    // 验证书的ISBN号的正则表达式
    public static final String ISBN_REGEX = "^(\\d[- ]*){9,13}[\\dxX]$";
    // 帐号验证/^[a-zA-Z0-9]+$/
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9_]+$";
    // 密码验证，只允许数字和字母(分类编码验证)
    public static final String PASSWORD_REGEX = "^[a-zA-Z0-9]+$";
    // 必须包含数字加字母 或者额外添加特殊字符
    public static final String WEB_PWD_REGEX = "^(?![^a-zA-Z]+$)(?!\\D+$).*$";

    private static final long MINUTE_TIME = 60 * 1000;
    private static final long HOUR_TIME = 60 * 60 * 1000;
    private static final long DAY_TIME = 24 * 60 * 60 * 1000;
    private static final long WEEK_TIME = 7 * 24 * 60 * 60 * 1000;

    private static final DecimalFormat FORMAT = new DecimalFormat("#.00");
    /**
     * 学生默认密码为12345678
     */
    public static final String STU_PASSWORD = "12345678";

    /**
     * 验证登录帐号格式是否合法
     *
     * @param postCode
     * @return
     */
    public static boolean checkUserName(String userName) {
        return Pattern.matches(USERNAME_REGEX, userName);
    }

    /**
     * 验证邮编格式是否合法
     *
     * @param postCode
     * @return
     */
    public static boolean checkPostCode(String postCode) {
        return Pattern.matches(POSTCODE_REGEX, postCode);
    }

    /**
     * 验证密码是否合法
     *
     * @param postCode
     * @return
     */
    public static boolean checkPassWord(String passWord) {
        return Pattern.matches(PASSWORD_REGEX, passWord);
    }

    /**
     * 验证密码是否合法 <br/>
     * 必须包含数字加字母 或者额外添加特殊字符
     *
     * @param pwd
     * @return
     */
    public static boolean checkWebPWD(String pwd) {
        return Pattern.matches(WEB_PWD_REGEX, pwd);
    }

    /**
     * 验证QQ格式是否合法
     *
     * @param qq
     * @return
     */
    public static boolean checkQq(String qq) {
        return Pattern.matches(QQ_REGEX, qq);
    }

    /**
     * 验证邮箱格式是否合法
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    /**
     * 验证手机格式是否合法
     *
     * @param phone
     * @return
     */
    public static boolean checkPhone(String phone) {
        return Pattern.matches(PHONE_REGEX, phone);
    }

    /**
     * 验证手机格式是否合法（包括港澳台）
     *
     * @param phone
     * @return
     */
    public static boolean checkPhoneAll(String phone) {
        return Pattern.matches(PHONE_ALL_REGEX, phone);
    }

    /**
     * 验证固话格式是否合法
     *
     * @param telePhone
     * @return
     */
    public static boolean checkTelePhone(String telePhone) {
        return Pattern.matches(TELEPHONE_REGEX, telePhone);
    }

    /**
     * 验证URL是否合法
     *
     * @param url
     * @return
     */
    public static boolean checkUrl(String url) {
        return Pattern.matches(URL_REGEX, url);
    }

    public static void main(String[] args) {
        String url = "http://wzt.ldqqvip.com/dtzg/?m=a98d3e1d63170d74449b45941e986b6d/";
        System.out.println(checkUrl(url));
    }

    /**
     * 验证中文是否合法
     *
     * @param chinese
     * @return
     */
    public static boolean checkChinese(String chinese) {
        if (StringUtils.isBlank(chinese)) {
            return false;
        }

        char[] aa = chinese.toCharArray();
        for (int i = 0; i < aa.length; i++) {
            char c = aa[i];
            if (!Pattern.matches(CHINESE_REGEX, String.valueOf(c))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 方法简述：将 byte[] 转换成ASCII字符
     *
     * @param bt
     *            byte[] 表示要转换的字节
     *
     * @return str 返回转换好的String
     */
    public static String bytestoASCII(byte[] bt) {
        String str = "";
        for (int i = 0; i < bt.length; i++) {
            int ii = bt[i];
            Character d = new Character((char) ii);
            str = str.concat(d.toString());
        }
        return str;
    }

    /**
     * 获取文件大小，单位B
     *
     * @param file
     */
    @SuppressWarnings("resource")
    public static final int getFileSize(File file) {
        FileInputStream photoInuptStream = null;
        try {
            photoInuptStream = new FileInputStream(file);
            return photoInuptStream.available();// 字节转换成KB
        }
        catch (Exception e) {
            return 0;
        }
    }

    /**
     * 获取心情的摘要(长为len)<br>
     * 一个表情当2个字符处理。
     *
     * @param emotion
     * @param len
     * @return
     */
    public static final String getEmotionAbstract(String emotion, int len) {
        if (StringUtils.isBlank(emotion) || emotion.length() <= len) {
            return emotion;
        }

        StringBuffer emotionAbstract = new StringBuffer();

        int length = 0;
        int start = 0, end = 0;
        String temp = emotion;
        while (length < len) {
            if (temp.length() < (len - length)) {
                emotionAbstract.append(temp);
                break;
            }

            start = temp.indexOf("<img");
            if (start == -1 || (start + 1 > (len - length))) {
                emotionAbstract.append(temp.substring(0, (len - length)) + "...");
                break;
            }
            if (start + 1 == (len - length)) {
                emotionAbstract.append(temp.substring(0, (len - length - 1)) + "...");
                break;
            }

            if (start != 0) {
                length += start;
                emotionAbstract.append(temp.substring(0, start));
            }

            end = temp.indexOf(">");
            emotionAbstract.append(temp.substring(start, end + 1));
            length += 2;

            temp = temp.substring(end + 1);

            if (length >= len) {
                emotionAbstract.append("...");
            }
        }

        // String _emotionAbstract = emotionAbstract.toString();
        // if (!_emotionAbstract.endsWith("/>") &&
        // _emotionAbstract.endsWith(">")) {
        // return _emotionAbstract.substring(0, _emotionAbstract.length() - 1)
        // + "/>";
        // } else {
        // return _emotionAbstract;
        // }
        return emotionAbstract.toString();
    }

    /**
     * 获取字符串的字节数(一个汉字两个字节)
     *
     * @param str
     * @return
     */
    public static int byteLength(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }

        int len = str.length();
        if (len == 0) {
            return 0;
        }

        int byteLen = 0;
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) > 255) {
                byteLen += 2;
            }
            else {
                byteLen++;
            }
        }

        return byteLen;
    }

    /**
     * 获取当前系统的网卡的mac地址.
     *
     * @return mac地址
     */
    public static String getMACAddress() {
        // 获取当前操作系统名称
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.startsWith("windows")) {
            return getWindowsMACAddress();
            // } else if (osName.startsWith("linux")) {
            // return getLinuxMACAddress();
        }
        else {
            return getUnixMACAddress();
        }
    }

    /**
     * 获取Unix网卡的mac地址.
     *
     * @return mac地址
     */
    private static String getUnixMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            /**
             * Unix下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
             */
            process = Runtime.getRuntime().exec("/sbin/ifconfig eth0");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                /**
                 * 寻找标示字符串[hwaddr]
                 */
                index = line.toLowerCase().indexOf("hwaddr");
                /**
                 * 找到了
                 */
                if (index != -1) {
                    /**
                     * 取出mac地址并去除2边空格
                     */
                    mac = line.substring(index + "hwaddr".length() + 1).trim();
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 获取Linux网卡的mac地址.
     *
     * @return mac地址
     */
    @SuppressWarnings("unused")
    private static String getLinuxMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            /**
             * linux下的命令，一般取eth0作为本地主网卡 显示信息中包含有mac地址信息
             */
            process = Runtime.getRuntime().exec("/sbin/ifconfig eth0");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                index = line.toLowerCase().indexOf("硬件地址");
                /**
                 * 找到了
                 */
                if (index != -1) {
                    /**
                     * 取出mac地址并去除2边空格
                     */
                    mac = line.substring(index + 4).trim();
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 获取widnows网卡的mac地址.
     *
     * @return mac地址
     */
    private static String getWindowsMACAddress() {
        String mac = null;
        BufferedReader bufferedReader = null;
        Process process = null;
        try {
            /**
             * windows下的命令，显示信息中包含有mac地址信息
             */
            process = Runtime.getRuntime().exec("ipconfig /all");
            bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            int index = -1;
            while ((line = bufferedReader.readLine()) != null) {
                /**
                 * 寻找标示字符串[physical address]
                 */
                index = line.toLowerCase().indexOf("physical address");
                if (index != -1) {
                    index = line.indexOf(":");
                    if (index != -1) {
                        /**
                         * 取出mac地址并去除2边空格
                         */
                        mac = line.substring(index + 1).trim();
                    }
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
            bufferedReader = null;
            process = null;
        }

        return mac;
    }

    /**
     * 验证书的ISBN号是否合法
     *
     * @param isbn
     * @return
     */
    public static boolean checkIsbn(String isbn) {
        return Pattern.matches(ISBN_REGEX, isbn);
    }

    /**
     * 根据当前时间，生成指定时间的字符串信息，用于在页面上做友好显示 <br>
     * <li>如果时间小于5分钟，显示“刚刚更新”</li><br>
     * <li>如果时间大于等于5分钟，小于一个小时，显示“**分钟前”</li><br>
     * <li>如果时间大于等于1个小时，小于一天，显示“**小时前”</li><br>
     * <li>如果时间大于等于一天，小于一周，显示“**天前”</li><br>
     * <li>如果时间大于一周，显示yyyy-MM-dd</li>
     *
     * @param actionDate
     * @return
     */
    public static String getTimeStr(Date date) {
        long curTime = Calendar.getInstance().getTimeInMillis() - date.getTime();

        if (curTime < MINUTE_TIME * 5) {
            return "刚刚更新";
        }
        else if (curTime < HOUR_TIME) {
            return (curTime / MINUTE_TIME) + "分钟前";
        }
        else if (curTime < DAY_TIME) {
            return (curTime / HOUR_TIME) + "小时前";
        }
        else if (curTime < WEEK_TIME) {
            return (curTime / DAY_TIME) + "天前";
        }
        else {
            return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
        }
    }

    // /**
    // * 自动生成接口所需的交互key
    // *
    // * @return
    // */
    // public static String generateInterfaceKey() {
    // StringBuffer sb = new StringBuffer();
    // sb.append(NetstudyConfig.getParam(Constants.INTERFACE_KEY));
    // long time = System.currentTimeMillis();
    // sb.append(time);
    // String md5 = SecurityUtils.encodeByMD5(sb.toString());
    // sb = new StringBuffer(md5).append(time);
    // return sb.toString();
    // }

    /**
     * 最近过去的十年年份列表
     *
     * @return
     */
    public static List<String> getRecentlyTenYear() {
        int showLatestYearNum = 10;// 显示最近10年
        List<String> years = new LinkedList<String>();
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        for (int i = 0; i < showLatestYearNum; i++) {
            years.add((currentYear - i) + "");
        }
        return years;
    }

    /**
     * 最近过去的十年年份列表
     *
     * @return
     */
    public static List<Integer> getRecentlyTenYearInt() {
        int showLatestYearNum = 10;// 显示最近10年
        List<Integer> years = new LinkedList<Integer>();
        Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        for (int i = 0; i < showLatestYearNum; i++) {
            years.add((currentYear - i));
        }
        return years;
    }

    private final static String passwordKey = "0101010101010101";

    /**
     * 解密
     *
     * @param password
     * @return
     */
    public static String decodePassword(String password) {
        if (Validators.isBlank(password)) {
            return "";
        }
        return SecurityUtils.decodeBy3DESAndBase64(password, SecurityUtils.encodeByMD5(passwordKey));
    }

    /**
     * 加密
     *
     * @param password
     * @return
     */
    public static String encodePassword(String password) {
        if (Validators.isBlank(password)) {
            return "";
        }
        return SecurityUtils.encodeBy3DESAndBase64(password, SecurityUtils.encodeByMD5(passwordKey));
    }

    /**
     * 学生随机密码8位
     *
     * @return
     */
    public static String encodeRandomPassword() {
        return encodeRandomNumPassword(8);
    }

    /**
     * 密码随机N位数字
     *
     * @return
     */
    public static String encodeRandomNumPassword(int length) {
        String password = RandomUtils.getRandomNum(length);
        return SecurityUtils.encodeBy3DESAndBase64(password, SecurityUtils.encodeByMD5(passwordKey));
    }

    /**
     * 学生默认密码为12345678
     *
     * @return
     */
    public static String encodeFixPassword() {
        String password = "12345678";// 学生默认密码为12345678
        return SecurityUtils.encodeBy3DESAndBase64(password, SecurityUtils.encodeByMD5(passwordKey));
    }

    /**
     * 教师默认密码为wx123456
     *
     * @return
     */
    public static String encodeFixTeaPassword() {
        String password = "wx123456";// 教师默认密码为wx123456
        return SecurityUtils.encodeBy3DESAndBase64(password, SecurityUtils.encodeByMD5(passwordKey));
    }

    /**
     * 获取文件后缀
     *
     * @param imgdest
     * @return
     * @throws Exception
     */
    public static String getSuffix(String dest) throws Exception {
        if (StringUtils.isBlank(dest)) {
            return "";
        }

        int posIndex = dest.lastIndexOf(".");
        if (posIndex < 0) {
            throw new Exception("文件格式不正确");
        }

        String imgSuffix = dest.substring(posIndex + 1);
        int suffixMark = imgSuffix.indexOf("?");
        if (suffixMark > 0) {
            imgSuffix = imgSuffix.substring(0, suffixMark);
        }
        return imgSuffix;
    }

    /**
     * 文件上传地址符解析器
     *
     * @author zhangxz
     * @version $Revision: 1.0 $, $Date: 2014-4-4 上午11:01:16 $
     */
    public static class UploadFilePathMarkParser {
        /**
         * 将content中的地址符转换成真实地址
         *
         * @param content
         * @return
         */
        public static String MarkToPath(String content) {
            if (StringUtils.isBlank(content)) {
                return "";
            }
            return content.replaceAll(BaseConstants.DOMAIN_UPLOAD_FILE_MARK,
                    NetstudyConfig.getParam(BaseConstants.DOMAIN_UPLOAD_FILE));
        }

        /**
         * 将content中的真实地址转换成地址符
         *
         * @param content
         * @return
         */
        public static String PathToMark(String content) {
            if (StringUtils.isBlank(content)) {
                return "";
            }
            return content.replaceAll(NetstudyConfig.getParam(BaseConstants.DOMAIN_UPLOAD_FILE),
                    BaseConstants.DOMAIN_UPLOAD_FILE_MARK);
        }
    }

    // /**
    // * 获取学段
    // *
    // * @param agencyId
    // * @return
    // */
    // public static List<ValueName<String, String>> getSectionList(Long agencyId) {
    // List<ValueName<String, String>> sectionList = null;
    // // 使用默认学段
    // sectionList = new ArrayList<ValueName<String, String>>();
    // sectionList
    // .add(new ValueName<String, String>(SectionType.XIAOXUE.getValue(), SectionType.XIAOXUE.getNameValue()));
    // sectionList.add(
    // new ValueName<String, String>(SectionType.CHUZHONG.getValue(), SectionType.CHUZHONG.getNameValue()));
    // sectionList.add(
    // new ValueName<String, String>(SectionType.GAOZHONG.getValue(), SectionType.GAOZHONG.getNameValue()));
    //
    // return sectionList;
    // }

    // /**
    // * 获取学科
    // *
    // * @param code
    // * @param agencyId
    // */
    // public static CourseSubject getCourseSubject(String code, long agencyId) {
    // ApplicationContext aContext = ApplicationContextHolder.getApplicationContext();
    // CourseSubjectService courseSubjectService = aContext.getBean(CourseSubjectService.class);
    // return courseSubjectService.getCourseSubjectByCodeAndAgencyId(code, agencyId);
    // }
    /**
     * 保留2位小数
     *
     * @param f
     * @return
     */
    public static String decimalFormat(float f) {
        return FORMAT.format(f);
    }

    /**
     * 执行定时任务判断
     *
     * @param from
     *            0 - spring调度 1-分布式任务调度
     *
     * @return
     */
    public static boolean validateTask(int from) {
        String test_timer = PropertiesHelper.getParam("test.test_timer");
        if (from == 0) {
            String jvm_netstudy_monitor = System.getProperty(BaseConstants.JVM_NETSTUDY_MONITOR);
            if ("0".equals(test_timer) && "true".equals(jvm_netstudy_monitor)) {
                return true;
            }
        }
        else if (from == 1) {
            if ("1".equals(test_timer)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 过滤emoji表情信息
     *
     * @param str
     * @return
     */
    public static String removeEmoji(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        return str.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", "");
    }

    /**
     * 含有emoji表情信息
     *
     * @param str
     * @return
     */
    public static boolean hasEmoji(String value) {
        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(value);
        while (matcher.find()) {
            return true;
        }
        return false;
    }
}
