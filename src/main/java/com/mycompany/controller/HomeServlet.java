package main.java.com.mycompany.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A web servlet class that extends {@link HttpServlet}.
 * It exposes the endpoint to handle a GET request from a client to show the home page.
 * @author Julia Tsukanova
 * @version 1.0
 */
@WebServlet("")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 2584874911297182874L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String homePage = "/WEB-INF/index.jsp";
		request.getRequestDispatcher(homePage).forward(request, response);
	}
}
