var table;
var oper;
var selectOpenids = "";//已选择的openid
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
					"data" : null,
					"title" : "#",
					"orderable" : false,
					"className": 'select-checkbox',
					"defaultContent" : ""
				},{
					"data" : "openid"
				},
				{
					"data" : "nickname"
				}, {
					"data" : "tagid_list"
				}, {
					"data" : "remark"
				},
				{
					"data" : null,
					"title" : "操作",
					"defaultContent" : "<button id='detailsBtn' class='btn btn-xs bg-blue ' type='button'>详情</button>"
				}  ],
				"columnDefs" : [ {
					"targets" : [ 1 ],
					"visible" : false,
					"searchable" : false
				}],
				select: {
		            style:    'multi',
		            selector: 'td:first-child'
		        },

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

	}).on('click','tbody tr',function(){
		alert(1)
	});
	
	
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
	
	tagsTable.on('click','tbody tr',function(){
		var data = table.row($(this).closest('tr')).data();
	});
	
	// 获取用户信息
	//getUserList();
	
	// 填充标签菜单
	//getUserTags();
	//showTag1();
	//$("#toUserTagModal").modal("show");
	$("#addTagBtn").click(function() {
		$("#addTagModal").modal("show");
	});
	
	$("#addTagToUsersBtn").click(function() {
		 var selectIds = table.rows( { selected: true } );
		 var rowData = table.rows( selectIds[0] ).data().toArray();
		 
		 var str = "";
		 for(var i=0; i<rowData.length; i++){
			 str += rowData[i].openid;
			 str += ",";
		 }
		 selectOpenids = str.substring(0,str.length-1);
		 
		 getUserTags();
		 $("#toUserTagModal").modal("show");
	});
	
	$("#removeTagToUsersBtn").click(function() {
		 var selectIds = table.rows( { selected: true } );
		 var rowData = table.rows( selectIds[0] ).data().toArray();
		 
		 var str = "";
		 for(var i=0; i<rowData.length; i++){
			 str += rowData[i].openid;
			 str += ",";
		 }
		 selectOpenids = str.substring(0,str.length-1);
		 
		 //选中要删除的标签
		 getUserTags();
		 $("#toUserTagModal").modal("show");

		 selectOpenids = str.substring(0,str.length-1);
	});
	
	$("#saveTagBtn").click(function() {
		saveTag();
	});
	
	$("#editBtn").click(function() {
		console.log("edit");
	});
	
	$("#labelTagBtn").click(function(){
		labelTag();
	});
	
	$("#removeTagBtn").click(function(){
		removeTag();
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
    				'<input type="checkbox" value=' +  tags[i].id + '>' +
    				'<span >' +  tags[i].name + '</span>' +
    			'</label>';
	}
	$("#tagContent").html(html);
}

// 给用户打标签
function labelTag(){
	var selectTags = "";
	$("label input[type=checkbox]").each(function(){
	    if(this.checked){
	    	selectTags += $(this).val();
	    }
	});  
	//console.log(tt);
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/wechat/user/labelTag",
		traditional : false,
		data: {openids: selectOpenids, tag: selectTags},
		dataType : 'json',
		success : function(result) {
			if (result) {
				console.log(result.obj);
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

//给用户移除标签
function removeTag(){
	var selectTags = "";
	$("label input[type=checkbox]").each(function(){
	    if(this.checked){
	    	selectTags += $(this).val();
	    }
	});  
	
	$.ajax({
		type : "POST",
		url : baseUrl + "/wechat/user/removeTag",
		traditional : false,
		data: {openids: selectOpenids, tag: selectTags},
		dataType : 'json',
		success : function(result) {
			if (result) {
				console.log(result.obj);
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

