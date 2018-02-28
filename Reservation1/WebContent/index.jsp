<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-confirm/3.3.0/jquery-confirm.min.js"></script>
	
<script>
function todaybap() {
	console.log("밥밥")
    $.get("${pageContext.request.contextPath}/findbap", function(data, status){
    	console.log(status);
    	$.alert({
    	    title: '오늘의 점심',
    	    content: data,
    	});
    });
}
// url ${pageContext.request.contextPath}/insertbap
function send(){
	console.log($("#inputmenu").val());
    $.post("${pageContext.request.contextPath}/insertbap",
    {
        name: $("#inputmenu").val()
    }, function(data, status) {
    	console.log(data);
    			var html = "";
    	        $.each(data, function(i, field){
    	        	console.log(i);
    	        	console.log(field.idx);
    	        	console.log(field.menu);
    	        	html += i + ". " + field.menu + "<br>";
    	        });
    	$("#menulist").html(html);
        //alert("Data: " + data + "\nStatus: " + status);
    });
}
</script>	
<title>밥 메뉴</title>
</head>
<body>
	<div class="container">
		<h2>점심 뭐먹지?</h2>
		<!-- Trigger the modal with a button -->
		<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
			data-target="#myModal">메뉴 추가</button>
		<button type="button" class="btn btn-info btn-lg" onclick="todaybap()">점심뭐먹지</button>

		<div id="menulist"></div>
		<!-- Modal Start -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">메뉴추가</h4>
					</div>
					<div class="modal-body">
<!-- 						<form class="form-inline"> -->
							<div class="form-group">
								<label class="sr-only" for="exampleInputEmail3">메뉴 이름</label> 
								<input type="text" class="form-control"
									id="inputmenu" placeholder="메뉴 입력">
							</div>
						
							<button class="btn btn-default" onclick="send()">추가</button>
<!-- 						</form> -->
					</div>
					<div class="modal-footer">
						
					</div>
				</div>

			</div>
		</div>
		<!-- Modal End -->
	</div>
</body>
</html>