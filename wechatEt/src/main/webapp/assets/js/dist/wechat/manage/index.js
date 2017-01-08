
$(function() {
	
	$("#showQrCodeBtn").click(function(){
		console.log(1)
		getQrCodeTicket(60,false,111);
	});
	
	$('#btnAddAttach').click(function() {
		saveFile();
	});
});

// 显示二维码
function getQrCodeTicket(expire_seconds, isLimit, scence_id){
	$.ajax({
		type : "POST",
		url : baseUrl + "/wechat/manage/showQRCode",
		traditional : false,
		dataType : 'json',
		data: { expire_seconds: expire_seconds, isLimit, scence_id},
		success : function(result) {
			console.log(result.obj);
			if (result.code) {
				var url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + result.obj;
				window.open(url);

			}
		},
		error : function() {
			bootbox.alert(result.msg);
		}
	});
}

function saveFile(){
	var postUrl = baseUrl + "/wechat/manage/upLoadFile";
    $("#fileForm").ajaxSubmit({
		type : "post",
		url : postUrl,
		dataType : "json",
		//traditional : false,
		success : function(result) {
			if (result) {
				if (result.code) {
					//bootbox.alert(result.msg);
				} else {
					
					//bootbox.alert(result.msg);
				}
			} else {
				
				bootbox.alert("异常");
			}
		},
		error : function() {
			bootbox.alert("网络连接错误，请重试！");
		}
	});
}