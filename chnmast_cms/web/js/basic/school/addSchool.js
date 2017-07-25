(function($, window, document){
	var pub = {};
	var pro = null;
	var city = null;
	var cou = null;
	window.AddSchool = pub;
    pub.init = function(subjectCode) {
    	var regionCode = $("#regionCode").val();
		if(regionCode){
			pro = regionCode.substr(0,2);
			city = regionCode.substr(0,4)
			cou = regionCode;
		}
		initData();
        bindEvent();
    }
    function initData(){
    	//加载省列表
		var param1 = {
			url:"system-listRegions.htm",
			data:{"regionType":"province"},
			handler:function(result){
				$("#provinceSel").empty();
				$("#provinceSel").append("<option value=''>请选择所在省</option>" );
    			if(result){
    				for(var i=0;i<result.length;i++) {
    					if(pro != null && pro == result[i].regionCode){
    						$("#provinceSel").append("<option value='" + result[i].regionCode + "' selected>" + result[i].regionName + "</option>" );
    					}else{
    						$("#provinceSel").append("<option value='" + result[i].regionCode + "'>" + result[i].regionName + "</option>" );
    					}
    				}
    			}
				//加载对应的city
				loadCities();
			}
		}
		Sender.getJsonByUrl(param1);
    }
    //绑定页面事件方法
    function bindEvent() {
    	$("#provinceSel").bind("change",loadCities);
    	$("#citySel").bind("change",loadCounty);
    	$("#countySel").bind("change",function(){
			var region = $("#countySel").val();
			if(region != null && region != ''){
				$("#regionCode").val(region);
			}
    	});
    	$("#saveBtn").bind("click", addSchool);
    }
    
    //加载市列表
	function loadCities(){
		var province = $("#provinceSel").val();
		if(province == null || province == ''){
			return;
		}
		var param = {
			url:"system-listRegions.htm",
			data:{"regionType":"city", "regionCode":province},
			handler:function(result){
				$("#citySel").empty();
				$("#citySel").append("<option value=''>请选择所在市</option>" );
				$("#countySel").empty();
				$("#countySel").append("<option value=''>请选择所在区/县</option>" );
				$("#regionCode").val("");
				if(result != null && result != ''){
    				for(var i=0;i<result.length;i++) {
    					if(city != null && city == result[i].regionCode){
    						$("#citySel").append("<option value='" + result[i].regionCode + "' selected>" + result[i].regionName + "</option>" );
    					}else{
    						$("#citySel").append("<option value='" + result[i].regionCode + "'>" + result[i].regionName + "</option>" );
    					}
    				}
    			}else{
    				$("#regionCode").val(province + "0000");
    			}
				loadCounty();
			}
		}
		Sender.getJsonByUrl(param);
	}
	//加载对应的区县列表
	function loadCounty(){
		var city = $("#citySel").val();
		if(city == null || city == ''){
			return;
		}
		var param = {
			url:"system-listRegions.htm",
			data:{regionType:"county","regionCode":city},
			handler:function(result){
				$("#countySel").empty();
				$("#countySel").append("<option value=''>请选择所在区/县</option>" );
				$("#regionCode").val("");
				if(result != null && result != ''){
    				for(var i=0;i<result.length;i++) {
    					if(cou != null && cou == result[i].regionCode){
    						$("#countySel").append("<option value='" + result[i].regionCode + "' selected>" + result[i].regionName + "</option>" );
    						$("#regionCode").val(result[i].regionCode);
    					}else{
    						$("#countySel").append("<option value='" + result[i].regionCode + "'>" + result[i].regionName + "</option>" );
    					}
    				}
    			}else{
    				$("#regionCode").val(city + "00");
    			}
			}
		}
		Sender.getJsonByUrl(param);
	}
	
	
    //保存
    function addSchool(){
    	FieldMsg.clearFieldError("#schoolForm"); //清除各字段的报错提示
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#schoolForm")) return;
       	
       	var params ={
        		"url":"/basic/schoolAdd.htm",
        		"data":$("#schoolForm").serialize(),
        		"fn":function(result) {
        			if(result != '') {
        				Tips.showErrorMsg({"info":result});
						return ;
					}else{
						Tips.showSuccessMsg({"info":"新增学校成功!"});
//						location.href = Domain.cms_path + "/basic/schoolManage.htm";
					}
		        }
            };
            /**ajax提交数据*/
            Sender.ajax(params);
//		$('#schoolForm').submit();
    }
    
    pub.showResult = function(msg, isSuccess, id) {
		if(isSuccess){
			Tips.showSuccessMsg({"info":"添加学校成功!"});
			setTimeout(function(){
				window.location.href = Domain.cms_path + '/basic/schoolManage.htm';
			}, 2000);
		}else{
			Tips.showErrorMsg({"info" : msg});
		}
    }
})(jQuery, window, document);