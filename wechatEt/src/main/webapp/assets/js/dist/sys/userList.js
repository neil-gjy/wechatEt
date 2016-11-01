var table;
var oper;
$(function() {

	// 初始化用户列表
	table = $("#list").dataTable(
			$.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {

				"ajax" : {
					"url" : baseUrl + "/sys/user/loadUserList",
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
					"data" : "id"
				}, {
					"data" : "username"
				}, {
					"data" : "name"
				}, {
					"data" : "orgName"
				},
				{
					"data" : null,
					"title" : "操作",
					"defaultContent" : "<button id='detailsBtn' class='btn btn-xs bg-blue ' type='button'>详情</button>" +
					" <button id='editBtn' class='btn btn-xs bg-blue ' type='button'>编辑</button> " + "<button id='delBtn' class='btn btn-xs bg-blue ' type='button'>删除</button>"
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

	$('#btnNew').click(function() {
		$("#addModalLabel").html("添加");

		$('#addModal').modal();
		oper = "Add";
	});

	$('#btnSave').click(function() {
		save();
	});
});

function save() {

	var postUrl = "";
	if (oper == "Add") {
		postUrl = baseUrl + "/sys/user/userAdd";
	} if (oper == "Edit") {
		postUrl = baseUrl + "/sys/user/userEdit";
	}
	$("#userForm").ajaxSubmit({
		type : "post",
		url : postUrl,
		dataType : "json",
		success : function(result) {
			if (result) {
				if (result.code) {
					bootbox.alert(result.msg);
					$('#addModal').modal("hide");
					table.draw();
				} else {
					bootbox.alert(result.msg);
				}
			} else {

				bootbox.alert("异常");
			}
		},
		error : function() {
			bootbox.alert("网络连接错误，请重试！");
		}
	});
	return false;
}

function del(id) {

	bootbox.confirm("确定要删除吗？", function(result) {
		if (result) {
			$.ajax({
				type : "POST",
				url : baseUrl + "/sys/user/userDel",
				data : {
					id : id
				},
				traditional : false,
				dataType : 'json',
				success : function(result) {
					if (result) {
						if (result.msg) {
							bootbox.alert(result.msg);
							table.draw(false);
						}
					}
				},
				error : function() {
					bootbox.alert(result.msg);
				}
			});
		}
	});

}

function showDetail(data) {

	if (data > 0) {

		var curRowData = $("#list").jqGrid('getRowData', data);

		$('#addModal').modal();
	}

}

