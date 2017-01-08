<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>

<div class="row">
	<button class="btn btn-sm btn-primary " id="showQrCodeBtn">
		<span class="glyphicon glyphicon-plus"></span> 获取二维码
	</button>
	
	<form class="form-horizontal" role="form" id="fileForm" enctype="multipart/form-data">
					
		<div class="form-group">
			<label class="col-sm-2 control-label" for="file">文件</label>
			
			<input class="col-sm-4 form-control" id="file" name="file" type="file" />
		</div>
	</form>
	<button class="btn btn-primary" id="btnAddAttach">保存</button>
</div>


<script type="text/javascript"
	src="${pageContext.request.contextPath}/assets/js/dist/wechat/manage/index.js"></script>
