package com.dao;
//package com.dao;
//
//import java.sql.*;
//import java.util.*;
//
//import com.beans.AdminInfo;
//import com.beans.RoleInfo;
//import com.jdbc.DBUtil;
//
//public class RoleDao {
//	
//	/**
//	 * ��ɫ���
//	 * @param role ��ɫ�û���Ϣʵ����
//	 * @return �ɹ�����1
//	 */
//	public int addRole(RoleInfo role) {
//		int result=0;
//		Connection conn=null;
//		PreparedStatement stm=null;
//		try {
//			conn=DBUtil.getConn();
//			String sql="insert into roleInfo (roleName,des) values(?,?)";
//			stm=conn.prepareStatement(sql);
//			stm.setObject(1, role.getRoleName());
//			stm.setObject(2, role.getDes());
//			
//			result=stm.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally {
//			DBUtil.close(null, stm, conn);
//		}
//		return result;
//	}
//	
//	//��ѯ�����û� 
//	public List<RoleInfo> getAllRole() {
//		List<RoleInfo> roleList=new ArrayList<RoleInfo>();
//			Connection conn=null;
//			PreparedStatement stm=null;
//			ResultSet rs=null;
//
//			try {
//				conn=DBUtil.getConn();
//				String sql="select * from roleInfo";
//				stm=conn.prepareStatement(sql);
//				rs=stm.executeQuery();
//				while(rs.next()) {
//					RoleInfo role=new RoleInfo();
//					role.setId(rs.getInt("id"));
//					role.setRoleName(rs.getString("roleName"));
//					role.setDes(rs.getString("des"));
//					roleList.add(role);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}finally{
//				DBUtil.close(rs, stm, conn);
//			}
//
//		return roleList;
//	}
//	
//    //��ѯ�û���Ϣ     
//	public RoleInfo getRoleById(int id) {
//		RoleInfo role=null;
//		Connection conn=null;
//		PreparedStatement stm=null;  
//		ResultSet rs=null;
//		
//		try {
//				conn=DBUtil.getConn();
//				String sql="select * from roleinfo where id=? "; 
//				stm=conn.prepareStatement(sql);  
//				stm.setInt(1, id);
//				
//				rs=stm.executeQuery();
//				if(rs.next()){
//					role=new RoleInfo();
//					role.setId(rs.getInt("id"));
//					role.setRoleName(rs.getString("roleName"));
//					role.setDes(rs.getString("des"));
//				}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally{
//			DBUtil.close(rs, stm, conn);
//		}
//		
//		return role;	
//	}
//	
//	//�޸��û�
//	public int updateRole(RoleInfo role) {
//		int result=0;
//		Connection conn=null;
//		PreparedStatement stm=null;
//		try {
//			conn=DBUtil.getConn();
//			String sql="update roleInfo set roleName=?, des=? where id=?";
//			stm=conn.prepareStatement(sql);
//			stm.setObject(1, role.getRoleName());
//			stm.setObject(2, role.getDes());
//			stm.setObject(3, role.getId());
//
//			result =stm.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}finally{
//			DBUtil.close(null, stm, conn);
//		}
//		return result;
//	}
//	
//	//�û�ɾ��
//			public int deleteRoleById(int id) {
//				int result=0;
//				Connection conn=null;
//				PreparedStatement stm=null;
//				try {
//					conn=DBUtil.getConn();
//					String sql="delete from roleInfo where id=?";
//					stm=conn.prepareStatement(sql);
//					stm.setInt(1, id);
//					result= stm.executeUpdate();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}finally{
//					DBUtil.close(null, stm, conn);
//				}
//				return result;
//			}
//	//��ѯadmin_list�����û� 
//	public List<AdminInfo> getAllAdmin() {
//		List<AdminInfo> adminList=new ArrayList<AdminInfo>();
//			
//			Connection conn=null;
//			PreparedStatement stm=null;
//			ResultSet rs=null;
//
//			try {
//				conn=DBUtil.getConn();
//				String sql="select a.*, b.roleName from adminInfo a left join roleInfo b on a.roleId=b.id";
//				stm=conn.prepareStatement(sql);
//				rs=stm.executeQuery();
//				while(rs.next()) {
//					AdminInfo admin=new AdminInfo();
//					admin.setId(rs.getInt("id"));
//					admin.setAdminName(rs.getString("adminName"));
//					admin.setPassword(rs.getString("password"));
//					admin.setNote(rs.getString("note"));
//					admin.setRoleId(rs.getInt("roleId"));
//					admin.setState(rs.getString("state"));
//					admin.setState(rs.getString("roleName"));
//					
//					adminList.add(admin);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}finally{
//				DBUtil.close(rs, stm, conn);
//			}
//
//		return adminList;
//	}
//
//}
