(function($, window, document){
	var pub = {};
	window.editCoupon = pub;
	pub.init = function(){
		bindEvent();
	}
	
	//绑定事件
    function bindEvent() {
    	var couponId = $('#couponId').val();
    	if(couponId==0){//如果是修改编辑
    		$('#coupon_batchCode').bind('focusout',autoGenerateCouponCode);
    	}
    	$('#saveBtn').bind('click', saveCoupon); 
    	$('#bindCourse-btn').bind('click', bindCourse);
    	$('#batchDelBtn').bind('click', batchDelBtn);
    	$('#batchCancelBtn').bind('click', batchCancelBtn);
    	
    	$("input[name='coupon.createType']").bind('change', createTypeChange);
    	$("input[name='coupon.couponType']").bind('change', couponTypeChange);
    }
    
    
    /**
     * 保存
     */
    function saveCoupon(){
    	if(!Verify.checkAllVerify("#coupon_form")){ //验证所有字段
    		var position = $(".form-table").offset();
	        $(document).scrollTop(position.top-200);
    		return; 
    	}
    	FieldMsg.clearFieldError("#coupon_form");
    	var couponTypeVal = $("input[name='coupon.couponType']:checked").val();
    	if(couponTypeVal== "COUPON_DISCOUNT"){// 验证折扣比例
    		var discount = $("#coupon_discount").val();
    		if(!Validator.test(/(^[1]{1}$)|(^[0]{1}\.[0-9]{1,2}$)/, discount)){
    			FieldMsg.addFieldError("#coupon_discount", "折扣优惠小于等于1，且最多保留两位小数");
    			return;
    		}
    	}else if(couponTypeVal== "COUPON_AMOUNT"){// 验证优惠金额
    		var amount = $("#coupon_amount").val();
    		if(!Validator.isNotMinusFloat(amount, 2)){
    			FieldMsg.addFieldError("#coupon_amount", "优惠金额不能小于0，且最多保留两位小数");
        		return;
        	}
    	}
    	var createTypeVal = $("input[name='coupon.createType']:checked").val();
    	if(createTypeVal == "CREATE_BATCH"){//批量生成
    		Tips.loading({"info":"批量生成中，请耐心等待！"});
    	}
    	var serializedata = $('#coupon_form').serialize();
		var params = {
			"url" :  Domain.cms_path + "/account/saveCoupon.htm",
			"data" : serializedata,
			"dataType" : "json",
			"fn" : function(result) {
				Tips.loaded();
				if (result.isSuccess) {
					// 提示成功
					Tips.showSuccessMsg({
						"info" : result.message,
						"callFn" : function() {
							window.location.href = Domain.cms_path+"/account/couponManage.htm"
						}
					});
				} else {
					// 提示出错
					Tips.showErrorMsg({
						"info" : result.message,
						"callFn" : function() {
						}
					});
					return;
				}
			}
		}
		Sender.ajax(params);
    }
    
    /**
     * 批量删除
     */
    function batchDelBtn(){
    	var couponId = $('#couponId').val();
    	if(couponId == 0){
    		return;
    	}
    	var param1 = {
			info : "您确定批量删除此优惠券所有优惠号？",
			callFn : function() {
				Sender.ajax({
					"url" : Domain.cms_path + "/account/batchDeleteCoupon.htm",
					"data" : {id:couponId},
					"dataType" : "json",
					"fn" :function(data){
						if(data.isSuccess){
							Tips.showSuccessMsg({
								"info" : data.promptMsg,
								"callFn" : function() {
									window.location.href = Domain.cms_path+"/account/couponManage.htm"
								}
							});
						}else{
							Tips.showErrorMsg({"info" : data.errorMsg});
						}
					}
				});
			}
		}
    	Tips.showConfirmWin(param1);
    }
    
    /**
     * 批量注销
     */
    function batchCancelBtn(){
    	var couponId = $('#couponId').val();
    	if(couponId == 0){
    		return;
    	}
    	var param1 = {
			info : "您确定批量注销此优惠券所有优惠号？",
			callFn : function() {
				Sender.ajax({
					"url" : Domain.cms_path + "/account/batchCancelCoupon.htm",
					"data" : {id:couponId},
					"dataType" : "json",
					"fn" :function(data){
						if(data.isSuccess){
							Tips.showSuccessMsg({
								"info" : data.promptMsg,
								"callFn" : function() {
									window.location.href = Domain.cms_path+"/account/couponManage.htm"
								}
							});
						}else{
							Tips.showErrorMsg({"info" : data.errorMsg});
						}
					}
				});
			}
		}
    	Tips.showConfirmWin(param1);
    }
    
    /**
     * 生成类型change
     */
    function createTypeChange(){
    	if($(this).val() == "CREATE_SINGLE"){
    		$("#createNumTr").hide();
    		$("#coupon_createNum").removeAttr("notNull");
    		$("#coupon_createNum").removeAttr("regex");
    		$("#coupon_createNum").removeAttr("dataType");
    		$("#coupon_createNum").removeAttr("maxValue");
    		
    		$("#couponCodeTr").show();
    		$('#coupon_batchCode').unbind("focusout").bind('focusout',autoGenerateCouponCode);
    	}else{
    		$("#createNumTr").show();
    		$("#coupon_createNum").attr("notNull",true);
    		$("#coupon_createNum").attr("regex","/^[1-9]*[0-9][0-9]*$/");
    		$("#coupon_createNum").attr("dataType","integer");
    		$("#coupon_createNum").attr("maxValue",40000);
    		
    		$("#couponCodeTr").hide();
    		$('#coupon_batchCode').unbind("focusout");
    	}
    }
    
    /**
     * 优惠类型change
     */
    function couponTypeChange(){
    	if($(this).val() == "COUPON_DISCOUNT"){
    		$("#discountTr").show();
    		$("#amountTr").hide();
    		$("#coupon_discount").attr("notNull",true);
    		$("#coupon_amount").removeAttr("notNull");
    	}else{
    		$("#amountTr").show();
    		$("#discountTr").hide();
    		$("#coupon_discount").removeAttr("notNull");
    		$("#coupon_amount").attr("notNull",true);
    	}
    }
    
    /**
     *  绑定课程
     */
    function bindCourse(){
    	$("#bindCourseDiv_left").html("");
    	$("#bindCourseDiv_right").html("");

    	// 向右添加事件
    	$('#bindCourseDiv_left').unbind().on('click','li a',function(){
			var contentType = $("#bindCourseDiv_contentType").val();
			var type_str = "<span class='c-red'>[C]</span>";
			if(contentType == "CONCERT") {
				type_str = "<span class='c-red'>[Y]</span>";
			}else if(contentType=="VOD") {
				type_str = "<span class='c-red'>[S]</span>";
			}
    		$(this).parent('li').removeClass('hover').prepend(type_str).appendTo($('#bindCourseDiv_right'));
    	});
    	
    	// 向左移除事件
    	$('#bindCourseDiv_right').unbind().on('click','li a',function(){
			var contentType = $("#bindCourseDiv_contentType").val();
			if($(this).siblings("[name='ids']").attr("contentType") == contentType){
				$(this).parent('li').children('span').remove();
				$(this).parent('li').removeClass('hover').appendTo($('#bindCourseDiv_left'));
			}else{
				$(this).parent('li').remove();
			}
    	});
    	
    	// 全部添加
        $("#bindCourseDiv_addAll").unbind().bind("click", function(){
        	var contentType = $("#bindCourseDiv_contentType").val();
			var type_str = "<span class='c-red'>[C]</span>";
			if(contentType == "CONCERT") {
				type_str = "<span class='c-red'>[Y]</span>";
			}else if(contentType=="VOD") {
				type_str = "<span class='c-red'>[S]</span>";
			}
			$('#bindCourseDiv_left').children("li").each(function(){
				$(this).removeClass('hover').prepend(type_str).appendTo($('#bindCourseDiv_right'));
			});
        });
        
        // 全部移除
        $("#bindCourseDiv_removeAll").unbind().bind("click", function(){
        	var contentType = $("#bindCourseDiv_contentType").val();
        	$('#bindCourseDiv_right').children("li").each(function(){
        		if($(this).find("[name='ids']").attr("contentType") == contentType){
        			$(this).children('span').remove();
        			$(this).removeClass('hover').appendTo($('#bindCourseDiv_left'));
	        	}else{
					$(this).remove();
				}
			});
        });
        
        // 类型切换
        $("#bindCourseDiv_contentType").unbind().bind("change", function(){
        	var contentType = $("#bindCourseDiv_contentType").val();
        	Sender.ajax({
    			"url" : Domain.cms_path + "/account/gainCourseNoBind.htm?contentType=" + contentType,
    			"data" : $("#bindCourseDiv_form").serialize(),
    			"dataType" : "json",
    			"fn" :function(data){
    				$("#bindCourseDiv_left").html("");
    				for(var i=0;i<data.length;i++){
            			var li=$("<li>"+data[i].courseName+"<a href='javascript:;'></a><input type='hidden' name='ids' contentType="+data[i].contentType+" value="+data[i].id+"></li>");
            			$('#bindCourseDiv_left').append(li);
        			}
    				// 绑定js效果
    				$('.org-list li').hover(function(){
                		$(this).addClass('hover');
                	},function(){
                		$(this).removeClass('hover');
                	});
    			}
    		});
        });
        
        // 选择课程确定
        $("#bindCourseDiv_submit").unbind().bind("click", function(){
        	var showStr = "";
        	var valueStr = "";
			$('#bindCourseDiv_right').children("li").each(function(){
				showStr += $(this).text().substring(3)+",";
				valueStr += $(this).find("[name='ids']").val()+","
			});
			if(showStr.length > 0){
				showStr = showStr.substring(0,showStr.length-1)
				valueStr = valueStr.substring(0,valueStr.length-1)
			}
			$("#bindCourse-btn").attr("isFirst", false);
			$("#bindCourse_value").val(valueStr);
			$("#showCourse").text(showStr);
			$("#bindCourseDiv .close").click();
        });
        
        // 默认加载数据
        var couponId = $(this).attr("dataValue");
        var isFirst = $(this).attr("isFirst");
        var idsStr = $("#bindCourse_value").val();
        Sender.ajax({
        	"url" : Domain.cms_path + "/account/gainCourseBinded.htm",
        	"data" : {"id":couponId, "isFirst":isFirst, "idsStr":idsStr},
        	"dataType" : "json",
        	"async" : false,
        	"fn" : function(result){
        		for(var i=0;i<result.length;i++){
        			var type_str = "<span class='c-red'>[C]</span>";
					if(result[i].contentType == "CONCERT") {
						type_str = "<span class='c-red'>[Y]</span>";
					}else if(result[i].contentType=="VOD") {
						type_str = "<span class='c-red'>[S]</span>";
					}
        			var li=$("<li>"+type_str+result[i].courseName+"<a href='javascript:;'></a><input type='hidden' name='ids' contentType="+result[i].contentType+" value="+result[i].id+"></li>");
        			$('#bindCourseDiv_right').append(li);
    			}
        		// 绑定js效果
        		$('.org-list li').hover(function(){
            		$(this).addClass('hover');
            	},function(){
            		$(this).removeClass('hover');
            	});
        		
        		var contentType = $("#bindCourseDiv_contentType").val();
        		Sender.ajax({
        			"url" : Domain.cms_path + "/account/gainCourseNoBind.htm?contentType=" + contentType,
        			"data" : $("#bindCourseDiv_form").serialize(),
        			"dataType" : "json",
        			"fn" :function(data){
        				for(var i=0;i<data.length;i++){
                			var li=$("<li>"+data[i].courseName+"<a href='javascript:;'></a><input type='hidden' name='ids' contentType="+data[i].contentType+" value="+data[i].id+"></li>");
                			$('#bindCourseDiv_left').append(li);
            			}
        				// 绑定js效果
        				$('.org-list li').hover(function(){
                    		$(this).addClass('hover');
                    	},function(){
                    		$(this).removeClass('hover');
                    	});
        			}
        		});
        	}
        });
        
    	var param={
			div:"#bindCourseDiv", 
			closeObject:"#bindCourseDiv .close, #bindCourseDiv .reset"
		}
    	Box.showDiv(param);
    }
    
    /**
     * 根据批次号自动生成优惠券号
     */
    function autoGenerateCouponCode(){
    	var couponCode = $('#coupon_couponCode').val();
    	var batchCode = $('#coupon_batchCode').val();
    	if (batchCode == '') {
			return;
		}
		if(null != couponCode && couponCode != '' && couponCode != undefined ){
			var temp = couponCode.substr(0,4);
			if(temp == batchCode){
				return;
			}
		}
		
		var params = {
			"url" :  Domain.cms_path + "/account/autoGenerateCouponCode.htm",
			"dataType" : "text",
			"data" : {
				"batchCode" : batchCode,
			},
			"fn" : function(result) {
				if (null == result || result == "") {
					return;
				}
				$('#coupon_couponCode').val(result);
			}
		}
		Sender.ajax(params);
    }
    
})(jQuery, window, document);
