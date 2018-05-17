package Web;

import Bean.Grade;
import Bean.PageBean;
import Dao.GradeDao;
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
import java.sql.ResultSet;

@WebServlet(name = "GradeServlet")
public class GradeListServlet extends HttpServlet {

	DBUtil dbUtil = new DBUtil();
	GradeDao gradeDao = new GradeDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String gradeName = request.getParameter("gradeName");
		if (gradeName == null) {
			gradeName = "";
		}
		Grade grade = new Grade();
		grade.setGradeName(gradeName);
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = dbUtil.GetCon();
			JSONObject result = new JSONObject();
			final ResultSet rs = gradeDao.gradeList(con, pageBean, grade);
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(rs);
			int total = gradeDao.gradeCount(con, grade);
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
		doPost(request, response);
	}
}
