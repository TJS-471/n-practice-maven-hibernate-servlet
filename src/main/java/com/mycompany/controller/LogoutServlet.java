package main.java.com.mycompany.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * A web servlet class that extends {@link HttpServlet}.
 * It exposes the endpoint to handle a GET request from a client to logout.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = -4317809857385134592L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String dest = "/WEB-INF/admin/user_login.jsp";
		String logoutMessage = "You have been logged out.";
		HttpSession session = req.getSession();
		session.removeAttribute("login");
		req.setAttribute("logoutMessage", logoutMessage);
		req.getRequestDispatcher(dest).forward(req, resp);
	}

}
