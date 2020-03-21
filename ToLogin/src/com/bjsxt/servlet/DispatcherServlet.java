package com.bjsxt.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjsxt.controller.LoginController;

public class DispatcherServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestURI = req.getRequestURI();
		if("/ToLogin/login.do".equals(requestURI)){
			try {
				toLogin(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void toLogin(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		Class<?> c1 = Class.forName("com.bjsxt.controller.LoginController");
		LoginController lc = (LoginController) c1.newInstance();
		Method m = c1.getDeclaredMethod("login", HttpServletRequest.class);
		String str = (String) m.invoke(lc,req);

		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(str);
		out.flush();
		out.close();
	}
}
