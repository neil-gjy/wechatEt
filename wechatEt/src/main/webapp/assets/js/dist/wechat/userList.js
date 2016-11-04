var table;
var oper;
$(function() {

	// 初始化用户列表
	table = $("#list").dataTable(
			$.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {

				"ajax" : {
					"url" : baseUrl + "/wechat/user/loadUserList",
					"type" : "POST",
					"data" : function(data) {
						data.search = $("#searchInput").val();
						return JSON.stringify(data);
					},
					"dataType" : "json",
					"processData" : false,
					"contentType" : 'application/json;charset=UTF-8'
				},
				"columns" : [ {
					"data" : "openid"
				}, {
					"data" : "nickname"
				}, {
					"data" : "language"
				}, {
					"data" : "remark"
				},
				{
					"data" : null,
					"title" : "操作",
					"defaultContent" : "<button id='detailsBtn' class='btn btn-xs bg-blue ' type='button'>详情</button>"
				}  ],
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : false,
					"searchable" : false
				}]

			})).api();

	$("#searchBtn").click(function() {
		table.draw();
	});

	table.on('click', '#detailsBtn', function() {
		var data = table.row($(this).closest('tr')).data();
		$('#id').val(data.id);
		$('#version').val(data.version);
		$('#username').val(data.username);
		$('#name').val(data.name);
		$('#password').val(data.password);
		$('#orgId').val(data.orgId);
		$('#deptId').val(data.deptId);
		$('#roleId').val(data.roleId);

		$('#addModal').modal();
	}).on('click', '#editBtn', function() {
		var data = table.row($(this).closest('tr')).data();
		$('#id').val(data.id);
		$('#version').val(data.version);
		$('#username').val(data.username);
		$('#name').val(data.name);
		$('#password').val(data.password);
		$('#orgId').val(data.orgId);
		$('#deptId').val(data.deptId);
		$('#roleId').val(data.roleId);

		$('#addModal').modal();
		oper = "Edit";
	}).on('click', '#delBtn', function() {
		var data = table.row($(this).closest('tr')).data();
		del(data.id);

	});
	
	// 获取用户信息
	getUserList();
	
	// 填充标签菜单
	getUserTags();


});



function showDetail(data) {

	if (data > 0) {

		var curRowData = $("#list").jqGrid('getRowData', data);

		$('#addModal').modal();
	}

}

// 获取用户列表
function getUserList(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/wechat/user/getUsersList",
		traditional : false,
		dataType : 'json',
		success : function(result) {
			if (result) {
				console.log(result.obj);
				//fillTagList(result.obj);
				/*if (result.msg) {
					bootbox.alert(result.msg);
				}*/
			}
		},
		error : function() {
			bootbox.alert(result.msg);
		}
	});
}


function getUserTags(){
	$.ajax({
		type : "POST",
		url : baseUrl + "/wechat/user/getTagsList",
		traditional : false,
		dataType : 'json',
		success : function(result) {
			if (result) {
				//console.log(result.obj);
				fillTagList(result.obj);
				/*if (result.msg) {
					bootbox.alert(result.msg);
				}*/
			}
		},
		error : function() {
			bootbox.alert(result.msg);
		}
	});
}

function fillTagList(tags){
	var html = "";
	for(var i=0; i<tags.length; i++){
		html += '<dd id="group' + tags[i].id + '" class="inner_menu_item">' + 
		'<a class="inner_menu_link js_group_link" href="javascript:;" data-id="' + tags[i].id + '" title="' + tags[i].name + '">' +
			'<strong>' + tags[i].name + '</strong>' +
			'<em class="num">(' + tags[i].count + ')</em>' +
		'</a>'+
		'</dd>';
	}
	
	$("#menuContent").html(html);
}

