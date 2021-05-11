package main.java.com.mycompany.controller;

import main.java.com.mycompany.dto.UserDto;
import main.java.com.mycompany.service.UserService;
import main.java.com.mycompany.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A web servlet class that extends {@link HttpServlet}.
 * It exposes the endpoint to handle GET and POST requests from a client to authenticate.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -4321732426984401729L;
	private UserService service = new UserServiceImpl();
	private static final String ADMIN_ROLE = "Admin";
	private static final String USER_ROLE = "User";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = "/WEB-INF/admin/user_login.jsp";
		String errorMessage = req.getParameter("message");
		if(errorMessage != null) {
		req.setAttribute("errorMessage", errorMessage);
		}
		req.getRequestDispatcher(login).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		if(password != null && !password.isEmpty()) {
			password = password.trim();
		}
		String dest = "";
		// get user from a db
		UserDto userExists = service.getByLogin(login);
		// check if login and password match
		if (userExists == null || !userExists.getPassword().equals(password)) {
			String errorMessage = "Invalid username or password.";
			req.setAttribute("errorMessage", errorMessage);
			dest = "/WEB-INF/admin/user_login.jsp";
		}
		// check his roles
		else {
			List<String> roles = userExists.getRoles();
			if (roles != null && roles.size() > 0) {
				String role = roles.get(0);
				if (role.equals(USER_ROLE)) {
					req.getSession().setAttribute("login", login);
					req.setAttribute("user", userExists);
					dest = "/WEB-INF/user/user_page.jsp";
				} else if (role.equals(ADMIN_ROLE)) {
					req.getSession().setAttribute("login", login);
					dest = "/admin/list_users";
				}
			}
		}
		// redirect to the appropriate page
		req.getRequestDispatcher(dest).forward(req, resp);
	}

}
