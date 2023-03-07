<%@page import="mul.cam.a.dto.PdsDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
List<PdsDto> list = (List<PdsDto>)request.getAttribute("pdslist"); //짐풀기
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자료실</title>
</head>
<body>
<h1 align="center">자료실</h1>
<hr>
<div align="center">
<table border="1">
<col width="50"><col width="100"><col width="300"><col width="50">
<col width="50"><col width="50"><col width="100"><!-- <col width="50"> -->
<thead>
<tr>
	<th>번호</th><th>작성자</th><th>제목</th><th>다운로드</th>
	<th>조회수</th><th>다운수</th><th>작성일</th><!-- <th>삭제</th> -->
</tr>
</thead>
<tbody>
<%
	for(int i = 0; i< list.size(); i++){
		PdsDto pds = list.get(i);
%>
	<tr>
		<th><%=i + 1 %></th>
		<td><%=pds.getId() %></td>
		<td><%=pds.getTitle() %></td>
		<td>
			<input type="button" value="download" 
			  onclick="filedown(<%=pds.getSeq() %>, '<%=pds.getNewfilename() %>', '<%=pds.getFilename() %>')">
			  <!--filedown함수호출 download count늘리기           seq랑 문자 ' '잘 확인하고 붙히기              -->
			  <!-- js함수에 값 넣기 -->
		</td>
		<td><%=pds.getReadcount()%></td>	
		<td><%=pds.getDowncount() %></td>	
		<td><%=pds.getRegdate() %></td>	
	</tr>
<%
	} 
%>		 

</tbody>

</table>
<button type="button" onclick="pdsWrite()">자료추가</button>
</div>
<!--다운로드 버튼 클릭시 밑에 js post로 가게하는거  -->
<form name="file_down" action="filedownLoad.do" method="post"><!-- filedownLoadview로가라 -->
	<input type="hidden" name="newfilename"> 
	<input type="hidden" name="filename">
	<input type="hidden" name="seq">
</form>

<script type="text/javascript">
function pdsWrite() {
	location.href = "pdswrite.do"; //get으로감
	
}
//
//dowm은 post로 보내는게 기본    file_down에 ~ value안에 = ~매개변수를 넣어라
function filedown(seq, newfilename, filename) {
	document.file_down.newfilename.value = newfilename; //input이라 .value 붙혀줌
	document.file_down.filename.value  = filename;
	document.file_down.seq.value  = seq;
	document.file_down.submit();
}
</script>


</body>
</html>