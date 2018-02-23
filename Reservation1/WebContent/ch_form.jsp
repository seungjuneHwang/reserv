<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.test.reservation.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
%>

<%--  <%

	request.setCharacterEncoding("utf-8");



    String _row=request.getParameter("row");

    String _col=request.getParameter("col");

    

    int row=10, col=15;

    if(_row!=null)

    	row=Integer.parseInt(_row);

    if(_col!=null)

    	col=Integer.parseInt(_col);

    

    int w=30+col*30+col/5*20;

    if(col%5==0)

    	w-=20;

%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

*{
	padding: 0px; margin: 0px; font-size: 9pt;
}

td {
	font-size: 9pt;font-family: 돋움;
}
</style>

<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.3.min.js"></script>

<script type="text/javascript">

function send(row, col) {

	console.log(row);
	console.log(col);
	var f=document.forms[0];

	 // 자바스트립트에서 표현식 사용 가능
// 	var row="20";
// 	var col="30";
	var n=0;

	for(var i=0; i<row*col; i++) {
		if(f.ch[i].checked==true)
			n++;
	}

	if(n<1 || n>1) {
		alert("좌석은 1개 까지 가능 합니다.");
		return;
	}
	f.submit();
}

</script>

<script type="text/javascript">

// $("#ch").change(function(){
// 	location.reload();
// 	//setTimeout(function(){ location.reload(); }, 3000);
// });

$( document ).ready(function() {
// 	setTimeout(function(){ location.reload(); }, 5000);
});

</script>
</head>
<body>
<br/><br/>
<%
//ArrayList<ReservationDTO> list = (ArrayList<ReservationDTO>)request.getAttribute("chlist");
HashMap<String, ReservationDTO> map = (HashMap<String, ReservationDTO>)request.getAttribute("chlist");
// String [] strX = 
// ArrayList<Integer> listX = new ArrayList<Integer>();
// ArrayList<Integer> listY = new ArrayList<Integer>();

int x = 0;  // 행렬 나누기
int y = 0;  // 행렬 나누기

for(Map.Entry<String, ReservationDTO> entry : map.entrySet()) {
    String key = entry.getKey();
    //System.out.println(key);
    ReservationDTO value = entry.getValue();
    String [] sp = value.getCh().split(":");
	x = Integer.parseInt(sp[0]);
	y = Integer.parseInt(sp[1]);
}
// for (ReservationDTO bb : list) {
// 	String [] sp = bb.getCh().split(":");
// 	listX.add(Integer.parseInt(sp[0]) );
// 	listX.add(Integer.parseInt(sp[1]) );
// // 	out.println(bb.getCh()+"<br> ");
// }

//  int x = Collections.max(listX).intValue();  // 행렬 나누기
// int y = Collections.max(listY).intValue();  // 행렬 나누기

%>


<form action="ok.reservation" method="post">
<table width="1030">
<tr height="30">

<%
	out.println("<td width='30'>&nbsp;</td>");

    for(int i=1; i<=x; i++) {
    	if(i!=1 && i%5==1) {
    		out.println("<td width='20'>&nbsp;</td>");
    	}
    	out.println("<td width='30' align='center'>"+i+"</td>");
    }
%>

</tr>

<%
    String s;
	for(int i=1; i<=x; i++) {
		out.println("<tr height='25'>");
		out.println("<td align='center'>"+i+"</td>");
		for(int j=1; j<=y; j++) {
			if(j!=1 && j%5==1) {
				out.println("<td width='20' bgcolor='green'>&nbsp;</td>");
			}
				out.print("<td width='30' align='center'>");
			s = i + ":" + j;
			//out.println(s);
			ReservationDTO res = map.get(s);
			//System.out.println(s);
			if (res.getIsch() == 0) {
				out.println(res.getIsch());
				out.print("<input type='checkbox' name='ch'  value='"+ s+"' >");
			} else {
				out.println(res.getIsch());
				out.print("<input type='checkbox' name='ch' disabled=true  value='"+ s+"' >");
			}
			
			out.println("</td>");
		}
		out.println("</tr>");
	}
%>
</table>


<table width="1030">
<tr height="50">
      <td align="left">
           <input type="button" value="좌석예약"
                 onclick="send(<%=x %>, <%=y %> );">
      </td>
</tr>

</table>

</form>
</body>
</html>