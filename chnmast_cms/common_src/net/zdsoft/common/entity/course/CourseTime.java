package net.zdsoft.common.entity.course;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import net.zdsoft.common.config.NetstudyConfig;
import net.zdsoft.common.constant.BaseConstants;
import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.enums.ClassStatus;
import net.zdsoft.common.enums.CourseContentType;
import net.zdsoft.common.enums.CourseStudyType;
import net.zdsoft.common.enums.CourseTimeStatus;
import net.zdsoft.common.utils.NetstudyFileUtils;
import net.zdsoft.keel.util.DateUtils;

/**
 * 课次
 *
 * @author hongx
 * @version $Revision: 1.0 $, $Date: 2015年10月27日 下午3:21:50 $
 */
public class CourseTime extends BaseEntity {
    private static final long serialVersionUID = -7421575132373144803L;

    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 开始时间
     */
    private Date beginTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 课次序号
     */
    private int seq;
    /**
     * 课次主要内容或主题
     */
    private String title;

    /************************* 扩展属性 ****************************/
    private String courseName;
    private CourseContentType contentType;// 内容分类（1：基地课程，2：音乐会）
    private CourseStudyType studyType;// 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）
    private String teaRealName;// 主讲教师
    private String assRealName;// 助教姓名
    private Long teaId;// 主讲名师ID
    private Long assId;// 助教ID
    private String pictureFile;// 课程图片路径
    private Long wxbId;// 无限宝服务器ID
    private String place;// 上课地点
    private long onlinePriceId;
    private float onlinePrice;// 直播价
    private float onlinePriceVip;// 直播vip价
    private long localPriceId;// 现场价id
    private float localPrice;// 现场价
    private float localPriceVip;// 现场vip价
    private int localNum;// 现场票数
    private int localSaleNum;// 现场已买票数
    private boolean isPaid;// 是否已购买
    private boolean isFree;//
    private float vodPrice;// 点播价
    private float vodPriceVip;// 点播价VIP
    private long vodPriceId;// 点播价id
    private long vodId;// 视频id
    private float videoDuration;// 视频时长
    private String videoDurationStr;// 视频时长

    private List<CoursePrice> timePriceList;// 场次对应的票类列表

    private String studyTime;// 课堂进程（1月1日 7：00-20：00）

    private String orderDetailPriceStr;// 订单详情中场次下的票价信息
    private int totalNum;// 总票数（对应课程总课次数2016-08-05）
    private long verifyCode;// 验证码
    private CourseTimeStatus status;// 课次状态
    private CourseVideo video;// 课次对应的视频（点播视频）

    private boolean showPlay = true;// 是否显示播放按钮

    /**
     * 进入无线宝时间（开始时间+提前进入时间）
     */
    public Date getEnterTime() {
        String beforStr = NetstudyConfig.getParam(BaseConstants.WXB_COURSE_BEFORE_TIME);
        int befor = 15;// 默认15分钟
        if (StringUtils.isNotBlank(beforStr)) {
            befor = Integer.parseInt(beforStr);
        }
        return DateUtils.addMinute(beginTime, -befor);
    }

    /**
     * 退出无线宝时间（结束时间+延迟退出时间）
     */
    public Date getOutTime() {
        String delayStr = NetstudyConfig.getParam(BaseConstants.WXB_COURSE_DELAY_TIME);
        int delay = 15;// 默认15分钟
        if (StringUtils.isNotBlank(delayStr)) {
            delay = Integer.parseInt(delayStr);
        }
        return DateUtils.addMinute(endTime, delay);
    }

    /**
     * 是否是历史课次
     */
    public boolean getIsHistory() {
        Date date = new Date();
        if (endTime != null) {
            if (date.after(endTime)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断课次当前状态
     */
    public ClassStatus getClassStatus() {
        Date now = new Date(); // 当前时间
        String beforStr = NetstudyConfig.getParam(BaseConstants.WXB_COURSE_BEFORE_TIME);
        String delayStr = NetstudyConfig.getParam(BaseConstants.WXB_COURSE_DELAY_TIME);
        int befor = 15;// 默认15分钟
        int delay = 15;// 默认15分钟
        if (StringUtils.isNotBlank(beforStr)) {
            befor = Integer.parseInt(beforStr);
        }
        if (StringUtils.isNotBlank(delayStr)) {
            delay = Integer.parseInt(delayStr);
        }

        // 时间
        Date advanceEnterTime = DateUtils.addMinute(now, befor);// 提前15分钟进入课堂,要先显示出来
        Date waitEnterTime = DateUtils.addMinute(advanceEnterTime, 30);// 进入倒计时
        Date delayLeaveTime = DateUtils.addMinute(now, -delay);// 延时15分钟退出课堂,要先显示出来

        ClassStatus classStatus = null;
        if (advanceEnterTime.after(beginTime) && delayLeaveTime.before(endTime)) {
            classStatus = ClassStatus.in;
        }
        else if (delayLeaveTime.after(endTime)) {
            classStatus = ClassStatus.after;
        }
        else if (waitEnterTime.after(beginTime) && advanceEnterTime.before(beginTime)) {
            classStatus = ClassStatus.wait;
        }
        else {
            classStatus = ClassStatus.before;
        }
        return classStatus;
    }

    /**************************** get、set方法 *************************/
    /**
     * @return Returns the courseId.
     */
    public long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
     *            The courseId to set.
     */
    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return Returns the beginTime.
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * @param beginTime
     *            The beginTime to set.
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * @return Returns the endTime.
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            The endTime to set.
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return Returns the seq.
     */
    public int getSeq() {
        return seq;
    }

    /**
     * @param seq
     *            The seq to set.
     */
    public void setSeq(int seq) {
        this.seq = seq;
    }

    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return Returns the courseName.
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName
     *            The courseName to set.
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return Returns the contentType.
     */
    public CourseContentType getContentType() {
        return contentType;
    }

    /**
     * @param contentType
     *            The contentType to set.
     */
    public void setContentType(CourseContentType contentType) {
        this.contentType = contentType;
    }

    /**
     * @return Returns the teaRealName.
     */
    public String getTeaRealName() {
        return teaRealName;
    }

    /**
     * @param teaRealName
     *            The teaRealName to set.
     */
    public void setTeaRealName(String teaRealName) {
        this.teaRealName = teaRealName;
    }

    /**
     * @return Returns the timePriceList.
     */
    public List<CoursePrice> getTimePriceList() {
        return timePriceList;
    }

    /**
     * @param timePriceList
     *            The timePriceList to set.
     */
    public void setTimePriceList(List<CoursePrice> timePriceList) {
        this.timePriceList = timePriceList;
    }

    /**
     * @return Returns the studyTime.
     */
    public String getStudyTime() {
        return studyTime;
    }

    /**
     * @param studyTime
     *            The studyTime to set.
     */
    public void setStudyTime(String studyTime) {
        this.studyTime = studyTime;
    }

    /**
     * @return Returns the teaId.
     */
    public Long getTeaId() {
        return teaId;
    }

    /**
     * @param teaId
     *            The teaId to set.
     */
    public void setTeaId(Long teaId) {
        this.teaId = teaId;
    }

    /**
     * @return Returns the assId.
     */
    public Long getAssId() {
        return assId;
    }

    /**
     * @param assId
     *            The assId to set.
     */
    public void setAssId(Long assId) {
        this.assId = assId;
    }

    /**
     * @return Returns the assRealName.
     */
    public String getAssRealName() {
        return assRealName;
    }

    /**
     * @param assRealName
     *            The assRealName to set.
     */
    public void setAssRealName(String assRealName) {
        this.assRealName = assRealName;
    }

    /**
     * @return Returns the wxbId.
     */
    public Long getWxbId() {
        return wxbId;
    }

    /**
     * @param wxbId
     *            The wxbId to set.
     */
    public void setWxbId(Long wxbId) {
        this.wxbId = wxbId;
    }

    /**
     * @return Returns the studyType.
     */
    public CourseStudyType getStudyType() {
        return studyType;
    }

    /**
     * @param studyType
     *            The studyType to set.
     */
    public void setStudyType(CourseStudyType studyType) {
        this.studyType = studyType;
    }

    public float getOnlinePrice() {
        return onlinePrice;
    }

    public void setOnlinePrice(float onlinePrice) {
        this.onlinePrice = onlinePrice;
    }

    public float getOnlinePriceVip() {
        return onlinePriceVip;
    }

    public void setOnlinePriceVip(float onlinePriceVip) {
        this.onlinePriceVip = onlinePriceVip;
    }

    public boolean getIsPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public long getOnlinePriceId() {
        return onlinePriceId;
    }

    public void setOnlinePriceId(long onlinePriceId) {
        this.onlinePriceId = onlinePriceId;
    }

    public boolean getIsFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }

    /**
     * 课次状态 0：未开始，1：进行中，2：已经结束
     */
    public int getAgencyCouseState() {
        Date now = new Date();
        Date actBeginTime = beginTime;
        // if (CourseStudyType.ONLINE.equals(studyType)) {
        int addMinite = Integer.valueOf(NetstudyConfig.getParam(BaseConstants.WXB_COURSE_BEFORE_TIME));
        actBeginTime = DateUtils.addMinute(beginTime, -addMinite);// 实际可进入课堂时间
        // }

        if (null == beginTime || null == endTime) {
            return 0;
        }
        if (now.before(actBeginTime)) {
            return 0;
        }
        if (now.after(actBeginTime) && now.before(endTime)) {
            return 1;
        }
        if (now.after(endTime)) {
            return 2;
        }
        return 0;
    }

    /**
     * @return Returns the orderDetailPriceStr.
     */
    public String getOrderDetailPriceStr() {
        return orderDetailPriceStr;
    }

    /**
     * @param orderDetailPriceStr
     *            The orderDetailPriceStr to set.
     */
    public void setOrderDetailPriceStr(String orderDetailPriceStr) {
        this.orderDetailPriceStr = orderDetailPriceStr;
    }

    public long getLocalPriceId() {
        return localPriceId;
    }

    public void setLocalPriceId(long localPriceId) {
        this.localPriceId = localPriceId;
    }

    public float getLocalPrice() {
        return localPrice;
    }

    public void setLocalPrice(float localPrice) {
        this.localPrice = localPrice;
    }

    public float getLocalPriceVip() {
        return localPriceVip;
    }

    public void setLocalPriceVip(float localPriceVip) {
        this.localPriceVip = localPriceVip;
    }

    public CourseTimeStatus getStatus() {
        return status;
    }

    public void setStatus(CourseTimeStatus status) {
        this.status = status;
    }

    public int getLocalNum() {
        return localNum;
    }

    public void setLocalNum(int localNum) {
        this.localNum = localNum;
    }

    public int getLocalSaleNum() {
        return localSaleNum;
    }

    public void setLocalSaleNum(int localSaleNum) {
        this.localSaleNum = localSaleNum;
    }

    /**
     * @return Returns the place.
     */
    public String getPlace() {
        return place;
    }

    /**
     * @param place
     *            The place to set.
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * @return Returns the totalNum.
     */
    public int getTotalNum() {
        return totalNum;
    }

    /**
     * @param totalNum
     *            The totalNum to set.
     */
    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    /**
     * @return Returns the verifyCode.
     */
    public long getVerifyCode() {
        return verifyCode;
    }

    /**
     * @param verifyCode
     *            The verifyCode to set.
     */
    public void setVerifyCode(long verifyCode) {
        this.verifyCode = verifyCode;
    }

    /**
     * @return Returns the video.
     */
    public CourseVideo getVideo() {
        return video;
    }

    /**
     * @param video
     *            The video to set.
     */
    public void setVideo(CourseVideo video) {
        this.video = video;
    }

    public float getVodPrice() {
        return vodPrice;
    }

    public void setVodPrice(float vodPrice) {
        this.vodPrice = vodPrice;
    }

    public float getVodPriceVip() {
        return vodPriceVip;
    }

    public void setVodPriceVip(float vodPriceVip) {
        this.vodPriceVip = vodPriceVip;
    }

    public long getVodPriceId() {
        return vodPriceId;
    }

    public void setVodPriceId(long vodPriceId) {
        this.vodPriceId = vodPriceId;
    }

    public long getVodId() {
        return vodId;
    }

    public void setVodId(long vodId) {
        this.vodId = vodId;
    }

    public float getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(float videoDuration) {
        this.videoDuration = videoDuration;
    }

    /**
     * 获取播放时长
     */
    public String getVideoDurationStr() {
        String str = "";
        if (videoDuration == 0) {
            str = "-";
            return str;
        }
        int allSeconds = (int) (videoDuration * 60);
        int hour = allSeconds / 3600;
        if (hour > 0) {
            str += hour + "小时";
        }
        int munites = (allSeconds - hour * 3600) / 60;
        if (munites > 0) {
            str += munites + "分";
        }
        int second = allSeconds % 60;
        if (second > 0) {
            str += second + "秒";
        }
        return str;
    }

    /**
     * @return Returns the pictureFile.
     */
    public String getPictureFile() {
        return pictureFile;
    }

    /**
     * @param pictureFile
     *            The pictureFile to set.
     */
    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    public boolean getShowPlay() {
        return showPlay;
    }

    public void setShowPlay(boolean showPlay) {
        this.showPlay = showPlay;
    }

    /**
     * 压缩图片路径
     *
     * @return
     */
    public String getSmallPictureFile() {
        if (StringUtils.isEmpty(pictureFile)) {
            return "";
        }
        return NetstudyFileUtils.builSmallFilePath(pictureFile, "_small");
    }

}
