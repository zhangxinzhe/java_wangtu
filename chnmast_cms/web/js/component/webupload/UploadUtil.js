//监听分块的上传，并创建md5
WebUploader.Uploader.register({
	'before-send': 'md5'
	}, {
	md5: function(block) {
		if(block.file.blockSend){
			return block.file.blockSend(block);
		}
	}
});

//文件开始上传监听
WebUploader.Uploader.register({
	'before-send-file': 'md5'
	}, {
	md5: function(file) {
		if(file.fileSend){
			return file.fileSend(file);
		}
	}
});

var WebUploadUtil_Block = function(){
	/**
     * 创建上传对象
     * @param config {url,path,}
     */
    function create(config) {
    	var $ = jQuery;
    	var block_md5 = null;//分块md5
    	var token_secret = null;
   	 	var	 save_token = null;
   	 	var expiration = new Date().getTime() + 5*60*60*1000;
   	 	
   	 	var id = config.id;
   	 	var initUpload = config.initUpload ? config.initUpload : function(){};
   	 	var beforeUpload = config.beforeUpload ? config.beforeUpload : function(){};
   	 	var afterUpload = config.afterUpload ? config.afterUpload : function(){};
   	 	var error = config.error ? config.error : function(){};
   	 	var progress = config.progress;
   	 	var accept = UploadUtil.createAccept(config);
   	 	var path = UploadUtil.buildPath(config.path);
   	 	var realPath = path;
   	 	var formApi = FileSystemConfig.formApi?FileSystemConfig.formApi:'';
   	 	var fileSize = config.fileSize;//单位M
   	 	var multiple = config.multiple ? true : false;//是否选择多个文件
   	 	var tokenMap = {};
   	 	
   	 	var options = {
	   	 		// 自动上传。
		        auto: false,
		        // swf文件路径
		        swf: UploadUtil.getJsRootPath() + '/js-webuploader/Uploader.swf',
		        // 文件接收服务端。
		        server: FileSystemConfig.uploadUrl_block,
		        // 选择文件的按钮。可选。
		        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
		        pick: {
		        	'id':'#' + id,
		        	'multiple':multiple
		        },
		        // 只允许选择文件，可选。
		        'accept': accept,
		        threads:1,//上传并发数。允许同时最大上传进程数。
		        chunked:true,//是否要分片处理大文件上传
		        chunkSize:2 * 1024 * 1024, //分多大一片？ 默认大小为5M.
		        //chunkSize:102400, 
		        chunkRetry:2, //如果某个分片由于网络问题出错，允许自动重传多少次？
		        //fileNumLimit:1, //验证文件总数量, 超出则不允许加入队列。
		        compress:false,
		        //fileSingleSizeLimit验证单个文件大小是否超出限制, 超出则不允许加入队列
		        //fileSizeLimit://int验证文件总大小是否超出限制, 超出则不允许加入队列
		        prepareNextFile: true
   	 	}
   	 	
   	 	//文件大小
   	 	if(fileSize){
   	 		options.fileSingleSizeLimit = fileSize * 1024 * 1024;
   	 	}
   	 	
   	 	//创建uploader
    	var uploader = WebUploader.create(options);
    	
   	 	//文件开始上传的时候
    	function fileSend(file){
			var deferred = WebUploader.Deferred();
			//只md5文件开始
			var end = file.size;
			if(UploadUtil.isLocal() && end > uploader.options.chunkSize){
				end = uploader.options.chunkSize;
			}
			if(file.file_hash){
				end = 0;
			}
			uploader.md5File(file,0,end).progress(function(percentage) {
				if(end > 0){
					progress(file.fileIndex,percentage.toFixed(2),true);
				}
	        }).then(function(md5) {
	        	//发现MD5值出现冲突，故加此段
	        	if(UploadUtil.isLocal()){
	        		md5 = jQuery.md5(file.lastModifiedDate + file.name + file.size + md5);
	        	}
	        	
	        	if(end == 0){
	        		md5 = file.file_hash;
	        	}else{
	        		file.file_hash = md5;
	        	}
				var policy = {
	                     'path':file.realPath,
	                     'expiration':expiration,
	                     'file_blocks':Math.ceil(file.size/uploader.options.chunkSize),
	                     'file_hash':md5,
	                     'file_size':file.size
	                 };
				 
	             if(UploadUtil.isLocal()){
	            	 policy.step = 1;
	            	 policy.type = 'block';
	            	 policy.block_size = options.chunkSize;
	             }
                 var signature = 'expiration' + policy.expiration;
                 signature += 'file_blocks' + policy.file_blocks;
                 signature += 'file_hash' + policy.file_hash;
                 signature += 'file_size' + policy.file_size;
                 signature += 'path' + policy.path;
                 signature += formApi;
                 signature = jQuery.md5(signature);
                 
                 var historyData = file.file_history;
                 if(historyData != null){
            		 token_secret = historyData.token_secret;
                	 save_token = historyData.save_token;
                	 deferred.resolve([file]);
                 }else{
                	 //通知开始上传
                     toServer(policy,signature,function(_status,data){
                    	 if(_status == 'success'){
                    		 token_secret = data.token_secret;
                        	 save_token = data.save_token;
                        	 var status = data.status;
                        	 file.file_status = status;
                        	 //全部上传完成
                        	 if(status[status.length-1] == 1){
                        		 file.setStatus(4);//4表示成功
                        		 uploader.trigger( 'uploadProgress', file, 1 );//触发进度
                        	 }
                        	 file.file_history = data;
                     	 }

                     	 if(_status == 'error'){
                     		 error({'code':'send_error','msg':'文件上传失败','reason':null,'file':file});
                     		 //uploader.cancelFile(file);
                     		 uploadNextFile();
                     	 }

                     	 deferred.resolve([file]);
                     });
                 }
			});
			return deferred.promise();
		}

    	//上传分块的是否创建，计算md5
    	function blockSend(block) {
    		var status = block.file.file_status;
    		
			//跳过已上传的分块
			if(status && status[block.chunk] == 1){
				var file = block.file;
				var upload;
				$.each(uploader._widgets,function(i,item){
					if(item._nextBlock){
						upload = item;
						return false;
           		 	}
           	 	});
				var newBlock;
				$.each(status,function(i,item){
					if(item == 0){
						return false;
					}
					//未上传减一
					file.remaning--;
					//移动游标
					newBlock = upload._nextBlock();
					if(newBlock){
						//第一块和第一个没上传的块数据交换
						newBlock.percentage = 1;//分块上传完成
						delete newBlock.transport;
					}
				});
				if(newBlock){
					//改成新的
					block.chunk = newBlock.chunk;
					block.start = newBlock.start;
					block.end = newBlock.end;
					//换成新的文件
					block.blob = block.chunks === 1 ? file.source : file.source.slice( block.start, block.end );
				}
			}
			if(UploadUtil.isLocal()){
				block_md5 = WebUploader.guid();
			}else{
				var deferred = WebUploader.Deferred();
				uploader.md5File(block.blob,0,block.blob.size).then(function(val) {
					block_md5 = val;
					deferred.resolve([block]);
				});
				return deferred.promise();
			}
		}
    	
    	//每次放入队列之前执行
    	var allFiles = null;
    	var errs = null;
    	uploader.on( 'beforeFileQueued', function(file) {
    		if(!allFiles){
    			//上传前进行重置
    			uploader.reset();
    			//初始化上传数组
    			allFiles = new Array();
    		}
    		if(!errs){
    			errs = new Array();
    		}
    		file.fileIndex = allFiles.length;
    		allFiles.push(file);
    	});
    	
    	//validate不通过
    	uploader.on('error', function(type,fs,file){
    		var data = null;
    		if('Q_TYPE_DENIED' == type){
    			data = {'code':'type_error','msg':'文件类型不符合'};
    		}else if('F_EXCEED_SIZE' == type){
    			data = {'code':'size_error','msg':'文件不能大于' + fileSize + 'M'};
    			data.file = file;
    		}else{
    			data = {'code':'other_error','msg':'文件验证不通过'};
    		}
    		data.file = fs;
    		errs.push(data);
    	});
    	
    	//全部触发之后执行
    	uploader.on( 'filesQueued', function(sfiles) {
    		//初始化
    		initUpload(allFiles);
    		//处理错误信息
    		for(var i in errs){
    			error(errs[i]);
    		}
    		//清理临时数据
    		allFiles = null;
    		errs = null;
    	});
    	
    	var files = null;
    	//选择了文件，可以多个
    	uploader.on( 'fileQueued', function(file) {
    		file.fileSend = fileSend;
    		file.blockSend = blockSend;
    		
    		//禁掉上传按钮
    		uploader.request('disable');
    		
    		//触发beforeUpload
    		var name = file.name.substring(0,file.name.lastIndexOf("."));
    		var ext = file.name.substring(file.name.lastIndexOf(".") + 1,file.name.length);
    		file.realPath = path.replace('<fileName>',name);
    		file.realPath = file.realPath.replace('<extName>',ext);
    		var data = {
				'fileName':file.name,
				'fileSize':file.size,
				'fileType':file.type,
				'path':UploadUtil.getRelativePath(file.realPath),
				'fileIndex':file.fileIndex
    		}
    		
    		var result = beforeUpload(data);
    		//失败
    		if(result && result != true){
    			errs.push({'code':'other_error','msg':result,'reason':null,'file':file});
    			return;
    		}
    		
    		if(!files){
    			files = new Array();
    			uploader.upload(file);
    		}else{
    			files.push(file);
    		}
    		
    		//预览
    		UploadUtil.showPreview(uploader,file,config.preview);
    	});
    	
    	//开始上传文件
    	uploader.on( 'uploadStart', function(file) {
    	});
    	
    	//分块开始上传
    	uploader.on( 'uploadBeforeSend', function(object,data,headers) {
    		var policy = {
                    'save_token':save_token,
                    'expiration':expiration,
                    'block_index':object.chunk,
                    'block_hash':block_md5
        	};
    		if(UploadUtil.isLocal()){
    			policy.step = 2;
    			policy.type = 'block';
    			policy.path = object.file.realPath;
    		}
    		
            var signature = 'block_hash' + policy.block_hash;
            signature += 'block_index' + policy.block_index;
            signature += 'expiration' + policy.expiration;
            signature += 'save_token' + policy.save_token;
            signature += token_secret;
            signature = jQuery.md5(signature);
            
            data.policy = UploadUtil.policyBase64(policy);
            data.signature = signature;
    	});
    	
    	//分块上传结果
    	uploader.on( 'uploadAccept', function(object,ret) {
    		object.file.file_status = ret.status;
    	});
    	
    	//文件上传成功
    	uploader.on( 'uploadSuccess', function( file,response ) {
            var policy = {
                    'save_token':save_token,
                    'expiration':expiration
            };
            if(UploadUtil.isLocal()){
            	policy.step = 3;
    			policy.type = 'block';
    			policy.path = file.realPath;
    		};

            var signature = 'expiration' + policy.expiration;
            signature += 'save_token' + policy.save_token;
            signature += token_secret;
            signature = jQuery.md5(signature);
            
            //通知开始上传
            var myFile = file;
            toServer(policy,signature,function(status,data){
            	if(status == 'success'){
            		data.fileIndex = myFile.fileIndex;
            		//data.path =  UploadUtil.getRelativePath(myFile.realPath);
            		data.name = myFile.name;
            		afterUpload(data);
            	}
            	
            	if(status == 'error'){
            		error({'code':'send_error','msg':'文件上传失败','reason':null,'file':myFile});
            	}
            	
            	//上传下一个文件
            	uploadNextFile();
            });
    	});
    	
    	//文件上传失败
    	uploader.on( 'uploadError', function(file,reason) {
    		error({'code':'send_error','msg':'文件上传失败','reason':reason,'file':file});
    		
    		//上传下一个文件
        	uploadNextFile();
    	});
    	
    	uploader.on( 'uploadProgress', function(file, percentage) {
    		progress(file.fileIndex,percentage.toFixed(2));
    	});
    	
    	// 完成上传完了，成功或者失败，重置上传。
	    uploader.on( 'uploadComplete', function(file) {
	    });
	    
	    //当所有文件上传结束时触发
	    uploader.on( 'uploadFinished', function() {
	    	//延迟100毫秒，检测是否还有文件没有上传
	    	setTimeout(function(){
	    		if(!uploader.isInProgress()){
	    			//uploader.reset();
	    			uploader.request('enable');
	    			files = null;
	    		}
	    	},100);
	    });
	    
	    //下载下一个
	    function uploadNextFile(){
	    	var file = null;
	    	if(files){
	    		file = files.shift();
	    	}
	    	if(file){
	    		//还有文件没有上传
				uploader.upload(file);
				return;
	    	}
	    	
	    	//文件上传完成
    		uploader.trigger('uploadFinished');
	    }
	    
	    uploader.toRetry = function(file){
	    	if(files == null){
	    		files = new Array();
	    		uploader.retry(file);
	    		return;
	    	}
	    	files.push(file);
	    }
	    return uploader;
    }
    
    //发消息给server
    function toServer(policy,signature,fn){
        $.ajax({
        	type: 'post',
        	url: FileSystemConfig.uploadUrl_block, 
        	data: {'policy':UploadUtil.policyBase64(policy),'signature':signature},
        	async : false,
        	dataType: 'json',
        	success: function(data){
        		fn('success',data);
        	},
        	error: function(XMLHttpRequest,status,errorThrown){
        		fn('error','');
        	}
        });
    }
    
    return {'create':create};
}();

var WebUploadUtil_Total = function(){
	/**
	 * 
	 */
	function create(config){
    	var $ = jQuery;
    	var block_md5 = null;//分块md5
   	 	var expiration = Math.floor(new Date().getTime()/1000) + 600;//秒
   	 	
   	 	var id = config.id;
   	 	var initUpload = config.initUpload ? config.initUpload : function(){};
   	 	var beforeUpload = config.beforeUpload ? config.beforeUpload : function(){};
   	 	var afterUpload = config.afterUpload ? config.afterUpload : function(){};
   	 	var progress = config.progress ? config.progress : function(){};
   	 	var error = config.error ? config.error : function(){};
   	 	var accept = UploadUtil.createAccept(config);
   	 	var path = UploadUtil.buildPath(config.path);
   	 	var formApi = FileSystemConfig.formApi?FileSystemConfig.formApi:'';
   	 	var fileSize = config.fileSize;//单位M
   	 	var multiple = config.multiple ? true : false;//是否选择多个文件
   	 	
   	 	var options = {
   		        // 自动上传。
   		        auto: true,
   		        // swf文件路径
   		        swf: UploadUtil.getJsRootPath() + '/js-webuploader/Uploader.swf',
   		        // 文件接收服务端。
   		        server: FileSystemConfig.uploadUrl_total,
   		        // 选择文件的按钮。可选。
   		        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
   		        pick: {
		        	'id':'#' + id,
		        	'multiple':multiple
		        },
   		        // 只允许选择文件，可选。
   		        'accept': accept,
   		        //threads:2,//上传并发数。允许同时最大上传进程数。
   		        //chunkSize:102400, 
   		        chunkRetry:2, //如果某个分片由于网络问题出错，允许自动重传多少次？
   		        //fileNumLimit:1, //验证文件总数量, 超出则不允许加入队列。
   		        compress:false
   		        //fileSingleSizeLimit验证单个文件大小是否超出限制, 超出则不允许加入队列
   		        //fileSizeLimit://int验证文件总大小是否超出限制, 超出则不允许加入队列
	    }
   	 	
   	 	//文件大小
   	 	if(fileSize){
   	 		options.fileSingleSizeLimit = fileSize * 1024 * 1024;
   	 	}
   	 	
   	 	//创建uploader
    	var uploader = WebUploader.create(options);
    	
    	//每次放入队列之前执行
    	var allFiles = null;
    	var errs = null;
    	uploader.on( 'beforeFileQueued', function(file) {
    		if(!allFiles){
    			//上传前进行重置
    			uploader.reset();
    			//初始化上传数组
    			allFiles = new Array();
    		}
    		if(!errs){
    			errs = new Array();
    		}
    		file.fileIndex = allFiles.length;
    		allFiles.push(file);
    	});
    	
    	//validate不通过
    	uploader.on('error', function(type,fs,file){
    		var data = null;
    		if('Q_TYPE_DENIED' == type){
    			data = {'code':'type_error','msg':'文件类型不符合'};
    		}else if('F_EXCEED_SIZE' == type){
    			data = {'code':'size_error','msg':'文件不能大于' + fileSize + 'M'};
    			data.file = file;
    		}else{
    			data = {'code':'other_error','msg':'文件验证不通过'};
    		}
    		data.file = fs;
    		errs.push(data);
    	});

    	//全部触发之后执行
    	uploader.on( 'filesQueued', function(files) {
    		//初始化
    		initUpload(allFiles);
    		//处理错误信息
    		for(var i in errs){
    			error(errs[i]);
    		}
    		//清理临时数据
    		allFiles = null;
    		errs = null;
    	});
    	
    	//选择了文件，可以多个
    	uploader.on( 'fileQueued', function(file) {
    		//禁掉上传按钮
    		uploader.request('disable');
    		
    		//触发beforeUpload
    		var name = file.name.substring(0,file.name.lastIndexOf("."));
    		var ext = file.name.substring(file.name.lastIndexOf(".") + 1,file.name.length);
    		file.realPath = path.replace('<fileName>',name);
    		file.realPath = file.realPath.replace('<extName>',ext);
    		var data = {
				'fileName':file.name,
				'fileSize':file.size,
				'fileType':file.type,
				'path':UploadUtil.getRelativePath(file.realPath),
				'fileIndex':file.fileIndex
    		}
    		
    		var result = beforeUpload(data);
    		//失败
    		if(result && result != true){
    			uploader.removeFile(file,true);
    			errs.push({'code':'other_error','msg':result,'reason':null,'file':file});
    			return;
    		}
    		
    		//预览
    		UploadUtil.showPreview(uploader,file,config.preview);
    	});

    	//开始上传文件
    	uploader.on( 'uploadStart', function(file) {
    		
    	});
    	
    	//开始上传
    	uploader.on( 'uploadBeforeSend', function(object,data,headers) {
    		var policy = '{';
    		policy += '"bucket":"kehouweb",';
    		policy += '"expiration":' + expiration + ',';
    		policy += '"save-key":"' + object.file.realPath + '"';
    		policy += '}';
    		if(UploadUtil.isLocal()){
    			policy.type = 'total';
    		}
    		data.policy = jQuery.base64.encode(policy);
            var signature = data.policy + '&' + formApi;
            signature = jQuery.md5(signature);
            data.signature = signature;
    	});
    	
    	//上传结束
    	uploader.on( 'uploadAccept', function(object,ret) {
    		var data = {
    			code:ret.code,
    			path:UploadUtil.getRelativePath(object.file.realPath),
    			name:object.file.name,
    			data:eval('(' + ret._raw + ')')
    		}
    		data.fileIndex = object.file.fileIndex;
    		afterUpload(data);
    	});
    	
    	//文件上传成功
    	uploader.on( 'uploadSuccess', function( file ) {
    		
    	});
    	
    	//文件上传失败
    	uploader.on( 'uploadError', function(file,reason) {
    		error({'code':'send_error','msg':'文件上传失败','reason':reason,'file':file});
    	});
    	
    	//上传进度
    	uploader.on( 'uploadProgress', function(file, percentage) {
    		progress(file.fileIndex,percentage.toFixed(2));
    	});
    	
    	// 完成上传完了，成功或者失败，重置上传。
	    uploader.on( 'uploadComplete', function(file) {
	    });
	    
	    //当所有文件上传结束时触发
	    uploader.on( 'uploadFinished', function() {
	    	//uploader.reset();
	    	uploader.request('enable');
	    });
	    
	    uploader.toRetry = function(file){
	    	uploader.retry(file);
	    }
	    
	    return uploader;
	}
	
	return {'create':create};
}();

var UploadUtil = {
	create:function(config){
		if('block' == config.uploadType){
			return WebUploadUtil_Block.create(config);
		}else{
			return WebUploadUtil_Total.create(config);
		}
	},
	//获取根地址
	getJsRootPath:function(){
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
	},
	//按规则生成正式路径
	buildPath:function(path){
		if(!path){
			alert('上传路径不能为空');
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
		
		return filePath;
	},
	//创建文件过滤
	createAccept:function(config){
    	var accept = null;
    	if(config.exts){
    		accept ={
    				title: 'Images',
    				extensions: config.exts
    				//mimeTypes: 'image/*'
    		}
    	}
    	return accept;
    },
    //base64
    policyBase64:function(policy){
    	var array = new Array();
        for(var key in policy){
            if((typeof policy[key]) == 'number'){
                array.push('"' + key + '":' + policy[key]);
            }else{
                array.push('"' + key + '":"' + policy[key] + '"');
            }
        }
        policy = '{' + array.join(',') + '}';
        policy = jQuery.base64.encode(policy);
        return policy;
    },
    //是本地上传
    isLocal:function(){
    	if(FileSystemConfig.formApi){
    		return false;
    	}
    	
    	return true;
    },
    //获取相对路径
    getRelativePath:function(path){
		return path;
    },
    //图片预览
	showPreview:function(uploader,file,preview){
		if(preview && preview.fn){
			uploader.makeThumb(file, function( error, ret ) {
				preview.fn(error, ret);
			},preview.width,preview.height);
		}
	}
};

/**
 * UploadUtil进行分装，添加默认样式
 */
var UploadUIUtil = {
	create:function(config){
		var uploader = null;
		
		var UIConfig = {};
		jQuery.extend(UIConfig, config);
		var container = $('#' + config.id + '-list');
		
		UIConfig.initUpload = function(allFiles){
			container.html('');
			container.show();
			for(var i = 0;i < allFiles.length;i++){
				var li = '<li>';
				li +=		'<p class="file-name">' + allFiles[i].name + '</p>'
				li +=		'<p class="file-loading fn-clear">';
				li +=			'<a href="javascript:;" class="fn-right file-btn" style="display:none;">取消</a>';
				li +=			'<span class="per-bg"><span class="per-in" style="width:0%;"></span></span>';
				li +=			'<span class="per-txt">正在上传0%</span>';
				li +=		'</p>';
				li +=    '</li>';
				$(container).append(li);
			}
			
			if(config.initUpload){
				config.initUpload(allFiles);
			}
		}
		
		UIConfig.beforeUpload = function(data){
			var result = config.beforeUpload(data);
			if(result && result != true){
				return result;
			}
        	return result;
		}

		UIConfig.afterUpload = function(data){
			var li = $(container.find('li')[data.fileIndex]);
			var tip_bg = li.find('.per-bg');
			var tip_txt = li.find('.per-txt');
			var file_btn = li.find('.file-btn');
			
			//自定义返回样式
			config.afterUpload(data);
			
			tip_bg.hide();
			tip_txt.html('上传成功');
			//删除功能
			if(UIConfig.remove){
				file_btn.unbind('click');
				file_btn.show();
				file_btn.html('删除');
				file_btn.click(function(){
					li.hide();
					if(UIConfig.remove){
						UIConfig.remove(data);
					}
					if(container.find('li:visible').length <= 0){
						container.hide();
					}
				});
			}
        }
		
		UIConfig.progress = function(fileIndex,percentage,isMd5){
			var li = $(container.find('li')[fileIndex]);
			var tip_bg = li.find('.per-in');
			var tip_txt = li.find('.per-txt');
			percentage = parseInt(percentage * 100)  + '%';
			if(UploadUtil.isLocal()){
				if(!isMd5){
					tip_bg.css('width',percentage);
					tip_txt.html('正在上传' + percentage);
				}
			}else{
				tip_bg.css('width',percentage);
				if(isMd5){
					tip_txt.html('正在解析' + percentage);
				}else{
					tip_txt.html('正在上传' + percentage);
				}
			}
        }
		
		UIConfig.error = function(data){
			var file = data.file;
			var li = $(container.find('li')[file.fileIndex]);
			
			//隐藏加载中
			li.find('.file-loading').hide();
			li.find('.tips-error').remove();
			
			//执行回调
			if(config.error){
				config.error(data);
			}
			
			//显示错误提示
			var tips_error = '<p class="tips-error">';
			//上传失败，则提示继续上传send_error
			if(data.code == 'send_error'){
				tips_error += '<a href="javascript:;" class="fn-right">重试</a>';
			}
			tips_error += data.msg;
			tips_error += '</p>';
			tips_error = $(tips_error);
			
			if(data.code == 'send_error'){
				tips_error.find('a').click(function(){
					tips_error.remove();
					li.find('.file-loading').show();
					uploader.toRetry(file);
				});
			}
			$(li).append(tips_error);
        }
		uploader = UploadUtil.create(UIConfig);
	}
}