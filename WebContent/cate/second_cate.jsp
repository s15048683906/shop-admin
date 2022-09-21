<%@ page language="java" import="java.util.*,com.beans.*,com.dao.*" pageEncoding="UTF-8"%>
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
	
	<link rel="stylesheet" type="text/css" href="css/edittable.css"  ></link>  
	<link rel="stylesheet" type="text/css" href="css/validate.css"  ></link>  
	<script type="text/javascript"  src="js/jquery-1.8.0.js"></script>

	 <script>		
			$(function(){
				 $("input[type=text],textarea").focus(function(){
					  $(this).addClass("input_focus");
					}).blur(function(){
							$(this).removeClass("input_focus");
					});

				$(".form_btn").hover(function(){
						$(this).css("color","red").css("background","#6FB2DB");
					},
					
					function(){
						$(this).css("color","#295568").css("background","#BAD9E3");
					});	
			});		
	</script>  

  </head>
  
  <body>
     <div class ="div_title">
		 <div class="div_titlename"> <img src="images/san_jiao.gif" ><span>商品管理   添加二级分类</span></div>
	 </div>
	 <%
	 	CateDao cateDao=new CateDao();
	 	 List<CateInfo> cateList=cateDao.getCateList(0);
	 	 request.setAttribute("cateList",cateList);
	 %>
	<form action="CateServlet" method="post">
		<input type="hidden" name="flag" value="addSmallCate">
		<table class="edit_table" >
				<tr>
		 			 	<td class="td_info">父级分类：</td>
		 			 	<td class="td_bg"> 
	 			 		    <select name="bigCateId">
								<option>请选择所属分类</option>
								<c:forEach var="cate" items="${cateList }">
									<option value="${cate.id}"> ${cate.cateName }</option>
								</c:forEach>
							</select>
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="cateName_msg">2-20位，非空白字符</label>
		 			 	</td> 
		 		</tr>
		 		<tr>
		 			 	<td class="td_info">分类名称：</td>	
		 			 	<td class="td_input_short"> 
		 			 		<input type="text" class="txtbox" id="cateName" name="cateName" value="${param.cateName }"/> 
		 			 	</td>   
		 			 	<td>
		 			 		<label class="validate_info" id="cateName_msg">2-20位，非空白字符</label>
		 			 	</td> 
		 		</tr>
		 		<tr>
		 			<td class="td_info">分类描述:</td>	
		 			<td><textarea rows="4" cols="27" name="des" class="txtarea">${param.des }</textarea> </td>	
		 			<td><label></label></td>	
		 		</tr>
		 		<tr>
		 			<td class="td_info"> </td>	
		 			<td> 
		 			<input class="form_btn" type="submit" value="提交" onclick="return confirm('确定提交吗')" />
		 			<input type="reset"  class="form_btn" value="重置" /> </td>	
		 			<td>
		 				<label id="result_msg" class="result_msg"></label>
		 			</td>	
		 		</tr>
		</table>
	</form>
    ${msg }
  </body>
</html>
