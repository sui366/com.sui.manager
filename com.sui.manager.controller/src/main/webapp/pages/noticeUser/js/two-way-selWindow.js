$(function () {
	
	var demo2 = $('.demo1').bootstrapDualListbox({ 
		nonSelectedListLabel: '未选择条目', 
		selectedListLabel: '已选择条目', 
		preserveSelectionOnMove: 'moved', 
		moveOnSelect: false, 
		});
	
	$("#showValue").prop("value","确定");
	$("#showValue").click(function () { 
		var $userNames = $("#userNames", window.parent.document);
		var $demo1 = $('[name="duallistbox_demo1"]');
		$userNames.val($demo1.val());
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
		
});
