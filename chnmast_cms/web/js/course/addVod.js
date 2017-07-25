(function($, window, document){
	var pub = {};
	window.addVod = pub;
    pub.init = function(params) {
    	// 选择讲师助教
        $(".selectTea").bind("click", openSelectDiv);
        // 保存信息
        $("#saveBtn").bind("click", saveVod);
        
        $("#TimeLi").bind("click",function(){
    		var dataValue = $(this).attr("dataValue");
    		if(dataValue=="" || dataValue==0){
    			$("#courseLi").addClass('current').siblings('li').removeClass('current');
    			Tips.showErrorMsg({"info":"课程信息保存成功，才能维护课次和价格！"});
    		}else{
    			window.location.href = Domain.cms_path + "/vod/vodTimeManage.htm?id="+dataValue;
    		}
    	});
    }
    
    var isTrue_vod = true;//防止重复提交
    function saveVod(){
    	if(!isTrue_vod){
    		return;
    	}
    	isTrue_vod = false;
    	if(!valAllInfo()){
    		isTrue_vod = true;
    		return;
    	}
		var vodId = $("#vodId").val();
		$("#vod_form").attr("action", Domain.cms_path + "/vod/addVod.htm");
		Sender.submitAjax({
			"frm" : "#vod_form",
			"dataType" : "json",
			"fn" : function(result){
				isTrue_vod = true;
				if(result.isSuccess){
					$("#uploadTempFile").val("");
					if(vodId=="" || vodId==0) {//新增保存
						if (result.promptMsg.indexOf("SUCCESS_") > -1){
							Tips.showSuccessMsg({
								"info" : "课程新增保存成功！",
								"callFn" : function(){
									window.location.href = Domain.cms_path + "/vod/addVod.htm?id="+result.promptMsg.substring(8);
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
    
    function valAllInfo() {
    	FieldMsg.clearFieldError("#vod_form"); //清除各字段的报错提示
    	if(!Verify.checkAllVerify("#vod_form")){ //验证所有字段
    		return false; 
    	}
    	if(!Verify.checkOverLen("#introduction", "课程介绍", 800, true)){
    		return false;
    	}
    	if(!Verify.checkOverLen("#remark", "备注内容", 800, true)){
    		return false;
    	}
    	return true;
    }
    
    
    /** 打开选择讲师、助教弹出层 **/
    function openSelectDiv() {
		var id = $("#teaId").val();
    	var url = Domain.cms_path + "/vod/selectTea.htm?flag=1"
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


/** 点播视频、课次维护 **/
(function($, window, document){
	var pub = {};
	window.addVodTime = pub;
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
    	
    	$("#time_tbody").on("click", "a.del-video", delVideo);
    	
    	$("#time_tbody").on("click", "a.edit-ftp-video", editftpVideo);
    	
    	$("#cost_tbody").on("click", "#editCostPrice", editCostPrice);
    	$("#cost_tbody").on("click", "#saveCostPrice", saveCostPrice);
    	
    	$(".price-table").on("keyup", "input[flag='nowPrice']", function(){$(this).val($(this).val().replace(/[^\-?\d.]/g,''))});
    	$(".price-table").on("keyup", "input[flag='priceNum']", function(){$(this).val($(this).val().replace(/[^0-9]/g,''))});
    }
    
    /** 添加课次 **/
    function addTime() {
    	if(!checkCanOperate('time','add')){
    		return;
    	}
    	
    	var index = $("#time_tbody").find("tr.time-tr").length+1;
    	var str = "<tr class='tt time-tr'>" +
    		"<td class='t-center b'><span class='sort-num'>" + index + "</span></td>" +
			"<td><input type='text' class='input-txt' placeholder='主题' style='width:90%;' name='timeTitles' value='' maxlength='75'></td>" +
			"<td width='100'><span class='c-orange'>票价：</span><input type='text' class='input-txt' flag='nowPrice' placeholder='票价' style='width:40%;'></td>" +
    		"<td width='100'><span class='c-orange'>会员价：</span><input type='text' class='input-txt' flag='nowPriceVip' placeholder='会员价' style='width:40%;'></td>" +
    		"<td><span class='c-orange'>备注：</span><input type='text' class='input-txt' placeholder='点播（最多5个字）' maxlength='5' style='width:80%;'></td>" +
    		"<td style='display:none'><span></span></td>"+
			"<td class='t-center'><a href='javascript:;' class='fn-right i-del ml-20 mr-20 del-time' title='删除课次' dataValue=''></a><a href='javascript:;' class='fn-right i-save save-time' title='保存课次' dataValue=''></a></td>" +
			"</tr>" +
			"<tr>" +
			"<td class='t-center b row-td'></td>" +
			"<td colspan='5' class='public-table-inner'>" +
			"<table class='public-table price-table'>" +
			"<tbody></tbody>" +
			"<tfoot>" +
			"<tr class='first last'>" +
			//"<td colspan='5'><a href='javascript:;' class='abtn abtn-green fn-btn add-vedio'><img src='/images/icon/add2.png'>添加视频</a></td>" +
			"<td colspan='5'></td>" +
			"</tr>" +
			"</tfoot>" +
			"</table>" +
			"</td>" +
			"</tr>";
    	$("#time_tbody").append(str);
    	$(".add-vedio").bind("click", function(){ Tips.showErrorMsg({"info":"请先保存课次信息！"});});
    }
    
    /** 删除课次 **/
    function delTime() {
    	var dataValue = $(this).attr("dataValue");
    	var courseId = $("#courseId").val();
    	var trObj = $(this).parent("td").parent("tr");
    	if(dataValue == undefined || dataValue == "" || dataValue == 0){
    		trObj.nextAll("tr").each(function(i){
        		$(this).find(".sort-num").html($(this).find(".sort-num").text()-1);
        	});
        	trObj.nextAll("tr").eq(0).remove();
        	trObj.remove();
    	}else{
    		Sender.ajax({
				'url' : Domain.cms_path + '/vod/gainVodTimeNum.htm',
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
								'url' : Domain.cms_path + '/vod/delVodTime.htm',
								'data' : {'id':dataValue},
								'dataType' : 'json',
								'fn' : function(result) {
									if(result.isSuccess){
										Tips.showSuccessMsg({
											"info" : "课次信息删除成功！", 
											"callFn" : function(){
												window.location.href = Domain.cms_path + "/vod/vodTimeManage.htm?id=" + courseId;
											}
										});
									}else{
										Tips.showErrorMsg({"info":result.errorMsg});
									}
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
    	var tdObj = $(this).parent().siblings("td");
    	var indexValue = tdObj.eq(0).text();
    	var titleValue =tdObj.eq(1).text();;
    	var nowPriceValue = tdObj.eq(2).find("span").attr("nowPrice");
    	var nowPriceVipValue = tdObj.eq(3).find("span").attr("nowPriceVip");
    	var remarkValue = tdObj.eq(4).find("span").text();
    	var idValue = $(this).attr("dataValue");
    	var priceIdValue=tdObj.eq(5).find("span").text();
    	
    	var str = "<td class='t-center b'><span class='sort-num'>" + indexValue + "</span></td>" +
			"<td><input type='text' class='input-txt' placeholder='主题' style='width:90%;' name='timeTitles' value='" + titleValue + "' maxlength='75'></td>" +
			"<td width='120'><span class='c-orange'>票价：</span><input type='text' class='input-txt' flag='nowPrice' placeholder='票价' value='"+nowPriceValue +"' style='width:40%;'></td>" +
    		"<td width='120'><span class='c-orange'>会员价：</span><input type='text' class='input-txt' flag='nowPrice'  placeholder='会员价' value='"+nowPriceVipValue +"'  style='width:40%;'></td>" +
    		"<td><span class='c-orange'>备注：</span><input type='text' class='input-txt' placeholder='点播（最多5个字）' maxlength='5'  value='"+remarkValue +"' style='width:50%;'></td>" +
    		"<td style='display:none'><span>"+priceIdValue+"</span></td>"+
			"<td class='t-center'><a href='javascript:;' class='fn-right i-del ml-20 mr-20 del-time' title='删除课次' dataValue='" + idValue + "'></a><a href='javascript:;' class='fn-right i-save save-time' title='保存课次' priceIdValue='"+priceIdValue+"' dataValue='" + idValue + "'></a></td>";
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
    	var tdObj = $(this).parent().siblings("td");
    	var indexValue = tdObj.eq(0).text();
    	var titleValue = tdObj.eq(1).find("input").val();
    	var nowPrice = tdObj.eq(2).find("input").val();
    	var nowPriceVip = tdObj.eq(3).find("input").val();
    	var remark = tdObj.eq(4).find("input").val();
    	var priceIdValue=tdObj.eq(5).find("span").text();
    	
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
    	
    	isTrue_time = false;
		Sender.ajax({
			"url" : Domain.cms_path + '/vod/addVodTime.htm',
			"data" : {"courseTime.id":idValue,
				"courseTime.courseId":courseId, 
				"courseTime.title":titleValue ,
				"coursePrice.id":priceIdValue,
				"coursePrice.courseId":courseId,
				"coursePrice.courseTimeId":idValue,
				"coursePrice.nowPrice":nowPrice, 
				"coursePrice.nowPriceVip":nowPriceVip,
				"coursePrice.remark":remark},
			"dataType" : "json",
			"fn" : function(result){
				isTrue_time = true;
				if(result.isSuccess){
					Tips.showSuccessMsg({
						"info" : "课次信息保存成功！", 
						"callFn" : function(){
							window.location.href = Domain.cms_path + "/vod/vodTimeManage.htm?id=" + courseId;
						}
					});
				}else{
					Tips.showErrorMsg({"info":result.errorMsg});
				}
			}
		});
    }

    /** 删除视频 **/
    function delVideo() {
    	var dataValue = $(this).attr("dataValue");
    	var courseId=$("#courseId").val();
    	var trObj = $(this).parent("td").parent("tr");
    	if(dataValue == undefined || dataValue == "" || dataValue == 0){
    		return;
    	}else{
    		Tips.showConfirmWin({
				'info' : "确定删除此视频？",
				'callFn' : function(){
					Sender.ajax({
						'url' : Domain.cms_path + '/vod/delVideo.htm',
						'data' : {'id':dataValue},
						'dataType' : 'json',
						'fn' : function(result) {
							if(result.isSuccess){
								Tips.showSuccessMsg({
									"info" : "视频删除成功！", 
									"callFn" : function(){
										window.location.href = Domain.cms_path + "/vod/vodTimeManage.htm?id=" + courseId;
									}
								});
							}else{
								Tips.showErrorMsg({"info":result.errorMsg});
							}
							
						}
					});
				}
			});
    	}
    } 

    /** 维护视频地址 **/
    function editftpVideo() {
    	$("#videoUrlDiv_videoFile").val("");
    	$("#videoUrlDiv_msg").html("");
    	var videoId = $(this).attr("videoId");
    	var courseId = $(this).attr("courseId");
    	var courseTimeId = $(this).attr("courseTimeId");
    	var userIdTemp = $(this).attr("userIdTemp");
    	var flag = $(this).attr("flag");
    	var videoUrl = "";
    	if(!videoId){
    		videoId = 0;
    	}
    	if(videoId > 0){
    		videoUrl = $(this).attr("videoFile");
    		$("#videoUrlDiv_videoFile").val(videoUrl);
    	}
    	Box.showWin({"target":"#videoUrlDiv","modal":true,"isCenter":true});
    	
    	// 提交
    	var isTrue_videoUrl = true;
    	$("#videoUrl_submit").unbind().bind("click", function(){
    		$("#videoUrlDiv_msg").html("");
    		
    		if(!isTrue_videoUrl){
    			return;
    		}
    		var videoFile = StringUtil.trim($("#videoUrlDiv_videoFile").val());
    		if(videoFile == ""){
    			$("#videoUrlDiv_msg").html("请输入链接地址！");
    			return;
    		}
    		if(Validator.test(/[\\\<\>\&~!@#$%|]/ ,videoFile)){
    			$("#videoUrlDiv_msg").html("链接地址不能包含特殊字符：\<>&~!@#$%|");
    			return;
    		}
    		
    		isTrue_videoUrl = false;
			Sender.ajax({
	    		"url" : Domain.cms_path + '/vod/upload/addVideo.htm',
	    		"data" : {
	    			"video.id":videoId,
	    			"video.courseId":courseId, 
					"video.courseTimeId":courseTimeId,
					"video.videoFile":videoFile,
					"userIdTemp":userIdTemp, 
					"flag":flag
					},
				"dataType" : "json",
				"fn" : function(result){
					isTrue_videoUrl = true;
					if(result.isSuccess){
						$("#videoUrlDiv .close").click();
						Tips.showSuccessMsg({
			                "info" : "视频地址维护成功！",
			                "callFn" : function(){
			                	window.location.href = Domain.cms_path + "/vod/vodTimeManage.htm?id="+courseId;
			                }
			            });
					}else{
						$("#videoUrlDiv_msg").html(result.errorMsg);
					}
				}
	    	});
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
    	var m1 = addORedit=="add" ? "添加" : "编辑";
    	var m2 = timeORprice=="time" ? "课次" : "视频";
    	if(!isCheck){
    		Tips.showErrorMsg({"info":"请先保存其他"+otherMsg+"信息，才能"+m1+"新的"+m2+"！"});
    		return false;
    	}
    	return true;
    }
    
    /*********************** 初始化课次end *********************/

})(jQuery, window, document);