
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
	        	title: {
	                validators: {
	                    notEmpty: {  message: '主题不能为空' }
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
			url : pathHelper_localUrl+"customer/service/update.action",
			data : $("#subDataForm").serialize(),
			complete:function(){
				layer.close(index);
			},
			success : function(result) {
				if(result.success){
					window.location.href= pathHelper_localUrl+"customer/service/list.shtml";
				}else{
					layer.alert(result.message);
				}
			}
		});
	},
	buildContract:function(customerId){
		$.ajax({
			type : "post",
			url : pathHelper_localUrl+"customer/contract/info.action",
			data : {"customerId":customerId},
			complete:function(){
//				layer.close(index);
			},
			success : function(result) {
				
				var strs = [];
				if(result.success){
					for(var i=0; i<result.data.list.length; i++){
						var obj = result.data.list[i];
						strs.push("<option value=\""+obj.name+"\">"+obj.name+"</option>");
					}
					
					$("#contractName").empty();
					
					$("#contractName").append(strs.join(""));
					
				}else{
					layer.alert(result.message);
				}
			}
		});
		
//		$.ajax({
//			type : "post",
//			url : pathHelper_localUrl+"customer/sysUser/userList.action",
////			data : {"customerId":customerId},
//			complete:function(){
////				layer.close(index);
//			},
//			success : function(result) {
//				
//				var strs = [];
//				if(result.success){
//					for(var i=0; i<result.data.list.length; i++){
//						var obj = result.data.list[i];
//						strs.push("<option value=\""+obj.name+"\">"+obj.name+"</option>");
//					}
//					
//					$("#reciveManName").empty();
//					
//					$("#reciveManName").append(strs.join(""));
//					
//				}else{
//					layer.alert(result.message);
//				}
//			}
//		});
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
		$("#title").attr("readonly", true);
		
		var solvers = $("#solversHid").val();
		if(isDataNull(solvers)){
			$("#solvers option").each(function() {
				if (solvers.indexOf($(this).val()) > -1) {
					$(this).attr("selected", true);
				}
			});  
		}
		
	}
	
	$('.selectpicker').selectpicker();
	
});

