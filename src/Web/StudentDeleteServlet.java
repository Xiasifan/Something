package Web;

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

@WebServlet(name = "StudentDeleteServlet")
public class StudentDeleteServlet extends HttpServlet {
	DBUtil dbUtil = new DBUtil();
	StudentDao studentDao = new StudentDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String delIds=request.getParameter("delIds");
		Connection con=null;
		try{
			con=dbUtil.GetCon();
			JSONObject result=new JSONObject();
			int delNums=studentDao.studentDelete(con, delIds);
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
