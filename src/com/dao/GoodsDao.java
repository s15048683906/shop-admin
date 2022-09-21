package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.beans.GoodsInfo;
import com.jdbc.DBUtil;

public class GoodsDao {
	
	 //查询所有商品
	public List<GoodsInfo> getAllGoods() {
		List<GoodsInfo> goodsList=new ArrayList<GoodsInfo>();
			
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;

		try {
			conn=DBUtil.getConn();
			String sql="select * from goodsInfo";
			stm=conn.prepareStatement(sql);
			rs=stm.executeQuery();
			while(rs.next()) {
				GoodsInfo goods=new GoodsInfo();
				goods.setGoodsName(rs.getString("goodsName"));
				goods.setUnit(rs.getString("unit"));
				goods.setPrice(rs.getFloat("price"));
				goods.setBigCateId(rs.getInt("bigCateId"));
				goods.setSmallCateId(rs.getInt("smallCateId"));

				goodsList.add(goods);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, stm, conn);
		}

		return goodsList;
	}

	//添加商品
	public int addGoods(GoodsInfo goods) {
		int result=0;
		Connection conn=null;
		PreparedStatement stm=null;
		try {
			conn=DBUtil.getConn();
			String sql="insert into goodsInfo (goodsName,bigCateId,smallCateId,unit,price,producter,des) values(?,?,?,?,?,?,?)";
			stm=conn.prepareStatement(sql);
			stm.setObject(1, goods.getGoodsName());
			stm.setObject(2, goods.getBigCateId());
			stm.setObject(3, goods.getSmallCateId());
			stm.setObject(4, goods.getUnit());
			stm.setObject(5,goods.getPrice());
			stm.setObject(6, goods.getProducter());
			stm.setObject(7, goods.getDes());
			result=stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtil.close(null, stm, conn);
		}
		return result;
	}
	
	

}
