
function PageUpdate() {
};
PageUpdate.prototype = {
		
	validate:function(){
		$('#subDataForm').bootstrapValidator({
	        message: '请正确填写信息',
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	name: {
	                validators: {
	                    notEmpty: {  message: '姓名不能为空' },
	                    stringLength:{max:100, message: '长度不能超过100个字符'}
	                }
	            },
	            point: {
	            	validators: {
	            		digits: {  message: '请正确填写积分' },
	            		between: {min:0, max:10000000, message:'积分必须在0-10000000之间'}
	            	}
	            }
	        }
	    });
	},
	
	subData:function(){
		
		$('#subDataForm').data('bootstrapValidator').validate();  
		if(!$('#subDataForm').data('bootstrapValidator').isValid()){  
            return;
        }  
		
 		
		var index = layer.load();
 		$.ajax({
			type : "post",
			url : pathHelper_localUrl+"customer/contract/update.action",
			data : $("#subDataForm").serialize(),
			complete:function(){
				layer.close(index);
			},
			success : function(result) {
				if(result.success){
					window.location.href= pathHelper_localUrl+"customer/contract/list.shtml";
				}else{
					layer.alert(result.message);
				}
			}
		});
	}
	
}


var pageUpdate = null;
$(document).ready(function() {
		
	pageUpdate = new PageUpdate();
	
	pageUpdate.validate();
	
	$("#subBtn").click(function(){
		pageUpdate.subData();
	});
	
	var id = $("#id").val();
	if(isDataNull(id)){
		$("#name").attr("readonly", true);
	}
	
	
});

