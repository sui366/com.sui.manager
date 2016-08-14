(function($){
	$("#dataTable").bootstrapTable({
		onCheck : function(row){
			selects.push(row.userName);
		},
		onUncheck : function(row){
			for(var i=0; i<selects.length; i++){
				if(row.userName == selects[i]){
					selects.splice(i,1);
				}
			}
		},
		onCheckAll : function(){
			var allData = $("#dataTable").bootstrapTable("getData");
			for(var i=0; i<allData.length; i++){
				var bool = 0;
				for(var j=0; j<selects.length; j++){
					if(allData[i].userName == selects[j]){
						bool = 1;
						break;
					}
				}
				if(bool == 0){
					selects.push(allData[i].userName);
				}
			};
		},
		onUncheckAll : function(){
			var allData = $("#dataTable").bootstrapTable("getData");
			for(var i=0; i<allData.length; i++){
				var bool = 0;
				for(var j=0; j<selects.length; j++){
					if(allData[i].userName == selects[j]){
						bool = 1;
						break;
					}
				}
				if(bool == 1){
					selects.splice(i,1);
				}
			};
		}
	});
	$("#submit").on("click",function(){
		var addrInput="";
		if(selects.length != 0){
			for(var i=0; i<selects.length; i++){
				addrInput=addrInput+selects[i]+",";
			}
		}	
		if(addrInput != null && addrInput!=""){
			addrInput=addrInput.substring(0, addrInput.length-1);
		}
		$("#userNames", window.parent.document).val(addrInput);
		var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
        parent.layer.close(index);
	});
	$("#reset").on("click",function(){
		$("input[type='checkbox']").prop("checked",false);
		selects.splice(0,selects.length);
	});
	
})(jQuery);
window.onload = function(){
	var $userNames = $("#userNames", window.parent.document).val();
	if($userNames != null && $userNames != ""){	
		var userNames = $userNames.split(",");
		for(var i=0; i<userNames.length; i++){
			selects[i] = userNames[i];
		}
	}
	$("#researchBtn").click(function(){
		$('#dataTable').bootstrapTable('removeAll');
		$('#dataTable').bootstrapTable('refresh', {});  
	});
	$("#resetBtn").click(function(){
		$("#researchByName").val("");
		$("#researchBtn").click();
	});
	$("#researchByName").on("keydown",function(){
		if(event.keyCode == "13"){
			$("#researchBtn").click();
		}
	});
		
	
};
function stateFormatter(value,row){
	for(var i=0; i<selects.length; i++){
		if(row.userName == selects[i]){
			return {
				checked : true,
			}
		}
	};
	return value;
}

function queryParams(params){
	if($("#researchByName").val()!=null && $("#researchByName").val() != ""){
		return {  
			"userName": $("#researchByName").val(),  
			"limit":params.limit,"offset":params.offset
		};
	}else{
		return {  
			"limit":params.limit,"offset":params.offset
		};
	}	
}