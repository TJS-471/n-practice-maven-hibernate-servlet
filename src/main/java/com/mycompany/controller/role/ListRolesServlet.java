package main.java.com.mycompany.controller.role;

import main.java.com.mycompany.dto.RoleDto;
import main.java.com.mycompany.dto.UserDto;
import main.java.com.mycompany.service.RoleService;
import main.java.com.mycompany.service.UserService;
import main.java.com.mycompany.service.impl.RoleServiceImpl;
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
 * It exposes the endpoint to handle a GET request from a client to list all roles.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet(urlPatterns = { "/admin/list_roles" })
public class ListRolesServlet extends HttpServlet {
	private static final long serialVersionUID = 4672764955746030026L;
	private RoleService service = new RoleServiceImpl();
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String listPage = "/WEB-INF/role/roles_list.jsp";
		String login = (String) req.getSession().getAttribute("login");
		UserDto userByLogin = userService.getByLogin(login);
		String lastName = userByLogin.getLastName();
		req.setAttribute("lastName", lastName);
		List<String> roles = userByLogin.getRoles();
		for(String role : roles) {
			req.setAttribute("role", role);
		}
		List<RoleDto> roleList = service.getAll();
		req.setAttribute("roleList", roleList);
		req.getRequestDispatcher(listPage).forward(req, resp);
	}

}
