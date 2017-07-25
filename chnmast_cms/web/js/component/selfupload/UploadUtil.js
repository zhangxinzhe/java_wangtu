/**
 * 公司自己的上传控件
 */
var SelfUploadUtil = function() {
    /**
     * 创建上传对象
     * @param config {url,path,}
     */
    function create(config) {
    	//基本参数确定
    	var id = config.id;//控件id
    	var path = UploadUtil.buildPath(config.path);//文件保存路径
    	var customId = config.customId;//自定义配置
    	var url = FileSystemConfig.localUploadUrl;//上传服务器接口地址
    	var fileSize = config.fileSize;
    	var serverType = config.serverType?config.serverType:FileSystemConfig.fileType;
    	var layout = config.layout?config.layout:'portrait';//buttonOnly、landscape、portrait
    	var xmlUrl = config.xmlUrl;
    	var width = config.width;
    	var height = config.height;
    	var tip = config.tip;
    	var blockSize = config.blockSize?config.blockSize:2048;
    	var uniqControl = config.uniqControl?config.uniqControl:"";
    	if(serverType == 4){
			url = FileSystemConfig.upyunUploadUrl;
    	}
    	var swfUrl = Domain.cms_path + '/sysfile/flash/UploadPlugin.swf?' + FileSystemConfig.version;
    	if(config.uploadType == 'block' && url == FileSystemConfig.localUploadUrl){
    		swfUrl = Domain.cms_path + '/sysfile/flash/WUploaderUPV.swf?' + FileSystemConfig.version;//采用分块上传
    	}
    	if(!xmlUrl){
    		xmlUrl = UploadUtil.getJsRootPath() + 'config.xml?' + FileSystemConfig.version;
    		if(xmlUrl.indexOf('http:') != 0){
    			xmlUrl= 'http://' + window.location.host + xmlUrl;
    		}
    	}
    	var beforeUpload = config.beforeUpload;
    	var afterUpload = config.afterUpload;
    	
    	//初始化swf
    	var flashVars={'buttonId':id,'catalog':path,'apiDomain':url,'config':xmlUrl,'fileSize':500,'transmit':0,'layout':layout,'blockSize':blockSize,'uniqControl':uniqControl};
    	if(customId){
    		flashVars['fileFormatType'] = customId;
    	}
    	if(fileSize){
    		flashVars['fileSize'] = fileSize;
    	}
    	if(tip){
    		flashVars['tip'] = tip
    	}
        var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent',quality:'high'};
        var attributes={'id':id};
        //纵向
        if(layout == 'portrait'){
        	width = width?width + '' : '200';
        	height = height?height + '' : '65';
    	//横向
        }else{
        	width = width?width + '' : '500';
        	height = height?height + '' : '35';
        }
        swfobject.embedSWF(swfUrl, id,width,height,'11.2.0','expressInstall.swf',flashVars,params,attributes);

        //事件绑定
        EventMap.putB(id, beforeUpload);//上传前
        EventMap.putA(id, afterUpload);//上传后
    }
    
    //文件上传前事件
    window.beforeUpload = function(id, data) {
    	data = eval('(' + data + ')');
    	data.path = modifyPath(data.path);
    	if(data.fileSize <= 0){
    		return "不允许上传空文件";
    	}
    	var fn = EventMap.getB(id);
    	if(!fn){
    		return true;
    	}
    	var r = fn(data);
    	if(!r){
    		r = true;
    	}
        return r;
    }
    
    //文件上传后事件
    window.afterUpload = function(id, data) {
    	data = eval('(' + data + ')');
    	data.data = eval('(' + data.data + ')');
    	if(!data.path){
    		data.path = data.data.url;
    	}
    	data.path = modifyPath(data.path);
		var fn = EventMap.getA(id);
		var msg = '';
    	if(fn){
    		msg = fn(data);
    	}
    	if(!msg){
    		msg = "上传成功！";
    	}
    	return "{\"success\":\"" + msg + "\"}";
    }
    
    //去掉平台的目录
    function modifyPath(path){
    	return path.substring(UploadUtil.getRoot().length);
    }
    
    //打印日志
    window.flashLog = function(data){
    	alert(data);
    }
    
    //事件绑定
	var EventMap = function() {
        var b = {};
        var a = {};
        var s = {};

        function putB(id, fn) {
            b[id] = fn;
        }

        function getB(id) {
            return b[id];
        }

        function putA(id, fn) {
            a[id] = fn;
        }

        function getA(id) {
            return a[id];
        }

        return {
            'putB': putB,
            'getB': getB,
            'putA': putA,
            'getA': getA
        };
    } ();
    
    return {'create':create};
}();

//加载文件系统参数
var FileSystemConfig;
//上传的工厂
var UploadUtil = {
	'getConfig':function(fn){
		var url = Domain.cms_path + "/upload/config.htm";
		UploadUtil.ajax(url,function(data){
			FileSystemConfig = data;//获取参数
			if(fn){
				fn();
			}
		});
	},
	//创建对象
	'create':function(config){
		if(FileSystemConfig){
			SelfUploadUtil.create(config);
			return;
		}
		
		//获取配置文件之后再执行
		UploadUtil.getConfig(function(){
			SelfUploadUtil.create(config);
		});
	},
	//创建上传路径
	'buildPath':function(path){
		if(!path){
			alert('上传路径不能为空！');
		}
		var filePath = null;
		//通过函数，自定义文件路径
		if(path instanceof Function){
			filePath = path();
		//固定路径
		}else if(typeof path == 'string'){
			filePath = path.substring(0,path.lastIndexOf(".") + 1) + "<extName>";
		//动态设置路径
		}else{
			var obj = path.object;
			var filePath = path.format;
			for(var p in obj ){
				filePath = filePath.replace('<' + p + '>',obj[p]);
			}
		}
		
		return UploadUtil.getRoot() + filePath;
	},
	'getRoot':function(){
		if(FileSystemConfig.fileType == 1){
			return "";
		}
		if(FileSystemConfig.fileType == 4){
			return FileSystemConfig.upyunFileRoot;
		}
		return "";
	},
	'ajax':function(url,fn){
		var fnName = 'runBackFn_' + new Date().getTime() + '_' + parseInt(Math.random() * 1000);
		var and = url.indexOf('?') > 0 ? '&' : '?';
		url += and + 'fn=' + fnName;
		var script = '<script type="text/javascript" src="' + url + '"></script>';
		window[fnName] = function(data){
			fn(data);
		}
		jQuery(script).appendTo('body');
	},
	'getJsRootPath':function(){
		var path = null;
		jQuery('script').each(function(i,item){
    		path = jQuery(item).attr('src');
    		if(!path){
    			return true;
    		}
    		var index = path.indexOf('UploadUtil.js');
    		if(path && index > 0){
    			path = path.substring(0,index);
    			return false;
    		}
    	});
		
		return path;
	}
}

UploadUtil.getConfig();