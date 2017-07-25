<!doctype html>
<head>
<meta charset="UTF-8">
<#include "/pages/common/common.ftl">
<#include "/pages/jsinclude/commonjs.ftl" />
<title>${SITE_CMS_SITE_NAME}-优惠券管理</title>

</head>
<body>
<@header curAppId=appId/>

<div id="container" class="fn-clear">
    <@sidebar curTopModId=topModId curSubModId=subModId />
    <div id="content">
        <p class="crumbs">当前位置：优惠券管理&nbsp;&gt;&nbsp;<span><#if !coupon.id?exists || coupon.id == 0>新增<#else>修改</#if></span></p>
        <form id="coupon_form" action="" method="post">
            <input type="hidden" id="couponId" name="coupon.id" value="${(coupon.id)!'0'}"/>
            <input type="hidden" name="coupon.couponCodeId" value="${(coupon.couponCodeId)!'0'}"/>
            <table class="form-table validateForm mt-10">
                <tr>
                    <th><span class="must">*</span>优惠券名称：</th>
                    <td>
                        <input type="text" name="coupon.name" value="${(coupon.name)!''}" regex="/^.{1,25}$/" regexMsg="优惠券名字不得超过25位!" notNull="true" maxlength="25" class="input-txt t-200" placeholder="输入优惠券名称">
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>生成类型：</th>
                    <td>
                        <#list couponCreateTypes?if_exists as type>
                            <span <#if type_index != 0>class="ml-30"</#if>>
                                <input type="radio" class="chk" name="coupon.createType" value="${type}" <#if coupon.createType?? && type==coupon.createType> checked="checked"<#else><#if coupon.id?exists && coupon.id != 0 > disabled</#if></#if> />${type.nameValue}
                            </span>
                        </#list>
                    </td>
                </tr>
                <tr id="createNumTr" <#if !coupon.createType?? || (coupon.createType?? && coupon.createType!=createTypeBatch)> style="display:none;"</#if>>
                    <th><span class="must">*</span>生成数量：</th>
                    <td>
                        <input type="text" id="coupon_createNum" name="coupon.createNum" value="${coupon.createNum!}" maxlength="5" 
                            <#if coupon.id?exists && coupon.id != 0>readOnly class="input-txt input-txt-disabled t-200"<#else>class="input-txt t-200"</#if> 
                            <#if coupon.createType?? && coupon.createType==createTypeBatch> notNull="true" regex="/^[1-9]*[0-9][0-9]*$/" dataType="integer" maxValue=40000</#if> 
                            regexMsg="只能输入大于等于0的整数" placeholder="输入生成优惠券数量" />
                        <span class="onTips">注：批量生成优惠券的数量，每次新增最多4万张</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>批次号：</th>
                    <td>
                        <input id="coupon_batchCode" type="text" name="coupon.batchCode" value="${(coupon.batchCode)!''}" 
                            <#if coupon.id?exists && coupon.id != 0 >readOnly class="input-txt input-txt-disabled t-200"<#else>class="input-txt t-200"</#if> 
                            notNull="true" regex="/^[0-9]{4}$/" regexMsg="请输入4位有效数字!" maxlength="4"  placeholder="输入批次号" />
                        <span class="onTips">注：4位数字，建议年份+编号，如1601</span>
                    </td>
                </tr>
                <tr id="couponCodeTr" <#if coupon.createType?? && coupon.createType==createTypeBatch> style="display:none;"</#if>>
                    <th><span class="must">*</span>优惠券号：</th>
                    <td>
                        <input type="text" id="coupon_couponCode" name="coupon.couponCode" readOnly value="${(coupon.couponCode)!''}"  class="input-txt input-txt-disabled t-200" >
                        <span class="onTips">注：由批次号加8位随机数自动生成</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>优惠类型：</th>
                    <td>
                        <#list couponTypes?if_exists as type>
                            <span <#if type_index != 0>class="ml-30"</#if>>
                                <input type="radio" class="chk" name="coupon.couponType" value="${type}" <#if coupon.couponType?? && type==coupon.couponType> checked="checked"</#if> />${type.nameValue}
                            </span>
                        </#list>
                    </td>
                </tr>
                <tr id="discountTr" <#if coupon.couponType?? && coupon.couponType!=couponDiscount> style="display:none;"</#if>>
                    <th><span class="must">*</span>折扣比例：</th>
                    <td>
                        <input type="text" id="coupon_discount" name="coupon.discount" value="${(coupon.discount)!'1'}" class="input-txt t-200" 
                            <#if !coupon.couponType?? || (coupon.couponType?? && coupon.couponType==couponDiscount)> notNull="true"</#if>
                            maxlength="4" placeholder="输入折扣比例" />
                        <span class="onTips">注：输入0.95，表示95折优惠</span>
                    </td>
                </tr>
                <tr id="amountTr" <#if !coupon.couponType?? || (coupon.couponType?? && coupon.couponType!=couponAmount)> style="display:none;"</#if>>
                    <th><span class="must">*</span>优惠金额：</th>
                    <td>
                        <input type="text" id="coupon_amount" name="coupon.amount" value="${coupon.amount!}" class="input-txt t-200" 
                            <#if coupon.couponType?? && coupon.couponType==couponAmount> notNull="true"</#if>
                            placeholder="输入优惠金额" />
                        <span class="onTips">注：优惠金额单位：（元）</span>
                    </td>
                </tr>
                <tr>
                    <th><span class="must">*</span>使用类型：</th>
                    <td>
                        <#list couponUseTypes?if_exists as type>
                            <span <#if type_index != 0>class="ml-30"</#if>><input type="radio" class="chk" name="coupon.couponUseType" value="${type}" <#if coupon.couponUseType?? && type==coupon.couponUseType> checked="checked"</#if> />${type.nameValue}</span>
                        </#list>
                    </td>
                </tr>
                <tr>
                    <th>推广人姓名：</th>
                    <td>
                        <input type="text" name="coupon.ownerName" value="${(coupon.ownerName)!''}" maxlength="30" class="input-txt t-200" placeholder="输入推广人姓名">
                    </td>
                </tr>
                <tr>
                    <th>使用开始时间：</th>
                    <td>
                        <input type="text"  class="input-txt fn-left" style="width:200px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#btwp').click();" name="coupon.beginTime" id="beginTime" <#if coupon?? && coupon.beginTime?exists>value="${coupon.beginTime?string("yyyy-MM-dd")!''}"</#if>>
                        <a hidefocus="true" href="javascript:;" id="btwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'beginTime',isShowClear:true,readOnly:true,isShowWeek:true,maxDate:'#F{$dp.$D(\'endTime\')}'})"></a>
                    </td>
                </tr>
                <tr>
                    <th>使用截至时间：</th>
                    <td>
                        <input type="text" class="input-txt fn-left" style="width:200px;" readonly onkeydown="if(window.event.keyCode == 8){return false;}" onclick="$('#etwp').click();" name="coupon.endTime" id="endTime" <#if coupon?? && coupon.endTime?exists>value="${coupon.endTime?string("yyyy-MM-dd")!''}"</#if>>
                        <a hidefocus="true" href="javascript:;" id="etwp" class="i-date fn-left mt-5 ml-10" onclick="WdatePicker({el:'endTime',isShowClear:true,readOnly:true,isShowWeek:true,minDate:'#F{$dp.$D(\'beginTime\')}'})"></a>
                    </td>
                </tr>
                <tr>
                    <th valign="top" class="pt-10">绑定课程：</th>
                    <td valign="top" class="pt-10">
                        <a href="javascript:;" class="abtn abtn-green fn-btn" id="bindCourse-btn" dataValue="${coupon.id!}" isFirst="true"><img src="${DOMAIN_CMS}/images/icon/add2.png">绑定课程</a>
                        <span class="onTips">注：若未绑定课程，则所有课程都可以使用此优惠券</span>
                        <p class="pt-5">
                            <input type="hidden" id="bindCourse_value" name="idsStr" value="${coupon.courseIdsStr!}"/>
                            <textarea class="text-area t-350" id="showCourse" readonly>${coupon.tempStr!}</textarea>
                        </p>
                    </td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td colspan="2">
                        <a href="javascript:;" id="saveBtn" class="abtn abtn-blue">保存</a>
                        <#--
                        <#if canDelCoupon && coupon.id?exists && coupon.id != 0 && coupon.createType==createTypeBatch>
                        <a href="javascript:;" id="batchDelBtn" class="abtn abtn-blue ml-10">批量删除</a>
                        </#if>
                        -->
                        <#if canCancelCoupon && coupon.id?exists && coupon.id != 0 && coupon.createType==createTypeBatch>
                        <a href="javascript:;" id="batchCancelBtn" class="abtn abtn-blue ml-10">批量注销</a>
                        </#if>
                        <a href="${DOMAIN_CMS}/account/couponManage.htm" class="abtn abtn-green ml-10" >返回</a>
                    </td>
                </tr>
                <#--
                <#if canDelCoupon && coupon.id?exists && coupon.id != 0 && coupon.createType==createTypeBatch>
                <tr>
                    <th>&nbsp;</th>
                    <td colspan="2">
                        <span class="c-red">注：批量删除，对此优惠券的所有优惠号做批量删除处理！</span>
                    </td>
                </tr>
                </#if>
                -->
                <#if canCancelCoupon && coupon.id?exists && coupon.id != 0 && coupon.createType==createTypeBatch>
                <tr>
                    <th>&nbsp;</th>
                    <td colspan="2">
                        <span class="c-red">注：批量注销，对此优惠券的所有优惠号做批量注处理！</span>
                    </td>
                </tr>
                </#if>
            </table>
        </form>
    </div>
</div>
<@footer />

<div class="popUp-layer" id="bindCourseDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>提示</span></p>
    <div class="wrap fn-clear" style="width:610px;">
        <div class="org-wrap">
            <div class="org-tt" style="height:27px;">
                <span>课程类型：</span>
                <select class="input-sel w-100" id="bindCourseDiv_contentType">
                    <option value="COURSE">基地课程</option>
                    <option value="CONCERT">音乐会</option>
                    <option value="VOD">点播视频</option>
                </select>
            </div>
            <ul class="org-list org-list-left" id="bindCourseDiv_left">
            </ul>
        </div>
        <div class="org-opt">
            <a href="javascript:;" class="abtn abtn-blue" id="bindCourseDiv_addAll">全部添加</a>
            <a href="javascript:;" class="abtn abtn-blue" id="bindCourseDiv_removeAll">全部移除</a>
        </div>
        <div class="org-wrap">
            <div class="org-tt" style="height:27px;">选中的课程：</div>
            <form id="bindCourseDiv_form" action="" method="post">
                <ul class="org-list org-list-right" id="bindCourseDiv_right">
                </ul>
            </form>
        </div>
        <div class="fn-clear fn-right mt-10 mr-30 c-red">注：C基地课程，Y音乐会，S点播视频</div>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue submit" href="javascript:;" id="bindCourseDiv_submit"><span>确定</span></a>
        <a class="abtn abtn-green reset ml-5" href="javascript:;"><span>取消</span></a>
    </p>
</div>

<#include "/pages/jsinclude/account/coupon/editCouponJs.ftl"/>
<script>
    $(function(){
        editCoupon.init();
    });
</script>
</body>
</html>