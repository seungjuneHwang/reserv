package com.test.bap;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class FindAllBap
 */
@WebServlet(name = "findbap", urlPatterns = { "/findbap" })
public class FindAllBap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAllBap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		InertBapDAO ib = new InertBapDAO();
		ArrayList<BapBean> list = ib.bapSearch();
		response.setContentType("text/html; charset=UTF-8");
		Random rnd = new Random();
		int rNum = rnd.nextInt(list.size());
		System.out.println(rNum);
		BapBean bb = list.get(rNum);
		System.out.println(bb.getMenu());
//		request.setAttribute("menu", bb.getMenu());
		PrintWriter out = response.getWriter();
//		Gson gson = new Gson();
//		String str = gson.toJson(list);
		out.print(bb.getMenu());
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
