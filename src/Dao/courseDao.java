package Dao;

import Bean.Course;
import Bean.PageBean;
import Bean.Student;
import Util.String_util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class courseDao {
	public ResultSet courseList(Connection con, PageBean pageBean, Course course) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_course c,t_grade g where c.gradeId=g.id");
		if (course.getGradeId() != -1) {
			sb.append(" and c.gradeId ='"+course.getGradeId()+"'");
		}
		if (String_util.isNotEmpty(course.getCourseName())) {
			sb.append("and c.course like '%" + course.getCourseName() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName2())) {
			sb.append("and c.course like '%" + course.getCourseName2() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName3())) {
			sb.append("and c.course like '%" + course.getCourseName3() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName4())) {
			sb.append("and c.course like '%" + course.getCourseName4() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName5())) {
			sb.append("and c.course like '%" + course.getCourseName5() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName6())) {
			sb.append("and c.course like '%" + course.getCourseName6() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName7())) {
			sb.append("and c.course like '%" + course.getCourseName7() + "%'");
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getRows());
		}
		PreparedStatement pstmt=con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}
	public int courseCount(Connection connection,Course course) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_course c,t_grade g where c.gradeId=g.id");
		if (course.getGradeId() != -1) {
			sb.append(" and c.gradeId ='"+course.getGradeId()+"'");
		}
		if (String_util.isNotEmpty(course.getCourseName())) {
			sb.append("and c.course like '%" + course.getCourseName() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName2())) {
			sb.append("and c.course like '%" + course.getCourseName2() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName3())) {
			sb.append("and c.course like '%" + course.getCourseName3() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName4())) {
			sb.append("and c.course like '%" + course.getCourseName4() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName5())) {
			sb.append("and c.course like '%" + course.getCourseName5() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName6())) {
			sb.append("and c.course like '%" + course.getCourseName6() + "%'");
		}
		if (String_util.isNotEmpty(course.getCourseName7())) {
			sb.append("and c.course like '%" + course.getCourseName7() + "%'");
		}
		PreparedStatement pstmt=connection.prepareStatement(sb.toString());
		ResultSet rs=pstmt.executeQuery();
		if(rs.next()){
			return rs.getInt("total");
		}else{
			return 0;
		}
	}

	public int courseModify(Connection con, Course course)throws Exception {
		String sql="update t_course set courseName=?,courseName2=?,courseName3=?,courseName4=?,courseName5=?,courseName6=?,courseName7=?,courseDesc=? where courseId=?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);

		preparedStatement.setString(1,course.getCourseName());
		preparedStatement.setString(2,course.getCourseName2());
		preparedStatement.setString(3,course.getCourseName3());
		preparedStatement.setString(4,course.getCourseName4());
		preparedStatement.setString(5,course.getCourseName5());
		preparedStatement.setString(6,course.getCourseName6());
		preparedStatement.setString(7,course.getCourseName7());
		preparedStatement.setString(8,course.getCourseDesc());
		preparedStatement.setInt(9,course.getCourseId());
		return preparedStatement.executeUpdate();
	}
}
