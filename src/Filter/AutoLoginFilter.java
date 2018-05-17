package Filter;

import Bean.User;
import Dao.UserDao;
import Util.DBUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class AutoLoginFilter implements Filter {
	DBUtil dbUtil = new DBUtil();
	UserDao userDao = new UserDao();

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

		// 造型对象  
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 1.首先判断sesion中有没有admin  
		Object object = request.getSession().getAttribute("currentUser");
		// 如果session中有用户  
		if (object != null) {
			// 跳转到成功登录的界面  
			request.getRequestDispatcher("main.jsp").forward(request, response);
			return;
		}
		/*---------------------------以下是当session中不存在currentUser信息时候的处理---------------------------------*/
		// 2.判断cookie中是否存在 autologin标识符 的cookie对象  
		// 声明cookie  
		Cookie autoCookie = null;
		// 获取所有的cookie  
		Cookie cookies[] = request.getCookies();
		// 如果没有cookie信息,就继续执行login.do,跳转到login.jsp页面  
		if (cookies != null) {
			// 如果有,就遍历cookie  
			for (Cookie cookie : cookies) {
				// 判断cookie中是否有autologin标识符的cookie  
				if ("autologin".equals(cookie.getName())) {
					autoCookie = cookie; // 如果有 就赋值给临时变量autoCookie  
				}
			}

			// 3. 判断autoCookie是否等于null  
			if (autoCookie == null) {
				// 如果等于null,则继续执行login.jsp页面  
				chain.doFilter(request, response);
				return;
			}

			// 3.如果autoCookie不等于null,就判断cookie的值  
			// 获取cookie值  
			String value = autoCookie.getValue();
			// 拆分cookie的值  
			String temp[] = value.split(":");
			// 判断长度 是否等于自己拼接的长度
			if (temp.length != 3) {
				// 如果不等于3,则继续执行login.jsp页面  
				chain.doFilter(request, response);
				return;
			}

			// 获取cookie拆分的各个值  
			String name = temp[0]; // 用户名  
			String time = temp[1];// 获取有效时间  
			String service_md5Value = temp[2];// 获取md5的加密后的字符

			// 4.判断cookie是否失效  
			if (Long.valueOf(time) <= System.currentTimeMillis()) {
				// 如果失效,则继续执行login.jsp页面  
				chain.doFilter(request, response);
				return;
			}

			// 5.如果cookie没有失效,根据用户名,去查询用户信息  
			try {
				Connection connection = dbUtil.GetCon();
				// 判断用户是否为null
				User user = userDao.login(connection, name);
				if (user == null) {
					// 如果没有查询的用户,则继续执行login.jsp页面
					chain.doFilter(request, response);
					return;
				}
				// 按照服务器拼接的字符的方式,拼接md5加密的字符串
				String md5Temp = user.getUserName() + ":" + user.getPassword() + ":" + time;
				// 判断md5加密后和服务器端加密的字符是否相等
				if (!md5Temp.equals(service_md5Value)) {
					// 在不相等的情况下,则继续执行login.jsp页面
					chain.doFilter(request, response);
					return;
				}
				// 如果满足了cookie取值判断的所有结果,则跳转到成功登录的界面.
				request.getSession().setAttribute("admin", user);
				request.getRequestDispatcher("./sc.jsp").forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			// 在没有cookie信息的时候,则继续login.jsp页面
			chain.doFilter(request, response);
			return;
		}
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}
}  

