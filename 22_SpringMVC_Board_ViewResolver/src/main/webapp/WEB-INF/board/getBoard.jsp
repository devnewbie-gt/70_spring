<%@page import="com.spring.biz.board.BoardVO"%>
<%@page import="com.spring.biz.board.impl.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 전달받은 값(seq)을 사용하여 DB 데이터를 조회하고 화면에 표시 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 상세</title>
<style type="text/css">
	#container {
		width: 700px;
		margin: 0 auto;
	}
	
	h1 { text-align: center; }
	table {	border-collapse: collapse; }
	table, th, td {
		border: 1px solid black;
		margin: 0 auto;
	}
	th { background-color: orange; }
	
	.center {
		text-align: center;
	}
	
	h1, h3, p { text-align: center; }
	
	.border-none, .border-none td { border: none; }
</style>
</head>
<body>

<div id="container">
	
	<h1>굴 상세</h1>
	<p><a href="redirect:logout.do">Log-out</a></p>
	<hr>
	
	<form action="updateBoard.do" method="post">
		<input type="hidden" name="seq" value="${board.seq }">
		<table>
			<tr>
				<th width="70">제목</th>
				<td><input type="text" name="title" size="30" value="${board.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${board.writer }"></td>
			</tr>
			<tr>
				<th>등록일</th>
				<td>${board.regdate }</td>
			</tr>
			<tr>
				<th>조회수</th>
				<td>${board.cnt }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="10" cols="40">${board.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2" class="center">
					<input type="submit" value="글 수정">
				</td>
			</tr>
		</table>
	</form>
	
	<p>
		<a href="insertBoard">글 등록</a>
		<a href="redirect:deleteBoard.do?seq=${board.seq }">글 삭제</a>
		<a href="redirect:getBoardList.do">글 목록 가기</a>
	</p>
	
</div>

</body>
</html>