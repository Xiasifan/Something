package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Bean.PageBean;
import Bean.Score;
import Bean.Student;
import Util.DateUtil;
import Util.String_util;

public class ScoreDao {
	public ResultSet scoreList(Connection con, PageBean pageBean, Score score) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_score s, t_student g where s.stuId=g.stuId");
		if (String_util.isNotEmpty(score.getStuNo())) {
			sb.append(" and s.stuNo like '%" + score.getStuNo() + "%'");
		}
		if (String_util.isNotEmpty(score.getStuName())) {
			sb.append(" and s.stuName like '%" + score.getStuName() + "%'");
		}
		if (String_util.isNotEmpty(score.getCourseName())) {
			sb.append(" and s.courseName ='" + score.getCourseName() + "'");
		}
		if (String_util.isNotEmpty(score.getCourseName2())) {
			sb.append(" and s.courseName2 ='" + score.getCourseName2() + "'");
		}
		if (String_util.isNotEmpty(score.getCourseName3())) {
			sb.append(" and s.courseName3 ='" + score.getCourseName3() + "'");
		}
		if (String_util.isNotEmpty(score.getCourseName4())) {
			sb.append(" and s.courseName4 ='" + score.getCourseName4() + "'");
		}
		if (String_util.isNotEmpty(score.getCourseName5())) {
			sb.append(" and s.courseName5 ='" + score.getCourseName5() + "'");
		}
		if (String_util.isNotEmpty(score.getCourseName6())) {
			sb.append(" and s.courseName6 ='" + score.getCourseName6() + "'");
		}
		if (String_util.isNotEmpty(score.getCourseName7())) {
			sb.append(" and s.courseName7 ='" + score.getCourseName7() + "'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		return pstmt.executeQuery();
	}

	public int scoreCount(Connection con, Score score) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_student s,t_score g where s.stuId=g.stuId");
		if (String_util.isNotEmpty(score.getStuNo())) {
			sb.append(" and s.stuNo like '%" + score.getStuNo() + "%'");
		}
		if (String_util.isNotEmpty(score.getStuName())) {
			sb.append(" and s.stuName like '%" + score.getStuName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString());
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public int scoreDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_scorewhere score in(" + delIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int scoreAdd(Connection con, Score score) throws Exception {
		String sql = "insert into t_score  values(null,null,null,null,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, score.getStuNo());
		pstmt.setInt(2, score.getStuId());
		pstmt.setString(3, score.getStuNo());
		pstmt.setString(4, score.getStuName());
		pstmt.setString(5, score.getCourseName());
		pstmt.setString(6, score.getCourseName2());
		pstmt.setString(7, score.getCourseName3());
		pstmt.setString(8, score.getCourseName4());
		pstmt.setString(9, score.getCourseName5());
		pstmt.setString(10, score.getCourseName6());
		pstmt.setString(11, score.getCourseName7());
		return pstmt.executeUpdate();
	}

	public int scoreModify(Connection con, Score score) throws Exception {
		String sql = "update t_score  set   courseName=?,courseName2=?,courseName3=?,courseName4=? ,courseName5=?,courseName6=?,courseName7=? where scoreID=?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1,score.getCourseName());
		pstmt.setString(2,score.getCourseName2());
		pstmt.setString(3,score.getCourseName3());
		pstmt.setString(4,score.getCourseName4());
		pstmt.setString(5,score.getCourseName5());
		pstmt.setString(6,score.getCourseName6());
		pstmt.setString(7,score.getCourseName7());
		pstmt.setInt(8, score.getScoreId());
		return pstmt.executeUpdate();
	}
}
