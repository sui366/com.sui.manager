
/**
 * data:需要校验的val值 
 * 该方法用来校验某一参数是否为空,未定义等,如果验证通过,返回true；否则 返回false
 */
function isDataNull(data){
	if(null == data ||"" == data || "undefined" == data){
		data = "";
	}
	//
	var check_sign = false;
	if("" != $.trim(data)){
		check_sign = true;
	}
	return check_sign;
}

//获取字符串长度 
function getLength(str){
  var len = 0;  
  for (var i=0; i<str.length; i++) {  
    if (str.charCodeAt(i)>127 || str.charCodeAt(i)==94) {  
       len += 2;  
     } else {  
       len ++;  
     }  
   }  
  return len;  
}

//截取字符串
function doSubStr(str, len){
	var str_length = 0;
	var str_len = 0;
	str_cut = new String();
	str_len = str.length;
	for (var i = 0; i < str_len; i++) {
		a = str.charAt(i);
		str_length++;
		if (escape(a).length > 4) {
			// 中文字符的长度经编码之后大于4
			str_length++;
		}
		str_cut = str_cut.concat(a);
		if (str_length >= len) {
			return str_cut;
		}
	}
	// 如果给定字符串小于指定长度，则返回源字符串；
	if (str_length < len) {
		return str;
	}
}

//检测中文
function checkChinese(val, chinese){
	var find = false;
	if(!isDataNull(val)){
		return find;
	}
	if(false == chinese){
		var str_len = val.length;
		var a = "";
		for (var i = 0; i < str_len; i++) {
			a = val.charAt(i);
			if (escape(a).length > 4) {
				// 中文字符的长度经编码之后大于4
				find = true;
				break;
			}
		}
	}
	else{
		var str_len = val.length;
		var a = "";
		for (var i = 0; i < str_len; i++) {
			a = val.charAt(i);
			if (escape(a).length < 4) {
				// 中文字符的长度经编码之后大于4
				find = true;
				break;
			}
		}
	}
	return find;
}

//校验表单数据
$.fn.extend({
	//控制输入长度
	keyupControlLength : function(len){
		$(this).keyup(function(){
			var val = $(this).val();
			if(getLength(val)>len){
				$(this).val(doSubStr(val, len));
			}  
		});	
	}
});

//判断一组标签是否有空值
validateResult = true;
function requiredGroupValidate(group){
//	if(validateResult == false){
//		return false;
//	}
	
	for(index in group){
		$(group[index]).blur();
	}
	
	for(index in group){
		var result = $(group[index]).checkIsNotNull(null);
		if(!result){
			validateResult = false;
			break;
		}
		else{
			validateResult = true;
		}
	}
	return validateResult;
}

$.fn.extend({
	//校验为空
	checkIsNotNull : function(desc){
		var result = false;
		var idMss = $(this).attr("id")+"_mss";
		if($("#"+idMss).length<=0){
			$(this).after("<span id=\""+idMss+"\"></span>");
		}
		if(isDataNull($(this).val())){
			getImgChangeValTrue('#'+idMss);
			result = true;
		}
		else{
			if(!isDataNull(desc)){
				desc = "不能为空";
			}
			getImgChangeValFalse('#'+idMss,desc);
		}
		return result;
	}
	
});	

//表单元素校验规则
$.fn.extend({
	//
	fieldControl : function(param){
		var len = param.len;//长度 值为数字
		var number = param.num;//是否为数字 值为true or false;
		var required = param.required;//是否必填 值为true or false;
		var telephone = param.telephone;//是否手机号码 值为true or false;
		var password = param.password;//是否密码 值为true or false;
		var email = param.email;//是否邮箱 值为true or false;
		var chinese = param.chinese;//是否中文  值为true or false;
		
		$(this).blur(function(){
			
			var val = $(this).val();
			
			var idMss = $(this).attr("id")+"_mss";
			if($("#"+idMss).length<=0){
				$(this).after("<span id=\""+idMss+"\"></span>");
			}
			
			if(isDataNull(len)){
				if(getLength(val)>len){
					$(this).val(doSubStr(val, len));
					return;
				}  
			}  
			
			if(isDataNull(required) && true == required){
					
				if(isDataNull(val)){
					getImgChangeValTrue('#'+idMss);
				}
				else{
					getImgChangeValFalse('#'+idMss,"不能为空");
					return;
				}
				
			}
			
			if(isDataNull(number) && true == number){
				var reg = /[^0-9]*$/;
				$(this).val(val.replace(reg,""));
			}
			
//			var idMss = $(this).attr("id")+"_mss";
			if($("#"+idMss).length<=0){
				$(this).after("<span id=\""+idMss+"\"></span>");
			}
			
			if(isDataNull(telephone) && true == telephone){
				var reg1=/^(\d{3,4}-)?\d{7,8}$/;
				var reg2=/^(1)\d{10}$/;
				if(!reg1.test(val) && !reg2.test(val)){
					getImgChangeValFalse('#'+idMss,"格式不正确(正确格式如：以1开头11位手机或3-4位区号加-及7-8位的固定电话号码)");
					return;
				}
			}
			
			if(isDataNull(password) && true == password){
				var reg=/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/;
				if(!reg.test(val)){
					getImgChangeValFalse('#'+idMss,"密码为6-22个字符,可由英文,数字及符号组成");
					return;
				}
			}
			
			if(isDataNull(email) && true == email){
				var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,4}$/;
				if(!reg.test(val)){
					getImgChangeValFalse('#'+idMss,"邮箱格式错误,如: zhangsan@126.com");
					return;
				}
			}
			
			if(isDataNull(chinese)){
				if(checkChinese(val, chinese)){
					return;
				}
			}
		});	
	}
});

//验证时正确信息显示 
function getImgChangeValTrue(id) { 
	$(id).empty(); 
}

function getImgChangeValFalse(id,title,url) {
	$(id).text(title); 
//	$(id).attr("placeholder", title); 
	$(id).css("color", "red"); 
//	$(id).parent().addClass("has-error");
} 
