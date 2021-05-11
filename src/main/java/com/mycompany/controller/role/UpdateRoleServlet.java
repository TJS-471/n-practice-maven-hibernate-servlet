package main.java.com.mycompany.controller.role;

import main.java.com.mycompany.dto.RoleDto;
import main.java.com.mycompany.service.RoleService;
import main.java.com.mycompany.service.impl.RoleServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A web servlet class that extends {@link HttpServlet}.
 * It exposes the endpoint to handle GET and POST requests from a client to update the existing role.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet("/admin/update_role")
public class UpdateRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 8495952877098815301L;
	private RoleService service = new RoleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String updatePage = "/WEB-INF/role/role_form.jsp";
		long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		RoleDto role = new RoleDto(id, name);
		req.setAttribute("role", role);
		req.getRequestDispatcher(updatePage).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String listUrl = req.getContextPath() + "/admin/list_roles";
		RoleDto role = new RoleDto();
		long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		role.setId(id);
		role.setName(name);
		service.update(role);
		resp.sendRedirect(listUrl);
	}
}
