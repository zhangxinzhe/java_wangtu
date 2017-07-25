/**
 * =============================== 工具函数目录，输入框验证方法 ===============================
 * Verify.checkNotEmpty			检查输入框中是否为空，并且过滤掉了placeholder内容，空格也表示空，验证必填时使用。
 * Verify.checkPlusInt			判断输入框中只能输入大于0的整数
 * Verify.checkNotMinusInt		判断输入框中只能输入大于等于0的整数（即：只能输入数字）   
 * Verify.checkNotMinusFloat	判断输入框中只能输入大于等于0的数值（包括小数）
 * Verify.checkNumRange			判断输入框中的数值是否在指定范围内（如：0-100），包括上下界值
 * Verify.checkLetter			判断输入框中只能输入字母
 * Verify.checkLetterOrNum		判断输入框中只能输入数字或字母
 * Verify.checkLetterOrNumOrUnLine	判断输入框中只能输入数字或字母或下划线
 * Verify.checkMobile			判断输入框中是否为手机号码
 * Verify.checkPhone			判断输入框中是否为固定电话号码
 * Verify.checkUserName			判断输入框中只能输入字母、数字和下划线，并且长度4-25位，即用户名的规则
 * Verify.checkOverLen			判定指定的字段长度是否超过了指定的最大长度（一个汉字算一个字符）
 * Verify.checkOverLen2			判定指定的字段长度是否超过了指定的最大长度（一个汉字算两个字符）
 * Verify.checkOverLen3			判定指定的字段长度是否在指定范围内(1个汉字算2个字符)
 * Verify.checkDateFormat		校验日期格式：YYYY-MM-DD
 * Verify.checkIdCard			校验身份证号是否有效，包括15位和18位
 * Verify.checkHttpIpPort		判断输入框中是否为http://IP:PORT格式地址
 * Verify.checkHttp				判断输入框中是否为http地址格式，即以http://开头的字符串
 * Verify.checkEmail			判断输入框中是否为邮箱地址
 * Verify.checkInvalidChar		判断是否为有效字符
 * Verify.checkSelectCheckbox	判断是否有checkbox被选择，一般用于有选择的删除
 * Verify.checkAllVerify		统一校验输入内容，如果输入的全部是空格，那么将内容设置为空
 * 
 * VerifyConfig.checkConfig     系统参数的验证规则
 */
(function(window, undefined){
	window.Verify = {
		/**
		 *  检查输入框中是否为空，并且过滤掉了placeholder内容，空格也表示空，验证必填时使用。
		 *  element 	元素在jQuery中的selector名字，如id=courseId的，则传入#courseId，不能空
		 *  msgText 	输入框中文标题，用于显示在提示信息中，为空时取msgText属性，取不到则默认为空串
		 */
		checkNotEmpty: function (element, msgText){
			var elem = $(element);
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isEmpty(elem.getVal())){
				FieldMsg.addFieldError(elem, msgText + "不能为空，请输入！", true);
				return false;
			}
			return true;
		},
		
		/**
		 *  判断输入框中只能输入大于0的整数
		 */
		checkPlusInt: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isPlusInt(elem.attr("value"))){
				FieldMsg.addFieldError(elem, msgText + "只能输入大于0的整数！", true);
				return false;
			}
			return true;
		},
		
		/**
		 *  判断输入框中只能输入大于等于0的整数（即：只能输入数字）
		 */
		checkNotMinusInt: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isNumber(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "只能输入大于等于0的整数！", true);
				return false;
			}
			return true;
		},
		
		/**
		 *  判断输入框中只能输入大于等于0的数值（包括小数）
		 *  dl：最多几位小数
		 */
		checkNotMinusFloat: function (element, dl, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isNotMinusFloat(elemVal, dl)){
				var msg = msgText + "只能输入大于等于0的数值！";
				if(dl && dl>0){
					msg = msgText + "只能输入大于等于0的数值，并且最多" + dl + "位小数";
				}
				FieldMsg.addFieldError(elem, msg, true);
				return false;
			}
			return true;
		},
		
		
		/**
		 *  判断输入框中的数值是否在指定范围内（如：0-100），包括上下界值
		 *  min：最小值，为空时取minValue属性，取不到则默认为0
		 *  max：最大值，为空时取maxValue属性，取不到则默认为整数的最大值
		 */
		checkNumRange: function (element, msgText, min, max){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			if(!min) min = elem.attr("minValue");
			if(!min) min = 0;
			if(!max) max = elem.attr("maxValue");
			if(!max) max = Number.MAX_VALUE;
				
			if(Validator.isNotBlank(elemVal)){
				var elemInt = Number(elemVal);
				if(elemInt == "NaN"){
					FieldMsg.addFieldError(elem, msgText + "请输入正确的数值！", true);
					return false;
				}
				if(elemInt<Number(min) || elemInt>Number(max)){
					FieldMsg.addFieldError(elem, msgText + "只能是" + min + "~" + max + "范围内的数值！", true);
					return false;
				}
			}
			return true;
		},
		
		/**
		 *  判断输入框中只能输入字母
		 */
		checkLetter: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isLetter(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "只能为字母！", true);
				return false;
			}
			return true;
		 },
		 
		/**
		 *  判断输入框中只能输入数字或字母
		 */
		checkLetterOrNum: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isLetterOrNum(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "只能为数字或字母！", true);
				return false;
			}
			return true;
		 },
		 
		 /**
		  *  判断输入框中只能输入数字或字母或下划线
		  */
		checkLetterOrNumOrUnLine: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isLetterOrNumOrUnLine(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "只能为字母数字或下划线！", true);
				return false;
			}
			return true;
		 },
		 
		/**
		 *  判断输入框中是否为手机号码
		 */
		checkMobile: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isMobileAll(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "中请输入正确的手机号码！", true);
				return false;
			}
			return true;
		 },
		 
		/**
		 *  判断输入框中是否为固定电话号码
		 */
		checkPhone: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isPhone(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "中请输入正确的固定电话号码！", true);
				return false;
			}
			return true;
		 },
		 
		/**
		 *  判断输入框中只能输入字母、数字和下划线，并且长度4-25位，即用户名的规则
		 */
		checkUserName: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isUserName(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "只能数字、字母和下划线，长度为4-25位！", true);
				return false;
			}
			return true;
		 },
		 
		 /**
		  *  判定指定的字段长度是否超过了指定的最大长度（一个汉字算一个字符）
		  *  maxLen  允许最大长度，为空时取maxLength属性，取不到则默认为整数的最大值
		  *  isNotValBlank：false需要验证，true不验证（2015-11-05添加）
		  */
		 checkOverLen: function (element, msgText, maxLen, isNotValBlank){
			 var elem = $(element);
			 var elemVal = elem.val();
			 if(!msgText) msgText = elem.attr("msgText");
			 if(!msgText) msgText = "";
			 if(!maxLen) maxLen = elem.attr("maxLength");
			 if(!maxLen) maxLen = Number.MAX_VALUE;
			 if(!isNotValBlank) isNotValBlank = false;
			
			 if(!Validator.isNotBlank(elemVal)){
				if(!isNotValBlank){
					FieldMsg.addFieldError(elem, msgText + " 不能为空", true);
					return false;
				}else{
					return true;
				}
			 }

			 var maxlength = parseInt(maxLen);
			 if(Validator.isNotBlank(elemVal) && StringUtil.getLength(elemVal)>maxlength){
				FieldMsg.addFieldError(elem, msgText + "长度超过范围,允许最大长度为 " + maxLen + " 字符（1个汉字算1个字符）", true);
				return false;
			}
			return true;
		 },
		 
		 /**
		  *  判定指定的字段长度是否超过了指定的最大长度（一个汉字算两个字符）
		  *  maxLen  允许最大长度，为空时取maxLength属性，取不到则默认为整数的最大值
		  *  isNotValBlank：false需要验证，true不验证（2015-11-05添加）
		  */
		 checkOverLen2: function (element, msgText, maxLen, isNotValBlank){
			 var elem = $(element);
			 var elemVal = elem.val();
			 if(!msgText) msgText = elem.attr("msgText");
			 if(!msgText) msgText = "";
			 if(!maxLen) maxLen = elem.attr("maxLength");
			 if(!maxLen) maxLen = Number.MAX_VALUE;
			 if(!isNotValBlank) isNotValBlank = false;
			 
			 if(!Validator.isNotBlank(elemVal)){
				 if(!isNotValBlank){
					 FieldMsg.addFieldError(elem, msgText + " 不能为空", true);
					 return false;
				 }else{
					 return true;
				 }
			 }
			 
			 var maxlength = parseInt(maxLen);
			 if(Validator.isNotBlank(elemVal) && StringUtil.getLength2(elemVal)>maxlength){
				FieldMsg.addFieldError(elem, msgText + "长度超过范围,允许最大长度为 " + maxLen + " 字符（1个汉字算2个字符）", true);
				return false;
			}
			return true;
		 },
		 
		 /**
		  *  判定指定的字段长度是否在指定范围内(1个汉字算2个字符)
		  *  maxLen  允许最大长度，为空时取maxLength属性，取不到则默认为整数的最大值
		  */
		 checkOverLen3: function (element, msgText,minLen ,maxLen){
			 var elem = $(element);
			 var elemVal = elem.val();
			 if(!msgText) msgText = elem.attr("msgText");
			 if(!msgText) msgText = "";
			 if(!minLen) minLen = elem.attr("minLength");
			 if(!minLen) minLen = Number.MIN_VALUE;
			 if(!maxLen) maxLen = elem.attr("maxLength");
			 if(!maxLen) maxLen = Number.MAX_VALUE;
			 
			 if(!Validator.isNotBlank(elemVal)){
				 FieldMsg.addFieldError(elem, msgText + " 不能为空", true);
				 return false;
			 }
			 var minlength = parseInt(minLen);
			 var maxlength = parseInt(maxLen);
			 if(Validator.isNotBlank(elemVal) && (StringUtil.getLength2(elemVal)>maxlength||StringUtil.getLength2(elemVal)<minlength)){
				FieldMsg.addFieldError(elem, msgText + " 长度不在"+minLen+"~"+maxLen+"范围内（1个汉字算2个字符）", true);
				return false;
			}
			return true;
		 },
		 
		 /**
		  * 校验日期格式：YYYY-MM-DD
		  */
		 checkDateFormat: function (element, msgText){
			 var elem = $(element);
			 var elemVal = elem.val();
			 if(!msgText) msgText = elem.attr("msgText");
			 if(!msgText) msgText = "";
			 
			 if(Validator.isNotBlank(elemVal) && !Validator.isDateFormat(elemVal)){
				 FieldMsg.addFieldError(elem, msgText 
						 + '必须为1900-1-1到2038-12-31之间的日期，格式为：yyyy-mm-dd，并注意日期和月份的有效值', true);
				 return false;
			 }
			 return true;
		 },
		 
		 /**
		  * 校验身份证号是否有效，包括15位和18位
		  */
		 checkIdCard: function (element, msgText){
			 var elem = $(element);
			 var elemVal = elem.val();
			 if(!msgText) msgText = elem.attr("msgText");
			 if(!msgText) msgText = "";
			 
			 if(Validator.isNotBlank(elemVal)){
				 if(Validator.isIdCard15(elemVal) || Validator.isIdCard18(elemVal)) {
					 // 验证身份证号中出生日期是否正确
					 var year,month,day,date;
			     	 if (StringUtil.getLength(elemVal) == 15){
			     		 year = "19" + elemVal.substring(6, 8);
			     		 month = elemVal.substring(8, 10);
			     		 day = elemVal.substring(10, 12)
			     		 date = year + "-" + month + "-" + day;
			     	 }
			     	 else if (StringUtil.getLength(elemVal) == 18){
			     		 year = elemVal.substring(6, 10);
			     		 month = elemVal.substring(10, 12);
			     		 day = elemVal.substring(12, 14)
			     		 date = year + "-" + month + "-" + day;
			     	 }
			     	 if(!Validator.isDateFormat(date)){
			     		 FieldMsg.addFieldError(element, msgText + '号码中出生日期不对！', true);
				         return false;
			     	 }
			     }else{
			    	 FieldMsg.addFieldError(element, msgText + '号码不对！', true);
			         return false;
			     }
			 }
			 return true;
		 },
		 
		/**
		 *  判断输入框中是否为http://IP:PORT格式地址
		 */
		checkHttpIpPort: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isValidHttpIpPort(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "请确认符合http://IP:PORT格式！", true);
				return false;
			}
			return true;
		 },

		/**
		 *  判断输入框中是否为http地址格式，即以http://开头的字符串
		 */
		checkHttp: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !StringUtil.startWith(elemVal, "http://")){
				FieldMsg.addFieldError(elem, msgText + "请确认符合http的地址格式！", true);
				return false;
			}
			return true;
		 },
		 
		/**
		 *  判断输入框中是否为邮箱地址
		 */
		checkEmail: function (element, msgText){
			var elem = $(element);
			var elemVal = elem.val();
			if(!msgText) msgText = elem.attr("msgText");
			if(!msgText) msgText = "";
			
			if(Validator.isNotBlank(elemVal) && !Validator.isEmail(elemVal)){
				FieldMsg.addFieldError(elem, msgText + "请输入正确的邮箱地址！", true);
				return false;
			}
			return true;
		 },
		 
		 /**
		  * 判断是否为有效字符
		  */
		 checkInvalidChar: function (element, msgText){
			 var elem = $(element);
			 var elemVal = elem.val();
			 if(!msgText) msgText = elem.attr("msgText");
			 if(!msgText) msgText = "";
			
			 if(Validator.isNotBlank(elemVal) && Validator.hasIllegalChar(elemVal)){
				 FieldMsg.addFieldError(elem, msgText + "不能有< > ~ ! @ # $ % & | *等字符！", true);
				 return false;
			 }
			 return true;
		 },
		 
		 /**
		  *  判断是否有checkbox被选择，一般用于有选择的删除
		  *  frm 			jQuery的selector名字（如id=openDiv的，则传入#openDiv），一般是指form容器
		  *  checkboxName 	要选中的checkbox的name值，字符串，不为空
		  */
		 checkSelectCheckbox: function (frm, checkboxName){
			 var flag = false;
			 $(frm +" input[name="+checkboxName+"]").each(function(){
				 //如果被选中   
				 if($(this).attr("checked")){
					 flag = true;
					 return false;
			     } 
			 });
			 if(!flag){
				 Tips.showWarnWin("没有行被选择，请先选择！");
			 }
			 return flag;
		 },
		 
		 /**
		  * 判断系统参数中课程图片code与名称的对应关系，是系统参数专用的
		  */
		 checkPicTypeValue:function(element,msgText){
			 	var elem = $(element);
				var elemVal = elem.val();  
				if(!msgText) msgText = elem.attr("msgText");
				if(!msgText) msgText = "";
				if(Validator.isNotBlank(elemVal) && !Validator.isPicTypeValue(elemVal)){
					FieldMsg.addFieldError(elem, msgText + "输入格式不对,格式如 xx:小学;cz:初中", true);
					return false;
				}
				return true; 
		 },

		/**
		 * 统一校验输入内容，如果输入的全部是空格，那么将内容设置为空
		 * 校验规则有：
		 * 1、必输，设置属性 notNull="true"
		 * 2、最大长度，设置性 maxLength="20"
		 * 3、满足正则表达式，设置属性 regex="/^\d+$/" regexMsg="请输入数字"
		 * 4、满足javascript，设置属性 validateScript="checkFunction"
		 * 5、验证数字范围，设置属性 dataType（必须） maxValue minValue nonnegative（非负数） integerLength（多少位整数） decimalLength（多少位小数）
		 * 校验的输入框有：input、select、textarea三种
		 * 
		 * @param 	frm： 		jQuery的selector名字（如id=openDiv的，则传入#openDiv ）或对象，一般是指form容器的#id或输入框对象
		 * @return 	true：		表示验证成功，false：表示验证失败
		 * 
		 */
		 checkAllVerify: function (frm){
		 	var notValidate = false;
		 	
		 	var tgs = ["INPUT:not(:file)", "SELECT", "TEXTAREA"];
		 	var len = tgs.length;
		 	for(var j = 0; j < len; j ++){
		 		if(frm){
		 			if(typeof(frm) ==  "string"){
		 				//容器id
		 				var os = jQuery(frm + " " + tgs[j]);
		 			}else{
		 				//某一确定的输入元素对象
		 				len = 1;
		 				var os = jQuery(frm);
		 			}
		 		}
		 		else{
		 			var os = jQuery(tgs[j]);
		 		}
		 		if(os){
		 			var os_len = os.length;
		 			for(var i = 0; i < os_len; i ++){
		 				var o = os[i];
		 				FieldMsg.clearFieldError(o);
		 				var osName = o.getAttribute("msgText");
		 				if(!osName){
		 					osName = o.getAttribute("msgText");
		 				}
		 				if(!osName){
		 					osName = "";
		 				}
		 						
		 				o.value = StringUtil.trim(o.value);
		 				
		 				var disabled = o.getAttribute("disabled");
		 				if(disabled){
		 					continue;
		 				}
		 				var notNull = o.getAttribute("notNull");
		 				if(!notNull){
		 					notNull = o.getAttribute("notnull");
		 				}
		 				
		 				// 1、验证必输
		 				if(notNull && (notNull.toLowerCase() == "yes" || notNull == "1" || notNull.toLowerCase() == "true" || notNull.toLowerCase() == "notnull")){
		 					var v = o.value;
		 					if(!v || StringUtil.trim(v) == ""){	
		 						FieldMsg.addFieldError(o, osName + " 不能为空！", true);
		 						notValidate = true;
		 					}
		 				}
		 				
		 				if(StringUtil.trim(o.value).length > 0){
		 					// 2、验证最大长度
		 					var maxLength = o.getAttribute("maxLength");
		 					if(!maxLength){
		 						maxLength = o.getAttribute("maxlength");
		 					}
		 					if(maxLength && maxLength > 0 && maxLength < 100000){
		 						var valueLength = o.value.length;
		 						if(valueLength > maxLength){
		 							FieldMsg.addFieldError(o, osName + " 长度为" + valueLength + "个字符，超出了最大长度限制：" + maxLength + "个字符", true);
		 							notValidate = true;
		 						}
		 					}
		 					
		 					// 3、验证正则表达式
		 					var regex = o.getAttribute("regex");
		 					if(regex){
		 						var msg = o.getAttribute("regexMsg");
		 						if (regex.indexOf("/") == 0){
		 							regex = regex.substring(1);
		 						}
		 						if(regex.indexOf("/") == regex.length - 1){
		 							regex = regex.substring(0, regex.length - 1);
		 						}
		 						var re = new RegExp(regex);
		 						if (!re.test(o.value)){
		 							if(!msg) msg = ""; 
		 							FieldMsg.addFieldError(o, osName + " 不正确！" + msg, true);
	 								notValidate = true;
		 						}
		 					}
		 					
		 					// 4、验证javascript
		 					var validateScript = o.getAttribute("validateScript");
		 					if(!validateScript){
		 						validateScript = o.getAttribute("validatescript");
		 					}
		 					if(validateScript){
		 						var ret = true;
		 						try{
		 							ret = eval(validateScript)(StringUtil.trim(o.value));
		 						}
		 						catch(e){
		 							try{
		 								ret = eval(validateScript + "('" + StringUtil.trim(o.value) + "');");
		 							}
		 							catch(ee){
		 							}
		 						}
		 						if(!ret || ret == false){
		 							//方法中自定义错误消息，这里不显示错误提示
		 							//FieldMsg.addFieldError(o, osName + " 不正确！", true);
		 							notValidate = true;
		 						}
		 					}
		 					
		 					// 5、验证数字范围（包括整数、小数、多少位）
		 					var dataType = o.getAttribute("dataType");
		 					if (!dataType){
		 						dataType = o.getAttribute("datatype");
		 					}
		 					if(dataType && (dataType == "integer" || dataType == "float")){
		 						var max = o.getAttribute("maxValue");
		 						var min = o.getAttribute("minValue");
		 						var integerLength = o.getAttribute("integerLength");
		 						var nonnegative = o.getAttribute("nonnegative");
		 						var ch = false;
		 						if(dataType == "integer"){
		 							if(dl) dl=null;
		 							if(Validator.isInteger(o.value) == true){
		 								ch = true;
		 								if(!max || !Validator.isInteger(max)){
		 									max = null;
		 								}
		 								if(!min || !Validator.isInteger(min)){
		 									min = null;
		 								}
		 							}
		 						}
		 						else if(dataType == "float"){
		 							var dl = o.getAttribute("decimalLength");
		 							if(!dl){
		 								dl= o.getAttribute("decimallength");
		 							}
		 							
		 							if(Validator.isFloat(o.value, dl)== true){
		 								ch = true;
		 								if(!max || !Validator.isFloat(max)){
		 									max = null;
		 								}
		 								if(!min || !Validator.isFloat(min)){
		 									min = null;
		 								}
		 							}
		 						}
		 						if(ch){
		 							o.value = parseFloat(o.value);
		 							if(max){
		 								if(o.value > parseFloat(max)){
		 									FieldMsg.addFieldError(o, osName + " 最大值不能超过" + max, true);
		 									notValidate = true;
		 								}
		 							}
		 							if(min){
		 								if(o.value < parseFloat(min)){
		 									FieldMsg.addFieldError(o, osName + " 最小值不能小于" + min, true);
		 									notValidate = true;
		 								}
		 							}
		 							if(integerLength){
		 								var beginIndex=0;
		 								if(o.value.indexOf("+")>=0 || o.value.indexOf("-")>=0){
		 									beginIndex=1;
		 								}
		 								var v;
		 								if (o.value.indexOf(".") >= 0){
		 									v = o.value.substring(beginIndex, o.value.indexOf("."));
		 								}
		 								else{
		 									v = o.value.substring(beginIndex, o.value.length);
		 								}
		 								if(v.length > integerLength){
		 									if (dl){
		 										FieldMsg.addFieldError(o, osName + " 输入格式不对，要求是不超过" + integerLength + "位整数，" + dl + "位小数的数值", true);
		 										notValidate = true;
		 									}
		 									FieldMsg.addFieldError(o, osName + " 输入格式不对，要求是不超过" + integerLength + "位整数位的数值", true);
		 									notValidate = true;
		 								}
		 							}
		 							if(nonnegative){
		 								if(nonnegative && (nonnegative.toLowerCase() == "yes" || nonnegative == "1" || nonnegative.toLowerCase() == "true" )){
		 									if(o.value.indexOf("-")>=0){
		 										FieldMsg.addFieldError(o, osName + " 输入格式不对，只能输入非负数值型数据", true);
		 										notValidate = true;
		 									}
		 								}
		 							}
		 						}
		 						else{
		 							if (dl)
		 								FieldMsg.addFieldError(o, osName + " 输入格式不对，要求是不超过" + dl + "位小数的数值", true);
		 							else
		 								FieldMsg.addFieldError(o, osName + " 输入格式不对", true);
		 							notValidate = true;
		 						}
		 					}
		 				}
		 			}
		 		}
		 	}

		 	return !notValidate;
		 }
		 
	};
	
	/**
	 * 系统参数的验证规则
	 * element jquery传入需要验证的id，如 #id
	 */
	window.VerifyConfig = {
			 checkConfig: function (element,msgText){
				    if(!msgText) msgText = $(element).attr("msgText");
					if(!msgText) msgText = "";
					var a =$(element).attr('validateConfig');
					if(a==""){
						return true;
					}
				    if(a.indexOf('int-range')>0){
				    	return Verify.checkNumRange(element, msgText, a.substring(17).split('-')[0],a.substring(17).split('-')[1]);
				    }
				    if(a.indexOf('max-length')>0){
				    	return Verify.checkOverLen(element, msgText, a.substring(18).split('-')[0]);
				    }
				    if(a.indexOf('required')==0){
				    	return Verify.checkNotEmpty(element,msgText);
				    }
				    if(a.indexOf('validate-url')==0){
				    	return Verify.checkHttp(element, msgText);
				    }
				    if(a.indexOf('pic-type-value')>0){
				    	return Verify.checkPicTypeValue(element, msgText);
				    }
				    return false;
			 }	
	};
})(window);


