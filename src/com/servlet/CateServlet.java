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
	
    //ɾ����Ʒ����
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id= Integer.parseInt(request.getParameter("id"));
		int result=cateDao.deleteCateById(id);
		
		if(result==1) {
			request.setAttribute("msg", "ɾ���ɹ�");
			manage(request,response);
		}
		
	}	


	//���ݸ��Ӽ���ϵ����ʾ��Ʒ����ά������
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int parentId=0;
		List<CateInfo> cateList=cateDao.getCateList(parentId);
		request.setAttribute("cateList", cateList);
		request.getRequestDispatcher("/cate/cate_manage.jsp").forward(request, response);
	}

	//��ӷ���
	private void addBigCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ȡ�ͻ��˴�����������
		String cateName=request.getParameter("cateName");
		String des=request.getParameter("des");
		
		CateInfo cate= new CateInfo();
		cate.setCateName(cateName);
		cate.setDes(des);
		
		//����dao��,�������ݲ���, ��ת��
		int result=cateDao.addCate(cate);
		if(result==1) {
			request.setAttribute("msg", "������ӳɹ�");
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
			request.setAttribute("msg", "������ӳɹ�");
			request.getRequestDispatcher("/cate/second_cate.jsp").forward(request, response);;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
				doGet(request, response);
	}
	
}
