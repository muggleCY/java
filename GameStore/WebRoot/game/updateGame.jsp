<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    	
	<title>游戏修改</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="">

	
	
	<link rel="stylesheet" href="<%=basePath%>css/bootstrap.min.css">
	<script src="<%=basePath%>js/jquery.js"></script>
	<script src="<%=basePath%>js/bootstrap.min.js"></script>
	<style type="text/css">
	#container {
		margin-top: -20px;
		margin-left: 10px;
	}
	</style>
	<script type="text/javascript">
			function check() {
				
				var name = $("#name").val();
				if(name == ""){
					alert("名称不能为空");
					return false;
				}
				var gametype = $("#gametype option:selected").text();
				if(gametype == "请选择"){
					alert("游戏类型不能为空");
					return false;
				}
				var developers = $("#developers").val();
				if(developers == ""){
					alert("开发商不能为空");
					return false;
				}
				var gameFiling = $("#gameFiling").val();
				if(gameFiling == ""){
					alert("备案号不能为空");
					return false;
				}
				var introduction = $("#introduction").val();
				if(introduction == ""){
					alert("游戏简介不能为空");
					return false;
				}
				if(introduction.length > 200){
					alert("游戏简介过长");
					return false;
				}
				var detail = $("#detail").val();
				if(detail == ""){
					alert("游戏详情不能为空");
					return false;
				}
				if(detail.length > 200){
					alert("游戏详情过长");
					return false;
				}
				var currency = $("#currency").val();
				if(currency == ""){
					alert("游币金额不能为空");
					return false;
				}
				if(!(/^[0-9]+\.?[0-9]*$/.test(currency))){
					alert("游币金额格式错误");
					return false;
				}
				var tariffe = $("#tariffe").val();
				if(tariffe == ""){
					alert("话费金额不能为空");
					return false;
				}
				if(!(/^[0-9]+\.?[0-9]*$/.test(tariffe))){
					alert("话费金额格式错误");
					return false;
				}
				if((/[#%*._&^$@/\\()<>{}[\] ]/.test(name))||(/[#%*._&^$@/\\()<>{}[\] ]/.test(developers))||(/[#%*._&^$@/\\()<>{}[\] ]/.test(gameFiling))||(/[#%*._&^$@/\\()<>{}[\] ]/.test(introduction))||(/[#%*._&^$@/\\()<>{}[\] ]/.test(detail))){
	  				alert("含有非法字符");
	  				return false;
	  			}
				if($("#id").val() ==null){
					
				
				var file1 = $("#coverFile").val();
				var file2 = $("#gameScreenO").val();
				var file3 = $("#gameScreenT").val();
				var file4 = $("#gameScreenTh").val();
				if(file1 == null ||file2 == null||file3 == null||file4 == null){
					alert("请上传图片");
					return false;
				}
				var type1 = file1.substring(file1.lastIndexOf(".")+1);
				var type2 = file2.substring(file2.lastIndexOf(".")+1);
				var type3 = file3.substring(file3.lastIndexOf(".")+1);
				var type4 = file4.substring(file4.lastIndexOf(".")+1);
				if(type1 !="jpg"&&type1 !="png"&&type1 !="bmp"){
					alert("请上传图片文件");
					return false;
				}
				if(type2 !="jpg"&&type2 !="png"&&type2 !="bmp"){
					alert("请上传图片文件");
					return false;
				}
				if(type3 !="jpg"&&type3 !="png"&&type3 !="bmp"){
					alert("请上传图片文件");
					return false;
				}
				if(type4 !="jpg"&&type4 !="png"&&type4 !="bmp"){
					alert("请上传图片文件");
					return false;
				}
				}
				return true;
			}
			function getDownListTypes(){
	  	    	$.ajax({
	  	            type:"POST",
	  	            url:"<%=basePath%>gameType/getTypes.do",
	  	            success:function (message) {
	  	                var list = message;
	  	                var typeEle = $("#gameType");
	  	                for (var i = 0; i < list.length; i++) {
	  	                    var option = $('<option></option>');
	  	                    option.attr("value",list[i].id);
	  	                    option.text(list[i].typeName);
	  	                  	typeEle.append(option);
	  	                }
	  	              var gameType = $("#hidden").val();
		  	  	      console.log(gameType);
		  	  	      $("#gameType").find("option[value='"+gameType+"']").attr("selected",true);
	  	            }
	  	        });
	  	    }
			$(document).ready(function(){
	  			getDownListTypes();
	  	    	
	  		});
	</script>
	
	
  </head>
  
  <body>
    <div id="container">
		<div class="page-header ">
			<h2>新增/修改 游戏</h2>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-8 col-md-offset-2">
				<form action="<%=basePath%>game/addOrModifyGame.do" class="form-horizontal text-center" enctype="multipart/form-data"
					method="post">
					<input type="hidden" name="id" id="id" value="${game.id }" >
					<table class="table table-bordered table-condensed ">
						<thead></thead>
						<tbody>
							<tr>
								<td >游戏名称</td>
								<td>
									<div class="col-sm-6 col-md-offset-3">
										<input type="text" id="name"
											class="col-sm-12" name="gameName"  value="${game.gameName}" />
											
									</div>
								</td>
							</tr>

							<tr>
								<td>游戏类型</td>
								<td>
									<div class="col-sm-6 col-md-offset-3">
									<input type="hidden" value="${game.gameType}" id = "hidden">
										<select id="gameType" name="gameType" class="col-sm-12" >
										<option>请选择</option>
										</select>
									</div>
								</td>
							</tr>

							<tr>
								<td>游戏状态</td>
								<td>
									<div class="col-sm-6 col-md-offset-3">
										<div class="col-sm-12">
												<label> <input type="radio"  name="gameStatus"
													value="1" checked="checked" <c:if test="${game.gameStatus==1}"> checked='checked'</c:if> 
												> 商用 </label>
												<label> <input type="radio"  name="gameStatus"
													value="2"  <c:if test="${game.gameStatus==2}"> checked='checked'</c:if> 
												> 下线</label>
										</div>
									</div>
								</td>
							</tr>

							<tr>
								<td>开发商</td>
								<td>
									<div class="col-sm-6 col-md-offset-3">
										<input id="developers" type="text" name="gameDevelopers"  class="col-sm-12" 
											value="${game.gameDevelopers}" />
									</div>
								</td>
							</tr>

							<tr>
								<td>备案号</td>
								<td>
									<div class=" col-sm-6 col-md-offset-3">
										<input type="text" id="gameFiling" name="gameFiling" class="col-sm-12" 
											value="${game.gameFiling}" />
									</div></td>
							</tr>

							<tr>
								<td>游戏简介</td>
								<td>
									<div class="col-sm-6 col-md-offset-3">
										<textarea id="introduction" name="gameIntroduction" class="form-control"
											rows="3">${game.gameIntroduction}</textarea>
									</div>
								</td>
							</tr>

							<tr>
								<td>游戏详情</td>
								<td>
									<div class="col-sm-6 col-md-offset-3">
										<textarea id="detail" name="gameDetail" class="form-control" rows="3">${game.gameDetail}</textarea>
									</div>
								</td>
							</tr>

							<tr>
								<td>游戏币价格</td>
								<td>
									<div class="col-sm-6 col-md-offset-3">
										<input id="currency" type="text" name="gameCurrency" class="col-sm-12"
											value="${game.gameCurrency}" />
									</div>
								</td>
							</tr>

							<tr>
								<td>话费价格</td>
								<td>
									<div class="col-sm-6 col-md-offset-3">
										<input id="tariffe" type="text" name="gameTariffe" class="col-sm-12" 
											value="${game.gameTariffe}" />
									</div>
								</td>
							</tr>

							<tr>
								<td>游戏封面</td>
								<td>
									<div class="col-sm-6  col-md-offset-3">
									<c:if test="${game.id != null}">
										<div class="thumbnail">
											<img class="img-responsive img-rounded " width="80"
												src="<%=basePath%>img/cover/${game.gameCover}" />
										</div>
									</c:if>
										<div class="caption">
											<p>
												<input type="file" id="coverFile" name="coverFile" value=""
													 />
											</p>
										</div>

									</div>
								</td>
							</tr>

							<tr>
								<td>游戏截图</td>
								<td>
									<c:if test="${game.id != null}">
								
									<div class="col-sm-4 col-md-2 col-md-offset-1">
										<div class="thumbnail">
											<img class="img-rounded " width="150"  src="<%=basePath%>img/screen/${game.gameScreen1}">
										</div>
										<div class="caption">
											<p><input type="file" value=""  name="gameScreenO" /></p>
										</div>
									</div>
									<div class="col-sm-4 col-md-2 col-md-offset-1">
										<div class="thumbnail">
											<img class="img-rounded "  width="150"   src="<%=basePath%>img/screen/${game.gameScreen2}">
										</div>
										<div class="caption">
											<p><input type="file" value=""   name="gameScreenT" /></p>
										</div>
									</div>
									<div class="col-sm-4 col-md-2 col-md-offset-1">
										<div class="thumbnail">
											<img class="img-rounded "  width="150"   src="<%=basePath%>img/screen/${game.gameScreen3}">
										</div>
										<div class="caption">
											<p><input type="file" value=""  name="gameScreenTh" /></p>
										</div>
									</div>
									</c:if>
									<c:if test="${game.id == null}">
										<div class="col-sm-6  col-md-offset-3">
											<div class="caption">
												<p><input type="file" value="" id="gameScreenO" name="gameScreenO" /></p>
											</div>
										</div><br>
										<div class="col-sm-6  col-md-offset-3">
											<div class="caption">
												<p><input type="file" value="" id="gameScreenT" name="gameScreenT" /></p>
											</div>
										</div><br>
										<div class="col-sm-6  col-md-offset-3">
											<div class="caption">
												<p><input type="file" value="" id="gameScreenTh" name="gameScreenTh" /></p>
											</div>
										</div>
									</c:if>
								</td>
							</tr>
							<c:if test="${game.id != null}">
								<tr>
									<td>创建时间</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<fmt:formatDate value="${game.createTime}" pattern="yyyy-MM-dd  hh:mm:ss"/>
										</div>
									</td>
								</tr>
	
								<tr>
									<td>修改时间</td>
									<td>
										<div class="col-sm-6 col-md-offset-3">
											<fmt:formatDate value="${game.updateTime}" pattern="yyyy-MM-dd  hh:mm:ss"/>
										</div>
									</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					<div class="row">
						<div class="col-xs-6 col-sm-10">
							<div class="form-group">
								<div class="col-xs-6 col-sm-4 col-md-offset-3">
									<input type="submit" class="btn btn-info"  value="提交"  onclick="return check()"/>
								</div>
								<div class="col-xs-6 col-sm-4">
									<a href="<%=basePath%>game/game.jsp" class="btn btn-warning" >返回上一页</a>
								</div>
							</div>
						</div>
					</div>
				</form>

			</div>
		</div>
	</div>
	    <c:if test="${isError}">
     	 <script type="text/javascript">
     	 	alert("${errorMessage}")
     	 </script>
     	</c:if>
  </body>
  <style>
td {
	vertical-align: middle !important;
}
</style>
</html>
