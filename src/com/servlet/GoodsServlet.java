package com.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.beans.GoodsInfo;
import com.dao.GoodsDao;

@WebServlet("/GoodsServlet")

public class GoodsServlet extends HttpServlet {
	GoodsDao goodsDao=new GoodsDao();
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String flag=request.getParameter("flag");
		
		if("add".equals(flag)){
			add(request,response);
		}
		if("manage".equals(flag)){
			manage(request,response);
		}
		
	}

	//��ѯ�����û���ת���û�ά������
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GoodsInfo> goodsList=goodsDao.getAllGoods();
		request.setAttribute("goodsList", goodsList);

		request.getRequestDispatcher("/goods/goods_manage.jsp").forward(request, response);
	}

	//�����Ʒ
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ȡ�ͻ��˴�����������
		String goodsName=request.getParameter("goodsName");
		int bigCateId=  Integer.parseInt(request.getParameter("bigCateId"));
		int smallCateId=  Integer.parseInt(request.getParameter("smallCateId"));
		String unit=request.getParameter("unit");
		Float price=Float.parseFloat(request.getParameter("price"));
		String producter=request.getParameter("producter");
		String des=request.getParameter("des");
		
		GoodsInfo goods= new GoodsInfo();
		goods.setGoodsName(goodsName);
		goods.setBigCateId(bigCateId);
		goods.setSmallCateId(smallCateId);
		goods.setUnit(unit);
		goods.setPrice(price);
		goods.setProducter(producter);
		goods.setDes(des);
		
		//����dao��,�������ݲ���, ��ת��
		int result=goodsDao.addGoods(goods);
		if(result==1) {
			request.setAttribute("msg", "��Ʒ��ӳɹ�");
			request.getRequestDispatcher("/goods/goods_add.jsp").forward(request, response);
		}
	}
	
	
	

}
