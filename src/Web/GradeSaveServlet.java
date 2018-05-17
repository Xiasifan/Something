package Web;

import Bean.Grade;
import Dao.GradeDao;
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

@WebServlet(name = "GradeSaveServlet")
public class GradeSaveServlet extends HttpServlet {
	DBUtil dbUtil=new DBUtil();
	GradeDao gradeDao=new GradeDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gradeName=request.getParameter("gradeName");
		String gradeDesc=request.getParameter("gradeDesc");
		String id=request.getParameter("id");
		Grade grade=new Grade(gradeName,gradeDesc);
		if(String_util.isNotEmpty(id)){
			grade.setId(Integer.parseInt(id));
		}
		Connection con=null;
		try{
			con=dbUtil.GetCon();
			int saveNums=0;
			JSONObject result=new JSONObject();
			if(String_util.isNotEmpty(id)){
				saveNums=gradeDao.gradeModify(con, grade);
			}else{
				saveNums=gradeDao.gradeAdd(con, grade);
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
		doPost(request, response);
	}
}
