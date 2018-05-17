package Web;

import Bean.Course;
import Dao.courseDao;
import Util.DBUtil;
import Util.ResponseUtil;
import Util.String_util;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "CourseSaveServlet")
public class CourseSaveServlet extends HttpServlet {
	DBUtil dbUtil = new DBUtil();
	courseDao courseDao = new courseDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
 		String courseId = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String courseName2 = request.getParameter("courseName2");
		String courseName3 = request.getParameter("courseName3");
		String courseName4 = request.getParameter("courseName4");
		String courseName5 = request.getParameter("courseName5");
		String courseName6 = request.getParameter("courseName6");
		String courseName7 = request.getParameter("courseName7");
		String courseDesc = request.getParameter("courseDesc");
		Course course = null;
		course = new Course(Integer.parseInt(courseId),courseName,courseName2, courseName3, courseName4,courseName5,courseName6, courseName7, courseDesc);
		if (String_util.isNotEmpty(courseId)) {
			course.setCourseId(Integer.parseInt(courseId));
		}
		Connection connection = null;
		try {
			connection = dbUtil.GetCon();
			int saveNum = 0;
			JSONObject result=new JSONObject();
			saveNum = courseDao.courseModify(connection, course);
			if (saveNum > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
				result.put("errorMsg", "保存失败");
			}
			ResponseUtil.write(response,result);
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
