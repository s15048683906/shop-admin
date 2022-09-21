package com.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.beans.CateInfo;
import com.dao.CateDao;

@WebServlet("/CateServlet")

public class CateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CateDao cateDao=new CateDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");

		if("addSmallCate".equals(flag)) {
			addSmallCate(request,response);
		}
		else if("addBigCate".equals(flag)) {
			addBigCate(request,response);
		}
		else if("manage".equals(flag)){
			manage(request,response);
		}
		else if("delete".equals(flag)){
			delete(request,response);
		}

	}
	
    //删除商品分类
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
		int result=cateDao.deleteCateById(id);
		
		if(result==1) {
			request.setAttribute("msg", "删除成功");
			manage(request,response);
		}
		
	}	


	//根据父子级关系，显示商品分类维护界面
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int parentId=0;
		List<CateInfo> cateList=cateDao.getCateList(parentId);
		request.setAttribute("cateList", cateList);
		request.getRequestDispatcher("/cate/cate_manage.jsp").forward(request, response);
	}

	//添加分类
	private void addBigCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取客户端传过来的数据
		String cateName=request.getParameter("cateName");
		String des=request.getParameter("des");
		
		CateInfo cate= new CateInfo();
		cate.setCateName(cateName);
		cate.setDes(des);
		
		//调用dao层,进行数据操作, 并转向
		int result=cateDao.addCate(cate);
		if(result==1) {
			request.setAttribute("msg", "分类添加成功");
			request.getRequestDispatcher("/cate/bigcate_add.jsp").forward(request, response);
		}
		
	}

	private void addSmallCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bigCateId=Integer.parseInt(request.getParameter("bigCateId"));
		String cateName=request.getParameter("cateName");
		String des=request.getParameter("des");
		
		CateInfo cate=new CateInfo();
		cate.setParentId(bigCateId);
		cate.setCateName(cateName);
		cate.setDes(des);
		
		int result=cateDao.addCate(cate);
		if(result==1) {
			request.setAttribute("msg", "分类添加成功");
			request.getRequestDispatcher("/cate/second_cate.jsp").forward(request, response);;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				doGet(request, response);
	}
	
}
