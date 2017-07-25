<div class="select-analog fn-left">
    <input type="hidden" id="collegeId" name="collegeId" value="${collegeId!}" />
    <input type="text" class="input-txt" id="collegeName" value="${collegeName!}" placeholder="请选择高校" readOnly/>
    <a href="javascript:;" id="openSelectCollegeDiv" class="open"></a>
</div>
<div class="popUp-layer" id="selectCollegeDiv" style="display:none;">
    <p class="tt"><a href="javascript:;" class="close" title="关闭"></a><span>高校风采展示-关联高校</span></p>
    <div class="wrap">
        <div class="org-search">
            <div class="org-search-tt">
                <span class="ml-10">高校名称：</span>
                <input type="text" class="input-txt t-200" id="selectCollegeDiv_name">
                <a href="javascript:;" class="abtn abtn-blue ml-10" id="selectCollegeDiv_search">查询</a>
            </div>
            <div class="org-search-inner" style="height:260px;" id="selectCollegeDiv_content">
            </div>
        </div>
    </div>
    <p class="dd">
        <a class="abtn abtn-blue" style="margin-right: 370px;" href="javascript:;" id="selectCollegeDiv_clear"><span>清除</span></a>
        <a class="abtn abtn-blue" href="javascript:;" id="selectCollegeDiv_submit"><span>确定</span></a>
        <a class="abtn abtn-green close ml-5" href="javascript:;"><span>取消</span></a>
    </p>
</div>
<script>
$(function(){
    // 打开弹出层
    $("#openSelectCollegeDiv").bind("click", function(){
        $("#selectCollegeDiv_name").val("");
        Box.showDiv({
            "div" : "#selectCollegeDiv",
            "closeObject" : "#selectCollegeDiv .close"
        });
        $("#selectCollegeDiv_search").click();
    });

    // 查询
    $("#selectCollegeDiv_search").bind("click", function(){
        var selectName = $("#selectCollegeDiv_name").val();
        var selectId = $("#collegeId").val();
        if(selectId == undefined){
            selectId = 0;
        }
        var param = {
            "div" : "#selectCollegeDiv_content",
            "url" : Domain.cms_path + "/course/selectCollege.htm?collegeName="+encodeURI(selectName)+"&collegeId="+selectId
        };
        Sender.load(param);
    });
    
    // 回车搜索
    $("#selectCollegeDiv_name").keyup(function(){
        if(event.keyCode == 13){
            $("#selectCollegeDiv_search").click();
        }
    });
    
    //清除条件
    $('#selectCollegeDiv_clear').bind("click", function(){
        $('#collegeId').val('');
        $('#collegeName').val('');
        $("#selectCollegeDiv .close").click();
    });
    
    
    // 选择
    $("#selectCollegeDiv_submit").bind("click", function(){
        var obj=$("#selectCollegeDiv_content").find('input:radio[name="selectCollegeDiv_radio"]:checked');
        if(obj.length == 0){
            Tips.showErrorMsg({
                "info":"请选择一个高校！", 
                "callFn":function(){
                    Box.showDiv({
                        "div" : "#selectCollegeDiv",
                        "closeObject" : "#selectCollegeDiv .close"
                    });
                }
            });
        }else{
            var collegeId = obj.val();
            var collegeName = obj.attr("dataValue");
            $("#collegeId").val(collegeId);
            $("#collegeName").val(collegeName);
            $("#selectCollegeDiv .close").click();
        }
    });
});
</script>