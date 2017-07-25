(function($, window, document){
	var pub = {};
	window.agencyRec = pub;
	pub.init = function(){
		bindEvent();
	}
	
	function bindEvent(){
		$('.public-tab li').bind('click',function(){
			var isRec = $(this).attr('isRec');
			window.location.href = Domain.cms_path + '/homepage/agency/agencyRec.htm?isAgencyRec=' + isRec;
		});
		
		$(".agencyRec").bind("click", function(){
			var isAgencyRec = $(this).attr("isReced_flag");
			var id = $(this).attr("dataValue");
			var info = "";
			if(isAgencyRec == "true"){// 推荐
				info = "确定推荐此培训基地吗！";
			}else {// 取消推荐
				info = "确定取消推荐此培训基地！";
			}
			Tips.showConfirmWin({
				'info' : info,
				'callFn' : function(){
					Tips.loading({'info':'正在操作，请稍候....'});
					Sender.ajax({
						'url' : Domain.cms_path + '/homepage/agency/agencyRecUpdate.htm',
						'data' : {'id':id, 'isAgencyRec':isAgencyRec},
						'dataType' : 'json',
						'fn' : function(retult) {
							Tips.loaded();
							FieldMsg.drawMessages(retult, function(){
								$('.public-tab').find("li.current").click();
							}, null);
						}
					});
				}
			});
		});
		
		if($('#searchButton')){
			$("#searchButton").bind("click", function(){
				$("#agencyForm").attr("action", Domain.cms_path + "/homepage/agency/agencyRec.htm");
				$("#agencyForm").submit();
			});
		}
		
		if($('#checkAll')){
			$('#checkAll').bind('click',function(){
				var ch = document.getElementById('checkAll').checked;
				$(".public-table :checkbox").attr("checked", ch);  
			});
		}
		
		if($("#cancelRec")){
			$("#cancelRec").bind("click", function(){
				var obj = $(".public-table").find('input:checkbox[name="ids"]:checked');
				if(obj.length == 0){
					Tips.showAlertWin({"info" : "请选择需要操作的培训基地！"});
				}else{
					$("#tableForm").attr("action", Domain.cms_path + "/homepage/agency/agencyRecBatchUpdate.htm")
					Tips.showConfirmWin({
						'info' : "确定批量取消推荐培训基地！",
						'callFn' : function(){
							Tips.loading({'info':'正在操作，请稍候....'});
							Sender.submitAjax({
								"frm" : "#tableForm",
								"dataType" : "json",
								"fn" : function(result){
									Tips.loaded();
									FieldMsg.drawMessages(result, function(){
										$('.public-tab').find("li.current").click();
									}, null);
								}
							});
						}
					});
				}
			});
		}
	}
})(jQuery, window, document)