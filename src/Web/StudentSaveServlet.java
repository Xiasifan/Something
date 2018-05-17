package Web;

import Bean.Student;
import Dao.StudentDao;
import Util.DBUtil;
import Util.DateUtil;
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

@WebServlet(name = "StudentSaveServlet")
public class StudentSaveServlet extends HttpServlet {
	DBUtil dbUtil = new DBUtil();
	StudentDao studentDao=new StudentDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String stuNo=request.getParameter("stuNo");
		String stuName=request.getParameter("stuName");
		String sex=request.getParameter("sex");
		String birthday=request.getParameter("birthday");
		String gradeId=request.getParameter("gradeId");
		String phoneNo=request.getParameter("phoneNo");
		String stuDesc=request.getParameter("stuDesc");
		String stuId=request.getParameter("stuId");

		Student student=null;
		try {
			student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),
					Integer.parseInt(gradeId), phoneNo, stuDesc);
		}  catch (Exception e1) {
			e1.printStackTrace();
		}
		if(String_util.isNotEmpty(stuId)){
			student.setStuId(Integer.parseInt(stuId));
		}
		Connection con=null;
		try{
			con=dbUtil.GetCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(String_util.isNotEmpty(stuId)){
				saveNums=studentDao.studentModify(con, student);
			}else{
				saveNums=studentDao.studentAdd(con, student);
			}
			if(saveNums>0){
				result.put("success", "true");
			}else{
				result.put("success", "true");
				result.put("errorMsg", "保存失败");
			}
			ResponseUtil.write(response, result);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
