
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
	        	goodNumber: {
	                validators: {
	                    notEmpty: {  message: '机身编号不能为空' }
	                }
	            },
	            preMoney: {
		        	validators: {
		        		regexp: {
	                        regexp: /^\d+(\.\d{2})?$/,
	                        message: '格式不正确'
	                    }
		        	}
		        },
		        reciveMoney: {
	            	validators: {
	            		regexp: {
	            			regexp: /^\d+(\.\d{2})?$/,
	            			message: '格式不正确'
	            		}
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
			url : pathHelper_localUrl+"customer/sales/update.action",
			data : $("#subDataForm").serialize(),
			complete:function(){
				layer.close(index);
			},
			success : function(result) {
				if(result.success){
					window.location.href= pathHelper_localUrl+"customer/sales/list.shtml";
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
					
					$("#takeUserName").empty();
					$("#signUserName").empty();
					
					$("#takeUserName").append(strs.join(""));
					$("#signUserName").append(strs.join(""));
					
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
		$("#goodNumber").attr("readonly", true);
		if("1" == $("#isbillHid").val()){
			$("#isBill").attr("checked",true);  
		}
	}
	
	$('.selectpicker').selectpicker();
	
	$("#customerId").change(function(){
		pageUpdate.buildContract($(this).val());
	});
	
});

