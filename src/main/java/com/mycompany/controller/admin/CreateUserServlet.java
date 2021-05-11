package main.java.com.mycompany.controller.admin;

import main.java.com.mycompany.dto.RoleDto;
import main.java.com.mycompany.dto.UserDto;
import main.java.com.mycompany.service.RoleService;
import main.java.com.mycompany.service.UserService;
import main.java.com.mycompany.service.impl.RoleServiceImpl;
import main.java.com.mycompany.service.impl.UserServiceImpl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * A web servlet class that extends {@link HttpServlet}.
 * It exposes the endpoint to handle GET and POST requests from a client to create a new user.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet("/admin/create_user")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 7422595120679228599L;
	private UserService service = new UserServiceImpl();
	private RoleService serviceRole = new RoleServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String createPage = "/WEB-INF/admin/user_form.jsp";
		List<RoleDto> rolesList = serviceRole.getAll();
		req.setAttribute("rolesList", rolesList);
		req.getRequestDispatcher(createPage).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String listUrl = req.getContextPath() + "/admin/list_users";

		String login = req.getParameter("login");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		Date birthDate = null;
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			birthDate = dateFormat.parse(req.getParameter("birthDate"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String roleName = req.getParameter("name");
		List<String> roles = new ArrayList<>();
		roles.add(roleName);
		UserDto userDto = new UserDto(login, email, password, firstName, lastName, birthDate, roles);
		service.save(userDto);
		resp.sendRedirect(listUrl);
	}

}
