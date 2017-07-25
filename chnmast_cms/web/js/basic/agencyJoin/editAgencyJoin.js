(function($, window, document){
	var pub = {};
	window.editAgencyJoin = pub;
    pub.init = function() {
    	var regionCode = $("#regionCode").val();
		if(regionCode){
			pro = regionCode.substr(0,2);
			city = regionCode.substr(0,4)
			cou = regionCode;
		}
		loadProvinceSel();
		
        bindEvent();
    }
    
    //绑定页面事件方法
    function bindEvent() {
		$("#provinceSel").bind("change",loadCities);
    	$("#citySel").bind("change",loadCounty);
    	$("#countySel").bind("change",function(){
    		$("#regionCode").val("");
			var region = $("#countySel").val();
			if(region != null && region != ''){
				$("#regionCode").val(region);
			}
    	});
		
		$("#saveBtn").bind("click", addAgencyJoin);
    }
    
    function addAgencyJoin(){
    	FieldMsg.clearFieldError("#agencyJoin_form"); //清除各字段的报错提示
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#agencyJoin_form")) return;
       	if($("#regionCode").val() == null || $("#regionCode").val() == ''){
       		Tips.showErrorMsg({"info":"所在地区必须填写！"});
       		return;
       	}
       	
       	var id = $("#agencyJoinId").val();
       	Sender.submitAjax({
			"frm" : "#agencyJoin_form",
			"dataType" : "json",
			"fn" : function(result){
				FieldMsg.drawMessages(result,function(){
					window.location.href = Domain.cms_path + "/basic/viewAgencyJoin.htm?id=" + id;
                });
			}
		});
    }
    
    //加载省列表
	function loadProvinceSel() {
		var param = {
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
		Sender.getJsonByUrl(param);
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
	
})(jQuery, window, document);