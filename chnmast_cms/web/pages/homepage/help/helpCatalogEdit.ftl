<#if catalog?exists>
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span><#if catalog.id! == 0>新增<#else>修改</#if>帮助分类</span></p>
    <div class="wrap">
        <form id="catalogForm" metnod="post">
            <input type="hidden" name="catalogLevel" value="${catalogLevel!}" />
            <input type="hidden" name="catalog.id" value="${catalog.id!}" />
            <table class="form-table validateForm">
                <tr>
                    <th style="width:100px;"><span class="must">*</span>标题：</th>
                    <td>
                        <input type="text" class="input-txt" placeholder="输入标题" id="catalogName" value="${catalog.catalogName!}" name="catalog.catalogName" notNull="true" maxLength="50"/>
                    </td>
                </tr>
                <tr>
                    <th style="width:100px;"><span class="must">*</span>是否显示：</th>
                    <td>
                        <#list yesNo?if_exists as t>
                        <label>
                        <input type="radio" <#if (catalog.isShow?exists && catalog.isShow==t)||!catalog.isShow?exists && t=="YES" >checked</#if> class="chk" name="catalog.isShow" value="${t}">${t.nameValue!} 
                        </label>
                        </#list>
                    </td>
                </tr>
                <tr>
                    <th style="width:100px;">显示顺序：</th>
                    <td>
                        <input type="text" class="input-txt" style="width:150px" id="displayOrder" value="${catalog.displayOrder!}" name="catalog.displayOrder" dataType="integer" integerLength="5">
                    </td>
                </tr>
                <#if catalogLevel == "seconde">
                    <tr>
                        <th style="width:100px;"><span class="must">*</span>所属父类：</th>
                        <td>
                            <select class="input-sel input-txt-error" style="width:120px;" id="parentId" name="catalog.parentId" notNull="true">
                                <option value="">--请选择--</option>
                                <#list catalogs?if_exists as item>
                                    <option value="${item.id!}" <#if catalog.parentId?exists && catalog.parentId! == item.id!>selected</#if>>${item.catalogName!}</option>
                                </#list>
                            </select>
                        </td>
                    </tr>
                </#if>
            </table>
        </form> 
        
    </div>
    <div style="background:#fff;padding-left:40px;height:30px" id="submit_tips"></div>
    <p class="dd">
        <a class="abtn abtn-blue submit" href="javascript:;" id="catalog_edit_sub"><span>确定</span></a>
        <a class="abtn abtn-green reset ml-5" href="javascript:;"><span>取消</span></a>
    </p>
<#else>
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>没有找到帮助分类</span></p>
    <div class="wrap">
    <div class="record-wrap mt-10">
        <div class="public-nodata">查询不到帮助分类，请重试！</div>
    </div>
    </div>
</#if>
<script>
    $(function(){
        window.HelpCatalogEdit.init();
    });
</script>