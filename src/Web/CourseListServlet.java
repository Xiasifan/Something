package Web;

import Bean.Course;
import Bean.PageBean;
import Dao.courseDao;
import Util.DBUtil;
import Util.JsonUtil;
import Util.ResponseUtil;
import Util.String_util;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "CourseListServlet")
public class CourseListServlet extends HttpServlet {
	DBUtil dbUtil = new DBUtil();
	courseDao courseDao = new courseDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gradeId = request.getParameter("gradeId");

		Course course = new Course();
		if (String_util.isNotEmpty(gradeId)) {
			course.setGradeId(Integer.parseInt(gradeId));
		}
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = dbUtil.GetCon();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(courseDao.courseList(con,pageBean,course));
			int total = courseDao.courseCount(con,course);
			result.put("rows", jsonArray);
			result.put("total", total);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
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
