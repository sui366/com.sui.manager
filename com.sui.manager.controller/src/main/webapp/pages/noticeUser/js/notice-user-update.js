
function NoticeUserUpdate() {
};
NoticeUserUpdate.prototype = {
	
	subData:function(){
		var validateResult = requiredGroupValidate(new Array("#url"));
 		if(!validateResult){
 			return;
 		}
		
 		$.ajax({
			type : "post",
			url : pathHelper_localUrl+"noticeUser/updateNoticeUserInfo.action",
			data : $("#subDataForm").serialize(),
			complete:function(){
			},
			success : function(result) {
				if(result.success){
					window.location.href= pathHelper_localUrl+"noticeUser/userList.shtml";
				}else{
					alert(result.message);
				}
			}
		});
	}
}


var noticeUserUpdate = null;



$(document).ready(function() {
	noticeUserUpdate = new NoticeUserUpdate();
	$("#subBtn").click(function(){
		
		noticeUserUpdate.subData();
	});
	
	$("#url").fieldControl({len:255,required:true});
	
	$('#select_username').on('click', function(){
		layer.open({
	        type: 2,
	        title: '选择用户',
	        maxmin: true,
	        shadeClose: false, //点击遮罩关闭层
	        area : ['900px' , '720px'],
	        content: pathHelper_localUrl+'sysUserGroup/sysUserListWindow.shtml'
	    });
		
	});
	if($("#url").val().trim() == "" || $("#url").val() == null){
		$("#url").on("blur",function(){
			$url = $("#url").val();
			$("#emailSubject").text($url+"地址异常邮件(监控平台通知)");
			$("#emailContent").text("您好: \r\n"+"     警告: "+$url+"地址异常，请尽快处理!");
			$("#mobileContent").text("(监控平台通知)"+$url+"地址异常，请尽快处理!");
		});
	}
});
