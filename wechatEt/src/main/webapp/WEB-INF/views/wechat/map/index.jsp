<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
	body, html,#allmap {width: 100%;height: 80%;overflow: hidden;margin:0;font-family:"微软雅黑";}
	</style>
	<%@ include file="/common/wx-header.jsp"%>
	<%@ include file="/common/taglibs.jsp"%>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=qCkaGbU3CTfvTbYjtRXb8rz1TFrq01tI"></script>
	<title>地图展示</title>
</head>
<body>
	
	<div class="row" id="allmap"></div>
	<div class="row">
		<button class="btn btn-sm btn-primary " id="registerBtn" onclick="registerMarker()">
			<span class="glyphicon glyphicon-plus"></span>签到
		</button>
	</div>
</body>

<script type="text/javascript">
	// 初始化坐标
	var curPoint;
	var initPoints = [{"lat":117.749784,"lng":39.009764},{"lat":117.688268,"lng":39.016044},{"lat":117.746694,"lng":39.051076}] ;
	// 百度地图API功能
	var map = new BMap.Map("allmap");	
	var point = new BMap.Point(117.331398,39.897445);
	map.centerAndZoom(point,12);
	for(var i=0; i<initPoints.length;i++){
		initMarkers(i);
	}

	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			//map.addOverlay(mk);
			curPoint = r.point;// 初始化当前位置
			map.panTo(r.point);
			//showMarker(r.point,1);
			//alert('您的位置：'+r.point.lng+','+r.point.lat);
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
	
	// 初始化坐标集合
	function initMarkers(index){
		var point = new BMap.Point(initPoints[i].lat, initPoints[i].lng)
		var myIcon = new BMap.Icon(baseUrl+"/assets/img/hdCountG&R/hdCountG/" + i + ".png", new BMap.Size(23, 25), {    
		 });      
		// 创建标注对象并添加到地图   
		 var marker = new BMap.Marker(point, {icon: myIcon});  
		
		 marker.addEventListener("click", function(e){   
			 var tMark = this;

			 if((map.getDistance(curPoint,e.point)).toFixed(2)<1000){
				 map.removeOverlay(tMark); 
				 showRegisterMarker(tMark.point,index);
			 }
			 else{
				 alert("距离过远")
			 }
			});
		 
		 map.addOverlay(marker);   
		
	}
	
	
	// 显示已签到图表
	function showRegisterMarker(point,index){
		var myIcon = new BMap.Icon(baseUrl+"/assets/img/hdCountG&R/hdCountR/" + index + ".png", new BMap.Size(23, 25), {    
			 });      
			// 创建标注对象并添加到地图   
			 var marker = new BMap.Marker(point, {icon: myIcon});  
			 
			 map.addOverlay(marker);    
		} 
	
	function registerMarker(){
		for(var i=0; i<initPoints.length;i++){
			var point = new BMap.Point(initPoints[i].lat, initPoints[i].lng);
			
			if((map.getDistance(curPoint,point)).toFixed(2)<1000){
				showRegisterMarker(point,i);
				alert("读书点"+ i + "签到成功！");
			}
		}
	}
	
	/* $('#registerBtn').click(function(){
		registerMarker();
	}); */
</script>	
</html>	
	