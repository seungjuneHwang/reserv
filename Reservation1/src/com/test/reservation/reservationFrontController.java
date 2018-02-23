package com.test.reservation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		if (command.equals("/register.reservation")) {
			String[] sCheck = request.getParameterValues("ch");

			for (int i = 0; i < sCheck.length; i++) {
				reservationDTO.setCh(sCheck[i]);
				reservationDAO.reservationInsert(reservationDTO);
			}
			String s = "";
			for (String c : sCheck) {
				s += c + " ";
			}
			out.print("선택자석:" + s);
		} else if (command.equals("/select.reservation")) {
//			ArrayList<ReservationDTO> list = reservationDAO.reservationStatus();
			HashMap<String, ReservationDTO> map = reservationDAO.reservationStatusMap();
//			request.setAttribute("chlist", list);
			request.setAttribute("chlist", map);
			// 확인용 테스트 코드
			for(Map.Entry<String, ReservationDTO> entry : map.entrySet()) {
			    String key = entry.getKey();
			    //System.out.println(key);
			    ReservationDTO value = entry.getValue();
			    //System.out.println(value.getCh());
			}
//			for (ReservationDTO bb : list) {
//				System.out.println(bb.getCh());  
//			}
			String site = "/ch_form.jsp";
			RequestDispatcher dis = request.getRequestDispatcher(site);
			dis.forward(request, response);
			
		} else if (command.equals("/init.reservation")) {
			String row = request.getParameter("row");
			String col = request.getParameter("col");
			int x = 0;
			int y = 0;
			if (row != null && col != null) {
				x = Integer.parseInt(row);
				y = Integer.parseInt(col);
				reservationDAO.reservationDrop();
				reservationDAO.reservationCreate();
			}
			
			ArrayList<String> strList = new ArrayList<>();
			for(int i=1; i<=x; i++) {
				for(int j=1; j<=y; j++) {
					String s = i + ":" + j;
					strList.add(s);
				}
			}
			
			for (String s: strList) {
				reservationDTO.setCh(s);
				reservationDAO.reservationInsert(reservationDTO);
			}
//			String site = "/select.reservation";
//			RequestDispatcher dis = request.getRequestDispatcher(site);
//			dis.forward(request, response);
			String site = request.getContextPath() + "/select.reservation";
			response.sendRedirect(site);
			
		} else if (command.equals("/ok.reservation")) {
			String sCheck = request.getParameter("ch");
			System.out.println(sCheck);
			reservationDTO.setCh(sCheck);
			reservationDTO.setIsch(1);
			reservationDAO.reservationUpdate(reservationDTO);
			
			String site = request.getContextPath() + "/select.reservation";
			response.sendRedirect(site);
			//out.print("선택자석:" + sCheck);
		}
	}

}
