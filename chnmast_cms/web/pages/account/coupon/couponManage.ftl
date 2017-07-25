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
        <p class="crumbs">当前位置：业务管理&nbsp;&gt;&nbsp;<span>优惠券管理</span></p>
        
        <!--搜索显示内容start-->
        <form id="couponSearchForm" action="" method="post" >
        <table class="form-table validateForm mt-10">
            <tr>
                <th>名称：</th>
                <td>
                    <input type="text" name="coupon.name" value="${(coupon.name)!''}" placeholder="输入名称" maxlength="50" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>批次号：</th>
                <td>
                    <input type="text" name="coupon.batchCode" value="${(coupon.batchCode)!''}" placeholder="输入批次号" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>优惠券号：</th>
                <td>
                    <input type="text" name="coupon.couponCode" value="${(coupon.couponCode)!''}" placeholder="输入优惠券号" maxlength="20" class="input-txt">
                </td>
            </tr>
            <tr>
                <th>生成类型：</th>
                <td>
                    <select class="input-sel w-200" name="coupon.createType">
                        <option value="">全部</option>
                        <#list couponCreateTypes?if_exists as type>
                            <option value="${type}" <#if coupon?? && coupon.createType?? && coupon.createType==type>selected</#if> >${type.nameValue}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>优惠类型：</th>
                <td>
                    <select class="input-sel w-200" name="coupon.couponType">
                        <option value="">全部</option>
                        <#list couponTypes?if_exists as type>
                            <option value="${type}" <#if coupon?? && coupon.couponType?? && coupon.couponType==type>selected</#if> >${type.nameValue}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>使用类型：</th>
                <td>
                    <select class="input-sel w-200" name="coupon.couponUseType">
                        <option value="">全部</option>
                        <option value="USE_MANY_TIMES" <#if coupon?? && coupon.couponUseType?? && coupon.couponUseType=="USE_MANY_TIMES">selected</#if> >多次</option>
                        <option value="USE_ONLY_ONE_TIME" <#if coupon?? && coupon.couponUseType?? && coupon.couponUseType=="USE_ONLY_ONE_TIME">selected</#if> >一次</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>是否注销：</th>
                <td>
                    <select class="input-sel w-200" name="codeIsCancel">
                        <#list yesNoTypes?if_exists as type>
                            <option value="${type}" <#if codeIsCancel?default('NO')==type>selected</#if> >${type.nameValue}</option>
                        </#list>
                    </select>
                </td>
            </tr>
            <tr>
                <th>&nbsp;</th>
                <td colspan="3">
                    <a href="javascript:;" id="searchButton" class="abtn abtn-blue">搜索</a>
                    <#if canAddCoupon>
                    <a href="${DOMAIN_CMS}/account/addCoupon.htm" class="abtn abtn-green ml-10"><img src="${DOMAIN_CMS}/images/icon/add2.png">新增</a>
                    </#if>
                    <#if canExportCoupon>
                    <a href="javascript:;" id="exportCoupon" class="abtn abtn-green ml-10"><img src="${DOMAIN_CMS}/images/icon/export.png">导出</a>
                    </#if>
                </td>
            </tr>
        </table>
        </form>
        <!--搜索显示内容end-->
        <!--结果显示内容start-->
        <div class="record-wrap mt-10">
        <@doublePage2>
            <table class="public-table">
                <tr>
                    <th width="45" class="t-center">序号</th>
                    <th class="t-center">名称</th>
                    <th class="t-center">批次号</th>
                    <th class="t-center">优惠券号</th>
                    <th class="t-center">生成类型</th>
                    <th class="t-center">优惠类型</th>
                    <th class="t-center">使用类型</th>
                    <th class="t-center">注销状态</th>
                    <th class="t-center">有效截止时间</th>
                    <th class="t-center">创建时间</th>
                    <th class="t-center">操作</th>
                </tr>
                <#list coupons?if_exists as item>
                <tr>
                    <td class="t-center">${item_index+1}</td>
                    <td class="t-center"><span title="${item.name!}"><@cutOff cutStr="${item.name!}" cutLen="25" /></span></td>
                    <td class="t-center">${(item.batchCode)!''}</td>
                    <td class="t-center">${(item.couponCode)!''}</td>
                    <td class="t-center">${item.createType.nameValue}</td>
                    <td class="t-center">${item.couponType.nameValue}（<span class="c-red"><#if item.couponType==couponDiscount>${(item.discount)!''}<#elseif item.couponType==couponAmount>${item.amount!}元</#if></span>）</td>
                    <td class="t-center">${item.couponUseType.nameValue}</td>
                    <td class="t-center"><span class="c-red">${item.isCancel.nameValue}</span></td>
                    <td class="t-center"><#if item.endTime??>${item.endTime?string('yyyy-MM-dd')!}</#if></td>
                    <td class="t-center"><#if item.createTime??>${item.createTime?string('yyyy-MM-dd HH:mm:ss')!}</#if></td>
                    <td class="t-center">
                        <a href="${DOMAIN_MOBILE}/yhquan/${(item.couponCode)!''}" target="_blank">查看</a>
                        <#if editAddCoupon>
                        <a href="${DOMAIN_CMS}/account/editCoupon.htm?id=${item.id!}" class="coupon-edit" >修改</a>
                        </#if>
                        <#--
                        <#if canDelCoupon>
                        <a href="javascript:;" class="coupon-del" dataValue="${item.couponCode!}">删除</a>
                        </#if>
                        -->
                        <#if canCancelCoupon && item.isCancel=='NO'>
                        <a href="javascript:;" class="coupon-cancel" dataValue="${item.couponCode!}">注销</a>
                        </#if>
                    </td>
                </tr>
                </#list>
            </table>
        </@doublePage2>
        </div>
        <!--结果显示内容end-->
    </div>
</div>
<@footer />

<#include "/pages/jsinclude/account/coupon/couponManageJs.ftl"/>
<script>
    $(function(){
        couponManage.init();
    });
</script>
</body>
</html>