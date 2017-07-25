package net.zdsoft.chnmaster.action.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;
import net.zdsoft.chnmaster.entity.system.SystemLog;
import net.zdsoft.chnmaster.service.system.SystemLogService;
import net.zdsoft.chnmaster.service.system.SystemUserService;
import net.zdsoft.common.dao.queryCondition.GreaterEqualCondition;
import net.zdsoft.common.dao.queryCondition.InNumberCondition;
import net.zdsoft.common.dao.queryCondition.LessEqualCondition;
import net.zdsoft.common.dao.queryCondition.LikeCondition;
import net.zdsoft.common.dao.queryCondition.QueryCondition;
import net.zdsoft.common.entity.PageDto;
import net.zdsoft.keel.util.DateUtils;
import net.zdsoft.keel.util.Validators;

/**
 * 系统操作日志
 * 
 * @author hongx
 */
@Scope("prototype")
@Controller
public class SystemLogAction extends CmsPageAction {
    private static final long serialVersionUID = 4094896844489693752L;

    @Resource
    private SystemLogService systemLogService;
    @Resource
    private SystemUserService systemUserService;

    private List<SystemLog> systemLogs;
    private Date beginTime;
    private Date endTime;
    private String username;
    private String realName;
    private String operate;
    private boolean f = true; // 首次访问

    public String systemLog() throws Exception {
        PageDto page = this.getPage();

        // 判断是否需要通过用户名和真实姓名来查询
        if (!Validators.isBlank(username) || !Validators.isBlank(realName)) {
            // 判断是否与需要查询的用户数据
            List<Long> userIds = systemUserService.getSystemUserId(username, realName);
            if (CollectionUtils.isNotEmpty(userIds)) {
                systemLogs = systemLogService.getSystemLogs(builderConditions(userIds), page);
            }
        }
        else {
            systemLogs = systemLogService.getSystemLogs(builderConditions(null), page);
        }

        return SUCCESS;
    }

    // 封装查询条件
    public List<QueryCondition> builderConditions(List<Long> userIds) throws Exception {
        List<QueryCondition> conditions = new ArrayList<QueryCondition>();

        if (beginTime != null && endTime != null && beginTime.compareTo(endTime) > 0) {
            addActionError("开始时间不能大于结束时间");
            return conditions;
        }

        if (beginTime != null) {
            conditions.add(new GreaterEqualCondition("A.OPERATETIME", DateUtils.getStartDate(beginTime)));
        }

        if (endTime != null) {
            conditions.add(new LessEqualCondition("A.OPERATETIME", DateUtils.getEndDate(endTime)));
        }

        if (!StringUtils.isBlank(operate)) {
            operate = operate.trim();
            conditions.add(new LikeCondition("A.OPERATE", operate));
        }

        if (getUser().getId() == 2 && f) {
            username = "financial";
            conditions.add(new LikeCondition("B.USERNAME", username));
        }

        if (CollectionUtils.isNotEmpty(userIds)) {
            conditions.add(new InNumberCondition("A.USERID", userIds));
        }

        return conditions;
    }

    // 以下是get set 方法
    public List<SystemLog> getSystemLogs() {
        return systemLogs;
    }

    public void setSystemLogs(List<SystemLog> systemLogs) {
        this.systemLogs = systemLogs;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOperate() {
        return operate;
    }

    public void setOperate(String operate) {
        this.operate = operate;
    }

    public void setF(boolean f) {
        this.f = f;
    }
}
