$(function(){
	//框架结构
	function windowScreen(){
		var windowW=$(window).width();
		var windowH=$(window).height();
		var headerH=$('#header').height();
		var containerH=$('#container').height();
		var footerH=$('#footer').height()+15;
		var allH=headerH+containerH+footerH;
		var newH=windowH-headerH-footerH;
		//框架问题：1000---1200双屏切换
		if(windowW<1200){
			$('body').addClass('small-screen');
		}else{
			$('body').removeClass('small-screen');
		};
		//底部位置问题
		var dvH=windowH-allH;
		if(dvH>0){
		$('#content').css('min-height',containerH+dvH+'px');
		}
	};
	windowScreen();
	$(window).resize(function(){
		windowScreen();		
	});

	//取消链接点击外部虚线框
	$('a').attr('hideFocus','true');
	
	//placeholder：兼容IE password
	$('input, textarea').placeholder();
	
	//表格效果
	$('.public-table tr:odd').addClass('odd');
	$('.public-table tr').hover(
		function(){
			$(this).addClass('tr-hover').siblings('tr').removeClass('tr-hover');
		},
		function(){
			$(this).removeClass('tr-hover');
		}
	);
	//tab切换
	$('.public-tab li,.public-sub-tab li').click(function(){
		$(this).addClass('current').siblings('li').removeClass('current');
	});
	
	//表单
	$('.validateForm .input-txt,.validateForm .text-area').focus(function(){
		$(this).addClass('input-txt-focus');
	});
	$('.validateForm .input-txt,.validateForm .text-area').blur(function(){
		$(this).removeClass('input-txt-focus');
	});
	//模拟数据选择下拉框效果
	$('.select-analog').each(function(){
        var selWidhth=$(this).width();
		var txtWidth=selWidhth-37;
		$(this).children('.input-txt').width(txtWidth);
    });
	
	//用户信息
	$('.user').hover(function(){
		$(this).addClass('user-hover');
	},function(){
		$(this).removeClass('user-hover');
	});
	
	//左侧导航
	$('.side-item-open').height($('.side-item-open').find('li').length*33+45);
	$('.side-item:not(".side-item-home") .side-item-tt a').click(function(e){
		//e.preventDefault();
		var $item=$(this).parents('.side-item');
		var len=$item.find('li').length;
		var height=len*33+45;
		$item.addClass('side-item-open').siblings('.side-item').removeClass('side-item-open');
		$item.animate({'height':height},500).siblings('.side-item').animate({'height':45},500);
	});
	$('.side-item ul a').click(function(e){
		//e.preventDefault();
		$('.side-item ul a').removeClass('current');
		$(this).addClass('current');
	});
	
	//机构选择
//	$('.org-list li').hover(function(){
//		$(this).addClass('hover');
//	},function(){
//		$(this).removeClass('hover');
//	});
//	$('.org-wrap .org-list').on('click','li a',function(){
//		var $list=$(this).parents('.org-wrap').siblings('.org-wrap').children('.org-list');
//		$(this).parent('li').appendTo($list);
//		$list.find('li').removeClass('hover');
//	});
	
	//帮助中心
	$('.help-tree dt,.help-tree dd').click(function(){
		$('.help-tree dt,.help-tree dd').removeClass('current');
		$(this).addClass('current');
	});
});

//自动关闭弹出提示层-公用函数
function autoLayer(obj){
	if($(obj).length>0){
		var docH=$(document).height();
		var windowW=$(window).width();
		var windowH=$(window).height();
		var layerW=$(obj).width();
		var layerH=$(obj).height();
		var layerL=(windowW-layerW)/2;
		var layerT=(windowH-layerH)/2;
		(docH>windowH) ? maskH=docH : maskH=windowH;
		$('body').append('<div class="mask-layer" style="height:'+maskH+'px"></div>');
		$(obj).css({'top':layerT,'left':layerL}).fadeIn(300,function(){
			$(this).find('.close').click(function(e){
				e.preventDefault();
				$(obj).hide();
				$('.mask-layer').fadeOut(300).remove();
			});
		}).delay(2000).hide(0,function(){
			$('.mask-layer').remove();
		});
	}else{
		alert('此对象不存在!');
	};
};
