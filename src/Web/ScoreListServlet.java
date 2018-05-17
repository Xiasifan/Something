package Web;

import Bean.PageBean;
import Bean.Score;
import Dao.ScoreDao;
import Util.DBUtil;
import Util.JsonUtil;
import Util.ResponseUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "ScoreListServlet")
public class ScoreListServlet extends HttpServlet {
	DBUtil dbUtil = new DBUtil();
	ScoreDao scoreDao = new ScoreDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");

		String stuId = request.getParameter("stuId");

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = dbUtil.GetCon();
			JSONObject result = new JSONObject();
			Score score =new Score();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(scoreDao.scoreList(con, pageBean, score));
			int total = scoreDao.scoreCount(con, score);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.CloseCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
}
