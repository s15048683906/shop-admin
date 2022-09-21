<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title></title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	
	<script type="text/javascript" src="js/jquery-1.8.0.js"></script> 
	<script type="text/javascript">
		$(function(){
			$("#ch_checkall,#top_ch_checkall").click(function(){
				if(this.checked){
					$("input[name=ck_id]").attr("checked","checked");
				}else{
					$("input[name=ck_id]").removeAttr("checked");
				}		
			});
					
			$("table tr").mouseover(function(){
				$(this).css("background","#D3EAEF");
				$(this).siblings().css("background","white");
			});
		});
	</script>

	<link rel="stylesheet" type="text/css" href="css/maintable.css" ></link>    

  </head>
  
  <body>
	<div class ="div_title">
		<div class="div_titlename"> <img src="images/san_jiao.gif" ><span>商品维护  分类管理</span></div>
    </div>   
	<form action="CateServlet" method="post">
	    <input type="hidden" name="flag" value="manage">
	    <input type="hidden" name="cateId" value="${cate.id }">
	    
	    <table class="main_table">
	    
	       <tr>
	       		<th colspan="2" >一级分类</th>
	       		<th colspan="2" >二级分类</th>
	       </tr>
	       
	       <c:forEach var="cate" items="${cateList }">
	       		<tr>
		       		<td>
		       			${cate.cateName }_${cate.id }
		       		</td>
		       		<td>
		      			<a href="CateServlet?flag=delete&id=${cate.id }" onclick="return confirm('确定要删除吗')" >删除</a>
          			</td>
		       		<td>
		       			<c:forEach var="ca_sub" items="${cate.subCateList }">
		       				${ca_sub.cateName }_${ca_sub.id }<br />
		       			</c:forEach>
		       		</td>
		       		<td>
		       			<c:forEach var="ca_sub" items="${cate.subCateList }">
		      			<a href="CateServlet?flag=delete&id=${cate.id }" onclick="return confirm('确定要删除吗')" >删除</a><br/>
          				</c:forEach>
          			</td>
	            </tr>
	       </c:forEach>
	       
	    </table>
	    
	</form>
	
	${msg }
	
  </body>
</html>





