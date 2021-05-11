package main.java.com.mycompany.controller.admin;

import main.java.com.mycompany.dto.UserDto;
import main.java.com.mycompany.service.UserService;
import main.java.com.mycompany.service.impl.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * A web servlet class that extends {@link HttpServlet}.
 * It exposes the endpoint to handle a GET request from a client to list all users.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet(urlPatterns = { "/admin/list_users" })
public class ListUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 4085180157534705398L;
	private UserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String listPage = "/WEB-INF/admin/users_list.jsp";
		String login = (String) req.getSession().getAttribute("login");
		UserDto userByLogin = service.getByLogin(login);
		String lastName = userByLogin.getLastName();
		req.setAttribute("lastName", lastName);
		List<String> roles = userByLogin.getRoles();
		for (String role : roles) {
			req.setAttribute("role", role);
		}
		List<UserDto> usersList = service.getAll();
		Map<Long, Integer> ages = new HashMap<>();
		for (UserDto user : usersList) {
			Date birthDate = user.getBirthDate();
			Period period = Period.between(birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
					LocalDate.now());
			int age = period.getYears();
			ages.put(user.getId(), age);
		}
		req.setAttribute("ages", ages);
		req.setAttribute("usersList", usersList);
		req.getRequestDispatcher(listPage).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
