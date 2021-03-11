<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>시작 페이지</title>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
	function ajaxGetBoardList(){
		alert(">> ajaxGetBoardList() 실행~~");
		$.ajax("ajaxGetBoardList.do", {
			type: "get",
			dataType: "json",
			success : function(returnValue){
				alert("성공~~~");
				console.log(returnValue);
				
				var dispHtml ="<ul>";
				
				$.each(returnValue, function(index, obj){
					dispHtml += "<li>";
					
					dispHtml += this.seq + ", ";	// this : 현재 처리하는 객체(returnValue, obj)
					dispHtml += this.title + ", ";
					dispHtml += this["writer"] + ", ";
					dispHtml += this["regdate"] + ", ";
					dispHtml += obj["cnt"] + ", ";
					
					dispHtml += "</li>";
					
				});
				
				dispHtml +="</ul>"
				$("#dispData").append(dispHtml);
			},
			error : function(){
				alert("무슨무슨 이유로 실패했습니다");
			}
		});
	}
</script>
</head>
<body>

<div id="container">
	<h1>게시판 프로그램</h1>
	<hr>
	<p><a href="login.do">로그인 페이지로 이동(GET)</a></p>
	<hr>
	<p><a href="javascript:ajaxGetBoardList()">게시글 목록 가져오기(Ajax - JSON)</a></p>
</div>
<hr>
<div id="dispData">
	<ul>
		<li>데이터를 가져와서 출력하기</li>
	</ul>
</div>

</body>
</html>