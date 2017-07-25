<#include "/pages/common/constant.ftl"/>
<div id="a1"></div>
<script type="text/javascript">
    //视频地址
    var videoUrl = '${DOMAIN_UPLOAD_FILE}/${videoFile!}';
    var flashvars={
        f:videoUrl,
        a:'',
        c:0,
        s:0,
        lv:0,//是否锁定进度栏，注意：如果是直播，需设置lv:1
        h:'3',
        p:'1',  //视频默认0是暂停，1是播放，2是不加载视频
        g:0   //从start秒开始播放
        };
    //var params={bgcolor:'#FFF',allowFullScreen:true,allowScriptAccess:'always',wmode:'transparent'};
    //CKobject.embedSWF('${DOMAIN_CMS}/js/component/ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',flashvars,params);
    CKobject.embed('${DOMAIN_CMS}/js/component/ckplayer/ckplayer.swf','a1','ckplayer_a1','600','400',false,flashvars,[videoUrl]);
    
</script>