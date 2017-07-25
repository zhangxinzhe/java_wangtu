(function($, window, document, undefined) {
	var pub = {};
	window.editCompete = pub;
	pub.init = function(competeId) {
		pub.competeId=competeId;
		bindEvent();
		initData();
	}

	//初始化
	function initData(){
		changeCompeteTypeFunc();
	}
	
	function bindEvent() {
		$('#saveBtn').bind('click', saveCompete);
		$('#form_file_del').bind('click', formFileDel);
		$('#competeType').bind('change', changeCompeteTypeFunc);
	}
	
	//保存活动信息
	function saveCompete(){
		if(!Verify.checkAllVerify("#competeForm")){
			return ;
		}
		var infoMsg = "活动若设置正在进行，则相同类型的其他活动自动设置非进行中<br/><br/>确定保存么？";
		var competeType = $('#competeType').val();
		if("HLJ_HC" == competeType || "HLJ_YD" == competeType){
			infoMsg = "黄龙奖活动若设置正在进行，相同期号比赛自动更新为正在进行中，<br/>且相同类型的其他活动自动设置非进行中<br/><br/>确定保存么？";
		}
		if($('select[name="isDoing"]').val()==1){
			Tips.showConfirmWin({
				"info": infoMsg,
				"callFn" : function (){
					//提交表单
					$('#competeForm').submit();
				}
			});
		}else{
			//提交表单
			$('#competeForm').submit();
		}
	}
	
	//删除文件
	function formFileDel(){
		Sender.ajax({
			"url" : "/homepage/compete/delCompeteFormFile.htm",
			"data" : {"id" : $(this).attr("dataValue")},
			"fn" : function(result){
				if(result == ""){
					$('#compete_fomrFile').val('');
					$('#compete_formName').val('');
					Tips.showSuccessMsg({"info":"文件删除成功！","callFn":function(){
						$("#form_file").hide();
					}});
				} else{
					Tips.showErrorMsg({"info":result});
				}
			}
		});
	}
	
	/**改变活动类型触发的方法**/
	function changeCompeteTypeFunc(){
		var competeTypeValue = $('#competeType').val();
		if(null==competeTypeValue || ''==competeTypeValue || undefined == competeTypeValue){
			return ;
		}
		//隐藏域赋值
		$('input[name="compete.competeType"]').val(competeTypeValue);
		
		if('SZCX' == competeTypeValue){
			$('#competeFeeTr').show();
			
			$('#competeBatchTr').hide();
			$('#competeBatch').attr("value", "");
			$('#competeBatch').removeAttr("notNull");
		}else{
			$('#competeFeeTr').hide();
			
			if(pub.competeId <= 0){//新增操作
				var isGainTure = true;//获取期号，是否成功
				if($("#competeBatch").attr("dataValue") == ""){
					Sender.ajax({
						"url" : "/homepage/compete/getCompeteBatch.htm",
						"data" : {"competeType" : competeTypeValue},
						"async" : false,
						"fn" : function(result){
							if(result == "ERROR"){
								Tips.showErrorMsg({"info" : "信息异常，请刷新页面重试！"});
								isGainTure = false;
							}else if(result == "MAX_VALUE"){
								Tips.showWarnWin({"info" : "当前年度最大期号已使用，请自行输入期号！"});
							}else{
								$('#competeBatch').attr("value", result);
								$('#competeBatch').attr("notNull", "true");
							}
						}
					});
				}else{
					$('#competeBatch').attr("notNull", "true");
				}
				if(!isGainTure){
					$('#competeBatchTr').show();
					return;
				}
			}
			$('#competeBatchTr').show();
		}
	}

	/**
	 * 验证有没有选择比赛时间
	 */
	window.checkGameTime = function (){
		var benginTime = $('#beginTime').val();
		var endTime =$('#endTime').val();
		if(benginTime == null || benginTime =='' || endTime == null || endTime == ''){
			Tips.showAlertWin({
				info : "请先选择比赛时间段！"
			});
			return false; 
		}else{
			return true;
		}
	}

})(jQuery, window, document);