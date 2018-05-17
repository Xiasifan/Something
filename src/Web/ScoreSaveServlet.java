package Web;

import Bean.Score;
import Dao.ScoreDao;
import Util.DBUtil;
import Util.ResponseUtil;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ScoreSaveServlet")
public class ScoreSaveServlet extends HttpServlet {
	DBUtil dbUtil = new DBUtil();
	ScoreDao scoreDao = new ScoreDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String scoreID=request.getParameter("scoreId");
		String stuNo=request.getParameter("stuNo");
		String stuName=request.getParameter("stuName");
		String  courseName=request.getParameter("courseName");
		String courseName2=request.getParameter("courseName2");
		String courseName3=request.getParameter("courseName3");
		String courseName4=request.getParameter("courseName4");
		String courseName5=request.getParameter("courseName5");
		String courseName6=request.getParameter("courseName6");
		String courseName7=request.getParameter("courseName7");
		Score score = null;
		score = new Score(Integer.parseInt(scoreID), stuNo, stuName, courseName, courseName2, courseName3,
				courseName4, courseName5, courseName6, courseName7);
		Connection connection = null;
		try {
			connection = dbUtil.GetCon();
			int saveNum = 0;
			JSONObject result = new JSONObject();
			saveNum = scoreDao.scoreModify(connection, score);
			if (saveNum > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
				result.put("errorMsg", "保存失败");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.CloseCon(connection);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

}
