
function PageList() {
};
PageList.prototype = {
	
	toInertPage:function(){
		window.location.href= pathHelper_localUrl+"sysUser/updatePage.shtml?t="+new Date().getTime();
	},
	toUpdatePage:function(id){
		window.location.href= pathHelper_localUrl+"sysUser/updatePage.shtml?t="+new Date().getTime()+"&id="+id;
	},
	
	deleteObj:function(id){
		if(!isDataNull(id)){
			layer.alert("请选择要删除的数据");
			return ;
		}
		
		layer.confirm('确定要删除该条数据？', {
		  btn: ['确定','取消'] 
		}, function(){
			var index = layer.load();
			$.ajax({
				type : "post",
				url : pathHelper_localUrl+"sysUser/delete.action",
				data : {"id":id},
				complete:function(){
					layer.close(index);
				},
				success : function(result) {
					if(result.success){
						window.location.href= pathHelper_localUrl+"sysUser/list.shtml";
					}else{
						layer.alert(result.message);
					}
				}
			});
		}, function(){
		  layer.closeAll();
		});
	},
	actionFormatter:function(value, row, index) {
		var buttons = [];
		buttons.push('<button type="button" class="btn btn-default resend" onclick="javascript:pageList.toUpdatePage('+row.id+');">&nbsp;修&nbsp;改&nbsp;</button>');
		buttons.push('<button type="button" class="btn btn-default resend" onclick="javascript:pageList.deleteObj('+row.id+');">&nbsp;删&nbsp;除&nbsp;</button>');
		if(isDataNull(currentUserName) && "admin" != row.loginName){
			buttons.push('<button type="button" class="btn btn-default resend" onclick="javascript:pageList.resetPwd('+row.id+');">重置密码</button>');
		}
		return buttons.join('');
	},
	resetPwd:function(id){
		var index = layer.load();
		$.ajax({
			type : "post",
			url : pathHelper_localUrl+"sysUser/resetPwd.action",
			data : {"id":id},
			complete:function(){
				layer.close(index);
			},
			success : function(result) {
				if(result.success){
					layer.alert("密码重置成功！");
				}else{
					layer.alert(result.message);
				}
			}
		});
	}
	
}


var pageList = null;
$(document).ready(function() {
		
	pageList = new PageList();
	
	$("#subBtn").click(function(){
		$('#dataTable').bootstrapTable('removeAll');
		$('#dataTable').bootstrapTable('refresh', {});  
	});
	
});


function queryParams(params){
	return {  
        "name": $("#name").val(),  
        "limit":params.limit,"offset":params.offset,
        "sortname":params.sort,"sortorder":params.order
    };
}

