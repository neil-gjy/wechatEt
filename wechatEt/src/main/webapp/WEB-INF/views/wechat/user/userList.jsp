<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<div class="row">
	
	<div class="col-xs-9">
		<div class="box box-primary">
			
			<!-- /.box-header -->
			<div class="box-body">
				<div class="rows">
					
				</div>
				<div class="row ">

					<div class="col-xs-9">
						<button class="btn btn-sm btn-primary " id="addTagToUsersBtn">
							<span class="glyphicon glyphicon-plus"></span> 打标签
						</button>
						<button class="btn btn-sm btn-primary " id="removeTagToUsersBtn">
							<span class="glyphicon glyphicon-plus"></span> 移除标签
						</button>
						<button class="btn btn-sm btn-primary" id="btnRefresh">
							<span class="glyphicon glyphicon-refresh"></span> 刷新
						</button>

					</div>
					<div class="col-xs-3">

						<div class="input-group">
							<input id="searchInput" type="text" placeholder="请输入关键字"
								class="input-sm form-control"> <span
								class="input-group-btn">
								<button id="searchBtn" type="button"
									class="btn btn-sm btn-primary">搜索</button>
							</span>
						</div>

					</div>
				</div>

				<table id="list"
					class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th></th>
							<th>ID</th>
							
							<th>昵称</th>
							<th>标签</th>
							<th>备注</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<!-- <tr>
							<td class="table_cell user">
								<div class="user_info">
									<a class="remark_name" target="_blank" href="/cgi-bin/singlesendpage?t=message/send&action=index&tofakeid=o-QHcwvci_s7swHzesBvHEHbl6bQ&token=418244102&lang=zh_CN" data-fakeid="o-QHcwvci_s7swHzesBvHEHbl6bQ">郭晶云</a>
									<span class="nick_name" data-fakeid="o-QHcwvci_s7swHzesBvHEHbl6bQ"></span>
									<a class="avatar" target="_blank" href="/cgi-bin/singlesendpage?t=message/send&action=index&tofakeid=o-QHcwvci_s7swHzesBvHEHbl6bQ&token=418244102&lang=zh_CN">
									<img class="js_msgSenderAvatar" src="/misc/getheadimg?fakeid=o-QHcwvci_s7swHzesBvHEHbl6bQ&token=418244102&lang=zh_CN" data-id="o-QHcwvci_s7swHzesBvHEHbl6bQ">
									</a>
									<label class="frm_checkbox_label" for="checko-QHcwvci_s7swHzesBvHEHbl6bQ">
									<i class="icon_checkbox"></i>
									<input id="checko-QHcwvci_s7swHzesBvHEHbl6bQ" class="frm_checkbox js_select" value="o-QHcwvci_s7swHzesBvHEHbl6bQ" type="checkbox">
									</label>
									<div class="js_tags user_tag_area">
										<span class="js_tags_list user_tag_list">无标签 </span>
											<span class="js_tags_btn dropdown_switch_area dropdown_closed" data-id="o-QHcwvci_s7swHzesBvHEHbl6bQ">
											<span class="icon_dropdown_switch">
											<i class="arrow arrow_up"></i>
											<i class="arrow arrow_down"></i>
											</span>
										</span>
									</div>
								</div>
							</td>
						</tr> -->
					</tbody>
				</table>
			</div>
			<!-- /.box-body -->
		</div>
		<!-- /.box -->
	</div>
	
	<div class="col-xs-3">
		<div class="box box-primary">
			<div class="box-header with-border">

              
            </div>
			<!-- /.box-header -->
			<div class="box-body">
				  <div class="rows">
	                <button class="btn btn-sm btn-primary " id="addTagNew">
						<span class="glyphicon glyphicon-plus"></span> 添加
					</button>
	              </div>
				 <table id="tagsList"
					class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>标签</th>
							<th>数量</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			</div>
		</div>
	</div>
	
	<!-- 标签添加对话框 -->
<div id="addTagModal" class="modal" tabindex="－1"
	data-backdrop="true" role="dialog" aria-labelledby="11"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 300px;">
		<div class="modal-content">
			<div class="modal-body">
				<label class="col-sm-2 control-label" >标签名称</label>
				<div class="col-sm-4">
					<input class="form-control" id="tagName" type="text"/>
				</div>
				
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				<button class="btn btn-primary" id="saveTagBtn">保存</button>
			</div>
		</div>
	</div>
</div>	

	<!-- 打标签对话框 -->
<div id="toUserTagModal" class="modal" tabindex="－1"
	data-backdrop="true" role="dialog" aria-labelledby="11"
	aria-hidden="true">
	<div class="modal-dialog" style="width: 300px;">
		<div class="modal-content">
			<div id="tagContent" class="modal-body">
				
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				<button class="btn btn-primary" id="labelTagBtn">添加标签</button>
				<button class="btn btn-primary" id="removeTagBtn">移除标签</button>
			</div>
		</div>
	</div>
</div>	

<div id="addModal" class="modal fade" tabindex="－1"
	data-backdrop="static" role="dialog" aria-labelledby=""
	aria-hidden="true">
	<div class="modal-dialog" style="width: 70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">添加</h4>
			</div>
			<div class="modal-body">
				<form class="form-horizontal" role="form" id="userForm" >
					<div class="form-group sr-only">
						<label class="col-sm-2 control-label" for="id">Id</label>
						<div class="col-sm-4">
							<input class="form-control" id="id" type="text" name="id" />
						</div>
						<label class="col-sm-2 control-label" for="version">Version</label>
						<div class="col-sm-4">
							<input class="form-control" id="version" type="text" name="version"/>
						</div>
					</div>
					<div class="form-group">						
						<label class="col-sm-2 control-label" for="username">用户名</label>
						<div class="col-sm-4">
							<input class="form-control" id="username" name="username" type="text" />
						</div>
						<label class="col-sm-2 control-label" for="name">用户姓名</label>
						<div class="col-sm-4">
							<input class="form-control" id="name" name="name" type="text" />
						</div>						
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="password">密码</label>
						<div class="col-sm-4">
							<input class="form-control" id="password" name="password" type="text" />
						</div>
						<label class="col-sm-2 control-label" for="rpwd">重复密码</label>
						<div class="col-sm-4">
							<input class="form-control" id="rpwd" name="rpwd" type="text"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="orgId">单位</label>
						<div class="col-sm-4">
							<select class="form-control" id="orgId" name="orgId" type="text">
							<option>1</option>
							</select>
						</div>
						<label class="col-sm-2 control-label" for="deptId">部门</label>
						<div class="col-sm-4">
							<input class="form-control" id="deptId" name="deptId"/>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="roleId">角色</label>
						<div class="col-sm-4">
							<input class="form-control" id="roleId" name="roleId" />
						</div>
						
					</div>

				</form>				
			
			</div>
			<div class="modal-footer">
				<button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
				<button class="btn btn-primary" id="btnSave">保存</button>
			</div>
		</div>
	</div>
</div>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/dist/wechat/userList.js"></script>