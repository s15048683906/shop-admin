package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.beans.CateInfo;
import com.jdbc.DBUtil;
import com.jdbc.MyBatisUtil;

public class CateDao {
	//根据父级id，查询其下级分类，如果parentid为0，查询所有一级分类
	public List<CateInfo> getCateList(int parentId){
		SqlSession s=MyBatisUtil.getSession();
		List<CateInfo> cateList=s.selectList("cate.getCateList",parentId);
		for(CateInfo cate:cateList) {
			if(cate.getParentId()==0) {
				cate.setSubCateList(getCateList(cate.getId()));
			}
		}
		s.close();
		return cateList;
	}
	
	//添加分类
	public int addCate(CateInfo cate) {
		SqlSession s= MyBatisUtil.getSession();
		int result=s.insert("cate.addCate",cate);
		s.commit();
		s.close();
		return result;
	}

	//查询所有分类
	public List<CateInfo> getAllCate() {
		List<CateInfo> cateList=new ArrayList<CateInfo>();
		
		Connection conn=null;
		PreparedStatement stm=null;
		ResultSet rs=null;

		try {
			conn=DBUtil.getConn();
			String sql="select * from cateInfo";
			stm=conn.prepareStatement(sql);
			rs=stm.executeQuery();
			while(rs.next()) {
				CateInfo cate=new CateInfo();
				cate.setId(rs.getInt("id"));
				cate.setCateName(rs.getString("cateName"));
				
				cateList.add(cate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, stm, conn);
		}

	    return cateList;
	}

	public CateInfo getCateById(int id) {
		SqlSession s=MyBatisUtil.getSession();
		CateInfo cate=s.selectOne("cate.getCateById",id);
		s.close();
		return cate;
	}

	public int deleteCateById(int id) {
		SqlSession s=MyBatisUtil.getSession();
		int result=s.delete("cate.deleteCateById",id);
		s.commit();  	
		s.close();
		return result;	
	}

}
