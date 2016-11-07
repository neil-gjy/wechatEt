var table;
var oper;
$(function() {

	// 初始化用户列表
//	table = $("#list").dataTable(
//			$.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {
//
//				"ajax" : {
//					"url" : baseUrl + "/wechat/user/loadUserList",
//					"type" : "POST",
//					"data" : function(data) {
//						data.search = $("#searchInput").val();
//						return JSON.stringify(data);
//					},
//					"dataType" : "json",
//					"processData" : false,
//					"contentType" : 'application/json;charset=UTF-8'
//				},
//				"columns" : [ {
//					"data" : null,
//					"title" : "#",
//					"orderable" : false,
//					"className": 'select-checkbox',
//					"defaultContent" : ""
//				},{
//					"data" : "openid"
//				},
//				{
//					"data" : "nickname"
//				}, {
//					"data" : "language"
//				}, {
//					"data" : "remark"
//				},
//				{
//					"data" : null,
//					"title" : "操作",
//					"defaultContent" : "<button id='detailsBtn' class='btn btn-xs bg-blue ' type='button'>详情</button>"
//				}  ],
//				"columnDefs" : [ {
//					"targets" : [ 1 ],
//					"visible" : false,
//					"searchable" : false
//				}],
//				select: {
//		            style:    'multi',
//		            selector: 'td:first-child'
//		        },
//
//			})).api();
//
//	$("#searchBtn").click(function() {
//		table.draw();
//	});
//
//	table.on('click', '#detailsBtn', function() {
//		var data = table.row($(this).closest('tr')).data();
//		$('#id').val(data.id);
//		$('#version').val(data.version);
//		$('#username').val(data.username);
//		$('#name').val(data.name);
//		$('#password').val(data.password);
//		$('#orgId').val(data.orgId);
//		$('#deptId').val(data.deptId);
//		$('#roleId').val(data.roleId);
//
//		$('#addModal').modal();
//	}).on('click', '#editBtn', function() {
//		var data = table.row($(this).closest('tr')).data();
//		$('#id').val(data.id);
//		$('#version').val(data.version);
//		$('#username').val(data.username);
//		$('#name').val(data.name);
//		$('#password').val(data.password);
//		$('#orgId').val(data.orgId);
//		$('#deptId').val(data.deptId);
//		$('#roleId').val(data.roleId);
//
//		$('#addModal').modal();
//		oper = "Edit";
//	}).on('click', '#delBtn', function() {
//		var data = table.row($(this).closest('tr')).data();
//		del(data.id);
//
//	});
	
	
	// 初始化标签列表
	tagsTable = $("#tagsList").dataTable(
			$.extend(true, {}, CONSTANT.DATA_TABLES.DEFAULT_OPTION, {

				"ajax" : {
					"url" : baseUrl + "/wechat/user/loadTagList",
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
					"data" : "name"
				}, {
					"data" : "count"
				},
				{
					"data" : null,
					"title" : "操作",
					"defaultContent" : "<button id='editBtn' class='btn btn-xs bg-blue ' type='button'>编辑</button>" +
									   "<button id='delBtn' class='btn btn-xs bg-blue ' type='button'>删除</button>"
				}  ],
				"columnDefs" : [ {
					"targets" : [ 0 ],
					"visible" : false,
					"searchable" : false
				}]

			})).api();
	
	// 获取用户信息
	//getUserList();
	
	// 填充标签菜单
	//getUserTags();
	showTag();
	$("#toUserTagModal").modal("show");
	$("#addTagBtn").click(function() {
		$("#addTagModal").modal("show");
	});
	
	$("#addTagToUsersBtn").click(function() {
		 var selectIds = table.rows( { selected: true } );
		 
		 var rowData = table.rows( selectIds[0] ).data().toArray();
		 for(var i=0; i<rowData.length; i++){
			 console.log(rowData[i].openid);
		 }
	});
	
	$("#saveTagBtn").click(function() {
		saveTag();
	});
	
	$("#editBtn").click(function() {
		console.log("edit");
	});

});

// 保存标签
function saveTag(){
	var tagName = $("#tagName").val();

	$.ajax({
		type : "POST",
		url : baseUrl + "/wechat/user/saveTag",
		traditional : false,
		dataType : 'json',
		data: { tags: tagName },
		success : function(result) {
			if (result.code) {
				getUserTags();
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

function showTag(tags){
	var html = "";
	for(var i=0; i<tags.length; i++){
		html += '<label class="col-xs-4 select-checkbox">' +
    				'<input type="checkbox">  标签1' +
    			'</label>' +
    			'<label class="col-xs-4 select-checkbox">' +
					'<input type="checkbox">  标签2' +
				'</label>';
	}
	$("#tagContent").html(html);
}



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
				showTag(result.obj);
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

