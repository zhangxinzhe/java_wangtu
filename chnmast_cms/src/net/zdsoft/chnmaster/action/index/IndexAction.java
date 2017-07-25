package net.zdsoft.chnmaster.action.index;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.zdsoft.chnmaster.action.common.CmsPageAction;

@Scope("prototype")
@Controller
public class IndexAction extends CmsPageAction {
    private static final long serialVersionUID = 1L;

    private List<String> nameList;
    private List<Integer> numList;
    private List<String> urllist;

    public String index() {
        // nameList = new ArrayList<>();
        // numList = new ArrayList<>();
        // urllist = new ArrayList<>();
        // Map<CourseContentType, Integer> map = courseService.getCourseCountNumOfAuditing();
        // for (CourseContentType key : map.keySet()) {
        // if (key != null) {
        // nameList.add("课程管理-" + key.getNameValue());
        // numList.add(map.get(key));
        //
        // switch (key) {
        // case COURSE:
        // urllist.add("/course/courseManage.htm?course.auditStatus=AUDITING");
        // break;
        // case CONCERT:
        // urllist.add("/concert/concertManage.htm?course.auditStatus=AUDITING");
        // break;
        // case VOD:
        // urllist.add("/vod/vodManage.htm?course.auditStatus=AUDITING");
        // break;
        // default:
        // break;
        // }
        // }
        // }
        //
        // int tutorNum = userTeacherService.getTutorCountOfAuditing();
        // if (tutorNum > 0) {
        // nameList.add("基础数据-音乐导师");
        // numList.add(tutorNum);
        // urllist.add("/basic/musicTutorManage.htm?tutor.auditStatus=AUDITING");
        // }
        //
        // int agencyNum = agencyJoinService.getAgencyJoinCountOfAuditing();
        // if (agencyNum > 0) {
        // nameList.add("审核管理-加入联盟");
        // numList.add(agencyNum);
        // urllist.add("/basic/agencyJoinManage.htm?agencyStatus=APPLYING");
        // }
        //
        // int memberNum1 = unionMemberService.getMemberCountOfAuditing(false);
        // if (memberNum1 > 0) {
        // nameList.add("审核管理-联合会会员-个人");
        // numList.add(memberNum1);
        // urllist.add("/basic/unionMemberManage.htm?unionMember.unionAuditStatus=AUDITING");
        // }
        //
        // int memberNum2 = unionMemberService.getMemberCountOfAuditing(true);
        // if (memberNum2 > 0) {
        // nameList.add("审核管理-联合会会员-团体");
        // numList.add(memberNum2);
        // urllist.add("/basic/unionMemberManage.htm?isTeam=true&&unionMember.unionAuditStatus=AUDITING");
        // }

        return SUCCESS;
    }

    public List<String> getNameList() {
        return nameList;
    }

    public List<Integer> getNumList() {
        return numList;
    }

    public List<String> getUrllist() {
        return urllist;
    }

}
