package com.test.reservation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.reservation")
public class reservationFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ReservationDTO reservationDTO;
	public ReservationDAO reservationDAO;
	
	public reservationFrontController() {
		reservationDTO = new ReservationDTO();
		reservationDAO = new ReservationDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		if(command.equals("/register.reservation")) {
			String[] sCheck = request.getParameterValues("ch");
			
			for(int i=0; i<sCheck.length;i++) {
				reservationDTO.setCh(sCheck[i]);
				reservationDAO.reservationInsert(reservationDTO);
			}
			String s="";
			for(String c:sCheck)

				s+=c+" ";
			out.print("선택자석:"+s);
			}
			
		}

}
