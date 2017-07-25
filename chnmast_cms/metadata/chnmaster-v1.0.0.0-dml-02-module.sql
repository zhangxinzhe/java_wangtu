-- 初始化后台子系统表
delete from T_SYSTEM_APP;

insert into T_SYSTEM_APP (id, app_name, app_des, index_url, is_using)
values (1, '中国音乐大师网', null, '/index.htm', 1);
commit;


-- 初始化后台模块表（模块id规则说明：子系统ID +2位一级模块序号 +2位二级模块序号 +2位功能点序号，如：新增教师为1200101，即1 20 01 01）
delete from T_SYSTEM_MODULE;

--
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1100000, 1, '首页', '/index.htm', null, 1, 0, 1, 0, 1100000);


--
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200000, 1, '基础数据', null, null, 1, 0, 0, 0, 1200000);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200100, 1, '培训基地', '/basic/agencyManage.htm', null, 1, 1200000, 0, 0, 1200100);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200101, 1, '新增', '/basic/addAgency.htm', null, 0, 1200100, 0, 1, 1200101);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200102, 1, '修改', '/basic/editAgency.htm', null, 0, 1200100, 0, 1, 1200102);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200103, 1, '删除', '/basic/delAgency.htm', null, 0, 1200100, 0, 1, 1200103);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200104, 1, '注销', '/basic/cancelAgency.htm', null, 0, 1200100, 0, 1, 1200104);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200105, 1, '恢复注销', '/basic/recoverAgency.htm', null, 0, 1200100, 0, 1, 1200105);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200106, 1, '重置账号密码', '/basic/resetAgencyPwd.htm', null, 0, 1200100, 0, 1, 1200106);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200107, 1, '展示图片', '/basic/agencyPicture.htm', null, 0, 1200100, 0, 1, 1200107);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200108, 1, '维护展示图片', '/basic/editAgencyPicture.htm', null, 0, 1200100, 0, 1, 1200108);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200200, 1, '名师大家', '/basic/teacherManage.htm', null, 1, 1200000, 0, 0, 1200200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200201, 1, '新增', '/basic/addTeacher.htm', null, 0, 1200200, 0, 1, 1200201);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200202, 1, '修改', '/basic/editTeacher.htm', null, 0, 1200200, 0, 1, 1200202);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200203, 1, '删除', '/basic/delTeacher.htm', null, 0, 1200200, 0, 1, 1200203);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200204, 1, '注销', '/basic/cancelTeacher.htm', null, 0, 1200200, 0, 1, 1200204);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200205, 1, '恢复注销', '/basic/recoverTeacher.htm', null, 0, 1200200, 0, 1, 1200205);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200206, 1, '风采展示', '/basic/teacherStyle.htm', null, 0, 1200200, 0, 1, 1200206);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200207, 1, '维护风采展示', '/basic/editTeacherStyle.htm', null, 0, 1200200, 0, 1, 1200207);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200300, 1, '个人用户', '/basic/studentManage.htm', null, 1, 1200000, 0, 0, 1200300);
/**
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200301, 1, '新增', '/basic/addStudent.htm', null, 0, 1200300, 0, 1, 1200301);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200302, 1, '修改', '/basic/editStudent.htm', null, 0, 1200300, 0, 1, 1200302);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200303, 1, '删除', '/basic/delStudent.htm', null, 0, 1200300, 0, 1, 1200303);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200304, 1, '导入', '/basic/importStudent.htm', null, 0, 1200300, 0, 1, 1200304);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200305, 1, '注销', '/basic/cancelStudent.htm', null, 0, 1200300, 0, 1, 1200305);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200306, 1, '恢复注销', '/basic/recoverStudent.htm', null, 0, 1200300, 0, 1, 1200306);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200307, 1, '重置密码', '/basic/resetStuPwd.htm', null, 0, 1200300, 0, 1, 1200307);
*/

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200400, 1, '助教管理', '/basic/assistantManage.htm', null, 1, 1200000, 0, 0, 1200400);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200401, 1, '新增', '/basic/assistantAdd.htm', null, 1, 1200400, 0, 1, 1200401);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200402, 1, '修改', '/basic/assistantEdit.htm', null, 1, 1200400, 0, 1, 1200402);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200403, 1, '删除', '/basic/assistantDelete.htm', null, 1, 1200400, 0, 1, 1200403);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200404, 1, '恢复注销', '/basic/assistantRecover.htm', null, 1, 1200400, 0, 1, 1200404);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200600, 1, '学校维护', '/basic/schoolManage.htm', null, 1, 1200000, 0, 0, 1200600);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200601, 1, '新增', '/basic/schoolAdd.htm', null, 1, 1200600, 0, 1, 1200601);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200602, 1, '修改', '/basic/schoolEdit.htm', null, 1, 1200600, 0, 1, 1200602);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200603, 1, '删除', '/basic/schoolDelete.htm', null, 1, 1200600, 0, 1, 1200603);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200604, 1, '导入', '/basic/schoolImport.htm', null, 1, 1200600, 0, 1, 1200604);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200700, 1, '音乐导师', '/basic/musicTutorManage.htm', null, 1, 1200000, 0, 0, 1200700);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200701, 1, '查看', '/basic/viewMusicTutor.htm', null, 0, 1200700, 0, 1, 1200701);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200702, 1, '新增', '/basic/addMusicTutor.htm', null, 0, 1200700, 0, 1, 1200702);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200703, 1, '修改', '/basic/editMusicTutor.htm', null, 0, 1200700, 0, 1, 1200703);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1200704, 1, '审核', '/basic/auditMusicTutor.htm', null, 0, 1200700, 0, 1, 1200704);


insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250000, 1, '审核管理', null, null, 1, 0, 0, 0, 1250000);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250100, 1, '加入联盟', '/basic/agencyJoinManage.htm', null, 1, 1250000, 0, 0, 1250100);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250101, 1, '查看', '/basic/viewAgencyJoin.htm', null, 1, 1250100, 0, 1, 1250101);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250102, 1, '修改', '/basic/editAgencyJoin.htm', null, 1, 1250100, 0, 1, 1250102);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250103, 1, '删除', '/basic/delAgencyJoin.htm', null, 1, 1250100, 0, 1, 1250103);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250104, 1, '同意加盟', '/basic/checkAgencyJoin.htm', null, 1, 1250100, 0, 1, 1250104);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250200, 1, '联合会会员', '/basic/unionMemberManage.htm', null, 1, 1250000, 0, 0, 1250200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250201, 1, '查看', '/basic/viewUnionMember.htm', null, 0, 1250200, 0, 1, 1250201);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250202, 1, '新增', '/basic/addUnionMember.htm', null, 0, 1250200, 0, 1, 1250202);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250203, 1, '修改', '/basic/editUnionMember.htm', null, 0, 1250200, 0, 1, 1250203);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250204, 1, '删除', '/basic/delUnionMember.htm', null, 0, 1250200, 0, 1, 1250204);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250205, 1, '审核', '/basic/auditUnionMember.htm', null, 0, 1250200, 0, 1, 1250205);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250300, 1, '导师审核', '/audit/tutorAuditManage.htm', null, 1, 1250000, 0, 0, 1250300);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1250400, 1, '评论审核', '/audit/commentAuditManage.htm', null, 1, 1250000, 0, 0, 1250400);




insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300000, 1, '课程/音乐会', null, null, 1, 0, 0, 0, 1300000);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300100, 1, '基地课程', '/course/courseManage.htm', null, 1, 1300000, 0, 0, 1300100);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300101, 1, '新增', '/course/addCourse.htm', null, 0, 1300100, 0, 1, 1300101);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300102, 1, '修改', '/course/editCourse.htm', null, 0, 1300100, 0, 1, 1300102);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300103, 1, '删除', '/course/delCourse.htm', null, 0, 1300100, 0, 1, 1300103);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300200, 1, '音乐会', '/concert/concertManage.htm', null, 1, 1300000, 0, 0, 1300200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300201, 1, '新增', '/concert/addConcert.htm', null, 0, 1300200, 0, 1, 1300201);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300202, 1, '修改', '/concert/editConcert.htm', null, 0, 1300200, 0, 1, 1300202);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300203, 1, '删除', '/concert/delConcert.htm', null, 0, 1300200, 0, 1, 1300203);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300300, 1, '点播视频', '/vod/vodManage.htm', null, 1, 1300000, 0, 0, 1300300);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300301, 1, '新增', '/vod/addVod.htm', null, 0, 1300300, 0, 1, 1300301);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300302, 1, '修改', '/vod/editVod.htm', null, 0, 1300300, 0, 1, 1300302);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300303, 1, '删除', '/vod/delVod.htm', null, 0, 1300300, 0, 1, 1300303);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1300400, 1, '活动专题', '/homepage/compete/competeManage.htm', null, 1, 1300000, 0, 0, 1300400);


-- 
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400000, 1, '首页管理', null, null, 1, 0, 0, 0, 1400000);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400100, 1, '图片音视频', '/homepage/adv/advManage.htm', null, 1, 1400000, 0, 0, 1400100);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400101, 1, '新增', '/homepage/adv/addAdv.htm', null, 1, 1400100, 0, 1, 1400101);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400102, 1, '修改', '/homepage/adv/updateAdv.htm', null, 1, 1400100, 0, 1, 1400102);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400103, 1, '删除', '/homepage/adv/indexAdvDelete.htm', null, 1, 1400100, 0, 1, 1400103);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400200, 1, '基地课程推荐', '/homepage/course/courseRec.htm', null, 1, 1400000, 0, 0, 1400200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400300, 1, '音乐会推荐', '/homepage/concert/concertRec.htm', null, 1, 1400000, 0, 0, 1400300);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400350, 1, '点播视频推荐', '/homepage/vod/vodRec.htm', null, 1, 1400000, 0, 0, 1400350);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400400, 1, '培训基地推荐', '/homepage/agency/agencyRec.htm', null, 1, 1400000, 0, 0, 1400400);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400500, 1, '名师大家推荐', '/homepage/tea/teacherRec.htm', null, 1, 1400000, 0, 0, 1400500);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400550, 1, '音乐导师推荐', '/homepage/tutor/tutorRec.htm', null, 1, 1400000, 0, 0, 1400550);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400560, 1, '高校风采展示', '/homepage/college/collegeManage.htm', null, 1, 1400000, 0, 0, 1400560);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400561, 1, '新增', '/homepage/college/collegeAdd.htm', null, 0, 1400000, 0, 1, 1400561);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400562, 1, '修改', '/homepage/college/collegeEdit.htm', null, 0, 1400000, 0, 1, 1400562);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400563, 1, '删除', '/homepage/college/collegeDel.htm', null, 0, 1400000, 0, 1, 1400563);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400600, 1, '优秀学员', '/homepage/stu/studentManage.htm', null, 1, 1400000, 0, 0, 1400600);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400601, 1, '新增', '/homepage/stu/addStudent.htm', null, 1, 1400600, 0, 1, 1400601);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400602, 1, '修改', '/homepage/stu/updateStudent.htm', null, 1, 1400600, 0, 1, 1400602);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400603, 1, '删除', '/homepage/stu/studentDelete.htm', null, 1, 1400600, 0, 1, 1400603);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400604, 1, '展示图片', '/homepage/stu/studentPicManage.htm', null, 0, 1400600, 0, 1, 1400604);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400605, 1, '维护展示图片', '/homepage/stu/addOrUpdateStuPicture.htm', null, 0, 1400600, 0, 1, 1400605);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400700, 1, '新闻公告', '/homepage/info/infoManage.htm', null, 1, 1400000, 0, 0, 1400700);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400701, 1, '新增', '/homepage/info/infoAdd.htm', NULL, 1, 1400700, 0, 1, 1400701);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400702, 1, '修改', '/homepage/info/infoEdit.htm', NULL, 1, 1400700, 0, 1, 1400702);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400703, 1, '删除', '/homepage/info/infoDelete.htm', NULL, 1, 1400700, 0, 1, 1400703);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400800, 1, '视频回顾', '/homepage/info/videoReManage.htm', null, 1, 1400000, 0, 0, 1400800);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400801, 1, '新增', '/homepage/info/videoReAdd.htm', NULL, 1, 1400800, 0, 1, 1400801);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400802, 1, '修改', '/homepage/info/videoReEdit.htm', NULL, 1, 1400800, 0, 1, 1400802);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400803, 1, '删除', '/homepage/info/videoReDel.htm', NULL, 1, 1400800, 0, 1, 1400803);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400900, 1, '帮助管理', '/homepage/help/helpManage.htm', null, 1, 1400000, 0, 0, 1400900);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1400950, 1, '页脚维护', '/homepage/index/footerManage.htm', null, 1, 1400000, 0, 0, 1400950);


-- 
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500000, 1, '业务管理', null, null, 1, 0, 0, 0, 1500000);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500100, 1, '支付处理', '/account/payDeal.htm', null, 1, 1500000, 0, 0, 1500100);
/**
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500101, 1, '取消订单', '/account/cancelOrder.htm', null, 0, 1500100, 0, 1, 1500101);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500102, 1, '线上支付完成订购', '/account/finishOnlinePay.htm', null, 0, 1500100, 0, 1, 1500102);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500103, 1, '线下支付完成订购', '/account/finishCashPay.htm', null, 0, 1500100, 0, 1, 1500103);
*/
/**
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500200, 1, '退课处理', '/account/returnDeal.htm', null, 1, 1500000, 0, 0, 1500200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500300, 1, '退费处理', '/account/withdrawDeal.htm', null, 1, 1500000, 0, 0, 1500300);
*/
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500300, 1, '票务验证', '/account/codeDeal.htm', null, 1, 1500000, 0, 0, 1500300);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500400, 1, '订单优惠', '/account/orderAnnul.htm', null, 1, 1500000, 0, 0, 1500400);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1500900, 1, '优惠券管理', '/account/couponManage.htm', null, 1, 1500000, 0, 0, 1500900);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500901, 1, '（批量）新增', '/account/addCoupon.htm', null, 0, 1500900, 0, 1, 1500901);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500902, 1, '修改', '/account/editCoupon.htm', null, 0, 1500900, 0, 1, 1500902);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500903, 1, '删除', '/account/delCoupon.htm', null, 0, 1500900, 0, 1, 1500903);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500904, 1, '导出', '/account/exportCoupon.htm', null, 0, 1500900, 0, 1, 1500904);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1500905, 1, '注销', '/account/cancelCoupon.htm', null, 0, 1500900, 0, 1, 1500905);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1501000, 1, 'HIFI会员管理', '/account/hifiMemberManage.htm', null, 1, 1500000, 0, 0, 1501000);


-- 
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1600000, 1, '运维管理', null, null, 1, 0, 0, 0, 1600000);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1600100, 1, '服务器维护', '/wxb/serverManage.htm', null, 1, 1600000, 0, 0, 1600100);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1600101, 1, '新增', '/wxb/addServer.htm', null, 0, 1600100, 0, 1, 1600101);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1600102, 1, '修改', '/wxb/editServer.htm', null, 0, 1600100, 0, 1, 1600102);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1600103, 1, '查看', '/wxb/serverInfo.htm', null, 0, 1600100, 0, 1, 1600103);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1600104, 1, '删除', '/wxb/delServer.htm', null, 0, 1600100, 0, 1, 1600104);
insert into T_SYSTEM_MODULE (id, app_id, NAME, URL, DESCRIPTION, ISSHOW, PARENTID, ISCOMMON, ISOPERATE, SEQ)
values (1600105, 1, '开启/停用服务器', null, null, 0, 1600100, 0, 1, 1600105);

/**
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1600200, 1, '导入无限宝日志', '/server/importServerLog.htm', null, 1, 1600000, 0, 0, 1600200);
*/


-- 
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700000, 1, '系统管理', null, null, 1, 0, 0, 0, 1700000);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700100, 1, '用户管理', '/system/systemUserManage.htm', null, 1, 1700000, 0, 0, 1700100);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700101, 1, '新增用户', '/system/addSystemUser.htm', null, 0, 1700100, 0, 1, 1700101);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700102, 1, '查看用户', '/system/viewSystemUser.htm', null, 0, 1700100, 0, 1, 1700102);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700103, 1, '冻结用户', '/system/cancelSystemUser.htm', null, 0, 1700100, 0, 1, 1700103);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700104, 1, '解冻用户', '/system/backSystemUser.htm', null, 0, 1700100, 0, 1, 1700104);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700105, 1, '分配权限', '/system/assignSystemRole.htm', null, 0, 1700100, 0, 1, 1700105);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700106, 1, '删除用户', '/system/delSystemUser.htm', null, 0, 1700100, 0, 1, 1700106);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700107, 1, '修改用户', '/system/editSystemUser.htm', null, 0, 1700100, 0, 1, 1700107);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700200, 1, '角色管理', '/system/systemRoleManage.htm', null, 1, 1700000, 0, 0, 1700200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700201, 1, '新增角色', '/system/addSystemRole.htm', null, 0, 1700200, 0, 1, 1700201);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700203, 1, '分配用户', '/system/assignSystemUser.htm', null, 0, 1700200, 0, 1, 1700203);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700204, 1, '删除角色', '/system/delSystemRole.htm', null, 0, 1700200, 0, 1, 1700204);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700205, 1, '查看角色', '/system/showSystemRole.htm', null, 0, 1700200, 0, 1, 1700205);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700206, 1, '修改角色', '/system/editSystemRole.htm', null, 0, 1700200, 0, 1, 1700206);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700300, 1, '系统日志', '/system/systemLog.htm', null, 1, 1700000, 0, 0, 1700300);

insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700400, 1, '系统参数', '/system/systemConfigManage.htm', null, 1, 1700000, 0, 0, 1700400);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1700401, 1, '修改系统参数', '/system/editSystemConfig.htm', null, 0, 1700400, 0, 1, 1700401);


-- 
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800000, 1, '统计查询', null, null, 1, 0, 0, 0, 1800000);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800100, 1, '报名统计', '/stat/reportStat.htm', null, 1, 1800000, 0, 0, 1800100);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800200, 1, '上课统计', '/stat/inclassStat.htm', null, 1, 1800000, 0, 0, 1800200);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800300, 1, '订单查询', '/stat/orderStat.htm', null, 1, 1800000, 0, 0, 1800300);
/**
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800400, 1, '订单优惠查询', '/stat/.htm', null, 1, 1800000, 0, 0, 1800400);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800500, 1, '退课处理查询', '/stat/.htm', null, 1, 1800000, 0, 0, 1800500);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800600, 1, '退费处理查询', '/stat/.htm', null, 1, 1800000, 0, 0, 1800600);
*/
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800700, 1, '收支统计', '/stat/fundsStat.htm', null, 1, 1800000, 0, 0, 1800700);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800800, 1, 'ios收支统计', '/stat/iospayStat.htm', null, 1, 1800000, 0, 0, 1800800);
insert into T_SYSTEM_MODULE (id, app_id, name, url, description, isshow, parentid, iscommon, isoperate, seq)
values (1800900, 1, '短信查询', '/stat/smsStat.htm', null, 1, 1800000, 0, 0, 1800900);
commit;


