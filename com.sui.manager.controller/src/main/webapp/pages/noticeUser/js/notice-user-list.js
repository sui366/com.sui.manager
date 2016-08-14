
function NoticeUserList() {
};
NoticeUserList.prototype = {
	
	toInertPage:function(){
		window.location.href= pathHelper_localUrl+"noticeUser/userUpdatePage.shtml?t="+new Date().getTime();
	},
	toUpdatePage:function(id){
		window.location.href= pathHelper_localUrl+"noticeUser/userUpdatePage.shtml?t="+new Date().getTime()+"&id="+id;
	},
	toDeletePage:function(id,operation){
		if(!isDataNull(id)){
			layer.alert("请选择要删除的数据");
			return ;
		}
		layer.confirm('确定要删除该条数据？', {
		  btn: ['确定','取消'] 
		}, function(){
			window.location.href= pathHelper_localUrl+"noticeUser/userUpdatePage.shtml?t="+new Date().getTime()+"&id="+id+"&operation="+operation;
		}, function(){
		  layer.closeAll();
		});
	},

	actionFormatter:function(value, row, index) {
	    return [
	            '<button type="button" class="btn btn-default resend" onclick="javascript:noticeUserList.toUpdatePage('+row.id+');">&nbsp;修&nbsp;改&nbsp;</button>',
	            '&nbsp;<button type="button" class="btn btn-default resend" onclick="javascript:noticeUserList.toDeletePage('+row.id+",0"+');">&nbsp;删&nbsp;除&nbsp;</button>'
	    ].join('');
	},
	
	typeFormatter:function(value, row, index){
		if(row.type == 1){
			return "get";
		}
		return "post";
	},
	
	stateFormatter:function(value, row, index){
		if(row.state == 1){
			return "正常";
		}
		return "异常";
	}
	
}


var noticeUserList = null;
$(document).ready(function() {
		
	noticeUserList = new NoticeUserList();
	
	$("#subBtn").click(function(){
		$('#dataTable').bootstrapTable('removeAll');
		$('#dataTable').bootstrapTable('refresh', {});  
	});
});


function queryParams(params){
	return {  
        "productType": $("#productType option:selected").val(),  
        "procType": $("#procType option:selected").val(),
        "limit":params.limit,"offset":params.offset
    };
}

