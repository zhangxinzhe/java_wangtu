(function($, window, document){
	var pub = {};
	window.addCourse = pub;
    pub.init = function(params) {
    	// 选择讲师助教
        $(".selectTea").bind("click", openSelectDiv);
        // 保存信息
        $("#saveBtn").bind("click", saveCourse);
        //审核通过
        $("#passBtn").bind("click", auditPass);
        //审核不通过
        $("#noPassBtn").bind("click", auditNoPass);
        //取消审核
        $("#cancelBtn").bind("click", auditCancel);
        
        $("#TimeLi").bind("click",function(){
    		var dataValue = $(this).attr("dataValue");
    		if(dataValue=="" || dataValue==0){
    			$("#courseLi").addClass('current').siblings('li').removeClass('current');
    			Tips.showErrorMsg({"info":"课程信息保存成功，才能维护课次和价格！"});
    		}else{
    			window.location.href = Domain.cms_path + "/course/courseTimeManage.htm?id="+dataValue;
    		}
    	});
        
        $("input[name='course.isBuyAll']").bind("click", function(){
        	var value = $(this).attr("value");
        	if("YES" == value) {
        		$("#isBuyAllMsg").show();
        	}else{
        		$("#isBuyAllMsg").hide();
        	}
        });
    }

    var isTrue_course = true;//防止重复提交
    function saveCourse(){
    	if(!isTrue_course){
    		return;
    	}
    	isTrue_course = false;

    	if(!valAllInfo()){
    		isTrue_course = true;
    		return;
    	}
    	var courseId = $("#courseId").val();
		$("#course_form").attr("action", Domain.cms_path + "/course/addCourse.htm");
		Sender.submitAjax({
			"frm" : "#course_form",
			"dataType" : "json",
			"fn" : function(result){
				isTrue_course = true;
				if(result.isSuccess){
					$("#uploadTempFile").val("");
					if(courseId=="" || courseId==0) {//新增保存
						if (result.promptMsg.indexOf("SUCCESS_") > -1){
							Tips.showSuccessMsg({
								"info" : "课程新增保存成功！",
								"callFn" : function(){
									window.location.href = Domain.cms_path + "/course/addCourse.htm?id="+result.promptMsg.substring(8);
								}
							});
						}
					}else{
						Tips.showSuccessMsg({"info" : "课程修改保存成功！"});
					}
				}else{
					Tips.showErrorMsg({"info":result.errorMsg});
				}
			}
		});
    }
    //审核通过
    function auditPass(){
    	var courseId = $("#courseId").val();
    	var wxbId = $("#wxbId").val();
    	$.ajax({
    		"url" :  Domain.cms_path +"/course/courseAudit.htm",
			"data" : {
				"auditStu" : 2,
				"wxbId" : wxbId,
				"id" : courseId
			},
			'dataType' : 'json',
			"success" : function(result) {
				FieldMsg.drawMessages(result, function(){
					window.location.reload(); 
                });
			}
    	});
    }
    //审核不通过
    function auditNoPass(){
    	$("#popUp2_tips").html('');
    	var courseId = $("#courseId").val();
    	Sender.openDiv({
            "div" : "#popUp2",
            "closeObject" : "#popUp2 a.close",
        });
    	
    	$("#popUp2_audit").bind('click',function(){
    		var auditMsg = $('#popUp2_auditMsg').val();
        	if(auditMsg == null || auditMsg == ""){
        		$("#popUp2_tips").html('请输入审核信息');
        		$('#popUp2_auditMsg').focus();
        		return;
        	}
        	
        	$.ajax({
        		"url" :  Domain.cms_path +"/course/courseAudit.htm",
    			"data" : {
    				"auditStu" : 3,
    				"auditMsg" : auditMsg,
    				"id" : courseId
    			},
    			'dataType' : 'json',
    			"success" : function(result) {
//    				if(msg.errorMsg != null){
//    					Tips.showErrorMsg({"info" : msg.errorMsg});
//    					return;
//    				} if(msg.promptMsg != null){
//    					Tips.showErrorMsg({"info" : msg.promptMsg});
//    					window.location.reload(); 
//    					return;
//    				}
    				FieldMsg.drawMessages(result, function(){
    					window.location.reload(); 
                    });
    			}
        	});
    	});
    }
    //取消审核
    function auditCancel(){
    	var courseId = $("#courseId").val();
    	$.ajax({
    		"url" :  Domain.cms_path +"/course/courseAudit.htm",
			"data" : {
				"auditStu" : 1,
				"id" : courseId
			},
			'dataType' : 'json',
			"success" : function(result) {
//				if(msg.errorMsg != null){
//					Tips.showErrorMsg({"info" : msg.errorMsg});
//					return;
//				} if(msg.promptMsg != null){
//					Tips.showErrorMsg({"info" : msg.promptMsg});
//					window.location.reload(); 
//					return;
//				}
				FieldMsg.drawMessages(result, function(){
					window.location.reload(); 
                });
			}
    	});
    }
    /*********************** 验证start *********************/
    function valAllInfo() {
    	FieldMsg.clearFieldError("#course_form"); //清除各字段的报错提示
    	if(!Verify.checkAllVerify("#course_form")){ //验证所有字段
    		var position = $(".form-table").offset();
	        $(document).scrollTop(position.top-200);
    		return false; 
    	}
    	var obj=$(".form-table").find('input:radio[name="course.courseType"]:checked');
    	if(obj.length == 0){
    		var position = $($(".form-table").find('input:radio[name="course.courseType"]')[0]).offset();
	        $(document).scrollTop(position.top-200);
	        Tips.showErrorMsg({"info":"请选择课程类型！"});
    		return false;
    	}
    	if(!Verify.checkOverLen("#introduction", "课程介绍", 800, true)){
    		var position = $("#introduction").offset();
	        $(document).scrollTop(position.top-200);
    		return false;
    	}
    	if(!Verify.checkOverLen("#remark", "备注内容", 800, true)){
    		return false;
    	}
    	
    	return true;
    }
    
    /*********************** 验证end *********************/
    
    
    /** 打开选择讲师、助教弹出层 **/
    function openSelectDiv() {
    	var flag = $(this).attr("flag");
    	var id;
    	if(flag == 1){
    		id = $("#teaId").val();
    	}else{
    		id = $("#assId").val();
    	}
    	var url = Domain.cms_path + "/course/selectTeaOrAss.htm?flag="+flag
    	if(id != ""){
    		url = url + "&id="+id;
    	}
    	Sender.openDiv({
    		"div" : "#selectTeaDiv",
    		"closeObject" : "#selectTeaDiv .close",
    		"url" : url
    	});
    }
    
})(jQuery, window, document);

/** 基地课程课次维护 **/
(function($, window, document){
	var pub = {};
	window.addCourseTime = pub;
	var isTrue = false;//防止重复提交
    pub.init = function(params) {
    	_initTimes();
    }
    
    /*********************** 初始化课次start *********************/
    _initTimes = function(){
    	$("#time_add").bind("click", addTime);
    	$("#time_tbody").on("click", "a.del-time", delTime);
    	$("#time_tbody").on("click", "a.edit-time", editTime);
    	$("#time_tbody").on("click", "a.save-time", saveTime);
    	
    	$("#time_tbody").on("click", "a.add-price", addPrice);
    	$("#time_tbody").on("click", "a.del-price", delPrice);
    	$("#time_tbody").on("click", "a.edit-price", editPrice);
    	$("#time_tbody").on("click", "a.save-price", savePrice);
    	
    	$("#cost_tbody").on("click", "#editCostPrice", editCostPrice);
    	$("#cost_tbody").on("click", "#saveCostPrice", saveCostPrice);
    	
    	$(".price-table").on("keyup", "input[flag='nowPrice']", function(){$(this).val($(this).val().replace(/[^\-?\d.]/g,''))});
    	$(".price-table").on("keyup", "input[flag='priceNum']", function(){$(this).val($(this).val().replace(/[^0-9]/g,''))});
    }
    
    // ============================= 课次维护 =====================================
    /** 添加课次 **/
    function addTime() {
    	if(!checkCanOperate('time','add')){
    		return;
    	}
    	
    	var bhHtml = pub.buildHourSelectHtml('beginHours');
    	var bmHtml = pub.buildMinuteSelectHtml('beginMinutes');
    	var ehHtml = pub.buildHourSelectHtml('endHours');
    	var emHtml = pub.buildMinuteSelectHtml('endMinutes');
    	
    	var index = $("#time_tbody").find("tr.time-tr").length+1;
    	var str = "<tr class='tt time-tr'>" +
    		"<td class='t-center b'><span class='sort-num'>" + index + "</span></td>" +
    		"<td><input type='text' class='input-date t-100' name='days' readonly value='' isHistoryData='false' onclick='WdatePicker({isShowClear:false,readOnly:true,isShowWeek:true,minDate:\"%y-%M-%d\"})'></td>" +
    		"<td>" + bhHtml + bmHtml + "</td>" +
			"<td>" + ehHtml + emHtml + "</td>" +
			"<td><input type='text' class='input-txt' placeholder='主题' style='width:90%;' name='timeTitles' value='' maxlength='75'></td>" +
			"<td class='t-center'><a href='javascript:;' class='i-save save-time' title='保存课次' dataValue=''></a><a href='javascript:;' class='i-del ml-10 del-time' title='删除课次' dataValue=''></a></td>" +
			"</tr>" +
			"<tr>" +
			"<td class='t-center b row-td'>票类</td>" +
			"<td colspan='5' class='public-table-inner'>" +
			"<table class='public-table price-table'>" +
			"<tbody></tbody>" +
			"<tfoot>" +
			"<tr class='first last'>" +
			"<td colspan='5'><a href='javascript:;' class='abtn abtn-green fn-btn add-price'><img src='/images/icon/add2.png'>添加价格</a></td>" +
			"</tr>" +
			"</tfoot>" +
			"</table>" +
			"</td>" +
			"</tr>";
    	$("#time_tbody").append(str);
    }
    
    /** 删除课次 **/
    function delTime() {
    	var dataValue = $(this).attr("dataValue");
    	var trObj = $(this).parent("td").parent("tr");
    	if(dataValue == undefined || dataValue == "" || dataValue == 0){
    		trObj.nextAll("tr").each(function(i){
        		$(this).find(".sort-num").html($(this).find(".sort-num").text()-1);
        	});
        	trObj.nextAll("tr").eq(0).remove();
        	trObj.remove();
    	}else{
    		Sender.ajax({
				'url' : Domain.cms_path + '/course/gainCourseTimeNum.htm',
				'data' : {'id':dataValue},
				'dataType' : 'json',
				'fn' : function(result) {
					var msg = "确定删除此课次？";
					if(result != null && result.FLAG != undefined && result.FLAG == "PUBLISHED"){
						msg = "已发布的课程至少保存一条课次记录<br/><b>是否确定删除？</b><br/><br/>是：删除课次并取消发布课程<br/>否：取消删除";
					}
					Tips.showConfirmWin({
						'info' : msg,
						'callFn' : function(){
							Sender.ajax({
								'url' : Domain.cms_path + '/course/delCourseTime.htm',
								'data' : {'id':dataValue},
								'dataType' : 'json',
								'fn' : function(result) {
									FieldMsg.drawMessages(result, function(){
										trObj.nextAll("tr").each(function(i){
							        		$(this).find(".sort-num").html($(this).find(".sort-num").text()-1);
							        	});
							        	trObj.nextAll("tr").eq(0).remove();
							        	trObj.remove();
							        	//更新合计
							        	updateTotalPrice();
									}, null);
								}
							});
						}
					});
				}
			});
    	}
    }
    
    /** 编辑课次 **/
    function editTime() {
    	if(!checkCanOperate('time','edit')){
    		return;
    	}
    	
    	var indexValue = $(this).parent().siblings("td").eq(0).text();
    	var dateValue = $(this).parent().siblings("td").eq(1).text();
    	var beginTimeValue = $(this).parent().siblings("td").eq(2).text();
    	var endTimeValue = $(this).parent().siblings("td").eq(3).text();
    	var titleValue = $(this).parent().siblings("td").eq(4).text();
    	var idValue = $(this).attr("dataValue");

    	var bhHtml = pub.buildHourSelectHtml('beginHours', beginTimeValue.split(":")[0]);
    	var bmHtml = pub.buildMinuteSelectHtml('beginMinutes', beginTimeValue.split(":")[1]);
    	var ehHtml = pub.buildHourSelectHtml('endHours', endTimeValue.split(":")[0]);
    	var emHtml = pub.buildMinuteSelectHtml('endMinutes', endTimeValue.split(":")[1]);
    	var str = "<td class='t-center b'><span class='sort-num'>" + indexValue + "</span></td>" +
			"<td><input type='text' class='input-date t-100' name='days' readonly value='" + dateValue + "' isHistoryData='false' onclick='WdatePicker({isShowClear:false,readOnly:true,isShowWeek:true,minDate:\"%y-%M-%d\"})'></td>" +
			"<td>" + bhHtml + bmHtml + "</td>" +
			"<td>" + ehHtml + emHtml + "</td>" +
			"<td><input type='text' class='input-txt' placeholder='主题' style='width:90%;' name='timeTitles' value='" + titleValue + "' maxlength='75'></td>" +
			"<td class='t-center'><a href='javascript:;' class='i-save save-time' title='保存课次' dataValue='" + idValue + "'></a><a href='javascript:;' class='i-del ml-10 del-time' title='删除课次' dataValue='" + idValue + "'></a></td>";
    	$(this).parent("td").parent("tr").html(str);
    }
    
    /** 保存课次 **/
    var isTrue_time = true;
    function saveTime() {
    	if(!isTrue_time){
    		return;
    	}
    	
    	var idValue = $(this).attr("dataValue");
    	var courseId = $("#courseId").val();
    	var indexValue = $(this).parent().siblings("td").eq(0).text();
    	var dateValue = $(this).parent().siblings("td").eq(1).find("input").val();
    	var bhValue = $(this).parent().siblings("td").eq(2).find("select[name='beginHours']").val();
    	var bmValue = $(this).parent().siblings("td").eq(2).find("select[name='beginMinutes']").val();
    	var ehValue = $(this).parent().siblings("td").eq(3).find("select[name='endHours']").val();
    	var emValue = $(this).parent().siblings("td").eq(3).find("select[name='endMinutes']").val();
    	var titleValue = $(this).parent().siblings("td").eq(4).find("input").val();
		if(dateValue==undefined || dateValue==""){
			Tips.showErrorMsg({"info":"课次日期不能为空"});
			return;
		}
		var curDate = Date.parse(new Date().format('yyyy-MM-dd'));
		if(curDate>Date.parse(dateValue)){
			Tips.showErrorMsg({"info":"课次日期有误，请重新选择"});
    		return;
		}
		var beginTemp = new Date(dateValue);
		var endTemp = new Date(dateValue);
		beginTemp.setHours(bhValue);
		beginTemp.setMinutes(bmValue);
		beginTemp.setSeconds(0);
		endTemp.setHours(ehValue);
		endTemp.setMinutes(emValue);
		endTemp.setSeconds(0);
		if(beginTemp.getTime() >= endTemp.getTime()){
			Tips.showErrorMsg({"info":"课次开始时间不能大于或等于结束时间，请重新选择"});
    		return;
		}
		
		isTrue_time = false;
		Sender.ajax({
			"url" : Domain.cms_path + '/course/addCourseTime.htm',
			"data" : {"courseTime.id":idValue, "courseTime.courseId":courseId, "courseTime.beginTime":beginTemp.format('yyyy-MM-dd HH:mm'),"courseTime.endTime":endTemp.format('yyyy-MM-dd HH:mm'), "courseTime.title":titleValue },
			"dataType" : "json",
			"fn" : function(result){
				isTrue_time = true;
				if(result.isSuccess){
					Tips.showSuccessMsg({
						"info" : "课次信息保存成功！", 
						"callFn" : function(){
							window.location.href = Domain.cms_path + "/course/courseTimeManage.htm?id=" + courseId;
						}
					});
				}else{
					Tips.showErrorMsg({"info":result.errorMsg});
				}
			}
		});
    }
    
    
    // ============================= 票价维护 =====================================
    /** 添加票价 **/
    function addPrice() {
    	if(!checkCanOperate('price','add')){
    		return;
    	}
    	
    	var obj = $(this).parents("table.price-table").find("tbody");
    	var str = "";
    	if(obj.find("tr").length == 0) {
    		str = "<tr class='first'>"
    	}else{
    		str = "<tr>"
    	}
    	var isHaveOnlinePrice = false;
    	if(obj.find("span[studyType='0']").length > 0){
    		isHaveOnlinePrice = true;
    	}
    	var isHaveLocalePrice = false;
    	if(obj.find("span[studyType='1']").length > 0){
    		isHaveLocalePrice = true;
    	}
    	var typeHtml = pub.buildPriceTypeSelectHtml(isHaveOnlinePrice, isHaveLocalePrice);
	    str += "<td width='110'>"+typeHtml+"</td>" +
    		"<td width='120'><span class='c-orange'>票价：</span><input type='text' class='input-txt' flag='nowPrice' placeholder='票价' style='width:40%;'></td>" +
    		"<td width='120'><span class='c-orange'>会员价：</span><input type='text' class='input-txt' flag='nowPriceVip' placeholder='会员价' style='width:40%;'></td>" +
    		"<td width='100'><span class='c-orange'>票数：</span><input type='text' class='input-txt' flag='priceNum' placeholder='票数' style='width:40%;' maxlength='5'></td>" +
    		"<td><span class='c-orange'>备注：</span><input type='text' class='input-txt' placeholder='备注（最多5个字）' maxlength='5' style='width:80%;'></td>" +
    		"<td width='85' class='t-center'><a href='javascript:;' class='i-save save-price' title='保存票类' dataValue=''></a><a href='javascript:;' class='i-del ml-10 del-price' title='删除票类' dataValue=''></a></td>" +
    		"</tr>";
	    obj.append(str);
    	$(this).parent().parent().removeClass("first");
    }
    
    /** 删除票价 **/
    function delPrice() {
    	var dataValue = $(this).attr("dataValue");
    	var trObj = $(this).parent("td").parent("tr");
    	var tdObj = $(this).parent().siblings("td");
    	var nowPrice = tdObj.eq(1).find("span").attr('nowPrice');
    	var nowPriceVip = tdObj.eq(2).find("span").attr('nowPriceVip');
    	if(dataValue == undefined || dataValue == "" || dataValue == 0){
    		if(trObj.hasClass("first")){
    			if(trObj.siblings("tr").length == 0){
    				trObj.parent("tbody").siblings("tfoot").children().first().addClass("first");
    			}else{
    				trObj.siblings("tr").eq(0).addClass("first");
    			}
    		}
    		trObj.remove();
    	}else{
    		Tips.showConfirmWin({
				'info' : "确定删除此票类？",
				'callFn' : function(){
					Sender.ajax({
						'url' : Domain.cms_path + '/course/delCoursePrice.htm',
						'data' : {'id':dataValue},
						'dataType' : 'json',
						'fn' : function(result) {
							FieldMsg.drawMessages(result, function(){
								if(trObj.hasClass("first")){
					    			if(trObj.siblings("tr").length == 0){
					    				trObj.parent("tbody").siblings("tfoot").children().first().addClass("first");
					    			}else{
					    				trObj.siblings("tr").eq(0).addClass("first");
					    			}
					    		}
					    		trObj.remove();
					    		//更新合计
				    			updateTotalPrice();
							}, null);
						}
					});
				}
			});
    	}
    }
    
    /** 编辑票价 **/
    function editPrice() {
    	if(!checkCanOperate('price','edit')){
    		return;
    	}
    	var tdObj = $(this).parent().siblings("td");
    	var studyTypeValue = tdObj.eq(0).find("span").attr("studyType");
    	var nowPriceValue = tdObj.eq(1).find("span").attr("nowPrice");
    	var nowPriceVipValue = tdObj.eq(2).find("span").attr("nowPriceVip");
    	var numValue = tdObj.eq(3).find("span").attr("num");
    	var remarkValue = tdObj.eq(4).find("span").text();
    	var idValue = $(this).attr("dataValue");
    	
    	var isHaveOnlinePrice = false;
    	if($(this).parent("td").parent("tr").siblings("tr").find("span[studyType='0']").length > 0){
    		isHaveOnlinePrice = true;
    	}
    	var isHaveLocalePrice = false;
    	if($(this).parent("td").parent("tr").siblings("tr").find("span[studyType='1']").length > 0){
    		isHaveLocalePrice = true;
    	}
    	
    	var typeHtml = pub.buildPriceTypeSelectHtml(isHaveOnlinePrice,isHaveLocalePrice, studyTypeValue);
	    var str = "<td width='110'>"+typeHtml+"</td>" +
    		"<td width='120'><span class='c-orange'>票价：</span><input type='text' class='input-txt' flag='nowPrice' placeholder='票价' style='width:40%;' value='"+nowPriceValue+"'></td>" +
    		"<td width='120'><span class='c-orange'>会员价：</span><input type='text' class='input-txt' flag='nowPriceVip' placeholder='会员价' style='width:40%;' value='"+nowPriceVipValue+"'></td>" +
    		"<td width='100'><span class='c-orange'>票数：</span><input type='text' class='input-txt' flag='priceNum' placeholder='票数' style='width:40%;' value='"+numValue+"' maxlength='5'></td>" +
    		"<td><span class='c-orange'>备注：</span><input type='text' class='input-txt' placeholder='备注（最多5个字）' maxlength='5' style='width:80%;' value='"+remarkValue+"'></td>" +
    		"<td width='85' class='t-center'><a href='javascript:;' class='i-save save-price' title='保存票类' dataValue='"+idValue+"'></a><a href='javascript:;' class='i-del ml-10 del-price' title='删除票类' dataValue='"+idValue+"'></a></td>" +
    		"</tr>";
	    $(this).parent("td").parent("tr").html(str);
    }
    
    /** 保存票价 **/
    var isTrue_price = true;
    function savePrice() {
    	if(!isTrue_price){
    		return;
    	}
    	
    	var idValue = $(this).attr("dataValue");
    	var courseId = $("#courseId").val();
    	var timeId = $(this).parents("table.price-table").parent("td").parent("tr").prev().attr("timeValue");
    	var tdObj = $(this).parent().siblings("td");
    	var typeValue = tdObj.eq(0).find("select[name='select_priceType']").val();
    	var typeName = tdObj.eq(0).find("select[name='select_priceType'] option:selected").text();
    	var nowPrice = tdObj.eq(1).find("input").val();
    	var nowPriceVip = tdObj.eq(2).find("input").val();
    	var num = tdObj.eq(3).find("input").val();
    	var remark = tdObj.eq(4).find("input").val();
    	if(typeValue==""){
			Tips.showErrorMsg({"info":"请选择票的类型"});
			return;
		}
    	if(nowPrice==""){
			Tips.showErrorMsg({"info":"请输入票价"});
			return;
		}
    	if(nowPriceVip == ""){
    		Tips.showErrorMsg({"info":"请输入会员价"});
			return;
    	}
    	if(!Validator.isNotMinusFloat(nowPrice, 2)){
    		Tips.showErrorMsg({"info":"票价不能小于0，最多保留两位小数"});
    		return;
    	}
    	if(!Validator.isNotMinusFloat(nowPriceVip, 2)){
    		Tips.showErrorMsg({"info":"会员价不能小于0，最多保留两位小数"});
    		return;
    	}
    	if(Number(nowPrice) < Number(nowPriceVip)){
    		Tips.showErrorMsg({"info":"会员价不能大于票价，请确认"});
    		return;
    	}
    	if(num==""){
			Tips.showErrorMsg({"info":"请输入票数"});
			return;
		}
    	if(typeValue==2){//现场票票数必须大于0
    		if(num <= 0){
    			Tips.showErrorMsg({"info":"现场票票数必须大于0"});
    			return;
    		}
    	}
    	if(!Validator.isNumber(num)){
    		Tips.showErrorMsg({"info":"票数必须是整数"});
			return;
    	}
    	var numStr = num + "张";
    	if(num == 0){
    		numStr = "不限制";
    	}
    	
    	isTrue_price = false;
    	var this_obj = $(this);
    	Sender.ajax({
			"url" : Domain.cms_path + '/course/addCoursePrice.htm',
			"data" : {"coursePrice.id":idValue, 
				"coursePrice.courseId":courseId, 
				"coursePrice.courseTimeId":timeId, 
				"coursePrice.nowPrice":nowPrice, 
				"coursePrice.nowPriceVip":nowPriceVip,
				"coursePrice.num":num, 
				"coursePrice.remark":remark,
				"flag":typeValue},
			"dataType" : "json",
			"fn" : function(result){
				isTrue_price = true;
				if(result.isSuccess){
					Tips.showSuccessMsg({
						"info" : "票类信息保存成功！", 
						"callFn" : function(){
							if(idValue=="" || idValue==0){//新增
								if (result.promptMsg.indexOf("SUCCESS_") > -1){
									idValue = result.promptMsg.substring(8);
								}
							}
							var studyType;
							if(typeValue==1){
								studyType = 0;
							}else if(typeValue==2){
								studyType = 1;
							}
							var str = "<td width='110'><span studyType='"+studyType+"'>" + typeName + "</span></td>" +
							"<td width='120'><span nowPrice='"+nowPrice+"'>票价：￥" + nowPrice + "</span></td>" +
							"<td width='120'><span nowPriceVip='"+nowPriceVip+"'>会员价：￥" + nowPriceVip + "</span></td>" +
							"<td width='100'><span num='"+num+"'>" + numStr + "</span></td>" +
							"<td><span>" + remark + "</span></td>" +
							"<td width='85' class='t-center'>" +
							"<a href='javascript:;' class='i-edit edit-price' title='编辑票类' dataValue='"+idValue+"'></a><a href='javascript:;' class='i-del ml-10 del-price' title='删除' dataValue='"+idValue+"'></a>" +
							"</td>";
							this_obj.parent("td").parent("tr").html(str);
							//更新合计
							updateTotalPrice();
						}
					});
				}else{
					Tips.showErrorMsg({"info":result.errorMsg});
				}
			}
		});
    }
    
    
    
    /** 编辑套票价 **/
    function editCostPrice() {
    	var tdObj = $(this).parent().siblings("td");
    	var oldPrice = tdObj.eq(1).find("span").attr("oldPrice");
    	var nowPrice = tdObj.eq(2).find("span").attr("nowPrice");
    	var nowPriceVip = tdObj.eq(3).find("span").attr("nowPriceVip");
    	
	    var str = "<td width='50' class='t-center'>课程价格</td>" +
    		"<td width='130'><span class='c-orange'>课程原价:</span><input type='text' flag='nowPrice' class='input-txt' style='width:40%;' value='"+oldPrice+"'></td>" +
    		"<td width='130'><span class='c-orange'>课程现价：</span><input type='text' flag='nowPrice' class='input-txt' style='width:40%;' value='"+nowPrice+"'></td>" +
    		"<td width='130'><span class='c-orange'>会员现价：</span><input type='text' flag='nowPrice' class='input-txt' style='width:40%;' value='"+nowPriceVip+"'></td>" +
    		"<td width='100' class='t-center'><a href='javascript:;' class='i-save save-price' id='saveCostPrice' title='保存价格'></a></td>" +
    		"</tr>";
	    $(this).parent("td").parent("tr").html(str);
    }
    
    /** 保存套票价 **/
    function saveCostPrice() {
    	var courseId = $("#courseId").val();
    	var tdObj = $(this).parent().siblings("td");
    	var oldPrice = tdObj.eq(1).find("input").val();
    	var nowPrice = tdObj.eq(2).find("input").val();
    	var nowPriceVip = tdObj.eq(3).find("input").val();
    	if(oldPrice==""){
			Tips.showErrorMsg({"info":"请输入课程原价"});
			return;
		}
    	if(nowPrice==""){
			Tips.showErrorMsg({"info":"请输入课程现价"});
			return;
		}
    	if(nowPriceVip == ""){
    		Tips.showErrorMsg({"info":"请输入会员现价"});
			return;
    	}
    	if(!Validator.isNotMinusFloat(oldPrice, 2)){
    		Tips.showErrorMsg({"info":"课程原价不能小于0，最多保留两位小数"});
    		return;
    	}
    	if(!Validator.isNotMinusFloat(nowPrice, 2)){
    		Tips.showErrorMsg({"info":"课程现价不能小于0，最多保留两位小数"});
    		return;
    	}
    	if(!Validator.isNotMinusFloat(nowPriceVip, 2)){
    		Tips.showErrorMsg({"info":"会员现价不能小于0，最多保留两位小数"});
    		return;
    	}
    	if(Number(oldPrice) < Number(nowPrice)){
    		Tips.showErrorMsg({"info":"课程现价不能大于课程原价，请确认"});
    		return;
    	}
    	if(Number(nowPrice) < Number(nowPriceVip)){
    		Tips.showErrorMsg({"info":"会员现价不能大于课程现价，请确认"});
    		return;
    	}
    	
    	var this_obj = $(this);
    	Sender.ajax({
			"url" : Domain.cms_path + '/course/saveCourseCostPrice.htm',
			"data" : {"course.id":courseId, 
				"course.courseOldcost":oldPrice, 
				"course.courseNowcost":nowPrice, 
				"course.courseNowcostVip":nowPriceVip},
			"dataType" : "json",
			"fn" : function(result){
				if(result.isSuccess){
					Tips.showSuccessMsg({
						"info" : result.promptMsg, 
						"callFn" : function(){
							var str = "<td class='t-center'>课程价格</td>" +
							"<td width='130'>课程原价:<span oldPrice='" + oldPrice + "'>￥" + oldPrice + "</span></td>" +
							"<td width='130'>课程现价:<span nowPrice='" + nowPrice + "'>￥" + nowPrice + "</span></td>" +
							"<td width='130'>会员现价:<span nowPriceVip='" + nowPriceVip + "'>￥" + nowPriceVip + "</span></td>" +
							"<td width='100' class='t-center'>" +
							"<a href='javascript:;' class='i-edit edit-price' title='编辑价格' id='editCostPrice'></a>" +
							"</td>";
							this_obj.parent("td").parent("tr").html(str);
						}
					});
				}else{
					Tips.showErrorMsg({"info":result.errorMsg});
				}
			}
		});
    }
    
    
    // 检查能否进行操作
    function checkCanOperate(timeORprice, addORedit){
    	var isCheck = true;
    	var otherMsg = "";
    	if($("tr.time-tr a.save-time").length > 0){
    		var isCheck = false;
    		otherMsg = "课次";
    	}
    	if($("#time_tbody a.save-price").length > 0){
    		var isCheck = false;
    		otherMsg = "票类";
    	}
    	var m1 = addORedit=="add" ? "添加" : "编辑";
    	var m2 = timeORprice=="time" ? "课次" : "票类";
    	if(!isCheck){
    		Tips.showErrorMsg({"info":"请先保存其他"+otherMsg+"信息，才能"+m1+"新的"+m2+"！"});
    		return false;
    	}
    	return true;
    }
    
    // 票类下拉框
    // isHaveOnlinePrice：是否已经有直播票
    // isHaveLocalePrice：是否已经有现场票
    // studyTypeValue：0在线直播，1现场听课/现场听音乐会，2点播
    // priceTypeValue：0正式，1预售
    pub.buildPriceTypeSelectHtml = function(isHaveOnlinePrice,isHaveLocalePrice, studyTypeValue){
    	var str = "<select class='input-sel' name='select_priceType'>" +
    			"<option value=''>选择票的类型</option>";
    	var isSel = false;
    	if(!isHaveOnlinePrice){
    		if(studyTypeValue!=undefined && studyTypeValue==0 ){
    			isSel = true;
    		}
    		if(isSel){
    			str += "<option value='1' selected='selected'>直播票</option>";
    		}else{
    			str += "<option value='1'>直播票</option>";
    		}
    	}
    	
    	isSel = false;
    	if(!isHaveLocalePrice){
    		if(studyTypeValue!=undefined && studyTypeValue==1){
    			isSel = true;
    		}
    		if(isSel){
    			str += "<option value='2' selected='selected'>现场票</option>";
    		}else{
    			str += "<option value='2'>现场票</option>";
    		}
    	}
    	
    	str += "</select>";
    	return str;
    }
    
    // 小时下拉框
    pub.buildHourSelectHtml = function(name, value, isHistory){
    	if(value != undefined){
    		value = parseInt(value);
    	}else{
    		value = 0;
    	}
        var hourHtml = "";
        if (!isHistory) {
        	hourHtml = "<select class='input-sel' style='padding:5px 0;' name='" + name + "'>";
        	for ( var i = 0; i < 24; i++) {
        		var sel = "";
	        	if(i == value){
	        		sel = "selected='selected'";
	        	}
	        	var tempValue = i;
	        	if(i < 10){
	        		tempValue = "0" + i;
	        	}
        		hourHtml += "<option value='" + tempValue + "'" + sel + ">" + tempValue + "</option>";
        	}
        }else {
        	var tempValue = value;
        	if(value < 10){
        		tempValue = "0" + value;
        	}
        	hourHtml = "<select class='input-sel' style='padding:5px 0;' name='" + name + "' disabled>";
        	hourHtml += "<option value='" + tempValue + "'>" + tempValue + "</option>";
        }
        hourHtml += "</select>点";
        return hourHtml;
    }
	// 分钟下拉框
    pub.buildMinuteSelectHtml = function(name, value, isHistory){
    	if(value != undefined){
    		value = parseInt(value);
    	}else{
    		value = 0;
    	}
        var minuteHtml = "";
        if (!isHistory) {
        	minuteHtml = "<select class='input-sel' style='padding:5px 0;' name='" + name + "'>";
        	for ( var i = 0; i < 60; i+=5) {
        		var sel = "";
            	if(i == value){
            		sel = "selected='selected'";
            	}
            	var tempValue = i;
	        	if(i < 10){
	        		tempValue = "0" + i;
	        	}
        		minuteHtml += "<option value='" + tempValue + "'" + sel + '>' + tempValue + "</option>";
        	}
        } else {
        	var tempValue = value;
        	if(value < 10){
        		tempValue = "0" + value;
        	}
        	minuteHtml = "<select class='input-sel' style='padding:5px 0;' name='" + name + "' disabled>";
        	minuteHtml += "<option value='" + tempValue + "'>" + tempValue + "</option>";
        }
        minuteHtml += "</select>分";
        return minuteHtml;
    }
    
    // 更新合计（只更新直播课价格）
    function updateTotalPrice(){
    	var spanList = $('span[studyType]');
    	var courseTotal = 0;
    	var courseTotalVip = 0;
    	if(spanList != undefined && spanList.length > 0){
    		for(var i=0;i<spanList.length;i++){
    			var _this = $(spanList[i]);
    			var price = _this.parent().next().find('span').attr('nowPrice');
				var priceVip = _this.parent().next().next().find('span').attr('nowPriceVip');
    			if(_this.attr('studyType') == 0 && price != undefined && priceVip != undefined){
    				courseTotal += parseFloat(price);
    				courseTotalVip += parseFloat(priceVip);
    			}
    		}
    	}
    	courseTotal = courseTotal.toFixed(2);
    	courseTotalVip = courseTotalVip.toFixed(2);
    	$('#courseTotal').attr('courseTotal',courseTotal);
        $('#courseTotalVip').attr('courseTotalVip',courseTotalVip);
        $('#courseTotal').html('￥'+courseTotal);
        $('#courseTotalVip').html('￥'+courseTotalVip);
    }
    /*********************** 初始化课次end *********************/
    
    /** 验证课程价格 **/
    function valPrices() {
    	var nowPrices = $("#price_tbody").find("input[name='nowPrices']");
    	var nums = $("#price_tbody").find("input[name='nums']");
    	var priceSeqs = $("#price_tbody").find("input[name='priceSeqs']");
    	var priceMarks = $("#price_tbody").find("input[name='priceMarks']");
    	var isVal = true;
    	var count = 0;
    	var newObj,numObj,seqObj,markObj;
    	for(var i=0; i<nowPrices.length; i++){
    		newObj = $(nowPrices[i]);
    		numObj = $(nums[i]);
    		seqObj = $(priceSeqs[i]);
    		markObj = $(priceMarks[i]);
    		if(numObj.val()=='' && newObj.val()=='' && seqObj.val()=='' && markObj.val()==''){
    			continue;
    		}else{
    			if(newObj.val()==''){
    				isVal = false;
    				newObj.addClass("input-txt-error");
    			}else if(!Validator.isNotMinusFloat(newObj.val(), 2)){
    				isVal = false;
    				newObj.addClass("input-txt-error");
    			}else {
    				newObj.removeClass("input-txt-error");
    			}
    			
    			if(numObj.val()==''){
    				isVal = false;
    				numObj.addClass("input-txt-error");
    			}else if(!Validator.isPlusInt(numObj.val())){
    				isVal = false;
    				numObj.addClass("input-txt-error");
    			}else {
    				numObj.removeClass("input-txt-error");
    			}
    			count++;
    		}
    	}
    	if(count == 0){
    		Tips.showErrorMsg({"info":"至少需要添加一条价格"});
    		return false;
    	}
    	if(!isVal){
    		Tips.showErrorMsg({"info":"基地课程价格有误，请修改！<br/><br/>注：金额不能为空，且最多保留两位小数<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量不能为空，且大于0的整数"});
    	}
    	return isVal;
    }
    
    /** 验证课次 **/
    function valTimes() {
    	$("#time_tbody").find("input[name='beginTimes']").remove();
    	$("#time_tbody").find("input[name='endTimes']").remove();
    	
    	var days = $("#time_tbody").find("input[name='days']");
    	var beginHours = $("#time_tbody").find("select[name='beginHours']");
    	var beginMinutes = $("#time_tbody").find("select[name='beginMinutes']");
    	var endHours = $("#time_tbody").find("select[name='endHours']");
    	var endMinutes = $("#time_tbody").find("select[name='endMinutes']");
    	if(days.length == 0){
    		Tips.showErrorMsg({"info":"至少需要添加一个课次"});
    		return false;
    	}
    	var curDate = Date.parse(new Date().format('yyyy-MM-dd'));
    	var saleTimeValue = $("#saleTime").val();
    	var saleDate = new Date(saleTimeValue.split(" ")[0]);
    	saleDate.setHours(saleTimeValue.split(" ")[1].split(":")[0]);
    	saleDate.setMinutes(saleTimeValue.split(" ")[1].split(":")[1]);
    	var saleTime = saleDate.getTime();
    	for(var i=0; i<days.length; i++){//验证时间
    		if($(days[i]).attr("isHistoryData")=="true") {
    			continue;
    		}
    		if(days[i].value == ""){
    			Tips.showErrorMsg({"info":"第"+(i+1)+"个课次出错：日期不能为空，请选择日期"});
        		return false;
    		}
    		if(curDate>Date.parse(days[i].value)){
    			Tips.showErrorMsg({"info":"第"+(i+1)+"个课次出错：日期有误，请选择日期"});
        		return false;
    		}
    		var beginTemp = new Date(days[i].value);
    		var endTemp = new Date(days[i].value);
    		beginTemp.setHours(beginHours[i].value);
    		beginTemp.setMinutes(beginMinutes[i].value);
    		beginTemp.setSeconds(0);
    		endTemp.setHours(endHours[i].value);
    		endTemp.setMinutes(endMinutes[i].value);
    		endTemp.setSeconds(0);
    		if(saleTime >= beginTemp.getTime()){
    			Tips.showErrorMsg({"info":"第"+(i+1)+"个课次出错：开始时间小于或等于售票时间，请选重新选择"});
    			return false;
    		}
    		if(beginTemp.getTime() >= endTemp.getTime()){
    			Tips.showErrorMsg({"info":"第"+(i+1)+"个课次出错：开始时间大于或等于结束时间，请重新选择"});
        		return false;
    		}
    	}
    	for(var i=0; i<days.length; i++){//添加input隐藏域
    		var beginTemp = new Date(days[i].value);
    		var endTemp = new Date(days[i].value);
    		beginTemp.setHours(beginHours[i].value);
    		beginTemp.setMinutes(beginMinutes[i].value);
    		beginTemp.setSeconds(0);
    		endTemp.setHours(endHours[i].value);
    		endTemp.setMinutes(endMinutes[i].value);
    		endTemp.setSeconds(0);
    		$("#time_tbody").append("<input type='hidden' name='beginTimes' value='"+beginTemp.format('yyyy-MM-dd HH:mm')+"'/>");
    		$("#time_tbody").append("<input type='hidden' name='endTimes' value='"+endTemp.format('yyyy-MM-dd HH:mm')+"'/>");
    	}
    	return true;
    }
    
})(jQuery, window, document);