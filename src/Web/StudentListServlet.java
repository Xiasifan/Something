package Web;

import Bean.PageBean;
import Bean.Student;
import Dao.StudentDao;
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

@WebServlet(name = "StudentListServlet")
public class StudentListServlet extends HttpServlet {
	DBUtil dbUtil = new DBUtil();
	StudentDao studentDao = new StudentDao();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");
		String sex = request.getParameter("sex");
		String bbirthday = request.getParameter("bbirthday");
		String ebirthday = request.getParameter("ebirthday");
		String gradeId = request.getParameter("gradeId");

		Student student = new Student();
		if (stuNo != null) {
			student.setStuNo(stuNo);
			student.setStuName(stuName);
			student.setSex(sex);
			if (String_util.isNotEmpty(gradeId)) {
				student.setGradeId(Integer.parseInt(gradeId));
			}
		}

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con = null;
		try {
			con = dbUtil.GetCon();
			JSONObject result = new JSONObject();
			JSONArray jsonArray = JsonUtil.formatRsToJsonArray(studentDao.studentList(con, pageBean, student, bbirthday, ebirthday));
			int total = studentDao.studentCount(con, student, bbirthday, ebirthday);
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
