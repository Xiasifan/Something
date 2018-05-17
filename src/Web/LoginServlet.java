package Web;

import Bean.User;
import Dao.UserDao;
import Util.DBUtil;
import Util.String_util;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import java.sql.Connection;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
	UserDao userDao = new UserDao();
	DBUtil dbUtil = new DBUtil();
	private long expires = 24 * 60 * 60; // 1天
	String_util string_util = new String_util();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String mark = request.getParameter("mark");
		String oper = request.getParameter("oper");
		request.setAttribute("userName",userName);
		request.setAttribute("password",password);
		if (string_util.isEmpty(userName) || string_util.isEmpty(password)) {

			request.setAttribute("error","请输入账号和密码");
			request.getRequestDispatcher("index.jsp").forward(request,response);
			return;
		}
		User user = new User(userName, password);
		try {
			Connection connection = dbUtil.GetCon();
			User currentUser=userDao.login(connection, user);

			if (currentUser == null) {
				request.setAttribute("error", "账号或密码错误");
				request.getRequestDispatcher("index.jsp").forward(request, response);

			} else {
				if ("mark".equals(mark)) {
					// 获取默认记住的天数
					String day = request.getParameter("day");
					// 转换成有效的时间
					expires = Integer.valueOf(day) * expires;
					// 声明cookie
					Cookie autoCookie = null;
					// 获取所有cookie
					Cookie cookies[] = request.getCookies();
					// 遍历cookie
					for (Cookie cookie : cookies) {
						// 判断是否存在自动登陆记录
						if ("autologin".equals(cookie.getName())) {
							autoCookie = cookie; // 赋值

							// 当cookie存在的时候,我需要重新设置值
							long time = (System.currentTimeMillis() + expires * 1000);
							//cookie拼接的value值,(可以根据自己的想法设计)
							String newValue = userName + ":" + time + ":";
							//设置值
							autoCookie .setValue(newValue);
						} else {
							// 不存在创建
							// name+":"+time+":"+md5(name:pass:time)
							long time = System.currentTimeMillis() + expires * 1000;
							//cookie拼接的value值,(可以根据自己的想法设计)
							String cookieValue = userName + ":" + time + ":";
							//创建cookie
							autoCookie = new Cookie("autologin", cookieValue);

						}
					}

					autoCookie.setMaxAge((int) expires);
					autoCookie.setPath("/StudentInfoMange");
					// 添加cookie
					response.addCookie(autoCookie);

				}
				//验证码
				String clientCheckcode = request.getParameter("validateCode");//接收客户端浏览器提交上来的验证码
				String serverCheckcode = (String) request.getSession().getAttribute("checkcode");//从服务器端的session中取出验证码
				if (clientCheckcode.equals(serverCheckcode)) {//将客户端验证码和服务器端验证比较，如果相等，则表示验证通过

				}else {
					request.setAttribute("error","验证码错误！！");
					request.getRequestDispatcher("index.jsp").forward(request,response);
				}
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", currentUser);
				request.getRequestDispatcher("main.jsp").forward(request,response);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}


	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


}
