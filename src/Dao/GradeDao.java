package Dao;

import Bean.Grade;
import Bean.PageBean;
import Util.String_util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GradeDao {
	public ResultSet gradeList(Connection con,PageBean pageBean,Grade grade)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_grade");
		if(grade!=null && String_util.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and","where"));
		return pstmt.executeQuery();
	}

	public int gradeCount(Connection con,Grade grade)throws Exception{
		StringBuffer sb=new StringBuffer("select count(*) as total from t_grade");
		if(String_util.isNotEmpty(grade.getGradeName())){
			sb.append(" and gradeName like '%"+grade.getGradeName()+"%'");
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}
	/*
	delete from tableName where field in (1,3,5)

	 */
	public int gradeDelete(Connection connection,String delIDs) throws Exception {
		String sql = "delete from t_grade where id in(" + delIDs + ")";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		return preparedStatement.executeUpdate();
	}
	public int gradeAdd(Connection connection,Grade grade) throws Exception {
		String sql = "insert into t_grade values(null,?,?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,grade.getGradeName());
		preparedStatement.setString(2,grade.getGradeDesc());
		return preparedStatement.executeUpdate();
	}

	public int gradeModify(Connection connection, Grade grade)throws Exception {
		String sql = "update t_grade set gradeName=?,gradeDesc=? where id=?";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1,grade.getGradeName());
		preparedStatement.setString(2,grade.getGradeDesc());
		preparedStatement.setInt(3,grade.getId());
		return preparedStatement.executeUpdate();
	}
}
