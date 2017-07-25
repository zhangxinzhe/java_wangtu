var totalWidth, totalHeight;
var imageWidthSize=260;
var imageHeightSize=160;
jQuery(window).load( function() {
	if(jQuery('#originalAvatarPos').length){
		totalWidth = jQuery('#originalAvatar').width();
		totalHeight = jQuery('#originalAvatar').height();
		if(totalWidth < imageWidthSize) totalWidth=imageWidthSize;
		if(totalHeight < imageHeightSize) totalHeight=imageHeightSize;
		var _ = jQuery('#originalAvatarPos').attr('value').split('_');		
		var pos = (_[2] != '0')? {
			x1: parseInt(_[0]),
			y1: parseInt(_[1]),
			x2: imageWidthSize,
			y2: imageHeightSize
			} : {
			x1: (totalWidth-imageWidthSize)/2,
			y1: (totalHeight-imageHeightSize)/2,
			x2: (totalWidth-imageWidthSize)/2+imageWidthSize,
			y2: (totalHeight-imageHeightSize)/2+imageHeightSize		
		};
		jQuery('#originalAvatar').imgAreaSelect(jQuery.extend(pos,{
			selectionColor: 'blue',
			borderWidth: 5,
			keys: { arrows: 10, shift: 1 },
			borderColor1:'black',
			borderColor2:'white',
			minWidth:260,
			minHeight:160,
			aspectRatio :'13:8',
			onSelectChange :preview,
			onSelectBegin : preview			
		})); 
		preview(null,pos);
	}
});

function preview(img, selection) {
		var scaleX = imageWidthSize / (selection.x2 - selection.x1);
		var scaleY = imageHeightSize / (selection.y2 - selection.y1);
		jQuery('#originalAvatarPos').attr('name', 'originalAvatarPos').attr(
				'value',selection.x1 + '_' + selection.y1 + '_'+ (selection.x2 - selection.x1)+ '_'+ (selection.y2 - selection.y1));
		jQuery('#previewAvatar').css( {
			width :Math.round(scaleX * totalWidth) + 'px',
			height :Math.round(scaleY * totalHeight) + 'px',
			marginLeft :'-' + Math.round(scaleX * selection.x1) + 'px',
			marginTop :'-' + Math.round(scaleY * selection.y1) + 'px'
		});
}


