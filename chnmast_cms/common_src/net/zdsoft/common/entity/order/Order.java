/*
 * @(#)Order.java    Created on 2015年12月23日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity.order;

import java.util.Date;

import net.zdsoft.common.entity.BaseEntity;
import net.zdsoft.common.entity.course.Course;
import net.zdsoft.common.enums.CourseBuyType;
import net.zdsoft.common.enums.CourseStudyType;
import net.zdsoft.common.enums.DeviceType;
import net.zdsoft.common.enums.HifiCheckStatus;
import net.zdsoft.common.enums.OrderCheckStatus;
import net.zdsoft.common.enums.OrderKind;
import net.zdsoft.common.enums.OrderReportReturnStatus;
import net.zdsoft.common.enums.OrderStatus;
import net.zdsoft.common.enums.OrderStatusType;
import net.zdsoft.common.enums.OrderType;
import net.zdsoft.common.enums.PayType;

/**
 * 订单t_order
 *
 * @author hanqr
 *
 * @version $Revision: 1.0 $, $Date: 2015年12月23日 下午2:06:28 $
 */
public class Order extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 交易号（32位uuid）
     */
    private String tradeNo;

    /**
     * 学生用户ID
     */
    private long userId;

    /**
     * 总金额
     */
    private float totalAmount;

    /**
     * 优惠金额
     */
    private float annulAmount;

    /**
     * 实付金额
     */
    private float payAmount;

    /**
     * 创建时间
     */
    private Date creationTime;

    /**
     * 付款或操作时间
     */
    private Date paymentTime;

    /**
     * 订单关闭时间
     */
    private Date closeTime;

    /**
     * 操作人ID
     */
    private long operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 状态（0待付款，1交易成功，2交易失败）
     */
    private OrderStatus orderStatus;

    /**
     * 状态类型（0待付款，11交易成功-系统完成，12交易成功-后台完成，21交易失败-后台取消，22交易失败-用户取消，23交易失败-超时未付款）
     */
    private OrderStatusType statusType;

    /**
     * 付款方式（0未确定，1支付宝，2余额，3微信，4线下支付，5免费，6后台支付，7苹果支付，8乐币支付）
     */
    private PayType payType;

    /**
     * 订单来源（0前台报名，1后台报名）
     */
    private OrderType orderType;

    /**
     * 付款确认状态（ 0未查询 1查询中 2检查成功,支付宝已付款 3检查成功,支付宝未付款 4检查成功,支付宝无订单 5检查失败）
     */
    private OrderCheckStatus checkStatus;

    /**
     * 付款查询描述（ 只有查询失败的时候才值）
     */
    private String checkResult;

    /**
     * 付款查询次数
     */
    private int checkNum;

    /**
     * 创建设备（0PC，1手机）
     */
    private DeviceType createDevice;

    /**
     * 修改设备（0PC，1手机）
     */
    private DeviceType modifyDevice;

    /**
     * 支付设备（0PC，1手机）
     */
    private DeviceType payDevice;

    /**
     * 购买类型（0：整课购买，1：选课次购买）
     */
    private CourseBuyType buyType;

    /**
     * 订单类型（0课程/音乐会，3活动，4点播视频，5HIFI会员，6IOS乐币）（1，2保留，拆分课程/音乐会）
     */
    private OrderKind orderKind;

    /**
     * 备注
     */
    private String remark;

    /**
     * hifi订单号
     */
    private String hifiOrder;

    /**
     * hifi订单检查状态
     */
    private HifiCheckStatus hifiStatus;

    /**************************** 扩展属性 *************************/
    private Course course;// 课程信息
    private int courseTimeNum;// 场次数
    private CourseStudyType studyType;// 上课形式（0在线直播，1现场听课/现场听音乐会，2点播）
    private String realname;// 姓名
    private String username;// 用户名
    private String phone;// 手机号
    private boolean isHaveAnnul;// 是否有优惠

    private int courseCommentNum;// 此订单课程评论数

    private OrderReportReturnStatus isReturnCourse;// 退课状态
    private String tempStr;// 字符串显示前台（临时使用）

    /**************************** get、set方法 *************************/
    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public float getAnnulAmount() {
        return annulAmount;
    }

    public void setAnnulAmount(float annulAmount) {
        this.annulAmount = annulAmount;
    }

    public float getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(float payAmount) {
        this.payAmount = payAmount;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(OrderStatusType statusType) {
        this.statusType = statusType;
    }

    public PayType getPayType() {
        return payType;
    }

    public void setPayType(PayType payType) {
        this.payType = payType;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderCheckStatus getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(OrderCheckStatus checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public int getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(int checkNum) {
        this.checkNum = checkNum;
    }

    public DeviceType getCreateDevice() {
        return createDevice;
    }

    public void setCreateDevice(DeviceType createDevice) {
        this.createDevice = createDevice;
    }

    public DeviceType getModifyDevice() {
        return modifyDevice;
    }

    public void setModifyDevice(DeviceType modifyDevice) {
        this.modifyDevice = modifyDevice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public DeviceType getPayDevice() {
        return payDevice;
    }

    public void setPayDevice(DeviceType payDevice) {
        this.payDevice = payDevice;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getCourseTimeNum() {
        return courseTimeNum;
    }

    public void setCourseTimeNum(int courseTimeNum) {
        this.courseTimeNum = courseTimeNum;
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

    public CourseBuyType getBuyType() {
        return buyType;
    }

    public void setBuyType(CourseBuyType buyType) {
        this.buyType = buyType;
    }

    /**
     * @return Returns the realname.
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param realname
     *            The realname to set.
     */
    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * @param username
     *            The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the isHaveAnnul.
     */
    public boolean getIsHaveAnnul() {
        return isHaveAnnul;
    }

    /**
     * @param isHaveAnnul
     *            The isHaveAnnul to set.
     */
    public void setIsHaveAnnul(boolean isHaveAnnul) {
        this.isHaveAnnul = isHaveAnnul;
    }

    public OrderKind getOrderKind() {
        return orderKind;
    }

    public void setOrderKind(OrderKind orderKind) {
        this.orderKind = orderKind;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public OrderReportReturnStatus getIsReturnCourse() {
        return isReturnCourse;
    }

    public void setIsReturnCourse(OrderReportReturnStatus isReturnCourse) {
        this.isReturnCourse = isReturnCourse;
    }

    /**
     * @return Returns the tempStr.
     */
    public String getTempStr() {
        return tempStr;
    }

    /**
     * @param tempStr
     *            The tempStr to set.
     */
    public void setTempStr(String tempStr) {
        this.tempStr = tempStr;
    }

    /**
     * @return Returns the courseCommentNum.
     */
    public int getCourseCommentNum() {
        return courseCommentNum;
    }

    /**
     * @param courseCommentNum
     *            The courseCommentNum to set.
     */
    public void setCourseCommentNum(int courseCommentNum) {
        this.courseCommentNum = courseCommentNum;
    }

    public String getHifiOrder() {
        return hifiOrder;
    }

    public void setHifiOrder(String hifiOrder) {
        this.hifiOrder = hifiOrder;
    }

    public HifiCheckStatus getHifiStatus() {
        return hifiStatus;
    }

    public void setHifiStatus(HifiCheckStatus hifiStatus) {
        this.hifiStatus = hifiStatus;
    }

}
