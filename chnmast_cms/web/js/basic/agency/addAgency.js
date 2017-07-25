(function($, window, document){
	var pub = {};
	var pro = null;
	var city = null;
	var cou = null;
	window.AddAgency = pub;
    pub.init = function(subjectCode) {
    	var regionCode = $("#regionCode").val();
		if(regionCode){
			pro = regionCode.substr(0,2);
			city = regionCode.substr(0,4)
			cou = regionCode;
		}
		initData();
        bindEvent();
        $("#levelTr").hide();
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
    	$("#nameInput").bind("blur", createUsername);
    	$("#provinceSel").bind("change",loadCities);
    	$("#citySel").bind("change",loadCounty);
    	$("#countySel").bind("change",function(){
			var region = $("#countySel").val();
			if(region != null && region != ''){
				$("#regionCode").val(region);
			}
    	});
    	$("#saveBtn").bind("click", addAgency);
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
	
	//获取基地名称简拼作为用户名
	function createUsername(){
		if($("#nameInput").val() != '' && $("#usernameInput").val() == ''){
			var hz = $("#nameInput").val();
			var params ={
		    		"url":"/basic/getPinyinFirst.htm",
		    		"data":{agencyName : hz},
		    		"fn":function(result) {
		    			if(result){
		    				$("#usernameInput").val(result);
		    			}
			        }
		        };
		        /**ajax提交数据*/
		        Sender.ajax(params);
		}
	}
	
    //保存
    function addAgency(){
    	FieldMsg.clearFieldError("#agencyForm"); //清除各字段的报错提示
    	//整个表单所有数据验证   错误信息
       	if(!Verify.checkAllVerify("#agencyForm")) return;
       	if($("#regionCode").val() == null || $("#regionCode").val() == ''){
       		Tips.showErrorMsg({"info":"所在地区必须填写！"});
       		return;
       	}
       	var elemVal = $("#usernameInput").val();
       	if(Validator.isNotBlank(elemVal) && (StringUtil.getLength2(elemVal) > 20||StringUtil.getLength2(elemVal) < 4)){
       		Tips.showErrorMsg({"info":"基地管理员 长度要在4~20范围内！"});
			return;
		}
       	
       	if(Validator.test(/[&;%\'\"<>]/,$("#passwordInput").val())){
       		FieldMsg.addFieldError("#passwordInput", "密码不能包含特殊字符", false);
       		return;
       	}
       	if(!Verify.checkOverLen3("#passwordInput","密码", 6,20))return;
    	if($("#repassword").val() != $("#passwordInput").val()){
        	FieldMsg.addFieldError("#repassword", "两次输入的密码不一致", false);
        	return;
    	}
		$('#agencyForm').submit();
    }
    
    pub.showResult = function(msg, isSuccess, id) {
    	if(isSuccess){
    		Tips.showConfirmWin1({"info" : "操作成功，是否去维护培训基地的展示图片？", "title":"操作成功", "callFn" : function(){
    			window.location.href = Domain.cms_path + '/basic/agencyPicture.htm?agencyId=' + id;
        	}, "cancelFn" : function(){
        		window.location.href = Domain.cms_path + '/basic/agencyManage.htm';
        	}});
    	}else{
    		if(msg == 'NO_PRIVACY'){
    			Tips.showSuccessMsg({"info":"添加培训基地成功!"});
    			setTimeout(function(){
    				window.location.href = Domain.cms_path + '/basic/agencyManage.htm';
    			}, 2000);
    		}else{
    			Tips.showErrorMsg({"info" : msg});
    		}
    	}
    }
})(jQuery, window, document);