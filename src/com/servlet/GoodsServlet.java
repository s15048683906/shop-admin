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

	//查询所有用户，转到用户维护界面
	private void manage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GoodsInfo> goodsList=goodsDao.getAllGoods();
		request.setAttribute("goodsList", goodsList);

		request.getRequestDispatcher("/goods/goods_manage.jsp").forward(request, response);
	}

	//添加商品
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//取客户端传过来的数据
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
		
		//调用dao层,进行数据操作, 并转向
		int result=goodsDao.addGoods(goods);
		if(result==1) {
			request.setAttribute("msg", "商品添加成功");
			request.getRequestDispatcher("/goods/goods_add.jsp").forward(request, response);
		}
	}
	
	
	

}
