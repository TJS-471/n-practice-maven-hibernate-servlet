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
 * It exposes the endpoint to handle a GET request from a client to delete a role.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet("/admin/delete_role")
public class DeleteRoleServlet extends HttpServlet {
	private static final long serialVersionUID = -3332712910319283010L;
	private RoleService service = new RoleServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String listUrl = req.getContextPath() + "/admin/list_roles";
		long id = Long.parseLong(req.getParameter("id"));
		String roleName = req.getParameter("name");
		RoleDto roleDto = new RoleDto(id, roleName);
		service.remove(roleDto);
		resp.sendRedirect(listUrl);
	}

}
