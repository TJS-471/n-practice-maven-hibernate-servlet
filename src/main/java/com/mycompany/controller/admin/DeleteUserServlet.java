package main.java.com.mycompany.controller.admin;

import main.java.com.mycompany.service.UserService;
import main.java.com.mycompany.service.impl.UserServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A web servlet class that extends {@link HttpServlet}.
 * It exposes the endpoint to handle a GET request from a client to delete a user.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet("/admin/delete_user")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = -8335596485258034581L;
	private UserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String listUrl = req.getContextPath() + "/admin/list_users";
		long id = Long.parseLong(req.getParameter("id"));
		service.remove(id);
		resp.sendRedirect(listUrl);
	}

}
