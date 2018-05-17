package Web;

import Dao.GradeDao;
import Dao.StudentDao;
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

@WebServlet(name = "gradeDeleteServlet")
public class gradeDeleteServlet extends HttpServlet {
	DBUtil dbUtil=new DBUtil();
	GradeDao gradeDao=new GradeDao();
	StudentDao studentDao = new StudentDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String delIds=request.getParameter("delIds");
		Connection con=null;
		try{
			con=dbUtil.GetCon();
			JSONObject result=new JSONObject();
			String str[]=delIds.split(",");
			for(int i=0;i<str.length;i++){
				boolean flag=studentDao.getStudentByGradeId(con, str[i]);
				if(flag){
					result.put("errorIndex", i);
					result.put("errorMsg", "班级下面有学生，不能删除！");
					ResponseUtil.write(response, result);
					return;
				}
			}
			int delNums=gradeDao.gradeDelete(con, delIds);
			if(delNums>0){
				result.put("success", "true");
				result.put("delNums", delNums);
			}else{
				result.put("errorMsg", "删除失败");
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
